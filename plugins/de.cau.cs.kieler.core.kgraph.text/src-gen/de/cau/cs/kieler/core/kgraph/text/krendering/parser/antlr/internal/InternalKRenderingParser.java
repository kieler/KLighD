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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INT", "RULE_STRING", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'RenderingLibrary'", "'{'", "','", "'}'", "'RenderingRef'", "'styles'", "':'", "'placementData'", "'Ellipse'", "'childPlacement'", "'children'", "'Rectangle'", "'RoundedRectangle'", "'Polyline'", "'points'", "'RoundedBendsPolyline'", "'Polygon'", "'Image'", "'-'", "'Arc'", "'ChildArea'", "'Text'", "'mapProperties'", "'CustomRendering'", "'className'", "'bundleName'", "'Spline'", "'DecoratorPlacementData'", "'rotateWithLine'", "'absolute'", "'relative'", "'xOffset'", "'yOffset'", "'width'", "'height'", "'GridPlacementData'", "'minCellWidth'", "'minCellHeight'", "'maxCellWidth'", "'maxCellHeight'", "'topLeft'", "'bottomRight'", "'AreaPlacementData'", "'PointPlacementData'", "'referencePoint'", "'verticalAlignment'", "'horizontalAlignment'", "'/'", "'left'", "'right'", "'top'", "'bottom'", "'color'", "'!'", "'KBackground'", "'targetColor'", "'alpha'", "'targetAlpha'", "'gradientAngle'", "'KForeground'", "'invisible'", "'lineWidth'", "'modifier'", "'='", "'lineStyle'", "'lineCapStyle'", "'rotation'", "'bold'", "'italic'", "'font'", "'fontSize'", "'gridPlacement'", "'.'", "'E'", "'e'", "'KInsets'", "'KPoint'", "'x'", "'y'", "'SOLID'", "'DASH'", "'DOT'", "'DASHDOT'", "'DASHDOTDOT'", "'CAP_FLAT'", "'CAP_ROUND'", "'CAP_SQUARE'", "'TOP'", "'CENTER'", "'BOTTOM'", "'LEFT'", "'RIGHT'"
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

            if ( (LA3_0==15||LA3_0==19||(LA3_0>=22 && LA3_0<=24)||(LA3_0>=26 && LA3_0<=28)||(LA3_0>=30 && LA3_0<=32)||LA3_0==34||LA3_0==37) ) {
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

                        if ( (LA2_0==13||LA2_0==15||LA2_0==19||(LA2_0>=22 && LA2_0<=24)||(LA2_0>=26 && LA2_0<=28)||(LA2_0>=30 && LA2_0<=32)||LA2_0==34||LA2_0==37) ) {
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
            case 27:
                {
                alt4=5;
                }
                break;
            case 28:
                {
                alt4=6;
                }
                break;
            case 30:
                {
                alt4=7;
                }
                break;
            case 15:
                {
                alt4=8;
                }
                break;
            case 31:
                {
                alt4=9;
                }
                break;
            case 32:
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
            case 26:
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:306:1: ruleKPlacementData returns [EObject current=null] : (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KAreaPlacementData_2= ruleKAreaPlacementData | this_KPointPlacementData_3= ruleKPointPlacementData ) ;
    public final EObject ruleKPlacementData() throws RecognitionException {
        EObject current = null;

        EObject this_KDecoratorPlacementData_0 = null;

        EObject this_KGridPlacementData_1 = null;

        EObject this_KAreaPlacementData_2 = null;

        EObject this_KPointPlacementData_3 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:309:28: ( (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KAreaPlacementData_2= ruleKAreaPlacementData | this_KPointPlacementData_3= ruleKPointPlacementData ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:310:1: (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KAreaPlacementData_2= ruleKAreaPlacementData | this_KPointPlacementData_3= ruleKPointPlacementData )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:310:1: (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KAreaPlacementData_2= ruleKAreaPlacementData | this_KPointPlacementData_3= ruleKPointPlacementData )
            int alt5=4;
            switch ( input.LA(1) ) {
            case 38:
                {
                alt5=1;
                }
                break;
            case 46:
                {
                alt5=2;
                }
                break;
            case 53:
                {
                alt5=3;
                }
                break;
            case 54:
                {
                alt5=4;
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
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:331:5: this_KAreaPlacementData_2= ruleKAreaPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKAreaPlacementDataParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKAreaPlacementData_in_ruleKPlacementData781);
                    this_KAreaPlacementData_2=ruleKAreaPlacementData();

                    state._fsp--;

                     
                            current = this_KAreaPlacementData_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:341:5: this_KPointPlacementData_3= ruleKPointPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKPointPlacementDataParserRuleCall_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKPointPlacementData_in_ruleKPlacementData808);
                    this_KPointPlacementData_3=ruleKPointPlacementData();

                    state._fsp--;

                     
                            current = this_KPointPlacementData_3; 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:357:1: entryRuleKStyle returns [EObject current=null] : iv_ruleKStyle= ruleKStyle EOF ;
    public final EObject entryRuleKStyle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStyle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:358:2: (iv_ruleKStyle= ruleKStyle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:359:2: iv_ruleKStyle= ruleKStyle EOF
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:366:1: ruleKStyle returns [EObject current=null] : (this_KLineWidth_0= ruleKLineWidth | this_KForeground_1= ruleKForeground | this_KBackground_2= ruleKBackground | this_KVisibility_3= ruleKVisibility | this_KLineStyle_4= ruleKLineStyle | this_KLineCapStyle_5= ruleKLineCapStyle | this_KRotation_6= ruleKRotation | this_KFontBold_7= ruleKFontBold | this_KFontItalic_8= ruleKFontItalic | this_KFontName_9= ruleKFontName | this_KFontSize_10= ruleKFontSize | this_KVerticalAlignment_11= ruleKVerticalAlignment | this_KHorizontalAlignment_12= ruleKHorizontalAlignment ) ;
    public final EObject ruleKStyle() throws RecognitionException {
        EObject current = null;

        EObject this_KLineWidth_0 = null;

        EObject this_KForeground_1 = null;

        EObject this_KBackground_2 = null;

        EObject this_KVisibility_3 = null;

        EObject this_KLineStyle_4 = null;

        EObject this_KLineCapStyle_5 = null;

        EObject this_KRotation_6 = null;

        EObject this_KFontBold_7 = null;

        EObject this_KFontItalic_8 = null;

        EObject this_KFontName_9 = null;

        EObject this_KFontSize_10 = null;

        EObject this_KVerticalAlignment_11 = null;

        EObject this_KHorizontalAlignment_12 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:369:28: ( (this_KLineWidth_0= ruleKLineWidth | this_KForeground_1= ruleKForeground | this_KBackground_2= ruleKBackground | this_KVisibility_3= ruleKVisibility | this_KLineStyle_4= ruleKLineStyle | this_KLineCapStyle_5= ruleKLineCapStyle | this_KRotation_6= ruleKRotation | this_KFontBold_7= ruleKFontBold | this_KFontItalic_8= ruleKFontItalic | this_KFontName_9= ruleKFontName | this_KFontSize_10= ruleKFontSize | this_KVerticalAlignment_11= ruleKVerticalAlignment | this_KHorizontalAlignment_12= ruleKHorizontalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:370:1: (this_KLineWidth_0= ruleKLineWidth | this_KForeground_1= ruleKForeground | this_KBackground_2= ruleKBackground | this_KVisibility_3= ruleKVisibility | this_KLineStyle_4= ruleKLineStyle | this_KLineCapStyle_5= ruleKLineCapStyle | this_KRotation_6= ruleKRotation | this_KFontBold_7= ruleKFontBold | this_KFontItalic_8= ruleKFontItalic | this_KFontName_9= ruleKFontName | this_KFontSize_10= ruleKFontSize | this_KVerticalAlignment_11= ruleKVerticalAlignment | this_KHorizontalAlignment_12= ruleKHorizontalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:370:1: (this_KLineWidth_0= ruleKLineWidth | this_KForeground_1= ruleKForeground | this_KBackground_2= ruleKBackground | this_KVisibility_3= ruleKVisibility | this_KLineStyle_4= ruleKLineStyle | this_KLineCapStyle_5= ruleKLineCapStyle | this_KRotation_6= ruleKRotation | this_KFontBold_7= ruleKFontBold | this_KFontItalic_8= ruleKFontItalic | this_KFontName_9= ruleKFontName | this_KFontSize_10= ruleKFontSize | this_KVerticalAlignment_11= ruleKVerticalAlignment | this_KHorizontalAlignment_12= ruleKHorizontalAlignment )
            int alt6=13;
            switch ( input.LA(1) ) {
            case 72:
                {
                alt6=1;
                }
                break;
            case 70:
                {
                alt6=2;
                }
                break;
            case 65:
                {
                alt6=3;
                }
                break;
            case 71:
                {
                alt6=4;
                }
                break;
            case 75:
                {
                alt6=5;
                }
                break;
            case 76:
                {
                alt6=6;
                }
                break;
            case 77:
                {
                alt6=7;
                }
                break;
            case 78:
                {
                alt6=8;
                }
                break;
            case 79:
                {
                alt6=9;
                }
                break;
            case 80:
                {
                alt6=10;
                }
                break;
            case 81:
                {
                alt6=11;
                }
                break;
            case 56:
                {
                alt6=12;
                }
                break;
            case 57:
                {
                alt6=13;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:371:5: this_KLineWidth_0= ruleKLineWidth
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKLineWidthParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKLineWidth_in_ruleKStyle900);
                    this_KLineWidth_0=ruleKLineWidth();

                    state._fsp--;

                     
                            current = this_KLineWidth_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:381:5: this_KForeground_1= ruleKForeground
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKForegroundParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKForeground_in_ruleKStyle927);
                    this_KForeground_1=ruleKForeground();

                    state._fsp--;

                     
                            current = this_KForeground_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:391:5: this_KBackground_2= ruleKBackground
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKBackgroundParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKBackground_in_ruleKStyle954);
                    this_KBackground_2=ruleKBackground();

                    state._fsp--;

                     
                            current = this_KBackground_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:401:5: this_KVisibility_3= ruleKVisibility
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
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:411:5: this_KLineStyle_4= ruleKLineStyle
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
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:421:5: this_KLineCapStyle_5= ruleKLineCapStyle
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKLineCapStyleParserRuleCall_5()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKLineCapStyle_in_ruleKStyle1035);
                    this_KLineCapStyle_5=ruleKLineCapStyle();

                    state._fsp--;

                     
                            current = this_KLineCapStyle_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:431:5: this_KRotation_6= ruleKRotation
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKRotationParserRuleCall_6()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRotation_in_ruleKStyle1062);
                    this_KRotation_6=ruleKRotation();

                    state._fsp--;

                     
                            current = this_KRotation_6; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 8 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:441:5: this_KFontBold_7= ruleKFontBold
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKFontBoldParserRuleCall_7()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKFontBold_in_ruleKStyle1089);
                    this_KFontBold_7=ruleKFontBold();

                    state._fsp--;

                     
                            current = this_KFontBold_7; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 9 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:451:5: this_KFontItalic_8= ruleKFontItalic
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKFontItalicParserRuleCall_8()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKFontItalic_in_ruleKStyle1116);
                    this_KFontItalic_8=ruleKFontItalic();

                    state._fsp--;

                     
                            current = this_KFontItalic_8; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 10 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:461:5: this_KFontName_9= ruleKFontName
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKFontNameParserRuleCall_9()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKFontName_in_ruleKStyle1143);
                    this_KFontName_9=ruleKFontName();

                    state._fsp--;

                     
                            current = this_KFontName_9; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 11 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:471:5: this_KFontSize_10= ruleKFontSize
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKFontSizeParserRuleCall_10()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKFontSize_in_ruleKStyle1170);
                    this_KFontSize_10=ruleKFontSize();

                    state._fsp--;

                     
                            current = this_KFontSize_10; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 12 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:481:5: this_KVerticalAlignment_11= ruleKVerticalAlignment
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKVerticalAlignmentParserRuleCall_11()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKVerticalAlignment_in_ruleKStyle1197);
                    this_KVerticalAlignment_11=ruleKVerticalAlignment();

                    state._fsp--;

                     
                            current = this_KVerticalAlignment_11; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 13 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:491:5: this_KHorizontalAlignment_12= ruleKHorizontalAlignment
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKHorizontalAlignmentParserRuleCall_12()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKHorizontalAlignment_in_ruleKStyle1224);
                    this_KHorizontalAlignment_12=ruleKHorizontalAlignment();

                    state._fsp--;

                     
                            current = this_KHorizontalAlignment_12; 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:516:1: ruleKPlacement returns [EObject current=null] : this_KGridPlacement_0= ruleKGridPlacement ;
    public final EObject ruleKPlacement() throws RecognitionException {
        EObject current = null;

        EObject this_KGridPlacement_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:519:28: (this_KGridPlacement_0= ruleKGridPlacement )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:521:5: this_KGridPlacement_0= ruleKGridPlacement
            {
             
                    newCompositeNode(grammarAccess.getKPlacementAccess().getKGridPlacementParserRuleCall()); 
                
            pushFollow(FollowSets000.FOLLOW_ruleKGridPlacement_in_ruleKPlacement1315);
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


    // $ANTLR start "entryRuleKXPosition"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:537:1: entryRuleKXPosition returns [EObject current=null] : iv_ruleKXPosition= ruleKXPosition EOF ;
    public final EObject entryRuleKXPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKXPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:538:2: (iv_ruleKXPosition= ruleKXPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:539:2: iv_ruleKXPosition= ruleKXPosition EOF
            {
             newCompositeNode(grammarAccess.getKXPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKXPosition_in_entryRuleKXPosition1349);
            iv_ruleKXPosition=ruleKXPosition();

            state._fsp--;

             current =iv_ruleKXPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKXPosition1359); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:546:1: ruleKXPosition returns [EObject current=null] : (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition ) ;
    public final EObject ruleKXPosition() throws RecognitionException {
        EObject current = null;

        EObject this_KLeftPosition_0 = null;

        EObject this_KRightPosition_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:549:28: ( (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:550:1: (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:550:1: (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==59) ) {
                alt7=1;
            }
            else if ( (LA7_0==60) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:551:5: this_KLeftPosition_0= ruleKLeftPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKXPositionAccess().getKLeftPositionParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKLeftPosition_in_ruleKXPosition1406);
                    this_KLeftPosition_0=ruleKLeftPosition();

                    state._fsp--;

                     
                            current = this_KLeftPosition_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:561:5: this_KRightPosition_1= ruleKRightPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKXPositionAccess().getKRightPositionParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRightPosition_in_ruleKXPosition1433);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:577:1: entryRuleKYPosition returns [EObject current=null] : iv_ruleKYPosition= ruleKYPosition EOF ;
    public final EObject entryRuleKYPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKYPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:578:2: (iv_ruleKYPosition= ruleKYPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:579:2: iv_ruleKYPosition= ruleKYPosition EOF
            {
             newCompositeNode(grammarAccess.getKYPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKYPosition_in_entryRuleKYPosition1468);
            iv_ruleKYPosition=ruleKYPosition();

            state._fsp--;

             current =iv_ruleKYPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKYPosition1478); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:586:1: ruleKYPosition returns [EObject current=null] : (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition ) ;
    public final EObject ruleKYPosition() throws RecognitionException {
        EObject current = null;

        EObject this_KTopPosition_0 = null;

        EObject this_KBottomPosition_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:589:28: ( (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:590:1: (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:590:1: (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==61) ) {
                alt8=1;
            }
            else if ( (LA8_0==62) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:591:5: this_KTopPosition_0= ruleKTopPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKYPositionAccess().getKTopPositionParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKTopPosition_in_ruleKYPosition1525);
                    this_KTopPosition_0=ruleKTopPosition();

                    state._fsp--;

                     
                            current = this_KTopPosition_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:601:5: this_KBottomPosition_1= ruleKBottomPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKYPositionAccess().getKBottomPositionParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKBottomPosition_in_ruleKYPosition1552);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:617:1: entryRuleKRenderingRef returns [EObject current=null] : iv_ruleKRenderingRef= ruleKRenderingRef EOF ;
    public final EObject entryRuleKRenderingRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRenderingRef = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:618:2: (iv_ruleKRenderingRef= ruleKRenderingRef EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:619:2: iv_ruleKRenderingRef= ruleKRenderingRef EOF
            {
             newCompositeNode(grammarAccess.getKRenderingRefRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRenderingRef_in_entryRuleKRenderingRef1587);
            iv_ruleKRenderingRef=ruleKRenderingRef();

            state._fsp--;

             current =iv_ruleKRenderingRef; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRenderingRef1597); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:626:1: ruleKRenderingRef returns [EObject current=null] : ( () otherlv_1= 'RenderingRef' ( ( ruleEString ) ) (otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? otherlv_12= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:629:28: ( ( () otherlv_1= 'RenderingRef' ( ( ruleEString ) ) (otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? otherlv_12= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:630:1: ( () otherlv_1= 'RenderingRef' ( ( ruleEString ) ) (otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? otherlv_12= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:630:1: ( () otherlv_1= 'RenderingRef' ( ( ruleEString ) ) (otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? otherlv_12= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:630:2: () otherlv_1= 'RenderingRef' ( ( ruleEString ) ) (otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? otherlv_12= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:630:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:631:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRenderingRefAccess().getKRenderingRefAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKRenderingRef1643); 

                	newLeafNode(otherlv_1, grammarAccess.getKRenderingRefAccess().getRenderingRefKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:640:1: ( ( ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:641:1: ( ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:641:1: ( ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:642:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getKRenderingRefRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getRenderingKRenderingCrossReference_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKRenderingRef1666);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:655:2: (otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? otherlv_12= '}' )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==12) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:655:4: otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? otherlv_12= '}'
                    {
                    otherlv_3=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRenderingRef1679); 

                        	newLeafNode(otherlv_3, grammarAccess.getKRenderingRefAccess().getLeftCurlyBracketKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:659:1: (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==16) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:659:3: otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )*
                            {
                            otherlv_4=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRenderingRef1692); 

                                	newLeafNode(otherlv_4, grammarAccess.getKRenderingRefAccess().getStylesKeyword_3_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:663:1: (otherlv_5= ':' )?
                            int alt9=2;
                            int LA9_0 = input.LA(1);

                            if ( (LA9_0==17) ) {
                                alt9=1;
                            }
                            switch (alt9) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:663:3: otherlv_5= ':'
                                    {
                                    otherlv_5=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRenderingRef1705); 

                                        	newLeafNode(otherlv_5, grammarAccess.getKRenderingRefAccess().getColonKeyword_3_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:667:3: ( (lv_styles_6_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:668:1: (lv_styles_6_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:668:1: (lv_styles_6_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:669:3: lv_styles_6_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getStylesKStyleParserRuleCall_3_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRenderingRef1728);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:685:2: ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )*
                            loop11:
                            do {
                                int alt11=2;
                                int LA11_0 = input.LA(1);

                                if ( (LA11_0==13||(LA11_0>=56 && LA11_0<=57)||LA11_0==65||(LA11_0>=70 && LA11_0<=72)||(LA11_0>=75 && LA11_0<=81)) ) {
                                    alt11=1;
                                }


                                switch (alt11) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:685:3: (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:685:3: (otherlv_7= ',' )?
                            	    int alt10=2;
                            	    int LA10_0 = input.LA(1);

                            	    if ( (LA10_0==13) ) {
                            	        alt10=1;
                            	    }
                            	    switch (alt10) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:685:5: otherlv_7= ','
                            	            {
                            	            otherlv_7=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRenderingRef1742); 

                            	                	newLeafNode(otherlv_7, grammarAccess.getKRenderingRefAccess().getCommaKeyword_3_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:689:3: ( (lv_styles_8_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:690:1: (lv_styles_8_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:690:1: (lv_styles_8_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:691:3: lv_styles_8_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getStylesKStyleParserRuleCall_3_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRenderingRef1765);
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
                            	    break loop11;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:707:6: (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==18) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:707:8: otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) )
                            {
                            otherlv_9=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRenderingRef1782); 

                                	newLeafNode(otherlv_9, grammarAccess.getKRenderingRefAccess().getPlacementDataKeyword_3_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:711:1: (otherlv_10= ':' )?
                            int alt13=2;
                            int LA13_0 = input.LA(1);

                            if ( (LA13_0==17) ) {
                                alt13=1;
                            }
                            switch (alt13) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:711:3: otherlv_10= ':'
                                    {
                                    otherlv_10=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRenderingRef1795); 

                                        	newLeafNode(otherlv_10, grammarAccess.getKRenderingRefAccess().getColonKeyword_3_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:715:3: ( (lv_placementData_11_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:716:1: (lv_placementData_11_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:716:1: (lv_placementData_11_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:717:3: lv_placementData_11_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getPlacementDataKPlacementDataParserRuleCall_3_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRenderingRef1818);
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

                    otherlv_12=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRenderingRef1832); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:745:1: entryRuleKEllipse returns [EObject current=null] : iv_ruleKEllipse= ruleKEllipse EOF ;
    public final EObject entryRuleKEllipse() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKEllipse = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:746:2: (iv_ruleKEllipse= ruleKEllipse EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:747:2: iv_ruleKEllipse= ruleKEllipse EOF
            {
             newCompositeNode(grammarAccess.getKEllipseRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKEllipse_in_entryRuleKEllipse1870);
            iv_ruleKEllipse=ruleKEllipse();

            state._fsp--;

             current =iv_ruleKEllipse; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKEllipse1880); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:754:1: ruleKEllipse returns [EObject current=null] : ( () otherlv_1= 'Ellipse' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:757:28: ( ( () otherlv_1= 'Ellipse' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:758:1: ( () otherlv_1= 'Ellipse' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:758:1: ( () otherlv_1= 'Ellipse' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:758:2: () otherlv_1= 'Ellipse' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:758:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:759:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKEllipseAccess().getKEllipseAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKEllipse1926); 

                	newLeafNode(otherlv_1, grammarAccess.getKEllipseAccess().getEllipseKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:768:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==12) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:768:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKEllipse1939); 

                        	newLeafNode(otherlv_2, grammarAccess.getKEllipseAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:772:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==16) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:772:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKEllipse1952); 

                                	newLeafNode(otherlv_3, grammarAccess.getKEllipseAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:776:1: (otherlv_4= ':' )?
                            int alt16=2;
                            int LA16_0 = input.LA(1);

                            if ( (LA16_0==17) ) {
                                alt16=1;
                            }
                            switch (alt16) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:776:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKEllipse1965); 

                                        	newLeafNode(otherlv_4, grammarAccess.getKEllipseAccess().getColonKeyword_2_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:780:3: ( (lv_styles_5_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:781:1: (lv_styles_5_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:781:1: (lv_styles_5_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:782:3: lv_styles_5_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKEllipseAccess().getStylesKStyleParserRuleCall_2_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKEllipse1988);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:798:2: ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            loop18:
                            do {
                                int alt18=2;
                                int LA18_0 = input.LA(1);

                                if ( (LA18_0==13||(LA18_0>=56 && LA18_0<=57)||LA18_0==65||(LA18_0>=70 && LA18_0<=72)||(LA18_0>=75 && LA18_0<=81)) ) {
                                    alt18=1;
                                }


                                switch (alt18) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:798:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:798:3: (otherlv_6= ',' )?
                            	    int alt17=2;
                            	    int LA17_0 = input.LA(1);

                            	    if ( (LA17_0==13) ) {
                            	        alt17=1;
                            	    }
                            	    switch (alt17) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:798:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKEllipse2002); 

                            	                	newLeafNode(otherlv_6, grammarAccess.getKEllipseAccess().getCommaKeyword_2_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:802:3: ( (lv_styles_7_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:803:1: (lv_styles_7_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:803:1: (lv_styles_7_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:804:3: lv_styles_7_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKEllipseAccess().getStylesKStyleParserRuleCall_2_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKEllipse2025);
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
                            	    break loop18;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:820:6: (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==18) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:820:8: otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKEllipse2042); 

                                	newLeafNode(otherlv_8, grammarAccess.getKEllipseAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:824:1: (otherlv_9= ':' )?
                            int alt20=2;
                            int LA20_0 = input.LA(1);

                            if ( (LA20_0==17) ) {
                                alt20=1;
                            }
                            switch (alt20) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:824:3: otherlv_9= ':'
                                    {
                                    otherlv_9=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKEllipse2055); 

                                        	newLeafNode(otherlv_9, grammarAccess.getKEllipseAccess().getColonKeyword_2_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:828:3: ( (lv_placementData_10_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:829:1: (lv_placementData_10_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:829:1: (lv_placementData_10_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:830:3: lv_placementData_10_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKEllipseAccess().getPlacementDataKPlacementDataParserRuleCall_2_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKEllipse2078);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:846:4: (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==20) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:846:6: otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            {
                            otherlv_11=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKEllipse2093); 

                                	newLeafNode(otherlv_11, grammarAccess.getKEllipseAccess().getChildPlacementKeyword_2_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:850:1: (otherlv_12= ':' )?
                            int alt22=2;
                            int LA22_0 = input.LA(1);

                            if ( (LA22_0==17) ) {
                                alt22=1;
                            }
                            switch (alt22) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:850:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKEllipse2106); 

                                        	newLeafNode(otherlv_12, grammarAccess.getKEllipseAccess().getColonKeyword_2_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:854:3: ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:855:1: (lv_childPlacement_13_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:855:1: (lv_childPlacement_13_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:856:3: lv_childPlacement_13_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKEllipseAccess().getChildPlacementKPlacementParserRuleCall_2_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKEllipse2129);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:872:4: (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )?
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==21) ) {
                        alt27=1;
                    }
                    switch (alt27) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:872:6: otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            {
                            otherlv_14=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKEllipse2144); 

                                	newLeafNode(otherlv_14, grammarAccess.getKEllipseAccess().getChildrenKeyword_2_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:876:1: (otherlv_15= ':' )?
                            int alt24=2;
                            int LA24_0 = input.LA(1);

                            if ( (LA24_0==17) ) {
                                alt24=1;
                            }
                            switch (alt24) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:876:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKEllipse2157); 

                                        	newLeafNode(otherlv_15, grammarAccess.getKEllipseAccess().getColonKeyword_2_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:880:3: ( (lv_children_16_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:881:1: (lv_children_16_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:881:1: (lv_children_16_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:882:3: lv_children_16_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKEllipseAccess().getChildrenKRenderingParserRuleCall_2_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKEllipse2180);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:898:2: ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            loop26:
                            do {
                                int alt26=2;
                                int LA26_0 = input.LA(1);

                                if ( (LA26_0==13||LA26_0==15||LA26_0==19||(LA26_0>=22 && LA26_0<=24)||(LA26_0>=26 && LA26_0<=28)||(LA26_0>=30 && LA26_0<=32)||LA26_0==34||LA26_0==37) ) {
                                    alt26=1;
                                }


                                switch (alt26) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:898:3: (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:898:3: (otherlv_17= ',' )?
                            	    int alt25=2;
                            	    int LA25_0 = input.LA(1);

                            	    if ( (LA25_0==13) ) {
                            	        alt25=1;
                            	    }
                            	    switch (alt25) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:898:5: otherlv_17= ','
                            	            {
                            	            otherlv_17=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKEllipse2194); 

                            	                	newLeafNode(otherlv_17, grammarAccess.getKEllipseAccess().getCommaKeyword_2_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:902:3: ( (lv_children_18_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:903:1: (lv_children_18_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:903:1: (lv_children_18_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:904:3: lv_children_18_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKEllipseAccess().getChildrenKRenderingParserRuleCall_2_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKEllipse2217);
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
                            	    break loop26;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_19=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKEllipse2233); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:932:1: entryRuleKRectangle returns [EObject current=null] : iv_ruleKRectangle= ruleKRectangle EOF ;
    public final EObject entryRuleKRectangle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRectangle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:933:2: (iv_ruleKRectangle= ruleKRectangle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:934:2: iv_ruleKRectangle= ruleKRectangle EOF
            {
             newCompositeNode(grammarAccess.getKRectangleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRectangle_in_entryRuleKRectangle2271);
            iv_ruleKRectangle=ruleKRectangle();

            state._fsp--;

             current =iv_ruleKRectangle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRectangle2281); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:941:1: ruleKRectangle returns [EObject current=null] : ( () otherlv_1= 'Rectangle' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:944:28: ( ( () otherlv_1= 'Rectangle' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:945:1: ( () otherlv_1= 'Rectangle' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:945:1: ( () otherlv_1= 'Rectangle' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:945:2: () otherlv_1= 'Rectangle' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:945:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:946:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRectangleAccess().getKRectangleAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleKRectangle2327); 

                	newLeafNode(otherlv_1, grammarAccess.getKRectangleAccess().getRectangleKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:955:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==12) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:955:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRectangle2340); 

                        	newLeafNode(otherlv_2, grammarAccess.getKRectangleAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:959:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt32=2;
                    int LA32_0 = input.LA(1);

                    if ( (LA32_0==16) ) {
                        alt32=1;
                    }
                    switch (alt32) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:959:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRectangle2353); 

                                	newLeafNode(otherlv_3, grammarAccess.getKRectangleAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:963:1: (otherlv_4= ':' )?
                            int alt29=2;
                            int LA29_0 = input.LA(1);

                            if ( (LA29_0==17) ) {
                                alt29=1;
                            }
                            switch (alt29) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:963:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRectangle2366); 

                                        	newLeafNode(otherlv_4, grammarAccess.getKRectangleAccess().getColonKeyword_2_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:967:3: ( (lv_styles_5_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:968:1: (lv_styles_5_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:968:1: (lv_styles_5_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:969:3: lv_styles_5_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRectangleAccess().getStylesKStyleParserRuleCall_2_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRectangle2389);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:985:2: ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            loop31:
                            do {
                                int alt31=2;
                                int LA31_0 = input.LA(1);

                                if ( (LA31_0==13||(LA31_0>=56 && LA31_0<=57)||LA31_0==65||(LA31_0>=70 && LA31_0<=72)||(LA31_0>=75 && LA31_0<=81)) ) {
                                    alt31=1;
                                }


                                switch (alt31) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:985:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:985:3: (otherlv_6= ',' )?
                            	    int alt30=2;
                            	    int LA30_0 = input.LA(1);

                            	    if ( (LA30_0==13) ) {
                            	        alt30=1;
                            	    }
                            	    switch (alt30) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:985:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRectangle2403); 

                            	                	newLeafNode(otherlv_6, grammarAccess.getKRectangleAccess().getCommaKeyword_2_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:989:3: ( (lv_styles_7_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:990:1: (lv_styles_7_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:990:1: (lv_styles_7_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:991:3: lv_styles_7_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKRectangleAccess().getStylesKStyleParserRuleCall_2_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRectangle2426);
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
                            	    break loop31;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1007:6: (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
                    int alt34=2;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0==18) ) {
                        alt34=1;
                    }
                    switch (alt34) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1007:8: otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRectangle2443); 

                                	newLeafNode(otherlv_8, grammarAccess.getKRectangleAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1011:1: (otherlv_9= ':' )?
                            int alt33=2;
                            int LA33_0 = input.LA(1);

                            if ( (LA33_0==17) ) {
                                alt33=1;
                            }
                            switch (alt33) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1011:3: otherlv_9= ':'
                                    {
                                    otherlv_9=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRectangle2456); 

                                        	newLeafNode(otherlv_9, grammarAccess.getKRectangleAccess().getColonKeyword_2_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1015:3: ( (lv_placementData_10_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1016:1: (lv_placementData_10_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1016:1: (lv_placementData_10_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1017:3: lv_placementData_10_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRectangleAccess().getPlacementDataKPlacementDataParserRuleCall_2_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRectangle2479);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1033:4: (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )?
                    int alt36=2;
                    int LA36_0 = input.LA(1);

                    if ( (LA36_0==20) ) {
                        alt36=1;
                    }
                    switch (alt36) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1033:6: otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            {
                            otherlv_11=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKRectangle2494); 

                                	newLeafNode(otherlv_11, grammarAccess.getKRectangleAccess().getChildPlacementKeyword_2_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1037:1: (otherlv_12= ':' )?
                            int alt35=2;
                            int LA35_0 = input.LA(1);

                            if ( (LA35_0==17) ) {
                                alt35=1;
                            }
                            switch (alt35) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1037:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRectangle2507); 

                                        	newLeafNode(otherlv_12, grammarAccess.getKRectangleAccess().getColonKeyword_2_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1041:3: ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1042:1: (lv_childPlacement_13_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1042:1: (lv_childPlacement_13_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1043:3: lv_childPlacement_13_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRectangleAccess().getChildPlacementKPlacementParserRuleCall_2_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKRectangle2530);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1059:4: (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )?
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( (LA40_0==21) ) {
                        alt40=1;
                    }
                    switch (alt40) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1059:6: otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            {
                            otherlv_14=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKRectangle2545); 

                                	newLeafNode(otherlv_14, grammarAccess.getKRectangleAccess().getChildrenKeyword_2_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1063:1: (otherlv_15= ':' )?
                            int alt37=2;
                            int LA37_0 = input.LA(1);

                            if ( (LA37_0==17) ) {
                                alt37=1;
                            }
                            switch (alt37) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1063:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRectangle2558); 

                                        	newLeafNode(otherlv_15, grammarAccess.getKRectangleAccess().getColonKeyword_2_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1067:3: ( (lv_children_16_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1068:1: (lv_children_16_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1068:1: (lv_children_16_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1069:3: lv_children_16_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRectangleAccess().getChildrenKRenderingParserRuleCall_2_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRectangle2581);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1085:2: ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            loop39:
                            do {
                                int alt39=2;
                                int LA39_0 = input.LA(1);

                                if ( (LA39_0==13||LA39_0==15||LA39_0==19||(LA39_0>=22 && LA39_0<=24)||(LA39_0>=26 && LA39_0<=28)||(LA39_0>=30 && LA39_0<=32)||LA39_0==34||LA39_0==37) ) {
                                    alt39=1;
                                }


                                switch (alt39) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1085:3: (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1085:3: (otherlv_17= ',' )?
                            	    int alt38=2;
                            	    int LA38_0 = input.LA(1);

                            	    if ( (LA38_0==13) ) {
                            	        alt38=1;
                            	    }
                            	    switch (alt38) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1085:5: otherlv_17= ','
                            	            {
                            	            otherlv_17=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRectangle2595); 

                            	                	newLeafNode(otherlv_17, grammarAccess.getKRectangleAccess().getCommaKeyword_2_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1089:3: ( (lv_children_18_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1090:1: (lv_children_18_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1090:1: (lv_children_18_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1091:3: lv_children_18_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKRectangleAccess().getChildrenKRenderingParserRuleCall_2_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRectangle2618);
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
                            	    break loop39;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_19=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRectangle2634); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1119:1: entryRuleKRoundedRectangle returns [EObject current=null] : iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF ;
    public final EObject entryRuleKRoundedRectangle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRoundedRectangle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1120:2: (iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1121:2: iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF
            {
             newCompositeNode(grammarAccess.getKRoundedRectangleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRoundedRectangle_in_entryRuleKRoundedRectangle2672);
            iv_ruleKRoundedRectangle=ruleKRoundedRectangle();

            state._fsp--;

             current =iv_ruleKRoundedRectangle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRoundedRectangle2682); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1128:1: ruleKRoundedRectangle returns [EObject current=null] : ( () otherlv_1= 'RoundedRectangle' ( (lv_cornerWidth_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_cornerHeight_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1131:28: ( ( () otherlv_1= 'RoundedRectangle' ( (lv_cornerWidth_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_cornerHeight_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1132:1: ( () otherlv_1= 'RoundedRectangle' ( (lv_cornerWidth_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_cornerHeight_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1132:1: ( () otherlv_1= 'RoundedRectangle' ( (lv_cornerWidth_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_cornerHeight_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1132:2: () otherlv_1= 'RoundedRectangle' ( (lv_cornerWidth_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_cornerHeight_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1132:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1133:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRoundedRectangleAccess().getKRoundedRectangleAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKRoundedRectangle2728); 

                	newLeafNode(otherlv_1, grammarAccess.getKRoundedRectangleAccess().getRoundedRectangleKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1142:1: ( (lv_cornerWidth_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1143:1: (lv_cornerWidth_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1143:1: (lv_cornerWidth_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1144:3: lv_cornerWidth_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getCornerWidthEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRoundedRectangle2749);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1160:2: (otherlv_3= ',' )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==13) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1160:4: otherlv_3= ','
                    {
                    otherlv_3=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRoundedRectangle2762); 

                        	newLeafNode(otherlv_3, grammarAccess.getKRoundedRectangleAccess().getCommaKeyword_3());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1164:3: ( (lv_cornerHeight_4_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1165:1: (lv_cornerHeight_4_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1165:1: (lv_cornerHeight_4_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1166:3: lv_cornerHeight_4_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getCornerHeightEFloatParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRoundedRectangle2785);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1182:2: (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==12) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1182:4: otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}'
                    {
                    otherlv_5=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRoundedRectangle2798); 

                        	newLeafNode(otherlv_5, grammarAccess.getKRoundedRectangleAccess().getLeftCurlyBracketKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1186:1: (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )?
                    int alt46=2;
                    int LA46_0 = input.LA(1);

                    if ( (LA46_0==16) ) {
                        alt46=1;
                    }
                    switch (alt46) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1186:3: otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            {
                            otherlv_6=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRoundedRectangle2811); 

                                	newLeafNode(otherlv_6, grammarAccess.getKRoundedRectangleAccess().getStylesKeyword_5_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1190:1: (otherlv_7= ':' )?
                            int alt43=2;
                            int LA43_0 = input.LA(1);

                            if ( (LA43_0==17) ) {
                                alt43=1;
                            }
                            switch (alt43) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1190:3: otherlv_7= ':'
                                    {
                                    otherlv_7=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRoundedRectangle2824); 

                                        	newLeafNode(otherlv_7, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_5_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1194:3: ( (lv_styles_8_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1195:1: (lv_styles_8_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1195:1: (lv_styles_8_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1196:3: lv_styles_8_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getStylesKStyleParserRuleCall_5_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRoundedRectangle2847);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1212:2: ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            loop45:
                            do {
                                int alt45=2;
                                int LA45_0 = input.LA(1);

                                if ( (LA45_0==13||(LA45_0>=56 && LA45_0<=57)||LA45_0==65||(LA45_0>=70 && LA45_0<=72)||(LA45_0>=75 && LA45_0<=81)) ) {
                                    alt45=1;
                                }


                                switch (alt45) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1212:3: (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1212:3: (otherlv_9= ',' )?
                            	    int alt44=2;
                            	    int LA44_0 = input.LA(1);

                            	    if ( (LA44_0==13) ) {
                            	        alt44=1;
                            	    }
                            	    switch (alt44) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1212:5: otherlv_9= ','
                            	            {
                            	            otherlv_9=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRoundedRectangle2861); 

                            	                	newLeafNode(otherlv_9, grammarAccess.getKRoundedRectangleAccess().getCommaKeyword_5_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1216:3: ( (lv_styles_10_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1217:1: (lv_styles_10_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1217:1: (lv_styles_10_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1218:3: lv_styles_10_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getStylesKStyleParserRuleCall_5_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRoundedRectangle2884);
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
                            	    break loop45;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1234:6: (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )?
                    int alt48=2;
                    int LA48_0 = input.LA(1);

                    if ( (LA48_0==18) ) {
                        alt48=1;
                    }
                    switch (alt48) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1234:8: otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) )
                            {
                            otherlv_11=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRoundedRectangle2901); 

                                	newLeafNode(otherlv_11, grammarAccess.getKRoundedRectangleAccess().getPlacementDataKeyword_5_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1238:1: (otherlv_12= ':' )?
                            int alt47=2;
                            int LA47_0 = input.LA(1);

                            if ( (LA47_0==17) ) {
                                alt47=1;
                            }
                            switch (alt47) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1238:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRoundedRectangle2914); 

                                        	newLeafNode(otherlv_12, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_5_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1242:3: ( (lv_placementData_13_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1243:1: (lv_placementData_13_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1243:1: (lv_placementData_13_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1244:3: lv_placementData_13_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getPlacementDataKPlacementDataParserRuleCall_5_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRoundedRectangle2937);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1260:4: (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )?
                    int alt50=2;
                    int LA50_0 = input.LA(1);

                    if ( (LA50_0==20) ) {
                        alt50=1;
                    }
                    switch (alt50) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1260:6: otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) )
                            {
                            otherlv_14=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKRoundedRectangle2952); 

                                	newLeafNode(otherlv_14, grammarAccess.getKRoundedRectangleAccess().getChildPlacementKeyword_5_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1264:1: (otherlv_15= ':' )?
                            int alt49=2;
                            int LA49_0 = input.LA(1);

                            if ( (LA49_0==17) ) {
                                alt49=1;
                            }
                            switch (alt49) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1264:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRoundedRectangle2965); 

                                        	newLeafNode(otherlv_15, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_5_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1268:3: ( (lv_childPlacement_16_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1269:1: (lv_childPlacement_16_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1269:1: (lv_childPlacement_16_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1270:3: lv_childPlacement_16_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getChildPlacementKPlacementParserRuleCall_5_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKRoundedRectangle2988);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1286:4: (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )?
                    int alt54=2;
                    int LA54_0 = input.LA(1);

                    if ( (LA54_0==21) ) {
                        alt54=1;
                    }
                    switch (alt54) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1286:6: otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )*
                            {
                            otherlv_17=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKRoundedRectangle3003); 

                                	newLeafNode(otherlv_17, grammarAccess.getKRoundedRectangleAccess().getChildrenKeyword_5_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1290:1: (otherlv_18= ':' )?
                            int alt51=2;
                            int LA51_0 = input.LA(1);

                            if ( (LA51_0==17) ) {
                                alt51=1;
                            }
                            switch (alt51) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1290:3: otherlv_18= ':'
                                    {
                                    otherlv_18=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRoundedRectangle3016); 

                                        	newLeafNode(otherlv_18, grammarAccess.getKRoundedRectangleAccess().getColonKeyword_5_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1294:3: ( (lv_children_19_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1295:1: (lv_children_19_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1295:1: (lv_children_19_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1296:3: lv_children_19_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getChildrenKRenderingParserRuleCall_5_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRoundedRectangle3039);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1312:2: ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )*
                            loop53:
                            do {
                                int alt53=2;
                                int LA53_0 = input.LA(1);

                                if ( (LA53_0==13||LA53_0==15||LA53_0==19||(LA53_0>=22 && LA53_0<=24)||(LA53_0>=26 && LA53_0<=28)||(LA53_0>=30 && LA53_0<=32)||LA53_0==34||LA53_0==37) ) {
                                    alt53=1;
                                }


                                switch (alt53) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1312:3: (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1312:3: (otherlv_20= ',' )?
                            	    int alt52=2;
                            	    int LA52_0 = input.LA(1);

                            	    if ( (LA52_0==13) ) {
                            	        alt52=1;
                            	    }
                            	    switch (alt52) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1312:5: otherlv_20= ','
                            	            {
                            	            otherlv_20=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRoundedRectangle3053); 

                            	                	newLeafNode(otherlv_20, grammarAccess.getKRoundedRectangleAccess().getCommaKeyword_5_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1316:3: ( (lv_children_21_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1317:1: (lv_children_21_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1317:1: (lv_children_21_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1318:3: lv_children_21_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getChildrenKRenderingParserRuleCall_5_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRoundedRectangle3076);
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
                            	    break loop53;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_22=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRoundedRectangle3092); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1346:1: entryRuleKPolyline_Impl returns [EObject current=null] : iv_ruleKPolyline_Impl= ruleKPolyline_Impl EOF ;
    public final EObject entryRuleKPolyline_Impl() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolyline_Impl = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1347:2: (iv_ruleKPolyline_Impl= ruleKPolyline_Impl EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1348:2: iv_ruleKPolyline_Impl= ruleKPolyline_Impl EOF
            {
             newCompositeNode(grammarAccess.getKPolyline_ImplRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPolyline_Impl_in_entryRuleKPolyline_Impl3130);
            iv_ruleKPolyline_Impl=ruleKPolyline_Impl();

            state._fsp--;

             current =iv_ruleKPolyline_Impl; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPolyline_Impl3140); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1355:1: ruleKPolyline_Impl returns [EObject current=null] : ( () otherlv_1= 'Polyline' (otherlv_2= '{' otherlv_3= 'points' (otherlv_4= ':' )? ( (lv_points_5_0= ruleKPosition ) ) ( (otherlv_6= ',' )? ( (lv_points_7_0= ruleKPosition ) ) )* (otherlv_8= 'styles' (otherlv_9= ':' )? ( (lv_styles_10_0= ruleKStyle ) ) ( (otherlv_11= ',' )? ( (lv_styles_12_0= ruleKStyle ) ) )* )? (otherlv_13= 'placementData' (otherlv_14= ':' )? ( (lv_placementData_15_0= ruleKPlacementData ) ) )? (otherlv_16= 'childPlacement' (otherlv_17= ':' )? ( (lv_childPlacement_18_0= ruleKPlacement ) ) )? (otherlv_19= 'children' (otherlv_20= ':' )? ( (lv_children_21_0= ruleKRendering ) ) ( (otherlv_22= ',' )? ( (lv_children_23_0= ruleKRendering ) ) )* )? otherlv_24= '}' )? ) ;
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
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token otherlv_22=null;
        Token otherlv_24=null;
        EObject lv_points_5_0 = null;

        EObject lv_points_7_0 = null;

        EObject lv_styles_10_0 = null;

        EObject lv_styles_12_0 = null;

        EObject lv_placementData_15_0 = null;

        EObject lv_childPlacement_18_0 = null;

        EObject lv_children_21_0 = null;

        EObject lv_children_23_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1358:28: ( ( () otherlv_1= 'Polyline' (otherlv_2= '{' otherlv_3= 'points' (otherlv_4= ':' )? ( (lv_points_5_0= ruleKPosition ) ) ( (otherlv_6= ',' )? ( (lv_points_7_0= ruleKPosition ) ) )* (otherlv_8= 'styles' (otherlv_9= ':' )? ( (lv_styles_10_0= ruleKStyle ) ) ( (otherlv_11= ',' )? ( (lv_styles_12_0= ruleKStyle ) ) )* )? (otherlv_13= 'placementData' (otherlv_14= ':' )? ( (lv_placementData_15_0= ruleKPlacementData ) ) )? (otherlv_16= 'childPlacement' (otherlv_17= ':' )? ( (lv_childPlacement_18_0= ruleKPlacement ) ) )? (otherlv_19= 'children' (otherlv_20= ':' )? ( (lv_children_21_0= ruleKRendering ) ) ( (otherlv_22= ',' )? ( (lv_children_23_0= ruleKRendering ) ) )* )? otherlv_24= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1359:1: ( () otherlv_1= 'Polyline' (otherlv_2= '{' otherlv_3= 'points' (otherlv_4= ':' )? ( (lv_points_5_0= ruleKPosition ) ) ( (otherlv_6= ',' )? ( (lv_points_7_0= ruleKPosition ) ) )* (otherlv_8= 'styles' (otherlv_9= ':' )? ( (lv_styles_10_0= ruleKStyle ) ) ( (otherlv_11= ',' )? ( (lv_styles_12_0= ruleKStyle ) ) )* )? (otherlv_13= 'placementData' (otherlv_14= ':' )? ( (lv_placementData_15_0= ruleKPlacementData ) ) )? (otherlv_16= 'childPlacement' (otherlv_17= ':' )? ( (lv_childPlacement_18_0= ruleKPlacement ) ) )? (otherlv_19= 'children' (otherlv_20= ':' )? ( (lv_children_21_0= ruleKRendering ) ) ( (otherlv_22= ',' )? ( (lv_children_23_0= ruleKRendering ) ) )* )? otherlv_24= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1359:1: ( () otherlv_1= 'Polyline' (otherlv_2= '{' otherlv_3= 'points' (otherlv_4= ':' )? ( (lv_points_5_0= ruleKPosition ) ) ( (otherlv_6= ',' )? ( (lv_points_7_0= ruleKPosition ) ) )* (otherlv_8= 'styles' (otherlv_9= ':' )? ( (lv_styles_10_0= ruleKStyle ) ) ( (otherlv_11= ',' )? ( (lv_styles_12_0= ruleKStyle ) ) )* )? (otherlv_13= 'placementData' (otherlv_14= ':' )? ( (lv_placementData_15_0= ruleKPlacementData ) ) )? (otherlv_16= 'childPlacement' (otherlv_17= ':' )? ( (lv_childPlacement_18_0= ruleKPlacement ) ) )? (otherlv_19= 'children' (otherlv_20= ':' )? ( (lv_children_21_0= ruleKRendering ) ) ( (otherlv_22= ',' )? ( (lv_children_23_0= ruleKRendering ) ) )* )? otherlv_24= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1359:2: () otherlv_1= 'Polyline' (otherlv_2= '{' otherlv_3= 'points' (otherlv_4= ':' )? ( (lv_points_5_0= ruleKPosition ) ) ( (otherlv_6= ',' )? ( (lv_points_7_0= ruleKPosition ) ) )* (otherlv_8= 'styles' (otherlv_9= ':' )? ( (lv_styles_10_0= ruleKStyle ) ) ( (otherlv_11= ',' )? ( (lv_styles_12_0= ruleKStyle ) ) )* )? (otherlv_13= 'placementData' (otherlv_14= ':' )? ( (lv_placementData_15_0= ruleKPlacementData ) ) )? (otherlv_16= 'childPlacement' (otherlv_17= ':' )? ( (lv_childPlacement_18_0= ruleKPlacement ) ) )? (otherlv_19= 'children' (otherlv_20= ':' )? ( (lv_children_21_0= ruleKRendering ) ) ( (otherlv_22= ',' )? ( (lv_children_23_0= ruleKRendering ) ) )* )? otherlv_24= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1359:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1360:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPolyline_ImplAccess().getKPolylineAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,24,FollowSets000.FOLLOW_24_in_ruleKPolyline_Impl3186); 

                	newLeafNode(otherlv_1, grammarAccess.getKPolyline_ImplAccess().getPolylineKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1369:1: (otherlv_2= '{' otherlv_3= 'points' (otherlv_4= ':' )? ( (lv_points_5_0= ruleKPosition ) ) ( (otherlv_6= ',' )? ( (lv_points_7_0= ruleKPosition ) ) )* (otherlv_8= 'styles' (otherlv_9= ':' )? ( (lv_styles_10_0= ruleKStyle ) ) ( (otherlv_11= ',' )? ( (lv_styles_12_0= ruleKStyle ) ) )* )? (otherlv_13= 'placementData' (otherlv_14= ':' )? ( (lv_placementData_15_0= ruleKPlacementData ) ) )? (otherlv_16= 'childPlacement' (otherlv_17= ':' )? ( (lv_childPlacement_18_0= ruleKPlacement ) ) )? (otherlv_19= 'children' (otherlv_20= ':' )? ( (lv_children_21_0= ruleKRendering ) ) ( (otherlv_22= ',' )? ( (lv_children_23_0= ruleKRendering ) ) )* )? otherlv_24= '}' )?
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==12) ) {
                alt71=1;
            }
            switch (alt71) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1369:3: otherlv_2= '{' otherlv_3= 'points' (otherlv_4= ':' )? ( (lv_points_5_0= ruleKPosition ) ) ( (otherlv_6= ',' )? ( (lv_points_7_0= ruleKPosition ) ) )* (otherlv_8= 'styles' (otherlv_9= ':' )? ( (lv_styles_10_0= ruleKStyle ) ) ( (otherlv_11= ',' )? ( (lv_styles_12_0= ruleKStyle ) ) )* )? (otherlv_13= 'placementData' (otherlv_14= ':' )? ( (lv_placementData_15_0= ruleKPlacementData ) ) )? (otherlv_16= 'childPlacement' (otherlv_17= ':' )? ( (lv_childPlacement_18_0= ruleKPlacement ) ) )? (otherlv_19= 'children' (otherlv_20= ':' )? ( (lv_children_21_0= ruleKRendering ) ) ( (otherlv_22= ',' )? ( (lv_children_23_0= ruleKRendering ) ) )* )? otherlv_24= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolyline_Impl3199); 

                        	newLeafNode(otherlv_2, grammarAccess.getKPolyline_ImplAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    otherlv_3=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleKPolyline_Impl3211); 

                        	newLeafNode(otherlv_3, grammarAccess.getKPolyline_ImplAccess().getPointsKeyword_2_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1377:1: (otherlv_4= ':' )?
                    int alt56=2;
                    int LA56_0 = input.LA(1);

                    if ( (LA56_0==17) ) {
                        alt56=1;
                    }
                    switch (alt56) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1377:3: otherlv_4= ':'
                            {
                            otherlv_4=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolyline_Impl3224); 

                                	newLeafNode(otherlv_4, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_2_2());
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1381:3: ( (lv_points_5_0= ruleKPosition ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1382:1: (lv_points_5_0= ruleKPosition )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1382:1: (lv_points_5_0= ruleKPosition )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1383:3: lv_points_5_0= ruleKPosition
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getPointsKPositionParserRuleCall_2_3_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKPolyline_Impl3247);
                    lv_points_5_0=ruleKPosition();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKPolyline_ImplRule());
                    	        }
                           		add(
                           			current, 
                           			"points",
                            		lv_points_5_0, 
                            		"KPosition");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1399:2: ( (otherlv_6= ',' )? ( (lv_points_7_0= ruleKPosition ) ) )*
                    loop58:
                    do {
                        int alt58=2;
                        int LA58_0 = input.LA(1);

                        if ( (LA58_0==13||(LA58_0>=59 && LA58_0<=60)) ) {
                            alt58=1;
                        }


                        switch (alt58) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1399:3: (otherlv_6= ',' )? ( (lv_points_7_0= ruleKPosition ) )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1399:3: (otherlv_6= ',' )?
                    	    int alt57=2;
                    	    int LA57_0 = input.LA(1);

                    	    if ( (LA57_0==13) ) {
                    	        alt57=1;
                    	    }
                    	    switch (alt57) {
                    	        case 1 :
                    	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1399:5: otherlv_6= ','
                    	            {
                    	            otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPolyline_Impl3261); 

                    	                	newLeafNode(otherlv_6, grammarAccess.getKPolyline_ImplAccess().getCommaKeyword_2_4_0());
                    	                

                    	            }
                    	            break;

                    	    }

                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1403:3: ( (lv_points_7_0= ruleKPosition ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1404:1: (lv_points_7_0= ruleKPosition )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1404:1: (lv_points_7_0= ruleKPosition )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1405:3: lv_points_7_0= ruleKPosition
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getPointsKPositionParserRuleCall_2_4_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKPolyline_Impl3284);
                    	    lv_points_7_0=ruleKPosition();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKPolyline_ImplRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"points",
                    	            		lv_points_7_0, 
                    	            		"KPosition");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop58;
                        }
                    } while (true);

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1421:4: (otherlv_8= 'styles' (otherlv_9= ':' )? ( (lv_styles_10_0= ruleKStyle ) ) ( (otherlv_11= ',' )? ( (lv_styles_12_0= ruleKStyle ) ) )* )?
                    int alt62=2;
                    int LA62_0 = input.LA(1);

                    if ( (LA62_0==16) ) {
                        alt62=1;
                    }
                    switch (alt62) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1421:6: otherlv_8= 'styles' (otherlv_9= ':' )? ( (lv_styles_10_0= ruleKStyle ) ) ( (otherlv_11= ',' )? ( (lv_styles_12_0= ruleKStyle ) ) )*
                            {
                            otherlv_8=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolyline_Impl3299); 

                                	newLeafNode(otherlv_8, grammarAccess.getKPolyline_ImplAccess().getStylesKeyword_2_5_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1425:1: (otherlv_9= ':' )?
                            int alt59=2;
                            int LA59_0 = input.LA(1);

                            if ( (LA59_0==17) ) {
                                alt59=1;
                            }
                            switch (alt59) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1425:3: otherlv_9= ':'
                                    {
                                    otherlv_9=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolyline_Impl3312); 

                                        	newLeafNode(otherlv_9, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_2_5_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1429:3: ( (lv_styles_10_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1430:1: (lv_styles_10_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1430:1: (lv_styles_10_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1431:3: lv_styles_10_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getStylesKStyleParserRuleCall_2_5_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolyline_Impl3335);
                            lv_styles_10_0=ruleKStyle();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKPolyline_ImplRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"styles",
                                    		lv_styles_10_0, 
                                    		"KStyle");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1447:2: ( (otherlv_11= ',' )? ( (lv_styles_12_0= ruleKStyle ) ) )*
                            loop61:
                            do {
                                int alt61=2;
                                int LA61_0 = input.LA(1);

                                if ( (LA61_0==13||(LA61_0>=56 && LA61_0<=57)||LA61_0==65||(LA61_0>=70 && LA61_0<=72)||(LA61_0>=75 && LA61_0<=81)) ) {
                                    alt61=1;
                                }


                                switch (alt61) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1447:3: (otherlv_11= ',' )? ( (lv_styles_12_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1447:3: (otherlv_11= ',' )?
                            	    int alt60=2;
                            	    int LA60_0 = input.LA(1);

                            	    if ( (LA60_0==13) ) {
                            	        alt60=1;
                            	    }
                            	    switch (alt60) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1447:5: otherlv_11= ','
                            	            {
                            	            otherlv_11=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPolyline_Impl3349); 

                            	                	newLeafNode(otherlv_11, grammarAccess.getKPolyline_ImplAccess().getCommaKeyword_2_5_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1451:3: ( (lv_styles_12_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1452:1: (lv_styles_12_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1452:1: (lv_styles_12_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1453:3: lv_styles_12_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getStylesKStyleParserRuleCall_2_5_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolyline_Impl3372);
                            	    lv_styles_12_0=ruleKStyle();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKPolyline_ImplRule());
                            	    	        }
                            	           		add(
                            	           			current, 
                            	           			"styles",
                            	            		lv_styles_12_0, 
                            	            		"KStyle");
                            	    	        afterParserOrEnumRuleCall();
                            	    	    

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop61;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1469:6: (otherlv_13= 'placementData' (otherlv_14= ':' )? ( (lv_placementData_15_0= ruleKPlacementData ) ) )?
                    int alt64=2;
                    int LA64_0 = input.LA(1);

                    if ( (LA64_0==18) ) {
                        alt64=1;
                    }
                    switch (alt64) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1469:8: otherlv_13= 'placementData' (otherlv_14= ':' )? ( (lv_placementData_15_0= ruleKPlacementData ) )
                            {
                            otherlv_13=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPolyline_Impl3389); 

                                	newLeafNode(otherlv_13, grammarAccess.getKPolyline_ImplAccess().getPlacementDataKeyword_2_6_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1473:1: (otherlv_14= ':' )?
                            int alt63=2;
                            int LA63_0 = input.LA(1);

                            if ( (LA63_0==17) ) {
                                alt63=1;
                            }
                            switch (alt63) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1473:3: otherlv_14= ':'
                                    {
                                    otherlv_14=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolyline_Impl3402); 

                                        	newLeafNode(otherlv_14, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_2_6_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1477:3: ( (lv_placementData_15_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1478:1: (lv_placementData_15_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1478:1: (lv_placementData_15_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1479:3: lv_placementData_15_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getPlacementDataKPlacementDataParserRuleCall_2_6_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKPolyline_Impl3425);
                            lv_placementData_15_0=ruleKPlacementData();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKPolyline_ImplRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"placementData",
                                    		lv_placementData_15_0, 
                                    		"KPlacementData");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1495:4: (otherlv_16= 'childPlacement' (otherlv_17= ':' )? ( (lv_childPlacement_18_0= ruleKPlacement ) ) )?
                    int alt66=2;
                    int LA66_0 = input.LA(1);

                    if ( (LA66_0==20) ) {
                        alt66=1;
                    }
                    switch (alt66) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1495:6: otherlv_16= 'childPlacement' (otherlv_17= ':' )? ( (lv_childPlacement_18_0= ruleKPlacement ) )
                            {
                            otherlv_16=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKPolyline_Impl3440); 

                                	newLeafNode(otherlv_16, grammarAccess.getKPolyline_ImplAccess().getChildPlacementKeyword_2_7_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1499:1: (otherlv_17= ':' )?
                            int alt65=2;
                            int LA65_0 = input.LA(1);

                            if ( (LA65_0==17) ) {
                                alt65=1;
                            }
                            switch (alt65) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1499:3: otherlv_17= ':'
                                    {
                                    otherlv_17=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolyline_Impl3453); 

                                        	newLeafNode(otherlv_17, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_2_7_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1503:3: ( (lv_childPlacement_18_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1504:1: (lv_childPlacement_18_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1504:1: (lv_childPlacement_18_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1505:3: lv_childPlacement_18_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getChildPlacementKPlacementParserRuleCall_2_7_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKPolyline_Impl3476);
                            lv_childPlacement_18_0=ruleKPlacement();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKPolyline_ImplRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"childPlacement",
                                    		lv_childPlacement_18_0, 
                                    		"KPlacement");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1521:4: (otherlv_19= 'children' (otherlv_20= ':' )? ( (lv_children_21_0= ruleKRendering ) ) ( (otherlv_22= ',' )? ( (lv_children_23_0= ruleKRendering ) ) )* )?
                    int alt70=2;
                    int LA70_0 = input.LA(1);

                    if ( (LA70_0==21) ) {
                        alt70=1;
                    }
                    switch (alt70) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1521:6: otherlv_19= 'children' (otherlv_20= ':' )? ( (lv_children_21_0= ruleKRendering ) ) ( (otherlv_22= ',' )? ( (lv_children_23_0= ruleKRendering ) ) )*
                            {
                            otherlv_19=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKPolyline_Impl3491); 

                                	newLeafNode(otherlv_19, grammarAccess.getKPolyline_ImplAccess().getChildrenKeyword_2_8_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1525:1: (otherlv_20= ':' )?
                            int alt67=2;
                            int LA67_0 = input.LA(1);

                            if ( (LA67_0==17) ) {
                                alt67=1;
                            }
                            switch (alt67) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1525:3: otherlv_20= ':'
                                    {
                                    otherlv_20=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolyline_Impl3504); 

                                        	newLeafNode(otherlv_20, grammarAccess.getKPolyline_ImplAccess().getColonKeyword_2_8_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1529:3: ( (lv_children_21_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1530:1: (lv_children_21_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1530:1: (lv_children_21_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1531:3: lv_children_21_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getChildrenKRenderingParserRuleCall_2_8_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolyline_Impl3527);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1547:2: ( (otherlv_22= ',' )? ( (lv_children_23_0= ruleKRendering ) ) )*
                            loop69:
                            do {
                                int alt69=2;
                                int LA69_0 = input.LA(1);

                                if ( (LA69_0==13||LA69_0==15||LA69_0==19||(LA69_0>=22 && LA69_0<=24)||(LA69_0>=26 && LA69_0<=28)||(LA69_0>=30 && LA69_0<=32)||LA69_0==34||LA69_0==37) ) {
                                    alt69=1;
                                }


                                switch (alt69) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1547:3: (otherlv_22= ',' )? ( (lv_children_23_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1547:3: (otherlv_22= ',' )?
                            	    int alt68=2;
                            	    int LA68_0 = input.LA(1);

                            	    if ( (LA68_0==13) ) {
                            	        alt68=1;
                            	    }
                            	    switch (alt68) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1547:5: otherlv_22= ','
                            	            {
                            	            otherlv_22=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPolyline_Impl3541); 

                            	                	newLeafNode(otherlv_22, grammarAccess.getKPolyline_ImplAccess().getCommaKeyword_2_8_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1551:3: ( (lv_children_23_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1552:1: (lv_children_23_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1552:1: (lv_children_23_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1553:3: lv_children_23_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getChildrenKRenderingParserRuleCall_2_8_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolyline_Impl3564);
                            	    lv_children_23_0=ruleKRendering();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKPolyline_ImplRule());
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
                            	    break loop69;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_24=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolyline_Impl3580); 

                        	newLeafNode(otherlv_24, grammarAccess.getKPolyline_ImplAccess().getRightCurlyBracketKeyword_2_9());
                        

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1581:1: entryRuleKRoundedBendsPolyline returns [EObject current=null] : iv_ruleKRoundedBendsPolyline= ruleKRoundedBendsPolyline EOF ;
    public final EObject entryRuleKRoundedBendsPolyline() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRoundedBendsPolyline = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1582:2: (iv_ruleKRoundedBendsPolyline= ruleKRoundedBendsPolyline EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1583:2: iv_ruleKRoundedBendsPolyline= ruleKRoundedBendsPolyline EOF
            {
             newCompositeNode(grammarAccess.getKRoundedBendsPolylineRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRoundedBendsPolyline_in_entryRuleKRoundedBendsPolyline3618);
            iv_ruleKRoundedBendsPolyline=ruleKRoundedBendsPolyline();

            state._fsp--;

             current =iv_ruleKRoundedBendsPolyline; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRoundedBendsPolyline3628); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1590:1: ruleKRoundedBendsPolyline returns [EObject current=null] : ( () otherlv_1= 'RoundedBendsPolyline' ( (lv_bendRadius_2_0= ruleEFloat ) ) (otherlv_3= '{' otherlv_4= 'points' (otherlv_5= ':' )? ( (lv_points_6_0= ruleKPosition ) ) ( (otherlv_7= ',' )? ( (lv_points_8_0= ruleKPosition ) ) )* (otherlv_9= 'styles' (otherlv_10= ':' )? ( (lv_styles_11_0= ruleKStyle ) ) ( (otherlv_12= ',' )? ( (lv_styles_13_0= ruleKStyle ) ) )* )? (otherlv_14= 'placementData' (otherlv_15= ':' )? ( (lv_placementData_16_0= ruleKPlacementData ) ) )? (otherlv_17= 'childPlacement' (otherlv_18= ':' )? ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? (otherlv_20= 'children' (otherlv_21= ':' )? ( (lv_children_22_0= ruleKRendering ) ) ( (otherlv_23= ',' )? ( (lv_children_24_0= ruleKRendering ) ) )* )? otherlv_25= '}' )? ) ;
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
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        Token otherlv_23=null;
        Token otherlv_25=null;
        AntlrDatatypeRuleToken lv_bendRadius_2_0 = null;

        EObject lv_points_6_0 = null;

        EObject lv_points_8_0 = null;

        EObject lv_styles_11_0 = null;

        EObject lv_styles_13_0 = null;

        EObject lv_placementData_16_0 = null;

        EObject lv_childPlacement_19_0 = null;

        EObject lv_children_22_0 = null;

        EObject lv_children_24_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1593:28: ( ( () otherlv_1= 'RoundedBendsPolyline' ( (lv_bendRadius_2_0= ruleEFloat ) ) (otherlv_3= '{' otherlv_4= 'points' (otherlv_5= ':' )? ( (lv_points_6_0= ruleKPosition ) ) ( (otherlv_7= ',' )? ( (lv_points_8_0= ruleKPosition ) ) )* (otherlv_9= 'styles' (otherlv_10= ':' )? ( (lv_styles_11_0= ruleKStyle ) ) ( (otherlv_12= ',' )? ( (lv_styles_13_0= ruleKStyle ) ) )* )? (otherlv_14= 'placementData' (otherlv_15= ':' )? ( (lv_placementData_16_0= ruleKPlacementData ) ) )? (otherlv_17= 'childPlacement' (otherlv_18= ':' )? ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? (otherlv_20= 'children' (otherlv_21= ':' )? ( (lv_children_22_0= ruleKRendering ) ) ( (otherlv_23= ',' )? ( (lv_children_24_0= ruleKRendering ) ) )* )? otherlv_25= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1594:1: ( () otherlv_1= 'RoundedBendsPolyline' ( (lv_bendRadius_2_0= ruleEFloat ) ) (otherlv_3= '{' otherlv_4= 'points' (otherlv_5= ':' )? ( (lv_points_6_0= ruleKPosition ) ) ( (otherlv_7= ',' )? ( (lv_points_8_0= ruleKPosition ) ) )* (otherlv_9= 'styles' (otherlv_10= ':' )? ( (lv_styles_11_0= ruleKStyle ) ) ( (otherlv_12= ',' )? ( (lv_styles_13_0= ruleKStyle ) ) )* )? (otherlv_14= 'placementData' (otherlv_15= ':' )? ( (lv_placementData_16_0= ruleKPlacementData ) ) )? (otherlv_17= 'childPlacement' (otherlv_18= ':' )? ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? (otherlv_20= 'children' (otherlv_21= ':' )? ( (lv_children_22_0= ruleKRendering ) ) ( (otherlv_23= ',' )? ( (lv_children_24_0= ruleKRendering ) ) )* )? otherlv_25= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1594:1: ( () otherlv_1= 'RoundedBendsPolyline' ( (lv_bendRadius_2_0= ruleEFloat ) ) (otherlv_3= '{' otherlv_4= 'points' (otherlv_5= ':' )? ( (lv_points_6_0= ruleKPosition ) ) ( (otherlv_7= ',' )? ( (lv_points_8_0= ruleKPosition ) ) )* (otherlv_9= 'styles' (otherlv_10= ':' )? ( (lv_styles_11_0= ruleKStyle ) ) ( (otherlv_12= ',' )? ( (lv_styles_13_0= ruleKStyle ) ) )* )? (otherlv_14= 'placementData' (otherlv_15= ':' )? ( (lv_placementData_16_0= ruleKPlacementData ) ) )? (otherlv_17= 'childPlacement' (otherlv_18= ':' )? ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? (otherlv_20= 'children' (otherlv_21= ':' )? ( (lv_children_22_0= ruleKRendering ) ) ( (otherlv_23= ',' )? ( (lv_children_24_0= ruleKRendering ) ) )* )? otherlv_25= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1594:2: () otherlv_1= 'RoundedBendsPolyline' ( (lv_bendRadius_2_0= ruleEFloat ) ) (otherlv_3= '{' otherlv_4= 'points' (otherlv_5= ':' )? ( (lv_points_6_0= ruleKPosition ) ) ( (otherlv_7= ',' )? ( (lv_points_8_0= ruleKPosition ) ) )* (otherlv_9= 'styles' (otherlv_10= ':' )? ( (lv_styles_11_0= ruleKStyle ) ) ( (otherlv_12= ',' )? ( (lv_styles_13_0= ruleKStyle ) ) )* )? (otherlv_14= 'placementData' (otherlv_15= ':' )? ( (lv_placementData_16_0= ruleKPlacementData ) ) )? (otherlv_17= 'childPlacement' (otherlv_18= ':' )? ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? (otherlv_20= 'children' (otherlv_21= ':' )? ( (lv_children_22_0= ruleKRendering ) ) ( (otherlv_23= ',' )? ( (lv_children_24_0= ruleKRendering ) ) )* )? otherlv_25= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1594:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1595:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRoundedBendsPolylineAccess().getKRoundedBendsPolylineAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleKRoundedBendsPolyline3674); 

                	newLeafNode(otherlv_1, grammarAccess.getKRoundedBendsPolylineAccess().getRoundedBendsPolylineKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1604:1: ( (lv_bendRadius_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1605:1: (lv_bendRadius_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1605:1: (lv_bendRadius_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1606:3: lv_bendRadius_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRoundedBendsPolylineAccess().getBendRadiusEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRoundedBendsPolyline3695);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1622:2: (otherlv_3= '{' otherlv_4= 'points' (otherlv_5= ':' )? ( (lv_points_6_0= ruleKPosition ) ) ( (otherlv_7= ',' )? ( (lv_points_8_0= ruleKPosition ) ) )* (otherlv_9= 'styles' (otherlv_10= ':' )? ( (lv_styles_11_0= ruleKStyle ) ) ( (otherlv_12= ',' )? ( (lv_styles_13_0= ruleKStyle ) ) )* )? (otherlv_14= 'placementData' (otherlv_15= ':' )? ( (lv_placementData_16_0= ruleKPlacementData ) ) )? (otherlv_17= 'childPlacement' (otherlv_18= ':' )? ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? (otherlv_20= 'children' (otherlv_21= ':' )? ( (lv_children_22_0= ruleKRendering ) ) ( (otherlv_23= ',' )? ( (lv_children_24_0= ruleKRendering ) ) )* )? otherlv_25= '}' )?
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==12) ) {
                alt87=1;
            }
            switch (alt87) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1622:4: otherlv_3= '{' otherlv_4= 'points' (otherlv_5= ':' )? ( (lv_points_6_0= ruleKPosition ) ) ( (otherlv_7= ',' )? ( (lv_points_8_0= ruleKPosition ) ) )* (otherlv_9= 'styles' (otherlv_10= ':' )? ( (lv_styles_11_0= ruleKStyle ) ) ( (otherlv_12= ',' )? ( (lv_styles_13_0= ruleKStyle ) ) )* )? (otherlv_14= 'placementData' (otherlv_15= ':' )? ( (lv_placementData_16_0= ruleKPlacementData ) ) )? (otherlv_17= 'childPlacement' (otherlv_18= ':' )? ( (lv_childPlacement_19_0= ruleKPlacement ) ) )? (otherlv_20= 'children' (otherlv_21= ':' )? ( (lv_children_22_0= ruleKRendering ) ) ( (otherlv_23= ',' )? ( (lv_children_24_0= ruleKRendering ) ) )* )? otherlv_25= '}'
                    {
                    otherlv_3=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRoundedBendsPolyline3708); 

                        	newLeafNode(otherlv_3, grammarAccess.getKRoundedBendsPolylineAccess().getLeftCurlyBracketKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleKRoundedBendsPolyline3720); 

                        	newLeafNode(otherlv_4, grammarAccess.getKRoundedBendsPolylineAccess().getPointsKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1630:1: (otherlv_5= ':' )?
                    int alt72=2;
                    int LA72_0 = input.LA(1);

                    if ( (LA72_0==17) ) {
                        alt72=1;
                    }
                    switch (alt72) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1630:3: otherlv_5= ':'
                            {
                            otherlv_5=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRoundedBendsPolyline3733); 

                                	newLeafNode(otherlv_5, grammarAccess.getKRoundedBendsPolylineAccess().getColonKeyword_3_2());
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1634:3: ( (lv_points_6_0= ruleKPosition ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1635:1: (lv_points_6_0= ruleKPosition )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1635:1: (lv_points_6_0= ruleKPosition )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1636:3: lv_points_6_0= ruleKPosition
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedBendsPolylineAccess().getPointsKPositionParserRuleCall_3_3_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKRoundedBendsPolyline3756);
                    lv_points_6_0=ruleKPosition();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKRoundedBendsPolylineRule());
                    	        }
                           		add(
                           			current, 
                           			"points",
                            		lv_points_6_0, 
                            		"KPosition");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1652:2: ( (otherlv_7= ',' )? ( (lv_points_8_0= ruleKPosition ) ) )*
                    loop74:
                    do {
                        int alt74=2;
                        int LA74_0 = input.LA(1);

                        if ( (LA74_0==13||(LA74_0>=59 && LA74_0<=60)) ) {
                            alt74=1;
                        }


                        switch (alt74) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1652:3: (otherlv_7= ',' )? ( (lv_points_8_0= ruleKPosition ) )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1652:3: (otherlv_7= ',' )?
                    	    int alt73=2;
                    	    int LA73_0 = input.LA(1);

                    	    if ( (LA73_0==13) ) {
                    	        alt73=1;
                    	    }
                    	    switch (alt73) {
                    	        case 1 :
                    	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1652:5: otherlv_7= ','
                    	            {
                    	            otherlv_7=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRoundedBendsPolyline3770); 

                    	                	newLeafNode(otherlv_7, grammarAccess.getKRoundedBendsPolylineAccess().getCommaKeyword_3_4_0());
                    	                

                    	            }
                    	            break;

                    	    }

                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1656:3: ( (lv_points_8_0= ruleKPosition ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1657:1: (lv_points_8_0= ruleKPosition )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1657:1: (lv_points_8_0= ruleKPosition )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1658:3: lv_points_8_0= ruleKPosition
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRoundedBendsPolylineAccess().getPointsKPositionParserRuleCall_3_4_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKRoundedBendsPolyline3793);
                    	    lv_points_8_0=ruleKPosition();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKRoundedBendsPolylineRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"points",
                    	            		lv_points_8_0, 
                    	            		"KPosition");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop74;
                        }
                    } while (true);

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1674:4: (otherlv_9= 'styles' (otherlv_10= ':' )? ( (lv_styles_11_0= ruleKStyle ) ) ( (otherlv_12= ',' )? ( (lv_styles_13_0= ruleKStyle ) ) )* )?
                    int alt78=2;
                    int LA78_0 = input.LA(1);

                    if ( (LA78_0==16) ) {
                        alt78=1;
                    }
                    switch (alt78) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1674:6: otherlv_9= 'styles' (otherlv_10= ':' )? ( (lv_styles_11_0= ruleKStyle ) ) ( (otherlv_12= ',' )? ( (lv_styles_13_0= ruleKStyle ) ) )*
                            {
                            otherlv_9=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRoundedBendsPolyline3808); 

                                	newLeafNode(otherlv_9, grammarAccess.getKRoundedBendsPolylineAccess().getStylesKeyword_3_5_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1678:1: (otherlv_10= ':' )?
                            int alt75=2;
                            int LA75_0 = input.LA(1);

                            if ( (LA75_0==17) ) {
                                alt75=1;
                            }
                            switch (alt75) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1678:3: otherlv_10= ':'
                                    {
                                    otherlv_10=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRoundedBendsPolyline3821); 

                                        	newLeafNode(otherlv_10, grammarAccess.getKRoundedBendsPolylineAccess().getColonKeyword_3_5_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1682:3: ( (lv_styles_11_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1683:1: (lv_styles_11_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1683:1: (lv_styles_11_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1684:3: lv_styles_11_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRoundedBendsPolylineAccess().getStylesKStyleParserRuleCall_3_5_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRoundedBendsPolyline3844);
                            lv_styles_11_0=ruleKStyle();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKRoundedBendsPolylineRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"styles",
                                    		lv_styles_11_0, 
                                    		"KStyle");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1700:2: ( (otherlv_12= ',' )? ( (lv_styles_13_0= ruleKStyle ) ) )*
                            loop77:
                            do {
                                int alt77=2;
                                int LA77_0 = input.LA(1);

                                if ( (LA77_0==13||(LA77_0>=56 && LA77_0<=57)||LA77_0==65||(LA77_0>=70 && LA77_0<=72)||(LA77_0>=75 && LA77_0<=81)) ) {
                                    alt77=1;
                                }


                                switch (alt77) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1700:3: (otherlv_12= ',' )? ( (lv_styles_13_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1700:3: (otherlv_12= ',' )?
                            	    int alt76=2;
                            	    int LA76_0 = input.LA(1);

                            	    if ( (LA76_0==13) ) {
                            	        alt76=1;
                            	    }
                            	    switch (alt76) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1700:5: otherlv_12= ','
                            	            {
                            	            otherlv_12=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRoundedBendsPolyline3858); 

                            	                	newLeafNode(otherlv_12, grammarAccess.getKRoundedBendsPolylineAccess().getCommaKeyword_3_5_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1704:3: ( (lv_styles_13_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1705:1: (lv_styles_13_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1705:1: (lv_styles_13_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1706:3: lv_styles_13_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKRoundedBendsPolylineAccess().getStylesKStyleParserRuleCall_3_5_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRoundedBendsPolyline3881);
                            	    lv_styles_13_0=ruleKStyle();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKRoundedBendsPolylineRule());
                            	    	        }
                            	           		add(
                            	           			current, 
                            	           			"styles",
                            	            		lv_styles_13_0, 
                            	            		"KStyle");
                            	    	        afterParserOrEnumRuleCall();
                            	    	    

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop77;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1722:6: (otherlv_14= 'placementData' (otherlv_15= ':' )? ( (lv_placementData_16_0= ruleKPlacementData ) ) )?
                    int alt80=2;
                    int LA80_0 = input.LA(1);

                    if ( (LA80_0==18) ) {
                        alt80=1;
                    }
                    switch (alt80) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1722:8: otherlv_14= 'placementData' (otherlv_15= ':' )? ( (lv_placementData_16_0= ruleKPlacementData ) )
                            {
                            otherlv_14=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRoundedBendsPolyline3898); 

                                	newLeafNode(otherlv_14, grammarAccess.getKRoundedBendsPolylineAccess().getPlacementDataKeyword_3_6_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1726:1: (otherlv_15= ':' )?
                            int alt79=2;
                            int LA79_0 = input.LA(1);

                            if ( (LA79_0==17) ) {
                                alt79=1;
                            }
                            switch (alt79) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1726:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRoundedBendsPolyline3911); 

                                        	newLeafNode(otherlv_15, grammarAccess.getKRoundedBendsPolylineAccess().getColonKeyword_3_6_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1730:3: ( (lv_placementData_16_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1731:1: (lv_placementData_16_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1731:1: (lv_placementData_16_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1732:3: lv_placementData_16_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRoundedBendsPolylineAccess().getPlacementDataKPlacementDataParserRuleCall_3_6_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRoundedBendsPolyline3934);
                            lv_placementData_16_0=ruleKPlacementData();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKRoundedBendsPolylineRule());
                            	        }
                                   		set(
                                   			current, 
                                   			"placementData",
                                    		lv_placementData_16_0, 
                                    		"KPlacementData");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1748:4: (otherlv_17= 'childPlacement' (otherlv_18= ':' )? ( (lv_childPlacement_19_0= ruleKPlacement ) ) )?
                    int alt82=2;
                    int LA82_0 = input.LA(1);

                    if ( (LA82_0==20) ) {
                        alt82=1;
                    }
                    switch (alt82) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1748:6: otherlv_17= 'childPlacement' (otherlv_18= ':' )? ( (lv_childPlacement_19_0= ruleKPlacement ) )
                            {
                            otherlv_17=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKRoundedBendsPolyline3949); 

                                	newLeafNode(otherlv_17, grammarAccess.getKRoundedBendsPolylineAccess().getChildPlacementKeyword_3_7_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1752:1: (otherlv_18= ':' )?
                            int alt81=2;
                            int LA81_0 = input.LA(1);

                            if ( (LA81_0==17) ) {
                                alt81=1;
                            }
                            switch (alt81) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1752:3: otherlv_18= ':'
                                    {
                                    otherlv_18=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRoundedBendsPolyline3962); 

                                        	newLeafNode(otherlv_18, grammarAccess.getKRoundedBendsPolylineAccess().getColonKeyword_3_7_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1756:3: ( (lv_childPlacement_19_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1757:1: (lv_childPlacement_19_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1757:1: (lv_childPlacement_19_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1758:3: lv_childPlacement_19_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRoundedBendsPolylineAccess().getChildPlacementKPlacementParserRuleCall_3_7_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKRoundedBendsPolyline3985);
                            lv_childPlacement_19_0=ruleKPlacement();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKRoundedBendsPolylineRule());
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1774:4: (otherlv_20= 'children' (otherlv_21= ':' )? ( (lv_children_22_0= ruleKRendering ) ) ( (otherlv_23= ',' )? ( (lv_children_24_0= ruleKRendering ) ) )* )?
                    int alt86=2;
                    int LA86_0 = input.LA(1);

                    if ( (LA86_0==21) ) {
                        alt86=1;
                    }
                    switch (alt86) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1774:6: otherlv_20= 'children' (otherlv_21= ':' )? ( (lv_children_22_0= ruleKRendering ) ) ( (otherlv_23= ',' )? ( (lv_children_24_0= ruleKRendering ) ) )*
                            {
                            otherlv_20=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKRoundedBendsPolyline4000); 

                                	newLeafNode(otherlv_20, grammarAccess.getKRoundedBendsPolylineAccess().getChildrenKeyword_3_8_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1778:1: (otherlv_21= ':' )?
                            int alt83=2;
                            int LA83_0 = input.LA(1);

                            if ( (LA83_0==17) ) {
                                alt83=1;
                            }
                            switch (alt83) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1778:3: otherlv_21= ':'
                                    {
                                    otherlv_21=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRoundedBendsPolyline4013); 

                                        	newLeafNode(otherlv_21, grammarAccess.getKRoundedBendsPolylineAccess().getColonKeyword_3_8_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1782:3: ( (lv_children_22_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1783:1: (lv_children_22_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1783:1: (lv_children_22_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1784:3: lv_children_22_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKRoundedBendsPolylineAccess().getChildrenKRenderingParserRuleCall_3_8_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRoundedBendsPolyline4036);
                            lv_children_22_0=ruleKRendering();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKRoundedBendsPolylineRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"children",
                                    		lv_children_22_0, 
                                    		"KRendering");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1800:2: ( (otherlv_23= ',' )? ( (lv_children_24_0= ruleKRendering ) ) )*
                            loop85:
                            do {
                                int alt85=2;
                                int LA85_0 = input.LA(1);

                                if ( (LA85_0==13||LA85_0==15||LA85_0==19||(LA85_0>=22 && LA85_0<=24)||(LA85_0>=26 && LA85_0<=28)||(LA85_0>=30 && LA85_0<=32)||LA85_0==34||LA85_0==37) ) {
                                    alt85=1;
                                }


                                switch (alt85) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1800:3: (otherlv_23= ',' )? ( (lv_children_24_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1800:3: (otherlv_23= ',' )?
                            	    int alt84=2;
                            	    int LA84_0 = input.LA(1);

                            	    if ( (LA84_0==13) ) {
                            	        alt84=1;
                            	    }
                            	    switch (alt84) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1800:5: otherlv_23= ','
                            	            {
                            	            otherlv_23=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRoundedBendsPolyline4050); 

                            	                	newLeafNode(otherlv_23, grammarAccess.getKRoundedBendsPolylineAccess().getCommaKeyword_3_8_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1804:3: ( (lv_children_24_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1805:1: (lv_children_24_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1805:1: (lv_children_24_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1806:3: lv_children_24_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKRoundedBendsPolylineAccess().getChildrenKRenderingParserRuleCall_3_8_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRoundedBendsPolyline4073);
                            	    lv_children_24_0=ruleKRendering();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKRoundedBendsPolylineRule());
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


                            }
                            break;

                    }

                    otherlv_25=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRoundedBendsPolyline4089); 

                        	newLeafNode(otherlv_25, grammarAccess.getKRoundedBendsPolylineAccess().getRightCurlyBracketKeyword_3_9());
                        

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1834:1: entryRuleKPolygon returns [EObject current=null] : iv_ruleKPolygon= ruleKPolygon EOF ;
    public final EObject entryRuleKPolygon() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolygon = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1835:2: (iv_ruleKPolygon= ruleKPolygon EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1836:2: iv_ruleKPolygon= ruleKPolygon EOF
            {
             newCompositeNode(grammarAccess.getKPolygonRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPolygon_in_entryRuleKPolygon4127);
            iv_ruleKPolygon=ruleKPolygon();

            state._fsp--;

             current =iv_ruleKPolygon; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPolygon4137); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1843:1: ruleKPolygon returns [EObject current=null] : ( () otherlv_1= 'Polygon' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1846:28: ( ( () otherlv_1= 'Polygon' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1847:1: ( () otherlv_1= 'Polygon' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1847:1: ( () otherlv_1= 'Polygon' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1847:2: () otherlv_1= 'Polygon' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1847:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1848:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPolygonAccess().getKPolygonAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleKPolygon4183); 

                	newLeafNode(otherlv_1, grammarAccess.getKPolygonAccess().getPolygonKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1857:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            int alt100=2;
            int LA100_0 = input.LA(1);

            if ( (LA100_0==12) ) {
                alt100=1;
            }
            switch (alt100) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1857:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolygon4196); 

                        	newLeafNode(otherlv_2, grammarAccess.getKPolygonAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1861:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt91=2;
                    int LA91_0 = input.LA(1);

                    if ( (LA91_0==16) ) {
                        alt91=1;
                    }
                    switch (alt91) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1861:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKPolygon4209); 

                                	newLeafNode(otherlv_3, grammarAccess.getKPolygonAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1865:1: (otherlv_4= ':' )?
                            int alt88=2;
                            int LA88_0 = input.LA(1);

                            if ( (LA88_0==17) ) {
                                alt88=1;
                            }
                            switch (alt88) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1865:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolygon4222); 

                                        	newLeafNode(otherlv_4, grammarAccess.getKPolygonAccess().getColonKeyword_2_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1869:3: ( (lv_styles_5_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1870:1: (lv_styles_5_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1870:1: (lv_styles_5_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1871:3: lv_styles_5_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolygonAccess().getStylesKStyleParserRuleCall_2_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolygon4245);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1887:2: ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            loop90:
                            do {
                                int alt90=2;
                                int LA90_0 = input.LA(1);

                                if ( (LA90_0==13||(LA90_0>=56 && LA90_0<=57)||LA90_0==65||(LA90_0>=70 && LA90_0<=72)||(LA90_0>=75 && LA90_0<=81)) ) {
                                    alt90=1;
                                }


                                switch (alt90) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1887:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1887:3: (otherlv_6= ',' )?
                            	    int alt89=2;
                            	    int LA89_0 = input.LA(1);

                            	    if ( (LA89_0==13) ) {
                            	        alt89=1;
                            	    }
                            	    switch (alt89) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1887:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPolygon4259); 

                            	                	newLeafNode(otherlv_6, grammarAccess.getKPolygonAccess().getCommaKeyword_2_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1891:3: ( (lv_styles_7_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1892:1: (lv_styles_7_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1892:1: (lv_styles_7_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1893:3: lv_styles_7_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKPolygonAccess().getStylesKStyleParserRuleCall_2_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolygon4282);
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
                            	    break loop90;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1909:6: (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
                    int alt93=2;
                    int LA93_0 = input.LA(1);

                    if ( (LA93_0==18) ) {
                        alt93=1;
                    }
                    switch (alt93) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1909:8: otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPolygon4299); 

                                	newLeafNode(otherlv_8, grammarAccess.getKPolygonAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1913:1: (otherlv_9= ':' )?
                            int alt92=2;
                            int LA92_0 = input.LA(1);

                            if ( (LA92_0==17) ) {
                                alt92=1;
                            }
                            switch (alt92) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1913:3: otherlv_9= ':'
                                    {
                                    otherlv_9=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolygon4312); 

                                        	newLeafNode(otherlv_9, grammarAccess.getKPolygonAccess().getColonKeyword_2_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1917:3: ( (lv_placementData_10_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1918:1: (lv_placementData_10_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1918:1: (lv_placementData_10_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1919:3: lv_placementData_10_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolygonAccess().getPlacementDataKPlacementDataParserRuleCall_2_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKPolygon4335);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1935:4: (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )?
                    int alt95=2;
                    int LA95_0 = input.LA(1);

                    if ( (LA95_0==20) ) {
                        alt95=1;
                    }
                    switch (alt95) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1935:6: otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            {
                            otherlv_11=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKPolygon4350); 

                                	newLeafNode(otherlv_11, grammarAccess.getKPolygonAccess().getChildPlacementKeyword_2_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1939:1: (otherlv_12= ':' )?
                            int alt94=2;
                            int LA94_0 = input.LA(1);

                            if ( (LA94_0==17) ) {
                                alt94=1;
                            }
                            switch (alt94) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1939:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolygon4363); 

                                        	newLeafNode(otherlv_12, grammarAccess.getKPolygonAccess().getColonKeyword_2_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1943:3: ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1944:1: (lv_childPlacement_13_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1944:1: (lv_childPlacement_13_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1945:3: lv_childPlacement_13_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolygonAccess().getChildPlacementKPlacementParserRuleCall_2_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKPolygon4386);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1961:4: (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )?
                    int alt99=2;
                    int LA99_0 = input.LA(1);

                    if ( (LA99_0==21) ) {
                        alt99=1;
                    }
                    switch (alt99) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1961:6: otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            {
                            otherlv_14=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKPolygon4401); 

                                	newLeafNode(otherlv_14, grammarAccess.getKPolygonAccess().getChildrenKeyword_2_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1965:1: (otherlv_15= ':' )?
                            int alt96=2;
                            int LA96_0 = input.LA(1);

                            if ( (LA96_0==17) ) {
                                alt96=1;
                            }
                            switch (alt96) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1965:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolygon4414); 

                                        	newLeafNode(otherlv_15, grammarAccess.getKPolygonAccess().getColonKeyword_2_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1969:3: ( (lv_children_16_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1970:1: (lv_children_16_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1970:1: (lv_children_16_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1971:3: lv_children_16_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKPolygonAccess().getChildrenKRenderingParserRuleCall_2_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolygon4437);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1987:2: ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            loop98:
                            do {
                                int alt98=2;
                                int LA98_0 = input.LA(1);

                                if ( (LA98_0==13||LA98_0==15||LA98_0==19||(LA98_0>=22 && LA98_0<=24)||(LA98_0>=26 && LA98_0<=28)||(LA98_0>=30 && LA98_0<=32)||LA98_0==34||LA98_0==37) ) {
                                    alt98=1;
                                }


                                switch (alt98) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1987:3: (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1987:3: (otherlv_17= ',' )?
                            	    int alt97=2;
                            	    int LA97_0 = input.LA(1);

                            	    if ( (LA97_0==13) ) {
                            	        alt97=1;
                            	    }
                            	    switch (alt97) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1987:5: otherlv_17= ','
                            	            {
                            	            otherlv_17=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPolygon4451); 

                            	                	newLeafNode(otherlv_17, grammarAccess.getKPolygonAccess().getCommaKeyword_2_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1991:3: ( (lv_children_18_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1992:1: (lv_children_18_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1992:1: (lv_children_18_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:1993:3: lv_children_18_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKPolygonAccess().getChildrenKRenderingParserRuleCall_2_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolygon4474);
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
                            	    break loop98;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_19=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolygon4490); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2021:1: entryRuleKImage returns [EObject current=null] : iv_ruleKImage= ruleKImage EOF ;
    public final EObject entryRuleKImage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKImage = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2022:2: (iv_ruleKImage= ruleKImage EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2023:2: iv_ruleKImage= ruleKImage EOF
            {
             newCompositeNode(grammarAccess.getKImageRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKImage_in_entryRuleKImage4528);
            iv_ruleKImage=ruleKImage();

            state._fsp--;

             current =iv_ruleKImage; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKImage4538); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2030:1: ruleKImage returns [EObject current=null] : ( () otherlv_1= 'Image' ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' ) ( (lv_imagePath_4_0= ruleEString ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2033:28: ( ( () otherlv_1= 'Image' ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' ) ( (lv_imagePath_4_0= ruleEString ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2034:1: ( () otherlv_1= 'Image' ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' ) ( (lv_imagePath_4_0= ruleEString ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2034:1: ( () otherlv_1= 'Image' ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' ) ( (lv_imagePath_4_0= ruleEString ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2034:2: () otherlv_1= 'Image' ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' ) ( (lv_imagePath_4_0= ruleEString ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2034:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2035:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKImageAccess().getKImageAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleKImage4584); 

                	newLeafNode(otherlv_1, grammarAccess.getKImageAccess().getImageKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2044:1: ( ( (lv_bundleName_2_0= ruleEString ) ) | otherlv_3= '-' )
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( ((LA101_0>=RULE_STRING && LA101_0<=RULE_ID)) ) {
                alt101=1;
            }
            else if ( (LA101_0==29) ) {
                alt101=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 101, 0, input);

                throw nvae;
            }
            switch (alt101) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2044:2: ( (lv_bundleName_2_0= ruleEString ) )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2044:2: ( (lv_bundleName_2_0= ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2045:1: (lv_bundleName_2_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2045:1: (lv_bundleName_2_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2046:3: lv_bundleName_2_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getKImageAccess().getBundleNameEStringParserRuleCall_2_0_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKImage4606);
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
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2063:7: otherlv_3= '-'
                    {
                    otherlv_3=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleKImage4624); 

                        	newLeafNode(otherlv_3, grammarAccess.getKImageAccess().getHyphenMinusKeyword_2_1());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2067:2: ( (lv_imagePath_4_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2068:1: (lv_imagePath_4_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2068:1: (lv_imagePath_4_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2069:3: lv_imagePath_4_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKImageAccess().getImagePathEStringParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKImage4646);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2085:2: (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )?
            int alt114=2;
            int LA114_0 = input.LA(1);

            if ( (LA114_0==12) ) {
                alt114=1;
            }
            switch (alt114) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2085:4: otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}'
                    {
                    otherlv_5=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKImage4659); 

                        	newLeafNode(otherlv_5, grammarAccess.getKImageAccess().getLeftCurlyBracketKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2089:1: (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )?
                    int alt105=2;
                    int LA105_0 = input.LA(1);

                    if ( (LA105_0==16) ) {
                        alt105=1;
                    }
                    switch (alt105) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2089:3: otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            {
                            otherlv_6=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKImage4672); 

                                	newLeafNode(otherlv_6, grammarAccess.getKImageAccess().getStylesKeyword_4_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2093:1: (otherlv_7= ':' )?
                            int alt102=2;
                            int LA102_0 = input.LA(1);

                            if ( (LA102_0==17) ) {
                                alt102=1;
                            }
                            switch (alt102) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2093:3: otherlv_7= ':'
                                    {
                                    otherlv_7=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKImage4685); 

                                        	newLeafNode(otherlv_7, grammarAccess.getKImageAccess().getColonKeyword_4_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2097:3: ( (lv_styles_8_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2098:1: (lv_styles_8_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2098:1: (lv_styles_8_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2099:3: lv_styles_8_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKImageAccess().getStylesKStyleParserRuleCall_4_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKImage4708);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2115:2: ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            loop104:
                            do {
                                int alt104=2;
                                int LA104_0 = input.LA(1);

                                if ( (LA104_0==13||(LA104_0>=56 && LA104_0<=57)||LA104_0==65||(LA104_0>=70 && LA104_0<=72)||(LA104_0>=75 && LA104_0<=81)) ) {
                                    alt104=1;
                                }


                                switch (alt104) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2115:3: (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2115:3: (otherlv_9= ',' )?
                            	    int alt103=2;
                            	    int LA103_0 = input.LA(1);

                            	    if ( (LA103_0==13) ) {
                            	        alt103=1;
                            	    }
                            	    switch (alt103) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2115:5: otherlv_9= ','
                            	            {
                            	            otherlv_9=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKImage4722); 

                            	                	newLeafNode(otherlv_9, grammarAccess.getKImageAccess().getCommaKeyword_4_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2119:3: ( (lv_styles_10_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2120:1: (lv_styles_10_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2120:1: (lv_styles_10_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2121:3: lv_styles_10_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKImageAccess().getStylesKStyleParserRuleCall_4_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKImage4745);
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
                            	    break loop104;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2137:6: (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )?
                    int alt107=2;
                    int LA107_0 = input.LA(1);

                    if ( (LA107_0==18) ) {
                        alt107=1;
                    }
                    switch (alt107) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2137:8: otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) )
                            {
                            otherlv_11=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKImage4762); 

                                	newLeafNode(otherlv_11, grammarAccess.getKImageAccess().getPlacementDataKeyword_4_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2141:1: (otherlv_12= ':' )?
                            int alt106=2;
                            int LA106_0 = input.LA(1);

                            if ( (LA106_0==17) ) {
                                alt106=1;
                            }
                            switch (alt106) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2141:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKImage4775); 

                                        	newLeafNode(otherlv_12, grammarAccess.getKImageAccess().getColonKeyword_4_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2145:3: ( (lv_placementData_13_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2146:1: (lv_placementData_13_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2146:1: (lv_placementData_13_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2147:3: lv_placementData_13_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKImageAccess().getPlacementDataKPlacementDataParserRuleCall_4_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKImage4798);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2163:4: (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )?
                    int alt109=2;
                    int LA109_0 = input.LA(1);

                    if ( (LA109_0==20) ) {
                        alt109=1;
                    }
                    switch (alt109) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2163:6: otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) )
                            {
                            otherlv_14=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKImage4813); 

                                	newLeafNode(otherlv_14, grammarAccess.getKImageAccess().getChildPlacementKeyword_4_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2167:1: (otherlv_15= ':' )?
                            int alt108=2;
                            int LA108_0 = input.LA(1);

                            if ( (LA108_0==17) ) {
                                alt108=1;
                            }
                            switch (alt108) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2167:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKImage4826); 

                                        	newLeafNode(otherlv_15, grammarAccess.getKImageAccess().getColonKeyword_4_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2171:3: ( (lv_childPlacement_16_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2172:1: (lv_childPlacement_16_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2172:1: (lv_childPlacement_16_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2173:3: lv_childPlacement_16_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKImageAccess().getChildPlacementKPlacementParserRuleCall_4_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKImage4849);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2189:4: (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )?
                    int alt113=2;
                    int LA113_0 = input.LA(1);

                    if ( (LA113_0==21) ) {
                        alt113=1;
                    }
                    switch (alt113) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2189:6: otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )*
                            {
                            otherlv_17=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKImage4864); 

                                	newLeafNode(otherlv_17, grammarAccess.getKImageAccess().getChildrenKeyword_4_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2193:1: (otherlv_18= ':' )?
                            int alt110=2;
                            int LA110_0 = input.LA(1);

                            if ( (LA110_0==17) ) {
                                alt110=1;
                            }
                            switch (alt110) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2193:3: otherlv_18= ':'
                                    {
                                    otherlv_18=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKImage4877); 

                                        	newLeafNode(otherlv_18, grammarAccess.getKImageAccess().getColonKeyword_4_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2197:3: ( (lv_children_19_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2198:1: (lv_children_19_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2198:1: (lv_children_19_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2199:3: lv_children_19_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKImageAccess().getChildrenKRenderingParserRuleCall_4_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKImage4900);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2215:2: ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )*
                            loop112:
                            do {
                                int alt112=2;
                                int LA112_0 = input.LA(1);

                                if ( (LA112_0==13||LA112_0==15||LA112_0==19||(LA112_0>=22 && LA112_0<=24)||(LA112_0>=26 && LA112_0<=28)||(LA112_0>=30 && LA112_0<=32)||LA112_0==34||LA112_0==37) ) {
                                    alt112=1;
                                }


                                switch (alt112) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2215:3: (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2215:3: (otherlv_20= ',' )?
                            	    int alt111=2;
                            	    int LA111_0 = input.LA(1);

                            	    if ( (LA111_0==13) ) {
                            	        alt111=1;
                            	    }
                            	    switch (alt111) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2215:5: otherlv_20= ','
                            	            {
                            	            otherlv_20=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKImage4914); 

                            	                	newLeafNode(otherlv_20, grammarAccess.getKImageAccess().getCommaKeyword_4_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2219:3: ( (lv_children_21_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2220:1: (lv_children_21_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2220:1: (lv_children_21_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2221:3: lv_children_21_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKImageAccess().getChildrenKRenderingParserRuleCall_4_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKImage4937);
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
                            	    break loop112;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_22=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKImage4953); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2249:1: entryRuleKArc returns [EObject current=null] : iv_ruleKArc= ruleKArc EOF ;
    public final EObject entryRuleKArc() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKArc = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2250:2: (iv_ruleKArc= ruleKArc EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2251:2: iv_ruleKArc= ruleKArc EOF
            {
             newCompositeNode(grammarAccess.getKArcRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKArc_in_entryRuleKArc4991);
            iv_ruleKArc=ruleKArc();

            state._fsp--;

             current =iv_ruleKArc; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKArc5001); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2258:1: ruleKArc returns [EObject current=null] : ( () otherlv_1= 'Arc' ( (lv_startAngle_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_arcAngle_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2261:28: ( ( () otherlv_1= 'Arc' ( (lv_startAngle_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_arcAngle_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2262:1: ( () otherlv_1= 'Arc' ( (lv_startAngle_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_arcAngle_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2262:1: ( () otherlv_1= 'Arc' ( (lv_startAngle_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_arcAngle_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2262:2: () otherlv_1= 'Arc' ( (lv_startAngle_2_0= ruleEFloat ) ) (otherlv_3= ',' )? ( (lv_arcAngle_4_0= ruleEFloat ) ) (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2262:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2263:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKArcAccess().getKArcAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,30,FollowSets000.FOLLOW_30_in_ruleKArc5047); 

                	newLeafNode(otherlv_1, grammarAccess.getKArcAccess().getArcKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2272:1: ( (lv_startAngle_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2273:1: (lv_startAngle_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2273:1: (lv_startAngle_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2274:3: lv_startAngle_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKArcAccess().getStartAngleEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKArc5068);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2290:2: (otherlv_3= ',' )?
            int alt115=2;
            int LA115_0 = input.LA(1);

            if ( (LA115_0==13) ) {
                alt115=1;
            }
            switch (alt115) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2290:4: otherlv_3= ','
                    {
                    otherlv_3=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKArc5081); 

                        	newLeafNode(otherlv_3, grammarAccess.getKArcAccess().getCommaKeyword_3());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2294:3: ( (lv_arcAngle_4_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2295:1: (lv_arcAngle_4_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2295:1: (lv_arcAngle_4_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2296:3: lv_arcAngle_4_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKArcAccess().getArcAngleEFloatParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKArc5104);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2312:2: (otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}' )?
            int alt128=2;
            int LA128_0 = input.LA(1);

            if ( (LA128_0==12) ) {
                alt128=1;
            }
            switch (alt128) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2312:4: otherlv_5= '{' (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )? (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )? (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )? otherlv_22= '}'
                    {
                    otherlv_5=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKArc5117); 

                        	newLeafNode(otherlv_5, grammarAccess.getKArcAccess().getLeftCurlyBracketKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2316:1: (otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )* )?
                    int alt119=2;
                    int LA119_0 = input.LA(1);

                    if ( (LA119_0==16) ) {
                        alt119=1;
                    }
                    switch (alt119) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2316:3: otherlv_6= 'styles' (otherlv_7= ':' )? ( (lv_styles_8_0= ruleKStyle ) ) ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            {
                            otherlv_6=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKArc5130); 

                                	newLeafNode(otherlv_6, grammarAccess.getKArcAccess().getStylesKeyword_5_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2320:1: (otherlv_7= ':' )?
                            int alt116=2;
                            int LA116_0 = input.LA(1);

                            if ( (LA116_0==17) ) {
                                alt116=1;
                            }
                            switch (alt116) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2320:3: otherlv_7= ':'
                                    {
                                    otherlv_7=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKArc5143); 

                                        	newLeafNode(otherlv_7, grammarAccess.getKArcAccess().getColonKeyword_5_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2324:3: ( (lv_styles_8_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2325:1: (lv_styles_8_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2325:1: (lv_styles_8_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2326:3: lv_styles_8_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKArcAccess().getStylesKStyleParserRuleCall_5_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKArc5166);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2342:2: ( (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) ) )*
                            loop118:
                            do {
                                int alt118=2;
                                int LA118_0 = input.LA(1);

                                if ( (LA118_0==13||(LA118_0>=56 && LA118_0<=57)||LA118_0==65||(LA118_0>=70 && LA118_0<=72)||(LA118_0>=75 && LA118_0<=81)) ) {
                                    alt118=1;
                                }


                                switch (alt118) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2342:3: (otherlv_9= ',' )? ( (lv_styles_10_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2342:3: (otherlv_9= ',' )?
                            	    int alt117=2;
                            	    int LA117_0 = input.LA(1);

                            	    if ( (LA117_0==13) ) {
                            	        alt117=1;
                            	    }
                            	    switch (alt117) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2342:5: otherlv_9= ','
                            	            {
                            	            otherlv_9=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKArc5180); 

                            	                	newLeafNode(otherlv_9, grammarAccess.getKArcAccess().getCommaKeyword_5_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2346:3: ( (lv_styles_10_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2347:1: (lv_styles_10_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2347:1: (lv_styles_10_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2348:3: lv_styles_10_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKArcAccess().getStylesKStyleParserRuleCall_5_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKArc5203);
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
                            	    break loop118;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2364:6: (otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) ) )?
                    int alt121=2;
                    int LA121_0 = input.LA(1);

                    if ( (LA121_0==18) ) {
                        alt121=1;
                    }
                    switch (alt121) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2364:8: otherlv_11= 'placementData' (otherlv_12= ':' )? ( (lv_placementData_13_0= ruleKPlacementData ) )
                            {
                            otherlv_11=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKArc5220); 

                                	newLeafNode(otherlv_11, grammarAccess.getKArcAccess().getPlacementDataKeyword_5_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2368:1: (otherlv_12= ':' )?
                            int alt120=2;
                            int LA120_0 = input.LA(1);

                            if ( (LA120_0==17) ) {
                                alt120=1;
                            }
                            switch (alt120) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2368:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKArc5233); 

                                        	newLeafNode(otherlv_12, grammarAccess.getKArcAccess().getColonKeyword_5_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2372:3: ( (lv_placementData_13_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2373:1: (lv_placementData_13_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2373:1: (lv_placementData_13_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2374:3: lv_placementData_13_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKArcAccess().getPlacementDataKPlacementDataParserRuleCall_5_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKArc5256);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2390:4: (otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) ) )?
                    int alt123=2;
                    int LA123_0 = input.LA(1);

                    if ( (LA123_0==20) ) {
                        alt123=1;
                    }
                    switch (alt123) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2390:6: otherlv_14= 'childPlacement' (otherlv_15= ':' )? ( (lv_childPlacement_16_0= ruleKPlacement ) )
                            {
                            otherlv_14=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKArc5271); 

                                	newLeafNode(otherlv_14, grammarAccess.getKArcAccess().getChildPlacementKeyword_5_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2394:1: (otherlv_15= ':' )?
                            int alt122=2;
                            int LA122_0 = input.LA(1);

                            if ( (LA122_0==17) ) {
                                alt122=1;
                            }
                            switch (alt122) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2394:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKArc5284); 

                                        	newLeafNode(otherlv_15, grammarAccess.getKArcAccess().getColonKeyword_5_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2398:3: ( (lv_childPlacement_16_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2399:1: (lv_childPlacement_16_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2399:1: (lv_childPlacement_16_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2400:3: lv_childPlacement_16_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKArcAccess().getChildPlacementKPlacementParserRuleCall_5_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKArc5307);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2416:4: (otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )* )?
                    int alt127=2;
                    int LA127_0 = input.LA(1);

                    if ( (LA127_0==21) ) {
                        alt127=1;
                    }
                    switch (alt127) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2416:6: otherlv_17= 'children' (otherlv_18= ':' )? ( (lv_children_19_0= ruleKRendering ) ) ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )*
                            {
                            otherlv_17=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKArc5322); 

                                	newLeafNode(otherlv_17, grammarAccess.getKArcAccess().getChildrenKeyword_5_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2420:1: (otherlv_18= ':' )?
                            int alt124=2;
                            int LA124_0 = input.LA(1);

                            if ( (LA124_0==17) ) {
                                alt124=1;
                            }
                            switch (alt124) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2420:3: otherlv_18= ':'
                                    {
                                    otherlv_18=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKArc5335); 

                                        	newLeafNode(otherlv_18, grammarAccess.getKArcAccess().getColonKeyword_5_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2424:3: ( (lv_children_19_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2425:1: (lv_children_19_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2425:1: (lv_children_19_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2426:3: lv_children_19_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKArcAccess().getChildrenKRenderingParserRuleCall_5_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKArc5358);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2442:2: ( (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) ) )*
                            loop126:
                            do {
                                int alt126=2;
                                int LA126_0 = input.LA(1);

                                if ( (LA126_0==13||LA126_0==15||LA126_0==19||(LA126_0>=22 && LA126_0<=24)||(LA126_0>=26 && LA126_0<=28)||(LA126_0>=30 && LA126_0<=32)||LA126_0==34||LA126_0==37) ) {
                                    alt126=1;
                                }


                                switch (alt126) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2442:3: (otherlv_20= ',' )? ( (lv_children_21_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2442:3: (otherlv_20= ',' )?
                            	    int alt125=2;
                            	    int LA125_0 = input.LA(1);

                            	    if ( (LA125_0==13) ) {
                            	        alt125=1;
                            	    }
                            	    switch (alt125) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2442:5: otherlv_20= ','
                            	            {
                            	            otherlv_20=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKArc5372); 

                            	                	newLeafNode(otherlv_20, grammarAccess.getKArcAccess().getCommaKeyword_5_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2446:3: ( (lv_children_21_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2447:1: (lv_children_21_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2447:1: (lv_children_21_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2448:3: lv_children_21_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKArcAccess().getChildrenKRenderingParserRuleCall_5_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKArc5395);
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
                            	    break loop126;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_22=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKArc5411); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2476:1: entryRuleKChildArea returns [EObject current=null] : iv_ruleKChildArea= ruleKChildArea EOF ;
    public final EObject entryRuleKChildArea() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKChildArea = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2477:2: (iv_ruleKChildArea= ruleKChildArea EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2478:2: iv_ruleKChildArea= ruleKChildArea EOF
            {
             newCompositeNode(grammarAccess.getKChildAreaRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKChildArea_in_entryRuleKChildArea5449);
            iv_ruleKChildArea=ruleKChildArea();

            state._fsp--;

             current =iv_ruleKChildArea; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKChildArea5459); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2485:1: ruleKChildArea returns [EObject current=null] : ( () otherlv_1= 'ChildArea' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? otherlv_11= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2488:28: ( ( () otherlv_1= 'ChildArea' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? otherlv_11= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2489:1: ( () otherlv_1= 'ChildArea' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? otherlv_11= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2489:1: ( () otherlv_1= 'ChildArea' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? otherlv_11= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2489:2: () otherlv_1= 'ChildArea' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? otherlv_11= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2489:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2490:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKChildAreaAccess().getKChildAreaAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,31,FollowSets000.FOLLOW_31_in_ruleKChildArea5505); 

                	newLeafNode(otherlv_1, grammarAccess.getKChildAreaAccess().getChildAreaKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2499:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? otherlv_11= '}' )?
            int alt135=2;
            int LA135_0 = input.LA(1);

            if ( (LA135_0==12) ) {
                alt135=1;
            }
            switch (alt135) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2499:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? otherlv_11= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKChildArea5518); 

                        	newLeafNode(otherlv_2, grammarAccess.getKChildAreaAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2503:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt132=2;
                    int LA132_0 = input.LA(1);

                    if ( (LA132_0==16) ) {
                        alt132=1;
                    }
                    switch (alt132) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2503:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKChildArea5531); 

                                	newLeafNode(otherlv_3, grammarAccess.getKChildAreaAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2507:1: (otherlv_4= ':' )?
                            int alt129=2;
                            int LA129_0 = input.LA(1);

                            if ( (LA129_0==17) ) {
                                alt129=1;
                            }
                            switch (alt129) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2507:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKChildArea5544); 

                                        	newLeafNode(otherlv_4, grammarAccess.getKChildAreaAccess().getColonKeyword_2_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2511:3: ( (lv_styles_5_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2512:1: (lv_styles_5_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2512:1: (lv_styles_5_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2513:3: lv_styles_5_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKChildAreaAccess().getStylesKStyleParserRuleCall_2_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKChildArea5567);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2529:2: ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            loop131:
                            do {
                                int alt131=2;
                                int LA131_0 = input.LA(1);

                                if ( (LA131_0==13||(LA131_0>=56 && LA131_0<=57)||LA131_0==65||(LA131_0>=70 && LA131_0<=72)||(LA131_0>=75 && LA131_0<=81)) ) {
                                    alt131=1;
                                }


                                switch (alt131) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2529:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2529:3: (otherlv_6= ',' )?
                            	    int alt130=2;
                            	    int LA130_0 = input.LA(1);

                            	    if ( (LA130_0==13) ) {
                            	        alt130=1;
                            	    }
                            	    switch (alt130) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2529:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKChildArea5581); 

                            	                	newLeafNode(otherlv_6, grammarAccess.getKChildAreaAccess().getCommaKeyword_2_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2533:3: ( (lv_styles_7_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2534:1: (lv_styles_7_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2534:1: (lv_styles_7_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2535:3: lv_styles_7_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKChildAreaAccess().getStylesKStyleParserRuleCall_2_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKChildArea5604);
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
                            	    break loop131;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2551:6: (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
                    int alt134=2;
                    int LA134_0 = input.LA(1);

                    if ( (LA134_0==18) ) {
                        alt134=1;
                    }
                    switch (alt134) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2551:8: otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKChildArea5621); 

                                	newLeafNode(otherlv_8, grammarAccess.getKChildAreaAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2555:1: (otherlv_9= ':' )?
                            int alt133=2;
                            int LA133_0 = input.LA(1);

                            if ( (LA133_0==17) ) {
                                alt133=1;
                            }
                            switch (alt133) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2555:3: otherlv_9= ':'
                                    {
                                    otherlv_9=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKChildArea5634); 

                                        	newLeafNode(otherlv_9, grammarAccess.getKChildAreaAccess().getColonKeyword_2_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2559:3: ( (lv_placementData_10_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2560:1: (lv_placementData_10_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2560:1: (lv_placementData_10_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2561:3: lv_placementData_10_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKChildAreaAccess().getPlacementDataKPlacementDataParserRuleCall_2_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKChildArea5657);
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

                    otherlv_11=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKChildArea5671); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2589:1: entryRuleKText returns [EObject current=null] : iv_ruleKText= ruleKText EOF ;
    public final EObject entryRuleKText() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKText = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2590:2: (iv_ruleKText= ruleKText EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2591:2: iv_ruleKText= ruleKText EOF
            {
             newCompositeNode(grammarAccess.getKTextRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKText_in_entryRuleKText5709);
            iv_ruleKText=ruleKText();

            state._fsp--;

             current =iv_ruleKText; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKText5719); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2598:1: ruleKText returns [EObject current=null] : ( () otherlv_1= 'Text' ( (lv_text_2_0= ruleEString ) )? (otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'childPlacement' (otherlv_13= ':' )? ( (lv_childPlacement_14_0= ruleKPlacement ) ) )? (otherlv_15= 'children' (otherlv_16= ':' )? ( (lv_children_17_0= ruleKRendering ) ) ( (otherlv_18= ',' )? ( (lv_children_19_0= ruleKRendering ) ) )* )? (otherlv_20= 'mapProperties' (otherlv_21= ':' )? ( (lv_persistentEntries_22_0= rulePersistentEntry ) ) ( (otherlv_23= ',' )? ( (lv_persistentEntries_24_0= rulePersistentEntry ) ) )* )? otherlv_25= '}' )? ) ;
    public final EObject ruleKText() throws RecognitionException {
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
        Token otherlv_21=null;
        Token otherlv_23=null;
        Token otherlv_25=null;
        AntlrDatatypeRuleToken lv_text_2_0 = null;

        EObject lv_styles_6_0 = null;

        EObject lv_styles_8_0 = null;

        EObject lv_placementData_11_0 = null;

        EObject lv_childPlacement_14_0 = null;

        EObject lv_children_17_0 = null;

        EObject lv_children_19_0 = null;

        EObject lv_persistentEntries_22_0 = null;

        EObject lv_persistentEntries_24_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2601:28: ( ( () otherlv_1= 'Text' ( (lv_text_2_0= ruleEString ) )? (otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'childPlacement' (otherlv_13= ':' )? ( (lv_childPlacement_14_0= ruleKPlacement ) ) )? (otherlv_15= 'children' (otherlv_16= ':' )? ( (lv_children_17_0= ruleKRendering ) ) ( (otherlv_18= ',' )? ( (lv_children_19_0= ruleKRendering ) ) )* )? (otherlv_20= 'mapProperties' (otherlv_21= ':' )? ( (lv_persistentEntries_22_0= rulePersistentEntry ) ) ( (otherlv_23= ',' )? ( (lv_persistentEntries_24_0= rulePersistentEntry ) ) )* )? otherlv_25= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2602:1: ( () otherlv_1= 'Text' ( (lv_text_2_0= ruleEString ) )? (otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'childPlacement' (otherlv_13= ':' )? ( (lv_childPlacement_14_0= ruleKPlacement ) ) )? (otherlv_15= 'children' (otherlv_16= ':' )? ( (lv_children_17_0= ruleKRendering ) ) ( (otherlv_18= ',' )? ( (lv_children_19_0= ruleKRendering ) ) )* )? (otherlv_20= 'mapProperties' (otherlv_21= ':' )? ( (lv_persistentEntries_22_0= rulePersistentEntry ) ) ( (otherlv_23= ',' )? ( (lv_persistentEntries_24_0= rulePersistentEntry ) ) )* )? otherlv_25= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2602:1: ( () otherlv_1= 'Text' ( (lv_text_2_0= ruleEString ) )? (otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'childPlacement' (otherlv_13= ':' )? ( (lv_childPlacement_14_0= ruleKPlacement ) ) )? (otherlv_15= 'children' (otherlv_16= ':' )? ( (lv_children_17_0= ruleKRendering ) ) ( (otherlv_18= ',' )? ( (lv_children_19_0= ruleKRendering ) ) )* )? (otherlv_20= 'mapProperties' (otherlv_21= ':' )? ( (lv_persistentEntries_22_0= rulePersistentEntry ) ) ( (otherlv_23= ',' )? ( (lv_persistentEntries_24_0= rulePersistentEntry ) ) )* )? otherlv_25= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2602:2: () otherlv_1= 'Text' ( (lv_text_2_0= ruleEString ) )? (otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'childPlacement' (otherlv_13= ':' )? ( (lv_childPlacement_14_0= ruleKPlacement ) ) )? (otherlv_15= 'children' (otherlv_16= ':' )? ( (lv_children_17_0= ruleKRendering ) ) ( (otherlv_18= ',' )? ( (lv_children_19_0= ruleKRendering ) ) )* )? (otherlv_20= 'mapProperties' (otherlv_21= ':' )? ( (lv_persistentEntries_22_0= rulePersistentEntry ) ) ( (otherlv_23= ',' )? ( (lv_persistentEntries_24_0= rulePersistentEntry ) ) )* )? otherlv_25= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2602:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2603:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKTextAccess().getKTextAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKText5765); 

                	newLeafNode(otherlv_1, grammarAccess.getKTextAccess().getTextKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2612:1: ( (lv_text_2_0= ruleEString ) )?
            int alt136=2;
            int LA136_0 = input.LA(1);

            if ( ((LA136_0>=RULE_STRING && LA136_0<=RULE_ID)) ) {
                alt136=1;
            }
            switch (alt136) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2613:1: (lv_text_2_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2613:1: (lv_text_2_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2614:3: lv_text_2_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getTextEStringParserRuleCall_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKText5786);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2630:3: (otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'childPlacement' (otherlv_13= ':' )? ( (lv_childPlacement_14_0= ruleKPlacement ) ) )? (otherlv_15= 'children' (otherlv_16= ':' )? ( (lv_children_17_0= ruleKRendering ) ) ( (otherlv_18= ',' )? ( (lv_children_19_0= ruleKRendering ) ) )* )? (otherlv_20= 'mapProperties' (otherlv_21= ':' )? ( (lv_persistentEntries_22_0= rulePersistentEntry ) ) ( (otherlv_23= ',' )? ( (lv_persistentEntries_24_0= rulePersistentEntry ) ) )* )? otherlv_25= '}' )?
            int alt153=2;
            int LA153_0 = input.LA(1);

            if ( (LA153_0==12) ) {
                alt153=1;
            }
            switch (alt153) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2630:5: otherlv_3= '{' (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )? (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'childPlacement' (otherlv_13= ':' )? ( (lv_childPlacement_14_0= ruleKPlacement ) ) )? (otherlv_15= 'children' (otherlv_16= ':' )? ( (lv_children_17_0= ruleKRendering ) ) ( (otherlv_18= ',' )? ( (lv_children_19_0= ruleKRendering ) ) )* )? (otherlv_20= 'mapProperties' (otherlv_21= ':' )? ( (lv_persistentEntries_22_0= rulePersistentEntry ) ) ( (otherlv_23= ',' )? ( (lv_persistentEntries_24_0= rulePersistentEntry ) ) )* )? otherlv_25= '}'
                    {
                    otherlv_3=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKText5800); 

                        	newLeafNode(otherlv_3, grammarAccess.getKTextAccess().getLeftCurlyBracketKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2634:1: (otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )* )?
                    int alt140=2;
                    int LA140_0 = input.LA(1);

                    if ( (LA140_0==16) ) {
                        alt140=1;
                    }
                    switch (alt140) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2634:3: otherlv_4= 'styles' (otherlv_5= ':' )? ( (lv_styles_6_0= ruleKStyle ) ) ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )*
                            {
                            otherlv_4=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKText5813); 

                                	newLeafNode(otherlv_4, grammarAccess.getKTextAccess().getStylesKeyword_3_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2638:1: (otherlv_5= ':' )?
                            int alt137=2;
                            int LA137_0 = input.LA(1);

                            if ( (LA137_0==17) ) {
                                alt137=1;
                            }
                            switch (alt137) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2638:3: otherlv_5= ':'
                                    {
                                    otherlv_5=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKText5826); 

                                        	newLeafNode(otherlv_5, grammarAccess.getKTextAccess().getColonKeyword_3_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2642:3: ( (lv_styles_6_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2643:1: (lv_styles_6_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2643:1: (lv_styles_6_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2644:3: lv_styles_6_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKTextAccess().getStylesKStyleParserRuleCall_3_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKText5849);
                            lv_styles_6_0=ruleKStyle();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKTextRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"styles",
                                    		lv_styles_6_0, 
                                    		"KStyle");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2660:2: ( (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) ) )*
                            loop139:
                            do {
                                int alt139=2;
                                int LA139_0 = input.LA(1);

                                if ( (LA139_0==13||(LA139_0>=56 && LA139_0<=57)||LA139_0==65||(LA139_0>=70 && LA139_0<=72)||(LA139_0>=75 && LA139_0<=81)) ) {
                                    alt139=1;
                                }


                                switch (alt139) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2660:3: (otherlv_7= ',' )? ( (lv_styles_8_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2660:3: (otherlv_7= ',' )?
                            	    int alt138=2;
                            	    int LA138_0 = input.LA(1);

                            	    if ( (LA138_0==13) ) {
                            	        alt138=1;
                            	    }
                            	    switch (alt138) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2660:5: otherlv_7= ','
                            	            {
                            	            otherlv_7=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKText5863); 

                            	                	newLeafNode(otherlv_7, grammarAccess.getKTextAccess().getCommaKeyword_3_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2664:3: ( (lv_styles_8_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2665:1: (lv_styles_8_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2665:1: (lv_styles_8_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2666:3: lv_styles_8_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKTextAccess().getStylesKStyleParserRuleCall_3_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKText5886);
                            	    lv_styles_8_0=ruleKStyle();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKTextRule());
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
                            	    break loop139;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2682:6: (otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) ) )?
                    int alt142=2;
                    int LA142_0 = input.LA(1);

                    if ( (LA142_0==18) ) {
                        alt142=1;
                    }
                    switch (alt142) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2682:8: otherlv_9= 'placementData' (otherlv_10= ':' )? ( (lv_placementData_11_0= ruleKPlacementData ) )
                            {
                            otherlv_9=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKText5903); 

                                	newLeafNode(otherlv_9, grammarAccess.getKTextAccess().getPlacementDataKeyword_3_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2686:1: (otherlv_10= ':' )?
                            int alt141=2;
                            int LA141_0 = input.LA(1);

                            if ( (LA141_0==17) ) {
                                alt141=1;
                            }
                            switch (alt141) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2686:3: otherlv_10= ':'
                                    {
                                    otherlv_10=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKText5916); 

                                        	newLeafNode(otherlv_10, grammarAccess.getKTextAccess().getColonKeyword_3_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2690:3: ( (lv_placementData_11_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2691:1: (lv_placementData_11_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2691:1: (lv_placementData_11_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2692:3: lv_placementData_11_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKTextAccess().getPlacementDataKPlacementDataParserRuleCall_3_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKText5939);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2708:4: (otherlv_12= 'childPlacement' (otherlv_13= ':' )? ( (lv_childPlacement_14_0= ruleKPlacement ) ) )?
                    int alt144=2;
                    int LA144_0 = input.LA(1);

                    if ( (LA144_0==20) ) {
                        alt144=1;
                    }
                    switch (alt144) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2708:6: otherlv_12= 'childPlacement' (otherlv_13= ':' )? ( (lv_childPlacement_14_0= ruleKPlacement ) )
                            {
                            otherlv_12=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKText5954); 

                                	newLeafNode(otherlv_12, grammarAccess.getKTextAccess().getChildPlacementKeyword_3_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2712:1: (otherlv_13= ':' )?
                            int alt143=2;
                            int LA143_0 = input.LA(1);

                            if ( (LA143_0==17) ) {
                                alt143=1;
                            }
                            switch (alt143) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2712:3: otherlv_13= ':'
                                    {
                                    otherlv_13=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKText5967); 

                                        	newLeafNode(otherlv_13, grammarAccess.getKTextAccess().getColonKeyword_3_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2716:3: ( (lv_childPlacement_14_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2717:1: (lv_childPlacement_14_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2717:1: (lv_childPlacement_14_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2718:3: lv_childPlacement_14_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKTextAccess().getChildPlacementKPlacementParserRuleCall_3_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKText5990);
                            lv_childPlacement_14_0=ruleKPlacement();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKTextRule());
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2734:4: (otherlv_15= 'children' (otherlv_16= ':' )? ( (lv_children_17_0= ruleKRendering ) ) ( (otherlv_18= ',' )? ( (lv_children_19_0= ruleKRendering ) ) )* )?
                    int alt148=2;
                    int LA148_0 = input.LA(1);

                    if ( (LA148_0==21) ) {
                        alt148=1;
                    }
                    switch (alt148) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2734:6: otherlv_15= 'children' (otherlv_16= ':' )? ( (lv_children_17_0= ruleKRendering ) ) ( (otherlv_18= ',' )? ( (lv_children_19_0= ruleKRendering ) ) )*
                            {
                            otherlv_15=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKText6005); 

                                	newLeafNode(otherlv_15, grammarAccess.getKTextAccess().getChildrenKeyword_3_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2738:1: (otherlv_16= ':' )?
                            int alt145=2;
                            int LA145_0 = input.LA(1);

                            if ( (LA145_0==17) ) {
                                alt145=1;
                            }
                            switch (alt145) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2738:3: otherlv_16= ':'
                                    {
                                    otherlv_16=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKText6018); 

                                        	newLeafNode(otherlv_16, grammarAccess.getKTextAccess().getColonKeyword_3_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2742:3: ( (lv_children_17_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2743:1: (lv_children_17_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2743:1: (lv_children_17_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2744:3: lv_children_17_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKTextAccess().getChildrenKRenderingParserRuleCall_3_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKText6041);
                            lv_children_17_0=ruleKRendering();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getKTextRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"children",
                                    		lv_children_17_0, 
                                    		"KRendering");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2760:2: ( (otherlv_18= ',' )? ( (lv_children_19_0= ruleKRendering ) ) )*
                            loop147:
                            do {
                                int alt147=2;
                                int LA147_0 = input.LA(1);

                                if ( (LA147_0==13||LA147_0==15||LA147_0==19||(LA147_0>=22 && LA147_0<=24)||(LA147_0>=26 && LA147_0<=28)||(LA147_0>=30 && LA147_0<=32)||LA147_0==34||LA147_0==37) ) {
                                    alt147=1;
                                }


                                switch (alt147) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2760:3: (otherlv_18= ',' )? ( (lv_children_19_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2760:3: (otherlv_18= ',' )?
                            	    int alt146=2;
                            	    int LA146_0 = input.LA(1);

                            	    if ( (LA146_0==13) ) {
                            	        alt146=1;
                            	    }
                            	    switch (alt146) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2760:5: otherlv_18= ','
                            	            {
                            	            otherlv_18=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKText6055); 

                            	                	newLeafNode(otherlv_18, grammarAccess.getKTextAccess().getCommaKeyword_3_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2764:3: ( (lv_children_19_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2765:1: (lv_children_19_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2765:1: (lv_children_19_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2766:3: lv_children_19_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKTextAccess().getChildrenKRenderingParserRuleCall_3_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKText6078);
                            	    lv_children_19_0=ruleKRendering();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getKTextRule());
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
                            	    break loop147;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2782:6: (otherlv_20= 'mapProperties' (otherlv_21= ':' )? ( (lv_persistentEntries_22_0= rulePersistentEntry ) ) ( (otherlv_23= ',' )? ( (lv_persistentEntries_24_0= rulePersistentEntry ) ) )* )?
                    int alt152=2;
                    int LA152_0 = input.LA(1);

                    if ( (LA152_0==33) ) {
                        alt152=1;
                    }
                    switch (alt152) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2782:8: otherlv_20= 'mapProperties' (otherlv_21= ':' )? ( (lv_persistentEntries_22_0= rulePersistentEntry ) ) ( (otherlv_23= ',' )? ( (lv_persistentEntries_24_0= rulePersistentEntry ) ) )*
                            {
                            otherlv_20=(Token)match(input,33,FollowSets000.FOLLOW_33_in_ruleKText6095); 

                                	newLeafNode(otherlv_20, grammarAccess.getKTextAccess().getMapPropertiesKeyword_3_5_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2786:1: (otherlv_21= ':' )?
                            int alt149=2;
                            int LA149_0 = input.LA(1);

                            if ( (LA149_0==17) ) {
                                alt149=1;
                            }
                            switch (alt149) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2786:3: otherlv_21= ':'
                                    {
                                    otherlv_21=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKText6108); 

                                        	newLeafNode(otherlv_21, grammarAccess.getKTextAccess().getColonKeyword_3_5_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2790:3: ( (lv_persistentEntries_22_0= rulePersistentEntry ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2791:1: (lv_persistentEntries_22_0= rulePersistentEntry )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2791:1: (lv_persistentEntries_22_0= rulePersistentEntry )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2792:3: lv_persistentEntries_22_0= rulePersistentEntry
                            {
                             
                            	        newCompositeNode(grammarAccess.getKTextAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_5_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_ruleKText6131);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2808:2: ( (otherlv_23= ',' )? ( (lv_persistentEntries_24_0= rulePersistentEntry ) ) )*
                            loop151:
                            do {
                                int alt151=2;
                                int LA151_0 = input.LA(1);

                                if ( ((LA151_0>=RULE_STRING && LA151_0<=RULE_ID)||LA151_0==13) ) {
                                    alt151=1;
                                }


                                switch (alt151) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2808:3: (otherlv_23= ',' )? ( (lv_persistentEntries_24_0= rulePersistentEntry ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2808:3: (otherlv_23= ',' )?
                            	    int alt150=2;
                            	    int LA150_0 = input.LA(1);

                            	    if ( (LA150_0==13) ) {
                            	        alt150=1;
                            	    }
                            	    switch (alt150) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2808:5: otherlv_23= ','
                            	            {
                            	            otherlv_23=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKText6145); 

                            	                	newLeafNode(otherlv_23, grammarAccess.getKTextAccess().getCommaKeyword_3_5_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2812:3: ( (lv_persistentEntries_24_0= rulePersistentEntry ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2813:1: (lv_persistentEntries_24_0= rulePersistentEntry )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2813:1: (lv_persistentEntries_24_0= rulePersistentEntry )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2814:3: lv_persistentEntries_24_0= rulePersistentEntry
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKTextAccess().getPersistentEntriesPersistentEntryParserRuleCall_3_5_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_ruleKText6168);
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
                            	    break loop151;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_25=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKText6184); 

                        	newLeafNode(otherlv_25, grammarAccess.getKTextAccess().getRightCurlyBracketKeyword_3_6());
                        

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2842:1: entryRuleKCustomRendering returns [EObject current=null] : iv_ruleKCustomRendering= ruleKCustomRendering EOF ;
    public final EObject entryRuleKCustomRendering() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKCustomRendering = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2843:2: (iv_ruleKCustomRendering= ruleKCustomRendering EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2844:2: iv_ruleKCustomRendering= ruleKCustomRendering EOF
            {
             newCompositeNode(grammarAccess.getKCustomRenderingRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKCustomRendering_in_entryRuleKCustomRendering6222);
            iv_ruleKCustomRendering=ruleKCustomRendering();

            state._fsp--;

             current =iv_ruleKCustomRendering; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKCustomRendering6232); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2851:1: ruleKCustomRendering returns [EObject current=null] : ( () otherlv_1= 'CustomRendering' (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' (otherlv_13= ':' )? ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'childPlacement' (otherlv_16= ':' )? ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? (otherlv_18= 'children' (otherlv_19= ':' )? ( (lv_children_20_0= ruleKRendering ) ) ( (otherlv_21= ',' )? ( (lv_children_22_0= ruleKRendering ) ) )* )? otherlv_23= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2854:28: ( ( () otherlv_1= 'CustomRendering' (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' (otherlv_13= ':' )? ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'childPlacement' (otherlv_16= ':' )? ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? (otherlv_18= 'children' (otherlv_19= ':' )? ( (lv_children_20_0= ruleKRendering ) ) ( (otherlv_21= ',' )? ( (lv_children_22_0= ruleKRendering ) ) )* )? otherlv_23= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2855:1: ( () otherlv_1= 'CustomRendering' (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' (otherlv_13= ':' )? ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'childPlacement' (otherlv_16= ':' )? ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? (otherlv_18= 'children' (otherlv_19= ':' )? ( (lv_children_20_0= ruleKRendering ) ) ( (otherlv_21= ',' )? ( (lv_children_22_0= ruleKRendering ) ) )* )? otherlv_23= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2855:1: ( () otherlv_1= 'CustomRendering' (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' (otherlv_13= ':' )? ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'childPlacement' (otherlv_16= ':' )? ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? (otherlv_18= 'children' (otherlv_19= ':' )? ( (lv_children_20_0= ruleKRendering ) ) ( (otherlv_21= ',' )? ( (lv_children_22_0= ruleKRendering ) ) )* )? otherlv_23= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2855:2: () otherlv_1= 'CustomRendering' (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' (otherlv_13= ':' )? ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'childPlacement' (otherlv_16= ':' )? ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? (otherlv_18= 'children' (otherlv_19= ':' )? ( (lv_children_20_0= ruleKRendering ) ) ( (otherlv_21= ',' )? ( (lv_children_22_0= ruleKRendering ) ) )* )? otherlv_23= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2855:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2856:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKCustomRenderingAccess().getKCustomRenderingAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,34,FollowSets000.FOLLOW_34_in_ruleKCustomRendering6278); 

                	newLeafNode(otherlv_1, grammarAccess.getKCustomRenderingAccess().getCustomRenderingKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2865:1: (otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' (otherlv_13= ':' )? ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'childPlacement' (otherlv_16= ':' )? ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? (otherlv_18= 'children' (otherlv_19= ':' )? ( (lv_children_20_0= ruleKRendering ) ) ( (otherlv_21= ',' )? ( (lv_children_22_0= ruleKRendering ) ) )* )? otherlv_23= '}' )?
            int alt166=2;
            int LA166_0 = input.LA(1);

            if ( (LA166_0==12) ) {
                alt166=1;
            }
            switch (alt166) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2865:3: otherlv_2= '{' otherlv_3= 'className' ( (lv_className_4_0= ruleEString ) ) otherlv_5= 'bundleName' ( (lv_bundleName_6_0= ruleEString ) ) (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )? (otherlv_12= 'placementData' (otherlv_13= ':' )? ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'childPlacement' (otherlv_16= ':' )? ( (lv_childPlacement_17_0= ruleKPlacement ) ) )? (otherlv_18= 'children' (otherlv_19= ':' )? ( (lv_children_20_0= ruleKRendering ) ) ( (otherlv_21= ',' )? ( (lv_children_22_0= ruleKRendering ) ) )* )? otherlv_23= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKCustomRendering6291); 

                        	newLeafNode(otherlv_2, grammarAccess.getKCustomRenderingAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    otherlv_3=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKCustomRendering6303); 

                        	newLeafNode(otherlv_3, grammarAccess.getKCustomRenderingAccess().getClassNameKeyword_2_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2873:1: ( (lv_className_4_0= ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2874:1: (lv_className_4_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2874:1: (lv_className_4_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2875:3: lv_className_4_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getClassNameEStringParserRuleCall_2_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKCustomRendering6324);
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

                    otherlv_5=(Token)match(input,36,FollowSets000.FOLLOW_36_in_ruleKCustomRendering6336); 

                        	newLeafNode(otherlv_5, grammarAccess.getKCustomRenderingAccess().getBundleNameKeyword_2_3());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2895:1: ( (lv_bundleName_6_0= ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2896:1: (lv_bundleName_6_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2896:1: (lv_bundleName_6_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2897:3: lv_bundleName_6_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getBundleNameEStringParserRuleCall_2_4_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKCustomRendering6357);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2913:2: (otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )* )?
                    int alt157=2;
                    int LA157_0 = input.LA(1);

                    if ( (LA157_0==16) ) {
                        alt157=1;
                    }
                    switch (alt157) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2913:4: otherlv_7= 'styles' (otherlv_8= ':' )? ( (lv_styles_9_0= ruleKStyle ) ) ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )*
                            {
                            otherlv_7=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKCustomRendering6370); 

                                	newLeafNode(otherlv_7, grammarAccess.getKCustomRenderingAccess().getStylesKeyword_2_5_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2917:1: (otherlv_8= ':' )?
                            int alt154=2;
                            int LA154_0 = input.LA(1);

                            if ( (LA154_0==17) ) {
                                alt154=1;
                            }
                            switch (alt154) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2917:3: otherlv_8= ':'
                                    {
                                    otherlv_8=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKCustomRendering6383); 

                                        	newLeafNode(otherlv_8, grammarAccess.getKCustomRenderingAccess().getColonKeyword_2_5_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2921:3: ( (lv_styles_9_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2922:1: (lv_styles_9_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2922:1: (lv_styles_9_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2923:3: lv_styles_9_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getStylesKStyleParserRuleCall_2_5_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKCustomRendering6406);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2939:2: ( (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) ) )*
                            loop156:
                            do {
                                int alt156=2;
                                int LA156_0 = input.LA(1);

                                if ( (LA156_0==13||(LA156_0>=56 && LA156_0<=57)||LA156_0==65||(LA156_0>=70 && LA156_0<=72)||(LA156_0>=75 && LA156_0<=81)) ) {
                                    alt156=1;
                                }


                                switch (alt156) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2939:3: (otherlv_10= ',' )? ( (lv_styles_11_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2939:3: (otherlv_10= ',' )?
                            	    int alt155=2;
                            	    int LA155_0 = input.LA(1);

                            	    if ( (LA155_0==13) ) {
                            	        alt155=1;
                            	    }
                            	    switch (alt155) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2939:5: otherlv_10= ','
                            	            {
                            	            otherlv_10=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKCustomRendering6420); 

                            	                	newLeafNode(otherlv_10, grammarAccess.getKCustomRenderingAccess().getCommaKeyword_2_5_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2943:3: ( (lv_styles_11_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2944:1: (lv_styles_11_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2944:1: (lv_styles_11_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2945:3: lv_styles_11_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getStylesKStyleParserRuleCall_2_5_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKCustomRendering6443);
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
                            	    break loop156;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2961:6: (otherlv_12= 'placementData' (otherlv_13= ':' )? ( (lv_placementData_14_0= ruleKPlacementData ) ) )?
                    int alt159=2;
                    int LA159_0 = input.LA(1);

                    if ( (LA159_0==18) ) {
                        alt159=1;
                    }
                    switch (alt159) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2961:8: otherlv_12= 'placementData' (otherlv_13= ':' )? ( (lv_placementData_14_0= ruleKPlacementData ) )
                            {
                            otherlv_12=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKCustomRendering6460); 

                                	newLeafNode(otherlv_12, grammarAccess.getKCustomRenderingAccess().getPlacementDataKeyword_2_6_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2965:1: (otherlv_13= ':' )?
                            int alt158=2;
                            int LA158_0 = input.LA(1);

                            if ( (LA158_0==17) ) {
                                alt158=1;
                            }
                            switch (alt158) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2965:3: otherlv_13= ':'
                                    {
                                    otherlv_13=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKCustomRendering6473); 

                                        	newLeafNode(otherlv_13, grammarAccess.getKCustomRenderingAccess().getColonKeyword_2_6_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2969:3: ( (lv_placementData_14_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2970:1: (lv_placementData_14_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2970:1: (lv_placementData_14_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2971:3: lv_placementData_14_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getPlacementDataKPlacementDataParserRuleCall_2_6_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKCustomRendering6496);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2987:4: (otherlv_15= 'childPlacement' (otherlv_16= ':' )? ( (lv_childPlacement_17_0= ruleKPlacement ) ) )?
                    int alt161=2;
                    int LA161_0 = input.LA(1);

                    if ( (LA161_0==20) ) {
                        alt161=1;
                    }
                    switch (alt161) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2987:6: otherlv_15= 'childPlacement' (otherlv_16= ':' )? ( (lv_childPlacement_17_0= ruleKPlacement ) )
                            {
                            otherlv_15=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKCustomRendering6511); 

                                	newLeafNode(otherlv_15, grammarAccess.getKCustomRenderingAccess().getChildPlacementKeyword_2_7_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2991:1: (otherlv_16= ':' )?
                            int alt160=2;
                            int LA160_0 = input.LA(1);

                            if ( (LA160_0==17) ) {
                                alt160=1;
                            }
                            switch (alt160) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2991:3: otherlv_16= ':'
                                    {
                                    otherlv_16=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKCustomRendering6524); 

                                        	newLeafNode(otherlv_16, grammarAccess.getKCustomRenderingAccess().getColonKeyword_2_7_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2995:3: ( (lv_childPlacement_17_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2996:1: (lv_childPlacement_17_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2996:1: (lv_childPlacement_17_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:2997:3: lv_childPlacement_17_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getChildPlacementKPlacementParserRuleCall_2_7_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKCustomRendering6547);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3013:4: (otherlv_18= 'children' (otherlv_19= ':' )? ( (lv_children_20_0= ruleKRendering ) ) ( (otherlv_21= ',' )? ( (lv_children_22_0= ruleKRendering ) ) )* )?
                    int alt165=2;
                    int LA165_0 = input.LA(1);

                    if ( (LA165_0==21) ) {
                        alt165=1;
                    }
                    switch (alt165) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3013:6: otherlv_18= 'children' (otherlv_19= ':' )? ( (lv_children_20_0= ruleKRendering ) ) ( (otherlv_21= ',' )? ( (lv_children_22_0= ruleKRendering ) ) )*
                            {
                            otherlv_18=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKCustomRendering6562); 

                                	newLeafNode(otherlv_18, grammarAccess.getKCustomRenderingAccess().getChildrenKeyword_2_8_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3017:1: (otherlv_19= ':' )?
                            int alt162=2;
                            int LA162_0 = input.LA(1);

                            if ( (LA162_0==17) ) {
                                alt162=1;
                            }
                            switch (alt162) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3017:3: otherlv_19= ':'
                                    {
                                    otherlv_19=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKCustomRendering6575); 

                                        	newLeafNode(otherlv_19, grammarAccess.getKCustomRenderingAccess().getColonKeyword_2_8_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3021:3: ( (lv_children_20_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3022:1: (lv_children_20_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3022:1: (lv_children_20_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3023:3: lv_children_20_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getChildrenKRenderingParserRuleCall_2_8_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKCustomRendering6598);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3039:2: ( (otherlv_21= ',' )? ( (lv_children_22_0= ruleKRendering ) ) )*
                            loop164:
                            do {
                                int alt164=2;
                                int LA164_0 = input.LA(1);

                                if ( (LA164_0==13||LA164_0==15||LA164_0==19||(LA164_0>=22 && LA164_0<=24)||(LA164_0>=26 && LA164_0<=28)||(LA164_0>=30 && LA164_0<=32)||LA164_0==34||LA164_0==37) ) {
                                    alt164=1;
                                }


                                switch (alt164) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3039:3: (otherlv_21= ',' )? ( (lv_children_22_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3039:3: (otherlv_21= ',' )?
                            	    int alt163=2;
                            	    int LA163_0 = input.LA(1);

                            	    if ( (LA163_0==13) ) {
                            	        alt163=1;
                            	    }
                            	    switch (alt163) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3039:5: otherlv_21= ','
                            	            {
                            	            otherlv_21=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKCustomRendering6612); 

                            	                	newLeafNode(otherlv_21, grammarAccess.getKCustomRenderingAccess().getCommaKeyword_2_8_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3043:3: ( (lv_children_22_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3044:1: (lv_children_22_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3044:1: (lv_children_22_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3045:3: lv_children_22_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getChildrenKRenderingParserRuleCall_2_8_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKCustomRendering6635);
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
                            	    break loop164;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_23=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKCustomRendering6651); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3073:1: entryRuleKSpline returns [EObject current=null] : iv_ruleKSpline= ruleKSpline EOF ;
    public final EObject entryRuleKSpline() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKSpline = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3074:2: (iv_ruleKSpline= ruleKSpline EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3075:2: iv_ruleKSpline= ruleKSpline EOF
            {
             newCompositeNode(grammarAccess.getKSplineRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKSpline_in_entryRuleKSpline6689);
            iv_ruleKSpline=ruleKSpline();

            state._fsp--;

             current =iv_ruleKSpline; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKSpline6699); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3082:1: ruleKSpline returns [EObject current=null] : ( () otherlv_1= 'Spline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3085:28: ( ( () otherlv_1= 'Spline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3086:1: ( () otherlv_1= 'Spline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3086:1: ( () otherlv_1= 'Spline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3086:2: () otherlv_1= 'Spline' (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3086:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3087:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKSplineAccess().getKSplineAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,37,FollowSets000.FOLLOW_37_in_ruleKSpline6745); 

                	newLeafNode(otherlv_1, grammarAccess.getKSplineAccess().getSplineKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3096:1: (otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}' )?
            int alt179=2;
            int LA179_0 = input.LA(1);

            if ( (LA179_0==12) ) {
                alt179=1;
            }
            switch (alt179) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3096:3: otherlv_2= '{' (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )? (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )? (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )? otherlv_19= '}'
                    {
                    otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKSpline6758); 

                        	newLeafNode(otherlv_2, grammarAccess.getKSplineAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3100:1: (otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )* )?
                    int alt170=2;
                    int LA170_0 = input.LA(1);

                    if ( (LA170_0==16) ) {
                        alt170=1;
                    }
                    switch (alt170) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3100:3: otherlv_3= 'styles' (otherlv_4= ':' )? ( (lv_styles_5_0= ruleKStyle ) ) ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            {
                            otherlv_3=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKSpline6771); 

                                	newLeafNode(otherlv_3, grammarAccess.getKSplineAccess().getStylesKeyword_2_1_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3104:1: (otherlv_4= ':' )?
                            int alt167=2;
                            int LA167_0 = input.LA(1);

                            if ( (LA167_0==17) ) {
                                alt167=1;
                            }
                            switch (alt167) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3104:3: otherlv_4= ':'
                                    {
                                    otherlv_4=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKSpline6784); 

                                        	newLeafNode(otherlv_4, grammarAccess.getKSplineAccess().getColonKeyword_2_1_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3108:3: ( (lv_styles_5_0= ruleKStyle ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3109:1: (lv_styles_5_0= ruleKStyle )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3109:1: (lv_styles_5_0= ruleKStyle )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3110:3: lv_styles_5_0= ruleKStyle
                            {
                             
                            	        newCompositeNode(grammarAccess.getKSplineAccess().getStylesKStyleParserRuleCall_2_1_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKSpline6807);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3126:2: ( (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) ) )*
                            loop169:
                            do {
                                int alt169=2;
                                int LA169_0 = input.LA(1);

                                if ( (LA169_0==13||(LA169_0>=56 && LA169_0<=57)||LA169_0==65||(LA169_0>=70 && LA169_0<=72)||(LA169_0>=75 && LA169_0<=81)) ) {
                                    alt169=1;
                                }


                                switch (alt169) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3126:3: (otherlv_6= ',' )? ( (lv_styles_7_0= ruleKStyle ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3126:3: (otherlv_6= ',' )?
                            	    int alt168=2;
                            	    int LA168_0 = input.LA(1);

                            	    if ( (LA168_0==13) ) {
                            	        alt168=1;
                            	    }
                            	    switch (alt168) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3126:5: otherlv_6= ','
                            	            {
                            	            otherlv_6=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKSpline6821); 

                            	                	newLeafNode(otherlv_6, grammarAccess.getKSplineAccess().getCommaKeyword_2_1_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3130:3: ( (lv_styles_7_0= ruleKStyle ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3131:1: (lv_styles_7_0= ruleKStyle )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3131:1: (lv_styles_7_0= ruleKStyle )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3132:3: lv_styles_7_0= ruleKStyle
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKSplineAccess().getStylesKStyleParserRuleCall_2_1_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKSpline6844);
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
                            	    break loop169;
                                }
                            } while (true);


                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3148:6: (otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
                    int alt172=2;
                    int LA172_0 = input.LA(1);

                    if ( (LA172_0==18) ) {
                        alt172=1;
                    }
                    switch (alt172) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3148:8: otherlv_8= 'placementData' (otherlv_9= ':' )? ( (lv_placementData_10_0= ruleKPlacementData ) )
                            {
                            otherlv_8=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKSpline6861); 

                                	newLeafNode(otherlv_8, grammarAccess.getKSplineAccess().getPlacementDataKeyword_2_2_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3152:1: (otherlv_9= ':' )?
                            int alt171=2;
                            int LA171_0 = input.LA(1);

                            if ( (LA171_0==17) ) {
                                alt171=1;
                            }
                            switch (alt171) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3152:3: otherlv_9= ':'
                                    {
                                    otherlv_9=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKSpline6874); 

                                        	newLeafNode(otherlv_9, grammarAccess.getKSplineAccess().getColonKeyword_2_2_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3156:3: ( (lv_placementData_10_0= ruleKPlacementData ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3157:1: (lv_placementData_10_0= ruleKPlacementData )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3157:1: (lv_placementData_10_0= ruleKPlacementData )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3158:3: lv_placementData_10_0= ruleKPlacementData
                            {
                             
                            	        newCompositeNode(grammarAccess.getKSplineAccess().getPlacementDataKPlacementDataParserRuleCall_2_2_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKSpline6897);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3174:4: (otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) ) )?
                    int alt174=2;
                    int LA174_0 = input.LA(1);

                    if ( (LA174_0==20) ) {
                        alt174=1;
                    }
                    switch (alt174) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3174:6: otherlv_11= 'childPlacement' (otherlv_12= ':' )? ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            {
                            otherlv_11=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKSpline6912); 

                                	newLeafNode(otherlv_11, grammarAccess.getKSplineAccess().getChildPlacementKeyword_2_3_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3178:1: (otherlv_12= ':' )?
                            int alt173=2;
                            int LA173_0 = input.LA(1);

                            if ( (LA173_0==17) ) {
                                alt173=1;
                            }
                            switch (alt173) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3178:3: otherlv_12= ':'
                                    {
                                    otherlv_12=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKSpline6925); 

                                        	newLeafNode(otherlv_12, grammarAccess.getKSplineAccess().getColonKeyword_2_3_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3182:3: ( (lv_childPlacement_13_0= ruleKPlacement ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3183:1: (lv_childPlacement_13_0= ruleKPlacement )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3183:1: (lv_childPlacement_13_0= ruleKPlacement )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3184:3: lv_childPlacement_13_0= ruleKPlacement
                            {
                             
                            	        newCompositeNode(grammarAccess.getKSplineAccess().getChildPlacementKPlacementParserRuleCall_2_3_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKSpline6948);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3200:4: (otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )* )?
                    int alt178=2;
                    int LA178_0 = input.LA(1);

                    if ( (LA178_0==21) ) {
                        alt178=1;
                    }
                    switch (alt178) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3200:6: otherlv_14= 'children' (otherlv_15= ':' )? ( (lv_children_16_0= ruleKRendering ) ) ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            {
                            otherlv_14=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKSpline6963); 

                                	newLeafNode(otherlv_14, grammarAccess.getKSplineAccess().getChildrenKeyword_2_4_0());
                                
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3204:1: (otherlv_15= ':' )?
                            int alt175=2;
                            int LA175_0 = input.LA(1);

                            if ( (LA175_0==17) ) {
                                alt175=1;
                            }
                            switch (alt175) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3204:3: otherlv_15= ':'
                                    {
                                    otherlv_15=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKSpline6976); 

                                        	newLeafNode(otherlv_15, grammarAccess.getKSplineAccess().getColonKeyword_2_4_1());
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3208:3: ( (lv_children_16_0= ruleKRendering ) )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3209:1: (lv_children_16_0= ruleKRendering )
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3209:1: (lv_children_16_0= ruleKRendering )
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3210:3: lv_children_16_0= ruleKRendering
                            {
                             
                            	        newCompositeNode(grammarAccess.getKSplineAccess().getChildrenKRenderingParserRuleCall_2_4_2_0()); 
                            	    
                            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKSpline6999);
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

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3226:2: ( (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) ) )*
                            loop177:
                            do {
                                int alt177=2;
                                int LA177_0 = input.LA(1);

                                if ( (LA177_0==13||LA177_0==15||LA177_0==19||(LA177_0>=22 && LA177_0<=24)||(LA177_0>=26 && LA177_0<=28)||(LA177_0>=30 && LA177_0<=32)||LA177_0==34||LA177_0==37) ) {
                                    alt177=1;
                                }


                                switch (alt177) {
                            	case 1 :
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3226:3: (otherlv_17= ',' )? ( (lv_children_18_0= ruleKRendering ) )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3226:3: (otherlv_17= ',' )?
                            	    int alt176=2;
                            	    int LA176_0 = input.LA(1);

                            	    if ( (LA176_0==13) ) {
                            	        alt176=1;
                            	    }
                            	    switch (alt176) {
                            	        case 1 :
                            	            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3226:5: otherlv_17= ','
                            	            {
                            	            otherlv_17=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKSpline7013); 

                            	                	newLeafNode(otherlv_17, grammarAccess.getKSplineAccess().getCommaKeyword_2_4_3_0());
                            	                

                            	            }
                            	            break;

                            	    }

                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3230:3: ( (lv_children_18_0= ruleKRendering ) )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3231:1: (lv_children_18_0= ruleKRendering )
                            	    {
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3231:1: (lv_children_18_0= ruleKRendering )
                            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3232:3: lv_children_18_0= ruleKRendering
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getKSplineAccess().getChildrenKRenderingParserRuleCall_2_4_3_1_0()); 
                            	    	    
                            	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKSpline7036);
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
                            	    break loop177;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_19=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKSpline7052); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3260:1: entryRuleKDecoratorPlacementData returns [EObject current=null] : iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF ;
    public final EObject entryRuleKDecoratorPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKDecoratorPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3261:2: (iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3262:2: iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKDecoratorPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKDecoratorPlacementData_in_entryRuleKDecoratorPlacementData7090);
            iv_ruleKDecoratorPlacementData=ruleKDecoratorPlacementData();

            state._fsp--;

             current =iv_ruleKDecoratorPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKDecoratorPlacementData7100); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3269:1: ruleKDecoratorPlacementData returns [EObject current=null] : (otherlv_0= 'DecoratorPlacementData' otherlv_1= '{' ( (lv_rotateWithLine_2_0= 'rotateWithLine' ) )? otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? (otherlv_7= 'xOffset' ( (lv_xOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'yOffset' ( (lv_yOffset_10_0= ruleEFloat ) ) )? (otherlv_11= 'width' ( (lv_width_12_0= ruleEFloat ) ) )? (otherlv_13= 'height' ( (lv_height_14_0= ruleEFloat ) ) )? otherlv_15= '}' ) ;
    public final EObject ruleKDecoratorPlacementData() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_rotateWithLine_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        AntlrDatatypeRuleToken lv_absolute_4_0 = null;

        AntlrDatatypeRuleToken lv_relative_6_0 = null;

        AntlrDatatypeRuleToken lv_xOffset_8_0 = null;

        AntlrDatatypeRuleToken lv_yOffset_10_0 = null;

        AntlrDatatypeRuleToken lv_width_12_0 = null;

        AntlrDatatypeRuleToken lv_height_14_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3272:28: ( (otherlv_0= 'DecoratorPlacementData' otherlv_1= '{' ( (lv_rotateWithLine_2_0= 'rotateWithLine' ) )? otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? (otherlv_7= 'xOffset' ( (lv_xOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'yOffset' ( (lv_yOffset_10_0= ruleEFloat ) ) )? (otherlv_11= 'width' ( (lv_width_12_0= ruleEFloat ) ) )? (otherlv_13= 'height' ( (lv_height_14_0= ruleEFloat ) ) )? otherlv_15= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3273:1: (otherlv_0= 'DecoratorPlacementData' otherlv_1= '{' ( (lv_rotateWithLine_2_0= 'rotateWithLine' ) )? otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? (otherlv_7= 'xOffset' ( (lv_xOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'yOffset' ( (lv_yOffset_10_0= ruleEFloat ) ) )? (otherlv_11= 'width' ( (lv_width_12_0= ruleEFloat ) ) )? (otherlv_13= 'height' ( (lv_height_14_0= ruleEFloat ) ) )? otherlv_15= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3273:1: (otherlv_0= 'DecoratorPlacementData' otherlv_1= '{' ( (lv_rotateWithLine_2_0= 'rotateWithLine' ) )? otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? (otherlv_7= 'xOffset' ( (lv_xOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'yOffset' ( (lv_yOffset_10_0= ruleEFloat ) ) )? (otherlv_11= 'width' ( (lv_width_12_0= ruleEFloat ) ) )? (otherlv_13= 'height' ( (lv_height_14_0= ruleEFloat ) ) )? otherlv_15= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3273:3: otherlv_0= 'DecoratorPlacementData' otherlv_1= '{' ( (lv_rotateWithLine_2_0= 'rotateWithLine' ) )? otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? (otherlv_7= 'xOffset' ( (lv_xOffset_8_0= ruleEFloat ) ) )? (otherlv_9= 'yOffset' ( (lv_yOffset_10_0= ruleEFloat ) ) )? (otherlv_11= 'width' ( (lv_width_12_0= ruleEFloat ) ) )? (otherlv_13= 'height' ( (lv_height_14_0= ruleEFloat ) ) )? otherlv_15= '}'
            {
            otherlv_0=(Token)match(input,38,FollowSets000.FOLLOW_38_in_ruleKDecoratorPlacementData7137); 

                	newLeafNode(otherlv_0, grammarAccess.getKDecoratorPlacementDataAccess().getDecoratorPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKDecoratorPlacementData7149); 

                	newLeafNode(otherlv_1, grammarAccess.getKDecoratorPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3281:1: ( (lv_rotateWithLine_2_0= 'rotateWithLine' ) )?
            int alt180=2;
            int LA180_0 = input.LA(1);

            if ( (LA180_0==39) ) {
                alt180=1;
            }
            switch (alt180) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3282:1: (lv_rotateWithLine_2_0= 'rotateWithLine' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3282:1: (lv_rotateWithLine_2_0= 'rotateWithLine' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3283:3: lv_rotateWithLine_2_0= 'rotateWithLine'
                    {
                    lv_rotateWithLine_2_0=(Token)match(input,39,FollowSets000.FOLLOW_39_in_ruleKDecoratorPlacementData7167); 

                            newLeafNode(lv_rotateWithLine_2_0, grammarAccess.getKDecoratorPlacementDataAccess().getRotateWithLineRotateWithLineKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKDecoratorPlacementDataRule());
                    	        }
                           		setWithLastConsumed(current, "rotateWithLine", true, "rotateWithLine");
                    	    

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,40,FollowSets000.FOLLOW_40_in_ruleKDecoratorPlacementData7193); 

                	newLeafNode(otherlv_3, grammarAccess.getKDecoratorPlacementDataAccess().getAbsoluteKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3300:1: ( (lv_absolute_4_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3301:1: (lv_absolute_4_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3301:1: (lv_absolute_4_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3302:3: lv_absolute_4_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getAbsoluteEFloatParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7214);
            lv_absolute_4_0=ruleEFloat();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKDecoratorPlacementDataRule());
            	        }
                   		set(
                   			current, 
                   			"absolute",
                    		lv_absolute_4_0, 
                    		"EFloat");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3318:2: (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )?
            int alt181=2;
            int LA181_0 = input.LA(1);

            if ( (LA181_0==41) ) {
                alt181=1;
            }
            switch (alt181) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3318:4: otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,41,FollowSets000.FOLLOW_41_in_ruleKDecoratorPlacementData7227); 

                        	newLeafNode(otherlv_5, grammarAccess.getKDecoratorPlacementDataAccess().getRelativeKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3322:1: ( (lv_relative_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3323:1: (lv_relative_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3323:1: (lv_relative_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3324:3: lv_relative_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getRelativeEFloatParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7248);
                    lv_relative_6_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKDecoratorPlacementDataRule());
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3340:4: (otherlv_7= 'xOffset' ( (lv_xOffset_8_0= ruleEFloat ) ) )?
            int alt182=2;
            int LA182_0 = input.LA(1);

            if ( (LA182_0==42) ) {
                alt182=1;
            }
            switch (alt182) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3340:6: otherlv_7= 'xOffset' ( (lv_xOffset_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,42,FollowSets000.FOLLOW_42_in_ruleKDecoratorPlacementData7263); 

                        	newLeafNode(otherlv_7, grammarAccess.getKDecoratorPlacementDataAccess().getXOffsetKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3344:1: ( (lv_xOffset_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3345:1: (lv_xOffset_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3345:1: (lv_xOffset_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3346:3: lv_xOffset_8_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getXOffsetEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7284);
                    lv_xOffset_8_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKDecoratorPlacementDataRule());
                    	        }
                           		set(
                           			current, 
                           			"xOffset",
                            		lv_xOffset_8_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3362:4: (otherlv_9= 'yOffset' ( (lv_yOffset_10_0= ruleEFloat ) ) )?
            int alt183=2;
            int LA183_0 = input.LA(1);

            if ( (LA183_0==43) ) {
                alt183=1;
            }
            switch (alt183) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3362:6: otherlv_9= 'yOffset' ( (lv_yOffset_10_0= ruleEFloat ) )
                    {
                    otherlv_9=(Token)match(input,43,FollowSets000.FOLLOW_43_in_ruleKDecoratorPlacementData7299); 

                        	newLeafNode(otherlv_9, grammarAccess.getKDecoratorPlacementDataAccess().getYOffsetKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3366:1: ( (lv_yOffset_10_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3367:1: (lv_yOffset_10_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3367:1: (lv_yOffset_10_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3368:3: lv_yOffset_10_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getYOffsetEFloatParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7320);
                    lv_yOffset_10_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKDecoratorPlacementDataRule());
                    	        }
                           		set(
                           			current, 
                           			"yOffset",
                            		lv_yOffset_10_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3384:4: (otherlv_11= 'width' ( (lv_width_12_0= ruleEFloat ) ) )?
            int alt184=2;
            int LA184_0 = input.LA(1);

            if ( (LA184_0==44) ) {
                alt184=1;
            }
            switch (alt184) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3384:6: otherlv_11= 'width' ( (lv_width_12_0= ruleEFloat ) )
                    {
                    otherlv_11=(Token)match(input,44,FollowSets000.FOLLOW_44_in_ruleKDecoratorPlacementData7335); 

                        	newLeafNode(otherlv_11, grammarAccess.getKDecoratorPlacementDataAccess().getWidthKeyword_8_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3388:1: ( (lv_width_12_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3389:1: (lv_width_12_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3389:1: (lv_width_12_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3390:3: lv_width_12_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getWidthEFloatParserRuleCall_8_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7356);
                    lv_width_12_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKDecoratorPlacementDataRule());
                    	        }
                           		set(
                           			current, 
                           			"width",
                            		lv_width_12_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3406:4: (otherlv_13= 'height' ( (lv_height_14_0= ruleEFloat ) ) )?
            int alt185=2;
            int LA185_0 = input.LA(1);

            if ( (LA185_0==45) ) {
                alt185=1;
            }
            switch (alt185) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3406:6: otherlv_13= 'height' ( (lv_height_14_0= ruleEFloat ) )
                    {
                    otherlv_13=(Token)match(input,45,FollowSets000.FOLLOW_45_in_ruleKDecoratorPlacementData7371); 

                        	newLeafNode(otherlv_13, grammarAccess.getKDecoratorPlacementDataAccess().getHeightKeyword_9_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3410:1: ( (lv_height_14_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3411:1: (lv_height_14_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3411:1: (lv_height_14_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3412:3: lv_height_14_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getHeightEFloatParserRuleCall_9_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7392);
                    lv_height_14_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKDecoratorPlacementDataRule());
                    	        }
                           		set(
                           			current, 
                           			"height",
                            		lv_height_14_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_15=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKDecoratorPlacementData7406); 

                	newLeafNode(otherlv_15, grammarAccess.getKDecoratorPlacementDataAccess().getRightCurlyBracketKeyword_10());
                

            }


            }

             leaveRule(); 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3440:1: entryRuleKGridPlacementData returns [EObject current=null] : iv_ruleKGridPlacementData= ruleKGridPlacementData EOF ;
    public final EObject entryRuleKGridPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKGridPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3441:2: (iv_ruleKGridPlacementData= ruleKGridPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3442:2: iv_ruleKGridPlacementData= ruleKGridPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKGridPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKGridPlacementData_in_entryRuleKGridPlacementData7442);
            iv_ruleKGridPlacementData=ruleKGridPlacementData();

            state._fsp--;

             current =iv_ruleKGridPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKGridPlacementData7452); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3449:1: ruleKGridPlacementData returns [EObject current=null] : ( () otherlv_1= 'GridPlacementData' otherlv_2= '{' (otherlv_3= 'minCellWidth' ( (lv_minCellWidth_4_0= ruleEFloat ) ) )? (otherlv_5= 'minCellHeight' ( (lv_minCellHeight_6_0= ruleEFloat ) ) )? (otherlv_7= 'maxCellWidth' ( (lv_maxCellWidth_8_0= ruleEFloat ) ) )? (otherlv_9= 'maxCellHeight' ( (lv_maxCellHeight_10_0= ruleEFloat ) ) )? (otherlv_11= 'topLeft' ( (lv_topLeft_12_0= ruleKPosition ) ) )? (otherlv_13= 'bottomRight' ( (lv_bottomRight_14_0= ruleKPosition ) ) )? otherlv_15= '}' ) ;
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
        AntlrDatatypeRuleToken lv_minCellWidth_4_0 = null;

        AntlrDatatypeRuleToken lv_minCellHeight_6_0 = null;

        AntlrDatatypeRuleToken lv_maxCellWidth_8_0 = null;

        AntlrDatatypeRuleToken lv_maxCellHeight_10_0 = null;

        EObject lv_topLeft_12_0 = null;

        EObject lv_bottomRight_14_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3452:28: ( ( () otherlv_1= 'GridPlacementData' otherlv_2= '{' (otherlv_3= 'minCellWidth' ( (lv_minCellWidth_4_0= ruleEFloat ) ) )? (otherlv_5= 'minCellHeight' ( (lv_minCellHeight_6_0= ruleEFloat ) ) )? (otherlv_7= 'maxCellWidth' ( (lv_maxCellWidth_8_0= ruleEFloat ) ) )? (otherlv_9= 'maxCellHeight' ( (lv_maxCellHeight_10_0= ruleEFloat ) ) )? (otherlv_11= 'topLeft' ( (lv_topLeft_12_0= ruleKPosition ) ) )? (otherlv_13= 'bottomRight' ( (lv_bottomRight_14_0= ruleKPosition ) ) )? otherlv_15= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3453:1: ( () otherlv_1= 'GridPlacementData' otherlv_2= '{' (otherlv_3= 'minCellWidth' ( (lv_minCellWidth_4_0= ruleEFloat ) ) )? (otherlv_5= 'minCellHeight' ( (lv_minCellHeight_6_0= ruleEFloat ) ) )? (otherlv_7= 'maxCellWidth' ( (lv_maxCellWidth_8_0= ruleEFloat ) ) )? (otherlv_9= 'maxCellHeight' ( (lv_maxCellHeight_10_0= ruleEFloat ) ) )? (otherlv_11= 'topLeft' ( (lv_topLeft_12_0= ruleKPosition ) ) )? (otherlv_13= 'bottomRight' ( (lv_bottomRight_14_0= ruleKPosition ) ) )? otherlv_15= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3453:1: ( () otherlv_1= 'GridPlacementData' otherlv_2= '{' (otherlv_3= 'minCellWidth' ( (lv_minCellWidth_4_0= ruleEFloat ) ) )? (otherlv_5= 'minCellHeight' ( (lv_minCellHeight_6_0= ruleEFloat ) ) )? (otherlv_7= 'maxCellWidth' ( (lv_maxCellWidth_8_0= ruleEFloat ) ) )? (otherlv_9= 'maxCellHeight' ( (lv_maxCellHeight_10_0= ruleEFloat ) ) )? (otherlv_11= 'topLeft' ( (lv_topLeft_12_0= ruleKPosition ) ) )? (otherlv_13= 'bottomRight' ( (lv_bottomRight_14_0= ruleKPosition ) ) )? otherlv_15= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3453:2: () otherlv_1= 'GridPlacementData' otherlv_2= '{' (otherlv_3= 'minCellWidth' ( (lv_minCellWidth_4_0= ruleEFloat ) ) )? (otherlv_5= 'minCellHeight' ( (lv_minCellHeight_6_0= ruleEFloat ) ) )? (otherlv_7= 'maxCellWidth' ( (lv_maxCellWidth_8_0= ruleEFloat ) ) )? (otherlv_9= 'maxCellHeight' ( (lv_maxCellHeight_10_0= ruleEFloat ) ) )? (otherlv_11= 'topLeft' ( (lv_topLeft_12_0= ruleKPosition ) ) )? (otherlv_13= 'bottomRight' ( (lv_bottomRight_14_0= ruleKPosition ) ) )? otherlv_15= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3453:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3454:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKGridPlacementDataAccess().getKGridPlacementDataAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,46,FollowSets000.FOLLOW_46_in_ruleKGridPlacementData7498); 

                	newLeafNode(otherlv_1, grammarAccess.getKGridPlacementDataAccess().getGridPlacementDataKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKGridPlacementData7510); 

                	newLeafNode(otherlv_2, grammarAccess.getKGridPlacementDataAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3467:1: (otherlv_3= 'minCellWidth' ( (lv_minCellWidth_4_0= ruleEFloat ) ) )?
            int alt186=2;
            int LA186_0 = input.LA(1);

            if ( (LA186_0==47) ) {
                alt186=1;
            }
            switch (alt186) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3467:3: otherlv_3= 'minCellWidth' ( (lv_minCellWidth_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,47,FollowSets000.FOLLOW_47_in_ruleKGridPlacementData7523); 

                        	newLeafNode(otherlv_3, grammarAccess.getKGridPlacementDataAccess().getMinCellWidthKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3471:1: ( (lv_minCellWidth_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3472:1: (lv_minCellWidth_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3472:1: (lv_minCellWidth_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3473:3: lv_minCellWidth_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getMinCellWidthEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData7544);
                    lv_minCellWidth_4_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKGridPlacementDataRule());
                    	        }
                           		set(
                           			current, 
                           			"minCellWidth",
                            		lv_minCellWidth_4_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3489:4: (otherlv_5= 'minCellHeight' ( (lv_minCellHeight_6_0= ruleEFloat ) ) )?
            int alt187=2;
            int LA187_0 = input.LA(1);

            if ( (LA187_0==48) ) {
                alt187=1;
            }
            switch (alt187) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3489:6: otherlv_5= 'minCellHeight' ( (lv_minCellHeight_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,48,FollowSets000.FOLLOW_48_in_ruleKGridPlacementData7559); 

                        	newLeafNode(otherlv_5, grammarAccess.getKGridPlacementDataAccess().getMinCellHeightKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3493:1: ( (lv_minCellHeight_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3494:1: (lv_minCellHeight_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3494:1: (lv_minCellHeight_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3495:3: lv_minCellHeight_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getMinCellHeightEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData7580);
                    lv_minCellHeight_6_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKGridPlacementDataRule());
                    	        }
                           		set(
                           			current, 
                           			"minCellHeight",
                            		lv_minCellHeight_6_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3511:4: (otherlv_7= 'maxCellWidth' ( (lv_maxCellWidth_8_0= ruleEFloat ) ) )?
            int alt188=2;
            int LA188_0 = input.LA(1);

            if ( (LA188_0==49) ) {
                alt188=1;
            }
            switch (alt188) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3511:6: otherlv_7= 'maxCellWidth' ( (lv_maxCellWidth_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,49,FollowSets000.FOLLOW_49_in_ruleKGridPlacementData7595); 

                        	newLeafNode(otherlv_7, grammarAccess.getKGridPlacementDataAccess().getMaxCellWidthKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3515:1: ( (lv_maxCellWidth_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3516:1: (lv_maxCellWidth_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3516:1: (lv_maxCellWidth_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3517:3: lv_maxCellWidth_8_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getMaxCellWidthEFloatParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData7616);
                    lv_maxCellWidth_8_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKGridPlacementDataRule());
                    	        }
                           		set(
                           			current, 
                           			"maxCellWidth",
                            		lv_maxCellWidth_8_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3533:4: (otherlv_9= 'maxCellHeight' ( (lv_maxCellHeight_10_0= ruleEFloat ) ) )?
            int alt189=2;
            int LA189_0 = input.LA(1);

            if ( (LA189_0==50) ) {
                alt189=1;
            }
            switch (alt189) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3533:6: otherlv_9= 'maxCellHeight' ( (lv_maxCellHeight_10_0= ruleEFloat ) )
                    {
                    otherlv_9=(Token)match(input,50,FollowSets000.FOLLOW_50_in_ruleKGridPlacementData7631); 

                        	newLeafNode(otherlv_9, grammarAccess.getKGridPlacementDataAccess().getMaxCellHeightKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3537:1: ( (lv_maxCellHeight_10_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3538:1: (lv_maxCellHeight_10_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3538:1: (lv_maxCellHeight_10_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3539:3: lv_maxCellHeight_10_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getMaxCellHeightEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData7652);
                    lv_maxCellHeight_10_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKGridPlacementDataRule());
                    	        }
                           		set(
                           			current, 
                           			"maxCellHeight",
                            		lv_maxCellHeight_10_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3555:4: (otherlv_11= 'topLeft' ( (lv_topLeft_12_0= ruleKPosition ) ) )?
            int alt190=2;
            int LA190_0 = input.LA(1);

            if ( (LA190_0==51) ) {
                alt190=1;
            }
            switch (alt190) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3555:6: otherlv_11= 'topLeft' ( (lv_topLeft_12_0= ruleKPosition ) )
                    {
                    otherlv_11=(Token)match(input,51,FollowSets000.FOLLOW_51_in_ruleKGridPlacementData7667); 

                        	newLeafNode(otherlv_11, grammarAccess.getKGridPlacementDataAccess().getTopLeftKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3559:1: ( (lv_topLeft_12_0= ruleKPosition ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3560:1: (lv_topLeft_12_0= ruleKPosition )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3560:1: (lv_topLeft_12_0= ruleKPosition )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3561:3: lv_topLeft_12_0= ruleKPosition
                    {
                     
                    	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getTopLeftKPositionParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKGridPlacementData7688);
                    lv_topLeft_12_0=ruleKPosition();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKGridPlacementDataRule());
                    	        }
                           		set(
                           			current, 
                           			"topLeft",
                            		lv_topLeft_12_0, 
                            		"KPosition");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3577:4: (otherlv_13= 'bottomRight' ( (lv_bottomRight_14_0= ruleKPosition ) ) )?
            int alt191=2;
            int LA191_0 = input.LA(1);

            if ( (LA191_0==52) ) {
                alt191=1;
            }
            switch (alt191) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3577:6: otherlv_13= 'bottomRight' ( (lv_bottomRight_14_0= ruleKPosition ) )
                    {
                    otherlv_13=(Token)match(input,52,FollowSets000.FOLLOW_52_in_ruleKGridPlacementData7703); 

                        	newLeafNode(otherlv_13, grammarAccess.getKGridPlacementDataAccess().getBottomRightKeyword_8_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3581:1: ( (lv_bottomRight_14_0= ruleKPosition ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3582:1: (lv_bottomRight_14_0= ruleKPosition )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3582:1: (lv_bottomRight_14_0= ruleKPosition )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3583:3: lv_bottomRight_14_0= ruleKPosition
                    {
                     
                    	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getBottomRightKPositionParserRuleCall_8_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKGridPlacementData7724);
                    lv_bottomRight_14_0=ruleKPosition();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKGridPlacementDataRule());
                    	        }
                           		set(
                           			current, 
                           			"bottomRight",
                            		lv_bottomRight_14_0, 
                            		"KPosition");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_15=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKGridPlacementData7738); 

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


    // $ANTLR start "entryRuleKAreaPlacementData"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3611:1: entryRuleKAreaPlacementData returns [EObject current=null] : iv_ruleKAreaPlacementData= ruleKAreaPlacementData EOF ;
    public final EObject entryRuleKAreaPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKAreaPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3612:2: (iv_ruleKAreaPlacementData= ruleKAreaPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3613:2: iv_ruleKAreaPlacementData= ruleKAreaPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKAreaPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKAreaPlacementData_in_entryRuleKAreaPlacementData7774);
            iv_ruleKAreaPlacementData=ruleKAreaPlacementData();

            state._fsp--;

             current =iv_ruleKAreaPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKAreaPlacementData7784); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3620:1: ruleKAreaPlacementData returns [EObject current=null] : (otherlv_0= 'AreaPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'bottomRight' ( (lv_bottomRight_6_0= ruleKPosition ) ) otherlv_7= '}' ) ;
    public final EObject ruleKAreaPlacementData() throws RecognitionException {
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3623:28: ( (otherlv_0= 'AreaPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'bottomRight' ( (lv_bottomRight_6_0= ruleKPosition ) ) otherlv_7= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3624:1: (otherlv_0= 'AreaPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'bottomRight' ( (lv_bottomRight_6_0= ruleKPosition ) ) otherlv_7= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3624:1: (otherlv_0= 'AreaPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'bottomRight' ( (lv_bottomRight_6_0= ruleKPosition ) ) otherlv_7= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3624:3: otherlv_0= 'AreaPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'bottomRight' ( (lv_bottomRight_6_0= ruleKPosition ) ) otherlv_7= '}'
            {
            otherlv_0=(Token)match(input,53,FollowSets000.FOLLOW_53_in_ruleKAreaPlacementData7821); 

                	newLeafNode(otherlv_0, grammarAccess.getKAreaPlacementDataAccess().getAreaPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKAreaPlacementData7833); 

                	newLeafNode(otherlv_1, grammarAccess.getKAreaPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,51,FollowSets000.FOLLOW_51_in_ruleKAreaPlacementData7845); 

                	newLeafNode(otherlv_2, grammarAccess.getKAreaPlacementDataAccess().getTopLeftKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3636:1: ( (lv_topLeft_3_0= ruleKPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3637:1: (lv_topLeft_3_0= ruleKPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3637:1: (lv_topLeft_3_0= ruleKPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3638:3: lv_topLeft_3_0= ruleKPosition
            {
             
            	        newCompositeNode(grammarAccess.getKAreaPlacementDataAccess().getTopLeftKPositionParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKAreaPlacementData7866);
            lv_topLeft_3_0=ruleKPosition();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKAreaPlacementDataRule());
            	        }
                   		set(
                   			current, 
                   			"topLeft",
                    		lv_topLeft_3_0, 
                    		"KPosition");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3654:2: (otherlv_4= ',' )?
            int alt192=2;
            int LA192_0 = input.LA(1);

            if ( (LA192_0==13) ) {
                alt192=1;
            }
            switch (alt192) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3654:4: otherlv_4= ','
                    {
                    otherlv_4=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKAreaPlacementData7879); 

                        	newLeafNode(otherlv_4, grammarAccess.getKAreaPlacementDataAccess().getCommaKeyword_4());
                        

                    }
                    break;

            }

            otherlv_5=(Token)match(input,52,FollowSets000.FOLLOW_52_in_ruleKAreaPlacementData7893); 

                	newLeafNode(otherlv_5, grammarAccess.getKAreaPlacementDataAccess().getBottomRightKeyword_5());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3662:1: ( (lv_bottomRight_6_0= ruleKPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3663:1: (lv_bottomRight_6_0= ruleKPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3663:1: (lv_bottomRight_6_0= ruleKPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3664:3: lv_bottomRight_6_0= ruleKPosition
            {
             
            	        newCompositeNode(grammarAccess.getKAreaPlacementDataAccess().getBottomRightKPositionParserRuleCall_6_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKAreaPlacementData7914);
            lv_bottomRight_6_0=ruleKPosition();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKAreaPlacementDataRule());
            	        }
                   		set(
                   			current, 
                   			"bottomRight",
                    		lv_bottomRight_6_0, 
                    		"KPosition");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_7=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKAreaPlacementData7926); 

                	newLeafNode(otherlv_7, grammarAccess.getKAreaPlacementDataAccess().getRightCurlyBracketKeyword_7());
                

            }


            }

             leaveRule(); 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3692:1: entryRuleKPointPlacementData returns [EObject current=null] : iv_ruleKPointPlacementData= ruleKPointPlacementData EOF ;
    public final EObject entryRuleKPointPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPointPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3693:2: (iv_ruleKPointPlacementData= ruleKPointPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3694:2: iv_ruleKPointPlacementData= ruleKPointPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKPointPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPointPlacementData_in_entryRuleKPointPlacementData7962);
            iv_ruleKPointPlacementData=ruleKPointPlacementData();

            state._fsp--;

             current =iv_ruleKPointPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPointPlacementData7972); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3701:1: ruleKPointPlacementData returns [EObject current=null] : (otherlv_0= 'PointPlacementData' otherlv_1= '{' otherlv_2= 'referencePoint' ( (lv_referencePoint_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'verticalAlignment' ( (lv_verticalAlignment_6_0= ruleVerticalAlignment ) ) (otherlv_7= ',' )? otherlv_8= 'horizontalAlignment' ( (lv_horizontalAlignment_9_0= ruleHorizontalAlignment ) ) (otherlv_10= ',' )? otherlv_11= '}' ) ;
    public final EObject ruleKPointPlacementData() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        EObject lv_referencePoint_3_0 = null;

        Enumerator lv_verticalAlignment_6_0 = null;

        Enumerator lv_horizontalAlignment_9_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3704:28: ( (otherlv_0= 'PointPlacementData' otherlv_1= '{' otherlv_2= 'referencePoint' ( (lv_referencePoint_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'verticalAlignment' ( (lv_verticalAlignment_6_0= ruleVerticalAlignment ) ) (otherlv_7= ',' )? otherlv_8= 'horizontalAlignment' ( (lv_horizontalAlignment_9_0= ruleHorizontalAlignment ) ) (otherlv_10= ',' )? otherlv_11= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3705:1: (otherlv_0= 'PointPlacementData' otherlv_1= '{' otherlv_2= 'referencePoint' ( (lv_referencePoint_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'verticalAlignment' ( (lv_verticalAlignment_6_0= ruleVerticalAlignment ) ) (otherlv_7= ',' )? otherlv_8= 'horizontalAlignment' ( (lv_horizontalAlignment_9_0= ruleHorizontalAlignment ) ) (otherlv_10= ',' )? otherlv_11= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3705:1: (otherlv_0= 'PointPlacementData' otherlv_1= '{' otherlv_2= 'referencePoint' ( (lv_referencePoint_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'verticalAlignment' ( (lv_verticalAlignment_6_0= ruleVerticalAlignment ) ) (otherlv_7= ',' )? otherlv_8= 'horizontalAlignment' ( (lv_horizontalAlignment_9_0= ruleHorizontalAlignment ) ) (otherlv_10= ',' )? otherlv_11= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3705:3: otherlv_0= 'PointPlacementData' otherlv_1= '{' otherlv_2= 'referencePoint' ( (lv_referencePoint_3_0= ruleKPosition ) ) (otherlv_4= ',' )? otherlv_5= 'verticalAlignment' ( (lv_verticalAlignment_6_0= ruleVerticalAlignment ) ) (otherlv_7= ',' )? otherlv_8= 'horizontalAlignment' ( (lv_horizontalAlignment_9_0= ruleHorizontalAlignment ) ) (otherlv_10= ',' )? otherlv_11= '}'
            {
            otherlv_0=(Token)match(input,54,FollowSets000.FOLLOW_54_in_ruleKPointPlacementData8009); 

                	newLeafNode(otherlv_0, grammarAccess.getKPointPlacementDataAccess().getPointPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPointPlacementData8021); 

                	newLeafNode(otherlv_1, grammarAccess.getKPointPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,55,FollowSets000.FOLLOW_55_in_ruleKPointPlacementData8033); 

                	newLeafNode(otherlv_2, grammarAccess.getKPointPlacementDataAccess().getReferencePointKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3717:1: ( (lv_referencePoint_3_0= ruleKPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3718:1: (lv_referencePoint_3_0= ruleKPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3718:1: (lv_referencePoint_3_0= ruleKPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3719:3: lv_referencePoint_3_0= ruleKPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPointPlacementDataAccess().getReferencePointKPositionParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKPointPlacementData8054);
            lv_referencePoint_3_0=ruleKPosition();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKPointPlacementDataRule());
            	        }
                   		set(
                   			current, 
                   			"referencePoint",
                    		lv_referencePoint_3_0, 
                    		"KPosition");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3735:2: (otherlv_4= ',' )?
            int alt193=2;
            int LA193_0 = input.LA(1);

            if ( (LA193_0==13) ) {
                alt193=1;
            }
            switch (alt193) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3735:4: otherlv_4= ','
                    {
                    otherlv_4=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPointPlacementData8067); 

                        	newLeafNode(otherlv_4, grammarAccess.getKPointPlacementDataAccess().getCommaKeyword_4());
                        

                    }
                    break;

            }

            otherlv_5=(Token)match(input,56,FollowSets000.FOLLOW_56_in_ruleKPointPlacementData8081); 

                	newLeafNode(otherlv_5, grammarAccess.getKPointPlacementDataAccess().getVerticalAlignmentKeyword_5());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3743:1: ( (lv_verticalAlignment_6_0= ruleVerticalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3744:1: (lv_verticalAlignment_6_0= ruleVerticalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3744:1: (lv_verticalAlignment_6_0= ruleVerticalAlignment )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3745:3: lv_verticalAlignment_6_0= ruleVerticalAlignment
            {
             
            	        newCompositeNode(grammarAccess.getKPointPlacementDataAccess().getVerticalAlignmentVerticalAlignmentEnumRuleCall_6_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleVerticalAlignment_in_ruleKPointPlacementData8102);
            lv_verticalAlignment_6_0=ruleVerticalAlignment();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKPointPlacementDataRule());
            	        }
                   		set(
                   			current, 
                   			"verticalAlignment",
                    		lv_verticalAlignment_6_0, 
                    		"VerticalAlignment");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3761:2: (otherlv_7= ',' )?
            int alt194=2;
            int LA194_0 = input.LA(1);

            if ( (LA194_0==13) ) {
                alt194=1;
            }
            switch (alt194) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3761:4: otherlv_7= ','
                    {
                    otherlv_7=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPointPlacementData8115); 

                        	newLeafNode(otherlv_7, grammarAccess.getKPointPlacementDataAccess().getCommaKeyword_7());
                        

                    }
                    break;

            }

            otherlv_8=(Token)match(input,57,FollowSets000.FOLLOW_57_in_ruleKPointPlacementData8129); 

                	newLeafNode(otherlv_8, grammarAccess.getKPointPlacementDataAccess().getHorizontalAlignmentKeyword_8());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3769:1: ( (lv_horizontalAlignment_9_0= ruleHorizontalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3770:1: (lv_horizontalAlignment_9_0= ruleHorizontalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3770:1: (lv_horizontalAlignment_9_0= ruleHorizontalAlignment )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3771:3: lv_horizontalAlignment_9_0= ruleHorizontalAlignment
            {
             
            	        newCompositeNode(grammarAccess.getKPointPlacementDataAccess().getHorizontalAlignmentHorizontalAlignmentEnumRuleCall_9_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleHorizontalAlignment_in_ruleKPointPlacementData8150);
            lv_horizontalAlignment_9_0=ruleHorizontalAlignment();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKPointPlacementDataRule());
            	        }
                   		set(
                   			current, 
                   			"horizontalAlignment",
                    		lv_horizontalAlignment_9_0, 
                    		"HorizontalAlignment");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3787:2: (otherlv_10= ',' )?
            int alt195=2;
            int LA195_0 = input.LA(1);

            if ( (LA195_0==13) ) {
                alt195=1;
            }
            switch (alt195) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3787:4: otherlv_10= ','
                    {
                    otherlv_10=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKPointPlacementData8163); 

                        	newLeafNode(otherlv_10, grammarAccess.getKPointPlacementDataAccess().getCommaKeyword_10());
                        

                    }
                    break;

            }

            otherlv_11=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPointPlacementData8177); 

                	newLeafNode(otherlv_11, grammarAccess.getKPointPlacementDataAccess().getRightCurlyBracketKeyword_11());
                

            }


            }

             leaveRule(); 
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


    // $ANTLR start "entryRuleKPosition"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3803:1: entryRuleKPosition returns [EObject current=null] : iv_ruleKPosition= ruleKPosition EOF ;
    public final EObject entryRuleKPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3804:2: (iv_ruleKPosition= ruleKPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3805:2: iv_ruleKPosition= ruleKPosition EOF
            {
             newCompositeNode(grammarAccess.getKPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_entryRuleKPosition8213);
            iv_ruleKPosition=ruleKPosition();

            state._fsp--;

             current =iv_ruleKPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPosition8223); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3812:1: ruleKPosition returns [EObject current=null] : ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= '/' ( (lv_y_2_0= ruleKYPosition ) ) ) ;
    public final EObject ruleKPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_x_0_0 = null;

        EObject lv_y_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3815:28: ( ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= '/' ( (lv_y_2_0= ruleKYPosition ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3816:1: ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= '/' ( (lv_y_2_0= ruleKYPosition ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3816:1: ( ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= '/' ( (lv_y_2_0= ruleKYPosition ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3816:2: ( (lv_x_0_0= ruleKXPosition ) ) otherlv_1= '/' ( (lv_y_2_0= ruleKYPosition ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3816:2: ( (lv_x_0_0= ruleKXPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3817:1: (lv_x_0_0= ruleKXPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3817:1: (lv_x_0_0= ruleKXPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3818:3: lv_x_0_0= ruleKXPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPositionAccess().getXKXPositionParserRuleCall_0_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKXPosition_in_ruleKPosition8269);
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

            otherlv_1=(Token)match(input,58,FollowSets000.FOLLOW_58_in_ruleKPosition8281); 

                	newLeafNode(otherlv_1, grammarAccess.getKPositionAccess().getSolidusKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3838:1: ( (lv_y_2_0= ruleKYPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3839:1: (lv_y_2_0= ruleKYPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3839:1: (lv_y_2_0= ruleKYPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3840:3: lv_y_2_0= ruleKYPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPositionAccess().getYKYPositionParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKYPosition_in_ruleKPosition8302);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3864:1: entryRuleKLeftPosition returns [EObject current=null] : iv_ruleKLeftPosition= ruleKLeftPosition EOF ;
    public final EObject entryRuleKLeftPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLeftPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3865:2: (iv_ruleKLeftPosition= ruleKLeftPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3866:2: iv_ruleKLeftPosition= ruleKLeftPosition EOF
            {
             newCompositeNode(grammarAccess.getKLeftPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLeftPosition_in_entryRuleKLeftPosition8338);
            iv_ruleKLeftPosition=ruleKLeftPosition();

            state._fsp--;

             current =iv_ruleKLeftPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLeftPosition8348); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3873:1: ruleKLeftPosition returns [EObject current=null] : ( () otherlv_1= 'left' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) ;
    public final EObject ruleKLeftPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_absolute_2_0 = null;

        AntlrDatatypeRuleToken lv_relative_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3876:28: ( ( () otherlv_1= 'left' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3877:1: ( () otherlv_1= 'left' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3877:1: ( () otherlv_1= 'left' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3877:2: () otherlv_1= 'left' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3877:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3878:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKLeftPositionAccess().getKLeftPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,59,FollowSets000.FOLLOW_59_in_ruleKLeftPosition8394); 

                	newLeafNode(otherlv_1, grammarAccess.getKLeftPositionAccess().getLeftKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3887:1: ( (lv_absolute_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3888:1: (lv_absolute_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3888:1: (lv_absolute_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3889:3: lv_absolute_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKLeftPositionAccess().getAbsoluteEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKLeftPosition8415);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3905:2: ( (lv_relative_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3906:1: (lv_relative_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3906:1: (lv_relative_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3907:3: lv_relative_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKLeftPositionAccess().getRelativeEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKLeftPosition8436);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3931:1: entryRuleKRightPosition returns [EObject current=null] : iv_ruleKRightPosition= ruleKRightPosition EOF ;
    public final EObject entryRuleKRightPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRightPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3932:2: (iv_ruleKRightPosition= ruleKRightPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3933:2: iv_ruleKRightPosition= ruleKRightPosition EOF
            {
             newCompositeNode(grammarAccess.getKRightPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRightPosition_in_entryRuleKRightPosition8472);
            iv_ruleKRightPosition=ruleKRightPosition();

            state._fsp--;

             current =iv_ruleKRightPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRightPosition8482); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3940:1: ruleKRightPosition returns [EObject current=null] : ( () otherlv_1= 'right' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) ;
    public final EObject ruleKRightPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_absolute_2_0 = null;

        AntlrDatatypeRuleToken lv_relative_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3943:28: ( ( () otherlv_1= 'right' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3944:1: ( () otherlv_1= 'right' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3944:1: ( () otherlv_1= 'right' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3944:2: () otherlv_1= 'right' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3944:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3945:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRightPositionAccess().getKRightPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,60,FollowSets000.FOLLOW_60_in_ruleKRightPosition8528); 

                	newLeafNode(otherlv_1, grammarAccess.getKRightPositionAccess().getRightKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3954:1: ( (lv_absolute_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3955:1: (lv_absolute_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3955:1: (lv_absolute_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3956:3: lv_absolute_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRightPositionAccess().getAbsoluteEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRightPosition8549);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3972:2: ( (lv_relative_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3973:1: (lv_relative_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3973:1: (lv_relative_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3974:3: lv_relative_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRightPositionAccess().getRelativeEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRightPosition8570);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3998:1: entryRuleKTopPosition returns [EObject current=null] : iv_ruleKTopPosition= ruleKTopPosition EOF ;
    public final EObject entryRuleKTopPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKTopPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:3999:2: (iv_ruleKTopPosition= ruleKTopPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4000:2: iv_ruleKTopPosition= ruleKTopPosition EOF
            {
             newCompositeNode(grammarAccess.getKTopPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKTopPosition_in_entryRuleKTopPosition8606);
            iv_ruleKTopPosition=ruleKTopPosition();

            state._fsp--;

             current =iv_ruleKTopPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKTopPosition8616); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4007:1: ruleKTopPosition returns [EObject current=null] : ( () otherlv_1= 'top' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) ;
    public final EObject ruleKTopPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_absolute_2_0 = null;

        AntlrDatatypeRuleToken lv_relative_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4010:28: ( ( () otherlv_1= 'top' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4011:1: ( () otherlv_1= 'top' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4011:1: ( () otherlv_1= 'top' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4011:2: () otherlv_1= 'top' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4011:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4012:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKTopPositionAccess().getKTopPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,61,FollowSets000.FOLLOW_61_in_ruleKTopPosition8662); 

                	newLeafNode(otherlv_1, grammarAccess.getKTopPositionAccess().getTopKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4021:1: ( (lv_absolute_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4022:1: (lv_absolute_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4022:1: (lv_absolute_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4023:3: lv_absolute_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKTopPositionAccess().getAbsoluteEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKTopPosition8683);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4039:2: ( (lv_relative_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4040:1: (lv_relative_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4040:1: (lv_relative_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4041:3: lv_relative_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKTopPositionAccess().getRelativeEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKTopPosition8704);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4065:1: entryRuleKBottomPosition returns [EObject current=null] : iv_ruleKBottomPosition= ruleKBottomPosition EOF ;
    public final EObject entryRuleKBottomPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBottomPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4066:2: (iv_ruleKBottomPosition= ruleKBottomPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4067:2: iv_ruleKBottomPosition= ruleKBottomPosition EOF
            {
             newCompositeNode(grammarAccess.getKBottomPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKBottomPosition_in_entryRuleKBottomPosition8740);
            iv_ruleKBottomPosition=ruleKBottomPosition();

            state._fsp--;

             current =iv_ruleKBottomPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKBottomPosition8750); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4074:1: ruleKBottomPosition returns [EObject current=null] : ( () otherlv_1= 'bottom' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) ;
    public final EObject ruleKBottomPosition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_absolute_2_0 = null;

        AntlrDatatypeRuleToken lv_relative_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4077:28: ( ( () otherlv_1= 'bottom' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4078:1: ( () otherlv_1= 'bottom' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4078:1: ( () otherlv_1= 'bottom' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4078:2: () otherlv_1= 'bottom' ( (lv_absolute_2_0= ruleEFloat ) ) ( (lv_relative_3_0= ruleEFloat ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4078:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4079:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKBottomPositionAccess().getKBottomPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,62,FollowSets000.FOLLOW_62_in_ruleKBottomPosition8796); 

                	newLeafNode(otherlv_1, grammarAccess.getKBottomPositionAccess().getBottomKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4088:1: ( (lv_absolute_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4089:1: (lv_absolute_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4089:1: (lv_absolute_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4090:3: lv_absolute_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKBottomPositionAccess().getAbsoluteEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKBottomPosition8817);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4106:2: ( (lv_relative_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4107:1: (lv_relative_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4107:1: (lv_relative_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4108:3: lv_relative_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKBottomPositionAccess().getRelativeEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKBottomPosition8838);
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


    // $ANTLR start "entryRuleKColor"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4132:1: entryRuleKColor returns [EObject current=null] : iv_ruleKColor= ruleKColor EOF ;
    public final EObject entryRuleKColor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKColor = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4133:2: (iv_ruleKColor= ruleKColor EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4134:2: iv_ruleKColor= ruleKColor EOF
            {
             newCompositeNode(grammarAccess.getKColorRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKColor_in_entryRuleKColor8874);
            iv_ruleKColor=ruleKColor();

            state._fsp--;

             current =iv_ruleKColor; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKColor8884); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4141:1: ruleKColor returns [EObject current=null] : ( () otherlv_1= 'color' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? ) ;
    public final EObject ruleKColor() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_5_0=null;
        AntlrDatatypeRuleToken lv_red_2_0 = null;

        AntlrDatatypeRuleToken lv_green_3_0 = null;

        AntlrDatatypeRuleToken lv_blue_4_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4144:28: ( ( () otherlv_1= 'color' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4145:1: ( () otherlv_1= 'color' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4145:1: ( () otherlv_1= 'color' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4145:2: () otherlv_1= 'color' ( (lv_red_2_0= ruleEInt ) ) ( (lv_green_3_0= ruleEInt ) ) ( (lv_blue_4_0= ruleEInt ) ) ( (lv_propagateToChildren_5_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4145:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4146:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKColorAccess().getKColorAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,63,FollowSets000.FOLLOW_63_in_ruleKColor8930); 

                	newLeafNode(otherlv_1, grammarAccess.getKColorAccess().getColorKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4155:1: ( (lv_red_2_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4156:1: (lv_red_2_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4156:1: (lv_red_2_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4157:3: lv_red_2_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKColorAccess().getRedEIntParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKColor8951);
            lv_red_2_0=ruleEInt();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKColorRule());
            	        }
                   		set(
                   			current, 
                   			"red",
                    		lv_red_2_0, 
                    		"EInt");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4173:2: ( (lv_green_3_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4174:1: (lv_green_3_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4174:1: (lv_green_3_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4175:3: lv_green_3_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKColorAccess().getGreenEIntParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKColor8972);
            lv_green_3_0=ruleEInt();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKColorRule());
            	        }
                   		set(
                   			current, 
                   			"green",
                    		lv_green_3_0, 
                    		"EInt");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4191:2: ( (lv_blue_4_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4192:1: (lv_blue_4_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4192:1: (lv_blue_4_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4193:3: lv_blue_4_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKColorAccess().getBlueEIntParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKColor8993);
            lv_blue_4_0=ruleEInt();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKColorRule());
            	        }
                   		set(
                   			current, 
                   			"blue",
                    		lv_blue_4_0, 
                    		"EInt");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4209:2: ( (lv_propagateToChildren_5_0= '!' ) )?
            int alt196=2;
            int LA196_0 = input.LA(1);

            if ( (LA196_0==64) ) {
                alt196=1;
            }
            switch (alt196) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4210:1: (lv_propagateToChildren_5_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4210:1: (lv_propagateToChildren_5_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4211:3: lv_propagateToChildren_5_0= '!'
                    {
                    lv_propagateToChildren_5_0=(Token)match(input,64,FollowSets000.FOLLOW_64_in_ruleKColor9011); 

                            newLeafNode(lv_propagateToChildren_5_0, grammarAccess.getKColorAccess().getPropagateToChildrenExclamationMarkKeyword_5_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKColorRule());
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
    // $ANTLR end "ruleKColor"


    // $ANTLR start "entryRuleKBackground"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4232:1: entryRuleKBackground returns [EObject current=null] : iv_ruleKBackground= ruleKBackground EOF ;
    public final EObject entryRuleKBackground() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBackground = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4233:2: (iv_ruleKBackground= ruleKBackground EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4234:2: iv_ruleKBackground= ruleKBackground EOF
            {
             newCompositeNode(grammarAccess.getKBackgroundRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKBackground_in_entryRuleKBackground9061);
            iv_ruleKBackground=ruleKBackground();

            state._fsp--;

             current =iv_ruleKBackground; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKBackground9071); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4241:1: ruleKBackground returns [EObject current=null] : (otherlv_0= 'KBackground' otherlv_1= '{' (otherlv_2= 'color' ( (lv_color_3_0= ruleKColor ) ) )? (otherlv_4= 'targetColor' ( (lv_targetColor_5_0= ruleKColor ) ) )? (otherlv_6= 'alpha' ( (lv_alpha_7_0= ruleEInt ) ) )? (otherlv_8= 'targetAlpha' ( (lv_targetAlpha_9_0= ruleEInt ) ) )? (otherlv_10= 'gradientAngle' ( (lv_gradientAngle_11_0= ruleEFloat ) ) )? otherlv_12= '}' ) ;
    public final EObject ruleKBackground() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        EObject lv_color_3_0 = null;

        EObject lv_targetColor_5_0 = null;

        AntlrDatatypeRuleToken lv_alpha_7_0 = null;

        AntlrDatatypeRuleToken lv_targetAlpha_9_0 = null;

        AntlrDatatypeRuleToken lv_gradientAngle_11_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4244:28: ( (otherlv_0= 'KBackground' otherlv_1= '{' (otherlv_2= 'color' ( (lv_color_3_0= ruleKColor ) ) )? (otherlv_4= 'targetColor' ( (lv_targetColor_5_0= ruleKColor ) ) )? (otherlv_6= 'alpha' ( (lv_alpha_7_0= ruleEInt ) ) )? (otherlv_8= 'targetAlpha' ( (lv_targetAlpha_9_0= ruleEInt ) ) )? (otherlv_10= 'gradientAngle' ( (lv_gradientAngle_11_0= ruleEFloat ) ) )? otherlv_12= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4245:1: (otherlv_0= 'KBackground' otherlv_1= '{' (otherlv_2= 'color' ( (lv_color_3_0= ruleKColor ) ) )? (otherlv_4= 'targetColor' ( (lv_targetColor_5_0= ruleKColor ) ) )? (otherlv_6= 'alpha' ( (lv_alpha_7_0= ruleEInt ) ) )? (otherlv_8= 'targetAlpha' ( (lv_targetAlpha_9_0= ruleEInt ) ) )? (otherlv_10= 'gradientAngle' ( (lv_gradientAngle_11_0= ruleEFloat ) ) )? otherlv_12= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4245:1: (otherlv_0= 'KBackground' otherlv_1= '{' (otherlv_2= 'color' ( (lv_color_3_0= ruleKColor ) ) )? (otherlv_4= 'targetColor' ( (lv_targetColor_5_0= ruleKColor ) ) )? (otherlv_6= 'alpha' ( (lv_alpha_7_0= ruleEInt ) ) )? (otherlv_8= 'targetAlpha' ( (lv_targetAlpha_9_0= ruleEInt ) ) )? (otherlv_10= 'gradientAngle' ( (lv_gradientAngle_11_0= ruleEFloat ) ) )? otherlv_12= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4245:3: otherlv_0= 'KBackground' otherlv_1= '{' (otherlv_2= 'color' ( (lv_color_3_0= ruleKColor ) ) )? (otherlv_4= 'targetColor' ( (lv_targetColor_5_0= ruleKColor ) ) )? (otherlv_6= 'alpha' ( (lv_alpha_7_0= ruleEInt ) ) )? (otherlv_8= 'targetAlpha' ( (lv_targetAlpha_9_0= ruleEInt ) ) )? (otherlv_10= 'gradientAngle' ( (lv_gradientAngle_11_0= ruleEFloat ) ) )? otherlv_12= '}'
            {
            otherlv_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKBackground9108); 

                	newLeafNode(otherlv_0, grammarAccess.getKBackgroundAccess().getKBackgroundKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKBackground9120); 

                	newLeafNode(otherlv_1, grammarAccess.getKBackgroundAccess().getLeftCurlyBracketKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4253:1: (otherlv_2= 'color' ( (lv_color_3_0= ruleKColor ) ) )?
            int alt197=2;
            int LA197_0 = input.LA(1);

            if ( (LA197_0==63) ) {
                alt197=1;
            }
            switch (alt197) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4253:3: otherlv_2= 'color' ( (lv_color_3_0= ruleKColor ) )
                    {
                    otherlv_2=(Token)match(input,63,FollowSets000.FOLLOW_63_in_ruleKBackground9133); 

                        	newLeafNode(otherlv_2, grammarAccess.getKBackgroundAccess().getColorKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4257:1: ( (lv_color_3_0= ruleKColor ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4258:1: (lv_color_3_0= ruleKColor )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4258:1: (lv_color_3_0= ruleKColor )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4259:3: lv_color_3_0= ruleKColor
                    {
                     
                    	        newCompositeNode(grammarAccess.getKBackgroundAccess().getColorKColorParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKColor_in_ruleKBackground9154);
                    lv_color_3_0=ruleKColor();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKBackgroundRule());
                    	        }
                           		set(
                           			current, 
                           			"color",
                            		lv_color_3_0, 
                            		"KColor");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4275:4: (otherlv_4= 'targetColor' ( (lv_targetColor_5_0= ruleKColor ) ) )?
            int alt198=2;
            int LA198_0 = input.LA(1);

            if ( (LA198_0==66) ) {
                alt198=1;
            }
            switch (alt198) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4275:6: otherlv_4= 'targetColor' ( (lv_targetColor_5_0= ruleKColor ) )
                    {
                    otherlv_4=(Token)match(input,66,FollowSets000.FOLLOW_66_in_ruleKBackground9169); 

                        	newLeafNode(otherlv_4, grammarAccess.getKBackgroundAccess().getTargetColorKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4279:1: ( (lv_targetColor_5_0= ruleKColor ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4280:1: (lv_targetColor_5_0= ruleKColor )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4280:1: (lv_targetColor_5_0= ruleKColor )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4281:3: lv_targetColor_5_0= ruleKColor
                    {
                     
                    	        newCompositeNode(grammarAccess.getKBackgroundAccess().getTargetColorKColorParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKColor_in_ruleKBackground9190);
                    lv_targetColor_5_0=ruleKColor();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKBackgroundRule());
                    	        }
                           		set(
                           			current, 
                           			"targetColor",
                            		lv_targetColor_5_0, 
                            		"KColor");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4297:4: (otherlv_6= 'alpha' ( (lv_alpha_7_0= ruleEInt ) ) )?
            int alt199=2;
            int LA199_0 = input.LA(1);

            if ( (LA199_0==67) ) {
                alt199=1;
            }
            switch (alt199) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4297:6: otherlv_6= 'alpha' ( (lv_alpha_7_0= ruleEInt ) )
                    {
                    otherlv_6=(Token)match(input,67,FollowSets000.FOLLOW_67_in_ruleKBackground9205); 

                        	newLeafNode(otherlv_6, grammarAccess.getKBackgroundAccess().getAlphaKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4301:1: ( (lv_alpha_7_0= ruleEInt ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4302:1: (lv_alpha_7_0= ruleEInt )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4302:1: (lv_alpha_7_0= ruleEInt )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4303:3: lv_alpha_7_0= ruleEInt
                    {
                     
                    	        newCompositeNode(grammarAccess.getKBackgroundAccess().getAlphaEIntParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKBackground9226);
                    lv_alpha_7_0=ruleEInt();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKBackgroundRule());
                    	        }
                           		set(
                           			current, 
                           			"alpha",
                            		lv_alpha_7_0, 
                            		"EInt");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4319:4: (otherlv_8= 'targetAlpha' ( (lv_targetAlpha_9_0= ruleEInt ) ) )?
            int alt200=2;
            int LA200_0 = input.LA(1);

            if ( (LA200_0==68) ) {
                alt200=1;
            }
            switch (alt200) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4319:6: otherlv_8= 'targetAlpha' ( (lv_targetAlpha_9_0= ruleEInt ) )
                    {
                    otherlv_8=(Token)match(input,68,FollowSets000.FOLLOW_68_in_ruleKBackground9241); 

                        	newLeafNode(otherlv_8, grammarAccess.getKBackgroundAccess().getTargetAlphaKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4323:1: ( (lv_targetAlpha_9_0= ruleEInt ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4324:1: (lv_targetAlpha_9_0= ruleEInt )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4324:1: (lv_targetAlpha_9_0= ruleEInt )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4325:3: lv_targetAlpha_9_0= ruleEInt
                    {
                     
                    	        newCompositeNode(grammarAccess.getKBackgroundAccess().getTargetAlphaEIntParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKBackground9262);
                    lv_targetAlpha_9_0=ruleEInt();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKBackgroundRule());
                    	        }
                           		set(
                           			current, 
                           			"targetAlpha",
                            		lv_targetAlpha_9_0, 
                            		"EInt");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4341:4: (otherlv_10= 'gradientAngle' ( (lv_gradientAngle_11_0= ruleEFloat ) ) )?
            int alt201=2;
            int LA201_0 = input.LA(1);

            if ( (LA201_0==69) ) {
                alt201=1;
            }
            switch (alt201) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4341:6: otherlv_10= 'gradientAngle' ( (lv_gradientAngle_11_0= ruleEFloat ) )
                    {
                    otherlv_10=(Token)match(input,69,FollowSets000.FOLLOW_69_in_ruleKBackground9277); 

                        	newLeafNode(otherlv_10, grammarAccess.getKBackgroundAccess().getGradientAngleKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4345:1: ( (lv_gradientAngle_11_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4346:1: (lv_gradientAngle_11_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4346:1: (lv_gradientAngle_11_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4347:3: lv_gradientAngle_11_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKBackgroundAccess().getGradientAngleEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKBackground9298);
                    lv_gradientAngle_11_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKBackgroundRule());
                    	        }
                           		set(
                           			current, 
                           			"gradientAngle",
                            		lv_gradientAngle_11_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_12=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKBackground9312); 

                	newLeafNode(otherlv_12, grammarAccess.getKBackgroundAccess().getRightCurlyBracketKeyword_7());
                

            }


            }

             leaveRule(); 
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


    // $ANTLR start "entryRuleKForeground"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4375:1: entryRuleKForeground returns [EObject current=null] : iv_ruleKForeground= ruleKForeground EOF ;
    public final EObject entryRuleKForeground() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKForeground = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4376:2: (iv_ruleKForeground= ruleKForeground EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4377:2: iv_ruleKForeground= ruleKForeground EOF
            {
             newCompositeNode(grammarAccess.getKForegroundRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKForeground_in_entryRuleKForeground9348);
            iv_ruleKForeground=ruleKForeground();

            state._fsp--;

             current =iv_ruleKForeground; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKForeground9358); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4384:1: ruleKForeground returns [EObject current=null] : (otherlv_0= 'KForeground' otherlv_1= '{' (otherlv_2= 'color' ( (lv_color_3_0= ruleKColor ) ) )? (otherlv_4= 'targetColor' ( (lv_targetColor_5_0= ruleKColor ) ) )? (otherlv_6= 'alpha' ( (lv_alpha_7_0= ruleEInt ) ) )? (otherlv_8= 'targetAlpha' ( (lv_targetAlpha_9_0= ruleEInt ) ) )? (otherlv_10= 'gradientAngle' ( (lv_gradientAngle_11_0= ruleEFloat ) ) )? otherlv_12= '}' ) ;
    public final EObject ruleKForeground() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        EObject lv_color_3_0 = null;

        EObject lv_targetColor_5_0 = null;

        AntlrDatatypeRuleToken lv_alpha_7_0 = null;

        AntlrDatatypeRuleToken lv_targetAlpha_9_0 = null;

        AntlrDatatypeRuleToken lv_gradientAngle_11_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4387:28: ( (otherlv_0= 'KForeground' otherlv_1= '{' (otherlv_2= 'color' ( (lv_color_3_0= ruleKColor ) ) )? (otherlv_4= 'targetColor' ( (lv_targetColor_5_0= ruleKColor ) ) )? (otherlv_6= 'alpha' ( (lv_alpha_7_0= ruleEInt ) ) )? (otherlv_8= 'targetAlpha' ( (lv_targetAlpha_9_0= ruleEInt ) ) )? (otherlv_10= 'gradientAngle' ( (lv_gradientAngle_11_0= ruleEFloat ) ) )? otherlv_12= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4388:1: (otherlv_0= 'KForeground' otherlv_1= '{' (otherlv_2= 'color' ( (lv_color_3_0= ruleKColor ) ) )? (otherlv_4= 'targetColor' ( (lv_targetColor_5_0= ruleKColor ) ) )? (otherlv_6= 'alpha' ( (lv_alpha_7_0= ruleEInt ) ) )? (otherlv_8= 'targetAlpha' ( (lv_targetAlpha_9_0= ruleEInt ) ) )? (otherlv_10= 'gradientAngle' ( (lv_gradientAngle_11_0= ruleEFloat ) ) )? otherlv_12= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4388:1: (otherlv_0= 'KForeground' otherlv_1= '{' (otherlv_2= 'color' ( (lv_color_3_0= ruleKColor ) ) )? (otherlv_4= 'targetColor' ( (lv_targetColor_5_0= ruleKColor ) ) )? (otherlv_6= 'alpha' ( (lv_alpha_7_0= ruleEInt ) ) )? (otherlv_8= 'targetAlpha' ( (lv_targetAlpha_9_0= ruleEInt ) ) )? (otherlv_10= 'gradientAngle' ( (lv_gradientAngle_11_0= ruleEFloat ) ) )? otherlv_12= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4388:3: otherlv_0= 'KForeground' otherlv_1= '{' (otherlv_2= 'color' ( (lv_color_3_0= ruleKColor ) ) )? (otherlv_4= 'targetColor' ( (lv_targetColor_5_0= ruleKColor ) ) )? (otherlv_6= 'alpha' ( (lv_alpha_7_0= ruleEInt ) ) )? (otherlv_8= 'targetAlpha' ( (lv_targetAlpha_9_0= ruleEInt ) ) )? (otherlv_10= 'gradientAngle' ( (lv_gradientAngle_11_0= ruleEFloat ) ) )? otherlv_12= '}'
            {
            otherlv_0=(Token)match(input,70,FollowSets000.FOLLOW_70_in_ruleKForeground9395); 

                	newLeafNode(otherlv_0, grammarAccess.getKForegroundAccess().getKForegroundKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKForeground9407); 

                	newLeafNode(otherlv_1, grammarAccess.getKForegroundAccess().getLeftCurlyBracketKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4396:1: (otherlv_2= 'color' ( (lv_color_3_0= ruleKColor ) ) )?
            int alt202=2;
            int LA202_0 = input.LA(1);

            if ( (LA202_0==63) ) {
                alt202=1;
            }
            switch (alt202) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4396:3: otherlv_2= 'color' ( (lv_color_3_0= ruleKColor ) )
                    {
                    otherlv_2=(Token)match(input,63,FollowSets000.FOLLOW_63_in_ruleKForeground9420); 

                        	newLeafNode(otherlv_2, grammarAccess.getKForegroundAccess().getColorKeyword_2_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4400:1: ( (lv_color_3_0= ruleKColor ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4401:1: (lv_color_3_0= ruleKColor )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4401:1: (lv_color_3_0= ruleKColor )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4402:3: lv_color_3_0= ruleKColor
                    {
                     
                    	        newCompositeNode(grammarAccess.getKForegroundAccess().getColorKColorParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKColor_in_ruleKForeground9441);
                    lv_color_3_0=ruleKColor();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKForegroundRule());
                    	        }
                           		set(
                           			current, 
                           			"color",
                            		lv_color_3_0, 
                            		"KColor");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4418:4: (otherlv_4= 'targetColor' ( (lv_targetColor_5_0= ruleKColor ) ) )?
            int alt203=2;
            int LA203_0 = input.LA(1);

            if ( (LA203_0==66) ) {
                alt203=1;
            }
            switch (alt203) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4418:6: otherlv_4= 'targetColor' ( (lv_targetColor_5_0= ruleKColor ) )
                    {
                    otherlv_4=(Token)match(input,66,FollowSets000.FOLLOW_66_in_ruleKForeground9456); 

                        	newLeafNode(otherlv_4, grammarAccess.getKForegroundAccess().getTargetColorKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4422:1: ( (lv_targetColor_5_0= ruleKColor ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4423:1: (lv_targetColor_5_0= ruleKColor )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4423:1: (lv_targetColor_5_0= ruleKColor )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4424:3: lv_targetColor_5_0= ruleKColor
                    {
                     
                    	        newCompositeNode(grammarAccess.getKForegroundAccess().getTargetColorKColorParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKColor_in_ruleKForeground9477);
                    lv_targetColor_5_0=ruleKColor();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKForegroundRule());
                    	        }
                           		set(
                           			current, 
                           			"targetColor",
                            		lv_targetColor_5_0, 
                            		"KColor");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4440:4: (otherlv_6= 'alpha' ( (lv_alpha_7_0= ruleEInt ) ) )?
            int alt204=2;
            int LA204_0 = input.LA(1);

            if ( (LA204_0==67) ) {
                alt204=1;
            }
            switch (alt204) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4440:6: otherlv_6= 'alpha' ( (lv_alpha_7_0= ruleEInt ) )
                    {
                    otherlv_6=(Token)match(input,67,FollowSets000.FOLLOW_67_in_ruleKForeground9492); 

                        	newLeafNode(otherlv_6, grammarAccess.getKForegroundAccess().getAlphaKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4444:1: ( (lv_alpha_7_0= ruleEInt ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4445:1: (lv_alpha_7_0= ruleEInt )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4445:1: (lv_alpha_7_0= ruleEInt )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4446:3: lv_alpha_7_0= ruleEInt
                    {
                     
                    	        newCompositeNode(grammarAccess.getKForegroundAccess().getAlphaEIntParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKForeground9513);
                    lv_alpha_7_0=ruleEInt();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKForegroundRule());
                    	        }
                           		set(
                           			current, 
                           			"alpha",
                            		lv_alpha_7_0, 
                            		"EInt");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4462:4: (otherlv_8= 'targetAlpha' ( (lv_targetAlpha_9_0= ruleEInt ) ) )?
            int alt205=2;
            int LA205_0 = input.LA(1);

            if ( (LA205_0==68) ) {
                alt205=1;
            }
            switch (alt205) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4462:6: otherlv_8= 'targetAlpha' ( (lv_targetAlpha_9_0= ruleEInt ) )
                    {
                    otherlv_8=(Token)match(input,68,FollowSets000.FOLLOW_68_in_ruleKForeground9528); 

                        	newLeafNode(otherlv_8, grammarAccess.getKForegroundAccess().getTargetAlphaKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4466:1: ( (lv_targetAlpha_9_0= ruleEInt ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4467:1: (lv_targetAlpha_9_0= ruleEInt )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4467:1: (lv_targetAlpha_9_0= ruleEInt )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4468:3: lv_targetAlpha_9_0= ruleEInt
                    {
                     
                    	        newCompositeNode(grammarAccess.getKForegroundAccess().getTargetAlphaEIntParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKForeground9549);
                    lv_targetAlpha_9_0=ruleEInt();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKForegroundRule());
                    	        }
                           		set(
                           			current, 
                           			"targetAlpha",
                            		lv_targetAlpha_9_0, 
                            		"EInt");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4484:4: (otherlv_10= 'gradientAngle' ( (lv_gradientAngle_11_0= ruleEFloat ) ) )?
            int alt206=2;
            int LA206_0 = input.LA(1);

            if ( (LA206_0==69) ) {
                alt206=1;
            }
            switch (alt206) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4484:6: otherlv_10= 'gradientAngle' ( (lv_gradientAngle_11_0= ruleEFloat ) )
                    {
                    otherlv_10=(Token)match(input,69,FollowSets000.FOLLOW_69_in_ruleKForeground9564); 

                        	newLeafNode(otherlv_10, grammarAccess.getKForegroundAccess().getGradientAngleKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4488:1: ( (lv_gradientAngle_11_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4489:1: (lv_gradientAngle_11_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4489:1: (lv_gradientAngle_11_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4490:3: lv_gradientAngle_11_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKForegroundAccess().getGradientAngleEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKForeground9585);
                    lv_gradientAngle_11_0=ruleEFloat();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKForegroundRule());
                    	        }
                           		set(
                           			current, 
                           			"gradientAngle",
                            		lv_gradientAngle_11_0, 
                            		"EFloat");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_12=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKForeground9599); 

                	newLeafNode(otherlv_12, grammarAccess.getKForegroundAccess().getRightCurlyBracketKeyword_7());
                

            }


            }

             leaveRule(); 
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


    // $ANTLR start "entryRuleKVisibility"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4518:1: entryRuleKVisibility returns [EObject current=null] : iv_ruleKVisibility= ruleKVisibility EOF ;
    public final EObject entryRuleKVisibility() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKVisibility = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4519:2: (iv_ruleKVisibility= ruleKVisibility EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4520:2: iv_ruleKVisibility= ruleKVisibility EOF
            {
             newCompositeNode(grammarAccess.getKVisibilityRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKVisibility_in_entryRuleKVisibility9635);
            iv_ruleKVisibility=ruleKVisibility();

            state._fsp--;

             current =iv_ruleKVisibility; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKVisibility9645); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4527:1: ruleKVisibility returns [EObject current=null] : ( () ( (lv_invisible_1_0= 'invisible' ) ) ) ;
    public final EObject ruleKVisibility() throws RecognitionException {
        EObject current = null;

        Token lv_invisible_1_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4530:28: ( ( () ( (lv_invisible_1_0= 'invisible' ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4531:1: ( () ( (lv_invisible_1_0= 'invisible' ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4531:1: ( () ( (lv_invisible_1_0= 'invisible' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4531:2: () ( (lv_invisible_1_0= 'invisible' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4531:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4532:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKVisibilityAccess().getKInvisibilityAction_0(),
                        current);
                

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4537:2: ( (lv_invisible_1_0= 'invisible' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4538:1: (lv_invisible_1_0= 'invisible' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4538:1: (lv_invisible_1_0= 'invisible' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4539:3: lv_invisible_1_0= 'invisible'
            {
            lv_invisible_1_0=(Token)match(input,71,FollowSets000.FOLLOW_71_in_ruleKVisibility9697); 

                    newLeafNode(lv_invisible_1_0, grammarAccess.getKVisibilityAccess().getInvisibleInvisibleKeyword_1_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKVisibilityRule());
            	        }
                   		setWithLastConsumed(current, "invisible", true, "invisible");
            	    

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
    // $ANTLR end "ruleKVisibility"


    // $ANTLR start "entryRuleKLineWidth"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4560:1: entryRuleKLineWidth returns [EObject current=null] : iv_ruleKLineWidth= ruleKLineWidth EOF ;
    public final EObject entryRuleKLineWidth() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLineWidth = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4561:2: (iv_ruleKLineWidth= ruleKLineWidth EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4562:2: iv_ruleKLineWidth= ruleKLineWidth EOF
            {
             newCompositeNode(grammarAccess.getKLineWidthRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLineWidth_in_entryRuleKLineWidth9746);
            iv_ruleKLineWidth=ruleKLineWidth();

            state._fsp--;

             current =iv_ruleKLineWidth; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLineWidth9756); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4569:1: ruleKLineWidth returns [EObject current=null] : (otherlv_0= 'lineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? (otherlv_3= 'modifier' otherlv_4= '=' ( (lv_functionId_5_0= ruleEString ) ) )? ) ;
    public final EObject ruleKLineWidth() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_propagateToChildren_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_lineWidth_1_0 = null;

        AntlrDatatypeRuleToken lv_functionId_5_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4572:28: ( (otherlv_0= 'lineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? (otherlv_3= 'modifier' otherlv_4= '=' ( (lv_functionId_5_0= ruleEString ) ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4573:1: (otherlv_0= 'lineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? (otherlv_3= 'modifier' otherlv_4= '=' ( (lv_functionId_5_0= ruleEString ) ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4573:1: (otherlv_0= 'lineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? (otherlv_3= 'modifier' otherlv_4= '=' ( (lv_functionId_5_0= ruleEString ) ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4573:3: otherlv_0= 'lineWidth' ( (lv_lineWidth_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? (otherlv_3= 'modifier' otherlv_4= '=' ( (lv_functionId_5_0= ruleEString ) ) )?
            {
            otherlv_0=(Token)match(input,72,FollowSets000.FOLLOW_72_in_ruleKLineWidth9793); 

                	newLeafNode(otherlv_0, grammarAccess.getKLineWidthAccess().getLineWidthKeyword_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4577:1: ( (lv_lineWidth_1_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4578:1: (lv_lineWidth_1_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4578:1: (lv_lineWidth_1_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4579:3: lv_lineWidth_1_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKLineWidthAccess().getLineWidthEIntParserRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKLineWidth9814);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4595:2: ( (lv_propagateToChildren_2_0= '!' ) )?
            int alt207=2;
            int LA207_0 = input.LA(1);

            if ( (LA207_0==64) ) {
                alt207=1;
            }
            switch (alt207) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4596:1: (lv_propagateToChildren_2_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4596:1: (lv_propagateToChildren_2_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4597:3: lv_propagateToChildren_2_0= '!'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,64,FollowSets000.FOLLOW_64_in_ruleKLineWidth9832); 

                            newLeafNode(lv_propagateToChildren_2_0, grammarAccess.getKLineWidthAccess().getPropagateToChildrenExclamationMarkKeyword_2_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getKLineWidthRule());
                    	        }
                           		setWithLastConsumed(current, "propagateToChildren", true, "!");
                    	    

                    }


                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4610:3: (otherlv_3= 'modifier' otherlv_4= '=' ( (lv_functionId_5_0= ruleEString ) ) )?
            int alt208=2;
            int LA208_0 = input.LA(1);

            if ( (LA208_0==73) ) {
                alt208=1;
            }
            switch (alt208) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4610:5: otherlv_3= 'modifier' otherlv_4= '=' ( (lv_functionId_5_0= ruleEString ) )
                    {
                    otherlv_3=(Token)match(input,73,FollowSets000.FOLLOW_73_in_ruleKLineWidth9859); 

                        	newLeafNode(otherlv_3, grammarAccess.getKLineWidthAccess().getModifierKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,74,FollowSets000.FOLLOW_74_in_ruleKLineWidth9871); 

                        	newLeafNode(otherlv_4, grammarAccess.getKLineWidthAccess().getEqualsSignKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4618:1: ( (lv_functionId_5_0= ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4619:1: (lv_functionId_5_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4619:1: (lv_functionId_5_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4620:3: lv_functionId_5_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getKLineWidthAccess().getFunctionIdEStringParserRuleCall_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKLineWidth9892);
                    lv_functionId_5_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getKLineWidthRule());
                    	        }
                           		set(
                           			current, 
                           			"functionId",
                            		lv_functionId_5_0, 
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
    // $ANTLR end "ruleKLineWidth"


    // $ANTLR start "entryRuleKLineStyle"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4644:1: entryRuleKLineStyle returns [EObject current=null] : iv_ruleKLineStyle= ruleKLineStyle EOF ;
    public final EObject entryRuleKLineStyle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLineStyle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4645:2: (iv_ruleKLineStyle= ruleKLineStyle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4646:2: iv_ruleKLineStyle= ruleKLineStyle EOF
            {
             newCompositeNode(grammarAccess.getKLineStyleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLineStyle_in_entryRuleKLineStyle9930);
            iv_ruleKLineStyle=ruleKLineStyle();

            state._fsp--;

             current =iv_ruleKLineStyle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLineStyle9940); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4653:1: ruleKLineStyle returns [EObject current=null] : ( () otherlv_1= 'lineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) ;
    public final EObject ruleKLineStyle() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        Enumerator lv_lineStyle_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4656:28: ( ( () otherlv_1= 'lineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4657:1: ( () otherlv_1= 'lineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4657:1: ( () otherlv_1= 'lineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4657:2: () otherlv_1= 'lineStyle' ( (lv_lineStyle_2_0= ruleLineStyle ) ) ( (lv_propagateToChildren_3_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4657:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4658:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKLineStyleAccess().getKLineStyleAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,75,FollowSets000.FOLLOW_75_in_ruleKLineStyle9986); 

                	newLeafNode(otherlv_1, grammarAccess.getKLineStyleAccess().getLineStyleKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4667:1: ( (lv_lineStyle_2_0= ruleLineStyle ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4668:1: (lv_lineStyle_2_0= ruleLineStyle )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4668:1: (lv_lineStyle_2_0= ruleLineStyle )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4669:3: lv_lineStyle_2_0= ruleLineStyle
            {
             
            	        newCompositeNode(grammarAccess.getKLineStyleAccess().getLineStyleLineStyleEnumRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleLineStyle_in_ruleKLineStyle10007);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4685:2: ( (lv_propagateToChildren_3_0= '!' ) )?
            int alt209=2;
            int LA209_0 = input.LA(1);

            if ( (LA209_0==64) ) {
                alt209=1;
            }
            switch (alt209) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4686:1: (lv_propagateToChildren_3_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4686:1: (lv_propagateToChildren_3_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4687:3: lv_propagateToChildren_3_0= '!'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,64,FollowSets000.FOLLOW_64_in_ruleKLineStyle10025); 

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


    // $ANTLR start "entryRuleKLineCapStyle"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4708:1: entryRuleKLineCapStyle returns [EObject current=null] : iv_ruleKLineCapStyle= ruleKLineCapStyle EOF ;
    public final EObject entryRuleKLineCapStyle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLineCapStyle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4709:2: (iv_ruleKLineCapStyle= ruleKLineCapStyle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4710:2: iv_ruleKLineCapStyle= ruleKLineCapStyle EOF
            {
             newCompositeNode(grammarAccess.getKLineCapStyleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLineCapStyle_in_entryRuleKLineCapStyle10075);
            iv_ruleKLineCapStyle=ruleKLineCapStyle();

            state._fsp--;

             current =iv_ruleKLineCapStyle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLineCapStyle10085); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKLineCapStyle"


    // $ANTLR start "ruleKLineCapStyle"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4717:1: ruleKLineCapStyle returns [EObject current=null] : ( () otherlv_1= 'lineCapStyle' ( (lv_lineCapStyle_2_0= ruleLineCapStyle ) ) ) ;
    public final EObject ruleKLineCapStyle() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Enumerator lv_lineCapStyle_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4720:28: ( ( () otherlv_1= 'lineCapStyle' ( (lv_lineCapStyle_2_0= ruleLineCapStyle ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4721:1: ( () otherlv_1= 'lineCapStyle' ( (lv_lineCapStyle_2_0= ruleLineCapStyle ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4721:1: ( () otherlv_1= 'lineCapStyle' ( (lv_lineCapStyle_2_0= ruleLineCapStyle ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4721:2: () otherlv_1= 'lineCapStyle' ( (lv_lineCapStyle_2_0= ruleLineCapStyle ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4721:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4722:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKLineCapStyleAccess().getKLineCapStyleAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,76,FollowSets000.FOLLOW_76_in_ruleKLineCapStyle10131); 

                	newLeafNode(otherlv_1, grammarAccess.getKLineCapStyleAccess().getLineCapStyleKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4731:1: ( (lv_lineCapStyle_2_0= ruleLineCapStyle ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4732:1: (lv_lineCapStyle_2_0= ruleLineCapStyle )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4732:1: (lv_lineCapStyle_2_0= ruleLineCapStyle )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4733:3: lv_lineCapStyle_2_0= ruleLineCapStyle
            {
             
            	        newCompositeNode(grammarAccess.getKLineCapStyleAccess().getLineCapStyleLineCapStyleEnumRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleLineCapStyle_in_ruleKLineCapStyle10152);
            lv_lineCapStyle_2_0=ruleLineCapStyle();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getKLineCapStyleRule());
            	        }
                   		set(
                   			current, 
                   			"lineCapStyle",
                    		lv_lineCapStyle_2_0, 
                    		"LineCapStyle");
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
    // $ANTLR end "ruleKLineCapStyle"


    // $ANTLR start "entryRuleKRotation"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4757:1: entryRuleKRotation returns [EObject current=null] : iv_ruleKRotation= ruleKRotation EOF ;
    public final EObject entryRuleKRotation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRotation = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4758:2: (iv_ruleKRotation= ruleKRotation EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4759:2: iv_ruleKRotation= ruleKRotation EOF
            {
             newCompositeNode(grammarAccess.getKRotationRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRotation_in_entryRuleKRotation10188);
            iv_ruleKRotation=ruleKRotation();

            state._fsp--;

             current =iv_ruleKRotation; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRotation10198); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4766:1: ruleKRotation returns [EObject current=null] : ( () otherlv_1= 'rotation' ( (lv_rotation_2_0= ruleEFloat ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) ;
    public final EObject ruleKRotation() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        AntlrDatatypeRuleToken lv_rotation_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4769:28: ( ( () otherlv_1= 'rotation' ( (lv_rotation_2_0= ruleEFloat ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4770:1: ( () otherlv_1= 'rotation' ( (lv_rotation_2_0= ruleEFloat ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4770:1: ( () otherlv_1= 'rotation' ( (lv_rotation_2_0= ruleEFloat ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4770:2: () otherlv_1= 'rotation' ( (lv_rotation_2_0= ruleEFloat ) ) ( (lv_propagateToChildren_3_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4770:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4771:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRotationAccess().getKRotationAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,77,FollowSets000.FOLLOW_77_in_ruleKRotation10244); 

                	newLeafNode(otherlv_1, grammarAccess.getKRotationAccess().getRotationKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4780:1: ( (lv_rotation_2_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4781:1: (lv_rotation_2_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4781:1: (lv_rotation_2_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4782:3: lv_rotation_2_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRotationAccess().getRotationEFloatParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRotation10265);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4798:2: ( (lv_propagateToChildren_3_0= '!' ) )?
            int alt210=2;
            int LA210_0 = input.LA(1);

            if ( (LA210_0==64) ) {
                alt210=1;
            }
            switch (alt210) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4799:1: (lv_propagateToChildren_3_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4799:1: (lv_propagateToChildren_3_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4800:3: lv_propagateToChildren_3_0= '!'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,64,FollowSets000.FOLLOW_64_in_ruleKRotation10283); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4821:1: entryRuleKFontBold returns [EObject current=null] : iv_ruleKFontBold= ruleKFontBold EOF ;
    public final EObject entryRuleKFontBold() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontBold = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4822:2: (iv_ruleKFontBold= ruleKFontBold EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4823:2: iv_ruleKFontBold= ruleKFontBold EOF
            {
             newCompositeNode(grammarAccess.getKFontBoldRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKFontBold_in_entryRuleKFontBold10333);
            iv_ruleKFontBold=ruleKFontBold();

            state._fsp--;

             current =iv_ruleKFontBold; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKFontBold10343); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4830:1: ruleKFontBold returns [EObject current=null] : ( () ( (lv_bold_1_0= 'bold' ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) ;
    public final EObject ruleKFontBold() throws RecognitionException {
        EObject current = null;

        Token lv_bold_1_0=null;
        Token lv_propagateToChildren_2_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4833:28: ( ( () ( (lv_bold_1_0= 'bold' ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4834:1: ( () ( (lv_bold_1_0= 'bold' ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4834:1: ( () ( (lv_bold_1_0= 'bold' ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4834:2: () ( (lv_bold_1_0= 'bold' ) ) ( (lv_propagateToChildren_2_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4834:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4835:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKFontBoldAccess().getKFontBoldAction_0(),
                        current);
                

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4840:2: ( (lv_bold_1_0= 'bold' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4841:1: (lv_bold_1_0= 'bold' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4841:1: (lv_bold_1_0= 'bold' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4842:3: lv_bold_1_0= 'bold'
            {
            lv_bold_1_0=(Token)match(input,78,FollowSets000.FOLLOW_78_in_ruleKFontBold10395); 

                    newLeafNode(lv_bold_1_0, grammarAccess.getKFontBoldAccess().getBoldBoldKeyword_1_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKFontBoldRule());
            	        }
                   		setWithLastConsumed(current, "bold", true, "bold");
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4855:2: ( (lv_propagateToChildren_2_0= '!' ) )?
            int alt211=2;
            int LA211_0 = input.LA(1);

            if ( (LA211_0==64) ) {
                alt211=1;
            }
            switch (alt211) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4856:1: (lv_propagateToChildren_2_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4856:1: (lv_propagateToChildren_2_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4857:3: lv_propagateToChildren_2_0= '!'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,64,FollowSets000.FOLLOW_64_in_ruleKFontBold10426); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4878:1: entryRuleKFontItalic returns [EObject current=null] : iv_ruleKFontItalic= ruleKFontItalic EOF ;
    public final EObject entryRuleKFontItalic() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontItalic = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4879:2: (iv_ruleKFontItalic= ruleKFontItalic EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4880:2: iv_ruleKFontItalic= ruleKFontItalic EOF
            {
             newCompositeNode(grammarAccess.getKFontItalicRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKFontItalic_in_entryRuleKFontItalic10476);
            iv_ruleKFontItalic=ruleKFontItalic();

            state._fsp--;

             current =iv_ruleKFontItalic; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKFontItalic10486); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4887:1: ruleKFontItalic returns [EObject current=null] : ( () ( (lv_italic_1_0= 'italic' ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) ;
    public final EObject ruleKFontItalic() throws RecognitionException {
        EObject current = null;

        Token lv_italic_1_0=null;
        Token lv_propagateToChildren_2_0=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4890:28: ( ( () ( (lv_italic_1_0= 'italic' ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4891:1: ( () ( (lv_italic_1_0= 'italic' ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4891:1: ( () ( (lv_italic_1_0= 'italic' ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4891:2: () ( (lv_italic_1_0= 'italic' ) ) ( (lv_propagateToChildren_2_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4891:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4892:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKFontItalicAccess().getKFontItalicAction_0(),
                        current);
                

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4897:2: ( (lv_italic_1_0= 'italic' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4898:1: (lv_italic_1_0= 'italic' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4898:1: (lv_italic_1_0= 'italic' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4899:3: lv_italic_1_0= 'italic'
            {
            lv_italic_1_0=(Token)match(input,79,FollowSets000.FOLLOW_79_in_ruleKFontItalic10538); 

                    newLeafNode(lv_italic_1_0, grammarAccess.getKFontItalicAccess().getItalicItalicKeyword_1_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKFontItalicRule());
            	        }
                   		setWithLastConsumed(current, "italic", true, "italic");
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4912:2: ( (lv_propagateToChildren_2_0= '!' ) )?
            int alt212=2;
            int LA212_0 = input.LA(1);

            if ( (LA212_0==64) ) {
                alt212=1;
            }
            switch (alt212) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4913:1: (lv_propagateToChildren_2_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4913:1: (lv_propagateToChildren_2_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4914:3: lv_propagateToChildren_2_0= '!'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,64,FollowSets000.FOLLOW_64_in_ruleKFontItalic10569); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4935:1: entryRuleKFontName returns [EObject current=null] : iv_ruleKFontName= ruleKFontName EOF ;
    public final EObject entryRuleKFontName() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontName = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4936:2: (iv_ruleKFontName= ruleKFontName EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4937:2: iv_ruleKFontName= ruleKFontName EOF
            {
             newCompositeNode(grammarAccess.getKFontNameRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKFontName_in_entryRuleKFontName10619);
            iv_ruleKFontName=ruleKFontName();

            state._fsp--;

             current =iv_ruleKFontName; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKFontName10629); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4944:1: ruleKFontName returns [EObject current=null] : (otherlv_0= 'font' ( (lv_name_1_0= ruleEString ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) ;
    public final EObject ruleKFontName() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_propagateToChildren_2_0=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4947:28: ( (otherlv_0= 'font' ( (lv_name_1_0= ruleEString ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4948:1: (otherlv_0= 'font' ( (lv_name_1_0= ruleEString ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4948:1: (otherlv_0= 'font' ( (lv_name_1_0= ruleEString ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4948:3: otherlv_0= 'font' ( (lv_name_1_0= ruleEString ) ) ( (lv_propagateToChildren_2_0= '!' ) )?
            {
            otherlv_0=(Token)match(input,80,FollowSets000.FOLLOW_80_in_ruleKFontName10666); 

                	newLeafNode(otherlv_0, grammarAccess.getKFontNameAccess().getFontKeyword_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4952:1: ( (lv_name_1_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4953:1: (lv_name_1_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4953:1: (lv_name_1_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4954:3: lv_name_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKFontNameAccess().getNameEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKFontName10687);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4970:2: ( (lv_propagateToChildren_2_0= '!' ) )?
            int alt213=2;
            int LA213_0 = input.LA(1);

            if ( (LA213_0==64) ) {
                alt213=1;
            }
            switch (alt213) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4971:1: (lv_propagateToChildren_2_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4971:1: (lv_propagateToChildren_2_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4972:3: lv_propagateToChildren_2_0= '!'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,64,FollowSets000.FOLLOW_64_in_ruleKFontName10705); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4993:1: entryRuleKFontSize returns [EObject current=null] : iv_ruleKFontSize= ruleKFontSize EOF ;
    public final EObject entryRuleKFontSize() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKFontSize = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4994:2: (iv_ruleKFontSize= ruleKFontSize EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:4995:2: iv_ruleKFontSize= ruleKFontSize EOF
            {
             newCompositeNode(grammarAccess.getKFontSizeRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKFontSize_in_entryRuleKFontSize10755);
            iv_ruleKFontSize=ruleKFontSize();

            state._fsp--;

             current =iv_ruleKFontSize; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKFontSize10765); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5002:1: ruleKFontSize returns [EObject current=null] : (otherlv_0= 'fontSize' ( (lv_size_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) ;
    public final EObject ruleKFontSize() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_propagateToChildren_2_0=null;
        AntlrDatatypeRuleToken lv_size_1_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5005:28: ( (otherlv_0= 'fontSize' ( (lv_size_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5006:1: (otherlv_0= 'fontSize' ( (lv_size_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5006:1: (otherlv_0= 'fontSize' ( (lv_size_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5006:3: otherlv_0= 'fontSize' ( (lv_size_1_0= ruleEInt ) ) ( (lv_propagateToChildren_2_0= '!' ) )?
            {
            otherlv_0=(Token)match(input,81,FollowSets000.FOLLOW_81_in_ruleKFontSize10802); 

                	newLeafNode(otherlv_0, grammarAccess.getKFontSizeAccess().getFontSizeKeyword_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5010:1: ( (lv_size_1_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5011:1: (lv_size_1_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5011:1: (lv_size_1_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5012:3: lv_size_1_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKFontSizeAccess().getSizeEIntParserRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKFontSize10823);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5028:2: ( (lv_propagateToChildren_2_0= '!' ) )?
            int alt214=2;
            int LA214_0 = input.LA(1);

            if ( (LA214_0==64) ) {
                alt214=1;
            }
            switch (alt214) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5029:1: (lv_propagateToChildren_2_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5029:1: (lv_propagateToChildren_2_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5030:3: lv_propagateToChildren_2_0= '!'
                    {
                    lv_propagateToChildren_2_0=(Token)match(input,64,FollowSets000.FOLLOW_64_in_ruleKFontSize10841); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5051:1: entryRuleKVerticalAlignment returns [EObject current=null] : iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF ;
    public final EObject entryRuleKVerticalAlignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKVerticalAlignment = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5052:2: (iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5053:2: iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF
            {
             newCompositeNode(grammarAccess.getKVerticalAlignmentRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKVerticalAlignment_in_entryRuleKVerticalAlignment10891);
            iv_ruleKVerticalAlignment=ruleKVerticalAlignment();

            state._fsp--;

             current =iv_ruleKVerticalAlignment; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKVerticalAlignment10901); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5060:1: ruleKVerticalAlignment returns [EObject current=null] : ( () otherlv_1= 'verticalAlignment' ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) ;
    public final EObject ruleKVerticalAlignment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        Enumerator lv_verticalAlignment_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5063:28: ( ( () otherlv_1= 'verticalAlignment' ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5064:1: ( () otherlv_1= 'verticalAlignment' ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5064:1: ( () otherlv_1= 'verticalAlignment' ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5064:2: () otherlv_1= 'verticalAlignment' ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5064:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5065:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKVerticalAlignmentAccess().getKVerticalAlignmentAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,56,FollowSets000.FOLLOW_56_in_ruleKVerticalAlignment10947); 

                	newLeafNode(otherlv_1, grammarAccess.getKVerticalAlignmentAccess().getVerticalAlignmentKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5074:1: ( (lv_verticalAlignment_2_0= ruleVerticalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5075:1: (lv_verticalAlignment_2_0= ruleVerticalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5075:1: (lv_verticalAlignment_2_0= ruleVerticalAlignment )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5076:3: lv_verticalAlignment_2_0= ruleVerticalAlignment
            {
             
            	        newCompositeNode(grammarAccess.getKVerticalAlignmentAccess().getVerticalAlignmentVerticalAlignmentEnumRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleVerticalAlignment_in_ruleKVerticalAlignment10968);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5092:2: ( (lv_propagateToChildren_3_0= '!' ) )?
            int alt215=2;
            int LA215_0 = input.LA(1);

            if ( (LA215_0==64) ) {
                alt215=1;
            }
            switch (alt215) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5093:1: (lv_propagateToChildren_3_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5093:1: (lv_propagateToChildren_3_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5094:3: lv_propagateToChildren_3_0= '!'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,64,FollowSets000.FOLLOW_64_in_ruleKVerticalAlignment10986); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5115:1: entryRuleKHorizontalAlignment returns [EObject current=null] : iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF ;
    public final EObject entryRuleKHorizontalAlignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKHorizontalAlignment = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5116:2: (iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5117:2: iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF
            {
             newCompositeNode(grammarAccess.getKHorizontalAlignmentRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKHorizontalAlignment_in_entryRuleKHorizontalAlignment11036);
            iv_ruleKHorizontalAlignment=ruleKHorizontalAlignment();

            state._fsp--;

             current =iv_ruleKHorizontalAlignment; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKHorizontalAlignment11046); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5124:1: ruleKHorizontalAlignment returns [EObject current=null] : ( () otherlv_1= 'horizontalAlignment' ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) ;
    public final EObject ruleKHorizontalAlignment() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_propagateToChildren_3_0=null;
        Enumerator lv_horizontalAlignment_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5127:28: ( ( () otherlv_1= 'horizontalAlignment' ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5128:1: ( () otherlv_1= 'horizontalAlignment' ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5128:1: ( () otherlv_1= 'horizontalAlignment' ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5128:2: () otherlv_1= 'horizontalAlignment' ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) ) ( (lv_propagateToChildren_3_0= '!' ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5128:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5129:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKHorizontalAlignmentAccess().getKHorizontalAlignmentAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,57,FollowSets000.FOLLOW_57_in_ruleKHorizontalAlignment11092); 

                	newLeafNode(otherlv_1, grammarAccess.getKHorizontalAlignmentAccess().getHorizontalAlignmentKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5138:1: ( (lv_horizontalAlignment_2_0= ruleHorizontalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5139:1: (lv_horizontalAlignment_2_0= ruleHorizontalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5139:1: (lv_horizontalAlignment_2_0= ruleHorizontalAlignment )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5140:3: lv_horizontalAlignment_2_0= ruleHorizontalAlignment
            {
             
            	        newCompositeNode(grammarAccess.getKHorizontalAlignmentAccess().getHorizontalAlignmentHorizontalAlignmentEnumRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleHorizontalAlignment_in_ruleKHorizontalAlignment11113);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5156:2: ( (lv_propagateToChildren_3_0= '!' ) )?
            int alt216=2;
            int LA216_0 = input.LA(1);

            if ( (LA216_0==64) ) {
                alt216=1;
            }
            switch (alt216) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5157:1: (lv_propagateToChildren_3_0= '!' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5157:1: (lv_propagateToChildren_3_0= '!' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5158:3: lv_propagateToChildren_3_0= '!'
                    {
                    lv_propagateToChildren_3_0=(Token)match(input,64,FollowSets000.FOLLOW_64_in_ruleKHorizontalAlignment11131); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5179:1: entryRuleKGridPlacement returns [EObject current=null] : iv_ruleKGridPlacement= ruleKGridPlacement EOF ;
    public final EObject entryRuleKGridPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKGridPlacement = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5180:2: (iv_ruleKGridPlacement= ruleKGridPlacement EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5181:2: iv_ruleKGridPlacement= ruleKGridPlacement EOF
            {
             newCompositeNode(grammarAccess.getKGridPlacementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKGridPlacement_in_entryRuleKGridPlacement11181);
            iv_ruleKGridPlacement=ruleKGridPlacement();

            state._fsp--;

             current =iv_ruleKGridPlacement; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKGridPlacement11191); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5188:1: ruleKGridPlacement returns [EObject current=null] : ( () otherlv_1= 'gridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) ) ;
    public final EObject ruleKGridPlacement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_numColumns_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5191:28: ( ( () otherlv_1= 'gridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5192:1: ( () otherlv_1= 'gridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5192:1: ( () otherlv_1= 'gridPlacement' ( (lv_numColumns_2_0= ruleEInt ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5192:2: () otherlv_1= 'gridPlacement' ( (lv_numColumns_2_0= ruleEInt ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5192:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5193:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKGridPlacementAccess().getKGridPlacementAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,82,FollowSets000.FOLLOW_82_in_ruleKGridPlacement11237); 

                	newLeafNode(otherlv_1, grammarAccess.getKGridPlacementAccess().getGridPlacementKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5202:1: ( (lv_numColumns_2_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5203:1: (lv_numColumns_2_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5203:1: (lv_numColumns_2_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5204:3: lv_numColumns_2_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementAccess().getNumColumnsEIntParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKGridPlacement11258);
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


    // $ANTLR start "entryRuleEFloat"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5228:1: entryRuleEFloat returns [String current=null] : iv_ruleEFloat= ruleEFloat EOF ;
    public final String entryRuleEFloat() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEFloat = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5229:2: (iv_ruleEFloat= ruleEFloat EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5230:2: iv_ruleEFloat= ruleEFloat EOF
            {
             newCompositeNode(grammarAccess.getEFloatRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_entryRuleEFloat11295);
            iv_ruleEFloat=ruleEFloat();

            state._fsp--;

             current =iv_ruleEFloat.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEFloat11306); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5237:1: ruleEFloat returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )? ) ;
    public final AntlrDatatypeRuleToken ruleEFloat() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;
        Token this_INT_3=null;
        Token this_INT_7=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5240:28: ( ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5241:1: ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5241:1: ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5241:2: (kw= '-' )? this_INT_1= RULE_INT (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5241:2: (kw= '-' )?
            int alt217=2;
            int LA217_0 = input.LA(1);

            if ( (LA217_0==29) ) {
                alt217=1;
            }
            switch (alt217) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5242:2: kw= '-'
                    {
                    kw=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleEFloat11345); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEFloatAccess().getHyphenMinusKeyword_0()); 
                        

                    }
                    break;

            }

            this_INT_1=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat11362); 

            		current.merge(this_INT_1);
                
             
                newLeafNode(this_INT_1, grammarAccess.getEFloatAccess().getINTTerminalRuleCall_1()); 
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5254:1: (kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )?
            int alt221=2;
            int LA221_0 = input.LA(1);

            if ( (LA221_0==83) ) {
                alt221=1;
            }
            switch (alt221) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5255:2: kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )?
                    {
                    kw=(Token)match(input,83,FollowSets000.FOLLOW_83_in_ruleEFloat11381); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEFloatAccess().getFullStopKeyword_2_0()); 
                        
                    this_INT_3=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat11396); 

                    		current.merge(this_INT_3);
                        
                     
                        newLeafNode(this_INT_3, grammarAccess.getEFloatAccess().getINTTerminalRuleCall_2_1()); 
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5267:1: ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )?
                    int alt220=2;
                    int LA220_0 = input.LA(1);

                    if ( ((LA220_0>=84 && LA220_0<=85)) ) {
                        alt220=1;
                    }
                    switch (alt220) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5267:2: (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT
                            {
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5267:2: (kw= 'E' | kw= 'e' )
                            int alt218=2;
                            int LA218_0 = input.LA(1);

                            if ( (LA218_0==84) ) {
                                alt218=1;
                            }
                            else if ( (LA218_0==85) ) {
                                alt218=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 218, 0, input);

                                throw nvae;
                            }
                            switch (alt218) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5268:2: kw= 'E'
                                    {
                                    kw=(Token)match(input,84,FollowSets000.FOLLOW_84_in_ruleEFloat11416); 

                                            current.merge(kw);
                                            newLeafNode(kw, grammarAccess.getEFloatAccess().getEKeyword_2_2_0_0()); 
                                        

                                    }
                                    break;
                                case 2 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5275:2: kw= 'e'
                                    {
                                    kw=(Token)match(input,85,FollowSets000.FOLLOW_85_in_ruleEFloat11435); 

                                            current.merge(kw);
                                            newLeafNode(kw, grammarAccess.getEFloatAccess().getEKeyword_2_2_0_1()); 
                                        

                                    }
                                    break;

                            }

                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5280:2: (kw= '-' )?
                            int alt219=2;
                            int LA219_0 = input.LA(1);

                            if ( (LA219_0==29) ) {
                                alt219=1;
                            }
                            switch (alt219) {
                                case 1 :
                                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5281:2: kw= '-'
                                    {
                                    kw=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleEFloat11450); 

                                            current.merge(kw);
                                            newLeafNode(kw, grammarAccess.getEFloatAccess().getHyphenMinusKeyword_2_2_1()); 
                                        

                                    }
                                    break;

                            }

                            this_INT_7=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat11467); 

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


    // $ANTLR start "entryRuleEInt"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5303:1: entryRuleEInt returns [String current=null] : iv_ruleEInt= ruleEInt EOF ;
    public final String entryRuleEInt() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEInt = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5304:2: (iv_ruleEInt= ruleEInt EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5305:2: iv_ruleEInt= ruleEInt EOF
            {
             newCompositeNode(grammarAccess.getEIntRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_entryRuleEInt11519);
            iv_ruleEInt=ruleEInt();

            state._fsp--;

             current =iv_ruleEInt.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEInt11530); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5312:1: ruleEInt returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleEInt() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5315:28: ( ( (kw= '-' )? this_INT_1= RULE_INT ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5316:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5316:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5316:2: (kw= '-' )? this_INT_1= RULE_INT
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5316:2: (kw= '-' )?
            int alt222=2;
            int LA222_0 = input.LA(1);

            if ( (LA222_0==29) ) {
                alt222=1;
            }
            switch (alt222) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5317:2: kw= '-'
                    {
                    kw=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleEInt11569); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEIntAccess().getHyphenMinusKeyword_0()); 
                        

                    }
                    break;

            }

            this_INT_1=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEInt11586); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5339:1: entryRuleKInsets returns [EObject current=null] : iv_ruleKInsets= ruleKInsets EOF ;
    public final EObject entryRuleKInsets() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKInsets = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5340:2: (iv_ruleKInsets= ruleKInsets EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5341:2: iv_ruleKInsets= ruleKInsets EOF
            {
             newCompositeNode(grammarAccess.getKInsetsRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKInsets_in_entryRuleKInsets11633);
            iv_ruleKInsets=ruleKInsets();

            state._fsp--;

             current =iv_ruleKInsets; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKInsets11643); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5348:1: ruleKInsets returns [EObject current=null] : ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5351:28: ( ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5352:1: ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5352:1: ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5352:2: () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5352:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5353:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKInsetsAccess().getKInsetsAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,86,FollowSets000.FOLLOW_86_in_ruleKInsets11689); 

                	newLeafNode(otherlv_1, grammarAccess.getKInsetsAccess().getKInsetsKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKInsets11701); 

                	newLeafNode(otherlv_2, grammarAccess.getKInsetsAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5366:1: (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )?
            int alt223=2;
            int LA223_0 = input.LA(1);

            if ( (LA223_0==61) ) {
                alt223=1;
            }
            switch (alt223) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5366:3: otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,61,FollowSets000.FOLLOW_61_in_ruleKInsets11714); 

                        	newLeafNode(otherlv_3, grammarAccess.getKInsetsAccess().getTopKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5370:1: ( (lv_top_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5371:1: (lv_top_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5371:1: (lv_top_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5372:3: lv_top_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getTopEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets11735);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5388:4: (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )?
            int alt224=2;
            int LA224_0 = input.LA(1);

            if ( (LA224_0==62) ) {
                alt224=1;
            }
            switch (alt224) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5388:6: otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,62,FollowSets000.FOLLOW_62_in_ruleKInsets11750); 

                        	newLeafNode(otherlv_5, grammarAccess.getKInsetsAccess().getBottomKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5392:1: ( (lv_bottom_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5393:1: (lv_bottom_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5393:1: (lv_bottom_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5394:3: lv_bottom_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getBottomEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets11771);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5410:4: (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )?
            int alt225=2;
            int LA225_0 = input.LA(1);

            if ( (LA225_0==59) ) {
                alt225=1;
            }
            switch (alt225) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5410:6: otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,59,FollowSets000.FOLLOW_59_in_ruleKInsets11786); 

                        	newLeafNode(otherlv_7, grammarAccess.getKInsetsAccess().getLeftKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5414:1: ( (lv_left_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5415:1: (lv_left_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5415:1: (lv_left_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5416:3: lv_left_8_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getLeftEFloatParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets11807);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5432:4: (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )?
            int alt226=2;
            int LA226_0 = input.LA(1);

            if ( (LA226_0==60) ) {
                alt226=1;
            }
            switch (alt226) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5432:6: otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) )
                    {
                    otherlv_9=(Token)match(input,60,FollowSets000.FOLLOW_60_in_ruleKInsets11822); 

                        	newLeafNode(otherlv_9, grammarAccess.getKInsetsAccess().getRightKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5436:1: ( (lv_right_10_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5437:1: (lv_right_10_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5437:1: (lv_right_10_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5438:3: lv_right_10_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getRightEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets11843);
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

            otherlv_11=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKInsets11857); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5468:1: entryRuleKPoint returns [EObject current=null] : iv_ruleKPoint= ruleKPoint EOF ;
    public final EObject entryRuleKPoint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPoint = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5469:2: (iv_ruleKPoint= ruleKPoint EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5470:2: iv_ruleKPoint= ruleKPoint EOF
            {
             newCompositeNode(grammarAccess.getKPointRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPoint_in_entryRuleKPoint11895);
            iv_ruleKPoint=ruleKPoint();

            state._fsp--;

             current =iv_ruleKPoint; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPoint11905); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5477:1: ruleKPoint returns [EObject current=null] : ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) ) ;
    public final EObject ruleKPoint() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_x_3_0 = null;

        AntlrDatatypeRuleToken lv_y_5_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5480:28: ( ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5481:1: ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5481:1: ( () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5481:2: () otherlv_1= 'KPoint' (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) ) (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5481:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5482:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPointAccess().getKPointAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,87,FollowSets000.FOLLOW_87_in_ruleKPoint11951); 

                	newLeafNode(otherlv_1, grammarAccess.getKPointAccess().getKPointKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5491:1: (otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5491:3: otherlv_2= 'x' ( (lv_x_3_0= ruleEFloat ) )
            {
            otherlv_2=(Token)match(input,88,FollowSets000.FOLLOW_88_in_ruleKPoint11964); 

                	newLeafNode(otherlv_2, grammarAccess.getKPointAccess().getXKeyword_2_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5495:1: ( (lv_x_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5496:1: (lv_x_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5496:1: (lv_x_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5497:3: lv_x_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKPointAccess().getXEFloatParserRuleCall_2_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKPoint11985);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5513:3: (otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5513:5: otherlv_4= 'y' ( (lv_y_5_0= ruleEFloat ) )
            {
            otherlv_4=(Token)match(input,89,FollowSets000.FOLLOW_89_in_ruleKPoint11999); 

                	newLeafNode(otherlv_4, grammarAccess.getKPointAccess().getYKeyword_3_0());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5517:1: ( (lv_y_5_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5518:1: (lv_y_5_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5518:1: (lv_y_5_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5519:3: lv_y_5_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKPointAccess().getYEFloatParserRuleCall_3_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKPoint12020);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5543:1: entryRulePersistentEntry returns [EObject current=null] : iv_rulePersistentEntry= rulePersistentEntry EOF ;
    public final EObject entryRulePersistentEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePersistentEntry = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5544:2: (iv_rulePersistentEntry= rulePersistentEntry EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5545:2: iv_rulePersistentEntry= rulePersistentEntry EOF
            {
             newCompositeNode(grammarAccess.getPersistentEntryRule()); 
            pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry12057);
            iv_rulePersistentEntry=rulePersistentEntry();

            state._fsp--;

             current =iv_rulePersistentEntry; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRulePersistentEntry12067); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5552:1: rulePersistentEntry returns [EObject current=null] : ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? ) ;
    public final EObject rulePersistentEntry() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_key_0_0 = null;

        AntlrDatatypeRuleToken lv_value_2_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5555:28: ( ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5556:1: ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5556:1: ( ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5556:2: ( (lv_key_0_0= ruleEString ) ) (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5556:2: ( (lv_key_0_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5557:1: (lv_key_0_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5557:1: (lv_key_0_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5558:3: lv_key_0_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getKeyEStringParserRuleCall_0_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_rulePersistentEntry12113);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5574:2: (otherlv_1= '=' ( (lv_value_2_0= ruleEString ) ) )?
            int alt227=2;
            int LA227_0 = input.LA(1);

            if ( (LA227_0==74) ) {
                alt227=1;
            }
            switch (alt227) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5574:4: otherlv_1= '=' ( (lv_value_2_0= ruleEString ) )
                    {
                    otherlv_1=(Token)match(input,74,FollowSets000.FOLLOW_74_in_rulePersistentEntry12126); 

                        	newLeafNode(otherlv_1, grammarAccess.getPersistentEntryAccess().getEqualsSignKeyword_1_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5578:1: ( (lv_value_2_0= ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5579:1: (lv_value_2_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5579:1: (lv_value_2_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5580:3: lv_value_2_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getPersistentEntryAccess().getValueEStringParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_rulePersistentEntry12147);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5604:1: entryRuleEString returns [String current=null] : iv_ruleEString= ruleEString EOF ;
    public final String entryRuleEString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEString = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5605:2: (iv_ruleEString= ruleEString EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5606:2: iv_ruleEString= ruleEString EOF
            {
             newCompositeNode(grammarAccess.getEStringRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_entryRuleEString12186);
            iv_ruleEString=ruleEString();

            state._fsp--;

             current =iv_ruleEString.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEString12197); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5613:1: ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleEString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token this_ID_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5616:28: ( (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5617:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5617:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            int alt228=2;
            int LA228_0 = input.LA(1);

            if ( (LA228_0==RULE_STRING) ) {
                alt228=1;
            }
            else if ( (LA228_0==RULE_ID) ) {
                alt228=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 228, 0, input);

                throw nvae;
            }
            switch (alt228) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5617:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleEString12237); 

                    		current.merge(this_STRING_0);
                        
                     
                        newLeafNode(this_STRING_0, grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5625:10: this_ID_1= RULE_ID
                    {
                    this_ID_1=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleEString12263); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5640:1: ruleLineStyle returns [Enumerator current=null] : ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) ) ;
    public final Enumerator ruleLineStyle() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5642:28: ( ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5643:1: ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5643:1: ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) )
            int alt229=5;
            switch ( input.LA(1) ) {
            case 90:
                {
                alt229=1;
                }
                break;
            case 91:
                {
                alt229=2;
                }
                break;
            case 92:
                {
                alt229=3;
                }
                break;
            case 93:
                {
                alt229=4;
                }
                break;
            case 94:
                {
                alt229=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 229, 0, input);

                throw nvae;
            }

            switch (alt229) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5643:2: (enumLiteral_0= 'SOLID' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5643:2: (enumLiteral_0= 'SOLID' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5643:4: enumLiteral_0= 'SOLID'
                    {
                    enumLiteral_0=(Token)match(input,90,FollowSets000.FOLLOW_90_in_ruleLineStyle12322); 

                            current = grammarAccess.getLineStyleAccess().getSOLIDEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getLineStyleAccess().getSOLIDEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5649:6: (enumLiteral_1= 'DASH' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5649:6: (enumLiteral_1= 'DASH' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5649:8: enumLiteral_1= 'DASH'
                    {
                    enumLiteral_1=(Token)match(input,91,FollowSets000.FOLLOW_91_in_ruleLineStyle12339); 

                            current = grammarAccess.getLineStyleAccess().getDASHEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getLineStyleAccess().getDASHEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5655:6: (enumLiteral_2= 'DOT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5655:6: (enumLiteral_2= 'DOT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5655:8: enumLiteral_2= 'DOT'
                    {
                    enumLiteral_2=(Token)match(input,92,FollowSets000.FOLLOW_92_in_ruleLineStyle12356); 

                            current = grammarAccess.getLineStyleAccess().getDOTEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getLineStyleAccess().getDOTEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5661:6: (enumLiteral_3= 'DASHDOT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5661:6: (enumLiteral_3= 'DASHDOT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5661:8: enumLiteral_3= 'DASHDOT'
                    {
                    enumLiteral_3=(Token)match(input,93,FollowSets000.FOLLOW_93_in_ruleLineStyle12373); 

                            current = grammarAccess.getLineStyleAccess().getDASHDOTEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getLineStyleAccess().getDASHDOTEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5667:6: (enumLiteral_4= 'DASHDOTDOT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5667:6: (enumLiteral_4= 'DASHDOTDOT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5667:8: enumLiteral_4= 'DASHDOTDOT'
                    {
                    enumLiteral_4=(Token)match(input,94,FollowSets000.FOLLOW_94_in_ruleLineStyle12390); 

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


    // $ANTLR start "ruleLineCapStyle"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5677:1: ruleLineCapStyle returns [Enumerator current=null] : ( (enumLiteral_0= 'CAP_FLAT' ) | (enumLiteral_1= 'CAP_ROUND' ) | (enumLiteral_2= 'CAP_SQUARE' ) ) ;
    public final Enumerator ruleLineCapStyle() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5679:28: ( ( (enumLiteral_0= 'CAP_FLAT' ) | (enumLiteral_1= 'CAP_ROUND' ) | (enumLiteral_2= 'CAP_SQUARE' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5680:1: ( (enumLiteral_0= 'CAP_FLAT' ) | (enumLiteral_1= 'CAP_ROUND' ) | (enumLiteral_2= 'CAP_SQUARE' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5680:1: ( (enumLiteral_0= 'CAP_FLAT' ) | (enumLiteral_1= 'CAP_ROUND' ) | (enumLiteral_2= 'CAP_SQUARE' ) )
            int alt230=3;
            switch ( input.LA(1) ) {
            case 95:
                {
                alt230=1;
                }
                break;
            case 96:
                {
                alt230=2;
                }
                break;
            case 97:
                {
                alt230=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 230, 0, input);

                throw nvae;
            }

            switch (alt230) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5680:2: (enumLiteral_0= 'CAP_FLAT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5680:2: (enumLiteral_0= 'CAP_FLAT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5680:4: enumLiteral_0= 'CAP_FLAT'
                    {
                    enumLiteral_0=(Token)match(input,95,FollowSets000.FOLLOW_95_in_ruleLineCapStyle12435); 

                            current = grammarAccess.getLineCapStyleAccess().getCAP_FLATEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getLineCapStyleAccess().getCAP_FLATEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5686:6: (enumLiteral_1= 'CAP_ROUND' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5686:6: (enumLiteral_1= 'CAP_ROUND' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5686:8: enumLiteral_1= 'CAP_ROUND'
                    {
                    enumLiteral_1=(Token)match(input,96,FollowSets000.FOLLOW_96_in_ruleLineCapStyle12452); 

                            current = grammarAccess.getLineCapStyleAccess().getCAP_ROUNDEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getLineCapStyleAccess().getCAP_ROUNDEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5692:6: (enumLiteral_2= 'CAP_SQUARE' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5692:6: (enumLiteral_2= 'CAP_SQUARE' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5692:8: enumLiteral_2= 'CAP_SQUARE'
                    {
                    enumLiteral_2=(Token)match(input,97,FollowSets000.FOLLOW_97_in_ruleLineCapStyle12469); 

                            current = grammarAccess.getLineCapStyleAccess().getCAP_SQUAREEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getLineCapStyleAccess().getCAP_SQUAREEnumLiteralDeclaration_2()); 
                        

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
    // $ANTLR end "ruleLineCapStyle"


    // $ANTLR start "ruleVerticalAlignment"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5702:1: ruleVerticalAlignment returns [Enumerator current=null] : ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) ) ;
    public final Enumerator ruleVerticalAlignment() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5704:28: ( ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5705:1: ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5705:1: ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) )
            int alt231=3;
            switch ( input.LA(1) ) {
            case 98:
                {
                alt231=1;
                }
                break;
            case 99:
                {
                alt231=2;
                }
                break;
            case 100:
                {
                alt231=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 231, 0, input);

                throw nvae;
            }

            switch (alt231) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5705:2: (enumLiteral_0= 'TOP' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5705:2: (enumLiteral_0= 'TOP' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5705:4: enumLiteral_0= 'TOP'
                    {
                    enumLiteral_0=(Token)match(input,98,FollowSets000.FOLLOW_98_in_ruleVerticalAlignment12514); 

                            current = grammarAccess.getVerticalAlignmentAccess().getTOPEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getVerticalAlignmentAccess().getTOPEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5711:6: (enumLiteral_1= 'CENTER' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5711:6: (enumLiteral_1= 'CENTER' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5711:8: enumLiteral_1= 'CENTER'
                    {
                    enumLiteral_1=(Token)match(input,99,FollowSets000.FOLLOW_99_in_ruleVerticalAlignment12531); 

                            current = grammarAccess.getVerticalAlignmentAccess().getCENTEREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getVerticalAlignmentAccess().getCENTEREnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5717:6: (enumLiteral_2= 'BOTTOM' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5717:6: (enumLiteral_2= 'BOTTOM' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5717:8: enumLiteral_2= 'BOTTOM'
                    {
                    enumLiteral_2=(Token)match(input,100,FollowSets000.FOLLOW_100_in_ruleVerticalAlignment12548); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5727:1: ruleHorizontalAlignment returns [Enumerator current=null] : ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) ) ;
    public final Enumerator ruleHorizontalAlignment() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5729:28: ( ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5730:1: ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5730:1: ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) )
            int alt232=3;
            switch ( input.LA(1) ) {
            case 101:
                {
                alt232=1;
                }
                break;
            case 99:
                {
                alt232=2;
                }
                break;
            case 102:
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
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5730:2: (enumLiteral_0= 'LEFT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5730:2: (enumLiteral_0= 'LEFT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5730:4: enumLiteral_0= 'LEFT'
                    {
                    enumLiteral_0=(Token)match(input,101,FollowSets000.FOLLOW_101_in_ruleHorizontalAlignment12593); 

                            current = grammarAccess.getHorizontalAlignmentAccess().getLEFTEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getHorizontalAlignmentAccess().getLEFTEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5736:6: (enumLiteral_1= 'CENTER' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5736:6: (enumLiteral_1= 'CENTER' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5736:8: enumLiteral_1= 'CENTER'
                    {
                    enumLiteral_1=(Token)match(input,99,FollowSets000.FOLLOW_99_in_ruleHorizontalAlignment12610); 

                            current = grammarAccess.getHorizontalAlignmentAccess().getCENTEREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getHorizontalAlignmentAccess().getCENTEREnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5742:6: (enumLiteral_2= 'RIGHT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5742:6: (enumLiteral_2= 'RIGHT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/kgraph/text/krendering/parser/antlr/internal/InternalKRendering.g:5742:8: enumLiteral_2= 'RIGHT'
                    {
                    enumLiteral_2=(Token)match(input,102,FollowSets000.FOLLOW_102_in_ruleHorizontalAlignment12627); 

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
        public static final BitSet FOLLOW_12_in_ruleKRenderingLibrary143 = new BitSet(new long[]{0x00000025DDC8C000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRenderingLibrary165 = new BitSet(new long[]{0x00000025DDC8E000L});
        public static final BitSet FOLLOW_13_in_ruleKRenderingLibrary179 = new BitSet(new long[]{0x00000025DDC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRenderingLibrary202 = new BitSet(new long[]{0x00000025DDC8E000L});
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
        public static final BitSet FOLLOW_ruleKAreaPlacementData_in_ruleKPlacementData781 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPointPlacementData_in_ruleKPlacementData808 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStyle_in_entryRuleKStyle843 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKStyle853 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineWidth_in_ruleKStyle900 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForeground_in_ruleKStyle927 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackground_in_ruleKStyle954 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVisibility_in_ruleKStyle981 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineStyle_in_ruleKStyle1008 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineCapStyle_in_ruleKStyle1035 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRotation_in_ruleKStyle1062 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontBold_in_ruleKStyle1089 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontItalic_in_ruleKStyle1116 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontName_in_ruleKStyle1143 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontSize_in_ruleKStyle1170 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVerticalAlignment_in_ruleKStyle1197 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKHorizontalAlignment_in_ruleKStyle1224 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPlacement_in_entryRuleKPlacement1259 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPlacement1269 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacement_in_ruleKPlacement1315 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKXPosition_in_entryRuleKXPosition1349 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKXPosition1359 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLeftPosition_in_ruleKXPosition1406 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRightPosition_in_ruleKXPosition1433 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKYPosition_in_entryRuleKYPosition1468 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKYPosition1478 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKTopPosition_in_ruleKYPosition1525 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBottomPosition_in_ruleKYPosition1552 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRenderingRef_in_entryRuleKRenderingRef1587 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRenderingRef1597 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_15_in_ruleKRenderingRef1643 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKRenderingRef1666 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKRenderingRef1679 = new BitSet(new long[]{0x0000000000054000L});
        public static final BitSet FOLLOW_16_in_ruleKRenderingRef1692 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_17_in_ruleKRenderingRef1705 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRenderingRef1728 = new BitSet(new long[]{0x0300000000066000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_13_in_ruleKRenderingRef1742 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRenderingRef1765 = new BitSet(new long[]{0x0300000000066000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_18_in_ruleKRenderingRef1782 = new BitSet(new long[]{0x0060404000020000L});
        public static final BitSet FOLLOW_17_in_ruleKRenderingRef1795 = new BitSet(new long[]{0x0060404000020000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRenderingRef1818 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKRenderingRef1832 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKEllipse_in_entryRuleKEllipse1870 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKEllipse1880 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_19_in_ruleKEllipse1926 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKEllipse1939 = new BitSet(new long[]{0x0000000000354000L});
        public static final BitSet FOLLOW_16_in_ruleKEllipse1952 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_17_in_ruleKEllipse1965 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKEllipse1988 = new BitSet(new long[]{0x0300000000366000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_13_in_ruleKEllipse2002 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKEllipse2025 = new BitSet(new long[]{0x0300000000366000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_18_in_ruleKEllipse2042 = new BitSet(new long[]{0x0060404000020000L});
        public static final BitSet FOLLOW_17_in_ruleKEllipse2055 = new BitSet(new long[]{0x0060404000020000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKEllipse2078 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKEllipse2093 = new BitSet(new long[]{0x0000000000020000L,0x0000000000040000L});
        public static final BitSet FOLLOW_17_in_ruleKEllipse2106 = new BitSet(new long[]{0x0000000000020000L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKEllipse2129 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKEllipse2144 = new BitSet(new long[]{0x00000025DDCA8000L});
        public static final BitSet FOLLOW_17_in_ruleKEllipse2157 = new BitSet(new long[]{0x00000025DDC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKEllipse2180 = new BitSet(new long[]{0x00000025DDC8E000L});
        public static final BitSet FOLLOW_13_in_ruleKEllipse2194 = new BitSet(new long[]{0x00000025DDC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKEllipse2217 = new BitSet(new long[]{0x00000025DDC8E000L});
        public static final BitSet FOLLOW_14_in_ruleKEllipse2233 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRectangle_in_entryRuleKRectangle2271 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRectangle2281 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_ruleKRectangle2327 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKRectangle2340 = new BitSet(new long[]{0x0000000000354000L});
        public static final BitSet FOLLOW_16_in_ruleKRectangle2353 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_17_in_ruleKRectangle2366 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRectangle2389 = new BitSet(new long[]{0x0300000000366000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_13_in_ruleKRectangle2403 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRectangle2426 = new BitSet(new long[]{0x0300000000366000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_18_in_ruleKRectangle2443 = new BitSet(new long[]{0x0060404000020000L});
        public static final BitSet FOLLOW_17_in_ruleKRectangle2456 = new BitSet(new long[]{0x0060404000020000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRectangle2479 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKRectangle2494 = new BitSet(new long[]{0x0000000000020000L,0x0000000000040000L});
        public static final BitSet FOLLOW_17_in_ruleKRectangle2507 = new BitSet(new long[]{0x0000000000020000L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKRectangle2530 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKRectangle2545 = new BitSet(new long[]{0x00000025DDCA8000L});
        public static final BitSet FOLLOW_17_in_ruleKRectangle2558 = new BitSet(new long[]{0x00000025DDC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRectangle2581 = new BitSet(new long[]{0x00000025DDC8E000L});
        public static final BitSet FOLLOW_13_in_ruleKRectangle2595 = new BitSet(new long[]{0x00000025DDC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRectangle2618 = new BitSet(new long[]{0x00000025DDC8E000L});
        public static final BitSet FOLLOW_14_in_ruleKRectangle2634 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRoundedRectangle_in_entryRuleKRoundedRectangle2672 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRoundedRectangle2682 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_23_in_ruleKRoundedRectangle2728 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRoundedRectangle2749 = new BitSet(new long[]{0x0000000020002010L});
        public static final BitSet FOLLOW_13_in_ruleKRoundedRectangle2762 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRoundedRectangle2785 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKRoundedRectangle2798 = new BitSet(new long[]{0x0000000000354000L});
        public static final BitSet FOLLOW_16_in_ruleKRoundedRectangle2811 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_17_in_ruleKRoundedRectangle2824 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRoundedRectangle2847 = new BitSet(new long[]{0x0300000000366000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_13_in_ruleKRoundedRectangle2861 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRoundedRectangle2884 = new BitSet(new long[]{0x0300000000366000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_18_in_ruleKRoundedRectangle2901 = new BitSet(new long[]{0x0060404000020000L});
        public static final BitSet FOLLOW_17_in_ruleKRoundedRectangle2914 = new BitSet(new long[]{0x0060404000020000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRoundedRectangle2937 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKRoundedRectangle2952 = new BitSet(new long[]{0x0000000000020000L,0x0000000000040000L});
        public static final BitSet FOLLOW_17_in_ruleKRoundedRectangle2965 = new BitSet(new long[]{0x0000000000020000L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKRoundedRectangle2988 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKRoundedRectangle3003 = new BitSet(new long[]{0x00000025DDCA8000L});
        public static final BitSet FOLLOW_17_in_ruleKRoundedRectangle3016 = new BitSet(new long[]{0x00000025DDC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRoundedRectangle3039 = new BitSet(new long[]{0x00000025DDC8E000L});
        public static final BitSet FOLLOW_13_in_ruleKRoundedRectangle3053 = new BitSet(new long[]{0x00000025DDC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRoundedRectangle3076 = new BitSet(new long[]{0x00000025DDC8E000L});
        public static final BitSet FOLLOW_14_in_ruleKRoundedRectangle3092 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolyline_Impl_in_entryRuleKPolyline_Impl3130 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPolyline_Impl3140 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_24_in_ruleKPolyline_Impl3186 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKPolyline_Impl3199 = new BitSet(new long[]{0x0000000002000000L});
        public static final BitSet FOLLOW_25_in_ruleKPolyline_Impl3211 = new BitSet(new long[]{0x1800000000020000L});
        public static final BitSet FOLLOW_17_in_ruleKPolyline_Impl3224 = new BitSet(new long[]{0x1800000000020000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKPolyline_Impl3247 = new BitSet(new long[]{0x1800000000376000L});
        public static final BitSet FOLLOW_13_in_ruleKPolyline_Impl3261 = new BitSet(new long[]{0x1800000000020000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKPolyline_Impl3284 = new BitSet(new long[]{0x1800000000376000L});
        public static final BitSet FOLLOW_16_in_ruleKPolyline_Impl3299 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_17_in_ruleKPolyline_Impl3312 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolyline_Impl3335 = new BitSet(new long[]{0x0300000000366000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_13_in_ruleKPolyline_Impl3349 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolyline_Impl3372 = new BitSet(new long[]{0x0300000000366000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_18_in_ruleKPolyline_Impl3389 = new BitSet(new long[]{0x0060404000020000L});
        public static final BitSet FOLLOW_17_in_ruleKPolyline_Impl3402 = new BitSet(new long[]{0x0060404000020000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKPolyline_Impl3425 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKPolyline_Impl3440 = new BitSet(new long[]{0x0000000000020000L,0x0000000000040000L});
        public static final BitSet FOLLOW_17_in_ruleKPolyline_Impl3453 = new BitSet(new long[]{0x0000000000020000L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKPolyline_Impl3476 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKPolyline_Impl3491 = new BitSet(new long[]{0x00000025DDCA8000L});
        public static final BitSet FOLLOW_17_in_ruleKPolyline_Impl3504 = new BitSet(new long[]{0x00000025DDC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolyline_Impl3527 = new BitSet(new long[]{0x00000025DDC8E000L});
        public static final BitSet FOLLOW_13_in_ruleKPolyline_Impl3541 = new BitSet(new long[]{0x00000025DDC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolyline_Impl3564 = new BitSet(new long[]{0x00000025DDC8E000L});
        public static final BitSet FOLLOW_14_in_ruleKPolyline_Impl3580 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRoundedBendsPolyline_in_entryRuleKRoundedBendsPolyline3618 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRoundedBendsPolyline3628 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_26_in_ruleKRoundedBendsPolyline3674 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRoundedBendsPolyline3695 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKRoundedBendsPolyline3708 = new BitSet(new long[]{0x0000000002000000L});
        public static final BitSet FOLLOW_25_in_ruleKRoundedBendsPolyline3720 = new BitSet(new long[]{0x1800000000020000L});
        public static final BitSet FOLLOW_17_in_ruleKRoundedBendsPolyline3733 = new BitSet(new long[]{0x1800000000020000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKRoundedBendsPolyline3756 = new BitSet(new long[]{0x1800000000376000L});
        public static final BitSet FOLLOW_13_in_ruleKRoundedBendsPolyline3770 = new BitSet(new long[]{0x1800000000020000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKRoundedBendsPolyline3793 = new BitSet(new long[]{0x1800000000376000L});
        public static final BitSet FOLLOW_16_in_ruleKRoundedBendsPolyline3808 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_17_in_ruleKRoundedBendsPolyline3821 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRoundedBendsPolyline3844 = new BitSet(new long[]{0x0300000000366000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_13_in_ruleKRoundedBendsPolyline3858 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRoundedBendsPolyline3881 = new BitSet(new long[]{0x0300000000366000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_18_in_ruleKRoundedBendsPolyline3898 = new BitSet(new long[]{0x0060404000020000L});
        public static final BitSet FOLLOW_17_in_ruleKRoundedBendsPolyline3911 = new BitSet(new long[]{0x0060404000020000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRoundedBendsPolyline3934 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKRoundedBendsPolyline3949 = new BitSet(new long[]{0x0000000000020000L,0x0000000000040000L});
        public static final BitSet FOLLOW_17_in_ruleKRoundedBendsPolyline3962 = new BitSet(new long[]{0x0000000000020000L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKRoundedBendsPolyline3985 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKRoundedBendsPolyline4000 = new BitSet(new long[]{0x00000025DDCA8000L});
        public static final BitSet FOLLOW_17_in_ruleKRoundedBendsPolyline4013 = new BitSet(new long[]{0x00000025DDC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRoundedBendsPolyline4036 = new BitSet(new long[]{0x00000025DDC8E000L});
        public static final BitSet FOLLOW_13_in_ruleKRoundedBendsPolyline4050 = new BitSet(new long[]{0x00000025DDC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRoundedBendsPolyline4073 = new BitSet(new long[]{0x00000025DDC8E000L});
        public static final BitSet FOLLOW_14_in_ruleKRoundedBendsPolyline4089 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolygon_in_entryRuleKPolygon4127 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPolygon4137 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_27_in_ruleKPolygon4183 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKPolygon4196 = new BitSet(new long[]{0x0000000000354000L});
        public static final BitSet FOLLOW_16_in_ruleKPolygon4209 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_17_in_ruleKPolygon4222 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolygon4245 = new BitSet(new long[]{0x0300000000366000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_13_in_ruleKPolygon4259 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolygon4282 = new BitSet(new long[]{0x0300000000366000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_18_in_ruleKPolygon4299 = new BitSet(new long[]{0x0060404000020000L});
        public static final BitSet FOLLOW_17_in_ruleKPolygon4312 = new BitSet(new long[]{0x0060404000020000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKPolygon4335 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKPolygon4350 = new BitSet(new long[]{0x0000000000020000L,0x0000000000040000L});
        public static final BitSet FOLLOW_17_in_ruleKPolygon4363 = new BitSet(new long[]{0x0000000000020000L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKPolygon4386 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKPolygon4401 = new BitSet(new long[]{0x00000025DDCA8000L});
        public static final BitSet FOLLOW_17_in_ruleKPolygon4414 = new BitSet(new long[]{0x00000025DDC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolygon4437 = new BitSet(new long[]{0x00000025DDC8E000L});
        public static final BitSet FOLLOW_13_in_ruleKPolygon4451 = new BitSet(new long[]{0x00000025DDC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolygon4474 = new BitSet(new long[]{0x00000025DDC8E000L});
        public static final BitSet FOLLOW_14_in_ruleKPolygon4490 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKImage_in_entryRuleKImage4528 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKImage4538 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_28_in_ruleKImage4584 = new BitSet(new long[]{0x0000000020000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKImage4606 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_29_in_ruleKImage4624 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKImage4646 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKImage4659 = new BitSet(new long[]{0x0000000000354000L});
        public static final BitSet FOLLOW_16_in_ruleKImage4672 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_17_in_ruleKImage4685 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKImage4708 = new BitSet(new long[]{0x0300000000366000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_13_in_ruleKImage4722 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKImage4745 = new BitSet(new long[]{0x0300000000366000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_18_in_ruleKImage4762 = new BitSet(new long[]{0x0060404000020000L});
        public static final BitSet FOLLOW_17_in_ruleKImage4775 = new BitSet(new long[]{0x0060404000020000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKImage4798 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKImage4813 = new BitSet(new long[]{0x0000000000020000L,0x0000000000040000L});
        public static final BitSet FOLLOW_17_in_ruleKImage4826 = new BitSet(new long[]{0x0000000000020000L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKImage4849 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKImage4864 = new BitSet(new long[]{0x00000025DDCA8000L});
        public static final BitSet FOLLOW_17_in_ruleKImage4877 = new BitSet(new long[]{0x00000025DDC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKImage4900 = new BitSet(new long[]{0x00000025DDC8E000L});
        public static final BitSet FOLLOW_13_in_ruleKImage4914 = new BitSet(new long[]{0x00000025DDC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKImage4937 = new BitSet(new long[]{0x00000025DDC8E000L});
        public static final BitSet FOLLOW_14_in_ruleKImage4953 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKArc_in_entryRuleKArc4991 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKArc5001 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_30_in_ruleKArc5047 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKArc5068 = new BitSet(new long[]{0x0000000020002010L});
        public static final BitSet FOLLOW_13_in_ruleKArc5081 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKArc5104 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKArc5117 = new BitSet(new long[]{0x0000000000354000L});
        public static final BitSet FOLLOW_16_in_ruleKArc5130 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_17_in_ruleKArc5143 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKArc5166 = new BitSet(new long[]{0x0300000000366000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_13_in_ruleKArc5180 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKArc5203 = new BitSet(new long[]{0x0300000000366000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_18_in_ruleKArc5220 = new BitSet(new long[]{0x0060404000020000L});
        public static final BitSet FOLLOW_17_in_ruleKArc5233 = new BitSet(new long[]{0x0060404000020000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKArc5256 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKArc5271 = new BitSet(new long[]{0x0000000000020000L,0x0000000000040000L});
        public static final BitSet FOLLOW_17_in_ruleKArc5284 = new BitSet(new long[]{0x0000000000020000L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKArc5307 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKArc5322 = new BitSet(new long[]{0x00000025DDCA8000L});
        public static final BitSet FOLLOW_17_in_ruleKArc5335 = new BitSet(new long[]{0x00000025DDC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKArc5358 = new BitSet(new long[]{0x00000025DDC8E000L});
        public static final BitSet FOLLOW_13_in_ruleKArc5372 = new BitSet(new long[]{0x00000025DDC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKArc5395 = new BitSet(new long[]{0x00000025DDC8E000L});
        public static final BitSet FOLLOW_14_in_ruleKArc5411 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKChildArea_in_entryRuleKChildArea5449 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKChildArea5459 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_31_in_ruleKChildArea5505 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKChildArea5518 = new BitSet(new long[]{0x0000000000054000L});
        public static final BitSet FOLLOW_16_in_ruleKChildArea5531 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_17_in_ruleKChildArea5544 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKChildArea5567 = new BitSet(new long[]{0x0300000000066000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_13_in_ruleKChildArea5581 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKChildArea5604 = new BitSet(new long[]{0x0300000000066000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_18_in_ruleKChildArea5621 = new BitSet(new long[]{0x0060404000020000L});
        public static final BitSet FOLLOW_17_in_ruleKChildArea5634 = new BitSet(new long[]{0x0060404000020000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKChildArea5657 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKChildArea5671 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKText_in_entryRuleKText5709 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKText5719 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_32_in_ruleKText5765 = new BitSet(new long[]{0x0000000000001062L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKText5786 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKText5800 = new BitSet(new long[]{0x0000000200354000L});
        public static final BitSet FOLLOW_16_in_ruleKText5813 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_17_in_ruleKText5826 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKText5849 = new BitSet(new long[]{0x0300000200366000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_13_in_ruleKText5863 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKText5886 = new BitSet(new long[]{0x0300000200366000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_18_in_ruleKText5903 = new BitSet(new long[]{0x0060404000020000L});
        public static final BitSet FOLLOW_17_in_ruleKText5916 = new BitSet(new long[]{0x0060404000020000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKText5939 = new BitSet(new long[]{0x0000000200304000L});
        public static final BitSet FOLLOW_20_in_ruleKText5954 = new BitSet(new long[]{0x0000000000020000L,0x0000000000040000L});
        public static final BitSet FOLLOW_17_in_ruleKText5967 = new BitSet(new long[]{0x0000000000020000L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKText5990 = new BitSet(new long[]{0x0000000200204000L});
        public static final BitSet FOLLOW_21_in_ruleKText6005 = new BitSet(new long[]{0x00000025DDCA8000L});
        public static final BitSet FOLLOW_17_in_ruleKText6018 = new BitSet(new long[]{0x00000025DDC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKText6041 = new BitSet(new long[]{0x00000027DDC8E000L});
        public static final BitSet FOLLOW_13_in_ruleKText6055 = new BitSet(new long[]{0x00000025DDC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKText6078 = new BitSet(new long[]{0x00000027DDC8E000L});
        public static final BitSet FOLLOW_33_in_ruleKText6095 = new BitSet(new long[]{0x0000000000020060L});
        public static final BitSet FOLLOW_17_in_ruleKText6108 = new BitSet(new long[]{0x0000000000020060L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_ruleKText6131 = new BitSet(new long[]{0x0000000000026060L});
        public static final BitSet FOLLOW_13_in_ruleKText6145 = new BitSet(new long[]{0x0000000000020060L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_ruleKText6168 = new BitSet(new long[]{0x0000000000026060L});
        public static final BitSet FOLLOW_14_in_ruleKText6184 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKCustomRendering_in_entryRuleKCustomRendering6222 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKCustomRendering6232 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_34_in_ruleKCustomRendering6278 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKCustomRendering6291 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_35_in_ruleKCustomRendering6303 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKCustomRendering6324 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_36_in_ruleKCustomRendering6336 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKCustomRendering6357 = new BitSet(new long[]{0x0000000000354000L});
        public static final BitSet FOLLOW_16_in_ruleKCustomRendering6370 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_17_in_ruleKCustomRendering6383 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKCustomRendering6406 = new BitSet(new long[]{0x0300000000366000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_13_in_ruleKCustomRendering6420 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKCustomRendering6443 = new BitSet(new long[]{0x0300000000366000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_18_in_ruleKCustomRendering6460 = new BitSet(new long[]{0x0060404000020000L});
        public static final BitSet FOLLOW_17_in_ruleKCustomRendering6473 = new BitSet(new long[]{0x0060404000020000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKCustomRendering6496 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKCustomRendering6511 = new BitSet(new long[]{0x0000000000020000L,0x0000000000040000L});
        public static final BitSet FOLLOW_17_in_ruleKCustomRendering6524 = new BitSet(new long[]{0x0000000000020000L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKCustomRendering6547 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKCustomRendering6562 = new BitSet(new long[]{0x00000025DDCA8000L});
        public static final BitSet FOLLOW_17_in_ruleKCustomRendering6575 = new BitSet(new long[]{0x00000025DDC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKCustomRendering6598 = new BitSet(new long[]{0x00000025DDC8E000L});
        public static final BitSet FOLLOW_13_in_ruleKCustomRendering6612 = new BitSet(new long[]{0x00000025DDC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKCustomRendering6635 = new BitSet(new long[]{0x00000025DDC8E000L});
        public static final BitSet FOLLOW_14_in_ruleKCustomRendering6651 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKSpline_in_entryRuleKSpline6689 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKSpline6699 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_37_in_ruleKSpline6745 = new BitSet(new long[]{0x0000000000001002L});
        public static final BitSet FOLLOW_12_in_ruleKSpline6758 = new BitSet(new long[]{0x0000000000354000L});
        public static final BitSet FOLLOW_16_in_ruleKSpline6771 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_17_in_ruleKSpline6784 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKSpline6807 = new BitSet(new long[]{0x0300000000366000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_13_in_ruleKSpline6821 = new BitSet(new long[]{0x0300000000020000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKSpline6844 = new BitSet(new long[]{0x0300000000366000L,0x000000000003F9C2L});
        public static final BitSet FOLLOW_18_in_ruleKSpline6861 = new BitSet(new long[]{0x0060404000020000L});
        public static final BitSet FOLLOW_17_in_ruleKSpline6874 = new BitSet(new long[]{0x0060404000020000L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKSpline6897 = new BitSet(new long[]{0x0000000000304000L});
        public static final BitSet FOLLOW_20_in_ruleKSpline6912 = new BitSet(new long[]{0x0000000000020000L,0x0000000000040000L});
        public static final BitSet FOLLOW_17_in_ruleKSpline6925 = new BitSet(new long[]{0x0000000000020000L,0x0000000000040000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKSpline6948 = new BitSet(new long[]{0x0000000000204000L});
        public static final BitSet FOLLOW_21_in_ruleKSpline6963 = new BitSet(new long[]{0x00000025DDCA8000L});
        public static final BitSet FOLLOW_17_in_ruleKSpline6976 = new BitSet(new long[]{0x00000025DDC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKSpline6999 = new BitSet(new long[]{0x00000025DDC8E000L});
        public static final BitSet FOLLOW_13_in_ruleKSpline7013 = new BitSet(new long[]{0x00000025DDC88000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKSpline7036 = new BitSet(new long[]{0x00000025DDC8E000L});
        public static final BitSet FOLLOW_14_in_ruleKSpline7052 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDecoratorPlacementData_in_entryRuleKDecoratorPlacementData7090 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKDecoratorPlacementData7100 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_38_in_ruleKDecoratorPlacementData7137 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKDecoratorPlacementData7149 = new BitSet(new long[]{0x0000018000000000L});
        public static final BitSet FOLLOW_39_in_ruleKDecoratorPlacementData7167 = new BitSet(new long[]{0x0000010000000000L});
        public static final BitSet FOLLOW_40_in_ruleKDecoratorPlacementData7193 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7214 = new BitSet(new long[]{0x00003E0000004000L});
        public static final BitSet FOLLOW_41_in_ruleKDecoratorPlacementData7227 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7248 = new BitSet(new long[]{0x00003C0000004000L});
        public static final BitSet FOLLOW_42_in_ruleKDecoratorPlacementData7263 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7284 = new BitSet(new long[]{0x0000380000004000L});
        public static final BitSet FOLLOW_43_in_ruleKDecoratorPlacementData7299 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7320 = new BitSet(new long[]{0x0000300000004000L});
        public static final BitSet FOLLOW_44_in_ruleKDecoratorPlacementData7335 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7356 = new BitSet(new long[]{0x0000200000004000L});
        public static final BitSet FOLLOW_45_in_ruleKDecoratorPlacementData7371 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7392 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKDecoratorPlacementData7406 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacementData_in_entryRuleKGridPlacementData7442 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKGridPlacementData7452 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_46_in_ruleKGridPlacementData7498 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKGridPlacementData7510 = new BitSet(new long[]{0x001F800000004000L});
        public static final BitSet FOLLOW_47_in_ruleKGridPlacementData7523 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData7544 = new BitSet(new long[]{0x001F000000004000L});
        public static final BitSet FOLLOW_48_in_ruleKGridPlacementData7559 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData7580 = new BitSet(new long[]{0x001E000000004000L});
        public static final BitSet FOLLOW_49_in_ruleKGridPlacementData7595 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData7616 = new BitSet(new long[]{0x001C000000004000L});
        public static final BitSet FOLLOW_50_in_ruleKGridPlacementData7631 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData7652 = new BitSet(new long[]{0x0018000000004000L});
        public static final BitSet FOLLOW_51_in_ruleKGridPlacementData7667 = new BitSet(new long[]{0x1800000000020000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKGridPlacementData7688 = new BitSet(new long[]{0x0010000000004000L});
        public static final BitSet FOLLOW_52_in_ruleKGridPlacementData7703 = new BitSet(new long[]{0x1800000000020000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKGridPlacementData7724 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKGridPlacementData7738 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKAreaPlacementData_in_entryRuleKAreaPlacementData7774 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKAreaPlacementData7784 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_53_in_ruleKAreaPlacementData7821 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKAreaPlacementData7833 = new BitSet(new long[]{0x0008000000000000L});
        public static final BitSet FOLLOW_51_in_ruleKAreaPlacementData7845 = new BitSet(new long[]{0x1800000000020000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKAreaPlacementData7866 = new BitSet(new long[]{0x0010000000002000L});
        public static final BitSet FOLLOW_13_in_ruleKAreaPlacementData7879 = new BitSet(new long[]{0x0010000000000000L});
        public static final BitSet FOLLOW_52_in_ruleKAreaPlacementData7893 = new BitSet(new long[]{0x1800000000020000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKAreaPlacementData7914 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKAreaPlacementData7926 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPointPlacementData_in_entryRuleKPointPlacementData7962 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPointPlacementData7972 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_54_in_ruleKPointPlacementData8009 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPointPlacementData8021 = new BitSet(new long[]{0x0080000000000000L});
        public static final BitSet FOLLOW_55_in_ruleKPointPlacementData8033 = new BitSet(new long[]{0x1800000000020000L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKPointPlacementData8054 = new BitSet(new long[]{0x0100000000002000L});
        public static final BitSet FOLLOW_13_in_ruleKPointPlacementData8067 = new BitSet(new long[]{0x0100000000000000L});
        public static final BitSet FOLLOW_56_in_ruleKPointPlacementData8081 = new BitSet(new long[]{0x0000000000000000L,0x0000001C00000000L});
        public static final BitSet FOLLOW_ruleVerticalAlignment_in_ruleKPointPlacementData8102 = new BitSet(new long[]{0x0200000000002000L});
        public static final BitSet FOLLOW_13_in_ruleKPointPlacementData8115 = new BitSet(new long[]{0x0200000000000000L});
        public static final BitSet FOLLOW_57_in_ruleKPointPlacementData8129 = new BitSet(new long[]{0x0000000000000000L,0x0000006800000000L});
        public static final BitSet FOLLOW_ruleHorizontalAlignment_in_ruleKPointPlacementData8150 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_13_in_ruleKPointPlacementData8163 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKPointPlacementData8177 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPosition_in_entryRuleKPosition8213 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPosition8223 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKXPosition_in_ruleKPosition8269 = new BitSet(new long[]{0x0400000000000000L});
        public static final BitSet FOLLOW_58_in_ruleKPosition8281 = new BitSet(new long[]{0x6000000000000000L});
        public static final BitSet FOLLOW_ruleKYPosition_in_ruleKPosition8302 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLeftPosition_in_entryRuleKLeftPosition8338 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLeftPosition8348 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_59_in_ruleKLeftPosition8394 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKLeftPosition8415 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKLeftPosition8436 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRightPosition_in_entryRuleKRightPosition8472 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRightPosition8482 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_60_in_ruleKRightPosition8528 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRightPosition8549 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRightPosition8570 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKTopPosition_in_entryRuleKTopPosition8606 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKTopPosition8616 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_61_in_ruleKTopPosition8662 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKTopPosition8683 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKTopPosition8704 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBottomPosition_in_entryRuleKBottomPosition8740 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKBottomPosition8750 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_62_in_ruleKBottomPosition8796 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKBottomPosition8817 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKBottomPosition8838 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKColor_in_entryRuleKColor8874 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKColor8884 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_63_in_ruleKColor8930 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKColor8951 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKColor8972 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKColor8993 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
        public static final BitSet FOLLOW_64_in_ruleKColor9011 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackground_in_entryRuleKBackground9061 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKBackground9071 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKBackground9108 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKBackground9120 = new BitSet(new long[]{0x8000000000004000L,0x000000000000003CL});
        public static final BitSet FOLLOW_63_in_ruleKBackground9133 = new BitSet(new long[]{0x8000000000000000L});
        public static final BitSet FOLLOW_ruleKColor_in_ruleKBackground9154 = new BitSet(new long[]{0x0000000000004000L,0x000000000000003CL});
        public static final BitSet FOLLOW_66_in_ruleKBackground9169 = new BitSet(new long[]{0x8000000000000000L});
        public static final BitSet FOLLOW_ruleKColor_in_ruleKBackground9190 = new BitSet(new long[]{0x0000000000004000L,0x0000000000000038L});
        public static final BitSet FOLLOW_67_in_ruleKBackground9205 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKBackground9226 = new BitSet(new long[]{0x0000000000004000L,0x0000000000000030L});
        public static final BitSet FOLLOW_68_in_ruleKBackground9241 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKBackground9262 = new BitSet(new long[]{0x0000000000004000L,0x0000000000000020L});
        public static final BitSet FOLLOW_69_in_ruleKBackground9277 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKBackground9298 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKBackground9312 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForeground_in_entryRuleKForeground9348 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKForeground9358 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_70_in_ruleKForeground9395 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKForeground9407 = new BitSet(new long[]{0x8000000000004000L,0x000000000000003CL});
        public static final BitSet FOLLOW_63_in_ruleKForeground9420 = new BitSet(new long[]{0x8000000000000000L});
        public static final BitSet FOLLOW_ruleKColor_in_ruleKForeground9441 = new BitSet(new long[]{0x0000000000004000L,0x000000000000003CL});
        public static final BitSet FOLLOW_66_in_ruleKForeground9456 = new BitSet(new long[]{0x8000000000000000L});
        public static final BitSet FOLLOW_ruleKColor_in_ruleKForeground9477 = new BitSet(new long[]{0x0000000000004000L,0x0000000000000038L});
        public static final BitSet FOLLOW_67_in_ruleKForeground9492 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKForeground9513 = new BitSet(new long[]{0x0000000000004000L,0x0000000000000030L});
        public static final BitSet FOLLOW_68_in_ruleKForeground9528 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKForeground9549 = new BitSet(new long[]{0x0000000000004000L,0x0000000000000020L});
        public static final BitSet FOLLOW_69_in_ruleKForeground9564 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKForeground9585 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKForeground9599 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVisibility_in_entryRuleKVisibility9635 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKVisibility9645 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_71_in_ruleKVisibility9697 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineWidth_in_entryRuleKLineWidth9746 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLineWidth9756 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_72_in_ruleKLineWidth9793 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKLineWidth9814 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000201L});
        public static final BitSet FOLLOW_64_in_ruleKLineWidth9832 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000200L});
        public static final BitSet FOLLOW_73_in_ruleKLineWidth9859 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
        public static final BitSet FOLLOW_74_in_ruleKLineWidth9871 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKLineWidth9892 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineStyle_in_entryRuleKLineStyle9930 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLineStyle9940 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_75_in_ruleKLineStyle9986 = new BitSet(new long[]{0x0000000000000000L,0x000000007C000000L});
        public static final BitSet FOLLOW_ruleLineStyle_in_ruleKLineStyle10007 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
        public static final BitSet FOLLOW_64_in_ruleKLineStyle10025 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineCapStyle_in_entryRuleKLineCapStyle10075 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLineCapStyle10085 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_76_in_ruleKLineCapStyle10131 = new BitSet(new long[]{0x0000000000000000L,0x0000000380000000L});
        public static final BitSet FOLLOW_ruleLineCapStyle_in_ruleKLineCapStyle10152 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRotation_in_entryRuleKRotation10188 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRotation10198 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_77_in_ruleKRotation10244 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRotation10265 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
        public static final BitSet FOLLOW_64_in_ruleKRotation10283 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontBold_in_entryRuleKFontBold10333 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKFontBold10343 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_78_in_ruleKFontBold10395 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
        public static final BitSet FOLLOW_64_in_ruleKFontBold10426 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontItalic_in_entryRuleKFontItalic10476 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKFontItalic10486 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_79_in_ruleKFontItalic10538 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
        public static final BitSet FOLLOW_64_in_ruleKFontItalic10569 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontName_in_entryRuleKFontName10619 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKFontName10629 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_80_in_ruleKFontName10666 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKFontName10687 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
        public static final BitSet FOLLOW_64_in_ruleKFontName10705 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKFontSize_in_entryRuleKFontSize10755 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKFontSize10765 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_81_in_ruleKFontSize10802 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKFontSize10823 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
        public static final BitSet FOLLOW_64_in_ruleKFontSize10841 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVerticalAlignment_in_entryRuleKVerticalAlignment10891 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKVerticalAlignment10901 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_56_in_ruleKVerticalAlignment10947 = new BitSet(new long[]{0x0000000000000000L,0x0000001C00000000L});
        public static final BitSet FOLLOW_ruleVerticalAlignment_in_ruleKVerticalAlignment10968 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
        public static final BitSet FOLLOW_64_in_ruleKVerticalAlignment10986 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKHorizontalAlignment_in_entryRuleKHorizontalAlignment11036 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKHorizontalAlignment11046 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_57_in_ruleKHorizontalAlignment11092 = new BitSet(new long[]{0x0000000000000000L,0x0000006800000000L});
        public static final BitSet FOLLOW_ruleHorizontalAlignment_in_ruleKHorizontalAlignment11113 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000001L});
        public static final BitSet FOLLOW_64_in_ruleKHorizontalAlignment11131 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacement_in_entryRuleKGridPlacement11181 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKGridPlacement11191 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_82_in_ruleKGridPlacement11237 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKGridPlacement11258 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_entryRuleEFloat11295 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEFloat11306 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_29_in_ruleEFloat11345 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat11362 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
        public static final BitSet FOLLOW_83_in_ruleEFloat11381 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat11396 = new BitSet(new long[]{0x0000000000000002L,0x0000000000300000L});
        public static final BitSet FOLLOW_84_in_ruleEFloat11416 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_85_in_ruleEFloat11435 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_29_in_ruleEFloat11450 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat11467 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEInt_in_entryRuleEInt11519 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEInt11530 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_29_in_ruleEInt11569 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEInt11586 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKInsets_in_entryRuleKInsets11633 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKInsets11643 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_86_in_ruleKInsets11689 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKInsets11701 = new BitSet(new long[]{0x7800000000004000L});
        public static final BitSet FOLLOW_61_in_ruleKInsets11714 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets11735 = new BitSet(new long[]{0x5800000000004000L});
        public static final BitSet FOLLOW_62_in_ruleKInsets11750 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets11771 = new BitSet(new long[]{0x1800000000004000L});
        public static final BitSet FOLLOW_59_in_ruleKInsets11786 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets11807 = new BitSet(new long[]{0x1000000000004000L});
        public static final BitSet FOLLOW_60_in_ruleKInsets11822 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets11843 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleKInsets11857 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPoint_in_entryRuleKPoint11895 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPoint11905 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_87_in_ruleKPoint11951 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
        public static final BitSet FOLLOW_88_in_ruleKPoint11964 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKPoint11985 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
        public static final BitSet FOLLOW_89_in_ruleKPoint11999 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKPoint12020 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry12057 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRulePersistentEntry12067 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_rulePersistentEntry12113 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000400L});
        public static final BitSet FOLLOW_74_in_rulePersistentEntry12126 = new BitSet(new long[]{0x0000000000000060L});
        public static final BitSet FOLLOW_ruleEString_in_rulePersistentEntry12147 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_entryRuleEString12186 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEString12197 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleEString12237 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleEString12263 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_90_in_ruleLineStyle12322 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_91_in_ruleLineStyle12339 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_92_in_ruleLineStyle12356 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_93_in_ruleLineStyle12373 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_94_in_ruleLineStyle12390 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_95_in_ruleLineCapStyle12435 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_96_in_ruleLineCapStyle12452 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_97_in_ruleLineCapStyle12469 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_98_in_ruleVerticalAlignment12514 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_99_in_ruleVerticalAlignment12531 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_100_in_ruleVerticalAlignment12548 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_101_in_ruleHorizontalAlignment12593 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_99_in_ruleHorizontalAlignment12610 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_102_in_ruleHorizontalAlignment12627 = new BitSet(new long[]{0x0000000000000002L});
    }


}