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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INT", "RULE_STRING", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'RenderingLibrary'", "'{'", "','", "'}'", "'RenderingRef'", "'placementData'", "'styles'", "'Ellipse'", "':'", "'childPlacement'", "'children'", "'Rectangle'", "'RoundedRectangle'", "'Polyline'", "'Polygon'", "'Image'", "'-'", "'Arc'", "'ChildArea'", "'Text'", "'clip'", "'CustomRendering'", "'className'", "'bundleName'", "'Spline'", "'DecoratorPlacementData'", "'relative'", "'location'", "'xOffset'", "'yOffset'", "'width'", "'height'", "'GridPlacementData'", "'widthHint'", "'heightHint'", "'insetRight'", "'insetBottom'", "'insetLeft'", "'insetTop'", "'StackPlacementData'", "'DirectPlacementData'", "'topLeft'", "'bottomRight'", "'PolylinePlacementData'", "'points'", "'detailedPlacementData'", "'/'", "'left'", "'right'", "'top'", "'bottom'", "'ForegroundColor'", "'!'", "'BackgroundColor'", "'LineWidth'", "'ForegroundVisibility'", "'BackgroundVisibility'", "'LineStyle'", "'bold'", "'italic'", "'font'", "'fontSize'", "'VerticalAlignment'", "'HorizontalAlignment'", "'GridPlacement'", "'StackPlacement'", "'.'", "'E'", "'e'", "'true'", "'false'", "'KInsets'", "'KPoint'", "'x'", "'y'", "'='", "'SOLID'", "'DASH'", "'DOT'", "'DASHDOT'", "'DASHDOTDOT'", "'TOP'", "'CENTER'", "'BOTTOM'", "'LEFT'", "'RIGHT'"
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:77:1: ruleKRenderingLibrary returns [EObject current=null] : ( () otherlv_1= 'RenderingLibrary' otherlv_2= '{' ( ( (lv_renderings_3_0= ruleKRendering ) ) ( (otherlv_4= ',' )? ( (lv_renderings_5_0= ruleKRendering ) ) )* )? otherlv_6= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:80:28: ( ( () otherlv_1= 'RenderingLibrary' otherlv_2= '{' ( ( (lv_renderings_3_0= ruleKRendering ) ) ( (otherlv_4= ',' )? ( (lv_renderings_5_0= ruleKRendering ) ) )* )? otherlv_6= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:81:1: ( () otherlv_1= 'RenderingLibrary' otherlv_2= '{' ( ( (lv_renderings_3_0= ruleKRendering ) ) ( (otherlv_4= ',' )? ( (lv_renderings_5_0= ruleKRendering ) ) )* )? otherlv_6= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:81:1: ( () otherlv_1= 'RenderingLibrary' otherlv_2= '{' ( ( (lv_renderings_3_0= ruleKRendering ) ) ( (otherlv_4= ',' )? ( (lv_renderings_5_0= ruleKRendering ) ) )* )? otherlv_6= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:81:2: () otherlv_1= 'RenderingLibrary' otherlv_2= '{' ( ( (lv_renderings_3_0= ruleKRendering ) ) ( (otherlv_4= ',' )? ( (lv_renderings_5_0= ruleKRendering ) ) )* )? otherlv_6= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:81:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:82:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRenderingLibraryAccess().getKRenderingLibraryAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleKRenderingLibrary131); 

                	newLeafNode(otherlv_1, grammarAccess.getKRenderingLibraryAccess().getRenderingLibraryKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRenderingLibrary143); 

                	newLeafNode(otherlv_2, grammarAccess.getKRenderingLibraryAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:95:1: ( ( (lv_renderings_3_0= ruleKRendering ) ) ( (otherlv_4= ',' )? ( (lv_renderings_5_0= ruleKRendering ) ) )* )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==15||LA3_0==18||(LA3_0>=22 && LA3_0<=26)||(LA3_0>=28 && LA3_0<=30)||LA3_0==32||LA3_0==35) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:95:2: ( (lv_renderings_3_0= ruleKRendering ) ) ( (otherlv_4= ',' )? ( (lv_renderings_5_0= ruleKRendering ) ) )*
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:113:2: ( (otherlv_4= ',' )? ( (lv_renderings_5_0= ruleKRendering ) ) )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==13||LA2_0==15||LA2_0==18||(LA2_0>=22 && LA2_0<=26)||(LA2_0>=28 && LA2_0<=30)||LA2_0==32||LA2_0==35) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:113:3: (otherlv_4= ',' )? ( (lv_renderings_5_0= ruleKRendering ) )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:113:3: (otherlv_4= ',' )?
                    	    int alt1=2;
                    	    int LA1_0 = input.LA(1);

                    	    if ( (LA1_0==13) ) {
                    	        alt1=1;
                    	    }
                    	    switch (alt1) {
                    	        case 1 :
                    	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:113:5: otherlv_4= ','
                    	            {
                    	            otherlv_4=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRenderingLibrary179); 

                    	                	newLeafNode(otherlv_4, grammarAccess.getKRenderingLibraryAccess().getCommaKeyword_3_1_0());
                    	                

                    	            }
                    	            break;

                    	    }

                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:117:3: ( (lv_renderings_5_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:118:1: (lv_renderings_5_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:118:1: (lv_renderings_5_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:119:3: lv_renderings_5_0= ruleKRendering
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:147:1: entryRuleKRendering returns [EObject current=null] : iv_ruleKRendering= ruleKRendering EOF ;
    public final EObject entryRuleKRendering() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRendering = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:148:2: (iv_ruleKRendering= ruleKRendering EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:149:2: iv_ruleKRendering= ruleKRendering EOF
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
            int alt4=12;
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
            case 25:
                {
                alt4=5;
                }
                break;
            case 26:
                {
                alt4=6;
                }
                break;
            case 28:
                {
                alt4=7;
                }
                break;
            case 15:
                {
                alt4=8;
                }
                break;
            case 29:
                {
                alt4=9;
                }
                break;
            case 30:
                {
                alt4=10;
                }
                break;
            case 32:
                {
                alt4=11;
                }
                break;
            case 35:
                {
                alt4=12;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:161:5: this_KEllipse_0= ruleKEllipse
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
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:171:5: this_KRectangle_1= ruleKRectangle
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
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:181:5: this_KRoundedRectangle_2= ruleKRoundedRectangle
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
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:191:5: this_KPolyline_Impl_3= ruleKPolyline_Impl
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
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:201:5: this_KPolygon_4= ruleKPolygon
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
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:211:5: this_KImage_5= ruleKImage
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
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:221:5: this_KArc_6= ruleKArc
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
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:231:5: this_KRenderingRef_7= ruleKRenderingRef
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
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:241:5: this_KChildArea_8= ruleKChildArea
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
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:251:5: this_KText_9= ruleKText
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
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:261:5: this_KCustomRendering_10= ruleKCustomRendering
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
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:271:5: this_KSpline_11= ruleKSpline
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKSplineParserRuleCall_11()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKSpline_in_ruleKRendering608);
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
            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_entryRuleKPlacementData643);
            iv_ruleKPlacementData=ruleKPlacementData();

            state._fsp--;

             current =iv_ruleKPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPlacementData653); 

            }

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
            int alt5=5;
            switch ( input.LA(1) ) {
            case 36:
                {
                alt5=1;
                }
                break;
            case 43:
                {
                alt5=2;
                }
                break;
            case 50:
                {
                alt5=3;
                }
                break;
            case 51:
                {
                alt5=4;
                }
                break;
            case 54:
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
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:301:5: this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKDecoratorPlacementDataParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKDecoratorPlacementData_in_ruleKPlacementData700);
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
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKGridPlacementData_in_ruleKPlacementData727);
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
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKStackPlacementData_in_ruleKPlacementData754);
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
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKDirectPlacementData_in_ruleKPlacementData781);
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
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKPolylinePlacementData_in_ruleKPlacementData808);
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
            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_entryRuleKStyle843);
            iv_ruleKStyle=ruleKStyle();

            state._fsp--;

             current =iv_ruleKStyle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKStyle853); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:366:1: ruleKStyle returns [EObject current=null] : (this_KForegroundColor_0= ruleKForegroundColor | this_KBackgroundColor_1= ruleKBackgroundColor | this_KLineWidth_2= ruleKLineWidth | this_KVisibility_3= ruleKVisibility | this_KLineStyle_4= ruleKLineStyle | this_KFontBold_5= ruleKFontBold | this_KFontItalic_6= ruleKFontItalic | this_KFontName_7= ruleKFontName | this_KFontSize_8= ruleKFontSize | this_KVerticalAlignment_9= ruleKVerticalAlignment | this_KHorizontalAlignment_10= ruleKHorizontalAlignment ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:369:28: ( (this_KForegroundColor_0= ruleKForegroundColor | this_KBackgroundColor_1= ruleKBackgroundColor | this_KLineWidth_2= ruleKLineWidth | this_KVisibility_3= ruleKVisibility | this_KLineStyle_4= ruleKLineStyle | this_KFontBold_5= ruleKFontBold | this_KFontItalic_6= ruleKFontItalic | this_KFontName_7= ruleKFontName | this_KFontSize_8= ruleKFontSize | this_KVerticalAlignment_9= ruleKVerticalAlignment | this_KHorizontalAlignment_10= ruleKHorizontalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:370:1: (this_KForegroundColor_0= ruleKForegroundColor | this_KBackgroundColor_1= ruleKBackgroundColor | this_KLineWidth_2= ruleKLineWidth | this_KVisibility_3= ruleKVisibility | this_KLineStyle_4= ruleKLineStyle | this_KFontBold_5= ruleKFontBold | this_KFontItalic_6= ruleKFontItalic | this_KFontName_7= ruleKFontName | this_KFontSize_8= ruleKFontSize | this_KVerticalAlignment_9= ruleKVerticalAlignment | this_KHorizontalAlignment_10= ruleKHorizontalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:370:1: (this_KForegroundColor_0= ruleKForegroundColor | this_KBackgroundColor_1= ruleKBackgroundColor | this_KLineWidth_2= ruleKLineWidth | this_KVisibility_3= ruleKVisibility | this_KLineStyle_4= ruleKLineStyle | this_KFontBold_5= ruleKFontBold | this_KFontItalic_6= ruleKFontItalic | this_KFontName_7= ruleKFontName | this_KFontSize_8= ruleKFontSize | this_KVerticalAlignment_9= ruleKVerticalAlignment | this_KHorizontalAlignment_10= ruleKHorizontalAlignment )
            int alt6=11;
            switch ( input.LA(1) ) {
            case 62:
                {
                alt6=1;
                }
                break;
            case 64:
                {
                alt6=2;
                }
                break;
            case 65:
                {
                alt6=3;
                }
                break;
            case 66:
            case 67:
                {
                alt6=4;
                }
                break;
            case 68:
                {
                alt6=5;
                }
                break;
            case 69:
                {
                alt6=6;
                }
                break;
            case 70:
                {
                alt6=7;
                }
                break;
            case 71:
                {
                alt6=8;
                }
                break;
            case 72:
                {
                alt6=9;
                }
                break;
            case 73:
                {
                alt6=10;
                }
                break;
            case 74:
                {
                alt6=11;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:371:5: this_KForegroundColor_0= ruleKForegroundColor
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKForegroundColorParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKForegroundColor_in_ruleKStyle900);
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
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKBackgroundColor_in_ruleKStyle927);
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
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKLineWidth_in_ruleKStyle954);
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
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKVisibility_in_ruleKStyle981);
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
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKLineStyle_in_ruleKStyle1008);
                    this_KLineStyle_4=ruleKLineStyle();

                    state._fsp--;

                     
                            current = this_KLineStyle_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:421:5: this_KFontBold_5= ruleKFontBold
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKFontBoldParserRuleCall_5()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKFontBold_in_ruleKStyle1035);
                    this_KFontBold_5=ruleKFontBold();

                    state._fsp--;

                     
                            current = this_KFontBold_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:431:5: this_KFontItalic_6= ruleKFontItalic
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKFontItalicParserRuleCall_6()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKFontItalic_in_ruleKStyle1062);
                    this_KFontItalic_6=ruleKFontItalic();

                    state._fsp--;

                     
                            current = this_KFontItalic_6; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 8 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:441:5: this_KFontName_7= ruleKFontName
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKFontNameParserRuleCall_7()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKFontName_in_ruleKStyle1089);
                    this_KFontName_7=ruleKFontName();

                    state._fsp--;

                     
                            current = this_KFontName_7; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 9 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:451:5: this_KFontSize_8= ruleKFontSize
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKFontSizeParserRuleCall_8()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKFontSize_in_ruleKStyle1116);
                    this_KFontSize_8=ruleKFontSize();

                    state._fsp--;

                     
                            current = this_KFontSize_8; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 10 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:461:5: this_KVerticalAlignment_9= ruleKVerticalAlignment
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKVerticalAlignmentParserRuleCall_9()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKVerticalAlignment_in_ruleKStyle1143);
                    this_KVerticalAlignment_9=ruleKVerticalAlignment();

                    state._fsp--;

                     
                            current = this_KVerticalAlignment_9; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 11 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:471:5: this_KHorizontalAlignment_10= ruleKHorizontalAlignment
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKHorizontalAlignmentParserRuleCall_10()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKHorizontalAlignment_in_ruleKStyle1170);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:487:1: entryRuleKPlacement returns [EObject current=null] : iv_ruleKPlacement= ruleKPlacement EOF ;
    public final EObject entryRuleKPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPlacement = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:488:2: (iv_ruleKPlacement= ruleKPlacement EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:489:2: iv_ruleKPlacement= ruleKPlacement EOF
            {
             newCompositeNode(grammarAccess.getKPlacementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_entryRuleKPlacement1205);
            iv_ruleKPlacement=ruleKPlacement();

            state._fsp--;

             current =iv_ruleKPlacement; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPlacement1215); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:496:1: ruleKPlacement returns [EObject current=null] : (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement ) ;
    public final EObject ruleKPlacement() throws RecognitionException {
        EObject current = null;

        EObject this_KGridPlacement_0 = null;

        EObject this_KStackPlacement_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:499:28: ( (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:500:1: (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:500:1: (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==75) ) {
                alt7=1;
            }
            else if ( (LA7_0==76) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:501:5: this_KGridPlacement_0= ruleKGridPlacement
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementAccess().getKGridPlacementParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKGridPlacement_in_ruleKPlacement1262);
                    this_KGridPlacement_0=ruleKGridPlacement();

                    state._fsp--;

                     
                            current = this_KGridPlacement_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:511:5: this_KStackPlacement_1= ruleKStackPlacement
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementAccess().getKStackPlacementParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKStackPlacement_in_ruleKPlacement1289);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:527:1: entryRuleKXPosition returns [EObject current=null] : iv_ruleKXPosition= ruleKXPosition EOF ;
    public final EObject entryRuleKXPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKXPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:528:2: (iv_ruleKXPosition= ruleKXPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:529:2: iv_ruleKXPosition= ruleKXPosition EOF
            {
             newCompositeNode(grammarAccess.getKXPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKXPosition_in_entryRuleKXPosition1324);
            iv_ruleKXPosition=ruleKXPosition();

            state._fsp--;

             current =iv_ruleKXPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKXPosition1334); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:536:1: ruleKXPosition returns [EObject current=null] : (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition ) ;
    public final EObject ruleKXPosition() throws RecognitionException {
        EObject current = null;

        EObject this_KLeftPosition_0 = null;

        EObject this_KRightPosition_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:539:28: ( (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:540:1: (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:540:1: (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==58) ) {
                alt8=1;
            }
            else if ( (LA8_0==59) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:541:5: this_KLeftPosition_0= ruleKLeftPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKXPositionAccess().getKLeftPositionParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKLeftPosition_in_ruleKXPosition1381);
                    this_KLeftPosition_0=ruleKLeftPosition();

                    state._fsp--;

                     
                            current = this_KLeftPosition_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:551:5: this_KRightPosition_1= ruleKRightPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKXPositionAccess().getKRightPositionParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRightPosition_in_ruleKXPosition1408);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:567:1: entryRuleKYPosition returns [EObject current=null] : iv_ruleKYPosition= ruleKYPosition EOF ;
    public final EObject entryRuleKYPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKYPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:568:2: (iv_ruleKYPosition= ruleKYPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:569:2: iv_ruleKYPosition= ruleKYPosition EOF
            {
             newCompositeNode(grammarAccess.getKYPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKYPosition_in_entryRuleKYPosition1443);
            iv_ruleKYPosition=ruleKYPosition();

            state._fsp--;

             current =iv_ruleKYPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKYPosition1453); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:576:1: ruleKYPosition returns [EObject current=null] : (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition ) ;
    public final EObject ruleKYPosition() throws RecognitionException {
        EObject current = null;

        EObject this_KTopPosition_0 = null;

        EObject this_KBottomPosition_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:579:28: ( (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:580:1: (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:580:1: (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==60) ) {
                alt9=1;
            }
            else if ( (LA9_0==61) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:581:5: this_KTopPosition_0= ruleKTopPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKYPositionAccess().getKTopPositionParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKTopPosition_in_ruleKYPosition1500);
                    this_KTopPosition_0=ruleKTopPosition();

                    state._fsp--;

                     
                            current = this_KTopPosition_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:591:5: this_KBottomPosition_1= ruleKBottomPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKYPositionAccess().getKBottomPositionParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKBottomPosition_in_ruleKYPosition1527);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:607:1: entryRuleKRenderingRef returns [EObject current=null] : iv_ruleKRenderingRef= ruleKRenderingRef EOF ;
    public final EObject entryRuleKRenderingRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRenderingRef = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:608:2: (iv_ruleKRenderingRef= ruleKRenderingRef EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:609:2: iv_ruleKRenderingRef= ruleKRenderingRef EOF
            {
             newCompositeNode(grammarAccess.getKRenderingRefRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRenderingRef_in_entryRuleKRenderingRef1562);
            iv_ruleKRenderingRef=ruleKRenderingRef();

            state._fsp--;

             current =iv_ruleKRenderingRef; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRenderingRef1572); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:616:1: ruleKRenderingRef returns [EObject current=null] : ( () otherlv_1= 'RenderingRef' ( ( ruleEString ) ) (otherlv_3= '{' (otherlv_4= 'placementData' ( (lv_placementData_5_0= ruleKPlacementData ) ) )? (otherlv_6= 'styles' otherlv_7= '{' ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* otherlv_11= '}' )? otherlv_12= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:619:28: ( ( () otherlv_1= 'RenderingRef' ( ( ruleEString ) ) (otherlv_3= '{' (otherlv_4= 'placementData' ( (lv_placementData_5_0= ruleKPlacementData ) ) )? (otherlv_6= 'styles' otherlv_7= '{' ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* otherlv_11= '}' )? otherlv_12= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:620:1: ( () otherlv_1= 'RenderingRef' ( ( ruleEString ) ) (otherlv_3= '{' (otherlv_4= 'placementData' ( (lv_placementData_5_0= ruleKPlacementData ) ) )? (otherlv_6= 'styles' otherlv_7= '{' ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* otherlv_11= '}' )? otherlv_12= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:620:1: ( () otherlv_1= 'RenderingRef' ( ( ruleEString ) ) (otherlv_3= '{' (otherlv_4= 'placementData' ( (lv_placementData_5_0= ruleKPlacementData ) ) )? (otherlv_6= 'styles' otherlv_7= '{' ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* otherlv_11= '}' )? otherlv_12= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:620:2: () otherlv_1= 'RenderingRef' ( ( ruleEString ) ) (otherlv_3= '{' (otherlv_4= 'placementData' ( (lv_placementData_5_0= ruleKPlacementData ) ) )? (otherlv_6= 'styles' otherlv_7= '{' ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* otherlv_11= '}' )? otherlv_12= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:620:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:621:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRenderingRefAccess().getKRenderingRefAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKRenderingRef1618); 

                	newLeafNode(otherlv_1, grammarAccess.getKRenderingRefAccess().getRenderingRefKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:630:1: ( ( ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:631:1: ( ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:631:1: ( ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:632:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getKRenderingRefRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getRenderingKRenderingCrossReference_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKRenderingRef1641);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:645:2: (otherlv_3= '{' (otherlv_4= 'placementData' ( (lv_placementData_5_0= ruleKPlacementData ) ) )? (otherlv_6= 'styles' otherlv_7= '{' ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* otherlv_11= '}' )? otherlv_12= '}' )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==12) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:645:4: otherlv_3= '{' (otherlv_4= 'placementData' ( (lv_placementData_5_0= ruleKPlacementData ) ) )? (otherlv_6= 'styles' otherlv_7= '{' ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* otherlv_11= '}' )? otherlv_12= '}'
                    {
                    otherlv_3=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRenderingRef1654); 

                        	newLeafNode(otherlv_3, grammarAccess.getKRenderingRefAccess().getLeftCurlyBracketKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:649:1: (otherlv_4= 'placementData' ( (lv_placementData_5_0= ruleKPlacementData ) ) )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0==16) ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:649:3: otherlv_4= 'placementData' ( (lv_placementData_5_0= ruleKPlacementData ) )
                            {
                            otherlv_4=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRenderingRef1667); 

                                	newLeafNode(otherlv_4, grammarAccess.getKRenderingRefAccess().getPlacementDataKeyword_3_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:653:1: ( (lv_placementData_5_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:654:1: (lv_placementData_5_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:654:1: (lv_placementData_5_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:655:3: lv_placementData_5_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getPlacementDataKPlacementDataParserRuleCall_3_1_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRenderingRef1688);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:671:4: (otherlv_6= 'styles' otherlv_7= '{' ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* otherlv_11= '}' )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0==17) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:671:6: otherlv_6= 'styles' otherlv_7= '{' ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* otherlv_11= '}'
                            {
                            otherlv_6=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRenderingRef1703); 

                                	newLeafNode(otherlv_6, grammarAccess.getKRenderingRefAccess().getStylesKeyword_3_2_0());
                                
                            otherlv_7=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRenderingRef1715); 

                                	newLeafNode(otherlv_7, grammarAccess.getKRenderingRefAccess().getLeftCurlyBracketKeyword_3_2_1());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:679:1: ( (lv_styles_8_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:680:1: (lv_styles_8_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:680:1: (lv_styles_8_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:681:3: lv_styles_8_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getStylesKStyleParserRuleCall_3_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRenderingRef1736);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:697:2: ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            loop12:
                            do {
                                int alt12=2;
                                int LA12_0 = input.LA(1);

                                if ( (LA12_0==13||LA12_0==62||(LA12_0>=64 && LA12_0<=74)) ) {
                                    alt12=1;
                                }


                                switch (alt12) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:697:3: (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:697:3: (otherlv_9= ',' )?
                            	    int alt11=2;
                            	    int LA11_0 = input.LA(1);

                            	    if ( (LA11_0==13) ) {
                            	        alt11=1;
                            	    }
                            	    switch (alt11) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:697:5: otherlv_9= ','
                            	            {
                            	            otherlv_9=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRenderingRef1750); 

                            	                	newLeafNode(otherlv_9, grammarAccess.getKRenderingRefAccess().getCommaKeyword_3_2_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:701:3: ( (lv_styles_10_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:702:1: (lv_styles_10_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:702:1: (lv_styles_10_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:703:3: lv_styles_10_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getStylesKStyleParserRuleCall_3_2_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRenderingRef1773);
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

                            otherlv_11=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRenderingRef1787); 

                                	newLeafNode(otherlv_11, grammarAccess.getKRenderingRefAccess().getRightCurlyBracketKeyword_3_2_4());
                                

                            }
                            break;

                    }

                    otherlv_12=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRenderingRef1801); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:735:1: entryRuleKEllipse returns [EObject current=null] : iv_ruleKEllipse= ruleKEllipse EOF ;
    public final EObject entryRuleKEllipse() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKEllipse = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:736:2: (iv_ruleKEllipse= ruleKEllipse EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:737:2: iv_ruleKEllipse= ruleKEllipse EOF
            {
             newCompositeNode(grammarAccess.getKEllipseRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKEllipse_in_entryRuleKEllipse1839);
            iv_ruleKEllipse=ruleKEllipse();

            state._fsp--;

             current =iv_ruleKEllipse; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKEllipse1849); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:744:1: ruleKEllipse returns [EObject current=null] : ( () otherlv_1= 'Ellipse' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:747:28: ( ( () otherlv_1= 'Ellipse' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:748:1: ( () otherlv_1= 'Ellipse' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:748:1: ( () otherlv_1= 'Ellipse' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:748:2: () otherlv_1= 'Ellipse' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:748:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:749:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKEllipseAccess().getKEllipseAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKEllipse1895); 

                	newLeafNode(otherlv_1, grammarAccess.getKEllipseAccess().getEllipseKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:758:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==12) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:758:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKEllipse1908); 

                        	newLeafNode(otherlv_2, grammarAccess.getKEllipseAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:762:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==17) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:762:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKEllipse1921); 

                                	newLeafNode(otherlv_3, grammarAccess.getKEllipseAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:766:1: (otherlv_4= ':' )?
                            int alt15=2;
                            int LA15_0 = input.LA(1);

                            if ( (LA15_0==19) ) {
                                alt15=1;
                            }
                            switch (alt15) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:766:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKEllipse1934); 

                                        	newLeafNode(otherlv_4, grammarAccess.getKEllipseAccess().getColonKeyword_2_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:770:3: ( (lv_styles_5_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:771:1: (lv_styles_5_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:771:1: (lv_styles_5_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:772:3: lv_styles_5_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKEllipseAccess().getStylesKStyleParserRuleCall_2_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKEllipse1957);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:788:2: ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            loop17:
                            do {
                                int alt17=2;
                                int LA17_0 = input.LA(1);

                                if ( (LA17_0==13||LA17_0==62||(LA17_0>=64 && LA17_0<=74)) ) {
                                    alt17=1;
                                }


                                switch (alt17) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:788:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:788:3: (otherlv_6= ',' )?
                            	    int alt16=2;
                            	    int LA16_0 = input.LA(1);

                            	    if ( (LA16_0==13) ) {
                            	        alt16=1;
                            	    }
                            	    switch (alt16) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:788:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKEllipse1971); 

                            	                	newLeafNode(otherlv_6, grammarAccess.getKEllipseAccess().getCommaKeyword_2_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:792:3: ( (lv_styles_7_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:793:1: (lv_styles_7_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:793:1: (lv_styles_7_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:794:3: lv_styles_7_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKEllipseAccess().getStylesKStyleParserRuleCall_2_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKEllipse1994);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:810:6: (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==16) ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:810:8: otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKEllipse2011); 

                                	newLeafNode(otherlv_8, grammarAccess.getKEllipseAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:814:1: (otherlv_9= ':' )?
                            int alt19=2;
                            int LA19_0 = input.LA(1);

                            if ( (LA19_0==19) ) {
                                alt19=1;
                            }
                            switch (alt19) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:814:3: otherlv_9= ':'
                                    {
                                    otherlv_9=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKEllipse2024); 

                                        	newLeafNode(otherlv_9, grammarAccess.getKEllipseAccess().getColonKeyword_2_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:818:3: ( (lv_placementData_10_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:819:1: (lv_placementData_10_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:819:1: (lv_placementData_10_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:820:3: lv_placementData_10_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKEllipseAccess().getPlacementDataKPlacementDataParserRuleCall_2_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKEllipse2047);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:836:4: (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==20) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:836:6: otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            {
                            otherlv_11=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKEllipse2062); 

                                	newLeafNode(otherlv_11, grammarAccess.getKEllipseAccess().getChildPlacementKeyword_2_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:840:1: (otherlv_12= ':' )?
                            int alt21=2;
                            int LA21_0 = input.LA(1);

                            if ( (LA21_0==19) ) {
                                alt21=1;
                            }
                            switch (alt21) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:840:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKEllipse2075); 

                                        	newLeafNode(otherlv_12, grammarAccess.getKEllipseAccess().getColonKeyword_2_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:844:3: ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:845:1: (lv_childPlacement_13_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:845:1: (lv_childPlacement_13_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:846:3: lv_childPlacement_13_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKEllipseAccess().getChildPlacementKPlacementParserRuleCall_2_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKEllipse2098);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:862:4: (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )?
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==21) ) {
                        alt26=1;
                    }
                    switch (alt26) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:862:6: otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            {
                            otherlv_14=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKEllipse2113); 

                                	newLeafNode(otherlv_14, grammarAccess.getKEllipseAccess().getChildrenKeyword_2_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:866:1: (otherlv_15= ':' )?
                            int alt23=2;
                            int LA23_0 = input.LA(1);

                            if ( (LA23_0==19) ) {
                                alt23=1;
                            }
                            switch (alt23) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:866:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKEllipse2126); 

                                        	newLeafNode(otherlv_15, grammarAccess.getKEllipseAccess().getColonKeyword_2_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:870:3: ( (lv_children_16_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:871:1: (lv_children_16_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:871:1: (lv_children_16_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:872:3: lv_children_16_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKEllipseAccess().getChildrenKRenderingParserRuleCall_2_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKEllipse2149);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:888:2: ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            loop25:
                            do {
                                int alt25=2;
                                int LA25_0 = input.LA(1);

                                if ( (LA25_0==13||LA25_0==15||LA25_0==18||(LA25_0>=22 && LA25_0<=26)||(LA25_0>=28 && LA25_0<=30)||LA25_0==32||LA25_0==35) ) {
                                    alt25=1;
                                }


                                switch (alt25) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:888:3: (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:888:3: (otherlv_17= ',' )?
                            	    int alt24=2;
                            	    int LA24_0 = input.LA(1);

                            	    if ( (LA24_0==13) ) {
                            	        alt24=1;
                            	    }
                            	    switch (alt24) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:888:5: otherlv_17= ','
                            	            {
                            	            otherlv_17=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKEllipse2163); 

                            	                	newLeafNode(otherlv_17, grammarAccess.getKEllipseAccess().getCommaKeyword_2_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:892:3: ( (lv_children_18_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:893:1: (lv_children_18_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:893:1: (lv_children_18_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:894:3: lv_children_18_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKEllipseAccess().getChildrenKRenderingParserRuleCall_2_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKEllipse2186);
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

                    otherlv_19=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKEllipse2202); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:922:1: entryRuleKRectangle returns [EObject current=null] : iv_ruleKRectangle= ruleKRectangle EOF ;
    public final EObject entryRuleKRectangle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRectangle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:923:2: (iv_ruleKRectangle= ruleKRectangle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:924:2: iv_ruleKRectangle= ruleKRectangle EOF
            {
             newCompositeNode(grammarAccess.getKRectangleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRectangle_in_entryRuleKRectangle2240);
            iv_ruleKRectangle=ruleKRectangle();

            state._fsp--;

             current =iv_ruleKRectangle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRectangle2250); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:931:1: ruleKRectangle returns [EObject current=null] : ( () otherlv_1= 'Rectangle' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:934:28: ( ( () otherlv_1= 'Rectangle' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:935:1: ( () otherlv_1= 'Rectangle' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:935:1: ( () otherlv_1= 'Rectangle' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:935:2: () otherlv_1= 'Rectangle' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:935:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:936:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRectangleAccess().getKRectangleAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleKRectangle2296); 

                	newLeafNode(otherlv_1, grammarAccess.getKRectangleAccess().getRectangleKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:945:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==12) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:945:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRectangle2309); 

                        	newLeafNode(otherlv_2, grammarAccess.getKRectangleAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:949:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt31=2;
                    int LA31_0 = input.LA(1);

                    if ( (LA31_0==17) ) {
                        alt31=1;
                    }
                    switch (alt31) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:949:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRectangle2322); 

                                	newLeafNode(otherlv_3, grammarAccess.getKRectangleAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:953:1: (otherlv_4= ':' )?
                            int alt28=2;
                            int LA28_0 = input.LA(1);

                            if ( (LA28_0==19) ) {
                                alt28=1;
                            }
                            switch (alt28) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:953:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKRectangle2335); 

                                        	newLeafNode(otherlv_4, grammarAccess.getKRectangleAccess().getColonKeyword_2_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:957:3: ( (lv_styles_5_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:958:1: (lv_styles_5_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:958:1: (lv_styles_5_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:959:3: lv_styles_5_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRectangleAccess().getStylesKStyleParserRuleCall_2_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRectangle2358);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:975:2: ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            loop30:
                            do {
                                int alt30=2;
                                int LA30_0 = input.LA(1);

                                if ( (LA30_0==13||LA30_0==62||(LA30_0>=64 && LA30_0<=74)) ) {
                                    alt30=1;
                                }


                                switch (alt30) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:975:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:975:3: (otherlv_6= ',' )?
                            	    int alt29=2;
                            	    int LA29_0 = input.LA(1);

                            	    if ( (LA29_0==13) ) {
                            	        alt29=1;
                            	    }
                            	    switch (alt29) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:975:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRectangle2372); 

                            	                	newLeafNode(otherlv_6, grammarAccess.getKRectangleAccess().getCommaKeyword_2_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:979:3: ( (lv_styles_7_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:980:1: (lv_styles_7_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:980:1: (lv_styles_7_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:981:3: lv_styles_7_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKRectangleAccess().getStylesKStyleParserRuleCall_2_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRectangle2395);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:997:6: (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
                    int alt33=2;
                    int LA33_0 = input.LA(1);

                    if ( (LA33_0==16) ) {
                        alt33=1;
                    }
                    switch (alt33) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:997:8: otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRectangle2412); 

                                	newLeafNode(otherlv_8, grammarAccess.getKRectangleAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1001:1: (otherlv_9= ':' )?
                            int alt32=2;
                            int LA32_0 = input.LA(1);

                            if ( (LA32_0==19) ) {
                                alt32=1;
                            }
                            switch (alt32) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1001:3: otherlv_9= ':'
                                    {
                                    otherlv_9=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKRectangle2425); 

                                        	newLeafNode(otherlv_9, grammarAccess.getKRectangleAccess().getColonKeyword_2_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1005:3: ( (lv_placementData_10_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1006:1: (lv_placementData_10_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1006:1: (lv_placementData_10_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1007:3: lv_placementData_10_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRectangleAccess().getPlacementDataKPlacementDataParserRuleCall_2_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRectangle2448);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1023:4: (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )?
                    int alt35=2;
                    int LA35_0 = input.LA(1);

                    if ( (LA35_0==20) ) {
                        alt35=1;
                    }
                    switch (alt35) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1023:6: otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            {
                            otherlv_11=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKRectangle2463); 

                                	newLeafNode(otherlv_11, grammarAccess.getKRectangleAccess().getChildPlacementKeyword_2_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1027:1: (otherlv_12= ':' )?
                            int alt34=2;
                            int LA34_0 = input.LA(1);

                            if ( (LA34_0==19) ) {
                                alt34=1;
                            }
                            switch (alt34) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1027:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKRectangle2476); 

                                        	newLeafNode(otherlv_12, grammarAccess.getKRectangleAccess().getColonKeyword_2_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1031:3: ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1032:1: (lv_childPlacement_13_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1032:1: (lv_childPlacement_13_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1033:3: lv_childPlacement_13_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRectangleAccess().getChildPlacementKPlacementParserRuleCall_2_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKRectangle2499);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1049:4: (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )?
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0==21) ) {
                        alt39=1;
                    }
                    switch (alt39) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1049:6: otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            {
                            otherlv_14=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKRectangle2514); 

                                	newLeafNode(otherlv_14, grammarAccess.getKRectangleAccess().getChildrenKeyword_2_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1053:1: (otherlv_15= ':' )?
                            int alt36=2;
                            int LA36_0 = input.LA(1);

                            if ( (LA36_0==19) ) {
                                alt36=1;
                            }
                            switch (alt36) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1053:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKRectangle2527); 

                                        	newLeafNode(otherlv_15, grammarAccess.getKRectangleAccess().getColonKeyword_2_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1057:3: ( (lv_children_16_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1058:1: (lv_children_16_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1058:1: (lv_children_16_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1059:3: lv_children_16_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRectangleAccess().getChildrenKRenderingParserRuleCall_2_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRectangle2550);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1075:2: ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            loop38:
                            do {
                                int alt38=2;
                                int LA38_0 = input.LA(1);

                                if ( (LA38_0==13||LA38_0==15||LA38_0==18||(LA38_0>=22 && LA38_0<=26)||(LA38_0>=28 && LA38_0<=30)||LA38_0==32||LA38_0==35) ) {
                                    alt38=1;
                                }


                                switch (alt38) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1075:3: (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1075:3: (otherlv_17= ',' )?
                            	    int alt37=2;
                            	    int LA37_0 = input.LA(1);

                            	    if ( (LA37_0==13) ) {
                            	        alt37=1;
                            	    }
                            	    switch (alt37) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1075:5: otherlv_17= ','
                            	            {
                            	            otherlv_17=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRectangle2564); 

                            	                	newLeafNode(otherlv_17, grammarAccess.getKRectangleAccess().getCommaKeyword_2_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1079:3: ( (lv_children_18_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1080:1: (lv_children_18_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1080:1: (lv_children_18_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1081:3: lv_children_18_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKRectangleAccess().getChildrenKRenderingParserRuleCall_2_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRectangle2587);
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

                    otherlv_19=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRectangle2603); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1109:1: entryRuleKRoundedRectangle returns [EObject current=null] : iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF ;
    public final EObject entryRuleKRoundedRectangle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRoundedRectangle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1110:2: (iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1111:2: iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF
            {
             newCompositeNode(grammarAccess.getKRoundedRectangleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRoundedRectangle_in_entryRuleKRoundedRectangle2641);
            iv_ruleKRoundedRectangle=ruleKRoundedRectangle();

            state._fsp--;

             current =iv_ruleKRoundedRectangle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRoundedRectangle2651); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1118:1: ruleKRoundedRectangle returns [EObject current=null] : ( () otherlv_1= 'RoundedRectangle' ( (lv_cornerWidth_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_cornerHeight_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1121:28: ( ( () otherlv_1= 'RoundedRectangle' ( (lv_cornerWidth_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_cornerHeight_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1122:1: ( () otherlv_1= 'RoundedRectangle' ( (lv_cornerWidth_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_cornerHeight_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1122:1: ( () otherlv_1= 'RoundedRectangle' ( (lv_cornerWidth_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_cornerHeight_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1122:2: () otherlv_1= 'RoundedRectangle' ( (lv_cornerWidth_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_cornerHeight_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1122:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1123:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRoundedRectangleAccess().getKRoundedRectangleAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKRoundedRectangle2697); 

                	newLeafNode(otherlv_1, grammarAccess.getKRoundedRectangleAccess().getRoundedRectangleKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1132:1: ( (lv_cornerWidth_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1133:1: (lv_cornerWidth_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1133:1: (lv_cornerWidth_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1134:3: lv_cornerWidth_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getCornerWidthEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRoundedRectangle2718);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1150:2: (otherlv_3= ',' )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==13) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1150:4: otherlv_3= ','
                    {
                    otherlv_3=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRoundedRectangle2731); 

                        	newLeafNode(otherlv_3, grammarAccess.getKRoundedRectangleAccess().getCommaKeyword_3());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1154:3: ( (lv_cornerHeight_4_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1155:1: (lv_cornerHeight_4_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1155:1: (lv_cornerHeight_4_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1156:3: lv_cornerHeight_4_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getCornerHeightEFloatParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRoundedRectangle2754);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1172:2: (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==12) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1172:4: otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}'
                    {
                    otherlv_5=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRoundedRectangle2767); 

                        	newLeafNode(otherlv_5, grammarAccess.getKRoundedRectangleAccess().getLeftCurlyBracketKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1176:1: (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )?
                    int alt45=2;
                    int LA45_0 = input.LA(1);

                    if ( (LA45_0==17) ) {
                        alt45=1;
                    }
                    switch (alt45) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1176:3: otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            {
                            otherlv_6=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRoundedRectangle2780); 

                                	newLeafNode(otherlv_6, grammarAccess.getKRoundedRectangleAccess().getStylesKeyword_5_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1180:1: (otherlv_7= ':' )?
                            int alt42=2;
                            int LA42_0 = input.LA(1);

                            if ( (LA42_0==19) ) {
                                alt42=1;
                            }
                            switch (alt42) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1180:3: otherlv_7= ':'
                                    {
                                    otherlv_7=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKRoundedRectangle2793); 

                                        	newLeafNode(otherlv_7, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_5_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1184:3: ( (lv_styles_8_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1185:1: (lv_styles_8_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1185:1: (lv_styles_8_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1186:3: lv_styles_8_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getStylesKStyleParserRuleCall_5_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRoundedRectangle2816);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1202:2: ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            loop44:
                            do {
                                int alt44=2;
                                int LA44_0 = input.LA(1);

                                if ( (LA44_0==13||LA44_0==62||(LA44_0>=64 && LA44_0<=74)) ) {
                                    alt44=1;
                                }


                                switch (alt44) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1202:3: (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1202:3: (otherlv_9= ',' )?
                            	    int alt43=2;
                            	    int LA43_0 = input.LA(1);

                            	    if ( (LA43_0==13) ) {
                            	        alt43=1;
                            	    }
                            	    switch (alt43) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1202:5: otherlv_9= ','
                            	            {
                            	            otherlv_9=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRoundedRectangle2830); 

                            	                	newLeafNode(otherlv_9, grammarAccess.getKRoundedRectangleAccess().getCommaKeyword_5_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1206:3: ( (lv_styles_10_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1207:1: (lv_styles_10_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1207:1: (lv_styles_10_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1208:3: lv_styles_10_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getStylesKStyleParserRuleCall_5_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRoundedRectangle2853);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1224:6: (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )?
                    int alt47=2;
                    int LA47_0 = input.LA(1);

                    if ( (LA47_0==16) ) {
                        alt47=1;
                    }
                    switch (alt47) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1224:8: otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) )
                            {
                            otherlv_11=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRoundedRectangle2870); 

                                	newLeafNode(otherlv_11, grammarAccess.getKRoundedRectangleAccess().getPlacementDataKeyword_5_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1228:1: (otherlv_12= ':' )?
                            int alt46=2;
                            int LA46_0 = input.LA(1);

                            if ( (LA46_0==19) ) {
                                alt46=1;
                            }
                            switch (alt46) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1228:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKRoundedRectangle2883); 

                                        	newLeafNode(otherlv_12, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_5_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1232:3: ( (lv_placementData_13_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1233:1: (lv_placementData_13_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1233:1: (lv_placementData_13_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1234:3: lv_placementData_13_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getPlacementDataKPlacementDataParserRuleCall_5_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRoundedRectangle2906);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1250:4: (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )?
                    int alt49=2;
                    int LA49_0 = input.LA(1);

                    if ( (LA49_0==20) ) {
                        alt49=1;
                    }
                    switch (alt49) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1250:6: otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) )
                            {
                            otherlv_14=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKRoundedRectangle2921); 

                                	newLeafNode(otherlv_14, grammarAccess.getKRoundedRectangleAccess().getChildPlacementKeyword_5_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1254:1: (otherlv_15= ':' )?
                            int alt48=2;
                            int LA48_0 = input.LA(1);

                            if ( (LA48_0==19) ) {
                                alt48=1;
                            }
                            switch (alt48) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1254:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKRoundedRectangle2934); 

                                        	newLeafNode(otherlv_15, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_5_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1258:3: ( (lv_childPlacement_16_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1259:1: (lv_childPlacement_16_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1259:1: (lv_childPlacement_16_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1260:3: lv_childPlacement_16_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getChildPlacementKPlacementParserRuleCall_5_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKRoundedRectangle2957);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1276:4: (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )?
                    int alt53=2;
                    int LA53_0 = input.LA(1);

                    if ( (LA53_0==21) ) {
                        alt53=1;
                    }
                    switch (alt53) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1276:6: otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )*
                            {
                            otherlv_17=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKRoundedRectangle2972); 

                                	newLeafNode(otherlv_17, grammarAccess.getKRoundedRectangleAccess().getChildrenKeyword_5_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1280:1: (otherlv_18= ':' )?
                            int alt50=2;
                            int LA50_0 = input.LA(1);

                            if ( (LA50_0==19) ) {
                                alt50=1;
                            }
                            switch (alt50) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1280:3: otherlv_18= ':'
                                    {
                                    otherlv_18=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKRoundedRectangle2985); 

                                        	newLeafNode(otherlv_18, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_5_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1284:3: ( (lv_children_19_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1285:1: (lv_children_19_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1285:1: (lv_children_19_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1286:3: lv_children_19_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getChildrenKRenderingParserRuleCall_5_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRoundedRectangle3008);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1302:2: ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )*
                            loop52:
                            do {
                                int alt52=2;
                                int LA52_0 = input.LA(1);

                                if ( (LA52_0==13||LA52_0==15||LA52_0==18||(LA52_0>=22 && LA52_0<=26)||(LA52_0>=28 && LA52_0<=30)||LA52_0==32||LA52_0==35) ) {
                                    alt52=1;
                                }


                                switch (alt52) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1302:3: (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1302:3: (otherlv_20= ',' )?
                            	    int alt51=2;
                            	    int LA51_0 = input.LA(1);

                            	    if ( (LA51_0==13) ) {
                            	        alt51=1;
                            	    }
                            	    switch (alt51) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1302:5: otherlv_20= ','
                            	            {
                            	            otherlv_20=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRoundedRectangle3022); 

                            	                	newLeafNode(otherlv_20, grammarAccess.getKRoundedRectangleAccess().getCommaKeyword_5_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1306:3: ( (lv_children_21_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1307:1: (lv_children_21_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1307:1: (lv_children_21_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1308:3: lv_children_21_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getChildrenKRenderingParserRuleCall_5_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRoundedRectangle3045);
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

                    otherlv_22=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRoundedRectangle3061); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1336:1: entryRuleKPolyline_Impl returns [EObject current=null] : iv_ruleKPolyline_Impl= ruleKPolyline_Impl EOF ;
    public final EObject entryRuleKPolyline_Impl() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolyline_Impl = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1337:2: (iv_ruleKPolyline_Impl= ruleKPolyline_Impl EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1338:2: iv_ruleKPolyline_Impl= ruleKPolyline_Impl EOF
            {
             newCompositeNode(grammarAccess.getKPolyline_ImplRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPolyline_Impl_in_entryRuleKPolyline_Impl3099);
            iv_ruleKPolyline_Impl=ruleKPolyline_Impl();

            state._fsp--;

             current =iv_ruleKPolyline_Impl; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPolyline_Impl3109); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1345:1: ruleKPolyline_Impl returns [EObject current=null] : ( () otherlv_1= 'Polyline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1348:28: ( ( () otherlv_1= 'Polyline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1349:1: ( () otherlv_1= 'Polyline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1349:1: ( () otherlv_1= 'Polyline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1349:2: () otherlv_1= 'Polyline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1349:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1350:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPolyline_ImplAccess().getKPolylineAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,24,FollowSets000.FOLLOW_24_in_ruleKPolyline_Impl3155); 

                	newLeafNode(otherlv_1, grammarAccess.getKPolyline_ImplAccess().getPolylineKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1359:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==12) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1359:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolyline_Impl3168); 

                        	newLeafNode(otherlv_2, grammarAccess.getKPolyline_ImplAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1363:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt58=2;
                    int LA58_0 = input.LA(1);

                    if ( (LA58_0==17) ) {
                        alt58=1;
                    }
                    switch (alt58) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1363:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolyline_Impl3181); 

                                	newLeafNode(otherlv_3, grammarAccess.getKPolyline_ImplAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1367:1: (otherlv_4= ':' )?
                            int alt55=2;
                            int LA55_0 = input.LA(1);

                            if ( (LA55_0==19) ) {
                                alt55=1;
                            }
                            switch (alt55) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1367:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKPolyline_Impl3194); 

                                        	newLeafNode(otherlv_4, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_2_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1371:3: ( (lv_styles_5_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1372:1: (lv_styles_5_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1372:1: (lv_styles_5_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1373:3: lv_styles_5_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getStylesKStyleParserRuleCall_2_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolyline_Impl3217);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1389:2: ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            loop57:
                            do {
                                int alt57=2;
                                int LA57_0 = input.LA(1);

                                if ( (LA57_0==13||LA57_0==62||(LA57_0>=64 && LA57_0<=74)) ) {
                                    alt57=1;
                                }


                                switch (alt57) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1389:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1389:3: (otherlv_6= ',' )?
                            	    int alt56=2;
                            	    int LA56_0 = input.LA(1);

                            	    if ( (LA56_0==13) ) {
                            	        alt56=1;
                            	    }
                            	    switch (alt56) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1389:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPolyline_Impl3231); 

                            	                	newLeafNode(otherlv_6, grammarAccess.getKPolyline_ImplAccess().getCommaKeyword_2_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1393:3: ( (lv_styles_7_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1394:1: (lv_styles_7_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1394:1: (lv_styles_7_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1395:3: lv_styles_7_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getStylesKStyleParserRuleCall_2_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolyline_Impl3254);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1411:6: (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
                    int alt60=2;
                    int LA60_0 = input.LA(1);

                    if ( (LA60_0==16) ) {
                        alt60=1;
                    }
                    switch (alt60) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1411:8: otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolyline_Impl3271); 

                                	newLeafNode(otherlv_8, grammarAccess.getKPolyline_ImplAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1415:1: (otherlv_9= ':' )?
                            int alt59=2;
                            int LA59_0 = input.LA(1);

                            if ( (LA59_0==19) ) {
                                alt59=1;
                            }
                            switch (alt59) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1415:3: otherlv_9= ':'
                                    {
                                    otherlv_9=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKPolyline_Impl3284); 

                                        	newLeafNode(otherlv_9, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_2_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1419:3: ( (lv_placementData_10_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1420:1: (lv_placementData_10_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1420:1: (lv_placementData_10_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1421:3: lv_placementData_10_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getPlacementDataKPlacementDataParserRuleCall_2_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKPolyline_Impl3307);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1437:4: (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )?
                    int alt62=2;
                    int LA62_0 = input.LA(1);

                    if ( (LA62_0==20) ) {
                        alt62=1;
                    }
                    switch (alt62) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1437:6: otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            {
                            otherlv_11=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKPolyline_Impl3322); 

                                	newLeafNode(otherlv_11, grammarAccess.getKPolyline_ImplAccess().getChildPlacementKeyword_2_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1441:1: (otherlv_12= ':' )?
                            int alt61=2;
                            int LA61_0 = input.LA(1);

                            if ( (LA61_0==19) ) {
                                alt61=1;
                            }
                            switch (alt61) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1441:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKPolyline_Impl3335); 

                                        	newLeafNode(otherlv_12, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_2_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1445:3: ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1446:1: (lv_childPlacement_13_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1446:1: (lv_childPlacement_13_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1447:3: lv_childPlacement_13_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getChildPlacementKPlacementParserRuleCall_2_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKPolyline_Impl3358);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1463:4: (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )?
                    int alt66=2;
                    int LA66_0 = input.LA(1);

                    if ( (LA66_0==21) ) {
                        alt66=1;
                    }
                    switch (alt66) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1463:6: otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            {
                            otherlv_14=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKPolyline_Impl3373); 

                                	newLeafNode(otherlv_14, grammarAccess.getKPolyline_ImplAccess().getChildrenKeyword_2_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1467:1: (otherlv_15= ':' )?
                            int alt63=2;
                            int LA63_0 = input.LA(1);

                            if ( (LA63_0==19) ) {
                                alt63=1;
                            }
                            switch (alt63) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1467:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKPolyline_Impl3386); 

                                        	newLeafNode(otherlv_15, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_2_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1471:3: ( (lv_children_16_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1472:1: (lv_children_16_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1472:1: (lv_children_16_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1473:3: lv_children_16_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getChildrenKRenderingParserRuleCall_2_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolyline_Impl3409);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1489:2: ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            loop65:
                            do {
                                int alt65=2;
                                int LA65_0 = input.LA(1);

                                if ( (LA65_0==13||LA65_0==15||LA65_0==18||(LA65_0>=22 && LA65_0<=26)||(LA65_0>=28 && LA65_0<=30)||LA65_0==32||LA65_0==35) ) {
                                    alt65=1;
                                }


                                switch (alt65) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1489:3: (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1489:3: (otherlv_17= ',' )?
                            	    int alt64=2;
                            	    int LA64_0 = input.LA(1);

                            	    if ( (LA64_0==13) ) {
                            	        alt64=1;
                            	    }
                            	    switch (alt64) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1489:5: otherlv_17= ','
                            	            {
                            	            otherlv_17=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPolyline_Impl3423); 

                            	                	newLeafNode(otherlv_17, grammarAccess.getKPolyline_ImplAccess().getCommaKeyword_2_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1493:3: ( (lv_children_18_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1494:1: (lv_children_18_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1494:1: (lv_children_18_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1495:3: lv_children_18_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getChildrenKRenderingParserRuleCall_2_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolyline_Impl3446);
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

                    otherlv_19=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolyline_Impl3462); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1523:1: entryRuleKPolygon returns [EObject current=null] : iv_ruleKPolygon= ruleKPolygon EOF ;
    public final EObject entryRuleKPolygon() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolygon = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1524:2: (iv_ruleKPolygon= ruleKPolygon EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1525:2: iv_ruleKPolygon= ruleKPolygon EOF
            {
             newCompositeNode(grammarAccess.getKPolygonRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPolygon_in_entryRuleKPolygon3500);
            iv_ruleKPolygon=ruleKPolygon();

            state._fsp--;

             current =iv_ruleKPolygon; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPolygon3510); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1532:1: ruleKPolygon returns [EObject current=null] : ( () otherlv_1= 'Polygon' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1535:28: ( ( () otherlv_1= 'Polygon' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1536:1: ( () otherlv_1= 'Polygon' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1536:1: ( () otherlv_1= 'Polygon' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1536:2: () otherlv_1= 'Polygon' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1536:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1537:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPolygonAccess().getKPolygonAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleKPolygon3556); 

                	newLeafNode(otherlv_1, grammarAccess.getKPolygonAccess().getPolygonKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1546:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==12) ) {
                alt80=1;
            }
            switch (alt80) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1546:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolygon3569); 

                        	newLeafNode(otherlv_2, grammarAccess.getKPolygonAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1550:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt71=2;
                    int LA71_0 = input.LA(1);

                    if ( (LA71_0==17) ) {
                        alt71=1;
                    }
                    switch (alt71) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1550:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolygon3582); 

                                	newLeafNode(otherlv_3, grammarAccess.getKPolygonAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1554:1: (otherlv_4= ':' )?
                            int alt68=2;
                            int LA68_0 = input.LA(1);

                            if ( (LA68_0==19) ) {
                                alt68=1;
                            }
                            switch (alt68) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1554:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKPolygon3595); 

                                        	newLeafNode(otherlv_4, grammarAccess.getKPolygonAccess().getColonKeyword_2_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1558:3: ( (lv_styles_5_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1559:1: (lv_styles_5_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1559:1: (lv_styles_5_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1560:3: lv_styles_5_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolygonAccess().getStylesKStyleParserRuleCall_2_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolygon3618);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1576:2: ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            loop70:
                            do {
                                int alt70=2;
                                int LA70_0 = input.LA(1);

                                if ( (LA70_0==13||LA70_0==62||(LA70_0>=64 && LA70_0<=74)) ) {
                                    alt70=1;
                                }


                                switch (alt70) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1576:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1576:3: (otherlv_6= ',' )?
                            	    int alt69=2;
                            	    int LA69_0 = input.LA(1);

                            	    if ( (LA69_0==13) ) {
                            	        alt69=1;
                            	    }
                            	    switch (alt69) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1576:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPolygon3632); 

                            	                	newLeafNode(otherlv_6, grammarAccess.getKPolygonAccess().getCommaKeyword_2_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1580:3: ( (lv_styles_7_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1581:1: (lv_styles_7_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1581:1: (lv_styles_7_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1582:3: lv_styles_7_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKPolygonAccess().getStylesKStyleParserRuleCall_2_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolygon3655);
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
                            	    break loop70;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1598:6: (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
                    int alt73=2;
                    int LA73_0 = input.LA(1);

                    if ( (LA73_0==16) ) {
                        alt73=1;
                    }
                    switch (alt73) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1598:8: otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolygon3672); 

                                	newLeafNode(otherlv_8, grammarAccess.getKPolygonAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1602:1: (otherlv_9= ':' )?
                            int alt72=2;
                            int LA72_0 = input.LA(1);

                            if ( (LA72_0==19) ) {
                                alt72=1;
                            }
                            switch (alt72) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1602:3: otherlv_9= ':'
                                    {
                                    otherlv_9=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKPolygon3685); 

                                        	newLeafNode(otherlv_9, grammarAccess.getKPolygonAccess().getColonKeyword_2_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1606:3: ( (lv_placementData_10_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1607:1: (lv_placementData_10_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1607:1: (lv_placementData_10_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1608:3: lv_placementData_10_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolygonAccess().getPlacementDataKPlacementDataParserRuleCall_2_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKPolygon3708);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1624:4: (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )?
                    int alt75=2;
                    int LA75_0 = input.LA(1);

                    if ( (LA75_0==20) ) {
                        alt75=1;
                    }
                    switch (alt75) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1624:6: otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            {
                            otherlv_11=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKPolygon3723); 

                                	newLeafNode(otherlv_11, grammarAccess.getKPolygonAccess().getChildPlacementKeyword_2_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1628:1: (otherlv_12= ':' )?
                            int alt74=2;
                            int LA74_0 = input.LA(1);

                            if ( (LA74_0==19) ) {
                                alt74=1;
                            }
                            switch (alt74) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1628:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKPolygon3736); 

                                        	newLeafNode(otherlv_12, grammarAccess.getKPolygonAccess().getColonKeyword_2_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1632:3: ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1633:1: (lv_childPlacement_13_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1633:1: (lv_childPlacement_13_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1634:3: lv_childPlacement_13_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolygonAccess().getChildPlacementKPlacementParserRuleCall_2_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKPolygon3759);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1650:4: (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )?
                    int alt79=2;
                    int LA79_0 = input.LA(1);

                    if ( (LA79_0==21) ) {
                        alt79=1;
                    }
                    switch (alt79) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1650:6: otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            {
                            otherlv_14=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKPolygon3774); 

                                	newLeafNode(otherlv_14, grammarAccess.getKPolygonAccess().getChildrenKeyword_2_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1654:1: (otherlv_15= ':' )?
                            int alt76=2;
                            int LA76_0 = input.LA(1);

                            if ( (LA76_0==19) ) {
                                alt76=1;
                            }
                            switch (alt76) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1654:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKPolygon3787); 

                                        	newLeafNode(otherlv_15, grammarAccess.getKPolygonAccess().getColonKeyword_2_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1658:3: ( (lv_children_16_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1659:1: (lv_children_16_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1659:1: (lv_children_16_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1660:3: lv_children_16_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolygonAccess().getChildrenKRenderingParserRuleCall_2_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolygon3810);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1676:2: ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            loop78:
                            do {
                                int alt78=2;
                                int LA78_0 = input.LA(1);

                                if ( (LA78_0==13||LA78_0==15||LA78_0==18||(LA78_0>=22 && LA78_0<=26)||(LA78_0>=28 && LA78_0<=30)||LA78_0==32||LA78_0==35) ) {
                                    alt78=1;
                                }


                                switch (alt78) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1676:3: (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1676:3: (otherlv_17= ',' )?
                            	    int alt77=2;
                            	    int LA77_0 = input.LA(1);

                            	    if ( (LA77_0==13) ) {
                            	        alt77=1;
                            	    }
                            	    switch (alt77) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1676:5: otherlv_17= ','
                            	            {
                            	            otherlv_17=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPolygon3824); 

                            	                	newLeafNode(otherlv_17, grammarAccess.getKPolygonAccess().getCommaKeyword_2_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1680:3: ( (lv_children_18_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1681:1: (lv_children_18_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1681:1: (lv_children_18_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1682:3: lv_children_18_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKPolygonAccess().getChildrenKRenderingParserRuleCall_2_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolygon3847);
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
                            	    break loop78;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_19=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolygon3863); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1710:1: entryRuleKImage returns [EObject current=null] : iv_ruleKImage= ruleKImage EOF ;
    public final EObject entryRuleKImage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKImage = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1711:2: (iv_ruleKImage= ruleKImage EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1712:2: iv_ruleKImage= ruleKImage EOF
            {
             newCompositeNode(grammarAccess.getKImageRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKImage_in_entryRuleKImage3901);
            iv_ruleKImage=ruleKImage();

            state._fsp--;

             current =iv_ruleKImage; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKImage3911); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1719:1: ruleKImage returns [EObject current=null] : ( () otherlv_1= 'Image' ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' ) ( (lv_imagePath_4_0= ruleEString ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1722:28: ( ( () otherlv_1= 'Image' ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' ) ( (lv_imagePath_4_0= ruleEString ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1723:1: ( () otherlv_1= 'Image' ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' ) ( (lv_imagePath_4_0= ruleEString ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1723:1: ( () otherlv_1= 'Image' ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' ) ( (lv_imagePath_4_0= ruleEString ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1723:2: () otherlv_1= 'Image' ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' ) ( (lv_imagePath_4_0= ruleEString ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1723:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1724:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKImageAccess().getKImageAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleKImage3957); 

                	newLeafNode(otherlv_1, grammarAccess.getKImageAccess().getImageKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1733:1: ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' )
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( ((LA81_0>=RULE_STRING && LA81_0<=RULE_ID)) ) {
                alt81=1;
            }
            else if ( (LA81_0==27) ) {
                alt81=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 81, 0, input);

                throw nvae;
            }
            switch (alt81) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1733:2: ( (lv_bundleName_2_0= ruleEString ) )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1733:2: ( (lv_bundleName_2_0= ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1734:1: (lv_bundleName_2_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1734:1: (lv_bundleName_2_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1735:3: lv_bundleName_2_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getKImageAccess().getBundleNameEStringParserRuleCall_2_0_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKImage3979);
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
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1752:7: otherlv_3= '-'
                    {
                    otherlv_3=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleKImage3997); 

                        	newLeafNode(otherlv_3, grammarAccess.getKImageAccess().getHyphenMinusKeyword_2_1());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1756:2: ( (lv_imagePath_4_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1757:1: (lv_imagePath_4_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1757:1: (lv_imagePath_4_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1758:3: lv_imagePath_4_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKImageAccess().getImagePathEStringParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKImage4019);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1774:2: (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==12) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1774:4: otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}'
                    {
                    otherlv_5=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKImage4032); 

                        	newLeafNode(otherlv_5, grammarAccess.getKImageAccess().getLeftCurlyBracketKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1778:1: (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )?
                    int alt85=2;
                    int LA85_0 = input.LA(1);

                    if ( (LA85_0==17) ) {
                        alt85=1;
                    }
                    switch (alt85) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1778:3: otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            {
                            otherlv_6=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKImage4045); 

                                	newLeafNode(otherlv_6, grammarAccess.getKImageAccess().getStylesKeyword_4_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1782:1: (otherlv_7= ':' )?
                            int alt82=2;
                            int LA82_0 = input.LA(1);

                            if ( (LA82_0==19) ) {
                                alt82=1;
                            }
                            switch (alt82) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1782:3: otherlv_7= ':'
                                    {
                                    otherlv_7=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKImage4058); 

                                        	newLeafNode(otherlv_7, grammarAccess.getKImageAccess().getColonKeyword_4_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1786:3: ( (lv_styles_8_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1787:1: (lv_styles_8_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1787:1: (lv_styles_8_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1788:3: lv_styles_8_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKImageAccess().getStylesKStyleParserRuleCall_4_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKImage4081);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1804:2: ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            loop84:
                            do {
                                int alt84=2;
                                int LA84_0 = input.LA(1);

                                if ( (LA84_0==13||LA84_0==62||(LA84_0>=64 && LA84_0<=74)) ) {
                                    alt84=1;
                                }


                                switch (alt84) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1804:3: (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1804:3: (otherlv_9= ',' )?
                            	    int alt83=2;
                            	    int LA83_0 = input.LA(1);

                            	    if ( (LA83_0==13) ) {
                            	        alt83=1;
                            	    }
                            	    switch (alt83) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1804:5: otherlv_9= ','
                            	            {
                            	            otherlv_9=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKImage4095); 

                            	                	newLeafNode(otherlv_9, grammarAccess.getKImageAccess().getCommaKeyword_4_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1808:3: ( (lv_styles_10_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1809:1: (lv_styles_10_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1809:1: (lv_styles_10_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1810:3: lv_styles_10_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKImageAccess().getStylesKStyleParserRuleCall_4_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKImage4118);
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
                            	    break loop84;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1826:6: (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )?
                    int alt86=2;
                    int LA86_0 = input.LA(1);

                    if ( (LA86_0==16) ) {
                        alt86=1;
                    }
                    switch (alt86) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1826:8: otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) )
                            {
                            otherlv_11=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKImage4135); 

                                	newLeafNode(otherlv_11, grammarAccess.getKImageAccess().getPlacementDataKeyword_4_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1830:1: ( (lv_placementData_12_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1831:1: (lv_placementData_12_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1831:1: (lv_placementData_12_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1832:3: lv_placementData_12_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKImageAccess().getPlacementDataKPlacementDataParserRuleCall_4_2_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKImage4156);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1848:4: (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )?
                    int alt89=2;
                    int LA89_0 = input.LA(1);

                    if ( (LA89_0==21) ) {
                        alt89=1;
                    }
                    switch (alt89) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1848:6: otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}'
                            {
                            otherlv_13=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKImage4171); 

                                	newLeafNode(otherlv_13, grammarAccess.getKImageAccess().getChildrenKeyword_4_3_0());
                                
                            otherlv_14=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKImage4183); 

                                	newLeafNode(otherlv_14, grammarAccess.getKImageAccess().getLeftCurlyBracketKeyword_4_3_1());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1856:1: ( (lv_children_15_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1857:1: (lv_children_15_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1857:1: (lv_children_15_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1858:3: lv_children_15_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKImageAccess().getChildrenKRenderingParserRuleCall_4_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKImage4204);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1874:2: ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )*
                            loop88:
                            do {
                                int alt88=2;
                                int LA88_0 = input.LA(1);

                                if ( (LA88_0==13||LA88_0==15||LA88_0==18||(LA88_0>=22 && LA88_0<=26)||(LA88_0>=28 && LA88_0<=30)||LA88_0==32||LA88_0==35) ) {
                                    alt88=1;
                                }


                                switch (alt88) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1874:3: (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1874:3: (otherlv_16= ',' )?
                            	    int alt87=2;
                            	    int LA87_0 = input.LA(1);

                            	    if ( (LA87_0==13) ) {
                            	        alt87=1;
                            	    }
                            	    switch (alt87) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1874:5: otherlv_16= ','
                            	            {
                            	            otherlv_16=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKImage4218); 

                            	                	newLeafNode(otherlv_16, grammarAccess.getKImageAccess().getCommaKeyword_4_3_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1878:3: ( (lv_children_17_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1879:1: (lv_children_17_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1879:1: (lv_children_17_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1880:3: lv_children_17_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKImageAccess().getChildrenKRenderingParserRuleCall_4_3_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKImage4241);
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
                            	    break loop88;
                                }
                            } while (true);

                            otherlv_18=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKImage4255); 

                                	newLeafNode(otherlv_18, grammarAccess.getKImageAccess().getRightCurlyBracketKeyword_4_3_4());
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1900:3: (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )?
                    int alt90=2;
                    int LA90_0 = input.LA(1);

                    if ( (LA90_0==20) ) {
                        alt90=1;
                    }
                    switch (alt90) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1900:5: otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) )
                            {
                            otherlv_19=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKImage4270); 

                                	newLeafNode(otherlv_19, grammarAccess.getKImageAccess().getChildPlacementKeyword_4_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1904:1: ( (lv_childPlacement_20_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1905:1: (lv_childPlacement_20_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1905:1: (lv_childPlacement_20_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1906:3: lv_childPlacement_20_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKImageAccess().getChildPlacementKPlacementParserRuleCall_4_4_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKImage4291);
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

                    otherlv_21=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKImage4305); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1934:1: entryRuleKArc returns [EObject current=null] : iv_ruleKArc= ruleKArc EOF ;
    public final EObject entryRuleKArc() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKArc = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1935:2: (iv_ruleKArc= ruleKArc EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1936:2: iv_ruleKArc= ruleKArc EOF
            {
             newCompositeNode(grammarAccess.getKArcRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKArc_in_entryRuleKArc4343);
            iv_ruleKArc=ruleKArc();

            state._fsp--;

             current =iv_ruleKArc; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKArc4353); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1943:1: ruleKArc returns [EObject current=null] : ( () otherlv_1= 'Arc' ( (lv_startAngle_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_arcAngle_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1946:28: ( ( () otherlv_1= 'Arc' ( (lv_startAngle_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_arcAngle_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1947:1: ( () otherlv_1= 'Arc' ( (lv_startAngle_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_arcAngle_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1947:1: ( () otherlv_1= 'Arc' ( (lv_startAngle_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_arcAngle_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1947:2: () otherlv_1= 'Arc' ( (lv_startAngle_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_arcAngle_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1947:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1948:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKArcAccess().getKArcAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleKArc4399); 

                	newLeafNode(otherlv_1, grammarAccess.getKArcAccess().getArcKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1957:1: ( (lv_startAngle_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1958:1: (lv_startAngle_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1958:1: (lv_startAngle_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1959:3: lv_startAngle_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKArcAccess().getStartAngleEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKArc4420);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1975:2: (otherlv_3= ',' )?
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==13) ) {
                alt92=1;
            }
            switch (alt92) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1975:4: otherlv_3= ','
                    {
                    otherlv_3=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKArc4433); 

                        	newLeafNode(otherlv_3, grammarAccess.getKArcAccess().getCommaKeyword_3());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1979:3: ( (lv_arcAngle_4_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1980:1: (lv_arcAngle_4_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1980:1: (lv_arcAngle_4_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1981:3: lv_arcAngle_4_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKArcAccess().getArcAngleEFloatParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKArc4456);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1997:2: (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}' )?
            int alt102=2;
            int LA102_0 = input.LA(1);

            if ( (LA102_0==12) ) {
                alt102=1;
            }
            switch (alt102) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1997:4: otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )? (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )? otherlv_21= '}'
                    {
                    otherlv_5=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKArc4469); 

                        	newLeafNode(otherlv_5, grammarAccess.getKArcAccess().getLeftCurlyBracketKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2001:1: (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )?
                    int alt96=2;
                    int LA96_0 = input.LA(1);

                    if ( (LA96_0==17) ) {
                        alt96=1;
                    }
                    switch (alt96) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2001:3: otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            {
                            otherlv_6=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKArc4482); 

                                	newLeafNode(otherlv_6, grammarAccess.getKArcAccess().getStylesKeyword_5_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2005:1: (otherlv_7= ':' )?
                            int alt93=2;
                            int LA93_0 = input.LA(1);

                            if ( (LA93_0==19) ) {
                                alt93=1;
                            }
                            switch (alt93) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2005:3: otherlv_7= ':'
                                    {
                                    otherlv_7=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKArc4495); 

                                        	newLeafNode(otherlv_7, grammarAccess.getKArcAccess().getColonKeyword_5_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2009:3: ( (lv_styles_8_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2010:1: (lv_styles_8_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2010:1: (lv_styles_8_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2011:3: lv_styles_8_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKArcAccess().getStylesKStyleParserRuleCall_5_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKArc4518);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2027:2: ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            loop95:
                            do {
                                int alt95=2;
                                int LA95_0 = input.LA(1);

                                if ( (LA95_0==13||LA95_0==62||(LA95_0>=64 && LA95_0<=74)) ) {
                                    alt95=1;
                                }


                                switch (alt95) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2027:3: (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2027:3: (otherlv_9= ',' )?
                            	    int alt94=2;
                            	    int LA94_0 = input.LA(1);

                            	    if ( (LA94_0==13) ) {
                            	        alt94=1;
                            	    }
                            	    switch (alt94) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2027:5: otherlv_9= ','
                            	            {
                            	            otherlv_9=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKArc4532); 

                            	                	newLeafNode(otherlv_9, grammarAccess.getKArcAccess().getCommaKeyword_5_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2031:3: ( (lv_styles_10_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2032:1: (lv_styles_10_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2032:1: (lv_styles_10_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2033:3: lv_styles_10_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKArcAccess().getStylesKStyleParserRuleCall_5_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKArc4555);
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
                            	    break loop95;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2049:6: (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )?
                    int alt97=2;
                    int LA97_0 = input.LA(1);

                    if ( (LA97_0==16) ) {
                        alt97=1;
                    }
                    switch (alt97) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2049:8: otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) )
                            {
                            otherlv_11=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKArc4572); 

                                	newLeafNode(otherlv_11, grammarAccess.getKArcAccess().getPlacementDataKeyword_5_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2053:1: ( (lv_placementData_12_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2054:1: (lv_placementData_12_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2054:1: (lv_placementData_12_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2055:3: lv_placementData_12_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKArcAccess().getPlacementDataKPlacementDataParserRuleCall_5_2_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKArc4593);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2071:4: (otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}' )?
                    int alt100=2;
                    int LA100_0 = input.LA(1);

                    if ( (LA100_0==21) ) {
                        alt100=1;
                    }
                    switch (alt100) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2071:6: otherlv_13= 'children' otherlv_14= '{' ( (lv_children_15_0= ruleKRendering ) ) ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )* otherlv_18= '}'
                            {
                            otherlv_13=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKArc4608); 

                                	newLeafNode(otherlv_13, grammarAccess.getKArcAccess().getChildrenKeyword_5_3_0());
                                
                            otherlv_14=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKArc4620); 

                                	newLeafNode(otherlv_14, grammarAccess.getKArcAccess().getLeftCurlyBracketKeyword_5_3_1());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2079:1: ( (lv_children_15_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2080:1: (lv_children_15_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2080:1: (lv_children_15_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2081:3: lv_children_15_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKArcAccess().getChildrenKRenderingParserRuleCall_5_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKArc4641);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2097:2: ( (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) ) )*
                            loop99:
                            do {
                                int alt99=2;
                                int LA99_0 = input.LA(1);

                                if ( (LA99_0==13||LA99_0==15||LA99_0==18||(LA99_0>=22 && LA99_0<=26)||(LA99_0>=28 && LA99_0<=30)||LA99_0==32||LA99_0==35) ) {
                                    alt99=1;
                                }


                                switch (alt99) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2097:3: (otherlv_16= ',' )? ( (lv_children_17_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2097:3: (otherlv_16= ',' )?
                            	    int alt98=2;
                            	    int LA98_0 = input.LA(1);

                            	    if ( (LA98_0==13) ) {
                            	        alt98=1;
                            	    }
                            	    switch (alt98) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2097:5: otherlv_16= ','
                            	            {
                            	            otherlv_16=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKArc4655); 

                            	                	newLeafNode(otherlv_16, grammarAccess.getKArcAccess().getCommaKeyword_5_3_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2101:3: ( (lv_children_17_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2102:1: (lv_children_17_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2102:1: (lv_children_17_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2103:3: lv_children_17_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKArcAccess().getChildrenKRenderingParserRuleCall_5_3_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKArc4678);
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
                            	    break loop99;
                                }
                            } while (true);

                            otherlv_18=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKArc4692); 

                                	newLeafNode(otherlv_18, grammarAccess.getKArcAccess().getRightCurlyBracketKeyword_5_3_4());
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2123:3: (otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) ) )?
                    int alt101=2;
                    int LA101_0 = input.LA(1);

                    if ( (LA101_0==20) ) {
                        alt101=1;
                    }
                    switch (alt101) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2123:5: otherlv_19= 'childPlacement' ( (lv_childPlacement_20_0= ruleKPlacement ) )
                            {
                            otherlv_19=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKArc4707); 

                                	newLeafNode(otherlv_19, grammarAccess.getKArcAccess().getChildPlacementKeyword_5_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2127:1: ( (lv_childPlacement_20_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2128:1: (lv_childPlacement_20_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2128:1: (lv_childPlacement_20_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2129:3: lv_childPlacement_20_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKArcAccess().getChildPlacementKPlacementParserRuleCall_5_4_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKArc4728);
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

                    otherlv_21=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKArc4742); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2157:1: entryRuleKChildArea returns [EObject current=null] : iv_ruleKChildArea= ruleKChildArea EOF ;
    public final EObject entryRuleKChildArea() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKChildArea = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2158:2: (iv_ruleKChildArea= ruleKChildArea EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2159:2: iv_ruleKChildArea= ruleKChildArea EOF
            {
             newCompositeNode(grammarAccess.getKChildAreaRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKChildArea_in_entryRuleKChildArea4780);
            iv_ruleKChildArea=ruleKChildArea();

            state._fsp--;

             current =iv_ruleKChildArea; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKChildArea4790); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2166:1: ruleKChildArea returns [EObject current=null] : ( () otherlv_1= 'ChildArea' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? otherlv_10= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2169:28: ( ( () otherlv_1= 'ChildArea' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? otherlv_10= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2170:1: ( () otherlv_1= 'ChildArea' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? otherlv_10= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2170:1: ( () otherlv_1= 'ChildArea' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? otherlv_10= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2170:2: () otherlv_1= 'ChildArea' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? otherlv_10= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2170:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2171:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKChildAreaAccess().getKChildAreaAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleKChildArea4836); 

                	newLeafNode(otherlv_1, grammarAccess.getKChildAreaAccess().getChildAreaKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2180:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? otherlv_10= '}' )?
            int alt108=2;
            int LA108_0 = input.LA(1);

            if ( (LA108_0==12) ) {
                alt108=1;
            }
            switch (alt108) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2180:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? otherlv_10= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKChildArea4849); 

                        	newLeafNode(otherlv_2, grammarAccess.getKChildAreaAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2184:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt106=2;
                    int LA106_0 = input.LA(1);

                    if ( (LA106_0==17) ) {
                        alt106=1;
                    }
                    switch (alt106) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2184:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKChildArea4862); 

                                	newLeafNode(otherlv_3, grammarAccess.getKChildAreaAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2188:1: (otherlv_4= ':' )?
                            int alt103=2;
                            int LA103_0 = input.LA(1);

                            if ( (LA103_0==19) ) {
                                alt103=1;
                            }
                            switch (alt103) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2188:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKChildArea4875); 

                                        	newLeafNode(otherlv_4, grammarAccess.getKChildAreaAccess().getColonKeyword_2_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2192:3: ( (lv_styles_5_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2193:1: (lv_styles_5_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2193:1: (lv_styles_5_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2194:3: lv_styles_5_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKChildAreaAccess().getStylesKStyleParserRuleCall_2_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKChildArea4898);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2210:2: ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            loop105:
                            do {
                                int alt105=2;
                                int LA105_0 = input.LA(1);

                                if ( (LA105_0==13||LA105_0==62||(LA105_0>=64 && LA105_0<=74)) ) {
                                    alt105=1;
                                }


                                switch (alt105) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2210:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2210:3: (otherlv_6= ',' )?
                            	    int alt104=2;
                            	    int LA104_0 = input.LA(1);

                            	    if ( (LA104_0==13) ) {
                            	        alt104=1;
                            	    }
                            	    switch (alt104) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2210:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKChildArea4912); 

                            	                	newLeafNode(otherlv_6, grammarAccess.getKChildAreaAccess().getCommaKeyword_2_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2214:3: ( (lv_styles_7_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2215:1: (lv_styles_7_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2215:1: (lv_styles_7_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2216:3: lv_styles_7_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKChildAreaAccess().getStylesKStyleParserRuleCall_2_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKChildArea4935);
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
                            	    break loop105;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2232:6: (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )?
                    int alt107=2;
                    int LA107_0 = input.LA(1);

                    if ( (LA107_0==16) ) {
                        alt107=1;
                    }
                    switch (alt107) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2232:8: otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKChildArea4952); 

                                	newLeafNode(otherlv_8, grammarAccess.getKChildAreaAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2236:1: ( (lv_placementData_9_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2237:1: (lv_placementData_9_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2237:1: (lv_placementData_9_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2238:3: lv_placementData_9_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKChildAreaAccess().getPlacementDataKPlacementDataParserRuleCall_2_2_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKChildArea4973);
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

                    otherlv_10=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKChildArea4987); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2266:1: entryRuleKText returns [EObject current=null] : iv_ruleKText= ruleKText EOF ;
    public final EObject entryRuleKText() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKText = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2267:2: (iv_ruleKText= ruleKText EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2268:2: iv_ruleKText= ruleKText EOF
            {
             newCompositeNode(grammarAccess.getKTextRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKText_in_entryRuleKText5025);
            iv_ruleKText=ruleKText();

            state._fsp--;

             current =iv_ruleKText; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKText5035); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2275:1: ruleKText returns [EObject current=null] : ( () otherlv_1= 'Text' ( (lv_text_2_0= ruleEString ) )? (otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'children' otherlv_13= '{' ( (lv_children_14_0= ruleKRendering ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )* otherlv_17= '}' )? (otherlv_18= 'childPlacement' ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? otherlv_20= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2278:28: ( ( () otherlv_1= 'Text' ( (lv_text_2_0= ruleEString ) )? (otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'children' otherlv_13= '{' ( (lv_children_14_0= ruleKRendering ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )* otherlv_17= '}' )? (otherlv_18= 'childPlacement' ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? otherlv_20= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2279:1: ( () otherlv_1= 'Text' ( (lv_text_2_0= ruleEString ) )? (otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'children' otherlv_13= '{' ( (lv_children_14_0= ruleKRendering ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )* otherlv_17= '}' )? (otherlv_18= 'childPlacement' ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? otherlv_20= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2279:1: ( () otherlv_1= 'Text' ( (lv_text_2_0= ruleEString ) )? (otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'children' otherlv_13= '{' ( (lv_children_14_0= ruleKRendering ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )* otherlv_17= '}' )? (otherlv_18= 'childPlacement' ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? otherlv_20= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2279:2: () otherlv_1= 'Text' ( (lv_text_2_0= ruleEString ) )? (otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'children' otherlv_13= '{' ( (lv_children_14_0= ruleKRendering ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )* otherlv_17= '}' )? (otherlv_18= 'childPlacement' ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? otherlv_20= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2279:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2280:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKTextAccess().getKTextAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,30,FollowSets000.FOLLOW_30_in_ruleKText5081); 

                	newLeafNode(otherlv_1, grammarAccess.getKTextAccess().getTextKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2289:1: ( (lv_text_2_0= ruleEString ) )?
            int alt109=2;
            int LA109_0 = input.LA(1);

            if ( ((LA109_0>=RULE_STRING && LA109_0<=RULE_ID)) ) {
                alt109=1;
            }
            switch (alt109) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2290:1: (lv_text_2_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2290:1: (lv_text_2_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2291:3: lv_text_2_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getTextEStringParserRuleCall_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKText5102);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2307:3: (otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'children' otherlv_13= '{' ( (lv_children_14_0= ruleKRendering ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )* otherlv_17= '}' )? (otherlv_18= 'childPlacement' ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? otherlv_20= '}' )?
            int alt120=2;
            int LA120_0 = input.LA(1);

            if ( (LA120_0==12) ) {
                alt120=1;
            }
            switch (alt120) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2307:5: otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'children' otherlv_13= '{' ( (lv_children_14_0= ruleKRendering ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )* otherlv_17= '}' )? (otherlv_18= 'childPlacement' ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? otherlv_20= '}'
                    {
                    otherlv_3=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKText5116); 

                        	newLeafNode(otherlv_3, grammarAccess.getKTextAccess().getLeftCurlyBracketKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2311:1: ( (lv_clip_4_0= 'clip' ) )?
                    int alt110=2;
                    int LA110_0 = input.LA(1);

                    if ( (LA110_0==31) ) {
                        alt110=1;
                    }
                    switch (alt110) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2312:1: (lv_clip_4_0= 'clip' )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2312:1: (lv_clip_4_0= 'clip' )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2313:3: lv_clip_4_0= 'clip'
                            {
                            lv_clip_4_0=(Token)match(input,31,FollowSets000.FOLLOW_31_in_ruleKText5134); 

                                    newLeafNode(lv_clip_4_0, grammarAccess.getKTextAccess().getClipClipKeyword_3_1_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getKTextRule());
                            	        }
                                   		setWithLastConsumed(current, "clip", true, "clip");
                            	    

                            }


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2326:3: (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )?
                    int alt114=2;
                    int LA114_0 = input.LA(1);

                    if ( (LA114_0==17) ) {
                        alt114=1;
                    }
                    switch (alt114) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2326:5: otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )*
                            {
                            otherlv_5=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKText5161); 

                                	newLeafNode(otherlv_5, grammarAccess.getKTextAccess().getStylesKeyword_3_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2330:1: (otherlv_6= ':' )?
                            int alt111=2;
                            int LA111_0 = input.LA(1);

                            if ( (LA111_0==19) ) {
                                alt111=1;
                            }
                            switch (alt111) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2330:3: otherlv_6= ':'
                                    {
                                    otherlv_6=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKText5174); 

                                        	newLeafNode(otherlv_6, grammarAccess.getKTextAccess().getColonKeyword_3_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2334:3: ( (lv_styles_7_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2335:1: (lv_styles_7_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2335:1: (lv_styles_7_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2336:3: lv_styles_7_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKTextAccess().getStylesKStyleParserRuleCall_3_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKText5197);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2352:2: ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )*
                            loop113:
                            do {
                                int alt113=2;
                                int LA113_0 = input.LA(1);

                                if ( (LA113_0==13||LA113_0==62||(LA113_0>=64 && LA113_0<=74)) ) {
                                    alt113=1;
                                }


                                switch (alt113) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2352:3: (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2352:3: (otherlv_8= ',' )?
                            	    int alt112=2;
                            	    int LA112_0 = input.LA(1);

                            	    if ( (LA112_0==13) ) {
                            	        alt112=1;
                            	    }
                            	    switch (alt112) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2352:5: otherlv_8= ','
                            	            {
                            	            otherlv_8=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKText5211); 

                            	                	newLeafNode(otherlv_8, grammarAccess.getKTextAccess().getCommaKeyword_3_2_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2356:3: ( (lv_styles_9_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2357:1: (lv_styles_9_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2357:1: (lv_styles_9_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2358:3: lv_styles_9_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKTextAccess().getStylesKStyleParserRuleCall_3_2_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKText5234);
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
                            	    break loop113;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2374:6: (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )?
                    int alt115=2;
                    int LA115_0 = input.LA(1);

                    if ( (LA115_0==16) ) {
                        alt115=1;
                    }
                    switch (alt115) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2374:8: otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) )
                            {
                            otherlv_10=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKText5251); 

                                	newLeafNode(otherlv_10, grammarAccess.getKTextAccess().getPlacementDataKeyword_3_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2378:1: ( (lv_placementData_11_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2379:1: (lv_placementData_11_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2379:1: (lv_placementData_11_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2380:3: lv_placementData_11_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKTextAccess().getPlacementDataKPlacementDataParserRuleCall_3_3_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKText5272);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2396:4: (otherlv_12= 'children' otherlv_13= '{' ( (lv_children_14_0= ruleKRendering ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )* otherlv_17= '}' )?
                    int alt118=2;
                    int LA118_0 = input.LA(1);

                    if ( (LA118_0==21) ) {
                        alt118=1;
                    }
                    switch (alt118) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2396:6: otherlv_12= 'children' otherlv_13= '{' ( (lv_children_14_0= ruleKRendering ) ) ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )* otherlv_17= '}'
                            {
                            otherlv_12=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKText5287); 

                                	newLeafNode(otherlv_12, grammarAccess.getKTextAccess().getChildrenKeyword_3_4_0());
                                
                            otherlv_13=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKText5299); 

                                	newLeafNode(otherlv_13, grammarAccess.getKTextAccess().getLeftCurlyBracketKeyword_3_4_1());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2404:1: ( (lv_children_14_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2405:1: (lv_children_14_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2405:1: (lv_children_14_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2406:3: lv_children_14_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKTextAccess().getChildrenKRenderingParserRuleCall_3_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKText5320);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2422:2: ( (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) ) )*
                            loop117:
                            do {
                                int alt117=2;
                                int LA117_0 = input.LA(1);

                                if ( (LA117_0==13||LA117_0==15||LA117_0==18||(LA117_0>=22 && LA117_0<=26)||(LA117_0>=28 && LA117_0<=30)||LA117_0==32||LA117_0==35) ) {
                                    alt117=1;
                                }


                                switch (alt117) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2422:3: (otherlv_15= ',' )? ( (lv_children_16_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2422:3: (otherlv_15= ',' )?
                            	    int alt116=2;
                            	    int LA116_0 = input.LA(1);

                            	    if ( (LA116_0==13) ) {
                            	        alt116=1;
                            	    }
                            	    switch (alt116) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2422:5: otherlv_15= ','
                            	            {
                            	            otherlv_15=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKText5334); 

                            	                	newLeafNode(otherlv_15, grammarAccess.getKTextAccess().getCommaKeyword_3_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2426:3: ( (lv_children_16_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2427:1: (lv_children_16_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2427:1: (lv_children_16_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2428:3: lv_children_16_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKTextAccess().getChildrenKRenderingParserRuleCall_3_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKText5357);
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
                            	    break loop117;
                                }
                            } while (true);

                            otherlv_17=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKText5371); 

                                	newLeafNode(otherlv_17, grammarAccess.getKTextAccess().getRightCurlyBracketKeyword_3_4_4());
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2448:3: (otherlv_18= 'childPlacement' ( (lv_childPlacement_19_0= ruleKPlacement ) ) )?
                    int alt119=2;
                    int LA119_0 = input.LA(1);

                    if ( (LA119_0==20) ) {
                        alt119=1;
                    }
                    switch (alt119) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2448:5: otherlv_18= 'childPlacement' ( (lv_childPlacement_19_0= ruleKPlacement ) )
                            {
                            otherlv_18=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKText5386); 

                                	newLeafNode(otherlv_18, grammarAccess.getKTextAccess().getChildPlacementKeyword_3_5_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2452:1: ( (lv_childPlacement_19_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2453:1: (lv_childPlacement_19_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2453:1: (lv_childPlacement_19_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2454:3: lv_childPlacement_19_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKTextAccess().getChildPlacementKPlacementParserRuleCall_3_5_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKText5407);
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

                    otherlv_20=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKText5421); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2482:1: entryRuleKCustomRendering returns [EObject current=null] : iv_ruleKCustomRendering= ruleKCustomRendering EOF ;
    public final EObject entryRuleKCustomRendering() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKCustomRendering = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2483:2: (iv_ruleKCustomRendering= ruleKCustomRendering EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2484:2: iv_ruleKCustomRendering= ruleKCustomRendering EOF
            {
             newCompositeNode(grammarAccess.getKCustomRenderingRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKCustomRendering_in_entryRuleKCustomRendering5459);
            iv_ruleKCustomRendering=ruleKCustomRendering();

            state._fsp--;

             current =iv_ruleKCustomRendering; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKCustomRendering5469); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2491:1: ruleKCustomRendering returns [EObject current=null] : ( () otherlv_1= 'CustomRendering' (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'children' otherlv_15= '{' ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* otherlv_19= '}' )? (otherlv_20= 'childPlacement' ( (lv_childPlacement_21_0= ruleKPlacement ) ) )? otherlv_22= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2494:28: ( ( () otherlv_1= 'CustomRendering' (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'children' otherlv_15= '{' ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* otherlv_19= '}' )? (otherlv_20= 'childPlacement' ( (lv_childPlacement_21_0= ruleKPlacement ) ) )? otherlv_22= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2495:1: ( () otherlv_1= 'CustomRendering' (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'children' otherlv_15= '{' ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* otherlv_19= '}' )? (otherlv_20= 'childPlacement' ( (lv_childPlacement_21_0= ruleKPlacement ) ) )? otherlv_22= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2495:1: ( () otherlv_1= 'CustomRendering' (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'children' otherlv_15= '{' ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* otherlv_19= '}' )? (otherlv_20= 'childPlacement' ( (lv_childPlacement_21_0= ruleKPlacement ) ) )? otherlv_22= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2495:2: () otherlv_1= 'CustomRendering' (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'children' otherlv_15= '{' ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* otherlv_19= '}' )? (otherlv_20= 'childPlacement' ( (lv_childPlacement_21_0= ruleKPlacement ) ) )? otherlv_22= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2495:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2496:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKCustomRenderingAccess().getKCustomRenderingAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKCustomRendering5515); 

                	newLeafNode(otherlv_1, grammarAccess.getKCustomRenderingAccess().getCustomRenderingKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2505:1: (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'children' otherlv_15= '{' ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* otherlv_19= '}' )? (otherlv_20= 'childPlacement' ( (lv_childPlacement_21_0= ruleKPlacement ) ) )? otherlv_22= '}' )?
            int alt130=2;
            int LA130_0 = input.LA(1);

            if ( (LA130_0==12) ) {
                alt130=1;
            }
            switch (alt130) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2505:3: otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'children' otherlv_15= '{' ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* otherlv_19= '}' )? (otherlv_20= 'childPlacement' ( (lv_childPlacement_21_0= ruleKPlacement ) ) )? otherlv_22= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKCustomRendering5528); 

                        	newLeafNode(otherlv_2, grammarAccess.getKCustomRenderingAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    otherlv_3=(Token)match(input,33,FollowSets000.FOLLOW_33_in_ruleKCustomRendering5540); 

                        	newLeafNode(otherlv_3, grammarAccess.getKCustomRenderingAccess().getClassNameKeyword_2_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2513:1: ( (lv_className_4_0= ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2514:1: (lv_className_4_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2514:1: (lv_className_4_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2515:3: lv_className_4_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getClassNameEStringParserRuleCall_2_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKCustomRendering5561);
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

                    otherlv_5=(Token)match(input,34,FollowSets000.FOLLOW_34_in_ruleKCustomRendering5573); 

                        	newLeafNode(otherlv_5, grammarAccess.getKCustomRenderingAccess().getBundleNameKeyword_2_3());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2535:1: ( (lv_bundleName_6_0= ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2536:1: (lv_bundleName_6_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2536:1: (lv_bundleName_6_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2537:3: lv_bundleName_6_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getBundleNameEStringParserRuleCall_2_4_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKCustomRendering5594);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2553:2: (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )?
                    int alt124=2;
                    int LA124_0 = input.LA(1);

                    if ( (LA124_0==17) ) {
                        alt124=1;
                    }
                    switch (alt124) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2553:4: otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )*
                            {
                            otherlv_7=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKCustomRendering5607); 

                                	newLeafNode(otherlv_7, grammarAccess.getKCustomRenderingAccess().getStylesKeyword_2_5_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2557:1: (otherlv_8= ':' )?
                            int alt121=2;
                            int LA121_0 = input.LA(1);

                            if ( (LA121_0==19) ) {
                                alt121=1;
                            }
                            switch (alt121) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2557:3: otherlv_8= ':'
                                    {
                                    otherlv_8=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKCustomRendering5620); 

                                        	newLeafNode(otherlv_8, grammarAccess.getKCustomRenderingAccess().getColonKeyword_2_5_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2561:3: ( (lv_styles_9_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2562:1: (lv_styles_9_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2562:1: (lv_styles_9_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2563:3: lv_styles_9_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getStylesKStyleParserRuleCall_2_5_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKCustomRendering5643);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2579:2: ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )*
                            loop123:
                            do {
                                int alt123=2;
                                int LA123_0 = input.LA(1);

                                if ( (LA123_0==13||LA123_0==62||(LA123_0>=64 && LA123_0<=74)) ) {
                                    alt123=1;
                                }


                                switch (alt123) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2579:3: (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2579:3: (otherlv_10= ',' )?
                            	    int alt122=2;
                            	    int LA122_0 = input.LA(1);

                            	    if ( (LA122_0==13) ) {
                            	        alt122=1;
                            	    }
                            	    switch (alt122) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2579:5: otherlv_10= ','
                            	            {
                            	            otherlv_10=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKCustomRendering5657); 

                            	                	newLeafNode(otherlv_10, grammarAccess.getKCustomRenderingAccess().getCommaKeyword_2_5_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2583:3: ( (lv_styles_11_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2584:1: (lv_styles_11_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2584:1: (lv_styles_11_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2585:3: lv_styles_11_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getStylesKStyleParserRuleCall_2_5_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKCustomRendering5680);
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
                            	    break loop123;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2601:6: (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )?
                    int alt125=2;
                    int LA125_0 = input.LA(1);

                    if ( (LA125_0==16) ) {
                        alt125=1;
                    }
                    switch (alt125) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2601:8: otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) )
                            {
                            otherlv_12=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKCustomRendering5697); 

                                	newLeafNode(otherlv_12, grammarAccess.getKCustomRenderingAccess().getPlacementDataKeyword_2_6_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2605:1: ( (lv_placementData_13_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2606:1: (lv_placementData_13_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2606:1: (lv_placementData_13_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2607:3: lv_placementData_13_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getPlacementDataKPlacementDataParserRuleCall_2_6_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKCustomRendering5718);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2623:4: (otherlv_14= 'children' otherlv_15= '{' ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* otherlv_19= '}' )?
                    int alt128=2;
                    int LA128_0 = input.LA(1);

                    if ( (LA128_0==21) ) {
                        alt128=1;
                    }
                    switch (alt128) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2623:6: otherlv_14= 'children' otherlv_15= '{' ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* otherlv_19= '}'
                            {
                            otherlv_14=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKCustomRendering5733); 

                                	newLeafNode(otherlv_14, grammarAccess.getKCustomRenderingAccess().getChildrenKeyword_2_7_0());
                                
                            otherlv_15=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKCustomRendering5745); 

                                	newLeafNode(otherlv_15, grammarAccess.getKCustomRenderingAccess().getLeftCurlyBracketKeyword_2_7_1());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2631:1: ( (lv_children_16_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2632:1: (lv_children_16_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2632:1: (lv_children_16_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2633:3: lv_children_16_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getChildrenKRenderingParserRuleCall_2_7_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKCustomRendering5766);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2649:2: ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            loop127:
                            do {
                                int alt127=2;
                                int LA127_0 = input.LA(1);

                                if ( (LA127_0==13||LA127_0==15||LA127_0==18||(LA127_0>=22 && LA127_0<=26)||(LA127_0>=28 && LA127_0<=30)||LA127_0==32||LA127_0==35) ) {
                                    alt127=1;
                                }


                                switch (alt127) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2649:3: (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2649:3: (otherlv_17= ',' )?
                            	    int alt126=2;
                            	    int LA126_0 = input.LA(1);

                            	    if ( (LA126_0==13) ) {
                            	        alt126=1;
                            	    }
                            	    switch (alt126) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2649:5: otherlv_17= ','
                            	            {
                            	            otherlv_17=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKCustomRendering5780); 

                            	                	newLeafNode(otherlv_17, grammarAccess.getKCustomRenderingAccess().getCommaKeyword_2_7_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2653:3: ( (lv_children_18_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2654:1: (lv_children_18_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2654:1: (lv_children_18_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2655:3: lv_children_18_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getChildrenKRenderingParserRuleCall_2_7_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKCustomRendering5803);
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
                            	    break loop127;
                                }
                            } while (true);

                            otherlv_19=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKCustomRendering5817); 

                                	newLeafNode(otherlv_19, grammarAccess.getKCustomRenderingAccess().getRightCurlyBracketKeyword_2_7_4());
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2675:3: (otherlv_20= 'childPlacement' ( (lv_childPlacement_21_0= ruleKPlacement ) ) )?
                    int alt129=2;
                    int LA129_0 = input.LA(1);

                    if ( (LA129_0==20) ) {
                        alt129=1;
                    }
                    switch (alt129) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2675:5: otherlv_20= 'childPlacement' ( (lv_childPlacement_21_0= ruleKPlacement ) )
                            {
                            otherlv_20=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKCustomRendering5832); 

                                	newLeafNode(otherlv_20, grammarAccess.getKCustomRenderingAccess().getChildPlacementKeyword_2_8_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2679:1: ( (lv_childPlacement_21_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2680:1: (lv_childPlacement_21_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2680:1: (lv_childPlacement_21_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2681:3: lv_childPlacement_21_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getChildPlacementKPlacementParserRuleCall_2_8_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKCustomRendering5853);
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

                    otherlv_22=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKCustomRendering5867); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2709:1: entryRuleKSpline returns [EObject current=null] : iv_ruleKSpline= ruleKSpline EOF ;
    public final EObject entryRuleKSpline() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKSpline = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2710:2: (iv_ruleKSpline= ruleKSpline EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2711:2: iv_ruleKSpline= ruleKSpline EOF
            {
             newCompositeNode(grammarAccess.getKSplineRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKSpline_in_entryRuleKSpline5905);
            iv_ruleKSpline=ruleKSpline();

            state._fsp--;

             current =iv_ruleKSpline; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKSpline5915); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2718:1: ruleKSpline returns [EObject current=null] : ( () otherlv_1= 'Spline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? (otherlv_10= 'children' otherlv_11= '{' ( (lv_children_12_0= ruleKRendering ) ) ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )* otherlv_15= '}' )? (otherlv_16= 'childPlacement' ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? otherlv_18= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2721:28: ( ( () otherlv_1= 'Spline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? (otherlv_10= 'children' otherlv_11= '{' ( (lv_children_12_0= ruleKRendering ) ) ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )* otherlv_15= '}' )? (otherlv_16= 'childPlacement' ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? otherlv_18= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2722:1: ( () otherlv_1= 'Spline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? (otherlv_10= 'children' otherlv_11= '{' ( (lv_children_12_0= ruleKRendering ) ) ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )* otherlv_15= '}' )? (otherlv_16= 'childPlacement' ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? otherlv_18= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2722:1: ( () otherlv_1= 'Spline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? (otherlv_10= 'children' otherlv_11= '{' ( (lv_children_12_0= ruleKRendering ) ) ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )* otherlv_15= '}' )? (otherlv_16= 'childPlacement' ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? otherlv_18= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2722:2: () otherlv_1= 'Spline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? (otherlv_10= 'children' otherlv_11= '{' ( (lv_children_12_0= ruleKRendering ) ) ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )* otherlv_15= '}' )? (otherlv_16= 'childPlacement' ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? otherlv_18= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2722:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2723:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKSplineAccess().getKSplineAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKSpline5961); 

                	newLeafNode(otherlv_1, grammarAccess.getKSplineAccess().getSplineKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2732:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? (otherlv_10= 'children' otherlv_11= '{' ( (lv_children_12_0= ruleKRendering ) ) ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )* otherlv_15= '}' )? (otherlv_16= 'childPlacement' ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? otherlv_18= '}' )?
            int alt140=2;
            int LA140_0 = input.LA(1);

            if ( (LA140_0==12) ) {
                alt140=1;
            }
            switch (alt140) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2732:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )? (otherlv_10= 'children' otherlv_11= '{' ( (lv_children_12_0= ruleKRendering ) ) ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )* otherlv_15= '}' )? (otherlv_16= 'childPlacement' ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? otherlv_18= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKSpline5974); 

                        	newLeafNode(otherlv_2, grammarAccess.getKSplineAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2736:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt134=2;
                    int LA134_0 = input.LA(1);

                    if ( (LA134_0==17) ) {
                        alt134=1;
                    }
                    switch (alt134) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2736:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKSpline5987); 

                                	newLeafNode(otherlv_3, grammarAccess.getKSplineAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2740:1: (otherlv_4= ':' )?
                            int alt131=2;
                            int LA131_0 = input.LA(1);

                            if ( (LA131_0==19) ) {
                                alt131=1;
                            }
                            switch (alt131) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2740:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKSpline6000); 

                                        	newLeafNode(otherlv_4, grammarAccess.getKSplineAccess().getColonKeyword_2_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2744:3: ( (lv_styles_5_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2745:1: (lv_styles_5_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2745:1: (lv_styles_5_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2746:3: lv_styles_5_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKSplineAccess().getStylesKStyleParserRuleCall_2_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKSpline6023);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2762:2: ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            loop133:
                            do {
                                int alt133=2;
                                int LA133_0 = input.LA(1);

                                if ( (LA133_0==13||LA133_0==62||(LA133_0>=64 && LA133_0<=74)) ) {
                                    alt133=1;
                                }


                                switch (alt133) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2762:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2762:3: (otherlv_6= ',' )?
                            	    int alt132=2;
                            	    int LA132_0 = input.LA(1);

                            	    if ( (LA132_0==13) ) {
                            	        alt132=1;
                            	    }
                            	    switch (alt132) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2762:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKSpline6037); 

                            	                	newLeafNode(otherlv_6, grammarAccess.getKSplineAccess().getCommaKeyword_2_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2766:3: ( (lv_styles_7_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2767:1: (lv_styles_7_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2767:1: (lv_styles_7_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2768:3: lv_styles_7_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKSplineAccess().getStylesKStyleParserRuleCall_2_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKSpline6060);
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
                            	    break loop133;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2784:6: (otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) ) )?
                    int alt135=2;
                    int LA135_0 = input.LA(1);

                    if ( (LA135_0==16) ) {
                        alt135=1;
                    }
                    switch (alt135) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2784:8: otherlv_8= 'placementData' ( (lv_placementData_9_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKSpline6077); 

                                	newLeafNode(otherlv_8, grammarAccess.getKSplineAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2788:1: ( (lv_placementData_9_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2789:1: (lv_placementData_9_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2789:1: (lv_placementData_9_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2790:3: lv_placementData_9_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKSplineAccess().getPlacementDataKPlacementDataParserRuleCall_2_2_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKSpline6098);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2806:4: (otherlv_10= 'children' otherlv_11= '{' ( (lv_children_12_0= ruleKRendering ) ) ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )* otherlv_15= '}' )?
                    int alt138=2;
                    int LA138_0 = input.LA(1);

                    if ( (LA138_0==21) ) {
                        alt138=1;
                    }
                    switch (alt138) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2806:6: otherlv_10= 'children' otherlv_11= '{' ( (lv_children_12_0= ruleKRendering ) ) ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )* otherlv_15= '}'
                            {
                            otherlv_10=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKSpline6113); 

                                	newLeafNode(otherlv_10, grammarAccess.getKSplineAccess().getChildrenKeyword_2_3_0());
                                
                            otherlv_11=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKSpline6125); 

                                	newLeafNode(otherlv_11, grammarAccess.getKSplineAccess().getLeftCurlyBracketKeyword_2_3_1());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2814:1: ( (lv_children_12_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2815:1: (lv_children_12_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2815:1: (lv_children_12_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2816:3: lv_children_12_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKSplineAccess().getChildrenKRenderingParserRuleCall_2_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKSpline6146);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2832:2: ( (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) ) )*
                            loop137:
                            do {
                                int alt137=2;
                                int LA137_0 = input.LA(1);

                                if ( (LA137_0==13||LA137_0==15||LA137_0==18||(LA137_0>=22 && LA137_0<=26)||(LA137_0>=28 && LA137_0<=30)||LA137_0==32||LA137_0==35) ) {
                                    alt137=1;
                                }


                                switch (alt137) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2832:3: (otherlv_13= ',' )? ( (lv_children_14_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2832:3: (otherlv_13= ',' )?
                            	    int alt136=2;
                            	    int LA136_0 = input.LA(1);

                            	    if ( (LA136_0==13) ) {
                            	        alt136=1;
                            	    }
                            	    switch (alt136) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2832:5: otherlv_13= ','
                            	            {
                            	            otherlv_13=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKSpline6160); 

                            	                	newLeafNode(otherlv_13, grammarAccess.getKSplineAccess().getCommaKeyword_2_3_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2836:3: ( (lv_children_14_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2837:1: (lv_children_14_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2837:1: (lv_children_14_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2838:3: lv_children_14_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKSplineAccess().getChildrenKRenderingParserRuleCall_2_3_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKSpline6183);
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
                            	    break loop137;
                                }
                            } while (true);

                            otherlv_15=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKSpline6197); 

                                	newLeafNode(otherlv_15, grammarAccess.getKSplineAccess().getRightCurlyBracketKeyword_2_3_4());
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2858:3: (otherlv_16= 'childPlacement' ( (lv_childPlacement_17_0= ruleKPlacement ) ) )?
                    int alt139=2;
                    int LA139_0 = input.LA(1);

                    if ( (LA139_0==20) ) {
                        alt139=1;
                    }
                    switch (alt139) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2858:5: otherlv_16= 'childPlacement' ( (lv_childPlacement_17_0= ruleKPlacement ) )
                            {
                            otherlv_16=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKSpline6212); 

                                	newLeafNode(otherlv_16, grammarAccess.getKSplineAccess().getChildPlacementKeyword_2_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2862:1: ( (lv_childPlacement_17_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2863:1: (lv_childPlacement_17_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2863:1: (lv_childPlacement_17_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2864:3: lv_childPlacement_17_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKSplineAccess().getChildPlacementKPlacementParserRuleCall_2_4_1_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKSpline6233);
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

                    otherlv_18=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKSpline6247); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2892:1: entryRuleKDecoratorPlacementData returns [EObject current=null] : iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF ;
    public final EObject entryRuleKDecoratorPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKDecoratorPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2893:2: (iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2894:2: iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKDecoratorPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKDecoratorPlacementData_in_entryRuleKDecoratorPlacementData6285);
            iv_ruleKDecoratorPlacementData=ruleKDecoratorPlacementData();

            state._fsp--;

             current =iv_ruleKDecoratorPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKDecoratorPlacementData6295); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2901:1: ruleKDecoratorPlacementData returns [EObject current=null] : (otherlv_0= 'DecoratorPlacementData' otherlv_1= '{' ( (lv_relative_2_0= 'relative' ) )? otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )? (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )? otherlv_13= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2904:28: ( (otherlv_0= 'DecoratorPlacementData' otherlv_1= '{' ( (lv_relative_2_0= 'relative' ) )? otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )? (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )? otherlv_13= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2905:1: (otherlv_0= 'DecoratorPlacementData' otherlv_1= '{' ( (lv_relative_2_0= 'relative' ) )? otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )? (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )? otherlv_13= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2905:1: (otherlv_0= 'DecoratorPlacementData' otherlv_1= '{' ( (lv_relative_2_0= 'relative' ) )? otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )? (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )? otherlv_13= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2905:3: otherlv_0= 'DecoratorPlacementData' otherlv_1= '{' ( (lv_relative_2_0= 'relative' ) )? otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )? (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )? otherlv_13= '}'
            {
            otherlv_0=(Token)match(input,36,FollowSets000.FOLLOW_36_in_ruleKDecoratorPlacementData6332); 

                	newLeafNode(otherlv_0, grammarAccess.getKDecoratorPlacementDataAccess().getDecoratorPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKDecoratorPlacementData6344); 

                	newLeafNode(otherlv_1, grammarAccess.getKDecoratorPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2913:1: ( (lv_relative_2_0= 'relative' ) )?
            int alt141=2;
            int LA141_0 = input.LA(1);

            if ( (LA141_0==37) ) {
                alt141=1;
            }
            switch (alt141) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2914:1: (lv_relative_2_0= 'relative' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2914:1: (lv_relative_2_0= 'relative' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2915:3: lv_relative_2_0= 'relative'
                    {
                    lv_relative_2_0=(Token)match(input,37,FollowSets000.FOLLOW_37_in_ruleKDecoratorPlacementData6362); 

                            newLeafNode(lv_relative_2_0, grammarAccess.getKDecoratorPlacementDataAccess().getRelativeRelativeKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKDecoratorPlacementDataRule());
                    	        }
                           		setWithLastConsumed(current, "relative", true, "relative");
                    	    

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,38,FollowSets000.FOLLOW_38_in_ruleKDecoratorPlacementData6388); 

                	newLeafNode(otherlv_3, grammarAccess.getKDecoratorPlacementDataAccess().getLocationKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2932:1: ( (lv_location_4_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2933:1: (lv_location_4_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2933:1: (lv_location_4_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2934:3: lv_location_4_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getLocationEFloatParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData6409);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2950:2: (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )?
            int alt142=2;
            int LA142_0 = input.LA(1);

            if ( (LA142_0==39) ) {
                alt142=1;
            }
            switch (alt142) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2950:4: otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,39,FollowSets000.FOLLOW_39_in_ruleKDecoratorPlacementData6422); 

                        	newLeafNode(otherlv_5, grammarAccess.getKDecoratorPlacementDataAccess().getXOffsetKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2954:1: ( (lv_xOffset_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2955:1: (lv_xOffset_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2955:1: (lv_xOffset_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2956:3: lv_xOffset_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getXOffsetEFloatParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData6443);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2972:4: (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )?
            int alt143=2;
            int LA143_0 = input.LA(1);

            if ( (LA143_0==40) ) {
                alt143=1;
            }
            switch (alt143) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2972:6: otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,40,FollowSets000.FOLLOW_40_in_ruleKDecoratorPlacementData6458); 

                        	newLeafNode(otherlv_7, grammarAccess.getKDecoratorPlacementDataAccess().getYOffsetKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2976:1: ( (lv_yOffset_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2977:1: (lv_yOffset_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2977:1: (lv_yOffset_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2978:3: lv_yOffset_8_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getYOffsetEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData6479);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2994:4: (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )?
            int alt144=2;
            int LA144_0 = input.LA(1);

            if ( (LA144_0==41) ) {
                alt144=1;
            }
            switch (alt144) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2994:6: otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) )
                    {
                    otherlv_9=(Token)match(input,41,FollowSets000.FOLLOW_41_in_ruleKDecoratorPlacementData6494); 

                        	newLeafNode(otherlv_9, grammarAccess.getKDecoratorPlacementDataAccess().getWidthKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2998:1: ( (lv_width_10_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2999:1: (lv_width_10_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2999:1: (lv_width_10_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3000:3: lv_width_10_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getWidthEFloatParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData6515);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3016:4: (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )?
            int alt145=2;
            int LA145_0 = input.LA(1);

            if ( (LA145_0==42) ) {
                alt145=1;
            }
            switch (alt145) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3016:6: otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) )
                    {
                    otherlv_11=(Token)match(input,42,FollowSets000.FOLLOW_42_in_ruleKDecoratorPlacementData6530); 

                        	newLeafNode(otherlv_11, grammarAccess.getKDecoratorPlacementDataAccess().getHeightKeyword_8_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3020:1: ( (lv_height_12_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3021:1: (lv_height_12_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3021:1: (lv_height_12_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3022:3: lv_height_12_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getHeightEFloatParserRuleCall_8_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData6551);
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

            otherlv_13=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKDecoratorPlacementData6565); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3050:1: entryRuleKGridPlacementData returns [EObject current=null] : iv_ruleKGridPlacementData= ruleKGridPlacementData EOF ;
    public final EObject entryRuleKGridPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKGridPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3051:2: (iv_ruleKGridPlacementData= ruleKGridPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3052:2: iv_ruleKGridPlacementData= ruleKGridPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKGridPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKGridPlacementData_in_entryRuleKGridPlacementData6601);
            iv_ruleKGridPlacementData=ruleKGridPlacementData();

            state._fsp--;

             current =iv_ruleKGridPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKGridPlacementData6611); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3059:1: ruleKGridPlacementData returns [EObject current=null] : (otherlv_0= 'GridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'insetRight' ( (lv_insetRight_7_0= ruleEFloat ) ) otherlv_8= 'insetBottom' ( (lv_insetBottom_9_0= ruleEFloat ) ) otherlv_10= 'insetLeft' ( (lv_insetLeft_11_0= ruleEFloat ) ) otherlv_12= 'insetTop' ( (lv_insetTop_13_0= ruleEFloat ) ) otherlv_14= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3062:28: ( (otherlv_0= 'GridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'insetRight' ( (lv_insetRight_7_0= ruleEFloat ) ) otherlv_8= 'insetBottom' ( (lv_insetBottom_9_0= ruleEFloat ) ) otherlv_10= 'insetLeft' ( (lv_insetLeft_11_0= ruleEFloat ) ) otherlv_12= 'insetTop' ( (lv_insetTop_13_0= ruleEFloat ) ) otherlv_14= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3063:1: (otherlv_0= 'GridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'insetRight' ( (lv_insetRight_7_0= ruleEFloat ) ) otherlv_8= 'insetBottom' ( (lv_insetBottom_9_0= ruleEFloat ) ) otherlv_10= 'insetLeft' ( (lv_insetLeft_11_0= ruleEFloat ) ) otherlv_12= 'insetTop' ( (lv_insetTop_13_0= ruleEFloat ) ) otherlv_14= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3063:1: (otherlv_0= 'GridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'insetRight' ( (lv_insetRight_7_0= ruleEFloat ) ) otherlv_8= 'insetBottom' ( (lv_insetBottom_9_0= ruleEFloat ) ) otherlv_10= 'insetLeft' ( (lv_insetLeft_11_0= ruleEFloat ) ) otherlv_12= 'insetTop' ( (lv_insetTop_13_0= ruleEFloat ) ) otherlv_14= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3063:3: otherlv_0= 'GridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'insetRight' ( (lv_insetRight_7_0= ruleEFloat ) ) otherlv_8= 'insetBottom' ( (lv_insetBottom_9_0= ruleEFloat ) ) otherlv_10= 'insetLeft' ( (lv_insetLeft_11_0= ruleEFloat ) ) otherlv_12= 'insetTop' ( (lv_insetTop_13_0= ruleEFloat ) ) otherlv_14= '}'
            {
            otherlv_0=(Token)match(input,43,FollowSets000.FOLLOW_43_in_ruleKGridPlacementData6648); 

                	newLeafNode(otherlv_0, grammarAccess.getKGridPlacementDataAccess().getGridPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKGridPlacementData6660); 

                	newLeafNode(otherlv_1, grammarAccess.getKGridPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,44,FollowSets000.FOLLOW_44_in_ruleKGridPlacementData6672); 

                	newLeafNode(otherlv_2, grammarAccess.getKGridPlacementDataAccess().getWidthHintKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3075:1: ( (lv_widthHint_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3076:1: (lv_widthHint_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3076:1: (lv_widthHint_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3077:3: lv_widthHint_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getWidthHintEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData6693);
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

            otherlv_4=(Token)match(input,45,FollowSets000.FOLLOW_45_in_ruleKGridPlacementData6705); 

                	newLeafNode(otherlv_4, grammarAccess.getKGridPlacementDataAccess().getHeightHintKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3097:1: ( (lv_heightHint_5_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3098:1: (lv_heightHint_5_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3098:1: (lv_heightHint_5_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3099:3: lv_heightHint_5_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getHeightHintEFloatParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData6726);
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

            otherlv_6=(Token)match(input,46,FollowSets000.FOLLOW_46_in_ruleKGridPlacementData6738); 

                	newLeafNode(otherlv_6, grammarAccess.getKGridPlacementDataAccess().getInsetRightKeyword_6());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3119:1: ( (lv_insetRight_7_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3120:1: (lv_insetRight_7_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3120:1: (lv_insetRight_7_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3121:3: lv_insetRight_7_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getInsetRightEFloatParserRuleCall_7_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData6759);
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

            otherlv_8=(Token)match(input,47,FollowSets000.FOLLOW_47_in_ruleKGridPlacementData6771); 

                	newLeafNode(otherlv_8, grammarAccess.getKGridPlacementDataAccess().getInsetBottomKeyword_8());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3141:1: ( (lv_insetBottom_9_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3142:1: (lv_insetBottom_9_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3142:1: (lv_insetBottom_9_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3143:3: lv_insetBottom_9_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getInsetBottomEFloatParserRuleCall_9_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData6792);
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

            otherlv_10=(Token)match(input,48,FollowSets000.FOLLOW_48_in_ruleKGridPlacementData6804); 

                	newLeafNode(otherlv_10, grammarAccess.getKGridPlacementDataAccess().getInsetLeftKeyword_10());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3163:1: ( (lv_insetLeft_11_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3164:1: (lv_insetLeft_11_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3164:1: (lv_insetLeft_11_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3165:3: lv_insetLeft_11_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getInsetLeftEFloatParserRuleCall_11_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData6825);
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

            otherlv_12=(Token)match(input,49,FollowSets000.FOLLOW_49_in_ruleKGridPlacementData6837); 

                	newLeafNode(otherlv_12, grammarAccess.getKGridPlacementDataAccess().getInsetTopKeyword_12());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3185:1: ( (lv_insetTop_13_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3186:1: (lv_insetTop_13_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3186:1: (lv_insetTop_13_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3187:3: lv_insetTop_13_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getInsetTopEFloatParserRuleCall_13_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData6858);
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

            otherlv_14=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKGridPlacementData6870); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3215:1: entryRuleKStackPlacementData returns [EObject current=null] : iv_ruleKStackPlacementData= ruleKStackPlacementData EOF ;
    public final EObject entryRuleKStackPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStackPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3216:2: (iv_ruleKStackPlacementData= ruleKStackPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3217:2: iv_ruleKStackPlacementData= ruleKStackPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKStackPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKStackPlacementData_in_entryRuleKStackPlacementData6906);
            iv_ruleKStackPlacementData=ruleKStackPlacementData();

            state._fsp--;

             current =iv_ruleKStackPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKStackPlacementData6916); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3224:1: ruleKStackPlacementData returns [EObject current=null] : (otherlv_0= 'StackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3227:28: ( (otherlv_0= 'StackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3228:1: (otherlv_0= 'StackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3228:1: (otherlv_0= 'StackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3228:3: otherlv_0= 'StackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}'
            {
            otherlv_0=(Token)match(input,50,FollowSets000.FOLLOW_50_in_ruleKStackPlacementData6953); 

                	newLeafNode(otherlv_0, grammarAccess.getKStackPlacementDataAccess().getStackPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKStackPlacementData6965); 

                	newLeafNode(otherlv_1, grammarAccess.getKStackPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,46,FollowSets000.FOLLOW_46_in_ruleKStackPlacementData6977); 

                	newLeafNode(otherlv_2, grammarAccess.getKStackPlacementDataAccess().getInsetRightKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3240:1: ( (lv_insetRight_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3241:1: (lv_insetRight_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3241:1: (lv_insetRight_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3242:3: lv_insetRight_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetRightEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData6998);
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

            otherlv_4=(Token)match(input,47,FollowSets000.FOLLOW_47_in_ruleKStackPlacementData7010); 

                	newLeafNode(otherlv_4, grammarAccess.getKStackPlacementDataAccess().getInsetBottomKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3262:1: ( (lv_insetBottom_5_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3263:1: (lv_insetBottom_5_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3263:1: (lv_insetBottom_5_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3264:3: lv_insetBottom_5_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetBottomEFloatParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData7031);
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

            otherlv_6=(Token)match(input,48,FollowSets000.FOLLOW_48_in_ruleKStackPlacementData7043); 

                	newLeafNode(otherlv_6, grammarAccess.getKStackPlacementDataAccess().getInsetLeftKeyword_6());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3284:1: ( (lv_insetLeft_7_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3285:1: (lv_insetLeft_7_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3285:1: (lv_insetLeft_7_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3286:3: lv_insetLeft_7_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetLeftEFloatParserRuleCall_7_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData7064);
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

            otherlv_8=(Token)match(input,49,FollowSets000.FOLLOW_49_in_ruleKStackPlacementData7076); 

                	newLeafNode(otherlv_8, grammarAccess.getKStackPlacementDataAccess().getInsetTopKeyword_8());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3306:1: ( (lv_insetTop_9_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3307:1: (lv_insetTop_9_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3307:1: (lv_insetTop_9_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3308:3: lv_insetTop_9_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetTopEFloatParserRuleCall_9_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData7097);
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

            otherlv_10=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKStackPlacementData7109); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3336:1: entryRuleKDirectPlacementData returns [EObject current=null] : iv_ruleKDirectPlacementData= ruleKDirectPlacementData EOF ;
    public final EObject entryRuleKDirectPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKDirectPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3337:2: (iv_ruleKDirectPlacementData= ruleKDirectPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3338:2: iv_ruleKDirectPlacementData= ruleKDirectPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKDirectPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKDirectPlacementData_in_entryRuleKDirectPlacementData7145);
            iv_ruleKDirectPlacementData=ruleKDirectPlacementData();

            state._fsp--;

             current =iv_ruleKDirectPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKDirectPlacementData7155); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3345:1: ruleKDirectPlacementData returns [EObject current=null] : (otherlv_0= 'DirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'bottomRight' ( (lv_bottomRight_6_0= ruleKPosition ) ) otherlv_7= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3348:28: ( (otherlv_0= 'DirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'bottomRight' ( (lv_bottomRight_6_0= ruleKPosition ) ) otherlv_7= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3349:1: (otherlv_0= 'DirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'bottomRight' ( (lv_bottomRight_6_0= ruleKPosition ) ) otherlv_7= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3349:1: (otherlv_0= 'DirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'bottomRight' ( (lv_bottomRight_6_0= ruleKPosition ) ) otherlv_7= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3349:3: otherlv_0= 'DirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'bottomRight' ( (lv_bottomRight_6_0= ruleKPosition ) ) otherlv_7= '}'
            {
            otherlv_0=(Token)match(input,51,FollowSets000.FOLLOW_51_in_ruleKDirectPlacementData7192); 

                	newLeafNode(otherlv_0, grammarAccess.getKDirectPlacementDataAccess().getDirectPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKDirectPlacementData7204); 

                	newLeafNode(otherlv_1, grammarAccess.getKDirectPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,52,FollowSets000.FOLLOW_52_in_ruleKDirectPlacementData7216); 

                	newLeafNode(otherlv_2, grammarAccess.getKDirectPlacementDataAccess().getTopLeftKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3361:1: ( (lv_topLeft_3_0= ruleKPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3362:1: (lv_topLeft_3_0= ruleKPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3362:1: (lv_topLeft_3_0= ruleKPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3363:3: lv_topLeft_3_0= ruleKPosition
            {
             
            	        newCompositeNode(grammarAccess.getKDirectPlacementDataAccess().getTopLeftKPositionParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKDirectPlacementData7237);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3379:2: (otherlv_4= ',' )?
            int alt146=2;
            int LA146_0 = input.LA(1);

            if ( (LA146_0==13) ) {
                alt146=1;
            }
            switch (alt146) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3379:4: otherlv_4= ','
                    {
                    otherlv_4=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKDirectPlacementData7250); 

                        	newLeafNode(otherlv_4, grammarAccess.getKDirectPlacementDataAccess().getCommaKeyword_4());
                        

                    }
                    break;

            }

            otherlv_5=(Token)match(input,53,FollowSets000.FOLLOW_53_in_ruleKDirectPlacementData7264); 

                	newLeafNode(otherlv_5, grammarAccess.getKDirectPlacementDataAccess().getBottomRightKeyword_5());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3387:1: ( (lv_bottomRight_6_0= ruleKPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3388:1: (lv_bottomRight_6_0= ruleKPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3388:1: (lv_bottomRight_6_0= ruleKPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3389:3: lv_bottomRight_6_0= ruleKPosition
            {
             
            	        newCompositeNode(grammarAccess.getKDirectPlacementDataAccess().getBottomRightKPositionParserRuleCall_6_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKDirectPlacementData7285);
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

            otherlv_7=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKDirectPlacementData7297); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3417:1: entryRuleKPolylinePlacementData returns [EObject current=null] : iv_ruleKPolylinePlacementData= ruleKPolylinePlacementData EOF ;
    public final EObject entryRuleKPolylinePlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolylinePlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3418:2: (iv_ruleKPolylinePlacementData= ruleKPolylinePlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3419:2: iv_ruleKPolylinePlacementData= ruleKPolylinePlacementData EOF
            {
             newCompositeNode(grammarAccess.getKPolylinePlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPolylinePlacementData_in_entryRuleKPolylinePlacementData7333);
            iv_ruleKPolylinePlacementData=ruleKPolylinePlacementData();

            state._fsp--;

             current =iv_ruleKPolylinePlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPolylinePlacementData7343); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3426:1: ruleKPolylinePlacementData returns [EObject current=null] : (otherlv_0= 'PolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' (otherlv_3= ':' )? ( (lv_points_4_0= ruleKPosition ) ) ( (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) ) )* (otherlv_7= 'detailedPlacementData' ( (lv_detailPlacementData_8_0= ruleKPlacementData ) ) )? otherlv_9= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3429:28: ( (otherlv_0= 'PolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' (otherlv_3= ':' )? ( (lv_points_4_0= ruleKPosition ) ) ( (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) ) )* (otherlv_7= 'detailedPlacementData' ( (lv_detailPlacementData_8_0= ruleKPlacementData ) ) )? otherlv_9= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3430:1: (otherlv_0= 'PolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' (otherlv_3= ':' )? ( (lv_points_4_0= ruleKPosition ) ) ( (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) ) )* (otherlv_7= 'detailedPlacementData' ( (lv_detailPlacementData_8_0= ruleKPlacementData ) ) )? otherlv_9= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3430:1: (otherlv_0= 'PolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' (otherlv_3= ':' )? ( (lv_points_4_0= ruleKPosition ) ) ( (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) ) )* (otherlv_7= 'detailedPlacementData' ( (lv_detailPlacementData_8_0= ruleKPlacementData ) ) )? otherlv_9= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3430:3: otherlv_0= 'PolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' (otherlv_3= ':' )? ( (lv_points_4_0= ruleKPosition ) ) ( (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) ) )* (otherlv_7= 'detailedPlacementData' ( (lv_detailPlacementData_8_0= ruleKPlacementData ) ) )? otherlv_9= '}'
            {
            otherlv_0=(Token)match(input,54,FollowSets000.FOLLOW_54_in_ruleKPolylinePlacementData7380); 

                	newLeafNode(otherlv_0, grammarAccess.getKPolylinePlacementDataAccess().getPolylinePlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolylinePlacementData7392); 

                	newLeafNode(otherlv_1, grammarAccess.getKPolylinePlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,55,FollowSets000.FOLLOW_55_in_ruleKPolylinePlacementData7404); 

                	newLeafNode(otherlv_2, grammarAccess.getKPolylinePlacementDataAccess().getPointsKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3442:1: (otherlv_3= ':' )?
            int alt147=2;
            int LA147_0 = input.LA(1);

            if ( (LA147_0==19) ) {
                alt147=1;
            }
            switch (alt147) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3442:3: otherlv_3= ':'
                    {
                    otherlv_3=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKPolylinePlacementData7417); 

                        	newLeafNode(otherlv_3, grammarAccess.getKPolylinePlacementDataAccess().getColonKeyword_3());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3446:3: ( (lv_points_4_0= ruleKPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3447:1: (lv_points_4_0= ruleKPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3447:1: (lv_points_4_0= ruleKPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3448:3: lv_points_4_0= ruleKPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPolylinePlacementDataAccess().getPointsKPositionParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData7440);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3464:2: ( (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) ) )*
            loop149:
            do {
                int alt149=2;
                int LA149_0 = input.LA(1);

                if ( (LA149_0==13||(LA149_0>=58 && LA149_0<=59)) ) {
                    alt149=1;
                }


                switch (alt149) {
            	case 1 :
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3464:3: (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) )
            	    {
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3464:3: (otherlv_5= ',' )?
            	    int alt148=2;
            	    int LA148_0 = input.LA(1);

            	    if ( (LA148_0==13) ) {
            	        alt148=1;
            	    }
            	    switch (alt148) {
            	        case 1 :
            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3464:5: otherlv_5= ','
            	            {
            	            otherlv_5=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPolylinePlacementData7454); 

            	                	newLeafNode(otherlv_5, grammarAccess.getKPolylinePlacementDataAccess().getCommaKeyword_5_0());
            	                

            	            }
            	            break;

            	    }

            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3468:3: ( (lv_points_6_0= ruleKPosition ) )
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3469:1: (lv_points_6_0= ruleKPosition )
            	    {
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3469:1: (lv_points_6_0= ruleKPosition )
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3470:3: lv_points_6_0= ruleKPosition
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKPolylinePlacementDataAccess().getPointsKPositionParserRuleCall_5_1_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData7477);
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
            	    break loop149;
                }
            } while (true);

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3486:4: (otherlv_7= 'detailedPlacementData' ( (lv_detailPlacementData_8_0= ruleKPlacementData ) ) )?
            int alt150=2;
            int LA150_0 = input.LA(1);

            if ( (LA150_0==56) ) {
                alt150=1;
            }
            switch (alt150) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3486:6: otherlv_7= 'detailedPlacementData' ( (lv_detailPlacementData_8_0= ruleKPlacementData ) )
                    {
                    otherlv_7=(Token)match(input,56,FollowSets000.FOLLOW_56_in_ruleKPolylinePlacementData7492); 

                        	newLeafNode(otherlv_7, grammarAccess.getKPolylinePlacementDataAccess().getDetailedPlacementDataKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3490:1: ( (lv_detailPlacementData_8_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3491:1: (lv_detailPlacementData_8_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3491:1: (lv_detailPlacementData_8_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3492:3: lv_detailPlacementData_8_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolylinePlacementDataAccess().getDetailPlacementDataKPlacementDataParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKPolylinePlacementData7513);
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

            otherlv_9=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolylinePlacementData7527); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3520:1: entryRuleKPosition returns [EObject current=null] : iv_ruleKPosition= ruleKPosition EOF ;
    public final EObject entryRuleKPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3521:2: (iv_ruleKPosition= ruleKPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3522:2: iv_ruleKPosition= ruleKPosition EOF
            {
             newCompositeNode(grammarAccess.getKPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_entryRuleKPosition7563);
            iv_ruleKPosition=ruleKPosition();

            state._fsp--;

             current =iv_ruleKPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPosition7573); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3529:1: ruleKPosition returns [EObject current=null] : ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= '/' ( (lv_y_2_0= ruleKYPosition ) ) ) ;
    public final EObject ruleKPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_x_0_0 = null;

        EObject lv_y_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3532:28: ( ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= '/' ( (lv_y_2_0= ruleKYPosition ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3533:1: ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= '/' ( (lv_y_2_0= ruleKYPosition ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3533:1: ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= '/' ( (lv_y_2_0= ruleKYPosition ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3533:2: ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= '/' ( (lv_y_2_0= ruleKYPosition ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3533:2: ( (lv_x_0_0= ruleKXPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3534:1: (lv_x_0_0= ruleKXPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3534:1: (lv_x_0_0= ruleKXPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3535:3: lv_x_0_0= ruleKXPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPositionAccess().getXKXPositionParserRuleCall_0_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKXPosition_in_ruleKPosition7619);
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

            otherlv_1=(Token)match(input,57,FollowSets000.FOLLOW_57_in_ruleKPosition7631); 

                	newLeafNode(otherlv_1, grammarAccess.getKPositionAccess().getSolidusKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3555:1: ( (lv_y_2_0= ruleKYPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3556:1: (lv_y_2_0= ruleKYPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3556:1: (lv_y_2_0= ruleKYPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3557:3: lv_y_2_0= ruleKYPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPositionAccess().getYKYPositionParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKYPosition_in_ruleKPosition7652);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3581:1: entryRuleKLeftPosition returns [EObject current=null] : iv_ruleKLeftPosition= ruleKLeftPosition EOF ;
    public final EObject entryRuleKLeftPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLeftPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3582:2: (iv_ruleKLeftPosition= ruleKLeftPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3583:2: iv_ruleKLeftPosition= ruleKLeftPosition EOF
            {
             newCompositeNode(grammarAccess.getKLeftPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLeftPosition_in_entryRuleKLeftPosition7688);
            iv_ruleKLeftPosition=ruleKLeftPosition();

            state._fsp--;

             current =iv_ruleKLeftPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLeftPosition7698); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3590:1: ruleKLeftPosition returns [EObject current=null] : ( () otherlv_1= 'left' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) ;
    public final EObject ruleKLeftPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_absolute_2_0 = null;

        AntlrDatatypeRuleToken lv_relative_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3593:28: ( ( () otherlv_1= 'left' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3594:1: ( () otherlv_1= 'left' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3594:1: ( () otherlv_1= 'left' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3594:2: () otherlv_1= 'left' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3594:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3595:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKLeftPositionAccess().getKLeftPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,58,FollowSets000.FOLLOW_58_in_ruleKLeftPosition7744); 

                	newLeafNode(otherlv_1, grammarAccess.getKLeftPositionAccess().getLeftKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3604:1: ( (lv_absolute_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3605:1: (lv_absolute_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3605:1: (lv_absolute_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3606:3: lv_absolute_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKLeftPositionAccess().getAbsoluteEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKLeftPosition7765);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3622:2: ( (lv_relative_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3623:1: (lv_relative_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3623:1: (lv_relative_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3624:3: lv_relative_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKLeftPositionAccess().getRelativeEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKLeftPosition7786);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3648:1: entryRuleKRightPosition returns [EObject current=null] : iv_ruleKRightPosition= ruleKRightPosition EOF ;
    public final EObject entryRuleKRightPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRightPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3649:2: (iv_ruleKRightPosition= ruleKRightPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3650:2: iv_ruleKRightPosition= ruleKRightPosition EOF
            {
             newCompositeNode(grammarAccess.getKRightPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRightPosition_in_entryRuleKRightPosition7822);
            iv_ruleKRightPosition=ruleKRightPosition();

            state._fsp--;

             current =iv_ruleKRightPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRightPosition7832); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3657:1: ruleKRightPosition returns [EObject current=null] : ( () otherlv_1= 'right' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) ;
    public final EObject ruleKRightPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_absolute_2_0 = null;

        AntlrDatatypeRuleToken lv_relative_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3660:28: ( ( () otherlv_1= 'right' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3661:1: ( () otherlv_1= 'right' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3661:1: ( () otherlv_1= 'right' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3661:2: () otherlv_1= 'right' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3661:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3662:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRightPositionAccess().getKRightPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,59,FollowSets000.FOLLOW_59_in_ruleKRightPosition7878); 

                	newLeafNode(otherlv_1, grammarAccess.getKRightPositionAccess().getRightKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3671:1: ( (lv_absolute_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3672:1: (lv_absolute_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3672:1: (lv_absolute_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3673:3: lv_absolute_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRightPositionAccess().getAbsoluteEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRightPosition7899);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3689:2: ( (lv_relative_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3690:1: (lv_relative_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3690:1: (lv_relative_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3691:3: lv_relative_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRightPositionAccess().getRelativeEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRightPosition7920);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3715:1: entryRuleKTopPosition returns [EObject current=null] : iv_ruleKTopPosition= ruleKTopPosition EOF ;
    public final EObject entryRuleKTopPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKTopPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3716:2: (iv_ruleKTopPosition= ruleKTopPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3717:2: iv_ruleKTopPosition= ruleKTopPosition EOF
            {
             newCompositeNode(grammarAccess.getKTopPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKTopPosition_in_entryRuleKTopPosition7956);
            iv_ruleKTopPosition=ruleKTopPosition();

            state._fsp--;

             current =iv_ruleKTopPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKTopPosition7966); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3724:1: ruleKTopPosition returns [EObject current=null] : ( () otherlv_1= 'top' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) ;
    public final EObject ruleKTopPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_absolute_2_0 = null;

        AntlrDatatypeRuleToken lv_relative_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3727:28: ( ( () otherlv_1= 'top' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3728:1: ( () otherlv_1= 'top' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3728:1: ( () otherlv_1= 'top' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3728:2: () otherlv_1= 'top' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3728:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3729:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKTopPositionAccess().getKTopPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,60,FollowSets000.FOLLOW_60_in_ruleKTopPosition8012); 

                	newLeafNode(otherlv_1, grammarAccess.getKTopPositionAccess().getTopKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3738:1: ( (lv_absolute_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3739:1: (lv_absolute_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3739:1: (lv_absolute_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3740:3: lv_absolute_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKTopPositionAccess().getAbsoluteEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKTopPosition8033);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3756:2: ( (lv_relative_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3757:1: (lv_relative_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3757:1: (lv_relative_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3758:3: lv_relative_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKTopPositionAccess().getRelativeEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKTopPosition8054);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3782:1: entryRuleKBottomPosition returns [EObject current=null] : iv_ruleKBottomPosition= ruleKBottomPosition EOF ;
    public final EObject entryRuleKBottomPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBottomPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3783:2: (iv_ruleKBottomPosition= ruleKBottomPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3784:2: iv_ruleKBottomPosition= ruleKBottomPosition EOF
            {
             newCompositeNode(grammarAccess.getKBottomPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKBottomPosition_in_entryRuleKBottomPosition8090);
            iv_ruleKBottomPosition=ruleKBottomPosition();

            state._fsp--;

             current =iv_ruleKBottomPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKBottomPosition8100); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3791:1: ruleKBottomPosition returns [EObject current=null] : ( () otherlv_1= 'bottom' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) ;
    public final EObject ruleKBottomPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_absolute_2_0 = null;

        AntlrDatatypeRuleToken lv_relative_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3794:28: ( ( () otherlv_1= 'bottom' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3795:1: ( () otherlv_1= 'bottom' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3795:1: ( () otherlv_1= 'bottom' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3795:2: () otherlv_1= 'bottom' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3795:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3796:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKBottomPositionAccess().getKBottomPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,61,FollowSets000.FOLLOW_61_in_ruleKBottomPosition8146); 

                	newLeafNode(otherlv_1, grammarAccess.getKBottomPositionAccess().getBottomKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3805:1: ( (lv_absolute_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3806:1: (lv_absolute_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3806:1: (lv_absolute_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3807:3: lv_absolute_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKBottomPositionAccess().getAbsoluteEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKBottomPosition8167);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3823:2: ( (lv_relative_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3824:1: (lv_relative_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3824:1: (lv_relative_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3825:3: lv_relative_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKBottomPositionAccess().getRelativeEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKBottomPosition8188);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3849:1: entryRuleKForegroundColor returns [EObject current=null] : iv_ruleKForegroundColor= ruleKForegroundColor EOF ;
    public final EObject entryRuleKForegroundColor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKForegroundColor = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3850:2: (iv_ruleKForegroundColor= ruleKForegroundColor EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3851:2: iv_ruleKForegroundColor= ruleKForegroundColor EOF
            {
             newCompositeNode(grammarAccess.getKForegroundColorRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKForegroundColor_in_entryRuleKForegroundColor8224);
            iv_ruleKForegroundColor=ruleKForegroundColor();

            state._fsp--;

             current =iv_ruleKForegroundColor; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKForegroundColor8234); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3858:1: ruleKForegroundColor returns [EObject current=null] : ( () otherlv_1= 'ForegroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? ) ;
    public final EObject ruleKForegroundColor() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_5_0=null;
        AntlrDatatypeRuleToken lv_red_2_0 = null;

        AntlrDatatypeRuleToken lv_green_3_0 = null;

        AntlrDatatypeRuleToken lv_blue_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3861:28: ( ( () otherlv_1= 'ForegroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3862:1: ( () otherlv_1= 'ForegroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3862:1: ( () otherlv_1= 'ForegroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3862:2: () otherlv_1= 'ForegroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3862:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3863:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKForegroundColorAccess().getKForegroundColorAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,62,FollowSets000.FOLLOW_62_in_ruleKForegroundColor8280); 

                	newLeafNode(otherlv_1, grammarAccess.getKForegroundColorAccess().getForegroundColorKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3872:1: ( (lv_red_2_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3873:1: (lv_red_2_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3873:1: (lv_red_2_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3874:3: lv_red_2_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKForegroundColorAccess().getRedEIntParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKForegroundColor8301);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3890:2: ( (lv_green_3_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3891:1: (lv_green_3_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3891:1: (lv_green_3_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3892:3: lv_green_3_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKForegroundColorAccess().getGreenEIntParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKForegroundColor8322);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3908:2: ( (lv_blue_4_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3909:1: (lv_blue_4_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3909:1: (lv_blue_4_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3910:3: lv_blue_4_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKForegroundColorAccess().getBlueEIntParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKForegroundColor8343);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3926:2: ( (lv_propagateToChildren_5_0= '!' ) )?
            int alt151=2;
            int LA151_0 = input.LA(1);

            if ( (LA151_0==63) ) {
                alt151=1;
            }
            switch (alt151) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3927:1: (lv_propagateToChildren_5_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3927:1: (lv_propagateToChildren_5_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3928:3: lv_propagateToChildren_5_0= '!'
                    {
                    lv_propagateToChildren_5_0=(Token)match(input,63,FollowSets000.FOLLOW_63_in_ruleKForegroundColor8361); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3949:1: entryRuleKBackgroundColor returns [EObject current=null] : iv_ruleKBackgroundColor= ruleKBackgroundColor EOF ;
    public final EObject entryRuleKBackgroundColor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBackgroundColor = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3950:2: (iv_ruleKBackgroundColor= ruleKBackgroundColor EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3951:2: iv_ruleKBackgroundColor= ruleKBackgroundColor EOF
            {
             newCompositeNode(grammarAccess.getKBackgroundColorRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKBackgroundColor_in_entryRuleKBackgroundColor8411);
            iv_ruleKBackgroundColor=ruleKBackgroundColor();

            state._fsp--;

             current =iv_ruleKBackgroundColor; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKBackgroundColor8421); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3958:1: ruleKBackgroundColor returns [EObject current=null] : ( () otherlv_1= 'BackgroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? ) ;
    public final EObject ruleKBackgroundColor() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_5_0=null;
        AntlrDatatypeRuleToken lv_red_2_0 = null;

        AntlrDatatypeRuleToken lv_green_3_0 = null;

        AntlrDatatypeRuleToken lv_blue_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3961:28: ( ( () otherlv_1= 'BackgroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3962:1: ( () otherlv_1= 'BackgroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3962:1: ( () otherlv_1= 'BackgroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3962:2: () otherlv_1= 'BackgroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3962:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3963:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKBackgroundColorAccess().getKBackgroundColorAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,64,FollowSets000.FOLLOW_64_in_ruleKBackgroundColor8467); 

                	newLeafNode(otherlv_1, grammarAccess.getKBackgroundColorAccess().getBackgroundColorKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3972:1: ( (lv_red_2_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3973:1: (lv_red_2_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3973:1: (lv_red_2_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3974:3: lv_red_2_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKBackgroundColorAccess().getRedEIntParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKBackgroundColor8488);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3990:2: ( (lv_green_3_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3991:1: (lv_green_3_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3991:1: (lv_green_3_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3992:3: lv_green_3_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKBackgroundColorAccess().getGreenEIntParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKBackgroundColor8509);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4008:2: ( (lv_blue_4_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4009:1: (lv_blue_4_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4009:1: (lv_blue_4_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4010:3: lv_blue_4_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKBackgroundColorAccess().getBlueEIntParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKBackgroundColor8530);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4026:2: ( (lv_propagateToChildren_5_0= '!' ) )?
            int alt152=2;
            int LA152_0 = input.LA(1);

            if ( (LA152_0==63) ) {
                alt152=1;
            }
            switch (alt152) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4027:1: (lv_propagateToChildren_5_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4027:1: (lv_propagateToChildren_5_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4028:3: lv_propagateToChildren_5_0= '!'
                    {
                    lv_propagateToChildren_5_0=(Token)match(input,63,FollowSets000.FOLLOW_63_in_ruleKBackgroundColor8548); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4049:1: entryRuleKLineWidth returns [EObject current=null] : iv_ruleKLineWidth= ruleKLineWidth EOF ;
    public final EObject entryRuleKLineWidth() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLineWidth = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4050:2: (iv_ruleKLineWidth= ruleKLineWidth EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4051:2: iv_ruleKLineWidth= ruleKLineWidth EOF
            {
             newCompositeNode(grammarAccess.getKLineWidthRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLineWidth_in_entryRuleKLineWidth8598);
            iv_ruleKLineWidth=ruleKLineWidth();

            state._fsp--;

             current =iv_ruleKLineWidth; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLineWidth8608); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4058:1: ruleKLineWidth returns [EObject current=null] : (otherlv_0= 'LineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) ;
    public final EObject ruleKLineWidth() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_propagateToChildren_2_0=null;
        AntlrDatatypeRuleToken lv_lineWidth_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4061:28: ( (otherlv_0= 'LineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4062:1: (otherlv_0= 'LineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4062:1: (otherlv_0= 'LineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4062:3: otherlv_0= 'LineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )?
            {
            otherlv_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKLineWidth8645); 

                	newLeafNode(otherlv_0, grammarAccess.getKLineWidthAccess().getLineWidthKeyword_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4066:1: ( (lv_lineWidth_1_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4067:1: (lv_lineWidth_1_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4067:1: (lv_lineWidth_1_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4068:3: lv_lineWidth_1_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKLineWidthAccess().getLineWidthEIntParserRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKLineWidth8666);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4084:2: ( (lv_propagateToChildren_2_0= '!' ) )?
            int alt153=2;
            int LA153_0 = input.LA(1);

            if ( (LA153_0==63) ) {
                alt153=1;
            }
            switch (alt153) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4085:1: (lv_propagateToChildren_2_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4085:1: (lv_propagateToChildren_2_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4086:3: lv_propagateToChildren_2_0= '!'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,63,FollowSets000.FOLLOW_63_in_ruleKLineWidth8684); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4107:1: entryRuleKVisibility returns [EObject current=null] : iv_ruleKVisibility= ruleKVisibility EOF ;
    public final EObject entryRuleKVisibility() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKVisibility = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4108:2: (iv_ruleKVisibility= ruleKVisibility EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4109:2: iv_ruleKVisibility= ruleKVisibility EOF
            {
             newCompositeNode(grammarAccess.getKVisibilityRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKVisibility_in_entryRuleKVisibility8734);
            iv_ruleKVisibility=ruleKVisibility();

            state._fsp--;

             current =iv_ruleKVisibility; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKVisibility8744); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4116:1: ruleKVisibility returns [EObject current=null] : (this_KForegroundVisibility_0= ruleKForegroundVisibility | this_KBackgroundVisibility_1= ruleKBackgroundVisibility ) ;
    public final EObject ruleKVisibility() throws RecognitionException {
        EObject current = null;

        EObject this_KForegroundVisibility_0 = null;

        EObject this_KBackgroundVisibility_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4119:28: ( (this_KForegroundVisibility_0= ruleKForegroundVisibility | this_KBackgroundVisibility_1= ruleKBackgroundVisibility ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4120:1: (this_KForegroundVisibility_0= ruleKForegroundVisibility | this_KBackgroundVisibility_1= ruleKBackgroundVisibility )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4120:1: (this_KForegroundVisibility_0= ruleKForegroundVisibility | this_KBackgroundVisibility_1= ruleKBackgroundVisibility )
            int alt154=2;
            int LA154_0 = input.LA(1);

            if ( (LA154_0==66) ) {
                alt154=1;
            }
            else if ( (LA154_0==67) ) {
                alt154=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 154, 0, input);

                throw nvae;
            }
            switch (alt154) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4121:5: this_KForegroundVisibility_0= ruleKForegroundVisibility
                    {
                     
                            newCompositeNode(grammarAccess.getKVisibilityAccess().getKForegroundVisibilityParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKForegroundVisibility_in_ruleKVisibility8791);
                    this_KForegroundVisibility_0=ruleKForegroundVisibility();

                    state._fsp--;

                     
                            current = this_KForegroundVisibility_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4131:5: this_KBackgroundVisibility_1= ruleKBackgroundVisibility
                    {
                     
                            newCompositeNode(grammarAccess.getKVisibilityAccess().getKBackgroundVisibilityParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKBackgroundVisibility_in_ruleKVisibility8818);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4147:1: entryRuleKForegroundVisibility returns [EObject current=null] : iv_ruleKForegroundVisibility= ruleKForegroundVisibility EOF ;
    public final EObject entryRuleKForegroundVisibility() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKForegroundVisibility = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4148:2: (iv_ruleKForegroundVisibility= ruleKForegroundVisibility EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4149:2: iv_ruleKForegroundVisibility= ruleKForegroundVisibility EOF
            {
             newCompositeNode(grammarAccess.getKForegroundVisibilityRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKForegroundVisibility_in_entryRuleKForegroundVisibility8853);
            iv_ruleKForegroundVisibility=ruleKForegroundVisibility();

            state._fsp--;

             current =iv_ruleKForegroundVisibility; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKForegroundVisibility8863); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4156:1: ruleKForegroundVisibility returns [EObject current=null] : ( () otherlv_1= 'ForegroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) ;
    public final EObject ruleKForegroundVisibility() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        AntlrDatatypeRuleToken lv_visible_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4159:28: ( ( () otherlv_1= 'ForegroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4160:1: ( () otherlv_1= 'ForegroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4160:1: ( () otherlv_1= 'ForegroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4160:2: () otherlv_1= 'ForegroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4160:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4161:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKForegroundVisibilityAccess().getKForegroundVisibilityAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,66,FollowSets000.FOLLOW_66_in_ruleKForegroundVisibility8909); 

                	newLeafNode(otherlv_1, grammarAccess.getKForegroundVisibilityAccess().getForegroundVisibilityKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4170:1: ( (lv_visible_2_0= ruleEBoolean ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4171:1: (lv_visible_2_0= ruleEBoolean )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4171:1: (lv_visible_2_0= ruleEBoolean )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4172:3: lv_visible_2_0= ruleEBoolean
            {
             
            	        newCompositeNode(grammarAccess.getKForegroundVisibilityAccess().getVisibleEBooleanParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleKForegroundVisibility8930);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4188:2: ( (lv_propagateToChildren_3_0= '!' ) )?
            int alt155=2;
            int LA155_0 = input.LA(1);

            if ( (LA155_0==63) ) {
                alt155=1;
            }
            switch (alt155) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4189:1: (lv_propagateToChildren_3_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4189:1: (lv_propagateToChildren_3_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4190:3: lv_propagateToChildren_3_0= '!'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,63,FollowSets000.FOLLOW_63_in_ruleKForegroundVisibility8948); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4211:1: entryRuleKBackgroundVisibility returns [EObject current=null] : iv_ruleKBackgroundVisibility= ruleKBackgroundVisibility EOF ;
    public final EObject entryRuleKBackgroundVisibility() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBackgroundVisibility = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4212:2: (iv_ruleKBackgroundVisibility= ruleKBackgroundVisibility EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4213:2: iv_ruleKBackgroundVisibility= ruleKBackgroundVisibility EOF
            {
             newCompositeNode(grammarAccess.getKBackgroundVisibilityRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKBackgroundVisibility_in_entryRuleKBackgroundVisibility8998);
            iv_ruleKBackgroundVisibility=ruleKBackgroundVisibility();

            state._fsp--;

             current =iv_ruleKBackgroundVisibility; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKBackgroundVisibility9008); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4220:1: ruleKBackgroundVisibility returns [EObject current=null] : ( () otherlv_1= 'BackgroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) ;
    public final EObject ruleKBackgroundVisibility() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        AntlrDatatypeRuleToken lv_visible_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4223:28: ( ( () otherlv_1= 'BackgroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4224:1: ( () otherlv_1= 'BackgroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4224:1: ( () otherlv_1= 'BackgroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4224:2: () otherlv_1= 'BackgroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4224:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4225:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKBackgroundVisibilityAccess().getKBackgroundVisibilityAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,67,FollowSets000.FOLLOW_67_in_ruleKBackgroundVisibility9054); 

                	newLeafNode(otherlv_1, grammarAccess.getKBackgroundVisibilityAccess().getBackgroundVisibilityKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4234:1: ( (lv_visible_2_0= ruleEBoolean ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4235:1: (lv_visible_2_0= ruleEBoolean )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4235:1: (lv_visible_2_0= ruleEBoolean )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4236:3: lv_visible_2_0= ruleEBoolean
            {
             
            	        newCompositeNode(grammarAccess.getKBackgroundVisibilityAccess().getVisibleEBooleanParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleKBackgroundVisibility9075);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4252:2: ( (lv_propagateToChildren_3_0= '!' ) )?
            int alt156=2;
            int LA156_0 = input.LA(1);

            if ( (LA156_0==63) ) {
                alt156=1;
            }
            switch (alt156) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4253:1: (lv_propagateToChildren_3_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4253:1: (lv_propagateToChildren_3_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4254:3: lv_propagateToChildren_3_0= '!'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,63,FollowSets000.FOLLOW_63_in_ruleKBackgroundVisibility9093); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4275:1: entryRuleKLineStyle returns [EObject current=null] : iv_ruleKLineStyle= ruleKLineStyle EOF ;
    public final EObject entryRuleKLineStyle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLineStyle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4276:2: (iv_ruleKLineStyle= ruleKLineStyle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4277:2: iv_ruleKLineStyle= ruleKLineStyle EOF
            {
             newCompositeNode(grammarAccess.getKLineStyleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLineStyle_in_entryRuleKLineStyle9143);
            iv_ruleKLineStyle=ruleKLineStyle();

            state._fsp--;

             current =iv_ruleKLineStyle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLineStyle9153); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4284:1: ruleKLineStyle returns [EObject current=null] : ( () otherlv_1= 'LineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) ;
    public final EObject ruleKLineStyle() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        Enumerator lv_lineStyle_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4287:28: ( ( () otherlv_1= 'LineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4288:1: ( () otherlv_1= 'LineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4288:1: ( () otherlv_1= 'LineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4288:2: () otherlv_1= 'LineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) ) ( (lv_propagateToChildren_3_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4288:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4289:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKLineStyleAccess().getKLineStyleAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,68,FollowSets000.FOLLOW_68_in_ruleKLineStyle9199); 

                	newLeafNode(otherlv_1, grammarAccess.getKLineStyleAccess().getLineStyleKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4298:1: ( (lv_lineStyle_2_0= ruleLineStyle ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4299:1: (lv_lineStyle_2_0= ruleLineStyle )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4299:1: (lv_lineStyle_2_0= ruleLineStyle )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4300:3: lv_lineStyle_2_0= ruleLineStyle
            {
             
            	        newCompositeNode(grammarAccess.getKLineStyleAccess().getLineStyleLineStyleEnumRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleLineStyle_in_ruleKLineStyle9220);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4316:2: ( (lv_propagateToChildren_3_0= '!' ) )?
            int alt157=2;
            int LA157_0 = input.LA(1);

            if ( (LA157_0==63) ) {
                alt157=1;
            }
            switch (alt157) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4317:1: (lv_propagateToChildren_3_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4317:1: (lv_propagateToChildren_3_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4318:3: lv_propagateToChildren_3_0= '!'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,63,FollowSets000.FOLLOW_63_in_ruleKLineStyle9238); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4339:1: entryRuleKFontBold returns [EObject current=null] : iv_ruleKFontBold= ruleKFontBold EOF ;
    public final EObject entryRuleKFontBold() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontBold = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4340:2: (iv_ruleKFontBold= ruleKFontBold EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4341:2: iv_ruleKFontBold= ruleKFontBold EOF
            {
             newCompositeNode(grammarAccess.getKFontBoldRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKFontBold_in_entryRuleKFontBold9288);
            iv_ruleKFontBold=ruleKFontBold();

            state._fsp--;

             current =iv_ruleKFontBold; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKFontBold9298); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4348:1: ruleKFontBold returns [EObject current=null] : ( () otherlv_1= 'bold' ( (lv_propagateToChildren_2_0= '!' ) )? ) ;
    public final EObject ruleKFontBold() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_2_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4351:28: ( ( () otherlv_1= 'bold' ( (lv_propagateToChildren_2_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4352:1: ( () otherlv_1= 'bold' ( (lv_propagateToChildren_2_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4352:1: ( () otherlv_1= 'bold' ( (lv_propagateToChildren_2_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4352:2: () otherlv_1= 'bold' ( (lv_propagateToChildren_2_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4352:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4353:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKFontBoldAccess().getKFontBoldAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,69,FollowSets000.FOLLOW_69_in_ruleKFontBold9344); 

                	newLeafNode(otherlv_1, grammarAccess.getKFontBoldAccess().getBoldKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4362:1: ( (lv_propagateToChildren_2_0= '!' ) )?
            int alt158=2;
            int LA158_0 = input.LA(1);

            if ( (LA158_0==63) ) {
                alt158=1;
            }
            switch (alt158) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4363:1: (lv_propagateToChildren_2_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4363:1: (lv_propagateToChildren_2_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4364:3: lv_propagateToChildren_2_0= '!'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,63,FollowSets000.FOLLOW_63_in_ruleKFontBold9362); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4385:1: entryRuleKFontItalic returns [EObject current=null] : iv_ruleKFontItalic= ruleKFontItalic EOF ;
    public final EObject entryRuleKFontItalic() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontItalic = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4386:2: (iv_ruleKFontItalic= ruleKFontItalic EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4387:2: iv_ruleKFontItalic= ruleKFontItalic EOF
            {
             newCompositeNode(grammarAccess.getKFontItalicRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKFontItalic_in_entryRuleKFontItalic9412);
            iv_ruleKFontItalic=ruleKFontItalic();

            state._fsp--;

             current =iv_ruleKFontItalic; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKFontItalic9422); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4394:1: ruleKFontItalic returns [EObject current=null] : ( () otherlv_1= 'italic' ( (lv_propagateToChildren_2_0= '!' ) )? ) ;
    public final EObject ruleKFontItalic() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_2_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4397:28: ( ( () otherlv_1= 'italic' ( (lv_propagateToChildren_2_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4398:1: ( () otherlv_1= 'italic' ( (lv_propagateToChildren_2_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4398:1: ( () otherlv_1= 'italic' ( (lv_propagateToChildren_2_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4398:2: () otherlv_1= 'italic' ( (lv_propagateToChildren_2_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4398:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4399:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKFontItalicAccess().getKFontItalicAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,70,FollowSets000.FOLLOW_70_in_ruleKFontItalic9468); 

                	newLeafNode(otherlv_1, grammarAccess.getKFontItalicAccess().getItalicKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4408:1: ( (lv_propagateToChildren_2_0= '!' ) )?
            int alt159=2;
            int LA159_0 = input.LA(1);

            if ( (LA159_0==63) ) {
                alt159=1;
            }
            switch (alt159) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4409:1: (lv_propagateToChildren_2_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4409:1: (lv_propagateToChildren_2_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4410:3: lv_propagateToChildren_2_0= '!'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,63,FollowSets000.FOLLOW_63_in_ruleKFontItalic9486); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4431:1: entryRuleKFontName returns [EObject current=null] : iv_ruleKFontName= ruleKFontName EOF ;
    public final EObject entryRuleKFontName() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontName = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4432:2: (iv_ruleKFontName= ruleKFontName EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4433:2: iv_ruleKFontName= ruleKFontName EOF
            {
             newCompositeNode(grammarAccess.getKFontNameRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKFontName_in_entryRuleKFontName9536);
            iv_ruleKFontName=ruleKFontName();

            state._fsp--;

             current =iv_ruleKFontName; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKFontName9546); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4440:1: ruleKFontName returns [EObject current=null] : (otherlv_0= 'font' ( (lv_name_1_0= ruleEString ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) ;
    public final EObject ruleKFontName() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_propagateToChildren_2_0=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4443:28: ( (otherlv_0= 'font' ( (lv_name_1_0= ruleEString ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4444:1: (otherlv_0= 'font' ( (lv_name_1_0= ruleEString ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4444:1: (otherlv_0= 'font' ( (lv_name_1_0= ruleEString ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4444:3: otherlv_0= 'font' ( (lv_name_1_0= ruleEString ) ) ( (lv_propagateToChildren_2_0= '!' ) )?
            {
            otherlv_0=(Token)match(input,71,FollowSets000.FOLLOW_71_in_ruleKFontName9583); 

                	newLeafNode(otherlv_0, grammarAccess.getKFontNameAccess().getFontKeyword_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4448:1: ( (lv_name_1_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4449:1: (lv_name_1_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4449:1: (lv_name_1_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4450:3: lv_name_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKFontNameAccess().getNameEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKFontName9604);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4466:2: ( (lv_propagateToChildren_2_0= '!' ) )?
            int alt160=2;
            int LA160_0 = input.LA(1);

            if ( (LA160_0==63) ) {
                alt160=1;
            }
            switch (alt160) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4467:1: (lv_propagateToChildren_2_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4467:1: (lv_propagateToChildren_2_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4468:3: lv_propagateToChildren_2_0= '!'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,63,FollowSets000.FOLLOW_63_in_ruleKFontName9622); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4489:1: entryRuleKFontSize returns [EObject current=null] : iv_ruleKFontSize= ruleKFontSize EOF ;
    public final EObject entryRuleKFontSize() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontSize = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4490:2: (iv_ruleKFontSize= ruleKFontSize EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4491:2: iv_ruleKFontSize= ruleKFontSize EOF
            {
             newCompositeNode(grammarAccess.getKFontSizeRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKFontSize_in_entryRuleKFontSize9672);
            iv_ruleKFontSize=ruleKFontSize();

            state._fsp--;

             current =iv_ruleKFontSize; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKFontSize9682); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4498:1: ruleKFontSize returns [EObject current=null] : (otherlv_0= 'fontSize' ( (lv_size_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) ;
    public final EObject ruleKFontSize() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_propagateToChildren_2_0=null;
        AntlrDatatypeRuleToken lv_size_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4501:28: ( (otherlv_0= 'fontSize' ( (lv_size_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4502:1: (otherlv_0= 'fontSize' ( (lv_size_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4502:1: (otherlv_0= 'fontSize' ( (lv_size_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4502:3: otherlv_0= 'fontSize' ( (lv_size_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )?
            {
            otherlv_0=(Token)match(input,72,FollowSets000.FOLLOW_72_in_ruleKFontSize9719); 

                	newLeafNode(otherlv_0, grammarAccess.getKFontSizeAccess().getFontSizeKeyword_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4506:1: ( (lv_size_1_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4507:1: (lv_size_1_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4507:1: (lv_size_1_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4508:3: lv_size_1_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKFontSizeAccess().getSizeEIntParserRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKFontSize9740);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4524:2: ( (lv_propagateToChildren_2_0= '!' ) )?
            int alt161=2;
            int LA161_0 = input.LA(1);

            if ( (LA161_0==63) ) {
                alt161=1;
            }
            switch (alt161) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4525:1: (lv_propagateToChildren_2_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4525:1: (lv_propagateToChildren_2_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4526:3: lv_propagateToChildren_2_0= '!'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,63,FollowSets000.FOLLOW_63_in_ruleKFontSize9758); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4547:1: entryRuleKVerticalAlignment returns [EObject current=null] : iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF ;
    public final EObject entryRuleKVerticalAlignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKVerticalAlignment = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4548:2: (iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4549:2: iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF
            {
             newCompositeNode(grammarAccess.getKVerticalAlignmentRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKVerticalAlignment_in_entryRuleKVerticalAlignment9808);
            iv_ruleKVerticalAlignment=ruleKVerticalAlignment();

            state._fsp--;

             current =iv_ruleKVerticalAlignment; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKVerticalAlignment9818); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4556:1: ruleKVerticalAlignment returns [EObject current=null] : ( () otherlv_1= 'VerticalAlignment' ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) ;
    public final EObject ruleKVerticalAlignment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        Enumerator lv_verticalAlignment_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4559:28: ( ( () otherlv_1= 'VerticalAlignment' ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4560:1: ( () otherlv_1= 'VerticalAlignment' ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4560:1: ( () otherlv_1= 'VerticalAlignment' ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4560:2: () otherlv_1= 'VerticalAlignment' ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4560:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4561:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKVerticalAlignmentAccess().getKVerticalAlignmentAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,73,FollowSets000.FOLLOW_73_in_ruleKVerticalAlignment9864); 

                	newLeafNode(otherlv_1, grammarAccess.getKVerticalAlignmentAccess().getVerticalAlignmentKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4570:1: ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4571:1: (lv_verticalAlignment_2_0= ruleVerticalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4571:1: (lv_verticalAlignment_2_0= ruleVerticalAlignment )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4572:3: lv_verticalAlignment_2_0= ruleVerticalAlignment
            {
             
            	        newCompositeNode(grammarAccess.getKVerticalAlignmentAccess().getVerticalAlignmentVerticalAlignmentEnumRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleVerticalAlignment_in_ruleKVerticalAlignment9885);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4588:2: ( (lv_propagateToChildren_3_0= '!' ) )?
            int alt162=2;
            int LA162_0 = input.LA(1);

            if ( (LA162_0==63) ) {
                alt162=1;
            }
            switch (alt162) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4589:1: (lv_propagateToChildren_3_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4589:1: (lv_propagateToChildren_3_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4590:3: lv_propagateToChildren_3_0= '!'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,63,FollowSets000.FOLLOW_63_in_ruleKVerticalAlignment9903); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4611:1: entryRuleKHorizontalAlignment returns [EObject current=null] : iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF ;
    public final EObject entryRuleKHorizontalAlignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKHorizontalAlignment = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4612:2: (iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4613:2: iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF
            {
             newCompositeNode(grammarAccess.getKHorizontalAlignmentRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKHorizontalAlignment_in_entryRuleKHorizontalAlignment9953);
            iv_ruleKHorizontalAlignment=ruleKHorizontalAlignment();

            state._fsp--;

             current =iv_ruleKHorizontalAlignment; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKHorizontalAlignment9963); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4620:1: ruleKHorizontalAlignment returns [EObject current=null] : ( () otherlv_1= 'HorizontalAlignment' ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) ) ) ;
    public final EObject ruleKHorizontalAlignment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        Enumerator lv_horizontalAlignment_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4623:28: ( ( () otherlv_1= 'HorizontalAlignment' ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4624:1: ( () otherlv_1= 'HorizontalAlignment' ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4624:1: ( () otherlv_1= 'HorizontalAlignment' ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4624:2: () otherlv_1= 'HorizontalAlignment' ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4624:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4625:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKHorizontalAlignmentAccess().getKHorizontalAlignmentAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,74,FollowSets000.FOLLOW_74_in_ruleKHorizontalAlignment10009); 

                	newLeafNode(otherlv_1, grammarAccess.getKHorizontalAlignmentAccess().getHorizontalAlignmentKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4634:1: ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4635:1: (lv_horizontalAlignment_2_0= ruleHorizontalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4635:1: (lv_horizontalAlignment_2_0= ruleHorizontalAlignment )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4636:3: lv_horizontalAlignment_2_0= ruleHorizontalAlignment
            {
             
            	        newCompositeNode(grammarAccess.getKHorizontalAlignmentAccess().getHorizontalAlignmentHorizontalAlignmentEnumRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleHorizontalAlignment_in_ruleKHorizontalAlignment10030);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4652:2: ( (lv_propagateToChildren_3_0= '!' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4653:1: (lv_propagateToChildren_3_0= '!' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4653:1: (lv_propagateToChildren_3_0= '!' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4654:3: lv_propagateToChildren_3_0= '!'
            {
            lv_propagateToChildren_3_0=(Token)match(input,63,FollowSets000.FOLLOW_63_in_ruleKHorizontalAlignment10048); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4675:1: entryRuleKGridPlacement returns [EObject current=null] : iv_ruleKGridPlacement= ruleKGridPlacement EOF ;
    public final EObject entryRuleKGridPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKGridPlacement = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4676:2: (iv_ruleKGridPlacement= ruleKGridPlacement EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4677:2: iv_ruleKGridPlacement= ruleKGridPlacement EOF
            {
             newCompositeNode(grammarAccess.getKGridPlacementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKGridPlacement_in_entryRuleKGridPlacement10097);
            iv_ruleKGridPlacement=ruleKGridPlacement();

            state._fsp--;

             current =iv_ruleKGridPlacement; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKGridPlacement10107); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4684:1: ruleKGridPlacement returns [EObject current=null] : ( () otherlv_1= 'GridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) ) ;
    public final EObject ruleKGridPlacement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_numColumns_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4687:28: ( ( () otherlv_1= 'GridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4688:1: ( () otherlv_1= 'GridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4688:1: ( () otherlv_1= 'GridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4688:2: () otherlv_1= 'GridPlacement' ( (lv_numColumns_2_0= ruleEInt ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4688:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4689:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKGridPlacementAccess().getKGridPlacementAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,75,FollowSets000.FOLLOW_75_in_ruleKGridPlacement10153); 

                	newLeafNode(otherlv_1, grammarAccess.getKGridPlacementAccess().getGridPlacementKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4698:1: ( (lv_numColumns_2_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4699:1: (lv_numColumns_2_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4699:1: (lv_numColumns_2_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4700:3: lv_numColumns_2_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementAccess().getNumColumnsEIntParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKGridPlacement10174);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4724:1: entryRuleKStackPlacement returns [EObject current=null] : iv_ruleKStackPlacement= ruleKStackPlacement EOF ;
    public final EObject entryRuleKStackPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStackPlacement = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4725:2: (iv_ruleKStackPlacement= ruleKStackPlacement EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4726:2: iv_ruleKStackPlacement= ruleKStackPlacement EOF
            {
             newCompositeNode(grammarAccess.getKStackPlacementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKStackPlacement_in_entryRuleKStackPlacement10210);
            iv_ruleKStackPlacement=ruleKStackPlacement();

            state._fsp--;

             current =iv_ruleKStackPlacement; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKStackPlacement10220); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4733:1: ruleKStackPlacement returns [EObject current=null] : ( () otherlv_1= 'StackPlacement' ) ;
    public final EObject ruleKStackPlacement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4736:28: ( ( () otherlv_1= 'StackPlacement' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4737:1: ( () otherlv_1= 'StackPlacement' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4737:1: ( () otherlv_1= 'StackPlacement' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4737:2: () otherlv_1= 'StackPlacement'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4737:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4738:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKStackPlacementAccess().getKStackPlacementAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,76,FollowSets000.FOLLOW_76_in_ruleKStackPlacement10266); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4755:1: entryRuleEFloat returns [String current=null] : iv_ruleEFloat= ruleEFloat EOF ;
    public final String entryRuleEFloat() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEFloat = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4756:2: (iv_ruleEFloat= ruleEFloat EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4757:2: iv_ruleEFloat= ruleEFloat EOF
            {
             newCompositeNode(grammarAccess.getEFloatRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_entryRuleEFloat10303);
            iv_ruleEFloat=ruleEFloat();

            state._fsp--;

             current =iv_ruleEFloat.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEFloat10314); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4764:1: ruleEFloat returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )? ) ;
    public final AntlrDatatypeRuleToken ruleEFloat() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;
        Token this_INT_3=null;
        Token this_INT_7=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4767:28: ( ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4768:1: ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4768:1: ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4768:2: (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4768:2: (kw= '-' )?
            int alt163=2;
            int LA163_0 = input.LA(1);

            if ( (LA163_0==27) ) {
                alt163=1;
            }
            switch (alt163) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4769:2: kw= '-'
                    {
                    kw=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleEFloat10353); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEFloatAccess().getHyphenMinusKeyword_0()); 
                        

                    }
                    break;

            }

            this_INT_1=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat10370); 

            		current.merge(this_INT_1);
                
             
                newLeafNode(this_INT_1, grammarAccess.getEFloatAccess().getINTTerminalRuleCall_1()); 
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4781:1: (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )?
            int alt167=2;
            int LA167_0 = input.LA(1);

            if ( (LA167_0==77) ) {
                alt167=1;
            }
            switch (alt167) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4782:2: kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )?
                    {
                    kw=(Token)match(input,77,FollowSets000.FOLLOW_77_in_ruleEFloat10389); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEFloatAccess().getFullStopKeyword_2_0()); 
                        
                    this_INT_3=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat10404); 

                    		current.merge(this_INT_3);
                        
                     
                        newLeafNode(this_INT_3, grammarAccess.getEFloatAccess().getINTTerminalRuleCall_2_1()); 
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4794:1: ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )?
                    int alt166=2;
                    int LA166_0 = input.LA(1);

                    if ( ((LA166_0>=78 && LA166_0<=79)) ) {
                        alt166=1;
                    }
                    switch (alt166) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4794:2: (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4794:2: (kw= 'E' | kw= 'e' )
                            int alt164=2;
                            int LA164_0 = input.LA(1);

                            if ( (LA164_0==78) ) {
                                alt164=1;
                            }
                            else if ( (LA164_0==79) ) {
                                alt164=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 164, 0, input);

                                throw nvae;
                            }
                            switch (alt164) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4795:2: kw= 'E'
                                    {
                                    kw=(Token)match(input,78,FollowSets000.FOLLOW_78_in_ruleEFloat10424); 

                                            current.merge(kw);
                                            newLeafNode(kw, grammarAccess.getEFloatAccess().getEKeyword_2_2_0_0()); 
                                        

                                    }
                                    break;
                                case 2 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4802:2: kw= 'e'
                                    {
                                    kw=(Token)match(input,79,FollowSets000.FOLLOW_79_in_ruleEFloat10443); 

                                            current.merge(kw);
                                            newLeafNode(kw, grammarAccess.getEFloatAccess().getEKeyword_2_2_0_1()); 
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4807:2: (kw= '-' )?
                            int alt165=2;
                            int LA165_0 = input.LA(1);

                            if ( (LA165_0==27) ) {
                                alt165=1;
                            }
                            switch (alt165) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4808:2: kw= '-'
                                    {
                                    kw=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleEFloat10458); 

                                            current.merge(kw);
                                            newLeafNode(kw, grammarAccess.getEFloatAccess().getHyphenMinusKeyword_2_2_1()); 
                                        

                                    }
                                    break;

                            }

                            this_INT_7=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat10475); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4828:1: entryRuleEBoolean returns [String current=null] : iv_ruleEBoolean= ruleEBoolean EOF ;
    public final String entryRuleEBoolean() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEBoolean = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4829:2: (iv_ruleEBoolean= ruleEBoolean EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4830:2: iv_ruleEBoolean= ruleEBoolean EOF
            {
             newCompositeNode(grammarAccess.getEBooleanRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_entryRuleEBoolean10525);
            iv_ruleEBoolean=ruleEBoolean();

            state._fsp--;

             current =iv_ruleEBoolean.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEBoolean10536); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4837:1: ruleEBoolean returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'true' | kw= 'false' ) ;
    public final AntlrDatatypeRuleToken ruleEBoolean() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4840:28: ( (kw= 'true' | kw= 'false' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4841:1: (kw= 'true' | kw= 'false' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4841:1: (kw= 'true' | kw= 'false' )
            int alt168=2;
            int LA168_0 = input.LA(1);

            if ( (LA168_0==80) ) {
                alt168=1;
            }
            else if ( (LA168_0==81) ) {
                alt168=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 168, 0, input);

                throw nvae;
            }
            switch (alt168) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4842:2: kw= 'true'
                    {
                    kw=(Token)match(input,80,FollowSets000.FOLLOW_80_in_ruleEBoolean10574); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEBooleanAccess().getTrueKeyword_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4849:2: kw= 'false'
                    {
                    kw=(Token)match(input,81,FollowSets000.FOLLOW_81_in_ruleEBoolean10593); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4862:1: entryRuleEInt returns [String current=null] : iv_ruleEInt= ruleEInt EOF ;
    public final String entryRuleEInt() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEInt = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4863:2: (iv_ruleEInt= ruleEInt EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4864:2: iv_ruleEInt= ruleEInt EOF
            {
             newCompositeNode(grammarAccess.getEIntRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_entryRuleEInt10634);
            iv_ruleEInt=ruleEInt();

            state._fsp--;

             current =iv_ruleEInt.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEInt10645); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4871:1: ruleEInt returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleEInt() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4874:28: ( ( (kw= '-' )? this_INT_1= RULE_INT ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4875:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4875:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4875:2: (kw= '-' )? this_INT_1= RULE_INT
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4875:2: (kw= '-' )?
            int alt169=2;
            int LA169_0 = input.LA(1);

            if ( (LA169_0==27) ) {
                alt169=1;
            }
            switch (alt169) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4876:2: kw= '-'
                    {
                    kw=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleEInt10684); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEIntAccess().getHyphenMinusKeyword_0()); 
                        

                    }
                    break;

            }

            this_INT_1=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEInt10701); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4898:1: entryRuleKInsets returns [EObject current=null] : iv_ruleKInsets= ruleKInsets EOF ;
    public final EObject entryRuleKInsets() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKInsets = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4899:2: (iv_ruleKInsets= ruleKInsets EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4900:2: iv_ruleKInsets= ruleKInsets EOF
            {
             newCompositeNode(grammarAccess.getKInsetsRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKInsets_in_entryRuleKInsets10748);
            iv_ruleKInsets=ruleKInsets();

            state._fsp--;

             current =iv_ruleKInsets; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKInsets10758); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4907:1: ruleKInsets returns [EObject current=null] : ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4910:28: ( ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4911:1: ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4911:1: ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4911:2: () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4911:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4912:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKInsetsAccess().getKInsetsAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,82,FollowSets000.FOLLOW_82_in_ruleKInsets10804); 

                	newLeafNode(otherlv_1, grammarAccess.getKInsetsAccess().getKInsetsKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKInsets10816); 

                	newLeafNode(otherlv_2, grammarAccess.getKInsetsAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4925:1: (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )?
            int alt170=2;
            int LA170_0 = input.LA(1);

            if ( (LA170_0==60) ) {
                alt170=1;
            }
            switch (alt170) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4925:3: otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,60,FollowSets000.FOLLOW_60_in_ruleKInsets10829); 

                        	newLeafNode(otherlv_3, grammarAccess.getKInsetsAccess().getTopKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4929:1: ( (lv_top_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4930:1: (lv_top_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4930:1: (lv_top_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4931:3: lv_top_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getTopEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets10850);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4947:4: (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )?
            int alt171=2;
            int LA171_0 = input.LA(1);

            if ( (LA171_0==61) ) {
                alt171=1;
            }
            switch (alt171) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4947:6: otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,61,FollowSets000.FOLLOW_61_in_ruleKInsets10865); 

                        	newLeafNode(otherlv_5, grammarAccess.getKInsetsAccess().getBottomKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4951:1: ( (lv_bottom_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4952:1: (lv_bottom_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4952:1: (lv_bottom_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4953:3: lv_bottom_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getBottomEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets10886);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4969:4: (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )?
            int alt172=2;
            int LA172_0 = input.LA(1);

            if ( (LA172_0==58) ) {
                alt172=1;
            }
            switch (alt172) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4969:6: otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,58,FollowSets000.FOLLOW_58_in_ruleKInsets10901); 

                        	newLeafNode(otherlv_7, grammarAccess.getKInsetsAccess().getLeftKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4973:1: ( (lv_left_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4974:1: (lv_left_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4974:1: (lv_left_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4975:3: lv_left_8_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getLeftEFloatParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets10922);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4991:4: (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )?
            int alt173=2;
            int LA173_0 = input.LA(1);

            if ( (LA173_0==59) ) {
                alt173=1;
            }
            switch (alt173) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4991:6: otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) )
                    {
                    otherlv_9=(Token)match(input,59,FollowSets000.FOLLOW_59_in_ruleKInsets10937); 

                        	newLeafNode(otherlv_9, grammarAccess.getKInsetsAccess().getRightKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4995:1: ( (lv_right_10_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4996:1: (lv_right_10_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4996:1: (lv_right_10_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4997:3: lv_right_10_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getRightEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets10958);
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

            otherlv_11=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKInsets10972); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5027:1: entryRuleKPoint returns [EObject current=null] : iv_ruleKPoint= ruleKPoint EOF ;
    public final EObject entryRuleKPoint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPoint = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5028:2: (iv_ruleKPoint= ruleKPoint EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5029:2: iv_ruleKPoint= ruleKPoint EOF
            {
             newCompositeNode(grammarAccess.getKPointRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPoint_in_entryRuleKPoint11010);
            iv_ruleKPoint=ruleKPoint();

            state._fsp--;

             current =iv_ruleKPoint; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPoint11020); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5036:1: ruleKPoint returns [EObject current=null] : ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) ) ;
    public final EObject ruleKPoint() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_x_3_0 = null;

        AntlrDatatypeRuleToken lv_y_5_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5039:28: ( ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5040:1: ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5040:1: ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5040:2: () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5040:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5041:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPointAccess().getKPointAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,83,FollowSets000.FOLLOW_83_in_ruleKPoint11066); 

                	newLeafNode(otherlv_1, grammarAccess.getKPointAccess().getKPointKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5050:1: (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5050:3: otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) )
            {
            otherlv_2=(Token)match(input,84,FollowSets000.FOLLOW_84_in_ruleKPoint11079); 

                	newLeafNode(otherlv_2, grammarAccess.getKPointAccess().getXKeyword_2_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5054:1: ( (lv_x_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5055:1: (lv_x_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5055:1: (lv_x_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5056:3: lv_x_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKPointAccess().getXEFloatParserRuleCall_2_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKPoint11100);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5072:3: (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5072:5: otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) )
            {
            otherlv_4=(Token)match(input,85,FollowSets000.FOLLOW_85_in_ruleKPoint11114); 

                	newLeafNode(otherlv_4, grammarAccess.getKPointAccess().getYKeyword_3_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5076:1: ( (lv_y_5_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5077:1: (lv_y_5_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5077:1: (lv_y_5_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5078:3: lv_y_5_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKPointAccess().getYEFloatParserRuleCall_3_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKPoint11135);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5102:1: entryRulePersistentEntry returns [EObject current=null] : iv_rulePersistentEntry= rulePersistentEntry EOF ;
    public final EObject entryRulePersistentEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePersistentEntry = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5103:2: (iv_rulePersistentEntry= rulePersistentEntry EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5104:2: iv_rulePersistentEntry= rulePersistentEntry EOF
            {
             newCompositeNode(grammarAccess.getPersistentEntryRule()); 
            pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry11172);
            iv_rulePersistentEntry=rulePersistentEntry();

            state._fsp--;

             current =iv_rulePersistentEntry; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRulePersistentEntry11182); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5111:1: rulePersistentEntry returns [EObject current=null] : ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? ) ;
    public final EObject rulePersistentEntry() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_key_0_0 = null;

        AntlrDatatypeRuleToken lv_value_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5114:28: ( ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5115:1: ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5115:1: ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5115:2: ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5115:2: ( (lv_key_0_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5116:1: (lv_key_0_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5116:1: (lv_key_0_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5117:3: lv_key_0_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getKeyEStringParserRuleCall_0_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_rulePersistentEntry11228);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5133:2: (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )?
            int alt174=2;
            int LA174_0 = input.LA(1);

            if ( (LA174_0==86) ) {
                alt174=1;
            }
            switch (alt174) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5133:4: otherlv_1= '=' ( (lv_value_2_0= ruleEString ) )
                    {
                    otherlv_1=(Token)match(input,86,FollowSets000.FOLLOW_86_in_rulePersistentEntry11241); 

                        	newLeafNode(otherlv_1, grammarAccess.getPersistentEntryAccess().getEqualsSignKeyword_1_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5137:1: ( (lv_value_2_0= ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5138:1: (lv_value_2_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5138:1: (lv_value_2_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5139:3: lv_value_2_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getValueEStringParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_rulePersistentEntry11262);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5163:1: entryRuleEString returns [String current=null] : iv_ruleEString= ruleEString EOF ;
    public final String entryRuleEString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEString = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5164:2: (iv_ruleEString= ruleEString EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5165:2: iv_ruleEString= ruleEString EOF
            {
             newCompositeNode(grammarAccess.getEStringRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_entryRuleEString11301);
            iv_ruleEString=ruleEString();

            state._fsp--;

             current =iv_ruleEString.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEString11312); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5172:1: ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleEString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token this_ID_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5175:28: ( (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5176:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5176:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            int alt175=2;
            int LA175_0 = input.LA(1);

            if ( (LA175_0==RULE_STRING) ) {
                alt175=1;
            }
            else if ( (LA175_0==RULE_ID) ) {
                alt175=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 175, 0, input);

                throw nvae;
            }
            switch (alt175) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5176:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleEString11352); 

                    		current.merge(this_STRING_0);
                        
                     
                        newLeafNode(this_STRING_0, grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5184:10: this_ID_1= RULE_ID
                    {
                    this_ID_1=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleEString11378); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5199:1: ruleLineStyle returns [Enumerator current=null] : ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) ) ;
    public final Enumerator ruleLineStyle() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5201:28: ( ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5202:1: ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5202:1: ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) )
            int alt176=5;
            switch ( input.LA(1) ) {
            case 87:
                {
                alt176=1;
                }
                break;
            case 88:
                {
                alt176=2;
                }
                break;
            case 89:
                {
                alt176=3;
                }
                break;
            case 90:
                {
                alt176=4;
                }
                break;
            case 91:
                {
                alt176=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 176, 0, input);

                throw nvae;
            }

            switch (alt176) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5202:2: (enumLiteral_0= 'SOLID' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5202:2: (enumLiteral_0= 'SOLID' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5202:4: enumLiteral_0= 'SOLID'
                    {
                    enumLiteral_0=(Token)match(input,87,FollowSets000.FOLLOW_87_in_ruleLineStyle11437); 

                            current = grammarAccess.getLineStyleAccess().getSOLIDEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getLineStyleAccess().getSOLIDEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5208:6: (enumLiteral_1= 'DASH' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5208:6: (enumLiteral_1= 'DASH' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5208:8: enumLiteral_1= 'DASH'
                    {
                    enumLiteral_1=(Token)match(input,88,FollowSets000.FOLLOW_88_in_ruleLineStyle11454); 

                            current = grammarAccess.getLineStyleAccess().getDASHEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getLineStyleAccess().getDASHEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5214:6: (enumLiteral_2= 'DOT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5214:6: (enumLiteral_2= 'DOT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5214:8: enumLiteral_2= 'DOT'
                    {
                    enumLiteral_2=(Token)match(input,89,FollowSets000.FOLLOW_89_in_ruleLineStyle11471); 

                            current = grammarAccess.getLineStyleAccess().getDOTEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getLineStyleAccess().getDOTEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5220:6: (enumLiteral_3= 'DASHDOT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5220:6: (enumLiteral_3= 'DASHDOT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5220:8: enumLiteral_3= 'DASHDOT'
                    {
                    enumLiteral_3=(Token)match(input,90,FollowSets000.FOLLOW_90_in_ruleLineStyle11488); 

                            current = grammarAccess.getLineStyleAccess().getDASHDOTEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getLineStyleAccess().getDASHDOTEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5226:6: (enumLiteral_4= 'DASHDOTDOT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5226:6: (enumLiteral_4= 'DASHDOTDOT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5226:8: enumLiteral_4= 'DASHDOTDOT'
                    {
                    enumLiteral_4=(Token)match(input,91,FollowSets000.FOLLOW_91_in_ruleLineStyle11505); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5236:1: ruleVerticalAlignment returns [Enumerator current=null] : ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) ) ;
    public final Enumerator ruleVerticalAlignment() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5238:28: ( ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5239:1: ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5239:1: ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) )
            int alt177=3;
            switch ( input.LA(1) ) {
            case 92:
                {
                alt177=1;
                }
                break;
            case 93:
                {
                alt177=2;
                }
                break;
            case 94:
                {
                alt177=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 177, 0, input);

                throw nvae;
            }

            switch (alt177) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5239:2: (enumLiteral_0= 'TOP' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5239:2: (enumLiteral_0= 'TOP' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5239:4: enumLiteral_0= 'TOP'
                    {
                    enumLiteral_0=(Token)match(input,92,FollowSets000.FOLLOW_92_in_ruleVerticalAlignment11550); 

                            current = grammarAccess.getVerticalAlignmentAccess().getTOPEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getVerticalAlignmentAccess().getTOPEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5245:6: (enumLiteral_1= 'CENTER' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5245:6: (enumLiteral_1= 'CENTER' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5245:8: enumLiteral_1= 'CENTER'
                    {
                    enumLiteral_1=(Token)match(input,93,FollowSets000.FOLLOW_93_in_ruleVerticalAlignment11567); 

                            current = grammarAccess.getVerticalAlignmentAccess().getCENTEREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getVerticalAlignmentAccess().getCENTEREnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5251:6: (enumLiteral_2= 'BOTTOM' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5251:6: (enumLiteral_2= 'BOTTOM' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5251:8: enumLiteral_2= 'BOTTOM'
                    {
                    enumLiteral_2=(Token)match(input,94,FollowSets000.FOLLOW_94_in_ruleVerticalAlignment11584); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5261:1: ruleHorizontalAlignment returns [Enumerator current=null] : ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) ) ;
    public final Enumerator ruleHorizontalAlignment() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5263:28: ( ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5264:1: ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5264:1: ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) )
            int alt178=3;
            switch ( input.LA(1) ) {
            case 95:
                {
                alt178=1;
                }
                break;
            case 93:
                {
                alt178=2;
                }
                break;
            case 96:
                {
                alt178=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 178, 0, input);

                throw nvae;
            }

            switch (alt178) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5264:2: (enumLiteral_0= 'LEFT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5264:2: (enumLiteral_0= 'LEFT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5264:4: enumLiteral_0= 'LEFT'
                    {
                    enumLiteral_0=(Token)match(input,95,FollowSets000.FOLLOW_95_in_ruleHorizontalAlignment11629); 

                            current = grammarAccess.getHorizontalAlignmentAccess().getLEFTEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getHorizontalAlignmentAccess().getLEFTEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5270:6: (enumLiteral_1= 'CENTER' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5270:6: (enumLiteral_1= 'CENTER' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5270:8: enumLiteral_1= 'CENTER'
                    {
                    enumLiteral_1=(Token)match(input,93,FollowSets000.FOLLOW_93_in_ruleHorizontalAlignment11646); 

                            current = grammarAccess.getHorizontalAlignmentAccess().getCENTEREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getHorizontalAlignmentAccess().getCENTEREnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5276:6: (enumLiteral_2= 'RIGHT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5276:6: (enumLiteral_2= 'RIGHT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5276:8: enumLiteral_2= 'RIGHT'
                    {
                    enumLiteral_2=(Token)match(input,96,FollowSets000.FOLLOW_96_in_ruleHorizontalAlignment11663); 

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
        public static final BitSet FOLLOW_12_in_ruleKRenderingLibrary143 = new BitSet(new long[]{0x0000000977C4C000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRenderingLibrary165 = new BitSet(new long[]{0x0000000977C4E000L});
        public static final BitSet FOLLOW_13_in_ruleKRenderingLibrary179 = new BitSet(new long[]{0x0000000977C48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRenderingLibrary202 = new BitSet(new long[]{0x0000000977C4E000L});
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
        public static final BitSet FOLLOW_ruleKPlacementData_in_entryRuleKPlacementData643 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPlacementData653 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDecoratorPlacementData_in_ruleKPlacementData700 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacementData_in_ruleKPlacementData727 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacementData_in_ruleKPlacementData754 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDirectPlacementData_in_ruleKPlacementData781 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolylinePlacementData_in_ruleKPlacementData808 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStyle_in_entryRuleKStyle843 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKStyle853 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForegroundColor_in_ruleKStyle900 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackgroundColor_in_ruleKStyle927 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineWidth_in_ruleKStyle954 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVisibility_in_ruleKStyle981 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineStyle_in_ruleKStyle1008 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontBold_in_ruleKStyle1035 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontItalic_in_ruleKStyle1062 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontName_in_ruleKStyle1089 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontSize_in_ruleKStyle1116 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVerticalAlignment_in_ruleKStyle1143 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKHorizontalAlignment_in_ruleKStyle1170 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPlacement_in_entryRuleKPlacement1205 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPlacement1215 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacement_in_ruleKPlacement1262 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacement_in_ruleKPlacement1289 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKXPosition_in_entryRuleKXPosition1324 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKXPosition1334 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLeftPosition_in_ruleKXPosition1381 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRightPosition_in_ruleKXPosition1408 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKYPosition_in_entryRuleKYPosition1443 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKYPosition1453 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKTopPosition_in_ruleKYPosition1500 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBottomPosition_in_ruleKYPosition1527 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRenderingRef_in_entryRuleKRenderingRef1562 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRenderingRef1572 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_15_in_ruleKRenderingRef1618 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKRenderingRef1641 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKRenderingRef1654 = new BitSet(new long[]{0x0000000000034000L});
        public static final BitSet FOLLOW_16_in_ruleKRenderingRef1667 = new BitSet(new long[]{0x004C081000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRenderingRef1688 = new BitSet(new long[]{0x0000000000024000L});
        public static final BitSet FOLLOW_17_in_ruleKRenderingRef1703 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRenderingRef1715 = new BitSet(new long[]{0x4000000000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRenderingRef1736 = new BitSet(new long[]{0x4000000000006000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_13_in_ruleKRenderingRef1750 = new BitSet(new long[]{0x4000000000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRenderingRef1773 = new BitSet(new long[]{0x4000000000006000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_14_in_ruleKRenderingRef1787 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKRenderingRef1801 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKEllipse_in_entryRuleKEllipse1839 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKEllipse1849 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_18_in_ruleKEllipse1895 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKEllipse1908 = new BitSet(new long[]{0x0000000000334000L});
        public static final BitSet FOLLOW_17_in_ruleKEllipse1921 = new BitSet(new long[]{0x4000000000080000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_19_in_ruleKEllipse1934 = new BitSet(new long[]{0x4000000000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKEllipse1957 = new BitSet(new long[]{0x4000000000316000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_13_in_ruleKEllipse1971 = new BitSet(new long[]{0x4000000000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKEllipse1994 = new BitSet(new long[]{0x4000000000316000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_16_in_ruleKEllipse2011 = new BitSet(new long[]{0x004C081000080000L});
        public static final BitSet FOLLOW_19_in_ruleKEllipse2024 = new BitSet(new long[]{0x004C081000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKEllipse2047 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKEllipse2062 = new BitSet(new long[]{0x0000000000080000L,0x0000000000001800L});
        public static final BitSet FOLLOW_19_in_ruleKEllipse2075 = new BitSet(new long[]{0x0000000000080000L,0x0000000000001800L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKEllipse2098 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKEllipse2113 = new BitSet(new long[]{0x0000000977CC8000L});
        public static final BitSet FOLLOW_19_in_ruleKEllipse2126 = new BitSet(new long[]{0x0000000977C48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKEllipse2149 = new BitSet(new long[]{0x0000000977C4E000L});
        public static final BitSet FOLLOW_13_in_ruleKEllipse2163 = new BitSet(new long[]{0x0000000977C48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKEllipse2186 = new BitSet(new long[]{0x0000000977C4E000L});
        public static final BitSet FOLLOW_14_in_ruleKEllipse2202 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRectangle_in_entryRuleKRectangle2240 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRectangle2250 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_ruleKRectangle2296 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKRectangle2309 = new BitSet(new long[]{0x0000000000334000L});
        public static final BitSet FOLLOW_17_in_ruleKRectangle2322 = new BitSet(new long[]{0x4000000000080000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_19_in_ruleKRectangle2335 = new BitSet(new long[]{0x4000000000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRectangle2358 = new BitSet(new long[]{0x4000000000316000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_13_in_ruleKRectangle2372 = new BitSet(new long[]{0x4000000000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRectangle2395 = new BitSet(new long[]{0x4000000000316000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_16_in_ruleKRectangle2412 = new BitSet(new long[]{0x004C081000080000L});
        public static final BitSet FOLLOW_19_in_ruleKRectangle2425 = new BitSet(new long[]{0x004C081000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRectangle2448 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKRectangle2463 = new BitSet(new long[]{0x0000000000080000L,0x0000000000001800L});
        public static final BitSet FOLLOW_19_in_ruleKRectangle2476 = new BitSet(new long[]{0x0000000000080000L,0x0000000000001800L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKRectangle2499 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKRectangle2514 = new BitSet(new long[]{0x0000000977CC8000L});
        public static final BitSet FOLLOW_19_in_ruleKRectangle2527 = new BitSet(new long[]{0x0000000977C48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRectangle2550 = new BitSet(new long[]{0x0000000977C4E000L});
        public static final BitSet FOLLOW_13_in_ruleKRectangle2564 = new BitSet(new long[]{0x0000000977C48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRectangle2587 = new BitSet(new long[]{0x0000000977C4E000L});
        public static final BitSet FOLLOW_14_in_ruleKRectangle2603 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRoundedRectangle_in_entryRuleKRoundedRectangle2641 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRoundedRectangle2651 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_23_in_ruleKRoundedRectangle2697 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRoundedRectangle2718 = new BitSet(new long[]{0x0000000008002010L});
        public static final BitSet FOLLOW_13_in_ruleKRoundedRectangle2731 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRoundedRectangle2754 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKRoundedRectangle2767 = new BitSet(new long[]{0x0000000000334000L});
        public static final BitSet FOLLOW_17_in_ruleKRoundedRectangle2780 = new BitSet(new long[]{0x4000000000080000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_19_in_ruleKRoundedRectangle2793 = new BitSet(new long[]{0x4000000000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRoundedRectangle2816 = new BitSet(new long[]{0x4000000000316000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_13_in_ruleKRoundedRectangle2830 = new BitSet(new long[]{0x4000000000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRoundedRectangle2853 = new BitSet(new long[]{0x4000000000316000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_16_in_ruleKRoundedRectangle2870 = new BitSet(new long[]{0x004C081000080000L});
        public static final BitSet FOLLOW_19_in_ruleKRoundedRectangle2883 = new BitSet(new long[]{0x004C081000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRoundedRectangle2906 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKRoundedRectangle2921 = new BitSet(new long[]{0x0000000000080000L,0x0000000000001800L});
        public static final BitSet FOLLOW_19_in_ruleKRoundedRectangle2934 = new BitSet(new long[]{0x0000000000080000L,0x0000000000001800L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKRoundedRectangle2957 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKRoundedRectangle2972 = new BitSet(new long[]{0x0000000977CC8000L});
        public static final BitSet FOLLOW_19_in_ruleKRoundedRectangle2985 = new BitSet(new long[]{0x0000000977C48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRoundedRectangle3008 = new BitSet(new long[]{0x0000000977C4E000L});
        public static final BitSet FOLLOW_13_in_ruleKRoundedRectangle3022 = new BitSet(new long[]{0x0000000977C48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRoundedRectangle3045 = new BitSet(new long[]{0x0000000977C4E000L});
        public static final BitSet FOLLOW_14_in_ruleKRoundedRectangle3061 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolyline_Impl_in_entryRuleKPolyline_Impl3099 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPolyline_Impl3109 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_24_in_ruleKPolyline_Impl3155 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKPolyline_Impl3168 = new BitSet(new long[]{0x0000000000334000L});
        public static final BitSet FOLLOW_17_in_ruleKPolyline_Impl3181 = new BitSet(new long[]{0x4000000000080000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_19_in_ruleKPolyline_Impl3194 = new BitSet(new long[]{0x4000000000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolyline_Impl3217 = new BitSet(new long[]{0x4000000000316000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_13_in_ruleKPolyline_Impl3231 = new BitSet(new long[]{0x4000000000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolyline_Impl3254 = new BitSet(new long[]{0x4000000000316000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_16_in_ruleKPolyline_Impl3271 = new BitSet(new long[]{0x004C081000080000L});
        public static final BitSet FOLLOW_19_in_ruleKPolyline_Impl3284 = new BitSet(new long[]{0x004C081000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKPolyline_Impl3307 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKPolyline_Impl3322 = new BitSet(new long[]{0x0000000000080000L,0x0000000000001800L});
        public static final BitSet FOLLOW_19_in_ruleKPolyline_Impl3335 = new BitSet(new long[]{0x0000000000080000L,0x0000000000001800L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKPolyline_Impl3358 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKPolyline_Impl3373 = new BitSet(new long[]{0x0000000977CC8000L});
        public static final BitSet FOLLOW_19_in_ruleKPolyline_Impl3386 = new BitSet(new long[]{0x0000000977C48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolyline_Impl3409 = new BitSet(new long[]{0x0000000977C4E000L});
        public static final BitSet FOLLOW_13_in_ruleKPolyline_Impl3423 = new BitSet(new long[]{0x0000000977C48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolyline_Impl3446 = new BitSet(new long[]{0x0000000977C4E000L});
        public static final BitSet FOLLOW_14_in_ruleKPolyline_Impl3462 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolygon_in_entryRuleKPolygon3500 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPolygon3510 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_25_in_ruleKPolygon3556 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKPolygon3569 = new BitSet(new long[]{0x0000000000334000L});
        public static final BitSet FOLLOW_17_in_ruleKPolygon3582 = new BitSet(new long[]{0x4000000000080000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_19_in_ruleKPolygon3595 = new BitSet(new long[]{0x4000000000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolygon3618 = new BitSet(new long[]{0x4000000000316000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_13_in_ruleKPolygon3632 = new BitSet(new long[]{0x4000000000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolygon3655 = new BitSet(new long[]{0x4000000000316000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_16_in_ruleKPolygon3672 = new BitSet(new long[]{0x004C081000080000L});
        public static final BitSet FOLLOW_19_in_ruleKPolygon3685 = new BitSet(new long[]{0x004C081000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKPolygon3708 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKPolygon3723 = new BitSet(new long[]{0x0000000000080000L,0x0000000000001800L});
        public static final BitSet FOLLOW_19_in_ruleKPolygon3736 = new BitSet(new long[]{0x0000000000080000L,0x0000000000001800L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKPolygon3759 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKPolygon3774 = new BitSet(new long[]{0x0000000977CC8000L});
        public static final BitSet FOLLOW_19_in_ruleKPolygon3787 = new BitSet(new long[]{0x0000000977C48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolygon3810 = new BitSet(new long[]{0x0000000977C4E000L});
        public static final BitSet FOLLOW_13_in_ruleKPolygon3824 = new BitSet(new long[]{0x0000000977C48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolygon3847 = new BitSet(new long[]{0x0000000977C4E000L});
        public static final BitSet FOLLOW_14_in_ruleKPolygon3863 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKImage_in_entryRuleKImage3901 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKImage3911 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_26_in_ruleKImage3957 = new BitSet(new long[]{0x0000000008000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKImage3979 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_27_in_ruleKImage3997 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKImage4019 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKImage4032 = new BitSet(new long[]{0x0000000000334000L});
        public static final BitSet FOLLOW_17_in_ruleKImage4045 = new BitSet(new long[]{0x4000000000080000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_19_in_ruleKImage4058 = new BitSet(new long[]{0x4000000000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKImage4081 = new BitSet(new long[]{0x4000000000316000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_13_in_ruleKImage4095 = new BitSet(new long[]{0x4000000000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKImage4118 = new BitSet(new long[]{0x4000000000316000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_16_in_ruleKImage4135 = new BitSet(new long[]{0x004C081000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKImage4156 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_21_in_ruleKImage4171 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKImage4183 = new BitSet(new long[]{0x0000000977C48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKImage4204 = new BitSet(new long[]{0x0000000977C4E000L});
        public static final BitSet FOLLOW_13_in_ruleKImage4218 = new BitSet(new long[]{0x0000000977C48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKImage4241 = new BitSet(new long[]{0x0000000977C4E000L});
        public static final BitSet FOLLOW_14_in_ruleKImage4255 = new BitSet(new long[]{0x0000000000104000L});
        public static final BitSet FOLLOW_20_in_ruleKImage4270 = new BitSet(new long[]{0x0000000000080000L,0x0000000000001800L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKImage4291 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKImage4305 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKArc_in_entryRuleKArc4343 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKArc4353 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_28_in_ruleKArc4399 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKArc4420 = new BitSet(new long[]{0x0000000008002010L});
        public static final BitSet FOLLOW_13_in_ruleKArc4433 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKArc4456 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKArc4469 = new BitSet(new long[]{0x0000000000334000L});
        public static final BitSet FOLLOW_17_in_ruleKArc4482 = new BitSet(new long[]{0x4000000000080000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_19_in_ruleKArc4495 = new BitSet(new long[]{0x4000000000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKArc4518 = new BitSet(new long[]{0x4000000000316000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_13_in_ruleKArc4532 = new BitSet(new long[]{0x4000000000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKArc4555 = new BitSet(new long[]{0x4000000000316000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_16_in_ruleKArc4572 = new BitSet(new long[]{0x004C081000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKArc4593 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_21_in_ruleKArc4608 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKArc4620 = new BitSet(new long[]{0x0000000977C48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKArc4641 = new BitSet(new long[]{0x0000000977C4E000L});
        public static final BitSet FOLLOW_13_in_ruleKArc4655 = new BitSet(new long[]{0x0000000977C48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKArc4678 = new BitSet(new long[]{0x0000000977C4E000L});
        public static final BitSet FOLLOW_14_in_ruleKArc4692 = new BitSet(new long[]{0x0000000000104000L});
        public static final BitSet FOLLOW_20_in_ruleKArc4707 = new BitSet(new long[]{0x0000000000080000L,0x0000000000001800L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKArc4728 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKArc4742 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKChildArea_in_entryRuleKChildArea4780 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKChildArea4790 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_29_in_ruleKChildArea4836 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKChildArea4849 = new BitSet(new long[]{0x0000000000034000L});
        public static final BitSet FOLLOW_17_in_ruleKChildArea4862 = new BitSet(new long[]{0x4000000000080000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_19_in_ruleKChildArea4875 = new BitSet(new long[]{0x4000000000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKChildArea4898 = new BitSet(new long[]{0x4000000000016000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_13_in_ruleKChildArea4912 = new BitSet(new long[]{0x4000000000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKChildArea4935 = new BitSet(new long[]{0x4000000000016000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_16_in_ruleKChildArea4952 = new BitSet(new long[]{0x004C081000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKChildArea4973 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKChildArea4987 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKText_in_entryRuleKText5025 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKText5035 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_30_in_ruleKText5081 = new BitSet(new long[]{0x0000000000001062L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKText5102 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKText5116 = new BitSet(new long[]{0x0000000080334000L});
        public static final BitSet FOLLOW_31_in_ruleKText5134 = new BitSet(new long[]{0x0000000000334000L});
        public static final BitSet FOLLOW_17_in_ruleKText5161 = new BitSet(new long[]{0x4000000000080000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_19_in_ruleKText5174 = new BitSet(new long[]{0x4000000000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKText5197 = new BitSet(new long[]{0x4000000000316000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_13_in_ruleKText5211 = new BitSet(new long[]{0x4000000000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKText5234 = new BitSet(new long[]{0x4000000000316000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_16_in_ruleKText5251 = new BitSet(new long[]{0x004C081000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKText5272 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_21_in_ruleKText5287 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKText5299 = new BitSet(new long[]{0x0000000977C48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKText5320 = new BitSet(new long[]{0x0000000977C4E000L});
        public static final BitSet FOLLOW_13_in_ruleKText5334 = new BitSet(new long[]{0x0000000977C48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKText5357 = new BitSet(new long[]{0x0000000977C4E000L});
        public static final BitSet FOLLOW_14_in_ruleKText5371 = new BitSet(new long[]{0x0000000000104000L});
        public static final BitSet FOLLOW_20_in_ruleKText5386 = new BitSet(new long[]{0x0000000000080000L,0x0000000000001800L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKText5407 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKText5421 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKCustomRendering_in_entryRuleKCustomRendering5459 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKCustomRendering5469 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_32_in_ruleKCustomRendering5515 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKCustomRendering5528 = new BitSet(new long[]{0x0000000200000000L});
        public static final BitSet FOLLOW_33_in_ruleKCustomRendering5540 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKCustomRendering5561 = new BitSet(new long[]{0x0000000400000000L});
        public static final BitSet FOLLOW_34_in_ruleKCustomRendering5573 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKCustomRendering5594 = new BitSet(new long[]{0x0000000000334000L});
        public static final BitSet FOLLOW_17_in_ruleKCustomRendering5607 = new BitSet(new long[]{0x4000000000080000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_19_in_ruleKCustomRendering5620 = new BitSet(new long[]{0x4000000000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKCustomRendering5643 = new BitSet(new long[]{0x4000000000316000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_13_in_ruleKCustomRendering5657 = new BitSet(new long[]{0x4000000000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKCustomRendering5680 = new BitSet(new long[]{0x4000000000316000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_16_in_ruleKCustomRendering5697 = new BitSet(new long[]{0x004C081000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKCustomRendering5718 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_21_in_ruleKCustomRendering5733 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKCustomRendering5745 = new BitSet(new long[]{0x0000000977C48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKCustomRendering5766 = new BitSet(new long[]{0x0000000977C4E000L});
        public static final BitSet FOLLOW_13_in_ruleKCustomRendering5780 = new BitSet(new long[]{0x0000000977C48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKCustomRendering5803 = new BitSet(new long[]{0x0000000977C4E000L});
        public static final BitSet FOLLOW_14_in_ruleKCustomRendering5817 = new BitSet(new long[]{0x0000000000104000L});
        public static final BitSet FOLLOW_20_in_ruleKCustomRendering5832 = new BitSet(new long[]{0x0000000000080000L,0x0000000000001800L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKCustomRendering5853 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKCustomRendering5867 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKSpline_in_entryRuleKSpline5905 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKSpline5915 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_35_in_ruleKSpline5961 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKSpline5974 = new BitSet(new long[]{0x0000000000334000L});
        public static final BitSet FOLLOW_17_in_ruleKSpline5987 = new BitSet(new long[]{0x4000000000080000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_19_in_ruleKSpline6000 = new BitSet(new long[]{0x4000000000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKSpline6023 = new BitSet(new long[]{0x4000000000316000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_13_in_ruleKSpline6037 = new BitSet(new long[]{0x4000000000000000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKSpline6060 = new BitSet(new long[]{0x4000000000316000L,0x00000000000007FFL});
        public static final BitSet FOLLOW_16_in_ruleKSpline6077 = new BitSet(new long[]{0x004C081000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKSpline6098 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_21_in_ruleKSpline6113 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKSpline6125 = new BitSet(new long[]{0x0000000977C48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKSpline6146 = new BitSet(new long[]{0x0000000977C4E000L});
        public static final BitSet FOLLOW_13_in_ruleKSpline6160 = new BitSet(new long[]{0x0000000977C48000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKSpline6183 = new BitSet(new long[]{0x0000000977C4E000L});
        public static final BitSet FOLLOW_14_in_ruleKSpline6197 = new BitSet(new long[]{0x0000000000104000L});
        public static final BitSet FOLLOW_20_in_ruleKSpline6212 = new BitSet(new long[]{0x0000000000080000L,0x0000000000001800L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKSpline6233 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKSpline6247 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDecoratorPlacementData_in_entryRuleKDecoratorPlacementData6285 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKDecoratorPlacementData6295 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_36_in_ruleKDecoratorPlacementData6332 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKDecoratorPlacementData6344 = new BitSet(new long[]{0x0000006000000000L});
        public static final BitSet FOLLOW_37_in_ruleKDecoratorPlacementData6362 = new BitSet(new long[]{0x0000004000000000L});
        public static final BitSet FOLLOW_38_in_ruleKDecoratorPlacementData6388 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData6409 = new BitSet(new long[]{0x0000078000004000L});
        public static final BitSet FOLLOW_39_in_ruleKDecoratorPlacementData6422 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData6443 = new BitSet(new long[]{0x0000070000004000L});
        public static final BitSet FOLLOW_40_in_ruleKDecoratorPlacementData6458 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData6479 = new BitSet(new long[]{0x0000060000004000L});
        public static final BitSet FOLLOW_41_in_ruleKDecoratorPlacementData6494 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData6515 = new BitSet(new long[]{0x0000040000004000L});
        public static final BitSet FOLLOW_42_in_ruleKDecoratorPlacementData6530 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData6551 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKDecoratorPlacementData6565 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacementData_in_entryRuleKGridPlacementData6601 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKGridPlacementData6611 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_43_in_ruleKGridPlacementData6648 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKGridPlacementData6660 = new BitSet(new long[]{0x0000100000000000L});
        public static final BitSet FOLLOW_44_in_ruleKGridPlacementData6672 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData6693 = new BitSet(new long[]{0x0000200000000000L});
        public static final BitSet FOLLOW_45_in_ruleKGridPlacementData6705 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData6726 = new BitSet(new long[]{0x0000400000000000L});
        public static final BitSet FOLLOW_46_in_ruleKGridPlacementData6738 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData6759 = new BitSet(new long[]{0x0000800000000000L});
        public static final BitSet FOLLOW_47_in_ruleKGridPlacementData6771 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData6792 = new BitSet(new long[]{0x0001000000000000L});
        public static final BitSet FOLLOW_48_in_ruleKGridPlacementData6804 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData6825 = new BitSet(new long[]{0x0002000000000000L});
        public static final BitSet FOLLOW_49_in_ruleKGridPlacementData6837 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData6858 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKGridPlacementData6870 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacementData_in_entryRuleKStackPlacementData6906 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKStackPlacementData6916 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_50_in_ruleKStackPlacementData6953 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKStackPlacementData6965 = new BitSet(new long[]{0x0000400000000000L});
        public static final BitSet FOLLOW_46_in_ruleKStackPlacementData6977 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData6998 = new BitSet(new long[]{0x0000800000000000L});
        public static final BitSet FOLLOW_47_in_ruleKStackPlacementData7010 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData7031 = new BitSet(new long[]{0x0001000000000000L});
        public static final BitSet FOLLOW_48_in_ruleKStackPlacementData7043 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData7064 = new BitSet(new long[]{0x0002000000000000L});
        public static final BitSet FOLLOW_49_in_ruleKStackPlacementData7076 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData7097 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKStackPlacementData7109 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDirectPlacementData_in_entryRuleKDirectPlacementData7145 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKDirectPlacementData7155 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_51_in_ruleKDirectPlacementData7192 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKDirectPlacementData7204 = new BitSet(new long[]{0x0010000000000000L});
        public static final BitSet FOLLOW_52_in_ruleKDirectPlacementData7216 = new BitSet(new long[]{0x0C00000000000000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKDirectPlacementData7237 = new BitSet(new long[]{0x0020000000002000L});
        public static final BitSet FOLLOW_13_in_ruleKDirectPlacementData7250 = new BitSet(new long[]{0x0020000000000000L});
        public static final BitSet FOLLOW_53_in_ruleKDirectPlacementData7264 = new BitSet(new long[]{0x0C00000000000000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKDirectPlacementData7285 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKDirectPlacementData7297 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolylinePlacementData_in_entryRuleKPolylinePlacementData7333 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPolylinePlacementData7343 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_54_in_ruleKPolylinePlacementData7380 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPolylinePlacementData7392 = new BitSet(new long[]{0x0080000000000000L});
        public static final BitSet FOLLOW_55_in_ruleKPolylinePlacementData7404 = new BitSet(new long[]{0x0C00000000080000L});
        public static final BitSet FOLLOW_19_in_ruleKPolylinePlacementData7417 = new BitSet(new long[]{0x0C00000000000000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData7440 = new BitSet(new long[]{0x0D00000000006000L});
        public static final BitSet FOLLOW_13_in_ruleKPolylinePlacementData7454 = new BitSet(new long[]{0x0C00000000000000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData7477 = new BitSet(new long[]{0x0D00000000006000L});
        public static final BitSet FOLLOW_56_in_ruleKPolylinePlacementData7492 = new BitSet(new long[]{0x004C081000000000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKPolylinePlacementData7513 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKPolylinePlacementData7527 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPosition_in_entryRuleKPosition7563 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPosition7573 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKXPosition_in_ruleKPosition7619 = new BitSet(new long[]{0x0200000000000000L});
        public static final BitSet FOLLOW_57_in_ruleKPosition7631 = new BitSet(new long[]{0x3000000000000000L});
        public static final BitSet FOLLOW_ruleKYPosition_in_ruleKPosition7652 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLeftPosition_in_entryRuleKLeftPosition7688 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLeftPosition7698 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_58_in_ruleKLeftPosition7744 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKLeftPosition7765 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKLeftPosition7786 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRightPosition_in_entryRuleKRightPosition7822 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRightPosition7832 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_59_in_ruleKRightPosition7878 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRightPosition7899 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRightPosition7920 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKTopPosition_in_entryRuleKTopPosition7956 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKTopPosition7966 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_60_in_ruleKTopPosition8012 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKTopPosition8033 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKTopPosition8054 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBottomPosition_in_entryRuleKBottomPosition8090 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKBottomPosition8100 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_61_in_ruleKBottomPosition8146 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKBottomPosition8167 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKBottomPosition8188 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForegroundColor_in_entryRuleKForegroundColor8224 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKForegroundColor8234 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_62_in_ruleKForegroundColor8280 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKForegroundColor8301 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKForegroundColor8322 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKForegroundColor8343 = new BitSet(new long[]{0x8000000000000002L});
        public static final BitSet FOLLOW_63_in_ruleKForegroundColor8361 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackgroundColor_in_entryRuleKBackgroundColor8411 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKBackgroundColor8421 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_64_in_ruleKBackgroundColor8467 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKBackgroundColor8488 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKBackgroundColor8509 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKBackgroundColor8530 = new BitSet(new long[]{0x8000000000000002L});
        public static final BitSet FOLLOW_63_in_ruleKBackgroundColor8548 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineWidth_in_entryRuleKLineWidth8598 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLineWidth8608 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKLineWidth8645 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKLineWidth8666 = new BitSet(new long[]{0x8000000000000002L});
        public static final BitSet FOLLOW_63_in_ruleKLineWidth8684 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVisibility_in_entryRuleKVisibility8734 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKVisibility8744 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForegroundVisibility_in_ruleKVisibility8791 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackgroundVisibility_in_ruleKVisibility8818 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForegroundVisibility_in_entryRuleKForegroundVisibility8853 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKForegroundVisibility8863 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_66_in_ruleKForegroundVisibility8909 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleKForegroundVisibility8930 = new BitSet(new long[]{0x8000000000000002L});
        public static final BitSet FOLLOW_63_in_ruleKForegroundVisibility8948 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackgroundVisibility_in_entryRuleKBackgroundVisibility8998 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKBackgroundVisibility9008 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_67_in_ruleKBackgroundVisibility9054 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleKBackgroundVisibility9075 = new BitSet(new long[]{0x8000000000000002L});
        public static final BitSet FOLLOW_63_in_ruleKBackgroundVisibility9093 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineStyle_in_entryRuleKLineStyle9143 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLineStyle9153 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_68_in_ruleKLineStyle9199 = new BitSet(new long[]{0x0000000000000000L,0x000000000F800000L});
        public static final BitSet FOLLOW_ruleLineStyle_in_ruleKLineStyle9220 = new BitSet(new long[]{0x8000000000000002L});
        public static final BitSet FOLLOW_63_in_ruleKLineStyle9238 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontBold_in_entryRuleKFontBold9288 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKFontBold9298 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_69_in_ruleKFontBold9344 = new BitSet(new long[]{0x8000000000000002L});
        public static final BitSet FOLLOW_63_in_ruleKFontBold9362 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontItalic_in_entryRuleKFontItalic9412 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKFontItalic9422 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_70_in_ruleKFontItalic9468 = new BitSet(new long[]{0x8000000000000002L});
        public static final BitSet FOLLOW_63_in_ruleKFontItalic9486 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontName_in_entryRuleKFontName9536 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKFontName9546 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_71_in_ruleKFontName9583 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKFontName9604 = new BitSet(new long[]{0x8000000000000002L});
        public static final BitSet FOLLOW_63_in_ruleKFontName9622 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontSize_in_entryRuleKFontSize9672 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKFontSize9682 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_72_in_ruleKFontSize9719 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKFontSize9740 = new BitSet(new long[]{0x8000000000000002L});
        public static final BitSet FOLLOW_63_in_ruleKFontSize9758 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVerticalAlignment_in_entryRuleKVerticalAlignment9808 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKVerticalAlignment9818 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_73_in_ruleKVerticalAlignment9864 = new BitSet(new long[]{0x0000000000000000L,0x0000000070000000L});
        public static final BitSet FOLLOW_ruleVerticalAlignment_in_ruleKVerticalAlignment9885 = new BitSet(new long[]{0x8000000000000002L});
        public static final BitSet FOLLOW_63_in_ruleKVerticalAlignment9903 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKHorizontalAlignment_in_entryRuleKHorizontalAlignment9953 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKHorizontalAlignment9963 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_74_in_ruleKHorizontalAlignment10009 = new BitSet(new long[]{0x0000000000000000L,0x00000001A0000000L});
        public static final BitSet FOLLOW_ruleHorizontalAlignment_in_ruleKHorizontalAlignment10030 = new BitSet(new long[]{0x8000000000000000L});
        public static final BitSet FOLLOW_63_in_ruleKHorizontalAlignment10048 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacement_in_entryRuleKGridPlacement10097 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKGridPlacement10107 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_75_in_ruleKGridPlacement10153 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKGridPlacement10174 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacement_in_entryRuleKStackPlacement10210 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKStackPlacement10220 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_76_in_ruleKStackPlacement10266 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_entryRuleEFloat10303 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEFloat10314 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_27_in_ruleEFloat10353 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat10370 = new BitSet(new long[]{0x0000000000000002L,0x0000000000002000L});
        public static final BitSet FOLLOW_77_in_ruleEFloat10389 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat10404 = new BitSet(new long[]{0x0000000000000002L,0x000000000000C000L});
        public static final BitSet FOLLOW_78_in_ruleEFloat10424 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_79_in_ruleEFloat10443 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_27_in_ruleEFloat10458 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat10475 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEBoolean_in_entryRuleEBoolean10525 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEBoolean10536 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_80_in_ruleEBoolean10574 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_81_in_ruleEBoolean10593 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEInt_in_entryRuleEInt10634 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEInt10645 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_27_in_ruleEInt10684 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEInt10701 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKInsets_in_entryRuleKInsets10748 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKInsets10758 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_82_in_ruleKInsets10804 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKInsets10816 = new BitSet(new long[]{0x3C00000000004000L});
        public static final BitSet FOLLOW_60_in_ruleKInsets10829 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets10850 = new BitSet(new long[]{0x2C00000000004000L});
        public static final BitSet FOLLOW_61_in_ruleKInsets10865 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets10886 = new BitSet(new long[]{0x0C00000000004000L});
        public static final BitSet FOLLOW_58_in_ruleKInsets10901 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets10922 = new BitSet(new long[]{0x0800000000004000L});
        public static final BitSet FOLLOW_59_in_ruleKInsets10937 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets10958 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKInsets10972 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPoint_in_entryRuleKPoint11010 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPoint11020 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_83_in_ruleKPoint11066 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
        public static final BitSet FOLLOW_84_in_ruleKPoint11079 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKPoint11100 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
        public static final BitSet FOLLOW_85_in_ruleKPoint11114 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKPoint11135 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry11172 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRulePersistentEntry11182 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_rulePersistentEntry11228 = new BitSet(new long[]{0x0000000000000002L,0x0000000000400000L});
        public static final BitSet FOLLOW_86_in_rulePersistentEntry11241 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_rulePersistentEntry11262 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_entryRuleEString11301 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEString11312 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleEString11352 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleEString11378 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_87_in_ruleLineStyle11437 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_88_in_ruleLineStyle11454 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_89_in_ruleLineStyle11471 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_90_in_ruleLineStyle11488 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_91_in_ruleLineStyle11505 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_92_in_ruleVerticalAlignment11550 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_93_in_ruleVerticalAlignment11567 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_94_in_ruleVerticalAlignment11584 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_95_in_ruleHorizontalAlignment11629 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_93_in_ruleHorizontalAlignment11646 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_96_in_ruleHorizontalAlignment11663 = new BitSet(new long[]{0x0000000000000002L});
    }


}