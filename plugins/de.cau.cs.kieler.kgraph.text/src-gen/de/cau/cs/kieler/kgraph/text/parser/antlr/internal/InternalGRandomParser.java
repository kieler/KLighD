package de.cau.cs.kieler.kgraph.text.parser.antlr.internal;

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
import de.cau.cs.kieler.kgraph.text.services.GRandomGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalGRandomParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INT", "RULE_STRING", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'generate'", "'{'", "'maxWidth'", "'='", "'maxDegree'", "'partitionFraction'", "'seed'", "'format'", "'filename'", "'}'", "'hierarchy'", "'levels'", "'cross-hierarchy edges'", "'compound nodes'", "'cross-hierarchy relative edges'", "'edges'", "'density'", "'total'", "'relative'", "'outgoing'", "'labels'", "'self loops'", "'nodes'", "'size'", "'height'", "'width'", "'ports'", "'re-use'", "'constraint'", "'to'", "'+/-'", "'.'", "'kgt'", "'kgx'", "'trees'", "'bipartite graphs'", "'graphs'", "'triconnected graphs'", "'biconnected graphs'", "'acyclic graphs'", "'north'", "'east'", "'south'", "'west'", "'incoming'", "'free'", "'side'", "'position'", "'order'", "'ratio'"
    };
    public static final int T__50=50;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__59=59;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__55=55;
    public static final int T__12=12;
    public static final int T__56=56;
    public static final int T__13=13;
    public static final int T__57=57;
    public static final int T__14=14;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__60=60;
    public static final int RULE_ID=6;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=4;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int RULE_STRING=5;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;

    // delegates
    // delegators


        public InternalGRandomParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalGRandomParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalGRandomParser.tokenNames; }
    public String getGrammarFileName() { return "InternalGRandom.g"; }



     	private GRandomGrammarAccess grammarAccess;

        public InternalGRandomParser(TokenStream input, GRandomGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "RandGraph";
       	}

       	@Override
       	protected GRandomGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleRandGraph"
    // InternalGRandom.g:65:1: entryRuleRandGraph returns [EObject current=null] : iv_ruleRandGraph= ruleRandGraph EOF ;
    public final EObject entryRuleRandGraph() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRandGraph = null;


        try {
            // InternalGRandom.g:65:50: (iv_ruleRandGraph= ruleRandGraph EOF )
            // InternalGRandom.g:66:2: iv_ruleRandGraph= ruleRandGraph EOF
            {
             newCompositeNode(grammarAccess.getRandGraphRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRandGraph=ruleRandGraph();

            state._fsp--;

             current =iv_ruleRandGraph; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRandGraph"


    // $ANTLR start "ruleRandGraph"
    // InternalGRandom.g:72:1: ruleRandGraph returns [EObject current=null] : ( (lv_configs_0_0= ruleConfiguration ) )* ;
    public final EObject ruleRandGraph() throws RecognitionException {
        EObject current = null;

        EObject lv_configs_0_0 = null;



        	enterRule();

        try {
            // InternalGRandom.g:78:2: ( ( (lv_configs_0_0= ruleConfiguration ) )* )
            // InternalGRandom.g:79:2: ( (lv_configs_0_0= ruleConfiguration ) )*
            {
            // InternalGRandom.g:79:2: ( (lv_configs_0_0= ruleConfiguration ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==11) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalGRandom.g:80:3: (lv_configs_0_0= ruleConfiguration )
            	    {
            	    // InternalGRandom.g:80:3: (lv_configs_0_0= ruleConfiguration )
            	    // InternalGRandom.g:81:4: lv_configs_0_0= ruleConfiguration
            	    {

            	    				newCompositeNode(grammarAccess.getRandGraphAccess().getConfigsConfigurationParserRuleCall_0());
            	    			
            	    pushFollow(FOLLOW_3);
            	    lv_configs_0_0=ruleConfiguration();

            	    state._fsp--;


            	    				if (current==null) {
            	    					current = createModelElementForParent(grammarAccess.getRandGraphRule());
            	    				}
            	    				add(
            	    					current,
            	    					"configs",
            	    					lv_configs_0_0,
            	    					"de.cau.cs.kieler.kgraph.text.GRandom.Configuration");
            	    				afterParserOrEnumRuleCall();
            	    			

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRandGraph"


    // $ANTLR start "entryRuleConfiguration"
    // InternalGRandom.g:101:1: entryRuleConfiguration returns [EObject current=null] : iv_ruleConfiguration= ruleConfiguration EOF ;
    public final EObject entryRuleConfiguration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConfiguration = null;


        try {
            // InternalGRandom.g:101:54: (iv_ruleConfiguration= ruleConfiguration EOF )
            // InternalGRandom.g:102:2: iv_ruleConfiguration= ruleConfiguration EOF
            {
             newCompositeNode(grammarAccess.getConfigurationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConfiguration=ruleConfiguration();

            state._fsp--;

             current =iv_ruleConfiguration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConfiguration"


    // $ANTLR start "ruleConfiguration"
    // InternalGRandom.g:108:1: ruleConfiguration returns [EObject current=null] : (otherlv_0= 'generate' ( (lv_samples_1_0= RULE_INT ) ) ( (lv_form_2_0= ruleForm ) ) (otherlv_3= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_nodes_5_0= ruleNodes ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_edges_6_0= ruleEdges ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mW_7_0= 'maxWidth' ) ) otherlv_8= '=' ( (lv_maxWidth_9_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mD_10_0= 'maxDegree' ) ) otherlv_11= '=' ( (lv_maxDegree_12_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_pF_13_0= 'partitionFraction' ) ) otherlv_14= '=' ( (lv_fraction_15_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_hierarchy_16_0= ruleHierarchy ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'seed' otherlv_18= '=' ( (lv_seed_19_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'format' otherlv_21= '=' ( (lv_format_22_0= ruleFormats ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'filename' otherlv_24= '=' ( (lv_filename_25_0= RULE_STRING ) ) ) ) ) ) )* ) ) ) otherlv_26= '}' )? ) ;
    public final EObject ruleConfiguration() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_samples_1_0=null;
        Token otherlv_3=null;
        Token lv_mW_7_0=null;
        Token otherlv_8=null;
        Token lv_mD_10_0=null;
        Token otherlv_11=null;
        Token lv_pF_13_0=null;
        Token otherlv_14=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        Token otherlv_23=null;
        Token otherlv_24=null;
        Token lv_filename_25_0=null;
        Token otherlv_26=null;
        Enumerator lv_form_2_0 = null;

        EObject lv_nodes_5_0 = null;

        EObject lv_edges_6_0 = null;

        AntlrDatatypeRuleToken lv_maxWidth_9_0 = null;

        AntlrDatatypeRuleToken lv_maxDegree_12_0 = null;

        EObject lv_fraction_15_0 = null;

        EObject lv_hierarchy_16_0 = null;

        AntlrDatatypeRuleToken lv_seed_19_0 = null;

        Enumerator lv_format_22_0 = null;



        	enterRule();

        try {
            // InternalGRandom.g:114:2: ( (otherlv_0= 'generate' ( (lv_samples_1_0= RULE_INT ) ) ( (lv_form_2_0= ruleForm ) ) (otherlv_3= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_nodes_5_0= ruleNodes ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_edges_6_0= ruleEdges ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mW_7_0= 'maxWidth' ) ) otherlv_8= '=' ( (lv_maxWidth_9_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mD_10_0= 'maxDegree' ) ) otherlv_11= '=' ( (lv_maxDegree_12_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_pF_13_0= 'partitionFraction' ) ) otherlv_14= '=' ( (lv_fraction_15_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_hierarchy_16_0= ruleHierarchy ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'seed' otherlv_18= '=' ( (lv_seed_19_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'format' otherlv_21= '=' ( (lv_format_22_0= ruleFormats ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'filename' otherlv_24= '=' ( (lv_filename_25_0= RULE_STRING ) ) ) ) ) ) )* ) ) ) otherlv_26= '}' )? ) )
            // InternalGRandom.g:115:2: (otherlv_0= 'generate' ( (lv_samples_1_0= RULE_INT ) ) ( (lv_form_2_0= ruleForm ) ) (otherlv_3= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_nodes_5_0= ruleNodes ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_edges_6_0= ruleEdges ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mW_7_0= 'maxWidth' ) ) otherlv_8= '=' ( (lv_maxWidth_9_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mD_10_0= 'maxDegree' ) ) otherlv_11= '=' ( (lv_maxDegree_12_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_pF_13_0= 'partitionFraction' ) ) otherlv_14= '=' ( (lv_fraction_15_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_hierarchy_16_0= ruleHierarchy ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'seed' otherlv_18= '=' ( (lv_seed_19_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'format' otherlv_21= '=' ( (lv_format_22_0= ruleFormats ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'filename' otherlv_24= '=' ( (lv_filename_25_0= RULE_STRING ) ) ) ) ) ) )* ) ) ) otherlv_26= '}' )? )
            {
            // InternalGRandom.g:115:2: (otherlv_0= 'generate' ( (lv_samples_1_0= RULE_INT ) ) ( (lv_form_2_0= ruleForm ) ) (otherlv_3= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_nodes_5_0= ruleNodes ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_edges_6_0= ruleEdges ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mW_7_0= 'maxWidth' ) ) otherlv_8= '=' ( (lv_maxWidth_9_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mD_10_0= 'maxDegree' ) ) otherlv_11= '=' ( (lv_maxDegree_12_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_pF_13_0= 'partitionFraction' ) ) otherlv_14= '=' ( (lv_fraction_15_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_hierarchy_16_0= ruleHierarchy ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'seed' otherlv_18= '=' ( (lv_seed_19_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'format' otherlv_21= '=' ( (lv_format_22_0= ruleFormats ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'filename' otherlv_24= '=' ( (lv_filename_25_0= RULE_STRING ) ) ) ) ) ) )* ) ) ) otherlv_26= '}' )? )
            // InternalGRandom.g:116:3: otherlv_0= 'generate' ( (lv_samples_1_0= RULE_INT ) ) ( (lv_form_2_0= ruleForm ) ) (otherlv_3= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_nodes_5_0= ruleNodes ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_edges_6_0= ruleEdges ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mW_7_0= 'maxWidth' ) ) otherlv_8= '=' ( (lv_maxWidth_9_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mD_10_0= 'maxDegree' ) ) otherlv_11= '=' ( (lv_maxDegree_12_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_pF_13_0= 'partitionFraction' ) ) otherlv_14= '=' ( (lv_fraction_15_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_hierarchy_16_0= ruleHierarchy ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'seed' otherlv_18= '=' ( (lv_seed_19_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'format' otherlv_21= '=' ( (lv_format_22_0= ruleFormats ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'filename' otherlv_24= '=' ( (lv_filename_25_0= RULE_STRING ) ) ) ) ) ) )* ) ) ) otherlv_26= '}' )?
            {
            otherlv_0=(Token)match(input,11,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getConfigurationAccess().getGenerateKeyword_0());
            		
            // InternalGRandom.g:120:3: ( (lv_samples_1_0= RULE_INT ) )
            // InternalGRandom.g:121:4: (lv_samples_1_0= RULE_INT )
            {
            // InternalGRandom.g:121:4: (lv_samples_1_0= RULE_INT )
            // InternalGRandom.g:122:5: lv_samples_1_0= RULE_INT
            {
            lv_samples_1_0=(Token)match(input,RULE_INT,FOLLOW_5); 

            					newLeafNode(lv_samples_1_0, grammarAccess.getConfigurationAccess().getSamplesINTTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getConfigurationRule());
            					}
            					setWithLastConsumed(
            						current,
            						"samples",
            						lv_samples_1_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            // InternalGRandom.g:138:3: ( (lv_form_2_0= ruleForm ) )
            // InternalGRandom.g:139:4: (lv_form_2_0= ruleForm )
            {
            // InternalGRandom.g:139:4: (lv_form_2_0= ruleForm )
            // InternalGRandom.g:140:5: lv_form_2_0= ruleForm
            {

            					newCompositeNode(grammarAccess.getConfigurationAccess().getFormFormEnumRuleCall_2_0());
            				
            pushFollow(FOLLOW_6);
            lv_form_2_0=ruleForm();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getConfigurationRule());
            					}
            					set(
            						current,
            						"form",
            						lv_form_2_0,
            						"de.cau.cs.kieler.kgraph.text.GRandom.Form");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalGRandom.g:157:3: (otherlv_3= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_nodes_5_0= ruleNodes ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_edges_6_0= ruleEdges ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mW_7_0= 'maxWidth' ) ) otherlv_8= '=' ( (lv_maxWidth_9_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mD_10_0= 'maxDegree' ) ) otherlv_11= '=' ( (lv_maxDegree_12_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_pF_13_0= 'partitionFraction' ) ) otherlv_14= '=' ( (lv_fraction_15_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_hierarchy_16_0= ruleHierarchy ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'seed' otherlv_18= '=' ( (lv_seed_19_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'format' otherlv_21= '=' ( (lv_format_22_0= ruleFormats ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'filename' otherlv_24= '=' ( (lv_filename_25_0= RULE_STRING ) ) ) ) ) ) )* ) ) ) otherlv_26= '}' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==12) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // InternalGRandom.g:158:4: otherlv_3= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_nodes_5_0= ruleNodes ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_edges_6_0= ruleEdges ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mW_7_0= 'maxWidth' ) ) otherlv_8= '=' ( (lv_maxWidth_9_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mD_10_0= 'maxDegree' ) ) otherlv_11= '=' ( (lv_maxDegree_12_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_pF_13_0= 'partitionFraction' ) ) otherlv_14= '=' ( (lv_fraction_15_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_hierarchy_16_0= ruleHierarchy ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'seed' otherlv_18= '=' ( (lv_seed_19_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'format' otherlv_21= '=' ( (lv_format_22_0= ruleFormats ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'filename' otherlv_24= '=' ( (lv_filename_25_0= RULE_STRING ) ) ) ) ) ) )* ) ) ) otherlv_26= '}'
                    {
                    otherlv_3=(Token)match(input,12,FOLLOW_7); 

                    				newLeafNode(otherlv_3, grammarAccess.getConfigurationAccess().getLeftCurlyBracketKeyword_3_0());
                    			
                    // InternalGRandom.g:162:4: ( ( ( ( ({...}? => ( ({...}? => ( (lv_nodes_5_0= ruleNodes ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_edges_6_0= ruleEdges ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mW_7_0= 'maxWidth' ) ) otherlv_8= '=' ( (lv_maxWidth_9_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mD_10_0= 'maxDegree' ) ) otherlv_11= '=' ( (lv_maxDegree_12_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_pF_13_0= 'partitionFraction' ) ) otherlv_14= '=' ( (lv_fraction_15_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_hierarchy_16_0= ruleHierarchy ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'seed' otherlv_18= '=' ( (lv_seed_19_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'format' otherlv_21= '=' ( (lv_format_22_0= ruleFormats ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'filename' otherlv_24= '=' ( (lv_filename_25_0= RULE_STRING ) ) ) ) ) ) )* ) ) )
                    // InternalGRandom.g:163:5: ( ( ( ({...}? => ( ({...}? => ( (lv_nodes_5_0= ruleNodes ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_edges_6_0= ruleEdges ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mW_7_0= 'maxWidth' ) ) otherlv_8= '=' ( (lv_maxWidth_9_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mD_10_0= 'maxDegree' ) ) otherlv_11= '=' ( (lv_maxDegree_12_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_pF_13_0= 'partitionFraction' ) ) otherlv_14= '=' ( (lv_fraction_15_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_hierarchy_16_0= ruleHierarchy ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'seed' otherlv_18= '=' ( (lv_seed_19_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'format' otherlv_21= '=' ( (lv_format_22_0= ruleFormats ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'filename' otherlv_24= '=' ( (lv_filename_25_0= RULE_STRING ) ) ) ) ) ) )* ) )
                    {
                    // InternalGRandom.g:163:5: ( ( ( ({...}? => ( ({...}? => ( (lv_nodes_5_0= ruleNodes ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_edges_6_0= ruleEdges ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mW_7_0= 'maxWidth' ) ) otherlv_8= '=' ( (lv_maxWidth_9_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mD_10_0= 'maxDegree' ) ) otherlv_11= '=' ( (lv_maxDegree_12_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_pF_13_0= 'partitionFraction' ) ) otherlv_14= '=' ( (lv_fraction_15_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_hierarchy_16_0= ruleHierarchy ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'seed' otherlv_18= '=' ( (lv_seed_19_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'format' otherlv_21= '=' ( (lv_format_22_0= ruleFormats ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'filename' otherlv_24= '=' ( (lv_filename_25_0= RULE_STRING ) ) ) ) ) ) )* ) )
                    // InternalGRandom.g:164:6: ( ( ({...}? => ( ({...}? => ( (lv_nodes_5_0= ruleNodes ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_edges_6_0= ruleEdges ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mW_7_0= 'maxWidth' ) ) otherlv_8= '=' ( (lv_maxWidth_9_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mD_10_0= 'maxDegree' ) ) otherlv_11= '=' ( (lv_maxDegree_12_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_pF_13_0= 'partitionFraction' ) ) otherlv_14= '=' ( (lv_fraction_15_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_hierarchy_16_0= ruleHierarchy ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'seed' otherlv_18= '=' ( (lv_seed_19_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'format' otherlv_21= '=' ( (lv_format_22_0= ruleFormats ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'filename' otherlv_24= '=' ( (lv_filename_25_0= RULE_STRING ) ) ) ) ) ) )* )
                    {
                     
                    					  getUnorderedGroupHelper().enter(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1());
                    					
                    // InternalGRandom.g:167:6: ( ( ({...}? => ( ({...}? => ( (lv_nodes_5_0= ruleNodes ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_edges_6_0= ruleEdges ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mW_7_0= 'maxWidth' ) ) otherlv_8= '=' ( (lv_maxWidth_9_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mD_10_0= 'maxDegree' ) ) otherlv_11= '=' ( (lv_maxDegree_12_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_pF_13_0= 'partitionFraction' ) ) otherlv_14= '=' ( (lv_fraction_15_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_hierarchy_16_0= ruleHierarchy ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'seed' otherlv_18= '=' ( (lv_seed_19_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'format' otherlv_21= '=' ( (lv_format_22_0= ruleFormats ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'filename' otherlv_24= '=' ( (lv_filename_25_0= RULE_STRING ) ) ) ) ) ) )* )
                    // InternalGRandom.g:168:7: ( ({...}? => ( ({...}? => ( (lv_nodes_5_0= ruleNodes ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_edges_6_0= ruleEdges ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mW_7_0= 'maxWidth' ) ) otherlv_8= '=' ( (lv_maxWidth_9_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mD_10_0= 'maxDegree' ) ) otherlv_11= '=' ( (lv_maxDegree_12_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_pF_13_0= 'partitionFraction' ) ) otherlv_14= '=' ( (lv_fraction_15_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_hierarchy_16_0= ruleHierarchy ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'seed' otherlv_18= '=' ( (lv_seed_19_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'format' otherlv_21= '=' ( (lv_format_22_0= ruleFormats ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'filename' otherlv_24= '=' ( (lv_filename_25_0= RULE_STRING ) ) ) ) ) ) )*
                    {
                    // InternalGRandom.g:168:7: ( ({...}? => ( ({...}? => ( (lv_nodes_5_0= ruleNodes ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_edges_6_0= ruleEdges ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mW_7_0= 'maxWidth' ) ) otherlv_8= '=' ( (lv_maxWidth_9_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mD_10_0= 'maxDegree' ) ) otherlv_11= '=' ( (lv_maxDegree_12_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_pF_13_0= 'partitionFraction' ) ) otherlv_14= '=' ( (lv_fraction_15_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_hierarchy_16_0= ruleHierarchy ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'seed' otherlv_18= '=' ( (lv_seed_19_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'format' otherlv_21= '=' ( (lv_format_22_0= ruleFormats ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'filename' otherlv_24= '=' ( (lv_filename_25_0= RULE_STRING ) ) ) ) ) ) )*
                    loop2:
                    do {
                        int alt2=10;
                        alt2 = dfa2.predict(input);
                        switch (alt2) {
                    	case 1 :
                    	    // InternalGRandom.g:169:5: ({...}? => ( ({...}? => ( (lv_nodes_5_0= ruleNodes ) ) ) ) )
                    	    {
                    	    // InternalGRandom.g:169:5: ({...}? => ( ({...}? => ( (lv_nodes_5_0= ruleNodes ) ) ) ) )
                    	    // InternalGRandom.g:170:6: {...}? => ( ({...}? => ( (lv_nodes_5_0= ruleNodes ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 0) ) {
                    	        throw new FailedPredicateException(input, "ruleConfiguration", "getUnorderedGroupHelper().canSelect(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 0)");
                    	    }
                    	    // InternalGRandom.g:170:113: ( ({...}? => ( (lv_nodes_5_0= ruleNodes ) ) ) )
                    	    // InternalGRandom.g:171:7: ({...}? => ( (lv_nodes_5_0= ruleNodes ) ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 0);
                    	    						
                    	    // InternalGRandom.g:174:10: ({...}? => ( (lv_nodes_5_0= ruleNodes ) ) )
                    	    // InternalGRandom.g:174:11: {...}? => ( (lv_nodes_5_0= ruleNodes ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleConfiguration", "true");
                    	    }
                    	    // InternalGRandom.g:174:20: ( (lv_nodes_5_0= ruleNodes ) )
                    	    // InternalGRandom.g:174:21: (lv_nodes_5_0= ruleNodes )
                    	    {
                    	    // InternalGRandom.g:174:21: (lv_nodes_5_0= ruleNodes )
                    	    // InternalGRandom.g:175:11: lv_nodes_5_0= ruleNodes
                    	    {

                    	    											newCompositeNode(grammarAccess.getConfigurationAccess().getNodesNodesParserRuleCall_3_1_0_0());
                    	    										
                    	    pushFollow(FOLLOW_7);
                    	    lv_nodes_5_0=ruleNodes();

                    	    state._fsp--;


                    	    											if (current==null) {
                    	    												current = createModelElementForParent(grammarAccess.getConfigurationRule());
                    	    											}
                    	    											set(
                    	    												current,
                    	    												"nodes",
                    	    												lv_nodes_5_0,
                    	    												"de.cau.cs.kieler.kgraph.text.GRandom.Nodes");
                    	    											afterParserOrEnumRuleCall();
                    	    										

                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalGRandom.g:197:5: ({...}? => ( ({...}? => ( (lv_edges_6_0= ruleEdges ) ) ) ) )
                    	    {
                    	    // InternalGRandom.g:197:5: ({...}? => ( ({...}? => ( (lv_edges_6_0= ruleEdges ) ) ) ) )
                    	    // InternalGRandom.g:198:6: {...}? => ( ({...}? => ( (lv_edges_6_0= ruleEdges ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 1) ) {
                    	        throw new FailedPredicateException(input, "ruleConfiguration", "getUnorderedGroupHelper().canSelect(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 1)");
                    	    }
                    	    // InternalGRandom.g:198:113: ( ({...}? => ( (lv_edges_6_0= ruleEdges ) ) ) )
                    	    // InternalGRandom.g:199:7: ({...}? => ( (lv_edges_6_0= ruleEdges ) ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 1);
                    	    						
                    	    // InternalGRandom.g:202:10: ({...}? => ( (lv_edges_6_0= ruleEdges ) ) )
                    	    // InternalGRandom.g:202:11: {...}? => ( (lv_edges_6_0= ruleEdges ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleConfiguration", "true");
                    	    }
                    	    // InternalGRandom.g:202:20: ( (lv_edges_6_0= ruleEdges ) )
                    	    // InternalGRandom.g:202:21: (lv_edges_6_0= ruleEdges )
                    	    {
                    	    // InternalGRandom.g:202:21: (lv_edges_6_0= ruleEdges )
                    	    // InternalGRandom.g:203:11: lv_edges_6_0= ruleEdges
                    	    {

                    	    											newCompositeNode(grammarAccess.getConfigurationAccess().getEdgesEdgesParserRuleCall_3_1_1_0());
                    	    										
                    	    pushFollow(FOLLOW_7);
                    	    lv_edges_6_0=ruleEdges();

                    	    state._fsp--;


                    	    											if (current==null) {
                    	    												current = createModelElementForParent(grammarAccess.getConfigurationRule());
                    	    											}
                    	    											set(
                    	    												current,
                    	    												"edges",
                    	    												lv_edges_6_0,
                    	    												"de.cau.cs.kieler.kgraph.text.GRandom.Edges");
                    	    											afterParserOrEnumRuleCall();
                    	    										

                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 3 :
                    	    // InternalGRandom.g:225:5: ({...}? => ( ({...}? => ( ( (lv_mW_7_0= 'maxWidth' ) ) otherlv_8= '=' ( (lv_maxWidth_9_0= ruleInteger ) ) ) ) ) )
                    	    {
                    	    // InternalGRandom.g:225:5: ({...}? => ( ({...}? => ( ( (lv_mW_7_0= 'maxWidth' ) ) otherlv_8= '=' ( (lv_maxWidth_9_0= ruleInteger ) ) ) ) ) )
                    	    // InternalGRandom.g:226:6: {...}? => ( ({...}? => ( ( (lv_mW_7_0= 'maxWidth' ) ) otherlv_8= '=' ( (lv_maxWidth_9_0= ruleInteger ) ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 2) ) {
                    	        throw new FailedPredicateException(input, "ruleConfiguration", "getUnorderedGroupHelper().canSelect(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 2)");
                    	    }
                    	    // InternalGRandom.g:226:113: ( ({...}? => ( ( (lv_mW_7_0= 'maxWidth' ) ) otherlv_8= '=' ( (lv_maxWidth_9_0= ruleInteger ) ) ) ) )
                    	    // InternalGRandom.g:227:7: ({...}? => ( ( (lv_mW_7_0= 'maxWidth' ) ) otherlv_8= '=' ( (lv_maxWidth_9_0= ruleInteger ) ) ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 2);
                    	    						
                    	    // InternalGRandom.g:230:10: ({...}? => ( ( (lv_mW_7_0= 'maxWidth' ) ) otherlv_8= '=' ( (lv_maxWidth_9_0= ruleInteger ) ) ) )
                    	    // InternalGRandom.g:230:11: {...}? => ( ( (lv_mW_7_0= 'maxWidth' ) ) otherlv_8= '=' ( (lv_maxWidth_9_0= ruleInteger ) ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleConfiguration", "true");
                    	    }
                    	    // InternalGRandom.g:230:20: ( ( (lv_mW_7_0= 'maxWidth' ) ) otherlv_8= '=' ( (lv_maxWidth_9_0= ruleInteger ) ) )
                    	    // InternalGRandom.g:230:21: ( (lv_mW_7_0= 'maxWidth' ) ) otherlv_8= '=' ( (lv_maxWidth_9_0= ruleInteger ) )
                    	    {
                    	    // InternalGRandom.g:230:21: ( (lv_mW_7_0= 'maxWidth' ) )
                    	    // InternalGRandom.g:231:11: (lv_mW_7_0= 'maxWidth' )
                    	    {
                    	    // InternalGRandom.g:231:11: (lv_mW_7_0= 'maxWidth' )
                    	    // InternalGRandom.g:232:12: lv_mW_7_0= 'maxWidth'
                    	    {
                    	    lv_mW_7_0=(Token)match(input,13,FOLLOW_8); 

                    	    												newLeafNode(lv_mW_7_0, grammarAccess.getConfigurationAccess().getMWMaxWidthKeyword_3_1_2_0_0());
                    	    											

                    	    												if (current==null) {
                    	    													current = createModelElement(grammarAccess.getConfigurationRule());
                    	    												}
                    	    												setWithLastConsumed(current, "mW", true, "maxWidth");
                    	    											

                    	    }


                    	    }

                    	    otherlv_8=(Token)match(input,14,FOLLOW_4); 

                    	    										newLeafNode(otherlv_8, grammarAccess.getConfigurationAccess().getEqualsSignKeyword_3_1_2_1());
                    	    									
                    	    // InternalGRandom.g:248:10: ( (lv_maxWidth_9_0= ruleInteger ) )
                    	    // InternalGRandom.g:249:11: (lv_maxWidth_9_0= ruleInteger )
                    	    {
                    	    // InternalGRandom.g:249:11: (lv_maxWidth_9_0= ruleInteger )
                    	    // InternalGRandom.g:250:12: lv_maxWidth_9_0= ruleInteger
                    	    {

                    	    												newCompositeNode(grammarAccess.getConfigurationAccess().getMaxWidthIntegerParserRuleCall_3_1_2_2_0());
                    	    											
                    	    pushFollow(FOLLOW_7);
                    	    lv_maxWidth_9_0=ruleInteger();

                    	    state._fsp--;


                    	    												if (current==null) {
                    	    													current = createModelElementForParent(grammarAccess.getConfigurationRule());
                    	    												}
                    	    												set(
                    	    													current,
                    	    													"maxWidth",
                    	    													lv_maxWidth_9_0,
                    	    													"de.cau.cs.kieler.kgraph.text.GRandom.Integer");
                    	    												afterParserOrEnumRuleCall();
                    	    											

                    	    }


                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 4 :
                    	    // InternalGRandom.g:273:5: ({...}? => ( ({...}? => ( ( (lv_mD_10_0= 'maxDegree' ) ) otherlv_11= '=' ( (lv_maxDegree_12_0= ruleInteger ) ) ) ) ) )
                    	    {
                    	    // InternalGRandom.g:273:5: ({...}? => ( ({...}? => ( ( (lv_mD_10_0= 'maxDegree' ) ) otherlv_11= '=' ( (lv_maxDegree_12_0= ruleInteger ) ) ) ) ) )
                    	    // InternalGRandom.g:274:6: {...}? => ( ({...}? => ( ( (lv_mD_10_0= 'maxDegree' ) ) otherlv_11= '=' ( (lv_maxDegree_12_0= ruleInteger ) ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 3) ) {
                    	        throw new FailedPredicateException(input, "ruleConfiguration", "getUnorderedGroupHelper().canSelect(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 3)");
                    	    }
                    	    // InternalGRandom.g:274:113: ( ({...}? => ( ( (lv_mD_10_0= 'maxDegree' ) ) otherlv_11= '=' ( (lv_maxDegree_12_0= ruleInteger ) ) ) ) )
                    	    // InternalGRandom.g:275:7: ({...}? => ( ( (lv_mD_10_0= 'maxDegree' ) ) otherlv_11= '=' ( (lv_maxDegree_12_0= ruleInteger ) ) ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 3);
                    	    						
                    	    // InternalGRandom.g:278:10: ({...}? => ( ( (lv_mD_10_0= 'maxDegree' ) ) otherlv_11= '=' ( (lv_maxDegree_12_0= ruleInteger ) ) ) )
                    	    // InternalGRandom.g:278:11: {...}? => ( ( (lv_mD_10_0= 'maxDegree' ) ) otherlv_11= '=' ( (lv_maxDegree_12_0= ruleInteger ) ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleConfiguration", "true");
                    	    }
                    	    // InternalGRandom.g:278:20: ( ( (lv_mD_10_0= 'maxDegree' ) ) otherlv_11= '=' ( (lv_maxDegree_12_0= ruleInteger ) ) )
                    	    // InternalGRandom.g:278:21: ( (lv_mD_10_0= 'maxDegree' ) ) otherlv_11= '=' ( (lv_maxDegree_12_0= ruleInteger ) )
                    	    {
                    	    // InternalGRandom.g:278:21: ( (lv_mD_10_0= 'maxDegree' ) )
                    	    // InternalGRandom.g:279:11: (lv_mD_10_0= 'maxDegree' )
                    	    {
                    	    // InternalGRandom.g:279:11: (lv_mD_10_0= 'maxDegree' )
                    	    // InternalGRandom.g:280:12: lv_mD_10_0= 'maxDegree'
                    	    {
                    	    lv_mD_10_0=(Token)match(input,15,FOLLOW_8); 

                    	    												newLeafNode(lv_mD_10_0, grammarAccess.getConfigurationAccess().getMDMaxDegreeKeyword_3_1_3_0_0());
                    	    											

                    	    												if (current==null) {
                    	    													current = createModelElement(grammarAccess.getConfigurationRule());
                    	    												}
                    	    												setWithLastConsumed(current, "mD", true, "maxDegree");
                    	    											

                    	    }


                    	    }

                    	    otherlv_11=(Token)match(input,14,FOLLOW_4); 

                    	    										newLeafNode(otherlv_11, grammarAccess.getConfigurationAccess().getEqualsSignKeyword_3_1_3_1());
                    	    									
                    	    // InternalGRandom.g:296:10: ( (lv_maxDegree_12_0= ruleInteger ) )
                    	    // InternalGRandom.g:297:11: (lv_maxDegree_12_0= ruleInteger )
                    	    {
                    	    // InternalGRandom.g:297:11: (lv_maxDegree_12_0= ruleInteger )
                    	    // InternalGRandom.g:298:12: lv_maxDegree_12_0= ruleInteger
                    	    {

                    	    												newCompositeNode(grammarAccess.getConfigurationAccess().getMaxDegreeIntegerParserRuleCall_3_1_3_2_0());
                    	    											
                    	    pushFollow(FOLLOW_7);
                    	    lv_maxDegree_12_0=ruleInteger();

                    	    state._fsp--;


                    	    												if (current==null) {
                    	    													current = createModelElementForParent(grammarAccess.getConfigurationRule());
                    	    												}
                    	    												set(
                    	    													current,
                    	    													"maxDegree",
                    	    													lv_maxDegree_12_0,
                    	    													"de.cau.cs.kieler.kgraph.text.GRandom.Integer");
                    	    												afterParserOrEnumRuleCall();
                    	    											

                    	    }


                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 5 :
                    	    // InternalGRandom.g:321:5: ({...}? => ( ({...}? => ( ( (lv_pF_13_0= 'partitionFraction' ) ) otherlv_14= '=' ( (lv_fraction_15_0= ruleDoubleQuantity ) ) ) ) ) )
                    	    {
                    	    // InternalGRandom.g:321:5: ({...}? => ( ({...}? => ( ( (lv_pF_13_0= 'partitionFraction' ) ) otherlv_14= '=' ( (lv_fraction_15_0= ruleDoubleQuantity ) ) ) ) ) )
                    	    // InternalGRandom.g:322:6: {...}? => ( ({...}? => ( ( (lv_pF_13_0= 'partitionFraction' ) ) otherlv_14= '=' ( (lv_fraction_15_0= ruleDoubleQuantity ) ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 4) ) {
                    	        throw new FailedPredicateException(input, "ruleConfiguration", "getUnorderedGroupHelper().canSelect(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 4)");
                    	    }
                    	    // InternalGRandom.g:322:113: ( ({...}? => ( ( (lv_pF_13_0= 'partitionFraction' ) ) otherlv_14= '=' ( (lv_fraction_15_0= ruleDoubleQuantity ) ) ) ) )
                    	    // InternalGRandom.g:323:7: ({...}? => ( ( (lv_pF_13_0= 'partitionFraction' ) ) otherlv_14= '=' ( (lv_fraction_15_0= ruleDoubleQuantity ) ) ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 4);
                    	    						
                    	    // InternalGRandom.g:326:10: ({...}? => ( ( (lv_pF_13_0= 'partitionFraction' ) ) otherlv_14= '=' ( (lv_fraction_15_0= ruleDoubleQuantity ) ) ) )
                    	    // InternalGRandom.g:326:11: {...}? => ( ( (lv_pF_13_0= 'partitionFraction' ) ) otherlv_14= '=' ( (lv_fraction_15_0= ruleDoubleQuantity ) ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleConfiguration", "true");
                    	    }
                    	    // InternalGRandom.g:326:20: ( ( (lv_pF_13_0= 'partitionFraction' ) ) otherlv_14= '=' ( (lv_fraction_15_0= ruleDoubleQuantity ) ) )
                    	    // InternalGRandom.g:326:21: ( (lv_pF_13_0= 'partitionFraction' ) ) otherlv_14= '=' ( (lv_fraction_15_0= ruleDoubleQuantity ) )
                    	    {
                    	    // InternalGRandom.g:326:21: ( (lv_pF_13_0= 'partitionFraction' ) )
                    	    // InternalGRandom.g:327:11: (lv_pF_13_0= 'partitionFraction' )
                    	    {
                    	    // InternalGRandom.g:327:11: (lv_pF_13_0= 'partitionFraction' )
                    	    // InternalGRandom.g:328:12: lv_pF_13_0= 'partitionFraction'
                    	    {
                    	    lv_pF_13_0=(Token)match(input,16,FOLLOW_8); 

                    	    												newLeafNode(lv_pF_13_0, grammarAccess.getConfigurationAccess().getPFPartitionFractionKeyword_3_1_4_0_0());
                    	    											

                    	    												if (current==null) {
                    	    													current = createModelElement(grammarAccess.getConfigurationRule());
                    	    												}
                    	    												setWithLastConsumed(current, "pF", true, "partitionFraction");
                    	    											

                    	    }


                    	    }

                    	    otherlv_14=(Token)match(input,14,FOLLOW_4); 

                    	    										newLeafNode(otherlv_14, grammarAccess.getConfigurationAccess().getEqualsSignKeyword_3_1_4_1());
                    	    									
                    	    // InternalGRandom.g:344:10: ( (lv_fraction_15_0= ruleDoubleQuantity ) )
                    	    // InternalGRandom.g:345:11: (lv_fraction_15_0= ruleDoubleQuantity )
                    	    {
                    	    // InternalGRandom.g:345:11: (lv_fraction_15_0= ruleDoubleQuantity )
                    	    // InternalGRandom.g:346:12: lv_fraction_15_0= ruleDoubleQuantity
                    	    {

                    	    												newCompositeNode(grammarAccess.getConfigurationAccess().getFractionDoubleQuantityParserRuleCall_3_1_4_2_0());
                    	    											
                    	    pushFollow(FOLLOW_7);
                    	    lv_fraction_15_0=ruleDoubleQuantity();

                    	    state._fsp--;


                    	    												if (current==null) {
                    	    													current = createModelElementForParent(grammarAccess.getConfigurationRule());
                    	    												}
                    	    												set(
                    	    													current,
                    	    													"fraction",
                    	    													lv_fraction_15_0,
                    	    													"de.cau.cs.kieler.kgraph.text.GRandom.DoubleQuantity");
                    	    												afterParserOrEnumRuleCall();
                    	    											

                    	    }


                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 6 :
                    	    // InternalGRandom.g:369:5: ({...}? => ( ({...}? => ( (lv_hierarchy_16_0= ruleHierarchy ) ) ) ) )
                    	    {
                    	    // InternalGRandom.g:369:5: ({...}? => ( ({...}? => ( (lv_hierarchy_16_0= ruleHierarchy ) ) ) ) )
                    	    // InternalGRandom.g:370:6: {...}? => ( ({...}? => ( (lv_hierarchy_16_0= ruleHierarchy ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 5) ) {
                    	        throw new FailedPredicateException(input, "ruleConfiguration", "getUnorderedGroupHelper().canSelect(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 5)");
                    	    }
                    	    // InternalGRandom.g:370:113: ( ({...}? => ( (lv_hierarchy_16_0= ruleHierarchy ) ) ) )
                    	    // InternalGRandom.g:371:7: ({...}? => ( (lv_hierarchy_16_0= ruleHierarchy ) ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 5);
                    	    						
                    	    // InternalGRandom.g:374:10: ({...}? => ( (lv_hierarchy_16_0= ruleHierarchy ) ) )
                    	    // InternalGRandom.g:374:11: {...}? => ( (lv_hierarchy_16_0= ruleHierarchy ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleConfiguration", "true");
                    	    }
                    	    // InternalGRandom.g:374:20: ( (lv_hierarchy_16_0= ruleHierarchy ) )
                    	    // InternalGRandom.g:374:21: (lv_hierarchy_16_0= ruleHierarchy )
                    	    {
                    	    // InternalGRandom.g:374:21: (lv_hierarchy_16_0= ruleHierarchy )
                    	    // InternalGRandom.g:375:11: lv_hierarchy_16_0= ruleHierarchy
                    	    {

                    	    											newCompositeNode(grammarAccess.getConfigurationAccess().getHierarchyHierarchyParserRuleCall_3_1_5_0());
                    	    										
                    	    pushFollow(FOLLOW_7);
                    	    lv_hierarchy_16_0=ruleHierarchy();

                    	    state._fsp--;


                    	    											if (current==null) {
                    	    												current = createModelElementForParent(grammarAccess.getConfigurationRule());
                    	    											}
                    	    											set(
                    	    												current,
                    	    												"hierarchy",
                    	    												lv_hierarchy_16_0,
                    	    												"de.cau.cs.kieler.kgraph.text.GRandom.Hierarchy");
                    	    											afterParserOrEnumRuleCall();
                    	    										

                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 7 :
                    	    // InternalGRandom.g:397:5: ({...}? => ( ({...}? => (otherlv_17= 'seed' otherlv_18= '=' ( (lv_seed_19_0= ruleInteger ) ) ) ) ) )
                    	    {
                    	    // InternalGRandom.g:397:5: ({...}? => ( ({...}? => (otherlv_17= 'seed' otherlv_18= '=' ( (lv_seed_19_0= ruleInteger ) ) ) ) ) )
                    	    // InternalGRandom.g:398:6: {...}? => ( ({...}? => (otherlv_17= 'seed' otherlv_18= '=' ( (lv_seed_19_0= ruleInteger ) ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 6) ) {
                    	        throw new FailedPredicateException(input, "ruleConfiguration", "getUnorderedGroupHelper().canSelect(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 6)");
                    	    }
                    	    // InternalGRandom.g:398:113: ( ({...}? => (otherlv_17= 'seed' otherlv_18= '=' ( (lv_seed_19_0= ruleInteger ) ) ) ) )
                    	    // InternalGRandom.g:399:7: ({...}? => (otherlv_17= 'seed' otherlv_18= '=' ( (lv_seed_19_0= ruleInteger ) ) ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 6);
                    	    						
                    	    // InternalGRandom.g:402:10: ({...}? => (otherlv_17= 'seed' otherlv_18= '=' ( (lv_seed_19_0= ruleInteger ) ) ) )
                    	    // InternalGRandom.g:402:11: {...}? => (otherlv_17= 'seed' otherlv_18= '=' ( (lv_seed_19_0= ruleInteger ) ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleConfiguration", "true");
                    	    }
                    	    // InternalGRandom.g:402:20: (otherlv_17= 'seed' otherlv_18= '=' ( (lv_seed_19_0= ruleInteger ) ) )
                    	    // InternalGRandom.g:402:21: otherlv_17= 'seed' otherlv_18= '=' ( (lv_seed_19_0= ruleInteger ) )
                    	    {
                    	    otherlv_17=(Token)match(input,17,FOLLOW_8); 

                    	    										newLeafNode(otherlv_17, grammarAccess.getConfigurationAccess().getSeedKeyword_3_1_6_0());
                    	    									
                    	    otherlv_18=(Token)match(input,14,FOLLOW_4); 

                    	    										newLeafNode(otherlv_18, grammarAccess.getConfigurationAccess().getEqualsSignKeyword_3_1_6_1());
                    	    									
                    	    // InternalGRandom.g:410:10: ( (lv_seed_19_0= ruleInteger ) )
                    	    // InternalGRandom.g:411:11: (lv_seed_19_0= ruleInteger )
                    	    {
                    	    // InternalGRandom.g:411:11: (lv_seed_19_0= ruleInteger )
                    	    // InternalGRandom.g:412:12: lv_seed_19_0= ruleInteger
                    	    {

                    	    												newCompositeNode(grammarAccess.getConfigurationAccess().getSeedIntegerParserRuleCall_3_1_6_2_0());
                    	    											
                    	    pushFollow(FOLLOW_7);
                    	    lv_seed_19_0=ruleInteger();

                    	    state._fsp--;


                    	    												if (current==null) {
                    	    													current = createModelElementForParent(grammarAccess.getConfigurationRule());
                    	    												}
                    	    												set(
                    	    													current,
                    	    													"seed",
                    	    													lv_seed_19_0,
                    	    													"de.cau.cs.kieler.kgraph.text.GRandom.Integer");
                    	    												afterParserOrEnumRuleCall();
                    	    											

                    	    }


                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 8 :
                    	    // InternalGRandom.g:435:5: ({...}? => ( ({...}? => (otherlv_20= 'format' otherlv_21= '=' ( (lv_format_22_0= ruleFormats ) ) ) ) ) )
                    	    {
                    	    // InternalGRandom.g:435:5: ({...}? => ( ({...}? => (otherlv_20= 'format' otherlv_21= '=' ( (lv_format_22_0= ruleFormats ) ) ) ) ) )
                    	    // InternalGRandom.g:436:6: {...}? => ( ({...}? => (otherlv_20= 'format' otherlv_21= '=' ( (lv_format_22_0= ruleFormats ) ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 7) ) {
                    	        throw new FailedPredicateException(input, "ruleConfiguration", "getUnorderedGroupHelper().canSelect(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 7)");
                    	    }
                    	    // InternalGRandom.g:436:113: ( ({...}? => (otherlv_20= 'format' otherlv_21= '=' ( (lv_format_22_0= ruleFormats ) ) ) ) )
                    	    // InternalGRandom.g:437:7: ({...}? => (otherlv_20= 'format' otherlv_21= '=' ( (lv_format_22_0= ruleFormats ) ) ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 7);
                    	    						
                    	    // InternalGRandom.g:440:10: ({...}? => (otherlv_20= 'format' otherlv_21= '=' ( (lv_format_22_0= ruleFormats ) ) ) )
                    	    // InternalGRandom.g:440:11: {...}? => (otherlv_20= 'format' otherlv_21= '=' ( (lv_format_22_0= ruleFormats ) ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleConfiguration", "true");
                    	    }
                    	    // InternalGRandom.g:440:20: (otherlv_20= 'format' otherlv_21= '=' ( (lv_format_22_0= ruleFormats ) ) )
                    	    // InternalGRandom.g:440:21: otherlv_20= 'format' otherlv_21= '=' ( (lv_format_22_0= ruleFormats ) )
                    	    {
                    	    otherlv_20=(Token)match(input,18,FOLLOW_8); 

                    	    										newLeafNode(otherlv_20, grammarAccess.getConfigurationAccess().getFormatKeyword_3_1_7_0());
                    	    									
                    	    otherlv_21=(Token)match(input,14,FOLLOW_9); 

                    	    										newLeafNode(otherlv_21, grammarAccess.getConfigurationAccess().getEqualsSignKeyword_3_1_7_1());
                    	    									
                    	    // InternalGRandom.g:448:10: ( (lv_format_22_0= ruleFormats ) )
                    	    // InternalGRandom.g:449:11: (lv_format_22_0= ruleFormats )
                    	    {
                    	    // InternalGRandom.g:449:11: (lv_format_22_0= ruleFormats )
                    	    // InternalGRandom.g:450:12: lv_format_22_0= ruleFormats
                    	    {

                    	    												newCompositeNode(grammarAccess.getConfigurationAccess().getFormatFormatsEnumRuleCall_3_1_7_2_0());
                    	    											
                    	    pushFollow(FOLLOW_7);
                    	    lv_format_22_0=ruleFormats();

                    	    state._fsp--;


                    	    												if (current==null) {
                    	    													current = createModelElementForParent(grammarAccess.getConfigurationRule());
                    	    												}
                    	    												set(
                    	    													current,
                    	    													"format",
                    	    													lv_format_22_0,
                    	    													"de.cau.cs.kieler.kgraph.text.GRandom.Formats");
                    	    												afterParserOrEnumRuleCall();
                    	    											

                    	    }


                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 9 :
                    	    // InternalGRandom.g:473:5: ({...}? => ( ({...}? => (otherlv_23= 'filename' otherlv_24= '=' ( (lv_filename_25_0= RULE_STRING ) ) ) ) ) )
                    	    {
                    	    // InternalGRandom.g:473:5: ({...}? => ( ({...}? => (otherlv_23= 'filename' otherlv_24= '=' ( (lv_filename_25_0= RULE_STRING ) ) ) ) ) )
                    	    // InternalGRandom.g:474:6: {...}? => ( ({...}? => (otherlv_23= 'filename' otherlv_24= '=' ( (lv_filename_25_0= RULE_STRING ) ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 8) ) {
                    	        throw new FailedPredicateException(input, "ruleConfiguration", "getUnorderedGroupHelper().canSelect(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 8)");
                    	    }
                    	    // InternalGRandom.g:474:113: ( ({...}? => (otherlv_23= 'filename' otherlv_24= '=' ( (lv_filename_25_0= RULE_STRING ) ) ) ) )
                    	    // InternalGRandom.g:475:7: ({...}? => (otherlv_23= 'filename' otherlv_24= '=' ( (lv_filename_25_0= RULE_STRING ) ) ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 8);
                    	    						
                    	    // InternalGRandom.g:478:10: ({...}? => (otherlv_23= 'filename' otherlv_24= '=' ( (lv_filename_25_0= RULE_STRING ) ) ) )
                    	    // InternalGRandom.g:478:11: {...}? => (otherlv_23= 'filename' otherlv_24= '=' ( (lv_filename_25_0= RULE_STRING ) ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleConfiguration", "true");
                    	    }
                    	    // InternalGRandom.g:478:20: (otherlv_23= 'filename' otherlv_24= '=' ( (lv_filename_25_0= RULE_STRING ) ) )
                    	    // InternalGRandom.g:478:21: otherlv_23= 'filename' otherlv_24= '=' ( (lv_filename_25_0= RULE_STRING ) )
                    	    {
                    	    otherlv_23=(Token)match(input,19,FOLLOW_8); 

                    	    										newLeafNode(otherlv_23, grammarAccess.getConfigurationAccess().getFilenameKeyword_3_1_8_0());
                    	    									
                    	    otherlv_24=(Token)match(input,14,FOLLOW_10); 

                    	    										newLeafNode(otherlv_24, grammarAccess.getConfigurationAccess().getEqualsSignKeyword_3_1_8_1());
                    	    									
                    	    // InternalGRandom.g:486:10: ( (lv_filename_25_0= RULE_STRING ) )
                    	    // InternalGRandom.g:487:11: (lv_filename_25_0= RULE_STRING )
                    	    {
                    	    // InternalGRandom.g:487:11: (lv_filename_25_0= RULE_STRING )
                    	    // InternalGRandom.g:488:12: lv_filename_25_0= RULE_STRING
                    	    {
                    	    lv_filename_25_0=(Token)match(input,RULE_STRING,FOLLOW_7); 

                    	    												newLeafNode(lv_filename_25_0, grammarAccess.getConfigurationAccess().getFilenameSTRINGTerminalRuleCall_3_1_8_2_0());
                    	    											

                    	    												if (current==null) {
                    	    													current = createModelElement(grammarAccess.getConfigurationRule());
                    	    												}
                    	    												setWithLastConsumed(
                    	    													current,
                    	    													"filename",
                    	    													lv_filename_25_0,
                    	    													"org.eclipse.xtext.common.Terminals.STRING");
                    	    											

                    	    }


                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);


                    }


                    }

                     
                    					  getUnorderedGroupHelper().leave(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1());
                    					

                    }

                    otherlv_26=(Token)match(input,20,FOLLOW_2); 

                    				newLeafNode(otherlv_26, grammarAccess.getConfigurationAccess().getRightCurlyBracketKeyword_3_2());
                    			

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
    // $ANTLR end "ruleConfiguration"


    // $ANTLR start "entryRuleHierarchy"
    // InternalGRandom.g:526:1: entryRuleHierarchy returns [EObject current=null] : iv_ruleHierarchy= ruleHierarchy EOF ;
    public final EObject entryRuleHierarchy() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleHierarchy = null;


        try {
            // InternalGRandom.g:526:50: (iv_ruleHierarchy= ruleHierarchy EOF )
            // InternalGRandom.g:527:2: iv_ruleHierarchy= ruleHierarchy EOF
            {
             newCompositeNode(grammarAccess.getHierarchyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleHierarchy=ruleHierarchy();

            state._fsp--;

             current =iv_ruleHierarchy; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleHierarchy"


    // $ANTLR start "ruleHierarchy"
    // InternalGRandom.g:533:1: ruleHierarchy returns [EObject current=null] : ( () otherlv_1= 'hierarchy' (otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'levels' otherlv_5= '=' ( (lv_levels_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'cross-hierarchy edges' otherlv_8= '=' ( (lv_edges_9_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'compound nodes' otherlv_11= '=' ( (lv_numHierarchNodes_12_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'cross-hierarchy relative edges' otherlv_14= '=' ( (lv_crossHierarchRel_15_0= ruleDoubleQuantity ) ) ) ) ) ) )* ) ) ) otherlv_16= '}' )? ) ;
    public final EObject ruleHierarchy() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        EObject lv_levels_6_0 = null;

        EObject lv_edges_9_0 = null;

        EObject lv_numHierarchNodes_12_0 = null;

        EObject lv_crossHierarchRel_15_0 = null;



        	enterRule();

        try {
            // InternalGRandom.g:539:2: ( ( () otherlv_1= 'hierarchy' (otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'levels' otherlv_5= '=' ( (lv_levels_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'cross-hierarchy edges' otherlv_8= '=' ( (lv_edges_9_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'compound nodes' otherlv_11= '=' ( (lv_numHierarchNodes_12_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'cross-hierarchy relative edges' otherlv_14= '=' ( (lv_crossHierarchRel_15_0= ruleDoubleQuantity ) ) ) ) ) ) )* ) ) ) otherlv_16= '}' )? ) )
            // InternalGRandom.g:540:2: ( () otherlv_1= 'hierarchy' (otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'levels' otherlv_5= '=' ( (lv_levels_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'cross-hierarchy edges' otherlv_8= '=' ( (lv_edges_9_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'compound nodes' otherlv_11= '=' ( (lv_numHierarchNodes_12_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'cross-hierarchy relative edges' otherlv_14= '=' ( (lv_crossHierarchRel_15_0= ruleDoubleQuantity ) ) ) ) ) ) )* ) ) ) otherlv_16= '}' )? )
            {
            // InternalGRandom.g:540:2: ( () otherlv_1= 'hierarchy' (otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'levels' otherlv_5= '=' ( (lv_levels_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'cross-hierarchy edges' otherlv_8= '=' ( (lv_edges_9_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'compound nodes' otherlv_11= '=' ( (lv_numHierarchNodes_12_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'cross-hierarchy relative edges' otherlv_14= '=' ( (lv_crossHierarchRel_15_0= ruleDoubleQuantity ) ) ) ) ) ) )* ) ) ) otherlv_16= '}' )? )
            // InternalGRandom.g:541:3: () otherlv_1= 'hierarchy' (otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'levels' otherlv_5= '=' ( (lv_levels_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'cross-hierarchy edges' otherlv_8= '=' ( (lv_edges_9_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'compound nodes' otherlv_11= '=' ( (lv_numHierarchNodes_12_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'cross-hierarchy relative edges' otherlv_14= '=' ( (lv_crossHierarchRel_15_0= ruleDoubleQuantity ) ) ) ) ) ) )* ) ) ) otherlv_16= '}' )?
            {
            // InternalGRandom.g:541:3: ()
            // InternalGRandom.g:542:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getHierarchyAccess().getHierarchyAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,21,FOLLOW_6); 

            			newLeafNode(otherlv_1, grammarAccess.getHierarchyAccess().getHierarchyKeyword_1());
            		
            // InternalGRandom.g:552:3: (otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'levels' otherlv_5= '=' ( (lv_levels_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'cross-hierarchy edges' otherlv_8= '=' ( (lv_edges_9_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'compound nodes' otherlv_11= '=' ( (lv_numHierarchNodes_12_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'cross-hierarchy relative edges' otherlv_14= '=' ( (lv_crossHierarchRel_15_0= ruleDoubleQuantity ) ) ) ) ) ) )* ) ) ) otherlv_16= '}' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==12) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // InternalGRandom.g:553:4: otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'levels' otherlv_5= '=' ( (lv_levels_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'cross-hierarchy edges' otherlv_8= '=' ( (lv_edges_9_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'compound nodes' otherlv_11= '=' ( (lv_numHierarchNodes_12_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'cross-hierarchy relative edges' otherlv_14= '=' ( (lv_crossHierarchRel_15_0= ruleDoubleQuantity ) ) ) ) ) ) )* ) ) ) otherlv_16= '}'
                    {
                    otherlv_2=(Token)match(input,12,FOLLOW_11); 

                    				newLeafNode(otherlv_2, grammarAccess.getHierarchyAccess().getLeftCurlyBracketKeyword_2_0());
                    			
                    // InternalGRandom.g:557:4: ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'levels' otherlv_5= '=' ( (lv_levels_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'cross-hierarchy edges' otherlv_8= '=' ( (lv_edges_9_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'compound nodes' otherlv_11= '=' ( (lv_numHierarchNodes_12_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'cross-hierarchy relative edges' otherlv_14= '=' ( (lv_crossHierarchRel_15_0= ruleDoubleQuantity ) ) ) ) ) ) )* ) ) )
                    // InternalGRandom.g:558:5: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'levels' otherlv_5= '=' ( (lv_levels_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'cross-hierarchy edges' otherlv_8= '=' ( (lv_edges_9_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'compound nodes' otherlv_11= '=' ( (lv_numHierarchNodes_12_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'cross-hierarchy relative edges' otherlv_14= '=' ( (lv_crossHierarchRel_15_0= ruleDoubleQuantity ) ) ) ) ) ) )* ) )
                    {
                    // InternalGRandom.g:558:5: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'levels' otherlv_5= '=' ( (lv_levels_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'cross-hierarchy edges' otherlv_8= '=' ( (lv_edges_9_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'compound nodes' otherlv_11= '=' ( (lv_numHierarchNodes_12_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'cross-hierarchy relative edges' otherlv_14= '=' ( (lv_crossHierarchRel_15_0= ruleDoubleQuantity ) ) ) ) ) ) )* ) )
                    // InternalGRandom.g:559:6: ( ( ({...}? => ( ({...}? => (otherlv_4= 'levels' otherlv_5= '=' ( (lv_levels_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'cross-hierarchy edges' otherlv_8= '=' ( (lv_edges_9_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'compound nodes' otherlv_11= '=' ( (lv_numHierarchNodes_12_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'cross-hierarchy relative edges' otherlv_14= '=' ( (lv_crossHierarchRel_15_0= ruleDoubleQuantity ) ) ) ) ) ) )* )
                    {
                     
                    					  getUnorderedGroupHelper().enter(grammarAccess.getHierarchyAccess().getUnorderedGroup_2_1());
                    					
                    // InternalGRandom.g:562:6: ( ( ({...}? => ( ({...}? => (otherlv_4= 'levels' otherlv_5= '=' ( (lv_levels_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'cross-hierarchy edges' otherlv_8= '=' ( (lv_edges_9_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'compound nodes' otherlv_11= '=' ( (lv_numHierarchNodes_12_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'cross-hierarchy relative edges' otherlv_14= '=' ( (lv_crossHierarchRel_15_0= ruleDoubleQuantity ) ) ) ) ) ) )* )
                    // InternalGRandom.g:563:7: ( ({...}? => ( ({...}? => (otherlv_4= 'levels' otherlv_5= '=' ( (lv_levels_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'cross-hierarchy edges' otherlv_8= '=' ( (lv_edges_9_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'compound nodes' otherlv_11= '=' ( (lv_numHierarchNodes_12_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'cross-hierarchy relative edges' otherlv_14= '=' ( (lv_crossHierarchRel_15_0= ruleDoubleQuantity ) ) ) ) ) ) )*
                    {
                    // InternalGRandom.g:563:7: ( ({...}? => ( ({...}? => (otherlv_4= 'levels' otherlv_5= '=' ( (lv_levels_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'cross-hierarchy edges' otherlv_8= '=' ( (lv_edges_9_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_10= 'compound nodes' otherlv_11= '=' ( (lv_numHierarchNodes_12_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_13= 'cross-hierarchy relative edges' otherlv_14= '=' ( (lv_crossHierarchRel_15_0= ruleDoubleQuantity ) ) ) ) ) ) )*
                    loop4:
                    do {
                        int alt4=5;
                        int LA4_0 = input.LA(1);

                        if ( LA4_0 == 22 && getUnorderedGroupHelper().canSelect(grammarAccess.getHierarchyAccess().getUnorderedGroup_2_1(), 0) ) {
                            alt4=1;
                        }
                        else if ( LA4_0 == 23 && getUnorderedGroupHelper().canSelect(grammarAccess.getHierarchyAccess().getUnorderedGroup_2_1(), 1) ) {
                            alt4=2;
                        }
                        else if ( LA4_0 == 24 && getUnorderedGroupHelper().canSelect(grammarAccess.getHierarchyAccess().getUnorderedGroup_2_1(), 2) ) {
                            alt4=3;
                        }
                        else if ( LA4_0 == 25 && getUnorderedGroupHelper().canSelect(grammarAccess.getHierarchyAccess().getUnorderedGroup_2_1(), 3) ) {
                            alt4=4;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // InternalGRandom.g:564:5: ({...}? => ( ({...}? => (otherlv_4= 'levels' otherlv_5= '=' ( (lv_levels_6_0= ruleDoubleQuantity ) ) ) ) ) )
                    	    {
                    	    // InternalGRandom.g:564:5: ({...}? => ( ({...}? => (otherlv_4= 'levels' otherlv_5= '=' ( (lv_levels_6_0= ruleDoubleQuantity ) ) ) ) ) )
                    	    // InternalGRandom.g:565:6: {...}? => ( ({...}? => (otherlv_4= 'levels' otherlv_5= '=' ( (lv_levels_6_0= ruleDoubleQuantity ) ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getHierarchyAccess().getUnorderedGroup_2_1(), 0) ) {
                    	        throw new FailedPredicateException(input, "ruleHierarchy", "getUnorderedGroupHelper().canSelect(grammarAccess.getHierarchyAccess().getUnorderedGroup_2_1(), 0)");
                    	    }
                    	    // InternalGRandom.g:565:109: ( ({...}? => (otherlv_4= 'levels' otherlv_5= '=' ( (lv_levels_6_0= ruleDoubleQuantity ) ) ) ) )
                    	    // InternalGRandom.g:566:7: ({...}? => (otherlv_4= 'levels' otherlv_5= '=' ( (lv_levels_6_0= ruleDoubleQuantity ) ) ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getHierarchyAccess().getUnorderedGroup_2_1(), 0);
                    	    						
                    	    // InternalGRandom.g:569:10: ({...}? => (otherlv_4= 'levels' otherlv_5= '=' ( (lv_levels_6_0= ruleDoubleQuantity ) ) ) )
                    	    // InternalGRandom.g:569:11: {...}? => (otherlv_4= 'levels' otherlv_5= '=' ( (lv_levels_6_0= ruleDoubleQuantity ) ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleHierarchy", "true");
                    	    }
                    	    // InternalGRandom.g:569:20: (otherlv_4= 'levels' otherlv_5= '=' ( (lv_levels_6_0= ruleDoubleQuantity ) ) )
                    	    // InternalGRandom.g:569:21: otherlv_4= 'levels' otherlv_5= '=' ( (lv_levels_6_0= ruleDoubleQuantity ) )
                    	    {
                    	    otherlv_4=(Token)match(input,22,FOLLOW_8); 

                    	    										newLeafNode(otherlv_4, grammarAccess.getHierarchyAccess().getLevelsKeyword_2_1_0_0());
                    	    									
                    	    otherlv_5=(Token)match(input,14,FOLLOW_4); 

                    	    										newLeafNode(otherlv_5, grammarAccess.getHierarchyAccess().getEqualsSignKeyword_2_1_0_1());
                    	    									
                    	    // InternalGRandom.g:577:10: ( (lv_levels_6_0= ruleDoubleQuantity ) )
                    	    // InternalGRandom.g:578:11: (lv_levels_6_0= ruleDoubleQuantity )
                    	    {
                    	    // InternalGRandom.g:578:11: (lv_levels_6_0= ruleDoubleQuantity )
                    	    // InternalGRandom.g:579:12: lv_levels_6_0= ruleDoubleQuantity
                    	    {

                    	    												newCompositeNode(grammarAccess.getHierarchyAccess().getLevelsDoubleQuantityParserRuleCall_2_1_0_2_0());
                    	    											
                    	    pushFollow(FOLLOW_11);
                    	    lv_levels_6_0=ruleDoubleQuantity();

                    	    state._fsp--;


                    	    												if (current==null) {
                    	    													current = createModelElementForParent(grammarAccess.getHierarchyRule());
                    	    												}
                    	    												set(
                    	    													current,
                    	    													"levels",
                    	    													lv_levels_6_0,
                    	    													"de.cau.cs.kieler.kgraph.text.GRandom.DoubleQuantity");
                    	    												afterParserOrEnumRuleCall();
                    	    											

                    	    }


                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getHierarchyAccess().getUnorderedGroup_2_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalGRandom.g:602:5: ({...}? => ( ({...}? => (otherlv_7= 'cross-hierarchy edges' otherlv_8= '=' ( (lv_edges_9_0= ruleDoubleQuantity ) ) ) ) ) )
                    	    {
                    	    // InternalGRandom.g:602:5: ({...}? => ( ({...}? => (otherlv_7= 'cross-hierarchy edges' otherlv_8= '=' ( (lv_edges_9_0= ruleDoubleQuantity ) ) ) ) ) )
                    	    // InternalGRandom.g:603:6: {...}? => ( ({...}? => (otherlv_7= 'cross-hierarchy edges' otherlv_8= '=' ( (lv_edges_9_0= ruleDoubleQuantity ) ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getHierarchyAccess().getUnorderedGroup_2_1(), 1) ) {
                    	        throw new FailedPredicateException(input, "ruleHierarchy", "getUnorderedGroupHelper().canSelect(grammarAccess.getHierarchyAccess().getUnorderedGroup_2_1(), 1)");
                    	    }
                    	    // InternalGRandom.g:603:109: ( ({...}? => (otherlv_7= 'cross-hierarchy edges' otherlv_8= '=' ( (lv_edges_9_0= ruleDoubleQuantity ) ) ) ) )
                    	    // InternalGRandom.g:604:7: ({...}? => (otherlv_7= 'cross-hierarchy edges' otherlv_8= '=' ( (lv_edges_9_0= ruleDoubleQuantity ) ) ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getHierarchyAccess().getUnorderedGroup_2_1(), 1);
                    	    						
                    	    // InternalGRandom.g:607:10: ({...}? => (otherlv_7= 'cross-hierarchy edges' otherlv_8= '=' ( (lv_edges_9_0= ruleDoubleQuantity ) ) ) )
                    	    // InternalGRandom.g:607:11: {...}? => (otherlv_7= 'cross-hierarchy edges' otherlv_8= '=' ( (lv_edges_9_0= ruleDoubleQuantity ) ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleHierarchy", "true");
                    	    }
                    	    // InternalGRandom.g:607:20: (otherlv_7= 'cross-hierarchy edges' otherlv_8= '=' ( (lv_edges_9_0= ruleDoubleQuantity ) ) )
                    	    // InternalGRandom.g:607:21: otherlv_7= 'cross-hierarchy edges' otherlv_8= '=' ( (lv_edges_9_0= ruleDoubleQuantity ) )
                    	    {
                    	    otherlv_7=(Token)match(input,23,FOLLOW_8); 

                    	    										newLeafNode(otherlv_7, grammarAccess.getHierarchyAccess().getCrossHierarchyEdgesKeyword_2_1_1_0());
                    	    									
                    	    otherlv_8=(Token)match(input,14,FOLLOW_4); 

                    	    										newLeafNode(otherlv_8, grammarAccess.getHierarchyAccess().getEqualsSignKeyword_2_1_1_1());
                    	    									
                    	    // InternalGRandom.g:615:10: ( (lv_edges_9_0= ruleDoubleQuantity ) )
                    	    // InternalGRandom.g:616:11: (lv_edges_9_0= ruleDoubleQuantity )
                    	    {
                    	    // InternalGRandom.g:616:11: (lv_edges_9_0= ruleDoubleQuantity )
                    	    // InternalGRandom.g:617:12: lv_edges_9_0= ruleDoubleQuantity
                    	    {

                    	    												newCompositeNode(grammarAccess.getHierarchyAccess().getEdgesDoubleQuantityParserRuleCall_2_1_1_2_0());
                    	    											
                    	    pushFollow(FOLLOW_11);
                    	    lv_edges_9_0=ruleDoubleQuantity();

                    	    state._fsp--;


                    	    												if (current==null) {
                    	    													current = createModelElementForParent(grammarAccess.getHierarchyRule());
                    	    												}
                    	    												set(
                    	    													current,
                    	    													"edges",
                    	    													lv_edges_9_0,
                    	    													"de.cau.cs.kieler.kgraph.text.GRandom.DoubleQuantity");
                    	    												afterParserOrEnumRuleCall();
                    	    											

                    	    }


                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getHierarchyAccess().getUnorderedGroup_2_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 3 :
                    	    // InternalGRandom.g:640:5: ({...}? => ( ({...}? => (otherlv_10= 'compound nodes' otherlv_11= '=' ( (lv_numHierarchNodes_12_0= ruleDoubleQuantity ) ) ) ) ) )
                    	    {
                    	    // InternalGRandom.g:640:5: ({...}? => ( ({...}? => (otherlv_10= 'compound nodes' otherlv_11= '=' ( (lv_numHierarchNodes_12_0= ruleDoubleQuantity ) ) ) ) ) )
                    	    // InternalGRandom.g:641:6: {...}? => ( ({...}? => (otherlv_10= 'compound nodes' otherlv_11= '=' ( (lv_numHierarchNodes_12_0= ruleDoubleQuantity ) ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getHierarchyAccess().getUnorderedGroup_2_1(), 2) ) {
                    	        throw new FailedPredicateException(input, "ruleHierarchy", "getUnorderedGroupHelper().canSelect(grammarAccess.getHierarchyAccess().getUnorderedGroup_2_1(), 2)");
                    	    }
                    	    // InternalGRandom.g:641:109: ( ({...}? => (otherlv_10= 'compound nodes' otherlv_11= '=' ( (lv_numHierarchNodes_12_0= ruleDoubleQuantity ) ) ) ) )
                    	    // InternalGRandom.g:642:7: ({...}? => (otherlv_10= 'compound nodes' otherlv_11= '=' ( (lv_numHierarchNodes_12_0= ruleDoubleQuantity ) ) ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getHierarchyAccess().getUnorderedGroup_2_1(), 2);
                    	    						
                    	    // InternalGRandom.g:645:10: ({...}? => (otherlv_10= 'compound nodes' otherlv_11= '=' ( (lv_numHierarchNodes_12_0= ruleDoubleQuantity ) ) ) )
                    	    // InternalGRandom.g:645:11: {...}? => (otherlv_10= 'compound nodes' otherlv_11= '=' ( (lv_numHierarchNodes_12_0= ruleDoubleQuantity ) ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleHierarchy", "true");
                    	    }
                    	    // InternalGRandom.g:645:20: (otherlv_10= 'compound nodes' otherlv_11= '=' ( (lv_numHierarchNodes_12_0= ruleDoubleQuantity ) ) )
                    	    // InternalGRandom.g:645:21: otherlv_10= 'compound nodes' otherlv_11= '=' ( (lv_numHierarchNodes_12_0= ruleDoubleQuantity ) )
                    	    {
                    	    otherlv_10=(Token)match(input,24,FOLLOW_8); 

                    	    										newLeafNode(otherlv_10, grammarAccess.getHierarchyAccess().getCompoundNodesKeyword_2_1_2_0());
                    	    									
                    	    otherlv_11=(Token)match(input,14,FOLLOW_4); 

                    	    										newLeafNode(otherlv_11, grammarAccess.getHierarchyAccess().getEqualsSignKeyword_2_1_2_1());
                    	    									
                    	    // InternalGRandom.g:653:10: ( (lv_numHierarchNodes_12_0= ruleDoubleQuantity ) )
                    	    // InternalGRandom.g:654:11: (lv_numHierarchNodes_12_0= ruleDoubleQuantity )
                    	    {
                    	    // InternalGRandom.g:654:11: (lv_numHierarchNodes_12_0= ruleDoubleQuantity )
                    	    // InternalGRandom.g:655:12: lv_numHierarchNodes_12_0= ruleDoubleQuantity
                    	    {

                    	    												newCompositeNode(grammarAccess.getHierarchyAccess().getNumHierarchNodesDoubleQuantityParserRuleCall_2_1_2_2_0());
                    	    											
                    	    pushFollow(FOLLOW_11);
                    	    lv_numHierarchNodes_12_0=ruleDoubleQuantity();

                    	    state._fsp--;


                    	    												if (current==null) {
                    	    													current = createModelElementForParent(grammarAccess.getHierarchyRule());
                    	    												}
                    	    												set(
                    	    													current,
                    	    													"numHierarchNodes",
                    	    													lv_numHierarchNodes_12_0,
                    	    													"de.cau.cs.kieler.kgraph.text.GRandom.DoubleQuantity");
                    	    												afterParserOrEnumRuleCall();
                    	    											

                    	    }


                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getHierarchyAccess().getUnorderedGroup_2_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 4 :
                    	    // InternalGRandom.g:678:5: ({...}? => ( ({...}? => (otherlv_13= 'cross-hierarchy relative edges' otherlv_14= '=' ( (lv_crossHierarchRel_15_0= ruleDoubleQuantity ) ) ) ) ) )
                    	    {
                    	    // InternalGRandom.g:678:5: ({...}? => ( ({...}? => (otherlv_13= 'cross-hierarchy relative edges' otherlv_14= '=' ( (lv_crossHierarchRel_15_0= ruleDoubleQuantity ) ) ) ) ) )
                    	    // InternalGRandom.g:679:6: {...}? => ( ({...}? => (otherlv_13= 'cross-hierarchy relative edges' otherlv_14= '=' ( (lv_crossHierarchRel_15_0= ruleDoubleQuantity ) ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getHierarchyAccess().getUnorderedGroup_2_1(), 3) ) {
                    	        throw new FailedPredicateException(input, "ruleHierarchy", "getUnorderedGroupHelper().canSelect(grammarAccess.getHierarchyAccess().getUnorderedGroup_2_1(), 3)");
                    	    }
                    	    // InternalGRandom.g:679:109: ( ({...}? => (otherlv_13= 'cross-hierarchy relative edges' otherlv_14= '=' ( (lv_crossHierarchRel_15_0= ruleDoubleQuantity ) ) ) ) )
                    	    // InternalGRandom.g:680:7: ({...}? => (otherlv_13= 'cross-hierarchy relative edges' otherlv_14= '=' ( (lv_crossHierarchRel_15_0= ruleDoubleQuantity ) ) ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getHierarchyAccess().getUnorderedGroup_2_1(), 3);
                    	    						
                    	    // InternalGRandom.g:683:10: ({...}? => (otherlv_13= 'cross-hierarchy relative edges' otherlv_14= '=' ( (lv_crossHierarchRel_15_0= ruleDoubleQuantity ) ) ) )
                    	    // InternalGRandom.g:683:11: {...}? => (otherlv_13= 'cross-hierarchy relative edges' otherlv_14= '=' ( (lv_crossHierarchRel_15_0= ruleDoubleQuantity ) ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleHierarchy", "true");
                    	    }
                    	    // InternalGRandom.g:683:20: (otherlv_13= 'cross-hierarchy relative edges' otherlv_14= '=' ( (lv_crossHierarchRel_15_0= ruleDoubleQuantity ) ) )
                    	    // InternalGRandom.g:683:21: otherlv_13= 'cross-hierarchy relative edges' otherlv_14= '=' ( (lv_crossHierarchRel_15_0= ruleDoubleQuantity ) )
                    	    {
                    	    otherlv_13=(Token)match(input,25,FOLLOW_8); 

                    	    										newLeafNode(otherlv_13, grammarAccess.getHierarchyAccess().getCrossHierarchyRelativeEdgesKeyword_2_1_3_0());
                    	    									
                    	    otherlv_14=(Token)match(input,14,FOLLOW_4); 

                    	    										newLeafNode(otherlv_14, grammarAccess.getHierarchyAccess().getEqualsSignKeyword_2_1_3_1());
                    	    									
                    	    // InternalGRandom.g:691:10: ( (lv_crossHierarchRel_15_0= ruleDoubleQuantity ) )
                    	    // InternalGRandom.g:692:11: (lv_crossHierarchRel_15_0= ruleDoubleQuantity )
                    	    {
                    	    // InternalGRandom.g:692:11: (lv_crossHierarchRel_15_0= ruleDoubleQuantity )
                    	    // InternalGRandom.g:693:12: lv_crossHierarchRel_15_0= ruleDoubleQuantity
                    	    {

                    	    												newCompositeNode(grammarAccess.getHierarchyAccess().getCrossHierarchRelDoubleQuantityParserRuleCall_2_1_3_2_0());
                    	    											
                    	    pushFollow(FOLLOW_11);
                    	    lv_crossHierarchRel_15_0=ruleDoubleQuantity();

                    	    state._fsp--;


                    	    												if (current==null) {
                    	    													current = createModelElementForParent(grammarAccess.getHierarchyRule());
                    	    												}
                    	    												set(
                    	    													current,
                    	    													"crossHierarchRel",
                    	    													lv_crossHierarchRel_15_0,
                    	    													"de.cau.cs.kieler.kgraph.text.GRandom.DoubleQuantity");
                    	    												afterParserOrEnumRuleCall();
                    	    											

                    	    }


                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getHierarchyAccess().getUnorderedGroup_2_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    }


                    }

                     
                    					  getUnorderedGroupHelper().leave(grammarAccess.getHierarchyAccess().getUnorderedGroup_2_1());
                    					

                    }

                    otherlv_16=(Token)match(input,20,FOLLOW_2); 

                    				newLeafNode(otherlv_16, grammarAccess.getHierarchyAccess().getRightCurlyBracketKeyword_2_2());
                    			

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
    // $ANTLR end "ruleHierarchy"


    // $ANTLR start "entryRuleEdges"
    // InternalGRandom.g:732:1: entryRuleEdges returns [EObject current=null] : iv_ruleEdges= ruleEdges EOF ;
    public final EObject entryRuleEdges() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEdges = null;


        try {
            // InternalGRandom.g:732:46: (iv_ruleEdges= ruleEdges EOF )
            // InternalGRandom.g:733:2: iv_ruleEdges= ruleEdges EOF
            {
             newCompositeNode(grammarAccess.getEdgesRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEdges=ruleEdges();

            state._fsp--;

             current =iv_ruleEdges; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEdges"


    // $ANTLR start "ruleEdges"
    // InternalGRandom.g:739:1: ruleEdges returns [EObject current=null] : ( (otherlv_0= 'edges' ( ( (lv_density_1_0= 'density' ) ) | ( (lv_total_2_0= 'total' ) ) | ( (lv_relative_3_0= 'relative' ) ) | ( (lv_outbound_4_0= 'outgoing' ) ) ) otherlv_5= '=' ( (lv_nEdges_6_0= ruleDoubleQuantity ) ) ) (otherlv_7= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_labels_9_0= 'labels' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_selfLoops_10_0= 'self loops' ) ) ) ) ) )* ) ) ) otherlv_11= '}' )? ) ;
    public final EObject ruleEdges() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_density_1_0=null;
        Token lv_total_2_0=null;
        Token lv_relative_3_0=null;
        Token lv_outbound_4_0=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token lv_labels_9_0=null;
        Token lv_selfLoops_10_0=null;
        Token otherlv_11=null;
        EObject lv_nEdges_6_0 = null;



        	enterRule();

        try {
            // InternalGRandom.g:745:2: ( ( (otherlv_0= 'edges' ( ( (lv_density_1_0= 'density' ) ) | ( (lv_total_2_0= 'total' ) ) | ( (lv_relative_3_0= 'relative' ) ) | ( (lv_outbound_4_0= 'outgoing' ) ) ) otherlv_5= '=' ( (lv_nEdges_6_0= ruleDoubleQuantity ) ) ) (otherlv_7= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_labels_9_0= 'labels' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_selfLoops_10_0= 'self loops' ) ) ) ) ) )* ) ) ) otherlv_11= '}' )? ) )
            // InternalGRandom.g:746:2: ( (otherlv_0= 'edges' ( ( (lv_density_1_0= 'density' ) ) | ( (lv_total_2_0= 'total' ) ) | ( (lv_relative_3_0= 'relative' ) ) | ( (lv_outbound_4_0= 'outgoing' ) ) ) otherlv_5= '=' ( (lv_nEdges_6_0= ruleDoubleQuantity ) ) ) (otherlv_7= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_labels_9_0= 'labels' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_selfLoops_10_0= 'self loops' ) ) ) ) ) )* ) ) ) otherlv_11= '}' )? )
            {
            // InternalGRandom.g:746:2: ( (otherlv_0= 'edges' ( ( (lv_density_1_0= 'density' ) ) | ( (lv_total_2_0= 'total' ) ) | ( (lv_relative_3_0= 'relative' ) ) | ( (lv_outbound_4_0= 'outgoing' ) ) ) otherlv_5= '=' ( (lv_nEdges_6_0= ruleDoubleQuantity ) ) ) (otherlv_7= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_labels_9_0= 'labels' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_selfLoops_10_0= 'self loops' ) ) ) ) ) )* ) ) ) otherlv_11= '}' )? )
            // InternalGRandom.g:747:3: (otherlv_0= 'edges' ( ( (lv_density_1_0= 'density' ) ) | ( (lv_total_2_0= 'total' ) ) | ( (lv_relative_3_0= 'relative' ) ) | ( (lv_outbound_4_0= 'outgoing' ) ) ) otherlv_5= '=' ( (lv_nEdges_6_0= ruleDoubleQuantity ) ) ) (otherlv_7= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_labels_9_0= 'labels' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_selfLoops_10_0= 'self loops' ) ) ) ) ) )* ) ) ) otherlv_11= '}' )?
            {
            // InternalGRandom.g:747:3: (otherlv_0= 'edges' ( ( (lv_density_1_0= 'density' ) ) | ( (lv_total_2_0= 'total' ) ) | ( (lv_relative_3_0= 'relative' ) ) | ( (lv_outbound_4_0= 'outgoing' ) ) ) otherlv_5= '=' ( (lv_nEdges_6_0= ruleDoubleQuantity ) ) )
            // InternalGRandom.g:748:4: otherlv_0= 'edges' ( ( (lv_density_1_0= 'density' ) ) | ( (lv_total_2_0= 'total' ) ) | ( (lv_relative_3_0= 'relative' ) ) | ( (lv_outbound_4_0= 'outgoing' ) ) ) otherlv_5= '=' ( (lv_nEdges_6_0= ruleDoubleQuantity ) )
            {
            otherlv_0=(Token)match(input,26,FOLLOW_12); 

            				newLeafNode(otherlv_0, grammarAccess.getEdgesAccess().getEdgesKeyword_0_0());
            			
            // InternalGRandom.g:752:4: ( ( (lv_density_1_0= 'density' ) ) | ( (lv_total_2_0= 'total' ) ) | ( (lv_relative_3_0= 'relative' ) ) | ( (lv_outbound_4_0= 'outgoing' ) ) )
            int alt6=4;
            switch ( input.LA(1) ) {
            case 27:
                {
                alt6=1;
                }
                break;
            case 28:
                {
                alt6=2;
                }
                break;
            case 29:
                {
                alt6=3;
                }
                break;
            case 30:
                {
                alt6=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // InternalGRandom.g:753:5: ( (lv_density_1_0= 'density' ) )
                    {
                    // InternalGRandom.g:753:5: ( (lv_density_1_0= 'density' ) )
                    // InternalGRandom.g:754:6: (lv_density_1_0= 'density' )
                    {
                    // InternalGRandom.g:754:6: (lv_density_1_0= 'density' )
                    // InternalGRandom.g:755:7: lv_density_1_0= 'density'
                    {
                    lv_density_1_0=(Token)match(input,27,FOLLOW_8); 

                    							newLeafNode(lv_density_1_0, grammarAccess.getEdgesAccess().getDensityDensityKeyword_0_1_0_0());
                    						

                    							if (current==null) {
                    								current = createModelElement(grammarAccess.getEdgesRule());
                    							}
                    							setWithLastConsumed(current, "density", true, "density");
                    						

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalGRandom.g:768:5: ( (lv_total_2_0= 'total' ) )
                    {
                    // InternalGRandom.g:768:5: ( (lv_total_2_0= 'total' ) )
                    // InternalGRandom.g:769:6: (lv_total_2_0= 'total' )
                    {
                    // InternalGRandom.g:769:6: (lv_total_2_0= 'total' )
                    // InternalGRandom.g:770:7: lv_total_2_0= 'total'
                    {
                    lv_total_2_0=(Token)match(input,28,FOLLOW_8); 

                    							newLeafNode(lv_total_2_0, grammarAccess.getEdgesAccess().getTotalTotalKeyword_0_1_1_0());
                    						

                    							if (current==null) {
                    								current = createModelElement(grammarAccess.getEdgesRule());
                    							}
                    							setWithLastConsumed(current, "total", true, "total");
                    						

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalGRandom.g:783:5: ( (lv_relative_3_0= 'relative' ) )
                    {
                    // InternalGRandom.g:783:5: ( (lv_relative_3_0= 'relative' ) )
                    // InternalGRandom.g:784:6: (lv_relative_3_0= 'relative' )
                    {
                    // InternalGRandom.g:784:6: (lv_relative_3_0= 'relative' )
                    // InternalGRandom.g:785:7: lv_relative_3_0= 'relative'
                    {
                    lv_relative_3_0=(Token)match(input,29,FOLLOW_8); 

                    							newLeafNode(lv_relative_3_0, grammarAccess.getEdgesAccess().getRelativeRelativeKeyword_0_1_2_0());
                    						

                    							if (current==null) {
                    								current = createModelElement(grammarAccess.getEdgesRule());
                    							}
                    							setWithLastConsumed(current, "relative", true, "relative");
                    						

                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalGRandom.g:798:5: ( (lv_outbound_4_0= 'outgoing' ) )
                    {
                    // InternalGRandom.g:798:5: ( (lv_outbound_4_0= 'outgoing' ) )
                    // InternalGRandom.g:799:6: (lv_outbound_4_0= 'outgoing' )
                    {
                    // InternalGRandom.g:799:6: (lv_outbound_4_0= 'outgoing' )
                    // InternalGRandom.g:800:7: lv_outbound_4_0= 'outgoing'
                    {
                    lv_outbound_4_0=(Token)match(input,30,FOLLOW_8); 

                    							newLeafNode(lv_outbound_4_0, grammarAccess.getEdgesAccess().getOutboundOutgoingKeyword_0_1_3_0());
                    						

                    							if (current==null) {
                    								current = createModelElement(grammarAccess.getEdgesRule());
                    							}
                    							setWithLastConsumed(current, "outbound", true, "outgoing");
                    						

                    }


                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,14,FOLLOW_4); 

            				newLeafNode(otherlv_5, grammarAccess.getEdgesAccess().getEqualsSignKeyword_0_2());
            			
            // InternalGRandom.g:817:4: ( (lv_nEdges_6_0= ruleDoubleQuantity ) )
            // InternalGRandom.g:818:5: (lv_nEdges_6_0= ruleDoubleQuantity )
            {
            // InternalGRandom.g:818:5: (lv_nEdges_6_0= ruleDoubleQuantity )
            // InternalGRandom.g:819:6: lv_nEdges_6_0= ruleDoubleQuantity
            {

            						newCompositeNode(grammarAccess.getEdgesAccess().getNEdgesDoubleQuantityParserRuleCall_0_3_0());
            					
            pushFollow(FOLLOW_6);
            lv_nEdges_6_0=ruleDoubleQuantity();

            state._fsp--;


            						if (current==null) {
            							current = createModelElementForParent(grammarAccess.getEdgesRule());
            						}
            						set(
            							current,
            							"nEdges",
            							lv_nEdges_6_0,
            							"de.cau.cs.kieler.kgraph.text.GRandom.DoubleQuantity");
            						afterParserOrEnumRuleCall();
            					

            }


            }


            }

            // InternalGRandom.g:837:3: (otherlv_7= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_labels_9_0= 'labels' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_selfLoops_10_0= 'self loops' ) ) ) ) ) )* ) ) ) otherlv_11= '}' )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==12) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalGRandom.g:838:4: otherlv_7= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_labels_9_0= 'labels' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_selfLoops_10_0= 'self loops' ) ) ) ) ) )* ) ) ) otherlv_11= '}'
                    {
                    otherlv_7=(Token)match(input,12,FOLLOW_13); 

                    				newLeafNode(otherlv_7, grammarAccess.getEdgesAccess().getLeftCurlyBracketKeyword_1_0());
                    			
                    // InternalGRandom.g:842:4: ( ( ( ( ({...}? => ( ({...}? => ( (lv_labels_9_0= 'labels' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_selfLoops_10_0= 'self loops' ) ) ) ) ) )* ) ) )
                    // InternalGRandom.g:843:5: ( ( ( ({...}? => ( ({...}? => ( (lv_labels_9_0= 'labels' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_selfLoops_10_0= 'self loops' ) ) ) ) ) )* ) )
                    {
                    // InternalGRandom.g:843:5: ( ( ( ({...}? => ( ({...}? => ( (lv_labels_9_0= 'labels' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_selfLoops_10_0= 'self loops' ) ) ) ) ) )* ) )
                    // InternalGRandom.g:844:6: ( ( ({...}? => ( ({...}? => ( (lv_labels_9_0= 'labels' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_selfLoops_10_0= 'self loops' ) ) ) ) ) )* )
                    {
                     
                    					  getUnorderedGroupHelper().enter(grammarAccess.getEdgesAccess().getUnorderedGroup_1_1());
                    					
                    // InternalGRandom.g:847:6: ( ( ({...}? => ( ({...}? => ( (lv_labels_9_0= 'labels' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_selfLoops_10_0= 'self loops' ) ) ) ) ) )* )
                    // InternalGRandom.g:848:7: ( ({...}? => ( ({...}? => ( (lv_labels_9_0= 'labels' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_selfLoops_10_0= 'self loops' ) ) ) ) ) )*
                    {
                    // InternalGRandom.g:848:7: ( ({...}? => ( ({...}? => ( (lv_labels_9_0= 'labels' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_selfLoops_10_0= 'self loops' ) ) ) ) ) )*
                    loop7:
                    do {
                        int alt7=3;
                        int LA7_0 = input.LA(1);

                        if ( LA7_0 == 31 && getUnorderedGroupHelper().canSelect(grammarAccess.getEdgesAccess().getUnorderedGroup_1_1(), 0) ) {
                            alt7=1;
                        }
                        else if ( LA7_0 == 32 && getUnorderedGroupHelper().canSelect(grammarAccess.getEdgesAccess().getUnorderedGroup_1_1(), 1) ) {
                            alt7=2;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // InternalGRandom.g:849:5: ({...}? => ( ({...}? => ( (lv_labels_9_0= 'labels' ) ) ) ) )
                    	    {
                    	    // InternalGRandom.g:849:5: ({...}? => ( ({...}? => ( (lv_labels_9_0= 'labels' ) ) ) ) )
                    	    // InternalGRandom.g:850:6: {...}? => ( ({...}? => ( (lv_labels_9_0= 'labels' ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getEdgesAccess().getUnorderedGroup_1_1(), 0) ) {
                    	        throw new FailedPredicateException(input, "ruleEdges", "getUnorderedGroupHelper().canSelect(grammarAccess.getEdgesAccess().getUnorderedGroup_1_1(), 0)");
                    	    }
                    	    // InternalGRandom.g:850:105: ( ({...}? => ( (lv_labels_9_0= 'labels' ) ) ) )
                    	    // InternalGRandom.g:851:7: ({...}? => ( (lv_labels_9_0= 'labels' ) ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getEdgesAccess().getUnorderedGroup_1_1(), 0);
                    	    						
                    	    // InternalGRandom.g:854:10: ({...}? => ( (lv_labels_9_0= 'labels' ) ) )
                    	    // InternalGRandom.g:854:11: {...}? => ( (lv_labels_9_0= 'labels' ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleEdges", "true");
                    	    }
                    	    // InternalGRandom.g:854:20: ( (lv_labels_9_0= 'labels' ) )
                    	    // InternalGRandom.g:854:21: (lv_labels_9_0= 'labels' )
                    	    {
                    	    // InternalGRandom.g:854:21: (lv_labels_9_0= 'labels' )
                    	    // InternalGRandom.g:855:11: lv_labels_9_0= 'labels'
                    	    {
                    	    lv_labels_9_0=(Token)match(input,31,FOLLOW_13); 

                    	    											newLeafNode(lv_labels_9_0, grammarAccess.getEdgesAccess().getLabelsLabelsKeyword_1_1_0_0());
                    	    										

                    	    											if (current==null) {
                    	    												current = createModelElement(grammarAccess.getEdgesRule());
                    	    											}
                    	    											setWithLastConsumed(current, "labels", true, "labels");
                    	    										

                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getEdgesAccess().getUnorderedGroup_1_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalGRandom.g:872:5: ({...}? => ( ({...}? => ( (lv_selfLoops_10_0= 'self loops' ) ) ) ) )
                    	    {
                    	    // InternalGRandom.g:872:5: ({...}? => ( ({...}? => ( (lv_selfLoops_10_0= 'self loops' ) ) ) ) )
                    	    // InternalGRandom.g:873:6: {...}? => ( ({...}? => ( (lv_selfLoops_10_0= 'self loops' ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getEdgesAccess().getUnorderedGroup_1_1(), 1) ) {
                    	        throw new FailedPredicateException(input, "ruleEdges", "getUnorderedGroupHelper().canSelect(grammarAccess.getEdgesAccess().getUnorderedGroup_1_1(), 1)");
                    	    }
                    	    // InternalGRandom.g:873:105: ( ({...}? => ( (lv_selfLoops_10_0= 'self loops' ) ) ) )
                    	    // InternalGRandom.g:874:7: ({...}? => ( (lv_selfLoops_10_0= 'self loops' ) ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getEdgesAccess().getUnorderedGroup_1_1(), 1);
                    	    						
                    	    // InternalGRandom.g:877:10: ({...}? => ( (lv_selfLoops_10_0= 'self loops' ) ) )
                    	    // InternalGRandom.g:877:11: {...}? => ( (lv_selfLoops_10_0= 'self loops' ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleEdges", "true");
                    	    }
                    	    // InternalGRandom.g:877:20: ( (lv_selfLoops_10_0= 'self loops' ) )
                    	    // InternalGRandom.g:877:21: (lv_selfLoops_10_0= 'self loops' )
                    	    {
                    	    // InternalGRandom.g:877:21: (lv_selfLoops_10_0= 'self loops' )
                    	    // InternalGRandom.g:878:11: lv_selfLoops_10_0= 'self loops'
                    	    {
                    	    lv_selfLoops_10_0=(Token)match(input,32,FOLLOW_13); 

                    	    											newLeafNode(lv_selfLoops_10_0, grammarAccess.getEdgesAccess().getSelfLoopsSelfLoopsKeyword_1_1_1_0());
                    	    										

                    	    											if (current==null) {
                    	    												current = createModelElement(grammarAccess.getEdgesRule());
                    	    											}
                    	    											setWithLastConsumed(current, "selfLoops", true, "self loops");
                    	    										

                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getEdgesAccess().getUnorderedGroup_1_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);


                    }


                    }

                     
                    					  getUnorderedGroupHelper().leave(grammarAccess.getEdgesAccess().getUnorderedGroup_1_1());
                    					

                    }

                    otherlv_11=(Token)match(input,20,FOLLOW_2); 

                    				newLeafNode(otherlv_11, grammarAccess.getEdgesAccess().getRightCurlyBracketKeyword_1_2());
                    			

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
    // $ANTLR end "ruleEdges"


    // $ANTLR start "entryRuleNodes"
    // InternalGRandom.g:911:1: entryRuleNodes returns [EObject current=null] : iv_ruleNodes= ruleNodes EOF ;
    public final EObject entryRuleNodes() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNodes = null;


        try {
            // InternalGRandom.g:911:46: (iv_ruleNodes= ruleNodes EOF )
            // InternalGRandom.g:912:2: iv_ruleNodes= ruleNodes EOF
            {
             newCompositeNode(grammarAccess.getNodesRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNodes=ruleNodes();

            state._fsp--;

             current =iv_ruleNodes; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNodes"


    // $ANTLR start "ruleNodes"
    // InternalGRandom.g:918:1: ruleNodes returns [EObject current=null] : ( () otherlv_1= 'nodes' otherlv_2= '=' ( (lv_nNodes_3_0= ruleDoubleQuantity ) ) (otherlv_4= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_ports_6_0= rulePorts ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_labels_7_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) )* ) ) ) otherlv_9= '}' )? ) ;
    public final EObject ruleNodes() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_9=null;
        EObject lv_nNodes_3_0 = null;

        EObject lv_ports_6_0 = null;

        AntlrDatatypeRuleToken lv_labels_7_0 = null;

        EObject lv_size_8_0 = null;



        	enterRule();

        try {
            // InternalGRandom.g:924:2: ( ( () otherlv_1= 'nodes' otherlv_2= '=' ( (lv_nNodes_3_0= ruleDoubleQuantity ) ) (otherlv_4= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_ports_6_0= rulePorts ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_labels_7_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) )* ) ) ) otherlv_9= '}' )? ) )
            // InternalGRandom.g:925:2: ( () otherlv_1= 'nodes' otherlv_2= '=' ( (lv_nNodes_3_0= ruleDoubleQuantity ) ) (otherlv_4= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_ports_6_0= rulePorts ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_labels_7_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) )* ) ) ) otherlv_9= '}' )? )
            {
            // InternalGRandom.g:925:2: ( () otherlv_1= 'nodes' otherlv_2= '=' ( (lv_nNodes_3_0= ruleDoubleQuantity ) ) (otherlv_4= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_ports_6_0= rulePorts ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_labels_7_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) )* ) ) ) otherlv_9= '}' )? )
            // InternalGRandom.g:926:3: () otherlv_1= 'nodes' otherlv_2= '=' ( (lv_nNodes_3_0= ruleDoubleQuantity ) ) (otherlv_4= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_ports_6_0= rulePorts ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_labels_7_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) )* ) ) ) otherlv_9= '}' )?
            {
            // InternalGRandom.g:926:3: ()
            // InternalGRandom.g:927:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getNodesAccess().getNodesAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,33,FOLLOW_8); 

            			newLeafNode(otherlv_1, grammarAccess.getNodesAccess().getNodesKeyword_1());
            		
            otherlv_2=(Token)match(input,14,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getNodesAccess().getEqualsSignKeyword_2());
            		
            // InternalGRandom.g:941:3: ( (lv_nNodes_3_0= ruleDoubleQuantity ) )
            // InternalGRandom.g:942:4: (lv_nNodes_3_0= ruleDoubleQuantity )
            {
            // InternalGRandom.g:942:4: (lv_nNodes_3_0= ruleDoubleQuantity )
            // InternalGRandom.g:943:5: lv_nNodes_3_0= ruleDoubleQuantity
            {

            					newCompositeNode(grammarAccess.getNodesAccess().getNNodesDoubleQuantityParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_6);
            lv_nNodes_3_0=ruleDoubleQuantity();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getNodesRule());
            					}
            					set(
            						current,
            						"nNodes",
            						lv_nNodes_3_0,
            						"de.cau.cs.kieler.kgraph.text.GRandom.DoubleQuantity");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalGRandom.g:960:3: (otherlv_4= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_ports_6_0= rulePorts ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_labels_7_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) )* ) ) ) otherlv_9= '}' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==12) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalGRandom.g:961:4: otherlv_4= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_ports_6_0= rulePorts ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_labels_7_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) )* ) ) ) otherlv_9= '}'
                    {
                    otherlv_4=(Token)match(input,12,FOLLOW_14); 

                    				newLeafNode(otherlv_4, grammarAccess.getNodesAccess().getLeftCurlyBracketKeyword_4_0());
                    			
                    // InternalGRandom.g:965:4: ( ( ( ( ({...}? => ( ({...}? => ( (lv_ports_6_0= rulePorts ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_labels_7_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) )* ) ) )
                    // InternalGRandom.g:966:5: ( ( ( ({...}? => ( ({...}? => ( (lv_ports_6_0= rulePorts ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_labels_7_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) )* ) )
                    {
                    // InternalGRandom.g:966:5: ( ( ( ({...}? => ( ({...}? => ( (lv_ports_6_0= rulePorts ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_labels_7_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) )* ) )
                    // InternalGRandom.g:967:6: ( ( ({...}? => ( ({...}? => ( (lv_ports_6_0= rulePorts ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_labels_7_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) )* )
                    {
                     
                    					  getUnorderedGroupHelper().enter(grammarAccess.getNodesAccess().getUnorderedGroup_4_1());
                    					
                    // InternalGRandom.g:970:6: ( ( ({...}? => ( ({...}? => ( (lv_ports_6_0= rulePorts ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_labels_7_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) )* )
                    // InternalGRandom.g:971:7: ( ({...}? => ( ({...}? => ( (lv_ports_6_0= rulePorts ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_labels_7_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) )*
                    {
                    // InternalGRandom.g:971:7: ( ({...}? => ( ({...}? => ( (lv_ports_6_0= rulePorts ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_labels_7_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) )*
                    loop9:
                    do {
                        int alt9=4;
                        int LA9_0 = input.LA(1);

                        if ( LA9_0 == 37 && getUnorderedGroupHelper().canSelect(grammarAccess.getNodesAccess().getUnorderedGroup_4_1(), 0) ) {
                            alt9=1;
                        }
                        else if ( LA9_0 == 31 && getUnorderedGroupHelper().canSelect(grammarAccess.getNodesAccess().getUnorderedGroup_4_1(), 1) ) {
                            alt9=2;
                        }
                        else if ( LA9_0 == 34 && getUnorderedGroupHelper().canSelect(grammarAccess.getNodesAccess().getUnorderedGroup_4_1(), 2) ) {
                            alt9=3;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // InternalGRandom.g:972:5: ({...}? => ( ({...}? => ( (lv_ports_6_0= rulePorts ) ) ) ) )
                    	    {
                    	    // InternalGRandom.g:972:5: ({...}? => ( ({...}? => ( (lv_ports_6_0= rulePorts ) ) ) ) )
                    	    // InternalGRandom.g:973:6: {...}? => ( ({...}? => ( (lv_ports_6_0= rulePorts ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getNodesAccess().getUnorderedGroup_4_1(), 0) ) {
                    	        throw new FailedPredicateException(input, "ruleNodes", "getUnorderedGroupHelper().canSelect(grammarAccess.getNodesAccess().getUnorderedGroup_4_1(), 0)");
                    	    }
                    	    // InternalGRandom.g:973:105: ( ({...}? => ( (lv_ports_6_0= rulePorts ) ) ) )
                    	    // InternalGRandom.g:974:7: ({...}? => ( (lv_ports_6_0= rulePorts ) ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getNodesAccess().getUnorderedGroup_4_1(), 0);
                    	    						
                    	    // InternalGRandom.g:977:10: ({...}? => ( (lv_ports_6_0= rulePorts ) ) )
                    	    // InternalGRandom.g:977:11: {...}? => ( (lv_ports_6_0= rulePorts ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleNodes", "true");
                    	    }
                    	    // InternalGRandom.g:977:20: ( (lv_ports_6_0= rulePorts ) )
                    	    // InternalGRandom.g:977:21: (lv_ports_6_0= rulePorts )
                    	    {
                    	    // InternalGRandom.g:977:21: (lv_ports_6_0= rulePorts )
                    	    // InternalGRandom.g:978:11: lv_ports_6_0= rulePorts
                    	    {

                    	    											newCompositeNode(grammarAccess.getNodesAccess().getPortsPortsParserRuleCall_4_1_0_0());
                    	    										
                    	    pushFollow(FOLLOW_14);
                    	    lv_ports_6_0=rulePorts();

                    	    state._fsp--;


                    	    											if (current==null) {
                    	    												current = createModelElementForParent(grammarAccess.getNodesRule());
                    	    											}
                    	    											set(
                    	    												current,
                    	    												"ports",
                    	    												lv_ports_6_0,
                    	    												"de.cau.cs.kieler.kgraph.text.GRandom.Ports");
                    	    											afterParserOrEnumRuleCall();
                    	    										

                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getNodesAccess().getUnorderedGroup_4_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalGRandom.g:1000:5: ({...}? => ( ({...}? => ( (lv_labels_7_0= ruleLabels ) ) ) ) )
                    	    {
                    	    // InternalGRandom.g:1000:5: ({...}? => ( ({...}? => ( (lv_labels_7_0= ruleLabels ) ) ) ) )
                    	    // InternalGRandom.g:1001:6: {...}? => ( ({...}? => ( (lv_labels_7_0= ruleLabels ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getNodesAccess().getUnorderedGroup_4_1(), 1) ) {
                    	        throw new FailedPredicateException(input, "ruleNodes", "getUnorderedGroupHelper().canSelect(grammarAccess.getNodesAccess().getUnorderedGroup_4_1(), 1)");
                    	    }
                    	    // InternalGRandom.g:1001:105: ( ({...}? => ( (lv_labels_7_0= ruleLabels ) ) ) )
                    	    // InternalGRandom.g:1002:7: ({...}? => ( (lv_labels_7_0= ruleLabels ) ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getNodesAccess().getUnorderedGroup_4_1(), 1);
                    	    						
                    	    // InternalGRandom.g:1005:10: ({...}? => ( (lv_labels_7_0= ruleLabels ) ) )
                    	    // InternalGRandom.g:1005:11: {...}? => ( (lv_labels_7_0= ruleLabels ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleNodes", "true");
                    	    }
                    	    // InternalGRandom.g:1005:20: ( (lv_labels_7_0= ruleLabels ) )
                    	    // InternalGRandom.g:1005:21: (lv_labels_7_0= ruleLabels )
                    	    {
                    	    // InternalGRandom.g:1005:21: (lv_labels_7_0= ruleLabels )
                    	    // InternalGRandom.g:1006:11: lv_labels_7_0= ruleLabels
                    	    {

                    	    											newCompositeNode(grammarAccess.getNodesAccess().getLabelsLabelsParserRuleCall_4_1_1_0());
                    	    										
                    	    pushFollow(FOLLOW_14);
                    	    lv_labels_7_0=ruleLabels();

                    	    state._fsp--;


                    	    											if (current==null) {
                    	    												current = createModelElementForParent(grammarAccess.getNodesRule());
                    	    											}
                    	    											set(
                    	    												current,
                    	    												"labels",
                    	    												true,
                    	    												"de.cau.cs.kieler.kgraph.text.GRandom.Labels");
                    	    											afterParserOrEnumRuleCall();
                    	    										

                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getNodesAccess().getUnorderedGroup_4_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 3 :
                    	    // InternalGRandom.g:1028:5: ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) )
                    	    {
                    	    // InternalGRandom.g:1028:5: ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) )
                    	    // InternalGRandom.g:1029:6: {...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getNodesAccess().getUnorderedGroup_4_1(), 2) ) {
                    	        throw new FailedPredicateException(input, "ruleNodes", "getUnorderedGroupHelper().canSelect(grammarAccess.getNodesAccess().getUnorderedGroup_4_1(), 2)");
                    	    }
                    	    // InternalGRandom.g:1029:105: ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) )
                    	    // InternalGRandom.g:1030:7: ({...}? => ( (lv_size_8_0= ruleSize ) ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getNodesAccess().getUnorderedGroup_4_1(), 2);
                    	    						
                    	    // InternalGRandom.g:1033:10: ({...}? => ( (lv_size_8_0= ruleSize ) ) )
                    	    // InternalGRandom.g:1033:11: {...}? => ( (lv_size_8_0= ruleSize ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleNodes", "true");
                    	    }
                    	    // InternalGRandom.g:1033:20: ( (lv_size_8_0= ruleSize ) )
                    	    // InternalGRandom.g:1033:21: (lv_size_8_0= ruleSize )
                    	    {
                    	    // InternalGRandom.g:1033:21: (lv_size_8_0= ruleSize )
                    	    // InternalGRandom.g:1034:11: lv_size_8_0= ruleSize
                    	    {

                    	    											newCompositeNode(grammarAccess.getNodesAccess().getSizeSizeParserRuleCall_4_1_2_0());
                    	    										
                    	    pushFollow(FOLLOW_14);
                    	    lv_size_8_0=ruleSize();

                    	    state._fsp--;


                    	    											if (current==null) {
                    	    												current = createModelElementForParent(grammarAccess.getNodesRule());
                    	    											}
                    	    											set(
                    	    												current,
                    	    												"size",
                    	    												lv_size_8_0,
                    	    												"de.cau.cs.kieler.kgraph.text.GRandom.Size");
                    	    											afterParserOrEnumRuleCall();
                    	    										

                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getNodesAccess().getUnorderedGroup_4_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);


                    }


                    }

                     
                    					  getUnorderedGroupHelper().leave(grammarAccess.getNodesAccess().getUnorderedGroup_4_1());
                    					

                    }

                    otherlv_9=(Token)match(input,20,FOLLOW_2); 

                    				newLeafNode(otherlv_9, grammarAccess.getNodesAccess().getRightCurlyBracketKeyword_4_2());
                    			

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
    // $ANTLR end "ruleNodes"


    // $ANTLR start "entryRuleSize"
    // InternalGRandom.g:1072:1: entryRuleSize returns [EObject current=null] : iv_ruleSize= ruleSize EOF ;
    public final EObject entryRuleSize() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSize = null;


        try {
            // InternalGRandom.g:1072:45: (iv_ruleSize= ruleSize EOF )
            // InternalGRandom.g:1073:2: iv_ruleSize= ruleSize EOF
            {
             newCompositeNode(grammarAccess.getSizeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSize=ruleSize();

            state._fsp--;

             current =iv_ruleSize; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSize"


    // $ANTLR start "ruleSize"
    // InternalGRandom.g:1079:1: ruleSize returns [EObject current=null] : ( () (otherlv_1= 'size' (otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'height' otherlv_5= '=' ( (lv_height_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'width' otherlv_8= '=' ( (lv_width_9_0= ruleDoubleQuantity ) ) ) ) ) ) )* ) ) ) otherlv_10= '}' )? ) ) ;
    public final EObject ruleSize() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        EObject lv_height_6_0 = null;

        EObject lv_width_9_0 = null;



        	enterRule();

        try {
            // InternalGRandom.g:1085:2: ( ( () (otherlv_1= 'size' (otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'height' otherlv_5= '=' ( (lv_height_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'width' otherlv_8= '=' ( (lv_width_9_0= ruleDoubleQuantity ) ) ) ) ) ) )* ) ) ) otherlv_10= '}' )? ) ) )
            // InternalGRandom.g:1086:2: ( () (otherlv_1= 'size' (otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'height' otherlv_5= '=' ( (lv_height_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'width' otherlv_8= '=' ( (lv_width_9_0= ruleDoubleQuantity ) ) ) ) ) ) )* ) ) ) otherlv_10= '}' )? ) )
            {
            // InternalGRandom.g:1086:2: ( () (otherlv_1= 'size' (otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'height' otherlv_5= '=' ( (lv_height_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'width' otherlv_8= '=' ( (lv_width_9_0= ruleDoubleQuantity ) ) ) ) ) ) )* ) ) ) otherlv_10= '}' )? ) )
            // InternalGRandom.g:1087:3: () (otherlv_1= 'size' (otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'height' otherlv_5= '=' ( (lv_height_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'width' otherlv_8= '=' ( (lv_width_9_0= ruleDoubleQuantity ) ) ) ) ) ) )* ) ) ) otherlv_10= '}' )? )
            {
            // InternalGRandom.g:1087:3: ()
            // InternalGRandom.g:1088:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getSizeAccess().getSizeAction_0(),
            					current);
            			

            }

            // InternalGRandom.g:1094:3: (otherlv_1= 'size' (otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'height' otherlv_5= '=' ( (lv_height_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'width' otherlv_8= '=' ( (lv_width_9_0= ruleDoubleQuantity ) ) ) ) ) ) )* ) ) ) otherlv_10= '}' )? )
            // InternalGRandom.g:1095:4: otherlv_1= 'size' (otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'height' otherlv_5= '=' ( (lv_height_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'width' otherlv_8= '=' ( (lv_width_9_0= ruleDoubleQuantity ) ) ) ) ) ) )* ) ) ) otherlv_10= '}' )?
            {
            otherlv_1=(Token)match(input,34,FOLLOW_6); 

            				newLeafNode(otherlv_1, grammarAccess.getSizeAccess().getSizeKeyword_1_0());
            			
            // InternalGRandom.g:1099:4: (otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'height' otherlv_5= '=' ( (lv_height_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'width' otherlv_8= '=' ( (lv_width_9_0= ruleDoubleQuantity ) ) ) ) ) ) )* ) ) ) otherlv_10= '}' )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==12) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalGRandom.g:1100:5: otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'height' otherlv_5= '=' ( (lv_height_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'width' otherlv_8= '=' ( (lv_width_9_0= ruleDoubleQuantity ) ) ) ) ) ) )* ) ) ) otherlv_10= '}'
                    {
                    otherlv_2=(Token)match(input,12,FOLLOW_15); 

                    					newLeafNode(otherlv_2, grammarAccess.getSizeAccess().getLeftCurlyBracketKeyword_1_1_0());
                    				
                    // InternalGRandom.g:1104:5: ( ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'height' otherlv_5= '=' ( (lv_height_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'width' otherlv_8= '=' ( (lv_width_9_0= ruleDoubleQuantity ) ) ) ) ) ) )* ) ) )
                    // InternalGRandom.g:1105:6: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'height' otherlv_5= '=' ( (lv_height_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'width' otherlv_8= '=' ( (lv_width_9_0= ruleDoubleQuantity ) ) ) ) ) ) )* ) )
                    {
                    // InternalGRandom.g:1105:6: ( ( ( ({...}? => ( ({...}? => (otherlv_4= 'height' otherlv_5= '=' ( (lv_height_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'width' otherlv_8= '=' ( (lv_width_9_0= ruleDoubleQuantity ) ) ) ) ) ) )* ) )
                    // InternalGRandom.g:1106:7: ( ( ({...}? => ( ({...}? => (otherlv_4= 'height' otherlv_5= '=' ( (lv_height_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'width' otherlv_8= '=' ( (lv_width_9_0= ruleDoubleQuantity ) ) ) ) ) ) )* )
                    {
                     
                    						  getUnorderedGroupHelper().enter(grammarAccess.getSizeAccess().getUnorderedGroup_1_1_1());
                    						
                    // InternalGRandom.g:1109:7: ( ( ({...}? => ( ({...}? => (otherlv_4= 'height' otherlv_5= '=' ( (lv_height_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'width' otherlv_8= '=' ( (lv_width_9_0= ruleDoubleQuantity ) ) ) ) ) ) )* )
                    // InternalGRandom.g:1110:8: ( ({...}? => ( ({...}? => (otherlv_4= 'height' otherlv_5= '=' ( (lv_height_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'width' otherlv_8= '=' ( (lv_width_9_0= ruleDoubleQuantity ) ) ) ) ) ) )*
                    {
                    // InternalGRandom.g:1110:8: ( ({...}? => ( ({...}? => (otherlv_4= 'height' otherlv_5= '=' ( (lv_height_6_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_7= 'width' otherlv_8= '=' ( (lv_width_9_0= ruleDoubleQuantity ) ) ) ) ) ) )*
                    loop11:
                    do {
                        int alt11=3;
                        int LA11_0 = input.LA(1);

                        if ( LA11_0 == 35 && getUnorderedGroupHelper().canSelect(grammarAccess.getSizeAccess().getUnorderedGroup_1_1_1(), 0) ) {
                            alt11=1;
                        }
                        else if ( LA11_0 == 36 && getUnorderedGroupHelper().canSelect(grammarAccess.getSizeAccess().getUnorderedGroup_1_1_1(), 1) ) {
                            alt11=2;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // InternalGRandom.g:1111:6: ({...}? => ( ({...}? => (otherlv_4= 'height' otherlv_5= '=' ( (lv_height_6_0= ruleDoubleQuantity ) ) ) ) ) )
                    	    {
                    	    // InternalGRandom.g:1111:6: ({...}? => ( ({...}? => (otherlv_4= 'height' otherlv_5= '=' ( (lv_height_6_0= ruleDoubleQuantity ) ) ) ) ) )
                    	    // InternalGRandom.g:1112:7: {...}? => ( ({...}? => (otherlv_4= 'height' otherlv_5= '=' ( (lv_height_6_0= ruleDoubleQuantity ) ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getSizeAccess().getUnorderedGroup_1_1_1(), 0) ) {
                    	        throw new FailedPredicateException(input, "ruleSize", "getUnorderedGroupHelper().canSelect(grammarAccess.getSizeAccess().getUnorderedGroup_1_1_1(), 0)");
                    	    }
                    	    // InternalGRandom.g:1112:107: ( ({...}? => (otherlv_4= 'height' otherlv_5= '=' ( (lv_height_6_0= ruleDoubleQuantity ) ) ) ) )
                    	    // InternalGRandom.g:1113:8: ({...}? => (otherlv_4= 'height' otherlv_5= '=' ( (lv_height_6_0= ruleDoubleQuantity ) ) ) )
                    	    {

                    	    								getUnorderedGroupHelper().select(grammarAccess.getSizeAccess().getUnorderedGroup_1_1_1(), 0);
                    	    							
                    	    // InternalGRandom.g:1116:11: ({...}? => (otherlv_4= 'height' otherlv_5= '=' ( (lv_height_6_0= ruleDoubleQuantity ) ) ) )
                    	    // InternalGRandom.g:1116:12: {...}? => (otherlv_4= 'height' otherlv_5= '=' ( (lv_height_6_0= ruleDoubleQuantity ) ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleSize", "true");
                    	    }
                    	    // InternalGRandom.g:1116:21: (otherlv_4= 'height' otherlv_5= '=' ( (lv_height_6_0= ruleDoubleQuantity ) ) )
                    	    // InternalGRandom.g:1116:22: otherlv_4= 'height' otherlv_5= '=' ( (lv_height_6_0= ruleDoubleQuantity ) )
                    	    {
                    	    otherlv_4=(Token)match(input,35,FOLLOW_8); 

                    	    											newLeafNode(otherlv_4, grammarAccess.getSizeAccess().getHeightKeyword_1_1_1_0_0());
                    	    										
                    	    otherlv_5=(Token)match(input,14,FOLLOW_4); 

                    	    											newLeafNode(otherlv_5, grammarAccess.getSizeAccess().getEqualsSignKeyword_1_1_1_0_1());
                    	    										
                    	    // InternalGRandom.g:1124:11: ( (lv_height_6_0= ruleDoubleQuantity ) )
                    	    // InternalGRandom.g:1125:12: (lv_height_6_0= ruleDoubleQuantity )
                    	    {
                    	    // InternalGRandom.g:1125:12: (lv_height_6_0= ruleDoubleQuantity )
                    	    // InternalGRandom.g:1126:13: lv_height_6_0= ruleDoubleQuantity
                    	    {

                    	    													newCompositeNode(grammarAccess.getSizeAccess().getHeightDoubleQuantityParserRuleCall_1_1_1_0_2_0());
                    	    												
                    	    pushFollow(FOLLOW_15);
                    	    lv_height_6_0=ruleDoubleQuantity();

                    	    state._fsp--;


                    	    													if (current==null) {
                    	    														current = createModelElementForParent(grammarAccess.getSizeRule());
                    	    													}
                    	    													set(
                    	    														current,
                    	    														"height",
                    	    														lv_height_6_0,
                    	    														"de.cau.cs.kieler.kgraph.text.GRandom.DoubleQuantity");
                    	    													afterParserOrEnumRuleCall();
                    	    												

                    	    }


                    	    }


                    	    }


                    	    }

                    	     
                    	    								getUnorderedGroupHelper().returnFromSelection(grammarAccess.getSizeAccess().getUnorderedGroup_1_1_1());
                    	    							

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalGRandom.g:1149:6: ({...}? => ( ({...}? => (otherlv_7= 'width' otherlv_8= '=' ( (lv_width_9_0= ruleDoubleQuantity ) ) ) ) ) )
                    	    {
                    	    // InternalGRandom.g:1149:6: ({...}? => ( ({...}? => (otherlv_7= 'width' otherlv_8= '=' ( (lv_width_9_0= ruleDoubleQuantity ) ) ) ) ) )
                    	    // InternalGRandom.g:1150:7: {...}? => ( ({...}? => (otherlv_7= 'width' otherlv_8= '=' ( (lv_width_9_0= ruleDoubleQuantity ) ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getSizeAccess().getUnorderedGroup_1_1_1(), 1) ) {
                    	        throw new FailedPredicateException(input, "ruleSize", "getUnorderedGroupHelper().canSelect(grammarAccess.getSizeAccess().getUnorderedGroup_1_1_1(), 1)");
                    	    }
                    	    // InternalGRandom.g:1150:107: ( ({...}? => (otherlv_7= 'width' otherlv_8= '=' ( (lv_width_9_0= ruleDoubleQuantity ) ) ) ) )
                    	    // InternalGRandom.g:1151:8: ({...}? => (otherlv_7= 'width' otherlv_8= '=' ( (lv_width_9_0= ruleDoubleQuantity ) ) ) )
                    	    {

                    	    								getUnorderedGroupHelper().select(grammarAccess.getSizeAccess().getUnorderedGroup_1_1_1(), 1);
                    	    							
                    	    // InternalGRandom.g:1154:11: ({...}? => (otherlv_7= 'width' otherlv_8= '=' ( (lv_width_9_0= ruleDoubleQuantity ) ) ) )
                    	    // InternalGRandom.g:1154:12: {...}? => (otherlv_7= 'width' otherlv_8= '=' ( (lv_width_9_0= ruleDoubleQuantity ) ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "ruleSize", "true");
                    	    }
                    	    // InternalGRandom.g:1154:21: (otherlv_7= 'width' otherlv_8= '=' ( (lv_width_9_0= ruleDoubleQuantity ) ) )
                    	    // InternalGRandom.g:1154:22: otherlv_7= 'width' otherlv_8= '=' ( (lv_width_9_0= ruleDoubleQuantity ) )
                    	    {
                    	    otherlv_7=(Token)match(input,36,FOLLOW_8); 

                    	    											newLeafNode(otherlv_7, grammarAccess.getSizeAccess().getWidthKeyword_1_1_1_1_0());
                    	    										
                    	    otherlv_8=(Token)match(input,14,FOLLOW_4); 

                    	    											newLeafNode(otherlv_8, grammarAccess.getSizeAccess().getEqualsSignKeyword_1_1_1_1_1());
                    	    										
                    	    // InternalGRandom.g:1162:11: ( (lv_width_9_0= ruleDoubleQuantity ) )
                    	    // InternalGRandom.g:1163:12: (lv_width_9_0= ruleDoubleQuantity )
                    	    {
                    	    // InternalGRandom.g:1163:12: (lv_width_9_0= ruleDoubleQuantity )
                    	    // InternalGRandom.g:1164:13: lv_width_9_0= ruleDoubleQuantity
                    	    {

                    	    													newCompositeNode(grammarAccess.getSizeAccess().getWidthDoubleQuantityParserRuleCall_1_1_1_1_2_0());
                    	    												
                    	    pushFollow(FOLLOW_15);
                    	    lv_width_9_0=ruleDoubleQuantity();

                    	    state._fsp--;


                    	    													if (current==null) {
                    	    														current = createModelElementForParent(grammarAccess.getSizeRule());
                    	    													}
                    	    													set(
                    	    														current,
                    	    														"width",
                    	    														lv_width_9_0,
                    	    														"de.cau.cs.kieler.kgraph.text.GRandom.DoubleQuantity");
                    	    													afterParserOrEnumRuleCall();
                    	    												

                    	    }


                    	    }


                    	    }


                    	    }

                    	     
                    	    								getUnorderedGroupHelper().returnFromSelection(grammarAccess.getSizeAccess().getUnorderedGroup_1_1_1());
                    	    							

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);


                    }


                    }

                     
                    						  getUnorderedGroupHelper().leave(grammarAccess.getSizeAccess().getUnorderedGroup_1_1_1());
                    						

                    }

                    otherlv_10=(Token)match(input,20,FOLLOW_2); 

                    					newLeafNode(otherlv_10, grammarAccess.getSizeAccess().getRightCurlyBracketKeyword_1_1_2());
                    				

                    }
                    break;

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
    // $ANTLR end "ruleSize"


    // $ANTLR start "entryRulePorts"
    // InternalGRandom.g:1204:1: entryRulePorts returns [EObject current=null] : iv_rulePorts= rulePorts EOF ;
    public final EObject entryRulePorts() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePorts = null;


        try {
            // InternalGRandom.g:1204:46: (iv_rulePorts= rulePorts EOF )
            // InternalGRandom.g:1205:2: iv_rulePorts= rulePorts EOF
            {
             newCompositeNode(grammarAccess.getPortsRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePorts=rulePorts();

            state._fsp--;

             current =iv_rulePorts; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePorts"


    // $ANTLR start "rulePorts"
    // InternalGRandom.g:1211:1: rulePorts returns [EObject current=null] : ( () otherlv_1= 'ports' (otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_labels_4_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 're-use' otherlv_6= '=' ( (lv_reUse_7_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'constraint' otherlv_10= '=' ( (lv_constraint_11_0= ruleConstraintType ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_flow_12_0= ruleFlow ) ) )+ ) ) )* ) ) ) otherlv_13= '}' )? ) ;
    public final EObject rulePorts() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_13=null;
        AntlrDatatypeRuleToken lv_labels_4_0 = null;

        EObject lv_reUse_7_0 = null;

        EObject lv_size_8_0 = null;

        Enumerator lv_constraint_11_0 = null;

        EObject lv_flow_12_0 = null;



        	enterRule();

        try {
            // InternalGRandom.g:1217:2: ( ( () otherlv_1= 'ports' (otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_labels_4_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 're-use' otherlv_6= '=' ( (lv_reUse_7_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'constraint' otherlv_10= '=' ( (lv_constraint_11_0= ruleConstraintType ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_flow_12_0= ruleFlow ) ) )+ ) ) )* ) ) ) otherlv_13= '}' )? ) )
            // InternalGRandom.g:1218:2: ( () otherlv_1= 'ports' (otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_labels_4_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 're-use' otherlv_6= '=' ( (lv_reUse_7_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'constraint' otherlv_10= '=' ( (lv_constraint_11_0= ruleConstraintType ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_flow_12_0= ruleFlow ) ) )+ ) ) )* ) ) ) otherlv_13= '}' )? )
            {
            // InternalGRandom.g:1218:2: ( () otherlv_1= 'ports' (otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_labels_4_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 're-use' otherlv_6= '=' ( (lv_reUse_7_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'constraint' otherlv_10= '=' ( (lv_constraint_11_0= ruleConstraintType ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_flow_12_0= ruleFlow ) ) )+ ) ) )* ) ) ) otherlv_13= '}' )? )
            // InternalGRandom.g:1219:3: () otherlv_1= 'ports' (otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_labels_4_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 're-use' otherlv_6= '=' ( (lv_reUse_7_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'constraint' otherlv_10= '=' ( (lv_constraint_11_0= ruleConstraintType ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_flow_12_0= ruleFlow ) ) )+ ) ) )* ) ) ) otherlv_13= '}' )?
            {
            // InternalGRandom.g:1219:3: ()
            // InternalGRandom.g:1220:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getPortsAccess().getPortsAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,37,FOLLOW_6); 

            			newLeafNode(otherlv_1, grammarAccess.getPortsAccess().getPortsKeyword_1());
            		
            // InternalGRandom.g:1230:3: (otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_labels_4_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 're-use' otherlv_6= '=' ( (lv_reUse_7_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'constraint' otherlv_10= '=' ( (lv_constraint_11_0= ruleConstraintType ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_flow_12_0= ruleFlow ) ) )+ ) ) )* ) ) ) otherlv_13= '}' )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==12) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalGRandom.g:1231:4: otherlv_2= '{' ( ( ( ( ({...}? => ( ({...}? => ( (lv_labels_4_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 're-use' otherlv_6= '=' ( (lv_reUse_7_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'constraint' otherlv_10= '=' ( (lv_constraint_11_0= ruleConstraintType ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_flow_12_0= ruleFlow ) ) )+ ) ) )* ) ) ) otherlv_13= '}'
                    {
                    otherlv_2=(Token)match(input,12,FOLLOW_16); 

                    				newLeafNode(otherlv_2, grammarAccess.getPortsAccess().getLeftCurlyBracketKeyword_2_0());
                    			
                    // InternalGRandom.g:1235:4: ( ( ( ( ({...}? => ( ({...}? => ( (lv_labels_4_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 're-use' otherlv_6= '=' ( (lv_reUse_7_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'constraint' otherlv_10= '=' ( (lv_constraint_11_0= ruleConstraintType ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_flow_12_0= ruleFlow ) ) )+ ) ) )* ) ) )
                    // InternalGRandom.g:1236:5: ( ( ( ({...}? => ( ({...}? => ( (lv_labels_4_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 're-use' otherlv_6= '=' ( (lv_reUse_7_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'constraint' otherlv_10= '=' ( (lv_constraint_11_0= ruleConstraintType ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_flow_12_0= ruleFlow ) ) )+ ) ) )* ) )
                    {
                    // InternalGRandom.g:1236:5: ( ( ( ({...}? => ( ({...}? => ( (lv_labels_4_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 're-use' otherlv_6= '=' ( (lv_reUse_7_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'constraint' otherlv_10= '=' ( (lv_constraint_11_0= ruleConstraintType ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_flow_12_0= ruleFlow ) ) )+ ) ) )* ) )
                    // InternalGRandom.g:1237:6: ( ( ({...}? => ( ({...}? => ( (lv_labels_4_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 're-use' otherlv_6= '=' ( (lv_reUse_7_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'constraint' otherlv_10= '=' ( (lv_constraint_11_0= ruleConstraintType ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_flow_12_0= ruleFlow ) ) )+ ) ) )* )
                    {
                     
                    					  getUnorderedGroupHelper().enter(grammarAccess.getPortsAccess().getUnorderedGroup_2_1());
                    					
                    // InternalGRandom.g:1240:6: ( ( ({...}? => ( ({...}? => ( (lv_labels_4_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 're-use' otherlv_6= '=' ( (lv_reUse_7_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'constraint' otherlv_10= '=' ( (lv_constraint_11_0= ruleConstraintType ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_flow_12_0= ruleFlow ) ) )+ ) ) )* )
                    // InternalGRandom.g:1241:7: ( ({...}? => ( ({...}? => ( (lv_labels_4_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 're-use' otherlv_6= '=' ( (lv_reUse_7_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'constraint' otherlv_10= '=' ( (lv_constraint_11_0= ruleConstraintType ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_flow_12_0= ruleFlow ) ) )+ ) ) )*
                    {
                    // InternalGRandom.g:1241:7: ( ({...}? => ( ({...}? => ( (lv_labels_4_0= ruleLabels ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_5= 're-use' otherlv_6= '=' ( (lv_reUse_7_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_9= 'constraint' otherlv_10= '=' ( (lv_constraint_11_0= ruleConstraintType ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_flow_12_0= ruleFlow ) ) )+ ) ) )*
                    loop14:
                    do {
                        int alt14=6;
                        int LA14_0 = input.LA(1);

                        if ( LA14_0 == 31 && getUnorderedGroupHelper().canSelect(grammarAccess.getPortsAccess().getUnorderedGroup_2_1(), 0) ) {
                            alt14=1;
                        }
                        else if ( LA14_0 == 38 && getUnorderedGroupHelper().canSelect(grammarAccess.getPortsAccess().getUnorderedGroup_2_1(), 1) ) {
                            alt14=2;
                        }
                        else if ( LA14_0 == 34 && getUnorderedGroupHelper().canSelect(grammarAccess.getPortsAccess().getUnorderedGroup_2_1(), 2) ) {
                            alt14=3;
                        }
                        else if ( LA14_0 == 39 && getUnorderedGroupHelper().canSelect(grammarAccess.getPortsAccess().getUnorderedGroup_2_1(), 3) ) {
                            alt14=4;
                        }
                        else if ( ( LA14_0 == 30 || LA14_0 == 55 ) && getUnorderedGroupHelper().canSelect(grammarAccess.getPortsAccess().getUnorderedGroup_2_1(), 4) ) {
                            alt14=5;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // InternalGRandom.g:1242:5: ({...}? => ( ({...}? => ( (lv_labels_4_0= ruleLabels ) ) ) ) )
                    	    {
                    	    // InternalGRandom.g:1242:5: ({...}? => ( ({...}? => ( (lv_labels_4_0= ruleLabels ) ) ) ) )
                    	    // InternalGRandom.g:1243:6: {...}? => ( ({...}? => ( (lv_labels_4_0= ruleLabels ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getPortsAccess().getUnorderedGroup_2_1(), 0) ) {
                    	        throw new FailedPredicateException(input, "rulePorts", "getUnorderedGroupHelper().canSelect(grammarAccess.getPortsAccess().getUnorderedGroup_2_1(), 0)");
                    	    }
                    	    // InternalGRandom.g:1243:105: ( ({...}? => ( (lv_labels_4_0= ruleLabels ) ) ) )
                    	    // InternalGRandom.g:1244:7: ({...}? => ( (lv_labels_4_0= ruleLabels ) ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getPortsAccess().getUnorderedGroup_2_1(), 0);
                    	    						
                    	    // InternalGRandom.g:1247:10: ({...}? => ( (lv_labels_4_0= ruleLabels ) ) )
                    	    // InternalGRandom.g:1247:11: {...}? => ( (lv_labels_4_0= ruleLabels ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "rulePorts", "true");
                    	    }
                    	    // InternalGRandom.g:1247:20: ( (lv_labels_4_0= ruleLabels ) )
                    	    // InternalGRandom.g:1247:21: (lv_labels_4_0= ruleLabels )
                    	    {
                    	    // InternalGRandom.g:1247:21: (lv_labels_4_0= ruleLabels )
                    	    // InternalGRandom.g:1248:11: lv_labels_4_0= ruleLabels
                    	    {

                    	    											newCompositeNode(grammarAccess.getPortsAccess().getLabelsLabelsParserRuleCall_2_1_0_0());
                    	    										
                    	    pushFollow(FOLLOW_16);
                    	    lv_labels_4_0=ruleLabels();

                    	    state._fsp--;


                    	    											if (current==null) {
                    	    												current = createModelElementForParent(grammarAccess.getPortsRule());
                    	    											}
                    	    											set(
                    	    												current,
                    	    												"labels",
                    	    												true,
                    	    												"de.cau.cs.kieler.kgraph.text.GRandom.Labels");
                    	    											afterParserOrEnumRuleCall();
                    	    										

                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getPortsAccess().getUnorderedGroup_2_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalGRandom.g:1270:5: ({...}? => ( ({...}? => (otherlv_5= 're-use' otherlv_6= '=' ( (lv_reUse_7_0= ruleDoubleQuantity ) ) ) ) ) )
                    	    {
                    	    // InternalGRandom.g:1270:5: ({...}? => ( ({...}? => (otherlv_5= 're-use' otherlv_6= '=' ( (lv_reUse_7_0= ruleDoubleQuantity ) ) ) ) ) )
                    	    // InternalGRandom.g:1271:6: {...}? => ( ({...}? => (otherlv_5= 're-use' otherlv_6= '=' ( (lv_reUse_7_0= ruleDoubleQuantity ) ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getPortsAccess().getUnorderedGroup_2_1(), 1) ) {
                    	        throw new FailedPredicateException(input, "rulePorts", "getUnorderedGroupHelper().canSelect(grammarAccess.getPortsAccess().getUnorderedGroup_2_1(), 1)");
                    	    }
                    	    // InternalGRandom.g:1271:105: ( ({...}? => (otherlv_5= 're-use' otherlv_6= '=' ( (lv_reUse_7_0= ruleDoubleQuantity ) ) ) ) )
                    	    // InternalGRandom.g:1272:7: ({...}? => (otherlv_5= 're-use' otherlv_6= '=' ( (lv_reUse_7_0= ruleDoubleQuantity ) ) ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getPortsAccess().getUnorderedGroup_2_1(), 1);
                    	    						
                    	    // InternalGRandom.g:1275:10: ({...}? => (otherlv_5= 're-use' otherlv_6= '=' ( (lv_reUse_7_0= ruleDoubleQuantity ) ) ) )
                    	    // InternalGRandom.g:1275:11: {...}? => (otherlv_5= 're-use' otherlv_6= '=' ( (lv_reUse_7_0= ruleDoubleQuantity ) ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "rulePorts", "true");
                    	    }
                    	    // InternalGRandom.g:1275:20: (otherlv_5= 're-use' otherlv_6= '=' ( (lv_reUse_7_0= ruleDoubleQuantity ) ) )
                    	    // InternalGRandom.g:1275:21: otherlv_5= 're-use' otherlv_6= '=' ( (lv_reUse_7_0= ruleDoubleQuantity ) )
                    	    {
                    	    otherlv_5=(Token)match(input,38,FOLLOW_8); 

                    	    										newLeafNode(otherlv_5, grammarAccess.getPortsAccess().getReUseKeyword_2_1_1_0());
                    	    									
                    	    otherlv_6=(Token)match(input,14,FOLLOW_4); 

                    	    										newLeafNode(otherlv_6, grammarAccess.getPortsAccess().getEqualsSignKeyword_2_1_1_1());
                    	    									
                    	    // InternalGRandom.g:1283:10: ( (lv_reUse_7_0= ruleDoubleQuantity ) )
                    	    // InternalGRandom.g:1284:11: (lv_reUse_7_0= ruleDoubleQuantity )
                    	    {
                    	    // InternalGRandom.g:1284:11: (lv_reUse_7_0= ruleDoubleQuantity )
                    	    // InternalGRandom.g:1285:12: lv_reUse_7_0= ruleDoubleQuantity
                    	    {

                    	    												newCompositeNode(grammarAccess.getPortsAccess().getReUseDoubleQuantityParserRuleCall_2_1_1_2_0());
                    	    											
                    	    pushFollow(FOLLOW_16);
                    	    lv_reUse_7_0=ruleDoubleQuantity();

                    	    state._fsp--;


                    	    												if (current==null) {
                    	    													current = createModelElementForParent(grammarAccess.getPortsRule());
                    	    												}
                    	    												set(
                    	    													current,
                    	    													"reUse",
                    	    													lv_reUse_7_0,
                    	    													"de.cau.cs.kieler.kgraph.text.GRandom.DoubleQuantity");
                    	    												afterParserOrEnumRuleCall();
                    	    											

                    	    }


                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getPortsAccess().getUnorderedGroup_2_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 3 :
                    	    // InternalGRandom.g:1308:5: ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) )
                    	    {
                    	    // InternalGRandom.g:1308:5: ({...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) ) )
                    	    // InternalGRandom.g:1309:6: {...}? => ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getPortsAccess().getUnorderedGroup_2_1(), 2) ) {
                    	        throw new FailedPredicateException(input, "rulePorts", "getUnorderedGroupHelper().canSelect(grammarAccess.getPortsAccess().getUnorderedGroup_2_1(), 2)");
                    	    }
                    	    // InternalGRandom.g:1309:105: ( ({...}? => ( (lv_size_8_0= ruleSize ) ) ) )
                    	    // InternalGRandom.g:1310:7: ({...}? => ( (lv_size_8_0= ruleSize ) ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getPortsAccess().getUnorderedGroup_2_1(), 2);
                    	    						
                    	    // InternalGRandom.g:1313:10: ({...}? => ( (lv_size_8_0= ruleSize ) ) )
                    	    // InternalGRandom.g:1313:11: {...}? => ( (lv_size_8_0= ruleSize ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "rulePorts", "true");
                    	    }
                    	    // InternalGRandom.g:1313:20: ( (lv_size_8_0= ruleSize ) )
                    	    // InternalGRandom.g:1313:21: (lv_size_8_0= ruleSize )
                    	    {
                    	    // InternalGRandom.g:1313:21: (lv_size_8_0= ruleSize )
                    	    // InternalGRandom.g:1314:11: lv_size_8_0= ruleSize
                    	    {

                    	    											newCompositeNode(grammarAccess.getPortsAccess().getSizeSizeParserRuleCall_2_1_2_0());
                    	    										
                    	    pushFollow(FOLLOW_16);
                    	    lv_size_8_0=ruleSize();

                    	    state._fsp--;


                    	    											if (current==null) {
                    	    												current = createModelElementForParent(grammarAccess.getPortsRule());
                    	    											}
                    	    											set(
                    	    												current,
                    	    												"size",
                    	    												lv_size_8_0,
                    	    												"de.cau.cs.kieler.kgraph.text.GRandom.Size");
                    	    											afterParserOrEnumRuleCall();
                    	    										

                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getPortsAccess().getUnorderedGroup_2_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 4 :
                    	    // InternalGRandom.g:1336:5: ({...}? => ( ({...}? => (otherlv_9= 'constraint' otherlv_10= '=' ( (lv_constraint_11_0= ruleConstraintType ) ) ) ) ) )
                    	    {
                    	    // InternalGRandom.g:1336:5: ({...}? => ( ({...}? => (otherlv_9= 'constraint' otherlv_10= '=' ( (lv_constraint_11_0= ruleConstraintType ) ) ) ) ) )
                    	    // InternalGRandom.g:1337:6: {...}? => ( ({...}? => (otherlv_9= 'constraint' otherlv_10= '=' ( (lv_constraint_11_0= ruleConstraintType ) ) ) ) )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getPortsAccess().getUnorderedGroup_2_1(), 3) ) {
                    	        throw new FailedPredicateException(input, "rulePorts", "getUnorderedGroupHelper().canSelect(grammarAccess.getPortsAccess().getUnorderedGroup_2_1(), 3)");
                    	    }
                    	    // InternalGRandom.g:1337:105: ( ({...}? => (otherlv_9= 'constraint' otherlv_10= '=' ( (lv_constraint_11_0= ruleConstraintType ) ) ) ) )
                    	    // InternalGRandom.g:1338:7: ({...}? => (otherlv_9= 'constraint' otherlv_10= '=' ( (lv_constraint_11_0= ruleConstraintType ) ) ) )
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getPortsAccess().getUnorderedGroup_2_1(), 3);
                    	    						
                    	    // InternalGRandom.g:1341:10: ({...}? => (otherlv_9= 'constraint' otherlv_10= '=' ( (lv_constraint_11_0= ruleConstraintType ) ) ) )
                    	    // InternalGRandom.g:1341:11: {...}? => (otherlv_9= 'constraint' otherlv_10= '=' ( (lv_constraint_11_0= ruleConstraintType ) ) )
                    	    {
                    	    if ( !((true)) ) {
                    	        throw new FailedPredicateException(input, "rulePorts", "true");
                    	    }
                    	    // InternalGRandom.g:1341:20: (otherlv_9= 'constraint' otherlv_10= '=' ( (lv_constraint_11_0= ruleConstraintType ) ) )
                    	    // InternalGRandom.g:1341:21: otherlv_9= 'constraint' otherlv_10= '=' ( (lv_constraint_11_0= ruleConstraintType ) )
                    	    {
                    	    otherlv_9=(Token)match(input,39,FOLLOW_8); 

                    	    										newLeafNode(otherlv_9, grammarAccess.getPortsAccess().getConstraintKeyword_2_1_3_0());
                    	    									
                    	    otherlv_10=(Token)match(input,14,FOLLOW_17); 

                    	    										newLeafNode(otherlv_10, grammarAccess.getPortsAccess().getEqualsSignKeyword_2_1_3_1());
                    	    									
                    	    // InternalGRandom.g:1349:10: ( (lv_constraint_11_0= ruleConstraintType ) )
                    	    // InternalGRandom.g:1350:11: (lv_constraint_11_0= ruleConstraintType )
                    	    {
                    	    // InternalGRandom.g:1350:11: (lv_constraint_11_0= ruleConstraintType )
                    	    // InternalGRandom.g:1351:12: lv_constraint_11_0= ruleConstraintType
                    	    {

                    	    												newCompositeNode(grammarAccess.getPortsAccess().getConstraintConstraintTypeEnumRuleCall_2_1_3_2_0());
                    	    											
                    	    pushFollow(FOLLOW_16);
                    	    lv_constraint_11_0=ruleConstraintType();

                    	    state._fsp--;


                    	    												if (current==null) {
                    	    													current = createModelElementForParent(grammarAccess.getPortsRule());
                    	    												}
                    	    												set(
                    	    													current,
                    	    													"constraint",
                    	    													lv_constraint_11_0,
                    	    													"de.cau.cs.kieler.kgraph.text.GRandom.ConstraintType");
                    	    												afterParserOrEnumRuleCall();
                    	    											

                    	    }


                    	    }


                    	    }


                    	    }

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getPortsAccess().getUnorderedGroup_2_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;
                    	case 5 :
                    	    // InternalGRandom.g:1374:5: ({...}? => ( ({...}? => ( (lv_flow_12_0= ruleFlow ) ) )+ ) )
                    	    {
                    	    // InternalGRandom.g:1374:5: ({...}? => ( ({...}? => ( (lv_flow_12_0= ruleFlow ) ) )+ ) )
                    	    // InternalGRandom.g:1375:6: {...}? => ( ({...}? => ( (lv_flow_12_0= ruleFlow ) ) )+ )
                    	    {
                    	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getPortsAccess().getUnorderedGroup_2_1(), 4) ) {
                    	        throw new FailedPredicateException(input, "rulePorts", "getUnorderedGroupHelper().canSelect(grammarAccess.getPortsAccess().getUnorderedGroup_2_1(), 4)");
                    	    }
                    	    // InternalGRandom.g:1375:105: ( ({...}? => ( (lv_flow_12_0= ruleFlow ) ) )+ )
                    	    // InternalGRandom.g:1376:7: ({...}? => ( (lv_flow_12_0= ruleFlow ) ) )+
                    	    {

                    	    							getUnorderedGroupHelper().select(grammarAccess.getPortsAccess().getUnorderedGroup_2_1(), 4);
                    	    						
                    	    // InternalGRandom.g:1379:10: ({...}? => ( (lv_flow_12_0= ruleFlow ) ) )+
                    	    int cnt13=0;
                    	    loop13:
                    	    do {
                    	        int alt13=2;
                    	        int LA13_0 = input.LA(1);

                    	        if ( (LA13_0==55) ) {
                    	            int LA13_2 = input.LA(2);

                    	            if ( ((true)) ) {
                    	                alt13=1;
                    	            }


                    	        }
                    	        else if ( (LA13_0==30) ) {
                    	            int LA13_3 = input.LA(2);

                    	            if ( ((true)) ) {
                    	                alt13=1;
                    	            }


                    	        }


                    	        switch (alt13) {
                    	    	case 1 :
                    	    	    // InternalGRandom.g:1379:11: {...}? => ( (lv_flow_12_0= ruleFlow ) )
                    	    	    {
                    	    	    if ( !((true)) ) {
                    	    	        throw new FailedPredicateException(input, "rulePorts", "true");
                    	    	    }
                    	    	    // InternalGRandom.g:1379:20: ( (lv_flow_12_0= ruleFlow ) )
                    	    	    // InternalGRandom.g:1379:21: (lv_flow_12_0= ruleFlow )
                    	    	    {
                    	    	    // InternalGRandom.g:1379:21: (lv_flow_12_0= ruleFlow )
                    	    	    // InternalGRandom.g:1380:11: lv_flow_12_0= ruleFlow
                    	    	    {

                    	    	    											newCompositeNode(grammarAccess.getPortsAccess().getFlowFlowParserRuleCall_2_1_4_0());
                    	    	    										
                    	    	    pushFollow(FOLLOW_16);
                    	    	    lv_flow_12_0=ruleFlow();

                    	    	    state._fsp--;


                    	    	    											if (current==null) {
                    	    	    												current = createModelElementForParent(grammarAccess.getPortsRule());
                    	    	    											}
                    	    	    											add(
                    	    	    												current,
                    	    	    												"flow",
                    	    	    												lv_flow_12_0,
                    	    	    												"de.cau.cs.kieler.kgraph.text.GRandom.Flow");
                    	    	    											afterParserOrEnumRuleCall();
                    	    	    										

                    	    	    }


                    	    	    }


                    	    	    }
                    	    	    break;

                    	    	default :
                    	    	    if ( cnt13 >= 1 ) break loop13;
                    	                EarlyExitException eee =
                    	                    new EarlyExitException(13, input);
                    	                throw eee;
                    	        }
                    	        cnt13++;
                    	    } while (true);

                    	     
                    	    							getUnorderedGroupHelper().returnFromSelection(grammarAccess.getPortsAccess().getUnorderedGroup_2_1());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);


                    }


                    }

                     
                    					  getUnorderedGroupHelper().leave(grammarAccess.getPortsAccess().getUnorderedGroup_2_1());
                    					

                    }

                    otherlv_13=(Token)match(input,20,FOLLOW_2); 

                    				newLeafNode(otherlv_13, grammarAccess.getPortsAccess().getRightCurlyBracketKeyword_2_2());
                    			

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
    // $ANTLR end "rulePorts"


    // $ANTLR start "entryRuleFlow"
    // InternalGRandom.g:1418:1: entryRuleFlow returns [EObject current=null] : iv_ruleFlow= ruleFlow EOF ;
    public final EObject entryRuleFlow() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFlow = null;


        try {
            // InternalGRandom.g:1418:45: (iv_ruleFlow= ruleFlow EOF )
            // InternalGRandom.g:1419:2: iv_ruleFlow= ruleFlow EOF
            {
             newCompositeNode(grammarAccess.getFlowRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFlow=ruleFlow();

            state._fsp--;

             current =iv_ruleFlow; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFlow"


    // $ANTLR start "ruleFlow"
    // InternalGRandom.g:1425:1: ruleFlow returns [EObject current=null] : ( ( (lv_flowType_0_0= ruleFlowType ) ) ( (lv_side_1_0= ruleSide ) ) otherlv_2= '=' ( (lv_amount_3_0= ruleDoubleQuantity ) ) ) ;
    public final EObject ruleFlow() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Enumerator lv_flowType_0_0 = null;

        Enumerator lv_side_1_0 = null;

        EObject lv_amount_3_0 = null;



        	enterRule();

        try {
            // InternalGRandom.g:1431:2: ( ( ( (lv_flowType_0_0= ruleFlowType ) ) ( (lv_side_1_0= ruleSide ) ) otherlv_2= '=' ( (lv_amount_3_0= ruleDoubleQuantity ) ) ) )
            // InternalGRandom.g:1432:2: ( ( (lv_flowType_0_0= ruleFlowType ) ) ( (lv_side_1_0= ruleSide ) ) otherlv_2= '=' ( (lv_amount_3_0= ruleDoubleQuantity ) ) )
            {
            // InternalGRandom.g:1432:2: ( ( (lv_flowType_0_0= ruleFlowType ) ) ( (lv_side_1_0= ruleSide ) ) otherlv_2= '=' ( (lv_amount_3_0= ruleDoubleQuantity ) ) )
            // InternalGRandom.g:1433:3: ( (lv_flowType_0_0= ruleFlowType ) ) ( (lv_side_1_0= ruleSide ) ) otherlv_2= '=' ( (lv_amount_3_0= ruleDoubleQuantity ) )
            {
            // InternalGRandom.g:1433:3: ( (lv_flowType_0_0= ruleFlowType ) )
            // InternalGRandom.g:1434:4: (lv_flowType_0_0= ruleFlowType )
            {
            // InternalGRandom.g:1434:4: (lv_flowType_0_0= ruleFlowType )
            // InternalGRandom.g:1435:5: lv_flowType_0_0= ruleFlowType
            {

            					newCompositeNode(grammarAccess.getFlowAccess().getFlowTypeFlowTypeEnumRuleCall_0_0());
            				
            pushFollow(FOLLOW_18);
            lv_flowType_0_0=ruleFlowType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getFlowRule());
            					}
            					set(
            						current,
            						"flowType",
            						lv_flowType_0_0,
            						"de.cau.cs.kieler.kgraph.text.GRandom.FlowType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalGRandom.g:1452:3: ( (lv_side_1_0= ruleSide ) )
            // InternalGRandom.g:1453:4: (lv_side_1_0= ruleSide )
            {
            // InternalGRandom.g:1453:4: (lv_side_1_0= ruleSide )
            // InternalGRandom.g:1454:5: lv_side_1_0= ruleSide
            {

            					newCompositeNode(grammarAccess.getFlowAccess().getSideSideEnumRuleCall_1_0());
            				
            pushFollow(FOLLOW_8);
            lv_side_1_0=ruleSide();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getFlowRule());
            					}
            					set(
            						current,
            						"side",
            						lv_side_1_0,
            						"de.cau.cs.kieler.kgraph.text.GRandom.Side");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,14,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getFlowAccess().getEqualsSignKeyword_2());
            		
            // InternalGRandom.g:1475:3: ( (lv_amount_3_0= ruleDoubleQuantity ) )
            // InternalGRandom.g:1476:4: (lv_amount_3_0= ruleDoubleQuantity )
            {
            // InternalGRandom.g:1476:4: (lv_amount_3_0= ruleDoubleQuantity )
            // InternalGRandom.g:1477:5: lv_amount_3_0= ruleDoubleQuantity
            {

            					newCompositeNode(grammarAccess.getFlowAccess().getAmountDoubleQuantityParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_2);
            lv_amount_3_0=ruleDoubleQuantity();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getFlowRule());
            					}
            					set(
            						current,
            						"amount",
            						lv_amount_3_0,
            						"de.cau.cs.kieler.kgraph.text.GRandom.DoubleQuantity");
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
    // $ANTLR end "ruleFlow"


    // $ANTLR start "entryRuleLabels"
    // InternalGRandom.g:1498:1: entryRuleLabels returns [String current=null] : iv_ruleLabels= ruleLabels EOF ;
    public final String entryRuleLabels() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleLabels = null;


        try {
            // InternalGRandom.g:1498:46: (iv_ruleLabels= ruleLabels EOF )
            // InternalGRandom.g:1499:2: iv_ruleLabels= ruleLabels EOF
            {
             newCompositeNode(grammarAccess.getLabelsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLabels=ruleLabels();

            state._fsp--;

             current =iv_ruleLabels.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLabels"


    // $ANTLR start "ruleLabels"
    // InternalGRandom.g:1505:1: ruleLabels returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= 'labels' ;
    public final AntlrDatatypeRuleToken ruleLabels() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalGRandom.g:1511:2: (kw= 'labels' )
            // InternalGRandom.g:1512:2: kw= 'labels'
            {
            kw=(Token)match(input,31,FOLLOW_2); 

            		current.merge(kw);
            		newLeafNode(kw, grammarAccess.getLabelsAccess().getLabelsKeyword());
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLabels"


    // $ANTLR start "entryRuleDoubleQuantity"
    // InternalGRandom.g:1520:1: entryRuleDoubleQuantity returns [EObject current=null] : iv_ruleDoubleQuantity= ruleDoubleQuantity EOF ;
    public final EObject entryRuleDoubleQuantity() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDoubleQuantity = null;


        try {
            // InternalGRandom.g:1520:55: (iv_ruleDoubleQuantity= ruleDoubleQuantity EOF )
            // InternalGRandom.g:1521:2: iv_ruleDoubleQuantity= ruleDoubleQuantity EOF
            {
             newCompositeNode(grammarAccess.getDoubleQuantityRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDoubleQuantity=ruleDoubleQuantity();

            state._fsp--;

             current =iv_ruleDoubleQuantity; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDoubleQuantity"


    // $ANTLR start "ruleDoubleQuantity"
    // InternalGRandom.g:1527:1: ruleDoubleQuantity returns [EObject current=null] : ( ( (lv_quant_0_0= ruleDouble ) ) | ( ( (lv_min_1_0= ruleDouble ) ) ( (lv_minMax_2_0= 'to' ) ) ( (lv_max_3_0= ruleDouble ) ) ) | ( ( (lv_mean_4_0= ruleDouble ) ) ( (lv_gaussian_5_0= rulePm ) ) ( (lv_stddv_6_0= ruleDouble ) ) ) ) ;
    public final EObject ruleDoubleQuantity() throws RecognitionException {
        EObject current = null;

        Token lv_minMax_2_0=null;
        AntlrDatatypeRuleToken lv_quant_0_0 = null;

        AntlrDatatypeRuleToken lv_min_1_0 = null;

        AntlrDatatypeRuleToken lv_max_3_0 = null;

        AntlrDatatypeRuleToken lv_mean_4_0 = null;

        AntlrDatatypeRuleToken lv_gaussian_5_0 = null;

        AntlrDatatypeRuleToken lv_stddv_6_0 = null;



        	enterRule();

        try {
            // InternalGRandom.g:1533:2: ( ( ( (lv_quant_0_0= ruleDouble ) ) | ( ( (lv_min_1_0= ruleDouble ) ) ( (lv_minMax_2_0= 'to' ) ) ( (lv_max_3_0= ruleDouble ) ) ) | ( ( (lv_mean_4_0= ruleDouble ) ) ( (lv_gaussian_5_0= rulePm ) ) ( (lv_stddv_6_0= ruleDouble ) ) ) ) )
            // InternalGRandom.g:1534:2: ( ( (lv_quant_0_0= ruleDouble ) ) | ( ( (lv_min_1_0= ruleDouble ) ) ( (lv_minMax_2_0= 'to' ) ) ( (lv_max_3_0= ruleDouble ) ) ) | ( ( (lv_mean_4_0= ruleDouble ) ) ( (lv_gaussian_5_0= rulePm ) ) ( (lv_stddv_6_0= ruleDouble ) ) ) )
            {
            // InternalGRandom.g:1534:2: ( ( (lv_quant_0_0= ruleDouble ) ) | ( ( (lv_min_1_0= ruleDouble ) ) ( (lv_minMax_2_0= 'to' ) ) ( (lv_max_3_0= ruleDouble ) ) ) | ( ( (lv_mean_4_0= ruleDouble ) ) ( (lv_gaussian_5_0= rulePm ) ) ( (lv_stddv_6_0= ruleDouble ) ) ) )
            int alt16=3;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_INT) ) {
                switch ( input.LA(2) ) {
                case 42:
                    {
                    int LA16_2 = input.LA(3);

                    if ( (LA16_2==RULE_INT) ) {
                        switch ( input.LA(4) ) {
                        case 41:
                            {
                            alt16=3;
                            }
                            break;
                        case 40:
                            {
                            alt16=2;
                            }
                            break;
                        case EOF:
                        case 12:
                        case 13:
                        case 15:
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 25:
                        case 26:
                        case 30:
                        case 31:
                        case 33:
                        case 34:
                        case 35:
                        case 36:
                        case 38:
                        case 39:
                        case 55:
                            {
                            alt16=1;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("", 16, 6, input);

                            throw nvae;
                        }

                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 16, 2, input);

                        throw nvae;
                    }
                    }
                    break;
                case EOF:
                case 12:
                case 13:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 30:
                case 31:
                case 33:
                case 34:
                case 35:
                case 36:
                case 38:
                case 39:
                case 55:
                    {
                    alt16=1;
                    }
                    break;
                case 41:
                    {
                    alt16=3;
                    }
                    break;
                case 40:
                    {
                    alt16=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 16, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // InternalGRandom.g:1535:3: ( (lv_quant_0_0= ruleDouble ) )
                    {
                    // InternalGRandom.g:1535:3: ( (lv_quant_0_0= ruleDouble ) )
                    // InternalGRandom.g:1536:4: (lv_quant_0_0= ruleDouble )
                    {
                    // InternalGRandom.g:1536:4: (lv_quant_0_0= ruleDouble )
                    // InternalGRandom.g:1537:5: lv_quant_0_0= ruleDouble
                    {

                    					newCompositeNode(grammarAccess.getDoubleQuantityAccess().getQuantDoubleParserRuleCall_0_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_quant_0_0=ruleDouble();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getDoubleQuantityRule());
                    					}
                    					set(
                    						current,
                    						"quant",
                    						lv_quant_0_0,
                    						"de.cau.cs.kieler.kgraph.text.GRandom.Double");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalGRandom.g:1555:3: ( ( (lv_min_1_0= ruleDouble ) ) ( (lv_minMax_2_0= 'to' ) ) ( (lv_max_3_0= ruleDouble ) ) )
                    {
                    // InternalGRandom.g:1555:3: ( ( (lv_min_1_0= ruleDouble ) ) ( (lv_minMax_2_0= 'to' ) ) ( (lv_max_3_0= ruleDouble ) ) )
                    // InternalGRandom.g:1556:4: ( (lv_min_1_0= ruleDouble ) ) ( (lv_minMax_2_0= 'to' ) ) ( (lv_max_3_0= ruleDouble ) )
                    {
                    // InternalGRandom.g:1556:4: ( (lv_min_1_0= ruleDouble ) )
                    // InternalGRandom.g:1557:5: (lv_min_1_0= ruleDouble )
                    {
                    // InternalGRandom.g:1557:5: (lv_min_1_0= ruleDouble )
                    // InternalGRandom.g:1558:6: lv_min_1_0= ruleDouble
                    {

                    						newCompositeNode(grammarAccess.getDoubleQuantityAccess().getMinDoubleParserRuleCall_1_0_0());
                    					
                    pushFollow(FOLLOW_19);
                    lv_min_1_0=ruleDouble();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getDoubleQuantityRule());
                    						}
                    						set(
                    							current,
                    							"min",
                    							lv_min_1_0,
                    							"de.cau.cs.kieler.kgraph.text.GRandom.Double");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalGRandom.g:1575:4: ( (lv_minMax_2_0= 'to' ) )
                    // InternalGRandom.g:1576:5: (lv_minMax_2_0= 'to' )
                    {
                    // InternalGRandom.g:1576:5: (lv_minMax_2_0= 'to' )
                    // InternalGRandom.g:1577:6: lv_minMax_2_0= 'to'
                    {
                    lv_minMax_2_0=(Token)match(input,40,FOLLOW_4); 

                    						newLeafNode(lv_minMax_2_0, grammarAccess.getDoubleQuantityAccess().getMinMaxToKeyword_1_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getDoubleQuantityRule());
                    						}
                    						setWithLastConsumed(current, "minMax", true, "to");
                    					

                    }


                    }

                    // InternalGRandom.g:1589:4: ( (lv_max_3_0= ruleDouble ) )
                    // InternalGRandom.g:1590:5: (lv_max_3_0= ruleDouble )
                    {
                    // InternalGRandom.g:1590:5: (lv_max_3_0= ruleDouble )
                    // InternalGRandom.g:1591:6: lv_max_3_0= ruleDouble
                    {

                    						newCompositeNode(grammarAccess.getDoubleQuantityAccess().getMaxDoubleParserRuleCall_1_2_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_max_3_0=ruleDouble();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getDoubleQuantityRule());
                    						}
                    						set(
                    							current,
                    							"max",
                    							lv_max_3_0,
                    							"de.cau.cs.kieler.kgraph.text.GRandom.Double");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalGRandom.g:1610:3: ( ( (lv_mean_4_0= ruleDouble ) ) ( (lv_gaussian_5_0= rulePm ) ) ( (lv_stddv_6_0= ruleDouble ) ) )
                    {
                    // InternalGRandom.g:1610:3: ( ( (lv_mean_4_0= ruleDouble ) ) ( (lv_gaussian_5_0= rulePm ) ) ( (lv_stddv_6_0= ruleDouble ) ) )
                    // InternalGRandom.g:1611:4: ( (lv_mean_4_0= ruleDouble ) ) ( (lv_gaussian_5_0= rulePm ) ) ( (lv_stddv_6_0= ruleDouble ) )
                    {
                    // InternalGRandom.g:1611:4: ( (lv_mean_4_0= ruleDouble ) )
                    // InternalGRandom.g:1612:5: (lv_mean_4_0= ruleDouble )
                    {
                    // InternalGRandom.g:1612:5: (lv_mean_4_0= ruleDouble )
                    // InternalGRandom.g:1613:6: lv_mean_4_0= ruleDouble
                    {

                    						newCompositeNode(grammarAccess.getDoubleQuantityAccess().getMeanDoubleParserRuleCall_2_0_0());
                    					
                    pushFollow(FOLLOW_20);
                    lv_mean_4_0=ruleDouble();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getDoubleQuantityRule());
                    						}
                    						set(
                    							current,
                    							"mean",
                    							lv_mean_4_0,
                    							"de.cau.cs.kieler.kgraph.text.GRandom.Double");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalGRandom.g:1630:4: ( (lv_gaussian_5_0= rulePm ) )
                    // InternalGRandom.g:1631:5: (lv_gaussian_5_0= rulePm )
                    {
                    // InternalGRandom.g:1631:5: (lv_gaussian_5_0= rulePm )
                    // InternalGRandom.g:1632:6: lv_gaussian_5_0= rulePm
                    {

                    						newCompositeNode(grammarAccess.getDoubleQuantityAccess().getGaussianPmParserRuleCall_2_1_0());
                    					
                    pushFollow(FOLLOW_4);
                    lv_gaussian_5_0=rulePm();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getDoubleQuantityRule());
                    						}
                    						set(
                    							current,
                    							"gaussian",
                    							true,
                    							"de.cau.cs.kieler.kgraph.text.GRandom.Pm");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalGRandom.g:1649:4: ( (lv_stddv_6_0= ruleDouble ) )
                    // InternalGRandom.g:1650:5: (lv_stddv_6_0= ruleDouble )
                    {
                    // InternalGRandom.g:1650:5: (lv_stddv_6_0= ruleDouble )
                    // InternalGRandom.g:1651:6: lv_stddv_6_0= ruleDouble
                    {

                    						newCompositeNode(grammarAccess.getDoubleQuantityAccess().getStddvDoubleParserRuleCall_2_2_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_stddv_6_0=ruleDouble();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getDoubleQuantityRule());
                    						}
                    						set(
                    							current,
                    							"stddv",
                    							lv_stddv_6_0,
                    							"de.cau.cs.kieler.kgraph.text.GRandom.Double");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


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
    // $ANTLR end "ruleDoubleQuantity"


    // $ANTLR start "entryRulePm"
    // InternalGRandom.g:1673:1: entryRulePm returns [String current=null] : iv_rulePm= rulePm EOF ;
    public final String entryRulePm() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePm = null;


        try {
            // InternalGRandom.g:1673:42: (iv_rulePm= rulePm EOF )
            // InternalGRandom.g:1674:2: iv_rulePm= rulePm EOF
            {
             newCompositeNode(grammarAccess.getPmRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePm=rulePm();

            state._fsp--;

             current =iv_rulePm.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePm"


    // $ANTLR start "rulePm"
    // InternalGRandom.g:1680:1: rulePm returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '+/-' ;
    public final AntlrDatatypeRuleToken rulePm() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalGRandom.g:1686:2: (kw= '+/-' )
            // InternalGRandom.g:1687:2: kw= '+/-'
            {
            kw=(Token)match(input,41,FOLLOW_2); 

            		current.merge(kw);
            		newLeafNode(kw, grammarAccess.getPmAccess().getPlusSignSolidusHyphenMinusKeyword());
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePm"


    // $ANTLR start "entryRuleDouble"
    // InternalGRandom.g:1695:1: entryRuleDouble returns [String current=null] : iv_ruleDouble= ruleDouble EOF ;
    public final String entryRuleDouble() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleDouble = null;


        try {
            // InternalGRandom.g:1695:46: (iv_ruleDouble= ruleDouble EOF )
            // InternalGRandom.g:1696:2: iv_ruleDouble= ruleDouble EOF
            {
             newCompositeNode(grammarAccess.getDoubleRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDouble=ruleDouble();

            state._fsp--;

             current =iv_ruleDouble.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDouble"


    // $ANTLR start "ruleDouble"
    // InternalGRandom.g:1702:1: ruleDouble returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_INT_0= RULE_INT (kw= '.' this_INT_2= RULE_INT )? ) ;
    public final AntlrDatatypeRuleToken ruleDouble() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;
        Token kw=null;
        Token this_INT_2=null;


        	enterRule();

        try {
            // InternalGRandom.g:1708:2: ( (this_INT_0= RULE_INT (kw= '.' this_INT_2= RULE_INT )? ) )
            // InternalGRandom.g:1709:2: (this_INT_0= RULE_INT (kw= '.' this_INT_2= RULE_INT )? )
            {
            // InternalGRandom.g:1709:2: (this_INT_0= RULE_INT (kw= '.' this_INT_2= RULE_INT )? )
            // InternalGRandom.g:1710:3: this_INT_0= RULE_INT (kw= '.' this_INT_2= RULE_INT )?
            {
            this_INT_0=(Token)match(input,RULE_INT,FOLLOW_21); 

            			current.merge(this_INT_0);
            		

            			newLeafNode(this_INT_0, grammarAccess.getDoubleAccess().getINTTerminalRuleCall_0());
            		
            // InternalGRandom.g:1717:3: (kw= '.' this_INT_2= RULE_INT )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==42) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalGRandom.g:1718:4: kw= '.' this_INT_2= RULE_INT
                    {
                    kw=(Token)match(input,42,FOLLOW_4); 

                    				current.merge(kw);
                    				newLeafNode(kw, grammarAccess.getDoubleAccess().getFullStopKeyword_1_0());
                    			
                    this_INT_2=(Token)match(input,RULE_INT,FOLLOW_2); 

                    				current.merge(this_INT_2);
                    			

                    				newLeafNode(this_INT_2, grammarAccess.getDoubleAccess().getINTTerminalRuleCall_1_1());
                    			

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
    // $ANTLR end "ruleDouble"


    // $ANTLR start "entryRuleInteger"
    // InternalGRandom.g:1735:1: entryRuleInteger returns [String current=null] : iv_ruleInteger= ruleInteger EOF ;
    public final String entryRuleInteger() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleInteger = null;


        try {
            // InternalGRandom.g:1735:47: (iv_ruleInteger= ruleInteger EOF )
            // InternalGRandom.g:1736:2: iv_ruleInteger= ruleInteger EOF
            {
             newCompositeNode(grammarAccess.getIntegerRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleInteger=ruleInteger();

            state._fsp--;

             current =iv_ruleInteger.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInteger"


    // $ANTLR start "ruleInteger"
    // InternalGRandom.g:1742:1: ruleInteger returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_INT_0= RULE_INT (kw= '.' this_INT_2= RULE_INT )? ) ;
    public final AntlrDatatypeRuleToken ruleInteger() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;
        Token kw=null;
        Token this_INT_2=null;


        	enterRule();

        try {
            // InternalGRandom.g:1748:2: ( (this_INT_0= RULE_INT (kw= '.' this_INT_2= RULE_INT )? ) )
            // InternalGRandom.g:1749:2: (this_INT_0= RULE_INT (kw= '.' this_INT_2= RULE_INT )? )
            {
            // InternalGRandom.g:1749:2: (this_INT_0= RULE_INT (kw= '.' this_INT_2= RULE_INT )? )
            // InternalGRandom.g:1750:3: this_INT_0= RULE_INT (kw= '.' this_INT_2= RULE_INT )?
            {
            this_INT_0=(Token)match(input,RULE_INT,FOLLOW_21); 

            			current.merge(this_INT_0);
            		

            			newLeafNode(this_INT_0, grammarAccess.getIntegerAccess().getINTTerminalRuleCall_0());
            		
            // InternalGRandom.g:1757:3: (kw= '.' this_INT_2= RULE_INT )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==42) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalGRandom.g:1758:4: kw= '.' this_INT_2= RULE_INT
                    {
                    kw=(Token)match(input,42,FOLLOW_4); 

                    				current.merge(kw);
                    				newLeafNode(kw, grammarAccess.getIntegerAccess().getFullStopKeyword_1_0());
                    			
                    this_INT_2=(Token)match(input,RULE_INT,FOLLOW_2); 

                    				current.merge(this_INT_2);
                    			

                    				newLeafNode(this_INT_2, grammarAccess.getIntegerAccess().getINTTerminalRuleCall_1_1());
                    			

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
    // $ANTLR end "ruleInteger"


    // $ANTLR start "ruleFormats"
    // InternalGRandom.g:1775:1: ruleFormats returns [Enumerator current=null] : ( (enumLiteral_0= 'kgt' ) | (enumLiteral_1= 'kgx' ) ) ;
    public final Enumerator ruleFormats() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalGRandom.g:1781:2: ( ( (enumLiteral_0= 'kgt' ) | (enumLiteral_1= 'kgx' ) ) )
            // InternalGRandom.g:1782:2: ( (enumLiteral_0= 'kgt' ) | (enumLiteral_1= 'kgx' ) )
            {
            // InternalGRandom.g:1782:2: ( (enumLiteral_0= 'kgt' ) | (enumLiteral_1= 'kgx' ) )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==43) ) {
                alt19=1;
            }
            else if ( (LA19_0==44) ) {
                alt19=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // InternalGRandom.g:1783:3: (enumLiteral_0= 'kgt' )
                    {
                    // InternalGRandom.g:1783:3: (enumLiteral_0= 'kgt' )
                    // InternalGRandom.g:1784:4: enumLiteral_0= 'kgt'
                    {
                    enumLiteral_0=(Token)match(input,43,FOLLOW_2); 

                    				current = grammarAccess.getFormatsAccess().getKgtEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getFormatsAccess().getKgtEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalGRandom.g:1791:3: (enumLiteral_1= 'kgx' )
                    {
                    // InternalGRandom.g:1791:3: (enumLiteral_1= 'kgx' )
                    // InternalGRandom.g:1792:4: enumLiteral_1= 'kgx'
                    {
                    enumLiteral_1=(Token)match(input,44,FOLLOW_2); 

                    				current = grammarAccess.getFormatsAccess().getKgxEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getFormatsAccess().getKgxEnumLiteralDeclaration_1());
                    			

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
    // $ANTLR end "ruleFormats"


    // $ANTLR start "ruleForm"
    // InternalGRandom.g:1802:1: ruleForm returns [Enumerator current=null] : ( (enumLiteral_0= 'trees' ) | (enumLiteral_1= 'bipartite graphs' ) | (enumLiteral_2= 'graphs' ) | (enumLiteral_3= 'triconnected graphs' ) | (enumLiteral_4= 'biconnected graphs' ) | (enumLiteral_5= 'acyclic graphs' ) ) ;
    public final Enumerator ruleForm() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;


        	enterRule();

        try {
            // InternalGRandom.g:1808:2: ( ( (enumLiteral_0= 'trees' ) | (enumLiteral_1= 'bipartite graphs' ) | (enumLiteral_2= 'graphs' ) | (enumLiteral_3= 'triconnected graphs' ) | (enumLiteral_4= 'biconnected graphs' ) | (enumLiteral_5= 'acyclic graphs' ) ) )
            // InternalGRandom.g:1809:2: ( (enumLiteral_0= 'trees' ) | (enumLiteral_1= 'bipartite graphs' ) | (enumLiteral_2= 'graphs' ) | (enumLiteral_3= 'triconnected graphs' ) | (enumLiteral_4= 'biconnected graphs' ) | (enumLiteral_5= 'acyclic graphs' ) )
            {
            // InternalGRandom.g:1809:2: ( (enumLiteral_0= 'trees' ) | (enumLiteral_1= 'bipartite graphs' ) | (enumLiteral_2= 'graphs' ) | (enumLiteral_3= 'triconnected graphs' ) | (enumLiteral_4= 'biconnected graphs' ) | (enumLiteral_5= 'acyclic graphs' ) )
            int alt20=6;
            switch ( input.LA(1) ) {
            case 45:
                {
                alt20=1;
                }
                break;
            case 46:
                {
                alt20=2;
                }
                break;
            case 47:
                {
                alt20=3;
                }
                break;
            case 48:
                {
                alt20=4;
                }
                break;
            case 49:
                {
                alt20=5;
                }
                break;
            case 50:
                {
                alt20=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }

            switch (alt20) {
                case 1 :
                    // InternalGRandom.g:1810:3: (enumLiteral_0= 'trees' )
                    {
                    // InternalGRandom.g:1810:3: (enumLiteral_0= 'trees' )
                    // InternalGRandom.g:1811:4: enumLiteral_0= 'trees'
                    {
                    enumLiteral_0=(Token)match(input,45,FOLLOW_2); 

                    				current = grammarAccess.getFormAccess().getTreesEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getFormAccess().getTreesEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalGRandom.g:1818:3: (enumLiteral_1= 'bipartite graphs' )
                    {
                    // InternalGRandom.g:1818:3: (enumLiteral_1= 'bipartite graphs' )
                    // InternalGRandom.g:1819:4: enumLiteral_1= 'bipartite graphs'
                    {
                    enumLiteral_1=(Token)match(input,46,FOLLOW_2); 

                    				current = grammarAccess.getFormAccess().getBipartiteEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getFormAccess().getBipartiteEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalGRandom.g:1826:3: (enumLiteral_2= 'graphs' )
                    {
                    // InternalGRandom.g:1826:3: (enumLiteral_2= 'graphs' )
                    // InternalGRandom.g:1827:4: enumLiteral_2= 'graphs'
                    {
                    enumLiteral_2=(Token)match(input,47,FOLLOW_2); 

                    				current = grammarAccess.getFormAccess().getCustomEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getFormAccess().getCustomEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalGRandom.g:1834:3: (enumLiteral_3= 'triconnected graphs' )
                    {
                    // InternalGRandom.g:1834:3: (enumLiteral_3= 'triconnected graphs' )
                    // InternalGRandom.g:1835:4: enumLiteral_3= 'triconnected graphs'
                    {
                    enumLiteral_3=(Token)match(input,48,FOLLOW_2); 

                    				current = grammarAccess.getFormAccess().getTriconnectedEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getFormAccess().getTriconnectedEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalGRandom.g:1842:3: (enumLiteral_4= 'biconnected graphs' )
                    {
                    // InternalGRandom.g:1842:3: (enumLiteral_4= 'biconnected graphs' )
                    // InternalGRandom.g:1843:4: enumLiteral_4= 'biconnected graphs'
                    {
                    enumLiteral_4=(Token)match(input,49,FOLLOW_2); 

                    				current = grammarAccess.getFormAccess().getBiconnectedEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getFormAccess().getBiconnectedEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalGRandom.g:1850:3: (enumLiteral_5= 'acyclic graphs' )
                    {
                    // InternalGRandom.g:1850:3: (enumLiteral_5= 'acyclic graphs' )
                    // InternalGRandom.g:1851:4: enumLiteral_5= 'acyclic graphs'
                    {
                    enumLiteral_5=(Token)match(input,50,FOLLOW_2); 

                    				current = grammarAccess.getFormAccess().getAcyclicEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getFormAccess().getAcyclicEnumLiteralDeclaration_5());
                    			

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
    // $ANTLR end "ruleForm"


    // $ANTLR start "ruleSide"
    // InternalGRandom.g:1861:1: ruleSide returns [Enumerator current=null] : ( (enumLiteral_0= 'north' ) | (enumLiteral_1= 'east' ) | (enumLiteral_2= 'south' ) | (enumLiteral_3= 'west' ) ) ;
    public final Enumerator ruleSide() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;


        	enterRule();

        try {
            // InternalGRandom.g:1867:2: ( ( (enumLiteral_0= 'north' ) | (enumLiteral_1= 'east' ) | (enumLiteral_2= 'south' ) | (enumLiteral_3= 'west' ) ) )
            // InternalGRandom.g:1868:2: ( (enumLiteral_0= 'north' ) | (enumLiteral_1= 'east' ) | (enumLiteral_2= 'south' ) | (enumLiteral_3= 'west' ) )
            {
            // InternalGRandom.g:1868:2: ( (enumLiteral_0= 'north' ) | (enumLiteral_1= 'east' ) | (enumLiteral_2= 'south' ) | (enumLiteral_3= 'west' ) )
            int alt21=4;
            switch ( input.LA(1) ) {
            case 51:
                {
                alt21=1;
                }
                break;
            case 52:
                {
                alt21=2;
                }
                break;
            case 53:
                {
                alt21=3;
                }
                break;
            case 54:
                {
                alt21=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }

            switch (alt21) {
                case 1 :
                    // InternalGRandom.g:1869:3: (enumLiteral_0= 'north' )
                    {
                    // InternalGRandom.g:1869:3: (enumLiteral_0= 'north' )
                    // InternalGRandom.g:1870:4: enumLiteral_0= 'north'
                    {
                    enumLiteral_0=(Token)match(input,51,FOLLOW_2); 

                    				current = grammarAccess.getSideAccess().getNorthEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getSideAccess().getNorthEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalGRandom.g:1877:3: (enumLiteral_1= 'east' )
                    {
                    // InternalGRandom.g:1877:3: (enumLiteral_1= 'east' )
                    // InternalGRandom.g:1878:4: enumLiteral_1= 'east'
                    {
                    enumLiteral_1=(Token)match(input,52,FOLLOW_2); 

                    				current = grammarAccess.getSideAccess().getEastEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getSideAccess().getEastEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalGRandom.g:1885:3: (enumLiteral_2= 'south' )
                    {
                    // InternalGRandom.g:1885:3: (enumLiteral_2= 'south' )
                    // InternalGRandom.g:1886:4: enumLiteral_2= 'south'
                    {
                    enumLiteral_2=(Token)match(input,53,FOLLOW_2); 

                    				current = grammarAccess.getSideAccess().getSouthEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getSideAccess().getSouthEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalGRandom.g:1893:3: (enumLiteral_3= 'west' )
                    {
                    // InternalGRandom.g:1893:3: (enumLiteral_3= 'west' )
                    // InternalGRandom.g:1894:4: enumLiteral_3= 'west'
                    {
                    enumLiteral_3=(Token)match(input,54,FOLLOW_2); 

                    				current = grammarAccess.getSideAccess().getWestEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getSideAccess().getWestEnumLiteralDeclaration_3());
                    			

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
    // $ANTLR end "ruleSide"


    // $ANTLR start "ruleFlowType"
    // InternalGRandom.g:1904:1: ruleFlowType returns [Enumerator current=null] : ( (enumLiteral_0= 'incoming' ) | (enumLiteral_1= 'outgoing' ) ) ;
    public final Enumerator ruleFlowType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalGRandom.g:1910:2: ( ( (enumLiteral_0= 'incoming' ) | (enumLiteral_1= 'outgoing' ) ) )
            // InternalGRandom.g:1911:2: ( (enumLiteral_0= 'incoming' ) | (enumLiteral_1= 'outgoing' ) )
            {
            // InternalGRandom.g:1911:2: ( (enumLiteral_0= 'incoming' ) | (enumLiteral_1= 'outgoing' ) )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==55) ) {
                alt22=1;
            }
            else if ( (LA22_0==30) ) {
                alt22=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // InternalGRandom.g:1912:3: (enumLiteral_0= 'incoming' )
                    {
                    // InternalGRandom.g:1912:3: (enumLiteral_0= 'incoming' )
                    // InternalGRandom.g:1913:4: enumLiteral_0= 'incoming'
                    {
                    enumLiteral_0=(Token)match(input,55,FOLLOW_2); 

                    				current = grammarAccess.getFlowTypeAccess().getIncomingEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getFlowTypeAccess().getIncomingEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalGRandom.g:1920:3: (enumLiteral_1= 'outgoing' )
                    {
                    // InternalGRandom.g:1920:3: (enumLiteral_1= 'outgoing' )
                    // InternalGRandom.g:1921:4: enumLiteral_1= 'outgoing'
                    {
                    enumLiteral_1=(Token)match(input,30,FOLLOW_2); 

                    				current = grammarAccess.getFlowTypeAccess().getOutgoingEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getFlowTypeAccess().getOutgoingEnumLiteralDeclaration_1());
                    			

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
    // $ANTLR end "ruleFlowType"


    // $ANTLR start "ruleConstraintType"
    // InternalGRandom.g:1931:1: ruleConstraintType returns [Enumerator current=null] : ( (enumLiteral_0= 'free' ) | (enumLiteral_1= 'side' ) | (enumLiteral_2= 'position' ) | (enumLiteral_3= 'order' ) | (enumLiteral_4= 'ratio' ) ) ;
    public final Enumerator ruleConstraintType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;


        	enterRule();

        try {
            // InternalGRandom.g:1937:2: ( ( (enumLiteral_0= 'free' ) | (enumLiteral_1= 'side' ) | (enumLiteral_2= 'position' ) | (enumLiteral_3= 'order' ) | (enumLiteral_4= 'ratio' ) ) )
            // InternalGRandom.g:1938:2: ( (enumLiteral_0= 'free' ) | (enumLiteral_1= 'side' ) | (enumLiteral_2= 'position' ) | (enumLiteral_3= 'order' ) | (enumLiteral_4= 'ratio' ) )
            {
            // InternalGRandom.g:1938:2: ( (enumLiteral_0= 'free' ) | (enumLiteral_1= 'side' ) | (enumLiteral_2= 'position' ) | (enumLiteral_3= 'order' ) | (enumLiteral_4= 'ratio' ) )
            int alt23=5;
            switch ( input.LA(1) ) {
            case 56:
                {
                alt23=1;
                }
                break;
            case 57:
                {
                alt23=2;
                }
                break;
            case 58:
                {
                alt23=3;
                }
                break;
            case 59:
                {
                alt23=4;
                }
                break;
            case 60:
                {
                alt23=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }

            switch (alt23) {
                case 1 :
                    // InternalGRandom.g:1939:3: (enumLiteral_0= 'free' )
                    {
                    // InternalGRandom.g:1939:3: (enumLiteral_0= 'free' )
                    // InternalGRandom.g:1940:4: enumLiteral_0= 'free'
                    {
                    enumLiteral_0=(Token)match(input,56,FOLLOW_2); 

                    				current = grammarAccess.getConstraintTypeAccess().getFreeEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getConstraintTypeAccess().getFreeEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalGRandom.g:1947:3: (enumLiteral_1= 'side' )
                    {
                    // InternalGRandom.g:1947:3: (enumLiteral_1= 'side' )
                    // InternalGRandom.g:1948:4: enumLiteral_1= 'side'
                    {
                    enumLiteral_1=(Token)match(input,57,FOLLOW_2); 

                    				current = grammarAccess.getConstraintTypeAccess().getSideEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getConstraintTypeAccess().getSideEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalGRandom.g:1955:3: (enumLiteral_2= 'position' )
                    {
                    // InternalGRandom.g:1955:3: (enumLiteral_2= 'position' )
                    // InternalGRandom.g:1956:4: enumLiteral_2= 'position'
                    {
                    enumLiteral_2=(Token)match(input,58,FOLLOW_2); 

                    				current = grammarAccess.getConstraintTypeAccess().getPositionEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getConstraintTypeAccess().getPositionEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalGRandom.g:1963:3: (enumLiteral_3= 'order' )
                    {
                    // InternalGRandom.g:1963:3: (enumLiteral_3= 'order' )
                    // InternalGRandom.g:1964:4: enumLiteral_3= 'order'
                    {
                    enumLiteral_3=(Token)match(input,59,FOLLOW_2); 

                    				current = grammarAccess.getConstraintTypeAccess().getOrderEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getConstraintTypeAccess().getOrderEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalGRandom.g:1971:3: (enumLiteral_4= 'ratio' )
                    {
                    // InternalGRandom.g:1971:3: (enumLiteral_4= 'ratio' )
                    // InternalGRandom.g:1972:4: enumLiteral_4= 'ratio'
                    {
                    enumLiteral_4=(Token)match(input,60,FOLLOW_2); 

                    				current = grammarAccess.getConstraintTypeAccess().getRatioEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getConstraintTypeAccess().getRatioEnumLiteralDeclaration_4());
                    			

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
    // $ANTLR end "ruleConstraintType"

    // Delegated rules


    protected DFA2 dfa2 = new DFA2(this);
    static final String dfa_1s = "\13\uffff";
    static final String dfa_2s = "\1\15\12\uffff";
    static final String dfa_3s = "\1\41\12\uffff";
    static final String dfa_4s = "\1\uffff\1\12\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11";
    static final String dfa_5s = "\1\0\12\uffff}>";
    static final String[] dfa_6s = {
            "\1\4\1\uffff\1\5\1\6\1\10\1\11\1\12\1\1\1\7\4\uffff\1\3\6\uffff\1\2",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA2 extends DFA {

        public DFA2(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 2;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "()* loopback of 168:7: ( ({...}? => ( ({...}? => ( (lv_nodes_5_0= ruleNodes ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_edges_6_0= ruleEdges ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mW_7_0= 'maxWidth' ) ) otherlv_8= '=' ( (lv_maxWidth_9_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_mD_10_0= 'maxDegree' ) ) otherlv_11= '=' ( (lv_maxDegree_12_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => ( ( (lv_pF_13_0= 'partitionFraction' ) ) otherlv_14= '=' ( (lv_fraction_15_0= ruleDoubleQuantity ) ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_hierarchy_16_0= ruleHierarchy ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_17= 'seed' otherlv_18= '=' ( (lv_seed_19_0= ruleInteger ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_20= 'format' otherlv_21= '=' ( (lv_format_22_0= ruleFormats ) ) ) ) ) ) | ({...}? => ( ({...}? => (otherlv_23= 'filename' otherlv_24= '=' ( (lv_filename_25_0= RULE_STRING ) ) ) ) ) ) )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA2_0 = input.LA(1);

                         
                        int index2_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA2_0==20) ) {s = 1;}

                        else if ( LA2_0 == 33 && getUnorderedGroupHelper().canSelect(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 0) ) {s = 2;}

                        else if ( LA2_0 == 26 && getUnorderedGroupHelper().canSelect(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 1) ) {s = 3;}

                        else if ( LA2_0 == 13 && getUnorderedGroupHelper().canSelect(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 2) ) {s = 4;}

                        else if ( LA2_0 == 15 && getUnorderedGroupHelper().canSelect(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 3) ) {s = 5;}

                        else if ( LA2_0 == 16 && getUnorderedGroupHelper().canSelect(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 4) ) {s = 6;}

                        else if ( LA2_0 == 21 && getUnorderedGroupHelper().canSelect(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 5) ) {s = 7;}

                        else if ( LA2_0 == 17 && getUnorderedGroupHelper().canSelect(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 6) ) {s = 8;}

                        else if ( LA2_0 == 18 && getUnorderedGroupHelper().canSelect(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 7) ) {s = 9;}

                        else if ( LA2_0 == 19 && getUnorderedGroupHelper().canSelect(grammarAccess.getConfigurationAccess().getUnorderedGroup_3_1(), 8) ) {s = 10;}

                         
                        input.seek(index2_0);
                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 2, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0007E00000000000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x00000002043FA000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000180000000000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000003D00000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000078000000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000180100000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000002480100000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000001800100000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x008000E4C0100000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x1F00000000000000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0078000000000000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000040000000002L});

}
