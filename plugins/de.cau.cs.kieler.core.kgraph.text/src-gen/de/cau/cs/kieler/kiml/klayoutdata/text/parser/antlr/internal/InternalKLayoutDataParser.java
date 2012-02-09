package de.cau.cs.kieler.kiml.klayoutdata.text.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import de.cau.cs.kieler.kiml.klayoutdata.text.services.KLayoutDataGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalKLayoutDataParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INT", "RULE_ID", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'KShapeLayout'", "'{'", "'xpos'", "'ypos'", "'width'", "'height'", "'insets'", "'}'", "'KInsets'", "'top'", "'bottom'", "'left'", "'right'", "'KPoint'", "'x'", "'y'", "'-'", "'.'", "'E'", "'e'"
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
    public static final int RULE_SL_COMMENT=8;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__30=30;
    public static final int T__19=19;
    public static final int RULE_STRING=6;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int T__12=12;
    public static final int T__11=11;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int RULE_INT=4;
    public static final int RULE_WS=9;

    // delegates
    // delegators


        public InternalKLayoutDataParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalKLayoutDataParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalKLayoutDataParser.tokenNames; }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g"; }



     	private KLayoutDataGrammarAccess grammarAccess;
     	
        public InternalKLayoutDataParser(TokenStream input, KLayoutDataGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "KShapeLayout";	
       	}
       	
       	@Override
       	protected KLayoutDataGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleKShapeLayout"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:67:1: entryRuleKShapeLayout returns [EObject current=null] : iv_ruleKShapeLayout= ruleKShapeLayout EOF ;
    public final EObject entryRuleKShapeLayout() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKShapeLayout = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:68:2: (iv_ruleKShapeLayout= ruleKShapeLayout EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:69:2: iv_ruleKShapeLayout= ruleKShapeLayout EOF
            {
             newCompositeNode(grammarAccess.getKShapeLayoutRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKShapeLayout_in_entryRuleKShapeLayout75);
            iv_ruleKShapeLayout=ruleKShapeLayout();

            state._fsp--;

             current =iv_ruleKShapeLayout; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKShapeLayout85); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:76:1: ruleKShapeLayout returns [EObject current=null] : ( () otherlv_1= 'KShapeLayout' otherlv_2= '{' (otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) ) )? (otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) ) )? (otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) ) )? (otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) ) )? (otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) ) )? otherlv_13= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:79:28: ( ( () otherlv_1= 'KShapeLayout' otherlv_2= '{' (otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) ) )? (otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) ) )? (otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) ) )? (otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) ) )? (otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) ) )? otherlv_13= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:80:1: ( () otherlv_1= 'KShapeLayout' otherlv_2= '{' (otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) ) )? (otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) ) )? (otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) ) )? (otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) ) )? (otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) ) )? otherlv_13= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:80:1: ( () otherlv_1= 'KShapeLayout' otherlv_2= '{' (otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) ) )? (otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) ) )? (otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) ) )? (otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) ) )? (otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) ) )? otherlv_13= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:80:2: () otherlv_1= 'KShapeLayout' otherlv_2= '{' (otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) ) )? (otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) ) )? (otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) ) )? (otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) ) )? (otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) ) )? otherlv_13= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:80:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:81:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKShapeLayoutAccess().getKShapeLayoutAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleKShapeLayout131); 

                	newLeafNode(otherlv_1, grammarAccess.getKShapeLayoutAccess().getKShapeLayoutKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKShapeLayout143); 

                	newLeafNode(otherlv_2, grammarAccess.getKShapeLayoutAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:94:1: (otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) ) )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==13) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:94:3: otherlv_3= 'xpos' ( (lv_xpos_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleKShapeLayout156); 

                        	newLeafNode(otherlv_3, grammarAccess.getKShapeLayoutAccess().getXposKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:98:1: ( (lv_xpos_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:99:1: (lv_xpos_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:99:1: (lv_xpos_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:100:3: lv_xpos_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getXposEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKShapeLayout177);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:116:4: (otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) ) )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==14) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:116:6: otherlv_5= 'ypos' ( (lv_ypos_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleKShapeLayout192); 

                        	newLeafNode(otherlv_5, grammarAccess.getKShapeLayoutAccess().getYposKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:120:1: ( (lv_ypos_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:121:1: (lv_ypos_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:121:1: (lv_ypos_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:122:3: lv_ypos_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getYposEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKShapeLayout213);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:138:4: (otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) ) )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==15) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:138:6: otherlv_7= 'width' ( (lv_width_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleKShapeLayout228); 

                        	newLeafNode(otherlv_7, grammarAccess.getKShapeLayoutAccess().getWidthKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:142:1: ( (lv_width_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:143:1: (lv_width_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:143:1: (lv_width_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:144:3: lv_width_8_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getWidthEFloatParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKShapeLayout249);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:160:4: (otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) ) )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==16) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:160:6: otherlv_9= 'height' ( (lv_height_10_0= ruleEFloat ) )
                    {
                    otherlv_9=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleKShapeLayout264); 

                        	newLeafNode(otherlv_9, grammarAccess.getKShapeLayoutAccess().getHeightKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:164:1: ( (lv_height_10_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:165:1: (lv_height_10_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:165:1: (lv_height_10_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:166:3: lv_height_10_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getHeightEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKShapeLayout285);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:182:4: (otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==17) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:182:6: otherlv_11= 'insets' ( (lv_insets_12_0= ruleKInsets ) )
                    {
                    otherlv_11=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleKShapeLayout300); 

                        	newLeafNode(otherlv_11, grammarAccess.getKShapeLayoutAccess().getInsetsKeyword_7_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:186:1: ( (lv_insets_12_0= ruleKInsets ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:187:1: (lv_insets_12_0= ruleKInsets )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:187:1: (lv_insets_12_0= ruleKInsets )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:188:3: lv_insets_12_0= ruleKInsets
                    {
                     
                    	        newCompositeNode(grammarAccess.getKShapeLayoutAccess().getInsetsKInsetsParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleKInsets_in_ruleKShapeLayout321);
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

            otherlv_13=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKShapeLayout335); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:216:1: entryRuleKInsets returns [EObject current=null] : iv_ruleKInsets= ruleKInsets EOF ;
    public final EObject entryRuleKInsets() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKInsets = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:217:2: (iv_ruleKInsets= ruleKInsets EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:218:2: iv_ruleKInsets= ruleKInsets EOF
            {
             newCompositeNode(grammarAccess.getKInsetsRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKInsets_in_entryRuleKInsets371);
            iv_ruleKInsets=ruleKInsets();

            state._fsp--;

             current =iv_ruleKInsets; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKInsets381); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:225:1: ruleKInsets returns [EObject current=null] : ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:228:28: ( ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:229:1: ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:229:1: ( () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:229:2: () otherlv_1= 'KInsets' otherlv_2= '{' (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )? (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )? (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )? (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )? otherlv_11= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:229:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:230:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKInsetsAccess().getKInsetsAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleKInsets427); 

                	newLeafNode(otherlv_1, grammarAccess.getKInsetsAccess().getKInsetsKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKInsets439); 

                	newLeafNode(otherlv_2, grammarAccess.getKInsetsAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:243:1: (otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) ) )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==20) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:243:3: otherlv_3= 'top' ( (lv_top_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleKInsets452); 

                        	newLeafNode(otherlv_3, grammarAccess.getKInsetsAccess().getTopKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:247:1: ( (lv_top_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:248:1: (lv_top_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:248:1: (lv_top_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:249:3: lv_top_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getTopEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets473);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:265:4: (otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) ) )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==21) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:265:6: otherlv_5= 'bottom' ( (lv_bottom_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleKInsets488); 

                        	newLeafNode(otherlv_5, grammarAccess.getKInsetsAccess().getBottomKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:269:1: ( (lv_bottom_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:270:1: (lv_bottom_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:270:1: (lv_bottom_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:271:3: lv_bottom_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getBottomEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets509);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:287:4: (otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==22) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:287:6: otherlv_7= 'left' ( (lv_left_8_0= ruleEFloat ) )
                    {
                    otherlv_7=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleKInsets524); 

                        	newLeafNode(otherlv_7, grammarAccess.getKInsetsAccess().getLeftKeyword_5_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:291:1: ( (lv_left_8_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:292:1: (lv_left_8_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:292:1: (lv_left_8_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:293:3: lv_left_8_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getLeftEFloatParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets545);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:309:4: (otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==23) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:309:6: otherlv_9= 'right' ( (lv_right_10_0= ruleEFloat ) )
                    {
                    otherlv_9=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleKInsets560); 

                        	newLeafNode(otherlv_9, grammarAccess.getKInsetsAccess().getRightKeyword_6_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:313:1: ( (lv_right_10_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:314:1: (lv_right_10_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:314:1: (lv_right_10_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:315:3: lv_right_10_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKInsetsAccess().getRightEFloatParserRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKInsets581);
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

            otherlv_11=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKInsets595); 

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:345:1: entryRuleKPoint returns [EObject current=null] : iv_ruleKPoint= ruleKPoint EOF ;
    public final EObject entryRuleKPoint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKPoint = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:346:2: (iv_ruleKPoint= ruleKPoint EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:347:2: iv_ruleKPoint= ruleKPoint EOF
            {
             newCompositeNode(grammarAccess.getKPointRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPoint_in_entryRuleKPoint633);
            iv_ruleKPoint=ruleKPoint();

            state._fsp--;

             current =iv_ruleKPoint; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPoint643); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:354:1: ruleKPoint returns [EObject current=null] : ( () otherlv_1= 'KPoint' otherlv_2= '{' (otherlv_3= 'x' ( (lv_x_4_0= ruleEFloat ) ) )? (otherlv_5= 'y' ( (lv_y_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) ;
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
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:357:28: ( ( () otherlv_1= 'KPoint' otherlv_2= '{' (otherlv_3= 'x' ( (lv_x_4_0= ruleEFloat ) ) )? (otherlv_5= 'y' ( (lv_y_6_0= ruleEFloat ) ) )? otherlv_7= '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:358:1: ( () otherlv_1= 'KPoint' otherlv_2= '{' (otherlv_3= 'x' ( (lv_x_4_0= ruleEFloat ) ) )? (otherlv_5= 'y' ( (lv_y_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:358:1: ( () otherlv_1= 'KPoint' otherlv_2= '{' (otherlv_3= 'x' ( (lv_x_4_0= ruleEFloat ) ) )? (otherlv_5= 'y' ( (lv_y_6_0= ruleEFloat ) ) )? otherlv_7= '}' )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:358:2: () otherlv_1= 'KPoint' otherlv_2= '{' (otherlv_3= 'x' ( (lv_x_4_0= ruleEFloat ) ) )? (otherlv_5= 'y' ( (lv_y_6_0= ruleEFloat ) ) )? otherlv_7= '}'
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:358:2: ()
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:359:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getKPointAccess().getKPointAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,24,FollowSets000.FOLLOW_24_in_ruleKPoint689); 

                	newLeafNode(otherlv_1, grammarAccess.getKPointAccess().getKPointKeyword_1());
                
            otherlv_2=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleKPoint701); 

                	newLeafNode(otherlv_2, grammarAccess.getKPointAccess().getLeftCurlyBracketKeyword_2());
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:372:1: (otherlv_3= 'x' ( (lv_x_4_0= ruleEFloat ) ) )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==25) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:372:3: otherlv_3= 'x' ( (lv_x_4_0= ruleEFloat ) )
                    {
                    otherlv_3=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleKPoint714); 

                        	newLeafNode(otherlv_3, grammarAccess.getKPointAccess().getXKeyword_3_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:376:1: ( (lv_x_4_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:377:1: (lv_x_4_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:377:1: (lv_x_4_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:378:3: lv_x_4_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPointAccess().getXEFloatParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKPoint735);
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

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:394:4: (otherlv_5= 'y' ( (lv_y_6_0= ruleEFloat ) ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==26) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:394:6: otherlv_5= 'y' ( (lv_y_6_0= ruleEFloat ) )
                    {
                    otherlv_5=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleKPoint750); 

                        	newLeafNode(otherlv_5, grammarAccess.getKPointAccess().getYKeyword_4_0());
                        
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:398:1: ( (lv_y_6_0= ruleEFloat ) )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:399:1: (lv_y_6_0= ruleEFloat )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:399:1: (lv_y_6_0= ruleEFloat )
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:400:3: lv_y_6_0= ruleEFloat
                    {
                     
                    	        newCompositeNode(grammarAccess.getKPointAccess().getYEFloatParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_ruleKPoint771);
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

            otherlv_7=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleKPoint785); 

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


    // $ANTLR start "entryRuleEFloat"
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:430:1: entryRuleEFloat returns [String current=null] : iv_ruleEFloat= ruleEFloat EOF ;
    public final String entryRuleEFloat() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEFloat = null;


        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:431:2: (iv_ruleEFloat= ruleEFloat EOF )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:432:2: iv_ruleEFloat= ruleEFloat EOF
            {
             newCompositeNode(grammarAccess.getEFloatRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_entryRuleEFloat824);
            iv_ruleEFloat=ruleEFloat();

            state._fsp--;

             current =iv_ruleEFloat.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEFloat835); 

            }

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
    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:439:1: ruleEFloat returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? ) ;
    public final AntlrDatatypeRuleToken ruleEFloat() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;
        Token this_INT_3=null;
        Token this_INT_7=null;

         enterRule(); 
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:442:28: ( ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:443:1: ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:443:1: ( (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )? )
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:443:2: (kw= '-' )? (this_INT_1= RULE_INT )? kw= '.' this_INT_3= RULE_INT ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )?
            {
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:443:2: (kw= '-' )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==27) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:444:2: kw= '-'
                    {
                    kw=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleEFloat874); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEFloatAccess().getHyphenMinusKeyword_0()); 
                        

                    }
                    break;

            }

            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:449:3: (this_INT_1= RULE_INT )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==RULE_INT) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:449:8: this_INT_1= RULE_INT
                    {
                    this_INT_1=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat892); 

                    		current.merge(this_INT_1);
                        
                     
                        newLeafNode(this_INT_1, grammarAccess.getEFloatAccess().getINTTerminalRuleCall_1()); 
                        

                    }
                    break;

            }

            kw=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleEFloat912); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getEFloatAccess().getFullStopKeyword_2()); 
                
            this_INT_3=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat927); 

            		current.merge(this_INT_3);
                
             
                newLeafNode(this_INT_3, grammarAccess.getEFloatAccess().getINTTerminalRuleCall_3()); 
                
            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:469:1: ( (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( ((LA16_0>=29 && LA16_0<=30)) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:469:2: (kw= 'E' | kw= 'e' ) (kw= '-' )? this_INT_7= RULE_INT
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:469:2: (kw= 'E' | kw= 'e' )
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==29) ) {
                        alt14=1;
                    }
                    else if ( (LA14_0==30) ) {
                        alt14=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 14, 0, input);

                        throw nvae;
                    }
                    switch (alt14) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:470:2: kw= 'E'
                            {
                            kw=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleEFloat947); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getEFloatAccess().getEKeyword_4_0_0()); 
                                

                            }
                            break;
                        case 2 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:477:2: kw= 'e'
                            {
                            kw=(Token)match(input,30,FollowSets000.FOLLOW_30_in_ruleEFloat966); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getEFloatAccess().getEKeyword_4_0_1()); 
                                

                            }
                            break;

                    }

                    // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:482:2: (kw= '-' )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0==27) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // ../de.cau.cs.kieler.core.kgraph.text/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/parser/antlr/internal/InternalKLayoutData.g:483:2: kw= '-'
                            {
                            kw=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleEFloat981); 

                                    current.merge(kw);
                                    newLeafNode(kw, grammarAccess.getEFloatAccess().getHyphenMinusKeyword_4_1()); 
                                

                            }
                            break;

                    }

                    this_INT_7=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEFloat998); 

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

    // Delegated rules


 

    
    private static class FollowSets000 {
        public static final BitSet FOLLOW_ruleKShapeLayout_in_entryRuleKShapeLayout75 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKShapeLayout85 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_11_in_ruleKShapeLayout131 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKShapeLayout143 = new BitSet(new long[]{0x000000000007E000L});
        public static final BitSet FOLLOW_13_in_ruleKShapeLayout156 = new BitSet(new long[]{0x0000000018000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKShapeLayout177 = new BitSet(new long[]{0x000000000007C000L});
        public static final BitSet FOLLOW_14_in_ruleKShapeLayout192 = new BitSet(new long[]{0x0000000018000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKShapeLayout213 = new BitSet(new long[]{0x0000000000078000L});
        public static final BitSet FOLLOW_15_in_ruleKShapeLayout228 = new BitSet(new long[]{0x0000000018000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKShapeLayout249 = new BitSet(new long[]{0x0000000000070000L});
        public static final BitSet FOLLOW_16_in_ruleKShapeLayout264 = new BitSet(new long[]{0x0000000018000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKShapeLayout285 = new BitSet(new long[]{0x0000000000060000L});
        public static final BitSet FOLLOW_17_in_ruleKShapeLayout300 = new BitSet(new long[]{0x0000000000080000L});
        public static final BitSet FOLLOW_ruleKInsets_in_ruleKShapeLayout321 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKShapeLayout335 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKInsets_in_entryRuleKInsets371 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKInsets381 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_19_in_ruleKInsets427 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKInsets439 = new BitSet(new long[]{0x0000000000F40000L});
        public static final BitSet FOLLOW_20_in_ruleKInsets452 = new BitSet(new long[]{0x0000000018000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets473 = new BitSet(new long[]{0x0000000000E40000L});
        public static final BitSet FOLLOW_21_in_ruleKInsets488 = new BitSet(new long[]{0x0000000018000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets509 = new BitSet(new long[]{0x0000000000C40000L});
        public static final BitSet FOLLOW_22_in_ruleKInsets524 = new BitSet(new long[]{0x0000000018000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets545 = new BitSet(new long[]{0x0000000000840000L});
        public static final BitSet FOLLOW_23_in_ruleKInsets560 = new BitSet(new long[]{0x0000000018000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKInsets581 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKInsets595 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPoint_in_entryRuleKPoint633 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPoint643 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_24_in_ruleKPoint689 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleKPoint701 = new BitSet(new long[]{0x0000000006040000L});
        public static final BitSet FOLLOW_25_in_ruleKPoint714 = new BitSet(new long[]{0x0000000018000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKPoint735 = new BitSet(new long[]{0x0000000004040000L});
        public static final BitSet FOLLOW_26_in_ruleKPoint750 = new BitSet(new long[]{0x0000000018000010L});
        public static final BitSet FOLLOW_ruleEFloat_in_ruleKPoint771 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleKPoint785 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_entryRuleEFloat824 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEFloat835 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_27_in_ruleEFloat874 = new BitSet(new long[]{0x0000000010000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat892 = new BitSet(new long[]{0x0000000010000000L});
        public static final BitSet FOLLOW_28_in_ruleEFloat912 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat927 = new BitSet(new long[]{0x0000000060000002L});
        public static final BitSet FOLLOW_29_in_ruleEFloat947 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_30_in_ruleEFloat966 = new BitSet(new long[]{0x0000000008000010L});
        public static final BitSet FOLLOW_27_in_ruleEFloat981 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEFloat998 = new BitSet(new long[]{0x0000000000000002L});
    }


}