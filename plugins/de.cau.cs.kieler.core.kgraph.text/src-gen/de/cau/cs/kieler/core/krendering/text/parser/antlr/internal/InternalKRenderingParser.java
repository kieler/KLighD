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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'KRenderingLibrary'", "'{'", "'renderings'", "','", "'}'", "'KRenderingRef'", "'references'", "'('", "')'", "'rendering'", "'placementData'", "'styles'", "'propagateToChildren'", "'KStyle'", "'KEllipse'", "'children'", "'childPlacement'", "'KRectangle'", "'KRoundedRectangle'", "'cornerWidth'", "'cornerHeight'", "'KPolyline'", "'KPolygon'", "'KImage'", "'bundleName'", "'imagePath'", "'KArc'", "'startAngle'", "'arcAngle'", "'KChildArea'", "'clip'", "'KText'", "'text'", "'KCustomRendering'", "'className'", "'KSpline'", "'relative'", "'KDecoratorPlacementData'", "'location'", "'xOffset'", "'yOffset'", "'KGridPlacementData'", "'widthHint'", "'heightHint'", "'horizontalIndent'", "'verticalIndent'", "'KStackPlacementData'", "'insetRight'", "'insetBottom'", "'insetLeft'", "'insetTop'", "'KDirectPlacementData'", "'topLeft'", "'bottomRight'", "'KPolylinePlacementData'", "'points'", "'-'", "'.'", "'E'", "'e'", "'KPosition'", "'x'", "'y'", "'KLeftPosition'", "'absolute'", "'KRightPosition'", "'KTopPosition'", "'KBottomPosition'", "'KForegroundColor'", "'red'", "'green'", "'blue'", "'KBackgroundColor'", "'KLineWidth'", "'lineWidth'", "'lineVisible'", "'filled'", "'KVisibility'", "'KLineStyle'", "'lineStyle'", "'KVerticalAlignment'", "'verticalAlignment'", "'KHorizontalAlignment'", "'horizontalAlignment'", "'KGridPlacement'", "'numColumns'", "'KStackPlacement'", "'KInsets'", "'top'", "'bottom'", "'left'", "'right'", "'KPoint'", "'SOLID'", "'DASH'", "'DOT'", "'DASHDOT'", "'DASHDOTDOT'", "'TOP'", "'CENTER'", "'BOTTOM'", "'LEFT'", "'RIGHT'"
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
    public static final int RULE_STRING=4;
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:77:1: ruleKRenderingLibrary returns [EObject current=null] : ( () otherlv_1= 'KRenderingLibrary' otherlv_2= '{' (otherlv_3= 'renderings' otherlv_4= '{' ( (lv_renderings_5_0= ruleKRendering ) ) (otherlv_6= ',' ( (lv_renderings_7_0= ruleKRendering ) ) )* otherlv_8= '}' )? otherlv_9= '}' ) ;
    public final EObject ruleKRenderingLibrary() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        EObject lv_renderings_5_0 = null;

        EObject lv_renderings_7_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:80:28: ( ( () otherlv_1= 'KRenderingLibrary' otherlv_2= '{' (otherlv_3= 'renderings' otherlv_4= '{' ( (lv_renderings_5_0= ruleKRendering ) ) (otherlv_6= ',' ( (lv_renderings_7_0= ruleKRendering ) ) )* otherlv_8= '}' )? otherlv_9= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:81:1: ( () otherlv_1= 'KRenderingLibrary' otherlv_2= '{' (otherlv_3= 'renderings' otherlv_4= '{' ( (lv_renderings_5_0= ruleKRendering ) ) (otherlv_6= ',' ( (lv_renderings_7_0= ruleKRendering ) ) )* otherlv_8= '}' )? otherlv_9= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:81:1: ( () otherlv_1= 'KRenderingLibrary' otherlv_2= '{' (otherlv_3= 'renderings' otherlv_4= '{' ( (lv_renderings_5_0= ruleKRendering ) ) (otherlv_6= ',' ( (lv_renderings_7_0= ruleKRendering ) ) )* otherlv_8= '}' )? otherlv_9= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:81:2: () otherlv_1= 'KRenderingLibrary' otherlv_2= '{' (otherlv_3= 'renderings' otherlv_4= '{' ( (lv_renderings_5_0= ruleKRendering ) ) (otherlv_6= ',' ( (lv_renderings_7_0= ruleKRendering ) ) )* otherlv_8= '}' )? otherlv_9= '}'
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
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:95:1: (otherlv_3= 'renderings' otherlv_4= '{' ( (lv_renderings_5_0= ruleKRendering ) ) (otherlv_6= ',' ( (lv_renderings_7_0= ruleKRendering ) ) )* otherlv_8= '}' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==13) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:95:3: otherlv_3= 'renderings' otherlv_4= '{' ( (lv_renderings_5_0= ruleKRendering ) ) (otherlv_6= ',' ( (lv_renderings_7_0= ruleKRendering ) ) )* otherlv_8= '}'
                    {
                    otherlv_3=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKRenderingLibrary156); 

                        	newLeafNode(otherlv_3, grammarAccess.getKRenderingLibraryAccess().getRenderingsKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRenderingLibrary168); 

                        	newLeafNode(otherlv_4, grammarAccess.getKRenderingLibraryAccess().getLeftCurlyBracketKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:103:1: ( (lv_renderings_5_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:104:1: (lv_renderings_5_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:104:1: (lv_renderings_5_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:105:3: lv_renderings_5_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRenderingLibraryAccess().getRenderingsKRenderingParserRuleCall_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRenderingLibrary189);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:121:2: (otherlv_6= ',' ( (lv_renderings_7_0= ruleKRendering ) ) )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==14) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:121:4: otherlv_6= ',' ( (lv_renderings_7_0= ruleKRendering ) )
                    	    {
                    	    otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRenderingLibrary202); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKRenderingLibraryAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:125:1: ( (lv_renderings_7_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:126:1: (lv_renderings_7_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:126:1: (lv_renderings_7_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:127:3: lv_renderings_7_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRenderingLibraryAccess().getRenderingsKRenderingParserRuleCall_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRenderingLibrary223);
                    	    lv_renderings_7_0=ruleKRendering();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getKRenderingLibraryRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"renderings",
                    	            		lv_renderings_7_0, 
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

                    otherlv_8=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKRenderingLibrary237); 

                        	newLeafNode(otherlv_8, grammarAccess.getKRenderingLibraryAccess().getRightCurlyBracketKeyword_3_4());
                        

                    }
                    break;

            }

            otherlv_9=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKRenderingLibrary251); 

                	newLeafNode(otherlv_9, grammarAccess.getKRenderingLibraryAccess().getRightCurlyBracketKeyword_4());
                

            }


            }

             leaveRule(); 
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:159:1: entryRuleKRendering returns [EObject current=null] : iv_ruleKRendering= ruleKRendering EOF ;
    public final EObject entryRuleKRendering() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRendering = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:160:2: (iv_ruleKRendering= ruleKRendering EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:161:2: iv_ruleKRendering= ruleKRendering EOF
            {
             newCompositeNode(grammarAccess.getKRenderingRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_entryRuleKRendering287);
            iv_ruleKRendering=ruleKRendering();

            state._fsp--;

             current =iv_ruleKRendering; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRendering297); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:168:1: ruleKRendering returns [EObject current=null] : (this_KEllipse_0= ruleKEllipse | this_KRectangle_1= ruleKRectangle | this_KRoundedRectangle_2= ruleKRoundedRectangle | this_KPolyline_Impl_3= ruleKPolyline_Impl | this_KPolygon_4= ruleKPolygon | this_KImage_5= ruleKImage | this_KArc_6= ruleKArc | this_KRenderingRef_7= ruleKRenderingRef | this_KChildArea_8= ruleKChildArea | this_KText_9= ruleKText | this_KCustomRendering_10= ruleKCustomRendering | this_KSpline_11= ruleKSpline ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:171:28: ( (this_KEllipse_0= ruleKEllipse | this_KRectangle_1= ruleKRectangle | this_KRoundedRectangle_2= ruleKRoundedRectangle | this_KPolyline_Impl_3= ruleKPolyline_Impl | this_KPolygon_4= ruleKPolygon | this_KImage_5= ruleKImage | this_KArc_6= ruleKArc | this_KRenderingRef_7= ruleKRenderingRef | this_KChildArea_8= ruleKChildArea | this_KText_9= ruleKText | this_KCustomRendering_10= ruleKCustomRendering | this_KSpline_11= ruleKSpline ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:172:1: (this_KEllipse_0= ruleKEllipse | this_KRectangle_1= ruleKRectangle | this_KRoundedRectangle_2= ruleKRoundedRectangle | this_KPolyline_Impl_3= ruleKPolyline_Impl | this_KPolygon_4= ruleKPolygon | this_KImage_5= ruleKImage | this_KArc_6= ruleKArc | this_KRenderingRef_7= ruleKRenderingRef | this_KChildArea_8= ruleKChildArea | this_KText_9= ruleKText | this_KCustomRendering_10= ruleKCustomRendering | this_KSpline_11= ruleKSpline )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:172:1: (this_KEllipse_0= ruleKEllipse | this_KRectangle_1= ruleKRectangle | this_KRoundedRectangle_2= ruleKRoundedRectangle | this_KPolyline_Impl_3= ruleKPolyline_Impl | this_KPolygon_4= ruleKPolygon | this_KImage_5= ruleKImage | this_KArc_6= ruleKArc | this_KRenderingRef_7= ruleKRenderingRef | this_KChildArea_8= ruleKChildArea | this_KText_9= ruleKText | this_KCustomRendering_10= ruleKCustomRendering | this_KSpline_11= ruleKSpline )
            int alt3=12;
            switch ( input.LA(1) ) {
            case 25:
                {
                alt3=1;
                }
                break;
            case 28:
                {
                alt3=2;
                }
                break;
            case 29:
                {
                alt3=3;
                }
                break;
            case 32:
                {
                alt3=4;
                }
                break;
            case 33:
                {
                alt3=5;
                }
                break;
            case 34:
                {
                alt3=6;
                }
                break;
            case 37:
                {
                alt3=7;
                }
                break;
            case 16:
                {
                alt3=8;
                }
                break;
            case 40:
                {
                alt3=9;
                }
                break;
            case 41:
                {
                alt3=10;
                }
                break;
            case 44:
                {
                alt3=11;
                }
                break;
            case 46:
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
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:173:5: this_KEllipse_0= ruleKEllipse
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKEllipseParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKEllipse_in_ruleKRendering344);
                    this_KEllipse_0=ruleKEllipse();

                    state._fsp--;

                     
                            current = this_KEllipse_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:183:5: this_KRectangle_1= ruleKRectangle
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKRectangleParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRectangle_in_ruleKRendering371);
                    this_KRectangle_1=ruleKRectangle();

                    state._fsp--;

                     
                            current = this_KRectangle_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:193:5: this_KRoundedRectangle_2= ruleKRoundedRectangle
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKRoundedRectangleParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRoundedRectangle_in_ruleKRendering398);
                    this_KRoundedRectangle_2=ruleKRoundedRectangle();

                    state._fsp--;

                     
                            current = this_KRoundedRectangle_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:203:5: this_KPolyline_Impl_3= ruleKPolyline_Impl
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKPolyline_ImplParserRuleCall_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKPolyline_Impl_in_ruleKRendering425);
                    this_KPolyline_Impl_3=ruleKPolyline_Impl();

                    state._fsp--;

                     
                            current = this_KPolyline_Impl_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:213:5: this_KPolygon_4= ruleKPolygon
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKPolygonParserRuleCall_4()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKPolygon_in_ruleKRendering452);
                    this_KPolygon_4=ruleKPolygon();

                    state._fsp--;

                     
                            current = this_KPolygon_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:223:5: this_KImage_5= ruleKImage
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKImageParserRuleCall_5()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKImage_in_ruleKRendering479);
                    this_KImage_5=ruleKImage();

                    state._fsp--;

                     
                            current = this_KImage_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:233:5: this_KArc_6= ruleKArc
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKArcParserRuleCall_6()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKArc_in_ruleKRendering506);
                    this_KArc_6=ruleKArc();

                    state._fsp--;

                     
                            current = this_KArc_6; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 8 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:243:5: this_KRenderingRef_7= ruleKRenderingRef
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKRenderingRefParserRuleCall_7()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRenderingRef_in_ruleKRendering533);
                    this_KRenderingRef_7=ruleKRenderingRef();

                    state._fsp--;

                     
                            current = this_KRenderingRef_7; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 9 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:253:5: this_KChildArea_8= ruleKChildArea
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKChildAreaParserRuleCall_8()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKChildArea_in_ruleKRendering560);
                    this_KChildArea_8=ruleKChildArea();

                    state._fsp--;

                     
                            current = this_KChildArea_8; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 10 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:263:5: this_KText_9= ruleKText
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKTextParserRuleCall_9()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKText_in_ruleKRendering587);
                    this_KText_9=ruleKText();

                    state._fsp--;

                     
                            current = this_KText_9; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 11 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:273:5: this_KCustomRendering_10= ruleKCustomRendering
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKCustomRenderingParserRuleCall_10()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKCustomRendering_in_ruleKRendering614);
                    this_KCustomRendering_10=ruleKCustomRendering();

                    state._fsp--;

                     
                            current = this_KCustomRendering_10; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 12 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:283:5: this_KSpline_11= ruleKSpline
                    {
                     
                            newCompositeNode(grammarAccess.getKRenderingAccess().getKSplineParserRuleCall_11()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKSpline_in_ruleKRendering641);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:299:1: entryRuleKPlacementData returns [EObject current=null] : iv_ruleKPlacementData= ruleKPlacementData EOF ;
    public final EObject entryRuleKPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:300:2: (iv_ruleKPlacementData= ruleKPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:301:2: iv_ruleKPlacementData= ruleKPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_entryRuleKPlacementData676);
            iv_ruleKPlacementData=ruleKPlacementData();

            state._fsp--;

             current =iv_ruleKPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPlacementData686); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:308:1: ruleKPlacementData returns [EObject current=null] : (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KStackPlacementData_2= ruleKStackPlacementData | this_KDirectPlacementData_3= ruleKDirectPlacementData | this_KPolylinePlacementData_4= ruleKPolylinePlacementData ) ;
    public final EObject ruleKPlacementData() throws RecognitionException {
        EObject current = null;

        EObject this_KDecoratorPlacementData_0 = null;

        EObject this_KGridPlacementData_1 = null;

        EObject this_KStackPlacementData_2 = null;

        EObject this_KDirectPlacementData_3 = null;

        EObject this_KPolylinePlacementData_4 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:311:28: ( (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KStackPlacementData_2= ruleKStackPlacementData | this_KDirectPlacementData_3= ruleKDirectPlacementData | this_KPolylinePlacementData_4= ruleKPolylinePlacementData ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:312:1: (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KStackPlacementData_2= ruleKStackPlacementData | this_KDirectPlacementData_3= ruleKDirectPlacementData | this_KPolylinePlacementData_4= ruleKPolylinePlacementData )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:312:1: (this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData | this_KGridPlacementData_1= ruleKGridPlacementData | this_KStackPlacementData_2= ruleKStackPlacementData | this_KDirectPlacementData_3= ruleKDirectPlacementData | this_KPolylinePlacementData_4= ruleKPolylinePlacementData )
            int alt4=5;
            switch ( input.LA(1) ) {
            case 47:
                {
                alt4=1;
                }
                break;
            case 52:
                {
                alt4=2;
                }
                break;
            case 57:
                {
                alt4=3;
                }
                break;
            case 62:
                {
                alt4=4;
                }
                break;
            case 65:
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
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:313:5: this_KDecoratorPlacementData_0= ruleKDecoratorPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKDecoratorPlacementDataParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKDecoratorPlacementData_in_ruleKPlacementData733);
                    this_KDecoratorPlacementData_0=ruleKDecoratorPlacementData();

                    state._fsp--;

                     
                            current = this_KDecoratorPlacementData_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:323:5: this_KGridPlacementData_1= ruleKGridPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKGridPlacementDataParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKGridPlacementData_in_ruleKPlacementData760);
                    this_KGridPlacementData_1=ruleKGridPlacementData();

                    state._fsp--;

                     
                            current = this_KGridPlacementData_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:333:5: this_KStackPlacementData_2= ruleKStackPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKStackPlacementDataParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKStackPlacementData_in_ruleKPlacementData787);
                    this_KStackPlacementData_2=ruleKStackPlacementData();

                    state._fsp--;

                     
                            current = this_KStackPlacementData_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:343:5: this_KDirectPlacementData_3= ruleKDirectPlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKDirectPlacementDataParserRuleCall_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKDirectPlacementData_in_ruleKPlacementData814);
                    this_KDirectPlacementData_3=ruleKDirectPlacementData();

                    state._fsp--;

                     
                            current = this_KDirectPlacementData_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:353:5: this_KPolylinePlacementData_4= ruleKPolylinePlacementData
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementDataAccess().getKPolylinePlacementDataParserRuleCall_4()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKPolylinePlacementData_in_ruleKPlacementData841);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:369:1: entryRuleKStyle returns [EObject current=null] : iv_ruleKStyle= ruleKStyle EOF ;
    public final EObject entryRuleKStyle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStyle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:370:2: (iv_ruleKStyle= ruleKStyle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:371:2: iv_ruleKStyle= ruleKStyle EOF
            {
             newCompositeNode(grammarAccess.getKStyleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_entryRuleKStyle876);
            iv_ruleKStyle=ruleKStyle();

            state._fsp--;

             current =iv_ruleKStyle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKStyle886); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:378:1: ruleKStyle returns [EObject current=null] : (this_KStyle_Impl_0= ruleKStyle_Impl | this_KForegroundColor_1= ruleKForegroundColor | this_KBackgroundColor_2= ruleKBackgroundColor | this_KLineWidth_3= ruleKLineWidth | this_KVisibility_4= ruleKVisibility | this_KLineStyle_5= ruleKLineStyle | this_KVerticalAlignment_6= ruleKVerticalAlignment | this_KHorizontalAlignment_7= ruleKHorizontalAlignment ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:381:28: ( (this_KStyle_Impl_0= ruleKStyle_Impl | this_KForegroundColor_1= ruleKForegroundColor | this_KBackgroundColor_2= ruleKBackgroundColor | this_KLineWidth_3= ruleKLineWidth | this_KVisibility_4= ruleKVisibility | this_KLineStyle_5= ruleKLineStyle | this_KVerticalAlignment_6= ruleKVerticalAlignment | this_KHorizontalAlignment_7= ruleKHorizontalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:382:1: (this_KStyle_Impl_0= ruleKStyle_Impl | this_KForegroundColor_1= ruleKForegroundColor | this_KBackgroundColor_2= ruleKBackgroundColor | this_KLineWidth_3= ruleKLineWidth | this_KVisibility_4= ruleKVisibility | this_KLineStyle_5= ruleKLineStyle | this_KVerticalAlignment_6= ruleKVerticalAlignment | this_KHorizontalAlignment_7= ruleKHorizontalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:382:1: (this_KStyle_Impl_0= ruleKStyle_Impl | this_KForegroundColor_1= ruleKForegroundColor | this_KBackgroundColor_2= ruleKBackgroundColor | this_KLineWidth_3= ruleKLineWidth | this_KVisibility_4= ruleKVisibility | this_KLineStyle_5= ruleKLineStyle | this_KVerticalAlignment_6= ruleKVerticalAlignment | this_KHorizontalAlignment_7= ruleKHorizontalAlignment )
            int alt5=8;
            alt5 = dfa5.predict(input);
            switch (alt5) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:383:5: this_KStyle_Impl_0= ruleKStyle_Impl
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKStyle_ImplParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_Impl_in_ruleKStyle933);
                    this_KStyle_Impl_0=ruleKStyle_Impl();

                    state._fsp--;

                     
                            current = this_KStyle_Impl_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:393:5: this_KForegroundColor_1= ruleKForegroundColor
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKForegroundColorParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKForegroundColor_in_ruleKStyle960);
                    this_KForegroundColor_1=ruleKForegroundColor();

                    state._fsp--;

                     
                            current = this_KForegroundColor_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:403:5: this_KBackgroundColor_2= ruleKBackgroundColor
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKBackgroundColorParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKBackgroundColor_in_ruleKStyle987);
                    this_KBackgroundColor_2=ruleKBackgroundColor();

                    state._fsp--;

                     
                            current = this_KBackgroundColor_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:413:5: this_KLineWidth_3= ruleKLineWidth
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKLineWidthParserRuleCall_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKLineWidth_in_ruleKStyle1014);
                    this_KLineWidth_3=ruleKLineWidth();

                    state._fsp--;

                     
                            current = this_KLineWidth_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:423:5: this_KVisibility_4= ruleKVisibility
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKVisibilityParserRuleCall_4()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKVisibility_in_ruleKStyle1041);
                    this_KVisibility_4=ruleKVisibility();

                    state._fsp--;

                     
                            current = this_KVisibility_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:433:5: this_KLineStyle_5= ruleKLineStyle
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKLineStyleParserRuleCall_5()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKLineStyle_in_ruleKStyle1068);
                    this_KLineStyle_5=ruleKLineStyle();

                    state._fsp--;

                     
                            current = this_KLineStyle_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:443:5: this_KVerticalAlignment_6= ruleKVerticalAlignment
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKVerticalAlignmentParserRuleCall_6()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKVerticalAlignment_in_ruleKStyle1095);
                    this_KVerticalAlignment_6=ruleKVerticalAlignment();

                    state._fsp--;

                     
                            current = this_KVerticalAlignment_6; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 8 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:453:5: this_KHorizontalAlignment_7= ruleKHorizontalAlignment
                    {
                     
                            newCompositeNode(grammarAccess.getKStyleAccess().getKHorizontalAlignmentParserRuleCall_7()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKHorizontalAlignment_in_ruleKStyle1122);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:469:1: entryRuleKPlacement returns [EObject current=null] : iv_ruleKPlacement= ruleKPlacement EOF ;
    public final EObject entryRuleKPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPlacement = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:470:2: (iv_ruleKPlacement= ruleKPlacement EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:471:2: iv_ruleKPlacement= ruleKPlacement EOF
            {
             newCompositeNode(grammarAccess.getKPlacementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_entryRuleKPlacement1157);
            iv_ruleKPlacement=ruleKPlacement();

            state._fsp--;

             current =iv_ruleKPlacement; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPlacement1167); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:478:1: ruleKPlacement returns [EObject current=null] : (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement ) ;
    public final EObject ruleKPlacement() throws RecognitionException {
        EObject current = null;

        EObject this_KGridPlacement_0 = null;

        EObject this_KStackPlacement_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:481:28: ( (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:482:1: (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:482:1: (this_KGridPlacement_0= ruleKGridPlacement | this_KStackPlacement_1= ruleKStackPlacement )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==95) ) {
                alt6=1;
            }
            else if ( (LA6_0==97) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:483:5: this_KGridPlacement_0= ruleKGridPlacement
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementAccess().getKGridPlacementParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKGridPlacement_in_ruleKPlacement1214);
                    this_KGridPlacement_0=ruleKGridPlacement();

                    state._fsp--;

                     
                            current = this_KGridPlacement_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:493:5: this_KStackPlacement_1= ruleKStackPlacement
                    {
                     
                            newCompositeNode(grammarAccess.getKPlacementAccess().getKStackPlacementParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKStackPlacement_in_ruleKPlacement1241);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:509:1: entryRuleKXPosition returns [EObject current=null] : iv_ruleKXPosition= ruleKXPosition EOF ;
    public final EObject entryRuleKXPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKXPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:510:2: (iv_ruleKXPosition= ruleKXPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:511:2: iv_ruleKXPosition= ruleKXPosition EOF
            {
             newCompositeNode(grammarAccess.getKXPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKXPosition_in_entryRuleKXPosition1276);
            iv_ruleKXPosition=ruleKXPosition();

            state._fsp--;

             current =iv_ruleKXPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKXPosition1286); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:518:1: ruleKXPosition returns [EObject current=null] : (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition ) ;
    public final EObject ruleKXPosition() throws RecognitionException {
        EObject current = null;

        EObject this_KLeftPosition_0 = null;

        EObject this_KRightPosition_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:521:28: ( (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:522:1: (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:522:1: (this_KLeftPosition_0= ruleKLeftPosition | this_KRightPosition_1= ruleKRightPosition )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==74) ) {
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
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:523:5: this_KLeftPosition_0= ruleKLeftPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKXPositionAccess().getKLeftPositionParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKLeftPosition_in_ruleKXPosition1333);
                    this_KLeftPosition_0=ruleKLeftPosition();

                    state._fsp--;

                     
                            current = this_KLeftPosition_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:533:5: this_KRightPosition_1= ruleKRightPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKXPositionAccess().getKRightPositionParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKRightPosition_in_ruleKXPosition1360);
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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:549:1: entryRuleKYPosition returns [EObject current=null] : iv_ruleKYPosition= ruleKYPosition EOF ;
    public final EObject entryRuleKYPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKYPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:550:2: (iv_ruleKYPosition= ruleKYPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:551:2: iv_ruleKYPosition= ruleKYPosition EOF
            {
             newCompositeNode(grammarAccess.getKYPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKYPosition_in_entryRuleKYPosition1395);
            iv_ruleKYPosition=ruleKYPosition();

            state._fsp--;

             current =iv_ruleKYPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKYPosition1405); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:558:1: ruleKYPosition returns [EObject current=null] : (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition ) ;
    public final EObject ruleKYPosition() throws RecognitionException {
        EObject current = null;

        EObject this_KTopPosition_0 = null;

        EObject this_KBottomPosition_1 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:561:28: ( (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:562:1: (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:562:1: (this_KTopPosition_0= ruleKTopPosition | this_KBottomPosition_1= ruleKBottomPosition )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==77) ) {
                alt8=1;
            }
            else if ( (LA8_0==78) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:563:5: this_KTopPosition_0= ruleKTopPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKYPositionAccess().getKTopPositionParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKTopPosition_in_ruleKYPosition1452);
                    this_KTopPosition_0=ruleKTopPosition();

                    state._fsp--;

                     
                            current = this_KTopPosition_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:573:5: this_KBottomPosition_1= ruleKBottomPosition
                    {
                     
                            newCompositeNode(grammarAccess.getKYPositionAccess().getKBottomPositionParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleKBottomPosition_in_ruleKYPosition1479);
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


    // $ANTLR start "entryRuleEString"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:591:1: entryRuleEString returns [String current=null] : iv_ruleEString= ruleEString EOF ;
    public final String entryRuleEString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEString = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:592:2: (iv_ruleEString= ruleEString EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:593:2: iv_ruleEString= ruleEString EOF
            {
             newCompositeNode(grammarAccess.getEStringRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_entryRuleEString1517);
            iv_ruleEString=ruleEString();

            state._fsp--;

             current =iv_ruleEString.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEString1528); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:600:1: ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleEString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token this_ID_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:603:28: ( (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:604:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:604:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==RULE_STRING) ) {
                alt9=1;
            }
            else if ( (LA9_0==RULE_ID) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:604:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleEString1568); 

                    		current.merge(this_STRING_0);
                        
                     
                        newLeafNode(this_STRING_0, grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:612:10: this_ID_1= RULE_ID
                    {
                    this_ID_1=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleEString1594); 

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


    // $ANTLR start "entryRuleKRenderingRef"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:627:1: entryRuleKRenderingRef returns [EObject current=null] : iv_ruleKRenderingRef= ruleKRenderingRef EOF ;
    public final EObject entryRuleKRenderingRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRenderingRef = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:628:2: (iv_ruleKRenderingRef= ruleKRenderingRef EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:629:2: iv_ruleKRenderingRef= ruleKRenderingRef EOF
            {
             newCompositeNode(grammarAccess.getKRenderingRefRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRenderingRef_in_entryRuleKRenderingRef1639);
            iv_ruleKRenderingRef=ruleKRenderingRef();

            state._fsp--;

             current =iv_ruleKRenderingRef; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRenderingRef1649); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:636:1: ruleKRenderingRef returns [EObject current=null] : (otherlv_0= 'KRenderingRef' otherlv_1= '{' (otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')' )? otherlv_8= 'rendering' ( ( ruleEString ) ) (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}' )? otherlv_18= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:639:28: ( (otherlv_0= 'KRenderingRef' otherlv_1= '{' (otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')' )? otherlv_8= 'rendering' ( ( ruleEString ) ) (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}' )? otherlv_18= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:640:1: (otherlv_0= 'KRenderingRef' otherlv_1= '{' (otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')' )? otherlv_8= 'rendering' ( ( ruleEString ) ) (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}' )? otherlv_18= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:640:1: (otherlv_0= 'KRenderingRef' otherlv_1= '{' (otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')' )? otherlv_8= 'rendering' ( ( ruleEString ) ) (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}' )? otherlv_18= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:640:3: otherlv_0= 'KRenderingRef' otherlv_1= '{' (otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')' )? otherlv_8= 'rendering' ( ( ruleEString ) ) (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )? (otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}' )? otherlv_18= '}'
            {
            otherlv_0=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKRenderingRef1686); 

                	newLeafNode(otherlv_0, grammarAccess.getKRenderingRefAccess().getKRenderingRefKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRenderingRef1698); 

                	newLeafNode(otherlv_1, grammarAccess.getKRenderingRefAccess().getLeftCurlyBracketKeyword_1());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:648:1: (otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==17) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:648:3: otherlv_2= 'references' otherlv_3= '(' ( ( ruleEString ) ) (otherlv_5= ',' ( ( ruleEString ) ) )* otherlv_7= ')'
                    {
                    otherlv_2=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRenderingRef1711); 

                        	newLeafNode(otherlv_2, grammarAccess.getKRenderingRefAccess().getReferencesKeyword_2_0());
                        
                    otherlv_3=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRenderingRef1723); 

                        	newLeafNode(otherlv_3, grammarAccess.getKRenderingRefAccess().getLeftParenthesisKeyword_2_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:656:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:657:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:657:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:658:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKRenderingRefRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getReferencesKRenderingRefCrossReference_2_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKRenderingRef1746);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:671:2: (otherlv_5= ',' ( ( ruleEString ) ) )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==14) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:671:4: otherlv_5= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_5=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRenderingRef1759); 

                    	        	newLeafNode(otherlv_5, grammarAccess.getKRenderingRefAccess().getCommaKeyword_2_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:675:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:676:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:676:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:677:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKRenderingRefRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getReferencesKRenderingRefCrossReference_2_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKRenderingRef1782);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);

                    otherlv_7=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKRenderingRef1796); 

                        	newLeafNode(otherlv_7, grammarAccess.getKRenderingRefAccess().getRightParenthesisKeyword_2_4());
                        

                    }
                    break;

            }

            otherlv_8=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKRenderingRef1810); 

                	newLeafNode(otherlv_8, grammarAccess.getKRenderingRefAccess().getRenderingKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:698:1: ( ( ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:699:1: ( ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:699:1: ( ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:700:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getKRenderingRefRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getRenderingKRenderingCrossReference_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKRenderingRef1833);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:713:2: (otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==21) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:713:4: otherlv_10= 'placementData' ( (lv_placementData_11_0= ruleKPlacementData ) )
                    {
                    otherlv_10=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKRenderingRef1846); 

                        	newLeafNode(otherlv_10, grammarAccess.getKRenderingRefAccess().getPlacementDataKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:717:1: ( (lv_placementData_11_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:718:1: (lv_placementData_11_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:718:1: (lv_placementData_11_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:719:3: lv_placementData_11_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getPlacementDataKPlacementDataParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRenderingRef1867);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:735:4: (otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}' )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==22) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:735:6: otherlv_12= 'styles' otherlv_13= '{' ( (lv_styles_14_0= ruleKStyle ) ) (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )* otherlv_17= '}'
                    {
                    otherlv_12=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleKRenderingRef1882); 

                        	newLeafNode(otherlv_12, grammarAccess.getKRenderingRefAccess().getStylesKeyword_6_0());
                        
                    otherlv_13=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRenderingRef1894); 

                        	newLeafNode(otherlv_13, grammarAccess.getKRenderingRefAccess().getLeftCurlyBracketKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:743:1: ( (lv_styles_14_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:744:1: (lv_styles_14_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:744:1: (lv_styles_14_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:745:3: lv_styles_14_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getStylesKStyleParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRenderingRef1915);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:761:2: (otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) ) )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==14) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:761:4: otherlv_15= ',' ( (lv_styles_16_0= ruleKStyle ) )
                    	    {
                    	    otherlv_15=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRenderingRef1928); 

                    	        	newLeafNode(otherlv_15, grammarAccess.getKRenderingRefAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:765:1: ( (lv_styles_16_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:766:1: (lv_styles_16_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:766:1: (lv_styles_16_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:767:3: lv_styles_16_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRenderingRefAccess().getStylesKStyleParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRenderingRef1949);
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
                    	    break loop13;
                        }
                    } while (true);

                    otherlv_17=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKRenderingRef1963); 

                        	newLeafNode(otherlv_17, grammarAccess.getKRenderingRefAccess().getRightCurlyBracketKeyword_6_4());
                        

                    }
                    break;

            }

            otherlv_18=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKRenderingRef1977); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:799:1: entryRuleKStyle_Impl returns [EObject current=null] : iv_ruleKStyle_Impl= ruleKStyle_Impl EOF ;
    public final EObject entryRuleKStyle_Impl() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStyle_Impl = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:800:2: (iv_ruleKStyle_Impl= ruleKStyle_Impl EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:801:2: iv_ruleKStyle_Impl= ruleKStyle_Impl EOF
            {
             newCompositeNode(grammarAccess.getKStyle_ImplRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKStyle_Impl_in_entryRuleKStyle_Impl2013);
            iv_ruleKStyle_Impl=ruleKStyle_Impl();

            state._fsp--;

             current =iv_ruleKStyle_Impl; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKStyle_Impl2023); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:808:1: ruleKStyle_Impl returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KStyle' ) ;
    public final EObject ruleKStyle_Impl() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:811:28: ( ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KStyle' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:812:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KStyle' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:812:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KStyle' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:812:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KStyle'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:812:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:813:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:813:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:814:3: lv_propagateToChildren_0_0= 'propagateToChildren'
            {
            lv_propagateToChildren_0_0=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKStyle_Impl2066); 

                    newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKStyle_ImplAccess().getPropagateToChildrenPropagateToChildrenKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKStyle_ImplRule());
            	        }
                   		setWithLastConsumed(current, "propagateToChildren", true, "propagateToChildren");
            	    

            }


            }

            otherlv_1=(Token)match(input,24,FollowSets000.FOLLOW_24_in_ruleKStyle_Impl2091); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:839:1: entryRuleKEllipse returns [EObject current=null] : iv_ruleKEllipse= ruleKEllipse EOF ;
    public final EObject entryRuleKEllipse() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKEllipse = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:840:2: (iv_ruleKEllipse= ruleKEllipse EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:841:2: iv_ruleKEllipse= ruleKEllipse EOF
            {
             newCompositeNode(grammarAccess.getKEllipseRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKEllipse_in_entryRuleKEllipse2127);
            iv_ruleKEllipse=ruleKEllipse();

            state._fsp--;

             current =iv_ruleKEllipse; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKEllipse2137); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:848:1: ruleKEllipse returns [EObject current=null] : ( () otherlv_1= 'KEllipse' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:851:28: ( ( () otherlv_1= 'KEllipse' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:852:1: ( () otherlv_1= 'KEllipse' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:852:1: ( () otherlv_1= 'KEllipse' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:852:2: () otherlv_1= 'KEllipse' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:852:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:853:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKEllipseAccess().getKEllipseAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleKEllipse2183); 

                	newLeafNode(otherlv_1, grammarAccess.getKEllipseAccess().getKEllipseKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKEllipse2195); 

                	newLeafNode(otherlv_2, grammarAccess.getKEllipseAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:866:1: (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==17) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:866:3: otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')'
                    {
                    otherlv_3=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKEllipse2208); 

                        	newLeafNode(otherlv_3, grammarAccess.getKEllipseAccess().getReferencesKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKEllipse2220); 

                        	newLeafNode(otherlv_4, grammarAccess.getKEllipseAccess().getLeftParenthesisKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:874:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:875:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:875:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:876:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKEllipseRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKEllipseAccess().getReferencesKRenderingRefCrossReference_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKEllipse2243);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:889:2: (otherlv_6= ',' ( ( ruleEString ) ) )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==14) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:889:4: otherlv_6= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKEllipse2256); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKEllipseAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:893:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:894:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:894:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:895:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKEllipseRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKEllipseAccess().getReferencesKRenderingRefCrossReference_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKEllipse2279);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);

                    otherlv_8=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKEllipse2293); 

                        	newLeafNode(otherlv_8, grammarAccess.getKEllipseAccess().getRightParenthesisKeyword_3_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:912:3: (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==21) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:912:5: otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) )
                    {
                    otherlv_9=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKEllipse2308); 

                        	newLeafNode(otherlv_9, grammarAccess.getKEllipseAccess().getPlacementDataKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:916:1: ( (lv_placementData_10_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:917:1: (lv_placementData_10_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:917:1: (lv_placementData_10_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:918:3: lv_placementData_10_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEllipseAccess().getPlacementDataKPlacementDataParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKEllipse2329);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:934:4: (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==22) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:934:6: otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}'
                    {
                    otherlv_11=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleKEllipse2344); 

                        	newLeafNode(otherlv_11, grammarAccess.getKEllipseAccess().getStylesKeyword_5_0());
                        
                    otherlv_12=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKEllipse2356); 

                        	newLeafNode(otherlv_12, grammarAccess.getKEllipseAccess().getLeftCurlyBracketKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:942:1: ( (lv_styles_13_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:943:1: (lv_styles_13_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:943:1: (lv_styles_13_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:944:3: lv_styles_13_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEllipseAccess().getStylesKStyleParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKEllipse2377);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:960:2: (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==14) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:960:4: otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) )
                    	    {
                    	    otherlv_14=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKEllipse2390); 

                    	        	newLeafNode(otherlv_14, grammarAccess.getKEllipseAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:964:1: ( (lv_styles_15_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:965:1: (lv_styles_15_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:965:1: (lv_styles_15_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:966:3: lv_styles_15_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKEllipseAccess().getStylesKStyleParserRuleCall_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKEllipse2411);
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
                    	    break loop18;
                        }
                    } while (true);

                    otherlv_16=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKEllipse2425); 

                        	newLeafNode(otherlv_16, grammarAccess.getKEllipseAccess().getRightCurlyBracketKeyword_5_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:986:3: (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==26) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:986:5: otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}'
                    {
                    otherlv_17=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleKEllipse2440); 

                        	newLeafNode(otherlv_17, grammarAccess.getKEllipseAccess().getChildrenKeyword_6_0());
                        
                    otherlv_18=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKEllipse2452); 

                        	newLeafNode(otherlv_18, grammarAccess.getKEllipseAccess().getLeftCurlyBracketKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:994:1: ( (lv_children_19_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:995:1: (lv_children_19_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:995:1: (lv_children_19_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:996:3: lv_children_19_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEllipseAccess().getChildrenKRenderingParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKEllipse2473);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1012:2: (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )*
                    loop20:
                    do {
                        int alt20=2;
                        int LA20_0 = input.LA(1);

                        if ( (LA20_0==14) ) {
                            alt20=1;
                        }


                        switch (alt20) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1012:4: otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) )
                    	    {
                    	    otherlv_20=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKEllipse2486); 

                    	        	newLeafNode(otherlv_20, grammarAccess.getKEllipseAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1016:1: ( (lv_children_21_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1017:1: (lv_children_21_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1017:1: (lv_children_21_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1018:3: lv_children_21_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKEllipseAccess().getChildrenKRenderingParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKEllipse2507);
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
                    	    break loop20;
                        }
                    } while (true);

                    otherlv_22=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKEllipse2521); 

                        	newLeafNode(otherlv_22, grammarAccess.getKEllipseAccess().getRightCurlyBracketKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1038:3: (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==27) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1038:5: otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) )
                    {
                    otherlv_23=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleKEllipse2536); 

                        	newLeafNode(otherlv_23, grammarAccess.getKEllipseAccess().getChildPlacementKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1042:1: ( (lv_childPlacement_24_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1043:1: (lv_childPlacement_24_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1043:1: (lv_childPlacement_24_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1044:3: lv_childPlacement_24_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKEllipseAccess().getChildPlacementKPlacementParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKEllipse2557);
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

            otherlv_25=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKEllipse2571); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1072:1: entryRuleKRectangle returns [EObject current=null] : iv_ruleKRectangle= ruleKRectangle EOF ;
    public final EObject entryRuleKRectangle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRectangle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1073:2: (iv_ruleKRectangle= ruleKRectangle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1074:2: iv_ruleKRectangle= ruleKRectangle EOF
            {
             newCompositeNode(grammarAccess.getKRectangleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRectangle_in_entryRuleKRectangle2607);
            iv_ruleKRectangle=ruleKRectangle();

            state._fsp--;

             current =iv_ruleKRectangle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRectangle2617); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1081:1: ruleKRectangle returns [EObject current=null] : ( () otherlv_1= 'KRectangle' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1084:28: ( ( () otherlv_1= 'KRectangle' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1085:1: ( () otherlv_1= 'KRectangle' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1085:1: ( () otherlv_1= 'KRectangle' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1085:2: () otherlv_1= 'KRectangle' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1085:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1086:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRectangleAccess().getKRectangleAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleKRectangle2663); 

                	newLeafNode(otherlv_1, grammarAccess.getKRectangleAccess().getKRectangleKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRectangle2675); 

                	newLeafNode(otherlv_2, grammarAccess.getKRectangleAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1099:1: (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==17) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1099:3: otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')'
                    {
                    otherlv_3=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRectangle2688); 

                        	newLeafNode(otherlv_3, grammarAccess.getKRectangleAccess().getReferencesKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRectangle2700); 

                        	newLeafNode(otherlv_4, grammarAccess.getKRectangleAccess().getLeftParenthesisKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1107:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1108:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1108:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1109:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKRectangleRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKRectangleAccess().getReferencesKRenderingRefCrossReference_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKRectangle2723);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1122:2: (otherlv_6= ',' ( ( ruleEString ) ) )*
                    loop23:
                    do {
                        int alt23=2;
                        int LA23_0 = input.LA(1);

                        if ( (LA23_0==14) ) {
                            alt23=1;
                        }


                        switch (alt23) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1122:4: otherlv_6= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRectangle2736); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKRectangleAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1126:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1127:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1127:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1128:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKRectangleRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRectangleAccess().getReferencesKRenderingRefCrossReference_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKRectangle2759);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop23;
                        }
                    } while (true);

                    otherlv_8=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKRectangle2773); 

                        	newLeafNode(otherlv_8, grammarAccess.getKRectangleAccess().getRightParenthesisKeyword_3_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1145:3: (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==21) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1145:5: otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) )
                    {
                    otherlv_9=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKRectangle2788); 

                        	newLeafNode(otherlv_9, grammarAccess.getKRectangleAccess().getPlacementDataKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1149:1: ( (lv_placementData_10_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1150:1: (lv_placementData_10_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1150:1: (lv_placementData_10_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1151:3: lv_placementData_10_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRectangleAccess().getPlacementDataKPlacementDataParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRectangle2809);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1167:4: (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==22) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1167:6: otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}'
                    {
                    otherlv_11=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleKRectangle2824); 

                        	newLeafNode(otherlv_11, grammarAccess.getKRectangleAccess().getStylesKeyword_5_0());
                        
                    otherlv_12=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRectangle2836); 

                        	newLeafNode(otherlv_12, grammarAccess.getKRectangleAccess().getLeftCurlyBracketKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1175:1: ( (lv_styles_13_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1176:1: (lv_styles_13_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1176:1: (lv_styles_13_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1177:3: lv_styles_13_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRectangleAccess().getStylesKStyleParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRectangle2857);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1193:2: (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )*
                    loop26:
                    do {
                        int alt26=2;
                        int LA26_0 = input.LA(1);

                        if ( (LA26_0==14) ) {
                            alt26=1;
                        }


                        switch (alt26) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1193:4: otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) )
                    	    {
                    	    otherlv_14=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRectangle2870); 

                    	        	newLeafNode(otherlv_14, grammarAccess.getKRectangleAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1197:1: ( (lv_styles_15_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1198:1: (lv_styles_15_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1198:1: (lv_styles_15_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1199:3: lv_styles_15_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRectangleAccess().getStylesKStyleParserRuleCall_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRectangle2891);
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
                    	    break loop26;
                        }
                    } while (true);

                    otherlv_16=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKRectangle2905); 

                        	newLeafNode(otherlv_16, grammarAccess.getKRectangleAccess().getRightCurlyBracketKeyword_5_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1219:3: (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==26) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1219:5: otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}'
                    {
                    otherlv_17=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleKRectangle2920); 

                        	newLeafNode(otherlv_17, grammarAccess.getKRectangleAccess().getChildrenKeyword_6_0());
                        
                    otherlv_18=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRectangle2932); 

                        	newLeafNode(otherlv_18, grammarAccess.getKRectangleAccess().getLeftCurlyBracketKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1227:1: ( (lv_children_19_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1228:1: (lv_children_19_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1228:1: (lv_children_19_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1229:3: lv_children_19_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRectangleAccess().getChildrenKRenderingParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRectangle2953);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1245:2: (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )*
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==14) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1245:4: otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) )
                    	    {
                    	    otherlv_20=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRectangle2966); 

                    	        	newLeafNode(otherlv_20, grammarAccess.getKRectangleAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1249:1: ( (lv_children_21_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1250:1: (lv_children_21_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1250:1: (lv_children_21_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1251:3: lv_children_21_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRectangleAccess().getChildrenKRenderingParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRectangle2987);
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
                    	    break loop28;
                        }
                    } while (true);

                    otherlv_22=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKRectangle3001); 

                        	newLeafNode(otherlv_22, grammarAccess.getKRectangleAccess().getRightCurlyBracketKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1271:3: (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==27) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1271:5: otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) )
                    {
                    otherlv_23=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleKRectangle3016); 

                        	newLeafNode(otherlv_23, grammarAccess.getKRectangleAccess().getChildPlacementKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1275:1: ( (lv_childPlacement_24_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1276:1: (lv_childPlacement_24_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1276:1: (lv_childPlacement_24_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1277:3: lv_childPlacement_24_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRectangleAccess().getChildPlacementKPlacementParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKRectangle3037);
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

            otherlv_25=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKRectangle3051); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1305:1: entryRuleKRoundedRectangle returns [EObject current=null] : iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF ;
    public final EObject entryRuleKRoundedRectangle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRoundedRectangle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1306:2: (iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1307:2: iv_ruleKRoundedRectangle= ruleKRoundedRectangle EOF
            {
             newCompositeNode(grammarAccess.getKRoundedRectangleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRoundedRectangle_in_entryRuleKRoundedRectangle3087);
            iv_ruleKRoundedRectangle=ruleKRoundedRectangle();

            state._fsp--;

             current =iv_ruleKRoundedRectangle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRoundedRectangle3097); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1314:1: ruleKRoundedRectangle returns [EObject current=null] : (otherlv_0= 'KRoundedRectangle' otherlv_1= '{' otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1317:28: ( (otherlv_0= 'KRoundedRectangle' otherlv_1= '{' otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1318:1: (otherlv_0= 'KRoundedRectangle' otherlv_1= '{' otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1318:1: (otherlv_0= 'KRoundedRectangle' otherlv_1= '{' otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1318:3: otherlv_0= 'KRoundedRectangle' otherlv_1= '{' otherlv_2= 'cornerWidth' ( (lv_cornerWidth_3_0= ruleEFloat ) ) otherlv_4= 'cornerHeight' ( (lv_cornerHeight_5_0= ruleEFloat ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}'
            {
            otherlv_0=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleKRoundedRectangle3134); 

                	newLeafNode(otherlv_0, grammarAccess.getKRoundedRectangleAccess().getKRoundedRectangleKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRoundedRectangle3146); 

                	newLeafNode(otherlv_1, grammarAccess.getKRoundedRectangleAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,30,FollowSets000.FOLLOW_30_in_ruleKRoundedRectangle3158); 

                	newLeafNode(otherlv_2, grammarAccess.getKRoundedRectangleAccess().getCornerWidthKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1330:1: ( (lv_cornerWidth_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1331:1: (lv_cornerWidth_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1331:1: (lv_cornerWidth_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1332:3: lv_cornerWidth_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getCornerWidthEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRoundedRectangle3179);
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

            otherlv_4=(Token)match(input,31,FollowSets000.FOLLOW_31_in_ruleKRoundedRectangle3191); 

                	newLeafNode(otherlv_4, grammarAccess.getKRoundedRectangleAccess().getCornerHeightKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1352:1: ( (lv_cornerHeight_5_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1353:1: (lv_cornerHeight_5_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1353:1: (lv_cornerHeight_5_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1354:3: lv_cornerHeight_5_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getCornerHeightEFloatParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRoundedRectangle3212);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1370:2: (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==17) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1370:4: otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')'
                    {
                    otherlv_6=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKRoundedRectangle3225); 

                        	newLeafNode(otherlv_6, grammarAccess.getKRoundedRectangleAccess().getReferencesKeyword_6_0());
                        
                    otherlv_7=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKRoundedRectangle3237); 

                        	newLeafNode(otherlv_7, grammarAccess.getKRoundedRectangleAccess().getLeftParenthesisKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1378:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1379:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1379:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1380:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKRoundedRectangleRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getReferencesKRenderingRefCrossReference_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKRoundedRectangle3260);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1393:2: (otherlv_9= ',' ( ( ruleEString ) ) )*
                    loop31:
                    do {
                        int alt31=2;
                        int LA31_0 = input.LA(1);

                        if ( (LA31_0==14) ) {
                            alt31=1;
                        }


                        switch (alt31) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1393:4: otherlv_9= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_9=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRoundedRectangle3273); 

                    	        	newLeafNode(otherlv_9, grammarAccess.getKRoundedRectangleAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1397:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1398:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1398:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1399:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKRoundedRectangleRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getReferencesKRenderingRefCrossReference_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKRoundedRectangle3296);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop31;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKRoundedRectangle3310); 

                        	newLeafNode(otherlv_11, grammarAccess.getKRoundedRectangleAccess().getRightParenthesisKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1416:3: (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==21) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1416:5: otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) )
                    {
                    otherlv_12=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKRoundedRectangle3325); 

                        	newLeafNode(otherlv_12, grammarAccess.getKRoundedRectangleAccess().getPlacementDataKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1420:1: ( (lv_placementData_13_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1421:1: (lv_placementData_13_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1421:1: (lv_placementData_13_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1422:3: lv_placementData_13_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getPlacementDataKPlacementDataParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKRoundedRectangle3346);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1438:4: (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==22) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1438:6: otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}'
                    {
                    otherlv_14=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleKRoundedRectangle3361); 

                        	newLeafNode(otherlv_14, grammarAccess.getKRoundedRectangleAccess().getStylesKeyword_8_0());
                        
                    otherlv_15=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRoundedRectangle3373); 

                        	newLeafNode(otherlv_15, grammarAccess.getKRoundedRectangleAccess().getLeftCurlyBracketKeyword_8_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1446:1: ( (lv_styles_16_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1447:1: (lv_styles_16_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1447:1: (lv_styles_16_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1448:3: lv_styles_16_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getStylesKStyleParserRuleCall_8_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRoundedRectangle3394);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1464:2: (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )*
                    loop34:
                    do {
                        int alt34=2;
                        int LA34_0 = input.LA(1);

                        if ( (LA34_0==14) ) {
                            alt34=1;
                        }


                        switch (alt34) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1464:4: otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) )
                    	    {
                    	    otherlv_17=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRoundedRectangle3407); 

                    	        	newLeafNode(otherlv_17, grammarAccess.getKRoundedRectangleAccess().getCommaKeyword_8_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1468:1: ( (lv_styles_18_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1469:1: (lv_styles_18_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1469:1: (lv_styles_18_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1470:3: lv_styles_18_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getStylesKStyleParserRuleCall_8_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKRoundedRectangle3428);
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
                    	    break loop34;
                        }
                    } while (true);

                    otherlv_19=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKRoundedRectangle3442); 

                        	newLeafNode(otherlv_19, grammarAccess.getKRoundedRectangleAccess().getRightCurlyBracketKeyword_8_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1490:3: (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==26) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1490:5: otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}'
                    {
                    otherlv_20=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleKRoundedRectangle3457); 

                        	newLeafNode(otherlv_20, grammarAccess.getKRoundedRectangleAccess().getChildrenKeyword_9_0());
                        
                    otherlv_21=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRoundedRectangle3469); 

                        	newLeafNode(otherlv_21, grammarAccess.getKRoundedRectangleAccess().getLeftCurlyBracketKeyword_9_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1498:1: ( (lv_children_22_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1499:1: (lv_children_22_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1499:1: (lv_children_22_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1500:3: lv_children_22_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getChildrenKRenderingParserRuleCall_9_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRoundedRectangle3490);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1516:2: (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )*
                    loop36:
                    do {
                        int alt36=2;
                        int LA36_0 = input.LA(1);

                        if ( (LA36_0==14) ) {
                            alt36=1;
                        }


                        switch (alt36) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1516:4: otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) )
                    	    {
                    	    otherlv_23=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKRoundedRectangle3503); 

                    	        	newLeafNode(otherlv_23, grammarAccess.getKRoundedRectangleAccess().getCommaKeyword_9_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1520:1: ( (lv_children_24_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1521:1: (lv_children_24_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1521:1: (lv_children_24_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1522:3: lv_children_24_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getChildrenKRenderingParserRuleCall_9_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKRoundedRectangle3524);
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
                    	    break loop36;
                        }
                    } while (true);

                    otherlv_25=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKRoundedRectangle3538); 

                        	newLeafNode(otherlv_25, grammarAccess.getKRoundedRectangleAccess().getRightCurlyBracketKeyword_9_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1542:3: (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==27) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1542:5: otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) )
                    {
                    otherlv_26=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleKRoundedRectangle3553); 

                        	newLeafNode(otherlv_26, grammarAccess.getKRoundedRectangleAccess().getChildPlacementKeyword_10_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1546:1: ( (lv_childPlacement_27_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1547:1: (lv_childPlacement_27_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1547:1: (lv_childPlacement_27_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1548:3: lv_childPlacement_27_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRoundedRectangleAccess().getChildPlacementKPlacementParserRuleCall_10_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKRoundedRectangle3574);
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

            otherlv_28=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKRoundedRectangle3588); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1576:1: entryRuleKPolyline_Impl returns [EObject current=null] : iv_ruleKPolyline_Impl= ruleKPolyline_Impl EOF ;
    public final EObject entryRuleKPolyline_Impl() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolyline_Impl = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1577:2: (iv_ruleKPolyline_Impl= ruleKPolyline_Impl EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1578:2: iv_ruleKPolyline_Impl= ruleKPolyline_Impl EOF
            {
             newCompositeNode(grammarAccess.getKPolyline_ImplRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPolyline_Impl_in_entryRuleKPolyline_Impl3624);
            iv_ruleKPolyline_Impl=ruleKPolyline_Impl();

            state._fsp--;

             current =iv_ruleKPolyline_Impl; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPolyline_Impl3634); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1585:1: ruleKPolyline_Impl returns [EObject current=null] : ( () otherlv_1= 'KPolyline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1588:28: ( ( () otherlv_1= 'KPolyline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1589:1: ( () otherlv_1= 'KPolyline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1589:1: ( () otherlv_1= 'KPolyline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1589:2: () otherlv_1= 'KPolyline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1589:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1590:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPolyline_ImplAccess().getKPolylineAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleKPolyline_Impl3680); 

                	newLeafNode(otherlv_1, grammarAccess.getKPolyline_ImplAccess().getKPolylineKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolyline_Impl3692); 

                	newLeafNode(otherlv_2, grammarAccess.getKPolyline_ImplAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1603:1: (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==17) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1603:3: otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')'
                    {
                    otherlv_3=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolyline_Impl3705); 

                        	newLeafNode(otherlv_3, grammarAccess.getKPolyline_ImplAccess().getReferencesKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPolyline_Impl3717); 

                        	newLeafNode(otherlv_4, grammarAccess.getKPolyline_ImplAccess().getLeftParenthesisKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1611:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1612:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1612:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1613:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKPolyline_ImplRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getReferencesKRenderingRefCrossReference_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKPolyline_Impl3740);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1626:2: (otherlv_6= ',' ( ( ruleEString ) ) )*
                    loop39:
                    do {
                        int alt39=2;
                        int LA39_0 = input.LA(1);

                        if ( (LA39_0==14) ) {
                            alt39=1;
                        }


                        switch (alt39) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1626:4: otherlv_6= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolyline_Impl3753); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKPolyline_ImplAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1630:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1631:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1631:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1632:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKPolyline_ImplRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getReferencesKRenderingRefCrossReference_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKPolyline_Impl3776);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop39;
                        }
                    } while (true);

                    otherlv_8=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKPolyline_Impl3790); 

                        	newLeafNode(otherlv_8, grammarAccess.getKPolyline_ImplAccess().getRightParenthesisKeyword_3_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1649:3: (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==21) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1649:5: otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) )
                    {
                    otherlv_9=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKPolyline_Impl3805); 

                        	newLeafNode(otherlv_9, grammarAccess.getKPolyline_ImplAccess().getPlacementDataKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1653:1: ( (lv_placementData_10_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1654:1: (lv_placementData_10_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1654:1: (lv_placementData_10_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1655:3: lv_placementData_10_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getPlacementDataKPlacementDataParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKPolyline_Impl3826);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1671:4: (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==22) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1671:6: otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}'
                    {
                    otherlv_11=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleKPolyline_Impl3841); 

                        	newLeafNode(otherlv_11, grammarAccess.getKPolyline_ImplAccess().getStylesKeyword_5_0());
                        
                    otherlv_12=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolyline_Impl3853); 

                        	newLeafNode(otherlv_12, grammarAccess.getKPolyline_ImplAccess().getLeftCurlyBracketKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1679:1: ( (lv_styles_13_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1680:1: (lv_styles_13_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1680:1: (lv_styles_13_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1681:3: lv_styles_13_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getStylesKStyleParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolyline_Impl3874);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1697:2: (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )*
                    loop42:
                    do {
                        int alt42=2;
                        int LA42_0 = input.LA(1);

                        if ( (LA42_0==14) ) {
                            alt42=1;
                        }


                        switch (alt42) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1697:4: otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) )
                    	    {
                    	    otherlv_14=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolyline_Impl3887); 

                    	        	newLeafNode(otherlv_14, grammarAccess.getKPolyline_ImplAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1701:1: ( (lv_styles_15_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1702:1: (lv_styles_15_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1702:1: (lv_styles_15_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1703:3: lv_styles_15_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getStylesKStyleParserRuleCall_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolyline_Impl3908);
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
                    	    break loop42;
                        }
                    } while (true);

                    otherlv_16=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKPolyline_Impl3922); 

                        	newLeafNode(otherlv_16, grammarAccess.getKPolyline_ImplAccess().getRightCurlyBracketKeyword_5_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1723:3: (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==26) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1723:5: otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}'
                    {
                    otherlv_17=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleKPolyline_Impl3937); 

                        	newLeafNode(otherlv_17, grammarAccess.getKPolyline_ImplAccess().getChildrenKeyword_6_0());
                        
                    otherlv_18=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolyline_Impl3949); 

                        	newLeafNode(otherlv_18, grammarAccess.getKPolyline_ImplAccess().getLeftCurlyBracketKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1731:1: ( (lv_children_19_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1732:1: (lv_children_19_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1732:1: (lv_children_19_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1733:3: lv_children_19_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getChildrenKRenderingParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolyline_Impl3970);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1749:2: (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )*
                    loop44:
                    do {
                        int alt44=2;
                        int LA44_0 = input.LA(1);

                        if ( (LA44_0==14) ) {
                            alt44=1;
                        }


                        switch (alt44) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1749:4: otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) )
                    	    {
                    	    otherlv_20=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolyline_Impl3983); 

                    	        	newLeafNode(otherlv_20, grammarAccess.getKPolyline_ImplAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1753:1: ( (lv_children_21_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1754:1: (lv_children_21_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1754:1: (lv_children_21_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1755:3: lv_children_21_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getChildrenKRenderingParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolyline_Impl4004);
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
                    	    break loop44;
                        }
                    } while (true);

                    otherlv_22=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKPolyline_Impl4018); 

                        	newLeafNode(otherlv_22, grammarAccess.getKPolyline_ImplAccess().getRightCurlyBracketKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1775:3: (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==27) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1775:5: otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) )
                    {
                    otherlv_23=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleKPolyline_Impl4033); 

                        	newLeafNode(otherlv_23, grammarAccess.getKPolyline_ImplAccess().getChildPlacementKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1779:1: ( (lv_childPlacement_24_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1780:1: (lv_childPlacement_24_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1780:1: (lv_childPlacement_24_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1781:3: lv_childPlacement_24_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolyline_ImplAccess().getChildPlacementKPlacementParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKPolyline_Impl4054);
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

            otherlv_25=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKPolyline_Impl4068); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1809:1: entryRuleKPolygon returns [EObject current=null] : iv_ruleKPolygon= ruleKPolygon EOF ;
    public final EObject entryRuleKPolygon() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolygon = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1810:2: (iv_ruleKPolygon= ruleKPolygon EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1811:2: iv_ruleKPolygon= ruleKPolygon EOF
            {
             newCompositeNode(grammarAccess.getKPolygonRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPolygon_in_entryRuleKPolygon4104);
            iv_ruleKPolygon=ruleKPolygon();

            state._fsp--;

             current =iv_ruleKPolygon; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPolygon4114); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1818:1: ruleKPolygon returns [EObject current=null] : ( () otherlv_1= 'KPolygon' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1821:28: ( ( () otherlv_1= 'KPolygon' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1822:1: ( () otherlv_1= 'KPolygon' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1822:1: ( () otherlv_1= 'KPolygon' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1822:2: () otherlv_1= 'KPolygon' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1822:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1823:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPolygonAccess().getKPolygonAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,33,FollowSets000.FOLLOW_33_in_ruleKPolygon4160); 

                	newLeafNode(otherlv_1, grammarAccess.getKPolygonAccess().getKPolygonKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolygon4172); 

                	newLeafNode(otherlv_2, grammarAccess.getKPolygonAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1836:1: (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==17) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1836:3: otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')'
                    {
                    otherlv_3=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKPolygon4185); 

                        	newLeafNode(otherlv_3, grammarAccess.getKPolygonAccess().getReferencesKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPolygon4197); 

                        	newLeafNode(otherlv_4, grammarAccess.getKPolygonAccess().getLeftParenthesisKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1844:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1845:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1845:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1846:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKPolygonRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKPolygonAccess().getReferencesKRenderingRefCrossReference_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKPolygon4220);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1859:2: (otherlv_6= ',' ( ( ruleEString ) ) )*
                    loop47:
                    do {
                        int alt47=2;
                        int LA47_0 = input.LA(1);

                        if ( (LA47_0==14) ) {
                            alt47=1;
                        }


                        switch (alt47) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1859:4: otherlv_6= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolygon4233); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKPolygonAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1863:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1864:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1864:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1865:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKPolygonRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPolygonAccess().getReferencesKRenderingRefCrossReference_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKPolygon4256);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop47;
                        }
                    } while (true);

                    otherlv_8=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKPolygon4270); 

                        	newLeafNode(otherlv_8, grammarAccess.getKPolygonAccess().getRightParenthesisKeyword_3_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1882:3: (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==21) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1882:5: otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) )
                    {
                    otherlv_9=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKPolygon4285); 

                        	newLeafNode(otherlv_9, grammarAccess.getKPolygonAccess().getPlacementDataKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1886:1: ( (lv_placementData_10_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1887:1: (lv_placementData_10_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1887:1: (lv_placementData_10_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1888:3: lv_placementData_10_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolygonAccess().getPlacementDataKPlacementDataParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKPolygon4306);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1904:4: (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==22) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1904:6: otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}'
                    {
                    otherlv_11=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleKPolygon4321); 

                        	newLeafNode(otherlv_11, grammarAccess.getKPolygonAccess().getStylesKeyword_5_0());
                        
                    otherlv_12=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolygon4333); 

                        	newLeafNode(otherlv_12, grammarAccess.getKPolygonAccess().getLeftCurlyBracketKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1912:1: ( (lv_styles_13_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1913:1: (lv_styles_13_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1913:1: (lv_styles_13_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1914:3: lv_styles_13_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolygonAccess().getStylesKStyleParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolygon4354);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1930:2: (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )*
                    loop50:
                    do {
                        int alt50=2;
                        int LA50_0 = input.LA(1);

                        if ( (LA50_0==14) ) {
                            alt50=1;
                        }


                        switch (alt50) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1930:4: otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) )
                    	    {
                    	    otherlv_14=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolygon4367); 

                    	        	newLeafNode(otherlv_14, grammarAccess.getKPolygonAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1934:1: ( (lv_styles_15_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1935:1: (lv_styles_15_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1935:1: (lv_styles_15_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1936:3: lv_styles_15_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPolygonAccess().getStylesKStyleParserRuleCall_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKPolygon4388);
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
                    	    break loop50;
                        }
                    } while (true);

                    otherlv_16=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKPolygon4402); 

                        	newLeafNode(otherlv_16, grammarAccess.getKPolygonAccess().getRightCurlyBracketKeyword_5_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1956:3: (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==26) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1956:5: otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}'
                    {
                    otherlv_17=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleKPolygon4417); 

                        	newLeafNode(otherlv_17, grammarAccess.getKPolygonAccess().getChildrenKeyword_6_0());
                        
                    otherlv_18=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolygon4429); 

                        	newLeafNode(otherlv_18, grammarAccess.getKPolygonAccess().getLeftCurlyBracketKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1964:1: ( (lv_children_19_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1965:1: (lv_children_19_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1965:1: (lv_children_19_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1966:3: lv_children_19_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolygonAccess().getChildrenKRenderingParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolygon4450);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1982:2: (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )*
                    loop52:
                    do {
                        int alt52=2;
                        int LA52_0 = input.LA(1);

                        if ( (LA52_0==14) ) {
                            alt52=1;
                        }


                        switch (alt52) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1982:4: otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) )
                    	    {
                    	    otherlv_20=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolygon4463); 

                    	        	newLeafNode(otherlv_20, grammarAccess.getKPolygonAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1986:1: ( (lv_children_21_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1987:1: (lv_children_21_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1987:1: (lv_children_21_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:1988:3: lv_children_21_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKPolygonAccess().getChildrenKRenderingParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKPolygon4484);
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
                    	    break loop52;
                        }
                    } while (true);

                    otherlv_22=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKPolygon4498); 

                        	newLeafNode(otherlv_22, grammarAccess.getKPolygonAccess().getRightCurlyBracketKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2008:3: (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==27) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2008:5: otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) )
                    {
                    otherlv_23=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleKPolygon4513); 

                        	newLeafNode(otherlv_23, grammarAccess.getKPolygonAccess().getChildPlacementKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2012:1: ( (lv_childPlacement_24_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2013:1: (lv_childPlacement_24_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2013:1: (lv_childPlacement_24_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2014:3: lv_childPlacement_24_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPolygonAccess().getChildPlacementKPlacementParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKPolygon4534);
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

            otherlv_25=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKPolygon4548); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2042:1: entryRuleKImage returns [EObject current=null] : iv_ruleKImage= ruleKImage EOF ;
    public final EObject entryRuleKImage() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKImage = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2043:2: (iv_ruleKImage= ruleKImage EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2044:2: iv_ruleKImage= ruleKImage EOF
            {
             newCompositeNode(grammarAccess.getKImageRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKImage_in_entryRuleKImage4584);
            iv_ruleKImage=ruleKImage();

            state._fsp--;

             current =iv_ruleKImage; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKImage4594); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2051:1: ruleKImage returns [EObject current=null] : (otherlv_0= 'KImage' otherlv_1= '{' otherlv_2= 'bundleName' ( (lv_bundleName_3_0= ruleEString ) ) otherlv_4= 'imagePath' ( (lv_imagePath_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2054:28: ( (otherlv_0= 'KImage' otherlv_1= '{' otherlv_2= 'bundleName' ( (lv_bundleName_3_0= ruleEString ) ) otherlv_4= 'imagePath' ( (lv_imagePath_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2055:1: (otherlv_0= 'KImage' otherlv_1= '{' otherlv_2= 'bundleName' ( (lv_bundleName_3_0= ruleEString ) ) otherlv_4= 'imagePath' ( (lv_imagePath_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2055:1: (otherlv_0= 'KImage' otherlv_1= '{' otherlv_2= 'bundleName' ( (lv_bundleName_3_0= ruleEString ) ) otherlv_4= 'imagePath' ( (lv_imagePath_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2055:3: otherlv_0= 'KImage' otherlv_1= '{' otherlv_2= 'bundleName' ( (lv_bundleName_3_0= ruleEString ) ) otherlv_4= 'imagePath' ( (lv_imagePath_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}'
            {
            otherlv_0=(Token)match(input,34,FollowSets000.FOLLOW_34_in_ruleKImage4631); 

                	newLeafNode(otherlv_0, grammarAccess.getKImageAccess().getKImageKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKImage4643); 

                	newLeafNode(otherlv_1, grammarAccess.getKImageAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKImage4655); 

                	newLeafNode(otherlv_2, grammarAccess.getKImageAccess().getBundleNameKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2067:1: ( (lv_bundleName_3_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2068:1: (lv_bundleName_3_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2068:1: (lv_bundleName_3_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2069:3: lv_bundleName_3_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKImageAccess().getBundleNameEStringParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKImage4676);
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

            otherlv_4=(Token)match(input,36,FollowSets000.FOLLOW_36_in_ruleKImage4688); 

                	newLeafNode(otherlv_4, grammarAccess.getKImageAccess().getImagePathKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2089:1: ( (lv_imagePath_5_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2090:1: (lv_imagePath_5_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2090:1: (lv_imagePath_5_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2091:3: lv_imagePath_5_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKImageAccess().getImagePathEStringParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKImage4709);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2107:2: (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==17) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2107:4: otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')'
                    {
                    otherlv_6=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKImage4722); 

                        	newLeafNode(otherlv_6, grammarAccess.getKImageAccess().getReferencesKeyword_6_0());
                        
                    otherlv_7=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKImage4734); 

                        	newLeafNode(otherlv_7, grammarAccess.getKImageAccess().getLeftParenthesisKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2115:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2116:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2116:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2117:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKImageRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKImageAccess().getReferencesKRenderingRefCrossReference_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKImage4757);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2130:2: (otherlv_9= ',' ( ( ruleEString ) ) )*
                    loop55:
                    do {
                        int alt55=2;
                        int LA55_0 = input.LA(1);

                        if ( (LA55_0==14) ) {
                            alt55=1;
                        }


                        switch (alt55) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2130:4: otherlv_9= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_9=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKImage4770); 

                    	        	newLeafNode(otherlv_9, grammarAccess.getKImageAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2134:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2135:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2135:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2136:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKImageRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKImageAccess().getReferencesKRenderingRefCrossReference_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKImage4793);
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

                    otherlv_11=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKImage4807); 

                        	newLeafNode(otherlv_11, grammarAccess.getKImageAccess().getRightParenthesisKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2153:3: (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==21) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2153:5: otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) )
                    {
                    otherlv_12=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKImage4822); 

                        	newLeafNode(otherlv_12, grammarAccess.getKImageAccess().getPlacementDataKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2157:1: ( (lv_placementData_13_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2158:1: (lv_placementData_13_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2158:1: (lv_placementData_13_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2159:3: lv_placementData_13_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKImageAccess().getPlacementDataKPlacementDataParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKImage4843);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2175:4: (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==22) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2175:6: otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}'
                    {
                    otherlv_14=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleKImage4858); 

                        	newLeafNode(otherlv_14, grammarAccess.getKImageAccess().getStylesKeyword_8_0());
                        
                    otherlv_15=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKImage4870); 

                        	newLeafNode(otherlv_15, grammarAccess.getKImageAccess().getLeftCurlyBracketKeyword_8_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2183:1: ( (lv_styles_16_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2184:1: (lv_styles_16_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2184:1: (lv_styles_16_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2185:3: lv_styles_16_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKImageAccess().getStylesKStyleParserRuleCall_8_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKImage4891);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2201:2: (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )*
                    loop58:
                    do {
                        int alt58=2;
                        int LA58_0 = input.LA(1);

                        if ( (LA58_0==14) ) {
                            alt58=1;
                        }


                        switch (alt58) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2201:4: otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) )
                    	    {
                    	    otherlv_17=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKImage4904); 

                    	        	newLeafNode(otherlv_17, grammarAccess.getKImageAccess().getCommaKeyword_8_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2205:1: ( (lv_styles_18_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2206:1: (lv_styles_18_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2206:1: (lv_styles_18_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2207:3: lv_styles_18_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKImageAccess().getStylesKStyleParserRuleCall_8_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKImage4925);
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
                    	    break loop58;
                        }
                    } while (true);

                    otherlv_19=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKImage4939); 

                        	newLeafNode(otherlv_19, grammarAccess.getKImageAccess().getRightCurlyBracketKeyword_8_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2227:3: (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==26) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2227:5: otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}'
                    {
                    otherlv_20=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleKImage4954); 

                        	newLeafNode(otherlv_20, grammarAccess.getKImageAccess().getChildrenKeyword_9_0());
                        
                    otherlv_21=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKImage4966); 

                        	newLeafNode(otherlv_21, grammarAccess.getKImageAccess().getLeftCurlyBracketKeyword_9_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2235:1: ( (lv_children_22_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2236:1: (lv_children_22_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2236:1: (lv_children_22_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2237:3: lv_children_22_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKImageAccess().getChildrenKRenderingParserRuleCall_9_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKImage4987);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2253:2: (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )*
                    loop60:
                    do {
                        int alt60=2;
                        int LA60_0 = input.LA(1);

                        if ( (LA60_0==14) ) {
                            alt60=1;
                        }


                        switch (alt60) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2253:4: otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) )
                    	    {
                    	    otherlv_23=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKImage5000); 

                    	        	newLeafNode(otherlv_23, grammarAccess.getKImageAccess().getCommaKeyword_9_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2257:1: ( (lv_children_24_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2258:1: (lv_children_24_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2258:1: (lv_children_24_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2259:3: lv_children_24_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKImageAccess().getChildrenKRenderingParserRuleCall_9_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKImage5021);
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
                    	    break loop60;
                        }
                    } while (true);

                    otherlv_25=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKImage5035); 

                        	newLeafNode(otherlv_25, grammarAccess.getKImageAccess().getRightCurlyBracketKeyword_9_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2279:3: (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==27) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2279:5: otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) )
                    {
                    otherlv_26=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleKImage5050); 

                        	newLeafNode(otherlv_26, grammarAccess.getKImageAccess().getChildPlacementKeyword_10_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2283:1: ( (lv_childPlacement_27_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2284:1: (lv_childPlacement_27_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2284:1: (lv_childPlacement_27_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2285:3: lv_childPlacement_27_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKImageAccess().getChildPlacementKPlacementParserRuleCall_10_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKImage5071);
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

            otherlv_28=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKImage5085); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2313:1: entryRuleKArc returns [EObject current=null] : iv_ruleKArc= ruleKArc EOF ;
    public final EObject entryRuleKArc() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKArc = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2314:2: (iv_ruleKArc= ruleKArc EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2315:2: iv_ruleKArc= ruleKArc EOF
            {
             newCompositeNode(grammarAccess.getKArcRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKArc_in_entryRuleKArc5121);
            iv_ruleKArc=ruleKArc();

            state._fsp--;

             current =iv_ruleKArc; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKArc5131); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2322:1: ruleKArc returns [EObject current=null] : ( () otherlv_1= 'KArc' otherlv_2= '{' (otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) ) )? (otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) ) )? (otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}' )? (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}' )? (otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) ) )? otherlv_29= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2325:28: ( ( () otherlv_1= 'KArc' otherlv_2= '{' (otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) ) )? (otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) ) )? (otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}' )? (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}' )? (otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) ) )? otherlv_29= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2326:1: ( () otherlv_1= 'KArc' otherlv_2= '{' (otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) ) )? (otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) ) )? (otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}' )? (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}' )? (otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) ) )? otherlv_29= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2326:1: ( () otherlv_1= 'KArc' otherlv_2= '{' (otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) ) )? (otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) ) )? (otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}' )? (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}' )? (otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) ) )? otherlv_29= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2326:2: () otherlv_1= 'KArc' otherlv_2= '{' (otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) ) )? (otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) ) )? (otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )? (otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) ) )? (otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}' )? (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}' )? (otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) ) )? otherlv_29= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2326:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2327:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKArcAccess().getKArcAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,37,FollowSets000.FOLLOW_37_in_ruleKArc5177); 

                	newLeafNode(otherlv_1, grammarAccess.getKArcAccess().getKArcKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKArc5189); 

                	newLeafNode(otherlv_2, grammarAccess.getKArcAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2340:1: (otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) ) )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==38) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2340:3: otherlv_3= 'startAngle' ( (lv_startAngle_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,38,FollowSets000.FOLLOW_38_in_ruleKArc5202); 

                        	newLeafNode(otherlv_3, grammarAccess.getKArcAccess().getStartAngleKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2344:1: ( (lv_startAngle_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2345:1: (lv_startAngle_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2345:1: (lv_startAngle_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2346:3: lv_startAngle_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getStartAngleEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKArc5223);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2362:4: (otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) ) )?
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==39) ) {
                alt64=1;
            }
            switch (alt64) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2362:6: otherlv_5= 'arcAngle' ( (lv_arcAngle_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,39,FollowSets000.FOLLOW_39_in_ruleKArc5238); 

                        	newLeafNode(otherlv_5, grammarAccess.getKArcAccess().getArcAngleKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2366:1: ( (lv_arcAngle_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2367:1: (lv_arcAngle_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2367:1: (lv_arcAngle_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2368:3: lv_arcAngle_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getArcAngleEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKArc5259);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2384:4: (otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')' )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==17) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2384:6: otherlv_7= 'references' otherlv_8= '(' ( ( ruleEString ) ) (otherlv_10= ',' ( ( ruleEString ) ) )* otherlv_12= ')'
                    {
                    otherlv_7=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKArc5274); 

                        	newLeafNode(otherlv_7, grammarAccess.getKArcAccess().getReferencesKeyword_5_0());
                        
                    otherlv_8=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKArc5286); 

                        	newLeafNode(otherlv_8, grammarAccess.getKArcAccess().getLeftParenthesisKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2392:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2393:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2393:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2394:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKArcRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getReferencesKRenderingRefCrossReference_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKArc5309);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2407:2: (otherlv_10= ',' ( ( ruleEString ) ) )*
                    loop65:
                    do {
                        int alt65=2;
                        int LA65_0 = input.LA(1);

                        if ( (LA65_0==14) ) {
                            alt65=1;
                        }


                        switch (alt65) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2407:4: otherlv_10= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_10=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKArc5322); 

                    	        	newLeafNode(otherlv_10, grammarAccess.getKArcAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2411:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2412:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2412:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2413:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKArcRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKArcAccess().getReferencesKRenderingRefCrossReference_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKArc5345);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop65;
                        }
                    } while (true);

                    otherlv_12=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKArc5359); 

                        	newLeafNode(otherlv_12, grammarAccess.getKArcAccess().getRightParenthesisKeyword_5_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2430:3: (otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) ) )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==21) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2430:5: otherlv_13= 'placementData' ( (lv_placementData_14_0= ruleKPlacementData ) )
                    {
                    otherlv_13=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKArc5374); 

                        	newLeafNode(otherlv_13, grammarAccess.getKArcAccess().getPlacementDataKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2434:1: ( (lv_placementData_14_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2435:1: (lv_placementData_14_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2435:1: (lv_placementData_14_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2436:3: lv_placementData_14_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getPlacementDataKPlacementDataParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKArc5395);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2452:4: (otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}' )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==22) ) {
                alt69=1;
            }
            switch (alt69) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2452:6: otherlv_15= 'styles' otherlv_16= '{' ( (lv_styles_17_0= ruleKStyle ) ) (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )* otherlv_20= '}'
                    {
                    otherlv_15=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleKArc5410); 

                        	newLeafNode(otherlv_15, grammarAccess.getKArcAccess().getStylesKeyword_7_0());
                        
                    otherlv_16=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKArc5422); 

                        	newLeafNode(otherlv_16, grammarAccess.getKArcAccess().getLeftCurlyBracketKeyword_7_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2460:1: ( (lv_styles_17_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2461:1: (lv_styles_17_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2461:1: (lv_styles_17_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2462:3: lv_styles_17_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getStylesKStyleParserRuleCall_7_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKArc5443);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2478:2: (otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) ) )*
                    loop68:
                    do {
                        int alt68=2;
                        int LA68_0 = input.LA(1);

                        if ( (LA68_0==14) ) {
                            alt68=1;
                        }


                        switch (alt68) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2478:4: otherlv_18= ',' ( (lv_styles_19_0= ruleKStyle ) )
                    	    {
                    	    otherlv_18=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKArc5456); 

                    	        	newLeafNode(otherlv_18, grammarAccess.getKArcAccess().getCommaKeyword_7_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2482:1: ( (lv_styles_19_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2483:1: (lv_styles_19_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2483:1: (lv_styles_19_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2484:3: lv_styles_19_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKArcAccess().getStylesKStyleParserRuleCall_7_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKArc5477);
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
                    	    break loop68;
                        }
                    } while (true);

                    otherlv_20=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKArc5491); 

                        	newLeafNode(otherlv_20, grammarAccess.getKArcAccess().getRightCurlyBracketKeyword_7_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2504:3: (otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}' )?
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==26) ) {
                alt71=1;
            }
            switch (alt71) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2504:5: otherlv_21= 'children' otherlv_22= '{' ( (lv_children_23_0= ruleKRendering ) ) (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )* otherlv_26= '}'
                    {
                    otherlv_21=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleKArc5506); 

                        	newLeafNode(otherlv_21, grammarAccess.getKArcAccess().getChildrenKeyword_8_0());
                        
                    otherlv_22=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKArc5518); 

                        	newLeafNode(otherlv_22, grammarAccess.getKArcAccess().getLeftCurlyBracketKeyword_8_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2512:1: ( (lv_children_23_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2513:1: (lv_children_23_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2513:1: (lv_children_23_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2514:3: lv_children_23_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getChildrenKRenderingParserRuleCall_8_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKArc5539);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2530:2: (otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) ) )*
                    loop70:
                    do {
                        int alt70=2;
                        int LA70_0 = input.LA(1);

                        if ( (LA70_0==14) ) {
                            alt70=1;
                        }


                        switch (alt70) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2530:4: otherlv_24= ',' ( (lv_children_25_0= ruleKRendering ) )
                    	    {
                    	    otherlv_24=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKArc5552); 

                    	        	newLeafNode(otherlv_24, grammarAccess.getKArcAccess().getCommaKeyword_8_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2534:1: ( (lv_children_25_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2535:1: (lv_children_25_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2535:1: (lv_children_25_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2536:3: lv_children_25_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKArcAccess().getChildrenKRenderingParserRuleCall_8_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKArc5573);
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
                    	    break loop70;
                        }
                    } while (true);

                    otherlv_26=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKArc5587); 

                        	newLeafNode(otherlv_26, grammarAccess.getKArcAccess().getRightCurlyBracketKeyword_8_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2556:3: (otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) ) )?
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==27) ) {
                alt72=1;
            }
            switch (alt72) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2556:5: otherlv_27= 'childPlacement' ( (lv_childPlacement_28_0= ruleKPlacement ) )
                    {
                    otherlv_27=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleKArc5602); 

                        	newLeafNode(otherlv_27, grammarAccess.getKArcAccess().getChildPlacementKeyword_9_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2560:1: ( (lv_childPlacement_28_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2561:1: (lv_childPlacement_28_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2561:1: (lv_childPlacement_28_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2562:3: lv_childPlacement_28_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKArcAccess().getChildPlacementKPlacementParserRuleCall_9_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKArc5623);
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

            otherlv_29=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKArc5637); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2590:1: entryRuleKChildArea returns [EObject current=null] : iv_ruleKChildArea= ruleKChildArea EOF ;
    public final EObject entryRuleKChildArea() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKChildArea = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2591:2: (iv_ruleKChildArea= ruleKChildArea EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2592:2: iv_ruleKChildArea= ruleKChildArea EOF
            {
             newCompositeNode(grammarAccess.getKChildAreaRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKChildArea_in_entryRuleKChildArea5673);
            iv_ruleKChildArea=ruleKChildArea();

            state._fsp--;

             current =iv_ruleKChildArea; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKChildArea5683); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2599:1: ruleKChildArea returns [EObject current=null] : ( () otherlv_1= 'KChildArea' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? otherlv_17= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2602:28: ( ( () otherlv_1= 'KChildArea' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? otherlv_17= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2603:1: ( () otherlv_1= 'KChildArea' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? otherlv_17= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2603:1: ( () otherlv_1= 'KChildArea' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? otherlv_17= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2603:2: () otherlv_1= 'KChildArea' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? otherlv_17= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2603:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2604:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKChildAreaAccess().getKChildAreaAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,40,FollowSets000.FOLLOW_40_in_ruleKChildArea5729); 

                	newLeafNode(otherlv_1, grammarAccess.getKChildAreaAccess().getKChildAreaKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKChildArea5741); 

                	newLeafNode(otherlv_2, grammarAccess.getKChildAreaAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2617:1: (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==17) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2617:3: otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')'
                    {
                    otherlv_3=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKChildArea5754); 

                        	newLeafNode(otherlv_3, grammarAccess.getKChildAreaAccess().getReferencesKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKChildArea5766); 

                        	newLeafNode(otherlv_4, grammarAccess.getKChildAreaAccess().getLeftParenthesisKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2625:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2626:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2626:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2627:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKChildAreaRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKChildAreaAccess().getReferencesKRenderingRefCrossReference_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKChildArea5789);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2640:2: (otherlv_6= ',' ( ( ruleEString ) ) )*
                    loop73:
                    do {
                        int alt73=2;
                        int LA73_0 = input.LA(1);

                        if ( (LA73_0==14) ) {
                            alt73=1;
                        }


                        switch (alt73) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2640:4: otherlv_6= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKChildArea5802); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKChildAreaAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2644:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2645:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2645:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2646:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKChildAreaRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKChildAreaAccess().getReferencesKRenderingRefCrossReference_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKChildArea5825);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop73;
                        }
                    } while (true);

                    otherlv_8=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKChildArea5839); 

                        	newLeafNode(otherlv_8, grammarAccess.getKChildAreaAccess().getRightParenthesisKeyword_3_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2663:3: (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==21) ) {
                alt75=1;
            }
            switch (alt75) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2663:5: otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) )
                    {
                    otherlv_9=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKChildArea5854); 

                        	newLeafNode(otherlv_9, grammarAccess.getKChildAreaAccess().getPlacementDataKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2667:1: ( (lv_placementData_10_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2668:1: (lv_placementData_10_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2668:1: (lv_placementData_10_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2669:3: lv_placementData_10_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKChildAreaAccess().getPlacementDataKPlacementDataParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKChildArea5875);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2685:4: (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )?
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==22) ) {
                alt77=1;
            }
            switch (alt77) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2685:6: otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}'
                    {
                    otherlv_11=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleKChildArea5890); 

                        	newLeafNode(otherlv_11, grammarAccess.getKChildAreaAccess().getStylesKeyword_5_0());
                        
                    otherlv_12=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKChildArea5902); 

                        	newLeafNode(otherlv_12, grammarAccess.getKChildAreaAccess().getLeftCurlyBracketKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2693:1: ( (lv_styles_13_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2694:1: (lv_styles_13_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2694:1: (lv_styles_13_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2695:3: lv_styles_13_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKChildAreaAccess().getStylesKStyleParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKChildArea5923);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2711:2: (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )*
                    loop76:
                    do {
                        int alt76=2;
                        int LA76_0 = input.LA(1);

                        if ( (LA76_0==14) ) {
                            alt76=1;
                        }


                        switch (alt76) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2711:4: otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) )
                    	    {
                    	    otherlv_14=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKChildArea5936); 

                    	        	newLeafNode(otherlv_14, grammarAccess.getKChildAreaAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2715:1: ( (lv_styles_15_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2716:1: (lv_styles_15_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2716:1: (lv_styles_15_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2717:3: lv_styles_15_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKChildAreaAccess().getStylesKStyleParserRuleCall_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKChildArea5957);
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
                    	    break loop76;
                        }
                    } while (true);

                    otherlv_16=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKChildArea5971); 

                        	newLeafNode(otherlv_16, grammarAccess.getKChildAreaAccess().getRightCurlyBracketKeyword_5_4());
                        

                    }
                    break;

            }

            otherlv_17=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKChildArea5985); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2749:1: entryRuleKText returns [EObject current=null] : iv_ruleKText= ruleKText EOF ;
    public final EObject entryRuleKText() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKText = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2750:2: (iv_ruleKText= ruleKText EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2751:2: iv_ruleKText= ruleKText EOF
            {
             newCompositeNode(grammarAccess.getKTextRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKText_in_entryRuleKText6021);
            iv_ruleKText=ruleKText();

            state._fsp--;

             current =iv_ruleKText; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKText6031); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2758:1: ruleKText returns [EObject current=null] : ( ( (lv_clip_0_0= 'clip' ) ) otherlv_1= 'KText' otherlv_2= '{' (otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) ) )? (otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}' )? (otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}' )? (otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) ) )? otherlv_27= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2761:28: ( ( ( (lv_clip_0_0= 'clip' ) ) otherlv_1= 'KText' otherlv_2= '{' (otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) ) )? (otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}' )? (otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}' )? (otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) ) )? otherlv_27= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2762:1: ( ( (lv_clip_0_0= 'clip' ) ) otherlv_1= 'KText' otherlv_2= '{' (otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) ) )? (otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}' )? (otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}' )? (otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) ) )? otherlv_27= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2762:1: ( ( (lv_clip_0_0= 'clip' ) ) otherlv_1= 'KText' otherlv_2= '{' (otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) ) )? (otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}' )? (otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}' )? (otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) ) )? otherlv_27= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2762:2: ( (lv_clip_0_0= 'clip' ) ) otherlv_1= 'KText' otherlv_2= '{' (otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) ) )? (otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )? (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )? (otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}' )? (otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}' )? (otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) ) )? otherlv_27= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2762:2: ( (lv_clip_0_0= 'clip' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2763:1: (lv_clip_0_0= 'clip' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2763:1: (lv_clip_0_0= 'clip' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2764:3: lv_clip_0_0= 'clip'
            {
            lv_clip_0_0=(Token)match(input,41,FollowSets000.FOLLOW_41_in_ruleKText6074); 

                    newLeafNode(lv_clip_0_0, grammarAccess.getKTextAccess().getClipClipKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKTextRule());
            	        }
                   		setWithLastConsumed(current, "clip", true, "clip");
            	    

            }


            }

            otherlv_1=(Token)match(input,42,FollowSets000.FOLLOW_42_in_ruleKText6099); 

                	newLeafNode(otherlv_1, grammarAccess.getKTextAccess().getKTextKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKText6111); 

                	newLeafNode(otherlv_2, grammarAccess.getKTextAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2785:1: (otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) ) )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==43) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2785:3: otherlv_3= 'text' ( (lv_text_4_0= ruleEString ) )
                    {
                    otherlv_3=(Token)match(input,43,FollowSets000.FOLLOW_43_in_ruleKText6124); 

                        	newLeafNode(otherlv_3, grammarAccess.getKTextAccess().getTextKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2789:1: ( (lv_text_4_0= ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2790:1: (lv_text_4_0= ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2790:1: (lv_text_4_0= ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2791:3: lv_text_4_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getTextEStringParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKText6145);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2807:4: (otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')' )?
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==17) ) {
                alt80=1;
            }
            switch (alt80) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2807:6: otherlv_5= 'references' otherlv_6= '(' ( ( ruleEString ) ) (otherlv_8= ',' ( ( ruleEString ) ) )* otherlv_10= ')'
                    {
                    otherlv_5=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKText6160); 

                        	newLeafNode(otherlv_5, grammarAccess.getKTextAccess().getReferencesKeyword_4_0());
                        
                    otherlv_6=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKText6172); 

                        	newLeafNode(otherlv_6, grammarAccess.getKTextAccess().getLeftParenthesisKeyword_4_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2815:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2816:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2816:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2817:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKTextRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getReferencesKRenderingRefCrossReference_4_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKText6195);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2830:2: (otherlv_8= ',' ( ( ruleEString ) ) )*
                    loop79:
                    do {
                        int alt79=2;
                        int LA79_0 = input.LA(1);

                        if ( (LA79_0==14) ) {
                            alt79=1;
                        }


                        switch (alt79) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2830:4: otherlv_8= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_8=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKText6208); 

                    	        	newLeafNode(otherlv_8, grammarAccess.getKTextAccess().getCommaKeyword_4_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2834:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2835:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2835:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2836:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKTextRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKTextAccess().getReferencesKRenderingRefCrossReference_4_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKText6231);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop79;
                        }
                    } while (true);

                    otherlv_10=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKText6245); 

                        	newLeafNode(otherlv_10, grammarAccess.getKTextAccess().getRightParenthesisKeyword_4_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2853:3: (otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) ) )?
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( (LA81_0==21) ) {
                alt81=1;
            }
            switch (alt81) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2853:5: otherlv_11= 'placementData' ( (lv_placementData_12_0= ruleKPlacementData ) )
                    {
                    otherlv_11=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKText6260); 

                        	newLeafNode(otherlv_11, grammarAccess.getKTextAccess().getPlacementDataKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2857:1: ( (lv_placementData_12_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2858:1: (lv_placementData_12_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2858:1: (lv_placementData_12_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2859:3: lv_placementData_12_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getPlacementDataKPlacementDataParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKText6281);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2875:4: (otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}' )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==22) ) {
                alt83=1;
            }
            switch (alt83) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2875:6: otherlv_13= 'styles' otherlv_14= '{' ( (lv_styles_15_0= ruleKStyle ) ) (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )* otherlv_18= '}'
                    {
                    otherlv_13=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleKText6296); 

                        	newLeafNode(otherlv_13, grammarAccess.getKTextAccess().getStylesKeyword_6_0());
                        
                    otherlv_14=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKText6308); 

                        	newLeafNode(otherlv_14, grammarAccess.getKTextAccess().getLeftCurlyBracketKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2883:1: ( (lv_styles_15_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2884:1: (lv_styles_15_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2884:1: (lv_styles_15_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2885:3: lv_styles_15_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getStylesKStyleParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKText6329);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2901:2: (otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) ) )*
                    loop82:
                    do {
                        int alt82=2;
                        int LA82_0 = input.LA(1);

                        if ( (LA82_0==14) ) {
                            alt82=1;
                        }


                        switch (alt82) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2901:4: otherlv_16= ',' ( (lv_styles_17_0= ruleKStyle ) )
                    	    {
                    	    otherlv_16=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKText6342); 

                    	        	newLeafNode(otherlv_16, grammarAccess.getKTextAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2905:1: ( (lv_styles_17_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2906:1: (lv_styles_17_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2906:1: (lv_styles_17_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2907:3: lv_styles_17_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKTextAccess().getStylesKStyleParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKText6363);
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
                    	    break loop82;
                        }
                    } while (true);

                    otherlv_18=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKText6377); 

                        	newLeafNode(otherlv_18, grammarAccess.getKTextAccess().getRightCurlyBracketKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2927:3: (otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}' )?
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==26) ) {
                alt85=1;
            }
            switch (alt85) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2927:5: otherlv_19= 'children' otherlv_20= '{' ( (lv_children_21_0= ruleKRendering ) ) (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )* otherlv_24= '}'
                    {
                    otherlv_19=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleKText6392); 

                        	newLeafNode(otherlv_19, grammarAccess.getKTextAccess().getChildrenKeyword_7_0());
                        
                    otherlv_20=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKText6404); 

                        	newLeafNode(otherlv_20, grammarAccess.getKTextAccess().getLeftCurlyBracketKeyword_7_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2935:1: ( (lv_children_21_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2936:1: (lv_children_21_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2936:1: (lv_children_21_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2937:3: lv_children_21_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getChildrenKRenderingParserRuleCall_7_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKText6425);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2953:2: (otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) ) )*
                    loop84:
                    do {
                        int alt84=2;
                        int LA84_0 = input.LA(1);

                        if ( (LA84_0==14) ) {
                            alt84=1;
                        }


                        switch (alt84) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2953:4: otherlv_22= ',' ( (lv_children_23_0= ruleKRendering ) )
                    	    {
                    	    otherlv_22=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKText6438); 

                    	        	newLeafNode(otherlv_22, grammarAccess.getKTextAccess().getCommaKeyword_7_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2957:1: ( (lv_children_23_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2958:1: (lv_children_23_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2958:1: (lv_children_23_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2959:3: lv_children_23_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKTextAccess().getChildrenKRenderingParserRuleCall_7_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKText6459);
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
                    	    break loop84;
                        }
                    } while (true);

                    otherlv_24=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKText6473); 

                        	newLeafNode(otherlv_24, grammarAccess.getKTextAccess().getRightCurlyBracketKeyword_7_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2979:3: (otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) ) )?
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==27) ) {
                alt86=1;
            }
            switch (alt86) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2979:5: otherlv_25= 'childPlacement' ( (lv_childPlacement_26_0= ruleKPlacement ) )
                    {
                    otherlv_25=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleKText6488); 

                        	newLeafNode(otherlv_25, grammarAccess.getKTextAccess().getChildPlacementKeyword_8_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2983:1: ( (lv_childPlacement_26_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2984:1: (lv_childPlacement_26_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2984:1: (lv_childPlacement_26_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:2985:3: lv_childPlacement_26_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTextAccess().getChildPlacementKPlacementParserRuleCall_8_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKText6509);
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

            otherlv_27=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKText6523); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3013:1: entryRuleKCustomRendering returns [EObject current=null] : iv_ruleKCustomRendering= ruleKCustomRendering EOF ;
    public final EObject entryRuleKCustomRendering() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKCustomRendering = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3014:2: (iv_ruleKCustomRendering= ruleKCustomRendering EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3015:2: iv_ruleKCustomRendering= ruleKCustomRendering EOF
            {
             newCompositeNode(grammarAccess.getKCustomRenderingRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKCustomRendering_in_entryRuleKCustomRendering6559);
            iv_ruleKCustomRendering=ruleKCustomRendering();

            state._fsp--;

             current =iv_ruleKCustomRendering; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKCustomRendering6569); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3022:1: ruleKCustomRendering returns [EObject current=null] : (otherlv_0= 'KCustomRendering' otherlv_1= '{' otherlv_2= 'className' ( (lv_className_3_0= ruleEString ) ) otherlv_4= 'bundleName' ( (lv_bundleName_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3025:28: ( (otherlv_0= 'KCustomRendering' otherlv_1= '{' otherlv_2= 'className' ( (lv_className_3_0= ruleEString ) ) otherlv_4= 'bundleName' ( (lv_bundleName_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3026:1: (otherlv_0= 'KCustomRendering' otherlv_1= '{' otherlv_2= 'className' ( (lv_className_3_0= ruleEString ) ) otherlv_4= 'bundleName' ( (lv_bundleName_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3026:1: (otherlv_0= 'KCustomRendering' otherlv_1= '{' otherlv_2= 'className' ( (lv_className_3_0= ruleEString ) ) otherlv_4= 'bundleName' ( (lv_bundleName_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3026:3: otherlv_0= 'KCustomRendering' otherlv_1= '{' otherlv_2= 'className' ( (lv_className_3_0= ruleEString ) ) otherlv_4= 'bundleName' ( (lv_bundleName_5_0= ruleEString ) ) (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )? (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )? (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )? (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )? otherlv_28= '}'
            {
            otherlv_0=(Token)match(input,44,FollowSets000.FOLLOW_44_in_ruleKCustomRendering6606); 

                	newLeafNode(otherlv_0, grammarAccess.getKCustomRenderingAccess().getKCustomRenderingKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKCustomRendering6618); 

                	newLeafNode(otherlv_1, grammarAccess.getKCustomRenderingAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,45,FollowSets000.FOLLOW_45_in_ruleKCustomRendering6630); 

                	newLeafNode(otherlv_2, grammarAccess.getKCustomRenderingAccess().getClassNameKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3038:1: ( (lv_className_3_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3039:1: (lv_className_3_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3039:1: (lv_className_3_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3040:3: lv_className_3_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getClassNameEStringParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKCustomRendering6651);
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

            otherlv_4=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleKCustomRendering6663); 

                	newLeafNode(otherlv_4, grammarAccess.getKCustomRenderingAccess().getBundleNameKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3060:1: ( (lv_bundleName_5_0= ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3061:1: (lv_bundleName_5_0= ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3061:1: (lv_bundleName_5_0= ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3062:3: lv_bundleName_5_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getBundleNameEStringParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKCustomRendering6684);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3078:2: (otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==17) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3078:4: otherlv_6= 'references' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')'
                    {
                    otherlv_6=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKCustomRendering6697); 

                        	newLeafNode(otherlv_6, grammarAccess.getKCustomRenderingAccess().getReferencesKeyword_6_0());
                        
                    otherlv_7=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKCustomRendering6709); 

                        	newLeafNode(otherlv_7, grammarAccess.getKCustomRenderingAccess().getLeftParenthesisKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3086:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3087:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3087:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3088:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKCustomRenderingRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getReferencesKRenderingRefCrossReference_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKCustomRendering6732);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3101:2: (otherlv_9= ',' ( ( ruleEString ) ) )*
                    loop87:
                    do {
                        int alt87=2;
                        int LA87_0 = input.LA(1);

                        if ( (LA87_0==14) ) {
                            alt87=1;
                        }


                        switch (alt87) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3101:4: otherlv_9= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_9=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKCustomRendering6745); 

                    	        	newLeafNode(otherlv_9, grammarAccess.getKCustomRenderingAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3105:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3106:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3106:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3107:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKCustomRenderingRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getReferencesKRenderingRefCrossReference_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKCustomRendering6768);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop87;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKCustomRendering6782); 

                        	newLeafNode(otherlv_11, grammarAccess.getKCustomRenderingAccess().getRightParenthesisKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3124:3: (otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) ) )?
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( (LA89_0==21) ) {
                alt89=1;
            }
            switch (alt89) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3124:5: otherlv_12= 'placementData' ( (lv_placementData_13_0= ruleKPlacementData ) )
                    {
                    otherlv_12=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKCustomRendering6797); 

                        	newLeafNode(otherlv_12, grammarAccess.getKCustomRenderingAccess().getPlacementDataKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3128:1: ( (lv_placementData_13_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3129:1: (lv_placementData_13_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3129:1: (lv_placementData_13_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3130:3: lv_placementData_13_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getPlacementDataKPlacementDataParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKCustomRendering6818);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3146:4: (otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}' )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==22) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3146:6: otherlv_14= 'styles' otherlv_15= '{' ( (lv_styles_16_0= ruleKStyle ) ) (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )* otherlv_19= '}'
                    {
                    otherlv_14=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleKCustomRendering6833); 

                        	newLeafNode(otherlv_14, grammarAccess.getKCustomRenderingAccess().getStylesKeyword_8_0());
                        
                    otherlv_15=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKCustomRendering6845); 

                        	newLeafNode(otherlv_15, grammarAccess.getKCustomRenderingAccess().getLeftCurlyBracketKeyword_8_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3154:1: ( (lv_styles_16_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3155:1: (lv_styles_16_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3155:1: (lv_styles_16_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3156:3: lv_styles_16_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getStylesKStyleParserRuleCall_8_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKCustomRendering6866);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3172:2: (otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) ) )*
                    loop90:
                    do {
                        int alt90=2;
                        int LA90_0 = input.LA(1);

                        if ( (LA90_0==14) ) {
                            alt90=1;
                        }


                        switch (alt90) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3172:4: otherlv_17= ',' ( (lv_styles_18_0= ruleKStyle ) )
                    	    {
                    	    otherlv_17=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKCustomRendering6879); 

                    	        	newLeafNode(otherlv_17, grammarAccess.getKCustomRenderingAccess().getCommaKeyword_8_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3176:1: ( (lv_styles_18_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3177:1: (lv_styles_18_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3177:1: (lv_styles_18_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3178:3: lv_styles_18_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getStylesKStyleParserRuleCall_8_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKCustomRendering6900);
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
                    	    break loop90;
                        }
                    } while (true);

                    otherlv_19=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKCustomRendering6914); 

                        	newLeafNode(otherlv_19, grammarAccess.getKCustomRenderingAccess().getRightCurlyBracketKeyword_8_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3198:3: (otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}' )?
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==26) ) {
                alt93=1;
            }
            switch (alt93) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3198:5: otherlv_20= 'children' otherlv_21= '{' ( (lv_children_22_0= ruleKRendering ) ) (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )* otherlv_25= '}'
                    {
                    otherlv_20=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleKCustomRendering6929); 

                        	newLeafNode(otherlv_20, grammarAccess.getKCustomRenderingAccess().getChildrenKeyword_9_0());
                        
                    otherlv_21=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKCustomRendering6941); 

                        	newLeafNode(otherlv_21, grammarAccess.getKCustomRenderingAccess().getLeftCurlyBracketKeyword_9_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3206:1: ( (lv_children_22_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3207:1: (lv_children_22_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3207:1: (lv_children_22_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3208:3: lv_children_22_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getChildrenKRenderingParserRuleCall_9_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKCustomRendering6962);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3224:2: (otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) ) )*
                    loop92:
                    do {
                        int alt92=2;
                        int LA92_0 = input.LA(1);

                        if ( (LA92_0==14) ) {
                            alt92=1;
                        }


                        switch (alt92) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3224:4: otherlv_23= ',' ( (lv_children_24_0= ruleKRendering ) )
                    	    {
                    	    otherlv_23=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKCustomRendering6975); 

                    	        	newLeafNode(otherlv_23, grammarAccess.getKCustomRenderingAccess().getCommaKeyword_9_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3228:1: ( (lv_children_24_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3229:1: (lv_children_24_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3229:1: (lv_children_24_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3230:3: lv_children_24_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getChildrenKRenderingParserRuleCall_9_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKCustomRendering6996);
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
                    	    break loop92;
                        }
                    } while (true);

                    otherlv_25=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKCustomRendering7010); 

                        	newLeafNode(otherlv_25, grammarAccess.getKCustomRenderingAccess().getRightCurlyBracketKeyword_9_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3250:3: (otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) ) )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==27) ) {
                alt94=1;
            }
            switch (alt94) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3250:5: otherlv_26= 'childPlacement' ( (lv_childPlacement_27_0= ruleKPlacement ) )
                    {
                    otherlv_26=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleKCustomRendering7025); 

                        	newLeafNode(otherlv_26, grammarAccess.getKCustomRenderingAccess().getChildPlacementKeyword_10_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3254:1: ( (lv_childPlacement_27_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3255:1: (lv_childPlacement_27_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3255:1: (lv_childPlacement_27_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3256:3: lv_childPlacement_27_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKCustomRenderingAccess().getChildPlacementKPlacementParserRuleCall_10_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKCustomRendering7046);
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

            otherlv_28=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKCustomRendering7060); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3284:1: entryRuleKSpline returns [EObject current=null] : iv_ruleKSpline= ruleKSpline EOF ;
    public final EObject entryRuleKSpline() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKSpline = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3285:2: (iv_ruleKSpline= ruleKSpline EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3286:2: iv_ruleKSpline= ruleKSpline EOF
            {
             newCompositeNode(grammarAccess.getKSplineRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKSpline_in_entryRuleKSpline7096);
            iv_ruleKSpline=ruleKSpline();

            state._fsp--;

             current =iv_ruleKSpline; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKSpline7106); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3293:1: ruleKSpline returns [EObject current=null] : ( () otherlv_1= 'KSpline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3296:28: ( ( () otherlv_1= 'KSpline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3297:1: ( () otherlv_1= 'KSpline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3297:1: ( () otherlv_1= 'KSpline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3297:2: () otherlv_1= 'KSpline' otherlv_2= '{' (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )? (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )? (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )? (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )? (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )? otherlv_25= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3297:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3298:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKSplineAccess().getKSplineAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,46,FollowSets000.FOLLOW_46_in_ruleKSpline7152); 

                	newLeafNode(otherlv_1, grammarAccess.getKSplineAccess().getKSplineKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKSpline7164); 

                	newLeafNode(otherlv_2, grammarAccess.getKSplineAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3311:1: (otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')' )?
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==17) ) {
                alt96=1;
            }
            switch (alt96) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3311:3: otherlv_3= 'references' otherlv_4= '(' ( ( ruleEString ) ) (otherlv_6= ',' ( ( ruleEString ) ) )* otherlv_8= ')'
                    {
                    otherlv_3=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKSpline7177); 

                        	newLeafNode(otherlv_3, grammarAccess.getKSplineAccess().getReferencesKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKSpline7189); 

                        	newLeafNode(otherlv_4, grammarAccess.getKSplineAccess().getLeftParenthesisKeyword_3_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3319:1: ( ( ruleEString ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3320:1: ( ruleEString )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3320:1: ( ruleEString )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3321:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getKSplineRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getKSplineAccess().getReferencesKRenderingRefCrossReference_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKSpline7212);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3334:2: (otherlv_6= ',' ( ( ruleEString ) ) )*
                    loop95:
                    do {
                        int alt95=2;
                        int LA95_0 = input.LA(1);

                        if ( (LA95_0==14) ) {
                            alt95=1;
                        }


                        switch (alt95) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3334:4: otherlv_6= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_6=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKSpline7225); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getKSplineAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3338:1: ( ( ruleEString ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3339:1: ( ruleEString )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3339:1: ( ruleEString )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3340:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getKSplineRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getKSplineAccess().getReferencesKRenderingRefCrossReference_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleKSpline7248);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop95;
                        }
                    } while (true);

                    otherlv_8=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKSpline7262); 

                        	newLeafNode(otherlv_8, grammarAccess.getKSplineAccess().getRightParenthesisKeyword_3_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3357:3: (otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) ) )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==21) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3357:5: otherlv_9= 'placementData' ( (lv_placementData_10_0= ruleKPlacementData ) )
                    {
                    otherlv_9=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKSpline7277); 

                        	newLeafNode(otherlv_9, grammarAccess.getKSplineAccess().getPlacementDataKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3361:1: ( (lv_placementData_10_0= ruleKPlacementData ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3362:1: (lv_placementData_10_0= ruleKPlacementData )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3362:1: (lv_placementData_10_0= ruleKPlacementData )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3363:3: lv_placementData_10_0= ruleKPlacementData
                    {
                     
                    	        newCompositeNode(grammarAccess.getKSplineAccess().getPlacementDataKPlacementDataParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacementData_in_ruleKSpline7298);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3379:4: (otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}' )?
            int alt99=2;
            int LA99_0 = input.LA(1);

            if ( (LA99_0==22) ) {
                alt99=1;
            }
            switch (alt99) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3379:6: otherlv_11= 'styles' otherlv_12= '{' ( (lv_styles_13_0= ruleKStyle ) ) (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )* otherlv_16= '}'
                    {
                    otherlv_11=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleKSpline7313); 

                        	newLeafNode(otherlv_11, grammarAccess.getKSplineAccess().getStylesKeyword_5_0());
                        
                    otherlv_12=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKSpline7325); 

                        	newLeafNode(otherlv_12, grammarAccess.getKSplineAccess().getLeftCurlyBracketKeyword_5_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3387:1: ( (lv_styles_13_0= ruleKStyle ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3388:1: (lv_styles_13_0= ruleKStyle )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3388:1: (lv_styles_13_0= ruleKStyle )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3389:3: lv_styles_13_0= ruleKStyle
                    {
                     
                    	        newCompositeNode(grammarAccess.getKSplineAccess().getStylesKStyleParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKSpline7346);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3405:2: (otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) ) )*
                    loop98:
                    do {
                        int alt98=2;
                        int LA98_0 = input.LA(1);

                        if ( (LA98_0==14) ) {
                            alt98=1;
                        }


                        switch (alt98) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3405:4: otherlv_14= ',' ( (lv_styles_15_0= ruleKStyle ) )
                    	    {
                    	    otherlv_14=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKSpline7359); 

                    	        	newLeafNode(otherlv_14, grammarAccess.getKSplineAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3409:1: ( (lv_styles_15_0= ruleKStyle ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3410:1: (lv_styles_15_0= ruleKStyle )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3410:1: (lv_styles_15_0= ruleKStyle )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3411:3: lv_styles_15_0= ruleKStyle
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKSplineAccess().getStylesKStyleParserRuleCall_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKStyle_in_ruleKSpline7380);
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
                    	    break loop98;
                        }
                    } while (true);

                    otherlv_16=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKSpline7394); 

                        	newLeafNode(otherlv_16, grammarAccess.getKSplineAccess().getRightCurlyBracketKeyword_5_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3431:3: (otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}' )?
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( (LA101_0==26) ) {
                alt101=1;
            }
            switch (alt101) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3431:5: otherlv_17= 'children' otherlv_18= '{' ( (lv_children_19_0= ruleKRendering ) ) (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )* otherlv_22= '}'
                    {
                    otherlv_17=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleKSpline7409); 

                        	newLeafNode(otherlv_17, grammarAccess.getKSplineAccess().getChildrenKeyword_6_0());
                        
                    otherlv_18=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKSpline7421); 

                        	newLeafNode(otherlv_18, grammarAccess.getKSplineAccess().getLeftCurlyBracketKeyword_6_1());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3439:1: ( (lv_children_19_0= ruleKRendering ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3440:1: (lv_children_19_0= ruleKRendering )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3440:1: (lv_children_19_0= ruleKRendering )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3441:3: lv_children_19_0= ruleKRendering
                    {
                     
                    	        newCompositeNode(grammarAccess.getKSplineAccess().getChildrenKRenderingParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKSpline7442);
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

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3457:2: (otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) ) )*
                    loop100:
                    do {
                        int alt100=2;
                        int LA100_0 = input.LA(1);

                        if ( (LA100_0==14) ) {
                            alt100=1;
                        }


                        switch (alt100) {
                    	case 1 :
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3457:4: otherlv_20= ',' ( (lv_children_21_0= ruleKRendering ) )
                    	    {
                    	    otherlv_20=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKSpline7455); 

                    	        	newLeafNode(otherlv_20, grammarAccess.getKSplineAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3461:1: ( (lv_children_21_0= ruleKRendering ) )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3462:1: (lv_children_21_0= ruleKRendering )
                    	    {
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3462:1: (lv_children_21_0= ruleKRendering )
                    	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3463:3: lv_children_21_0= ruleKRendering
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getKSplineAccess().getChildrenKRenderingParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleKRendering_in_ruleKSpline7476);
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
                    	    break loop100;
                        }
                    } while (true);

                    otherlv_22=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKSpline7490); 

                        	newLeafNode(otherlv_22, grammarAccess.getKSplineAccess().getRightCurlyBracketKeyword_6_4());
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3483:3: (otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) ) )?
            int alt102=2;
            int LA102_0 = input.LA(1);

            if ( (LA102_0==27) ) {
                alt102=1;
            }
            switch (alt102) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3483:5: otherlv_23= 'childPlacement' ( (lv_childPlacement_24_0= ruleKPlacement ) )
                    {
                    otherlv_23=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleKSpline7505); 

                        	newLeafNode(otherlv_23, grammarAccess.getKSplineAccess().getChildPlacementKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3487:1: ( (lv_childPlacement_24_0= ruleKPlacement ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3488:1: (lv_childPlacement_24_0= ruleKPlacement )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3488:1: (lv_childPlacement_24_0= ruleKPlacement )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3489:3: lv_childPlacement_24_0= ruleKPlacement
                    {
                     
                    	        newCompositeNode(grammarAccess.getKSplineAccess().getChildPlacementKPlacementParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKPlacement_in_ruleKSpline7526);
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

            otherlv_25=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKSpline7540); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3517:1: entryRuleKDecoratorPlacementData returns [EObject current=null] : iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF ;
    public final EObject entryRuleKDecoratorPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKDecoratorPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3518:2: (iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3519:2: iv_ruleKDecoratorPlacementData= ruleKDecoratorPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKDecoratorPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKDecoratorPlacementData_in_entryRuleKDecoratorPlacementData7576);
            iv_ruleKDecoratorPlacementData=ruleKDecoratorPlacementData();

            state._fsp--;

             current =iv_ruleKDecoratorPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKDecoratorPlacementData7586); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3526:1: ruleKDecoratorPlacementData returns [EObject current=null] : ( ( (lv_relative_0_0= 'relative' ) ) otherlv_1= 'KDecoratorPlacementData' otherlv_2= '{' otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? otherlv_9= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3529:28: ( ( ( (lv_relative_0_0= 'relative' ) ) otherlv_1= 'KDecoratorPlacementData' otherlv_2= '{' otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? otherlv_9= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3530:1: ( ( (lv_relative_0_0= 'relative' ) ) otherlv_1= 'KDecoratorPlacementData' otherlv_2= '{' otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? otherlv_9= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3530:1: ( ( (lv_relative_0_0= 'relative' ) ) otherlv_1= 'KDecoratorPlacementData' otherlv_2= '{' otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? otherlv_9= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3530:2: ( (lv_relative_0_0= 'relative' ) ) otherlv_1= 'KDecoratorPlacementData' otherlv_2= '{' otherlv_3= 'location' ( (lv_location_4_0= ruleEFloat ) ) (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )? (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )? otherlv_9= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3530:2: ( (lv_relative_0_0= 'relative' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3531:1: (lv_relative_0_0= 'relative' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3531:1: (lv_relative_0_0= 'relative' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3532:3: lv_relative_0_0= 'relative'
            {
            lv_relative_0_0=(Token)match(input,47,FollowSets000.FOLLOW_47_in_ruleKDecoratorPlacementData7629); 

                    newLeafNode(lv_relative_0_0, grammarAccess.getKDecoratorPlacementDataAccess().getRelativeRelativeKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKDecoratorPlacementDataRule());
            	        }
                   		setWithLastConsumed(current, "relative", true, "relative");
            	    

            }


            }

            otherlv_1=(Token)match(input,48,FollowSets000.FOLLOW_48_in_ruleKDecoratorPlacementData7654); 

                	newLeafNode(otherlv_1, grammarAccess.getKDecoratorPlacementDataAccess().getKDecoratorPlacementDataKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKDecoratorPlacementData7666); 

                	newLeafNode(otherlv_2, grammarAccess.getKDecoratorPlacementDataAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,49,FollowSets000.FOLLOW_49_in_ruleKDecoratorPlacementData7678); 

                	newLeafNode(otherlv_3, grammarAccess.getKDecoratorPlacementDataAccess().getLocationKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3557:1: ( (lv_location_4_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3558:1: (lv_location_4_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3558:1: (lv_location_4_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3559:3: lv_location_4_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getLocationEFloatParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7699);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3575:2: (otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) ) )?
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==50) ) {
                alt103=1;
            }
            switch (alt103) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3575:4: otherlv_5= 'xOffset' ( (lv_xOffset_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,50,FollowSets000.FOLLOW_50_in_ruleKDecoratorPlacementData7712); 

                        	newLeafNode(otherlv_5, grammarAccess.getKDecoratorPlacementDataAccess().getXOffsetKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3579:1: ( (lv_xOffset_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3580:1: (lv_xOffset_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3580:1: (lv_xOffset_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3581:3: lv_xOffset_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getXOffsetEFloatParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7733);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3597:4: (otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) ) )?
            int alt104=2;
            int LA104_0 = input.LA(1);

            if ( (LA104_0==51) ) {
                alt104=1;
            }
            switch (alt104) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3597:6: otherlv_7= 'yOffset' ( (lv_yOffset_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,51,FollowSets000.FOLLOW_51_in_ruleKDecoratorPlacementData7748); 

                        	newLeafNode(otherlv_7, grammarAccess.getKDecoratorPlacementDataAccess().getYOffsetKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3601:1: ( (lv_yOffset_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3602:1: (lv_yOffset_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3602:1: (lv_yOffset_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3603:3: lv_yOffset_8_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKDecoratorPlacementDataAccess().getYOffsetEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7769);
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

            otherlv_9=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKDecoratorPlacementData7783); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3631:1: entryRuleKGridPlacementData returns [EObject current=null] : iv_ruleKGridPlacementData= ruleKGridPlacementData EOF ;
    public final EObject entryRuleKGridPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKGridPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3632:2: (iv_ruleKGridPlacementData= ruleKGridPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3633:2: iv_ruleKGridPlacementData= ruleKGridPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKGridPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKGridPlacementData_in_entryRuleKGridPlacementData7819);
            iv_ruleKGridPlacementData=ruleKGridPlacementData();

            state._fsp--;

             current =iv_ruleKGridPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKGridPlacementData7829); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3640:1: ruleKGridPlacementData returns [EObject current=null] : (otherlv_0= 'KGridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'horizontalIndent' ( (lv_horizontalIndent_7_0= ruleEFloat ) ) otherlv_8= 'verticalIndent' ( (lv_verticalIndent_9_0= ruleEFloat ) ) otherlv_10= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3643:28: ( (otherlv_0= 'KGridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'horizontalIndent' ( (lv_horizontalIndent_7_0= ruleEFloat ) ) otherlv_8= 'verticalIndent' ( (lv_verticalIndent_9_0= ruleEFloat ) ) otherlv_10= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3644:1: (otherlv_0= 'KGridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'horizontalIndent' ( (lv_horizontalIndent_7_0= ruleEFloat ) ) otherlv_8= 'verticalIndent' ( (lv_verticalIndent_9_0= ruleEFloat ) ) otherlv_10= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3644:1: (otherlv_0= 'KGridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'horizontalIndent' ( (lv_horizontalIndent_7_0= ruleEFloat ) ) otherlv_8= 'verticalIndent' ( (lv_verticalIndent_9_0= ruleEFloat ) ) otherlv_10= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3644:3: otherlv_0= 'KGridPlacementData' otherlv_1= '{' otherlv_2= 'widthHint' ( (lv_widthHint_3_0= ruleEFloat ) ) otherlv_4= 'heightHint' ( (lv_heightHint_5_0= ruleEFloat ) ) otherlv_6= 'horizontalIndent' ( (lv_horizontalIndent_7_0= ruleEFloat ) ) otherlv_8= 'verticalIndent' ( (lv_verticalIndent_9_0= ruleEFloat ) ) otherlv_10= '}'
            {
            otherlv_0=(Token)match(input,52,FollowSets000.FOLLOW_52_in_ruleKGridPlacementData7866); 

                	newLeafNode(otherlv_0, grammarAccess.getKGridPlacementDataAccess().getKGridPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKGridPlacementData7878); 

                	newLeafNode(otherlv_1, grammarAccess.getKGridPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,53,FollowSets000.FOLLOW_53_in_ruleKGridPlacementData7890); 

                	newLeafNode(otherlv_2, grammarAccess.getKGridPlacementDataAccess().getWidthHintKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3656:1: ( (lv_widthHint_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3657:1: (lv_widthHint_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3657:1: (lv_widthHint_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3658:3: lv_widthHint_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getWidthHintEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData7911);
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

            otherlv_4=(Token)match(input,54,FollowSets000.FOLLOW_54_in_ruleKGridPlacementData7923); 

                	newLeafNode(otherlv_4, grammarAccess.getKGridPlacementDataAccess().getHeightHintKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3678:1: ( (lv_heightHint_5_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3679:1: (lv_heightHint_5_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3679:1: (lv_heightHint_5_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3680:3: lv_heightHint_5_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getHeightHintEFloatParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData7944);
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

            otherlv_6=(Token)match(input,55,FollowSets000.FOLLOW_55_in_ruleKGridPlacementData7956); 

                	newLeafNode(otherlv_6, grammarAccess.getKGridPlacementDataAccess().getHorizontalIndentKeyword_6());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3700:1: ( (lv_horizontalIndent_7_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3701:1: (lv_horizontalIndent_7_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3701:1: (lv_horizontalIndent_7_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3702:3: lv_horizontalIndent_7_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getHorizontalIndentEFloatParserRuleCall_7_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData7977);
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

            otherlv_8=(Token)match(input,56,FollowSets000.FOLLOW_56_in_ruleKGridPlacementData7989); 

                	newLeafNode(otherlv_8, grammarAccess.getKGridPlacementDataAccess().getVerticalIndentKeyword_8());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3722:1: ( (lv_verticalIndent_9_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3723:1: (lv_verticalIndent_9_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3723:1: (lv_verticalIndent_9_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3724:3: lv_verticalIndent_9_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementDataAccess().getVerticalIndentEFloatParserRuleCall_9_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKGridPlacementData8010);
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

            otherlv_10=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKGridPlacementData8022); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3752:1: entryRuleKStackPlacementData returns [EObject current=null] : iv_ruleKStackPlacementData= ruleKStackPlacementData EOF ;
    public final EObject entryRuleKStackPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStackPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3753:2: (iv_ruleKStackPlacementData= ruleKStackPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3754:2: iv_ruleKStackPlacementData= ruleKStackPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKStackPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKStackPlacementData_in_entryRuleKStackPlacementData8058);
            iv_ruleKStackPlacementData=ruleKStackPlacementData();

            state._fsp--;

             current =iv_ruleKStackPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKStackPlacementData8068); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3761:1: ruleKStackPlacementData returns [EObject current=null] : (otherlv_0= 'KStackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3764:28: ( (otherlv_0= 'KStackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3765:1: (otherlv_0= 'KStackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3765:1: (otherlv_0= 'KStackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3765:3: otherlv_0= 'KStackPlacementData' otherlv_1= '{' otherlv_2= 'insetRight' ( (lv_insetRight_3_0= ruleEFloat ) ) otherlv_4= 'insetBottom' ( (lv_insetBottom_5_0= ruleEFloat ) ) otherlv_6= 'insetLeft' ( (lv_insetLeft_7_0= ruleEFloat ) ) otherlv_8= 'insetTop' ( (lv_insetTop_9_0= ruleEFloat ) ) otherlv_10= '}'
            {
            otherlv_0=(Token)match(input,57,FollowSets000.FOLLOW_57_in_ruleKStackPlacementData8105); 

                	newLeafNode(otherlv_0, grammarAccess.getKStackPlacementDataAccess().getKStackPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKStackPlacementData8117); 

                	newLeafNode(otherlv_1, grammarAccess.getKStackPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,58,FollowSets000.FOLLOW_58_in_ruleKStackPlacementData8129); 

                	newLeafNode(otherlv_2, grammarAccess.getKStackPlacementDataAccess().getInsetRightKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3777:1: ( (lv_insetRight_3_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3778:1: (lv_insetRight_3_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3778:1: (lv_insetRight_3_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3779:3: lv_insetRight_3_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetRightEFloatParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData8150);
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

            otherlv_4=(Token)match(input,59,FollowSets000.FOLLOW_59_in_ruleKStackPlacementData8162); 

                	newLeafNode(otherlv_4, grammarAccess.getKStackPlacementDataAccess().getInsetBottomKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3799:1: ( (lv_insetBottom_5_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3800:1: (lv_insetBottom_5_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3800:1: (lv_insetBottom_5_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3801:3: lv_insetBottom_5_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetBottomEFloatParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData8183);
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

            otherlv_6=(Token)match(input,60,FollowSets000.FOLLOW_60_in_ruleKStackPlacementData8195); 

                	newLeafNode(otherlv_6, grammarAccess.getKStackPlacementDataAccess().getInsetLeftKeyword_6());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3821:1: ( (lv_insetLeft_7_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3822:1: (lv_insetLeft_7_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3822:1: (lv_insetLeft_7_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3823:3: lv_insetLeft_7_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetLeftEFloatParserRuleCall_7_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData8216);
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

            otherlv_8=(Token)match(input,61,FollowSets000.FOLLOW_61_in_ruleKStackPlacementData8228); 

                	newLeafNode(otherlv_8, grammarAccess.getKStackPlacementDataAccess().getInsetTopKeyword_8());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3843:1: ( (lv_insetTop_9_0= ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3844:1: (lv_insetTop_9_0= ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3844:1: (lv_insetTop_9_0= ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3845:3: lv_insetTop_9_0= ruleEFloat
            {
             
            	        newCompositeNode(grammarAccess.getKStackPlacementDataAccess().getInsetTopEFloatParserRuleCall_9_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKStackPlacementData8249);
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

            otherlv_10=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKStackPlacementData8261); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3873:1: entryRuleKDirectPlacementData returns [EObject current=null] : iv_ruleKDirectPlacementData= ruleKDirectPlacementData EOF ;
    public final EObject entryRuleKDirectPlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKDirectPlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3874:2: (iv_ruleKDirectPlacementData= ruleKDirectPlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3875:2: iv_ruleKDirectPlacementData= ruleKDirectPlacementData EOF
            {
             newCompositeNode(grammarAccess.getKDirectPlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKDirectPlacementData_in_entryRuleKDirectPlacementData8297);
            iv_ruleKDirectPlacementData=ruleKDirectPlacementData();

            state._fsp--;

             current =iv_ruleKDirectPlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKDirectPlacementData8307); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3882:1: ruleKDirectPlacementData returns [EObject current=null] : (otherlv_0= 'KDirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) otherlv_4= 'bottomRight' ( (lv_bottomRight_5_0= ruleKPosition ) ) otherlv_6= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3885:28: ( (otherlv_0= 'KDirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) otherlv_4= 'bottomRight' ( (lv_bottomRight_5_0= ruleKPosition ) ) otherlv_6= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3886:1: (otherlv_0= 'KDirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) otherlv_4= 'bottomRight' ( (lv_bottomRight_5_0= ruleKPosition ) ) otherlv_6= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3886:1: (otherlv_0= 'KDirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) otherlv_4= 'bottomRight' ( (lv_bottomRight_5_0= ruleKPosition ) ) otherlv_6= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3886:3: otherlv_0= 'KDirectPlacementData' otherlv_1= '{' otherlv_2= 'topLeft' ( (lv_topLeft_3_0= ruleKPosition ) ) otherlv_4= 'bottomRight' ( (lv_bottomRight_5_0= ruleKPosition ) ) otherlv_6= '}'
            {
            otherlv_0=(Token)match(input,62,FollowSets000.FOLLOW_62_in_ruleKDirectPlacementData8344); 

                	newLeafNode(otherlv_0, grammarAccess.getKDirectPlacementDataAccess().getKDirectPlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKDirectPlacementData8356); 

                	newLeafNode(otherlv_1, grammarAccess.getKDirectPlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,63,FollowSets000.FOLLOW_63_in_ruleKDirectPlacementData8368); 

                	newLeafNode(otherlv_2, grammarAccess.getKDirectPlacementDataAccess().getTopLeftKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3898:1: ( (lv_topLeft_3_0= ruleKPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3899:1: (lv_topLeft_3_0= ruleKPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3899:1: (lv_topLeft_3_0= ruleKPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3900:3: lv_topLeft_3_0= ruleKPosition
            {
             
            	        newCompositeNode(grammarAccess.getKDirectPlacementDataAccess().getTopLeftKPositionParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKDirectPlacementData8389);
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

            otherlv_4=(Token)match(input,64,FollowSets000.FOLLOW_64_in_ruleKDirectPlacementData8401); 

                	newLeafNode(otherlv_4, grammarAccess.getKDirectPlacementDataAccess().getBottomRightKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3920:1: ( (lv_bottomRight_5_0= ruleKPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3921:1: (lv_bottomRight_5_0= ruleKPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3921:1: (lv_bottomRight_5_0= ruleKPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3922:3: lv_bottomRight_5_0= ruleKPosition
            {
             
            	        newCompositeNode(grammarAccess.getKDirectPlacementDataAccess().getBottomRightKPositionParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKDirectPlacementData8422);
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

            otherlv_6=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKDirectPlacementData8434); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3950:1: entryRuleKPolylinePlacementData returns [EObject current=null] : iv_ruleKPolylinePlacementData= ruleKPolylinePlacementData EOF ;
    public final EObject entryRuleKPolylinePlacementData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPolylinePlacementData = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3951:2: (iv_ruleKPolylinePlacementData= ruleKPolylinePlacementData EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3952:2: iv_ruleKPolylinePlacementData= ruleKPolylinePlacementData EOF
            {
             newCompositeNode(grammarAccess.getKPolylinePlacementDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPolylinePlacementData_in_entryRuleKPolylinePlacementData8470);
            iv_ruleKPolylinePlacementData=ruleKPolylinePlacementData();

            state._fsp--;

             current =iv_ruleKPolylinePlacementData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPolylinePlacementData8480); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3959:1: ruleKPolylinePlacementData returns [EObject current=null] : (otherlv_0= 'KPolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' otherlv_3= '{' ( (lv_points_4_0= ruleKPosition ) ) (otherlv_5= ',' ( (lv_points_6_0= ruleKPosition ) ) )* otherlv_7= '}' otherlv_8= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3962:28: ( (otherlv_0= 'KPolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' otherlv_3= '{' ( (lv_points_4_0= ruleKPosition ) ) (otherlv_5= ',' ( (lv_points_6_0= ruleKPosition ) ) )* otherlv_7= '}' otherlv_8= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3963:1: (otherlv_0= 'KPolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' otherlv_3= '{' ( (lv_points_4_0= ruleKPosition ) ) (otherlv_5= ',' ( (lv_points_6_0= ruleKPosition ) ) )* otherlv_7= '}' otherlv_8= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3963:1: (otherlv_0= 'KPolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' otherlv_3= '{' ( (lv_points_4_0= ruleKPosition ) ) (otherlv_5= ',' ( (lv_points_6_0= ruleKPosition ) ) )* otherlv_7= '}' otherlv_8= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3963:3: otherlv_0= 'KPolylinePlacementData' otherlv_1= '{' otherlv_2= 'points' otherlv_3= '{' ( (lv_points_4_0= ruleKPosition ) ) (otherlv_5= ',' ( (lv_points_6_0= ruleKPosition ) ) )* otherlv_7= '}' otherlv_8= '}'
            {
            otherlv_0=(Token)match(input,65,FollowSets000.FOLLOW_65_in_ruleKPolylinePlacementData8517); 

                	newLeafNode(otherlv_0, grammarAccess.getKPolylinePlacementDataAccess().getKPolylinePlacementDataKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolylinePlacementData8529); 

                	newLeafNode(otherlv_1, grammarAccess.getKPolylinePlacementDataAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,66,FollowSets000.FOLLOW_66_in_ruleKPolylinePlacementData8541); 

                	newLeafNode(otherlv_2, grammarAccess.getKPolylinePlacementDataAccess().getPointsKeyword_2());
                
            otherlv_3=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPolylinePlacementData8553); 

                	newLeafNode(otherlv_3, grammarAccess.getKPolylinePlacementDataAccess().getLeftCurlyBracketKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3979:1: ( (lv_points_4_0= ruleKPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3980:1: (lv_points_4_0= ruleKPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3980:1: (lv_points_4_0= ruleKPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3981:3: lv_points_4_0= ruleKPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPolylinePlacementDataAccess().getPointsKPositionParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData8574);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3997:2: (otherlv_5= ',' ( (lv_points_6_0= ruleKPosition ) ) )*
            loop105:
            do {
                int alt105=2;
                int LA105_0 = input.LA(1);

                if ( (LA105_0==14) ) {
                    alt105=1;
                }


                switch (alt105) {
            	case 1 :
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:3997:4: otherlv_5= ',' ( (lv_points_6_0= ruleKPosition ) )
            	    {
            	    otherlv_5=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKPolylinePlacementData8587); 

            	        	newLeafNode(otherlv_5, grammarAccess.getKPolylinePlacementDataAccess().getCommaKeyword_5_0());
            	        
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4001:1: ( (lv_points_6_0= ruleKPosition ) )
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4002:1: (lv_points_6_0= ruleKPosition )
            	    {
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4002:1: (lv_points_6_0= ruleKPosition )
            	    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4003:3: lv_points_6_0= ruleKPosition
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getKPolylinePlacementDataAccess().getPointsKPositionParserRuleCall_5_1_0()); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData8608);
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
            	    break loop105;
                }
            } while (true);

            otherlv_7=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKPolylinePlacementData8622); 

                	newLeafNode(otherlv_7, grammarAccess.getKPolylinePlacementDataAccess().getRightCurlyBracketKeyword_6());
                
            otherlv_8=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKPolylinePlacementData8634); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4035:1: entryRuleEFloat returns [String current=null] : iv_ruleEFloat= ruleEFloat EOF ;
    public final String entryRuleEFloat() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEFloat = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4036:2: (iv_ruleEFloat= ruleEFloat EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4037:2: iv_ruleEFloat= ruleEFloat EOF
            {
             newCompositeNode(grammarAccess.getEFloatRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_entryRuleEFloat8671);
            iv_ruleEFloat=ruleEFloat();

            state._fsp--;

             current =iv_ruleEFloat.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEFloat8682); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4044:1: ruleEFloat returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? ) ;
    public final AntlrDatatypeRuleToken ruleEFloat() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;
        Token this_INT_3=null;
        Token this_INT_7=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4047:28: ( ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4048:1: ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4048:1: ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4048:2: (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4048:2: (kw= '-' )?
            int alt106=2;
            int LA106_0 = input.LA(1);

            if ( (LA106_0==67) ) {
                alt106=1;
            }
            switch (alt106) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4049:2: kw= '-'
                    {
                    kw=(Token)match(input,67,FollowSets000.FOLLOW_67_in_ruleEFloat8721); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEFloatAccess().getHyphenMinusKeyword_0()); 
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4054:3: (this_INT_1= RULE_INT )?
            int alt107=2;
            int LA107_0 = input.LA(1);

            if ( (LA107_0==RULE_INT) ) {
                alt107=1;
            }
            switch (alt107) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4054:8: this_INT_1= RULE_INT
                    {
                    this_INT_1=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat8739); 

                    		current.merge(this_INT_1);
                        
                     
                        newLeafNode(this_INT_1, grammarAccess.getEFloatAccess().getINTTerminalRuleCall_1()); 
                        

                    }
                    break;

            }

            kw=(Token)match(input,68,FollowSets000.FOLLOW_68_in_ruleEFloat8759); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getEFloatAccess().getFullStopKeyword_2()); 
                
            this_INT_3=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat8774); 

            		current.merge(this_INT_3);
                
             
                newLeafNode(this_INT_3, grammarAccess.getEFloatAccess().getINTTerminalRuleCall_3()); 
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4074:1: ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )?
            int alt110=2;
            int LA110_0 = input.LA(1);

            if ( ((LA110_0>=69 && LA110_0<=70)) ) {
                alt110=1;
            }
            switch (alt110) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4074:2: (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4074:2: (kw= 'E' | kw= 'e' )
                    int alt108=2;
                    int LA108_0 = input.LA(1);

                    if ( (LA108_0==69) ) {
                        alt108=1;
                    }
                    else if ( (LA108_0==70) ) {
                        alt108=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 108, 0, input);

                        throw nvae;
                    }
                    switch (alt108) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4075:2: kw= 'E'
                            {
                            kw=(Token)match(input,69,FollowSets000.FOLLOW_69_in_ruleEFloat8794); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getEFloatAccess().getEKeyword_4_0_0()); 
                                

                            }
                            break;
                        case 2 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4082:2: kw= 'e'
                            {
                            kw=(Token)match(input,70,FollowSets000.FOLLOW_70_in_ruleEFloat8813); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getEFloatAccess().getEKeyword_4_0_1()); 
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4087:2: (kw= '-' )?
                    int alt109=2;
                    int LA109_0 = input.LA(1);

                    if ( (LA109_0==67) ) {
                        alt109=1;
                    }
                    switch (alt109) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4088:2: kw= '-'
                            {
                            kw=(Token)match(input,67,FollowSets000.FOLLOW_67_in_ruleEFloat8828); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getEFloatAccess().getHyphenMinusKeyword_4_1()); 
                                

                            }
                            break;

                    }

                    this_INT_7=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat8845); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4110:1: entryRuleKPosition returns [EObject current=null] : iv_ruleKPosition= ruleKPosition EOF ;
    public final EObject entryRuleKPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4111:2: (iv_ruleKPosition= ruleKPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4112:2: iv_ruleKPosition= ruleKPosition EOF
            {
             newCompositeNode(grammarAccess.getKPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPosition_in_entryRuleKPosition8894);
            iv_ruleKPosition=ruleKPosition();

            state._fsp--;

             current =iv_ruleKPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPosition8904); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4119:1: ruleKPosition returns [EObject current=null] : (otherlv_0= 'KPosition' otherlv_1= '{' otherlv_2= 'x' ( (lv_x_3_0= ruleKXPosition ) ) otherlv_4= 'y' ( (lv_y_5_0= ruleKYPosition ) ) otherlv_6= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4122:28: ( (otherlv_0= 'KPosition' otherlv_1= '{' otherlv_2= 'x' ( (lv_x_3_0= ruleKXPosition ) ) otherlv_4= 'y' ( (lv_y_5_0= ruleKYPosition ) ) otherlv_6= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4123:1: (otherlv_0= 'KPosition' otherlv_1= '{' otherlv_2= 'x' ( (lv_x_3_0= ruleKXPosition ) ) otherlv_4= 'y' ( (lv_y_5_0= ruleKYPosition ) ) otherlv_6= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4123:1: (otherlv_0= 'KPosition' otherlv_1= '{' otherlv_2= 'x' ( (lv_x_3_0= ruleKXPosition ) ) otherlv_4= 'y' ( (lv_y_5_0= ruleKYPosition ) ) otherlv_6= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4123:3: otherlv_0= 'KPosition' otherlv_1= '{' otherlv_2= 'x' ( (lv_x_3_0= ruleKXPosition ) ) otherlv_4= 'y' ( (lv_y_5_0= ruleKYPosition ) ) otherlv_6= '}'
            {
            otherlv_0=(Token)match(input,71,FollowSets000.FOLLOW_71_in_ruleKPosition8941); 

                	newLeafNode(otherlv_0, grammarAccess.getKPositionAccess().getKPositionKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPosition8953); 

                	newLeafNode(otherlv_1, grammarAccess.getKPositionAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,72,FollowSets000.FOLLOW_72_in_ruleKPosition8965); 

                	newLeafNode(otherlv_2, grammarAccess.getKPositionAccess().getXKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4135:1: ( (lv_x_3_0= ruleKXPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4136:1: (lv_x_3_0= ruleKXPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4136:1: (lv_x_3_0= ruleKXPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4137:3: lv_x_3_0= ruleKXPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPositionAccess().getXKXPositionParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKXPosition_in_ruleKPosition8986);
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

            otherlv_4=(Token)match(input,73,FollowSets000.FOLLOW_73_in_ruleKPosition8998); 

                	newLeafNode(otherlv_4, grammarAccess.getKPositionAccess().getYKeyword_4());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4157:1: ( (lv_y_5_0= ruleKYPosition ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4158:1: (lv_y_5_0= ruleKYPosition )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4158:1: (lv_y_5_0= ruleKYPosition )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4159:3: lv_y_5_0= ruleKYPosition
            {
             
            	        newCompositeNode(grammarAccess.getKPositionAccess().getYKYPositionParserRuleCall_5_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleKYPosition_in_ruleKPosition9019);
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

            otherlv_6=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKPosition9031); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4187:1: entryRuleKLeftPosition returns [EObject current=null] : iv_ruleKLeftPosition= ruleKLeftPosition EOF ;
    public final EObject entryRuleKLeftPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLeftPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4188:2: (iv_ruleKLeftPosition= ruleKLeftPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4189:2: iv_ruleKLeftPosition= ruleKLeftPosition EOF
            {
             newCompositeNode(grammarAccess.getKLeftPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLeftPosition_in_entryRuleKLeftPosition9067);
            iv_ruleKLeftPosition=ruleKLeftPosition();

            state._fsp--;

             current =iv_ruleKLeftPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLeftPosition9077); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4196:1: ruleKLeftPosition returns [EObject current=null] : ( () otherlv_1= 'KLeftPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4199:28: ( ( () otherlv_1= 'KLeftPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4200:1: ( () otherlv_1= 'KLeftPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4200:1: ( () otherlv_1= 'KLeftPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4200:2: () otherlv_1= 'KLeftPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4200:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4201:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKLeftPositionAccess().getKLeftPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,74,FollowSets000.FOLLOW_74_in_ruleKLeftPosition9123); 

                	newLeafNode(otherlv_1, grammarAccess.getKLeftPositionAccess().getKLeftPositionKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKLeftPosition9135); 

                	newLeafNode(otherlv_2, grammarAccess.getKLeftPositionAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4214:1: (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )?
            int alt111=2;
            int LA111_0 = input.LA(1);

            if ( (LA111_0==75) ) {
                alt111=1;
            }
            switch (alt111) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4214:3: otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,75,FollowSets000.FOLLOW_75_in_ruleKLeftPosition9148); 

                        	newLeafNode(otherlv_3, grammarAccess.getKLeftPositionAccess().getAbsoluteKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4218:1: ( (lv_absolute_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4219:1: (lv_absolute_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4219:1: (lv_absolute_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4220:3: lv_absolute_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKLeftPositionAccess().getAbsoluteEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKLeftPosition9169);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4236:4: (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )?
            int alt112=2;
            int LA112_0 = input.LA(1);

            if ( (LA112_0==47) ) {
                alt112=1;
            }
            switch (alt112) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4236:6: otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,47,FollowSets000.FOLLOW_47_in_ruleKLeftPosition9184); 

                        	newLeafNode(otherlv_5, grammarAccess.getKLeftPositionAccess().getRelativeKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4240:1: ( (lv_relative_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4241:1: (lv_relative_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4241:1: (lv_relative_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4242:3: lv_relative_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKLeftPositionAccess().getRelativeEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKLeftPosition9205);
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

            otherlv_7=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKLeftPosition9219); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4270:1: entryRuleKRightPosition returns [EObject current=null] : iv_ruleKRightPosition= ruleKRightPosition EOF ;
    public final EObject entryRuleKRightPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKRightPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4271:2: (iv_ruleKRightPosition= ruleKRightPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4272:2: iv_ruleKRightPosition= ruleKRightPosition EOF
            {
             newCompositeNode(grammarAccess.getKRightPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKRightPosition_in_entryRuleKRightPosition9255);
            iv_ruleKRightPosition=ruleKRightPosition();

            state._fsp--;

             current =iv_ruleKRightPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKRightPosition9265); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4279:1: ruleKRightPosition returns [EObject current=null] : ( () otherlv_1= 'KRightPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4282:28: ( ( () otherlv_1= 'KRightPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4283:1: ( () otherlv_1= 'KRightPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4283:1: ( () otherlv_1= 'KRightPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4283:2: () otherlv_1= 'KRightPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4283:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4284:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKRightPositionAccess().getKRightPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,76,FollowSets000.FOLLOW_76_in_ruleKRightPosition9311); 

                	newLeafNode(otherlv_1, grammarAccess.getKRightPositionAccess().getKRightPositionKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKRightPosition9323); 

                	newLeafNode(otherlv_2, grammarAccess.getKRightPositionAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4297:1: (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )?
            int alt113=2;
            int LA113_0 = input.LA(1);

            if ( (LA113_0==75) ) {
                alt113=1;
            }
            switch (alt113) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4297:3: otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,75,FollowSets000.FOLLOW_75_in_ruleKRightPosition9336); 

                        	newLeafNode(otherlv_3, grammarAccess.getKRightPositionAccess().getAbsoluteKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4301:1: ( (lv_absolute_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4302:1: (lv_absolute_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4302:1: (lv_absolute_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4303:3: lv_absolute_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRightPositionAccess().getAbsoluteEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRightPosition9357);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4319:4: (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )?
            int alt114=2;
            int LA114_0 = input.LA(1);

            if ( (LA114_0==47) ) {
                alt114=1;
            }
            switch (alt114) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4319:6: otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,47,FollowSets000.FOLLOW_47_in_ruleKRightPosition9372); 

                        	newLeafNode(otherlv_5, grammarAccess.getKRightPositionAccess().getRelativeKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4323:1: ( (lv_relative_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4324:1: (lv_relative_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4324:1: (lv_relative_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4325:3: lv_relative_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKRightPositionAccess().getRelativeEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKRightPosition9393);
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

            otherlv_7=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKRightPosition9407); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4353:1: entryRuleKTopPosition returns [EObject current=null] : iv_ruleKTopPosition= ruleKTopPosition EOF ;
    public final EObject entryRuleKTopPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKTopPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4354:2: (iv_ruleKTopPosition= ruleKTopPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4355:2: iv_ruleKTopPosition= ruleKTopPosition EOF
            {
             newCompositeNode(grammarAccess.getKTopPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKTopPosition_in_entryRuleKTopPosition9443);
            iv_ruleKTopPosition=ruleKTopPosition();

            state._fsp--;

             current =iv_ruleKTopPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKTopPosition9453); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4362:1: ruleKTopPosition returns [EObject current=null] : ( () otherlv_1= 'KTopPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4365:28: ( ( () otherlv_1= 'KTopPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4366:1: ( () otherlv_1= 'KTopPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4366:1: ( () otherlv_1= 'KTopPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4366:2: () otherlv_1= 'KTopPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4366:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4367:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKTopPositionAccess().getKTopPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,77,FollowSets000.FOLLOW_77_in_ruleKTopPosition9499); 

                	newLeafNode(otherlv_1, grammarAccess.getKTopPositionAccess().getKTopPositionKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKTopPosition9511); 

                	newLeafNode(otherlv_2, grammarAccess.getKTopPositionAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4380:1: (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )?
            int alt115=2;
            int LA115_0 = input.LA(1);

            if ( (LA115_0==75) ) {
                alt115=1;
            }
            switch (alt115) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4380:3: otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,75,FollowSets000.FOLLOW_75_in_ruleKTopPosition9524); 

                        	newLeafNode(otherlv_3, grammarAccess.getKTopPositionAccess().getAbsoluteKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4384:1: ( (lv_absolute_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4385:1: (lv_absolute_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4385:1: (lv_absolute_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4386:3: lv_absolute_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTopPositionAccess().getAbsoluteEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKTopPosition9545);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4402:4: (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )?
            int alt116=2;
            int LA116_0 = input.LA(1);

            if ( (LA116_0==47) ) {
                alt116=1;
            }
            switch (alt116) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4402:6: otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,47,FollowSets000.FOLLOW_47_in_ruleKTopPosition9560); 

                        	newLeafNode(otherlv_5, grammarAccess.getKTopPositionAccess().getRelativeKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4406:1: ( (lv_relative_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4407:1: (lv_relative_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4407:1: (lv_relative_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4408:3: lv_relative_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKTopPositionAccess().getRelativeEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKTopPosition9581);
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

            otherlv_7=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKTopPosition9595); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4436:1: entryRuleKBottomPosition returns [EObject current=null] : iv_ruleKBottomPosition= ruleKBottomPosition EOF ;
    public final EObject entryRuleKBottomPosition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBottomPosition = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4437:2: (iv_ruleKBottomPosition= ruleKBottomPosition EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4438:2: iv_ruleKBottomPosition= ruleKBottomPosition EOF
            {
             newCompositeNode(grammarAccess.getKBottomPositionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKBottomPosition_in_entryRuleKBottomPosition9631);
            iv_ruleKBottomPosition=ruleKBottomPosition();

            state._fsp--;

             current =iv_ruleKBottomPosition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKBottomPosition9641); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4445:1: ruleKBottomPosition returns [EObject current=null] : ( () otherlv_1= 'KBottomPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4448:28: ( ( () otherlv_1= 'KBottomPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4449:1: ( () otherlv_1= 'KBottomPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4449:1: ( () otherlv_1= 'KBottomPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4449:2: () otherlv_1= 'KBottomPosition' otherlv_2= '{' (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )? (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )? otherlv_7= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4449:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4450:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKBottomPositionAccess().getKBottomPositionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,78,FollowSets000.FOLLOW_78_in_ruleKBottomPosition9687); 

                	newLeafNode(otherlv_1, grammarAccess.getKBottomPositionAccess().getKBottomPositionKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKBottomPosition9699); 

                	newLeafNode(otherlv_2, grammarAccess.getKBottomPositionAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4463:1: (otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) ) )?
            int alt117=2;
            int LA117_0 = input.LA(1);

            if ( (LA117_0==75) ) {
                alt117=1;
            }
            switch (alt117) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4463:3: otherlv_3= 'absolute' ( (lv_absolute_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,75,FollowSets000.FOLLOW_75_in_ruleKBottomPosition9712); 

                        	newLeafNode(otherlv_3, grammarAccess.getKBottomPositionAccess().getAbsoluteKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4467:1: ( (lv_absolute_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4468:1: (lv_absolute_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4468:1: (lv_absolute_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4469:3: lv_absolute_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKBottomPositionAccess().getAbsoluteEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKBottomPosition9733);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4485:4: (otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) ) )?
            int alt118=2;
            int LA118_0 = input.LA(1);

            if ( (LA118_0==47) ) {
                alt118=1;
            }
            switch (alt118) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4485:6: otherlv_5= 'relative' ( (lv_relative_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,47,FollowSets000.FOLLOW_47_in_ruleKBottomPosition9748); 

                        	newLeafNode(otherlv_5, grammarAccess.getKBottomPositionAccess().getRelativeKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4489:1: ( (lv_relative_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4490:1: (lv_relative_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4490:1: (lv_relative_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4491:3: lv_relative_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKBottomPositionAccess().getRelativeEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKBottomPosition9769);
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

            otherlv_7=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKBottomPosition9783); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4519:1: entryRuleKForegroundColor returns [EObject current=null] : iv_ruleKForegroundColor= ruleKForegroundColor EOF ;
    public final EObject entryRuleKForegroundColor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKForegroundColor = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4520:2: (iv_ruleKForegroundColor= ruleKForegroundColor EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4521:2: iv_ruleKForegroundColor= ruleKForegroundColor EOF
            {
             newCompositeNode(grammarAccess.getKForegroundColorRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKForegroundColor_in_entryRuleKForegroundColor9819);
            iv_ruleKForegroundColor=ruleKForegroundColor();

            state._fsp--;

             current =iv_ruleKForegroundColor; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKForegroundColor9829); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4528:1: ruleKForegroundColor returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KForegroundColor' otherlv_2= '{' otherlv_3= 'red' ( (lv_red_4_0= ruleEInt ) ) otherlv_5= 'green' ( (lv_green_6_0= ruleEInt ) ) otherlv_7= 'blue' ( (lv_blue_8_0= ruleEInt ) ) otherlv_9= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4531:28: ( ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KForegroundColor' otherlv_2= '{' otherlv_3= 'red' ( (lv_red_4_0= ruleEInt ) ) otherlv_5= 'green' ( (lv_green_6_0= ruleEInt ) ) otherlv_7= 'blue' ( (lv_blue_8_0= ruleEInt ) ) otherlv_9= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4532:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KForegroundColor' otherlv_2= '{' otherlv_3= 'red' ( (lv_red_4_0= ruleEInt ) ) otherlv_5= 'green' ( (lv_green_6_0= ruleEInt ) ) otherlv_7= 'blue' ( (lv_blue_8_0= ruleEInt ) ) otherlv_9= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4532:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KForegroundColor' otherlv_2= '{' otherlv_3= 'red' ( (lv_red_4_0= ruleEInt ) ) otherlv_5= 'green' ( (lv_green_6_0= ruleEInt ) ) otherlv_7= 'blue' ( (lv_blue_8_0= ruleEInt ) ) otherlv_9= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4532:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KForegroundColor' otherlv_2= '{' otherlv_3= 'red' ( (lv_red_4_0= ruleEInt ) ) otherlv_5= 'green' ( (lv_green_6_0= ruleEInt ) ) otherlv_7= 'blue' ( (lv_blue_8_0= ruleEInt ) ) otherlv_9= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4532:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4533:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4533:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4534:3: lv_propagateToChildren_0_0= 'propagateToChildren'
            {
            lv_propagateToChildren_0_0=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKForegroundColor9872); 

                    newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKForegroundColorAccess().getPropagateToChildrenPropagateToChildrenKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKForegroundColorRule());
            	        }
                   		setWithLastConsumed(current, "propagateToChildren", true, "propagateToChildren");
            	    

            }


            }

            otherlv_1=(Token)match(input,79,FollowSets000.FOLLOW_79_in_ruleKForegroundColor9897); 

                	newLeafNode(otherlv_1, grammarAccess.getKForegroundColorAccess().getKForegroundColorKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKForegroundColor9909); 

                	newLeafNode(otherlv_2, grammarAccess.getKForegroundColorAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,80,FollowSets000.FOLLOW_80_in_ruleKForegroundColor9921); 

                	newLeafNode(otherlv_3, grammarAccess.getKForegroundColorAccess().getRedKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4559:1: ( (lv_red_4_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4560:1: (lv_red_4_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4560:1: (lv_red_4_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4561:3: lv_red_4_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKForegroundColorAccess().getRedEIntParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKForegroundColor9942);
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

            otherlv_5=(Token)match(input,81,FollowSets000.FOLLOW_81_in_ruleKForegroundColor9954); 

                	newLeafNode(otherlv_5, grammarAccess.getKForegroundColorAccess().getGreenKeyword_5());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4581:1: ( (lv_green_6_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4582:1: (lv_green_6_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4582:1: (lv_green_6_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4583:3: lv_green_6_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKForegroundColorAccess().getGreenEIntParserRuleCall_6_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKForegroundColor9975);
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

            otherlv_7=(Token)match(input,82,FollowSets000.FOLLOW_82_in_ruleKForegroundColor9987); 

                	newLeafNode(otherlv_7, grammarAccess.getKForegroundColorAccess().getBlueKeyword_7());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4603:1: ( (lv_blue_8_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4604:1: (lv_blue_8_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4604:1: (lv_blue_8_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4605:3: lv_blue_8_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKForegroundColorAccess().getBlueEIntParserRuleCall_8_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKForegroundColor10008);
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

            otherlv_9=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKForegroundColor10020); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4633:1: entryRuleKBackgroundColor returns [EObject current=null] : iv_ruleKBackgroundColor= ruleKBackgroundColor EOF ;
    public final EObject entryRuleKBackgroundColor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKBackgroundColor = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4634:2: (iv_ruleKBackgroundColor= ruleKBackgroundColor EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4635:2: iv_ruleKBackgroundColor= ruleKBackgroundColor EOF
            {
             newCompositeNode(grammarAccess.getKBackgroundColorRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKBackgroundColor_in_entryRuleKBackgroundColor10056);
            iv_ruleKBackgroundColor=ruleKBackgroundColor();

            state._fsp--;

             current =iv_ruleKBackgroundColor; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKBackgroundColor10066); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4642:1: ruleKBackgroundColor returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KBackgroundColor' otherlv_2= '{' otherlv_3= 'red' ( (lv_red_4_0= ruleEInt ) ) otherlv_5= 'green' ( (lv_green_6_0= ruleEInt ) ) otherlv_7= 'blue' ( (lv_blue_8_0= ruleEInt ) ) otherlv_9= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4645:28: ( ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KBackgroundColor' otherlv_2= '{' otherlv_3= 'red' ( (lv_red_4_0= ruleEInt ) ) otherlv_5= 'green' ( (lv_green_6_0= ruleEInt ) ) otherlv_7= 'blue' ( (lv_blue_8_0= ruleEInt ) ) otherlv_9= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4646:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KBackgroundColor' otherlv_2= '{' otherlv_3= 'red' ( (lv_red_4_0= ruleEInt ) ) otherlv_5= 'green' ( (lv_green_6_0= ruleEInt ) ) otherlv_7= 'blue' ( (lv_blue_8_0= ruleEInt ) ) otherlv_9= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4646:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KBackgroundColor' otherlv_2= '{' otherlv_3= 'red' ( (lv_red_4_0= ruleEInt ) ) otherlv_5= 'green' ( (lv_green_6_0= ruleEInt ) ) otherlv_7= 'blue' ( (lv_blue_8_0= ruleEInt ) ) otherlv_9= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4646:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KBackgroundColor' otherlv_2= '{' otherlv_3= 'red' ( (lv_red_4_0= ruleEInt ) ) otherlv_5= 'green' ( (lv_green_6_0= ruleEInt ) ) otherlv_7= 'blue' ( (lv_blue_8_0= ruleEInt ) ) otherlv_9= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4646:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4647:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4647:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4648:3: lv_propagateToChildren_0_0= 'propagateToChildren'
            {
            lv_propagateToChildren_0_0=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKBackgroundColor10109); 

                    newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKBackgroundColorAccess().getPropagateToChildrenPropagateToChildrenKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKBackgroundColorRule());
            	        }
                   		setWithLastConsumed(current, "propagateToChildren", true, "propagateToChildren");
            	    

            }


            }

            otherlv_1=(Token)match(input,83,FollowSets000.FOLLOW_83_in_ruleKBackgroundColor10134); 

                	newLeafNode(otherlv_1, grammarAccess.getKBackgroundColorAccess().getKBackgroundColorKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKBackgroundColor10146); 

                	newLeafNode(otherlv_2, grammarAccess.getKBackgroundColorAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,80,FollowSets000.FOLLOW_80_in_ruleKBackgroundColor10158); 

                	newLeafNode(otherlv_3, grammarAccess.getKBackgroundColorAccess().getRedKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4673:1: ( (lv_red_4_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4674:1: (lv_red_4_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4674:1: (lv_red_4_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4675:3: lv_red_4_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKBackgroundColorAccess().getRedEIntParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKBackgroundColor10179);
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

            otherlv_5=(Token)match(input,81,FollowSets000.FOLLOW_81_in_ruleKBackgroundColor10191); 

                	newLeafNode(otherlv_5, grammarAccess.getKBackgroundColorAccess().getGreenKeyword_5());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4695:1: ( (lv_green_6_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4696:1: (lv_green_6_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4696:1: (lv_green_6_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4697:3: lv_green_6_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKBackgroundColorAccess().getGreenEIntParserRuleCall_6_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKBackgroundColor10212);
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

            otherlv_7=(Token)match(input,82,FollowSets000.FOLLOW_82_in_ruleKBackgroundColor10224); 

                	newLeafNode(otherlv_7, grammarAccess.getKBackgroundColorAccess().getBlueKeyword_7());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4717:1: ( (lv_blue_8_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4718:1: (lv_blue_8_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4718:1: (lv_blue_8_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4719:3: lv_blue_8_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKBackgroundColorAccess().getBlueEIntParserRuleCall_8_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKBackgroundColor10245);
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

            otherlv_9=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKBackgroundColor10257); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4747:1: entryRuleKLineWidth returns [EObject current=null] : iv_ruleKLineWidth= ruleKLineWidth EOF ;
    public final EObject entryRuleKLineWidth() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLineWidth = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4748:2: (iv_ruleKLineWidth= ruleKLineWidth EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4749:2: iv_ruleKLineWidth= ruleKLineWidth EOF
            {
             newCompositeNode(grammarAccess.getKLineWidthRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLineWidth_in_entryRuleKLineWidth10293);
            iv_ruleKLineWidth=ruleKLineWidth();

            state._fsp--;

             current =iv_ruleKLineWidth; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLineWidth10303); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4756:1: ruleKLineWidth returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KLineWidth' otherlv_2= '{' otherlv_3= 'lineWidth' ( (lv_lineWidth_4_0= ruleEInt ) ) otherlv_5= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4759:28: ( ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KLineWidth' otherlv_2= '{' otherlv_3= 'lineWidth' ( (lv_lineWidth_4_0= ruleEInt ) ) otherlv_5= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4760:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KLineWidth' otherlv_2= '{' otherlv_3= 'lineWidth' ( (lv_lineWidth_4_0= ruleEInt ) ) otherlv_5= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4760:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KLineWidth' otherlv_2= '{' otherlv_3= 'lineWidth' ( (lv_lineWidth_4_0= ruleEInt ) ) otherlv_5= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4760:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KLineWidth' otherlv_2= '{' otherlv_3= 'lineWidth' ( (lv_lineWidth_4_0= ruleEInt ) ) otherlv_5= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4760:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4761:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4761:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4762:3: lv_propagateToChildren_0_0= 'propagateToChildren'
            {
            lv_propagateToChildren_0_0=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKLineWidth10346); 

                    newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKLineWidthAccess().getPropagateToChildrenPropagateToChildrenKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKLineWidthRule());
            	        }
                   		setWithLastConsumed(current, "propagateToChildren", true, "propagateToChildren");
            	    

            }


            }

            otherlv_1=(Token)match(input,84,FollowSets000.FOLLOW_84_in_ruleKLineWidth10371); 

                	newLeafNode(otherlv_1, grammarAccess.getKLineWidthAccess().getKLineWidthKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKLineWidth10383); 

                	newLeafNode(otherlv_2, grammarAccess.getKLineWidthAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,85,FollowSets000.FOLLOW_85_in_ruleKLineWidth10395); 

                	newLeafNode(otherlv_3, grammarAccess.getKLineWidthAccess().getLineWidthKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4787:1: ( (lv_lineWidth_4_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4788:1: (lv_lineWidth_4_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4788:1: (lv_lineWidth_4_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4789:3: lv_lineWidth_4_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKLineWidthAccess().getLineWidthEIntParserRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKLineWidth10416);
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

            otherlv_5=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKLineWidth10428); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4817:1: entryRuleKVisibility returns [EObject current=null] : iv_ruleKVisibility= ruleKVisibility EOF ;
    public final EObject entryRuleKVisibility() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKVisibility = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4818:2: (iv_ruleKVisibility= ruleKVisibility EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4819:2: iv_ruleKVisibility= ruleKVisibility EOF
            {
             newCompositeNode(grammarAccess.getKVisibilityRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKVisibility_in_entryRuleKVisibility10464);
            iv_ruleKVisibility=ruleKVisibility();

            state._fsp--;

             current =iv_ruleKVisibility; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKVisibility10474); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4826:1: ruleKVisibility returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) ( (lv_lineVisible_1_0= 'lineVisible' ) ) ( (lv_filled_2_0= 'filled' ) ) otherlv_3= 'KVisibility' ) ;
    public final EObject ruleKVisibility() throws RecognitionException {
        EObject current = null;

        Token lv_propagateToChildren_0_0=null;
        Token lv_lineVisible_1_0=null;
        Token lv_filled_2_0=null;
        Token otherlv_3=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4829:28: ( ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) ( (lv_lineVisible_1_0= 'lineVisible' ) ) ( (lv_filled_2_0= 'filled' ) ) otherlv_3= 'KVisibility' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4830:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) ( (lv_lineVisible_1_0= 'lineVisible' ) ) ( (lv_filled_2_0= 'filled' ) ) otherlv_3= 'KVisibility' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4830:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) ( (lv_lineVisible_1_0= 'lineVisible' ) ) ( (lv_filled_2_0= 'filled' ) ) otherlv_3= 'KVisibility' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4830:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) ( (lv_lineVisible_1_0= 'lineVisible' ) ) ( (lv_filled_2_0= 'filled' ) ) otherlv_3= 'KVisibility'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4830:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4831:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4831:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4832:3: lv_propagateToChildren_0_0= 'propagateToChildren'
            {
            lv_propagateToChildren_0_0=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKVisibility10517); 

                    newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKVisibilityAccess().getPropagateToChildrenPropagateToChildrenKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKVisibilityRule());
            	        }
                   		setWithLastConsumed(current, "propagateToChildren", true, "propagateToChildren");
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4845:2: ( (lv_lineVisible_1_0= 'lineVisible' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4846:1: (lv_lineVisible_1_0= 'lineVisible' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4846:1: (lv_lineVisible_1_0= 'lineVisible' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4847:3: lv_lineVisible_1_0= 'lineVisible'
            {
            lv_lineVisible_1_0=(Token)match(input,86,FollowSets000.FOLLOW_86_in_ruleKVisibility10548); 

                    newLeafNode(lv_lineVisible_1_0, grammarAccess.getKVisibilityAccess().getLineVisibleLineVisibleKeyword_1_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKVisibilityRule());
            	        }
                   		setWithLastConsumed(current, "lineVisible", true, "lineVisible");
            	    

            }


            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4860:2: ( (lv_filled_2_0= 'filled' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4861:1: (lv_filled_2_0= 'filled' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4861:1: (lv_filled_2_0= 'filled' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4862:3: lv_filled_2_0= 'filled'
            {
            lv_filled_2_0=(Token)match(input,87,FollowSets000.FOLLOW_87_in_ruleKVisibility10579); 

                    newLeafNode(lv_filled_2_0, grammarAccess.getKVisibilityAccess().getFilledFilledKeyword_2_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKVisibilityRule());
            	        }
                   		setWithLastConsumed(current, "filled", true, "filled");
            	    

            }


            }

            otherlv_3=(Token)match(input,88,FollowSets000.FOLLOW_88_in_ruleKVisibility10604); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4887:1: entryRuleKLineStyle returns [EObject current=null] : iv_ruleKLineStyle= ruleKLineStyle EOF ;
    public final EObject entryRuleKLineStyle() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKLineStyle = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4888:2: (iv_ruleKLineStyle= ruleKLineStyle EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4889:2: iv_ruleKLineStyle= ruleKLineStyle EOF
            {
             newCompositeNode(grammarAccess.getKLineStyleRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKLineStyle_in_entryRuleKLineStyle10640);
            iv_ruleKLineStyle=ruleKLineStyle();

            state._fsp--;

             current =iv_ruleKLineStyle; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKLineStyle10650); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4896:1: ruleKLineStyle returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KLineStyle' otherlv_2= '{' otherlv_3= 'lineStyle' ( (lv_lineStyle_4_0= ruleLineStyle ) ) otherlv_5= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4899:28: ( ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KLineStyle' otherlv_2= '{' otherlv_3= 'lineStyle' ( (lv_lineStyle_4_0= ruleLineStyle ) ) otherlv_5= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4900:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KLineStyle' otherlv_2= '{' otherlv_3= 'lineStyle' ( (lv_lineStyle_4_0= ruleLineStyle ) ) otherlv_5= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4900:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KLineStyle' otherlv_2= '{' otherlv_3= 'lineStyle' ( (lv_lineStyle_4_0= ruleLineStyle ) ) otherlv_5= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4900:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KLineStyle' otherlv_2= '{' otherlv_3= 'lineStyle' ( (lv_lineStyle_4_0= ruleLineStyle ) ) otherlv_5= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4900:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4901:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4901:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4902:3: lv_propagateToChildren_0_0= 'propagateToChildren'
            {
            lv_propagateToChildren_0_0=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKLineStyle10693); 

                    newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKLineStyleAccess().getPropagateToChildrenPropagateToChildrenKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKLineStyleRule());
            	        }
                   		setWithLastConsumed(current, "propagateToChildren", true, "propagateToChildren");
            	    

            }


            }

            otherlv_1=(Token)match(input,89,FollowSets000.FOLLOW_89_in_ruleKLineStyle10718); 

                	newLeafNode(otherlv_1, grammarAccess.getKLineStyleAccess().getKLineStyleKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKLineStyle10730); 

                	newLeafNode(otherlv_2, grammarAccess.getKLineStyleAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,90,FollowSets000.FOLLOW_90_in_ruleKLineStyle10742); 

                	newLeafNode(otherlv_3, grammarAccess.getKLineStyleAccess().getLineStyleKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4927:1: ( (lv_lineStyle_4_0= ruleLineStyle ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4928:1: (lv_lineStyle_4_0= ruleLineStyle )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4928:1: (lv_lineStyle_4_0= ruleLineStyle )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4929:3: lv_lineStyle_4_0= ruleLineStyle
            {
             
            	        newCompositeNode(grammarAccess.getKLineStyleAccess().getLineStyleLineStyleEnumRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleLineStyle_in_ruleKLineStyle10763);
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

            otherlv_5=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKLineStyle10775); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4957:1: entryRuleKVerticalAlignment returns [EObject current=null] : iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF ;
    public final EObject entryRuleKVerticalAlignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKVerticalAlignment = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4958:2: (iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4959:2: iv_ruleKVerticalAlignment= ruleKVerticalAlignment EOF
            {
             newCompositeNode(grammarAccess.getKVerticalAlignmentRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKVerticalAlignment_in_entryRuleKVerticalAlignment10811);
            iv_ruleKVerticalAlignment=ruleKVerticalAlignment();

            state._fsp--;

             current =iv_ruleKVerticalAlignment; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKVerticalAlignment10821); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4966:1: ruleKVerticalAlignment returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KVerticalAlignment' otherlv_2= '{' otherlv_3= 'verticalAlignment' ( (lv_verticalAlignment_4_0= ruleVerticalAlignment ) ) otherlv_5= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4969:28: ( ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KVerticalAlignment' otherlv_2= '{' otherlv_3= 'verticalAlignment' ( (lv_verticalAlignment_4_0= ruleVerticalAlignment ) ) otherlv_5= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4970:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KVerticalAlignment' otherlv_2= '{' otherlv_3= 'verticalAlignment' ( (lv_verticalAlignment_4_0= ruleVerticalAlignment ) ) otherlv_5= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4970:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KVerticalAlignment' otherlv_2= '{' otherlv_3= 'verticalAlignment' ( (lv_verticalAlignment_4_0= ruleVerticalAlignment ) ) otherlv_5= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4970:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KVerticalAlignment' otherlv_2= '{' otherlv_3= 'verticalAlignment' ( (lv_verticalAlignment_4_0= ruleVerticalAlignment ) ) otherlv_5= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4970:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4971:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4971:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4972:3: lv_propagateToChildren_0_0= 'propagateToChildren'
            {
            lv_propagateToChildren_0_0=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKVerticalAlignment10864); 

                    newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKVerticalAlignmentAccess().getPropagateToChildrenPropagateToChildrenKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKVerticalAlignmentRule());
            	        }
                   		setWithLastConsumed(current, "propagateToChildren", true, "propagateToChildren");
            	    

            }


            }

            otherlv_1=(Token)match(input,91,FollowSets000.FOLLOW_91_in_ruleKVerticalAlignment10889); 

                	newLeafNode(otherlv_1, grammarAccess.getKVerticalAlignmentAccess().getKVerticalAlignmentKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKVerticalAlignment10901); 

                	newLeafNode(otherlv_2, grammarAccess.getKVerticalAlignmentAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,92,FollowSets000.FOLLOW_92_in_ruleKVerticalAlignment10913); 

                	newLeafNode(otherlv_3, grammarAccess.getKVerticalAlignmentAccess().getVerticalAlignmentKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4997:1: ( (lv_verticalAlignment_4_0= ruleVerticalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4998:1: (lv_verticalAlignment_4_0= ruleVerticalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4998:1: (lv_verticalAlignment_4_0= ruleVerticalAlignment )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:4999:3: lv_verticalAlignment_4_0= ruleVerticalAlignment
            {
             
            	        newCompositeNode(grammarAccess.getKVerticalAlignmentAccess().getVerticalAlignmentVerticalAlignmentEnumRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleVerticalAlignment_in_ruleKVerticalAlignment10934);
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

            otherlv_5=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKVerticalAlignment10946); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5027:1: entryRuleKHorizontalAlignment returns [EObject current=null] : iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF ;
    public final EObject entryRuleKHorizontalAlignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKHorizontalAlignment = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5028:2: (iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5029:2: iv_ruleKHorizontalAlignment= ruleKHorizontalAlignment EOF
            {
             newCompositeNode(grammarAccess.getKHorizontalAlignmentRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKHorizontalAlignment_in_entryRuleKHorizontalAlignment10982);
            iv_ruleKHorizontalAlignment=ruleKHorizontalAlignment();

            state._fsp--;

             current =iv_ruleKHorizontalAlignment; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKHorizontalAlignment10992); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5036:1: ruleKHorizontalAlignment returns [EObject current=null] : ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KHorizontalAlignment' otherlv_2= '{' otherlv_3= 'horizontalAlignment' ( (lv_horizontalAlignment_4_0= ruleHorizontalAlignment ) ) otherlv_5= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5039:28: ( ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KHorizontalAlignment' otherlv_2= '{' otherlv_3= 'horizontalAlignment' ( (lv_horizontalAlignment_4_0= ruleHorizontalAlignment ) ) otherlv_5= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5040:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KHorizontalAlignment' otherlv_2= '{' otherlv_3= 'horizontalAlignment' ( (lv_horizontalAlignment_4_0= ruleHorizontalAlignment ) ) otherlv_5= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5040:1: ( ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KHorizontalAlignment' otherlv_2= '{' otherlv_3= 'horizontalAlignment' ( (lv_horizontalAlignment_4_0= ruleHorizontalAlignment ) ) otherlv_5= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5040:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) ) otherlv_1= 'KHorizontalAlignment' otherlv_2= '{' otherlv_3= 'horizontalAlignment' ( (lv_horizontalAlignment_4_0= ruleHorizontalAlignment ) ) otherlv_5= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5040:2: ( (lv_propagateToChildren_0_0= 'propagateToChildren' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5041:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5041:1: (lv_propagateToChildren_0_0= 'propagateToChildren' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5042:3: lv_propagateToChildren_0_0= 'propagateToChildren'
            {
            lv_propagateToChildren_0_0=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKHorizontalAlignment11035); 

                    newLeafNode(lv_propagateToChildren_0_0, grammarAccess.getKHorizontalAlignmentAccess().getPropagateToChildrenPropagateToChildrenKeyword_0_0());
                

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getKHorizontalAlignmentRule());
            	        }
                   		setWithLastConsumed(current, "propagateToChildren", true, "propagateToChildren");
            	    

            }


            }

            otherlv_1=(Token)match(input,93,FollowSets000.FOLLOW_93_in_ruleKHorizontalAlignment11060); 

                	newLeafNode(otherlv_1, grammarAccess.getKHorizontalAlignmentAccess().getKHorizontalAlignmentKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKHorizontalAlignment11072); 

                	newLeafNode(otherlv_2, grammarAccess.getKHorizontalAlignmentAccess().getLeftCurlyBracketKeyword_2());
                
            otherlv_3=(Token)match(input,94,FollowSets000.FOLLOW_94_in_ruleKHorizontalAlignment11084); 

                	newLeafNode(otherlv_3, grammarAccess.getKHorizontalAlignmentAccess().getHorizontalAlignmentKeyword_3());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5067:1: ( (lv_horizontalAlignment_4_0= ruleHorizontalAlignment ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5068:1: (lv_horizontalAlignment_4_0= ruleHorizontalAlignment )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5068:1: (lv_horizontalAlignment_4_0= ruleHorizontalAlignment )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5069:3: lv_horizontalAlignment_4_0= ruleHorizontalAlignment
            {
             
            	        newCompositeNode(grammarAccess.getKHorizontalAlignmentAccess().getHorizontalAlignmentHorizontalAlignmentEnumRuleCall_4_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleHorizontalAlignment_in_ruleKHorizontalAlignment11105);
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

            otherlv_5=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKHorizontalAlignment11117); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5097:1: entryRuleEInt returns [String current=null] : iv_ruleEInt= ruleEInt EOF ;
    public final String entryRuleEInt() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEInt = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5098:2: (iv_ruleEInt= ruleEInt EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5099:2: iv_ruleEInt= ruleEInt EOF
            {
             newCompositeNode(grammarAccess.getEIntRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_entryRuleEInt11154);
            iv_ruleEInt=ruleEInt();

            state._fsp--;

             current =iv_ruleEInt.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEInt11165); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5106:1: ruleEInt returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleEInt() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5109:28: ( ( (kw= '-' )? this_INT_1= RULE_INT ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5110:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5110:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5110:2: (kw= '-' )? this_INT_1= RULE_INT
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5110:2: (kw= '-' )?
            int alt119=2;
            int LA119_0 = input.LA(1);

            if ( (LA119_0==67) ) {
                alt119=1;
            }
            switch (alt119) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5111:2: kw= '-'
                    {
                    kw=(Token)match(input,67,FollowSets000.FOLLOW_67_in_ruleEInt11204); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEIntAccess().getHyphenMinusKeyword_0()); 
                        

                    }
                    break;

            }

            this_INT_1=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEInt11221); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5131:1: entryRuleKGridPlacement returns [EObject current=null] : iv_ruleKGridPlacement= ruleKGridPlacement EOF ;
    public final EObject entryRuleKGridPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKGridPlacement = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5132:2: (iv_ruleKGridPlacement= ruleKGridPlacement EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5133:2: iv_ruleKGridPlacement= ruleKGridPlacement EOF
            {
             newCompositeNode(grammarAccess.getKGridPlacementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKGridPlacement_in_entryRuleKGridPlacement11266);
            iv_ruleKGridPlacement=ruleKGridPlacement();

            state._fsp--;

             current =iv_ruleKGridPlacement; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKGridPlacement11276); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5140:1: ruleKGridPlacement returns [EObject current=null] : (otherlv_0= 'KGridPlacement' otherlv_1= '{' otherlv_2= 'numColumns' ( (lv_numColumns_3_0= ruleEInt ) ) otherlv_4= '}' ) ;
    public final EObject ruleKGridPlacement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_numColumns_3_0 = null;


         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5143:28: ( (otherlv_0= 'KGridPlacement' otherlv_1= '{' otherlv_2= 'numColumns' ( (lv_numColumns_3_0= ruleEInt ) ) otherlv_4= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5144:1: (otherlv_0= 'KGridPlacement' otherlv_1= '{' otherlv_2= 'numColumns' ( (lv_numColumns_3_0= ruleEInt ) ) otherlv_4= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5144:1: (otherlv_0= 'KGridPlacement' otherlv_1= '{' otherlv_2= 'numColumns' ( (lv_numColumns_3_0= ruleEInt ) ) otherlv_4= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5144:3: otherlv_0= 'KGridPlacement' otherlv_1= '{' otherlv_2= 'numColumns' ( (lv_numColumns_3_0= ruleEInt ) ) otherlv_4= '}'
            {
            otherlv_0=(Token)match(input,95,FollowSets000.FOLLOW_95_in_ruleKGridPlacement11313); 

                	newLeafNode(otherlv_0, grammarAccess.getKGridPlacementAccess().getKGridPlacementKeyword_0());
                
            otherlv_1=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKGridPlacement11325); 

                	newLeafNode(otherlv_1, grammarAccess.getKGridPlacementAccess().getLeftCurlyBracketKeyword_1());
                
            otherlv_2=(Token)match(input,96,FollowSets000.FOLLOW_96_in_ruleKGridPlacement11337); 

                	newLeafNode(otherlv_2, grammarAccess.getKGridPlacementAccess().getNumColumnsKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5156:1: ( (lv_numColumns_3_0= ruleEInt ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5157:1: (lv_numColumns_3_0= ruleEInt )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5157:1: (lv_numColumns_3_0= ruleEInt )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5158:3: lv_numColumns_3_0= ruleEInt
            {
             
            	        newCompositeNode(grammarAccess.getKGridPlacementAccess().getNumColumnsEIntParserRuleCall_3_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleKGridPlacement11358);
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

            otherlv_4=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKGridPlacement11370); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5186:1: entryRuleKStackPlacement returns [EObject current=null] : iv_ruleKStackPlacement= ruleKStackPlacement EOF ;
    public final EObject entryRuleKStackPlacement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKStackPlacement = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5187:2: (iv_ruleKStackPlacement= ruleKStackPlacement EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5188:2: iv_ruleKStackPlacement= ruleKStackPlacement EOF
            {
             newCompositeNode(grammarAccess.getKStackPlacementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKStackPlacement_in_entryRuleKStackPlacement11406);
            iv_ruleKStackPlacement=ruleKStackPlacement();

            state._fsp--;

             current =iv_ruleKStackPlacement; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKStackPlacement11416); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5195:1: ruleKStackPlacement returns [EObject current=null] : ( () otherlv_1= 'KStackPlacement' ) ;
    public final EObject ruleKStackPlacement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5198:28: ( ( () otherlv_1= 'KStackPlacement' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5199:1: ( () otherlv_1= 'KStackPlacement' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5199:1: ( () otherlv_1= 'KStackPlacement' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5199:2: () otherlv_1= 'KStackPlacement'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5199:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5200:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKStackPlacementAccess().getKStackPlacementAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,97,FollowSets000.FOLLOW_97_in_ruleKStackPlacement11462); 

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


    // $ANTLR start "entryRuleKInsets"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5219:1: entryRuleKInsets returns [EObject current=null] : iv_ruleKInsets= ruleKInsets EOF ;
    public final EObject entryRuleKInsets() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKInsets = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5220:2: (iv_ruleKInsets= ruleKInsets EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5221:2: iv_ruleKInsets= ruleKInsets EOF
            {
             newCompositeNode(grammarAccess.getKInsetsRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKInsets_in_entryRuleKInsets11500);
            iv_ruleKInsets=ruleKInsets();

            state._fsp--;

             current =iv_ruleKInsets; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKInsets11510); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5228:1: ruleKInsets returns [EObject current=null] : ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5231:28: ( ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5232:1: ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5232:1: ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5232:2: () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5232:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5233:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKInsetsAccess().getKInsetsAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,98,FollowSets000.FOLLOW_98_in_ruleKInsets11556); 

                	newLeafNode(otherlv_1, grammarAccess.getKInsetsAccess().getKInsetsKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKInsets11568); 

                	newLeafNode(otherlv_2, grammarAccess.getKInsetsAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5246:1: (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )?
            int alt120=2;
            int LA120_0 = input.LA(1);

            if ( (LA120_0==99) ) {
                alt120=1;
            }
            switch (alt120) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5246:3: otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,99,FollowSets000.FOLLOW_99_in_ruleKInsets11581); 

                        	newLeafNode(otherlv_3, grammarAccess.getKInsetsAccess().getTopKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5250:1: ( (lv_top_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5251:1: (lv_top_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5251:1: (lv_top_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5252:3: lv_top_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getTopEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets11602);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5268:4: (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )?
            int alt121=2;
            int LA121_0 = input.LA(1);

            if ( (LA121_0==100) ) {
                alt121=1;
            }
            switch (alt121) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5268:6: otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,100,FollowSets000.FOLLOW_100_in_ruleKInsets11617); 

                        	newLeafNode(otherlv_5, grammarAccess.getKInsetsAccess().getBottomKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5272:1: ( (lv_bottom_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5273:1: (lv_bottom_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5273:1: (lv_bottom_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5274:3: lv_bottom_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getBottomEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets11638);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5290:4: (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )?
            int alt122=2;
            int LA122_0 = input.LA(1);

            if ( (LA122_0==101) ) {
                alt122=1;
            }
            switch (alt122) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5290:6: otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,101,FollowSets000.FOLLOW_101_in_ruleKInsets11653); 

                        	newLeafNode(otherlv_7, grammarAccess.getKInsetsAccess().getLeftKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5294:1: ( (lv_left_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5295:1: (lv_left_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5295:1: (lv_left_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5296:3: lv_left_8_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getLeftEFloatParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets11674);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5312:4: (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )?
            int alt123=2;
            int LA123_0 = input.LA(1);

            if ( (LA123_0==102) ) {
                alt123=1;
            }
            switch (alt123) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5312:6: otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) )
                    {
                    otherlv_9=(Token)match(input,102,FollowSets000.FOLLOW_102_in_ruleKInsets11689); 

                        	newLeafNode(otherlv_9, grammarAccess.getKInsetsAccess().getRightKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5316:1: ( (lv_right_10_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5317:1: (lv_right_10_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5317:1: (lv_right_10_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5318:3: lv_right_10_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getRightEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets11710);
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

            otherlv_11=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKInsets11724); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5348:1: entryRuleKPoint returns [EObject current=null] : iv_ruleKPoint= ruleKPoint EOF ;
    public final EObject entryRuleKPoint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPoint = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5349:2: (iv_ruleKPoint= ruleKPoint EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5350:2: iv_ruleKPoint= ruleKPoint EOF
            {
             newCompositeNode(grammarAccess.getKPointRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPoint_in_entryRuleKPoint11762);
            iv_ruleKPoint=ruleKPoint();

            state._fsp--;

             current =iv_ruleKPoint; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPoint11772); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5357:1: ruleKPoint returns [EObject current=null] : ( () otherlv_1= 'KPoint' otherlv_2= '{' (otherlv_3= 'x' ( (lv_x_4_0= ruleEFloat ) ) )? (otherlv_5= 'y' ( (lv_y_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5360:28: ( ( () otherlv_1= 'KPoint' otherlv_2= '{' (otherlv_3= 'x' ( (lv_x_4_0= ruleEFloat ) ) )? (otherlv_5= 'y' ( (lv_y_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5361:1: ( () otherlv_1= 'KPoint' otherlv_2= '{' (otherlv_3= 'x' ( (lv_x_4_0= ruleEFloat ) ) )? (otherlv_5= 'y' ( (lv_y_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5361:1: ( () otherlv_1= 'KPoint' otherlv_2= '{' (otherlv_3= 'x' ( (lv_x_4_0= ruleEFloat ) ) )? (otherlv_5= 'y' ( (lv_y_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5361:2: () otherlv_1= 'KPoint' otherlv_2= '{' (otherlv_3= 'x' ( (lv_x_4_0= ruleEFloat ) ) )? (otherlv_5= 'y' ( (lv_y_6_0= ruleEFloat ) ) )? otherlv_7= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5361:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5362:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPointAccess().getKPointAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,103,FollowSets000.FOLLOW_103_in_ruleKPoint11818); 

                	newLeafNode(otherlv_1, grammarAccess.getKPointAccess().getKPointKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPoint11830); 

                	newLeafNode(otherlv_2, grammarAccess.getKPointAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5375:1: (otherlv_3= 'x' ( (lv_x_4_0= ruleEFloat ) ) )?
            int alt124=2;
            int LA124_0 = input.LA(1);

            if ( (LA124_0==72) ) {
                alt124=1;
            }
            switch (alt124) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5375:3: otherlv_3= 'x' ( (lv_x_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,72,FollowSets000.FOLLOW_72_in_ruleKPoint11843); 

                        	newLeafNode(otherlv_3, grammarAccess.getKPointAccess().getXKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5379:1: ( (lv_x_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5380:1: (lv_x_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5380:1: (lv_x_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5381:3: lv_x_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPointAccess().getXEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKPoint11864);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5397:4: (otherlv_5= 'y' ( (lv_y_6_0= ruleEFloat ) ) )?
            int alt125=2;
            int LA125_0 = input.LA(1);

            if ( (LA125_0==73) ) {
                alt125=1;
            }
            switch (alt125) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5397:6: otherlv_5= 'y' ( (lv_y_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,73,FollowSets000.FOLLOW_73_in_ruleKPoint11879); 

                        	newLeafNode(otherlv_5, grammarAccess.getKPointAccess().getYKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5401:1: ( (lv_y_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5402:1: (lv_y_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5402:1: (lv_y_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5403:3: lv_y_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPointAccess().getYEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKPoint11900);
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

            otherlv_7=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKPoint11914); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5431:1: ruleLineStyle returns [Enumerator current=null] : ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) ) ;
    public final Enumerator ruleLineStyle() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5433:28: ( ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5434:1: ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5434:1: ( (enumLiteral_0= 'SOLID' ) | (enumLiteral_1= 'DASH' ) | (enumLiteral_2= 'DOT' ) | (enumLiteral_3= 'DASHDOT' ) | (enumLiteral_4= 'DASHDOTDOT' ) )
            int alt126=5;
            switch ( input.LA(1) ) {
            case 104:
                {
                alt126=1;
                }
                break;
            case 105:
                {
                alt126=2;
                }
                break;
            case 106:
                {
                alt126=3;
                }
                break;
            case 107:
                {
                alt126=4;
                }
                break;
            case 108:
                {
                alt126=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 126, 0, input);

                throw nvae;
            }

            switch (alt126) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5434:2: (enumLiteral_0= 'SOLID' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5434:2: (enumLiteral_0= 'SOLID' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5434:4: enumLiteral_0= 'SOLID'
                    {
                    enumLiteral_0=(Token)match(input,104,FollowSets000.FOLLOW_104_in_ruleLineStyle11964); 

                            current = grammarAccess.getLineStyleAccess().getSOLIDEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getLineStyleAccess().getSOLIDEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5440:6: (enumLiteral_1= 'DASH' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5440:6: (enumLiteral_1= 'DASH' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5440:8: enumLiteral_1= 'DASH'
                    {
                    enumLiteral_1=(Token)match(input,105,FollowSets000.FOLLOW_105_in_ruleLineStyle11981); 

                            current = grammarAccess.getLineStyleAccess().getDASHEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getLineStyleAccess().getDASHEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5446:6: (enumLiteral_2= 'DOT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5446:6: (enumLiteral_2= 'DOT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5446:8: enumLiteral_2= 'DOT'
                    {
                    enumLiteral_2=(Token)match(input,106,FollowSets000.FOLLOW_106_in_ruleLineStyle11998); 

                            current = grammarAccess.getLineStyleAccess().getDOTEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getLineStyleAccess().getDOTEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5452:6: (enumLiteral_3= 'DASHDOT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5452:6: (enumLiteral_3= 'DASHDOT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5452:8: enumLiteral_3= 'DASHDOT'
                    {
                    enumLiteral_3=(Token)match(input,107,FollowSets000.FOLLOW_107_in_ruleLineStyle12015); 

                            current = grammarAccess.getLineStyleAccess().getDASHDOTEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getLineStyleAccess().getDASHDOTEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5458:6: (enumLiteral_4= 'DASHDOTDOT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5458:6: (enumLiteral_4= 'DASHDOTDOT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5458:8: enumLiteral_4= 'DASHDOTDOT'
                    {
                    enumLiteral_4=(Token)match(input,108,FollowSets000.FOLLOW_108_in_ruleLineStyle12032); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5468:1: ruleVerticalAlignment returns [Enumerator current=null] : ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) ) ;
    public final Enumerator ruleVerticalAlignment() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5470:28: ( ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5471:1: ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5471:1: ( (enumLiteral_0= 'TOP' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'BOTTOM' ) )
            int alt127=3;
            switch ( input.LA(1) ) {
            case 109:
                {
                alt127=1;
                }
                break;
            case 110:
                {
                alt127=2;
                }
                break;
            case 111:
                {
                alt127=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 127, 0, input);

                throw nvae;
            }

            switch (alt127) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5471:2: (enumLiteral_0= 'TOP' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5471:2: (enumLiteral_0= 'TOP' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5471:4: enumLiteral_0= 'TOP'
                    {
                    enumLiteral_0=(Token)match(input,109,FollowSets000.FOLLOW_109_in_ruleVerticalAlignment12077); 

                            current = grammarAccess.getVerticalAlignmentAccess().getTOPEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getVerticalAlignmentAccess().getTOPEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5477:6: (enumLiteral_1= 'CENTER' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5477:6: (enumLiteral_1= 'CENTER' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5477:8: enumLiteral_1= 'CENTER'
                    {
                    enumLiteral_1=(Token)match(input,110,FollowSets000.FOLLOW_110_in_ruleVerticalAlignment12094); 

                            current = grammarAccess.getVerticalAlignmentAccess().getCENTEREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getVerticalAlignmentAccess().getCENTEREnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5483:6: (enumLiteral_2= 'BOTTOM' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5483:6: (enumLiteral_2= 'BOTTOM' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5483:8: enumLiteral_2= 'BOTTOM'
                    {
                    enumLiteral_2=(Token)match(input,111,FollowSets000.FOLLOW_111_in_ruleVerticalAlignment12111); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5493:1: ruleHorizontalAlignment returns [Enumerator current=null] : ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) ) ;
    public final Enumerator ruleHorizontalAlignment() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5495:28: ( ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5496:1: ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5496:1: ( (enumLiteral_0= 'LEFT' ) | (enumLiteral_1= 'CENTER' ) | (enumLiteral_2= 'RIGHT' ) )
            int alt128=3;
            switch ( input.LA(1) ) {
            case 112:
                {
                alt128=1;
                }
                break;
            case 110:
                {
                alt128=2;
                }
                break;
            case 113:
                {
                alt128=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 128, 0, input);

                throw nvae;
            }

            switch (alt128) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5496:2: (enumLiteral_0= 'LEFT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5496:2: (enumLiteral_0= 'LEFT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5496:4: enumLiteral_0= 'LEFT'
                    {
                    enumLiteral_0=(Token)match(input,112,FollowSets000.FOLLOW_112_in_ruleHorizontalAlignment12156); 

                            current = grammarAccess.getHorizontalAlignmentAccess().getLEFTEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getHorizontalAlignmentAccess().getLEFTEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5502:6: (enumLiteral_1= 'CENTER' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5502:6: (enumLiteral_1= 'CENTER' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5502:8: enumLiteral_1= 'CENTER'
                    {
                    enumLiteral_1=(Token)match(input,110,FollowSets000.FOLLOW_110_in_ruleHorizontalAlignment12173); 

                            current = grammarAccess.getHorizontalAlignmentAccess().getCENTEREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getHorizontalAlignmentAccess().getCENTEREnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5508:6: (enumLiteral_2= 'RIGHT' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5508:6: (enumLiteral_2= 'RIGHT' )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/core/krendering/text/parser/antlr/internal/InternalKRendering.g:5508:8: enumLiteral_2= 'RIGHT'
                    {
                    enumLiteral_2=(Token)match(input,113,FollowSets000.FOLLOW_113_in_ruleHorizontalAlignment12190); 

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


    protected DFA5 dfa5 = new DFA5(this);
    static final String DFA5_eotS =
        "\12\uffff";
    static final String DFA5_eofS =
        "\12\uffff";
    static final String DFA5_minS =
        "\1\27\1\30\10\uffff";
    static final String DFA5_maxS =
        "\1\27\1\135\10\uffff";
    static final String DFA5_acceptS =
        "\2\uffff\1\4\1\6\1\10\1\5\1\3\1\1\1\7\1\2";
    static final String DFA5_specialS =
        "\12\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\1",
            "\1\7\66\uffff\1\11\3\uffff\1\6\1\2\1\uffff\1\5\2\uffff\1\3"+
            "\1\uffff\1\10\1\uffff\1\4",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
    static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
    static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
    static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
    static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
    static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
    static final short[][] DFA5_transition;

    static {
        int numStates = DFA5_transitionS.length;
        DFA5_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
        }
    }

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = DFA5_eot;
            this.eof = DFA5_eof;
            this.min = DFA5_min;
            this.max = DFA5_max;
            this.accept = DFA5_accept;
            this.special = DFA5_special;
            this.transition = DFA5_transition;
        }
        public String getDescription() {
            return "382:1: (this_KStyle_Impl_0= ruleKStyle_Impl | this_KForegroundColor_1= ruleKForegroundColor | this_KBackgroundColor_2= ruleKBackgroundColor | this_KLineWidth_3= ruleKLineWidth | this_KVisibility_4= ruleKVisibility | this_KLineStyle_5= ruleKLineStyle | this_KVerticalAlignment_6= ruleKVerticalAlignment | this_KHorizontalAlignment_7= ruleKHorizontalAlignment )";
        }
    }
 

    
    private static class FollowSets000 {
        public static final BitSet FOLLOW_ruleKRenderingLibrary_in_entryRuleKRenderingLibrary75 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRenderingLibrary85 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_11_in_ruleKRenderingLibrary131 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRenderingLibrary143 = new BitSet(new long[]{0x000000000000A000L});
        public static final BitSet FOLLOW_13_in_ruleKRenderingLibrary156 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRenderingLibrary168 = new BitSet(new long[]{0x0000532732010000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRenderingLibrary189 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleKRenderingLibrary202 = new BitSet(new long[]{0x0000532732010000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRenderingLibrary223 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleKRenderingLibrary237 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKRenderingLibrary251 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRendering_in_entryRuleKRendering287 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRendering297 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKEllipse_in_ruleKRendering344 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRectangle_in_ruleKRendering371 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRoundedRectangle_in_ruleKRendering398 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolyline_Impl_in_ruleKRendering425 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolygon_in_ruleKRendering452 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKImage_in_ruleKRendering479 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKArc_in_ruleKRendering506 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRenderingRef_in_ruleKRendering533 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKChildArea_in_ruleKRendering560 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKText_in_ruleKRendering587 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKCustomRendering_in_ruleKRendering614 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKSpline_in_ruleKRendering641 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_entryRuleKPlacementData676 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPlacementData686 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDecoratorPlacementData_in_ruleKPlacementData733 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacementData_in_ruleKPlacementData760 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacementData_in_ruleKPlacementData787 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDirectPlacementData_in_ruleKPlacementData814 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolylinePlacementData_in_ruleKPlacementData841 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStyle_in_entryRuleKStyle876 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKStyle886 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStyle_Impl_in_ruleKStyle933 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForegroundColor_in_ruleKStyle960 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackgroundColor_in_ruleKStyle987 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineWidth_in_ruleKStyle1014 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVisibility_in_ruleKStyle1041 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineStyle_in_ruleKStyle1068 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVerticalAlignment_in_ruleKStyle1095 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKHorizontalAlignment_in_ruleKStyle1122 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPlacement_in_entryRuleKPlacement1157 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPlacement1167 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacement_in_ruleKPlacement1214 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacement_in_ruleKPlacement1241 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKXPosition_in_entryRuleKXPosition1276 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKXPosition1286 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLeftPosition_in_ruleKXPosition1333 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRightPosition_in_ruleKXPosition1360 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKYPosition_in_entryRuleKYPosition1395 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKYPosition1405 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKTopPosition_in_ruleKYPosition1452 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBottomPosition_in_ruleKYPosition1479 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_entryRuleEString1517 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEString1528 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleEString1568 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleEString1594 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRenderingRef_in_entryRuleKRenderingRef1639 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRenderingRef1649 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_16_in_ruleKRenderingRef1686 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRenderingRef1698 = new BitSet(new long[]{0x0000000000120000L});
        public static final BitSet FOLLOW_17_in_ruleKRenderingRef1711 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKRenderingRef1723 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKRenderingRef1746 = new BitSet(new long[]{0x0000000000084000L});
        public static final BitSet FOLLOW_14_in_ruleKRenderingRef1759 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKRenderingRef1782 = new BitSet(new long[]{0x0000000000084000L});
        public static final BitSet FOLLOW_19_in_ruleKRenderingRef1796 = new BitSet(new long[]{0x0000000000100000L});
        public static final BitSet FOLLOW_20_in_ruleKRenderingRef1810 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKRenderingRef1833 = new BitSet(new long[]{0x0000000000608000L});
        public static final BitSet FOLLOW_21_in_ruleKRenderingRef1846 = new BitSet(new long[]{0x4210800000000000L,0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRenderingRef1867 = new BitSet(new long[]{0x0000000000408000L});
        public static final BitSet FOLLOW_22_in_ruleKRenderingRef1882 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRenderingRef1894 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRenderingRef1915 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleKRenderingRef1928 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRenderingRef1949 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleKRenderingRef1963 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKRenderingRef1977 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStyle_Impl_in_entryRuleKStyle_Impl2013 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKStyle_Impl2023 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_23_in_ruleKStyle_Impl2066 = new BitSet(new long[]{0x0000000001000000L});
        public static final BitSet FOLLOW_24_in_ruleKStyle_Impl2091 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKEllipse_in_entryRuleKEllipse2127 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKEllipse2137 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_25_in_ruleKEllipse2183 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKEllipse2195 = new BitSet(new long[]{0x000000000C628000L});
        public static final BitSet FOLLOW_17_in_ruleKEllipse2208 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKEllipse2220 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKEllipse2243 = new BitSet(new long[]{0x0000000000084000L});
        public static final BitSet FOLLOW_14_in_ruleKEllipse2256 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKEllipse2279 = new BitSet(new long[]{0x0000000000084000L});
        public static final BitSet FOLLOW_19_in_ruleKEllipse2293 = new BitSet(new long[]{0x000000000C608000L});
        public static final BitSet FOLLOW_21_in_ruleKEllipse2308 = new BitSet(new long[]{0x4210800000000000L,0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKEllipse2329 = new BitSet(new long[]{0x000000000C408000L});
        public static final BitSet FOLLOW_22_in_ruleKEllipse2344 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKEllipse2356 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKEllipse2377 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleKEllipse2390 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKEllipse2411 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleKEllipse2425 = new BitSet(new long[]{0x000000000C008000L});
        public static final BitSet FOLLOW_26_in_ruleKEllipse2440 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKEllipse2452 = new BitSet(new long[]{0x0000532732010000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKEllipse2473 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleKEllipse2486 = new BitSet(new long[]{0x0000532732010000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKEllipse2507 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleKEllipse2521 = new BitSet(new long[]{0x0000000008008000L});
        public static final BitSet FOLLOW_27_in_ruleKEllipse2536 = new BitSet(new long[]{0x0000000000000000L,0x0000000280000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKEllipse2557 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKEllipse2571 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRectangle_in_entryRuleKRectangle2607 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRectangle2617 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_28_in_ruleKRectangle2663 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRectangle2675 = new BitSet(new long[]{0x000000000C628000L});
        public static final BitSet FOLLOW_17_in_ruleKRectangle2688 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKRectangle2700 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKRectangle2723 = new BitSet(new long[]{0x0000000000084000L});
        public static final BitSet FOLLOW_14_in_ruleKRectangle2736 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKRectangle2759 = new BitSet(new long[]{0x0000000000084000L});
        public static final BitSet FOLLOW_19_in_ruleKRectangle2773 = new BitSet(new long[]{0x000000000C608000L});
        public static final BitSet FOLLOW_21_in_ruleKRectangle2788 = new BitSet(new long[]{0x4210800000000000L,0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRectangle2809 = new BitSet(new long[]{0x000000000C408000L});
        public static final BitSet FOLLOW_22_in_ruleKRectangle2824 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRectangle2836 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRectangle2857 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleKRectangle2870 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRectangle2891 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleKRectangle2905 = new BitSet(new long[]{0x000000000C008000L});
        public static final BitSet FOLLOW_26_in_ruleKRectangle2920 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRectangle2932 = new BitSet(new long[]{0x0000532732010000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRectangle2953 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleKRectangle2966 = new BitSet(new long[]{0x0000532732010000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRectangle2987 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleKRectangle3001 = new BitSet(new long[]{0x0000000008008000L});
        public static final BitSet FOLLOW_27_in_ruleKRectangle3016 = new BitSet(new long[]{0x0000000000000000L,0x0000000280000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKRectangle3037 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKRectangle3051 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRoundedRectangle_in_entryRuleKRoundedRectangle3087 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRoundedRectangle3097 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_29_in_ruleKRoundedRectangle3134 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRoundedRectangle3146 = new BitSet(new long[]{0x0000000040000000L});
        public static final BitSet FOLLOW_30_in_ruleKRoundedRectangle3158 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRoundedRectangle3179 = new BitSet(new long[]{0x0000000080000000L});
        public static final BitSet FOLLOW_31_in_ruleKRoundedRectangle3191 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRoundedRectangle3212 = new BitSet(new long[]{0x000000000C628000L});
        public static final BitSet FOLLOW_17_in_ruleKRoundedRectangle3225 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKRoundedRectangle3237 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKRoundedRectangle3260 = new BitSet(new long[]{0x0000000000084000L});
        public static final BitSet FOLLOW_14_in_ruleKRoundedRectangle3273 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKRoundedRectangle3296 = new BitSet(new long[]{0x0000000000084000L});
        public static final BitSet FOLLOW_19_in_ruleKRoundedRectangle3310 = new BitSet(new long[]{0x000000000C608000L});
        public static final BitSet FOLLOW_21_in_ruleKRoundedRectangle3325 = new BitSet(new long[]{0x4210800000000000L,0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKRoundedRectangle3346 = new BitSet(new long[]{0x000000000C408000L});
        public static final BitSet FOLLOW_22_in_ruleKRoundedRectangle3361 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRoundedRectangle3373 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRoundedRectangle3394 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleKRoundedRectangle3407 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKRoundedRectangle3428 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleKRoundedRectangle3442 = new BitSet(new long[]{0x000000000C008000L});
        public static final BitSet FOLLOW_26_in_ruleKRoundedRectangle3457 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRoundedRectangle3469 = new BitSet(new long[]{0x0000532732010000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRoundedRectangle3490 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleKRoundedRectangle3503 = new BitSet(new long[]{0x0000532732010000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKRoundedRectangle3524 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleKRoundedRectangle3538 = new BitSet(new long[]{0x0000000008008000L});
        public static final BitSet FOLLOW_27_in_ruleKRoundedRectangle3553 = new BitSet(new long[]{0x0000000000000000L,0x0000000280000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKRoundedRectangle3574 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKRoundedRectangle3588 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolyline_Impl_in_entryRuleKPolyline_Impl3624 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPolyline_Impl3634 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_32_in_ruleKPolyline_Impl3680 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPolyline_Impl3692 = new BitSet(new long[]{0x000000000C628000L});
        public static final BitSet FOLLOW_17_in_ruleKPolyline_Impl3705 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKPolyline_Impl3717 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKPolyline_Impl3740 = new BitSet(new long[]{0x0000000000084000L});
        public static final BitSet FOLLOW_14_in_ruleKPolyline_Impl3753 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKPolyline_Impl3776 = new BitSet(new long[]{0x0000000000084000L});
        public static final BitSet FOLLOW_19_in_ruleKPolyline_Impl3790 = new BitSet(new long[]{0x000000000C608000L});
        public static final BitSet FOLLOW_21_in_ruleKPolyline_Impl3805 = new BitSet(new long[]{0x4210800000000000L,0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKPolyline_Impl3826 = new BitSet(new long[]{0x000000000C408000L});
        public static final BitSet FOLLOW_22_in_ruleKPolyline_Impl3841 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPolyline_Impl3853 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolyline_Impl3874 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleKPolyline_Impl3887 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolyline_Impl3908 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleKPolyline_Impl3922 = new BitSet(new long[]{0x000000000C008000L});
        public static final BitSet FOLLOW_26_in_ruleKPolyline_Impl3937 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPolyline_Impl3949 = new BitSet(new long[]{0x0000532732010000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolyline_Impl3970 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleKPolyline_Impl3983 = new BitSet(new long[]{0x0000532732010000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolyline_Impl4004 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleKPolyline_Impl4018 = new BitSet(new long[]{0x0000000008008000L});
        public static final BitSet FOLLOW_27_in_ruleKPolyline_Impl4033 = new BitSet(new long[]{0x0000000000000000L,0x0000000280000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKPolyline_Impl4054 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKPolyline_Impl4068 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolygon_in_entryRuleKPolygon4104 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPolygon4114 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_33_in_ruleKPolygon4160 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPolygon4172 = new BitSet(new long[]{0x000000000C628000L});
        public static final BitSet FOLLOW_17_in_ruleKPolygon4185 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKPolygon4197 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKPolygon4220 = new BitSet(new long[]{0x0000000000084000L});
        public static final BitSet FOLLOW_14_in_ruleKPolygon4233 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKPolygon4256 = new BitSet(new long[]{0x0000000000084000L});
        public static final BitSet FOLLOW_19_in_ruleKPolygon4270 = new BitSet(new long[]{0x000000000C608000L});
        public static final BitSet FOLLOW_21_in_ruleKPolygon4285 = new BitSet(new long[]{0x4210800000000000L,0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKPolygon4306 = new BitSet(new long[]{0x000000000C408000L});
        public static final BitSet FOLLOW_22_in_ruleKPolygon4321 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPolygon4333 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolygon4354 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleKPolygon4367 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKPolygon4388 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleKPolygon4402 = new BitSet(new long[]{0x000000000C008000L});
        public static final BitSet FOLLOW_26_in_ruleKPolygon4417 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPolygon4429 = new BitSet(new long[]{0x0000532732010000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolygon4450 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleKPolygon4463 = new BitSet(new long[]{0x0000532732010000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKPolygon4484 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleKPolygon4498 = new BitSet(new long[]{0x0000000008008000L});
        public static final BitSet FOLLOW_27_in_ruleKPolygon4513 = new BitSet(new long[]{0x0000000000000000L,0x0000000280000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKPolygon4534 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKPolygon4548 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKImage_in_entryRuleKImage4584 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKImage4594 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_34_in_ruleKImage4631 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKImage4643 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_35_in_ruleKImage4655 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKImage4676 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_36_in_ruleKImage4688 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKImage4709 = new BitSet(new long[]{0x000000000C628000L});
        public static final BitSet FOLLOW_17_in_ruleKImage4722 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKImage4734 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKImage4757 = new BitSet(new long[]{0x0000000000084000L});
        public static final BitSet FOLLOW_14_in_ruleKImage4770 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKImage4793 = new BitSet(new long[]{0x0000000000084000L});
        public static final BitSet FOLLOW_19_in_ruleKImage4807 = new BitSet(new long[]{0x000000000C608000L});
        public static final BitSet FOLLOW_21_in_ruleKImage4822 = new BitSet(new long[]{0x4210800000000000L,0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKImage4843 = new BitSet(new long[]{0x000000000C408000L});
        public static final BitSet FOLLOW_22_in_ruleKImage4858 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKImage4870 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKImage4891 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleKImage4904 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKImage4925 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleKImage4939 = new BitSet(new long[]{0x000000000C008000L});
        public static final BitSet FOLLOW_26_in_ruleKImage4954 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKImage4966 = new BitSet(new long[]{0x0000532732010000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKImage4987 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleKImage5000 = new BitSet(new long[]{0x0000532732010000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKImage5021 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleKImage5035 = new BitSet(new long[]{0x0000000008008000L});
        public static final BitSet FOLLOW_27_in_ruleKImage5050 = new BitSet(new long[]{0x0000000000000000L,0x0000000280000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKImage5071 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKImage5085 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKArc_in_entryRuleKArc5121 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKArc5131 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_37_in_ruleKArc5177 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKArc5189 = new BitSet(new long[]{0x000000C00C628000L});
        public static final BitSet FOLLOW_38_in_ruleKArc5202 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKArc5223 = new BitSet(new long[]{0x000000800C628000L});
        public static final BitSet FOLLOW_39_in_ruleKArc5238 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKArc5259 = new BitSet(new long[]{0x000000000C628000L});
        public static final BitSet FOLLOW_17_in_ruleKArc5274 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKArc5286 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKArc5309 = new BitSet(new long[]{0x0000000000084000L});
        public static final BitSet FOLLOW_14_in_ruleKArc5322 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKArc5345 = new BitSet(new long[]{0x0000000000084000L});
        public static final BitSet FOLLOW_19_in_ruleKArc5359 = new BitSet(new long[]{0x000000000C608000L});
        public static final BitSet FOLLOW_21_in_ruleKArc5374 = new BitSet(new long[]{0x4210800000000000L,0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKArc5395 = new BitSet(new long[]{0x000000000C408000L});
        public static final BitSet FOLLOW_22_in_ruleKArc5410 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKArc5422 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKArc5443 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleKArc5456 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKArc5477 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleKArc5491 = new BitSet(new long[]{0x000000000C008000L});
        public static final BitSet FOLLOW_26_in_ruleKArc5506 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKArc5518 = new BitSet(new long[]{0x0000532732010000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKArc5539 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleKArc5552 = new BitSet(new long[]{0x0000532732010000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKArc5573 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleKArc5587 = new BitSet(new long[]{0x0000000008008000L});
        public static final BitSet FOLLOW_27_in_ruleKArc5602 = new BitSet(new long[]{0x0000000000000000L,0x0000000280000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKArc5623 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKArc5637 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKChildArea_in_entryRuleKChildArea5673 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKChildArea5683 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_40_in_ruleKChildArea5729 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKChildArea5741 = new BitSet(new long[]{0x0000000000628000L});
        public static final BitSet FOLLOW_17_in_ruleKChildArea5754 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKChildArea5766 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKChildArea5789 = new BitSet(new long[]{0x0000000000084000L});
        public static final BitSet FOLLOW_14_in_ruleKChildArea5802 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKChildArea5825 = new BitSet(new long[]{0x0000000000084000L});
        public static final BitSet FOLLOW_19_in_ruleKChildArea5839 = new BitSet(new long[]{0x0000000000608000L});
        public static final BitSet FOLLOW_21_in_ruleKChildArea5854 = new BitSet(new long[]{0x4210800000000000L,0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKChildArea5875 = new BitSet(new long[]{0x0000000000408000L});
        public static final BitSet FOLLOW_22_in_ruleKChildArea5890 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKChildArea5902 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKChildArea5923 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleKChildArea5936 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKChildArea5957 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleKChildArea5971 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKChildArea5985 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKText_in_entryRuleKText6021 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKText6031 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_41_in_ruleKText6074 = new BitSet(new long[]{0x0000040000000000L});
        public static final BitSet FOLLOW_42_in_ruleKText6099 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKText6111 = new BitSet(new long[]{0x000008000C628000L});
        public static final BitSet FOLLOW_43_in_ruleKText6124 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKText6145 = new BitSet(new long[]{0x000000000C628000L});
        public static final BitSet FOLLOW_17_in_ruleKText6160 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKText6172 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKText6195 = new BitSet(new long[]{0x0000000000084000L});
        public static final BitSet FOLLOW_14_in_ruleKText6208 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKText6231 = new BitSet(new long[]{0x0000000000084000L});
        public static final BitSet FOLLOW_19_in_ruleKText6245 = new BitSet(new long[]{0x000000000C608000L});
        public static final BitSet FOLLOW_21_in_ruleKText6260 = new BitSet(new long[]{0x4210800000000000L,0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKText6281 = new BitSet(new long[]{0x000000000C408000L});
        public static final BitSet FOLLOW_22_in_ruleKText6296 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKText6308 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKText6329 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleKText6342 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKText6363 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleKText6377 = new BitSet(new long[]{0x000000000C008000L});
        public static final BitSet FOLLOW_26_in_ruleKText6392 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKText6404 = new BitSet(new long[]{0x0000532732010000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKText6425 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleKText6438 = new BitSet(new long[]{0x0000532732010000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKText6459 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleKText6473 = new BitSet(new long[]{0x0000000008008000L});
        public static final BitSet FOLLOW_27_in_ruleKText6488 = new BitSet(new long[]{0x0000000000000000L,0x0000000280000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKText6509 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKText6523 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKCustomRendering_in_entryRuleKCustomRendering6559 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKCustomRendering6569 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_44_in_ruleKCustomRendering6606 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKCustomRendering6618 = new BitSet(new long[]{0x0000200000000000L});
        public static final BitSet FOLLOW_45_in_ruleKCustomRendering6630 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKCustomRendering6651 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_35_in_ruleKCustomRendering6663 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKCustomRendering6684 = new BitSet(new long[]{0x000000000C628000L});
        public static final BitSet FOLLOW_17_in_ruleKCustomRendering6697 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKCustomRendering6709 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKCustomRendering6732 = new BitSet(new long[]{0x0000000000084000L});
        public static final BitSet FOLLOW_14_in_ruleKCustomRendering6745 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKCustomRendering6768 = new BitSet(new long[]{0x0000000000084000L});
        public static final BitSet FOLLOW_19_in_ruleKCustomRendering6782 = new BitSet(new long[]{0x000000000C608000L});
        public static final BitSet FOLLOW_21_in_ruleKCustomRendering6797 = new BitSet(new long[]{0x4210800000000000L,0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKCustomRendering6818 = new BitSet(new long[]{0x000000000C408000L});
        public static final BitSet FOLLOW_22_in_ruleKCustomRendering6833 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKCustomRendering6845 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKCustomRendering6866 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleKCustomRendering6879 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKCustomRendering6900 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleKCustomRendering6914 = new BitSet(new long[]{0x000000000C008000L});
        public static final BitSet FOLLOW_26_in_ruleKCustomRendering6929 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKCustomRendering6941 = new BitSet(new long[]{0x0000532732010000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKCustomRendering6962 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleKCustomRendering6975 = new BitSet(new long[]{0x0000532732010000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKCustomRendering6996 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleKCustomRendering7010 = new BitSet(new long[]{0x0000000008008000L});
        public static final BitSet FOLLOW_27_in_ruleKCustomRendering7025 = new BitSet(new long[]{0x0000000000000000L,0x0000000280000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKCustomRendering7046 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKCustomRendering7060 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKSpline_in_entryRuleKSpline7096 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKSpline7106 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_46_in_ruleKSpline7152 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKSpline7164 = new BitSet(new long[]{0x000000000C628000L});
        public static final BitSet FOLLOW_17_in_ruleKSpline7177 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKSpline7189 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKSpline7212 = new BitSet(new long[]{0x0000000000084000L});
        public static final BitSet FOLLOW_14_in_ruleKSpline7225 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleKSpline7248 = new BitSet(new long[]{0x0000000000084000L});
        public static final BitSet FOLLOW_19_in_ruleKSpline7262 = new BitSet(new long[]{0x000000000C608000L});
        public static final BitSet FOLLOW_21_in_ruleKSpline7277 = new BitSet(new long[]{0x4210800000000000L,0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPlacementData_in_ruleKSpline7298 = new BitSet(new long[]{0x000000000C408000L});
        public static final BitSet FOLLOW_22_in_ruleKSpline7313 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKSpline7325 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKSpline7346 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleKSpline7359 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleKStyle_in_ruleKSpline7380 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleKSpline7394 = new BitSet(new long[]{0x000000000C008000L});
        public static final BitSet FOLLOW_26_in_ruleKSpline7409 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKSpline7421 = new BitSet(new long[]{0x0000532732010000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKSpline7442 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleKSpline7455 = new BitSet(new long[]{0x0000532732010000L});
        public static final BitSet FOLLOW_ruleKRendering_in_ruleKSpline7476 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleKSpline7490 = new BitSet(new long[]{0x0000000008008000L});
        public static final BitSet FOLLOW_27_in_ruleKSpline7505 = new BitSet(new long[]{0x0000000000000000L,0x0000000280000000L});
        public static final BitSet FOLLOW_ruleKPlacement_in_ruleKSpline7526 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKSpline7540 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDecoratorPlacementData_in_entryRuleKDecoratorPlacementData7576 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKDecoratorPlacementData7586 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_47_in_ruleKDecoratorPlacementData7629 = new BitSet(new long[]{0x0001000000000000L});
        public static final BitSet FOLLOW_48_in_ruleKDecoratorPlacementData7654 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKDecoratorPlacementData7666 = new BitSet(new long[]{0x0002000000000000L});
        public static final BitSet FOLLOW_49_in_ruleKDecoratorPlacementData7678 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7699 = new BitSet(new long[]{0x000C000000008000L});
        public static final BitSet FOLLOW_50_in_ruleKDecoratorPlacementData7712 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7733 = new BitSet(new long[]{0x0008000000008000L});
        public static final BitSet FOLLOW_51_in_ruleKDecoratorPlacementData7748 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKDecoratorPlacementData7769 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKDecoratorPlacementData7783 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacementData_in_entryRuleKGridPlacementData7819 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKGridPlacementData7829 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_52_in_ruleKGridPlacementData7866 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKGridPlacementData7878 = new BitSet(new long[]{0x0020000000000000L});
        public static final BitSet FOLLOW_53_in_ruleKGridPlacementData7890 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData7911 = new BitSet(new long[]{0x0040000000000000L});
        public static final BitSet FOLLOW_54_in_ruleKGridPlacementData7923 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData7944 = new BitSet(new long[]{0x0080000000000000L});
        public static final BitSet FOLLOW_55_in_ruleKGridPlacementData7956 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData7977 = new BitSet(new long[]{0x0100000000000000L});
        public static final BitSet FOLLOW_56_in_ruleKGridPlacementData7989 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKGridPlacementData8010 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKGridPlacementData8022 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacementData_in_entryRuleKStackPlacementData8058 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKStackPlacementData8068 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_57_in_ruleKStackPlacementData8105 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKStackPlacementData8117 = new BitSet(new long[]{0x0400000000000000L});
        public static final BitSet FOLLOW_58_in_ruleKStackPlacementData8129 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData8150 = new BitSet(new long[]{0x0800000000000000L});
        public static final BitSet FOLLOW_59_in_ruleKStackPlacementData8162 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData8183 = new BitSet(new long[]{0x1000000000000000L});
        public static final BitSet FOLLOW_60_in_ruleKStackPlacementData8195 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData8216 = new BitSet(new long[]{0x2000000000000000L});
        public static final BitSet FOLLOW_61_in_ruleKStackPlacementData8228 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKStackPlacementData8249 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKStackPlacementData8261 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKDirectPlacementData_in_entryRuleKDirectPlacementData8297 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKDirectPlacementData8307 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_62_in_ruleKDirectPlacementData8344 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKDirectPlacementData8356 = new BitSet(new long[]{0x8000000000000000L});
        public static final BitSet FOLLOW_63_in_ruleKDirectPlacementData8368 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKDirectPlacementData8389 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
        public static final BitSet FOLLOW_64_in_ruleKDirectPlacementData8401 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKDirectPlacementData8422 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKDirectPlacementData8434 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPolylinePlacementData_in_entryRuleKPolylinePlacementData8470 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPolylinePlacementData8480 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_65_in_ruleKPolylinePlacementData8517 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPolylinePlacementData8529 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
        public static final BitSet FOLLOW_66_in_ruleKPolylinePlacementData8541 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPolylinePlacementData8553 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData8574 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_14_in_ruleKPolylinePlacementData8587 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
        public static final BitSet FOLLOW_ruleKPosition_in_ruleKPolylinePlacementData8608 = new BitSet(new long[]{0x000000000000C000L});
        public static final BitSet FOLLOW_15_in_ruleKPolylinePlacementData8622 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKPolylinePlacementData8634 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_entryRuleEFloat8671 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEFloat8682 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_67_in_ruleEFloat8721 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat8739 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
        public static final BitSet FOLLOW_68_in_ruleEFloat8759 = new BitSet(new long[]{0x0000000000000040L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat8774 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000060L});
        public static final BitSet FOLLOW_69_in_ruleEFloat8794 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000008L});
        public static final BitSet FOLLOW_70_in_ruleEFloat8813 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000008L});
        public static final BitSet FOLLOW_67_in_ruleEFloat8828 = new BitSet(new long[]{0x0000000000000040L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat8845 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPosition_in_entryRuleKPosition8894 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPosition8904 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_71_in_ruleKPosition8941 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPosition8953 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
        public static final BitSet FOLLOW_72_in_ruleKPosition8965 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001400L});
        public static final BitSet FOLLOW_ruleKXPosition_in_ruleKPosition8986 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
        public static final BitSet FOLLOW_73_in_ruleKPosition8998 = new BitSet(new long[]{0x0000000000000000L,0x0000000000006000L});
        public static final BitSet FOLLOW_ruleKYPosition_in_ruleKPosition9019 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKPosition9031 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLeftPosition_in_entryRuleKLeftPosition9067 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLeftPosition9077 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_74_in_ruleKLeftPosition9123 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKLeftPosition9135 = new BitSet(new long[]{0x0000800000008000L,0x0000000000000800L});
        public static final BitSet FOLLOW_75_in_ruleKLeftPosition9148 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKLeftPosition9169 = new BitSet(new long[]{0x0000800000008000L});
        public static final BitSet FOLLOW_47_in_ruleKLeftPosition9184 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKLeftPosition9205 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKLeftPosition9219 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKRightPosition_in_entryRuleKRightPosition9255 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKRightPosition9265 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_76_in_ruleKRightPosition9311 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKRightPosition9323 = new BitSet(new long[]{0x0000800000008000L,0x0000000000000800L});
        public static final BitSet FOLLOW_75_in_ruleKRightPosition9336 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRightPosition9357 = new BitSet(new long[]{0x0000800000008000L});
        public static final BitSet FOLLOW_47_in_ruleKRightPosition9372 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKRightPosition9393 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKRightPosition9407 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKTopPosition_in_entryRuleKTopPosition9443 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKTopPosition9453 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_77_in_ruleKTopPosition9499 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKTopPosition9511 = new BitSet(new long[]{0x0000800000008000L,0x0000000000000800L});
        public static final BitSet FOLLOW_75_in_ruleKTopPosition9524 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKTopPosition9545 = new BitSet(new long[]{0x0000800000008000L});
        public static final BitSet FOLLOW_47_in_ruleKTopPosition9560 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKTopPosition9581 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKTopPosition9595 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBottomPosition_in_entryRuleKBottomPosition9631 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKBottomPosition9641 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_78_in_ruleKBottomPosition9687 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKBottomPosition9699 = new BitSet(new long[]{0x0000800000008000L,0x0000000000000800L});
        public static final BitSet FOLLOW_75_in_ruleKBottomPosition9712 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKBottomPosition9733 = new BitSet(new long[]{0x0000800000008000L});
        public static final BitSet FOLLOW_47_in_ruleKBottomPosition9748 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKBottomPosition9769 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKBottomPosition9783 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKForegroundColor_in_entryRuleKForegroundColor9819 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKForegroundColor9829 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_23_in_ruleKForegroundColor9872 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
        public static final BitSet FOLLOW_79_in_ruleKForegroundColor9897 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKForegroundColor9909 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
        public static final BitSet FOLLOW_80_in_ruleKForegroundColor9921 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000008L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKForegroundColor9942 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
        public static final BitSet FOLLOW_81_in_ruleKForegroundColor9954 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000008L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKForegroundColor9975 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
        public static final BitSet FOLLOW_82_in_ruleKForegroundColor9987 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000008L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKForegroundColor10008 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKForegroundColor10020 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKBackgroundColor_in_entryRuleKBackgroundColor10056 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKBackgroundColor10066 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_23_in_ruleKBackgroundColor10109 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
        public static final BitSet FOLLOW_83_in_ruleKBackgroundColor10134 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKBackgroundColor10146 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
        public static final BitSet FOLLOW_80_in_ruleKBackgroundColor10158 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000008L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKBackgroundColor10179 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
        public static final BitSet FOLLOW_81_in_ruleKBackgroundColor10191 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000008L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKBackgroundColor10212 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
        public static final BitSet FOLLOW_82_in_ruleKBackgroundColor10224 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000008L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKBackgroundColor10245 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKBackgroundColor10257 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineWidth_in_entryRuleKLineWidth10293 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLineWidth10303 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_23_in_ruleKLineWidth10346 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
        public static final BitSet FOLLOW_84_in_ruleKLineWidth10371 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKLineWidth10383 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
        public static final BitSet FOLLOW_85_in_ruleKLineWidth10395 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000008L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKLineWidth10416 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKLineWidth10428 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVisibility_in_entryRuleKVisibility10464 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKVisibility10474 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_23_in_ruleKVisibility10517 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
        public static final BitSet FOLLOW_86_in_ruleKVisibility10548 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
        public static final BitSet FOLLOW_87_in_ruleKVisibility10579 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
        public static final BitSet FOLLOW_88_in_ruleKVisibility10604 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKLineStyle_in_entryRuleKLineStyle10640 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKLineStyle10650 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_23_in_ruleKLineStyle10693 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
        public static final BitSet FOLLOW_89_in_ruleKLineStyle10718 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKLineStyle10730 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
        public static final BitSet FOLLOW_90_in_ruleKLineStyle10742 = new BitSet(new long[]{0x0000000000000000L,0x00001F0000000000L});
        public static final BitSet FOLLOW_ruleLineStyle_in_ruleKLineStyle10763 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKLineStyle10775 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKVerticalAlignment_in_entryRuleKVerticalAlignment10811 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKVerticalAlignment10821 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_23_in_ruleKVerticalAlignment10864 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000000L});
        public static final BitSet FOLLOW_91_in_ruleKVerticalAlignment10889 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKVerticalAlignment10901 = new BitSet(new long[]{0x0000000000000000L,0x0000000010000000L});
        public static final BitSet FOLLOW_92_in_ruleKVerticalAlignment10913 = new BitSet(new long[]{0x0000000000000000L,0x0000E00000000000L});
        public static final BitSet FOLLOW_ruleVerticalAlignment_in_ruleKVerticalAlignment10934 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKVerticalAlignment10946 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKHorizontalAlignment_in_entryRuleKHorizontalAlignment10982 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKHorizontalAlignment10992 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_23_in_ruleKHorizontalAlignment11035 = new BitSet(new long[]{0x0000000000000000L,0x0000000020000000L});
        public static final BitSet FOLLOW_93_in_ruleKHorizontalAlignment11060 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKHorizontalAlignment11072 = new BitSet(new long[]{0x0000000000000000L,0x0000000040000000L});
        public static final BitSet FOLLOW_94_in_ruleKHorizontalAlignment11084 = new BitSet(new long[]{0x0000000000000000L,0x0003400000000000L});
        public static final BitSet FOLLOW_ruleHorizontalAlignment_in_ruleKHorizontalAlignment11105 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKHorizontalAlignment11117 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEInt_in_entryRuleEInt11154 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEInt11165 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_67_in_ruleEInt11204 = new BitSet(new long[]{0x0000000000000040L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEInt11221 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKGridPlacement_in_entryRuleKGridPlacement11266 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKGridPlacement11276 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_95_in_ruleKGridPlacement11313 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKGridPlacement11325 = new BitSet(new long[]{0x0000000000000000L,0x0000000100000000L});
        public static final BitSet FOLLOW_96_in_ruleKGridPlacement11337 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000008L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleKGridPlacement11358 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKGridPlacement11370 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKStackPlacement_in_entryRuleKStackPlacement11406 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKStackPlacement11416 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_97_in_ruleKStackPlacement11462 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKInsets_in_entryRuleKInsets11500 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKInsets11510 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_98_in_ruleKInsets11556 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKInsets11568 = new BitSet(new long[]{0x0000000000008000L,0x0000007800000000L});
        public static final BitSet FOLLOW_99_in_ruleKInsets11581 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets11602 = new BitSet(new long[]{0x0000000000008000L,0x0000007000000000L});
        public static final BitSet FOLLOW_100_in_ruleKInsets11617 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets11638 = new BitSet(new long[]{0x0000000000008000L,0x0000006000000000L});
        public static final BitSet FOLLOW_101_in_ruleKInsets11653 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets11674 = new BitSet(new long[]{0x0000000000008000L,0x0000004000000000L});
        public static final BitSet FOLLOW_102_in_ruleKInsets11689 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets11710 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKInsets11724 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPoint_in_entryRuleKPoint11762 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPoint11772 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_103_in_ruleKPoint11818 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPoint11830 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000300L});
        public static final BitSet FOLLOW_72_in_ruleKPoint11843 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKPoint11864 = new BitSet(new long[]{0x0000000000008000L,0x0000000000000200L});
        public static final BitSet FOLLOW_73_in_ruleKPoint11879 = new BitSet(new long[]{0x0000000000000040L,0x0000000000000018L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKPoint11900 = new BitSet(new long[]{0x0000000000008000L});
        public static final BitSet FOLLOW_15_in_ruleKPoint11914 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_104_in_ruleLineStyle11964 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_105_in_ruleLineStyle11981 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_106_in_ruleLineStyle11998 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_107_in_ruleLineStyle12015 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_108_in_ruleLineStyle12032 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_109_in_ruleVerticalAlignment12077 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_110_in_ruleVerticalAlignment12094 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_111_in_ruleVerticalAlignment12111 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_112_in_ruleHorizontalAlignment12156 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_110_in_ruleHorizontalAlignment12173 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_113_in_ruleHorizontalAlignment12190 = new BitSet(new long[]{0x0000000000000002L});
    }


}