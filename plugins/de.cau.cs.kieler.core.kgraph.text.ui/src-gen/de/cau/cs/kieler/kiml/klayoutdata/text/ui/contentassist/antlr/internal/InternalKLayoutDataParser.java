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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INT", "RULE_ID", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'E'", "'e'", "'KShapeLayout'", "'{'", "'}'", "'xpos'", "'ypos'", "'width'", "'height'", "'insets'", "'KInsets'", "'top'", "'bottom'", "'left'", "'right'", "'KPoint'", "'x'", "'y'", "'-'", "'.'"
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


    // $ANTLR start "entryRuleEFloat"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:148:1: entryRuleEFloat : ruleEFloat EOF ;
    public final void entryRuleEFloat() throws RecognitionException {
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:149:1: ( ruleEFloat EOF )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:150:1: ruleEFloat EOF
            {
             before(grammarAccess.getEFloatRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_entryRuleEFloat245);
            ruleEFloat();

            state._fsp--;

             after(grammarAccess.getEFloatRule()); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEFloat252); 

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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:157:1: ruleEFloat : ( ( rule__EFloat__Group__0 ) ) ;
    public final void ruleEFloat() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:161:2: ( ( ( rule__EFloat__Group__0 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:162:1: ( ( rule__EFloat__Group__0 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:162:1: ( ( rule__EFloat__Group__0 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:163:1: ( rule__EFloat__Group__0 )
            {
             before(grammarAccess.getEFloatAccess().getGroup()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:164:1: ( rule__EFloat__Group__0 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:164:2: rule__EFloat__Group__0
            {
            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group__0_in_ruleEFloat278);
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


    // $ANTLR start "rule__EFloat__Alternatives_4_0"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:177:1: rule__EFloat__Alternatives_4_0 : ( ( 'E' ) | ( 'e' ) );
    public final void rule__EFloat__Alternatives_4_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:181:1: ( ( 'E' ) | ( 'e' ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==11) ) {
                alt1=1;
            }
            else if ( (LA1_0==12) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:182:1: ( 'E' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:182:1: ( 'E' )
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:183:1: 'E'
                    {
                     before(grammarAccess.getEFloatAccess().getEKeyword_4_0_0()); 
                    match(input,11,FollowSets000.FOLLOW_11_in_rule__EFloat__Alternatives_4_0316); 
                     after(grammarAccess.getEFloatAccess().getEKeyword_4_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:190:6: ( 'e' )
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:190:6: ( 'e' )
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:191:1: 'e'
                    {
                     before(grammarAccess.getEFloatAccess().getEKeyword_4_0_1()); 
                    match(input,12,FollowSets000.FOLLOW_12_in_rule__EFloat__Alternatives_4_0336); 
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:205:1: rule__KShapeLayout__Group__0 : rule__KShapeLayout__Group__0__Impl rule__KShapeLayout__Group__1 ;
    public final void rule__KShapeLayout__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:209:1: ( rule__KShapeLayout__Group__0__Impl rule__KShapeLayout__Group__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:210:2: rule__KShapeLayout__Group__0__Impl rule__KShapeLayout__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__0__Impl_in_rule__KShapeLayout__Group__0368);
            rule__KShapeLayout__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__1_in_rule__KShapeLayout__Group__0371);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:217:1: rule__KShapeLayout__Group__0__Impl : ( () ) ;
    public final void rule__KShapeLayout__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:221:1: ( ( () ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:222:1: ( () )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:222:1: ( () )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:223:1: ()
            {
             before(grammarAccess.getKShapeLayoutAccess().getKShapeLayoutAction_0()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:224:1: ()
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:226:1: 
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:236:1: rule__KShapeLayout__Group__1 : rule__KShapeLayout__Group__1__Impl rule__KShapeLayout__Group__2 ;
    public final void rule__KShapeLayout__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:240:1: ( rule__KShapeLayout__Group__1__Impl rule__KShapeLayout__Group__2 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:241:2: rule__KShapeLayout__Group__1__Impl rule__KShapeLayout__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__1__Impl_in_rule__KShapeLayout__Group__1429);
            rule__KShapeLayout__Group__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__2_in_rule__KShapeLayout__Group__1432);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:248:1: rule__KShapeLayout__Group__1__Impl : ( 'KShapeLayout' ) ;
    public final void rule__KShapeLayout__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:252:1: ( ( 'KShapeLayout' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:253:1: ( 'KShapeLayout' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:253:1: ( 'KShapeLayout' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:254:1: 'KShapeLayout'
            {
             before(grammarAccess.getKShapeLayoutAccess().getKShapeLayoutKeyword_1()); 
            match(input,13,FollowSets000.FOLLOW_13_in_rule__KShapeLayout__Group__1__Impl460); 
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:267:1: rule__KShapeLayout__Group__2 : rule__KShapeLayout__Group__2__Impl rule__KShapeLayout__Group__3 ;
    public final void rule__KShapeLayout__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:271:1: ( rule__KShapeLayout__Group__2__Impl rule__KShapeLayout__Group__3 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:272:2: rule__KShapeLayout__Group__2__Impl rule__KShapeLayout__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__2__Impl_in_rule__KShapeLayout__Group__2491);
            rule__KShapeLayout__Group__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__3_in_rule__KShapeLayout__Group__2494);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:279:1: rule__KShapeLayout__Group__2__Impl : ( '{' ) ;
    public final void rule__KShapeLayout__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:283:1: ( ( '{' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:284:1: ( '{' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:284:1: ( '{' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:285:1: '{'
            {
             before(grammarAccess.getKShapeLayoutAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,14,FollowSets000.FOLLOW_14_in_rule__KShapeLayout__Group__2__Impl522); 
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:298:1: rule__KShapeLayout__Group__3 : rule__KShapeLayout__Group__3__Impl rule__KShapeLayout__Group__4 ;
    public final void rule__KShapeLayout__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:302:1: ( rule__KShapeLayout__Group__3__Impl rule__KShapeLayout__Group__4 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:303:2: rule__KShapeLayout__Group__3__Impl rule__KShapeLayout__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__3__Impl_in_rule__KShapeLayout__Group__3553);
            rule__KShapeLayout__Group__3__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__4_in_rule__KShapeLayout__Group__3556);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:310:1: rule__KShapeLayout__Group__3__Impl : ( ( rule__KShapeLayout__Group_3__0 )? ) ;
    public final void rule__KShapeLayout__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:314:1: ( ( ( rule__KShapeLayout__Group_3__0 )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:315:1: ( ( rule__KShapeLayout__Group_3__0 )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:315:1: ( ( rule__KShapeLayout__Group_3__0 )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:316:1: ( rule__KShapeLayout__Group_3__0 )?
            {
             before(grammarAccess.getKShapeLayoutAccess().getGroup_3()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:317:1: ( rule__KShapeLayout__Group_3__0 )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==16) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:317:2: rule__KShapeLayout__Group_3__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_3__0_in_rule__KShapeLayout__Group__3__Impl583);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:327:1: rule__KShapeLayout__Group__4 : rule__KShapeLayout__Group__4__Impl rule__KShapeLayout__Group__5 ;
    public final void rule__KShapeLayout__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:331:1: ( rule__KShapeLayout__Group__4__Impl rule__KShapeLayout__Group__5 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:332:2: rule__KShapeLayout__Group__4__Impl rule__KShapeLayout__Group__5
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__4__Impl_in_rule__KShapeLayout__Group__4614);
            rule__KShapeLayout__Group__4__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__5_in_rule__KShapeLayout__Group__4617);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:339:1: rule__KShapeLayout__Group__4__Impl : ( ( rule__KShapeLayout__Group_4__0 )? ) ;
    public final void rule__KShapeLayout__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:343:1: ( ( ( rule__KShapeLayout__Group_4__0 )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:344:1: ( ( rule__KShapeLayout__Group_4__0 )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:344:1: ( ( rule__KShapeLayout__Group_4__0 )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:345:1: ( rule__KShapeLayout__Group_4__0 )?
            {
             before(grammarAccess.getKShapeLayoutAccess().getGroup_4()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:346:1: ( rule__KShapeLayout__Group_4__0 )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==17) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:346:2: rule__KShapeLayout__Group_4__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_4__0_in_rule__KShapeLayout__Group__4__Impl644);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:356:1: rule__KShapeLayout__Group__5 : rule__KShapeLayout__Group__5__Impl rule__KShapeLayout__Group__6 ;
    public final void rule__KShapeLayout__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:360:1: ( rule__KShapeLayout__Group__5__Impl rule__KShapeLayout__Group__6 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:361:2: rule__KShapeLayout__Group__5__Impl rule__KShapeLayout__Group__6
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__5__Impl_in_rule__KShapeLayout__Group__5675);
            rule__KShapeLayout__Group__5__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__6_in_rule__KShapeLayout__Group__5678);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:368:1: rule__KShapeLayout__Group__5__Impl : ( ( rule__KShapeLayout__Group_5__0 )? ) ;
    public final void rule__KShapeLayout__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:372:1: ( ( ( rule__KShapeLayout__Group_5__0 )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:373:1: ( ( rule__KShapeLayout__Group_5__0 )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:373:1: ( ( rule__KShapeLayout__Group_5__0 )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:374:1: ( rule__KShapeLayout__Group_5__0 )?
            {
             before(grammarAccess.getKShapeLayoutAccess().getGroup_5()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:375:1: ( rule__KShapeLayout__Group_5__0 )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==18) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:375:2: rule__KShapeLayout__Group_5__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_5__0_in_rule__KShapeLayout__Group__5__Impl705);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:385:1: rule__KShapeLayout__Group__6 : rule__KShapeLayout__Group__6__Impl rule__KShapeLayout__Group__7 ;
    public final void rule__KShapeLayout__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:389:1: ( rule__KShapeLayout__Group__6__Impl rule__KShapeLayout__Group__7 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:390:2: rule__KShapeLayout__Group__6__Impl rule__KShapeLayout__Group__7
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__6__Impl_in_rule__KShapeLayout__Group__6736);
            rule__KShapeLayout__Group__6__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__7_in_rule__KShapeLayout__Group__6739);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:397:1: rule__KShapeLayout__Group__6__Impl : ( ( rule__KShapeLayout__Group_6__0 )? ) ;
    public final void rule__KShapeLayout__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:401:1: ( ( ( rule__KShapeLayout__Group_6__0 )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:402:1: ( ( rule__KShapeLayout__Group_6__0 )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:402:1: ( ( rule__KShapeLayout__Group_6__0 )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:403:1: ( rule__KShapeLayout__Group_6__0 )?
            {
             before(grammarAccess.getKShapeLayoutAccess().getGroup_6()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:404:1: ( rule__KShapeLayout__Group_6__0 )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==19) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:404:2: rule__KShapeLayout__Group_6__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_6__0_in_rule__KShapeLayout__Group__6__Impl766);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:414:1: rule__KShapeLayout__Group__7 : rule__KShapeLayout__Group__7__Impl rule__KShapeLayout__Group__8 ;
    public final void rule__KShapeLayout__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:418:1: ( rule__KShapeLayout__Group__7__Impl rule__KShapeLayout__Group__8 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:419:2: rule__KShapeLayout__Group__7__Impl rule__KShapeLayout__Group__8
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__7__Impl_in_rule__KShapeLayout__Group__7797);
            rule__KShapeLayout__Group__7__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__8_in_rule__KShapeLayout__Group__7800);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:426:1: rule__KShapeLayout__Group__7__Impl : ( ( rule__KShapeLayout__Group_7__0 )? ) ;
    public final void rule__KShapeLayout__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:430:1: ( ( ( rule__KShapeLayout__Group_7__0 )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:431:1: ( ( rule__KShapeLayout__Group_7__0 )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:431:1: ( ( rule__KShapeLayout__Group_7__0 )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:432:1: ( rule__KShapeLayout__Group_7__0 )?
            {
             before(grammarAccess.getKShapeLayoutAccess().getGroup_7()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:433:1: ( rule__KShapeLayout__Group_7__0 )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==20) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:433:2: rule__KShapeLayout__Group_7__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_7__0_in_rule__KShapeLayout__Group__7__Impl827);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:443:1: rule__KShapeLayout__Group__8 : rule__KShapeLayout__Group__8__Impl ;
    public final void rule__KShapeLayout__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:447:1: ( rule__KShapeLayout__Group__8__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:448:2: rule__KShapeLayout__Group__8__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group__8__Impl_in_rule__KShapeLayout__Group__8858);
            rule__KShapeLayout__Group__8__Impl();

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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:454:1: rule__KShapeLayout__Group__8__Impl : ( '}' ) ;
    public final void rule__KShapeLayout__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:458:1: ( ( '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:459:1: ( '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:459:1: ( '}' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:460:1: '}'
            {
             before(grammarAccess.getKShapeLayoutAccess().getRightCurlyBracketKeyword_8()); 
            match(input,15,FollowSets000.FOLLOW_15_in_rule__KShapeLayout__Group__8__Impl886); 
             after(grammarAccess.getKShapeLayoutAccess().getRightCurlyBracketKeyword_8()); 

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


    // $ANTLR start "rule__KShapeLayout__Group_3__0"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:491:1: rule__KShapeLayout__Group_3__0 : rule__KShapeLayout__Group_3__0__Impl rule__KShapeLayout__Group_3__1 ;
    public final void rule__KShapeLayout__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:495:1: ( rule__KShapeLayout__Group_3__0__Impl rule__KShapeLayout__Group_3__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:496:2: rule__KShapeLayout__Group_3__0__Impl rule__KShapeLayout__Group_3__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_3__0__Impl_in_rule__KShapeLayout__Group_3__0935);
            rule__KShapeLayout__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_3__1_in_rule__KShapeLayout__Group_3__0938);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:503:1: rule__KShapeLayout__Group_3__0__Impl : ( 'xpos' ) ;
    public final void rule__KShapeLayout__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:507:1: ( ( 'xpos' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:508:1: ( 'xpos' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:508:1: ( 'xpos' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:509:1: 'xpos'
            {
             before(grammarAccess.getKShapeLayoutAccess().getXposKeyword_3_0()); 
            match(input,16,FollowSets000.FOLLOW_16_in_rule__KShapeLayout__Group_3__0__Impl966); 
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:522:1: rule__KShapeLayout__Group_3__1 : rule__KShapeLayout__Group_3__1__Impl ;
    public final void rule__KShapeLayout__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:526:1: ( rule__KShapeLayout__Group_3__1__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:527:2: rule__KShapeLayout__Group_3__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_3__1__Impl_in_rule__KShapeLayout__Group_3__1997);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:533:1: rule__KShapeLayout__Group_3__1__Impl : ( ( rule__KShapeLayout__XposAssignment_3_1 ) ) ;
    public final void rule__KShapeLayout__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:537:1: ( ( ( rule__KShapeLayout__XposAssignment_3_1 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:538:1: ( ( rule__KShapeLayout__XposAssignment_3_1 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:538:1: ( ( rule__KShapeLayout__XposAssignment_3_1 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:539:1: ( rule__KShapeLayout__XposAssignment_3_1 )
            {
             before(grammarAccess.getKShapeLayoutAccess().getXposAssignment_3_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:540:1: ( rule__KShapeLayout__XposAssignment_3_1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:540:2: rule__KShapeLayout__XposAssignment_3_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__XposAssignment_3_1_in_rule__KShapeLayout__Group_3__1__Impl1024);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:554:1: rule__KShapeLayout__Group_4__0 : rule__KShapeLayout__Group_4__0__Impl rule__KShapeLayout__Group_4__1 ;
    public final void rule__KShapeLayout__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:558:1: ( rule__KShapeLayout__Group_4__0__Impl rule__KShapeLayout__Group_4__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:559:2: rule__KShapeLayout__Group_4__0__Impl rule__KShapeLayout__Group_4__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_4__0__Impl_in_rule__KShapeLayout__Group_4__01058);
            rule__KShapeLayout__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_4__1_in_rule__KShapeLayout__Group_4__01061);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:566:1: rule__KShapeLayout__Group_4__0__Impl : ( 'ypos' ) ;
    public final void rule__KShapeLayout__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:570:1: ( ( 'ypos' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:571:1: ( 'ypos' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:571:1: ( 'ypos' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:572:1: 'ypos'
            {
             before(grammarAccess.getKShapeLayoutAccess().getYposKeyword_4_0()); 
            match(input,17,FollowSets000.FOLLOW_17_in_rule__KShapeLayout__Group_4__0__Impl1089); 
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:585:1: rule__KShapeLayout__Group_4__1 : rule__KShapeLayout__Group_4__1__Impl ;
    public final void rule__KShapeLayout__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:589:1: ( rule__KShapeLayout__Group_4__1__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:590:2: rule__KShapeLayout__Group_4__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_4__1__Impl_in_rule__KShapeLayout__Group_4__11120);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:596:1: rule__KShapeLayout__Group_4__1__Impl : ( ( rule__KShapeLayout__YposAssignment_4_1 ) ) ;
    public final void rule__KShapeLayout__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:600:1: ( ( ( rule__KShapeLayout__YposAssignment_4_1 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:601:1: ( ( rule__KShapeLayout__YposAssignment_4_1 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:601:1: ( ( rule__KShapeLayout__YposAssignment_4_1 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:602:1: ( rule__KShapeLayout__YposAssignment_4_1 )
            {
             before(grammarAccess.getKShapeLayoutAccess().getYposAssignment_4_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:603:1: ( rule__KShapeLayout__YposAssignment_4_1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:603:2: rule__KShapeLayout__YposAssignment_4_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__YposAssignment_4_1_in_rule__KShapeLayout__Group_4__1__Impl1147);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:617:1: rule__KShapeLayout__Group_5__0 : rule__KShapeLayout__Group_5__0__Impl rule__KShapeLayout__Group_5__1 ;
    public final void rule__KShapeLayout__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:621:1: ( rule__KShapeLayout__Group_5__0__Impl rule__KShapeLayout__Group_5__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:622:2: rule__KShapeLayout__Group_5__0__Impl rule__KShapeLayout__Group_5__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_5__0__Impl_in_rule__KShapeLayout__Group_5__01181);
            rule__KShapeLayout__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_5__1_in_rule__KShapeLayout__Group_5__01184);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:629:1: rule__KShapeLayout__Group_5__0__Impl : ( 'width' ) ;
    public final void rule__KShapeLayout__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:633:1: ( ( 'width' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:634:1: ( 'width' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:634:1: ( 'width' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:635:1: 'width'
            {
             before(grammarAccess.getKShapeLayoutAccess().getWidthKeyword_5_0()); 
            match(input,18,FollowSets000.FOLLOW_18_in_rule__KShapeLayout__Group_5__0__Impl1212); 
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:648:1: rule__KShapeLayout__Group_5__1 : rule__KShapeLayout__Group_5__1__Impl ;
    public final void rule__KShapeLayout__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:652:1: ( rule__KShapeLayout__Group_5__1__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:653:2: rule__KShapeLayout__Group_5__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_5__1__Impl_in_rule__KShapeLayout__Group_5__11243);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:659:1: rule__KShapeLayout__Group_5__1__Impl : ( ( rule__KShapeLayout__WidthAssignment_5_1 ) ) ;
    public final void rule__KShapeLayout__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:663:1: ( ( ( rule__KShapeLayout__WidthAssignment_5_1 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:664:1: ( ( rule__KShapeLayout__WidthAssignment_5_1 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:664:1: ( ( rule__KShapeLayout__WidthAssignment_5_1 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:665:1: ( rule__KShapeLayout__WidthAssignment_5_1 )
            {
             before(grammarAccess.getKShapeLayoutAccess().getWidthAssignment_5_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:666:1: ( rule__KShapeLayout__WidthAssignment_5_1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:666:2: rule__KShapeLayout__WidthAssignment_5_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__WidthAssignment_5_1_in_rule__KShapeLayout__Group_5__1__Impl1270);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:680:1: rule__KShapeLayout__Group_6__0 : rule__KShapeLayout__Group_6__0__Impl rule__KShapeLayout__Group_6__1 ;
    public final void rule__KShapeLayout__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:684:1: ( rule__KShapeLayout__Group_6__0__Impl rule__KShapeLayout__Group_6__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:685:2: rule__KShapeLayout__Group_6__0__Impl rule__KShapeLayout__Group_6__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_6__0__Impl_in_rule__KShapeLayout__Group_6__01304);
            rule__KShapeLayout__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_6__1_in_rule__KShapeLayout__Group_6__01307);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:692:1: rule__KShapeLayout__Group_6__0__Impl : ( 'height' ) ;
    public final void rule__KShapeLayout__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:696:1: ( ( 'height' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:697:1: ( 'height' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:697:1: ( 'height' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:698:1: 'height'
            {
             before(grammarAccess.getKShapeLayoutAccess().getHeightKeyword_6_0()); 
            match(input,19,FollowSets000.FOLLOW_19_in_rule__KShapeLayout__Group_6__0__Impl1335); 
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:711:1: rule__KShapeLayout__Group_6__1 : rule__KShapeLayout__Group_6__1__Impl ;
    public final void rule__KShapeLayout__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:715:1: ( rule__KShapeLayout__Group_6__1__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:716:2: rule__KShapeLayout__Group_6__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_6__1__Impl_in_rule__KShapeLayout__Group_6__11366);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:722:1: rule__KShapeLayout__Group_6__1__Impl : ( ( rule__KShapeLayout__HeightAssignment_6_1 ) ) ;
    public final void rule__KShapeLayout__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:726:1: ( ( ( rule__KShapeLayout__HeightAssignment_6_1 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:727:1: ( ( rule__KShapeLayout__HeightAssignment_6_1 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:727:1: ( ( rule__KShapeLayout__HeightAssignment_6_1 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:728:1: ( rule__KShapeLayout__HeightAssignment_6_1 )
            {
             before(grammarAccess.getKShapeLayoutAccess().getHeightAssignment_6_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:729:1: ( rule__KShapeLayout__HeightAssignment_6_1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:729:2: rule__KShapeLayout__HeightAssignment_6_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__HeightAssignment_6_1_in_rule__KShapeLayout__Group_6__1__Impl1393);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:743:1: rule__KShapeLayout__Group_7__0 : rule__KShapeLayout__Group_7__0__Impl rule__KShapeLayout__Group_7__1 ;
    public final void rule__KShapeLayout__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:747:1: ( rule__KShapeLayout__Group_7__0__Impl rule__KShapeLayout__Group_7__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:748:2: rule__KShapeLayout__Group_7__0__Impl rule__KShapeLayout__Group_7__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_7__0__Impl_in_rule__KShapeLayout__Group_7__01427);
            rule__KShapeLayout__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_7__1_in_rule__KShapeLayout__Group_7__01430);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:755:1: rule__KShapeLayout__Group_7__0__Impl : ( 'insets' ) ;
    public final void rule__KShapeLayout__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:759:1: ( ( 'insets' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:760:1: ( 'insets' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:760:1: ( 'insets' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:761:1: 'insets'
            {
             before(grammarAccess.getKShapeLayoutAccess().getInsetsKeyword_7_0()); 
            match(input,20,FollowSets000.FOLLOW_20_in_rule__KShapeLayout__Group_7__0__Impl1458); 
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:774:1: rule__KShapeLayout__Group_7__1 : rule__KShapeLayout__Group_7__1__Impl ;
    public final void rule__KShapeLayout__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:778:1: ( rule__KShapeLayout__Group_7__1__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:779:2: rule__KShapeLayout__Group_7__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__Group_7__1__Impl_in_rule__KShapeLayout__Group_7__11489);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:785:1: rule__KShapeLayout__Group_7__1__Impl : ( ( rule__KShapeLayout__InsetsAssignment_7_1 ) ) ;
    public final void rule__KShapeLayout__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:789:1: ( ( ( rule__KShapeLayout__InsetsAssignment_7_1 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:790:1: ( ( rule__KShapeLayout__InsetsAssignment_7_1 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:790:1: ( ( rule__KShapeLayout__InsetsAssignment_7_1 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:791:1: ( rule__KShapeLayout__InsetsAssignment_7_1 )
            {
             before(grammarAccess.getKShapeLayoutAccess().getInsetsAssignment_7_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:792:1: ( rule__KShapeLayout__InsetsAssignment_7_1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:792:2: rule__KShapeLayout__InsetsAssignment_7_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KShapeLayout__InsetsAssignment_7_1_in_rule__KShapeLayout__Group_7__1__Impl1516);
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


    // $ANTLR start "rule__KInsets__Group__0"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:806:1: rule__KInsets__Group__0 : rule__KInsets__Group__0__Impl rule__KInsets__Group__1 ;
    public final void rule__KInsets__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:810:1: ( rule__KInsets__Group__0__Impl rule__KInsets__Group__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:811:2: rule__KInsets__Group__0__Impl rule__KInsets__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__0__Impl_in_rule__KInsets__Group__01550);
            rule__KInsets__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__1_in_rule__KInsets__Group__01553);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:818:1: rule__KInsets__Group__0__Impl : ( () ) ;
    public final void rule__KInsets__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:822:1: ( ( () ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:823:1: ( () )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:823:1: ( () )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:824:1: ()
            {
             before(grammarAccess.getKInsetsAccess().getKInsetsAction_0()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:825:1: ()
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:827:1: 
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:837:1: rule__KInsets__Group__1 : rule__KInsets__Group__1__Impl rule__KInsets__Group__2 ;
    public final void rule__KInsets__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:841:1: ( rule__KInsets__Group__1__Impl rule__KInsets__Group__2 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:842:2: rule__KInsets__Group__1__Impl rule__KInsets__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__1__Impl_in_rule__KInsets__Group__11611);
            rule__KInsets__Group__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__2_in_rule__KInsets__Group__11614);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:849:1: rule__KInsets__Group__1__Impl : ( 'KInsets' ) ;
    public final void rule__KInsets__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:853:1: ( ( 'KInsets' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:854:1: ( 'KInsets' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:854:1: ( 'KInsets' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:855:1: 'KInsets'
            {
             before(grammarAccess.getKInsetsAccess().getKInsetsKeyword_1()); 
            match(input,21,FollowSets000.FOLLOW_21_in_rule__KInsets__Group__1__Impl1642); 
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:868:1: rule__KInsets__Group__2 : rule__KInsets__Group__2__Impl rule__KInsets__Group__3 ;
    public final void rule__KInsets__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:872:1: ( rule__KInsets__Group__2__Impl rule__KInsets__Group__3 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:873:2: rule__KInsets__Group__2__Impl rule__KInsets__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__2__Impl_in_rule__KInsets__Group__21673);
            rule__KInsets__Group__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__3_in_rule__KInsets__Group__21676);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:880:1: rule__KInsets__Group__2__Impl : ( '{' ) ;
    public final void rule__KInsets__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:884:1: ( ( '{' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:885:1: ( '{' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:885:1: ( '{' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:886:1: '{'
            {
             before(grammarAccess.getKInsetsAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,14,FollowSets000.FOLLOW_14_in_rule__KInsets__Group__2__Impl1704); 
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:899:1: rule__KInsets__Group__3 : rule__KInsets__Group__3__Impl rule__KInsets__Group__4 ;
    public final void rule__KInsets__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:903:1: ( rule__KInsets__Group__3__Impl rule__KInsets__Group__4 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:904:2: rule__KInsets__Group__3__Impl rule__KInsets__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__3__Impl_in_rule__KInsets__Group__31735);
            rule__KInsets__Group__3__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__4_in_rule__KInsets__Group__31738);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:911:1: rule__KInsets__Group__3__Impl : ( ( rule__KInsets__Group_3__0 )? ) ;
    public final void rule__KInsets__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:915:1: ( ( ( rule__KInsets__Group_3__0 )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:916:1: ( ( rule__KInsets__Group_3__0 )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:916:1: ( ( rule__KInsets__Group_3__0 )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:917:1: ( rule__KInsets__Group_3__0 )?
            {
             before(grammarAccess.getKInsetsAccess().getGroup_3()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:918:1: ( rule__KInsets__Group_3__0 )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==22) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:918:2: rule__KInsets__Group_3__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_3__0_in_rule__KInsets__Group__3__Impl1765);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:928:1: rule__KInsets__Group__4 : rule__KInsets__Group__4__Impl rule__KInsets__Group__5 ;
    public final void rule__KInsets__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:932:1: ( rule__KInsets__Group__4__Impl rule__KInsets__Group__5 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:933:2: rule__KInsets__Group__4__Impl rule__KInsets__Group__5
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__4__Impl_in_rule__KInsets__Group__41796);
            rule__KInsets__Group__4__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__5_in_rule__KInsets__Group__41799);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:940:1: rule__KInsets__Group__4__Impl : ( ( rule__KInsets__Group_4__0 )? ) ;
    public final void rule__KInsets__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:944:1: ( ( ( rule__KInsets__Group_4__0 )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:945:1: ( ( rule__KInsets__Group_4__0 )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:945:1: ( ( rule__KInsets__Group_4__0 )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:946:1: ( rule__KInsets__Group_4__0 )?
            {
             before(grammarAccess.getKInsetsAccess().getGroup_4()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:947:1: ( rule__KInsets__Group_4__0 )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==23) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:947:2: rule__KInsets__Group_4__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_4__0_in_rule__KInsets__Group__4__Impl1826);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:957:1: rule__KInsets__Group__5 : rule__KInsets__Group__5__Impl rule__KInsets__Group__6 ;
    public final void rule__KInsets__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:961:1: ( rule__KInsets__Group__5__Impl rule__KInsets__Group__6 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:962:2: rule__KInsets__Group__5__Impl rule__KInsets__Group__6
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__5__Impl_in_rule__KInsets__Group__51857);
            rule__KInsets__Group__5__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__6_in_rule__KInsets__Group__51860);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:969:1: rule__KInsets__Group__5__Impl : ( ( rule__KInsets__Group_5__0 )? ) ;
    public final void rule__KInsets__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:973:1: ( ( ( rule__KInsets__Group_5__0 )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:974:1: ( ( rule__KInsets__Group_5__0 )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:974:1: ( ( rule__KInsets__Group_5__0 )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:975:1: ( rule__KInsets__Group_5__0 )?
            {
             before(grammarAccess.getKInsetsAccess().getGroup_5()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:976:1: ( rule__KInsets__Group_5__0 )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==24) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:976:2: rule__KInsets__Group_5__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_5__0_in_rule__KInsets__Group__5__Impl1887);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:986:1: rule__KInsets__Group__6 : rule__KInsets__Group__6__Impl rule__KInsets__Group__7 ;
    public final void rule__KInsets__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:990:1: ( rule__KInsets__Group__6__Impl rule__KInsets__Group__7 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:991:2: rule__KInsets__Group__6__Impl rule__KInsets__Group__7
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__6__Impl_in_rule__KInsets__Group__61918);
            rule__KInsets__Group__6__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__7_in_rule__KInsets__Group__61921);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:998:1: rule__KInsets__Group__6__Impl : ( ( rule__KInsets__Group_6__0 )? ) ;
    public final void rule__KInsets__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1002:1: ( ( ( rule__KInsets__Group_6__0 )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1003:1: ( ( rule__KInsets__Group_6__0 )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1003:1: ( ( rule__KInsets__Group_6__0 )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1004:1: ( rule__KInsets__Group_6__0 )?
            {
             before(grammarAccess.getKInsetsAccess().getGroup_6()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1005:1: ( rule__KInsets__Group_6__0 )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==25) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1005:2: rule__KInsets__Group_6__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_6__0_in_rule__KInsets__Group__6__Impl1948);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1015:1: rule__KInsets__Group__7 : rule__KInsets__Group__7__Impl ;
    public final void rule__KInsets__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1019:1: ( rule__KInsets__Group__7__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1020:2: rule__KInsets__Group__7__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group__7__Impl_in_rule__KInsets__Group__71979);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1026:1: rule__KInsets__Group__7__Impl : ( '}' ) ;
    public final void rule__KInsets__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1030:1: ( ( '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1031:1: ( '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1031:1: ( '}' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1032:1: '}'
            {
             before(grammarAccess.getKInsetsAccess().getRightCurlyBracketKeyword_7()); 
            match(input,15,FollowSets000.FOLLOW_15_in_rule__KInsets__Group__7__Impl2007); 
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1061:1: rule__KInsets__Group_3__0 : rule__KInsets__Group_3__0__Impl rule__KInsets__Group_3__1 ;
    public final void rule__KInsets__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1065:1: ( rule__KInsets__Group_3__0__Impl rule__KInsets__Group_3__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1066:2: rule__KInsets__Group_3__0__Impl rule__KInsets__Group_3__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_3__0__Impl_in_rule__KInsets__Group_3__02054);
            rule__KInsets__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_3__1_in_rule__KInsets__Group_3__02057);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1073:1: rule__KInsets__Group_3__0__Impl : ( 'top' ) ;
    public final void rule__KInsets__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1077:1: ( ( 'top' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1078:1: ( 'top' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1078:1: ( 'top' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1079:1: 'top'
            {
             before(grammarAccess.getKInsetsAccess().getTopKeyword_3_0()); 
            match(input,22,FollowSets000.FOLLOW_22_in_rule__KInsets__Group_3__0__Impl2085); 
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1092:1: rule__KInsets__Group_3__1 : rule__KInsets__Group_3__1__Impl ;
    public final void rule__KInsets__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1096:1: ( rule__KInsets__Group_3__1__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1097:2: rule__KInsets__Group_3__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_3__1__Impl_in_rule__KInsets__Group_3__12116);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1103:1: rule__KInsets__Group_3__1__Impl : ( ( rule__KInsets__TopAssignment_3_1 ) ) ;
    public final void rule__KInsets__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1107:1: ( ( ( rule__KInsets__TopAssignment_3_1 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1108:1: ( ( rule__KInsets__TopAssignment_3_1 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1108:1: ( ( rule__KInsets__TopAssignment_3_1 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1109:1: ( rule__KInsets__TopAssignment_3_1 )
            {
             before(grammarAccess.getKInsetsAccess().getTopAssignment_3_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1110:1: ( rule__KInsets__TopAssignment_3_1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1110:2: rule__KInsets__TopAssignment_3_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__TopAssignment_3_1_in_rule__KInsets__Group_3__1__Impl2143);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1124:1: rule__KInsets__Group_4__0 : rule__KInsets__Group_4__0__Impl rule__KInsets__Group_4__1 ;
    public final void rule__KInsets__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1128:1: ( rule__KInsets__Group_4__0__Impl rule__KInsets__Group_4__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1129:2: rule__KInsets__Group_4__0__Impl rule__KInsets__Group_4__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_4__0__Impl_in_rule__KInsets__Group_4__02177);
            rule__KInsets__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_4__1_in_rule__KInsets__Group_4__02180);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1136:1: rule__KInsets__Group_4__0__Impl : ( 'bottom' ) ;
    public final void rule__KInsets__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1140:1: ( ( 'bottom' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1141:1: ( 'bottom' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1141:1: ( 'bottom' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1142:1: 'bottom'
            {
             before(grammarAccess.getKInsetsAccess().getBottomKeyword_4_0()); 
            match(input,23,FollowSets000.FOLLOW_23_in_rule__KInsets__Group_4__0__Impl2208); 
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1155:1: rule__KInsets__Group_4__1 : rule__KInsets__Group_4__1__Impl ;
    public final void rule__KInsets__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1159:1: ( rule__KInsets__Group_4__1__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1160:2: rule__KInsets__Group_4__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_4__1__Impl_in_rule__KInsets__Group_4__12239);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1166:1: rule__KInsets__Group_4__1__Impl : ( ( rule__KInsets__BottomAssignment_4_1 ) ) ;
    public final void rule__KInsets__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1170:1: ( ( ( rule__KInsets__BottomAssignment_4_1 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1171:1: ( ( rule__KInsets__BottomAssignment_4_1 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1171:1: ( ( rule__KInsets__BottomAssignment_4_1 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1172:1: ( rule__KInsets__BottomAssignment_4_1 )
            {
             before(grammarAccess.getKInsetsAccess().getBottomAssignment_4_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1173:1: ( rule__KInsets__BottomAssignment_4_1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1173:2: rule__KInsets__BottomAssignment_4_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__BottomAssignment_4_1_in_rule__KInsets__Group_4__1__Impl2266);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1187:1: rule__KInsets__Group_5__0 : rule__KInsets__Group_5__0__Impl rule__KInsets__Group_5__1 ;
    public final void rule__KInsets__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1191:1: ( rule__KInsets__Group_5__0__Impl rule__KInsets__Group_5__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1192:2: rule__KInsets__Group_5__0__Impl rule__KInsets__Group_5__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_5__0__Impl_in_rule__KInsets__Group_5__02300);
            rule__KInsets__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_5__1_in_rule__KInsets__Group_5__02303);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1199:1: rule__KInsets__Group_5__0__Impl : ( 'left' ) ;
    public final void rule__KInsets__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1203:1: ( ( 'left' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1204:1: ( 'left' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1204:1: ( 'left' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1205:1: 'left'
            {
             before(grammarAccess.getKInsetsAccess().getLeftKeyword_5_0()); 
            match(input,24,FollowSets000.FOLLOW_24_in_rule__KInsets__Group_5__0__Impl2331); 
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1218:1: rule__KInsets__Group_5__1 : rule__KInsets__Group_5__1__Impl ;
    public final void rule__KInsets__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1222:1: ( rule__KInsets__Group_5__1__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1223:2: rule__KInsets__Group_5__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_5__1__Impl_in_rule__KInsets__Group_5__12362);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1229:1: rule__KInsets__Group_5__1__Impl : ( ( rule__KInsets__LeftAssignment_5_1 ) ) ;
    public final void rule__KInsets__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1233:1: ( ( ( rule__KInsets__LeftAssignment_5_1 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1234:1: ( ( rule__KInsets__LeftAssignment_5_1 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1234:1: ( ( rule__KInsets__LeftAssignment_5_1 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1235:1: ( rule__KInsets__LeftAssignment_5_1 )
            {
             before(grammarAccess.getKInsetsAccess().getLeftAssignment_5_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1236:1: ( rule__KInsets__LeftAssignment_5_1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1236:2: rule__KInsets__LeftAssignment_5_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__LeftAssignment_5_1_in_rule__KInsets__Group_5__1__Impl2389);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1250:1: rule__KInsets__Group_6__0 : rule__KInsets__Group_6__0__Impl rule__KInsets__Group_6__1 ;
    public final void rule__KInsets__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1254:1: ( rule__KInsets__Group_6__0__Impl rule__KInsets__Group_6__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1255:2: rule__KInsets__Group_6__0__Impl rule__KInsets__Group_6__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_6__0__Impl_in_rule__KInsets__Group_6__02423);
            rule__KInsets__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_6__1_in_rule__KInsets__Group_6__02426);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1262:1: rule__KInsets__Group_6__0__Impl : ( 'right' ) ;
    public final void rule__KInsets__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1266:1: ( ( 'right' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1267:1: ( 'right' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1267:1: ( 'right' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1268:1: 'right'
            {
             before(grammarAccess.getKInsetsAccess().getRightKeyword_6_0()); 
            match(input,25,FollowSets000.FOLLOW_25_in_rule__KInsets__Group_6__0__Impl2454); 
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1281:1: rule__KInsets__Group_6__1 : rule__KInsets__Group_6__1__Impl ;
    public final void rule__KInsets__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1285:1: ( rule__KInsets__Group_6__1__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1286:2: rule__KInsets__Group_6__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__Group_6__1__Impl_in_rule__KInsets__Group_6__12485);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1292:1: rule__KInsets__Group_6__1__Impl : ( ( rule__KInsets__RightAssignment_6_1 ) ) ;
    public final void rule__KInsets__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1296:1: ( ( ( rule__KInsets__RightAssignment_6_1 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1297:1: ( ( rule__KInsets__RightAssignment_6_1 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1297:1: ( ( rule__KInsets__RightAssignment_6_1 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1298:1: ( rule__KInsets__RightAssignment_6_1 )
            {
             before(grammarAccess.getKInsetsAccess().getRightAssignment_6_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1299:1: ( rule__KInsets__RightAssignment_6_1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1299:2: rule__KInsets__RightAssignment_6_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KInsets__RightAssignment_6_1_in_rule__KInsets__Group_6__1__Impl2512);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1316:1: rule__KPoint__Group__0 : rule__KPoint__Group__0__Impl rule__KPoint__Group__1 ;
    public final void rule__KPoint__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1320:1: ( rule__KPoint__Group__0__Impl rule__KPoint__Group__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1321:2: rule__KPoint__Group__0__Impl rule__KPoint__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group__0__Impl_in_rule__KPoint__Group__02549);
            rule__KPoint__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group__1_in_rule__KPoint__Group__02552);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1328:1: rule__KPoint__Group__0__Impl : ( () ) ;
    public final void rule__KPoint__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1332:1: ( ( () ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1333:1: ( () )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1333:1: ( () )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1334:1: ()
            {
             before(grammarAccess.getKPointAccess().getKPointAction_0()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1335:1: ()
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1337:1: 
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1347:1: rule__KPoint__Group__1 : rule__KPoint__Group__1__Impl rule__KPoint__Group__2 ;
    public final void rule__KPoint__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1351:1: ( rule__KPoint__Group__1__Impl rule__KPoint__Group__2 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1352:2: rule__KPoint__Group__1__Impl rule__KPoint__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group__1__Impl_in_rule__KPoint__Group__12610);
            rule__KPoint__Group__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group__2_in_rule__KPoint__Group__12613);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1359:1: rule__KPoint__Group__1__Impl : ( 'KPoint' ) ;
    public final void rule__KPoint__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1363:1: ( ( 'KPoint' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1364:1: ( 'KPoint' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1364:1: ( 'KPoint' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1365:1: 'KPoint'
            {
             before(grammarAccess.getKPointAccess().getKPointKeyword_1()); 
            match(input,26,FollowSets000.FOLLOW_26_in_rule__KPoint__Group__1__Impl2641); 
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1378:1: rule__KPoint__Group__2 : rule__KPoint__Group__2__Impl rule__KPoint__Group__3 ;
    public final void rule__KPoint__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1382:1: ( rule__KPoint__Group__2__Impl rule__KPoint__Group__3 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1383:2: rule__KPoint__Group__2__Impl rule__KPoint__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group__2__Impl_in_rule__KPoint__Group__22672);
            rule__KPoint__Group__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group__3_in_rule__KPoint__Group__22675);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1390:1: rule__KPoint__Group__2__Impl : ( '{' ) ;
    public final void rule__KPoint__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1394:1: ( ( '{' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1395:1: ( '{' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1395:1: ( '{' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1396:1: '{'
            {
             before(grammarAccess.getKPointAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,14,FollowSets000.FOLLOW_14_in_rule__KPoint__Group__2__Impl2703); 
             after(grammarAccess.getKPointAccess().getLeftCurlyBracketKeyword_2()); 

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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1409:1: rule__KPoint__Group__3 : rule__KPoint__Group__3__Impl rule__KPoint__Group__4 ;
    public final void rule__KPoint__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1413:1: ( rule__KPoint__Group__3__Impl rule__KPoint__Group__4 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1414:2: rule__KPoint__Group__3__Impl rule__KPoint__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group__3__Impl_in_rule__KPoint__Group__32734);
            rule__KPoint__Group__3__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group__4_in_rule__KPoint__Group__32737);
            rule__KPoint__Group__4();

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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1421:1: rule__KPoint__Group__3__Impl : ( ( rule__KPoint__Group_3__0 )? ) ;
    public final void rule__KPoint__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1425:1: ( ( ( rule__KPoint__Group_3__0 )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1426:1: ( ( rule__KPoint__Group_3__0 )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1426:1: ( ( rule__KPoint__Group_3__0 )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1427:1: ( rule__KPoint__Group_3__0 )?
            {
             before(grammarAccess.getKPointAccess().getGroup_3()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1428:1: ( rule__KPoint__Group_3__0 )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==27) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1428:2: rule__KPoint__Group_3__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group_3__0_in_rule__KPoint__Group__3__Impl2764);
                    rule__KPoint__Group_3__0();

                    state._fsp--;


                    }
                    break;

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


    // $ANTLR start "rule__KPoint__Group__4"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1438:1: rule__KPoint__Group__4 : rule__KPoint__Group__4__Impl rule__KPoint__Group__5 ;
    public final void rule__KPoint__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1442:1: ( rule__KPoint__Group__4__Impl rule__KPoint__Group__5 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1443:2: rule__KPoint__Group__4__Impl rule__KPoint__Group__5
            {
            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group__4__Impl_in_rule__KPoint__Group__42795);
            rule__KPoint__Group__4__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group__5_in_rule__KPoint__Group__42798);
            rule__KPoint__Group__5();

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
    // $ANTLR end "rule__KPoint__Group__4"


    // $ANTLR start "rule__KPoint__Group__4__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1450:1: rule__KPoint__Group__4__Impl : ( ( rule__KPoint__Group_4__0 )? ) ;
    public final void rule__KPoint__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1454:1: ( ( ( rule__KPoint__Group_4__0 )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1455:1: ( ( rule__KPoint__Group_4__0 )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1455:1: ( ( rule__KPoint__Group_4__0 )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1456:1: ( rule__KPoint__Group_4__0 )?
            {
             before(grammarAccess.getKPointAccess().getGroup_4()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1457:1: ( rule__KPoint__Group_4__0 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==28) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1457:2: rule__KPoint__Group_4__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group_4__0_in_rule__KPoint__Group__4__Impl2825);
                    rule__KPoint__Group_4__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getKPointAccess().getGroup_4()); 

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
    // $ANTLR end "rule__KPoint__Group__4__Impl"


    // $ANTLR start "rule__KPoint__Group__5"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1467:1: rule__KPoint__Group__5 : rule__KPoint__Group__5__Impl ;
    public final void rule__KPoint__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1471:1: ( rule__KPoint__Group__5__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1472:2: rule__KPoint__Group__5__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group__5__Impl_in_rule__KPoint__Group__52856);
            rule__KPoint__Group__5__Impl();

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
    // $ANTLR end "rule__KPoint__Group__5"


    // $ANTLR start "rule__KPoint__Group__5__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1478:1: rule__KPoint__Group__5__Impl : ( '}' ) ;
    public final void rule__KPoint__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1482:1: ( ( '}' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1483:1: ( '}' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1483:1: ( '}' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1484:1: '}'
            {
             before(grammarAccess.getKPointAccess().getRightCurlyBracketKeyword_5()); 
            match(input,15,FollowSets000.FOLLOW_15_in_rule__KPoint__Group__5__Impl2884); 
             after(grammarAccess.getKPointAccess().getRightCurlyBracketKeyword_5()); 

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
    // $ANTLR end "rule__KPoint__Group__5__Impl"


    // $ANTLR start "rule__KPoint__Group_3__0"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1509:1: rule__KPoint__Group_3__0 : rule__KPoint__Group_3__0__Impl rule__KPoint__Group_3__1 ;
    public final void rule__KPoint__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1513:1: ( rule__KPoint__Group_3__0__Impl rule__KPoint__Group_3__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1514:2: rule__KPoint__Group_3__0__Impl rule__KPoint__Group_3__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group_3__0__Impl_in_rule__KPoint__Group_3__02927);
            rule__KPoint__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group_3__1_in_rule__KPoint__Group_3__02930);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1521:1: rule__KPoint__Group_3__0__Impl : ( 'x' ) ;
    public final void rule__KPoint__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1525:1: ( ( 'x' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1526:1: ( 'x' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1526:1: ( 'x' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1527:1: 'x'
            {
             before(grammarAccess.getKPointAccess().getXKeyword_3_0()); 
            match(input,27,FollowSets000.FOLLOW_27_in_rule__KPoint__Group_3__0__Impl2958); 
             after(grammarAccess.getKPointAccess().getXKeyword_3_0()); 

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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1540:1: rule__KPoint__Group_3__1 : rule__KPoint__Group_3__1__Impl ;
    public final void rule__KPoint__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1544:1: ( rule__KPoint__Group_3__1__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1545:2: rule__KPoint__Group_3__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group_3__1__Impl_in_rule__KPoint__Group_3__12989);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1551:1: rule__KPoint__Group_3__1__Impl : ( ( rule__KPoint__XAssignment_3_1 ) ) ;
    public final void rule__KPoint__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1555:1: ( ( ( rule__KPoint__XAssignment_3_1 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1556:1: ( ( rule__KPoint__XAssignment_3_1 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1556:1: ( ( rule__KPoint__XAssignment_3_1 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1557:1: ( rule__KPoint__XAssignment_3_1 )
            {
             before(grammarAccess.getKPointAccess().getXAssignment_3_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1558:1: ( rule__KPoint__XAssignment_3_1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1558:2: rule__KPoint__XAssignment_3_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KPoint__XAssignment_3_1_in_rule__KPoint__Group_3__1__Impl3016);
            rule__KPoint__XAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getKPointAccess().getXAssignment_3_1()); 

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


    // $ANTLR start "rule__KPoint__Group_4__0"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1572:1: rule__KPoint__Group_4__0 : rule__KPoint__Group_4__0__Impl rule__KPoint__Group_4__1 ;
    public final void rule__KPoint__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1576:1: ( rule__KPoint__Group_4__0__Impl rule__KPoint__Group_4__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1577:2: rule__KPoint__Group_4__0__Impl rule__KPoint__Group_4__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group_4__0__Impl_in_rule__KPoint__Group_4__03050);
            rule__KPoint__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group_4__1_in_rule__KPoint__Group_4__03053);
            rule__KPoint__Group_4__1();

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
    // $ANTLR end "rule__KPoint__Group_4__0"


    // $ANTLR start "rule__KPoint__Group_4__0__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1584:1: rule__KPoint__Group_4__0__Impl : ( 'y' ) ;
    public final void rule__KPoint__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1588:1: ( ( 'y' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1589:1: ( 'y' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1589:1: ( 'y' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1590:1: 'y'
            {
             before(grammarAccess.getKPointAccess().getYKeyword_4_0()); 
            match(input,28,FollowSets000.FOLLOW_28_in_rule__KPoint__Group_4__0__Impl3081); 
             after(grammarAccess.getKPointAccess().getYKeyword_4_0()); 

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
    // $ANTLR end "rule__KPoint__Group_4__0__Impl"


    // $ANTLR start "rule__KPoint__Group_4__1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1603:1: rule__KPoint__Group_4__1 : rule__KPoint__Group_4__1__Impl ;
    public final void rule__KPoint__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1607:1: ( rule__KPoint__Group_4__1__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1608:2: rule__KPoint__Group_4__1__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__KPoint__Group_4__1__Impl_in_rule__KPoint__Group_4__13112);
            rule__KPoint__Group_4__1__Impl();

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
    // $ANTLR end "rule__KPoint__Group_4__1"


    // $ANTLR start "rule__KPoint__Group_4__1__Impl"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1614:1: rule__KPoint__Group_4__1__Impl : ( ( rule__KPoint__YAssignment_4_1 ) ) ;
    public final void rule__KPoint__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1618:1: ( ( ( rule__KPoint__YAssignment_4_1 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1619:1: ( ( rule__KPoint__YAssignment_4_1 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1619:1: ( ( rule__KPoint__YAssignment_4_1 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1620:1: ( rule__KPoint__YAssignment_4_1 )
            {
             before(grammarAccess.getKPointAccess().getYAssignment_4_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1621:1: ( rule__KPoint__YAssignment_4_1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1621:2: rule__KPoint__YAssignment_4_1
            {
            pushFollow(FollowSets000.FOLLOW_rule__KPoint__YAssignment_4_1_in_rule__KPoint__Group_4__1__Impl3139);
            rule__KPoint__YAssignment_4_1();

            state._fsp--;


            }

             after(grammarAccess.getKPointAccess().getYAssignment_4_1()); 

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
    // $ANTLR end "rule__KPoint__Group_4__1__Impl"


    // $ANTLR start "rule__EFloat__Group__0"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1635:1: rule__EFloat__Group__0 : rule__EFloat__Group__0__Impl rule__EFloat__Group__1 ;
    public final void rule__EFloat__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1639:1: ( rule__EFloat__Group__0__Impl rule__EFloat__Group__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1640:2: rule__EFloat__Group__0__Impl rule__EFloat__Group__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group__0__Impl_in_rule__EFloat__Group__03173);
            rule__EFloat__Group__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group__1_in_rule__EFloat__Group__03176);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1647:1: rule__EFloat__Group__0__Impl : ( ( '-' )? ) ;
    public final void rule__EFloat__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1651:1: ( ( ( '-' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1652:1: ( ( '-' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1652:1: ( ( '-' )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1653:1: ( '-' )?
            {
             before(grammarAccess.getEFloatAccess().getHyphenMinusKeyword_0()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1654:1: ( '-' )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==29) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1655:2: '-'
                    {
                    match(input,29,FollowSets000.FOLLOW_29_in_rule__EFloat__Group__0__Impl3205); 

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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1666:1: rule__EFloat__Group__1 : rule__EFloat__Group__1__Impl rule__EFloat__Group__2 ;
    public final void rule__EFloat__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1670:1: ( rule__EFloat__Group__1__Impl rule__EFloat__Group__2 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1671:2: rule__EFloat__Group__1__Impl rule__EFloat__Group__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group__1__Impl_in_rule__EFloat__Group__13238);
            rule__EFloat__Group__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group__2_in_rule__EFloat__Group__13241);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1678:1: rule__EFloat__Group__1__Impl : ( ( RULE_INT )? ) ;
    public final void rule__EFloat__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1682:1: ( ( ( RULE_INT )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1683:1: ( ( RULE_INT )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1683:1: ( ( RULE_INT )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1684:1: ( RULE_INT )?
            {
             before(grammarAccess.getEFloatAccess().getINTTerminalRuleCall_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1685:1: ( RULE_INT )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==RULE_INT) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1685:3: RULE_INT
                    {
                    match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_rule__EFloat__Group__1__Impl3269); 

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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1695:1: rule__EFloat__Group__2 : rule__EFloat__Group__2__Impl rule__EFloat__Group__3 ;
    public final void rule__EFloat__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1699:1: ( rule__EFloat__Group__2__Impl rule__EFloat__Group__3 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1700:2: rule__EFloat__Group__2__Impl rule__EFloat__Group__3
            {
            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group__2__Impl_in_rule__EFloat__Group__23300);
            rule__EFloat__Group__2__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group__3_in_rule__EFloat__Group__23303);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1707:1: rule__EFloat__Group__2__Impl : ( '.' ) ;
    public final void rule__EFloat__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1711:1: ( ( '.' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1712:1: ( '.' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1712:1: ( '.' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1713:1: '.'
            {
             before(grammarAccess.getEFloatAccess().getFullStopKeyword_2()); 
            match(input,30,FollowSets000.FOLLOW_30_in_rule__EFloat__Group__2__Impl3331); 
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1726:1: rule__EFloat__Group__3 : rule__EFloat__Group__3__Impl rule__EFloat__Group__4 ;
    public final void rule__EFloat__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1730:1: ( rule__EFloat__Group__3__Impl rule__EFloat__Group__4 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1731:2: rule__EFloat__Group__3__Impl rule__EFloat__Group__4
            {
            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group__3__Impl_in_rule__EFloat__Group__33362);
            rule__EFloat__Group__3__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group__4_in_rule__EFloat__Group__33365);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1738:1: rule__EFloat__Group__3__Impl : ( RULE_INT ) ;
    public final void rule__EFloat__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1742:1: ( ( RULE_INT ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1743:1: ( RULE_INT )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1743:1: ( RULE_INT )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1744:1: RULE_INT
            {
             before(grammarAccess.getEFloatAccess().getINTTerminalRuleCall_3()); 
            match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_rule__EFloat__Group__3__Impl3392); 
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1755:1: rule__EFloat__Group__4 : rule__EFloat__Group__4__Impl ;
    public final void rule__EFloat__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1759:1: ( rule__EFloat__Group__4__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1760:2: rule__EFloat__Group__4__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group__4__Impl_in_rule__EFloat__Group__43421);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1766:1: rule__EFloat__Group__4__Impl : ( ( rule__EFloat__Group_4__0 )? ) ;
    public final void rule__EFloat__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1770:1: ( ( ( rule__EFloat__Group_4__0 )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1771:1: ( ( rule__EFloat__Group_4__0 )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1771:1: ( ( rule__EFloat__Group_4__0 )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1772:1: ( rule__EFloat__Group_4__0 )?
            {
             before(grammarAccess.getEFloatAccess().getGroup_4()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1773:1: ( rule__EFloat__Group_4__0 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( ((LA15_0>=11 && LA15_0<=12)) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1773:2: rule__EFloat__Group_4__0
                    {
                    pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group_4__0_in_rule__EFloat__Group__4__Impl3448);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1793:1: rule__EFloat__Group_4__0 : rule__EFloat__Group_4__0__Impl rule__EFloat__Group_4__1 ;
    public final void rule__EFloat__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1797:1: ( rule__EFloat__Group_4__0__Impl rule__EFloat__Group_4__1 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1798:2: rule__EFloat__Group_4__0__Impl rule__EFloat__Group_4__1
            {
            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group_4__0__Impl_in_rule__EFloat__Group_4__03489);
            rule__EFloat__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group_4__1_in_rule__EFloat__Group_4__03492);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1805:1: rule__EFloat__Group_4__0__Impl : ( ( rule__EFloat__Alternatives_4_0 ) ) ;
    public final void rule__EFloat__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1809:1: ( ( ( rule__EFloat__Alternatives_4_0 ) ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1810:1: ( ( rule__EFloat__Alternatives_4_0 ) )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1810:1: ( ( rule__EFloat__Alternatives_4_0 ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1811:1: ( rule__EFloat__Alternatives_4_0 )
            {
             before(grammarAccess.getEFloatAccess().getAlternatives_4_0()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1812:1: ( rule__EFloat__Alternatives_4_0 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1812:2: rule__EFloat__Alternatives_4_0
            {
            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Alternatives_4_0_in_rule__EFloat__Group_4__0__Impl3519);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1822:1: rule__EFloat__Group_4__1 : rule__EFloat__Group_4__1__Impl rule__EFloat__Group_4__2 ;
    public final void rule__EFloat__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1826:1: ( rule__EFloat__Group_4__1__Impl rule__EFloat__Group_4__2 )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1827:2: rule__EFloat__Group_4__1__Impl rule__EFloat__Group_4__2
            {
            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group_4__1__Impl_in_rule__EFloat__Group_4__13549);
            rule__EFloat__Group_4__1__Impl();

            state._fsp--;

            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group_4__2_in_rule__EFloat__Group_4__13552);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1834:1: rule__EFloat__Group_4__1__Impl : ( ( '-' )? ) ;
    public final void rule__EFloat__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1838:1: ( ( ( '-' )? ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1839:1: ( ( '-' )? )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1839:1: ( ( '-' )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1840:1: ( '-' )?
            {
             before(grammarAccess.getEFloatAccess().getHyphenMinusKeyword_4_1()); 
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1841:1: ( '-' )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==29) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1842:2: '-'
                    {
                    match(input,29,FollowSets000.FOLLOW_29_in_rule__EFloat__Group_4__1__Impl3581); 

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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1853:1: rule__EFloat__Group_4__2 : rule__EFloat__Group_4__2__Impl ;
    public final void rule__EFloat__Group_4__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1857:1: ( rule__EFloat__Group_4__2__Impl )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1858:2: rule__EFloat__Group_4__2__Impl
            {
            pushFollow(FollowSets000.FOLLOW_rule__EFloat__Group_4__2__Impl_in_rule__EFloat__Group_4__23614);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1864:1: rule__EFloat__Group_4__2__Impl : ( RULE_INT ) ;
    public final void rule__EFloat__Group_4__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1868:1: ( ( RULE_INT ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1869:1: ( RULE_INT )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1869:1: ( RULE_INT )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1870:1: RULE_INT
            {
             before(grammarAccess.getEFloatAccess().getINTTerminalRuleCall_4_2()); 
            match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_rule__EFloat__Group_4__2__Impl3641); 
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1888:1: rule__KShapeLayout__XposAssignment_3_1 : ( ruleEFloat ) ;
    public final void rule__KShapeLayout__XposAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1892:1: ( ( ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1893:1: ( ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1893:1: ( ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1894:1: ruleEFloat
            {
             before(grammarAccess.getKShapeLayoutAccess().getXposEFloatParserRuleCall_3_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_rule__KShapeLayout__XposAssignment_3_13681);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1903:1: rule__KShapeLayout__YposAssignment_4_1 : ( ruleEFloat ) ;
    public final void rule__KShapeLayout__YposAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1907:1: ( ( ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1908:1: ( ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1908:1: ( ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1909:1: ruleEFloat
            {
             before(grammarAccess.getKShapeLayoutAccess().getYposEFloatParserRuleCall_4_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_rule__KShapeLayout__YposAssignment_4_13712);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1918:1: rule__KShapeLayout__WidthAssignment_5_1 : ( ruleEFloat ) ;
    public final void rule__KShapeLayout__WidthAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1922:1: ( ( ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1923:1: ( ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1923:1: ( ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1924:1: ruleEFloat
            {
             before(grammarAccess.getKShapeLayoutAccess().getWidthEFloatParserRuleCall_5_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_rule__KShapeLayout__WidthAssignment_5_13743);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1933:1: rule__KShapeLayout__HeightAssignment_6_1 : ( ruleEFloat ) ;
    public final void rule__KShapeLayout__HeightAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1937:1: ( ( ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1938:1: ( ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1938:1: ( ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1939:1: ruleEFloat
            {
             before(grammarAccess.getKShapeLayoutAccess().getHeightEFloatParserRuleCall_6_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_rule__KShapeLayout__HeightAssignment_6_13774);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1948:1: rule__KShapeLayout__InsetsAssignment_7_1 : ( ruleKInsets ) ;
    public final void rule__KShapeLayout__InsetsAssignment_7_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1952:1: ( ( ruleKInsets ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1953:1: ( ruleKInsets )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1953:1: ( ruleKInsets )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1954:1: ruleKInsets
            {
             before(grammarAccess.getKShapeLayoutAccess().getInsetsKInsetsParserRuleCall_7_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleKInsets_in_rule__KShapeLayout__InsetsAssignment_7_13805);
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


    // $ANTLR start "rule__KInsets__TopAssignment_3_1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1963:1: rule__KInsets__TopAssignment_3_1 : ( ruleEFloat ) ;
    public final void rule__KInsets__TopAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1967:1: ( ( ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1968:1: ( ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1968:1: ( ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1969:1: ruleEFloat
            {
             before(grammarAccess.getKInsetsAccess().getTopEFloatParserRuleCall_3_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_rule__KInsets__TopAssignment_3_13836);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1978:1: rule__KInsets__BottomAssignment_4_1 : ( ruleEFloat ) ;
    public final void rule__KInsets__BottomAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1982:1: ( ( ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1983:1: ( ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1983:1: ( ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1984:1: ruleEFloat
            {
             before(grammarAccess.getKInsetsAccess().getBottomEFloatParserRuleCall_4_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_rule__KInsets__BottomAssignment_4_13867);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1993:1: rule__KInsets__LeftAssignment_5_1 : ( ruleEFloat ) ;
    public final void rule__KInsets__LeftAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1997:1: ( ( ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1998:1: ( ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1998:1: ( ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:1999:1: ruleEFloat
            {
             before(grammarAccess.getKInsetsAccess().getLeftEFloatParserRuleCall_5_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_rule__KInsets__LeftAssignment_5_13898);
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
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2008:1: rule__KInsets__RightAssignment_6_1 : ( ruleEFloat ) ;
    public final void rule__KInsets__RightAssignment_6_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2012:1: ( ( ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2013:1: ( ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2013:1: ( ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2014:1: ruleEFloat
            {
             before(grammarAccess.getKInsetsAccess().getRightEFloatParserRuleCall_6_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_rule__KInsets__RightAssignment_6_13929);
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


    // $ANTLR start "rule__KPoint__XAssignment_3_1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2027:1: rule__KPoint__XAssignment_3_1 : ( ruleEFloat ) ;
    public final void rule__KPoint__XAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2031:1: ( ( ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2032:1: ( ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2032:1: ( ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2033:1: ruleEFloat
            {
             before(grammarAccess.getKPointAccess().getXEFloatParserRuleCall_3_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_rule__KPoint__XAssignment_3_13964);
            ruleEFloat();

            state._fsp--;

             after(grammarAccess.getKPointAccess().getXEFloatParserRuleCall_3_1_0()); 

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
    // $ANTLR end "rule__KPoint__XAssignment_3_1"


    // $ANTLR start "rule__KPoint__YAssignment_4_1"
    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2042:1: rule__KPoint__YAssignment_4_1 : ( ruleEFloat ) ;
    public final void rule__KPoint__YAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2046:1: ( ( ruleEFloat ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2047:1: ( ruleEFloat )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2047:1: ( ruleEFloat )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/kiml/klayoutdata/text/ui/contentassist/antlr/internal/InternalKLayoutData.g:2048:1: ruleEFloat
            {
             before(grammarAccess.getKPointAccess().getYEFloatParserRuleCall_4_1_0()); 
            pushFollow(FollowSets000.FOLLOW_ruleEFloat_in_rule__KPoint__YAssignment_4_13995);
            ruleEFloat();

            state._fsp--;

             after(grammarAccess.getKPointAccess().getYEFloatParserRuleCall_4_1_0()); 

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
    // $ANTLR end "rule__KPoint__YAssignment_4_1"

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
        public static final BitSet FOLLOW_ruleEFloat_in_entryRuleEFloat245 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEFloat252 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__EFloat__Group__0_in_ruleEFloat278 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_11_in_rule__EFloat__Alternatives_4_0316 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_12_in_rule__EFloat__Alternatives_4_0336 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__0__Impl_in_rule__KShapeLayout__Group__0368 = new BitSet(new long[]{0x0000000000002000L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__1_in_rule__KShapeLayout__Group__0371 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__1__Impl_in_rule__KShapeLayout__Group__1429 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__2_in_rule__KShapeLayout__Group__1432 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_13_in_rule__KShapeLayout__Group__1__Impl460 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__2__Impl_in_rule__KShapeLayout__Group__2491 = new BitSet(new long[]{0x00000000001F8000L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__3_in_rule__KShapeLayout__Group__2494 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_14_in_rule__KShapeLayout__Group__2__Impl522 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__3__Impl_in_rule__KShapeLayout__Group__3553 = new BitSet(new long[]{0x00000000001F8000L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__4_in_rule__KShapeLayout__Group__3556 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_3__0_in_rule__KShapeLayout__Group__3__Impl583 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__4__Impl_in_rule__KShapeLayout__Group__4614 = new BitSet(new long[]{0x00000000001F8000L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__5_in_rule__KShapeLayout__Group__4617 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_4__0_in_rule__KShapeLayout__Group__4__Impl644 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__5__Impl_in_rule__KShapeLayout__Group__5675 = new BitSet(new long[]{0x00000000001F8000L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__6_in_rule__KShapeLayout__Group__5678 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_5__0_in_rule__KShapeLayout__Group__5__Impl705 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__6__Impl_in_rule__KShapeLayout__Group__6736 = new BitSet(new long[]{0x00000000001F8000L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__7_in_rule__KShapeLayout__Group__6739 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_6__0_in_rule__KShapeLayout__Group__6__Impl766 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__7__Impl_in_rule__KShapeLayout__Group__7797 = new BitSet(new long[]{0x00000000001F8000L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__8_in_rule__KShapeLayout__Group__7800 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_7__0_in_rule__KShapeLayout__Group__7__Impl827 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group__8__Impl_in_rule__KShapeLayout__Group__8858 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_15_in_rule__KShapeLayout__Group__8__Impl886 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_3__0__Impl_in_rule__KShapeLayout__Group_3__0935 = new BitSet(new long[]{0x0000000060000010L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_3__1_in_rule__KShapeLayout__Group_3__0938 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_16_in_rule__KShapeLayout__Group_3__0__Impl966 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_3__1__Impl_in_rule__KShapeLayout__Group_3__1997 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__XposAssignment_3_1_in_rule__KShapeLayout__Group_3__1__Impl1024 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_4__0__Impl_in_rule__KShapeLayout__Group_4__01058 = new BitSet(new long[]{0x0000000060000010L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_4__1_in_rule__KShapeLayout__Group_4__01061 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_17_in_rule__KShapeLayout__Group_4__0__Impl1089 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_4__1__Impl_in_rule__KShapeLayout__Group_4__11120 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__YposAssignment_4_1_in_rule__KShapeLayout__Group_4__1__Impl1147 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_5__0__Impl_in_rule__KShapeLayout__Group_5__01181 = new BitSet(new long[]{0x0000000060000010L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_5__1_in_rule__KShapeLayout__Group_5__01184 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_18_in_rule__KShapeLayout__Group_5__0__Impl1212 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_5__1__Impl_in_rule__KShapeLayout__Group_5__11243 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__WidthAssignment_5_1_in_rule__KShapeLayout__Group_5__1__Impl1270 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_6__0__Impl_in_rule__KShapeLayout__Group_6__01304 = new BitSet(new long[]{0x0000000060000010L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_6__1_in_rule__KShapeLayout__Group_6__01307 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_19_in_rule__KShapeLayout__Group_6__0__Impl1335 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_6__1__Impl_in_rule__KShapeLayout__Group_6__11366 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__HeightAssignment_6_1_in_rule__KShapeLayout__Group_6__1__Impl1393 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_7__0__Impl_in_rule__KShapeLayout__Group_7__01427 = new BitSet(new long[]{0x0000000000200000L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_7__1_in_rule__KShapeLayout__Group_7__01430 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_20_in_rule__KShapeLayout__Group_7__0__Impl1458 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__Group_7__1__Impl_in_rule__KShapeLayout__Group_7__11489 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KShapeLayout__InsetsAssignment_7_1_in_rule__KShapeLayout__Group_7__1__Impl1516 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group__0__Impl_in_rule__KInsets__Group__01550 = new BitSet(new long[]{0x0000000000200000L});
        public static final BitSet FOLLOW_rule__KInsets__Group__1_in_rule__KInsets__Group__01553 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group__1__Impl_in_rule__KInsets__Group__11611 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_rule__KInsets__Group__2_in_rule__KInsets__Group__11614 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_21_in_rule__KInsets__Group__1__Impl1642 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group__2__Impl_in_rule__KInsets__Group__21673 = new BitSet(new long[]{0x0000000003C08000L});
        public static final BitSet FOLLOW_rule__KInsets__Group__3_in_rule__KInsets__Group__21676 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_14_in_rule__KInsets__Group__2__Impl1704 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group__3__Impl_in_rule__KInsets__Group__31735 = new BitSet(new long[]{0x0000000003C08000L});
        public static final BitSet FOLLOW_rule__KInsets__Group__4_in_rule__KInsets__Group__31738 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group_3__0_in_rule__KInsets__Group__3__Impl1765 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group__4__Impl_in_rule__KInsets__Group__41796 = new BitSet(new long[]{0x0000000003C08000L});
        public static final BitSet FOLLOW_rule__KInsets__Group__5_in_rule__KInsets__Group__41799 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group_4__0_in_rule__KInsets__Group__4__Impl1826 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group__5__Impl_in_rule__KInsets__Group__51857 = new BitSet(new long[]{0x0000000003C08000L});
        public static final BitSet FOLLOW_rule__KInsets__Group__6_in_rule__KInsets__Group__51860 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group_5__0_in_rule__KInsets__Group__5__Impl1887 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group__6__Impl_in_rule__KInsets__Group__61918 = new BitSet(new long[]{0x0000000003C08000L});
        public static final BitSet FOLLOW_rule__KInsets__Group__7_in_rule__KInsets__Group__61921 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group_6__0_in_rule__KInsets__Group__6__Impl1948 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group__7__Impl_in_rule__KInsets__Group__71979 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_15_in_rule__KInsets__Group__7__Impl2007 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group_3__0__Impl_in_rule__KInsets__Group_3__02054 = new BitSet(new long[]{0x0000000060000010L});
        public static final BitSet FOLLOW_rule__KInsets__Group_3__1_in_rule__KInsets__Group_3__02057 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_rule__KInsets__Group_3__0__Impl2085 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group_3__1__Impl_in_rule__KInsets__Group_3__12116 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__TopAssignment_3_1_in_rule__KInsets__Group_3__1__Impl2143 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group_4__0__Impl_in_rule__KInsets__Group_4__02177 = new BitSet(new long[]{0x0000000060000010L});
        public static final BitSet FOLLOW_rule__KInsets__Group_4__1_in_rule__KInsets__Group_4__02180 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_23_in_rule__KInsets__Group_4__0__Impl2208 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group_4__1__Impl_in_rule__KInsets__Group_4__12239 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__BottomAssignment_4_1_in_rule__KInsets__Group_4__1__Impl2266 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group_5__0__Impl_in_rule__KInsets__Group_5__02300 = new BitSet(new long[]{0x0000000060000010L});
        public static final BitSet FOLLOW_rule__KInsets__Group_5__1_in_rule__KInsets__Group_5__02303 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_24_in_rule__KInsets__Group_5__0__Impl2331 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group_5__1__Impl_in_rule__KInsets__Group_5__12362 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__LeftAssignment_5_1_in_rule__KInsets__Group_5__1__Impl2389 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group_6__0__Impl_in_rule__KInsets__Group_6__02423 = new BitSet(new long[]{0x0000000060000010L});
        public static final BitSet FOLLOW_rule__KInsets__Group_6__1_in_rule__KInsets__Group_6__02426 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_25_in_rule__KInsets__Group_6__0__Impl2454 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__Group_6__1__Impl_in_rule__KInsets__Group_6__12485 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KInsets__RightAssignment_6_1_in_rule__KInsets__Group_6__1__Impl2512 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KPoint__Group__0__Impl_in_rule__KPoint__Group__02549 = new BitSet(new long[]{0x0000000004000000L});
        public static final BitSet FOLLOW_rule__KPoint__Group__1_in_rule__KPoint__Group__02552 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KPoint__Group__1__Impl_in_rule__KPoint__Group__12610 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_rule__KPoint__Group__2_in_rule__KPoint__Group__12613 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_26_in_rule__KPoint__Group__1__Impl2641 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KPoint__Group__2__Impl_in_rule__KPoint__Group__22672 = new BitSet(new long[]{0x0000000018008000L});
        public static final BitSet FOLLOW_rule__KPoint__Group__3_in_rule__KPoint__Group__22675 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_14_in_rule__KPoint__Group__2__Impl2703 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KPoint__Group__3__Impl_in_rule__KPoint__Group__32734 = new BitSet(new long[]{0x0000000018008000L});
        public static final BitSet FOLLOW_rule__KPoint__Group__4_in_rule__KPoint__Group__32737 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KPoint__Group_3__0_in_rule__KPoint__Group__3__Impl2764 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KPoint__Group__4__Impl_in_rule__KPoint__Group__42795 = new BitSet(new long[]{0x0000000018008000L});
        public static final BitSet FOLLOW_rule__KPoint__Group__5_in_rule__KPoint__Group__42798 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KPoint__Group_4__0_in_rule__KPoint__Group__4__Impl2825 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KPoint__Group__5__Impl_in_rule__KPoint__Group__52856 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_15_in_rule__KPoint__Group__5__Impl2884 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KPoint__Group_3__0__Impl_in_rule__KPoint__Group_3__02927 = new BitSet(new long[]{0x0000000060000010L});
        public static final BitSet FOLLOW_rule__KPoint__Group_3__1_in_rule__KPoint__Group_3__02930 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_27_in_rule__KPoint__Group_3__0__Impl2958 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KPoint__Group_3__1__Impl_in_rule__KPoint__Group_3__12989 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KPoint__XAssignment_3_1_in_rule__KPoint__Group_3__1__Impl3016 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KPoint__Group_4__0__Impl_in_rule__KPoint__Group_4__03050 = new BitSet(new long[]{0x0000000060000010L});
        public static final BitSet FOLLOW_rule__KPoint__Group_4__1_in_rule__KPoint__Group_4__03053 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_28_in_rule__KPoint__Group_4__0__Impl3081 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KPoint__Group_4__1__Impl_in_rule__KPoint__Group_4__13112 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__KPoint__YAssignment_4_1_in_rule__KPoint__Group_4__1__Impl3139 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__EFloat__Group__0__Impl_in_rule__EFloat__Group__03173 = new BitSet(new long[]{0x0000000060000010L});
        public static final BitSet FOLLOW_rule__EFloat__Group__1_in_rule__EFloat__Group__03176 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_29_in_rule__EFloat__Group__0__Impl3205 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__EFloat__Group__1__Impl_in_rule__EFloat__Group__13238 = new BitSet(new long[]{0x0000000060000010L});
        public static final BitSet FOLLOW_rule__EFloat__Group__2_in_rule__EFloat__Group__13241 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_INT_in_rule__EFloat__Group__1__Impl3269 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__EFloat__Group__2__Impl_in_rule__EFloat__Group__23300 = new BitSet(new long[]{0x0000000000000010L});
        public static final BitSet FOLLOW_rule__EFloat__Group__3_in_rule__EFloat__Group__23303 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_30_in_rule__EFloat__Group__2__Impl3331 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__EFloat__Group__3__Impl_in_rule__EFloat__Group__33362 = new BitSet(new long[]{0x0000000000001800L});
        public static final BitSet FOLLOW_rule__EFloat__Group__4_in_rule__EFloat__Group__33365 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_INT_in_rule__EFloat__Group__3__Impl3392 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__EFloat__Group__4__Impl_in_rule__EFloat__Group__43421 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__EFloat__Group_4__0_in_rule__EFloat__Group__4__Impl3448 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__EFloat__Group_4__0__Impl_in_rule__EFloat__Group_4__03489 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_rule__EFloat__Group_4__1_in_rule__EFloat__Group_4__03492 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__EFloat__Alternatives_4_0_in_rule__EFloat__Group_4__0__Impl3519 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__EFloat__Group_4__1__Impl_in_rule__EFloat__Group_4__13549 = new BitSet(new long[]{0x0000000020000010L});
        public static final BitSet FOLLOW_rule__EFloat__Group_4__2_in_rule__EFloat__Group_4__13552 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_29_in_rule__EFloat__Group_4__1__Impl3581 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rule__EFloat__Group_4__2__Impl_in_rule__EFloat__Group_4__23614 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_INT_in_rule__EFloat__Group_4__2__Impl3641 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_rule__KShapeLayout__XposAssignment_3_13681 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_rule__KShapeLayout__YposAssignment_4_13712 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_rule__KShapeLayout__WidthAssignment_5_13743 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_rule__KShapeLayout__HeightAssignment_6_13774 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleKInsets_in_rule__KShapeLayout__InsetsAssignment_7_13805 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_rule__KInsets__TopAssignment_3_13836 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_rule__KInsets__BottomAssignment_4_13867 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_rule__KInsets__LeftAssignment_5_13898 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_rule__KInsets__RightAssignment_6_13929 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_rule__KPoint__XAssignment_3_13964 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEFloat_in_rule__KPoint__YAssignment_4_13995 = new BitSet(new long[]{0x0000000000000002L});
    }


}