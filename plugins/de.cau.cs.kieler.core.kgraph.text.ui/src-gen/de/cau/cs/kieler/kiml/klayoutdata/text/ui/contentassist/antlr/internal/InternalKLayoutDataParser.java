package de.cau.cs.kieler.kiml.klayoutdata.text.ui.contentassist.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.DFA;
import de.cau.cs.kieler.kiml.klayoutdata.text.services.KLayoutDataGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalKLayoutDataParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'E'", "'e'", "'KShapeLayout'", "'{'", "'}'", "'xpos'", "'ypos'", "'width'", "'height'", "'insets'", "'persistentEntries'", "':'", "','", "'KInsets'", "'top'", "'bottom'", "'left'", "'right'", "'KPoint'", "'x'", "'y'", "'PersistentEntry'", "'key'", "'value'", "'-'", "'.'"
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
    public static final int T__31=31;
    public static final int RULE_STRING=4;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__16=16;
    public static final int T__34=34;
    public static final int T__15=15;
    public static final int T__35=35;
    public static final int T__18=18;
    public static final int T__36=36;
    public static final int T__17=17;
    public static final int T__12=12;
    public static final int T__11=11;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int RULE_INT=6;
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
    public String getGrammarFileName() { return "../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g"; }


     
     	private KLayoutDataGrammarAccess grammarAccess;
     	
        public void setGrammarAccess(KLayoutDataGrammarAccess grammarAccess) {
        	this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected Grammar getGrammar() {
        	return grammarAccess.getGrammar();
        }
        
        @Override
        protected String getValueForTokenName(String tokenName) {
        	return tokenName;
        }




    // $ANTLR start "entryRuleKShapeLayout"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:60:1: entryRuleKShapeLayout : ruleKShapeLayout EOF ;
    public final void entryRuleKShapeLayout() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:61:1: ( ruleKShapeLayout EOF )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:62:1: ruleKShapeLayout EOF
            {
             before(grammarAccess.getKShapeLayoutRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKShapeLayout_in_entryRuleKShapeLayout61);
            ruleKShapeLayout();

            state._fsp--;

             after(grammarAccess.getKShapeLayoutRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKShapeLayout68); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleKShapeLayout"


    // $ANTLR start "ruleKShapeLayout"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:69:1: ruleKShapeLayout : ( ( rule__KShapeLayout__Group__0 ) ) ;
    public final void ruleKShapeLayout() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:73:2: ( ( ( rule__KShapeLayout__Group__0 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:74:1: ( ( rule__KShapeLayout__Group__0 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:74:1: ( ( rule__KShapeLayout__Group__0 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:75:1: ( rule__KShapeLayout__Group__0 )
            {
             before(grammarAccess.getKShapeLayoutAccess().getGroup()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:76:1: ( rule__KShapeLayout__Group__0 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:76:2: rule__KShapeLayout__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__0_in_ruleKShapeLayout94);
            rule__KShapeLayout__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getKShapeLayoutAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleKShapeLayout"


    // $ANTLR start "entryRuleKInsets"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:88:1: entryRuleKInsets : ruleKInsets EOF ;
    public final void entryRuleKInsets() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:89:1: ( ruleKInsets EOF )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:90:1: ruleKInsets EOF
            {
             before(grammarAccess.getKInsetsRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKInsets_in_entryRuleKInsets121);
            ruleKInsets();

            state._fsp--;

             after(grammarAccess.getKInsetsRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKInsets128); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleKInsets"


    // $ANTLR start "ruleKInsets"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:97:1: ruleKInsets : ( ( rule__KInsets__Group__0 ) ) ;
    public final void ruleKInsets() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:101:2: ( ( ( rule__KInsets__Group__0 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:102:1: ( ( rule__KInsets__Group__0 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:102:1: ( ( rule__KInsets__Group__0 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:103:1: ( rule__KInsets__Group__0 )
            {
             before(grammarAccess.getKInsetsAccess().getGroup()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:104:1: ( rule__KInsets__Group__0 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:104:2: rule__KInsets__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__0_in_ruleKInsets154);
            rule__KInsets__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getKInsetsAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleKInsets"


    // $ANTLR start "entryRuleKPoint"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:118:1: entryRuleKPoint : ruleKPoint EOF ;
    public final void entryRuleKPoint() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:119:1: ( ruleKPoint EOF )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:120:1: ruleKPoint EOF
            {
             before(grammarAccess.getKPointRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleKPoint_in_entryRuleKPoint183);
            ruleKPoint();

            state._fsp--;

             after(grammarAccess.getKPointRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleKPoint190); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleKPoint"


    // $ANTLR start "ruleKPoint"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:127:1: ruleKPoint : ( ( rule__KPoint__Group__0 ) ) ;
    public final void ruleKPoint() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:131:2: ( ( ( rule__KPoint__Group__0 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:132:1: ( ( rule__KPoint__Group__0 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:132:1: ( ( rule__KPoint__Group__0 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:133:1: ( rule__KPoint__Group__0 )
            {
             before(grammarAccess.getKPointAccess().getGroup()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:134:1: ( rule__KPoint__Group__0 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:134:2: rule__KPoint__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group__0_in_ruleKPoint216);
            rule__KPoint__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getKPointAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleKPoint"


    // $ANTLR start "entryRulePersistentEntry"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:146:1: entryRulePersistentEntry : rulePersistentEntry EOF ;
    public final void entryRulePersistentEntry() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:147:1: ( rulePersistentEntry EOF )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:148:1: rulePersistentEntry EOF
            {
             before(grammarAccess.getPersistentEntryRule()); 
            pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry243);
            rulePersistentEntry();

            state._fsp--;

             after(grammarAccess.getPersistentEntryRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRulePersistentEntry250); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePersistentEntry"


    // $ANTLR start "rulePersistentEntry"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:155:1: rulePersistentEntry : ( ( rule__PersistentEntry__Group__0 ) ) ;
    public final void rulePersistentEntry() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:159:2: ( ( ( rule__PersistentEntry__Group__0 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:160:1: ( ( rule__PersistentEntry__Group__0 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:160:1: ( ( rule__PersistentEntry__Group__0 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:161:1: ( rule__PersistentEntry__Group__0 )
            {
             before(grammarAccess.getPersistentEntryAccess().getGroup()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:162:1: ( rule__PersistentEntry__Group__0 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:162:2: rule__PersistentEntry__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__Group__0_in_rulePersistentEntry276);
            rule__PersistentEntry__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getPersistentEntryAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePersistentEntry"


    // $ANTLR start "entryRuleEString"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:174:1: entryRuleEString : ruleEString EOF ;
    public final void entryRuleEString() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:175:1: ( ruleEString EOF )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:176:1: ruleEString EOF
            {
             before(grammarAccess.getEStringRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_entryRuleEString303);
            ruleEString();

            state._fsp--;

             after(grammarAccess.getEStringRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEString310); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleEString"


    // $ANTLR start "ruleEString"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:183:1: ruleEString : ( ( rule__EString__Alternatives ) ) ;
    public final void ruleEString() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:187:2: ( ( ( rule__EString__Alternatives ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:188:1: ( ( rule__EString__Alternatives ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:188:1: ( ( rule__EString__Alternatives ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:189:1: ( rule__EString__Alternatives )
            {
             before(grammarAccess.getEStringAccess().getAlternatives()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:190:1: ( rule__EString__Alternatives )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:190:2: rule__EString__Alternatives
            {
            pushFollow(FollowSets000.FOLLOW_rule__EString__Alternatives_in_ruleEString336);
            rule__EString__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getEStringAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEString"


    // $ANTLR start "entryRuleEFloat"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:202:1: entryRuleEFloat : ruleEFloat EOF ;
    public final void entryRuleEFloat() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:203:1: ( ruleEFloat EOF )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:204:1: ruleEFloat EOF
            {
             before(grammarAccess.getEFloatRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_entryRuleEFloat363);
            ruleEFloat();

            state._fsp--;

             after(grammarAccess.getEFloatRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEFloat370); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleEFloat"


    // $ANTLR start "ruleEFloat"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:211:1: ruleEFloat : ( ( rule__EFloat__Group__0 ) ) ;
    public final void ruleEFloat() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:215:2: ( ( ( rule__EFloat__Group__0 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:216:1: ( ( rule__EFloat__Group__0 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:216:1: ( ( rule__EFloat__Group__0 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:217:1: ( rule__EFloat__Group__0 )
            {
             before(grammarAccess.getEFloatAccess().getGroup()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:218:1: ( rule__EFloat__Group__0 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:218:2: rule__EFloat__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group__0_in_ruleEFloat396);
            rule__EFloat__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getEFloatAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEFloat"


    // $ANTLR start "rule__EString__Alternatives"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:230:1: rule__EString__Alternatives : ( ( RULE_STRING ) | ( RULE_ID ) );
    public final void rule__EString__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:234:1: ( ( RULE_STRING ) | ( RULE_ID ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==RULE_STRING) ) {
                alt1=1;
            }
            else if ( (LA1_0==RULE_ID) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:235:1: ( RULE_STRING )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:235:1: ( RULE_STRING )
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:236:1: RULE_STRING
                    {
                     before(grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); 
                    match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_rule__EString__Alternatives432); 
                     after(grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:241:6: ( RULE_ID )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:241:6: ( RULE_ID )
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:242:1: RULE_ID
                    {
                     before(grammarAccess.getEStringAccess().getIDTerminalRuleCall_1()); 
                    match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_rule__EString__Alternatives449); 
                     after(grammarAccess.getEStringAccess().getIDTerminalRuleCall_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EString__Alternatives"


    // $ANTLR start "rule__EFloat__Alternatives_4_0"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:252:1: rule__EFloat__Alternatives_4_0 : ( ( 'E' ) | ( 'e' ) );
    public final void rule__EFloat__Alternatives_4_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:256:1: ( ( 'E' ) | ( 'e' ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==11) ) {
                alt2=1;
            }
            else if ( (LA2_0==12) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:257:1: ( 'E' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:257:1: ( 'E' )
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:258:1: 'E'
                    {
                     before(grammarAccess.getEFloatAccess().getEKeyword_4_0_0()); 
                    match(input,11,FollowSets000.FOLLOW_11_in_rule__EFloat__Alternatives_4_0482); 
                     after(grammarAccess.getEFloatAccess().getEKeyword_4_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:265:6: ( 'e' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:265:6: ( 'e' )
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:266:1: 'e'
                    {
                     before(grammarAccess.getEFloatAccess().getEKeyword_4_0_1()); 
                    match(input,12,FollowSets000.FOLLOW_12_in_rule__EFloat__Alternatives_4_0502); 
                     after(grammarAccess.getEFloatAccess().getEKeyword_4_0_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EFloat__Alternatives_4_0"


    // $ANTLR start "rule__KShapeLayout__Group__0"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:280:1: rule__KShapeLayout__Group__0 : rule__KShapeLayout__Group__0__Impl rule__KShapeLayout__Group__1 ;
    public final void rule__KShapeLayout__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:284:1: ( rule__KShapeLayout__Group__0__Impl rule__KShapeLayout__Group__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:285:2: rule__KShapeLayout__Group__0__Impl rule__KShapeLayout__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__0__Impl_in_rule__KShapeLayout__Group__0534);
            rule__KShapeLayout__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__1_in_rule__KShapeLayout__Group__0537);
            rule__KShapeLayout__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group__0"


    // $ANTLR start "rule__KShapeLayout__Group__0__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:292:1: rule__KShapeLayout__Group__0__Impl : ( () ) ;
    public final void rule__KShapeLayout__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:296:1: ( ( () ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:297:1: ( () )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:297:1: ( () )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:298:1: ()
            {
             before(grammarAccess.getKShapeLayoutAccess().getKShapeLayoutAction_0()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:299:1: ()
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:301:1: 
            {
            }

             after(grammarAccess.getKShapeLayoutAccess().getKShapeLayoutAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group__0__Impl"


    // $ANTLR start "rule__KShapeLayout__Group__1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:311:1: rule__KShapeLayout__Group__1 : rule__KShapeLayout__Group__1__Impl rule__KShapeLayout__Group__2 ;
    public final void rule__KShapeLayout__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:315:1: ( rule__KShapeLayout__Group__1__Impl rule__KShapeLayout__Group__2 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:316:2: rule__KShapeLayout__Group__1__Impl rule__KShapeLayout__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__1__Impl_in_rule__KShapeLayout__Group__1595);
            rule__KShapeLayout__Group__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__2_in_rule__KShapeLayout__Group__1598);
            rule__KShapeLayout__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group__1"


    // $ANTLR start "rule__KShapeLayout__Group__1__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:323:1: rule__KShapeLayout__Group__1__Impl : ( 'KShapeLayout' ) ;
    public final void rule__KShapeLayout__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:327:1: ( ( 'KShapeLayout' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:328:1: ( 'KShapeLayout' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:328:1: ( 'KShapeLayout' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:329:1: 'KShapeLayout'
            {
             before(grammarAccess.getKShapeLayoutAccess().getKShapeLayoutKeyword_1()); 
            match(input,13,FollowSets000.FOLLOW_13_in_rule__KShapeLayout__Group__1__Impl626); 
             after(grammarAccess.getKShapeLayoutAccess().getKShapeLayoutKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group__1__Impl"


    // $ANTLR start "rule__KShapeLayout__Group__2"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:342:1: rule__KShapeLayout__Group__2 : rule__KShapeLayout__Group__2__Impl rule__KShapeLayout__Group__3 ;
    public final void rule__KShapeLayout__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:346:1: ( rule__KShapeLayout__Group__2__Impl rule__KShapeLayout__Group__3 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:347:2: rule__KShapeLayout__Group__2__Impl rule__KShapeLayout__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__2__Impl_in_rule__KShapeLayout__Group__2657);
            rule__KShapeLayout__Group__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__3_in_rule__KShapeLayout__Group__2660);
            rule__KShapeLayout__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group__2"


    // $ANTLR start "rule__KShapeLayout__Group__2__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:354:1: rule__KShapeLayout__Group__2__Impl : ( '{' ) ;
    public final void rule__KShapeLayout__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:358:1: ( ( '{' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:359:1: ( '{' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:359:1: ( '{' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:360:1: '{'
            {
             before(grammarAccess.getKShapeLayoutAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,14,FollowSets000.FOLLOW_14_in_rule__KShapeLayout__Group__2__Impl688); 
             after(grammarAccess.getKShapeLayoutAccess().getLeftCurlyBracketKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group__2__Impl"


    // $ANTLR start "rule__KShapeLayout__Group__3"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:373:1: rule__KShapeLayout__Group__3 : rule__KShapeLayout__Group__3__Impl rule__KShapeLayout__Group__4 ;
    public final void rule__KShapeLayout__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:377:1: ( rule__KShapeLayout__Group__3__Impl rule__KShapeLayout__Group__4 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:378:2: rule__KShapeLayout__Group__3__Impl rule__KShapeLayout__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__3__Impl_in_rule__KShapeLayout__Group__3719);
            rule__KShapeLayout__Group__3__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__4_in_rule__KShapeLayout__Group__3722);
            rule__KShapeLayout__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group__3"


    // $ANTLR start "rule__KShapeLayout__Group__3__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:385:1: rule__KShapeLayout__Group__3__Impl : ( ( rule__KShapeLayout__Group_3__0 )? ) ;
    public final void rule__KShapeLayout__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:389:1: ( ( ( rule__KShapeLayout__Group_3__0 )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:390:1: ( ( rule__KShapeLayout__Group_3__0 )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:390:1: ( ( rule__KShapeLayout__Group_3__0 )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:391:1: ( rule__KShapeLayout__Group_3__0 )?
            {
             before(grammarAccess.getKShapeLayoutAccess().getGroup_3()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:392:1: ( rule__KShapeLayout__Group_3__0 )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==16) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:392:2: rule__KShapeLayout__Group_3__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_3__0_in_rule__KShapeLayout__Group__3__Impl749);
                    rule__KShapeLayout__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getKShapeLayoutAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group__3__Impl"


    // $ANTLR start "rule__KShapeLayout__Group__4"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:402:1: rule__KShapeLayout__Group__4 : rule__KShapeLayout__Group__4__Impl rule__KShapeLayout__Group__5 ;
    public final void rule__KShapeLayout__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:406:1: ( rule__KShapeLayout__Group__4__Impl rule__KShapeLayout__Group__5 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:407:2: rule__KShapeLayout__Group__4__Impl rule__KShapeLayout__Group__5
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__4__Impl_in_rule__KShapeLayout__Group__4780);
            rule__KShapeLayout__Group__4__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__5_in_rule__KShapeLayout__Group__4783);
            rule__KShapeLayout__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group__4"


    // $ANTLR start "rule__KShapeLayout__Group__4__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:414:1: rule__KShapeLayout__Group__4__Impl : ( ( rule__KShapeLayout__Group_4__0 )? ) ;
    public final void rule__KShapeLayout__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:418:1: ( ( ( rule__KShapeLayout__Group_4__0 )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:419:1: ( ( rule__KShapeLayout__Group_4__0 )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:419:1: ( ( rule__KShapeLayout__Group_4__0 )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:420:1: ( rule__KShapeLayout__Group_4__0 )?
            {
             before(grammarAccess.getKShapeLayoutAccess().getGroup_4()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:421:1: ( rule__KShapeLayout__Group_4__0 )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==17) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:421:2: rule__KShapeLayout__Group_4__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_4__0_in_rule__KShapeLayout__Group__4__Impl810);
                    rule__KShapeLayout__Group_4__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getKShapeLayoutAccess().getGroup_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group__4__Impl"


    // $ANTLR start "rule__KShapeLayout__Group__5"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:431:1: rule__KShapeLayout__Group__5 : rule__KShapeLayout__Group__5__Impl rule__KShapeLayout__Group__6 ;
    public final void rule__KShapeLayout__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:435:1: ( rule__KShapeLayout__Group__5__Impl rule__KShapeLayout__Group__6 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:436:2: rule__KShapeLayout__Group__5__Impl rule__KShapeLayout__Group__6
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__5__Impl_in_rule__KShapeLayout__Group__5841);
            rule__KShapeLayout__Group__5__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__6_in_rule__KShapeLayout__Group__5844);
            rule__KShapeLayout__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group__5"


    // $ANTLR start "rule__KShapeLayout__Group__5__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:443:1: rule__KShapeLayout__Group__5__Impl : ( ( rule__KShapeLayout__Group_5__0 )? ) ;
    public final void rule__KShapeLayout__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:447:1: ( ( ( rule__KShapeLayout__Group_5__0 )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:448:1: ( ( rule__KShapeLayout__Group_5__0 )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:448:1: ( ( rule__KShapeLayout__Group_5__0 )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:449:1: ( rule__KShapeLayout__Group_5__0 )?
            {
             before(grammarAccess.getKShapeLayoutAccess().getGroup_5()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:450:1: ( rule__KShapeLayout__Group_5__0 )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==18) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:450:2: rule__KShapeLayout__Group_5__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_5__0_in_rule__KShapeLayout__Group__5__Impl871);
                    rule__KShapeLayout__Group_5__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getKShapeLayoutAccess().getGroup_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group__5__Impl"


    // $ANTLR start "rule__KShapeLayout__Group__6"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:460:1: rule__KShapeLayout__Group__6 : rule__KShapeLayout__Group__6__Impl rule__KShapeLayout__Group__7 ;
    public final void rule__KShapeLayout__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:464:1: ( rule__KShapeLayout__Group__6__Impl rule__KShapeLayout__Group__7 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:465:2: rule__KShapeLayout__Group__6__Impl rule__KShapeLayout__Group__7
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__6__Impl_in_rule__KShapeLayout__Group__6902);
            rule__KShapeLayout__Group__6__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__7_in_rule__KShapeLayout__Group__6905);
            rule__KShapeLayout__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group__6"


    // $ANTLR start "rule__KShapeLayout__Group__6__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:472:1: rule__KShapeLayout__Group__6__Impl : ( ( rule__KShapeLayout__Group_6__0 )? ) ;
    public final void rule__KShapeLayout__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:476:1: ( ( ( rule__KShapeLayout__Group_6__0 )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:477:1: ( ( rule__KShapeLayout__Group_6__0 )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:477:1: ( ( rule__KShapeLayout__Group_6__0 )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:478:1: ( rule__KShapeLayout__Group_6__0 )?
            {
             before(grammarAccess.getKShapeLayoutAccess().getGroup_6()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:479:1: ( rule__KShapeLayout__Group_6__0 )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==19) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:479:2: rule__KShapeLayout__Group_6__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_6__0_in_rule__KShapeLayout__Group__6__Impl932);
                    rule__KShapeLayout__Group_6__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getKShapeLayoutAccess().getGroup_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group__6__Impl"


    // $ANTLR start "rule__KShapeLayout__Group__7"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:489:1: rule__KShapeLayout__Group__7 : rule__KShapeLayout__Group__7__Impl rule__KShapeLayout__Group__8 ;
    public final void rule__KShapeLayout__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:493:1: ( rule__KShapeLayout__Group__7__Impl rule__KShapeLayout__Group__8 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:494:2: rule__KShapeLayout__Group__7__Impl rule__KShapeLayout__Group__8
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__7__Impl_in_rule__KShapeLayout__Group__7963);
            rule__KShapeLayout__Group__7__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__8_in_rule__KShapeLayout__Group__7966);
            rule__KShapeLayout__Group__8();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group__7"


    // $ANTLR start "rule__KShapeLayout__Group__7__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:501:1: rule__KShapeLayout__Group__7__Impl : ( ( rule__KShapeLayout__Group_7__0 )? ) ;
    public final void rule__KShapeLayout__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:505:1: ( ( ( rule__KShapeLayout__Group_7__0 )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:506:1: ( ( rule__KShapeLayout__Group_7__0 )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:506:1: ( ( rule__KShapeLayout__Group_7__0 )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:507:1: ( rule__KShapeLayout__Group_7__0 )?
            {
             before(grammarAccess.getKShapeLayoutAccess().getGroup_7()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:508:1: ( rule__KShapeLayout__Group_7__0 )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==20) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:508:2: rule__KShapeLayout__Group_7__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_7__0_in_rule__KShapeLayout__Group__7__Impl993);
                    rule__KShapeLayout__Group_7__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getKShapeLayoutAccess().getGroup_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group__7__Impl"


    // $ANTLR start "rule__KShapeLayout__Group__8"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:518:1: rule__KShapeLayout__Group__8 : rule__KShapeLayout__Group__8__Impl rule__KShapeLayout__Group__9 ;
    public final void rule__KShapeLayout__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:522:1: ( rule__KShapeLayout__Group__8__Impl rule__KShapeLayout__Group__9 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:523:2: rule__KShapeLayout__Group__8__Impl rule__KShapeLayout__Group__9
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__8__Impl_in_rule__KShapeLayout__Group__81024);
            rule__KShapeLayout__Group__8__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__9_in_rule__KShapeLayout__Group__81027);
            rule__KShapeLayout__Group__9();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group__8"


    // $ANTLR start "rule__KShapeLayout__Group__8__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:530:1: rule__KShapeLayout__Group__8__Impl : ( ( rule__KShapeLayout__Group_8__0 )? ) ;
    public final void rule__KShapeLayout__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:534:1: ( ( ( rule__KShapeLayout__Group_8__0 )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:535:1: ( ( rule__KShapeLayout__Group_8__0 )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:535:1: ( ( rule__KShapeLayout__Group_8__0 )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:536:1: ( rule__KShapeLayout__Group_8__0 )?
            {
             before(grammarAccess.getKShapeLayoutAccess().getGroup_8()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:537:1: ( rule__KShapeLayout__Group_8__0 )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==21) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:537:2: rule__KShapeLayout__Group_8__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_8__0_in_rule__KShapeLayout__Group__8__Impl1054);
                    rule__KShapeLayout__Group_8__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getKShapeLayoutAccess().getGroup_8()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group__8__Impl"


    // $ANTLR start "rule__KShapeLayout__Group__9"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:547:1: rule__KShapeLayout__Group__9 : rule__KShapeLayout__Group__9__Impl ;
    public final void rule__KShapeLayout__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:551:1: ( rule__KShapeLayout__Group__9__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:552:2: rule__KShapeLayout__Group__9__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__9__Impl_in_rule__KShapeLayout__Group__91085);
            rule__KShapeLayout__Group__9__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group__9"


    // $ANTLR start "rule__KShapeLayout__Group__9__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:558:1: rule__KShapeLayout__Group__9__Impl : ( '}' ) ;
    public final void rule__KShapeLayout__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:562:1: ( ( '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:563:1: ( '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:563:1: ( '}' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:564:1: '}'
            {
             before(grammarAccess.getKShapeLayoutAccess().getRightCurlyBracketKeyword_9()); 
            match(input,15,FollowSets000.FOLLOW_15_in_rule__KShapeLayout__Group__9__Impl1113); 
             after(grammarAccess.getKShapeLayoutAccess().getRightCurlyBracketKeyword_9()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group__9__Impl"


    // $ANTLR start "rule__KShapeLayout__Group_3__0"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:597:1: rule__KShapeLayout__Group_3__0 : rule__KShapeLayout__Group_3__0__Impl rule__KShapeLayout__Group_3__1 ;
    public final void rule__KShapeLayout__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:601:1: ( rule__KShapeLayout__Group_3__0__Impl rule__KShapeLayout__Group_3__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:602:2: rule__KShapeLayout__Group_3__0__Impl rule__KShapeLayout__Group_3__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_3__0__Impl_in_rule__KShapeLayout__Group_3__01164);
            rule__KShapeLayout__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_3__1_in_rule__KShapeLayout__Group_3__01167);
            rule__KShapeLayout__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_3__0"


    // $ANTLR start "rule__KShapeLayout__Group_3__0__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:609:1: rule__KShapeLayout__Group_3__0__Impl : ( 'xpos' ) ;
    public final void rule__KShapeLayout__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:613:1: ( ( 'xpos' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:614:1: ( 'xpos' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:614:1: ( 'xpos' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:615:1: 'xpos'
            {
             before(grammarAccess.getKShapeLayoutAccess().getXposKeyword_3_0()); 
            match(input,16,FollowSets000.FOLLOW_16_in_rule__KShapeLayout__Group_3__0__Impl1195); 
             after(grammarAccess.getKShapeLayoutAccess().getXposKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_3__0__Impl"


    // $ANTLR start "rule__KShapeLayout__Group_3__1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:628:1: rule__KShapeLayout__Group_3__1 : rule__KShapeLayout__Group_3__1__Impl ;
    public final void rule__KShapeLayout__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:632:1: ( rule__KShapeLayout__Group_3__1__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:633:2: rule__KShapeLayout__Group_3__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_3__1__Impl_in_rule__KShapeLayout__Group_3__11226);
            rule__KShapeLayout__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_3__1"


    // $ANTLR start "rule__KShapeLayout__Group_3__1__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:639:1: rule__KShapeLayout__Group_3__1__Impl : ( ( rule__KShapeLayout__XposAssignment_3_1 ) ) ;
    public final void rule__KShapeLayout__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:643:1: ( ( ( rule__KShapeLayout__XposAssignment_3_1 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:644:1: ( ( rule__KShapeLayout__XposAssignment_3_1 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:644:1: ( ( rule__KShapeLayout__XposAssignment_3_1 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:645:1: ( rule__KShapeLayout__XposAssignment_3_1 )
            {
             before(grammarAccess.getKShapeLayoutAccess().getXposAssignment_3_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:646:1: ( rule__KShapeLayout__XposAssignment_3_1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:646:2: rule__KShapeLayout__XposAssignment_3_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__XposAssignment_3_1_in_rule__KShapeLayout__Group_3__1__Impl1253);
            rule__KShapeLayout__XposAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getKShapeLayoutAccess().getXposAssignment_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_3__1__Impl"


    // $ANTLR start "rule__KShapeLayout__Group_4__0"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:660:1: rule__KShapeLayout__Group_4__0 : rule__KShapeLayout__Group_4__0__Impl rule__KShapeLayout__Group_4__1 ;
    public final void rule__KShapeLayout__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:664:1: ( rule__KShapeLayout__Group_4__0__Impl rule__KShapeLayout__Group_4__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:665:2: rule__KShapeLayout__Group_4__0__Impl rule__KShapeLayout__Group_4__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_4__0__Impl_in_rule__KShapeLayout__Group_4__01287);
            rule__KShapeLayout__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_4__1_in_rule__KShapeLayout__Group_4__01290);
            rule__KShapeLayout__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_4__0"


    // $ANTLR start "rule__KShapeLayout__Group_4__0__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:672:1: rule__KShapeLayout__Group_4__0__Impl : ( 'ypos' ) ;
    public final void rule__KShapeLayout__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:676:1: ( ( 'ypos' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:677:1: ( 'ypos' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:677:1: ( 'ypos' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:678:1: 'ypos'
            {
             before(grammarAccess.getKShapeLayoutAccess().getYposKeyword_4_0()); 
            match(input,17,FollowSets000.FOLLOW_17_in_rule__KShapeLayout__Group_4__0__Impl1318); 
             after(grammarAccess.getKShapeLayoutAccess().getYposKeyword_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_4__0__Impl"


    // $ANTLR start "rule__KShapeLayout__Group_4__1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:691:1: rule__KShapeLayout__Group_4__1 : rule__KShapeLayout__Group_4__1__Impl ;
    public final void rule__KShapeLayout__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:695:1: ( rule__KShapeLayout__Group_4__1__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:696:2: rule__KShapeLayout__Group_4__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_4__1__Impl_in_rule__KShapeLayout__Group_4__11349);
            rule__KShapeLayout__Group_4__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_4__1"


    // $ANTLR start "rule__KShapeLayout__Group_4__1__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:702:1: rule__KShapeLayout__Group_4__1__Impl : ( ( rule__KShapeLayout__YposAssignment_4_1 ) ) ;
    public final void rule__KShapeLayout__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:706:1: ( ( ( rule__KShapeLayout__YposAssignment_4_1 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:707:1: ( ( rule__KShapeLayout__YposAssignment_4_1 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:707:1: ( ( rule__KShapeLayout__YposAssignment_4_1 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:708:1: ( rule__KShapeLayout__YposAssignment_4_1 )
            {
             before(grammarAccess.getKShapeLayoutAccess().getYposAssignment_4_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:709:1: ( rule__KShapeLayout__YposAssignment_4_1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:709:2: rule__KShapeLayout__YposAssignment_4_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__YposAssignment_4_1_in_rule__KShapeLayout__Group_4__1__Impl1376);
            rule__KShapeLayout__YposAssignment_4_1();

            state._fsp--;


            }

             after(grammarAccess.getKShapeLayoutAccess().getYposAssignment_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_4__1__Impl"


    // $ANTLR start "rule__KShapeLayout__Group_5__0"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:723:1: rule__KShapeLayout__Group_5__0 : rule__KShapeLayout__Group_5__0__Impl rule__KShapeLayout__Group_5__1 ;
    public final void rule__KShapeLayout__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:727:1: ( rule__KShapeLayout__Group_5__0__Impl rule__KShapeLayout__Group_5__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:728:2: rule__KShapeLayout__Group_5__0__Impl rule__KShapeLayout__Group_5__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_5__0__Impl_in_rule__KShapeLayout__Group_5__01410);
            rule__KShapeLayout__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_5__1_in_rule__KShapeLayout__Group_5__01413);
            rule__KShapeLayout__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_5__0"


    // $ANTLR start "rule__KShapeLayout__Group_5__0__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:735:1: rule__KShapeLayout__Group_5__0__Impl : ( 'width' ) ;
    public final void rule__KShapeLayout__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:739:1: ( ( 'width' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:740:1: ( 'width' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:740:1: ( 'width' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:741:1: 'width'
            {
             before(grammarAccess.getKShapeLayoutAccess().getWidthKeyword_5_0()); 
            match(input,18,FollowSets000.FOLLOW_18_in_rule__KShapeLayout__Group_5__0__Impl1441); 
             after(grammarAccess.getKShapeLayoutAccess().getWidthKeyword_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_5__0__Impl"


    // $ANTLR start "rule__KShapeLayout__Group_5__1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:754:1: rule__KShapeLayout__Group_5__1 : rule__KShapeLayout__Group_5__1__Impl ;
    public final void rule__KShapeLayout__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:758:1: ( rule__KShapeLayout__Group_5__1__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:759:2: rule__KShapeLayout__Group_5__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_5__1__Impl_in_rule__KShapeLayout__Group_5__11472);
            rule__KShapeLayout__Group_5__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_5__1"


    // $ANTLR start "rule__KShapeLayout__Group_5__1__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:765:1: rule__KShapeLayout__Group_5__1__Impl : ( ( rule__KShapeLayout__WidthAssignment_5_1 ) ) ;
    public final void rule__KShapeLayout__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:769:1: ( ( ( rule__KShapeLayout__WidthAssignment_5_1 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:770:1: ( ( rule__KShapeLayout__WidthAssignment_5_1 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:770:1: ( ( rule__KShapeLayout__WidthAssignment_5_1 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:771:1: ( rule__KShapeLayout__WidthAssignment_5_1 )
            {
             before(grammarAccess.getKShapeLayoutAccess().getWidthAssignment_5_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:772:1: ( rule__KShapeLayout__WidthAssignment_5_1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:772:2: rule__KShapeLayout__WidthAssignment_5_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__WidthAssignment_5_1_in_rule__KShapeLayout__Group_5__1__Impl1499);
            rule__KShapeLayout__WidthAssignment_5_1();

            state._fsp--;


            }

             after(grammarAccess.getKShapeLayoutAccess().getWidthAssignment_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_5__1__Impl"


    // $ANTLR start "rule__KShapeLayout__Group_6__0"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:786:1: rule__KShapeLayout__Group_6__0 : rule__KShapeLayout__Group_6__0__Impl rule__KShapeLayout__Group_6__1 ;
    public final void rule__KShapeLayout__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:790:1: ( rule__KShapeLayout__Group_6__0__Impl rule__KShapeLayout__Group_6__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:791:2: rule__KShapeLayout__Group_6__0__Impl rule__KShapeLayout__Group_6__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_6__0__Impl_in_rule__KShapeLayout__Group_6__01533);
            rule__KShapeLayout__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_6__1_in_rule__KShapeLayout__Group_6__01536);
            rule__KShapeLayout__Group_6__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_6__0"


    // $ANTLR start "rule__KShapeLayout__Group_6__0__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:798:1: rule__KShapeLayout__Group_6__0__Impl : ( 'height' ) ;
    public final void rule__KShapeLayout__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:802:1: ( ( 'height' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:803:1: ( 'height' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:803:1: ( 'height' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:804:1: 'height'
            {
             before(grammarAccess.getKShapeLayoutAccess().getHeightKeyword_6_0()); 
            match(input,19,FollowSets000.FOLLOW_19_in_rule__KShapeLayout__Group_6__0__Impl1564); 
             after(grammarAccess.getKShapeLayoutAccess().getHeightKeyword_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_6__0__Impl"


    // $ANTLR start "rule__KShapeLayout__Group_6__1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:817:1: rule__KShapeLayout__Group_6__1 : rule__KShapeLayout__Group_6__1__Impl ;
    public final void rule__KShapeLayout__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:821:1: ( rule__KShapeLayout__Group_6__1__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:822:2: rule__KShapeLayout__Group_6__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_6__1__Impl_in_rule__KShapeLayout__Group_6__11595);
            rule__KShapeLayout__Group_6__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_6__1"


    // $ANTLR start "rule__KShapeLayout__Group_6__1__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:828:1: rule__KShapeLayout__Group_6__1__Impl : ( ( rule__KShapeLayout__HeightAssignment_6_1 ) ) ;
    public final void rule__KShapeLayout__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:832:1: ( ( ( rule__KShapeLayout__HeightAssignment_6_1 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:833:1: ( ( rule__KShapeLayout__HeightAssignment_6_1 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:833:1: ( ( rule__KShapeLayout__HeightAssignment_6_1 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:834:1: ( rule__KShapeLayout__HeightAssignment_6_1 )
            {
             before(grammarAccess.getKShapeLayoutAccess().getHeightAssignment_6_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:835:1: ( rule__KShapeLayout__HeightAssignment_6_1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:835:2: rule__KShapeLayout__HeightAssignment_6_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__HeightAssignment_6_1_in_rule__KShapeLayout__Group_6__1__Impl1622);
            rule__KShapeLayout__HeightAssignment_6_1();

            state._fsp--;


            }

             after(grammarAccess.getKShapeLayoutAccess().getHeightAssignment_6_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_6__1__Impl"


    // $ANTLR start "rule__KShapeLayout__Group_7__0"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:849:1: rule__KShapeLayout__Group_7__0 : rule__KShapeLayout__Group_7__0__Impl rule__KShapeLayout__Group_7__1 ;
    public final void rule__KShapeLayout__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:853:1: ( rule__KShapeLayout__Group_7__0__Impl rule__KShapeLayout__Group_7__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:854:2: rule__KShapeLayout__Group_7__0__Impl rule__KShapeLayout__Group_7__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_7__0__Impl_in_rule__KShapeLayout__Group_7__01656);
            rule__KShapeLayout__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_7__1_in_rule__KShapeLayout__Group_7__01659);
            rule__KShapeLayout__Group_7__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_7__0"


    // $ANTLR start "rule__KShapeLayout__Group_7__0__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:861:1: rule__KShapeLayout__Group_7__0__Impl : ( 'insets' ) ;
    public final void rule__KShapeLayout__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:865:1: ( ( 'insets' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:866:1: ( 'insets' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:866:1: ( 'insets' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:867:1: 'insets'
            {
             before(grammarAccess.getKShapeLayoutAccess().getInsetsKeyword_7_0()); 
            match(input,20,FollowSets000.FOLLOW_20_in_rule__KShapeLayout__Group_7__0__Impl1687); 
             after(grammarAccess.getKShapeLayoutAccess().getInsetsKeyword_7_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_7__0__Impl"


    // $ANTLR start "rule__KShapeLayout__Group_7__1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:880:1: rule__KShapeLayout__Group_7__1 : rule__KShapeLayout__Group_7__1__Impl ;
    public final void rule__KShapeLayout__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:884:1: ( rule__KShapeLayout__Group_7__1__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:885:2: rule__KShapeLayout__Group_7__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_7__1__Impl_in_rule__KShapeLayout__Group_7__11718);
            rule__KShapeLayout__Group_7__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_7__1"


    // $ANTLR start "rule__KShapeLayout__Group_7__1__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:891:1: rule__KShapeLayout__Group_7__1__Impl : ( ( rule__KShapeLayout__InsetsAssignment_7_1 ) ) ;
    public final void rule__KShapeLayout__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:895:1: ( ( ( rule__KShapeLayout__InsetsAssignment_7_1 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:896:1: ( ( rule__KShapeLayout__InsetsAssignment_7_1 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:896:1: ( ( rule__KShapeLayout__InsetsAssignment_7_1 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:897:1: ( rule__KShapeLayout__InsetsAssignment_7_1 )
            {
             before(grammarAccess.getKShapeLayoutAccess().getInsetsAssignment_7_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:898:1: ( rule__KShapeLayout__InsetsAssignment_7_1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:898:2: rule__KShapeLayout__InsetsAssignment_7_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__InsetsAssignment_7_1_in_rule__KShapeLayout__Group_7__1__Impl1745);
            rule__KShapeLayout__InsetsAssignment_7_1();

            state._fsp--;


            }

             after(grammarAccess.getKShapeLayoutAccess().getInsetsAssignment_7_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_7__1__Impl"


    // $ANTLR start "rule__KShapeLayout__Group_8__0"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:912:1: rule__KShapeLayout__Group_8__0 : rule__KShapeLayout__Group_8__0__Impl rule__KShapeLayout__Group_8__1 ;
    public final void rule__KShapeLayout__Group_8__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:916:1: ( rule__KShapeLayout__Group_8__0__Impl rule__KShapeLayout__Group_8__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:917:2: rule__KShapeLayout__Group_8__0__Impl rule__KShapeLayout__Group_8__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_8__0__Impl_in_rule__KShapeLayout__Group_8__01779);
            rule__KShapeLayout__Group_8__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_8__1_in_rule__KShapeLayout__Group_8__01782);
            rule__KShapeLayout__Group_8__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_8__0"


    // $ANTLR start "rule__KShapeLayout__Group_8__0__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:924:1: rule__KShapeLayout__Group_8__0__Impl : ( 'persistentEntries' ) ;
    public final void rule__KShapeLayout__Group_8__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:928:1: ( ( 'persistentEntries' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:929:1: ( 'persistentEntries' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:929:1: ( 'persistentEntries' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:930:1: 'persistentEntries'
            {
             before(grammarAccess.getKShapeLayoutAccess().getPersistentEntriesKeyword_8_0()); 
            match(input,21,FollowSets000.FOLLOW_21_in_rule__KShapeLayout__Group_8__0__Impl1810); 
             after(grammarAccess.getKShapeLayoutAccess().getPersistentEntriesKeyword_8_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_8__0__Impl"


    // $ANTLR start "rule__KShapeLayout__Group_8__1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:943:1: rule__KShapeLayout__Group_8__1 : rule__KShapeLayout__Group_8__1__Impl rule__KShapeLayout__Group_8__2 ;
    public final void rule__KShapeLayout__Group_8__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:947:1: ( rule__KShapeLayout__Group_8__1__Impl rule__KShapeLayout__Group_8__2 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:948:2: rule__KShapeLayout__Group_8__1__Impl rule__KShapeLayout__Group_8__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_8__1__Impl_in_rule__KShapeLayout__Group_8__11841);
            rule__KShapeLayout__Group_8__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_8__2_in_rule__KShapeLayout__Group_8__11844);
            rule__KShapeLayout__Group_8__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_8__1"


    // $ANTLR start "rule__KShapeLayout__Group_8__1__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:955:1: rule__KShapeLayout__Group_8__1__Impl : ( ':' ) ;
    public final void rule__KShapeLayout__Group_8__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:959:1: ( ( ':' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:960:1: ( ':' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:960:1: ( ':' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:961:1: ':'
            {
             before(grammarAccess.getKShapeLayoutAccess().getColonKeyword_8_1()); 
            match(input,22,FollowSets000.FOLLOW_22_in_rule__KShapeLayout__Group_8__1__Impl1872); 
             after(grammarAccess.getKShapeLayoutAccess().getColonKeyword_8_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_8__1__Impl"


    // $ANTLR start "rule__KShapeLayout__Group_8__2"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:974:1: rule__KShapeLayout__Group_8__2 : rule__KShapeLayout__Group_8__2__Impl rule__KShapeLayout__Group_8__3 ;
    public final void rule__KShapeLayout__Group_8__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:978:1: ( rule__KShapeLayout__Group_8__2__Impl rule__KShapeLayout__Group_8__3 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:979:2: rule__KShapeLayout__Group_8__2__Impl rule__KShapeLayout__Group_8__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_8__2__Impl_in_rule__KShapeLayout__Group_8__21903);
            rule__KShapeLayout__Group_8__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_8__3_in_rule__KShapeLayout__Group_8__21906);
            rule__KShapeLayout__Group_8__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_8__2"


    // $ANTLR start "rule__KShapeLayout__Group_8__2__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:986:1: rule__KShapeLayout__Group_8__2__Impl : ( ( rule__KShapeLayout__PersistentEntriesAssignment_8_2 ) ) ;
    public final void rule__KShapeLayout__Group_8__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:990:1: ( ( ( rule__KShapeLayout__PersistentEntriesAssignment_8_2 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:991:1: ( ( rule__KShapeLayout__PersistentEntriesAssignment_8_2 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:991:1: ( ( rule__KShapeLayout__PersistentEntriesAssignment_8_2 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:992:1: ( rule__KShapeLayout__PersistentEntriesAssignment_8_2 )
            {
             before(grammarAccess.getKShapeLayoutAccess().getPersistentEntriesAssignment_8_2()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:993:1: ( rule__KShapeLayout__PersistentEntriesAssignment_8_2 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:993:2: rule__KShapeLayout__PersistentEntriesAssignment_8_2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__PersistentEntriesAssignment_8_2_in_rule__KShapeLayout__Group_8__2__Impl1933);
            rule__KShapeLayout__PersistentEntriesAssignment_8_2();

            state._fsp--;


            }

             after(grammarAccess.getKShapeLayoutAccess().getPersistentEntriesAssignment_8_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_8__2__Impl"


    // $ANTLR start "rule__KShapeLayout__Group_8__3"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1003:1: rule__KShapeLayout__Group_8__3 : rule__KShapeLayout__Group_8__3__Impl ;
    public final void rule__KShapeLayout__Group_8__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1007:1: ( rule__KShapeLayout__Group_8__3__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1008:2: rule__KShapeLayout__Group_8__3__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_8__3__Impl_in_rule__KShapeLayout__Group_8__31963);
            rule__KShapeLayout__Group_8__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_8__3"


    // $ANTLR start "rule__KShapeLayout__Group_8__3__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1014:1: rule__KShapeLayout__Group_8__3__Impl : ( ( rule__KShapeLayout__Group_8_3__0 )* ) ;
    public final void rule__KShapeLayout__Group_8__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1018:1: ( ( ( rule__KShapeLayout__Group_8_3__0 )* ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1019:1: ( ( rule__KShapeLayout__Group_8_3__0 )* )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1019:1: ( ( rule__KShapeLayout__Group_8_3__0 )* )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1020:1: ( rule__KShapeLayout__Group_8_3__0 )*
            {
             before(grammarAccess.getKShapeLayoutAccess().getGroup_8_3()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1021:1: ( rule__KShapeLayout__Group_8_3__0 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==23) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1021:2: rule__KShapeLayout__Group_8_3__0
            	    {
            	    pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_8_3__0_in_rule__KShapeLayout__Group_8__3__Impl1990);
            	    rule__KShapeLayout__Group_8_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

             after(grammarAccess.getKShapeLayoutAccess().getGroup_8_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_8__3__Impl"


    // $ANTLR start "rule__KShapeLayout__Group_8_3__0"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1039:1: rule__KShapeLayout__Group_8_3__0 : rule__KShapeLayout__Group_8_3__0__Impl rule__KShapeLayout__Group_8_3__1 ;
    public final void rule__KShapeLayout__Group_8_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1043:1: ( rule__KShapeLayout__Group_8_3__0__Impl rule__KShapeLayout__Group_8_3__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1044:2: rule__KShapeLayout__Group_8_3__0__Impl rule__KShapeLayout__Group_8_3__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_8_3__0__Impl_in_rule__KShapeLayout__Group_8_3__02029);
            rule__KShapeLayout__Group_8_3__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_8_3__1_in_rule__KShapeLayout__Group_8_3__02032);
            rule__KShapeLayout__Group_8_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_8_3__0"


    // $ANTLR start "rule__KShapeLayout__Group_8_3__0__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1051:1: rule__KShapeLayout__Group_8_3__0__Impl : ( ',' ) ;
    public final void rule__KShapeLayout__Group_8_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1055:1: ( ( ',' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1056:1: ( ',' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1056:1: ( ',' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1057:1: ','
            {
             before(grammarAccess.getKShapeLayoutAccess().getCommaKeyword_8_3_0()); 
            match(input,23,FollowSets000.FOLLOW_23_in_rule__KShapeLayout__Group_8_3__0__Impl2060); 
             after(grammarAccess.getKShapeLayoutAccess().getCommaKeyword_8_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_8_3__0__Impl"


    // $ANTLR start "rule__KShapeLayout__Group_8_3__1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1070:1: rule__KShapeLayout__Group_8_3__1 : rule__KShapeLayout__Group_8_3__1__Impl ;
    public final void rule__KShapeLayout__Group_8_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1074:1: ( rule__KShapeLayout__Group_8_3__1__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1075:2: rule__KShapeLayout__Group_8_3__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_8_3__1__Impl_in_rule__KShapeLayout__Group_8_3__12091);
            rule__KShapeLayout__Group_8_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_8_3__1"


    // $ANTLR start "rule__KShapeLayout__Group_8_3__1__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1081:1: rule__KShapeLayout__Group_8_3__1__Impl : ( ( rule__KShapeLayout__PersistentEntriesAssignment_8_3_1 ) ) ;
    public final void rule__KShapeLayout__Group_8_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1085:1: ( ( ( rule__KShapeLayout__PersistentEntriesAssignment_8_3_1 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1086:1: ( ( rule__KShapeLayout__PersistentEntriesAssignment_8_3_1 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1086:1: ( ( rule__KShapeLayout__PersistentEntriesAssignment_8_3_1 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1087:1: ( rule__KShapeLayout__PersistentEntriesAssignment_8_3_1 )
            {
             before(grammarAccess.getKShapeLayoutAccess().getPersistentEntriesAssignment_8_3_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1088:1: ( rule__KShapeLayout__PersistentEntriesAssignment_8_3_1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1088:2: rule__KShapeLayout__PersistentEntriesAssignment_8_3_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__PersistentEntriesAssignment_8_3_1_in_rule__KShapeLayout__Group_8_3__1__Impl2118);
            rule__KShapeLayout__PersistentEntriesAssignment_8_3_1();

            state._fsp--;


            }

             after(grammarAccess.getKShapeLayoutAccess().getPersistentEntriesAssignment_8_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__Group_8_3__1__Impl"


    // $ANTLR start "rule__KInsets__Group__0"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1102:1: rule__KInsets__Group__0 : rule__KInsets__Group__0__Impl rule__KInsets__Group__1 ;
    public final void rule__KInsets__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1106:1: ( rule__KInsets__Group__0__Impl rule__KInsets__Group__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1107:2: rule__KInsets__Group__0__Impl rule__KInsets__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__0__Impl_in_rule__KInsets__Group__02152);
            rule__KInsets__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__1_in_rule__KInsets__Group__02155);
            rule__KInsets__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group__0"


    // $ANTLR start "rule__KInsets__Group__0__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1114:1: rule__KInsets__Group__0__Impl : ( () ) ;
    public final void rule__KInsets__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1118:1: ( ( () ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1119:1: ( () )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1119:1: ( () )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1120:1: ()
            {
             before(grammarAccess.getKInsetsAccess().getKInsetsAction_0()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1121:1: ()
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1123:1: 
            {
            }

             after(grammarAccess.getKInsetsAccess().getKInsetsAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group__0__Impl"


    // $ANTLR start "rule__KInsets__Group__1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1133:1: rule__KInsets__Group__1 : rule__KInsets__Group__1__Impl rule__KInsets__Group__2 ;
    public final void rule__KInsets__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1137:1: ( rule__KInsets__Group__1__Impl rule__KInsets__Group__2 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1138:2: rule__KInsets__Group__1__Impl rule__KInsets__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__1__Impl_in_rule__KInsets__Group__12213);
            rule__KInsets__Group__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__2_in_rule__KInsets__Group__12216);
            rule__KInsets__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group__1"


    // $ANTLR start "rule__KInsets__Group__1__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1145:1: rule__KInsets__Group__1__Impl : ( 'KInsets' ) ;
    public final void rule__KInsets__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1149:1: ( ( 'KInsets' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1150:1: ( 'KInsets' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1150:1: ( 'KInsets' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1151:1: 'KInsets'
            {
             before(grammarAccess.getKInsetsAccess().getKInsetsKeyword_1()); 
            match(input,24,FollowSets000.FOLLOW_24_in_rule__KInsets__Group__1__Impl2244); 
             after(grammarAccess.getKInsetsAccess().getKInsetsKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group__1__Impl"


    // $ANTLR start "rule__KInsets__Group__2"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1164:1: rule__KInsets__Group__2 : rule__KInsets__Group__2__Impl rule__KInsets__Group__3 ;
    public final void rule__KInsets__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1168:1: ( rule__KInsets__Group__2__Impl rule__KInsets__Group__3 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1169:2: rule__KInsets__Group__2__Impl rule__KInsets__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__2__Impl_in_rule__KInsets__Group__22275);
            rule__KInsets__Group__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__3_in_rule__KInsets__Group__22278);
            rule__KInsets__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group__2"


    // $ANTLR start "rule__KInsets__Group__2__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1176:1: rule__KInsets__Group__2__Impl : ( '{' ) ;
    public final void rule__KInsets__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1180:1: ( ( '{' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1181:1: ( '{' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1181:1: ( '{' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1182:1: '{'
            {
             before(grammarAccess.getKInsetsAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,14,FollowSets000.FOLLOW_14_in_rule__KInsets__Group__2__Impl2306); 
             after(grammarAccess.getKInsetsAccess().getLeftCurlyBracketKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group__2__Impl"


    // $ANTLR start "rule__KInsets__Group__3"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1195:1: rule__KInsets__Group__3 : rule__KInsets__Group__3__Impl rule__KInsets__Group__4 ;
    public final void rule__KInsets__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1199:1: ( rule__KInsets__Group__3__Impl rule__KInsets__Group__4 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1200:2: rule__KInsets__Group__3__Impl rule__KInsets__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__3__Impl_in_rule__KInsets__Group__32337);
            rule__KInsets__Group__3__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__4_in_rule__KInsets__Group__32340);
            rule__KInsets__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group__3"


    // $ANTLR start "rule__KInsets__Group__3__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1207:1: rule__KInsets__Group__3__Impl : ( ( rule__KInsets__Group_3__0 )? ) ;
    public final void rule__KInsets__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1211:1: ( ( ( rule__KInsets__Group_3__0 )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1212:1: ( ( rule__KInsets__Group_3__0 )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1212:1: ( ( rule__KInsets__Group_3__0 )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1213:1: ( rule__KInsets__Group_3__0 )?
            {
             before(grammarAccess.getKInsetsAccess().getGroup_3()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1214:1: ( rule__KInsets__Group_3__0 )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==25) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1214:2: rule__KInsets__Group_3__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_3__0_in_rule__KInsets__Group__3__Impl2367);
                    rule__KInsets__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getKInsetsAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group__3__Impl"


    // $ANTLR start "rule__KInsets__Group__4"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1224:1: rule__KInsets__Group__4 : rule__KInsets__Group__4__Impl rule__KInsets__Group__5 ;
    public final void rule__KInsets__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1228:1: ( rule__KInsets__Group__4__Impl rule__KInsets__Group__5 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1229:2: rule__KInsets__Group__4__Impl rule__KInsets__Group__5
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__4__Impl_in_rule__KInsets__Group__42398);
            rule__KInsets__Group__4__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__5_in_rule__KInsets__Group__42401);
            rule__KInsets__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group__4"


    // $ANTLR start "rule__KInsets__Group__4__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1236:1: rule__KInsets__Group__4__Impl : ( ( rule__KInsets__Group_4__0 )? ) ;
    public final void rule__KInsets__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1240:1: ( ( ( rule__KInsets__Group_4__0 )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1241:1: ( ( rule__KInsets__Group_4__0 )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1241:1: ( ( rule__KInsets__Group_4__0 )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1242:1: ( rule__KInsets__Group_4__0 )?
            {
             before(grammarAccess.getKInsetsAccess().getGroup_4()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1243:1: ( rule__KInsets__Group_4__0 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==26) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1243:2: rule__KInsets__Group_4__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_4__0_in_rule__KInsets__Group__4__Impl2428);
                    rule__KInsets__Group_4__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getKInsetsAccess().getGroup_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group__4__Impl"


    // $ANTLR start "rule__KInsets__Group__5"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1253:1: rule__KInsets__Group__5 : rule__KInsets__Group__5__Impl rule__KInsets__Group__6 ;
    public final void rule__KInsets__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1257:1: ( rule__KInsets__Group__5__Impl rule__KInsets__Group__6 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1258:2: rule__KInsets__Group__5__Impl rule__KInsets__Group__6
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__5__Impl_in_rule__KInsets__Group__52459);
            rule__KInsets__Group__5__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__6_in_rule__KInsets__Group__52462);
            rule__KInsets__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group__5"


    // $ANTLR start "rule__KInsets__Group__5__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1265:1: rule__KInsets__Group__5__Impl : ( ( rule__KInsets__Group_5__0 )? ) ;
    public final void rule__KInsets__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1269:1: ( ( ( rule__KInsets__Group_5__0 )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1270:1: ( ( rule__KInsets__Group_5__0 )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1270:1: ( ( rule__KInsets__Group_5__0 )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1271:1: ( rule__KInsets__Group_5__0 )?
            {
             before(grammarAccess.getKInsetsAccess().getGroup_5()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1272:1: ( rule__KInsets__Group_5__0 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==27) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1272:2: rule__KInsets__Group_5__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_5__0_in_rule__KInsets__Group__5__Impl2489);
                    rule__KInsets__Group_5__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getKInsetsAccess().getGroup_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group__5__Impl"


    // $ANTLR start "rule__KInsets__Group__6"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1282:1: rule__KInsets__Group__6 : rule__KInsets__Group__6__Impl rule__KInsets__Group__7 ;
    public final void rule__KInsets__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1286:1: ( rule__KInsets__Group__6__Impl rule__KInsets__Group__7 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1287:2: rule__KInsets__Group__6__Impl rule__KInsets__Group__7
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__6__Impl_in_rule__KInsets__Group__62520);
            rule__KInsets__Group__6__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__7_in_rule__KInsets__Group__62523);
            rule__KInsets__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group__6"


    // $ANTLR start "rule__KInsets__Group__6__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1294:1: rule__KInsets__Group__6__Impl : ( ( rule__KInsets__Group_6__0 )? ) ;
    public final void rule__KInsets__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1298:1: ( ( ( rule__KInsets__Group_6__0 )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1299:1: ( ( rule__KInsets__Group_6__0 )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1299:1: ( ( rule__KInsets__Group_6__0 )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1300:1: ( rule__KInsets__Group_6__0 )?
            {
             before(grammarAccess.getKInsetsAccess().getGroup_6()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1301:1: ( rule__KInsets__Group_6__0 )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==28) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1301:2: rule__KInsets__Group_6__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_6__0_in_rule__KInsets__Group__6__Impl2550);
                    rule__KInsets__Group_6__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getKInsetsAccess().getGroup_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group__6__Impl"


    // $ANTLR start "rule__KInsets__Group__7"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1311:1: rule__KInsets__Group__7 : rule__KInsets__Group__7__Impl ;
    public final void rule__KInsets__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1315:1: ( rule__KInsets__Group__7__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1316:2: rule__KInsets__Group__7__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__7__Impl_in_rule__KInsets__Group__72581);
            rule__KInsets__Group__7__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group__7"


    // $ANTLR start "rule__KInsets__Group__7__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1322:1: rule__KInsets__Group__7__Impl : ( '}' ) ;
    public final void rule__KInsets__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1326:1: ( ( '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1327:1: ( '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1327:1: ( '}' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1328:1: '}'
            {
             before(grammarAccess.getKInsetsAccess().getRightCurlyBracketKeyword_7()); 
            match(input,15,FollowSets000.FOLLOW_15_in_rule__KInsets__Group__7__Impl2609); 
             after(grammarAccess.getKInsetsAccess().getRightCurlyBracketKeyword_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group__7__Impl"


    // $ANTLR start "rule__KInsets__Group_3__0"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1357:1: rule__KInsets__Group_3__0 : rule__KInsets__Group_3__0__Impl rule__KInsets__Group_3__1 ;
    public final void rule__KInsets__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1361:1: ( rule__KInsets__Group_3__0__Impl rule__KInsets__Group_3__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1362:2: rule__KInsets__Group_3__0__Impl rule__KInsets__Group_3__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_3__0__Impl_in_rule__KInsets__Group_3__02656);
            rule__KInsets__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_3__1_in_rule__KInsets__Group_3__02659);
            rule__KInsets__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group_3__0"


    // $ANTLR start "rule__KInsets__Group_3__0__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1369:1: rule__KInsets__Group_3__0__Impl : ( 'top' ) ;
    public final void rule__KInsets__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1373:1: ( ( 'top' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1374:1: ( 'top' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1374:1: ( 'top' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1375:1: 'top'
            {
             before(grammarAccess.getKInsetsAccess().getTopKeyword_3_0()); 
            match(input,25,FollowSets000.FOLLOW_25_in_rule__KInsets__Group_3__0__Impl2687); 
             after(grammarAccess.getKInsetsAccess().getTopKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group_3__0__Impl"


    // $ANTLR start "rule__KInsets__Group_3__1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1388:1: rule__KInsets__Group_3__1 : rule__KInsets__Group_3__1__Impl ;
    public final void rule__KInsets__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1392:1: ( rule__KInsets__Group_3__1__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1393:2: rule__KInsets__Group_3__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_3__1__Impl_in_rule__KInsets__Group_3__12718);
            rule__KInsets__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group_3__1"


    // $ANTLR start "rule__KInsets__Group_3__1__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1399:1: rule__KInsets__Group_3__1__Impl : ( ( rule__KInsets__TopAssignment_3_1 ) ) ;
    public final void rule__KInsets__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1403:1: ( ( ( rule__KInsets__TopAssignment_3_1 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1404:1: ( ( rule__KInsets__TopAssignment_3_1 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1404:1: ( ( rule__KInsets__TopAssignment_3_1 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1405:1: ( rule__KInsets__TopAssignment_3_1 )
            {
             before(grammarAccess.getKInsetsAccess().getTopAssignment_3_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1406:1: ( rule__KInsets__TopAssignment_3_1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1406:2: rule__KInsets__TopAssignment_3_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__TopAssignment_3_1_in_rule__KInsets__Group_3__1__Impl2745);
            rule__KInsets__TopAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getKInsetsAccess().getTopAssignment_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group_3__1__Impl"


    // $ANTLR start "rule__KInsets__Group_4__0"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1420:1: rule__KInsets__Group_4__0 : rule__KInsets__Group_4__0__Impl rule__KInsets__Group_4__1 ;
    public final void rule__KInsets__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1424:1: ( rule__KInsets__Group_4__0__Impl rule__KInsets__Group_4__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1425:2: rule__KInsets__Group_4__0__Impl rule__KInsets__Group_4__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_4__0__Impl_in_rule__KInsets__Group_4__02779);
            rule__KInsets__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_4__1_in_rule__KInsets__Group_4__02782);
            rule__KInsets__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group_4__0"


    // $ANTLR start "rule__KInsets__Group_4__0__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1432:1: rule__KInsets__Group_4__0__Impl : ( 'bottom' ) ;
    public final void rule__KInsets__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1436:1: ( ( 'bottom' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1437:1: ( 'bottom' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1437:1: ( 'bottom' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1438:1: 'bottom'
            {
             before(grammarAccess.getKInsetsAccess().getBottomKeyword_4_0()); 
            match(input,26,FollowSets000.FOLLOW_26_in_rule__KInsets__Group_4__0__Impl2810); 
             after(grammarAccess.getKInsetsAccess().getBottomKeyword_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group_4__0__Impl"


    // $ANTLR start "rule__KInsets__Group_4__1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1451:1: rule__KInsets__Group_4__1 : rule__KInsets__Group_4__1__Impl ;
    public final void rule__KInsets__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1455:1: ( rule__KInsets__Group_4__1__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1456:2: rule__KInsets__Group_4__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_4__1__Impl_in_rule__KInsets__Group_4__12841);
            rule__KInsets__Group_4__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group_4__1"


    // $ANTLR start "rule__KInsets__Group_4__1__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1462:1: rule__KInsets__Group_4__1__Impl : ( ( rule__KInsets__BottomAssignment_4_1 ) ) ;
    public final void rule__KInsets__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1466:1: ( ( ( rule__KInsets__BottomAssignment_4_1 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1467:1: ( ( rule__KInsets__BottomAssignment_4_1 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1467:1: ( ( rule__KInsets__BottomAssignment_4_1 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1468:1: ( rule__KInsets__BottomAssignment_4_1 )
            {
             before(grammarAccess.getKInsetsAccess().getBottomAssignment_4_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1469:1: ( rule__KInsets__BottomAssignment_4_1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1469:2: rule__KInsets__BottomAssignment_4_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__BottomAssignment_4_1_in_rule__KInsets__Group_4__1__Impl2868);
            rule__KInsets__BottomAssignment_4_1();

            state._fsp--;


            }

             after(grammarAccess.getKInsetsAccess().getBottomAssignment_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group_4__1__Impl"


    // $ANTLR start "rule__KInsets__Group_5__0"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1483:1: rule__KInsets__Group_5__0 : rule__KInsets__Group_5__0__Impl rule__KInsets__Group_5__1 ;
    public final void rule__KInsets__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1487:1: ( rule__KInsets__Group_5__0__Impl rule__KInsets__Group_5__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1488:2: rule__KInsets__Group_5__0__Impl rule__KInsets__Group_5__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_5__0__Impl_in_rule__KInsets__Group_5__02902);
            rule__KInsets__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_5__1_in_rule__KInsets__Group_5__02905);
            rule__KInsets__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group_5__0"


    // $ANTLR start "rule__KInsets__Group_5__0__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1495:1: rule__KInsets__Group_5__0__Impl : ( 'left' ) ;
    public final void rule__KInsets__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1499:1: ( ( 'left' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1500:1: ( 'left' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1500:1: ( 'left' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1501:1: 'left'
            {
             before(grammarAccess.getKInsetsAccess().getLeftKeyword_5_0()); 
            match(input,27,FollowSets000.FOLLOW_27_in_rule__KInsets__Group_5__0__Impl2933); 
             after(grammarAccess.getKInsetsAccess().getLeftKeyword_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group_5__0__Impl"


    // $ANTLR start "rule__KInsets__Group_5__1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1514:1: rule__KInsets__Group_5__1 : rule__KInsets__Group_5__1__Impl ;
    public final void rule__KInsets__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1518:1: ( rule__KInsets__Group_5__1__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1519:2: rule__KInsets__Group_5__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_5__1__Impl_in_rule__KInsets__Group_5__12964);
            rule__KInsets__Group_5__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group_5__1"


    // $ANTLR start "rule__KInsets__Group_5__1__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1525:1: rule__KInsets__Group_5__1__Impl : ( ( rule__KInsets__LeftAssignment_5_1 ) ) ;
    public final void rule__KInsets__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1529:1: ( ( ( rule__KInsets__LeftAssignment_5_1 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1530:1: ( ( rule__KInsets__LeftAssignment_5_1 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1530:1: ( ( rule__KInsets__LeftAssignment_5_1 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1531:1: ( rule__KInsets__LeftAssignment_5_1 )
            {
             before(grammarAccess.getKInsetsAccess().getLeftAssignment_5_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1532:1: ( rule__KInsets__LeftAssignment_5_1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1532:2: rule__KInsets__LeftAssignment_5_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__LeftAssignment_5_1_in_rule__KInsets__Group_5__1__Impl2991);
            rule__KInsets__LeftAssignment_5_1();

            state._fsp--;


            }

             after(grammarAccess.getKInsetsAccess().getLeftAssignment_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group_5__1__Impl"


    // $ANTLR start "rule__KInsets__Group_6__0"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1546:1: rule__KInsets__Group_6__0 : rule__KInsets__Group_6__0__Impl rule__KInsets__Group_6__1 ;
    public final void rule__KInsets__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1550:1: ( rule__KInsets__Group_6__0__Impl rule__KInsets__Group_6__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1551:2: rule__KInsets__Group_6__0__Impl rule__KInsets__Group_6__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_6__0__Impl_in_rule__KInsets__Group_6__03025);
            rule__KInsets__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_6__1_in_rule__KInsets__Group_6__03028);
            rule__KInsets__Group_6__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group_6__0"


    // $ANTLR start "rule__KInsets__Group_6__0__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1558:1: rule__KInsets__Group_6__0__Impl : ( 'right' ) ;
    public final void rule__KInsets__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1562:1: ( ( 'right' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1563:1: ( 'right' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1563:1: ( 'right' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1564:1: 'right'
            {
             before(grammarAccess.getKInsetsAccess().getRightKeyword_6_0()); 
            match(input,28,FollowSets000.FOLLOW_28_in_rule__KInsets__Group_6__0__Impl3056); 
             after(grammarAccess.getKInsetsAccess().getRightKeyword_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group_6__0__Impl"


    // $ANTLR start "rule__KInsets__Group_6__1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1577:1: rule__KInsets__Group_6__1 : rule__KInsets__Group_6__1__Impl ;
    public final void rule__KInsets__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1581:1: ( rule__KInsets__Group_6__1__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1582:2: rule__KInsets__Group_6__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_6__1__Impl_in_rule__KInsets__Group_6__13087);
            rule__KInsets__Group_6__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group_6__1"


    // $ANTLR start "rule__KInsets__Group_6__1__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1588:1: rule__KInsets__Group_6__1__Impl : ( ( rule__KInsets__RightAssignment_6_1 ) ) ;
    public final void rule__KInsets__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1592:1: ( ( ( rule__KInsets__RightAssignment_6_1 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1593:1: ( ( rule__KInsets__RightAssignment_6_1 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1593:1: ( ( rule__KInsets__RightAssignment_6_1 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1594:1: ( rule__KInsets__RightAssignment_6_1 )
            {
             before(grammarAccess.getKInsetsAccess().getRightAssignment_6_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1595:1: ( rule__KInsets__RightAssignment_6_1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1595:2: rule__KInsets__RightAssignment_6_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__RightAssignment_6_1_in_rule__KInsets__Group_6__1__Impl3114);
            rule__KInsets__RightAssignment_6_1();

            state._fsp--;


            }

             after(grammarAccess.getKInsetsAccess().getRightAssignment_6_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__Group_6__1__Impl"


    // $ANTLR start "rule__KPoint__Group__0"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1614:1: rule__KPoint__Group__0 : rule__KPoint__Group__0__Impl rule__KPoint__Group__1 ;
    public final void rule__KPoint__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1618:1: ( rule__KPoint__Group__0__Impl rule__KPoint__Group__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1619:2: rule__KPoint__Group__0__Impl rule__KPoint__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group__0__Impl_in_rule__KPoint__Group__03153);
            rule__KPoint__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group__1_in_rule__KPoint__Group__03156);
            rule__KPoint__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KPoint__Group__0"


    // $ANTLR start "rule__KPoint__Group__0__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1626:1: rule__KPoint__Group__0__Impl : ( () ) ;
    public final void rule__KPoint__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1630:1: ( ( () ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1631:1: ( () )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1631:1: ( () )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1632:1: ()
            {
             before(grammarAccess.getKPointAccess().getKPointAction_0()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1633:1: ()
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1635:1: 
            {
            }

             after(grammarAccess.getKPointAccess().getKPointAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KPoint__Group__0__Impl"


    // $ANTLR start "rule__KPoint__Group__1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1645:1: rule__KPoint__Group__1 : rule__KPoint__Group__1__Impl rule__KPoint__Group__2 ;
    public final void rule__KPoint__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1649:1: ( rule__KPoint__Group__1__Impl rule__KPoint__Group__2 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1650:2: rule__KPoint__Group__1__Impl rule__KPoint__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group__1__Impl_in_rule__KPoint__Group__13214);
            rule__KPoint__Group__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group__2_in_rule__KPoint__Group__13217);
            rule__KPoint__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KPoint__Group__1"


    // $ANTLR start "rule__KPoint__Group__1__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1657:1: rule__KPoint__Group__1__Impl : ( 'KPoint' ) ;
    public final void rule__KPoint__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1661:1: ( ( 'KPoint' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1662:1: ( 'KPoint' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1662:1: ( 'KPoint' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1663:1: 'KPoint'
            {
             before(grammarAccess.getKPointAccess().getKPointKeyword_1()); 
            match(input,29,FollowSets000.FOLLOW_29_in_rule__KPoint__Group__1__Impl3245); 
             after(grammarAccess.getKPointAccess().getKPointKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KPoint__Group__1__Impl"


    // $ANTLR start "rule__KPoint__Group__2"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1676:1: rule__KPoint__Group__2 : rule__KPoint__Group__2__Impl rule__KPoint__Group__3 ;
    public final void rule__KPoint__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1680:1: ( rule__KPoint__Group__2__Impl rule__KPoint__Group__3 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1681:2: rule__KPoint__Group__2__Impl rule__KPoint__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group__2__Impl_in_rule__KPoint__Group__23276);
            rule__KPoint__Group__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group__3_in_rule__KPoint__Group__23279);
            rule__KPoint__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KPoint__Group__2"


    // $ANTLR start "rule__KPoint__Group__2__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1688:1: rule__KPoint__Group__2__Impl : ( ( rule__KPoint__Group_2__0 ) ) ;
    public final void rule__KPoint__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1692:1: ( ( ( rule__KPoint__Group_2__0 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1693:1: ( ( rule__KPoint__Group_2__0 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1693:1: ( ( rule__KPoint__Group_2__0 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1694:1: ( rule__KPoint__Group_2__0 )
            {
             before(grammarAccess.getKPointAccess().getGroup_2()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1695:1: ( rule__KPoint__Group_2__0 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1695:2: rule__KPoint__Group_2__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group_2__0_in_rule__KPoint__Group__2__Impl3306);
            rule__KPoint__Group_2__0();

            state._fsp--;


            }

             after(grammarAccess.getKPointAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KPoint__Group__2__Impl"


    // $ANTLR start "rule__KPoint__Group__3"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1705:1: rule__KPoint__Group__3 : rule__KPoint__Group__3__Impl ;
    public final void rule__KPoint__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1709:1: ( rule__KPoint__Group__3__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1710:2: rule__KPoint__Group__3__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group__3__Impl_in_rule__KPoint__Group__33336);
            rule__KPoint__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KPoint__Group__3"


    // $ANTLR start "rule__KPoint__Group__3__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1716:1: rule__KPoint__Group__3__Impl : ( ( rule__KPoint__Group_3__0 ) ) ;
    public final void rule__KPoint__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1720:1: ( ( ( rule__KPoint__Group_3__0 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1721:1: ( ( rule__KPoint__Group_3__0 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1721:1: ( ( rule__KPoint__Group_3__0 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1722:1: ( rule__KPoint__Group_3__0 )
            {
             before(grammarAccess.getKPointAccess().getGroup_3()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1723:1: ( rule__KPoint__Group_3__0 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1723:2: rule__KPoint__Group_3__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group_3__0_in_rule__KPoint__Group__3__Impl3363);
            rule__KPoint__Group_3__0();

            state._fsp--;


            }

             after(grammarAccess.getKPointAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KPoint__Group__3__Impl"


    // $ANTLR start "rule__KPoint__Group_2__0"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1741:1: rule__KPoint__Group_2__0 : rule__KPoint__Group_2__0__Impl rule__KPoint__Group_2__1 ;
    public final void rule__KPoint__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1745:1: ( rule__KPoint__Group_2__0__Impl rule__KPoint__Group_2__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1746:2: rule__KPoint__Group_2__0__Impl rule__KPoint__Group_2__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group_2__0__Impl_in_rule__KPoint__Group_2__03401);
            rule__KPoint__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group_2__1_in_rule__KPoint__Group_2__03404);
            rule__KPoint__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KPoint__Group_2__0"


    // $ANTLR start "rule__KPoint__Group_2__0__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1753:1: rule__KPoint__Group_2__0__Impl : ( 'x' ) ;
    public final void rule__KPoint__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1757:1: ( ( 'x' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1758:1: ( 'x' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1758:1: ( 'x' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1759:1: 'x'
            {
             before(grammarAccess.getKPointAccess().getXKeyword_2_0()); 
            match(input,30,FollowSets000.FOLLOW_30_in_rule__KPoint__Group_2__0__Impl3432); 
             after(grammarAccess.getKPointAccess().getXKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KPoint__Group_2__0__Impl"


    // $ANTLR start "rule__KPoint__Group_2__1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1772:1: rule__KPoint__Group_2__1 : rule__KPoint__Group_2__1__Impl ;
    public final void rule__KPoint__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1776:1: ( rule__KPoint__Group_2__1__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1777:2: rule__KPoint__Group_2__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group_2__1__Impl_in_rule__KPoint__Group_2__13463);
            rule__KPoint__Group_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KPoint__Group_2__1"


    // $ANTLR start "rule__KPoint__Group_2__1__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1783:1: rule__KPoint__Group_2__1__Impl : ( ( rule__KPoint__XAssignment_2_1 ) ) ;
    public final void rule__KPoint__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1787:1: ( ( ( rule__KPoint__XAssignment_2_1 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1788:1: ( ( rule__KPoint__XAssignment_2_1 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1788:1: ( ( rule__KPoint__XAssignment_2_1 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1789:1: ( rule__KPoint__XAssignment_2_1 )
            {
             before(grammarAccess.getKPointAccess().getXAssignment_2_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1790:1: ( rule__KPoint__XAssignment_2_1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1790:2: rule__KPoint__XAssignment_2_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KPoint__XAssignment_2_1_in_rule__KPoint__Group_2__1__Impl3490);
            rule__KPoint__XAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getKPointAccess().getXAssignment_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KPoint__Group_2__1__Impl"


    // $ANTLR start "rule__KPoint__Group_3__0"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1804:1: rule__KPoint__Group_3__0 : rule__KPoint__Group_3__0__Impl rule__KPoint__Group_3__1 ;
    public final void rule__KPoint__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1808:1: ( rule__KPoint__Group_3__0__Impl rule__KPoint__Group_3__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1809:2: rule__KPoint__Group_3__0__Impl rule__KPoint__Group_3__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group_3__0__Impl_in_rule__KPoint__Group_3__03524);
            rule__KPoint__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group_3__1_in_rule__KPoint__Group_3__03527);
            rule__KPoint__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KPoint__Group_3__0"


    // $ANTLR start "rule__KPoint__Group_3__0__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1816:1: rule__KPoint__Group_3__0__Impl : ( 'y' ) ;
    public final void rule__KPoint__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1820:1: ( ( 'y' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1821:1: ( 'y' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1821:1: ( 'y' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1822:1: 'y'
            {
             before(grammarAccess.getKPointAccess().getYKeyword_3_0()); 
            match(input,31,FollowSets000.FOLLOW_31_in_rule__KPoint__Group_3__0__Impl3555); 
             after(grammarAccess.getKPointAccess().getYKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KPoint__Group_3__0__Impl"


    // $ANTLR start "rule__KPoint__Group_3__1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1835:1: rule__KPoint__Group_3__1 : rule__KPoint__Group_3__1__Impl ;
    public final void rule__KPoint__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1839:1: ( rule__KPoint__Group_3__1__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1840:2: rule__KPoint__Group_3__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group_3__1__Impl_in_rule__KPoint__Group_3__13586);
            rule__KPoint__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KPoint__Group_3__1"


    // $ANTLR start "rule__KPoint__Group_3__1__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1846:1: rule__KPoint__Group_3__1__Impl : ( ( rule__KPoint__YAssignment_3_1 ) ) ;
    public final void rule__KPoint__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1850:1: ( ( ( rule__KPoint__YAssignment_3_1 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1851:1: ( ( rule__KPoint__YAssignment_3_1 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1851:1: ( ( rule__KPoint__YAssignment_3_1 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1852:1: ( rule__KPoint__YAssignment_3_1 )
            {
             before(grammarAccess.getKPointAccess().getYAssignment_3_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1853:1: ( rule__KPoint__YAssignment_3_1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1853:2: rule__KPoint__YAssignment_3_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KPoint__YAssignment_3_1_in_rule__KPoint__Group_3__1__Impl3613);
            rule__KPoint__YAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getKPointAccess().getYAssignment_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KPoint__Group_3__1__Impl"


    // $ANTLR start "rule__PersistentEntry__Group__0"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1867:1: rule__PersistentEntry__Group__0 : rule__PersistentEntry__Group__0__Impl rule__PersistentEntry__Group__1 ;
    public final void rule__PersistentEntry__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1871:1: ( rule__PersistentEntry__Group__0__Impl rule__PersistentEntry__Group__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1872:2: rule__PersistentEntry__Group__0__Impl rule__PersistentEntry__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__Group__0__Impl_in_rule__PersistentEntry__Group__03647);
            rule__PersistentEntry__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__Group__1_in_rule__PersistentEntry__Group__03650);
            rule__PersistentEntry__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistentEntry__Group__0"


    // $ANTLR start "rule__PersistentEntry__Group__0__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1879:1: rule__PersistentEntry__Group__0__Impl : ( 'PersistentEntry' ) ;
    public final void rule__PersistentEntry__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1883:1: ( ( 'PersistentEntry' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1884:1: ( 'PersistentEntry' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1884:1: ( 'PersistentEntry' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1885:1: 'PersistentEntry'
            {
             before(grammarAccess.getPersistentEntryAccess().getPersistentEntryKeyword_0()); 
            match(input,32,FollowSets000.FOLLOW_32_in_rule__PersistentEntry__Group__0__Impl3678); 
             after(grammarAccess.getPersistentEntryAccess().getPersistentEntryKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistentEntry__Group__0__Impl"


    // $ANTLR start "rule__PersistentEntry__Group__1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1898:1: rule__PersistentEntry__Group__1 : rule__PersistentEntry__Group__1__Impl rule__PersistentEntry__Group__2 ;
    public final void rule__PersistentEntry__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1902:1: ( rule__PersistentEntry__Group__1__Impl rule__PersistentEntry__Group__2 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1903:2: rule__PersistentEntry__Group__1__Impl rule__PersistentEntry__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__Group__1__Impl_in_rule__PersistentEntry__Group__13709);
            rule__PersistentEntry__Group__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__Group__2_in_rule__PersistentEntry__Group__13712);
            rule__PersistentEntry__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistentEntry__Group__1"


    // $ANTLR start "rule__PersistentEntry__Group__1__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1910:1: rule__PersistentEntry__Group__1__Impl : ( '{' ) ;
    public final void rule__PersistentEntry__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1914:1: ( ( '{' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1915:1: ( '{' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1915:1: ( '{' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1916:1: '{'
            {
             before(grammarAccess.getPersistentEntryAccess().getLeftCurlyBracketKeyword_1()); 
            match(input,14,FollowSets000.FOLLOW_14_in_rule__PersistentEntry__Group__1__Impl3740); 
             after(grammarAccess.getPersistentEntryAccess().getLeftCurlyBracketKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistentEntry__Group__1__Impl"


    // $ANTLR start "rule__PersistentEntry__Group__2"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1929:1: rule__PersistentEntry__Group__2 : rule__PersistentEntry__Group__2__Impl rule__PersistentEntry__Group__3 ;
    public final void rule__PersistentEntry__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1933:1: ( rule__PersistentEntry__Group__2__Impl rule__PersistentEntry__Group__3 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1934:2: rule__PersistentEntry__Group__2__Impl rule__PersistentEntry__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__Group__2__Impl_in_rule__PersistentEntry__Group__23771);
            rule__PersistentEntry__Group__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__Group__3_in_rule__PersistentEntry__Group__23774);
            rule__PersistentEntry__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistentEntry__Group__2"


    // $ANTLR start "rule__PersistentEntry__Group__2__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1941:1: rule__PersistentEntry__Group__2__Impl : ( 'key' ) ;
    public final void rule__PersistentEntry__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1945:1: ( ( 'key' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1946:1: ( 'key' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1946:1: ( 'key' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1947:1: 'key'
            {
             before(grammarAccess.getPersistentEntryAccess().getKeyKeyword_2()); 
            match(input,33,FollowSets000.FOLLOW_33_in_rule__PersistentEntry__Group__2__Impl3802); 
             after(grammarAccess.getPersistentEntryAccess().getKeyKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistentEntry__Group__2__Impl"


    // $ANTLR start "rule__PersistentEntry__Group__3"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1960:1: rule__PersistentEntry__Group__3 : rule__PersistentEntry__Group__3__Impl rule__PersistentEntry__Group__4 ;
    public final void rule__PersistentEntry__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1964:1: ( rule__PersistentEntry__Group__3__Impl rule__PersistentEntry__Group__4 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1965:2: rule__PersistentEntry__Group__3__Impl rule__PersistentEntry__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__Group__3__Impl_in_rule__PersistentEntry__Group__33833);
            rule__PersistentEntry__Group__3__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__Group__4_in_rule__PersistentEntry__Group__33836);
            rule__PersistentEntry__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistentEntry__Group__3"


    // $ANTLR start "rule__PersistentEntry__Group__3__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1972:1: rule__PersistentEntry__Group__3__Impl : ( ( rule__PersistentEntry__KeyAssignment_3 ) ) ;
    public final void rule__PersistentEntry__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1976:1: ( ( ( rule__PersistentEntry__KeyAssignment_3 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1977:1: ( ( rule__PersistentEntry__KeyAssignment_3 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1977:1: ( ( rule__PersistentEntry__KeyAssignment_3 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1978:1: ( rule__PersistentEntry__KeyAssignment_3 )
            {
             before(grammarAccess.getPersistentEntryAccess().getKeyAssignment_3()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1979:1: ( rule__PersistentEntry__KeyAssignment_3 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1979:2: rule__PersistentEntry__KeyAssignment_3
            {
            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__KeyAssignment_3_in_rule__PersistentEntry__Group__3__Impl3863);
            rule__PersistentEntry__KeyAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getPersistentEntryAccess().getKeyAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistentEntry__Group__3__Impl"


    // $ANTLR start "rule__PersistentEntry__Group__4"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1989:1: rule__PersistentEntry__Group__4 : rule__PersistentEntry__Group__4__Impl rule__PersistentEntry__Group__5 ;
    public final void rule__PersistentEntry__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1993:1: ( rule__PersistentEntry__Group__4__Impl rule__PersistentEntry__Group__5 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1994:2: rule__PersistentEntry__Group__4__Impl rule__PersistentEntry__Group__5
            {
            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__Group__4__Impl_in_rule__PersistentEntry__Group__43893);
            rule__PersistentEntry__Group__4__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__Group__5_in_rule__PersistentEntry__Group__43896);
            rule__PersistentEntry__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistentEntry__Group__4"


    // $ANTLR start "rule__PersistentEntry__Group__4__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2001:1: rule__PersistentEntry__Group__4__Impl : ( ( rule__PersistentEntry__Group_4__0 )? ) ;
    public final void rule__PersistentEntry__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2005:1: ( ( ( rule__PersistentEntry__Group_4__0 )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2006:1: ( ( rule__PersistentEntry__Group_4__0 )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2006:1: ( ( rule__PersistentEntry__Group_4__0 )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2007:1: ( rule__PersistentEntry__Group_4__0 )?
            {
             before(grammarAccess.getPersistentEntryAccess().getGroup_4()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2008:1: ( rule__PersistentEntry__Group_4__0 )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==34) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2008:2: rule__PersistentEntry__Group_4__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__Group_4__0_in_rule__PersistentEntry__Group__4__Impl3923);
                    rule__PersistentEntry__Group_4__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getPersistentEntryAccess().getGroup_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistentEntry__Group__4__Impl"


    // $ANTLR start "rule__PersistentEntry__Group__5"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2018:1: rule__PersistentEntry__Group__5 : rule__PersistentEntry__Group__5__Impl ;
    public final void rule__PersistentEntry__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2022:1: ( rule__PersistentEntry__Group__5__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2023:2: rule__PersistentEntry__Group__5__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__Group__5__Impl_in_rule__PersistentEntry__Group__53954);
            rule__PersistentEntry__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistentEntry__Group__5"


    // $ANTLR start "rule__PersistentEntry__Group__5__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2029:1: rule__PersistentEntry__Group__5__Impl : ( '}' ) ;
    public final void rule__PersistentEntry__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2033:1: ( ( '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2034:1: ( '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2034:1: ( '}' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2035:1: '}'
            {
             before(grammarAccess.getPersistentEntryAccess().getRightCurlyBracketKeyword_5()); 
            match(input,15,FollowSets000.FOLLOW_15_in_rule__PersistentEntry__Group__5__Impl3982); 
             after(grammarAccess.getPersistentEntryAccess().getRightCurlyBracketKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistentEntry__Group__5__Impl"


    // $ANTLR start "rule__PersistentEntry__Group_4__0"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2060:1: rule__PersistentEntry__Group_4__0 : rule__PersistentEntry__Group_4__0__Impl rule__PersistentEntry__Group_4__1 ;
    public final void rule__PersistentEntry__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2064:1: ( rule__PersistentEntry__Group_4__0__Impl rule__PersistentEntry__Group_4__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2065:2: rule__PersistentEntry__Group_4__0__Impl rule__PersistentEntry__Group_4__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__Group_4__0__Impl_in_rule__PersistentEntry__Group_4__04025);
            rule__PersistentEntry__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__Group_4__1_in_rule__PersistentEntry__Group_4__04028);
            rule__PersistentEntry__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistentEntry__Group_4__0"


    // $ANTLR start "rule__PersistentEntry__Group_4__0__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2072:1: rule__PersistentEntry__Group_4__0__Impl : ( 'value' ) ;
    public final void rule__PersistentEntry__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2076:1: ( ( 'value' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2077:1: ( 'value' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2077:1: ( 'value' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2078:1: 'value'
            {
             before(grammarAccess.getPersistentEntryAccess().getValueKeyword_4_0()); 
            match(input,34,FollowSets000.FOLLOW_34_in_rule__PersistentEntry__Group_4__0__Impl4056); 
             after(grammarAccess.getPersistentEntryAccess().getValueKeyword_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistentEntry__Group_4__0__Impl"


    // $ANTLR start "rule__PersistentEntry__Group_4__1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2091:1: rule__PersistentEntry__Group_4__1 : rule__PersistentEntry__Group_4__1__Impl ;
    public final void rule__PersistentEntry__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2095:1: ( rule__PersistentEntry__Group_4__1__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2096:2: rule__PersistentEntry__Group_4__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__Group_4__1__Impl_in_rule__PersistentEntry__Group_4__14087);
            rule__PersistentEntry__Group_4__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistentEntry__Group_4__1"


    // $ANTLR start "rule__PersistentEntry__Group_4__1__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2102:1: rule__PersistentEntry__Group_4__1__Impl : ( ( rule__PersistentEntry__ValueAssignment_4_1 ) ) ;
    public final void rule__PersistentEntry__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2106:1: ( ( ( rule__PersistentEntry__ValueAssignment_4_1 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2107:1: ( ( rule__PersistentEntry__ValueAssignment_4_1 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2107:1: ( ( rule__PersistentEntry__ValueAssignment_4_1 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2108:1: ( rule__PersistentEntry__ValueAssignment_4_1 )
            {
             before(grammarAccess.getPersistentEntryAccess().getValueAssignment_4_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2109:1: ( rule__PersistentEntry__ValueAssignment_4_1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2109:2: rule__PersistentEntry__ValueAssignment_4_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__PersistentEntry__ValueAssignment_4_1_in_rule__PersistentEntry__Group_4__1__Impl4114);
            rule__PersistentEntry__ValueAssignment_4_1();

            state._fsp--;


            }

             after(grammarAccess.getPersistentEntryAccess().getValueAssignment_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistentEntry__Group_4__1__Impl"


    // $ANTLR start "rule__EFloat__Group__0"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2123:1: rule__EFloat__Group__0 : rule__EFloat__Group__0__Impl rule__EFloat__Group__1 ;
    public final void rule__EFloat__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2127:1: ( rule__EFloat__Group__0__Impl rule__EFloat__Group__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2128:2: rule__EFloat__Group__0__Impl rule__EFloat__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group__0__Impl_in_rule__EFloat__Group__04148);
            rule__EFloat__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group__1_in_rule__EFloat__Group__04151);
            rule__EFloat__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EFloat__Group__0"


    // $ANTLR start "rule__EFloat__Group__0__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2135:1: rule__EFloat__Group__0__Impl : ( ( '-' )? ) ;
    public final void rule__EFloat__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2139:1: ( ( ( '-' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2140:1: ( ( '-' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2140:1: ( ( '-' )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2141:1: ( '-' )?
            {
             before(grammarAccess.getEFloatAccess().getHyphenMinusKeyword_0()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2142:1: ( '-' )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==35) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2143:2: '-'
                    {
                    match(input,35,FollowSets000.FOLLOW_35_in_rule__EFloat__Group__0__Impl4180); 

                    }
                    break;

            }

             after(grammarAccess.getEFloatAccess().getHyphenMinusKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EFloat__Group__0__Impl"


    // $ANTLR start "rule__EFloat__Group__1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2154:1: rule__EFloat__Group__1 : rule__EFloat__Group__1__Impl rule__EFloat__Group__2 ;
    public final void rule__EFloat__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2158:1: ( rule__EFloat__Group__1__Impl rule__EFloat__Group__2 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2159:2: rule__EFloat__Group__1__Impl rule__EFloat__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group__1__Impl_in_rule__EFloat__Group__14213);
            rule__EFloat__Group__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group__2_in_rule__EFloat__Group__14216);
            rule__EFloat__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EFloat__Group__1"


    // $ANTLR start "rule__EFloat__Group__1__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2166:1: rule__EFloat__Group__1__Impl : ( ( RULE_INT )? ) ;
    public final void rule__EFloat__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2170:1: ( ( ( RULE_INT )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2171:1: ( ( RULE_INT )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2171:1: ( ( RULE_INT )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2172:1: ( RULE_INT )?
            {
             before(grammarAccess.getEFloatAccess().getINTTerminalRuleCall_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2173:1: ( RULE_INT )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_INT) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2173:3: RULE_INT
                    {
                    match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_rule__EFloat__Group__1__Impl4244); 

                    }
                    break;

            }

             after(grammarAccess.getEFloatAccess().getINTTerminalRuleCall_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EFloat__Group__1__Impl"


    // $ANTLR start "rule__EFloat__Group__2"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2183:1: rule__EFloat__Group__2 : rule__EFloat__Group__2__Impl rule__EFloat__Group__3 ;
    public final void rule__EFloat__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2187:1: ( rule__EFloat__Group__2__Impl rule__EFloat__Group__3 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2188:2: rule__EFloat__Group__2__Impl rule__EFloat__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group__2__Impl_in_rule__EFloat__Group__24275);
            rule__EFloat__Group__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group__3_in_rule__EFloat__Group__24278);
            rule__EFloat__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EFloat__Group__2"


    // $ANTLR start "rule__EFloat__Group__2__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2195:1: rule__EFloat__Group__2__Impl : ( '.' ) ;
    public final void rule__EFloat__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2199:1: ( ( '.' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2200:1: ( '.' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2200:1: ( '.' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2201:1: '.'
            {
             before(grammarAccess.getEFloatAccess().getFullStopKeyword_2()); 
            match(input,36,FollowSets000.FOLLOW_36_in_rule__EFloat__Group__2__Impl4306); 
             after(grammarAccess.getEFloatAccess().getFullStopKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EFloat__Group__2__Impl"


    // $ANTLR start "rule__EFloat__Group__3"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2214:1: rule__EFloat__Group__3 : rule__EFloat__Group__3__Impl rule__EFloat__Group__4 ;
    public final void rule__EFloat__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2218:1: ( rule__EFloat__Group__3__Impl rule__EFloat__Group__4 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2219:2: rule__EFloat__Group__3__Impl rule__EFloat__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group__3__Impl_in_rule__EFloat__Group__34337);
            rule__EFloat__Group__3__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group__4_in_rule__EFloat__Group__34340);
            rule__EFloat__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EFloat__Group__3"


    // $ANTLR start "rule__EFloat__Group__3__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2226:1: rule__EFloat__Group__3__Impl : ( RULE_INT ) ;
    public final void rule__EFloat__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2230:1: ( ( RULE_INT ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2231:1: ( RULE_INT )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2231:1: ( RULE_INT )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2232:1: RULE_INT
            {
             before(grammarAccess.getEFloatAccess().getINTTerminalRuleCall_3()); 
            match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_rule__EFloat__Group__3__Impl4367); 
             after(grammarAccess.getEFloatAccess().getINTTerminalRuleCall_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EFloat__Group__3__Impl"


    // $ANTLR start "rule__EFloat__Group__4"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2243:1: rule__EFloat__Group__4 : rule__EFloat__Group__4__Impl ;
    public final void rule__EFloat__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2247:1: ( rule__EFloat__Group__4__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2248:2: rule__EFloat__Group__4__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group__4__Impl_in_rule__EFloat__Group__44396);
            rule__EFloat__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EFloat__Group__4"


    // $ANTLR start "rule__EFloat__Group__4__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2254:1: rule__EFloat__Group__4__Impl : ( ( rule__EFloat__Group_4__0 )? ) ;
    public final void rule__EFloat__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2258:1: ( ( ( rule__EFloat__Group_4__0 )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2259:1: ( ( rule__EFloat__Group_4__0 )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2259:1: ( ( rule__EFloat__Group_4__0 )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2260:1: ( rule__EFloat__Group_4__0 )?
            {
             before(grammarAccess.getEFloatAccess().getGroup_4()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2261:1: ( rule__EFloat__Group_4__0 )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( ((LA17_0>=11 && LA17_0<=12)) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2261:2: rule__EFloat__Group_4__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group_4__0_in_rule__EFloat__Group__4__Impl4423);
                    rule__EFloat__Group_4__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getEFloatAccess().getGroup_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EFloat__Group__4__Impl"


    // $ANTLR start "rule__EFloat__Group_4__0"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2281:1: rule__EFloat__Group_4__0 : rule__EFloat__Group_4__0__Impl rule__EFloat__Group_4__1 ;
    public final void rule__EFloat__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2285:1: ( rule__EFloat__Group_4__0__Impl rule__EFloat__Group_4__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2286:2: rule__EFloat__Group_4__0__Impl rule__EFloat__Group_4__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group_4__0__Impl_in_rule__EFloat__Group_4__04464);
            rule__EFloat__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group_4__1_in_rule__EFloat__Group_4__04467);
            rule__EFloat__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EFloat__Group_4__0"


    // $ANTLR start "rule__EFloat__Group_4__0__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2293:1: rule__EFloat__Group_4__0__Impl : ( ( rule__EFloat__Alternatives_4_0 ) ) ;
    public final void rule__EFloat__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2297:1: ( ( ( rule__EFloat__Alternatives_4_0 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2298:1: ( ( rule__EFloat__Alternatives_4_0 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2298:1: ( ( rule__EFloat__Alternatives_4_0 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2299:1: ( rule__EFloat__Alternatives_4_0 )
            {
             before(grammarAccess.getEFloatAccess().getAlternatives_4_0()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2300:1: ( rule__EFloat__Alternatives_4_0 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2300:2: rule__EFloat__Alternatives_4_0
            {
            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Alternatives_4_0_in_rule__EFloat__Group_4__0__Impl4494);
            rule__EFloat__Alternatives_4_0();

            state._fsp--;


            }

             after(grammarAccess.getEFloatAccess().getAlternatives_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EFloat__Group_4__0__Impl"


    // $ANTLR start "rule__EFloat__Group_4__1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2310:1: rule__EFloat__Group_4__1 : rule__EFloat__Group_4__1__Impl rule__EFloat__Group_4__2 ;
    public final void rule__EFloat__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2314:1: ( rule__EFloat__Group_4__1__Impl rule__EFloat__Group_4__2 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2315:2: rule__EFloat__Group_4__1__Impl rule__EFloat__Group_4__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group_4__1__Impl_in_rule__EFloat__Group_4__14524);
            rule__EFloat__Group_4__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group_4__2_in_rule__EFloat__Group_4__14527);
            rule__EFloat__Group_4__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EFloat__Group_4__1"


    // $ANTLR start "rule__EFloat__Group_4__1__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2322:1: rule__EFloat__Group_4__1__Impl : ( ( '-' )? ) ;
    public final void rule__EFloat__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2326:1: ( ( ( '-' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2327:1: ( ( '-' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2327:1: ( ( '-' )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2328:1: ( '-' )?
            {
             before(grammarAccess.getEFloatAccess().getHyphenMinusKeyword_4_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2329:1: ( '-' )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==35) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2330:2: '-'
                    {
                    match(input,35,FollowSets000.FOLLOW_35_in_rule__EFloat__Group_4__1__Impl4556); 

                    }
                    break;

            }

             after(grammarAccess.getEFloatAccess().getHyphenMinusKeyword_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EFloat__Group_4__1__Impl"


    // $ANTLR start "rule__EFloat__Group_4__2"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2341:1: rule__EFloat__Group_4__2 : rule__EFloat__Group_4__2__Impl ;
    public final void rule__EFloat__Group_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2345:1: ( rule__EFloat__Group_4__2__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2346:2: rule__EFloat__Group_4__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group_4__2__Impl_in_rule__EFloat__Group_4__24589);
            rule__EFloat__Group_4__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EFloat__Group_4__2"


    // $ANTLR start "rule__EFloat__Group_4__2__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2352:1: rule__EFloat__Group_4__2__Impl : ( RULE_INT ) ;
    public final void rule__EFloat__Group_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2356:1: ( ( RULE_INT ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2357:1: ( RULE_INT )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2357:1: ( RULE_INT )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2358:1: RULE_INT
            {
             before(grammarAccess.getEFloatAccess().getINTTerminalRuleCall_4_2()); 
            match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_rule__EFloat__Group_4__2__Impl4616); 
             after(grammarAccess.getEFloatAccess().getINTTerminalRuleCall_4_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EFloat__Group_4__2__Impl"


    // $ANTLR start "rule__KShapeLayout__XposAssignment_3_1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2376:1: rule__KShapeLayout__XposAssignment_3_1 : ( ruleEFloat ) ;
    public final void rule__KShapeLayout__XposAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2380:1: ( ( ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2381:1: ( ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2381:1: ( ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2382:1: ruleEFloat
            {
             before(grammarAccess.getKShapeLayoutAccess().getXposEFloatParserRuleCall_3_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_rule__KShapeLayout__XposAssignment_3_14656);
            ruleEFloat();

            state._fsp--;

             after(grammarAccess.getKShapeLayoutAccess().getXposEFloatParserRuleCall_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__XposAssignment_3_1"


    // $ANTLR start "rule__KShapeLayout__YposAssignment_4_1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2391:1: rule__KShapeLayout__YposAssignment_4_1 : ( ruleEFloat ) ;
    public final void rule__KShapeLayout__YposAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2395:1: ( ( ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2396:1: ( ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2396:1: ( ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2397:1: ruleEFloat
            {
             before(grammarAccess.getKShapeLayoutAccess().getYposEFloatParserRuleCall_4_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_rule__KShapeLayout__YposAssignment_4_14687);
            ruleEFloat();

            state._fsp--;

             after(grammarAccess.getKShapeLayoutAccess().getYposEFloatParserRuleCall_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__YposAssignment_4_1"


    // $ANTLR start "rule__KShapeLayout__WidthAssignment_5_1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2406:1: rule__KShapeLayout__WidthAssignment_5_1 : ( ruleEFloat ) ;
    public final void rule__KShapeLayout__WidthAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2410:1: ( ( ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2411:1: ( ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2411:1: ( ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2412:1: ruleEFloat
            {
             before(grammarAccess.getKShapeLayoutAccess().getWidthEFloatParserRuleCall_5_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_rule__KShapeLayout__WidthAssignment_5_14718);
            ruleEFloat();

            state._fsp--;

             after(grammarAccess.getKShapeLayoutAccess().getWidthEFloatParserRuleCall_5_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__WidthAssignment_5_1"


    // $ANTLR start "rule__KShapeLayout__HeightAssignment_6_1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2421:1: rule__KShapeLayout__HeightAssignment_6_1 : ( ruleEFloat ) ;
    public final void rule__KShapeLayout__HeightAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2425:1: ( ( ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2426:1: ( ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2426:1: ( ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2427:1: ruleEFloat
            {
             before(grammarAccess.getKShapeLayoutAccess().getHeightEFloatParserRuleCall_6_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_rule__KShapeLayout__HeightAssignment_6_14749);
            ruleEFloat();

            state._fsp--;

             after(grammarAccess.getKShapeLayoutAccess().getHeightEFloatParserRuleCall_6_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__HeightAssignment_6_1"


    // $ANTLR start "rule__KShapeLayout__InsetsAssignment_7_1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2436:1: rule__KShapeLayout__InsetsAssignment_7_1 : ( ruleKInsets ) ;
    public final void rule__KShapeLayout__InsetsAssignment_7_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2440:1: ( ( ruleKInsets ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2441:1: ( ruleKInsets )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2441:1: ( ruleKInsets )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2442:1: ruleKInsets
            {
             before(grammarAccess.getKShapeLayoutAccess().getInsetsKInsetsParserRuleCall_7_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleKInsets_in_rule__KShapeLayout__InsetsAssignment_7_14780);
            ruleKInsets();

            state._fsp--;

             after(grammarAccess.getKShapeLayoutAccess().getInsetsKInsetsParserRuleCall_7_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__InsetsAssignment_7_1"


    // $ANTLR start "rule__KShapeLayout__PersistentEntriesAssignment_8_2"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2451:1: rule__KShapeLayout__PersistentEntriesAssignment_8_2 : ( rulePersistentEntry ) ;
    public final void rule__KShapeLayout__PersistentEntriesAssignment_8_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2455:1: ( ( rulePersistentEntry ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2456:1: ( rulePersistentEntry )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2456:1: ( rulePersistentEntry )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2457:1: rulePersistentEntry
            {
             before(grammarAccess.getKShapeLayoutAccess().getPersistentEntriesPersistentEntryParserRuleCall_8_2_0()); 
            pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_rule__KShapeLayout__PersistentEntriesAssignment_8_24811);
            rulePersistentEntry();

            state._fsp--;

             after(grammarAccess.getKShapeLayoutAccess().getPersistentEntriesPersistentEntryParserRuleCall_8_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__PersistentEntriesAssignment_8_2"


    // $ANTLR start "rule__KShapeLayout__PersistentEntriesAssignment_8_3_1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2466:1: rule__KShapeLayout__PersistentEntriesAssignment_8_3_1 : ( rulePersistentEntry ) ;
    public final void rule__KShapeLayout__PersistentEntriesAssignment_8_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2470:1: ( ( rulePersistentEntry ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2471:1: ( rulePersistentEntry )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2471:1: ( rulePersistentEntry )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2472:1: rulePersistentEntry
            {
             before(grammarAccess.getKShapeLayoutAccess().getPersistentEntriesPersistentEntryParserRuleCall_8_3_1_0()); 
            pushFollow(FollowSets000.FOLLOW_rulePersistentEntry_in_rule__KShapeLayout__PersistentEntriesAssignment_8_3_14842);
            rulePersistentEntry();

            state._fsp--;

             after(grammarAccess.getKShapeLayoutAccess().getPersistentEntriesPersistentEntryParserRuleCall_8_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KShapeLayout__PersistentEntriesAssignment_8_3_1"


    // $ANTLR start "rule__KInsets__TopAssignment_3_1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2481:1: rule__KInsets__TopAssignment_3_1 : ( ruleEFloat ) ;
    public final void rule__KInsets__TopAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2485:1: ( ( ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2486:1: ( ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2486:1: ( ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2487:1: ruleEFloat
            {
             before(grammarAccess.getKInsetsAccess().getTopEFloatParserRuleCall_3_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_rule__KInsets__TopAssignment_3_14873);
            ruleEFloat();

            state._fsp--;

             after(grammarAccess.getKInsetsAccess().getTopEFloatParserRuleCall_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__TopAssignment_3_1"


    // $ANTLR start "rule__KInsets__BottomAssignment_4_1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2496:1: rule__KInsets__BottomAssignment_4_1 : ( ruleEFloat ) ;
    public final void rule__KInsets__BottomAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2500:1: ( ( ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2501:1: ( ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2501:1: ( ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2502:1: ruleEFloat
            {
             before(grammarAccess.getKInsetsAccess().getBottomEFloatParserRuleCall_4_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_rule__KInsets__BottomAssignment_4_14904);
            ruleEFloat();

            state._fsp--;

             after(grammarAccess.getKInsetsAccess().getBottomEFloatParserRuleCall_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__BottomAssignment_4_1"


    // $ANTLR start "rule__KInsets__LeftAssignment_5_1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2511:1: rule__KInsets__LeftAssignment_5_1 : ( ruleEFloat ) ;
    public final void rule__KInsets__LeftAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2515:1: ( ( ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2516:1: ( ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2516:1: ( ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2517:1: ruleEFloat
            {
             before(grammarAccess.getKInsetsAccess().getLeftEFloatParserRuleCall_5_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_rule__KInsets__LeftAssignment_5_14935);
            ruleEFloat();

            state._fsp--;

             after(grammarAccess.getKInsetsAccess().getLeftEFloatParserRuleCall_5_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__LeftAssignment_5_1"


    // $ANTLR start "rule__KInsets__RightAssignment_6_1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2526:1: rule__KInsets__RightAssignment_6_1 : ( ruleEFloat ) ;
    public final void rule__KInsets__RightAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2530:1: ( ( ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2531:1: ( ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2531:1: ( ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2532:1: ruleEFloat
            {
             before(grammarAccess.getKInsetsAccess().getRightEFloatParserRuleCall_6_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_rule__KInsets__RightAssignment_6_14966);
            ruleEFloat();

            state._fsp--;

             after(grammarAccess.getKInsetsAccess().getRightEFloatParserRuleCall_6_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KInsets__RightAssignment_6_1"


    // $ANTLR start "rule__KPoint__XAssignment_2_1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2547:1: rule__KPoint__XAssignment_2_1 : ( ruleEFloat ) ;
    public final void rule__KPoint__XAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2551:1: ( ( ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2552:1: ( ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2552:1: ( ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2553:1: ruleEFloat
            {
             before(grammarAccess.getKPointAccess().getXEFloatParserRuleCall_2_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_rule__KPoint__XAssignment_2_15003);
            ruleEFloat();

            state._fsp--;

             after(grammarAccess.getKPointAccess().getXEFloatParserRuleCall_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KPoint__XAssignment_2_1"


    // $ANTLR start "rule__KPoint__YAssignment_3_1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2562:1: rule__KPoint__YAssignment_3_1 : ( ruleEFloat ) ;
    public final void rule__KPoint__YAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2566:1: ( ( ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2567:1: ( ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2567:1: ( ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2568:1: ruleEFloat
            {
             before(grammarAccess.getKPointAccess().getYEFloatParserRuleCall_3_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_rule__KPoint__YAssignment_3_15034);
            ruleEFloat();

            state._fsp--;

             after(grammarAccess.getKPointAccess().getYEFloatParserRuleCall_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__KPoint__YAssignment_3_1"


    // $ANTLR start "rule__PersistentEntry__KeyAssignment_3"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2577:1: rule__PersistentEntry__KeyAssignment_3 : ( ruleEString ) ;
    public final void rule__PersistentEntry__KeyAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2581:1: ( ( ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2582:1: ( ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2582:1: ( ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2583:1: ruleEString
            {
             before(grammarAccess.getPersistentEntryAccess().getKeyEStringParserRuleCall_3_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_rule__PersistentEntry__KeyAssignment_35065);
            ruleEString();

            state._fsp--;

             after(grammarAccess.getPersistentEntryAccess().getKeyEStringParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistentEntry__KeyAssignment_3"


    // $ANTLR start "rule__PersistentEntry__ValueAssignment_4_1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2592:1: rule__PersistentEntry__ValueAssignment_4_1 : ( ruleEString ) ;
    public final void rule__PersistentEntry__ValueAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2596:1: ( ( ruleEString ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2597:1: ( ruleEString )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2597:1: ( ruleEString )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2598:1: ruleEString
            {
             before(grammarAccess.getPersistentEntryAccess().getValueEStringParserRuleCall_4_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_rule__PersistentEntry__ValueAssignment_4_15096);
            ruleEString();

            state._fsp--;

             after(grammarAccess.getPersistentEntryAccess().getValueEStringParserRuleCall_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PersistentEntry__ValueAssignment_4_1"

    // Delegated rules


 

    
    private static class FollowSets000 {
        public static final BitSet FOLLOW_ruleKShapeLayout_in_entryRuleKShapeLayout61 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKShapeLayout68 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__0_in_ruleKShapeLayout94 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKInsets_in_entryRuleKInsets121 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKInsets128 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group__0_in_ruleKInsets154 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKPoint_in_entryRuleKPoint183 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleKPoint190 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KPoint__Group__0_in_ruleKPoint216 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_entryRulePersistentEntry243 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRulePersistentEntry250 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PersistentEntry__Group__0_in_rulePersistentEntry276 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_entryRuleEString303 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEString310 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__EString__Alternatives_in_ruleEString336 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_entryRuleEFloat363 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEFloat370 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__EFloat__Group__0_in_ruleEFloat396 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_rule__EString__Alternatives432 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_rule__EString__Alternatives449 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_11_in_rule__EFloat__Alternatives_4_0482 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_12_in_rule__EFloat__Alternatives_4_0502 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__0__Impl_in_rule__KShapeLayout__Group__0534 = new BitSet(new long[]{0x0000000000002000L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__1_in_rule__KShapeLayout__Group__0537 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__1__Impl_in_rule__KShapeLayout__Group__1595 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__2_in_rule__KShapeLayout__Group__1598 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_13_in_rule__KShapeLayout__Group__1__Impl626 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__2__Impl_in_rule__KShapeLayout__Group__2657 = new BitSet(new long[]{0x00000000003F8000L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__3_in_rule__KShapeLayout__Group__2660 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_14_in_rule__KShapeLayout__Group__2__Impl688 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__3__Impl_in_rule__KShapeLayout__Group__3719 = new BitSet(new long[]{0x00000000003F8000L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__4_in_rule__KShapeLayout__Group__3722 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_3__0_in_rule__KShapeLayout__Group__3__Impl749 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__4__Impl_in_rule__KShapeLayout__Group__4780 = new BitSet(new long[]{0x00000000003F8000L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__5_in_rule__KShapeLayout__Group__4783 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_4__0_in_rule__KShapeLayout__Group__4__Impl810 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__5__Impl_in_rule__KShapeLayout__Group__5841 = new BitSet(new long[]{0x00000000003F8000L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__6_in_rule__KShapeLayout__Group__5844 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_5__0_in_rule__KShapeLayout__Group__5__Impl871 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__6__Impl_in_rule__KShapeLayout__Group__6902 = new BitSet(new long[]{0x00000000003F8000L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__7_in_rule__KShapeLayout__Group__6905 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_6__0_in_rule__KShapeLayout__Group__6__Impl932 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__7__Impl_in_rule__KShapeLayout__Group__7963 = new BitSet(new long[]{0x00000000003F8000L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__8_in_rule__KShapeLayout__Group__7966 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_7__0_in_rule__KShapeLayout__Group__7__Impl993 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__8__Impl_in_rule__KShapeLayout__Group__81024 = new BitSet(new long[]{0x00000000003F8000L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__9_in_rule__KShapeLayout__Group__81027 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_8__0_in_rule__KShapeLayout__Group__8__Impl1054 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__9__Impl_in_rule__KShapeLayout__Group__91085 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_15_in_rule__KShapeLayout__Group__9__Impl1113 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_3__0__Impl_in_rule__KShapeLayout__Group_3__01164 = new BitSet(new long[]{0x0000001800000040L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_3__1_in_rule__KShapeLayout__Group_3__01167 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_16_in_rule__KShapeLayout__Group_3__0__Impl1195 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_3__1__Impl_in_rule__KShapeLayout__Group_3__11226 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__XposAssignment_3_1_in_rule__KShapeLayout__Group_3__1__Impl1253 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_4__0__Impl_in_rule__KShapeLayout__Group_4__01287 = new BitSet(new long[]{0x0000001800000040L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_4__1_in_rule__KShapeLayout__Group_4__01290 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_17_in_rule__KShapeLayout__Group_4__0__Impl1318 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_4__1__Impl_in_rule__KShapeLayout__Group_4__11349 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__YposAssignment_4_1_in_rule__KShapeLayout__Group_4__1__Impl1376 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_5__0__Impl_in_rule__KShapeLayout__Group_5__01410 = new BitSet(new long[]{0x0000001800000040L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_5__1_in_rule__KShapeLayout__Group_5__01413 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_18_in_rule__KShapeLayout__Group_5__0__Impl1441 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_5__1__Impl_in_rule__KShapeLayout__Group_5__11472 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__WidthAssignment_5_1_in_rule__KShapeLayout__Group_5__1__Impl1499 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_6__0__Impl_in_rule__KShapeLayout__Group_6__01533 = new BitSet(new long[]{0x0000001800000040L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_6__1_in_rule__KShapeLayout__Group_6__01536 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_19_in_rule__KShapeLayout__Group_6__0__Impl1564 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_6__1__Impl_in_rule__KShapeLayout__Group_6__11595 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__HeightAssignment_6_1_in_rule__KShapeLayout__Group_6__1__Impl1622 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_7__0__Impl_in_rule__KShapeLayout__Group_7__01656 = new BitSet(new long[]{0x0000000001000000L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_7__1_in_rule__KShapeLayout__Group_7__01659 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_20_in_rule__KShapeLayout__Group_7__0__Impl1687 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_7__1__Impl_in_rule__KShapeLayout__Group_7__11718 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__InsetsAssignment_7_1_in_rule__KShapeLayout__Group_7__1__Impl1745 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_8__0__Impl_in_rule__KShapeLayout__Group_8__01779 = new BitSet(new long[]{0x0000000000400000L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_8__1_in_rule__KShapeLayout__Group_8__01782 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_21_in_rule__KShapeLayout__Group_8__0__Impl1810 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_8__1__Impl_in_rule__KShapeLayout__Group_8__11841 = new BitSet(new long[]{0x0000000100000000L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_8__2_in_rule__KShapeLayout__Group_8__11844 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_rule__KShapeLayout__Group_8__1__Impl1872 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_8__2__Impl_in_rule__KShapeLayout__Group_8__21903 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_8__3_in_rule__KShapeLayout__Group_8__21906 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__PersistentEntriesAssignment_8_2_in_rule__KShapeLayout__Group_8__2__Impl1933 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_8__3__Impl_in_rule__KShapeLayout__Group_8__31963 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_8_3__0_in_rule__KShapeLayout__Group_8__3__Impl1990 = new BitSet(new long[]{0x0000000000800002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_8_3__0__Impl_in_rule__KShapeLayout__Group_8_3__02029 = new BitSet(new long[]{0x0000000100000000L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_8_3__1_in_rule__KShapeLayout__Group_8_3__02032 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_23_in_rule__KShapeLayout__Group_8_3__0__Impl2060 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_8_3__1__Impl_in_rule__KShapeLayout__Group_8_3__12091 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__PersistentEntriesAssignment_8_3_1_in_rule__KShapeLayout__Group_8_3__1__Impl2118 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group__0__Impl_in_rule__KInsets__Group__02152 = new BitSet(new long[]{0x0000000001000000L});
        public static final BitSet FOLLOW_rule__KInsets__Group__1_in_rule__KInsets__Group__02155 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group__1__Impl_in_rule__KInsets__Group__12213 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_rule__KInsets__Group__2_in_rule__KInsets__Group__12216 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_24_in_rule__KInsets__Group__1__Impl2244 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group__2__Impl_in_rule__KInsets__Group__22275 = new BitSet(new long[]{0x000000001E008000L});
        public static final BitSet FOLLOW_rule__KInsets__Group__3_in_rule__KInsets__Group__22278 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_14_in_rule__KInsets__Group__2__Impl2306 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group__3__Impl_in_rule__KInsets__Group__32337 = new BitSet(new long[]{0x000000001E008000L});
        public static final BitSet FOLLOW_rule__KInsets__Group__4_in_rule__KInsets__Group__32340 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group_3__0_in_rule__KInsets__Group__3__Impl2367 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group__4__Impl_in_rule__KInsets__Group__42398 = new BitSet(new long[]{0x000000001E008000L});
        public static final BitSet FOLLOW_rule__KInsets__Group__5_in_rule__KInsets__Group__42401 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group_4__0_in_rule__KInsets__Group__4__Impl2428 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group__5__Impl_in_rule__KInsets__Group__52459 = new BitSet(new long[]{0x000000001E008000L});
        public static final BitSet FOLLOW_rule__KInsets__Group__6_in_rule__KInsets__Group__52462 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group_5__0_in_rule__KInsets__Group__5__Impl2489 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group__6__Impl_in_rule__KInsets__Group__62520 = new BitSet(new long[]{0x000000001E008000L});
        public static final BitSet FOLLOW_rule__KInsets__Group__7_in_rule__KInsets__Group__62523 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group_6__0_in_rule__KInsets__Group__6__Impl2550 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group__7__Impl_in_rule__KInsets__Group__72581 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_15_in_rule__KInsets__Group__7__Impl2609 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group_3__0__Impl_in_rule__KInsets__Group_3__02656 = new BitSet(new long[]{0x0000001800000040L});
        public static final BitSet FOLLOW_rule__KInsets__Group_3__1_in_rule__KInsets__Group_3__02659 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_25_in_rule__KInsets__Group_3__0__Impl2687 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group_3__1__Impl_in_rule__KInsets__Group_3__12718 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__TopAssignment_3_1_in_rule__KInsets__Group_3__1__Impl2745 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group_4__0__Impl_in_rule__KInsets__Group_4__02779 = new BitSet(new long[]{0x0000001800000040L});
        public static final BitSet FOLLOW_rule__KInsets__Group_4__1_in_rule__KInsets__Group_4__02782 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_26_in_rule__KInsets__Group_4__0__Impl2810 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group_4__1__Impl_in_rule__KInsets__Group_4__12841 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__BottomAssignment_4_1_in_rule__KInsets__Group_4__1__Impl2868 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group_5__0__Impl_in_rule__KInsets__Group_5__02902 = new BitSet(new long[]{0x0000001800000040L});
        public static final BitSet FOLLOW_rule__KInsets__Group_5__1_in_rule__KInsets__Group_5__02905 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_27_in_rule__KInsets__Group_5__0__Impl2933 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group_5__1__Impl_in_rule__KInsets__Group_5__12964 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__LeftAssignment_5_1_in_rule__KInsets__Group_5__1__Impl2991 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group_6__0__Impl_in_rule__KInsets__Group_6__03025 = new BitSet(new long[]{0x0000001800000040L});
        public static final BitSet FOLLOW_rule__KInsets__Group_6__1_in_rule__KInsets__Group_6__03028 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_28_in_rule__KInsets__Group_6__0__Impl3056 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group_6__1__Impl_in_rule__KInsets__Group_6__13087 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__RightAssignment_6_1_in_rule__KInsets__Group_6__1__Impl3114 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KPoint__Group__0__Impl_in_rule__KPoint__Group__03153 = new BitSet(new long[]{0x0000000020000000L});
        public static final BitSet FOLLOW_rule__KPoint__Group__1_in_rule__KPoint__Group__03156 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KPoint__Group__1__Impl_in_rule__KPoint__Group__13214 = new BitSet(new long[]{0x0000000040000000L});
        public static final BitSet FOLLOW_rule__KPoint__Group__2_in_rule__KPoint__Group__13217 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_29_in_rule__KPoint__Group__1__Impl3245 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KPoint__Group__2__Impl_in_rule__KPoint__Group__23276 = new BitSet(new long[]{0x0000000080000000L});
        public static final BitSet FOLLOW_rule__KPoint__Group__3_in_rule__KPoint__Group__23279 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KPoint__Group_2__0_in_rule__KPoint__Group__2__Impl3306 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KPoint__Group__3__Impl_in_rule__KPoint__Group__33336 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KPoint__Group_3__0_in_rule__KPoint__Group__3__Impl3363 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KPoint__Group_2__0__Impl_in_rule__KPoint__Group_2__03401 = new BitSet(new long[]{0x0000001800000040L});
        public static final BitSet FOLLOW_rule__KPoint__Group_2__1_in_rule__KPoint__Group_2__03404 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_30_in_rule__KPoint__Group_2__0__Impl3432 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KPoint__Group_2__1__Impl_in_rule__KPoint__Group_2__13463 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KPoint__XAssignment_2_1_in_rule__KPoint__Group_2__1__Impl3490 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KPoint__Group_3__0__Impl_in_rule__KPoint__Group_3__03524 = new BitSet(new long[]{0x0000001800000040L});
        public static final BitSet FOLLOW_rule__KPoint__Group_3__1_in_rule__KPoint__Group_3__03527 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_31_in_rule__KPoint__Group_3__0__Impl3555 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KPoint__Group_3__1__Impl_in_rule__KPoint__Group_3__13586 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KPoint__YAssignment_3_1_in_rule__KPoint__Group_3__1__Impl3613 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PersistentEntry__Group__0__Impl_in_rule__PersistentEntry__Group__03647 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_rule__PersistentEntry__Group__1_in_rule__PersistentEntry__Group__03650 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_32_in_rule__PersistentEntry__Group__0__Impl3678 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PersistentEntry__Group__1__Impl_in_rule__PersistentEntry__Group__13709 = new BitSet(new long[]{0x0000000200000000L});
        public static final BitSet FOLLOW_rule__PersistentEntry__Group__2_in_rule__PersistentEntry__Group__13712 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_14_in_rule__PersistentEntry__Group__1__Impl3740 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PersistentEntry__Group__2__Impl_in_rule__PersistentEntry__Group__23771 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_rule__PersistentEntry__Group__3_in_rule__PersistentEntry__Group__23774 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_33_in_rule__PersistentEntry__Group__2__Impl3802 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PersistentEntry__Group__3__Impl_in_rule__PersistentEntry__Group__33833 = new BitSet(new long[]{0x0000000400008000L});
        public static final BitSet FOLLOW_rule__PersistentEntry__Group__4_in_rule__PersistentEntry__Group__33836 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PersistentEntry__KeyAssignment_3_in_rule__PersistentEntry__Group__3__Impl3863 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PersistentEntry__Group__4__Impl_in_rule__PersistentEntry__Group__43893 = new BitSet(new long[]{0x0000000400008000L});
        public static final BitSet FOLLOW_rule__PersistentEntry__Group__5_in_rule__PersistentEntry__Group__43896 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PersistentEntry__Group_4__0_in_rule__PersistentEntry__Group__4__Impl3923 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PersistentEntry__Group__5__Impl_in_rule__PersistentEntry__Group__53954 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_15_in_rule__PersistentEntry__Group__5__Impl3982 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PersistentEntry__Group_4__0__Impl_in_rule__PersistentEntry__Group_4__04025 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_rule__PersistentEntry__Group_4__1_in_rule__PersistentEntry__Group_4__04028 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_34_in_rule__PersistentEntry__Group_4__0__Impl4056 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PersistentEntry__Group_4__1__Impl_in_rule__PersistentEntry__Group_4__14087 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__PersistentEntry__ValueAssignment_4_1_in_rule__PersistentEntry__Group_4__1__Impl4114 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__EFloat__Group__0__Impl_in_rule__EFloat__Group__04148 = new BitSet(new long[]{0x0000001800000040L});
        public static final BitSet FOLLOW_rule__EFloat__Group__1_in_rule__EFloat__Group__04151 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_35_in_rule__EFloat__Group__0__Impl4180 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__EFloat__Group__1__Impl_in_rule__EFloat__Group__14213 = new BitSet(new long[]{0x0000001800000040L});
        public static final BitSet FOLLOW_rule__EFloat__Group__2_in_rule__EFloat__Group__14216 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_INT_in_rule__EFloat__Group__1__Impl4244 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__EFloat__Group__2__Impl_in_rule__EFloat__Group__24275 = new BitSet(new long[]{0x0000000000000040L});
        public static final BitSet FOLLOW_rule__EFloat__Group__3_in_rule__EFloat__Group__24278 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_36_in_rule__EFloat__Group__2__Impl4306 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__EFloat__Group__3__Impl_in_rule__EFloat__Group__34337 = new BitSet(new long[]{0x0000000000001800L});
        public static final BitSet FOLLOW_rule__EFloat__Group__4_in_rule__EFloat__Group__34340 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_INT_in_rule__EFloat__Group__3__Impl4367 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__EFloat__Group__4__Impl_in_rule__EFloat__Group__44396 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__EFloat__Group_4__0_in_rule__EFloat__Group__4__Impl4423 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__EFloat__Group_4__0__Impl_in_rule__EFloat__Group_4__04464 = new BitSet(new long[]{0x0000000800000040L});
        public static final BitSet FOLLOW_rule__EFloat__Group_4__1_in_rule__EFloat__Group_4__04467 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__EFloat__Alternatives_4_0_in_rule__EFloat__Group_4__0__Impl4494 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__EFloat__Group_4__1__Impl_in_rule__EFloat__Group_4__14524 = new BitSet(new long[]{0x0000000800000040L});
        public static final BitSet FOLLOW_rule__EFloat__Group_4__2_in_rule__EFloat__Group_4__14527 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_35_in_rule__EFloat__Group_4__1__Impl4556 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__EFloat__Group_4__2__Impl_in_rule__EFloat__Group_4__24589 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_INT_in_rule__EFloat__Group_4__2__Impl4616 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_rule__KShapeLayout__XposAssignment_3_14656 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_rule__KShapeLayout__YposAssignment_4_14687 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_rule__KShapeLayout__WidthAssignment_5_14718 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_rule__KShapeLayout__HeightAssignment_6_14749 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKInsets_in_rule__KShapeLayout__InsetsAssignment_7_14780 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_rule__KShapeLayout__PersistentEntriesAssignment_8_24811 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePersistentEntry_in_rule__KShapeLayout__PersistentEntriesAssignment_8_3_14842 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_rule__KInsets__TopAssignment_3_14873 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_rule__KInsets__BottomAssignment_4_14904 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_rule__KInsets__LeftAssignment_5_14935 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_rule__KInsets__RightAssignment_6_14966 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_rule__KPoint__XAssignment_2_15003 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_rule__KPoint__YAssignment_3_15034 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_rule__PersistentEntry__KeyAssignment_35065 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_rule__PersistentEntry__ValueAssignment_4_15096 = new BitSet(new long[]{0x0000000000000002L});
    }


}