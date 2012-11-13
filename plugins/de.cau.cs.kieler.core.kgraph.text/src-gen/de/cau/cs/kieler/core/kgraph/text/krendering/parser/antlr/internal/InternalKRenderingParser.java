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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INT", "RULE_STRING", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'RenderingLibrary'", "'{'", "','", "'}'", "'RenderingRef'", "'styles'", "':'", "'placementData'", "'Ellipse'", "'childPlacement'", "'children'", "'Rectangle'", "'RoundedRectangle'", "'Polyline'", "'RoundedBendsPolyline'", "'Polygon'", "'Image'", "'-'", "'Arc'", "'ChildArea'", "'Text'", "'clip'", "'mapProperties'", "'CustomRendering'", "'className'", "'bundleName'", "'Spline'", "'DecoratorPlacementData'", "'relative'", "'location'", "'xOffset'", "'yOffset'", "'width'", "'height'", "'GridPlacementData'", "'widthHint'", "'heightHint'", "'insetRight'", "'insetBottom'", "'insetLeft'", "'insetTop'", "'StackPlacementData'", "'DirectPlacementData'", "'topLeft'", "'bottomRight'", "'PolylinePlacementData'", "'points'", "'detailedPlacementData'", "'/'", "'left'", "'right'", "'top'", "'bottom'", "'foregroundColor'", "'!'", "'backgroundColor'", "'lineWidth'", "'foregroundVisibility'", "'backgroundVisibility'", "'lineStyle'", "'rotation'", "'bold'", "'italic'", "'font'", "'fontSize'", "'verticalAlignment'", "'horizontalAlignment'", "'gridPlacement'", "'stackPlacement'", "'.'", "'E'", "'e'", "'true'", "'false'", "'KInsets'", "'KPoint'", "'x'", "'y'", "'='", "'SOLID'", "'DASH'", "'DOT'", "'DASHDOT'", "'DASHDOTDOT'", "'TOP'", "'CENTER'", "'BOTTOM'", "'LEFT'", "'RIGHT'"
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

            if ( (LA3_0==15||LA3_0==19||(LA3_0>=22 && LA3_0<=27)||(LA3_0>=29 && LA3_0<=31)||LA3_0==34||LA3_0==37) ) {
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

                        if ( (LA2_0==13||LA2_0==15||LA2_0==19||(LA2_0>=22 && LA2_0<=27)||(LA2_0>=29 && LA2_0<=31)||LA2_0==34||LA2_0==37) ) {
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
            case 19:
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:636:1: ruleKRenderingRef returns [EObject current=null] : ( () otherlv_1= 'RenderingRef' ( ( ruleEString ) ) (otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? otherlv_12= '}' )? ) ;
    public final EObject ruleKRenderingRef() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        EObject lv_styles_6_0 = null;

        EObject lv_styles_8_0 = null;

        EObject lv_placementData_11_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:639:28: ( ( () otherlv_1= 'RenderingRef' ( ( ruleEString ) ) (otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? otherlv_12= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:640:1: ( () otherlv_1= 'RenderingRef' ( ( ruleEString ) ) (otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? otherlv_12= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:640:1: ( () otherlv_1= 'RenderingRef' ( ( ruleEString ) ) (otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? otherlv_12= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:640:2: () otherlv_1= 'RenderingRef' ( ( ruleEString ) ) (otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? otherlv_12= '}' )?
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:665:2: (otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? otherlv_12= '}' )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==12) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:665:4: otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? otherlv_12= '}'
                    {
                    otherlv_3=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRenderingRef1708); 

                        	newLeafNode(otherlv_3, grammarAccess.getKRenderingRefAccess().getLeftCurlyBracketKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:669:1: (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0==16) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:669:3: otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )*
                            {
                            otherlv_4=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRenderingRef1721); 

                                	newLeafNode(otherlv_4, grammarAccess.getKRenderingRefAccess().getStylesKeyword_3_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:673:1: (otherlv_5= ':' )?
                            int alt10=2;
                            int LA10_0 = input.LA(1);

                            if ( (LA10_0==17) ) {
                                alt10=1;
                            }
                            switch (alt10) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:673:3: otherlv_5= ':'
                                    {
                                    otherlv_5=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRenderingRef1734); 

                                        	newLeafNode(otherlv_5, grammarAccess.getKRenderingRefAccess().getColonKeyword_3_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:677:3: ( (lv_styles_6_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:678:1: (lv_styles_6_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:678:1: (lv_styles_6_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:679:3: lv_styles_6_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getStylesKStyleParserRuleCall_3_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRenderingRef1757);
                            lv_styles_6_0=ruleKStyle();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKRenderingRefRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"styles",
                                    		lv_styles_6_0, 
                                    		"KStyle");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:695:2: ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )*
                            loop12:
                            do {
                                int alt12=2;
                                int LA12_0 = input.LA(1);

                                if ( (LA12_0==13||LA12_0==64||(LA12_0>=66 && LA12_0<=77)) ) {
                                    alt12=1;
                                }


                                switch (alt12) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:695:3: (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:695:3: (otherlv_7= ',' )?
                            	    int alt11=2;
                            	    int LA11_0 = input.LA(1);

                            	    if ( (LA11_0==13) ) {
                            	        alt11=1;
                            	    }
                            	    switch (alt11) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:695:5: otherlv_7= ','
                            	            {
                            	            otherlv_7=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRenderingRef1771); 

                            	                	newLeafNode(otherlv_7, grammarAccess.getKRenderingRefAccess().getCommaKeyword_3_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:699:3: ( (lv_styles_8_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:700:1: (lv_styles_8_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:700:1: (lv_styles_8_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:701:3: lv_styles_8_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getStylesKStyleParserRuleCall_3_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRenderingRef1794);
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


                            	    }
                            	    break;

                            	default :
                            	    break loop12;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:717:6: (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0==18) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:717:8: otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) )
                            {
                            otherlv_9=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRenderingRef1811); 

                                	newLeafNode(otherlv_9, grammarAccess.getKRenderingRefAccess().getPlacementDataKeyword_3_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:721:1: (otherlv_10= ':' )?
                            int alt14=2;
                            int LA14_0 = input.LA(1);

                            if ( (LA14_0==17) ) {
                                alt14=1;
                            }
                            switch (alt14) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:721:3: otherlv_10= ':'
                                    {
                                    otherlv_10=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRenderingRef1824); 

                                        	newLeafNode(otherlv_10, grammarAccess.getKRenderingRefAccess().getColonKeyword_3_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:725:3: ( (lv_placementData_11_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:726:1: (lv_placementData_11_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:726:1: (lv_placementData_11_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:727:3: lv_placementData_11_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getPlacementDataKPlacementDataParserRuleCall_3_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRenderingRef1847);
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

                    otherlv_12=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRenderingRef1861); 

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
            pushFollow(FollowSets000.FOLLOW_ruleKEllipse_in_entryRuleKEllipse1899);
            iv_ruleKEllipse=ruleKEllipse();

            state._fsp--;

             current =iv_ruleKEllipse; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKEllipse1909); 

            }

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

            otherlv_1=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKEllipse1955); 

                	newLeafNode(otherlv_1, grammarAccess.getKEllipseAccess().getEllipseKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:778:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==12) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:778:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKEllipse1968); 

                        	newLeafNode(otherlv_2, grammarAccess.getKEllipseAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:782:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==16) ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:782:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKEllipse1981); 

                                	newLeafNode(otherlv_3, grammarAccess.getKEllipseAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:786:1: (otherlv_4= ':' )?
                            int alt17=2;
                            int LA17_0 = input.LA(1);

                            if ( (LA17_0==17) ) {
                                alt17=1;
                            }
                            switch (alt17) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:786:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKEllipse1994); 

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
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKEllipse2017);
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
                            loop19:
                            do {
                                int alt19=2;
                                int LA19_0 = input.LA(1);

                                if ( (LA19_0==13||LA19_0==64||(LA19_0>=66 && LA19_0<=77)) ) {
                                    alt19=1;
                                }


                                switch (alt19) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:808:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:808:3: (otherlv_6= ',' )?
                            	    int alt18=2;
                            	    int LA18_0 = input.LA(1);

                            	    if ( (LA18_0==13) ) {
                            	        alt18=1;
                            	    }
                            	    switch (alt18) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:808:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKEllipse2031); 

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
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKEllipse2054);
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
                            	    break loop19;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:830:6: (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==18) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:830:8: otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKEllipse2071); 

                                	newLeafNode(otherlv_8, grammarAccess.getKEllipseAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:834:1: (otherlv_9= ':' )?
                            int alt21=2;
                            int LA21_0 = input.LA(1);

                            if ( (LA21_0==17) ) {
                                alt21=1;
                            }
                            switch (alt21) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:834:3: otherlv_9= ':'
                                    {
                                    otherlv_9=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKEllipse2084); 

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
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKEllipse2107);
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
                    int alt24=2;
                    int LA24_0 = input.LA(1);

                    if ( (LA24_0==20) ) {
                        alt24=1;
                    }
                    switch (alt24) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:856:6: otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            {
                            otherlv_11=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKEllipse2122); 

                                	newLeafNode(otherlv_11, grammarAccess.getKEllipseAccess().getChildPlacementKeyword_2_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:860:1: (otherlv_12= ':' )?
                            int alt23=2;
                            int LA23_0 = input.LA(1);

                            if ( (LA23_0==17) ) {
                                alt23=1;
                            }
                            switch (alt23) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:860:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKEllipse2135); 

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
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKEllipse2158);
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
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==21) ) {
                        alt28=1;
                    }
                    switch (alt28) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:882:6: otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            {
                            otherlv_14=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKEllipse2173); 

                                	newLeafNode(otherlv_14, grammarAccess.getKEllipseAccess().getChildrenKeyword_2_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:886:1: (otherlv_15= ':' )?
                            int alt25=2;
                            int LA25_0 = input.LA(1);

                            if ( (LA25_0==17) ) {
                                alt25=1;
                            }
                            switch (alt25) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:886:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKEllipse2186); 

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
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKEllipse2209);
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
                            loop27:
                            do {
                                int alt27=2;
                                int LA27_0 = input.LA(1);

                                if ( (LA27_0==13||LA27_0==15||LA27_0==19||(LA27_0>=22 && LA27_0<=27)||(LA27_0>=29 && LA27_0<=31)||LA27_0==34||LA27_0==37) ) {
                                    alt27=1;
                                }


                                switch (alt27) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:908:3: (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:908:3: (otherlv_17= ',' )?
                            	    int alt26=2;
                            	    int LA26_0 = input.LA(1);

                            	    if ( (LA26_0==13) ) {
                            	        alt26=1;
                            	    }
                            	    switch (alt26) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:908:5: otherlv_17= ','
                            	            {
                            	            otherlv_17=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKEllipse2223); 

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
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKEllipse2246);
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
                            	    break loop27;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_19=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKEllipse2262); 

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
            pushFollow(FollowSets000.FOLLOW_ruleKRectangle_in_entryRuleKRectangle2300);
            iv_ruleKRectangle=ruleKRectangle();

            state._fsp--;

             current =iv_ruleKRectangle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRectangle2310); 

            }

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

            otherlv_1=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleKRectangle2356); 

                	newLeafNode(otherlv_1, grammarAccess.getKRectangleAccess().getRectangleKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:965:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==12) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:965:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRectangle2369); 

                        	newLeafNode(otherlv_2, grammarAccess.getKRectangleAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:969:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt33=2;
                    int LA33_0 = input.LA(1);

                    if ( (LA33_0==16) ) {
                        alt33=1;
                    }
                    switch (alt33) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:969:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRectangle2382); 

                                	newLeafNode(otherlv_3, grammarAccess.getKRectangleAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:973:1: (otherlv_4= ':' )?
                            int alt30=2;
                            int LA30_0 = input.LA(1);

                            if ( (LA30_0==17) ) {
                                alt30=1;
                            }
                            switch (alt30) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:973:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRectangle2395); 

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
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRectangle2418);
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
                            loop32:
                            do {
                                int alt32=2;
                                int LA32_0 = input.LA(1);

                                if ( (LA32_0==13||LA32_0==64||(LA32_0>=66 && LA32_0<=77)) ) {
                                    alt32=1;
                                }


                                switch (alt32) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:995:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:995:3: (otherlv_6= ',' )?
                            	    int alt31=2;
                            	    int LA31_0 = input.LA(1);

                            	    if ( (LA31_0==13) ) {
                            	        alt31=1;
                            	    }
                            	    switch (alt31) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:995:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRectangle2432); 

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
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRectangle2455);
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
                            	    break loop32;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1017:6: (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
                    int alt35=2;
                    int LA35_0 = input.LA(1);

                    if ( (LA35_0==18) ) {
                        alt35=1;
                    }
                    switch (alt35) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1017:8: otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRectangle2472); 

                                	newLeafNode(otherlv_8, grammarAccess.getKRectangleAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1021:1: (otherlv_9= ':' )?
                            int alt34=2;
                            int LA34_0 = input.LA(1);

                            if ( (LA34_0==17) ) {
                                alt34=1;
                            }
                            switch (alt34) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1021:3: otherlv_9= ':'
                                    {
                                    otherlv_9=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRectangle2485); 

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
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRectangle2508);
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
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==20) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1043:6: otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            {
                            otherlv_11=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKRectangle2523); 

                                	newLeafNode(otherlv_11, grammarAccess.getKRectangleAccess().getChildPlacementKeyword_2_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1047:1: (otherlv_12= ':' )?
                            int alt36=2;
                            int LA36_0 = input.LA(1);

                            if ( (LA36_0==17) ) {
                                alt36=1;
                            }
                            switch (alt36) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1047:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRectangle2536); 

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
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKRectangle2559);
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
                    int alt41=2;
                    int LA41_0 = input.LA(1);

                    if ( (LA41_0==21) ) {
                        alt41=1;
                    }
                    switch (alt41) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1069:6: otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            {
                            otherlv_14=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKRectangle2574); 

                                	newLeafNode(otherlv_14, grammarAccess.getKRectangleAccess().getChildrenKeyword_2_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1073:1: (otherlv_15= ':' )?
                            int alt38=2;
                            int LA38_0 = input.LA(1);

                            if ( (LA38_0==17) ) {
                                alt38=1;
                            }
                            switch (alt38) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1073:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRectangle2587); 

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
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRectangle2610);
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
                            loop40:
                            do {
                                int alt40=2;
                                int LA40_0 = input.LA(1);

                                if ( (LA40_0==13||LA40_0==15||LA40_0==19||(LA40_0>=22 && LA40_0<=27)||(LA40_0>=29 && LA40_0<=31)||LA40_0==34||LA40_0==37) ) {
                                    alt40=1;
                                }


                                switch (alt40) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1095:3: (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1095:3: (otherlv_17= ',' )?
                            	    int alt39=2;
                            	    int LA39_0 = input.LA(1);

                            	    if ( (LA39_0==13) ) {
                            	        alt39=1;
                            	    }
                            	    switch (alt39) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1095:5: otherlv_17= ','
                            	            {
                            	            otherlv_17=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRectangle2624); 

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
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRectangle2647);
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
                            	    break loop40;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_19=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRectangle2663); 

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
            pushFollow(FollowSets000.FOLLOW_ruleKRoundedRectangle_in_entryRuleKRoundedRectangle2701);
            iv_ruleKRoundedRectangle=ruleKRoundedRectangle();

            state._fsp--;

             current =iv_ruleKRoundedRectangle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRoundedRectangle2711); 

            }

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

            otherlv_1=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKRoundedRectangle2757); 

                	newLeafNode(otherlv_1, grammarAccess.getKRoundedRectangleAccess().getRoundedRectangleKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1152:1: ( (lv_cornerWidth_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1153:1: (lv_cornerWidth_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1153:1: (lv_cornerWidth_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1154:3: lv_cornerWidth_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getCornerWidthEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRoundedRectangle2778);
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
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==13) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1170:4: otherlv_3= ','
                    {
                    otherlv_3=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRoundedRectangle2791); 

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
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRoundedRectangle2814);
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
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==12) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1192:4: otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}'
                    {
                    otherlv_5=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRoundedRectangle2827); 

                        	newLeafNode(otherlv_5, grammarAccess.getKRoundedRectangleAccess().getLeftCurlyBracketKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1196:1: (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )?
                    int alt47=2;
                    int LA47_0 = input.LA(1);

                    if ( (LA47_0==16) ) {
                        alt47=1;
                    }
                    switch (alt47) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1196:3: otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            {
                            otherlv_6=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRoundedRectangle2840); 

                                	newLeafNode(otherlv_6, grammarAccess.getKRoundedRectangleAccess().getStylesKeyword_5_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1200:1: (otherlv_7= ':' )?
                            int alt44=2;
                            int LA44_0 = input.LA(1);

                            if ( (LA44_0==17) ) {
                                alt44=1;
                            }
                            switch (alt44) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1200:3: otherlv_7= ':'
                                    {
                                    otherlv_7=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRoundedRectangle2853); 

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
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRoundedRectangle2876);
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
                            loop46:
                            do {
                                int alt46=2;
                                int LA46_0 = input.LA(1);

                                if ( (LA46_0==13||LA46_0==64||(LA46_0>=66 && LA46_0<=77)) ) {
                                    alt46=1;
                                }


                                switch (alt46) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1222:3: (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1222:3: (otherlv_9= ',' )?
                            	    int alt45=2;
                            	    int LA45_0 = input.LA(1);

                            	    if ( (LA45_0==13) ) {
                            	        alt45=1;
                            	    }
                            	    switch (alt45) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1222:5: otherlv_9= ','
                            	            {
                            	            otherlv_9=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRoundedRectangle2890); 

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
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRoundedRectangle2913);
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
                            	    break loop46;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1244:6: (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )?
                    int alt49=2;
                    int LA49_0 = input.LA(1);

                    if ( (LA49_0==18) ) {
                        alt49=1;
                    }
                    switch (alt49) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1244:8: otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) )
                            {
                            otherlv_11=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRoundedRectangle2930); 

                                	newLeafNode(otherlv_11, grammarAccess.getKRoundedRectangleAccess().getPlacementDataKeyword_5_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1248:1: (otherlv_12= ':' )?
                            int alt48=2;
                            int LA48_0 = input.LA(1);

                            if ( (LA48_0==17) ) {
                                alt48=1;
                            }
                            switch (alt48) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1248:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRoundedRectangle2943); 

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
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRoundedRectangle2966);
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
                    int alt51=2;
                    int LA51_0 = input.LA(1);

                    if ( (LA51_0==20) ) {
                        alt51=1;
                    }
                    switch (alt51) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1270:6: otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) )
                            {
                            otherlv_14=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKRoundedRectangle2981); 

                                	newLeafNode(otherlv_14, grammarAccess.getKRoundedRectangleAccess().getChildPlacementKeyword_5_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1274:1: (otherlv_15= ':' )?
                            int alt50=2;
                            int LA50_0 = input.LA(1);

                            if ( (LA50_0==17) ) {
                                alt50=1;
                            }
                            switch (alt50) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1274:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRoundedRectangle2994); 

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
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKRoundedRectangle3017);
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
                    int alt55=2;
                    int LA55_0 = input.LA(1);

                    if ( (LA55_0==21) ) {
                        alt55=1;
                    }
                    switch (alt55) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1296:6: otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )*
                            {
                            otherlv_17=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKRoundedRectangle3032); 

                                	newLeafNode(otherlv_17, grammarAccess.getKRoundedRectangleAccess().getChildrenKeyword_5_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1300:1: (otherlv_18= ':' )?
                            int alt52=2;
                            int LA52_0 = input.LA(1);

                            if ( (LA52_0==17) ) {
                                alt52=1;
                            }
                            switch (alt52) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1300:3: otherlv_18= ':'
                                    {
                                    otherlv_18=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRoundedRectangle3045); 

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
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRoundedRectangle3068);
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
                            loop54:
                            do {
                                int alt54=2;
                                int LA54_0 = input.LA(1);

                                if ( (LA54_0==13||LA54_0==15||LA54_0==19||(LA54_0>=22 && LA54_0<=27)||(LA54_0>=29 && LA54_0<=31)||LA54_0==34||LA54_0==37) ) {
                                    alt54=1;
                                }


                                switch (alt54) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1322:3: (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1322:3: (otherlv_20= ',' )?
                            	    int alt53=2;
                            	    int LA53_0 = input.LA(1);

                            	    if ( (LA53_0==13) ) {
                            	        alt53=1;
                            	    }
                            	    switch (alt53) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1322:5: otherlv_20= ','
                            	            {
                            	            otherlv_20=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRoundedRectangle3082); 

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
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRoundedRectangle3105);
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
                            	    break loop54;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_22=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRoundedRectangle3121); 

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
            pushFollow(FollowSets000.FOLLOW_ruleKPolyline_Impl_in_entryRuleKPolyline_Impl3159);
            iv_ruleKPolyline_Impl=ruleKPolyline_Impl();

            state._fsp--;

             current =iv_ruleKPolyline_Impl; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPolyline_Impl3169); 

            }

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

            otherlv_1=(Token)match(input,24,FollowSets000.FOLLOW_24_in_ruleKPolyline_Impl3215); 

                	newLeafNode(otherlv_1, grammarAccess.getKPolyline_ImplAccess().getPolylineKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1379:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==12) ) {
                alt69=1;
            }
            switch (alt69) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1379:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolyline_Impl3228); 

                        	newLeafNode(otherlv_2, grammarAccess.getKPolyline_ImplAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1383:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt60=2;
                    int LA60_0 = input.LA(1);

                    if ( (LA60_0==16) ) {
                        alt60=1;
                    }
                    switch (alt60) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1383:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolyline_Impl3241); 

                                	newLeafNode(otherlv_3, grammarAccess.getKPolyline_ImplAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1387:1: (otherlv_4= ':' )?
                            int alt57=2;
                            int LA57_0 = input.LA(1);

                            if ( (LA57_0==17) ) {
                                alt57=1;
                            }
                            switch (alt57) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1387:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolyline_Impl3254); 

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
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolyline_Impl3277);
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
                            loop59:
                            do {
                                int alt59=2;
                                int LA59_0 = input.LA(1);

                                if ( (LA59_0==13||LA59_0==64||(LA59_0>=66 && LA59_0<=77)) ) {
                                    alt59=1;
                                }


                                switch (alt59) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1409:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1409:3: (otherlv_6= ',' )?
                            	    int alt58=2;
                            	    int LA58_0 = input.LA(1);

                            	    if ( (LA58_0==13) ) {
                            	        alt58=1;
                            	    }
                            	    switch (alt58) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1409:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPolyline_Impl3291); 

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
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolyline_Impl3314);
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
                            	    break loop59;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1431:6: (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
                    int alt62=2;
                    int LA62_0 = input.LA(1);

                    if ( (LA62_0==18) ) {
                        alt62=1;
                    }
                    switch (alt62) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1431:8: otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPolyline_Impl3331); 

                                	newLeafNode(otherlv_8, grammarAccess.getKPolyline_ImplAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1435:1: (otherlv_9= ':' )?
                            int alt61=2;
                            int LA61_0 = input.LA(1);

                            if ( (LA61_0==17) ) {
                                alt61=1;
                            }
                            switch (alt61) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1435:3: otherlv_9= ':'
                                    {
                                    otherlv_9=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolyline_Impl3344); 

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
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKPolyline_Impl3367);
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
                    int alt64=2;
                    int LA64_0 = input.LA(1);

                    if ( (LA64_0==20) ) {
                        alt64=1;
                    }
                    switch (alt64) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1457:6: otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            {
                            otherlv_11=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKPolyline_Impl3382); 

                                	newLeafNode(otherlv_11, grammarAccess.getKPolyline_ImplAccess().getChildPlacementKeyword_2_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1461:1: (otherlv_12= ':' )?
                            int alt63=2;
                            int LA63_0 = input.LA(1);

                            if ( (LA63_0==17) ) {
                                alt63=1;
                            }
                            switch (alt63) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1461:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolyline_Impl3395); 

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
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKPolyline_Impl3418);
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
                    int alt68=2;
                    int LA68_0 = input.LA(1);

                    if ( (LA68_0==21) ) {
                        alt68=1;
                    }
                    switch (alt68) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1483:6: otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            {
                            otherlv_14=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKPolyline_Impl3433); 

                                	newLeafNode(otherlv_14, grammarAccess.getKPolyline_ImplAccess().getChildrenKeyword_2_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1487:1: (otherlv_15= ':' )?
                            int alt65=2;
                            int LA65_0 = input.LA(1);

                            if ( (LA65_0==17) ) {
                                alt65=1;
                            }
                            switch (alt65) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1487:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolyline_Impl3446); 

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
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolyline_Impl3469);
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
                            loop67:
                            do {
                                int alt67=2;
                                int LA67_0 = input.LA(1);

                                if ( (LA67_0==13||LA67_0==15||LA67_0==19||(LA67_0>=22 && LA67_0<=27)||(LA67_0>=29 && LA67_0<=31)||LA67_0==34||LA67_0==37) ) {
                                    alt67=1;
                                }


                                switch (alt67) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1509:3: (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1509:3: (otherlv_17= ',' )?
                            	    int alt66=2;
                            	    int LA66_0 = input.LA(1);

                            	    if ( (LA66_0==13) ) {
                            	        alt66=1;
                            	    }
                            	    switch (alt66) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1509:5: otherlv_17= ','
                            	            {
                            	            otherlv_17=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPolyline_Impl3483); 

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
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolyline_Impl3506);
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
                            	    break loop67;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_19=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolyline_Impl3522); 

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
            pushFollow(FollowSets000.FOLLOW_ruleKRoundedBendsPolyline_in_entryRuleKRoundedBendsPolyline3560);
            iv_ruleKRoundedBendsPolyline=ruleKRoundedBendsPolyline();

            state._fsp--;

             current =iv_ruleKRoundedBendsPolyline; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRoundedBendsPolyline3570); 

            }

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

            otherlv_1=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleKRoundedBendsPolyline3616); 

                	newLeafNode(otherlv_1, grammarAccess.getKRoundedBendsPolylineAccess().getRoundedBendsPolylineKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1566:1: ( (lv_bendRadius_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1567:1: (lv_bendRadius_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1567:1: (lv_bendRadius_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1568:3: lv_bendRadius_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRoundedBendsPolylineAccess().getBendRadiusEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRoundedBendsPolyline3637);
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
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==12) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1584:4: otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'childPlacement' (otherlv_13= ':' )? ( (lv_childPlacement_14_0= ruleKPlacement ) ) )? (otherlv_15= 'children' (otherlv_16= ':' )? ( (lv_children_17_0= ruleKRendering ) ) ( (otherlv_18= ',' )? ( (lv_children_19_0= ruleKRendering ) ) )* )? otherlv_20= '}'
                    {
                    otherlv_3=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRoundedBendsPolyline3650); 

                        	newLeafNode(otherlv_3, grammarAccess.getKRoundedBendsPolylineAccess().getLeftCurlyBracketKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1588:1: (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )?
                    int alt73=2;
                    int LA73_0 = input.LA(1);

                    if ( (LA73_0==16) ) {
                        alt73=1;
                    }
                    switch (alt73) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1588:3: otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )*
                            {
                            otherlv_4=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRoundedBendsPolyline3663); 

                                	newLeafNode(otherlv_4, grammarAccess.getKRoundedBendsPolylineAccess().getStylesKeyword_3_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1592:1: (otherlv_5= ':' )?
                            int alt70=2;
                            int LA70_0 = input.LA(1);

                            if ( (LA70_0==17) ) {
                                alt70=1;
                            }
                            switch (alt70) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1592:3: otherlv_5= ':'
                                    {
                                    otherlv_5=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRoundedBendsPolyline3676); 

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
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRoundedBendsPolyline3699);
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
                            loop72:
                            do {
                                int alt72=2;
                                int LA72_0 = input.LA(1);

                                if ( (LA72_0==13||LA72_0==64||(LA72_0>=66 && LA72_0<=77)) ) {
                                    alt72=1;
                                }


                                switch (alt72) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1614:3: (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1614:3: (otherlv_7= ',' )?
                            	    int alt71=2;
                            	    int LA71_0 = input.LA(1);

                            	    if ( (LA71_0==13) ) {
                            	        alt71=1;
                            	    }
                            	    switch (alt71) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1614:5: otherlv_7= ','
                            	            {
                            	            otherlv_7=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRoundedBendsPolyline3713); 

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
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRoundedBendsPolyline3736);
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
                            	    break loop72;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1636:6: (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )?
                    int alt75=2;
                    int LA75_0 = input.LA(1);

                    if ( (LA75_0==18) ) {
                        alt75=1;
                    }
                    switch (alt75) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1636:8: otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) )
                            {
                            otherlv_9=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRoundedBendsPolyline3753); 

                                	newLeafNode(otherlv_9, grammarAccess.getKRoundedBendsPolylineAccess().getPlacementDataKeyword_3_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1640:1: (otherlv_10= ':' )?
                            int alt74=2;
                            int LA74_0 = input.LA(1);

                            if ( (LA74_0==17) ) {
                                alt74=1;
                            }
                            switch (alt74) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1640:3: otherlv_10= ':'
                                    {
                                    otherlv_10=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRoundedBendsPolyline3766); 

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
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRoundedBendsPolyline3789);
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
                    int alt77=2;
                    int LA77_0 = input.LA(1);

                    if ( (LA77_0==20) ) {
                        alt77=1;
                    }
                    switch (alt77) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1662:6: otherlv_12= 'childPlacement' (otherlv_13= ':' )? ( (lv_childPlacement_14_0= ruleKPlacement ) )
                            {
                            otherlv_12=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKRoundedBendsPolyline3804); 

                                	newLeafNode(otherlv_12, grammarAccess.getKRoundedBendsPolylineAccess().getChildPlacementKeyword_3_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1666:1: (otherlv_13= ':' )?
                            int alt76=2;
                            int LA76_0 = input.LA(1);

                            if ( (LA76_0==17) ) {
                                alt76=1;
                            }
                            switch (alt76) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1666:3: otherlv_13= ':'
                                    {
                                    otherlv_13=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRoundedBendsPolyline3817); 

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
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKRoundedBendsPolyline3840);
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
                    int alt81=2;
                    int LA81_0 = input.LA(1);

                    if ( (LA81_0==21) ) {
                        alt81=1;
                    }
                    switch (alt81) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1688:6: otherlv_15= 'children' (otherlv_16= ':' )? ( (lv_children_17_0= ruleKRendering ) ) ( (otherlv_18= ',' )? ( (lv_children_19_0= ruleKRendering ) ) )*
                            {
                            otherlv_15=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKRoundedBendsPolyline3855); 

                                	newLeafNode(otherlv_15, grammarAccess.getKRoundedBendsPolylineAccess().getChildrenKeyword_3_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1692:1: (otherlv_16= ':' )?
                            int alt78=2;
                            int LA78_0 = input.LA(1);

                            if ( (LA78_0==17) ) {
                                alt78=1;
                            }
                            switch (alt78) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1692:3: otherlv_16= ':'
                                    {
                                    otherlv_16=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRoundedBendsPolyline3868); 

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
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRoundedBendsPolyline3891);
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
                            loop80:
                            do {
                                int alt80=2;
                                int LA80_0 = input.LA(1);

                                if ( (LA80_0==13||LA80_0==15||LA80_0==19||(LA80_0>=22 && LA80_0<=27)||(LA80_0>=29 && LA80_0<=31)||LA80_0==34||LA80_0==37) ) {
                                    alt80=1;
                                }


                                switch (alt80) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1714:3: (otherlv_18= ',' )? ( (lv_children_19_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1714:3: (otherlv_18= ',' )?
                            	    int alt79=2;
                            	    int LA79_0 = input.LA(1);

                            	    if ( (LA79_0==13) ) {
                            	        alt79=1;
                            	    }
                            	    switch (alt79) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1714:5: otherlv_18= ','
                            	            {
                            	            otherlv_18=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRoundedBendsPolyline3905); 

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
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRoundedBendsPolyline3928);
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
                            	    break loop80;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_20=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRoundedBendsPolyline3944); 

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
            pushFollow(FollowSets000.FOLLOW_ruleKPolygon_in_entryRuleKPolygon3982);
            iv_ruleKPolygon=ruleKPolygon();

            state._fsp--;

             current =iv_ruleKPolygon; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPolygon3992); 

            }

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

            otherlv_1=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleKPolygon4038); 

                	newLeafNode(otherlv_1, grammarAccess.getKPolygonAccess().getPolygonKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1771:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==12) ) {
                alt95=1;
            }
            switch (alt95) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1771:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolygon4051); 

                        	newLeafNode(otherlv_2, grammarAccess.getKPolygonAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1775:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt86=2;
                    int LA86_0 = input.LA(1);

                    if ( (LA86_0==16) ) {
                        alt86=1;
                    }
                    switch (alt86) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1775:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolygon4064); 

                                	newLeafNode(otherlv_3, grammarAccess.getKPolygonAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1779:1: (otherlv_4= ':' )?
                            int alt83=2;
                            int LA83_0 = input.LA(1);

                            if ( (LA83_0==17) ) {
                                alt83=1;
                            }
                            switch (alt83) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1779:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolygon4077); 

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
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolygon4100);
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
                            loop85:
                            do {
                                int alt85=2;
                                int LA85_0 = input.LA(1);

                                if ( (LA85_0==13||LA85_0==64||(LA85_0>=66 && LA85_0<=77)) ) {
                                    alt85=1;
                                }


                                switch (alt85) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1801:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1801:3: (otherlv_6= ',' )?
                            	    int alt84=2;
                            	    int LA84_0 = input.LA(1);

                            	    if ( (LA84_0==13) ) {
                            	        alt84=1;
                            	    }
                            	    switch (alt84) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1801:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPolygon4114); 

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
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolygon4137);
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
                            	    break loop85;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1823:6: (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
                    int alt88=2;
                    int LA88_0 = input.LA(1);

                    if ( (LA88_0==18) ) {
                        alt88=1;
                    }
                    switch (alt88) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1823:8: otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPolygon4154); 

                                	newLeafNode(otherlv_8, grammarAccess.getKPolygonAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1827:1: (otherlv_9= ':' )?
                            int alt87=2;
                            int LA87_0 = input.LA(1);

                            if ( (LA87_0==17) ) {
                                alt87=1;
                            }
                            switch (alt87) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1827:3: otherlv_9= ':'
                                    {
                                    otherlv_9=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolygon4167); 

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
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKPolygon4190);
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
                    int alt90=2;
                    int LA90_0 = input.LA(1);

                    if ( (LA90_0==20) ) {
                        alt90=1;
                    }
                    switch (alt90) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1849:6: otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            {
                            otherlv_11=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKPolygon4205); 

                                	newLeafNode(otherlv_11, grammarAccess.getKPolygonAccess().getChildPlacementKeyword_2_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1853:1: (otherlv_12= ':' )?
                            int alt89=2;
                            int LA89_0 = input.LA(1);

                            if ( (LA89_0==17) ) {
                                alt89=1;
                            }
                            switch (alt89) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1853:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolygon4218); 

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
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKPolygon4241);
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
                    int alt94=2;
                    int LA94_0 = input.LA(1);

                    if ( (LA94_0==21) ) {
                        alt94=1;
                    }
                    switch (alt94) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1875:6: otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            {
                            otherlv_14=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKPolygon4256); 

                                	newLeafNode(otherlv_14, grammarAccess.getKPolygonAccess().getChildrenKeyword_2_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1879:1: (otherlv_15= ':' )?
                            int alt91=2;
                            int LA91_0 = input.LA(1);

                            if ( (LA91_0==17) ) {
                                alt91=1;
                            }
                            switch (alt91) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1879:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolygon4269); 

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
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolygon4292);
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
                            loop93:
                            do {
                                int alt93=2;
                                int LA93_0 = input.LA(1);

                                if ( (LA93_0==13||LA93_0==15||LA93_0==19||(LA93_0>=22 && LA93_0<=27)||(LA93_0>=29 && LA93_0<=31)||LA93_0==34||LA93_0==37) ) {
                                    alt93=1;
                                }


                                switch (alt93) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1901:3: (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1901:3: (otherlv_17= ',' )?
                            	    int alt92=2;
                            	    int LA92_0 = input.LA(1);

                            	    if ( (LA92_0==13) ) {
                            	        alt92=1;
                            	    }
                            	    switch (alt92) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1901:5: otherlv_17= ','
                            	            {
                            	            otherlv_17=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPolygon4306); 

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
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolygon4329);
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
                            	    break loop93;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_19=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolygon4345); 

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
            pushFollow(FollowSets000.FOLLOW_ruleKImage_in_entryRuleKImage4383);
            iv_ruleKImage=ruleKImage();

            state._fsp--;

             current =iv_ruleKImage; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKImage4393); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1944:1: ruleKImage returns [EObject current=null] : ( () otherlv_1= 'Image' ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' ) ( (lv_imagePath_4_0= ruleEString ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? ) ;
    public final EObject ruleKImage() throws RecognitionException {
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
        AntlrDatatypeRuleToken lv_bundleName_2_0 = null;

        AntlrDatatypeRuleToken lv_imagePath_4_0 = null;

        EObject lv_styles_8_0 = null;

        EObject lv_styles_10_0 = null;

        EObject lv_placementData_13_0 = null;

        EObject lv_childPlacement_16_0 = null;

        EObject lv_children_19_0 = null;

        EObject lv_children_21_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1947:28: ( ( () otherlv_1= 'Image' ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' ) ( (lv_imagePath_4_0= ruleEString ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1948:1: ( () otherlv_1= 'Image' ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' ) ( (lv_imagePath_4_0= ruleEString ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1948:1: ( () otherlv_1= 'Image' ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' ) ( (lv_imagePath_4_0= ruleEString ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1948:2: () otherlv_1= 'Image' ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' ) ( (lv_imagePath_4_0= ruleEString ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1948:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1949:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKImageAccess().getKImageAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleKImage4439); 

                	newLeafNode(otherlv_1, grammarAccess.getKImageAccess().getImageKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1958:1: ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' )
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( ((LA96_0>=RULE_STRING && LA96_0<=RULE_ID)) ) {
                alt96=1;
            }
            else if ( (LA96_0==28) ) {
                alt96=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 96, 0, input);

                throw nvae;
            }
            switch (alt96) {
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
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKImage4461);
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
                    otherlv_3=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleKImage4479); 

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
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKImage4501);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1999:2: (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )?
            int alt109=2;
            int LA109_0 = input.LA(1);

            if ( (LA109_0==12) ) {
                alt109=1;
            }
            switch (alt109) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1999:4: otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}'
                    {
                    otherlv_5=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKImage4514); 

                        	newLeafNode(otherlv_5, grammarAccess.getKImageAccess().getLeftCurlyBracketKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2003:1: (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )?
                    int alt100=2;
                    int LA100_0 = input.LA(1);

                    if ( (LA100_0==16) ) {
                        alt100=1;
                    }
                    switch (alt100) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2003:3: otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            {
                            otherlv_6=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKImage4527); 

                                	newLeafNode(otherlv_6, grammarAccess.getKImageAccess().getStylesKeyword_4_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2007:1: (otherlv_7= ':' )?
                            int alt97=2;
                            int LA97_0 = input.LA(1);

                            if ( (LA97_0==17) ) {
                                alt97=1;
                            }
                            switch (alt97) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2007:3: otherlv_7= ':'
                                    {
                                    otherlv_7=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKImage4540); 

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
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKImage4563);
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
                            loop99:
                            do {
                                int alt99=2;
                                int LA99_0 = input.LA(1);

                                if ( (LA99_0==13||LA99_0==64||(LA99_0>=66 && LA99_0<=77)) ) {
                                    alt99=1;
                                }


                                switch (alt99) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2029:3: (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2029:3: (otherlv_9= ',' )?
                            	    int alt98=2;
                            	    int LA98_0 = input.LA(1);

                            	    if ( (LA98_0==13) ) {
                            	        alt98=1;
                            	    }
                            	    switch (alt98) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2029:5: otherlv_9= ','
                            	            {
                            	            otherlv_9=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKImage4577); 

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
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKImage4600);
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
                            	    break loop99;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2051:6: (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )?
                    int alt102=2;
                    int LA102_0 = input.LA(1);

                    if ( (LA102_0==18) ) {
                        alt102=1;
                    }
                    switch (alt102) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2051:8: otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) )
                            {
                            otherlv_11=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKImage4617); 

                                	newLeafNode(otherlv_11, grammarAccess.getKImageAccess().getPlacementDataKeyword_4_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2055:1: (otherlv_12= ':' )?
                            int alt101=2;
                            int LA101_0 = input.LA(1);

                            if ( (LA101_0==17) ) {
                                alt101=1;
                            }
                            switch (alt101) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2055:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKImage4630); 

                                        	newLeafNode(otherlv_12, grammarAccess.getKImageAccess().getColonKeyword_4_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2059:3: ( (lv_placementData_13_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2060:1: (lv_placementData_13_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2060:1: (lv_placementData_13_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2061:3: lv_placementData_13_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKImageAccess().getPlacementDataKPlacementDataParserRuleCall_4_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKImage4653);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2077:4: (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )?
                    int alt104=2;
                    int LA104_0 = input.LA(1);

                    if ( (LA104_0==20) ) {
                        alt104=1;
                    }
                    switch (alt104) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2077:6: otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) )
                            {
                            otherlv_14=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKImage4668); 

                                	newLeafNode(otherlv_14, grammarAccess.getKImageAccess().getChildPlacementKeyword_4_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2081:1: (otherlv_15= ':' )?
                            int alt103=2;
                            int LA103_0 = input.LA(1);

                            if ( (LA103_0==17) ) {
                                alt103=1;
                            }
                            switch (alt103) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2081:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKImage4681); 

                                        	newLeafNode(otherlv_15, grammarAccess.getKImageAccess().getColonKeyword_4_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2085:3: ( (lv_childPlacement_16_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2086:1: (lv_childPlacement_16_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2086:1: (lv_childPlacement_16_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2087:3: lv_childPlacement_16_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKImageAccess().getChildPlacementKPlacementParserRuleCall_4_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKImage4704);
                            lv_childPlacement_16_0=ruleKPlacement();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKImageRule());
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2103:4: (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )?
                    int alt108=2;
                    int LA108_0 = input.LA(1);

                    if ( (LA108_0==21) ) {
                        alt108=1;
                    }
                    switch (alt108) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2103:6: otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )*
                            {
                            otherlv_17=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKImage4719); 

                                	newLeafNode(otherlv_17, grammarAccess.getKImageAccess().getChildrenKeyword_4_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2107:1: (otherlv_18= ':' )?
                            int alt105=2;
                            int LA105_0 = input.LA(1);

                            if ( (LA105_0==17) ) {
                                alt105=1;
                            }
                            switch (alt105) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2107:3: otherlv_18= ':'
                                    {
                                    otherlv_18=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKImage4732); 

                                        	newLeafNode(otherlv_18, grammarAccess.getKImageAccess().getColonKeyword_4_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2111:3: ( (lv_children_19_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2112:1: (lv_children_19_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2112:1: (lv_children_19_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2113:3: lv_children_19_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKImageAccess().getChildrenKRenderingParserRuleCall_4_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKImage4755);
                            lv_children_19_0=ruleKRendering();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKImageRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"children",
                                    		lv_children_19_0, 
                                    		"KRendering");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2129:2: ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )*
                            loop107:
                            do {
                                int alt107=2;
                                int LA107_0 = input.LA(1);

                                if ( (LA107_0==13||LA107_0==15||LA107_0==19||(LA107_0>=22 && LA107_0<=27)||(LA107_0>=29 && LA107_0<=31)||LA107_0==34||LA107_0==37) ) {
                                    alt107=1;
                                }


                                switch (alt107) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2129:3: (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2129:3: (otherlv_20= ',' )?
                            	    int alt106=2;
                            	    int LA106_0 = input.LA(1);

                            	    if ( (LA106_0==13) ) {
                            	        alt106=1;
                            	    }
                            	    switch (alt106) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2129:5: otherlv_20= ','
                            	            {
                            	            otherlv_20=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKImage4769); 

                            	                	newLeafNode(otherlv_20, grammarAccess.getKImageAccess().getCommaKeyword_4_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2133:3: ( (lv_children_21_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2134:1: (lv_children_21_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2134:1: (lv_children_21_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2135:3: lv_children_21_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKImageAccess().getChildrenKRenderingParserRuleCall_4_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKImage4792);
                            	    lv_children_21_0=ruleKRendering();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKImageRule());
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
                            	    break loop107;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_22=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKImage4808); 

                        	newLeafNode(otherlv_22, grammarAccess.getKImageAccess().getRightCurlyBracketKeyword_4_5());
                        

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2163:1: entryRuleKArc returns [EObject current=null] : iv_ruleKArc= ruleKArc EOF ;
    public final EObject entryRuleKArc() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKArc = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2164:2: (iv_ruleKArc= ruleKArc EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2165:2: iv_ruleKArc= ruleKArc EOF
            {
             newCompositeNode(grammarAccess.getKArcRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKArc_in_entryRuleKArc4846);
            iv_ruleKArc=ruleKArc();

            state._fsp--;

             current =iv_ruleKArc; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKArc4856); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2172:1: ruleKArc returns [EObject current=null] : ( () otherlv_1= 'Arc' ( (lv_startAngle_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_arcAngle_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? ) ;
    public final EObject ruleKArc() throws RecognitionException {
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
        AntlrDatatypeRuleToken lv_startAngle_2_0 = null;

        AntlrDatatypeRuleToken lv_arcAngle_4_0 = null;

        EObject lv_styles_8_0 = null;

        EObject lv_styles_10_0 = null;

        EObject lv_placementData_13_0 = null;

        EObject lv_childPlacement_16_0 = null;

        EObject lv_children_19_0 = null;

        EObject lv_children_21_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2175:28: ( ( () otherlv_1= 'Arc' ( (lv_startAngle_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_arcAngle_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2176:1: ( () otherlv_1= 'Arc' ( (lv_startAngle_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_arcAngle_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2176:1: ( () otherlv_1= 'Arc' ( (lv_startAngle_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_arcAngle_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2176:2: () otherlv_1= 'Arc' ( (lv_startAngle_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_arcAngle_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2176:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2177:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKArcAccess().getKArcAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleKArc4902); 

                	newLeafNode(otherlv_1, grammarAccess.getKArcAccess().getArcKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2186:1: ( (lv_startAngle_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2187:1: (lv_startAngle_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2187:1: (lv_startAngle_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2188:3: lv_startAngle_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKArcAccess().getStartAngleEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKArc4923);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2204:2: (otherlv_3= ',' )?
            int alt110=2;
            int LA110_0 = input.LA(1);

            if ( (LA110_0==13) ) {
                alt110=1;
            }
            switch (alt110) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2204:4: otherlv_3= ','
                    {
                    otherlv_3=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKArc4936); 

                        	newLeafNode(otherlv_3, grammarAccess.getKArcAccess().getCommaKeyword_3());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2208:3: ( (lv_arcAngle_4_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2209:1: (lv_arcAngle_4_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2209:1: (lv_arcAngle_4_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2210:3: lv_arcAngle_4_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKArcAccess().getArcAngleEFloatParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKArc4959);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2226:2: (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )?
            int alt123=2;
            int LA123_0 = input.LA(1);

            if ( (LA123_0==12) ) {
                alt123=1;
            }
            switch (alt123) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2226:4: otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}'
                    {
                    otherlv_5=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKArc4972); 

                        	newLeafNode(otherlv_5, grammarAccess.getKArcAccess().getLeftCurlyBracketKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2230:1: (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )?
                    int alt114=2;
                    int LA114_0 = input.LA(1);

                    if ( (LA114_0==16) ) {
                        alt114=1;
                    }
                    switch (alt114) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2230:3: otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            {
                            otherlv_6=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKArc4985); 

                                	newLeafNode(otherlv_6, grammarAccess.getKArcAccess().getStylesKeyword_5_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2234:1: (otherlv_7= ':' )?
                            int alt111=2;
                            int LA111_0 = input.LA(1);

                            if ( (LA111_0==17) ) {
                                alt111=1;
                            }
                            switch (alt111) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2234:3: otherlv_7= ':'
                                    {
                                    otherlv_7=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKArc4998); 

                                        	newLeafNode(otherlv_7, grammarAccess.getKArcAccess().getColonKeyword_5_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2238:3: ( (lv_styles_8_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2239:1: (lv_styles_8_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2239:1: (lv_styles_8_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2240:3: lv_styles_8_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKArcAccess().getStylesKStyleParserRuleCall_5_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKArc5021);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2256:2: ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            loop113:
                            do {
                                int alt113=2;
                                int LA113_0 = input.LA(1);

                                if ( (LA113_0==13||LA113_0==64||(LA113_0>=66 && LA113_0<=77)) ) {
                                    alt113=1;
                                }


                                switch (alt113) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2256:3: (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2256:3: (otherlv_9= ',' )?
                            	    int alt112=2;
                            	    int LA112_0 = input.LA(1);

                            	    if ( (LA112_0==13) ) {
                            	        alt112=1;
                            	    }
                            	    switch (alt112) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2256:5: otherlv_9= ','
                            	            {
                            	            otherlv_9=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKArc5035); 

                            	                	newLeafNode(otherlv_9, grammarAccess.getKArcAccess().getCommaKeyword_5_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2260:3: ( (lv_styles_10_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2261:1: (lv_styles_10_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2261:1: (lv_styles_10_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2262:3: lv_styles_10_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKArcAccess().getStylesKStyleParserRuleCall_5_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKArc5058);
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
                            	    break loop113;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2278:6: (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )?
                    int alt116=2;
                    int LA116_0 = input.LA(1);

                    if ( (LA116_0==18) ) {
                        alt116=1;
                    }
                    switch (alt116) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2278:8: otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) )
                            {
                            otherlv_11=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKArc5075); 

                                	newLeafNode(otherlv_11, grammarAccess.getKArcAccess().getPlacementDataKeyword_5_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2282:1: (otherlv_12= ':' )?
                            int alt115=2;
                            int LA115_0 = input.LA(1);

                            if ( (LA115_0==17) ) {
                                alt115=1;
                            }
                            switch (alt115) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2282:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKArc5088); 

                                        	newLeafNode(otherlv_12, grammarAccess.getKArcAccess().getColonKeyword_5_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2286:3: ( (lv_placementData_13_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2287:1: (lv_placementData_13_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2287:1: (lv_placementData_13_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2288:3: lv_placementData_13_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKArcAccess().getPlacementDataKPlacementDataParserRuleCall_5_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKArc5111);
                            lv_placementData_13_0=ruleKPlacementData();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKArcRule());
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2304:4: (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )?
                    int alt118=2;
                    int LA118_0 = input.LA(1);

                    if ( (LA118_0==20) ) {
                        alt118=1;
                    }
                    switch (alt118) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2304:6: otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) )
                            {
                            otherlv_14=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKArc5126); 

                                	newLeafNode(otherlv_14, grammarAccess.getKArcAccess().getChildPlacementKeyword_5_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2308:1: (otherlv_15= ':' )?
                            int alt117=2;
                            int LA117_0 = input.LA(1);

                            if ( (LA117_0==17) ) {
                                alt117=1;
                            }
                            switch (alt117) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2308:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKArc5139); 

                                        	newLeafNode(otherlv_15, grammarAccess.getKArcAccess().getColonKeyword_5_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2312:3: ( (lv_childPlacement_16_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2313:1: (lv_childPlacement_16_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2313:1: (lv_childPlacement_16_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2314:3: lv_childPlacement_16_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKArcAccess().getChildPlacementKPlacementParserRuleCall_5_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKArc5162);
                            lv_childPlacement_16_0=ruleKPlacement();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKArcRule());
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2330:4: (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )?
                    int alt122=2;
                    int LA122_0 = input.LA(1);

                    if ( (LA122_0==21) ) {
                        alt122=1;
                    }
                    switch (alt122) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2330:6: otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )*
                            {
                            otherlv_17=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKArc5177); 

                                	newLeafNode(otherlv_17, grammarAccess.getKArcAccess().getChildrenKeyword_5_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2334:1: (otherlv_18= ':' )?
                            int alt119=2;
                            int LA119_0 = input.LA(1);

                            if ( (LA119_0==17) ) {
                                alt119=1;
                            }
                            switch (alt119) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2334:3: otherlv_18= ':'
                                    {
                                    otherlv_18=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKArc5190); 

                                        	newLeafNode(otherlv_18, grammarAccess.getKArcAccess().getColonKeyword_5_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2338:3: ( (lv_children_19_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2339:1: (lv_children_19_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2339:1: (lv_children_19_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2340:3: lv_children_19_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKArcAccess().getChildrenKRenderingParserRuleCall_5_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKArc5213);
                            lv_children_19_0=ruleKRendering();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKArcRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"children",
                                    		lv_children_19_0, 
                                    		"KRendering");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2356:2: ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )*
                            loop121:
                            do {
                                int alt121=2;
                                int LA121_0 = input.LA(1);

                                if ( (LA121_0==13||LA121_0==15||LA121_0==19||(LA121_0>=22 && LA121_0<=27)||(LA121_0>=29 && LA121_0<=31)||LA121_0==34||LA121_0==37) ) {
                                    alt121=1;
                                }


                                switch (alt121) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2356:3: (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2356:3: (otherlv_20= ',' )?
                            	    int alt120=2;
                            	    int LA120_0 = input.LA(1);

                            	    if ( (LA120_0==13) ) {
                            	        alt120=1;
                            	    }
                            	    switch (alt120) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2356:5: otherlv_20= ','
                            	            {
                            	            otherlv_20=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKArc5227); 

                            	                	newLeafNode(otherlv_20, grammarAccess.getKArcAccess().getCommaKeyword_5_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2360:3: ( (lv_children_21_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2361:1: (lv_children_21_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2361:1: (lv_children_21_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2362:3: lv_children_21_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKArcAccess().getChildrenKRenderingParserRuleCall_5_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKArc5250);
                            	    lv_children_21_0=ruleKRendering();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKArcRule());
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
                            	    break loop121;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_22=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKArc5266); 

                        	newLeafNode(otherlv_22, grammarAccess.getKArcAccess().getRightCurlyBracketKeyword_5_5());
                        

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2390:1: entryRuleKChildArea returns [EObject current=null] : iv_ruleKChildArea= ruleKChildArea EOF ;
    public final EObject entryRuleKChildArea() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKChildArea = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2391:2: (iv_ruleKChildArea= ruleKChildArea EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2392:2: iv_ruleKChildArea= ruleKChildArea EOF
            {
             newCompositeNode(grammarAccess.getKChildAreaRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKChildArea_in_entryRuleKChildArea5304);
            iv_ruleKChildArea=ruleKChildArea();

            state._fsp--;

             current =iv_ruleKChildArea; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKChildArea5314); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2399:1: ruleKChildArea returns [EObject current=null] : ( () otherlv_1= 'ChildArea' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? otherlv_11= '}' )? ) ;
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
        EObject lv_styles_5_0 = null;

        EObject lv_styles_7_0 = null;

        EObject lv_placementData_10_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2402:28: ( ( () otherlv_1= 'ChildArea' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? otherlv_11= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2403:1: ( () otherlv_1= 'ChildArea' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? otherlv_11= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2403:1: ( () otherlv_1= 'ChildArea' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? otherlv_11= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2403:2: () otherlv_1= 'ChildArea' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? otherlv_11= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2403:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2404:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKChildAreaAccess().getKChildAreaAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,30,FollowSets000.FOLLOW_30_in_ruleKChildArea5360); 

                	newLeafNode(otherlv_1, grammarAccess.getKChildAreaAccess().getChildAreaKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2413:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? otherlv_11= '}' )?
            int alt130=2;
            int LA130_0 = input.LA(1);

            if ( (LA130_0==12) ) {
                alt130=1;
            }
            switch (alt130) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2413:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? otherlv_11= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKChildArea5373); 

                        	newLeafNode(otherlv_2, grammarAccess.getKChildAreaAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2417:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt127=2;
                    int LA127_0 = input.LA(1);

                    if ( (LA127_0==16) ) {
                        alt127=1;
                    }
                    switch (alt127) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2417:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKChildArea5386); 

                                	newLeafNode(otherlv_3, grammarAccess.getKChildAreaAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2421:1: (otherlv_4= ':' )?
                            int alt124=2;
                            int LA124_0 = input.LA(1);

                            if ( (LA124_0==17) ) {
                                alt124=1;
                            }
                            switch (alt124) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2421:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKChildArea5399); 

                                        	newLeafNode(otherlv_4, grammarAccess.getKChildAreaAccess().getColonKeyword_2_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2425:3: ( (lv_styles_5_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2426:1: (lv_styles_5_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2426:1: (lv_styles_5_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2427:3: lv_styles_5_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKChildAreaAccess().getStylesKStyleParserRuleCall_2_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKChildArea5422);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2443:2: ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            loop126:
                            do {
                                int alt126=2;
                                int LA126_0 = input.LA(1);

                                if ( (LA126_0==13||LA126_0==64||(LA126_0>=66 && LA126_0<=77)) ) {
                                    alt126=1;
                                }


                                switch (alt126) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2443:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2443:3: (otherlv_6= ',' )?
                            	    int alt125=2;
                            	    int LA125_0 = input.LA(1);

                            	    if ( (LA125_0==13) ) {
                            	        alt125=1;
                            	    }
                            	    switch (alt125) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2443:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKChildArea5436); 

                            	                	newLeafNode(otherlv_6, grammarAccess.getKChildAreaAccess().getCommaKeyword_2_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2447:3: ( (lv_styles_7_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2448:1: (lv_styles_7_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2448:1: (lv_styles_7_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2449:3: lv_styles_7_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKChildAreaAccess().getStylesKStyleParserRuleCall_2_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKChildArea5459);
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
                            	    break loop126;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2465:6: (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
                    int alt129=2;
                    int LA129_0 = input.LA(1);

                    if ( (LA129_0==18) ) {
                        alt129=1;
                    }
                    switch (alt129) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2465:8: otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKChildArea5476); 

                                	newLeafNode(otherlv_8, grammarAccess.getKChildAreaAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2469:1: (otherlv_9= ':' )?
                            int alt128=2;
                            int LA128_0 = input.LA(1);

                            if ( (LA128_0==17) ) {
                                alt128=1;
                            }
                            switch (alt128) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2469:3: otherlv_9= ':'
                                    {
                                    otherlv_9=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKChildArea5489); 

                                        	newLeafNode(otherlv_9, grammarAccess.getKChildAreaAccess().getColonKeyword_2_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2473:3: ( (lv_placementData_10_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2474:1: (lv_placementData_10_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2474:1: (lv_placementData_10_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2475:3: lv_placementData_10_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKChildAreaAccess().getPlacementDataKPlacementDataParserRuleCall_2_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKChildArea5512);
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

                    otherlv_11=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKChildArea5526); 

                        	newLeafNode(otherlv_11, grammarAccess.getKChildAreaAccess().getRightCurlyBracketKeyword_2_3());
                        

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2503:1: entryRuleKText returns [EObject current=null] : iv_ruleKText= ruleKText EOF ;
    public final EObject entryRuleKText() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKText = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2504:2: (iv_ruleKText= ruleKText EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2505:2: iv_ruleKText= ruleKText EOF
            {
             newCompositeNode(grammarAccess.getKTextRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKText_in_entryRuleKText5564);
            iv_ruleKText=ruleKText();

            state._fsp--;

             current =iv_ruleKText; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKText5574); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2512:1: ruleKText returns [EObject current=null] : ( () otherlv_1= 'Text' ( (lv_text_2_0= ruleEString ) )? (otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' (otherlv_11= ':' )? ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'childPlacement' (otherlv_14= ':' )? ( (lv_childPlacement_15_0= ruleKPlacement ) ) )? (otherlv_16= 'children' (otherlv_17= ':' )? ( (lv_children_18_0= ruleKRendering ) ) ( (otherlv_19= ',' )? ( (lv_children_20_0= ruleKRendering ) ) )* )? (otherlv_21= 'mapProperties' (otherlv_22= ':' )? ( (lv_persistentEntries_23_0= rulePersistentEntry ) ) ( (otherlv_24= ',' )? ( (lv_persistentEntries_25_0= rulePersistentEntry ) ) )* )? otherlv_26= '}' )? ) ;
    public final EObject ruleKText() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token lv_clip_4_0=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Token otherlv_21=null;
        Token otherlv_22=null;
        Token otherlv_24=null;
        Token otherlv_26=null;
        AntlrDatatypeRuleToken lv_text_2_0 = null;

        EObject lv_styles_7_0 = null;

        EObject lv_styles_9_0 = null;

        EObject lv_placementData_12_0 = null;

        EObject lv_childPlacement_15_0 = null;

        EObject lv_children_18_0 = null;

        EObject lv_children_20_0 = null;

        EObject lv_persistentEntries_23_0 = null;

        EObject lv_persistentEntries_25_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2515:28: ( ( () otherlv_1= 'Text' ( (lv_text_2_0= ruleEString ) )? (otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' (otherlv_11= ':' )? ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'childPlacement' (otherlv_14= ':' )? ( (lv_childPlacement_15_0= ruleKPlacement ) ) )? (otherlv_16= 'children' (otherlv_17= ':' )? ( (lv_children_18_0= ruleKRendering ) ) ( (otherlv_19= ',' )? ( (lv_children_20_0= ruleKRendering ) ) )* )? (otherlv_21= 'mapProperties' (otherlv_22= ':' )? ( (lv_persistentEntries_23_0= rulePersistentEntry ) ) ( (otherlv_24= ',' )? ( (lv_persistentEntries_25_0= rulePersistentEntry ) ) )* )? otherlv_26= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2516:1: ( () otherlv_1= 'Text' ( (lv_text_2_0= ruleEString ) )? (otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' (otherlv_11= ':' )? ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'childPlacement' (otherlv_14= ':' )? ( (lv_childPlacement_15_0= ruleKPlacement ) ) )? (otherlv_16= 'children' (otherlv_17= ':' )? ( (lv_children_18_0= ruleKRendering ) ) ( (otherlv_19= ',' )? ( (lv_children_20_0= ruleKRendering ) ) )* )? (otherlv_21= 'mapProperties' (otherlv_22= ':' )? ( (lv_persistentEntries_23_0= rulePersistentEntry ) ) ( (otherlv_24= ',' )? ( (lv_persistentEntries_25_0= rulePersistentEntry ) ) )* )? otherlv_26= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2516:1: ( () otherlv_1= 'Text' ( (lv_text_2_0= ruleEString ) )? (otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' (otherlv_11= ':' )? ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'childPlacement' (otherlv_14= ':' )? ( (lv_childPlacement_15_0= ruleKPlacement ) ) )? (otherlv_16= 'children' (otherlv_17= ':' )? ( (lv_children_18_0= ruleKRendering ) ) ( (otherlv_19= ',' )? ( (lv_children_20_0= ruleKRendering ) ) )* )? (otherlv_21= 'mapProperties' (otherlv_22= ':' )? ( (lv_persistentEntries_23_0= rulePersistentEntry ) ) ( (otherlv_24= ',' )? ( (lv_persistentEntries_25_0= rulePersistentEntry ) ) )* )? otherlv_26= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2516:2: () otherlv_1= 'Text' ( (lv_text_2_0= ruleEString ) )? (otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' (otherlv_11= ':' )? ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'childPlacement' (otherlv_14= ':' )? ( (lv_childPlacement_15_0= ruleKPlacement ) ) )? (otherlv_16= 'children' (otherlv_17= ':' )? ( (lv_children_18_0= ruleKRendering ) ) ( (otherlv_19= ',' )? ( (lv_children_20_0= ruleKRendering ) ) )* )? (otherlv_21= 'mapProperties' (otherlv_22= ':' )? ( (lv_persistentEntries_23_0= rulePersistentEntry ) ) ( (otherlv_24= ',' )? ( (lv_persistentEntries_25_0= rulePersistentEntry ) ) )* )? otherlv_26= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2516:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2517:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKTextAccess().getKTextAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,31,FollowSets000.FOLLOW_31_in_ruleKText5620); 

                	newLeafNode(otherlv_1, grammarAccess.getKTextAccess().getTextKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2526:1: ( (lv_text_2_0= ruleEString ) )?
            int alt131=2;
            int LA131_0 = input.LA(1);

            if ( ((LA131_0>=RULE_STRING && LA131_0<=RULE_ID)) ) {
                alt131=1;
            }
            switch (alt131) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2527:1: (lv_text_2_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2527:1: (lv_text_2_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2528:3: lv_text_2_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getTextEStringParserRuleCall_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKText5641);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2544:3: (otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' (otherlv_11= ':' )? ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'childPlacement' (otherlv_14= ':' )? ( (lv_childPlacement_15_0= ruleKPlacement ) ) )? (otherlv_16= 'children' (otherlv_17= ':' )? ( (lv_children_18_0= ruleKRendering ) ) ( (otherlv_19= ',' )? ( (lv_children_20_0= ruleKRendering ) ) )* )? (otherlv_21= 'mapProperties' (otherlv_22= ':' )? ( (lv_persistentEntries_23_0= rulePersistentEntry ) ) ( (otherlv_24= ',' )? ( (lv_persistentEntries_25_0= rulePersistentEntry ) ) )* )? otherlv_26= '}' )?
            int alt149=2;
            int LA149_0 = input.LA(1);

            if ( (LA149_0==12) ) {
                alt149=1;
            }
            switch (alt149) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2544:5: otherlv_3= '{' ( (lv_clip_4_0= 'clip' ) )? (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )? (otherlv_10= 'placementData' (otherlv_11= ':' )? ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'childPlacement' (otherlv_14= ':' )? ( (lv_childPlacement_15_0= ruleKPlacement ) ) )? (otherlv_16= 'children' (otherlv_17= ':' )? ( (lv_children_18_0= ruleKRendering ) ) ( (otherlv_19= ',' )? ( (lv_children_20_0= ruleKRendering ) ) )* )? (otherlv_21= 'mapProperties' (otherlv_22= ':' )? ( (lv_persistentEntries_23_0= rulePersistentEntry ) ) ( (otherlv_24= ',' )? ( (lv_persistentEntries_25_0= rulePersistentEntry ) ) )* )? otherlv_26= '}'
                    {
                    otherlv_3=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKText5655); 

                        	newLeafNode(otherlv_3, grammarAccess.getKTextAccess().getLeftCurlyBracketKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2548:1: ( (lv_clip_4_0= 'clip' ) )?
                    int alt132=2;
                    int LA132_0 = input.LA(1);

                    if ( (LA132_0==32) ) {
                        alt132=1;
                    }
                    switch (alt132) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2549:1: (lv_clip_4_0= 'clip' )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2549:1: (lv_clip_4_0= 'clip' )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2550:3: lv_clip_4_0= 'clip'
                            {
                            lv_clip_4_0=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKText5673); 

                                    newLeafNode(lv_clip_4_0, grammarAccess.getKTextAccess().getClipClipKeyword_3_1_0());
                                

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getKTextRule());
                            	        }
                                   		setWithLastConsumed(current, "clip", true, "clip");
                            	    

                            }


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2563:3: (otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )* )?
                    int alt136=2;
                    int LA136_0 = input.LA(1);

                    if ( (LA136_0==16) ) {
                        alt136=1;
                    }
                    switch (alt136) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2563:5: otherlv_5= 'styles' (otherlv_6= ':' )? ( (lv_styles_7_0= ruleKStyle ) ) ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )*
                            {
                            otherlv_5=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKText5700); 

                                	newLeafNode(otherlv_5, grammarAccess.getKTextAccess().getStylesKeyword_3_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2567:1: (otherlv_6= ':' )?
                            int alt133=2;
                            int LA133_0 = input.LA(1);

                            if ( (LA133_0==17) ) {
                                alt133=1;
                            }
                            switch (alt133) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2567:3: otherlv_6= ':'
                                    {
                                    otherlv_6=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKText5713); 

                                        	newLeafNode(otherlv_6, grammarAccess.getKTextAccess().getColonKeyword_3_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2571:3: ( (lv_styles_7_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2572:1: (lv_styles_7_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2572:1: (lv_styles_7_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2573:3: lv_styles_7_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKTextAccess().getStylesKStyleParserRuleCall_3_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKText5736);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2589:2: ( (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) ) )*
                            loop135:
                            do {
                                int alt135=2;
                                int LA135_0 = input.LA(1);

                                if ( (LA135_0==13||LA135_0==64||(LA135_0>=66 && LA135_0<=77)) ) {
                                    alt135=1;
                                }


                                switch (alt135) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2589:3: (otherlv_8= ',' )? ( (lv_styles_9_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2589:3: (otherlv_8= ',' )?
                            	    int alt134=2;
                            	    int LA134_0 = input.LA(1);

                            	    if ( (LA134_0==13) ) {
                            	        alt134=1;
                            	    }
                            	    switch (alt134) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2589:5: otherlv_8= ','
                            	            {
                            	            otherlv_8=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKText5750); 

                            	                	newLeafNode(otherlv_8, grammarAccess.getKTextAccess().getCommaKeyword_3_2_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2593:3: ( (lv_styles_9_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2594:1: (lv_styles_9_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2594:1: (lv_styles_9_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2595:3: lv_styles_9_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKTextAccess().getStylesKStyleParserRuleCall_3_2_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKText5773);
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
                            	    break loop135;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2611:6: (otherlv_10= 'placementData' (otherlv_11= ':' )? ( (lv_placementData_12_0= ruleKPlacementData ) ) )?
                    int alt138=2;
                    int LA138_0 = input.LA(1);

                    if ( (LA138_0==18) ) {
                        alt138=1;
                    }
                    switch (alt138) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2611:8: otherlv_10= 'placementData' (otherlv_11= ':' )? ( (lv_placementData_12_0= ruleKPlacementData ) )
                            {
                            otherlv_10=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKText5790); 

                                	newLeafNode(otherlv_10, grammarAccess.getKTextAccess().getPlacementDataKeyword_3_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2615:1: (otherlv_11= ':' )?
                            int alt137=2;
                            int LA137_0 = input.LA(1);

                            if ( (LA137_0==17) ) {
                                alt137=1;
                            }
                            switch (alt137) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2615:3: otherlv_11= ':'
                                    {
                                    otherlv_11=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKText5803); 

                                        	newLeafNode(otherlv_11, grammarAccess.getKTextAccess().getColonKeyword_3_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2619:3: ( (lv_placementData_12_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2620:1: (lv_placementData_12_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2620:1: (lv_placementData_12_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2621:3: lv_placementData_12_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKTextAccess().getPlacementDataKPlacementDataParserRuleCall_3_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKText5826);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2637:4: (otherlv_13= 'childPlacement' (otherlv_14= ':' )? ( (lv_childPlacement_15_0= ruleKPlacement ) ) )?
                    int alt140=2;
                    int LA140_0 = input.LA(1);

                    if ( (LA140_0==20) ) {
                        alt140=1;
                    }
                    switch (alt140) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2637:6: otherlv_13= 'childPlacement' (otherlv_14= ':' )? ( (lv_childPlacement_15_0= ruleKPlacement ) )
                            {
                            otherlv_13=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKText5841); 

                                	newLeafNode(otherlv_13, grammarAccess.getKTextAccess().getChildPlacementKeyword_3_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2641:1: (otherlv_14= ':' )?
                            int alt139=2;
                            int LA139_0 = input.LA(1);

                            if ( (LA139_0==17) ) {
                                alt139=1;
                            }
                            switch (alt139) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2641:3: otherlv_14= ':'
                                    {
                                    otherlv_14=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKText5854); 

                                        	newLeafNode(otherlv_14, grammarAccess.getKTextAccess().getColonKeyword_3_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2645:3: ( (lv_childPlacement_15_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2646:1: (lv_childPlacement_15_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2646:1: (lv_childPlacement_15_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2647:3: lv_childPlacement_15_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKTextAccess().getChildPlacementKPlacementParserRuleCall_3_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKText5877);
                            lv_childPlacement_15_0=ruleKPlacement();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKTextRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"childPlacement",
                                    		lv_childPlacement_15_0, 
                                    		"KPlacement");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2663:4: (otherlv_16= 'children' (otherlv_17= ':' )? ( (lv_children_18_0= ruleKRendering ) ) ( (otherlv_19= ',' )? ( (lv_children_20_0= ruleKRendering ) ) )* )?
                    int alt144=2;
                    int LA144_0 = input.LA(1);

                    if ( (LA144_0==21) ) {
                        alt144=1;
                    }
                    switch (alt144) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2663:6: otherlv_16= 'children' (otherlv_17= ':' )? ( (lv_children_18_0= ruleKRendering ) ) ( (otherlv_19= ',' )? ( (lv_children_20_0= ruleKRendering ) ) )*
                            {
                            otherlv_16=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKText5892); 

                                	newLeafNode(otherlv_16, grammarAccess.getKTextAccess().getChildrenKeyword_3_5_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2667:1: (otherlv_17= ':' )?
                            int alt141=2;
                            int LA141_0 = input.LA(1);

                            if ( (LA141_0==17) ) {
                                alt141=1;
                            }
                            switch (alt141) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2667:3: otherlv_17= ':'
                                    {
                                    otherlv_17=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKText5905); 

                                        	newLeafNode(otherlv_17, grammarAccess.getKTextAccess().getColonKeyword_3_5_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2671:3: ( (lv_children_18_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2672:1: (lv_children_18_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2672:1: (lv_children_18_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2673:3: lv_children_18_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKTextAccess().getChildrenKRenderingParserRuleCall_3_5_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKText5928);
                            lv_children_18_0=ruleKRendering();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKTextRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"children",
                                    		lv_children_18_0, 
                                    		"KRendering");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2689:2: ( (otherlv_19= ',' )? ( (lv_children_20_0= ruleKRendering ) ) )*
                            loop143:
                            do {
                                int alt143=2;
                                int LA143_0 = input.LA(1);

                                if ( (LA143_0==13||LA143_0==15||LA143_0==19||(LA143_0>=22 && LA143_0<=27)||(LA143_0>=29 && LA143_0<=31)||LA143_0==34||LA143_0==37) ) {
                                    alt143=1;
                                }


                                switch (alt143) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2689:3: (otherlv_19= ',' )? ( (lv_children_20_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2689:3: (otherlv_19= ',' )?
                            	    int alt142=2;
                            	    int LA142_0 = input.LA(1);

                            	    if ( (LA142_0==13) ) {
                            	        alt142=1;
                            	    }
                            	    switch (alt142) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2689:5: otherlv_19= ','
                            	            {
                            	            otherlv_19=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKText5942); 

                            	                	newLeafNode(otherlv_19, grammarAccess.getKTextAccess().getCommaKeyword_3_5_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2693:3: ( (lv_children_20_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2694:1: (lv_children_20_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2694:1: (lv_children_20_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2695:3: lv_children_20_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKTextAccess().getChildrenKRenderingParserRuleCall_3_5_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKText5965);
                            	    lv_children_20_0=ruleKRendering();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKTextRule());
                            	    	        }
                            	           		add(
                            	           			current, 
                            	           			"children",
                            	            		lv_children_20_0, 
                            	            		"KRendering");
                            	    	        afterParserOrEnumRuleCall();
                            	    	    

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop143;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2711:6: (otherlv_21= 'mapProperties' (otherlv_22= ':' )? ( (lv_persistentEntries_23_0= rulePersistentEntry ) ) ( (otherlv_24= ',' )? ( (lv_persistentEntries_25_0= rulePersistentEntry ) ) )* )?
                    int alt148=2;
                    int LA148_0 = input.LA(1);

                    if ( (LA148_0==33) ) {
                        alt148=1;
                    }
                    switch (alt148) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2711:8: otherlv_21= 'mapProperties' (otherlv_22= ':' )? ( (lv_persistentEntries_23_0= rulePersistentEntry ) ) ( (otherlv_24= ',' )? ( (lv_persistentEntries_25_0= rulePersistentEntry ) ) )*
                            {
                            otherlv_21=(Token)match(input,33,FollowSets000.FOLLOW_33_in_ruleKText5982); 

                                	newLeafNode(otherlv_21, grammarAccess.getKTextAccess().getMapPropertiesKeyword_3_6_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2715:1: (otherlv_22= ':' )?
                            int alt145=2;
                            int LA145_0 = input.LA(1);

                            if ( (LA145_0==17) ) {
                                alt145=1;
                            }
                            switch (alt145) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2715:3: otherlv_22= ':'
                                    {
                                    otherlv_22=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKText5995); 

                                        	newLeafNode(otherlv_22, grammarAccess.getKTextAccess().getColonKeyword_3_6_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2719:3: ( (lv_persistentEntries_23_0= rulePersistentEntry ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2720:1: (lv_persistentEntries_23_0= rulePersistentEntry )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2720:1: (lv_persistentEntries_23_0= rulePersistentEntry )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2721:3: lv_persistentEntries_23_0= rulePersistentEntry
                            {
                             
                            	        newCompositeNode(grammarAccess.getKTextAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_6_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_ruleKText6018);
                            lv_persistentEntries_23_0=rulePersistentEntry();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKTextRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"persistentEntries",
                                    		lv_persistentEntries_23_0, 
                                    		"PersistentEntry");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2737:2: ( (otherlv_24= ',' )? ( (lv_persistentEntries_25_0= rulePersistentEntry ) ) )*
                            loop147:
                            do {
                                int alt147=2;
                                int LA147_0 = input.LA(1);

                                if ( ((LA147_0>=RULE_STRING && LA147_0<=RULE_ID)||LA147_0==13) ) {
                                    alt147=1;
                                }


                                switch (alt147) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2737:3: (otherlv_24= ',' )? ( (lv_persistentEntries_25_0= rulePersistentEntry ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2737:3: (otherlv_24= ',' )?
                            	    int alt146=2;
                            	    int LA146_0 = input.LA(1);

                            	    if ( (LA146_0==13) ) {
                            	        alt146=1;
                            	    }
                            	    switch (alt146) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2737:5: otherlv_24= ','
                            	            {
                            	            otherlv_24=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKText6032); 

                            	                	newLeafNode(otherlv_24, grammarAccess.getKTextAccess().getCommaKeyword_3_6_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2741:3: ( (lv_persistentEntries_25_0= rulePersistentEntry ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2742:1: (lv_persistentEntries_25_0= rulePersistentEntry )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2742:1: (lv_persistentEntries_25_0= rulePersistentEntry )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2743:3: lv_persistentEntries_25_0= rulePersistentEntry
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKTextAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_6_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_ruleKText6055);
                            	    lv_persistentEntries_25_0=rulePersistentEntry();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKTextRule());
                            	    	        }
                            	           		add(
                            	           			current, 
                            	           			"persistentEntries",
                            	            		lv_persistentEntries_25_0, 
                            	            		"PersistentEntry");
                            	    	        afterParserOrEnumRuleCall();
                            	    	    

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop147;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_26=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKText6071); 

                        	newLeafNode(otherlv_26, grammarAccess.getKTextAccess().getRightCurlyBracketKeyword_3_7());
                        

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2771:1: entryRuleKCustomRendering returns [EObject current=null] : iv_ruleKCustomRendering= ruleKCustomRendering EOF ;
    public final EObject entryRuleKCustomRendering() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKCustomRendering = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2772:2: (iv_ruleKCustomRendering= ruleKCustomRendering EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2773:2: iv_ruleKCustomRendering= ruleKCustomRendering EOF
            {
             newCompositeNode(grammarAccess.getKCustomRenderingRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKCustomRendering_in_entryRuleKCustomRendering6109);
            iv_ruleKCustomRendering=ruleKCustomRendering();

            state._fsp--;

             current =iv_ruleKCustomRendering; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKCustomRendering6119); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2780:1: ruleKCustomRendering returns [EObject current=null] : ( () otherlv_1= 'CustomRendering' (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' (otherlv_13= ':' )? ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'childPlacement' (otherlv_16= ':' )? ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? (otherlv_18= 'children' (otherlv_19= ':' )? ( (lv_children_20_0= ruleKRendering ) ) ( (otherlv_21= ',' )? ( (lv_children_22_0= ruleKRendering ) ) )* )? otherlv_23= '}' )? ) ;
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
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        Token otherlv_21=null;
        Token otherlv_23=null;
        AntlrDatatypeRuleToken lv_className_4_0 = null;

        AntlrDatatypeRuleToken lv_bundleName_6_0 = null;

        EObject lv_styles_9_0 = null;

        EObject lv_styles_11_0 = null;

        EObject lv_placementData_14_0 = null;

        EObject lv_childPlacement_17_0 = null;

        EObject lv_children_20_0 = null;

        EObject lv_children_22_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2783:28: ( ( () otherlv_1= 'CustomRendering' (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' (otherlv_13= ':' )? ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'childPlacement' (otherlv_16= ':' )? ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? (otherlv_18= 'children' (otherlv_19= ':' )? ( (lv_children_20_0= ruleKRendering ) ) ( (otherlv_21= ',' )? ( (lv_children_22_0= ruleKRendering ) ) )* )? otherlv_23= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2784:1: ( () otherlv_1= 'CustomRendering' (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' (otherlv_13= ':' )? ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'childPlacement' (otherlv_16= ':' )? ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? (otherlv_18= 'children' (otherlv_19= ':' )? ( (lv_children_20_0= ruleKRendering ) ) ( (otherlv_21= ',' )? ( (lv_children_22_0= ruleKRendering ) ) )* )? otherlv_23= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2784:1: ( () otherlv_1= 'CustomRendering' (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' (otherlv_13= ':' )? ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'childPlacement' (otherlv_16= ':' )? ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? (otherlv_18= 'children' (otherlv_19= ':' )? ( (lv_children_20_0= ruleKRendering ) ) ( (otherlv_21= ',' )? ( (lv_children_22_0= ruleKRendering ) ) )* )? otherlv_23= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2784:2: () otherlv_1= 'CustomRendering' (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' (otherlv_13= ':' )? ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'childPlacement' (otherlv_16= ':' )? ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? (otherlv_18= 'children' (otherlv_19= ':' )? ( (lv_children_20_0= ruleKRendering ) ) ( (otherlv_21= ',' )? ( (lv_children_22_0= ruleKRendering ) ) )* )? otherlv_23= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2784:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2785:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKCustomRenderingAccess().getKCustomRenderingAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,34,FollowSets000.FOLLOW_34_in_ruleKCustomRendering6165); 

                	newLeafNode(otherlv_1, grammarAccess.getKCustomRenderingAccess().getCustomRenderingKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2794:1: (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' (otherlv_13= ':' )? ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'childPlacement' (otherlv_16= ':' )? ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? (otherlv_18= 'children' (otherlv_19= ':' )? ( (lv_children_20_0= ruleKRendering ) ) ( (otherlv_21= ',' )? ( (lv_children_22_0= ruleKRendering ) ) )* )? otherlv_23= '}' )?
            int alt162=2;
            int LA162_0 = input.LA(1);

            if ( (LA162_0==12) ) {
                alt162=1;
            }
            switch (alt162) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2794:3: otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' (otherlv_13= ':' )? ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'childPlacement' (otherlv_16= ':' )? ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? (otherlv_18= 'children' (otherlv_19= ':' )? ( (lv_children_20_0= ruleKRendering ) ) ( (otherlv_21= ',' )? ( (lv_children_22_0= ruleKRendering ) ) )* )? otherlv_23= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKCustomRendering6178); 

                        	newLeafNode(otherlv_2, grammarAccess.getKCustomRenderingAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    otherlv_3=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKCustomRendering6190); 

                        	newLeafNode(otherlv_3, grammarAccess.getKCustomRenderingAccess().getClassNameKeyword_2_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2802:1: ( (lv_className_4_0= ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2803:1: (lv_className_4_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2803:1: (lv_className_4_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2804:3: lv_className_4_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getClassNameEStringParserRuleCall_2_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKCustomRendering6211);
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

                    otherlv_5=(Token)match(input,36,FollowSets000.FOLLOW_36_in_ruleKCustomRendering6223); 

                        	newLeafNode(otherlv_5, grammarAccess.getKCustomRenderingAccess().getBundleNameKeyword_2_3());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2824:1: ( (lv_bundleName_6_0= ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2825:1: (lv_bundleName_6_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2825:1: (lv_bundleName_6_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2826:3: lv_bundleName_6_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getBundleNameEStringParserRuleCall_2_4_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKCustomRendering6244);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2842:2: (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )?
                    int alt153=2;
                    int LA153_0 = input.LA(1);

                    if ( (LA153_0==16) ) {
                        alt153=1;
                    }
                    switch (alt153) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2842:4: otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )*
                            {
                            otherlv_7=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKCustomRendering6257); 

                                	newLeafNode(otherlv_7, grammarAccess.getKCustomRenderingAccess().getStylesKeyword_2_5_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2846:1: (otherlv_8= ':' )?
                            int alt150=2;
                            int LA150_0 = input.LA(1);

                            if ( (LA150_0==17) ) {
                                alt150=1;
                            }
                            switch (alt150) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2846:3: otherlv_8= ':'
                                    {
                                    otherlv_8=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKCustomRendering6270); 

                                        	newLeafNode(otherlv_8, grammarAccess.getKCustomRenderingAccess().getColonKeyword_2_5_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2850:3: ( (lv_styles_9_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2851:1: (lv_styles_9_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2851:1: (lv_styles_9_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2852:3: lv_styles_9_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getStylesKStyleParserRuleCall_2_5_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKCustomRendering6293);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2868:2: ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )*
                            loop152:
                            do {
                                int alt152=2;
                                int LA152_0 = input.LA(1);

                                if ( (LA152_0==13||LA152_0==64||(LA152_0>=66 && LA152_0<=77)) ) {
                                    alt152=1;
                                }


                                switch (alt152) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2868:3: (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2868:3: (otherlv_10= ',' )?
                            	    int alt151=2;
                            	    int LA151_0 = input.LA(1);

                            	    if ( (LA151_0==13) ) {
                            	        alt151=1;
                            	    }
                            	    switch (alt151) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2868:5: otherlv_10= ','
                            	            {
                            	            otherlv_10=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKCustomRendering6307); 

                            	                	newLeafNode(otherlv_10, grammarAccess.getKCustomRenderingAccess().getCommaKeyword_2_5_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2872:3: ( (lv_styles_11_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2873:1: (lv_styles_11_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2873:1: (lv_styles_11_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2874:3: lv_styles_11_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getStylesKStyleParserRuleCall_2_5_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKCustomRendering6330);
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
                            	    break loop152;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2890:6: (otherlv_12= 'placementData' (otherlv_13= ':' )? ( (lv_placementData_14_0= ruleKPlacementData ) ) )?
                    int alt155=2;
                    int LA155_0 = input.LA(1);

                    if ( (LA155_0==18) ) {
                        alt155=1;
                    }
                    switch (alt155) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2890:8: otherlv_12= 'placementData' (otherlv_13= ':' )? ( (lv_placementData_14_0= ruleKPlacementData ) )
                            {
                            otherlv_12=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKCustomRendering6347); 

                                	newLeafNode(otherlv_12, grammarAccess.getKCustomRenderingAccess().getPlacementDataKeyword_2_6_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2894:1: (otherlv_13= ':' )?
                            int alt154=2;
                            int LA154_0 = input.LA(1);

                            if ( (LA154_0==17) ) {
                                alt154=1;
                            }
                            switch (alt154) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2894:3: otherlv_13= ':'
                                    {
                                    otherlv_13=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKCustomRendering6360); 

                                        	newLeafNode(otherlv_13, grammarAccess.getKCustomRenderingAccess().getColonKeyword_2_6_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2898:3: ( (lv_placementData_14_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2899:1: (lv_placementData_14_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2899:1: (lv_placementData_14_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2900:3: lv_placementData_14_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getPlacementDataKPlacementDataParserRuleCall_2_6_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKCustomRendering6383);
                            lv_placementData_14_0=ruleKPlacementData();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKCustomRenderingRule());
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2916:4: (otherlv_15= 'childPlacement' (otherlv_16= ':' )? ( (lv_childPlacement_17_0= ruleKPlacement ) ) )?
                    int alt157=2;
                    int LA157_0 = input.LA(1);

                    if ( (LA157_0==20) ) {
                        alt157=1;
                    }
                    switch (alt157) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2916:6: otherlv_15= 'childPlacement' (otherlv_16= ':' )? ( (lv_childPlacement_17_0= ruleKPlacement ) )
                            {
                            otherlv_15=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKCustomRendering6398); 

                                	newLeafNode(otherlv_15, grammarAccess.getKCustomRenderingAccess().getChildPlacementKeyword_2_7_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2920:1: (otherlv_16= ':' )?
                            int alt156=2;
                            int LA156_0 = input.LA(1);

                            if ( (LA156_0==17) ) {
                                alt156=1;
                            }
                            switch (alt156) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2920:3: otherlv_16= ':'
                                    {
                                    otherlv_16=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKCustomRendering6411); 

                                        	newLeafNode(otherlv_16, grammarAccess.getKCustomRenderingAccess().getColonKeyword_2_7_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2924:3: ( (lv_childPlacement_17_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2925:1: (lv_childPlacement_17_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2925:1: (lv_childPlacement_17_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2926:3: lv_childPlacement_17_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getChildPlacementKPlacementParserRuleCall_2_7_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKCustomRendering6434);
                            lv_childPlacement_17_0=ruleKPlacement();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKCustomRenderingRule());
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2942:4: (otherlv_18= 'children' (otherlv_19= ':' )? ( (lv_children_20_0= ruleKRendering ) ) ( (otherlv_21= ',' )? ( (lv_children_22_0= ruleKRendering ) ) )* )?
                    int alt161=2;
                    int LA161_0 = input.LA(1);

                    if ( (LA161_0==21) ) {
                        alt161=1;
                    }
                    switch (alt161) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2942:6: otherlv_18= 'children' (otherlv_19= ':' )? ( (lv_children_20_0= ruleKRendering ) ) ( (otherlv_21= ',' )? ( (lv_children_22_0= ruleKRendering ) ) )*
                            {
                            otherlv_18=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKCustomRendering6449); 

                                	newLeafNode(otherlv_18, grammarAccess.getKCustomRenderingAccess().getChildrenKeyword_2_8_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2946:1: (otherlv_19= ':' )?
                            int alt158=2;
                            int LA158_0 = input.LA(1);

                            if ( (LA158_0==17) ) {
                                alt158=1;
                            }
                            switch (alt158) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2946:3: otherlv_19= ':'
                                    {
                                    otherlv_19=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKCustomRendering6462); 

                                        	newLeafNode(otherlv_19, grammarAccess.getKCustomRenderingAccess().getColonKeyword_2_8_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2950:3: ( (lv_children_20_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2951:1: (lv_children_20_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2951:1: (lv_children_20_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2952:3: lv_children_20_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getChildrenKRenderingParserRuleCall_2_8_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKCustomRendering6485);
                            lv_children_20_0=ruleKRendering();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKCustomRenderingRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"children",
                                    		lv_children_20_0, 
                                    		"KRendering");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2968:2: ( (otherlv_21= ',' )? ( (lv_children_22_0= ruleKRendering ) ) )*
                            loop160:
                            do {
                                int alt160=2;
                                int LA160_0 = input.LA(1);

                                if ( (LA160_0==13||LA160_0==15||LA160_0==19||(LA160_0>=22 && LA160_0<=27)||(LA160_0>=29 && LA160_0<=31)||LA160_0==34||LA160_0==37) ) {
                                    alt160=1;
                                }


                                switch (alt160) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2968:3: (otherlv_21= ',' )? ( (lv_children_22_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2968:3: (otherlv_21= ',' )?
                            	    int alt159=2;
                            	    int LA159_0 = input.LA(1);

                            	    if ( (LA159_0==13) ) {
                            	        alt159=1;
                            	    }
                            	    switch (alt159) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2968:5: otherlv_21= ','
                            	            {
                            	            otherlv_21=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKCustomRendering6499); 

                            	                	newLeafNode(otherlv_21, grammarAccess.getKCustomRenderingAccess().getCommaKeyword_2_8_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2972:3: ( (lv_children_22_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2973:1: (lv_children_22_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2973:1: (lv_children_22_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2974:3: lv_children_22_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getChildrenKRenderingParserRuleCall_2_8_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKCustomRendering6522);
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


                            	    }
                            	    break;

                            	default :
                            	    break loop160;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_23=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKCustomRendering6538); 

                        	newLeafNode(otherlv_23, grammarAccess.getKCustomRenderingAccess().getRightCurlyBracketKeyword_2_9());
                        

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3002:1: entryRuleKSpline returns [EObject current=null] : iv_ruleKSpline= ruleKSpline EOF ;
    public final EObject entryRuleKSpline() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKSpline = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3003:2: (iv_ruleKSpline= ruleKSpline EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3004:2: iv_ruleKSpline= ruleKSpline EOF
            {
             newCompositeNode(grammarAccess.getKSplineRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKSpline_in_entryRuleKSpline6576);
            iv_ruleKSpline=ruleKSpline();

            state._fsp--;

             current =iv_ruleKSpline; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKSpline6586); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3011:1: ruleKSpline returns [EObject current=null] : ( () otherlv_1= 'Spline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3014:28: ( ( () otherlv_1= 'Spline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3015:1: ( () otherlv_1= 'Spline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3015:1: ( () otherlv_1= 'Spline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3015:2: () otherlv_1= 'Spline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3015:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3016:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKSplineAccess().getKSplineAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,37,FollowSets000.FOLLOW_37_in_ruleKSpline6632); 

                	newLeafNode(otherlv_1, grammarAccess.getKSplineAccess().getSplineKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3025:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            int alt175=2;
            int LA175_0 = input.LA(1);

            if ( (LA175_0==12) ) {
                alt175=1;
            }
            switch (alt175) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3025:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKSpline6645); 

                        	newLeafNode(otherlv_2, grammarAccess.getKSplineAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3029:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt166=2;
                    int LA166_0 = input.LA(1);

                    if ( (LA166_0==16) ) {
                        alt166=1;
                    }
                    switch (alt166) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3029:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKSpline6658); 

                                	newLeafNode(otherlv_3, grammarAccess.getKSplineAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3033:1: (otherlv_4= ':' )?
                            int alt163=2;
                            int LA163_0 = input.LA(1);

                            if ( (LA163_0==17) ) {
                                alt163=1;
                            }
                            switch (alt163) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3033:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKSpline6671); 

                                        	newLeafNode(otherlv_4, grammarAccess.getKSplineAccess().getColonKeyword_2_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3037:3: ( (lv_styles_5_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3038:1: (lv_styles_5_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3038:1: (lv_styles_5_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3039:3: lv_styles_5_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKSplineAccess().getStylesKStyleParserRuleCall_2_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKSpline6694);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3055:2: ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            loop165:
                            do {
                                int alt165=2;
                                int LA165_0 = input.LA(1);

                                if ( (LA165_0==13||LA165_0==64||(LA165_0>=66 && LA165_0<=77)) ) {
                                    alt165=1;
                                }


                                switch (alt165) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3055:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3055:3: (otherlv_6= ',' )?
                            	    int alt164=2;
                            	    int LA164_0 = input.LA(1);

                            	    if ( (LA164_0==13) ) {
                            	        alt164=1;
                            	    }
                            	    switch (alt164) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3055:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKSpline6708); 

                            	                	newLeafNode(otherlv_6, grammarAccess.getKSplineAccess().getCommaKeyword_2_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3059:3: ( (lv_styles_7_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3060:1: (lv_styles_7_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3060:1: (lv_styles_7_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3061:3: lv_styles_7_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKSplineAccess().getStylesKStyleParserRuleCall_2_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKSpline6731);
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
                            	    break loop165;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3077:6: (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
                    int alt168=2;
                    int LA168_0 = input.LA(1);

                    if ( (LA168_0==18) ) {
                        alt168=1;
                    }
                    switch (alt168) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3077:8: otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKSpline6748); 

                                	newLeafNode(otherlv_8, grammarAccess.getKSplineAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3081:1: (otherlv_9= ':' )?
                            int alt167=2;
                            int LA167_0 = input.LA(1);

                            if ( (LA167_0==17) ) {
                                alt167=1;
                            }
                            switch (alt167) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3081:3: otherlv_9= ':'
                                    {
                                    otherlv_9=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKSpline6761); 

                                        	newLeafNode(otherlv_9, grammarAccess.getKSplineAccess().getColonKeyword_2_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3085:3: ( (lv_placementData_10_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3086:1: (lv_placementData_10_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3086:1: (lv_placementData_10_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3087:3: lv_placementData_10_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKSplineAccess().getPlacementDataKPlacementDataParserRuleCall_2_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKSpline6784);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3103:4: (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )?
                    int alt170=2;
                    int LA170_0 = input.LA(1);

                    if ( (LA170_0==20) ) {
                        alt170=1;
                    }
                    switch (alt170) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3103:6: otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            {
                            otherlv_11=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKSpline6799); 

                                	newLeafNode(otherlv_11, grammarAccess.getKSplineAccess().getChildPlacementKeyword_2_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3107:1: (otherlv_12= ':' )?
                            int alt169=2;
                            int LA169_0 = input.LA(1);

                            if ( (LA169_0==17) ) {
                                alt169=1;
                            }
                            switch (alt169) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3107:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKSpline6812); 

                                        	newLeafNode(otherlv_12, grammarAccess.getKSplineAccess().getColonKeyword_2_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3111:3: ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3112:1: (lv_childPlacement_13_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3112:1: (lv_childPlacement_13_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3113:3: lv_childPlacement_13_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKSplineAccess().getChildPlacementKPlacementParserRuleCall_2_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKSpline6835);
                            lv_childPlacement_13_0=ruleKPlacement();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKSplineRule());
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3129:4: (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )?
                    int alt174=2;
                    int LA174_0 = input.LA(1);

                    if ( (LA174_0==21) ) {
                        alt174=1;
                    }
                    switch (alt174) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3129:6: otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            {
                            otherlv_14=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKSpline6850); 

                                	newLeafNode(otherlv_14, grammarAccess.getKSplineAccess().getChildrenKeyword_2_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3133:1: (otherlv_15= ':' )?
                            int alt171=2;
                            int LA171_0 = input.LA(1);

                            if ( (LA171_0==17) ) {
                                alt171=1;
                            }
                            switch (alt171) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3133:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKSpline6863); 

                                        	newLeafNode(otherlv_15, grammarAccess.getKSplineAccess().getColonKeyword_2_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3137:3: ( (lv_children_16_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3138:1: (lv_children_16_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3138:1: (lv_children_16_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3139:3: lv_children_16_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKSplineAccess().getChildrenKRenderingParserRuleCall_2_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKSpline6886);
                            lv_children_16_0=ruleKRendering();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKSplineRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"children",
                                    		lv_children_16_0, 
                                    		"KRendering");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3155:2: ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            loop173:
                            do {
                                int alt173=2;
                                int LA173_0 = input.LA(1);

                                if ( (LA173_0==13||LA173_0==15||LA173_0==19||(LA173_0>=22 && LA173_0<=27)||(LA173_0>=29 && LA173_0<=31)||LA173_0==34||LA173_0==37) ) {
                                    alt173=1;
                                }


                                switch (alt173) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3155:3: (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3155:3: (otherlv_17= ',' )?
                            	    int alt172=2;
                            	    int LA172_0 = input.LA(1);

                            	    if ( (LA172_0==13) ) {
                            	        alt172=1;
                            	    }
                            	    switch (alt172) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3155:5: otherlv_17= ','
                            	            {
                            	            otherlv_17=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKSpline6900); 

                            	                	newLeafNode(otherlv_17, grammarAccess.getKSplineAccess().getCommaKeyword_2_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3159:3: ( (lv_children_18_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3160:1: (lv_children_18_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3160:1: (lv_children_18_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3161:3: lv_children_18_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKSplineAccess().getChildrenKRenderingParserRuleCall_2_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKSpline6923);
                            	    lv_children_18_0=ruleKRendering();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKSplineRule());
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
                            	    break loop173;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_19=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKSpline6939); 

                        	newLeafNode(otherlv_19, grammarAccess.getKSplineAccess().getRightCurlyBracketKeyword_2_5());
                        

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3189:1: entryRuleKDecoratorPlacementData returns [EObject current=null] : iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF ;
    public final EObject entryRuleKDecoratorPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKDecoratorPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3190:2: (iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3191:2: iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKDecoratorPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKDecoratorPlacementData_in_entryRuleKDecoratorPlacementData6977);
            iv_ruleKDecoratorPlacementData=ruleKDecoratorPlacementData();

            state._fsp--;

             current =iv_ruleKDecoratorPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKDecoratorPlacementData6987); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3198:1: ruleKDecoratorPlacementData returns [EObject current=null] : (otherlv_0= 'DecoratorPlacementData' otherlv_1= '{' ( (lv_relative_2_0= 'relative' ) )? otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )? (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )? otherlv_13= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3201:28: ( (otherlv_0= 'DecoratorPlacementData' otherlv_1= '{' ( (lv_relative_2_0= 'relative' ) )? otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )? (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )? otherlv_13= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3202:1: (otherlv_0= 'DecoratorPlacementData' otherlv_1= '{' ( (lv_relative_2_0= 'relative' ) )? otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )? (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )? otherlv_13= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3202:1: (otherlv_0= 'DecoratorPlacementData' otherlv_1= '{' ( (lv_relative_2_0= 'relative' ) )? otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )? (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )? otherlv_13= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3202:3: otherlv_0= 'DecoratorPlacementData' otherlv_1= '{' ( (lv_relative_2_0= 'relative' ) )? otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )? (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )? otherlv_13= '}'
            {
            otherlv_0=(Token)match(input,38,FollowSets000.FOLLOW_38_in_ruleKDecoratorPlacementData7024); 

                	newLeafNode(otherlv_0, grammarAccess.getKDecoratorPlacementDataAccess().getDecoratorPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKDecoratorPlacementData7036); 

                	newLeafNode(otherlv_1, grammarAccess.getKDecoratorPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3210:1: ( (lv_relative_2_0= 'relative' ) )?
            int alt176=2;
            int LA176_0 = input.LA(1);

            if ( (LA176_0==39) ) {
                alt176=1;
            }
            switch (alt176) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3211:1: (lv_relative_2_0= 'relative' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3211:1: (lv_relative_2_0= 'relative' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3212:3: lv_relative_2_0= 'relative'
                    {
                    lv_relative_2_0=(Token)match(input,39,FollowSets000.FOLLOW_39_in_ruleKDecoratorPlacementData7054); 

                            newLeafNode(lv_relative_2_0, grammarAccess.getKDecoratorPlacementDataAccess().getRelativeRelativeKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKDecoratorPlacementDataRule());
                    	        }
                           		setWithLastConsumed(current, "relative", true, "relative");
                    	    

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,40,FollowSets000.FOLLOW_40_in_ruleKDecoratorPlacementData7080); 

                	newLeafNode(otherlv_3, grammarAccess.getKDecoratorPlacementDataAccess().getLocationKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3229:1: ( (lv_location_4_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3230:1: (lv_location_4_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3230:1: (lv_location_4_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3231:3: lv_location_4_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getLocationEFloatParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7101);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3247:2: (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )?
            int alt177=2;
            int LA177_0 = input.LA(1);

            if ( (LA177_0==41) ) {
                alt177=1;
            }
            switch (alt177) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3247:4: otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,41,FollowSets000.FOLLOW_41_in_ruleKDecoratorPlacementData7114); 

                        	newLeafNode(otherlv_5, grammarAccess.getKDecoratorPlacementDataAccess().getXOffsetKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3251:1: ( (lv_xOffset_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3252:1: (lv_xOffset_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3252:1: (lv_xOffset_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3253:3: lv_xOffset_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getXOffsetEFloatParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7135);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3269:4: (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )?
            int alt178=2;
            int LA178_0 = input.LA(1);

            if ( (LA178_0==42) ) {
                alt178=1;
            }
            switch (alt178) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3269:6: otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,42,FollowSets000.FOLLOW_42_in_ruleKDecoratorPlacementData7150); 

                        	newLeafNode(otherlv_7, grammarAccess.getKDecoratorPlacementDataAccess().getYOffsetKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3273:1: ( (lv_yOffset_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3274:1: (lv_yOffset_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3274:1: (lv_yOffset_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3275:3: lv_yOffset_8_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getYOffsetEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7171);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3291:4: (otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) ) )?
            int alt179=2;
            int LA179_0 = input.LA(1);

            if ( (LA179_0==43) ) {
                alt179=1;
            }
            switch (alt179) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3291:6: otherlv_9= 'width' ( (lv_width_10_0= ruleEFloat ) )
                    {
                    otherlv_9=(Token)match(input,43,FollowSets000.FOLLOW_43_in_ruleKDecoratorPlacementData7186); 

                        	newLeafNode(otherlv_9, grammarAccess.getKDecoratorPlacementDataAccess().getWidthKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3295:1: ( (lv_width_10_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3296:1: (lv_width_10_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3296:1: (lv_width_10_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3297:3: lv_width_10_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getWidthEFloatParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7207);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3313:4: (otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) ) )?
            int alt180=2;
            int LA180_0 = input.LA(1);

            if ( (LA180_0==44) ) {
                alt180=1;
            }
            switch (alt180) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3313:6: otherlv_11= 'height' ( (lv_height_12_0= ruleEFloat ) )
                    {
                    otherlv_11=(Token)match(input,44,FollowSets000.FOLLOW_44_in_ruleKDecoratorPlacementData7222); 

                        	newLeafNode(otherlv_11, grammarAccess.getKDecoratorPlacementDataAccess().getHeightKeyword_8_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3317:1: ( (lv_height_12_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3318:1: (lv_height_12_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3318:1: (lv_height_12_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3319:3: lv_height_12_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getHeightEFloatParserRuleCall_8_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7243);
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

            otherlv_13=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKDecoratorPlacementData7257); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3347:1: entryRuleKGridPlacementData returns [EObject current=null] : iv_ruleKGridPlacementData= ruleKGridPlacementData EOF ;
    public final EObject entryRuleKGridPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKGridPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3348:2: (iv_ruleKGridPlacementData= ruleKGridPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3349:2: iv_ruleKGridPlacementData= ruleKGridPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKGridPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKGridPlacementData_in_entryRuleKGridPlacementData7293);
            iv_ruleKGridPlacementData=ruleKGridPlacementData();

            state._fsp--;

             current =iv_ruleKGridPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKGridPlacementData7303); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3356:1: ruleKGridPlacementData returns [EObject current=null] : ( () otherlv_1= 'GridPlacementData' otherlv_2= '{' (otherlv_3= 'widthHint' ( (lv_widthHint_4_0= ruleEFloat ) ) )? (otherlv_5= 'heightHint' ( (lv_heightHint_6_0= ruleEFloat ) ) )? (otherlv_7= 'insetRight' ( (lv_insetRight_8_0= ruleEFloat ) ) )? (otherlv_9= 'insetBottom' ( (lv_insetBottom_10_0= ruleEFloat ) ) )? (otherlv_11= 'insetLeft' ( (lv_insetLeft_12_0= ruleEFloat ) ) )? (otherlv_13= 'insetTop' ( (lv_insetTop_14_0= ruleEFloat ) ) )? otherlv_15= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3359:28: ( ( () otherlv_1= 'GridPlacementData' otherlv_2= '{' (otherlv_3= 'widthHint' ( (lv_widthHint_4_0= ruleEFloat ) ) )? (otherlv_5= 'heightHint' ( (lv_heightHint_6_0= ruleEFloat ) ) )? (otherlv_7= 'insetRight' ( (lv_insetRight_8_0= ruleEFloat ) ) )? (otherlv_9= 'insetBottom' ( (lv_insetBottom_10_0= ruleEFloat ) ) )? (otherlv_11= 'insetLeft' ( (lv_insetLeft_12_0= ruleEFloat ) ) )? (otherlv_13= 'insetTop' ( (lv_insetTop_14_0= ruleEFloat ) ) )? otherlv_15= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3360:1: ( () otherlv_1= 'GridPlacementData' otherlv_2= '{' (otherlv_3= 'widthHint' ( (lv_widthHint_4_0= ruleEFloat ) ) )? (otherlv_5= 'heightHint' ( (lv_heightHint_6_0= ruleEFloat ) ) )? (otherlv_7= 'insetRight' ( (lv_insetRight_8_0= ruleEFloat ) ) )? (otherlv_9= 'insetBottom' ( (lv_insetBottom_10_0= ruleEFloat ) ) )? (otherlv_11= 'insetLeft' ( (lv_insetLeft_12_0= ruleEFloat ) ) )? (otherlv_13= 'insetTop' ( (lv_insetTop_14_0= ruleEFloat ) ) )? otherlv_15= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3360:1: ( () otherlv_1= 'GridPlacementData' otherlv_2= '{' (otherlv_3= 'widthHint' ( (lv_widthHint_4_0= ruleEFloat ) ) )? (otherlv_5= 'heightHint' ( (lv_heightHint_6_0= ruleEFloat ) ) )? (otherlv_7= 'insetRight' ( (lv_insetRight_8_0= ruleEFloat ) ) )? (otherlv_9= 'insetBottom' ( (lv_insetBottom_10_0= ruleEFloat ) ) )? (otherlv_11= 'insetLeft' ( (lv_insetLeft_12_0= ruleEFloat ) ) )? (otherlv_13= 'insetTop' ( (lv_insetTop_14_0= ruleEFloat ) ) )? otherlv_15= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3360:2: () otherlv_1= 'GridPlacementData' otherlv_2= '{' (otherlv_3= 'widthHint' ( (lv_widthHint_4_0= ruleEFloat ) ) )? (otherlv_5= 'heightHint' ( (lv_heightHint_6_0= ruleEFloat ) ) )? (otherlv_7= 'insetRight' ( (lv_insetRight_8_0= ruleEFloat ) ) )? (otherlv_9= 'insetBottom' ( (lv_insetBottom_10_0= ruleEFloat ) ) )? (otherlv_11= 'insetLeft' ( (lv_insetLeft_12_0= ruleEFloat ) ) )? (otherlv_13= 'insetTop' ( (lv_insetTop_14_0= ruleEFloat ) ) )? otherlv_15= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3360:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3361:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKGridPlacementDataAccess().getKGridPlacementDataAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,45,FollowSets000.FOLLOW_45_in_ruleKGridPlacementData7349); 

                	newLeafNode(otherlv_1, grammarAccess.getKGridPlacementDataAccess().getGridPlacementDataKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKGridPlacementData7361); 

                	newLeafNode(otherlv_2, grammarAccess.getKGridPlacementDataAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3374:1: (otherlv_3= 'widthHint' ( (lv_widthHint_4_0= ruleEFloat ) ) )?
            int alt181=2;
            int LA181_0 = input.LA(1);

            if ( (LA181_0==46) ) {
                alt181=1;
            }
            switch (alt181) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3374:3: otherlv_3= 'widthHint' ( (lv_widthHint_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,46,FollowSets000.FOLLOW_46_in_ruleKGridPlacementData7374); 

                        	newLeafNode(otherlv_3, grammarAccess.getKGridPlacementDataAccess().getWidthHintKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3378:1: ( (lv_widthHint_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3379:1: (lv_widthHint_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3379:1: (lv_widthHint_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3380:3: lv_widthHint_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getWidthHintEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData7395);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3396:4: (otherlv_5= 'heightHint' ( (lv_heightHint_6_0= ruleEFloat ) ) )?
            int alt182=2;
            int LA182_0 = input.LA(1);

            if ( (LA182_0==47) ) {
                alt182=1;
            }
            switch (alt182) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3396:6: otherlv_5= 'heightHint' ( (lv_heightHint_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,47,FollowSets000.FOLLOW_47_in_ruleKGridPlacementData7410); 

                        	newLeafNode(otherlv_5, grammarAccess.getKGridPlacementDataAccess().getHeightHintKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3400:1: ( (lv_heightHint_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3401:1: (lv_heightHint_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3401:1: (lv_heightHint_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3402:3: lv_heightHint_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getHeightHintEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData7431);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3418:4: (otherlv_7= 'insetRight' ( (lv_insetRight_8_0= ruleEFloat ) ) )?
            int alt183=2;
            int LA183_0 = input.LA(1);

            if ( (LA183_0==48) ) {
                alt183=1;
            }
            switch (alt183) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3418:6: otherlv_7= 'insetRight' ( (lv_insetRight_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,48,FollowSets000.FOLLOW_48_in_ruleKGridPlacementData7446); 

                        	newLeafNode(otherlv_7, grammarAccess.getKGridPlacementDataAccess().getInsetRightKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3422:1: ( (lv_insetRight_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3423:1: (lv_insetRight_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3423:1: (lv_insetRight_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3424:3: lv_insetRight_8_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getInsetRightEFloatParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData7467);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3440:4: (otherlv_9= 'insetBottom' ( (lv_insetBottom_10_0= ruleEFloat ) ) )?
            int alt184=2;
            int LA184_0 = input.LA(1);

            if ( (LA184_0==49) ) {
                alt184=1;
            }
            switch (alt184) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3440:6: otherlv_9= 'insetBottom' ( (lv_insetBottom_10_0= ruleEFloat ) )
                    {
                    otherlv_9=(Token)match(input,49,FollowSets000.FOLLOW_49_in_ruleKGridPlacementData7482); 

                        	newLeafNode(otherlv_9, grammarAccess.getKGridPlacementDataAccess().getInsetBottomKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3444:1: ( (lv_insetBottom_10_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3445:1: (lv_insetBottom_10_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3445:1: (lv_insetBottom_10_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3446:3: lv_insetBottom_10_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getInsetBottomEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData7503);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3462:4: (otherlv_11= 'insetLeft' ( (lv_insetLeft_12_0= ruleEFloat ) ) )?
            int alt185=2;
            int LA185_0 = input.LA(1);

            if ( (LA185_0==50) ) {
                alt185=1;
            }
            switch (alt185) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3462:6: otherlv_11= 'insetLeft' ( (lv_insetLeft_12_0= ruleEFloat ) )
                    {
                    otherlv_11=(Token)match(input,50,FollowSets000.FOLLOW_50_in_ruleKGridPlacementData7518); 

                        	newLeafNode(otherlv_11, grammarAccess.getKGridPlacementDataAccess().getInsetLeftKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3466:1: ( (lv_insetLeft_12_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3467:1: (lv_insetLeft_12_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3467:1: (lv_insetLeft_12_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3468:3: lv_insetLeft_12_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getInsetLeftEFloatParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData7539);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3484:4: (otherlv_13= 'insetTop' ( (lv_insetTop_14_0= ruleEFloat ) ) )?
            int alt186=2;
            int LA186_0 = input.LA(1);

            if ( (LA186_0==51) ) {
                alt186=1;
            }
            switch (alt186) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3484:6: otherlv_13= 'insetTop' ( (lv_insetTop_14_0= ruleEFloat ) )
                    {
                    otherlv_13=(Token)match(input,51,FollowSets000.FOLLOW_51_in_ruleKGridPlacementData7554); 

                        	newLeafNode(otherlv_13, grammarAccess.getKGridPlacementDataAccess().getInsetTopKeyword_8_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3488:1: ( (lv_insetTop_14_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3489:1: (lv_insetTop_14_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3489:1: (lv_insetTop_14_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3490:3: lv_insetTop_14_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getInsetTopEFloatParserRuleCall_8_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData7575);
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

            otherlv_15=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKGridPlacementData7589); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3518:1: entryRuleKStackPlacementData returns [EObject current=null] : iv_ruleKStackPlacementData= ruleKStackPlacementData EOF ;
    public final EObject entryRuleKStackPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStackPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3519:2: (iv_ruleKStackPlacementData= ruleKStackPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3520:2: iv_ruleKStackPlacementData= ruleKStackPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKStackPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKStackPlacementData_in_entryRuleKStackPlacementData7625);
            iv_ruleKStackPlacementData=ruleKStackPlacementData();

            state._fsp--;

             current =iv_ruleKStackPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKStackPlacementData7635); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3527:1: ruleKStackPlacementData returns [EObject current=null] : ( () otherlv_1= 'StackPlacementData' otherlv_2= '{' (otherlv_3= 'insetRight' ( (lv_insetRight_4_0= ruleEFloat ) ) )? (otherlv_5= 'insetBottom' ( (lv_insetBottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'insetLeft' ( (lv_insetLeft_8_0= ruleEFloat ) ) )? (otherlv_9= 'insetTop' ( (lv_insetTop_10_0= ruleEFloat ) ) )? otherlv_11= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3530:28: ( ( () otherlv_1= 'StackPlacementData' otherlv_2= '{' (otherlv_3= 'insetRight' ( (lv_insetRight_4_0= ruleEFloat ) ) )? (otherlv_5= 'insetBottom' ( (lv_insetBottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'insetLeft' ( (lv_insetLeft_8_0= ruleEFloat ) ) )? (otherlv_9= 'insetTop' ( (lv_insetTop_10_0= ruleEFloat ) ) )? otherlv_11= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3531:1: ( () otherlv_1= 'StackPlacementData' otherlv_2= '{' (otherlv_3= 'insetRight' ( (lv_insetRight_4_0= ruleEFloat ) ) )? (otherlv_5= 'insetBottom' ( (lv_insetBottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'insetLeft' ( (lv_insetLeft_8_0= ruleEFloat ) ) )? (otherlv_9= 'insetTop' ( (lv_insetTop_10_0= ruleEFloat ) ) )? otherlv_11= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3531:1: ( () otherlv_1= 'StackPlacementData' otherlv_2= '{' (otherlv_3= 'insetRight' ( (lv_insetRight_4_0= ruleEFloat ) ) )? (otherlv_5= 'insetBottom' ( (lv_insetBottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'insetLeft' ( (lv_insetLeft_8_0= ruleEFloat ) ) )? (otherlv_9= 'insetTop' ( (lv_insetTop_10_0= ruleEFloat ) ) )? otherlv_11= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3531:2: () otherlv_1= 'StackPlacementData' otherlv_2= '{' (otherlv_3= 'insetRight' ( (lv_insetRight_4_0= ruleEFloat ) ) )? (otherlv_5= 'insetBottom' ( (lv_insetBottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'insetLeft' ( (lv_insetLeft_8_0= ruleEFloat ) ) )? (otherlv_9= 'insetTop' ( (lv_insetTop_10_0= ruleEFloat ) ) )? otherlv_11= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3531:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3532:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKStackPlacementDataAccess().getKStackPlacementDataAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,52,FollowSets000.FOLLOW_52_in_ruleKStackPlacementData7681); 

                	newLeafNode(otherlv_1, grammarAccess.getKStackPlacementDataAccess().getStackPlacementDataKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKStackPlacementData7693); 

                	newLeafNode(otherlv_2, grammarAccess.getKStackPlacementDataAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3545:1: (otherlv_3= 'insetRight' ( (lv_insetRight_4_0= ruleEFloat ) ) )?
            int alt187=2;
            int LA187_0 = input.LA(1);

            if ( (LA187_0==48) ) {
                alt187=1;
            }
            switch (alt187) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3545:3: otherlv_3= 'insetRight' ( (lv_insetRight_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,48,FollowSets000.FOLLOW_48_in_ruleKStackPlacementData7706); 

                        	newLeafNode(otherlv_3, grammarAccess.getKStackPlacementDataAccess().getInsetRightKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3549:1: ( (lv_insetRight_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3550:1: (lv_insetRight_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3550:1: (lv_insetRight_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3551:3: lv_insetRight_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetRightEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData7727);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3567:4: (otherlv_5= 'insetBottom' ( (lv_insetBottom_6_0= ruleEFloat ) ) )?
            int alt188=2;
            int LA188_0 = input.LA(1);

            if ( (LA188_0==49) ) {
                alt188=1;
            }
            switch (alt188) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3567:6: otherlv_5= 'insetBottom' ( (lv_insetBottom_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,49,FollowSets000.FOLLOW_49_in_ruleKStackPlacementData7742); 

                        	newLeafNode(otherlv_5, grammarAccess.getKStackPlacementDataAccess().getInsetBottomKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3571:1: ( (lv_insetBottom_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3572:1: (lv_insetBottom_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3572:1: (lv_insetBottom_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3573:3: lv_insetBottom_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetBottomEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData7763);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3589:4: (otherlv_7= 'insetLeft' ( (lv_insetLeft_8_0= ruleEFloat ) ) )?
            int alt189=2;
            int LA189_0 = input.LA(1);

            if ( (LA189_0==50) ) {
                alt189=1;
            }
            switch (alt189) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3589:6: otherlv_7= 'insetLeft' ( (lv_insetLeft_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,50,FollowSets000.FOLLOW_50_in_ruleKStackPlacementData7778); 

                        	newLeafNode(otherlv_7, grammarAccess.getKStackPlacementDataAccess().getInsetLeftKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3593:1: ( (lv_insetLeft_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3594:1: (lv_insetLeft_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3594:1: (lv_insetLeft_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3595:3: lv_insetLeft_8_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetLeftEFloatParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData7799);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3611:4: (otherlv_9= 'insetTop' ( (lv_insetTop_10_0= ruleEFloat ) ) )?
            int alt190=2;
            int LA190_0 = input.LA(1);

            if ( (LA190_0==51) ) {
                alt190=1;
            }
            switch (alt190) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3611:6: otherlv_9= 'insetTop' ( (lv_insetTop_10_0= ruleEFloat ) )
                    {
                    otherlv_9=(Token)match(input,51,FollowSets000.FOLLOW_51_in_ruleKStackPlacementData7814); 

                        	newLeafNode(otherlv_9, grammarAccess.getKStackPlacementDataAccess().getInsetTopKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3615:1: ( (lv_insetTop_10_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3616:1: (lv_insetTop_10_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3616:1: (lv_insetTop_10_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3617:3: lv_insetTop_10_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetTopEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData7835);
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

            otherlv_11=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKStackPlacementData7849); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3645:1: entryRuleKDirectPlacementData returns [EObject current=null] : iv_ruleKDirectPlacementData= ruleKDirectPlacementData EOF ;
    public final EObject entryRuleKDirectPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKDirectPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3646:2: (iv_ruleKDirectPlacementData= ruleKDirectPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3647:2: iv_ruleKDirectPlacementData= ruleKDirectPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKDirectPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKDirectPlacementData_in_entryRuleKDirectPlacementData7885);
            iv_ruleKDirectPlacementData=ruleKDirectPlacementData();

            state._fsp--;

             current =iv_ruleKDirectPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKDirectPlacementData7895); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3654:1: ruleKDirectPlacementData returns [EObject current=null] : (otherlv_0= 'DirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'bottomRight' ( (lv_bottomRight_6_0= ruleKPosition ) ) otherlv_7= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3657:28: ( (otherlv_0= 'DirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'bottomRight' ( (lv_bottomRight_6_0= ruleKPosition ) ) otherlv_7= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3658:1: (otherlv_0= 'DirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'bottomRight' ( (lv_bottomRight_6_0= ruleKPosition ) ) otherlv_7= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3658:1: (otherlv_0= 'DirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'bottomRight' ( (lv_bottomRight_6_0= ruleKPosition ) ) otherlv_7= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3658:3: otherlv_0= 'DirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'bottomRight' ( (lv_bottomRight_6_0= ruleKPosition ) ) otherlv_7= '}'
            {
            otherlv_0=(Token)match(input,53,FollowSets000.FOLLOW_53_in_ruleKDirectPlacementData7932); 

                	newLeafNode(otherlv_0, grammarAccess.getKDirectPlacementDataAccess().getDirectPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKDirectPlacementData7944); 

                	newLeafNode(otherlv_1, grammarAccess.getKDirectPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,54,FollowSets000.FOLLOW_54_in_ruleKDirectPlacementData7956); 

                	newLeafNode(otherlv_2, grammarAccess.getKDirectPlacementDataAccess().getTopLeftKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3670:1: ( (lv_topLeft_3_0= ruleKPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3671:1: (lv_topLeft_3_0= ruleKPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3671:1: (lv_topLeft_3_0= ruleKPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3672:3: lv_topLeft_3_0= ruleKPosition
            {
             
            	        newCompositeNode(grammarAccess.getKDirectPlacementDataAccess().getTopLeftKPositionParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKDirectPlacementData7977);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3688:2: (otherlv_4= ',' )?
            int alt191=2;
            int LA191_0 = input.LA(1);

            if ( (LA191_0==13) ) {
                alt191=1;
            }
            switch (alt191) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3688:4: otherlv_4= ','
                    {
                    otherlv_4=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKDirectPlacementData7990); 

                        	newLeafNode(otherlv_4, grammarAccess.getKDirectPlacementDataAccess().getCommaKeyword_4());
                        

                    }
                    break;

            }

            otherlv_5=(Token)match(input,55,FollowSets000.FOLLOW_55_in_ruleKDirectPlacementData8004); 

                	newLeafNode(otherlv_5, grammarAccess.getKDirectPlacementDataAccess().getBottomRightKeyword_5());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3696:1: ( (lv_bottomRight_6_0= ruleKPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3697:1: (lv_bottomRight_6_0= ruleKPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3697:1: (lv_bottomRight_6_0= ruleKPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3698:3: lv_bottomRight_6_0= ruleKPosition
            {
             
            	        newCompositeNode(grammarAccess.getKDirectPlacementDataAccess().getBottomRightKPositionParserRuleCall_6_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKDirectPlacementData8025);
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

            otherlv_7=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKDirectPlacementData8037); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3726:1: entryRuleKPolylinePlacementData returns [EObject current=null] : iv_ruleKPolylinePlacementData= ruleKPolylinePlacementData EOF ;
    public final EObject entryRuleKPolylinePlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolylinePlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3727:2: (iv_ruleKPolylinePlacementData= ruleKPolylinePlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3728:2: iv_ruleKPolylinePlacementData= ruleKPolylinePlacementData EOF
            {
             newCompositeNode(grammarAccess.getKPolylinePlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPolylinePlacementData_in_entryRuleKPolylinePlacementData8073);
            iv_ruleKPolylinePlacementData=ruleKPolylinePlacementData();

            state._fsp--;

             current =iv_ruleKPolylinePlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPolylinePlacementData8083); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3735:1: ruleKPolylinePlacementData returns [EObject current=null] : (otherlv_0= 'PolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' (otherlv_3= ':' )? ( (lv_points_4_0= ruleKPosition ) ) ( (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) ) )* (otherlv_7= 'detailedPlacementData' (otherlv_8= ':' )? ( (lv_detailPlacementData_9_0= ruleKPlacementData ) ) )? otherlv_10= '}' ) ;
    public final EObject ruleKPolylinePlacementData() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        EObject lv_points_4_0 = null;

        EObject lv_points_6_0 = null;

        EObject lv_detailPlacementData_9_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3738:28: ( (otherlv_0= 'PolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' (otherlv_3= ':' )? ( (lv_points_4_0= ruleKPosition ) ) ( (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) ) )* (otherlv_7= 'detailedPlacementData' (otherlv_8= ':' )? ( (lv_detailPlacementData_9_0= ruleKPlacementData ) ) )? otherlv_10= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3739:1: (otherlv_0= 'PolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' (otherlv_3= ':' )? ( (lv_points_4_0= ruleKPosition ) ) ( (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) ) )* (otherlv_7= 'detailedPlacementData' (otherlv_8= ':' )? ( (lv_detailPlacementData_9_0= ruleKPlacementData ) ) )? otherlv_10= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3739:1: (otherlv_0= 'PolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' (otherlv_3= ':' )? ( (lv_points_4_0= ruleKPosition ) ) ( (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) ) )* (otherlv_7= 'detailedPlacementData' (otherlv_8= ':' )? ( (lv_detailPlacementData_9_0= ruleKPlacementData ) ) )? otherlv_10= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3739:3: otherlv_0= 'PolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' (otherlv_3= ':' )? ( (lv_points_4_0= ruleKPosition ) ) ( (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) ) )* (otherlv_7= 'detailedPlacementData' (otherlv_8= ':' )? ( (lv_detailPlacementData_9_0= ruleKPlacementData ) ) )? otherlv_10= '}'
            {
            otherlv_0=(Token)match(input,56,FollowSets000.FOLLOW_56_in_ruleKPolylinePlacementData8120); 

                	newLeafNode(otherlv_0, grammarAccess.getKPolylinePlacementDataAccess().getPolylinePlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolylinePlacementData8132); 

                	newLeafNode(otherlv_1, grammarAccess.getKPolylinePlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,57,FollowSets000.FOLLOW_57_in_ruleKPolylinePlacementData8144); 

                	newLeafNode(otherlv_2, grammarAccess.getKPolylinePlacementDataAccess().getPointsKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3751:1: (otherlv_3= ':' )?
            int alt192=2;
            int LA192_0 = input.LA(1);

            if ( (LA192_0==17) ) {
                alt192=1;
            }
            switch (alt192) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3751:3: otherlv_3= ':'
                    {
                    otherlv_3=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolylinePlacementData8157); 

                        	newLeafNode(otherlv_3, grammarAccess.getKPolylinePlacementDataAccess().getColonKeyword_3());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3755:3: ( (lv_points_4_0= ruleKPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3756:1: (lv_points_4_0= ruleKPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3756:1: (lv_points_4_0= ruleKPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3757:3: lv_points_4_0= ruleKPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPolylinePlacementDataAccess().getPointsKPositionParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData8180);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3773:2: ( (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) ) )*
            loop194:
            do {
                int alt194=2;
                int LA194_0 = input.LA(1);

                if ( (LA194_0==13||(LA194_0>=60 && LA194_0<=61)) ) {
                    alt194=1;
                }


                switch (alt194) {
            	case 1 :
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3773:3: (otherlv_5= ',' )? ( (lv_points_6_0= ruleKPosition ) )
            	    {
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3773:3: (otherlv_5= ',' )?
            	    int alt193=2;
            	    int LA193_0 = input.LA(1);

            	    if ( (LA193_0==13) ) {
            	        alt193=1;
            	    }
            	    switch (alt193) {
            	        case 1 :
            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3773:5: otherlv_5= ','
            	            {
            	            otherlv_5=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPolylinePlacementData8194); 

            	                	newLeafNode(otherlv_5, grammarAccess.getKPolylinePlacementDataAccess().getCommaKeyword_5_0());
            	                

            	            }
            	            break;

            	    }

            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3777:3: ( (lv_points_6_0= ruleKPosition ) )
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3778:1: (lv_points_6_0= ruleKPosition )
            	    {
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3778:1: (lv_points_6_0= ruleKPosition )
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3779:3: lv_points_6_0= ruleKPosition
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKPolylinePlacementDataAccess().getPointsKPositionParserRuleCall_5_1_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData8217);
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
            	    break loop194;
                }
            } while (true);

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3795:4: (otherlv_7= 'detailedPlacementData' (otherlv_8= ':' )? ( (lv_detailPlacementData_9_0= ruleKPlacementData ) ) )?
            int alt196=2;
            int LA196_0 = input.LA(1);

            if ( (LA196_0==58) ) {
                alt196=1;
            }
            switch (alt196) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3795:6: otherlv_7= 'detailedPlacementData' (otherlv_8= ':' )? ( (lv_detailPlacementData_9_0= ruleKPlacementData ) )
                    {
                    otherlv_7=(Token)match(input,58,FollowSets000.FOLLOW_58_in_ruleKPolylinePlacementData8232); 

                        	newLeafNode(otherlv_7, grammarAccess.getKPolylinePlacementDataAccess().getDetailedPlacementDataKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3799:1: (otherlv_8= ':' )?
                    int alt195=2;
                    int LA195_0 = input.LA(1);

                    if ( (LA195_0==17) ) {
                        alt195=1;
                    }
                    switch (alt195) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3799:3: otherlv_8= ':'
                            {
                            otherlv_8=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolylinePlacementData8245); 

                                	newLeafNode(otherlv_8, grammarAccess.getKPolylinePlacementDataAccess().getColonKeyword_6_1());
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3803:3: ( (lv_detailPlacementData_9_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3804:1: (lv_detailPlacementData_9_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3804:1: (lv_detailPlacementData_9_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3805:3: lv_detailPlacementData_9_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolylinePlacementDataAccess().getDetailPlacementDataKPlacementDataParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKPolylinePlacementData8268);
                    lv_detailPlacementData_9_0=ruleKPlacementData();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKPolylinePlacementDataRule());
                    	        }
                           		set(
                           			current, 
                           			"detailPlacementData",
                            		lv_detailPlacementData_9_0, 
                            		"KPlacementData");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_10=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolylinePlacementData8282); 

                	newLeafNode(otherlv_10, grammarAccess.getKPolylinePlacementDataAccess().getRightCurlyBracketKeyword_7());
                

            }


            }

             leaveRule(); 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3833:1: entryRuleKPosition returns [EObject current=null] : iv_ruleKPosition= ruleKPosition EOF ;
    public final EObject entryRuleKPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3834:2: (iv_ruleKPosition= ruleKPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3835:2: iv_ruleKPosition= ruleKPosition EOF
            {
             newCompositeNode(grammarAccess.getKPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_entryRuleKPosition8318);
            iv_ruleKPosition=ruleKPosition();

            state._fsp--;

             current =iv_ruleKPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPosition8328); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3842:1: ruleKPosition returns [EObject current=null] : ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= '/' ( (lv_y_2_0= ruleKYPosition ) ) ) ;
    public final EObject ruleKPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_x_0_0 = null;

        EObject lv_y_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3845:28: ( ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= '/' ( (lv_y_2_0= ruleKYPosition ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3846:1: ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= '/' ( (lv_y_2_0= ruleKYPosition ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3846:1: ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= '/' ( (lv_y_2_0= ruleKYPosition ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3846:2: ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= '/' ( (lv_y_2_0= ruleKYPosition ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3846:2: ( (lv_x_0_0= ruleKXPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3847:1: (lv_x_0_0= ruleKXPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3847:1: (lv_x_0_0= ruleKXPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3848:3: lv_x_0_0= ruleKXPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPositionAccess().getXKXPositionParserRuleCall_0_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKXPosition_in_ruleKPosition8374);
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

            otherlv_1=(Token)match(input,59,FollowSets000.FOLLOW_59_in_ruleKPosition8386); 

                	newLeafNode(otherlv_1, grammarAccess.getKPositionAccess().getSolidusKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3868:1: ( (lv_y_2_0= ruleKYPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3869:1: (lv_y_2_0= ruleKYPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3869:1: (lv_y_2_0= ruleKYPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3870:3: lv_y_2_0= ruleKYPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPositionAccess().getYKYPositionParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKYPosition_in_ruleKPosition8407);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3894:1: entryRuleKLeftPosition returns [EObject current=null] : iv_ruleKLeftPosition= ruleKLeftPosition EOF ;
    public final EObject entryRuleKLeftPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLeftPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3895:2: (iv_ruleKLeftPosition= ruleKLeftPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3896:2: iv_ruleKLeftPosition= ruleKLeftPosition EOF
            {
             newCompositeNode(grammarAccess.getKLeftPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLeftPosition_in_entryRuleKLeftPosition8443);
            iv_ruleKLeftPosition=ruleKLeftPosition();

            state._fsp--;

             current =iv_ruleKLeftPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLeftPosition8453); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3903:1: ruleKLeftPosition returns [EObject current=null] : ( () otherlv_1= 'left' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) ;
    public final EObject ruleKLeftPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_absolute_2_0 = null;

        AntlrDatatypeRuleToken lv_relative_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3906:28: ( ( () otherlv_1= 'left' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3907:1: ( () otherlv_1= 'left' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3907:1: ( () otherlv_1= 'left' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3907:2: () otherlv_1= 'left' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3907:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3908:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKLeftPositionAccess().getKLeftPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,60,FollowSets000.FOLLOW_60_in_ruleKLeftPosition8499); 

                	newLeafNode(otherlv_1, grammarAccess.getKLeftPositionAccess().getLeftKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3917:1: ( (lv_absolute_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3918:1: (lv_absolute_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3918:1: (lv_absolute_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3919:3: lv_absolute_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKLeftPositionAccess().getAbsoluteEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKLeftPosition8520);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3935:2: ( (lv_relative_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3936:1: (lv_relative_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3936:1: (lv_relative_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3937:3: lv_relative_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKLeftPositionAccess().getRelativeEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKLeftPosition8541);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3961:1: entryRuleKRightPosition returns [EObject current=null] : iv_ruleKRightPosition= ruleKRightPosition EOF ;
    public final EObject entryRuleKRightPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRightPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3962:2: (iv_ruleKRightPosition= ruleKRightPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3963:2: iv_ruleKRightPosition= ruleKRightPosition EOF
            {
             newCompositeNode(grammarAccess.getKRightPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRightPosition_in_entryRuleKRightPosition8577);
            iv_ruleKRightPosition=ruleKRightPosition();

            state._fsp--;

             current =iv_ruleKRightPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRightPosition8587); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3970:1: ruleKRightPosition returns [EObject current=null] : ( () otherlv_1= 'right' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) ;
    public final EObject ruleKRightPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_absolute_2_0 = null;

        AntlrDatatypeRuleToken lv_relative_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3973:28: ( ( () otherlv_1= 'right' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3974:1: ( () otherlv_1= 'right' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3974:1: ( () otherlv_1= 'right' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3974:2: () otherlv_1= 'right' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3974:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3975:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRightPositionAccess().getKRightPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,61,FollowSets000.FOLLOW_61_in_ruleKRightPosition8633); 

                	newLeafNode(otherlv_1, grammarAccess.getKRightPositionAccess().getRightKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3984:1: ( (lv_absolute_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3985:1: (lv_absolute_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3985:1: (lv_absolute_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3986:3: lv_absolute_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRightPositionAccess().getAbsoluteEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRightPosition8654);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4002:2: ( (lv_relative_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4003:1: (lv_relative_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4003:1: (lv_relative_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4004:3: lv_relative_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRightPositionAccess().getRelativeEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRightPosition8675);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4028:1: entryRuleKTopPosition returns [EObject current=null] : iv_ruleKTopPosition= ruleKTopPosition EOF ;
    public final EObject entryRuleKTopPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKTopPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4029:2: (iv_ruleKTopPosition= ruleKTopPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4030:2: iv_ruleKTopPosition= ruleKTopPosition EOF
            {
             newCompositeNode(grammarAccess.getKTopPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKTopPosition_in_entryRuleKTopPosition8711);
            iv_ruleKTopPosition=ruleKTopPosition();

            state._fsp--;

             current =iv_ruleKTopPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKTopPosition8721); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4037:1: ruleKTopPosition returns [EObject current=null] : ( () otherlv_1= 'top' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) ;
    public final EObject ruleKTopPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_absolute_2_0 = null;

        AntlrDatatypeRuleToken lv_relative_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4040:28: ( ( () otherlv_1= 'top' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4041:1: ( () otherlv_1= 'top' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4041:1: ( () otherlv_1= 'top' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4041:2: () otherlv_1= 'top' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4041:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4042:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKTopPositionAccess().getKTopPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,62,FollowSets000.FOLLOW_62_in_ruleKTopPosition8767); 

                	newLeafNode(otherlv_1, grammarAccess.getKTopPositionAccess().getTopKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4051:1: ( (lv_absolute_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4052:1: (lv_absolute_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4052:1: (lv_absolute_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4053:3: lv_absolute_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKTopPositionAccess().getAbsoluteEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKTopPosition8788);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4069:2: ( (lv_relative_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4070:1: (lv_relative_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4070:1: (lv_relative_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4071:3: lv_relative_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKTopPositionAccess().getRelativeEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKTopPosition8809);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4095:1: entryRuleKBottomPosition returns [EObject current=null] : iv_ruleKBottomPosition= ruleKBottomPosition EOF ;
    public final EObject entryRuleKBottomPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBottomPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4096:2: (iv_ruleKBottomPosition= ruleKBottomPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4097:2: iv_ruleKBottomPosition= ruleKBottomPosition EOF
            {
             newCompositeNode(grammarAccess.getKBottomPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKBottomPosition_in_entryRuleKBottomPosition8845);
            iv_ruleKBottomPosition=ruleKBottomPosition();

            state._fsp--;

             current =iv_ruleKBottomPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKBottomPosition8855); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4104:1: ruleKBottomPosition returns [EObject current=null] : ( () otherlv_1= 'bottom' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) ;
    public final EObject ruleKBottomPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_absolute_2_0 = null;

        AntlrDatatypeRuleToken lv_relative_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4107:28: ( ( () otherlv_1= 'bottom' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4108:1: ( () otherlv_1= 'bottom' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4108:1: ( () otherlv_1= 'bottom' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4108:2: () otherlv_1= 'bottom' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4108:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4109:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKBottomPositionAccess().getKBottomPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,63,FollowSets000.FOLLOW_63_in_ruleKBottomPosition8901); 

                	newLeafNode(otherlv_1, grammarAccess.getKBottomPositionAccess().getBottomKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4118:1: ( (lv_absolute_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4119:1: (lv_absolute_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4119:1: (lv_absolute_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4120:3: lv_absolute_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKBottomPositionAccess().getAbsoluteEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKBottomPosition8922);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4136:2: ( (lv_relative_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4137:1: (lv_relative_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4137:1: (lv_relative_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4138:3: lv_relative_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKBottomPositionAccess().getRelativeEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKBottomPosition8943);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4162:1: entryRuleKForegroundColor returns [EObject current=null] : iv_ruleKForegroundColor= ruleKForegroundColor EOF ;
    public final EObject entryRuleKForegroundColor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKForegroundColor = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4163:2: (iv_ruleKForegroundColor= ruleKForegroundColor EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4164:2: iv_ruleKForegroundColor= ruleKForegroundColor EOF
            {
             newCompositeNode(grammarAccess.getKForegroundColorRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKForegroundColor_in_entryRuleKForegroundColor8979);
            iv_ruleKForegroundColor=ruleKForegroundColor();

            state._fsp--;

             current =iv_ruleKForegroundColor; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKForegroundColor8989); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4171:1: ruleKForegroundColor returns [EObject current=null] : ( () otherlv_1= 'foregroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? ) ;
    public final EObject ruleKForegroundColor() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_5_0=null;
        AntlrDatatypeRuleToken lv_red_2_0 = null;

        AntlrDatatypeRuleToken lv_green_3_0 = null;

        AntlrDatatypeRuleToken lv_blue_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4174:28: ( ( () otherlv_1= 'foregroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4175:1: ( () otherlv_1= 'foregroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4175:1: ( () otherlv_1= 'foregroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4175:2: () otherlv_1= 'foregroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4175:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4176:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKForegroundColorAccess().getKForegroundColorAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,64,FollowSets000.FOLLOW_64_in_ruleKForegroundColor9035); 

                	newLeafNode(otherlv_1, grammarAccess.getKForegroundColorAccess().getForegroundColorKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4185:1: ( (lv_red_2_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4186:1: (lv_red_2_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4186:1: (lv_red_2_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4187:3: lv_red_2_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKForegroundColorAccess().getRedEIntParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKForegroundColor9056);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4203:2: ( (lv_green_3_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4204:1: (lv_green_3_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4204:1: (lv_green_3_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4205:3: lv_green_3_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKForegroundColorAccess().getGreenEIntParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKForegroundColor9077);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4221:2: ( (lv_blue_4_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4222:1: (lv_blue_4_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4222:1: (lv_blue_4_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4223:3: lv_blue_4_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKForegroundColorAccess().getBlueEIntParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKForegroundColor9098);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4239:2: ( (lv_propagateToChildren_5_0= '!' ) )?
            int alt197=2;
            int LA197_0 = input.LA(1);

            if ( (LA197_0==65) ) {
                alt197=1;
            }
            switch (alt197) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4240:1: (lv_propagateToChildren_5_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4240:1: (lv_propagateToChildren_5_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4241:3: lv_propagateToChildren_5_0= '!'
                    {
                    lv_propagateToChildren_5_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKForegroundColor9116); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4262:1: entryRuleKBackgroundColor returns [EObject current=null] : iv_ruleKBackgroundColor= ruleKBackgroundColor EOF ;
    public final EObject entryRuleKBackgroundColor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBackgroundColor = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4263:2: (iv_ruleKBackgroundColor= ruleKBackgroundColor EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4264:2: iv_ruleKBackgroundColor= ruleKBackgroundColor EOF
            {
             newCompositeNode(grammarAccess.getKBackgroundColorRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKBackgroundColor_in_entryRuleKBackgroundColor9166);
            iv_ruleKBackgroundColor=ruleKBackgroundColor();

            state._fsp--;

             current =iv_ruleKBackgroundColor; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKBackgroundColor9176); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4271:1: ruleKBackgroundColor returns [EObject current=null] : ( () otherlv_1= 'backgroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? ) ;
    public final EObject ruleKBackgroundColor() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_5_0=null;
        AntlrDatatypeRuleToken lv_red_2_0 = null;

        AntlrDatatypeRuleToken lv_green_3_0 = null;

        AntlrDatatypeRuleToken lv_blue_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4274:28: ( ( () otherlv_1= 'backgroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4275:1: ( () otherlv_1= 'backgroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4275:1: ( () otherlv_1= 'backgroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4275:2: () otherlv_1= 'backgroundColor' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4275:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4276:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKBackgroundColorAccess().getKBackgroundColorAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,66,FollowSets000.FOLLOW_66_in_ruleKBackgroundColor9222); 

                	newLeafNode(otherlv_1, grammarAccess.getKBackgroundColorAccess().getBackgroundColorKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4285:1: ( (lv_red_2_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4286:1: (lv_red_2_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4286:1: (lv_red_2_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4287:3: lv_red_2_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKBackgroundColorAccess().getRedEIntParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKBackgroundColor9243);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4303:2: ( (lv_green_3_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4304:1: (lv_green_3_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4304:1: (lv_green_3_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4305:3: lv_green_3_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKBackgroundColorAccess().getGreenEIntParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKBackgroundColor9264);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4321:2: ( (lv_blue_4_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4322:1: (lv_blue_4_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4322:1: (lv_blue_4_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4323:3: lv_blue_4_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKBackgroundColorAccess().getBlueEIntParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKBackgroundColor9285);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4339:2: ( (lv_propagateToChildren_5_0= '!' ) )?
            int alt198=2;
            int LA198_0 = input.LA(1);

            if ( (LA198_0==65) ) {
                alt198=1;
            }
            switch (alt198) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4340:1: (lv_propagateToChildren_5_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4340:1: (lv_propagateToChildren_5_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4341:3: lv_propagateToChildren_5_0= '!'
                    {
                    lv_propagateToChildren_5_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKBackgroundColor9303); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4362:1: entryRuleKLineWidth returns [EObject current=null] : iv_ruleKLineWidth= ruleKLineWidth EOF ;
    public final EObject entryRuleKLineWidth() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLineWidth = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4363:2: (iv_ruleKLineWidth= ruleKLineWidth EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4364:2: iv_ruleKLineWidth= ruleKLineWidth EOF
            {
             newCompositeNode(grammarAccess.getKLineWidthRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLineWidth_in_entryRuleKLineWidth9353);
            iv_ruleKLineWidth=ruleKLineWidth();

            state._fsp--;

             current =iv_ruleKLineWidth; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLineWidth9363); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4371:1: ruleKLineWidth returns [EObject current=null] : (otherlv_0= 'lineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) ;
    public final EObject ruleKLineWidth() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_propagateToChildren_2_0=null;
        AntlrDatatypeRuleToken lv_lineWidth_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4374:28: ( (otherlv_0= 'lineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4375:1: (otherlv_0= 'lineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4375:1: (otherlv_0= 'lineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4375:3: otherlv_0= 'lineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )?
            {
            otherlv_0=(Token)match(input,67,FollowSets000.FOLLOW_67_in_ruleKLineWidth9400); 

                	newLeafNode(otherlv_0, grammarAccess.getKLineWidthAccess().getLineWidthKeyword_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4379:1: ( (lv_lineWidth_1_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4380:1: (lv_lineWidth_1_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4380:1: (lv_lineWidth_1_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4381:3: lv_lineWidth_1_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKLineWidthAccess().getLineWidthEIntParserRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKLineWidth9421);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4397:2: ( (lv_propagateToChildren_2_0= '!' ) )?
            int alt199=2;
            int LA199_0 = input.LA(1);

            if ( (LA199_0==65) ) {
                alt199=1;
            }
            switch (alt199) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4398:1: (lv_propagateToChildren_2_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4398:1: (lv_propagateToChildren_2_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4399:3: lv_propagateToChildren_2_0= '!'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKLineWidth9439); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4420:1: entryRuleKVisibility returns [EObject current=null] : iv_ruleKVisibility= ruleKVisibility EOF ;
    public final EObject entryRuleKVisibility() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKVisibility = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4421:2: (iv_ruleKVisibility= ruleKVisibility EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4422:2: iv_ruleKVisibility= ruleKVisibility EOF
            {
             newCompositeNode(grammarAccess.getKVisibilityRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKVisibility_in_entryRuleKVisibility9489);
            iv_ruleKVisibility=ruleKVisibility();

            state._fsp--;

             current =iv_ruleKVisibility; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKVisibility9499); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4429:1: ruleKVisibility returns [EObject current=null] : (this_KForegroundVisibility_0= ruleKForegroundVisibility | this_KBackgroundVisibility_1= ruleKBackgroundVisibility ) ;
    public final EObject ruleKVisibility() throws RecognitionException {
        EObject current = null;

        EObject this_KForegroundVisibility_0 = null;

        EObject this_KBackgroundVisibility_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4432:28: ( (this_KForegroundVisibility_0= ruleKForegroundVisibility | this_KBackgroundVisibility_1= ruleKBackgroundVisibility ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4433:1: (this_KForegroundVisibility_0= ruleKForegroundVisibility | this_KBackgroundVisibility_1= ruleKBackgroundVisibility )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4433:1: (this_KForegroundVisibility_0= ruleKForegroundVisibility | this_KBackgroundVisibility_1= ruleKBackgroundVisibility )
            int alt200=2;
            int LA200_0 = input.LA(1);

            if ( (LA200_0==68) ) {
                alt200=1;
            }
            else if ( (LA200_0==69) ) {
                alt200=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 200, 0, input);

                throw nvae;
            }
            switch (alt200) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4434:5: this_KForegroundVisibility_0= ruleKForegroundVisibility
                    {
                     
                            newCompositeNode(grammarAccess.getKVisibilityAccess().getKForegroundVisibilityParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKForegroundVisibility_in_ruleKVisibility9546);
                    this_KForegroundVisibility_0=ruleKForegroundVisibility();

                    state._fsp--;

                     
                            current = this_KForegroundVisibility_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4444:5: this_KBackgroundVisibility_1= ruleKBackgroundVisibility
                    {
                     
                            newCompositeNode(grammarAccess.getKVisibilityAccess().getKBackgroundVisibilityParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKBackgroundVisibility_in_ruleKVisibility9573);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4460:1: entryRuleKForegroundVisibility returns [EObject current=null] : iv_ruleKForegroundVisibility= ruleKForegroundVisibility EOF ;
    public final EObject entryRuleKForegroundVisibility() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKForegroundVisibility = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4461:2: (iv_ruleKForegroundVisibility= ruleKForegroundVisibility EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4462:2: iv_ruleKForegroundVisibility= ruleKForegroundVisibility EOF
            {
             newCompositeNode(grammarAccess.getKForegroundVisibilityRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKForegroundVisibility_in_entryRuleKForegroundVisibility9608);
            iv_ruleKForegroundVisibility=ruleKForegroundVisibility();

            state._fsp--;

             current =iv_ruleKForegroundVisibility; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKForegroundVisibility9618); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4469:1: ruleKForegroundVisibility returns [EObject current=null] : ( () otherlv_1= 'foregroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) ;
    public final EObject ruleKForegroundVisibility() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        AntlrDatatypeRuleToken lv_visible_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4472:28: ( ( () otherlv_1= 'foregroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4473:1: ( () otherlv_1= 'foregroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4473:1: ( () otherlv_1= 'foregroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4473:2: () otherlv_1= 'foregroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4473:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4474:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKForegroundVisibilityAccess().getKForegroundVisibilityAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,68,FollowSets000.FOLLOW_68_in_ruleKForegroundVisibility9664); 

                	newLeafNode(otherlv_1, grammarAccess.getKForegroundVisibilityAccess().getForegroundVisibilityKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4483:1: ( (lv_visible_2_0= ruleEBoolean ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4484:1: (lv_visible_2_0= ruleEBoolean )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4484:1: (lv_visible_2_0= ruleEBoolean )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4485:3: lv_visible_2_0= ruleEBoolean
            {
             
            	        newCompositeNode(grammarAccess.getKForegroundVisibilityAccess().getVisibleEBooleanParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleKForegroundVisibility9685);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4501:2: ( (lv_propagateToChildren_3_0= '!' ) )?
            int alt201=2;
            int LA201_0 = input.LA(1);

            if ( (LA201_0==65) ) {
                alt201=1;
            }
            switch (alt201) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4502:1: (lv_propagateToChildren_3_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4502:1: (lv_propagateToChildren_3_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4503:3: lv_propagateToChildren_3_0= '!'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKForegroundVisibility9703); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4524:1: entryRuleKBackgroundVisibility returns [EObject current=null] : iv_ruleKBackgroundVisibility= ruleKBackgroundVisibility EOF ;
    public final EObject entryRuleKBackgroundVisibility() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBackgroundVisibility = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4525:2: (iv_ruleKBackgroundVisibility= ruleKBackgroundVisibility EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4526:2: iv_ruleKBackgroundVisibility= ruleKBackgroundVisibility EOF
            {
             newCompositeNode(grammarAccess.getKBackgroundVisibilityRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKBackgroundVisibility_in_entryRuleKBackgroundVisibility9753);
            iv_ruleKBackgroundVisibility=ruleKBackgroundVisibility();

            state._fsp--;

             current =iv_ruleKBackgroundVisibility; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKBackgroundVisibility9763); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4533:1: ruleKBackgroundVisibility returns [EObject current=null] : ( () otherlv_1= 'backgroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) ;
    public final EObject ruleKBackgroundVisibility() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        AntlrDatatypeRuleToken lv_visible_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4536:28: ( ( () otherlv_1= 'backgroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4537:1: ( () otherlv_1= 'backgroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4537:1: ( () otherlv_1= 'backgroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4537:2: () otherlv_1= 'backgroundVisibility' ( (lv_visible_2_0= ruleEBoolean ) ) ( (lv_propagateToChildren_3_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4537:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4538:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKBackgroundVisibilityAccess().getKBackgroundVisibilityAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,69,FollowSets000.FOLLOW_69_in_ruleKBackgroundVisibility9809); 

                	newLeafNode(otherlv_1, grammarAccess.getKBackgroundVisibilityAccess().getBackgroundVisibilityKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4547:1: ( (lv_visible_2_0= ruleEBoolean ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4548:1: (lv_visible_2_0= ruleEBoolean )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4548:1: (lv_visible_2_0= ruleEBoolean )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4549:3: lv_visible_2_0= ruleEBoolean
            {
             
            	        newCompositeNode(grammarAccess.getKBackgroundVisibilityAccess().getVisibleEBooleanParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleKBackgroundVisibility9830);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4565:2: ( (lv_propagateToChildren_3_0= '!' ) )?
            int alt202=2;
            int LA202_0 = input.LA(1);

            if ( (LA202_0==65) ) {
                alt202=1;
            }
            switch (alt202) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4566:1: (lv_propagateToChildren_3_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4566:1: (lv_propagateToChildren_3_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4567:3: lv_propagateToChildren_3_0= '!'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKBackgroundVisibility9848); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4588:1: entryRuleKLineStyle returns [EObject current=null] : iv_ruleKLineStyle= ruleKLineStyle EOF ;
    public final EObject entryRuleKLineStyle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLineStyle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4589:2: (iv_ruleKLineStyle= ruleKLineStyle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4590:2: iv_ruleKLineStyle= ruleKLineStyle EOF
            {
             newCompositeNode(grammarAccess.getKLineStyleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLineStyle_in_entryRuleKLineStyle9898);
            iv_ruleKLineStyle=ruleKLineStyle();

            state._fsp--;

             current =iv_ruleKLineStyle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLineStyle9908); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4597:1: ruleKLineStyle returns [EObject current=null] : ( () otherlv_1= 'lineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) ;
    public final EObject ruleKLineStyle() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        Enumerator lv_lineStyle_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4600:28: ( ( () otherlv_1= 'lineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4601:1: ( () otherlv_1= 'lineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4601:1: ( () otherlv_1= 'lineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4601:2: () otherlv_1= 'lineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) ) ( (lv_propagateToChildren_3_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4601:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4602:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKLineStyleAccess().getKLineStyleAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,70,FollowSets000.FOLLOW_70_in_ruleKLineStyle9954); 

                	newLeafNode(otherlv_1, grammarAccess.getKLineStyleAccess().getLineStyleKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4611:1: ( (lv_lineStyle_2_0= ruleLineStyle ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4612:1: (lv_lineStyle_2_0= ruleLineStyle )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4612:1: (lv_lineStyle_2_0= ruleLineStyle )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4613:3: lv_lineStyle_2_0= ruleLineStyle
            {
             
            	        newCompositeNode(grammarAccess.getKLineStyleAccess().getLineStyleLineStyleEnumRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleLineStyle_in_ruleKLineStyle9975);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4629:2: ( (lv_propagateToChildren_3_0= '!' ) )?
            int alt203=2;
            int LA203_0 = input.LA(1);

            if ( (LA203_0==65) ) {
                alt203=1;
            }
            switch (alt203) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4630:1: (lv_propagateToChildren_3_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4630:1: (lv_propagateToChildren_3_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4631:3: lv_propagateToChildren_3_0= '!'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKLineStyle9993); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4652:1: entryRuleKRotation returns [EObject current=null] : iv_ruleKRotation= ruleKRotation EOF ;
    public final EObject entryRuleKRotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRotation = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4653:2: (iv_ruleKRotation= ruleKRotation EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4654:2: iv_ruleKRotation= ruleKRotation EOF
            {
             newCompositeNode(grammarAccess.getKRotationRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRotation_in_entryRuleKRotation10043);
            iv_ruleKRotation=ruleKRotation();

            state._fsp--;

             current =iv_ruleKRotation; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRotation10053); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4661:1: ruleKRotation returns [EObject current=null] : ( () otherlv_1= 'rotation' ( (lv_rotation_2_0= ruleEFloat ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) ;
    public final EObject ruleKRotation() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        AntlrDatatypeRuleToken lv_rotation_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4664:28: ( ( () otherlv_1= 'rotation' ( (lv_rotation_2_0= ruleEFloat ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4665:1: ( () otherlv_1= 'rotation' ( (lv_rotation_2_0= ruleEFloat ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4665:1: ( () otherlv_1= 'rotation' ( (lv_rotation_2_0= ruleEFloat ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4665:2: () otherlv_1= 'rotation' ( (lv_rotation_2_0= ruleEFloat ) ) ( (lv_propagateToChildren_3_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4665:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4666:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRotationAccess().getKRotationAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,71,FollowSets000.FOLLOW_71_in_ruleKRotation10099); 

                	newLeafNode(otherlv_1, grammarAccess.getKRotationAccess().getRotationKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4675:1: ( (lv_rotation_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4676:1: (lv_rotation_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4676:1: (lv_rotation_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4677:3: lv_rotation_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRotationAccess().getRotationEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRotation10120);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4693:2: ( (lv_propagateToChildren_3_0= '!' ) )?
            int alt204=2;
            int LA204_0 = input.LA(1);

            if ( (LA204_0==65) ) {
                alt204=1;
            }
            switch (alt204) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4694:1: (lv_propagateToChildren_3_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4694:1: (lv_propagateToChildren_3_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4695:3: lv_propagateToChildren_3_0= '!'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKRotation10138); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4716:1: entryRuleKFontBold returns [EObject current=null] : iv_ruleKFontBold= ruleKFontBold EOF ;
    public final EObject entryRuleKFontBold() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontBold = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4717:2: (iv_ruleKFontBold= ruleKFontBold EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4718:2: iv_ruleKFontBold= ruleKFontBold EOF
            {
             newCompositeNode(grammarAccess.getKFontBoldRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKFontBold_in_entryRuleKFontBold10188);
            iv_ruleKFontBold=ruleKFontBold();

            state._fsp--;

             current =iv_ruleKFontBold; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKFontBold10198); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4725:1: ruleKFontBold returns [EObject current=null] : ( () ( (lv_bold_1_0= 'bold' ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) ;
    public final EObject ruleKFontBold() throws RecognitionException {
        EObject current = null;

        Token lv_bold_1_0=null;
        Token lv_propagateToChildren_2_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4728:28: ( ( () ( (lv_bold_1_0= 'bold' ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4729:1: ( () ( (lv_bold_1_0= 'bold' ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4729:1: ( () ( (lv_bold_1_0= 'bold' ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4729:2: () ( (lv_bold_1_0= 'bold' ) ) ( (lv_propagateToChildren_2_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4729:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4730:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKFontBoldAccess().getKFontBoldAction_0(),
                        current);
                

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4735:2: ( (lv_bold_1_0= 'bold' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4736:1: (lv_bold_1_0= 'bold' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4736:1: (lv_bold_1_0= 'bold' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4737:3: lv_bold_1_0= 'bold'
            {
            lv_bold_1_0=(Token)match(input,72,FollowSets000.FOLLOW_72_in_ruleKFontBold10250); 

                    newLeafNode(lv_bold_1_0, grammarAccess.getKFontBoldAccess().getBoldBoldKeyword_1_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKFontBoldRule());
            	        }
                   		setWithLastConsumed(current, "bold", true, "bold");
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4750:2: ( (lv_propagateToChildren_2_0= '!' ) )?
            int alt205=2;
            int LA205_0 = input.LA(1);

            if ( (LA205_0==65) ) {
                alt205=1;
            }
            switch (alt205) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4751:1: (lv_propagateToChildren_2_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4751:1: (lv_propagateToChildren_2_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4752:3: lv_propagateToChildren_2_0= '!'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKFontBold10281); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4773:1: entryRuleKFontItalic returns [EObject current=null] : iv_ruleKFontItalic= ruleKFontItalic EOF ;
    public final EObject entryRuleKFontItalic() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontItalic = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4774:2: (iv_ruleKFontItalic= ruleKFontItalic EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4775:2: iv_ruleKFontItalic= ruleKFontItalic EOF
            {
             newCompositeNode(grammarAccess.getKFontItalicRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKFontItalic_in_entryRuleKFontItalic10331);
            iv_ruleKFontItalic=ruleKFontItalic();

            state._fsp--;

             current =iv_ruleKFontItalic; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKFontItalic10341); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4782:1: ruleKFontItalic returns [EObject current=null] : ( () ( (lv_italic_1_0= 'italic' ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) ;
    public final EObject ruleKFontItalic() throws RecognitionException {
        EObject current = null;

        Token lv_italic_1_0=null;
        Token lv_propagateToChildren_2_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4785:28: ( ( () ( (lv_italic_1_0= 'italic' ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4786:1: ( () ( (lv_italic_1_0= 'italic' ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4786:1: ( () ( (lv_italic_1_0= 'italic' ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4786:2: () ( (lv_italic_1_0= 'italic' ) ) ( (lv_propagateToChildren_2_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4786:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4787:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKFontItalicAccess().getKFontItalicAction_0(),
                        current);
                

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4792:2: ( (lv_italic_1_0= 'italic' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4793:1: (lv_italic_1_0= 'italic' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4793:1: (lv_italic_1_0= 'italic' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4794:3: lv_italic_1_0= 'italic'
            {
            lv_italic_1_0=(Token)match(input,73,FollowSets000.FOLLOW_73_in_ruleKFontItalic10393); 

                    newLeafNode(lv_italic_1_0, grammarAccess.getKFontItalicAccess().getItalicItalicKeyword_1_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKFontItalicRule());
            	        }
                   		setWithLastConsumed(current, "italic", true, "italic");
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4807:2: ( (lv_propagateToChildren_2_0= '!' ) )?
            int alt206=2;
            int LA206_0 = input.LA(1);

            if ( (LA206_0==65) ) {
                alt206=1;
            }
            switch (alt206) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4808:1: (lv_propagateToChildren_2_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4808:1: (lv_propagateToChildren_2_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4809:3: lv_propagateToChildren_2_0= '!'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKFontItalic10424); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4830:1: entryRuleKFontName returns [EObject current=null] : iv_ruleKFontName= ruleKFontName EOF ;
    public final EObject entryRuleKFontName() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontName = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4831:2: (iv_ruleKFontName= ruleKFontName EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4832:2: iv_ruleKFontName= ruleKFontName EOF
            {
             newCompositeNode(grammarAccess.getKFontNameRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKFontName_in_entryRuleKFontName10474);
            iv_ruleKFontName=ruleKFontName();

            state._fsp--;

             current =iv_ruleKFontName; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKFontName10484); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4839:1: ruleKFontName returns [EObject current=null] : (otherlv_0= 'font' ( (lv_name_1_0= ruleEString ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) ;
    public final EObject ruleKFontName() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_propagateToChildren_2_0=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4842:28: ( (otherlv_0= 'font' ( (lv_name_1_0= ruleEString ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4843:1: (otherlv_0= 'font' ( (lv_name_1_0= ruleEString ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4843:1: (otherlv_0= 'font' ( (lv_name_1_0= ruleEString ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4843:3: otherlv_0= 'font' ( (lv_name_1_0= ruleEString ) ) ( (lv_propagateToChildren_2_0= '!' ) )?
            {
            otherlv_0=(Token)match(input,74,FollowSets000.FOLLOW_74_in_ruleKFontName10521); 

                	newLeafNode(otherlv_0, grammarAccess.getKFontNameAccess().getFontKeyword_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4847:1: ( (lv_name_1_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4848:1: (lv_name_1_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4848:1: (lv_name_1_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4849:3: lv_name_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKFontNameAccess().getNameEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKFontName10542);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4865:2: ( (lv_propagateToChildren_2_0= '!' ) )?
            int alt207=2;
            int LA207_0 = input.LA(1);

            if ( (LA207_0==65) ) {
                alt207=1;
            }
            switch (alt207) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4866:1: (lv_propagateToChildren_2_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4866:1: (lv_propagateToChildren_2_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4867:3: lv_propagateToChildren_2_0= '!'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKFontName10560); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4888:1: entryRuleKFontSize returns [EObject current=null] : iv_ruleKFontSize= ruleKFontSize EOF ;
    public final EObject entryRuleKFontSize() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontSize = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4889:2: (iv_ruleKFontSize= ruleKFontSize EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4890:2: iv_ruleKFontSize= ruleKFontSize EOF
            {
             newCompositeNode(grammarAccess.getKFontSizeRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKFontSize_in_entryRuleKFontSize10610);
            iv_ruleKFontSize=ruleKFontSize();

            state._fsp--;

             current =iv_ruleKFontSize; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKFontSize10620); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4897:1: ruleKFontSize returns [EObject current=null] : (otherlv_0= 'fontSize' ( (lv_size_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) ;
    public final EObject ruleKFontSize() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_propagateToChildren_2_0=null;
        AntlrDatatypeRuleToken lv_size_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4900:28: ( (otherlv_0= 'fontSize' ( (lv_size_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4901:1: (otherlv_0= 'fontSize' ( (lv_size_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4901:1: (otherlv_0= 'fontSize' ( (lv_size_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4901:3: otherlv_0= 'fontSize' ( (lv_size_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )?
            {
            otherlv_0=(Token)match(input,75,FollowSets000.FOLLOW_75_in_ruleKFontSize10657); 

                	newLeafNode(otherlv_0, grammarAccess.getKFontSizeAccess().getFontSizeKeyword_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4905:1: ( (lv_size_1_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4906:1: (lv_size_1_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4906:1: (lv_size_1_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4907:3: lv_size_1_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKFontSizeAccess().getSizeEIntParserRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKFontSize10678);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4923:2: ( (lv_propagateToChildren_2_0= '!' ) )?
            int alt208=2;
            int LA208_0 = input.LA(1);

            if ( (LA208_0==65) ) {
                alt208=1;
            }
            switch (alt208) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4924:1: (lv_propagateToChildren_2_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4924:1: (lv_propagateToChildren_2_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4925:3: lv_propagateToChildren_2_0= '!'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKFontSize10696); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4946:1: entryRuleKVerticalAlignment returns [EObject current=null] : iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF ;
    public final EObject entryRuleKVerticalAlignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKVerticalAlignment = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4947:2: (iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4948:2: iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF
            {
             newCompositeNode(grammarAccess.getKVerticalAlignmentRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKVerticalAlignment_in_entryRuleKVerticalAlignment10746);
            iv_ruleKVerticalAlignment=ruleKVerticalAlignment();

            state._fsp--;

             current =iv_ruleKVerticalAlignment; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKVerticalAlignment10756); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4955:1: ruleKVerticalAlignment returns [EObject current=null] : ( () otherlv_1= 'verticalAlignment' ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) ;
    public final EObject ruleKVerticalAlignment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        Enumerator lv_verticalAlignment_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4958:28: ( ( () otherlv_1= 'verticalAlignment' ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4959:1: ( () otherlv_1= 'verticalAlignment' ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4959:1: ( () otherlv_1= 'verticalAlignment' ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4959:2: () otherlv_1= 'verticalAlignment' ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4959:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4960:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKVerticalAlignmentAccess().getKVerticalAlignmentAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,76,FollowSets000.FOLLOW_76_in_ruleKVerticalAlignment10802); 

                	newLeafNode(otherlv_1, grammarAccess.getKVerticalAlignmentAccess().getVerticalAlignmentKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4969:1: ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4970:1: (lv_verticalAlignment_2_0= ruleVerticalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4970:1: (lv_verticalAlignment_2_0= ruleVerticalAlignment )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4971:3: lv_verticalAlignment_2_0= ruleVerticalAlignment
            {
             
            	        newCompositeNode(grammarAccess.getKVerticalAlignmentAccess().getVerticalAlignmentVerticalAlignmentEnumRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleVerticalAlignment_in_ruleKVerticalAlignment10823);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4987:2: ( (lv_propagateToChildren_3_0= '!' ) )?
            int alt209=2;
            int LA209_0 = input.LA(1);

            if ( (LA209_0==65) ) {
                alt209=1;
            }
            switch (alt209) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4988:1: (lv_propagateToChildren_3_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4988:1: (lv_propagateToChildren_3_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4989:3: lv_propagateToChildren_3_0= '!'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKVerticalAlignment10841); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5010:1: entryRuleKHorizontalAlignment returns [EObject current=null] : iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF ;
    public final EObject entryRuleKHorizontalAlignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKHorizontalAlignment = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5011:2: (iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5012:2: iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF
            {
             newCompositeNode(grammarAccess.getKHorizontalAlignmentRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKHorizontalAlignment_in_entryRuleKHorizontalAlignment10891);
            iv_ruleKHorizontalAlignment=ruleKHorizontalAlignment();

            state._fsp--;

             current =iv_ruleKHorizontalAlignment; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKHorizontalAlignment10901); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5019:1: ruleKHorizontalAlignment returns [EObject current=null] : ( () otherlv_1= 'horizontalAlignment' ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) ;
    public final EObject ruleKHorizontalAlignment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        Enumerator lv_horizontalAlignment_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5022:28: ( ( () otherlv_1= 'horizontalAlignment' ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5023:1: ( () otherlv_1= 'horizontalAlignment' ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5023:1: ( () otherlv_1= 'horizontalAlignment' ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5023:2: () otherlv_1= 'horizontalAlignment' ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5023:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5024:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKHorizontalAlignmentAccess().getKHorizontalAlignmentAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,77,FollowSets000.FOLLOW_77_in_ruleKHorizontalAlignment10947); 

                	newLeafNode(otherlv_1, grammarAccess.getKHorizontalAlignmentAccess().getHorizontalAlignmentKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5033:1: ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5034:1: (lv_horizontalAlignment_2_0= ruleHorizontalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5034:1: (lv_horizontalAlignment_2_0= ruleHorizontalAlignment )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5035:3: lv_horizontalAlignment_2_0= ruleHorizontalAlignment
            {
             
            	        newCompositeNode(grammarAccess.getKHorizontalAlignmentAccess().getHorizontalAlignmentHorizontalAlignmentEnumRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleHorizontalAlignment_in_ruleKHorizontalAlignment10968);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5051:2: ( (lv_propagateToChildren_3_0= '!' ) )?
            int alt210=2;
            int LA210_0 = input.LA(1);

            if ( (LA210_0==65) ) {
                alt210=1;
            }
            switch (alt210) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5052:1: (lv_propagateToChildren_3_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5052:1: (lv_propagateToChildren_3_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5053:3: lv_propagateToChildren_3_0= '!'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKHorizontalAlignment10986); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5074:1: entryRuleKGridPlacement returns [EObject current=null] : iv_ruleKGridPlacement= ruleKGridPlacement EOF ;
    public final EObject entryRuleKGridPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKGridPlacement = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5075:2: (iv_ruleKGridPlacement= ruleKGridPlacement EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5076:2: iv_ruleKGridPlacement= ruleKGridPlacement EOF
            {
             newCompositeNode(grammarAccess.getKGridPlacementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKGridPlacement_in_entryRuleKGridPlacement11036);
            iv_ruleKGridPlacement=ruleKGridPlacement();

            state._fsp--;

             current =iv_ruleKGridPlacement; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKGridPlacement11046); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5083:1: ruleKGridPlacement returns [EObject current=null] : ( () otherlv_1= 'gridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) ) ;
    public final EObject ruleKGridPlacement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_numColumns_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5086:28: ( ( () otherlv_1= 'gridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5087:1: ( () otherlv_1= 'gridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5087:1: ( () otherlv_1= 'gridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5087:2: () otherlv_1= 'gridPlacement' ( (lv_numColumns_2_0= ruleEInt ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5087:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5088:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKGridPlacementAccess().getKGridPlacementAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,78,FollowSets000.FOLLOW_78_in_ruleKGridPlacement11092); 

                	newLeafNode(otherlv_1, grammarAccess.getKGridPlacementAccess().getGridPlacementKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5097:1: ( (lv_numColumns_2_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5098:1: (lv_numColumns_2_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5098:1: (lv_numColumns_2_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5099:3: lv_numColumns_2_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementAccess().getNumColumnsEIntParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKGridPlacement11113);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5123:1: entryRuleKStackPlacement returns [EObject current=null] : iv_ruleKStackPlacement= ruleKStackPlacement EOF ;
    public final EObject entryRuleKStackPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStackPlacement = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5124:2: (iv_ruleKStackPlacement= ruleKStackPlacement EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5125:2: iv_ruleKStackPlacement= ruleKStackPlacement EOF
            {
             newCompositeNode(grammarAccess.getKStackPlacementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKStackPlacement_in_entryRuleKStackPlacement11149);
            iv_ruleKStackPlacement=ruleKStackPlacement();

            state._fsp--;

             current =iv_ruleKStackPlacement; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKStackPlacement11159); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5132:1: ruleKStackPlacement returns [EObject current=null] : ( () otherlv_1= 'stackPlacement' ) ;
    public final EObject ruleKStackPlacement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5135:28: ( ( () otherlv_1= 'stackPlacement' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5136:1: ( () otherlv_1= 'stackPlacement' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5136:1: ( () otherlv_1= 'stackPlacement' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5136:2: () otherlv_1= 'stackPlacement'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5136:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5137:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKStackPlacementAccess().getKStackPlacementAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,79,FollowSets000.FOLLOW_79_in_ruleKStackPlacement11205); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5154:1: entryRuleEFloat returns [String current=null] : iv_ruleEFloat= ruleEFloat EOF ;
    public final String entryRuleEFloat() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEFloat = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5155:2: (iv_ruleEFloat= ruleEFloat EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5156:2: iv_ruleEFloat= ruleEFloat EOF
            {
             newCompositeNode(grammarAccess.getEFloatRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_entryRuleEFloat11242);
            iv_ruleEFloat=ruleEFloat();

            state._fsp--;

             current =iv_ruleEFloat.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEFloat11253); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5163:1: ruleEFloat returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )? ) ;
    public final AntlrDatatypeRuleToken ruleEFloat() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;
        Token this_INT_3=null;
        Token this_INT_7=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5166:28: ( ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5167:1: ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5167:1: ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5167:2: (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5167:2: (kw= '-' )?
            int alt211=2;
            int LA211_0 = input.LA(1);

            if ( (LA211_0==28) ) {
                alt211=1;
            }
            switch (alt211) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5168:2: kw= '-'
                    {
                    kw=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleEFloat11292); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEFloatAccess().getHyphenMinusKeyword_0()); 
                        

                    }
                    break;

            }

            this_INT_1=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat11309); 

            		current.merge(this_INT_1);
                
             
                newLeafNode(this_INT_1, grammarAccess.getEFloatAccess().getINTTerminalRuleCall_1()); 
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5180:1: (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )?
            int alt215=2;
            int LA215_0 = input.LA(1);

            if ( (LA215_0==80) ) {
                alt215=1;
            }
            switch (alt215) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5181:2: kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )?
                    {
                    kw=(Token)match(input,80,FollowSets000.FOLLOW_80_in_ruleEFloat11328); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEFloatAccess().getFullStopKeyword_2_0()); 
                        
                    this_INT_3=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat11343); 

                    		current.merge(this_INT_3);
                        
                     
                        newLeafNode(this_INT_3, grammarAccess.getEFloatAccess().getINTTerminalRuleCall_2_1()); 
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5193:1: ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )?
                    int alt214=2;
                    int LA214_0 = input.LA(1);

                    if ( ((LA214_0>=81 && LA214_0<=82)) ) {
                        alt214=1;
                    }
                    switch (alt214) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5193:2: (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5193:2: (kw= 'E' | kw= 'e' )
                            int alt212=2;
                            int LA212_0 = input.LA(1);

                            if ( (LA212_0==81) ) {
                                alt212=1;
                            }
                            else if ( (LA212_0==82) ) {
                                alt212=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 212, 0, input);

                                throw nvae;
                            }
                            switch (alt212) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5194:2: kw= 'E'
                                    {
                                    kw=(Token)match(input,81,FollowSets000.FOLLOW_81_in_ruleEFloat11363); 

                                            current.merge(kw);
                                            newLeafNode(kw, grammarAccess.getEFloatAccess().getEKeyword_2_2_0_0()); 
                                        

                                    }
                                    break;
                                case 2 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5201:2: kw= 'e'
                                    {
                                    kw=(Token)match(input,82,FollowSets000.FOLLOW_82_in_ruleEFloat11382); 

                                            current.merge(kw);
                                            newLeafNode(kw, grammarAccess.getEFloatAccess().getEKeyword_2_2_0_1()); 
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5206:2: (kw= '-' )?
                            int alt213=2;
                            int LA213_0 = input.LA(1);

                            if ( (LA213_0==28) ) {
                                alt213=1;
                            }
                            switch (alt213) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5207:2: kw= '-'
                                    {
                                    kw=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleEFloat11397); 

                                            current.merge(kw);
                                            newLeafNode(kw, grammarAccess.getEFloatAccess().getHyphenMinusKeyword_2_2_1()); 
                                        

                                    }
                                    break;

                            }

                            this_INT_7=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat11414); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5227:1: entryRuleEBoolean returns [String current=null] : iv_ruleEBoolean= ruleEBoolean EOF ;
    public final String entryRuleEBoolean() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEBoolean = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5228:2: (iv_ruleEBoolean= ruleEBoolean EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5229:2: iv_ruleEBoolean= ruleEBoolean EOF
            {
             newCompositeNode(grammarAccess.getEBooleanRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_entryRuleEBoolean11464);
            iv_ruleEBoolean=ruleEBoolean();

            state._fsp--;

             current =iv_ruleEBoolean.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEBoolean11475); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5236:1: ruleEBoolean returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'true' | kw= 'false' ) ;
    public final AntlrDatatypeRuleToken ruleEBoolean() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5239:28: ( (kw= 'true' | kw= 'false' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5240:1: (kw= 'true' | kw= 'false' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5240:1: (kw= 'true' | kw= 'false' )
            int alt216=2;
            int LA216_0 = input.LA(1);

            if ( (LA216_0==83) ) {
                alt216=1;
            }
            else if ( (LA216_0==84) ) {
                alt216=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 216, 0, input);

                throw nvae;
            }
            switch (alt216) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5241:2: kw= 'true'
                    {
                    kw=(Token)match(input,83,FollowSets000.FOLLOW_83_in_ruleEBoolean11513); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEBooleanAccess().getTrueKeyword_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5248:2: kw= 'false'
                    {
                    kw=(Token)match(input,84,FollowSets000.FOLLOW_84_in_ruleEBoolean11532); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5261:1: entryRuleEInt returns [String current=null] : iv_ruleEInt= ruleEInt EOF ;
    public final String entryRuleEInt() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEInt = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5262:2: (iv_ruleEInt= ruleEInt EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5263:2: iv_ruleEInt= ruleEInt EOF
            {
             newCompositeNode(grammarAccess.getEIntRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_entryRuleEInt11573);
            iv_ruleEInt=ruleEInt();

            state._fsp--;

             current =iv_ruleEInt.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEInt11584); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5270:1: ruleEInt returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleEInt() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5273:28: ( ( (kw= '-' )? this_INT_1= RULE_INT ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5274:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5274:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5274:2: (kw= '-' )? this_INT_1= RULE_INT
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5274:2: (kw= '-' )?
            int alt217=2;
            int LA217_0 = input.LA(1);

            if ( (LA217_0==28) ) {
                alt217=1;
            }
            switch (alt217) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5275:2: kw= '-'
                    {
                    kw=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleEInt11623); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEIntAccess().getHyphenMinusKeyword_0()); 
                        

                    }
                    break;

            }

            this_INT_1=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEInt11640); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5297:1: entryRuleKInsets returns [EObject current=null] : iv_ruleKInsets= ruleKInsets EOF ;
    public final EObject entryRuleKInsets() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKInsets = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5298:2: (iv_ruleKInsets= ruleKInsets EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5299:2: iv_ruleKInsets= ruleKInsets EOF
            {
             newCompositeNode(grammarAccess.getKInsetsRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKInsets_in_entryRuleKInsets11687);
            iv_ruleKInsets=ruleKInsets();

            state._fsp--;

             current =iv_ruleKInsets; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKInsets11697); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5306:1: ruleKInsets returns [EObject current=null] : ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5309:28: ( ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5310:1: ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5310:1: ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5310:2: () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5310:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5311:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKInsetsAccess().getKInsetsAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,85,FollowSets000.FOLLOW_85_in_ruleKInsets11743); 

                	newLeafNode(otherlv_1, grammarAccess.getKInsetsAccess().getKInsetsKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKInsets11755); 

                	newLeafNode(otherlv_2, grammarAccess.getKInsetsAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5324:1: (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )?
            int alt218=2;
            int LA218_0 = input.LA(1);

            if ( (LA218_0==62) ) {
                alt218=1;
            }
            switch (alt218) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5324:3: otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,62,FollowSets000.FOLLOW_62_in_ruleKInsets11768); 

                        	newLeafNode(otherlv_3, grammarAccess.getKInsetsAccess().getTopKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5328:1: ( (lv_top_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5329:1: (lv_top_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5329:1: (lv_top_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5330:3: lv_top_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getTopEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets11789);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5346:4: (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )?
            int alt219=2;
            int LA219_0 = input.LA(1);

            if ( (LA219_0==63) ) {
                alt219=1;
            }
            switch (alt219) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5346:6: otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,63,FollowSets000.FOLLOW_63_in_ruleKInsets11804); 

                        	newLeafNode(otherlv_5, grammarAccess.getKInsetsAccess().getBottomKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5350:1: ( (lv_bottom_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5351:1: (lv_bottom_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5351:1: (lv_bottom_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5352:3: lv_bottom_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getBottomEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets11825);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5368:4: (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )?
            int alt220=2;
            int LA220_0 = input.LA(1);

            if ( (LA220_0==60) ) {
                alt220=1;
            }
            switch (alt220) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5368:6: otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,60,FollowSets000.FOLLOW_60_in_ruleKInsets11840); 

                        	newLeafNode(otherlv_7, grammarAccess.getKInsetsAccess().getLeftKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5372:1: ( (lv_left_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5373:1: (lv_left_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5373:1: (lv_left_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5374:3: lv_left_8_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getLeftEFloatParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets11861);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5390:4: (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )?
            int alt221=2;
            int LA221_0 = input.LA(1);

            if ( (LA221_0==61) ) {
                alt221=1;
            }
            switch (alt221) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5390:6: otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) )
                    {
                    otherlv_9=(Token)match(input,61,FollowSets000.FOLLOW_61_in_ruleKInsets11876); 

                        	newLeafNode(otherlv_9, grammarAccess.getKInsetsAccess().getRightKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5394:1: ( (lv_right_10_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5395:1: (lv_right_10_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5395:1: (lv_right_10_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5396:3: lv_right_10_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getRightEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets11897);
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

            otherlv_11=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKInsets11911); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5426:1: entryRuleKPoint returns [EObject current=null] : iv_ruleKPoint= ruleKPoint EOF ;
    public final EObject entryRuleKPoint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPoint = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5427:2: (iv_ruleKPoint= ruleKPoint EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5428:2: iv_ruleKPoint= ruleKPoint EOF
            {
             newCompositeNode(grammarAccess.getKPointRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPoint_in_entryRuleKPoint11949);
            iv_ruleKPoint=ruleKPoint();

            state._fsp--;

             current =iv_ruleKPoint; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPoint11959); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5435:1: ruleKPoint returns [EObject current=null] : ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) ) ;
    public final EObject ruleKPoint() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_x_3_0 = null;

        AntlrDatatypeRuleToken lv_y_5_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5438:28: ( ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5439:1: ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5439:1: ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5439:2: () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5439:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5440:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPointAccess().getKPointAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,86,FollowSets000.FOLLOW_86_in_ruleKPoint12005); 

                	newLeafNode(otherlv_1, grammarAccess.getKPointAccess().getKPointKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5449:1: (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5449:3: otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) )
            {
            otherlv_2=(Token)match(input,87,FollowSets000.FOLLOW_87_in_ruleKPoint12018); 

                	newLeafNode(otherlv_2, grammarAccess.getKPointAccess().getXKeyword_2_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5453:1: ( (lv_x_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5454:1: (lv_x_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5454:1: (lv_x_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5455:3: lv_x_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKPointAccess().getXEFloatParserRuleCall_2_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKPoint12039);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5471:3: (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5471:5: otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) )
            {
            otherlv_4=(Token)match(input,88,FollowSets000.FOLLOW_88_in_ruleKPoint12053); 

                	newLeafNode(otherlv_4, grammarAccess.getKPointAccess().getYKeyword_3_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5475:1: ( (lv_y_5_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5476:1: (lv_y_5_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5476:1: (lv_y_5_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5477:3: lv_y_5_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKPointAccess().getYEFloatParserRuleCall_3_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKPoint12074);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5501:1: entryRulePersistentEntry returns [EObject current=null] : iv_rulePersistentEntry= rulePersistentEntry EOF ;
    public final EObject entryRulePersistentEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePersistentEntry = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5502:2: (iv_rulePersistentEntry= rulePersistentEntry EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5503:2: iv_rulePersistentEntry= rulePersistentEntry EOF
            {
             newCompositeNode(grammarAccess.getPersistentEntryRule()); 
            pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry12111);
            iv_rulePersistentEntry=rulePersistentEntry();

            state._fsp--;

             current =iv_rulePersistentEntry; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRulePersistentEntry12121); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5510:1: rulePersistentEntry returns [EObject current=null] : ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? ) ;
    public final EObject rulePersistentEntry() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_key_0_0 = null;

        AntlrDatatypeRuleToken lv_value_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5513:28: ( ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5514:1: ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5514:1: ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5514:2: ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5514:2: ( (lv_key_0_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5515:1: (lv_key_0_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5515:1: (lv_key_0_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5516:3: lv_key_0_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getKeyEStringParserRuleCall_0_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_rulePersistentEntry12167);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5532:2: (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )?
            int alt222=2;
            int LA222_0 = input.LA(1);

            if ( (LA222_0==89) ) {
                alt222=1;
            }
            switch (alt222) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5532:4: otherlv_1= '=' ( (lv_value_2_0= ruleEString ) )
                    {
                    otherlv_1=(Token)match(input,89,FollowSets000.FOLLOW_89_in_rulePersistentEntry12180); 

                        	newLeafNode(otherlv_1, grammarAccess.getPersistentEntryAccess().getEqualsSignKeyword_1_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5536:1: ( (lv_value_2_0= ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5537:1: (lv_value_2_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5537:1: (lv_value_2_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5538:3: lv_value_2_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getValueEStringParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_rulePersistentEntry12201);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5562:1: entryRuleEString returns [String current=null] : iv_ruleEString= ruleEString EOF ;
    public final String entryRuleEString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEString = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5563:2: (iv_ruleEString= ruleEString EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5564:2: iv_ruleEString= ruleEString EOF
            {
             newCompositeNode(grammarAccess.getEStringRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_entryRuleEString12240);
            iv_ruleEString=ruleEString();

            state._fsp--;

             current =iv_ruleEString.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEString12251); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5571:1: ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleEString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token this_ID_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5574:28: ( (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5575:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5575:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            int alt223=2;
            int LA223_0 = input.LA(1);

            if ( (LA223_0==RULE_STRING) ) {
                alt223=1;
            }
            else if ( (LA223_0==RULE_ID) ) {
                alt223=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 223, 0, input);

                throw nvae;
            }
            switch (alt223) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5575:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleEString12291); 

                    		current.merge(this_STRING_0);
                        
                     
                        newLeafNode(this_STRING_0, grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5583:10: this_ID_1= RULE_ID
                    {
                    this_ID_1=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleEString12317); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5598:1: ruleLineStyle returns [Enumerator current=null] : ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) ) ;
    public final Enumerator ruleLineStyle() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5600:28: ( ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5601:1: ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5601:1: ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) )
            int alt224=5;
            switch ( input.LA(1) ) {
            case 90:
                {
                alt224=1;
                }
                break;
            case 91:
                {
                alt224=2;
                }
                break;
            case 92:
                {
                alt224=3;
                }
                break;
            case 93:
                {
                alt224=4;
                }
                break;
            case 94:
                {
                alt224=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 224, 0, input);

                throw nvae;
            }

            switch (alt224) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5601:2: (enumLiteral_0= 'SOLID' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5601:2: (enumLiteral_0= 'SOLID' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5601:4: enumLiteral_0= 'SOLID'
                    {
                    enumLiteral_0=(Token)match(input,90,FollowSets000.FOLLOW_90_in_ruleLineStyle12376); 

                            current = grammarAccess.getLineStyleAccess().getSOLIDEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getLineStyleAccess().getSOLIDEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5607:6: (enumLiteral_1= 'DASH' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5607:6: (enumLiteral_1= 'DASH' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5607:8: enumLiteral_1= 'DASH'
                    {
                    enumLiteral_1=(Token)match(input,91,FollowSets000.FOLLOW_91_in_ruleLineStyle12393); 

                            current = grammarAccess.getLineStyleAccess().getDASHEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getLineStyleAccess().getDASHEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5613:6: (enumLiteral_2= 'DOT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5613:6: (enumLiteral_2= 'DOT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5613:8: enumLiteral_2= 'DOT'
                    {
                    enumLiteral_2=(Token)match(input,92,FollowSets000.FOLLOW_92_in_ruleLineStyle12410); 

                            current = grammarAccess.getLineStyleAccess().getDOTEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getLineStyleAccess().getDOTEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5619:6: (enumLiteral_3= 'DASHDOT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5619:6: (enumLiteral_3= 'DASHDOT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5619:8: enumLiteral_3= 'DASHDOT'
                    {
                    enumLiteral_3=(Token)match(input,93,FollowSets000.FOLLOW_93_in_ruleLineStyle12427); 

                            current = grammarAccess.getLineStyleAccess().getDASHDOTEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getLineStyleAccess().getDASHDOTEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5625:6: (enumLiteral_4= 'DASHDOTDOT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5625:6: (enumLiteral_4= 'DASHDOTDOT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5625:8: enumLiteral_4= 'DASHDOTDOT'
                    {
                    enumLiteral_4=(Token)match(input,94,FollowSets000.FOLLOW_94_in_ruleLineStyle12444); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5635:1: ruleVerticalAlignment returns [Enumerator current=null] : ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) ) ;
    public final Enumerator ruleVerticalAlignment() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5637:28: ( ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5638:1: ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5638:1: ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) )
            int alt225=3;
            switch ( input.LA(1) ) {
            case 95:
                {
                alt225=1;
                }
                break;
            case 96:
                {
                alt225=2;
                }
                break;
            case 97:
                {
                alt225=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 225, 0, input);

                throw nvae;
            }

            switch (alt225) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5638:2: (enumLiteral_0= 'TOP' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5638:2: (enumLiteral_0= 'TOP' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5638:4: enumLiteral_0= 'TOP'
                    {
                    enumLiteral_0=(Token)match(input,95,FollowSets000.FOLLOW_95_in_ruleVerticalAlignment12489); 

                            current = grammarAccess.getVerticalAlignmentAccess().getTOPEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getVerticalAlignmentAccess().getTOPEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5644:6: (enumLiteral_1= 'CENTER' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5644:6: (enumLiteral_1= 'CENTER' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5644:8: enumLiteral_1= 'CENTER'
                    {
                    enumLiteral_1=(Token)match(input,96,FollowSets000.FOLLOW_96_in_ruleVerticalAlignment12506); 

                            current = grammarAccess.getVerticalAlignmentAccess().getCENTEREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getVerticalAlignmentAccess().getCENTEREnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5650:6: (enumLiteral_2= 'BOTTOM' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5650:6: (enumLiteral_2= 'BOTTOM' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5650:8: enumLiteral_2= 'BOTTOM'
                    {
                    enumLiteral_2=(Token)match(input,97,FollowSets000.FOLLOW_97_in_ruleVerticalAlignment12523); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5660:1: ruleHorizontalAlignment returns [Enumerator current=null] : ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) ) ;
    public final Enumerator ruleHorizontalAlignment() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5662:28: ( ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5663:1: ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5663:1: ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) )
            int alt226=3;
            switch ( input.LA(1) ) {
            case 98:
                {
                alt226=1;
                }
                break;
            case 96:
                {
                alt226=2;
                }
                break;
            case 99:
                {
                alt226=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 226, 0, input);

                throw nvae;
            }

            switch (alt226) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5663:2: (enumLiteral_0= 'LEFT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5663:2: (enumLiteral_0= 'LEFT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5663:4: enumLiteral_0= 'LEFT'
                    {
                    enumLiteral_0=(Token)match(input,98,FollowSets000.FOLLOW_98_in_ruleHorizontalAlignment12568); 

                            current = grammarAccess.getHorizontalAlignmentAccess().getLEFTEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getHorizontalAlignmentAccess().getLEFTEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5669:6: (enumLiteral_1= 'CENTER' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5669:6: (enumLiteral_1= 'CENTER' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5669:8: enumLiteral_1= 'CENTER'
                    {
                    enumLiteral_1=(Token)match(input,96,FollowSets000.FOLLOW_96_in_ruleHorizontalAlignment12585); 

                            current = grammarAccess.getHorizontalAlignmentAccess().getCENTEREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getHorizontalAlignmentAccess().getCENTEREnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5675:6: (enumLiteral_2= 'RIGHT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5675:6: (enumLiteral_2= 'RIGHT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5675:8: enumLiteral_2= 'RIGHT'
                    {
                    enumLiteral_2=(Token)match(input,99,FollowSets000.FOLLOW_99_in_ruleHorizontalAlignment12602); 

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
        public static final BitSet FOLLOW_12_in_ruleKRenderingLibrary143 = new BitSet(new long[]{0x00000024EFC8C000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRenderingLibrary165 = new BitSet(new long[]{0x00000024EFC8E000L});
        public static final BitSet FOLLOW_13_in_ruleKRenderingLibrary179 = new BitSet(new long[]{0x00000024EFC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRenderingLibrary202 = new BitSet(new long[]{0x00000024EFC8E000L});
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
        public static final BitSet FOLLOW_12_in_ruleKRenderingRef1708 = new BitSet(new long[]{0x0000000000054000L});
        public static final BitSet FOLLOW_16_in_ruleKRenderingRef1721 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_17_in_ruleKRenderingRef1734 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRenderingRef1757 = new BitSet(new long[]{0x0000000000066000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_13_in_ruleKRenderingRef1771 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRenderingRef1794 = new BitSet(new long[]{0x0000000000066000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_18_in_ruleKRenderingRef1811 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_17_in_ruleKRenderingRef1824 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRenderingRef1847 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKRenderingRef1861 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKEllipse_in_entryRuleKEllipse1899 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKEllipse1909 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_19_in_ruleKEllipse1955 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKEllipse1968 = new BitSet(new long[]{0x0000000000354000L});
        public static final BitSet FOLLOW_16_in_ruleKEllipse1981 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_17_in_ruleKEllipse1994 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKEllipse2017 = new BitSet(new long[]{0x0000000000366000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_13_in_ruleKEllipse2031 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKEllipse2054 = new BitSet(new long[]{0x0000000000366000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_18_in_ruleKEllipse2071 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_17_in_ruleKEllipse2084 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKEllipse2107 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKEllipse2122 = new BitSet(new long[]{0x0000000000020000L,0x000000000000C000L});
        public static final BitSet FOLLOW_17_in_ruleKEllipse2135 = new BitSet(new long[]{0x0000000000020000L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKEllipse2158 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKEllipse2173 = new BitSet(new long[]{0x00000024EFCA8000L});
        public static final BitSet FOLLOW_17_in_ruleKEllipse2186 = new BitSet(new long[]{0x00000024EFC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKEllipse2209 = new BitSet(new long[]{0x00000024EFC8E000L});
        public static final BitSet FOLLOW_13_in_ruleKEllipse2223 = new BitSet(new long[]{0x00000024EFC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKEllipse2246 = new BitSet(new long[]{0x00000024EFC8E000L});
        public static final BitSet FOLLOW_14_in_ruleKEllipse2262 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRectangle_in_entryRuleKRectangle2300 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRectangle2310 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_ruleKRectangle2356 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKRectangle2369 = new BitSet(new long[]{0x0000000000354000L});
        public static final BitSet FOLLOW_16_in_ruleKRectangle2382 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_17_in_ruleKRectangle2395 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRectangle2418 = new BitSet(new long[]{0x0000000000366000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_13_in_ruleKRectangle2432 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRectangle2455 = new BitSet(new long[]{0x0000000000366000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_18_in_ruleKRectangle2472 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_17_in_ruleKRectangle2485 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRectangle2508 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKRectangle2523 = new BitSet(new long[]{0x0000000000020000L,0x000000000000C000L});
        public static final BitSet FOLLOW_17_in_ruleKRectangle2536 = new BitSet(new long[]{0x0000000000020000L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKRectangle2559 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKRectangle2574 = new BitSet(new long[]{0x00000024EFCA8000L});
        public static final BitSet FOLLOW_17_in_ruleKRectangle2587 = new BitSet(new long[]{0x00000024EFC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRectangle2610 = new BitSet(new long[]{0x00000024EFC8E000L});
        public static final BitSet FOLLOW_13_in_ruleKRectangle2624 = new BitSet(new long[]{0x00000024EFC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRectangle2647 = new BitSet(new long[]{0x00000024EFC8E000L});
        public static final BitSet FOLLOW_14_in_ruleKRectangle2663 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRoundedRectangle_in_entryRuleKRoundedRectangle2701 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRoundedRectangle2711 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_23_in_ruleKRoundedRectangle2757 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRoundedRectangle2778 = new BitSet(new long[]{0x0000000010002010L});
        public static final BitSet FOLLOW_13_in_ruleKRoundedRectangle2791 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRoundedRectangle2814 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKRoundedRectangle2827 = new BitSet(new long[]{0x0000000000354000L});
        public static final BitSet FOLLOW_16_in_ruleKRoundedRectangle2840 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_17_in_ruleKRoundedRectangle2853 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRoundedRectangle2876 = new BitSet(new long[]{0x0000000000366000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_13_in_ruleKRoundedRectangle2890 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRoundedRectangle2913 = new BitSet(new long[]{0x0000000000366000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_18_in_ruleKRoundedRectangle2930 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_17_in_ruleKRoundedRectangle2943 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRoundedRectangle2966 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKRoundedRectangle2981 = new BitSet(new long[]{0x0000000000020000L,0x000000000000C000L});
        public static final BitSet FOLLOW_17_in_ruleKRoundedRectangle2994 = new BitSet(new long[]{0x0000000000020000L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKRoundedRectangle3017 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKRoundedRectangle3032 = new BitSet(new long[]{0x00000024EFCA8000L});
        public static final BitSet FOLLOW_17_in_ruleKRoundedRectangle3045 = new BitSet(new long[]{0x00000024EFC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRoundedRectangle3068 = new BitSet(new long[]{0x00000024EFC8E000L});
        public static final BitSet FOLLOW_13_in_ruleKRoundedRectangle3082 = new BitSet(new long[]{0x00000024EFC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRoundedRectangle3105 = new BitSet(new long[]{0x00000024EFC8E000L});
        public static final BitSet FOLLOW_14_in_ruleKRoundedRectangle3121 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolyline_Impl_in_entryRuleKPolyline_Impl3159 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPolyline_Impl3169 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_24_in_ruleKPolyline_Impl3215 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKPolyline_Impl3228 = new BitSet(new long[]{0x0000000000354000L});
        public static final BitSet FOLLOW_16_in_ruleKPolyline_Impl3241 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_17_in_ruleKPolyline_Impl3254 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolyline_Impl3277 = new BitSet(new long[]{0x0000000000366000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_13_in_ruleKPolyline_Impl3291 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolyline_Impl3314 = new BitSet(new long[]{0x0000000000366000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_18_in_ruleKPolyline_Impl3331 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_17_in_ruleKPolyline_Impl3344 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKPolyline_Impl3367 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKPolyline_Impl3382 = new BitSet(new long[]{0x0000000000020000L,0x000000000000C000L});
        public static final BitSet FOLLOW_17_in_ruleKPolyline_Impl3395 = new BitSet(new long[]{0x0000000000020000L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKPolyline_Impl3418 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKPolyline_Impl3433 = new BitSet(new long[]{0x00000024EFCA8000L});
        public static final BitSet FOLLOW_17_in_ruleKPolyline_Impl3446 = new BitSet(new long[]{0x00000024EFC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolyline_Impl3469 = new BitSet(new long[]{0x00000024EFC8E000L});
        public static final BitSet FOLLOW_13_in_ruleKPolyline_Impl3483 = new BitSet(new long[]{0x00000024EFC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolyline_Impl3506 = new BitSet(new long[]{0x00000024EFC8E000L});
        public static final BitSet FOLLOW_14_in_ruleKPolyline_Impl3522 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRoundedBendsPolyline_in_entryRuleKRoundedBendsPolyline3560 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRoundedBendsPolyline3570 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_25_in_ruleKRoundedBendsPolyline3616 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRoundedBendsPolyline3637 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKRoundedBendsPolyline3650 = new BitSet(new long[]{0x0000000000354000L});
        public static final BitSet FOLLOW_16_in_ruleKRoundedBendsPolyline3663 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_17_in_ruleKRoundedBendsPolyline3676 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRoundedBendsPolyline3699 = new BitSet(new long[]{0x0000000000366000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_13_in_ruleKRoundedBendsPolyline3713 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRoundedBendsPolyline3736 = new BitSet(new long[]{0x0000000000366000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_18_in_ruleKRoundedBendsPolyline3753 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_17_in_ruleKRoundedBendsPolyline3766 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRoundedBendsPolyline3789 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKRoundedBendsPolyline3804 = new BitSet(new long[]{0x0000000000020000L,0x000000000000C000L});
        public static final BitSet FOLLOW_17_in_ruleKRoundedBendsPolyline3817 = new BitSet(new long[]{0x0000000000020000L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKRoundedBendsPolyline3840 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKRoundedBendsPolyline3855 = new BitSet(new long[]{0x00000024EFCA8000L});
        public static final BitSet FOLLOW_17_in_ruleKRoundedBendsPolyline3868 = new BitSet(new long[]{0x00000024EFC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRoundedBendsPolyline3891 = new BitSet(new long[]{0x00000024EFC8E000L});
        public static final BitSet FOLLOW_13_in_ruleKRoundedBendsPolyline3905 = new BitSet(new long[]{0x00000024EFC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRoundedBendsPolyline3928 = new BitSet(new long[]{0x00000024EFC8E000L});
        public static final BitSet FOLLOW_14_in_ruleKRoundedBendsPolyline3944 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolygon_in_entryRuleKPolygon3982 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPolygon3992 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_26_in_ruleKPolygon4038 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKPolygon4051 = new BitSet(new long[]{0x0000000000354000L});
        public static final BitSet FOLLOW_16_in_ruleKPolygon4064 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_17_in_ruleKPolygon4077 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolygon4100 = new BitSet(new long[]{0x0000000000366000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_13_in_ruleKPolygon4114 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolygon4137 = new BitSet(new long[]{0x0000000000366000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_18_in_ruleKPolygon4154 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_17_in_ruleKPolygon4167 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKPolygon4190 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKPolygon4205 = new BitSet(new long[]{0x0000000000020000L,0x000000000000C000L});
        public static final BitSet FOLLOW_17_in_ruleKPolygon4218 = new BitSet(new long[]{0x0000000000020000L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKPolygon4241 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKPolygon4256 = new BitSet(new long[]{0x00000024EFCA8000L});
        public static final BitSet FOLLOW_17_in_ruleKPolygon4269 = new BitSet(new long[]{0x00000024EFC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolygon4292 = new BitSet(new long[]{0x00000024EFC8E000L});
        public static final BitSet FOLLOW_13_in_ruleKPolygon4306 = new BitSet(new long[]{0x00000024EFC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolygon4329 = new BitSet(new long[]{0x00000024EFC8E000L});
        public static final BitSet FOLLOW_14_in_ruleKPolygon4345 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKImage_in_entryRuleKImage4383 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKImage4393 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_27_in_ruleKImage4439 = new BitSet(new long[]{0x0000000010000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKImage4461 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_28_in_ruleKImage4479 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKImage4501 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKImage4514 = new BitSet(new long[]{0x0000000000354000L});
        public static final BitSet FOLLOW_16_in_ruleKImage4527 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_17_in_ruleKImage4540 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKImage4563 = new BitSet(new long[]{0x0000000000366000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_13_in_ruleKImage4577 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKImage4600 = new BitSet(new long[]{0x0000000000366000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_18_in_ruleKImage4617 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_17_in_ruleKImage4630 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKImage4653 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKImage4668 = new BitSet(new long[]{0x0000000000020000L,0x000000000000C000L});
        public static final BitSet FOLLOW_17_in_ruleKImage4681 = new BitSet(new long[]{0x0000000000020000L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKImage4704 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKImage4719 = new BitSet(new long[]{0x00000024EFCA8000L});
        public static final BitSet FOLLOW_17_in_ruleKImage4732 = new BitSet(new long[]{0x00000024EFC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKImage4755 = new BitSet(new long[]{0x00000024EFC8E000L});
        public static final BitSet FOLLOW_13_in_ruleKImage4769 = new BitSet(new long[]{0x00000024EFC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKImage4792 = new BitSet(new long[]{0x00000024EFC8E000L});
        public static final BitSet FOLLOW_14_in_ruleKImage4808 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKArc_in_entryRuleKArc4846 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKArc4856 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_29_in_ruleKArc4902 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKArc4923 = new BitSet(new long[]{0x0000000010002010L});
        public static final BitSet FOLLOW_13_in_ruleKArc4936 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKArc4959 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKArc4972 = new BitSet(new long[]{0x0000000000354000L});
        public static final BitSet FOLLOW_16_in_ruleKArc4985 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_17_in_ruleKArc4998 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKArc5021 = new BitSet(new long[]{0x0000000000366000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_13_in_ruleKArc5035 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKArc5058 = new BitSet(new long[]{0x0000000000366000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_18_in_ruleKArc5075 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_17_in_ruleKArc5088 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKArc5111 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKArc5126 = new BitSet(new long[]{0x0000000000020000L,0x000000000000C000L});
        public static final BitSet FOLLOW_17_in_ruleKArc5139 = new BitSet(new long[]{0x0000000000020000L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKArc5162 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKArc5177 = new BitSet(new long[]{0x00000024EFCA8000L});
        public static final BitSet FOLLOW_17_in_ruleKArc5190 = new BitSet(new long[]{0x00000024EFC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKArc5213 = new BitSet(new long[]{0x00000024EFC8E000L});
        public static final BitSet FOLLOW_13_in_ruleKArc5227 = new BitSet(new long[]{0x00000024EFC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKArc5250 = new BitSet(new long[]{0x00000024EFC8E000L});
        public static final BitSet FOLLOW_14_in_ruleKArc5266 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKChildArea_in_entryRuleKChildArea5304 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKChildArea5314 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_30_in_ruleKChildArea5360 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKChildArea5373 = new BitSet(new long[]{0x0000000000054000L});
        public static final BitSet FOLLOW_16_in_ruleKChildArea5386 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_17_in_ruleKChildArea5399 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKChildArea5422 = new BitSet(new long[]{0x0000000000066000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_13_in_ruleKChildArea5436 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKChildArea5459 = new BitSet(new long[]{0x0000000000066000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_18_in_ruleKChildArea5476 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_17_in_ruleKChildArea5489 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKChildArea5512 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKChildArea5526 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKText_in_entryRuleKText5564 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKText5574 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_31_in_ruleKText5620 = new BitSet(new long[]{0x0000000000001062L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKText5641 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKText5655 = new BitSet(new long[]{0x0000000300354000L});
        public static final BitSet FOLLOW_32_in_ruleKText5673 = new BitSet(new long[]{0x0000000200354000L});
        public static final BitSet FOLLOW_16_in_ruleKText5700 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_17_in_ruleKText5713 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKText5736 = new BitSet(new long[]{0x0000000200366000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_13_in_ruleKText5750 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKText5773 = new BitSet(new long[]{0x0000000200366000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_18_in_ruleKText5790 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_17_in_ruleKText5803 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKText5826 = new BitSet(new long[]{0x0000000200304000L});
        public static final BitSet FOLLOW_20_in_ruleKText5841 = new BitSet(new long[]{0x0000000000020000L,0x000000000000C000L});
        public static final BitSet FOLLOW_17_in_ruleKText5854 = new BitSet(new long[]{0x0000000000020000L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKText5877 = new BitSet(new long[]{0x0000000200204000L});
        public static final BitSet FOLLOW_21_in_ruleKText5892 = new BitSet(new long[]{0x00000024EFCA8000L});
        public static final BitSet FOLLOW_17_in_ruleKText5905 = new BitSet(new long[]{0x00000024EFC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKText5928 = new BitSet(new long[]{0x00000026EFC8E000L});
        public static final BitSet FOLLOW_13_in_ruleKText5942 = new BitSet(new long[]{0x00000024EFC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKText5965 = new BitSet(new long[]{0x00000026EFC8E000L});
        public static final BitSet FOLLOW_33_in_ruleKText5982 = new BitSet(new long[]{0x0000000000020060L});
        public static final BitSet FOLLOW_17_in_ruleKText5995 = new BitSet(new long[]{0x0000000000020060L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_ruleKText6018 = new BitSet(new long[]{0x0000000000026060L});
        public static final BitSet FOLLOW_13_in_ruleKText6032 = new BitSet(new long[]{0x0000000000020060L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_ruleKText6055 = new BitSet(new long[]{0x0000000000026060L});
        public static final BitSet FOLLOW_14_in_ruleKText6071 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKCustomRendering_in_entryRuleKCustomRendering6109 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKCustomRendering6119 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_34_in_ruleKCustomRendering6165 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKCustomRendering6178 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_35_in_ruleKCustomRendering6190 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKCustomRendering6211 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_36_in_ruleKCustomRendering6223 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKCustomRendering6244 = new BitSet(new long[]{0x0000000000354000L});
        public static final BitSet FOLLOW_16_in_ruleKCustomRendering6257 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_17_in_ruleKCustomRendering6270 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKCustomRendering6293 = new BitSet(new long[]{0x0000000000366000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_13_in_ruleKCustomRendering6307 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKCustomRendering6330 = new BitSet(new long[]{0x0000000000366000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_18_in_ruleKCustomRendering6347 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_17_in_ruleKCustomRendering6360 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKCustomRendering6383 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKCustomRendering6398 = new BitSet(new long[]{0x0000000000020000L,0x000000000000C000L});
        public static final BitSet FOLLOW_17_in_ruleKCustomRendering6411 = new BitSet(new long[]{0x0000000000020000L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKCustomRendering6434 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKCustomRendering6449 = new BitSet(new long[]{0x00000024EFCA8000L});
        public static final BitSet FOLLOW_17_in_ruleKCustomRendering6462 = new BitSet(new long[]{0x00000024EFC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKCustomRendering6485 = new BitSet(new long[]{0x00000024EFC8E000L});
        public static final BitSet FOLLOW_13_in_ruleKCustomRendering6499 = new BitSet(new long[]{0x00000024EFC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKCustomRendering6522 = new BitSet(new long[]{0x00000024EFC8E000L});
        public static final BitSet FOLLOW_14_in_ruleKCustomRendering6538 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKSpline_in_entryRuleKSpline6576 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKSpline6586 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_37_in_ruleKSpline6632 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKSpline6645 = new BitSet(new long[]{0x0000000000354000L});
        public static final BitSet FOLLOW_16_in_ruleKSpline6658 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_17_in_ruleKSpline6671 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKSpline6694 = new BitSet(new long[]{0x0000000000366000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_13_in_ruleKSpline6708 = new BitSet(new long[]{0x0000000000020000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKSpline6731 = new BitSet(new long[]{0x0000000000366000L,0x0000000000003FFDL});
        public static final BitSet FOLLOW_18_in_ruleKSpline6748 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_17_in_ruleKSpline6761 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKSpline6784 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKSpline6799 = new BitSet(new long[]{0x0000000000020000L,0x000000000000C000L});
        public static final BitSet FOLLOW_17_in_ruleKSpline6812 = new BitSet(new long[]{0x0000000000020000L,0x000000000000C000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKSpline6835 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKSpline6850 = new BitSet(new long[]{0x00000024EFCA8000L});
        public static final BitSet FOLLOW_17_in_ruleKSpline6863 = new BitSet(new long[]{0x00000024EFC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKSpline6886 = new BitSet(new long[]{0x00000024EFC8E000L});
        public static final BitSet FOLLOW_13_in_ruleKSpline6900 = new BitSet(new long[]{0x00000024EFC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKSpline6923 = new BitSet(new long[]{0x00000024EFC8E000L});
        public static final BitSet FOLLOW_14_in_ruleKSpline6939 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDecoratorPlacementData_in_entryRuleKDecoratorPlacementData6977 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKDecoratorPlacementData6987 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_38_in_ruleKDecoratorPlacementData7024 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKDecoratorPlacementData7036 = new BitSet(new long[]{0x0000018000000000L});
        public static final BitSet FOLLOW_39_in_ruleKDecoratorPlacementData7054 = new BitSet(new long[]{0x0000010000000000L});
        public static final BitSet FOLLOW_40_in_ruleKDecoratorPlacementData7080 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7101 = new BitSet(new long[]{0x00001E0000004000L});
        public static final BitSet FOLLOW_41_in_ruleKDecoratorPlacementData7114 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7135 = new BitSet(new long[]{0x00001C0000004000L});
        public static final BitSet FOLLOW_42_in_ruleKDecoratorPlacementData7150 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7171 = new BitSet(new long[]{0x0000180000004000L});
        public static final BitSet FOLLOW_43_in_ruleKDecoratorPlacementData7186 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7207 = new BitSet(new long[]{0x0000100000004000L});
        public static final BitSet FOLLOW_44_in_ruleKDecoratorPlacementData7222 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7243 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKDecoratorPlacementData7257 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacementData_in_entryRuleKGridPlacementData7293 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKGridPlacementData7303 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_45_in_ruleKGridPlacementData7349 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKGridPlacementData7361 = new BitSet(new long[]{0x000FC00000004000L});
        public static final BitSet FOLLOW_46_in_ruleKGridPlacementData7374 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData7395 = new BitSet(new long[]{0x000F800000004000L});
        public static final BitSet FOLLOW_47_in_ruleKGridPlacementData7410 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData7431 = new BitSet(new long[]{0x000F000000004000L});
        public static final BitSet FOLLOW_48_in_ruleKGridPlacementData7446 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData7467 = new BitSet(new long[]{0x000E000000004000L});
        public static final BitSet FOLLOW_49_in_ruleKGridPlacementData7482 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData7503 = new BitSet(new long[]{0x000C000000004000L});
        public static final BitSet FOLLOW_50_in_ruleKGridPlacementData7518 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData7539 = new BitSet(new long[]{0x0008000000004000L});
        public static final BitSet FOLLOW_51_in_ruleKGridPlacementData7554 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData7575 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKGridPlacementData7589 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacementData_in_entryRuleKStackPlacementData7625 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKStackPlacementData7635 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_52_in_ruleKStackPlacementData7681 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKStackPlacementData7693 = new BitSet(new long[]{0x000F000000004000L});
        public static final BitSet FOLLOW_48_in_ruleKStackPlacementData7706 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData7727 = new BitSet(new long[]{0x000E000000004000L});
        public static final BitSet FOLLOW_49_in_ruleKStackPlacementData7742 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData7763 = new BitSet(new long[]{0x000C000000004000L});
        public static final BitSet FOLLOW_50_in_ruleKStackPlacementData7778 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData7799 = new BitSet(new long[]{0x0008000000004000L});
        public static final BitSet FOLLOW_51_in_ruleKStackPlacementData7814 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData7835 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKStackPlacementData7849 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDirectPlacementData_in_entryRuleKDirectPlacementData7885 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKDirectPlacementData7895 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_53_in_ruleKDirectPlacementData7932 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKDirectPlacementData7944 = new BitSet(new long[]{0x0040000000000000L});
        public static final BitSet FOLLOW_54_in_ruleKDirectPlacementData7956 = new BitSet(new long[]{0x3000000000000000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKDirectPlacementData7977 = new BitSet(new long[]{0x0080000000002000L});
        public static final BitSet FOLLOW_13_in_ruleKDirectPlacementData7990 = new BitSet(new long[]{0x0080000000000000L});
        public static final BitSet FOLLOW_55_in_ruleKDirectPlacementData8004 = new BitSet(new long[]{0x3000000000000000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKDirectPlacementData8025 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKDirectPlacementData8037 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolylinePlacementData_in_entryRuleKPolylinePlacementData8073 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPolylinePlacementData8083 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_56_in_ruleKPolylinePlacementData8120 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPolylinePlacementData8132 = new BitSet(new long[]{0x0200000000000000L});
        public static final BitSet FOLLOW_57_in_ruleKPolylinePlacementData8144 = new BitSet(new long[]{0x3000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleKPolylinePlacementData8157 = new BitSet(new long[]{0x3000000000000000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData8180 = new BitSet(new long[]{0x3400000000006000L});
        public static final BitSet FOLLOW_13_in_ruleKPolylinePlacementData8194 = new BitSet(new long[]{0x3000000000000000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData8217 = new BitSet(new long[]{0x3400000000006000L});
        public static final BitSet FOLLOW_58_in_ruleKPolylinePlacementData8232 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_17_in_ruleKPolylinePlacementData8245 = new BitSet(new long[]{0x0130204000020000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKPolylinePlacementData8268 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKPolylinePlacementData8282 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPosition_in_entryRuleKPosition8318 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPosition8328 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKXPosition_in_ruleKPosition8374 = new BitSet(new long[]{0x0800000000000000L});
        public static final BitSet FOLLOW_59_in_ruleKPosition8386 = new BitSet(new long[]{0xC000000000000000L});
        public static final BitSet FOLLOW_ruleKYPosition_in_ruleKPosition8407 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLeftPosition_in_entryRuleKLeftPosition8443 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLeftPosition8453 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_60_in_ruleKLeftPosition8499 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKLeftPosition8520 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKLeftPosition8541 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRightPosition_in_entryRuleKRightPosition8577 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRightPosition8587 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_61_in_ruleKRightPosition8633 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRightPosition8654 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRightPosition8675 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKTopPosition_in_entryRuleKTopPosition8711 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKTopPosition8721 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_62_in_ruleKTopPosition8767 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKTopPosition8788 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKTopPosition8809 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBottomPosition_in_entryRuleKBottomPosition8845 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKBottomPosition8855 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_63_in_ruleKBottomPosition8901 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKBottomPosition8922 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKBottomPosition8943 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForegroundColor_in_entryRuleKForegroundColor8979 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKForegroundColor8989 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_64_in_ruleKForegroundColor9035 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKForegroundColor9056 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKForegroundColor9077 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKForegroundColor9098 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKForegroundColor9116 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackgroundColor_in_entryRuleKBackgroundColor9166 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKBackgroundColor9176 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_66_in_ruleKBackgroundColor9222 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKBackgroundColor9243 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKBackgroundColor9264 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKBackgroundColor9285 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKBackgroundColor9303 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineWidth_in_entryRuleKLineWidth9353 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLineWidth9363 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_67_in_ruleKLineWidth9400 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKLineWidth9421 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKLineWidth9439 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVisibility_in_entryRuleKVisibility9489 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKVisibility9499 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForegroundVisibility_in_ruleKVisibility9546 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackgroundVisibility_in_ruleKVisibility9573 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForegroundVisibility_in_entryRuleKForegroundVisibility9608 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKForegroundVisibility9618 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_68_in_ruleKForegroundVisibility9664 = new BitSet(new long[]{0x0000000000000000L,0x0000000000180000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleKForegroundVisibility9685 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKForegroundVisibility9703 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackgroundVisibility_in_entryRuleKBackgroundVisibility9753 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKBackgroundVisibility9763 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_69_in_ruleKBackgroundVisibility9809 = new BitSet(new long[]{0x0000000000000000L,0x0000000000180000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleKBackgroundVisibility9830 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKBackgroundVisibility9848 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineStyle_in_entryRuleKLineStyle9898 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLineStyle9908 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_70_in_ruleKLineStyle9954 = new BitSet(new long[]{0x0000000000000000L,0x000000007C000000L});
        public static final BitSet FOLLOW_ruleLineStyle_in_ruleKLineStyle9975 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKLineStyle9993 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRotation_in_entryRuleKRotation10043 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRotation10053 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_71_in_ruleKRotation10099 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRotation10120 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKRotation10138 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontBold_in_entryRuleKFontBold10188 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKFontBold10198 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_72_in_ruleKFontBold10250 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKFontBold10281 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontItalic_in_entryRuleKFontItalic10331 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKFontItalic10341 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_73_in_ruleKFontItalic10393 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKFontItalic10424 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontName_in_entryRuleKFontName10474 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKFontName10484 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_74_in_ruleKFontName10521 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKFontName10542 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKFontName10560 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontSize_in_entryRuleKFontSize10610 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKFontSize10620 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_75_in_ruleKFontSize10657 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKFontSize10678 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKFontSize10696 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVerticalAlignment_in_entryRuleKVerticalAlignment10746 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKVerticalAlignment10756 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_76_in_ruleKVerticalAlignment10802 = new BitSet(new long[]{0x0000000000000000L,0x0000000380000000L});
        public static final BitSet FOLLOW_ruleVerticalAlignment_in_ruleKVerticalAlignment10823 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKVerticalAlignment10841 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKHorizontalAlignment_in_entryRuleKHorizontalAlignment10891 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKHorizontalAlignment10901 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_77_in_ruleKHorizontalAlignment10947 = new BitSet(new long[]{0x0000000000000000L,0x0000000D00000000L});
        public static final BitSet FOLLOW_ruleHorizontalAlignment_in_ruleKHorizontalAlignment10968 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKHorizontalAlignment10986 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacement_in_entryRuleKGridPlacement11036 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKGridPlacement11046 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_78_in_ruleKGridPlacement11092 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKGridPlacement11113 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacement_in_entryRuleKStackPlacement11149 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKStackPlacement11159 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_79_in_ruleKStackPlacement11205 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_entryRuleEFloat11242 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEFloat11253 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_28_in_ruleEFloat11292 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat11309 = new BitSet(new long[]{0x0000000000000002L,0x0000000000010000L});
        public static final BitSet FOLLOW_80_in_ruleEFloat11328 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat11343 = new BitSet(new long[]{0x0000000000000002L,0x0000000000060000L});
        public static final BitSet FOLLOW_81_in_ruleEFloat11363 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_82_in_ruleEFloat11382 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_28_in_ruleEFloat11397 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat11414 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEBoolean_in_entryRuleEBoolean11464 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEBoolean11475 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_83_in_ruleEBoolean11513 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_84_in_ruleEBoolean11532 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEInt_in_entryRuleEInt11573 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEInt11584 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_28_in_ruleEInt11623 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEInt11640 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKInsets_in_entryRuleKInsets11687 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKInsets11697 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_85_in_ruleKInsets11743 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKInsets11755 = new BitSet(new long[]{0xF000000000004000L});
        public static final BitSet FOLLOW_62_in_ruleKInsets11768 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets11789 = new BitSet(new long[]{0xB000000000004000L});
        public static final BitSet FOLLOW_63_in_ruleKInsets11804 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets11825 = new BitSet(new long[]{0x3000000000004000L});
        public static final BitSet FOLLOW_60_in_ruleKInsets11840 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets11861 = new BitSet(new long[]{0x2000000000004000L});
        public static final BitSet FOLLOW_61_in_ruleKInsets11876 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets11897 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKInsets11911 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPoint_in_entryRuleKPoint11949 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPoint11959 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_86_in_ruleKPoint12005 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
        public static final BitSet FOLLOW_87_in_ruleKPoint12018 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKPoint12039 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
        public static final BitSet FOLLOW_88_in_ruleKPoint12053 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKPoint12074 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry12111 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRulePersistentEntry12121 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_rulePersistentEntry12167 = new BitSet(new long[]{0x0000000000000002L,0x0000000002000000L});
        public static final BitSet FOLLOW_89_in_rulePersistentEntry12180 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_rulePersistentEntry12201 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_entryRuleEString12240 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEString12251 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleEString12291 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleEString12317 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_90_in_ruleLineStyle12376 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_91_in_ruleLineStyle12393 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_92_in_ruleLineStyle12410 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_93_in_ruleLineStyle12427 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_94_in_ruleLineStyle12444 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_95_in_ruleVerticalAlignment12489 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_96_in_ruleVerticalAlignment12506 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_97_in_ruleVerticalAlignment12523 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_98_in_ruleHorizontalAlignment12568 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_96_in_ruleHorizontalAlignment12585 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_99_in_ruleHorizontalAlignment12602 = new BitSet(new long[]{0x0000000000000002L});
    }


}