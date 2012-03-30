package de.cau.cs.kieler.core.kgraph.text.ui.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalKGraphLexer extends Lexer {
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
    public static final int T__90=90;
    public static final int T__15=15;
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
<<<<<<< HEAD
=======
    public static final int T__116=116;
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
    public static final int T__114=114;
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

    public InternalKGraphLexer() {;} 
    public InternalKGraphLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalKGraphLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g"; }

    // $ANTLR start "T__11"
    public final void mT__11() throws RecognitionException {
        try {
            int _type = T__11;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:11:7: ( '-' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:11:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__11"

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:12:7: ( 'E' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:12:9: 'E'
            {
            match('E'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:13:7: ( 'e' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:13:9: 'e'
            {
            match('e'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:14:7: ( 'true' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:14:9: 'true'
            {
            match("true"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:15:7: ( 'false' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:15:9: 'false'
            {
            match("false"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:16:7: ( 'SOLID' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:16:9: 'SOLID'
            {
            match("SOLID"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:17:7: ( 'DASH' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:17:9: 'DASH'
            {
            match("DASH"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:18:7: ( 'DOT' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:18:9: 'DOT'
            {
            match("DOT"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:19:7: ( 'DASHDOT' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:19:9: 'DASHDOT'
            {
            match("DASHDOT"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:20:7: ( 'DASHDOTDOT' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:20:9: 'DASHDOTDOT'
            {
            match("DASHDOTDOT"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:21:7: ( 'TOP' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:21:9: 'TOP'
            {
            match("TOP"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:22:7: ( 'CENTER' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:22:9: 'CENTER'
            {
            match("CENTER"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:23:7: ( 'BOTTOM' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:23:9: 'BOTTOM'
            {
            match("BOTTOM"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24:7: ( 'LEFT' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24:9: 'LEFT'
            {
            match("LEFT"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25:7: ( 'RIGHT' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25:9: 'RIGHT'
            {
            match("RIGHT"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:26:7: ( 'KNode' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:26:9: 'KNode'
            {
            match("KNode"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:27:7: ( '{' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:27:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:28:7: ( '}' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:28:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:29:7: ( 'ports' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:29:9: 'ports'
            {
            match("ports"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:30:7: ( ',' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:30:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:31:7: ( 'data' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:31:9: 'data'
            {
            match("data"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:32:7: ( ':' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:32:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:33:7: ( 'children' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:33:9: 'children'
            {
            match("children"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:34:7: ( 'KLabel' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:34:9: 'KLabel'
            {
            match("KLabel"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:35:7: ( 'KPort' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:35:9: 'KPort'
            {
            match("KPort"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "T__36"
    public final void mT__36() throws RecognitionException {
        try {
            int _type = T__36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:36:7: ( 'edges' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:36:9: 'edges'
            {
            match("edges"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__36"

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:37:7: ( 'labels' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:37:9: 'labels'
            {
            match("labels"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:38:7: ( '-->' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:38:9: '-->'
            {
            match("-->"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "T__39"
    public final void mT__39() throws RecognitionException {
        try {
            int _type = T__39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:39:7: ( 'target' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:39:9: 'target'
            {
            match("target"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__39"

    // $ANTLR start "T__40"
    public final void mT__40() throws RecognitionException {
        try {
            int _type = T__40;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:40:7: ( 'sourcePort' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:40:9: 'sourcePort'
            {
            match("sourcePort"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__40"

    // $ANTLR start "T__41"
    public final void mT__41() throws RecognitionException {
        try {
            int _type = T__41;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:41:7: ( 'targetPort' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:41:9: 'targetPort'
            {
            match("targetPort"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__41"

    // $ANTLR start "T__42"
    public final void mT__42() throws RecognitionException {
        try {
            int _type = T__42;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:42:7: ( 'RenderingLibrary' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:42:9: 'RenderingLibrary'
            {
            match("RenderingLibrary"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__42"

    // $ANTLR start "T__43"
    public final void mT__43() throws RecognitionException {
        try {
            int _type = T__43;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:43:7: ( 'RenderingRef' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:43:9: 'RenderingRef'
            {
            match("RenderingRef"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__43"

    // $ANTLR start "T__44"
    public final void mT__44() throws RecognitionException {
        try {
            int _type = T__44;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:44:7: ( 'placementData' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:44:9: 'placementData'
            {
            match("placementData"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__44"

    // $ANTLR start "T__45"
    public final void mT__45() throws RecognitionException {
        try {
            int _type = T__45;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:45:7: ( 'styles' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:45:9: 'styles'
            {
            match("styles"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__45"

    // $ANTLR start "T__46"
    public final void mT__46() throws RecognitionException {
        try {
            int _type = T__46;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:46:7: ( 'Ellipse' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:46:9: 'Ellipse'
            {
            match("Ellipse"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__46"

    // $ANTLR start "T__47"
    public final void mT__47() throws RecognitionException {
        try {
            int _type = T__47;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:47:7: ( 'childPlacement' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:47:9: 'childPlacement'
            {
            match("childPlacement"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__47"

    // $ANTLR start "T__48"
    public final void mT__48() throws RecognitionException {
        try {
            int _type = T__48;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:48:7: ( 'Rectangle' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:48:9: 'Rectangle'
            {
            match("Rectangle"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__48"

    // $ANTLR start "T__49"
    public final void mT__49() throws RecognitionException {
        try {
            int _type = T__49;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:49:7: ( 'RoundedRectangle' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:49:9: 'RoundedRectangle'
            {
            match("RoundedRectangle"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__49"

    // $ANTLR start "T__50"
    public final void mT__50() throws RecognitionException {
        try {
            int _type = T__50;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:50:7: ( 'Polyline' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:50:9: 'Polyline'
            {
            match("Polyline"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__50"

    // $ANTLR start "T__51"
    public final void mT__51() throws RecognitionException {
        try {
            int _type = T__51;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:51:7: ( 'Polygon' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:51:9: 'Polygon'
            {
            match("Polygon"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__51"

    // $ANTLR start "T__52"
    public final void mT__52() throws RecognitionException {
        try {
            int _type = T__52;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:52:7: ( 'Image' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:52:9: 'Image'
            {
            match("Image"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__52"

    // $ANTLR start "T__53"
    public final void mT__53() throws RecognitionException {
        try {
            int _type = T__53;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:53:7: ( 'Arc' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:53:9: 'Arc'
            {
            match("Arc"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__53"

    // $ANTLR start "T__54"
    public final void mT__54() throws RecognitionException {
        try {
            int _type = T__54;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:54:7: ( 'ChildArea' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:54:9: 'ChildArea'
            {
            match("ChildArea"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__54"

    // $ANTLR start "T__55"
    public final void mT__55() throws RecognitionException {
        try {
            int _type = T__55;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:55:7: ( 'Text' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:55:9: 'Text'
            {
            match("Text"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__55"

    // $ANTLR start "T__56"
    public final void mT__56() throws RecognitionException {
        try {
            int _type = T__56;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:56:7: ( 'CustomRendering' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:56:9: 'CustomRendering'
            {
            match("CustomRendering"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__56"

    // $ANTLR start "T__57"
    public final void mT__57() throws RecognitionException {
        try {
            int _type = T__57;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:57:7: ( 'className' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:57:9: 'className'
            {
            match("className"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__57"

    // $ANTLR start "T__58"
    public final void mT__58() throws RecognitionException {
        try {
            int _type = T__58;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:58:7: ( 'bundleName' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:58:9: 'bundleName'
            {
            match("bundleName"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__58"

    // $ANTLR start "T__59"
    public final void mT__59() throws RecognitionException {
        try {
            int _type = T__59;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:59:7: ( 'Spline' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:59:9: 'Spline'
            {
            match("Spline"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__59"

    // $ANTLR start "T__60"
    public final void mT__60() throws RecognitionException {
        try {
            int _type = T__60;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:60:7: ( 'DecoratorPlacementData' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:60:9: 'DecoratorPlacementData'
            {
            match("DecoratorPlacementData"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__60"

    // $ANTLR start "T__61"
    public final void mT__61() throws RecognitionException {
        try {
            int _type = T__61;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:61:7: ( 'location' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:61:9: 'location'
            {
            match("location"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__61"

    // $ANTLR start "T__62"
    public final void mT__62() throws RecognitionException {
        try {
            int _type = T__62;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:62:7: ( 'xOffset' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:62:9: 'xOffset'
            {
            match("xOffset"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__62"

    // $ANTLR start "T__63"
    public final void mT__63() throws RecognitionException {
        try {
            int _type = T__63;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:63:7: ( 'yOffset' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:63:9: 'yOffset'
            {
            match("yOffset"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__63"

    // $ANTLR start "T__64"
    public final void mT__64() throws RecognitionException {
        try {
            int _type = T__64;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:64:7: ( 'width' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:64:9: 'width'
            {
            match("width"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__64"

    // $ANTLR start "T__65"
    public final void mT__65() throws RecognitionException {
        try {
            int _type = T__65;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:65:7: ( 'height' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:65:9: 'height'
            {
            match("height"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__65"

    // $ANTLR start "T__66"
    public final void mT__66() throws RecognitionException {
        try {
            int _type = T__66;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:66:7: ( 'GridPlacementData' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:66:9: 'GridPlacementData'
            {
            match("GridPlacementData"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__66"

    // $ANTLR start "T__67"
    public final void mT__67() throws RecognitionException {
        try {
            int _type = T__67;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:67:7: ( 'widthHint' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:67:9: 'widthHint'
            {
            match("widthHint"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__67"

    // $ANTLR start "T__68"
    public final void mT__68() throws RecognitionException {
        try {
            int _type = T__68;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:68:7: ( 'heightHint' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:68:9: 'heightHint'
            {
            match("heightHint"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__68"

    // $ANTLR start "T__69"
    public final void mT__69() throws RecognitionException {
        try {
            int _type = T__69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:69:7: ( 'insetRight' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:69:9: 'insetRight'
            {
            match("insetRight"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__69"

    // $ANTLR start "T__70"
    public final void mT__70() throws RecognitionException {
        try {
            int _type = T__70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:70:7: ( 'insetBottom' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:70:9: 'insetBottom'
            {
            match("insetBottom"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__70"

    // $ANTLR start "T__71"
    public final void mT__71() throws RecognitionException {
        try {
            int _type = T__71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:71:7: ( 'insetLeft' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:71:9: 'insetLeft'
            {
            match("insetLeft"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__71"

    // $ANTLR start "T__72"
    public final void mT__72() throws RecognitionException {
        try {
            int _type = T__72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:72:7: ( 'insetTop' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:72:9: 'insetTop'
            {
            match("insetTop"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__72"

    // $ANTLR start "T__73"
    public final void mT__73() throws RecognitionException {
        try {
            int _type = T__73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:73:7: ( 'StackPlacementData' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:73:9: 'StackPlacementData'
            {
            match("StackPlacementData"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__73"

    // $ANTLR start "T__74"
    public final void mT__74() throws RecognitionException {
        try {
            int _type = T__74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:74:7: ( 'DirectPlacementData' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:74:9: 'DirectPlacementData'
            {
            match("DirectPlacementData"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__74"

    // $ANTLR start "T__75"
    public final void mT__75() throws RecognitionException {
        try {
            int _type = T__75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:75:7: ( 'topLeft' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:75:9: 'topLeft'
            {
            match("topLeft"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__75"

    // $ANTLR start "T__76"
    public final void mT__76() throws RecognitionException {
        try {
            int _type = T__76;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:76:7: ( 'bottomRight' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:76:9: 'bottomRight'
            {
            match("bottomRight"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__76"

    // $ANTLR start "T__77"
    public final void mT__77() throws RecognitionException {
        try {
            int _type = T__77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:77:7: ( 'PolylinePlacementData' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:77:9: 'PolylinePlacementData'
            {
            match("PolylinePlacementData"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__77"

    // $ANTLR start "T__78"
    public final void mT__78() throws RecognitionException {
        try {
            int _type = T__78;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:78:7: ( 'points' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:78:9: 'points'
            {
            match("points"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__78"

    // $ANTLR start "T__79"
    public final void mT__79() throws RecognitionException {
        try {
            int _type = T__79;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:79:7: ( 'detailedPlacementData' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:79:9: 'detailedPlacementData'
            {
            match("detailedPlacementData"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__79"

    // $ANTLR start "T__80"
    public final void mT__80() throws RecognitionException {
        try {
            int _type = T__80;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:80:7: ( '/' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:80:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__80"

    // $ANTLR start "T__81"
    public final void mT__81() throws RecognitionException {
        try {
            int _type = T__81;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:81:7: ( 'left' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:81:9: 'left'
            {
            match("left"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__81"

    // $ANTLR start "T__82"
    public final void mT__82() throws RecognitionException {
        try {
            int _type = T__82;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:82:7: ( 'right' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:82:9: 'right'
            {
            match("right"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__82"

    // $ANTLR start "T__83"
    public final void mT__83() throws RecognitionException {
        try {
            int _type = T__83;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:83:7: ( 'top' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:83:9: 'top'
            {
            match("top"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__83"

    // $ANTLR start "T__84"
    public final void mT__84() throws RecognitionException {
        try {
            int _type = T__84;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:84:7: ( 'bottom' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:84:9: 'bottom'
            {
            match("bottom"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__84"

    // $ANTLR start "T__85"
    public final void mT__85() throws RecognitionException {
        try {
            int _type = T__85;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:85:7: ( 'ForegroundColor' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:85:9: 'ForegroundColor'
            {
            match("ForegroundColor"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__85"

    // $ANTLR start "T__86"
    public final void mT__86() throws RecognitionException {
        try {
            int _type = T__86;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:86:7: ( 'BackgroundColor' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:86:9: 'BackgroundColor'
            {
            match("BackgroundColor"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__86"

    // $ANTLR start "T__87"
    public final void mT__87() throws RecognitionException {
        try {
            int _type = T__87;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:87:7: ( 'LineWidth' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:87:9: 'LineWidth'
            {
            match("LineWidth"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__87"

    // $ANTLR start "T__88"
    public final void mT__88() throws RecognitionException {
        try {
            int _type = T__88;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:88:7: ( 'ForegroundVisibility' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:88:9: 'ForegroundVisibility'
            {
            match("ForegroundVisibility"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__88"

    // $ANTLR start "T__89"
    public final void mT__89() throws RecognitionException {
        try {
            int _type = T__89;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:89:7: ( 'BackgroundVisibility' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:89:9: 'BackgroundVisibility'
            {
            match("BackgroundVisibility"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__89"

    // $ANTLR start "T__90"
    public final void mT__90() throws RecognitionException {
        try {
            int _type = T__90;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:90:7: ( 'LineStyle' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:90:9: 'LineStyle'
            {
            match("LineStyle"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__90"

    // $ANTLR start "T__91"
    public final void mT__91() throws RecognitionException {
        try {
            int _type = T__91;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:91:7: ( 'bold' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:91:9: 'bold'
            {
            match("bold"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__91"

    // $ANTLR start "T__92"
    public final void mT__92() throws RecognitionException {
        try {
            int _type = T__92;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:92:7: ( 'italic' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:92:9: 'italic'
            {
            match("italic"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__92"

    // $ANTLR start "T__93"
    public final void mT__93() throws RecognitionException {
        try {
            int _type = T__93;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:93:7: ( 'VerticalAlignment' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:93:9: 'VerticalAlignment'
            {
            match("VerticalAlignment"); 
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:93:7: ( 'font' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:93:9: 'font'
            {
            match("font"); 
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__93"

    // $ANTLR start "T__94"
    public final void mT__94() throws RecognitionException {
        try {
            int _type = T__94;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:94:7: ( 'HorizontalAlignment' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:94:9: 'HorizontalAlignment'
            {
            match("HorizontalAlignment"); 
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:94:7: ( 'fontSize' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:94:9: 'fontSize'
            {
            match("fontSize"); 
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__94"

    // $ANTLR start "T__95"
    public final void mT__95() throws RecognitionException {
        try {
            int _type = T__95;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:95:7: ( 'GridPlacement' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:95:9: 'GridPlacement'
            {
            match("GridPlacement"); 
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:95:7: ( 'VerticalAlignment' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:95:9: 'VerticalAlignment'
            {
            match("VerticalAlignment"); 
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__95"

    // $ANTLR start "T__96"
    public final void mT__96() throws RecognitionException {
        try {
            int _type = T__96;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:96:7: ( 'StackPlacement' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:96:9: 'StackPlacement'
            {
            match("StackPlacement"); 
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:96:7: ( 'HorizontalAlignment' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:96:9: 'HorizontalAlignment'
            {
            match("HorizontalAlignment"); 
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__96"

    // $ANTLR start "T__97"
    public final void mT__97() throws RecognitionException {
        try {
            int _type = T__97;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:97:7: ( '.' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:97:9: '.'
            {
            match('.'); 
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:97:7: ( 'GridPlacement' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:97:9: 'GridPlacement'
            {
            match("GridPlacement"); 

>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__97"

    // $ANTLR start "T__98"
    public final void mT__98() throws RecognitionException {
        try {
            int _type = T__98;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:98:7: ( 'KShapeLayout' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:98:9: 'KShapeLayout'
            {
            match("KShapeLayout"); 
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:98:7: ( 'StackPlacement' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:98:9: 'StackPlacement'
            {
            match("StackPlacement"); 
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__98"

    // $ANTLR start "T__99"
    public final void mT__99() throws RecognitionException {
        try {
            int _type = T__99;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:99:7: ( 'xpos' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:99:9: 'xpos'
            {
            match("xpos"); 

=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:99:7: ( '.' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:99:9: '.'
            {
            match('.'); 
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__99"

    // $ANTLR start "T__100"
    public final void mT__100() throws RecognitionException {
        try {
            int _type = T__100;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:100:8: ( 'ypos' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:100:10: 'ypos'
            {
            match("ypos"); 
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:100:8: ( 'KShapeLayout' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:100:10: 'KShapeLayout'
            {
            match("KShapeLayout"); 
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__100"

    // $ANTLR start "T__101"
    public final void mT__101() throws RecognitionException {
        try {
            int _type = T__101;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:101:8: ( 'insets' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:101:10: 'insets'
            {
            match("insets"); 
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:101:8: ( 'xpos' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:101:10: 'xpos'
            {
            match("xpos"); 
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__101"

    // $ANTLR start "T__102"
    public final void mT__102() throws RecognitionException {
        try {
            int _type = T__102;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:102:8: ( 'mapProperties' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:102:10: 'mapProperties'
            {
            match("mapProperties"); 
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:102:8: ( 'ypos' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:102:10: 'ypos'
            {
            match("ypos"); 
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__102"

    // $ANTLR start "T__103"
    public final void mT__103() throws RecognitionException {
        try {
            int _type = T__103;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:103:8: ( 'KInsets' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:103:10: 'KInsets'
            {
            match("KInsets"); 
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:103:8: ( 'insets' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:103:10: 'insets'
            {
            match("insets"); 
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__103"

    // $ANTLR start "T__104"
    public final void mT__104() throws RecognitionException {
        try {
            int _type = T__104;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:104:8: ( 'KEdgeLayout' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:104:10: 'KEdgeLayout'
            {
            match("KEdgeLayout"); 
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:104:8: ( 'mapProperties' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:104:10: 'mapProperties'
            {
            match("mapProperties"); 
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__104"

    // $ANTLR start "T__105"
    public final void mT__105() throws RecognitionException {
        try {
            int _type = T__105;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:105:8: ( 'sourcePoint' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:105:10: 'sourcePoint'
            {
            match("sourcePoint"); 
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:105:8: ( 'KInsets' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:105:10: 'KInsets'
            {
            match("KInsets"); 
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__105"

    // $ANTLR start "T__106"
    public final void mT__106() throws RecognitionException {
        try {
            int _type = T__106;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:106:8: ( 'targetPoint' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:106:10: 'targetPoint'
            {
            match("targetPoint"); 
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:106:8: ( 'KEdgeLayout' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:106:10: 'KEdgeLayout'
            {
            match("KEdgeLayout"); 
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__106"

    // $ANTLR start "T__107"
    public final void mT__107() throws RecognitionException {
        try {
            int _type = T__107;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:107:8: ( 'bendPoints' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:107:10: 'bendPoints'
            {
            match("bendPoints"); 
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:107:8: ( 'sourcePoint' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:107:10: 'sourcePoint'
            {
            match("sourcePoint"); 
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__107"

    // $ANTLR start "T__108"
    public final void mT__108() throws RecognitionException {
        try {
            int _type = T__108;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:108:8: ( 'KPoint' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:108:10: 'KPoint'
            {
            match("KPoint"); 
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:108:8: ( 'targetPoint' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:108:10: 'targetPoint'
            {
            match("targetPoint"); 
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__108"

    // $ANTLR start "T__109"
    public final void mT__109() throws RecognitionException {
        try {
            int _type = T__109;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:109:8: ( 'x' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:109:10: 'x'
            {
            match('x'); 
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:109:8: ( 'bendPoints' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:109:10: 'bendPoints'
            {
            match("bendPoints"); 

>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__109"

    // $ANTLR start "T__110"
    public final void mT__110() throws RecognitionException {
        try {
            int _type = T__110;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:110:8: ( 'y' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:110:10: 'y'
            {
            match('y'); 
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:110:8: ( 'KPoint' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:110:10: 'KPoint'
            {
            match("KPoint"); 

>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__110"

    // $ANTLR start "T__111"
    public final void mT__111() throws RecognitionException {
        try {
            int _type = T__111;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:111:8: ( '=' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:111:10: '='
            {
            match('='); 
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:111:8: ( 'x' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:111:10: 'x'
            {
            match('x'); 
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__111"

    // $ANTLR start "T__112"
    public final void mT__112() throws RecognitionException {
        try {
            int _type = T__112;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:112:8: ( 'clip' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:112:10: 'clip'
            {
            match("clip"); 

=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:112:8: ( 'y' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:112:10: 'y'
            {
            match('y'); 
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__112"

    // $ANTLR start "T__113"
    public final void mT__113() throws RecognitionException {
        try {
            int _type = T__113;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:113:8: ( 'relative' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:113:10: 'relative'
            {
            match("relative"); 

=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:113:8: ( '=' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:113:10: '='
            {
            match('='); 
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__113"

    // $ANTLR start "T__114"
    public final void mT__114() throws RecognitionException {
        try {
            int _type = T__114;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:114:8: ( '!' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:114:10: '!'
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:114:8: ( 'clip' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:114:10: 'clip'
            {
            match("clip"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__114"

    // $ANTLR start "T__115"
    public final void mT__115() throws RecognitionException {
        try {
            int _type = T__115;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:115:8: ( 'relative' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:115:10: 'relative'
            {
            match("relative"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__115"

    // $ANTLR start "T__116"
    public final void mT__116() throws RecognitionException {
        try {
            int _type = T__116;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:116:8: ( '!' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:116:10: '!'
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
<<<<<<< HEAD
    // $ANTLR end "T__114"
=======
    // $ANTLR end "T__116"
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24621:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24621:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24621:11: ( '^' )?
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25186:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25186:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25186:11: ( '^' )?
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='^') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
<<<<<<< HEAD
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24621:11: '^'
=======
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25186:11: '^'
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24621:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25186:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')||(LA2_0>='A' && LA2_0<='Z')||LA2_0=='_'||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ID"

    // $ANTLR start "RULE_INT"
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24623:10: ( ( '0' .. '9' )+ )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24623:12: ( '0' .. '9' )+
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24623:12: ( '0' .. '9' )+
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25188:10: ( ( '0' .. '9' )+ )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25188:12: ( '0' .. '9' )+
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25188:12: ( '0' .. '9' )+
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
<<<<<<< HEAD
            	    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24623:13: '0' .. '9'
=======
            	    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25188:13: '0' .. '9'
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_INT"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24625:13: ( ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24625:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24625:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25190:13: ( ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25190:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25190:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='\"') ) {
                alt6=1;
            }
            else if ( (LA6_0=='\'') ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
<<<<<<< HEAD
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24625:16: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24625:20: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )*
=======
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25190:16: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25190:20: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )*
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
                    loop4:
                    do {
                        int alt4=3;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0=='\\') ) {
                            alt4=1;
                        }
                        else if ( ((LA4_0>='\u0000' && LA4_0<='!')||(LA4_0>='#' && LA4_0<='[')||(LA4_0>=']' && LA4_0<='\uFFFF')) ) {
                            alt4=2;
                        }


                        switch (alt4) {
                    	case 1 :
<<<<<<< HEAD
                    	    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24625:21: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
=======
                    	    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25190:21: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||(input.LA(1)>='t' && input.LA(1)<='u') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;
                    	case 2 :
<<<<<<< HEAD
                    	    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24625:66: ~ ( ( '\\\\' | '\"' ) )
=======
                    	    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25190:66: ~ ( ( '\\\\' | '\"' ) )
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
<<<<<<< HEAD
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24625:86: '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24625:91: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )*
=======
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25190:86: '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25190:91: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )*
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
                    loop5:
                    do {
                        int alt5=3;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0=='\\') ) {
                            alt5=1;
                        }
                        else if ( ((LA5_0>='\u0000' && LA5_0<='&')||(LA5_0>='(' && LA5_0<='[')||(LA5_0>=']' && LA5_0<='\uFFFF')) ) {
                            alt5=2;
                        }


                        switch (alt5) {
                    	case 1 :
<<<<<<< HEAD
                    	    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24625:92: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
=======
                    	    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25190:92: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||(input.LA(1)>='t' && input.LA(1)<='u') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;
                    	case 2 :
<<<<<<< HEAD
                    	    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24625:137: ~ ( ( '\\\\' | '\\'' ) )
=======
                    	    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25190:137: ~ ( ( '\\\\' | '\\'' ) )
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);

                    match('\''); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_STRING"

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24627:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24627:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24627:24: ( options {greedy=false; } : . )*
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25192:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25192:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25192:24: ( options {greedy=false; } : . )*
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0=='*') ) {
                    int LA7_1 = input.LA(2);

                    if ( (LA7_1=='/') ) {
                        alt7=2;
                    }
                    else if ( ((LA7_1>='\u0000' && LA7_1<='.')||(LA7_1>='0' && LA7_1<='\uFFFF')) ) {
                        alt7=1;
                    }


                }
                else if ( ((LA7_0>='\u0000' && LA7_0<=')')||(LA7_0>='+' && LA7_0<='\uFFFF')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
<<<<<<< HEAD
            	    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24627:52: .
=======
            	    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25192:52: .
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            match("*/"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ML_COMMENT"

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24629:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24629:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24629:24: (~ ( ( '\\n' | '\\r' ) ) )*
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25194:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25194:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25194:24: (~ ( ( '\\n' | '\\r' ) ) )*
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='\u0000' && LA8_0<='\t')||(LA8_0>='\u000B' && LA8_0<='\f')||(LA8_0>='\u000E' && LA8_0<='\uFFFF')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
<<<<<<< HEAD
            	    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24629:24: ~ ( ( '\\n' | '\\r' ) )
=======
            	    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25194:24: ~ ( ( '\\n' | '\\r' ) )
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24629:40: ( ( '\\r' )? '\\n' )?
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25194:40: ( ( '\\r' )? '\\n' )?
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0=='\n'||LA10_0=='\r') ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
<<<<<<< HEAD
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24629:41: ( '\\r' )? '\\n'
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24629:41: ( '\\r' )?
=======
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25194:41: ( '\\r' )? '\\n'
                    {
                    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25194:41: ( '\\r' )?
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0=='\r') ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
<<<<<<< HEAD
                            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24629:41: '\\r'
=======
                            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25194:41: '\\r'
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SL_COMMENT"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24631:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24631:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24631:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25196:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25196:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25196:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>='\t' && LA11_0<='\n')||LA11_0=='\r'||LA11_0==' ') ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt11 >= 1 ) break loop11;
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WS"

    // $ANTLR start "RULE_ANY_OTHER"
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
<<<<<<< HEAD
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24633:16: ( . )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:24633:18: .
=======
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25198:16: ( . )
            // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:25198:18: .
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            {
            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ANY_OTHER"

    public void mTokens() throws RecognitionException {
<<<<<<< HEAD
        // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:8: ( T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | T__104 | T__105 | T__106 | T__107 | T__108 | T__109 | T__110 | T__111 | T__112 | T__113 | T__114 | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt12=111;
=======
        // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:8: ( T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | T__104 | T__105 | T__106 | T__107 | T__108 | T__109 | T__110 | T__111 | T__112 | T__113 | T__114 | T__115 | T__116 | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt12=113;
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
        alt12 = dfa12.predict(input);
        switch (alt12) {
            case 1 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:10: T__11
                {
                mT__11(); 

                }
                break;
            case 2 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:16: T__12
                {
                mT__12(); 

                }
                break;
            case 3 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:22: T__13
                {
                mT__13(); 

                }
                break;
            case 4 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:28: T__14
                {
                mT__14(); 

                }
                break;
            case 5 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:34: T__15
                {
                mT__15(); 

                }
                break;
            case 6 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:40: T__16
                {
                mT__16(); 

                }
                break;
            case 7 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:46: T__17
                {
                mT__17(); 

                }
                break;
            case 8 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:52: T__18
                {
                mT__18(); 

                }
                break;
            case 9 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:58: T__19
                {
                mT__19(); 

                }
                break;
            case 10 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:64: T__20
                {
                mT__20(); 

                }
                break;
            case 11 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:70: T__21
                {
                mT__21(); 

                }
                break;
            case 12 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:76: T__22
                {
                mT__22(); 

                }
                break;
            case 13 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:82: T__23
                {
                mT__23(); 

                }
                break;
            case 14 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:88: T__24
                {
                mT__24(); 

                }
                break;
            case 15 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:94: T__25
                {
                mT__25(); 

                }
                break;
            case 16 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:100: T__26
                {
                mT__26(); 

                }
                break;
            case 17 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:106: T__27
                {
                mT__27(); 

                }
                break;
            case 18 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:112: T__28
                {
                mT__28(); 

                }
                break;
            case 19 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:118: T__29
                {
                mT__29(); 

                }
                break;
            case 20 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:124: T__30
                {
                mT__30(); 

                }
                break;
            case 21 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:130: T__31
                {
                mT__31(); 

                }
                break;
            case 22 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:136: T__32
                {
                mT__32(); 

                }
                break;
            case 23 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:142: T__33
                {
                mT__33(); 

                }
                break;
            case 24 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:148: T__34
                {
                mT__34(); 

                }
                break;
            case 25 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:154: T__35
                {
                mT__35(); 

                }
                break;
            case 26 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:160: T__36
                {
                mT__36(); 

                }
                break;
            case 27 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:166: T__37
                {
                mT__37(); 

                }
                break;
            case 28 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:172: T__38
                {
                mT__38(); 

                }
                break;
            case 29 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:178: T__39
                {
                mT__39(); 

                }
                break;
            case 30 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:184: T__40
                {
                mT__40(); 

                }
                break;
            case 31 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:190: T__41
                {
                mT__41(); 

                }
                break;
            case 32 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:196: T__42
                {
                mT__42(); 

                }
                break;
            case 33 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:202: T__43
                {
                mT__43(); 

                }
                break;
            case 34 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:208: T__44
                {
                mT__44(); 

                }
                break;
            case 35 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:214: T__45
                {
                mT__45(); 

                }
                break;
            case 36 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:220: T__46
                {
                mT__46(); 

                }
                break;
            case 37 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:226: T__47
                {
                mT__47(); 

                }
                break;
            case 38 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:232: T__48
                {
                mT__48(); 

                }
                break;
            case 39 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:238: T__49
                {
                mT__49(); 

                }
                break;
            case 40 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:244: T__50
                {
                mT__50(); 

                }
                break;
            case 41 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:250: T__51
                {
                mT__51(); 

                }
                break;
            case 42 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:256: T__52
                {
                mT__52(); 

                }
                break;
            case 43 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:262: T__53
                {
                mT__53(); 

                }
                break;
            case 44 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:268: T__54
                {
                mT__54(); 

                }
                break;
            case 45 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:274: T__55
                {
                mT__55(); 

                }
                break;
            case 46 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:280: T__56
                {
                mT__56(); 

                }
                break;
            case 47 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:286: T__57
                {
                mT__57(); 

                }
                break;
            case 48 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:292: T__58
                {
                mT__58(); 

                }
                break;
            case 49 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:298: T__59
                {
                mT__59(); 

                }
                break;
            case 50 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:304: T__60
                {
                mT__60(); 

                }
                break;
            case 51 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:310: T__61
                {
                mT__61(); 

                }
                break;
            case 52 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:316: T__62
                {
                mT__62(); 

                }
                break;
            case 53 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:322: T__63
                {
                mT__63(); 

                }
                break;
            case 54 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:328: T__64
                {
                mT__64(); 

                }
                break;
            case 55 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:334: T__65
                {
                mT__65(); 

                }
                break;
            case 56 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:340: T__66
                {
                mT__66(); 

                }
                break;
            case 57 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:346: T__67
                {
                mT__67(); 

                }
                break;
            case 58 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:352: T__68
                {
                mT__68(); 

                }
                break;
            case 59 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:358: T__69
                {
                mT__69(); 

                }
                break;
            case 60 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:364: T__70
                {
                mT__70(); 

                }
                break;
            case 61 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:370: T__71
                {
                mT__71(); 

                }
                break;
            case 62 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:376: T__72
                {
                mT__72(); 

                }
                break;
            case 63 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:382: T__73
                {
                mT__73(); 

                }
                break;
            case 64 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:388: T__74
                {
                mT__74(); 

                }
                break;
            case 65 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:394: T__75
                {
                mT__75(); 

                }
                break;
            case 66 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:400: T__76
                {
                mT__76(); 

                }
                break;
            case 67 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:406: T__77
                {
                mT__77(); 

                }
                break;
            case 68 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:412: T__78
                {
                mT__78(); 

                }
                break;
            case 69 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:418: T__79
                {
                mT__79(); 

                }
                break;
            case 70 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:424: T__80
                {
                mT__80(); 

                }
                break;
            case 71 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:430: T__81
                {
                mT__81(); 

                }
                break;
            case 72 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:436: T__82
                {
                mT__82(); 

                }
                break;
            case 73 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:442: T__83
                {
                mT__83(); 

                }
                break;
            case 74 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:448: T__84
                {
                mT__84(); 

                }
                break;
            case 75 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:454: T__85
                {
                mT__85(); 

                }
                break;
            case 76 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:460: T__86
                {
                mT__86(); 

                }
                break;
            case 77 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:466: T__87
                {
                mT__87(); 

                }
                break;
            case 78 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:472: T__88
                {
                mT__88(); 

                }
                break;
            case 79 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:478: T__89
                {
                mT__89(); 

                }
                break;
            case 80 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:484: T__90
                {
                mT__90(); 

                }
                break;
            case 81 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:490: T__91
                {
                mT__91(); 

                }
                break;
            case 82 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:496: T__92
                {
                mT__92(); 

                }
                break;
            case 83 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:502: T__93
                {
                mT__93(); 

                }
                break;
            case 84 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:508: T__94
                {
                mT__94(); 

                }
                break;
            case 85 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:514: T__95
                {
                mT__95(); 

                }
                break;
            case 86 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:520: T__96
                {
                mT__96(); 

                }
                break;
            case 87 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:526: T__97
                {
                mT__97(); 

                }
                break;
            case 88 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:532: T__98
                {
                mT__98(); 

                }
                break;
            case 89 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:538: T__99
                {
                mT__99(); 

                }
                break;
            case 90 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:544: T__100
                {
                mT__100(); 

                }
                break;
            case 91 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:551: T__101
                {
                mT__101(); 

                }
                break;
            case 92 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:558: T__102
                {
                mT__102(); 

                }
                break;
            case 93 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:565: T__103
                {
                mT__103(); 

                }
                break;
            case 94 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:572: T__104
                {
                mT__104(); 

                }
                break;
            case 95 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:579: T__105
                {
                mT__105(); 

                }
                break;
            case 96 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:586: T__106
                {
                mT__106(); 

                }
                break;
            case 97 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:593: T__107
                {
                mT__107(); 

                }
                break;
            case 98 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:600: T__108
                {
                mT__108(); 

                }
                break;
            case 99 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:607: T__109
                {
                mT__109(); 

                }
                break;
            case 100 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:614: T__110
                {
                mT__110(); 

                }
                break;
            case 101 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:621: T__111
                {
                mT__111(); 

                }
                break;
            case 102 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:628: T__112
                {
                mT__112(); 

                }
                break;
            case 103 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:635: T__113
                {
                mT__113(); 

                }
                break;
            case 104 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:642: T__114
                {
                mT__114(); 

                }
                break;
            case 105 :
<<<<<<< HEAD
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:649: RULE_ID
=======
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:649: T__115
                {
                mT__115(); 

                }
                break;
            case 106 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:656: T__116
                {
                mT__116(); 

                }
                break;
            case 107 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:663: RULE_ID
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
                {
                mRULE_ID(); 

                }
                break;
<<<<<<< HEAD
            case 106 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:657: RULE_INT
=======
            case 108 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:671: RULE_INT
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
                {
                mRULE_INT(); 

                }
                break;
<<<<<<< HEAD
            case 107 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:666: RULE_STRING
=======
            case 109 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:680: RULE_STRING
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
                {
                mRULE_STRING(); 

                }
                break;
<<<<<<< HEAD
            case 108 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:678: RULE_ML_COMMENT
=======
            case 110 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:692: RULE_ML_COMMENT
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
                {
                mRULE_ML_COMMENT(); 

                }
                break;
<<<<<<< HEAD
            case 109 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:694: RULE_SL_COMMENT
=======
            case 111 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:708: RULE_SL_COMMENT
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
                {
                mRULE_SL_COMMENT(); 

                }
                break;
<<<<<<< HEAD
            case 110 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:710: RULE_WS
=======
            case 112 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:724: RULE_WS
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
                {
                mRULE_WS(); 

                }
                break;
<<<<<<< HEAD
            case 111 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:718: RULE_ANY_OTHER
=======
            case 113 :
                // ../de.cau.cs.kieler.core.kgraph.text.ui/src-gen/de/cau/cs/kieler/core/kgraph/text/ui/contentassist/antlr/internal/InternalKGraph.g:1:732: RULE_ANY_OTHER
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA12 dfa12 = new DFA12(this);
    static final String DFA12_eotS =
<<<<<<< HEAD
        "\1\uffff\1\63\1\65\1\70\12\66\2\uffff\1\66\1\uffff\1\66\1\uffff"+
        "\7\66\1\155\1\160\5\66\1\171\4\66\1\uffff\1\66\2\uffff\1\61\2\uffff"+
        "\2\61\4\uffff\1\66\2\uffff\1\66\1\uffff\35\66\2\uffff\2\66\1\uffff"+
        "\2\66\1\uffff\17\66\1\uffff\2\66\1\uffff\6\66\3\uffff\5\66\1\uffff"+
        "\1\66\5\uffff\4\66\1\u00ce\5\66\1\u00d4\2\66\1\u00d7\41\66\1\u00fa"+
        "\25\66\1\u0110\2\66\1\uffff\4\66\1\u0118\1\uffff\2\66\1\uffff\1"+
        "\u011b\5\66\1\u0121\17\66\1\u0132\3\66\1\u0136\2\66\1\u0139\4\66"+
        "\1\uffff\4\66\1\u0143\1\66\1\u0145\15\66\1\u0153\1\uffff\2\66\1"+
        "\u0156\1\u0157\3\66\1\uffff\2\66\1\uffff\5\66\1\uffff\2\66\1\u0164"+
        "\3\66\1\u0168\1\66\1\u016a\4\66\1\u016f\2\66\1\uffff\3\66\1\uffff"+
        "\2\66\1\uffff\4\66\1\u017c\4\66\1\uffff\1\66\1\uffff\1\u0183\5\66"+
        "\1\u018d\6\66\1\uffff\1\u0195\1\66\2\uffff\1\u0197\4\66\1\u019c"+
        "\2\66\1\u019f\3\66\1\uffff\3\66\1\uffff\1\u01a6\1\uffff\1\u01a7"+
        "\3\66\1\uffff\1\u01ab\5\66\1\u01b1\2\66\1\u01b4\2\66\1\uffff\1\66"+
        "\1\u01b9\4\66\1\uffff\1\u01bf\7\66\1\u01c7\1\uffff\5\66\1\u01cd"+
        "\1\66\1\uffff\1\u01cf\1\uffff\1\66\1\u01d2\2\66\1\uffff\2\66\1\uffff"+
        "\6\66\2\uffff\1\66\1\u01de\1\66\1\uffff\5\66\1\uffff\2\66\1\uffff"+
        "\1\66\1\u01e8\2\66\1\uffff\1\66\1\u01ec\1\u01ed\2\66\1\uffff\7\66"+
        "\1\uffff\5\66\1\uffff\1\66\1\uffff\2\66\1\uffff\13\66\1\uffff\3"+
        "\66\1\u020e\2\66\1\u0211\1\66\1\u0215\1\uffff\3\66\2\uffff\10\66"+
        "\1\u0221\1\u0222\12\66\1\u022d\2\66\1\u0230\1\u0231\1\66\1\u0234"+
        "\5\66\1\uffff\1\66\1\u023b\1\uffff\3\66\1\uffff\3\66\1\u0242\6\66"+
        "\1\u0249\2\uffff\4\66\1\u024e\2\66\1\u0251\2\66\1\uffff\2\66\2\uffff"+
        "\2\66\1\uffff\6\66\1\uffff\1\u025f\2\66\1\u0262\1\66\1\u0264\1\uffff"+
        "\1\u0265\3\66\1\u0269\1\66\1\uffff\4\66\1\uffff\1\u0270\1\66\1\uffff"+
        "\11\66\1\u027b\3\66\1\uffff\1\u027f\1\66\1\uffff\1\u0281\2\uffff"+
        "\3\66\1\uffff\1\u0285\5\66\1\uffff\7\66\1\u0292\1\66\1\u0294\1\uffff"+
        "\3\66\1\uffff\1\66\1\uffff\3\66\1\uffff\14\66\1\uffff\1\66\1\uffff"+
        "\1\u02a9\4\66\1\u02af\5\66\1\u02b5\1\u02b7\7\66\1\uffff\1\66\1\u02c0"+
        "\3\66\1\uffff\1\u02c4\4\66\1\uffff\1\66\1\uffff\2\66\1\u02cc\1\u02cd"+
        "\4\66\1\uffff\3\66\1\uffff\1\u02d5\6\66\2\uffff\1\66\1\u02dd\1\u02de"+
        "\2\66\1\u02e1\1\66\1\uffff\7\66\2\uffff\2\66\1\uffff\1\u02ec\1\66"+
        "\1\u02ee\1\66\1\u02f0\5\66\1\uffff\1\66\1\uffff\1\66\1\uffff\1\66"+
        "\1\u02f9\4\66\1\u02fe\1\66\1\uffff\1\u0300\2\66\1\u0303\1\uffff"+
        "\1\66\1\uffff\1\u0305\1\u0306\1\uffff\1\u0307\3\uffff";
    static final String DFA12_eofS =
        "\u0308\uffff";
    static final String DFA12_minS =
        "\1\0\1\55\2\60\2\141\1\117\1\101\1\117\1\105\1\117\1\105\1\111\1"+
        "\105\2\uffff\1\154\1\uffff\1\141\1\uffff\1\150\1\141\2\157\1\155"+
        "\1\162\1\145\2\60\1\151\1\145\1\162\1\145\1\156\1\52\1\145\1\157"+
        "\1\145\1\157\1\uffff\1\141\2\uffff\1\101\2\uffff\2\0\4\uffff\1\154"+
        "\2\uffff\1\147\1\uffff\1\165\1\162\1\160\1\154\1\114\1\154\1\141"+
        "\1\123\1\124\1\143\1\162\1\120\1\170\1\116\1\151\1\163\1\124\1\143"+
        "\1\106\1\156\1\107\1\143\1\165\1\157\1\141\1\157\1\150\1\156\1\144"+
        "\2\uffff\1\151\1\141\1\uffff\2\164\1\uffff\1\151\1\141\1\142\1\143"+
        "\1\146\1\165\1\171\1\154\1\141\1\143\1\156\1\164\1\156\1\146\1\157"+
        "\1\uffff\1\146\1\157\1\uffff\1\144\1\151\1\162\1\151\1\162\1\163"+
        "\3\uffff\1\147\1\154\3\162\1\uffff\1\160\5\uffff\1\151\2\145\1\147"+
        "\1\60\1\163\1\111\1\151\1\143\1\110\1\60\1\157\1\145\1\60\1\164"+
        "\1\124\1\154\1\164\1\124\1\153\1\124\1\145\1\110\1\144\1\164\1\156"+
        "\1\144\1\142\1\151\1\141\1\163\1\147\1\164\1\156\1\143\2\141\1\154"+
        "\1\163\1\160\1\145\1\141\1\164\1\162\1\154\1\171\1\147\1\60\1\144"+
        "\1\164\1\144\1\146\1\163\1\146\1\163\1\164\1\147\1\151\1\144\1\164"+
        "\1\145\1\150\1\141\1\145\1\164\1\151\1\120\1\160\1\163\1\60\2\145"+
        "\1\uffff\1\145\1\104\1\156\1\153\1\60\1\uffff\1\162\1\143\1\uffff"+
        "\1\60\1\105\1\144\1\157\1\117\1\147\1\60\1\123\1\124\1\145\1\141"+
        "\1\144\2\145\1\164\1\156\1\160\2\145\1\163\1\164\1\145\1\60\1\151"+
        "\1\144\1\163\1\60\1\154\1\164\1\60\1\143\1\145\1\147\1\145\1\uffff"+
        "\1\154\1\157\1\120\1\163\1\60\1\163\1\60\2\150\1\172\1\120\1\151"+
        "\3\164\1\147\1\151\1\172\1\162\1\163\1\60\1\uffff\1\164\1\146\2"+
        "\60\1\145\1\120\1\117\1\uffff\1\141\1\164\1\uffff\1\122\1\101\1"+
        "\155\1\115\1\162\1\uffff\1\151\1\164\1\60\1\162\1\156\1\145\1\60"+
        "\1\154\1\60\1\164\1\145\1\164\1\114\1\60\1\163\1\155\1\uffff\1\154"+
        "\1\120\1\116\1\uffff\1\163\1\151\1\uffff\1\145\1\163\1\151\1\157"+
        "\1\60\1\145\1\155\1\157\1\145\1\uffff\1\145\1\uffff\1\60\1\164\1"+
        "\157\1\154\1\143\1\102\1\60\1\151\1\162\1\143\2\157\1\145\1\uffff"+
        "\1\60\1\164\2\uffff\1\60\1\154\1\124\1\164\1\120\1\60\1\162\1\122"+
        "\1\60\1\157\1\144\1\171\1\uffff\1\151\1\147\1\144\1\uffff\1\60\1"+
        "\uffff\1\60\1\114\1\163\1\141\1\uffff\1\60\3\145\1\154\1\141\1\60"+
        "\1\157\1\120\1\60\2\156\1\uffff\1\116\1\60\1\151\2\164\1\151\1\uffff"+
        "\1\60\1\156\2\141\1\151\1\157\1\145\1\157\1\60\1\uffff\1\166\1\157"+
        "\1\141\1\156\1\160\1\60\1\157\1\uffff\1\60\1\uffff\1\141\1\60\1"+
        "\157\1\154\1\uffff\2\145\1\uffff\1\165\1\164\1\154\1\156\1\154\1"+
        "\122\2\uffff\1\141\1\60\1\171\1\uffff\1\156\1\144\1\156\1\141\1"+
        "\155\1\uffff\1\156\1\157\1\uffff\1\145\1\60\1\141\1\151\1\uffff"+
        "\1\156\2\60\1\156\1\151\1\uffff\1\164\1\143\1\154\1\147\1\164\1"+
        "\146\1\160\1\uffff\1\145\1\165\1\154\1\164\1\145\1\uffff\1\151\1"+
        "\uffff\1\143\1\117\1\uffff\1\162\2\141\2\156\1\150\1\145\1\147\2"+
        "\145\1\171\1\uffff\1\157\1\164\1\120\1\60\1\143\1\145\1\60\1\151"+
        "\1\60\1\uffff\1\155\1\147\1\164\2\uffff\1\164\1\156\1\141\1\145"+
        "\1\111\1\150\2\164\2\60\1\156\1\101\1\141\1\162\1\164\1\156\1\145"+
        "\1\124\1\120\1\143\1\60\2\144\2\60\1\114\1\60\1\143\1\157\1\165"+
        "\1\104\1\154\1\uffff\1\145\1\60\1\uffff\1\164\1\156\1\154\1\uffff"+
        "\1\145\1\150\1\163\1\60\1\164\1\154\1\155\1\156\1\164\1\157\1\60"+
        "\2\uffff\1\144\2\154\1\164\1\60\1\164\1\155\1\60\1\154\1\145\1\uffff"+
        "\1\145\1\103\2\uffff\1\151\1\145\1\uffff\1\164\1\165\1\164\2\141"+
        "\1\155\1\uffff\1\60\1\164\1\141\1\60\1\164\1\60\1\uffff\1\60\1\111"+
        "\1\145\1\144\1\60\1\155\1\uffff\1\103\1\151\1\101\1\151\1\uffff"+
        "\1\60\1\145\1\uffff\1\141\1\155\1\162\1\157\1\151\1\142\1\146\1"+
        "\141\1\164\1\60\1\164\1\143\1\145\1\uffff\1\60\1\143\1\uffff\1\60"+
        "\2\uffff\2\156\1\145\1\uffff\1\60\1\157\1\151\1\147\1\154\1\145"+
        "\1\uffff\1\156\1\143\1\145\1\151\1\154\1\163\1\162\1\60\1\156\1"+
        "\60\1\uffff\1\141\1\145\1\156\1\uffff\1\145\1\uffff\1\144\1\164"+
        "\1\156\1\uffff\1\154\1\163\1\156\1\151\1\163\1\164\1\145\2\156\1"+
        "\157\1\151\1\141\1\uffff\1\147\1\uffff\1\60\1\155\1\164\1\155\1"+
        "\145\1\60\1\164\1\157\1\151\1\155\1\147\2\60\1\155\1\164\1\147\1"+
        "\162\1\142\1\162\1\154\1\uffff\1\145\1\60\1\145\1\156\1\141\1\uffff"+
        "\1\60\1\162\1\142\1\145\1\156\1\uffff\1\141\1\uffff\1\145\1\104"+
        "\2\60\1\151\1\171\1\145\1\156\1\uffff\1\156\2\164\1\uffff\1\60\1"+
        "\151\1\156\1\155\1\164\1\156\1\141\2\uffff\1\154\2\60\2\164\1\60"+
        "\1\141\1\uffff\1\154\1\164\1\145\1\141\2\164\1\151\2\uffff\2\104"+
        "\1\uffff\1\60\1\151\1\60\1\156\1\60\1\104\1\141\1\164\2\141\1\uffff"+
        "\1\164\1\uffff\1\164\1\uffff\1\141\1\60\1\171\2\164\1\171\1\60\1"+
        "\164\1\uffff\1\60\2\141\1\60\1\uffff\1\141\1\uffff\2\60\1\uffff"+
        "\1\60\3\uffff";
=======
        "\1\uffff\1\62\1\64\1\67\12\65\2\uffff\1\65\1\uffff\1\65\1\uffff"+
        "\7\65\1\155\1\160\4\65\1\170\4\65\1\uffff\1\65\2\uffff\1\60\2\uffff"+
        "\2\60\4\uffff\1\65\2\uffff\1\65\1\uffff\36\65\2\uffff\2\65\1\uffff"+
        "\2\65\1\uffff\17\65\1\uffff\2\65\1\uffff\5\65\3\uffff\5\65\1\uffff"+
        "\1\65\5\uffff\4\65\1\u00ce\6\65\1\u00d5\2\65\1\u00d8\41\65\1\u00fb"+
        "\25\65\1\u0111\2\65\1\uffff\1\65\1\u0116\3\65\1\u011b\1\uffff\2"+
        "\65\1\uffff\1\u011e\5\65\1\u0124\17\65\1\u0135\3\65\1\u0139\2\65"+
        "\1\u013c\4\65\1\uffff\2\65\1\u0144\2\65\1\u0147\1\65\1\u0149\14"+
        "\65\1\u0156\1\uffff\2\65\1\u0159\1\65\1\uffff\1\u015b\3\65\1\uffff"+
        "\2\65\1\uffff\5\65\1\uffff\2\65\1\u0168\3\65\1\u016c\1\65\1\u016e"+
        "\4\65\1\u0173\2\65\1\uffff\3\65\1\uffff\2\65\1\uffff\4\65\1\u0180"+
        "\2\65\1\uffff\2\65\1\uffff\1\65\1\uffff\1\u0187\4\65\1\u0190\6\65"+
        "\1\uffff\1\u0198\1\65\1\uffff\1\65\1\uffff\1\u019b\4\65\1\u01a0"+
        "\2\65\1\u01a3\3\65\1\uffff\3\65\1\uffff\1\u01aa\1\uffff\1\u01ab"+
        "\3\65\1\uffff\1\u01af\5\65\1\u01b5\2\65\1\u01b8\2\65\1\uffff\1\65"+
        "\1\u01bd\4\65\1\uffff\1\u01c3\5\65\1\u01c9\1\u01ca\1\uffff\5\65"+
        "\1\u01d0\1\65\1\uffff\1\u01d2\1\65\1\uffff\1\65\1\u01d6\2\65\1\uffff"+
        "\2\65\1\uffff\6\65\2\uffff\1\65\1\u01e2\1\65\1\uffff\5\65\1\uffff"+
        "\2\65\1\uffff\1\65\1\u01ec\2\65\1\uffff\1\65\1\u01f0\1\u01f1\2\65"+
        "\1\uffff\5\65\2\uffff\5\65\1\uffff\1\65\1\uffff\1\u0200\2\65\1\uffff"+
        "\13\65\1\uffff\3\65\1\u0211\2\65\1\u0214\1\65\1\u0218\1\uffff\3"+
        "\65\2\uffff\6\65\1\u0222\1\u0223\6\65\1\uffff\4\65\1\u022e\2\65"+
        "\1\u0231\1\u0232\1\65\1\u0235\5\65\1\uffff\1\65\1\u023c\1\uffff"+
        "\3\65\1\uffff\3\65\1\u0243\4\65\1\u0248\2\uffff\4\65\1\u024d\2\65"+
        "\1\u0250\2\65\1\uffff\2\65\2\uffff\2\65\1\uffff\6\65\1\uffff\1\u025e"+
        "\2\65\1\u0261\1\65\1\u0263\1\uffff\1\u0264\1\65\1\u0266\1\65\1\uffff"+
        "\4\65\1\uffff\1\u026d\1\65\1\uffff\11\65\1\u0278\3\65\1\uffff\1"+
        "\u027c\1\65\1\uffff\1\u027e\2\uffff\1\65\1\uffff\1\u0280\5\65\1"+
        "\uffff\7\65\1\u028d\1\65\1\u028f\1\uffff\3\65\1\uffff\1\65\1\uffff"+
        "\1\65\1\uffff\14\65\1\uffff\1\65\1\uffff\1\u02a2\3\65\1\u02a7\4"+
        "\65\1\u02ac\1\u02ae\7\65\1\uffff\1\65\1\u02b7\2\65\1\uffff\4\65"+
        "\1\uffff\1\65\1\uffff\2\65\1\u02c1\1\u02c2\4\65\1\uffff\2\65\1\u02c9"+
        "\6\65\2\uffff\1\65\1\u02d1\1\u02d2\3\65\1\uffff\7\65\2\uffff\2\65"+
        "\1\u02df\1\65\1\u02e1\1\65\1\u02e3\5\65\1\uffff\1\65\1\uffff\1\65"+
        "\1\uffff\1\65\1\u02ec\4\65\1\u02f1\1\65\1\uffff\1\u02f3\2\65\1\u02f6"+
        "\1\uffff\1\65\1\uffff\1\u02f8\1\u02f9\1\uffff\1\u02fa\3\uffff";
    static final String DFA12_eofS =
        "\u02fb\uffff";
    static final String DFA12_minS =
        "\1\0\1\55\2\60\2\141\1\117\1\101\1\117\1\105\1\117\1\105\1\111\1"+
        "\105\2\uffff\1\154\1\uffff\1\141\1\uffff\1\150\1\141\2\157\1\155"+
        "\1\162\1\145\2\60\1\151\1\145\1\162\1\156\1\52\1\145\1\157\1\145"+
        "\1\157\1\uffff\1\141\2\uffff\1\101\2\uffff\2\0\4\uffff\1\154\2\uffff"+
        "\1\147\1\uffff\1\165\1\162\1\160\1\154\1\156\1\114\1\154\1\141\1"+
        "\123\1\124\1\143\1\162\1\120\1\170\1\116\1\151\1\163\1\124\1\143"+
        "\1\106\1\156\1\107\1\143\1\165\1\157\1\141\1\157\1\150\1\156\1\144"+
        "\2\uffff\1\151\1\141\1\uffff\2\164\1\uffff\1\151\1\141\1\142\1\143"+
        "\1\146\1\165\1\171\1\154\1\141\1\143\1\156\1\154\1\156\1\146\1\157"+
        "\1\uffff\1\146\1\157\1\uffff\1\144\2\151\1\163\1\141\3\uffff\1\147"+
        "\1\154\3\162\1\uffff\1\160\5\uffff\1\151\2\145\1\147\1\60\1\163"+
        "\1\164\1\111\1\151\1\143\1\110\1\60\1\157\1\145\1\60\1\164\1\124"+
        "\1\154\1\164\1\124\1\153\1\124\1\145\1\110\1\144\1\164\1\156\1\144"+
        "\1\142\1\151\1\141\1\163\1\147\1\164\1\156\1\143\2\141\1\154\1\163"+
        "\1\160\1\145\1\141\1\164\1\162\1\154\1\171\1\147\1\60\1\144\1\164"+
        "\2\144\1\146\1\163\1\146\1\163\1\164\1\147\1\144\1\145\1\154\1\150"+
        "\1\141\1\145\1\164\1\151\1\120\1\160\1\163\1\60\2\145\1\uffff\1"+
        "\145\1\60\1\104\1\156\1\153\1\60\1\uffff\1\162\1\143\1\uffff\1\60"+
        "\1\105\1\144\1\157\1\117\1\147\1\60\1\123\1\124\1\145\1\141\1\144"+
        "\2\145\1\164\1\156\1\160\2\145\1\163\1\164\1\145\1\60\1\151\1\144"+
        "\1\163\1\60\1\154\1\164\1\60\1\143\1\145\1\147\1\145\1\uffff\1\154"+
        "\1\157\1\60\1\120\1\163\1\60\1\163\1\60\2\150\1\120\1\164\1\151"+
        "\2\164\1\147\1\151\1\172\1\162\1\163\1\60\1\uffff\1\164\1\146\1"+
        "\60\1\151\1\uffff\1\60\1\145\1\120\1\117\1\uffff\1\141\1\164\1\uffff"+
        "\1\122\1\101\1\155\1\115\1\162\1\uffff\1\151\1\164\1\60\1\162\1"+
        "\156\1\145\1\60\1\154\1\60\1\164\1\145\1\164\1\114\1\60\1\163\1"+
        "\155\1\uffff\1\154\1\120\1\116\1\uffff\1\163\1\151\1\uffff\1\145"+
        "\1\163\1\151\1\157\1\60\1\145\1\155\1\uffff\1\157\1\145\1\uffff"+
        "\1\145\1\uffff\1\60\1\164\1\154\1\102\1\143\1\60\1\151\1\162\1\143"+
        "\2\157\1\145\1\uffff\1\60\1\164\1\uffff\1\172\1\uffff\1\60\1\154"+
        "\1\124\1\164\1\120\1\60\1\162\1\122\1\60\1\157\1\144\1\171\1\uffff"+
        "\1\151\1\147\1\144\1\uffff\1\60\1\uffff\1\60\1\114\1\163\1\141\1"+
        "\uffff\1\60\3\145\1\154\1\141\1\60\1\157\1\120\1\60\2\156\1\uffff"+
        "\1\116\1\60\1\151\2\164\1\151\1\uffff\1\60\1\141\1\151\1\157\1\145"+
        "\1\157\2\60\1\uffff\1\166\1\157\1\141\1\156\1\160\1\60\1\157\1\uffff"+
        "\1\60\1\145\1\uffff\1\141\1\60\1\157\1\154\1\uffff\2\145\1\uffff"+
        "\1\165\1\164\1\154\1\156\1\154\1\122\2\uffff\1\141\1\60\1\171\1"+
        "\uffff\1\156\1\144\1\156\1\141\1\155\1\uffff\1\156\1\157\1\uffff"+
        "\1\145\1\60\1\141\1\151\1\uffff\1\156\2\60\1\156\1\151\1\uffff\1"+
        "\143\1\147\1\164\1\146\1\160\2\uffff\1\145\1\165\1\154\1\164\1\145"+
        "\1\uffff\1\151\1\uffff\1\60\1\143\1\117\1\uffff\1\162\2\141\2\156"+
        "\1\150\1\145\1\147\2\145\1\171\1\uffff\1\157\1\164\1\120\1\60\1"+
        "\143\1\145\1\60\1\151\1\60\1\uffff\1\155\1\147\1\164\2\uffff\1\164"+
        "\1\156\1\145\1\150\2\164\2\60\1\156\1\101\1\141\1\162\1\164\1\156"+
        "\1\uffff\1\145\1\124\1\120\1\143\1\60\2\144\2\60\1\114\1\60\1\143"+
        "\1\157\1\165\1\104\1\154\1\uffff\1\145\1\60\1\uffff\1\164\1\156"+
        "\1\154\1\uffff\1\145\1\150\1\163\1\60\1\164\1\155\1\164\1\157\1"+
        "\60\2\uffff\1\144\2\154\1\164\1\60\1\164\1\155\1\60\1\154\1\145"+
        "\1\uffff\1\145\1\103\2\uffff\1\151\1\145\1\uffff\1\164\1\165\1\164"+
        "\2\141\1\155\1\uffff\1\60\1\164\1\141\1\60\1\164\1\60\1\uffff\1"+
        "\60\1\145\1\60\1\155\1\uffff\1\103\1\151\1\101\1\151\1\uffff\1\60"+
        "\1\145\1\uffff\1\141\1\155\1\162\1\157\1\151\1\142\1\146\1\141\1"+
        "\164\1\60\1\164\1\143\1\145\1\uffff\1\60\1\143\1\uffff\1\60\2\uffff"+
        "\1\156\1\uffff\1\60\1\157\1\151\1\147\1\154\1\145\1\uffff\1\156"+
        "\1\143\1\145\1\151\1\154\1\163\1\162\1\60\1\156\1\60\1\uffff\1\141"+
        "\1\145\1\156\1\uffff\1\145\1\uffff\1\164\1\uffff\1\154\1\163\1\156"+
        "\1\151\1\163\1\164\1\145\2\156\1\157\1\151\1\141\1\uffff\1\147\1"+
        "\uffff\1\60\1\155\1\164\1\155\1\60\1\157\1\151\1\155\1\147\2\60"+
        "\1\155\1\164\1\147\1\162\1\142\1\162\1\154\1\uffff\1\145\1\60\1"+
        "\145\1\141\1\uffff\1\162\1\142\1\145\1\156\1\uffff\1\141\1\uffff"+
        "\1\145\1\104\2\60\1\151\1\171\1\145\1\156\1\uffff\1\156\1\164\1"+
        "\60\1\151\1\156\1\155\1\164\1\156\1\141\2\uffff\1\154\2\60\2\164"+
        "\1\141\1\uffff\1\154\1\164\1\145\1\141\2\164\1\151\2\uffff\2\104"+
        "\1\60\1\151\1\60\1\156\1\60\1\104\1\141\1\164\2\141\1\uffff\1\164"+
        "\1\uffff\1\164\1\uffff\1\141\1\60\1\171\2\164\1\171\1\60\1\164\1"+
        "\uffff\1\60\2\141\1\60\1\uffff\1\141\1\uffff\2\60\1\uffff\1\60\3"+
        "\uffff";
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
    static final String DFA12_maxS =
        "\1\uffff\1\55\2\172\1\162\1\141\1\164\1\151\1\145\1\165\1\141\1"+
        "\151\1\157\1\123\2\uffff\1\157\1\uffff\1\145\1\uffff\1\154\1\157"+
<<<<<<< HEAD
        "\1\164\1\157\1\155\1\162\1\165\2\172\1\151\1\157\1\162\1\145\1\156"+
        "\1\57\1\151\1\157\1\145\1\157\1\uffff\1\141\2\uffff\1\172\2\uffff"+
        "\2\uffff\4\uffff\1\154\2\uffff\1\147\1\uffff\1\165\1\162\1\160\1"+
        "\154\1\114\1\154\1\141\1\123\1\124\1\143\1\162\1\120\1\170\1\116"+
        "\1\151\1\163\1\124\1\143\1\106\1\156\1\107\1\156\1\165\1\157\1\141"+
        "\1\157\1\150\1\156\1\144\2\uffff\1\162\1\141\1\uffff\2\164\1\uffff"+
        "\2\151\1\142\1\143\1\146\1\165\1\171\1\154\1\141\1\143\1\156\1\164"+
        "\1\156\1\146\1\157\1\uffff\1\146\1\157\1\uffff\1\144\1\151\1\162"+
        "\1\151\1\162\1\163\3\uffff\1\147\1\154\3\162\1\uffff\1\160\5\uffff"+
        "\1\151\2\145\1\147\1\172\1\163\1\111\1\151\1\143\1\110\1\172\1\157"+
        "\1\145\1\172\1\164\1\124\1\154\1\164\1\124\1\153\1\124\1\145\1\110"+
        "\1\144\1\164\1\156\1\144\1\142\1\162\1\141\1\163\1\147\1\164\1\156"+
        "\1\143\2\141\1\154\1\163\1\160\1\145\1\141\1\164\1\162\1\154\1\171"+
        "\1\147\1\172\1\144\1\164\1\144\1\146\1\163\1\146\1\163\1\164\1\147"+
        "\1\151\1\144\1\164\1\145\1\150\1\141\1\145\1\164\1\151\1\120\1\160"+
        "\1\163\1\172\2\145\1\uffff\1\145\1\104\1\156\1\153\1\172\1\uffff"+
        "\1\162\1\143\1\uffff\1\172\1\105\1\144\1\157\1\117\1\147\1\172\1"+
        "\127\1\124\1\145\1\141\1\144\2\145\1\164\1\156\1\160\2\145\1\163"+
        "\1\164\1\145\1\172\1\151\1\144\1\163\1\172\1\154\1\164\1\172\1\143"+
        "\1\145\1\154\1\145\1\uffff\1\154\1\157\1\120\1\163\1\172\1\163\1"+
        "\172\2\150\1\172\1\120\1\151\3\164\1\147\1\151\1\172\1\162\1\163"+
        "\1\172\1\uffff\1\164\1\146\2\172\1\145\1\120\1\117\1\uffff\1\141"+
        "\1\164\1\uffff\1\122\1\101\1\155\1\115\1\162\1\uffff\1\151\1\164"+
        "\1\172\1\162\1\156\1\145\1\172\1\154\1\172\1\164\1\145\1\164\1\114"+
        "\1\172\1\163\1\155\1\uffff\1\154\1\162\1\116\1\uffff\1\163\1\151"+
        "\1\uffff\1\145\1\163\1\151\1\157\1\172\1\145\1\155\1\157\1\145\1"+
        "\uffff\1\145\1\uffff\1\172\1\164\1\157\1\154\1\143\1\163\1\172\1"+
        "\151\1\162\1\143\2\157\1\145\1\uffff\1\172\1\164\2\uffff\1\172\1"+
        "\154\1\124\1\164\1\120\1\172\1\162\1\122\1\172\1\157\1\144\1\171"+
        "\1\uffff\1\151\1\147\1\144\1\uffff\1\172\1\uffff\1\172\1\114\1\163"+
        "\1\141\1\uffff\1\172\3\145\1\154\1\141\1\172\1\157\1\120\1\172\2"+
        "\156\1\uffff\1\116\1\172\1\151\2\164\1\151\1\uffff\1\172\1\156\2"+
        "\141\1\151\1\157\1\145\1\157\1\172\1\uffff\1\166\1\157\1\141\1\156"+
        "\1\160\1\172\1\157\1\uffff\1\172\1\uffff\1\141\1\172\1\157\1\154"+
        "\1\uffff\2\145\1\uffff\1\165\1\164\1\154\1\156\1\154\1\122\2\uffff"+
        "\1\141\1\172\1\171\1\uffff\1\156\1\144\1\156\1\141\1\155\1\uffff"+
        "\1\156\1\157\1\uffff\1\145\1\172\1\141\1\151\1\uffff\1\156\2\172"+
        "\1\156\1\151\1\uffff\1\164\1\143\1\154\1\147\1\164\1\146\1\160\1"+
        "\uffff\1\145\1\165\1\154\1\164\1\145\1\uffff\1\162\1\uffff\1\143"+
        "\1\117\1\uffff\1\162\2\141\2\156\1\150\1\145\1\147\2\145\1\171\1"+
        "\uffff\1\157\1\164\1\120\1\172\1\143\1\145\1\172\1\162\1\172\1\uffff"+
        "\1\155\1\147\1\164\2\uffff\1\164\1\156\1\141\1\145\1\111\1\150\2"+
        "\164\2\172\1\156\1\101\1\141\1\162\1\164\1\156\1\145\1\124\1\120"+
        "\1\143\1\172\2\144\2\172\1\122\1\172\1\143\1\157\1\165\1\104\1\154"+
        "\1\uffff\1\145\1\172\1\uffff\1\164\1\156\1\154\1\uffff\1\145\1\150"+
        "\1\163\1\172\1\164\1\154\1\155\1\156\1\164\1\157\1\172\2\uffff\1"+
        "\144\2\154\1\164\1\172\1\164\1\155\1\172\1\154\1\145\1\uffff\1\145"+
        "\1\126\2\uffff\1\151\1\145\1\uffff\1\164\1\165\1\164\2\141\1\155"+
        "\1\uffff\1\172\1\164\1\141\1\172\1\164\1\172\1\uffff\1\172\1\111"+
        "\1\145\1\144\1\172\1\155\1\uffff\1\126\1\151\1\101\1\151\1\uffff"+
        "\1\172\1\145\1\uffff\1\141\1\155\1\162\1\157\1\151\1\142\1\146\1"+
        "\141\1\164\1\172\1\164\1\143\1\145\1\uffff\1\172\1\143\1\uffff\1"+
        "\172\2\uffff\2\156\1\145\1\uffff\1\172\1\157\1\151\1\147\1\154\1"+
        "\145\1\uffff\1\156\1\143\1\145\1\151\1\154\1\163\1\162\1\172\1\156"+
        "\1\172\1\uffff\1\141\1\145\1\156\1\uffff\1\145\1\uffff\1\144\1\164"+
        "\1\156\1\uffff\1\154\1\163\1\156\1\151\1\163\1\164\1\145\2\156\1"+
        "\157\1\151\1\141\1\uffff\1\147\1\uffff\1\172\1\155\1\164\1\155\1"+
        "\145\1\172\1\164\1\157\1\151\1\155\1\147\2\172\1\155\1\164\1\147"+
        "\1\162\1\142\1\162\1\154\1\uffff\1\145\1\172\1\145\1\156\1\141\1"+
        "\uffff\1\172\1\162\1\142\1\145\1\156\1\uffff\1\141\1\uffff\1\145"+
        "\1\104\2\172\1\151\1\171\1\145\1\156\1\uffff\1\156\2\164\1\uffff"+
        "\1\172\1\151\1\156\1\155\1\164\1\156\1\141\2\uffff\1\154\2\172\2"+
        "\164\1\172\1\141\1\uffff\1\154\1\164\1\145\1\141\2\164\1\151\2\uffff"+
        "\2\104\1\uffff\1\172\1\151\1\172\1\156\1\172\1\104\1\141\1\164\2"+
=======
        "\1\164\1\157\1\155\1\162\1\165\2\172\1\151\1\145\1\162\1\164\1\57"+
        "\1\151\1\157\1\145\1\157\1\uffff\1\141\2\uffff\1\172\2\uffff\2\uffff"+
        "\4\uffff\1\154\2\uffff\1\147\1\uffff\1\165\1\162\1\160\1\154\1\156"+
        "\1\114\1\154\1\141\1\123\1\124\1\143\1\162\1\120\1\170\1\116\1\151"+
        "\1\163\1\124\1\143\1\106\1\156\1\107\1\156\1\165\1\157\1\141\1\157"+
        "\1\150\1\156\1\144\2\uffff\1\162\1\141\1\uffff\2\164\1\uffff\2\151"+
        "\1\142\1\143\1\146\1\165\1\171\1\154\1\141\1\143\1\156\1\164\1\156"+
        "\1\146\1\157\1\uffff\1\146\1\157\1\uffff\1\144\2\151\1\163\1\141"+
        "\3\uffff\1\147\1\154\3\162\1\uffff\1\160\5\uffff\1\151\2\145\1\147"+
        "\1\172\1\163\1\164\1\111\1\151\1\143\1\110\1\172\1\157\1\145\1\172"+
        "\1\164\1\124\1\154\1\164\1\124\1\153\1\124\1\145\1\110\1\144\1\164"+
        "\1\156\1\144\1\142\1\162\1\141\1\163\1\147\1\164\1\156\1\143\2\141"+
        "\1\154\1\163\1\160\1\145\1\141\1\164\1\162\1\154\1\171\1\147\1\172"+
        "\1\144\1\164\2\144\1\146\1\163\1\146\1\163\1\164\1\147\1\144\1\145"+
        "\1\154\1\150\1\141\1\145\1\164\1\151\1\120\1\160\1\163\1\172\2\145"+
        "\1\uffff\1\145\1\172\1\104\1\156\1\153\1\172\1\uffff\1\162\1\143"+
        "\1\uffff\1\172\1\105\1\144\1\157\1\117\1\147\1\172\1\127\1\124\1"+
        "\145\1\141\1\144\2\145\1\164\1\156\1\160\2\145\1\163\1\164\1\145"+
        "\1\172\1\151\1\144\1\163\1\172\1\154\1\164\1\172\1\143\1\145\1\154"+
        "\1\145\1\uffff\1\154\1\157\1\172\1\120\1\163\1\172\1\163\1\172\2"+
        "\150\1\120\1\164\1\151\2\164\1\147\1\151\1\172\1\162\1\163\1\172"+
        "\1\uffff\1\164\1\146\1\172\1\151\1\uffff\1\172\1\145\1\120\1\117"+
        "\1\uffff\1\141\1\164\1\uffff\1\122\1\101\1\155\1\115\1\162\1\uffff"+
        "\1\151\1\164\1\172\1\162\1\156\1\145\1\172\1\154\1\172\1\164\1\145"+
        "\1\164\1\114\1\172\1\163\1\155\1\uffff\1\154\1\162\1\116\1\uffff"+
        "\1\163\1\151\1\uffff\1\145\1\163\1\151\1\157\1\172\1\145\1\155\1"+
        "\uffff\1\157\1\145\1\uffff\1\145\1\uffff\1\172\1\164\1\154\1\163"+
        "\1\143\1\172\1\151\1\162\1\143\2\157\1\145\1\uffff\1\172\1\164\1"+
        "\uffff\1\172\1\uffff\1\172\1\154\1\124\1\164\1\120\1\172\1\162\1"+
        "\122\1\172\1\157\1\144\1\171\1\uffff\1\151\1\147\1\144\1\uffff\1"+
        "\172\1\uffff\1\172\1\114\1\163\1\141\1\uffff\1\172\3\145\1\154\1"+
        "\141\1\172\1\157\1\120\1\172\2\156\1\uffff\1\116\1\172\1\151\2\164"+
        "\1\151\1\uffff\1\172\1\141\1\151\1\157\1\145\1\157\2\172\1\uffff"+
        "\1\166\1\157\1\141\1\156\1\160\1\172\1\157\1\uffff\1\172\1\145\1"+
        "\uffff\1\141\1\172\1\157\1\154\1\uffff\2\145\1\uffff\1\165\1\164"+
        "\1\154\1\156\1\154\1\122\2\uffff\1\141\1\172\1\171\1\uffff\1\156"+
        "\1\144\1\156\1\141\1\155\1\uffff\1\156\1\157\1\uffff\1\145\1\172"+
        "\1\141\1\151\1\uffff\1\156\2\172\1\156\1\151\1\uffff\1\143\1\147"+
        "\1\164\1\146\1\160\2\uffff\1\145\1\165\1\154\1\164\1\145\1\uffff"+
        "\1\162\1\uffff\1\172\1\143\1\117\1\uffff\1\162\2\141\2\156\1\150"+
        "\1\145\1\147\2\145\1\171\1\uffff\1\157\1\164\1\120\1\172\1\143\1"+
        "\145\1\172\1\162\1\172\1\uffff\1\155\1\147\1\164\2\uffff\1\164\1"+
        "\156\1\145\1\150\2\164\2\172\1\156\1\101\1\141\1\162\1\164\1\156"+
        "\1\uffff\1\145\1\124\1\120\1\143\1\172\2\144\2\172\1\122\1\172\1"+
        "\143\1\157\1\165\1\104\1\154\1\uffff\1\145\1\172\1\uffff\1\164\1"+
        "\156\1\154\1\uffff\1\145\1\150\1\163\1\172\1\164\1\155\1\164\1\157"+
        "\1\172\2\uffff\1\144\2\154\1\164\1\172\1\164\1\155\1\172\1\154\1"+
        "\145\1\uffff\1\145\1\126\2\uffff\1\151\1\145\1\uffff\1\164\1\165"+
        "\1\164\2\141\1\155\1\uffff\1\172\1\164\1\141\1\172\1\164\1\172\1"+
        "\uffff\1\172\1\145\1\172\1\155\1\uffff\1\126\1\151\1\101\1\151\1"+
        "\uffff\1\172\1\145\1\uffff\1\141\1\155\1\162\1\157\1\151\1\142\1"+
        "\146\1\141\1\164\1\172\1\164\1\143\1\145\1\uffff\1\172\1\143\1\uffff"+
        "\1\172\2\uffff\1\156\1\uffff\1\172\1\157\1\151\1\147\1\154\1\145"+
        "\1\uffff\1\156\1\143\1\145\1\151\1\154\1\163\1\162\1\172\1\156\1"+
        "\172\1\uffff\1\141\1\145\1\156\1\uffff\1\145\1\uffff\1\164\1\uffff"+
        "\1\154\1\163\1\156\1\151\1\163\1\164\1\145\2\156\1\157\1\151\1\141"+
        "\1\uffff\1\147\1\uffff\1\172\1\155\1\164\1\155\1\172\1\157\1\151"+
        "\1\155\1\147\2\172\1\155\1\164\1\147\1\162\1\142\1\162\1\154\1\uffff"+
        "\1\145\1\172\1\145\1\141\1\uffff\1\162\1\142\1\145\1\156\1\uffff"+
        "\1\141\1\uffff\1\145\1\104\2\172\1\151\1\171\1\145\1\156\1\uffff"+
        "\1\156\1\164\1\172\1\151\1\156\1\155\1\164\1\156\1\141\2\uffff\1"+
        "\154\2\172\2\164\1\141\1\uffff\1\154\1\164\1\145\1\141\2\164\1\151"+
        "\2\uffff\2\104\1\172\1\151\1\172\1\156\1\172\1\104\1\141\1\164\2"+
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
        "\141\1\uffff\1\164\1\uffff\1\164\1\uffff\1\141\1\172\1\171\2\164"+
        "\1\171\1\172\1\164\1\uffff\1\172\2\141\1\172\1\uffff\1\141\1\uffff"+
        "\2\172\1\uffff\1\172\3\uffff";
    static final String DFA12_acceptS =
<<<<<<< HEAD
        "\16\uffff\1\21\1\22\1\uffff\1\24\1\uffff\1\26\23\uffff\1\127\1\uffff"+
        "\1\145\1\150\1\uffff\1\151\1\152\2\uffff\1\156\1\157\1\34\1\1\1"+
        "\uffff\1\2\1\151\1\uffff\1\3\35\uffff\1\21\1\22\2\uffff\1\24\2\uffff"+
        "\1\26\17\uffff\1\143\2\uffff\1\144\6\uffff\1\154\1\155\1\110\5\uffff"+
        "\1\127\1\uffff\1\145\1\150\1\152\1\153\1\156\110\uffff\1\113\5\uffff"+
        "\1\10\2\uffff\1\13\42\uffff\1\53\25\uffff\1\4\7\uffff\1\7\2\uffff"+
        "\1\55\5\uffff\1\16\20\uffff\1\25\3\uffff\1\146\2\uffff\1\111\11"+
        "\uffff\1\131\1\uffff\1\132\15\uffff\1\32\2\uffff\1\5\1\6\14\uffff"+
        "\1\17\3\uffff\1\20\1\uffff\1\31\4\uffff\1\23\14\uffff\1\52\6\uffff"+
        "\1\66\11\uffff\1\112\7\uffff\1\35\1\uffff\1\61\4\uffff\1\14\2\uffff"+
        "\1\15\6\uffff\1\30\1\142\3\uffff\1\106\5\uffff\1\33\2\uffff\1\43"+
        "\4\uffff\1\114\5\uffff\1\67\7\uffff\1\133\5\uffff\1\44\1\uffff\1"+
        "\103\2\uffff\1\11\13\uffff\1\135\11\uffff\1\51\3\uffff\1\64\1\65"+
        "\40\uffff\1\27\2\uffff\1\63\3\uffff\1\50\13\uffff\1\101\1\147\12"+
        "\uffff\1\54\2\uffff\1\117\1\122\2\uffff\1\46\6\uffff\1\57\6\uffff"+
        "\1\71\6\uffff\1\100\4\uffff\1\37\2\uffff\1\12\15\uffff\1\36\2\uffff"+
        "\1\60\1\uffff\1\141\1\72\3\uffff\1\76\6\uffff\1\140\12\uffff\1\136"+
        "\3\uffff\1\137\1\uffff\1\104\3\uffff\1\77\14\uffff\1\41\1\uffff"+
        "\1\130\24\uffff\1\42\5\uffff\1\125\5\uffff\1\134\1\uffff\1\126\10"+
        "\uffff\1\45\3\uffff\1\74\7\uffff\1\56\1\116\7\uffff\1\115\7\uffff"+
        "\1\40\1\47\2\uffff\1\73\12\uffff\1\70\1\uffff\1\123\1\uffff\1\75"+
        "\10\uffff\1\102\4\uffff\1\124\1\uffff\1\121\2\uffff\1\120\1\uffff"+
        "\1\107\1\105\1\62";
    static final String DFA12_specialS =
        "\1\0\55\uffff\1\1\1\2\u02d8\uffff}>";
    static final String[] DFA12_transitionS = {
            "\11\61\2\60\2\61\1\60\22\61\1\60\1\52\1\56\4\61\1\57\4\61\1"+
            "\21\1\1\1\47\1\42\12\55\1\23\2\61\1\51\3\61\1\31\1\12\1\11\1"+
            "\7\1\2\1\44\1\37\1\46\1\30\1\54\1\15\1\13\3\54\1\27\1\54\1\14"+
            "\1\6\1\10\1\54\1\45\4\54\3\61\1\53\1\54\1\61\1\54\1\32\1\24"+
            "\1\22\1\3\1\5\1\54\1\36\1\41\2\54\1\25\1\50\2\54\1\20\1\54\1"+
            "\43\1\26\1\4\1\54\1\40\1\35\1\33\1\34\1\54\1\16\1\61\1\17\uff82"+
            "\61",
            "\1\62",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\13\66\1\64\16\66",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\3\66\1\67\26\66",
            "\1\72\15\uffff\1\73\2\uffff\1\71",
            "\1\74",
=======
        "\16\uffff\1\21\1\22\1\uffff\1\24\1\uffff\1\26\22\uffff\1\131\1\uffff"+
        "\1\147\1\152\1\uffff\1\153\1\154\2\uffff\1\160\1\161\1\34\1\1\1"+
        "\uffff\1\2\1\153\1\uffff\1\3\36\uffff\1\21\1\22\2\uffff\1\24\2\uffff"+
        "\1\26\17\uffff\1\145\2\uffff\1\146\5\uffff\1\156\1\157\1\106\5\uffff"+
        "\1\131\1\uffff\1\147\1\152\1\154\1\155\1\160\111\uffff\1\111\6\uffff"+
        "\1\10\2\uffff\1\13\42\uffff\1\53\25\uffff\1\4\4\uffff\1\123\4\uffff"+
        "\1\7\2\uffff\1\55\5\uffff\1\16\20\uffff\1\25\3\uffff\1\150\2\uffff"+
        "\1\107\7\uffff\1\121\2\uffff\1\133\1\uffff\1\134\14\uffff\1\32\2"+
        "\uffff\1\5\1\uffff\1\6\14\uffff\1\17\3\uffff\1\20\1\uffff\1\31\4"+
        "\uffff\1\23\14\uffff\1\52\6\uffff\1\66\10\uffff\1\110\7\uffff\1"+
        "\35\2\uffff\1\61\4\uffff\1\14\2\uffff\1\15\6\uffff\1\30\1\144\3"+
        "\uffff\1\104\5\uffff\1\33\2\uffff\1\43\4\uffff\1\112\5\uffff\1\67"+
        "\5\uffff\1\135\1\122\5\uffff\1\44\1\uffff\1\101\3\uffff\1\11\13"+
        "\uffff\1\137\11\uffff\1\51\3\uffff\1\64\1\65\16\uffff\1\124\20\uffff"+
        "\1\27\2\uffff\1\63\3\uffff\1\50\11\uffff\1\76\1\151\12\uffff\1\54"+
        "\2\uffff\1\115\1\120\2\uffff\1\46\6\uffff\1\57\6\uffff\1\71\4\uffff"+
        "\1\75\4\uffff\1\37\2\uffff\1\12\15\uffff\1\36\2\uffff\1\60\1\uffff"+
        "\1\143\1\72\1\uffff\1\73\6\uffff\1\142\12\uffff\1\140\3\uffff\1"+
        "\141\1\uffff\1\102\1\uffff\1\74\14\uffff\1\41\1\uffff\1\132\22\uffff"+
        "\1\42\4\uffff\1\127\4\uffff\1\136\1\uffff\1\130\10\uffff\1\45\11"+
        "\uffff\1\56\1\114\6\uffff\1\113\7\uffff\1\40\1\47\14\uffff\1\70"+
        "\1\uffff\1\125\1\uffff\1\77\10\uffff\1\100\4\uffff\1\126\1\uffff"+
        "\1\117\2\uffff\1\116\1\uffff\1\105\1\103\1\62";
    static final String DFA12_specialS =
        "\1\0\54\uffff\1\1\1\2\u02cc\uffff}>";
    static final String[] DFA12_transitionS = {
            "\11\60\2\57\2\60\1\57\22\60\1\57\1\51\1\55\4\60\1\56\4\60\1"+
            "\21\1\1\1\46\1\41\12\54\1\23\2\60\1\50\3\60\1\31\1\12\1\11\1"+
            "\7\1\2\1\43\1\37\1\45\1\30\1\53\1\15\1\13\3\53\1\27\1\53\1\14"+
            "\1\6\1\10\1\53\1\44\4\53\3\60\1\52\1\53\1\60\1\53\1\32\1\24"+
            "\1\22\1\3\1\5\1\53\1\36\1\40\2\53\1\25\1\47\2\53\1\20\1\53\1"+
            "\42\1\26\1\4\2\53\1\35\1\33\1\34\1\53\1\16\1\60\1\17\uff82\60",
            "\1\61",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\13\65\1\63\16\65",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\3\65\1\66\26\65",
            "\1\71\15\uffff\1\72\2\uffff\1\70",
            "\1\73\15\uffff\1\74",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\75\40\uffff\1\76\3\uffff\1\77",
            "\1\100\15\uffff\1\101\25\uffff\1\102\3\uffff\1\103",
            "\1\104\25\uffff\1\105",
            "\1\106\42\uffff\1\107\14\uffff\1\110",
            "\1\111\21\uffff\1\112",
            "\1\113\43\uffff\1\114",
            "\1\115\33\uffff\1\116\11\uffff\1\117",
            "\1\125\3\uffff\1\124\2\uffff\1\121\1\uffff\1\120\1\uffff\1"+
            "\122\2\uffff\1\123",
            "",
            "",
            "\1\131\2\uffff\1\130",
            "",
            "\1\133\3\uffff\1\134",
            "",
            "\1\136\3\uffff\1\137",
            "\1\140\3\uffff\1\142\11\uffff\1\141",
            "\1\143\4\uffff\1\144",
            "\1\145",
            "\1\146",
            "\1\147",
            "\1\152\11\uffff\1\151\5\uffff\1\150",
<<<<<<< HEAD
            "\12\66\7\uffff\16\66\1\153\13\66\4\uffff\1\66\1\uffff\17\66"+
            "\1\154\12\66",
            "\12\66\7\uffff\16\66\1\156\13\66\4\uffff\1\66\1\uffff\17\66"+
            "\1\157\12\66",
            "\1\161",
            "\1\162\11\uffff\1\163",
            "\1\164",
            "\1\165",
            "\1\166",
            "\1\167\4\uffff\1\170",
            "\1\173\3\uffff\1\172",
            "\1\174",
            "\1\175",
            "\1\176",
            "",
            "\1\u0080",
=======
            "\12\65\7\uffff\16\65\1\153\13\65\4\uffff\1\65\1\uffff\17\65"+
            "\1\154\12\65",
            "\12\65\7\uffff\16\65\1\156\13\65\4\uffff\1\65\1\uffff\17\65"+
            "\1\157\12\65",
            "\1\161",
            "\1\162",
            "\1\163",
            "\1\164\5\uffff\1\165",
            "\1\166\4\uffff\1\167",
            "\1\172\3\uffff\1\171",
            "\1\173",
            "\1\174",
            "\1\175",
            "",
            "\1\177",
            "",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "",
            "\32\65\4\uffff\1\65\1\uffff\32\65",
            "",
            "",
            "\0\u0083",
            "\0\u0083",
            "",
<<<<<<< HEAD
            "\0\u0084",
            "\0\u0084",
=======
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "",
            "",
            "",
            "\1\u0085",
            "",
<<<<<<< HEAD
            "\1\u0086",
=======
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "",
            "\1\u0086",
            "",
            "\1\u0087",
<<<<<<< HEAD
            "",
=======
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u0088",
            "\1\u0089",
            "\1\u008a",
            "\1\u008b",
            "\1\u008c",
            "\1\u008d",
            "\1\u008e",
            "\1\u008f",
            "\1\u0090",
            "\1\u0091",
            "\1\u0092",
            "\1\u0093",
            "\1\u0094",
            "\1\u0095",
            "\1\u0096",
            "\1\u0097",
            "\1\u0098",
            "\1\u0099",
            "\1\u009a",
            "\1\u009b",
            "\1\u009c",
            "\1\u009e\12\uffff\1\u009d",
            "\1\u009f",
            "\1\u00a0",
            "\1\u00a1",
            "\1\u00a2",
            "\1\u00a3",
            "\1\u00a4",
            "\1\u00a5",
            "",
            "",
            "\1\u00a7\10\uffff\1\u00a6",
            "\1\u00a8",
            "",
            "\1\u00a9",
            "\1\u00aa",
            "",
            "\1\u00ab",
            "\1\u00ac\7\uffff\1\u00ad",
            "\1\u00ae",
            "\1\u00af",
            "\1\u00b0",
            "\1\u00b1",
            "\1\u00b2",
            "\1\u00b3",
            "\1\u00b4",
            "\1\u00b5",
            "\1\u00b6",
            "\1\u00b8\7\uffff\1\u00b7",
            "\1\u00b9",
            "\1\u00ba",
<<<<<<< HEAD
            "",
            "\1\u00bb",
=======
            "\1\u00bb",
            "",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u00bc",
            "",
            "\1\u00bd",
<<<<<<< HEAD
=======
            "",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u00be",
            "\1\u00bf",
            "\1\u00c0",
            "\1\u00c1",
            "\1\u00c2",
            "",
            "",
            "",
            "\1\u00c3",
            "\1\u00c4",
            "\1\u00c5",
            "\1\u00c6",
            "\1\u00c7",
            "",
            "\1\u00c8",
            "",
            "",
            "",
            "",
            "",
            "\1\u00c9",
            "\1\u00ca",
            "\1\u00cb",
            "\1\u00cc",
<<<<<<< HEAD
            "\12\66\7\uffff\13\66\1\u00cd\16\66\4\uffff\1\66\1\uffff\32"+
            "\66",
=======
            "\12\65\7\uffff\13\65\1\u00cd\16\65\4\uffff\1\65\1\uffff\32"+
            "\65",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u00cf",
            "\1\u00d0",
            "\1\u00d1",
            "\1\u00d2",
            "\1\u00d3",
<<<<<<< HEAD
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u00d5",
            "\1\u00d6",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u00d8",
=======
            "\1\u00d4",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u00d6",
            "\1\u00d7",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u00d9",
            "\1\u00da",
            "\1\u00db",
            "\1\u00dc",
            "\1\u00dd",
            "\1\u00de",
            "\1\u00df",
            "\1\u00e0",
            "\1\u00e1",
            "\1\u00e2",
            "\1\u00e3",
            "\1\u00e4",
            "\1\u00e5",
<<<<<<< HEAD
            "\1\u00e7\10\uffff\1\u00e6",
            "\1\u00e8",
=======
            "\1\u00e6",
            "\1\u00e8\10\uffff\1\u00e7",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u00e9",
            "\1\u00ea",
            "\1\u00eb",
            "\1\u00ec",
            "\1\u00ed",
            "\1\u00ee",
            "\1\u00ef",
            "\1\u00f0",
            "\1\u00f1",
            "\1\u00f2",
            "\1\u00f3",
            "\1\u00f4",
            "\1\u00f5",
            "\1\u00f6",
            "\1\u00f7",
            "\1\u00f8",
            "\1\u00f9",
<<<<<<< HEAD
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u00fb",
=======
            "\1\u00fa",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u00fc",
            "\1\u00fd",
            "\1\u00fe",
            "\1\u00ff",
            "\1\u0100",
            "\1\u0101",
            "\1\u0102",
            "\1\u0103",
            "\1\u0104",
            "\1\u0105",
            "\1\u0106",
            "\1\u0107",
            "\1\u0108",
            "\1\u0109",
            "\1\u010a",
            "\1\u010b",
            "\1\u010c",
            "\1\u010d",
            "\1\u010e",
            "\1\u010f",
<<<<<<< HEAD
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u0111",
=======
            "\1\u0110",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u0112",
            "",
            "\1\u0113",
            "",
            "\1\u0114",
<<<<<<< HEAD
            "\1\u0115",
            "\1\u0116",
            "\12\66\7\uffff\3\66\1\u0117\26\66\4\uffff\1\66\1\uffff\32\66",
            "",
=======
            "\12\65\7\uffff\22\65\1\u0115\7\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u0117",
            "\1\u0118",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u0119",
            "\12\65\7\uffff\3\65\1\u011a\26\65\4\uffff\1\65\1\uffff\32\65",
            "",
            "\1\u011c",
            "\1\u011d",
            "",
<<<<<<< HEAD
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u011c",
            "\1\u011d",
            "\1\u011e",
            "\1\u011f",
            "\1\u0120",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u0123\3\uffff\1\u0122",
            "\1\u0124",
            "\1\u0125",
            "\1\u0126",
=======
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u011f",
            "\1\u0120",
            "\1\u0121",
            "\1\u0122",
            "\1\u0123",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u0126\3\uffff\1\u0125",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u0127",
            "\1\u0128",
            "\1\u0129",
            "\1\u012a",
            "\1\u012b",
            "\1\u012c",
            "\1\u012d",
            "\1\u012e",
            "\1\u012f",
            "\1\u0130",
            "\1\u0131",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u0133",
            "\1\u0134",
<<<<<<< HEAD
            "\1\u0135",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u0137",
            "\1\u0138",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u013a",
            "\1\u013b",
            "\1\u013d\4\uffff\1\u013c",
            "\1\u013e",
            "",
            "\1\u013f",
            "\1\u0140",
=======
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u0136",
            "\1\u0137",
            "\1\u0138",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u013a",
            "\1\u013b",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u013d",
            "\1\u013e",
            "\1\u0140\4\uffff\1\u013f",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u0141",
            "",
            "\1\u0142",
<<<<<<< HEAD
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u0144",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u0146",
            "\1\u0147",
            "\1\u0148",
            "\1\u0149",
=======
            "\1\u0143",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u0145",
            "\1\u0146",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u0148",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u014a",
            "\1\u014b",
            "\1\u014c",
            "\1\u014d",
            "\1\u014e",
            "\1\u014f",
            "\1\u0150",
            "\1\u0151",
            "\1\u0152",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "",
            "\1\u0154",
            "\1\u0155",
<<<<<<< HEAD
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
=======
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "",
            "\1\u0157",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u0158",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u015a",
            "",
<<<<<<< HEAD
            "\1\u015b",
=======
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u015c",
            "",
            "\1\u015d",
            "\1\u015e",
<<<<<<< HEAD
=======
            "",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u015f",
            "\1\u0160",
            "",
            "\1\u0161",
<<<<<<< HEAD
            "",
            "\1\u0162",
            "\1\u0163",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
=======
            "\1\u0162",
            "\1\u0163",
            "\1\u0164",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u0165",
            "",
            "\1\u0166",
            "\1\u0167",
<<<<<<< HEAD
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u0169",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
=======
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u0169",
            "\1\u016a",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u016b",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u016d",
<<<<<<< HEAD
            "\1\u016e",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u0170",
            "\1\u0171",
            "",
            "\1\u0172",
            "\1\u0174\41\uffff\1\u0173",
            "\1\u0175",
            "",
            "\1\u0176",
            "\1\u0177",
            "",
            "\1\u0178",
=======
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u016f",
            "\1\u0170",
            "\1\u0171",
            "\1\u0172",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u0174",
            "\1\u0175",
            "",
            "\1\u0176",
            "\1\u0178\41\uffff\1\u0177",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u0179",
            "",
            "\1\u017a",
            "\1\u017b",
            "",
            "\1\u017c",
            "\1\u017d",
            "\1\u017e",
            "\1\u017f",
<<<<<<< HEAD
            "\1\u0180",
=======
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u0181",
            "\1\u0182",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "",
            "\1\u0181",
            "",
            "\12\66\7\uffff\7\66\1\u0182\22\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u0184",
            "\1\u0185",
<<<<<<< HEAD
            "\1\u0186",
            "\1\u0187",
            "\1\u0189\11\uffff\1\u018a\5\uffff\1\u0188\1\uffff\1\u018b\36"+
            "\uffff\1\u018c",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u018e",
            "\1\u018f",
            "\1\u0190",
            "\1\u0191",
            "\1\u0192",
            "\1\u0193",
            "",
            "\12\66\7\uffff\17\66\1\u0194\12\66\4\uffff\1\66\1\uffff\32"+
            "\66",
            "\1\u0196",
            "",
            "",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u0198",
            "\1\u0199",
            "\1\u019a",
            "\1\u019b",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u019d",
            "\1\u019e",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u01a0",
            "\1\u01a1",
            "\1\u01a2",
            "",
            "\1\u01a3",
=======
            "",
            "\12\65\7\uffff\7\65\1\u0186\22\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u0188",
            "\1\u0189",
            "\1\u018b\11\uffff\1\u018c\5\uffff\1\u018a\1\uffff\1\u018d\36"+
            "\uffff\1\u018e",
            "\1\u018f",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u0191",
            "\1\u0192",
            "\1\u0193",
            "\1\u0194",
            "\1\u0195",
            "\1\u0196",
            "",
            "\12\65\7\uffff\17\65\1\u0197\12\65\4\uffff\1\65\1\uffff\32"+
            "\65",
            "\1\u0199",
            "",
            "\1\u019a",
            "",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u019c",
            "\1\u019d",
            "\1\u019e",
            "\1\u019f",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u01a1",
            "\1\u01a2",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u01a4",
            "\1\u01a5",
            "\1\u01a6",
            "",
<<<<<<< HEAD
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u01a8",
            "\1\u01a9",
            "\1\u01aa",
            "",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u01ac",
            "\1\u01ad",
            "\1\u01ae",
            "\1\u01af",
            "\1\u01b0",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u01b2",
            "\1\u01b3",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u01b5",
            "\1\u01b6",
            "",
            "\1\u01b7",
            "\12\66\7\uffff\21\66\1\u01b8\10\66\4\uffff\1\66\1\uffff\32"+
            "\66",
            "\1\u01ba",
            "\1\u01bb",
            "\1\u01bc",
            "\1\u01bd",
            "",
            "\12\66\7\uffff\7\66\1\u01be\22\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u01c0",
            "\1\u01c1",
            "\1\u01c2",
            "\1\u01c3",
            "\1\u01c4",
            "\1\u01c5",
            "\1\u01c6",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "",
            "\1\u01c8",
            "\1\u01c9",
            "\1\u01ca",
            "\1\u01cb",
            "\1\u01cc",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u01ce",
            "",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "",
            "\1\u01d0",
            "\12\66\7\uffff\3\66\1\u01d1\26\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u01d3",
            "\1\u01d4",
            "",
            "\1\u01d5",
            "\1\u01d6",
            "",
=======
            "\1\u01a7",
            "\1\u01a8",
            "\1\u01a9",
            "",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u01ac",
            "\1\u01ad",
            "\1\u01ae",
            "",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u01b0",
            "\1\u01b1",
            "\1\u01b2",
            "\1\u01b3",
            "\1\u01b4",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u01b6",
            "\1\u01b7",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u01b9",
            "\1\u01ba",
            "",
            "\1\u01bb",
            "\12\65\7\uffff\21\65\1\u01bc\10\65\4\uffff\1\65\1\uffff\32"+
            "\65",
            "\1\u01be",
            "\1\u01bf",
            "\1\u01c0",
            "\1\u01c1",
            "",
            "\12\65\7\uffff\7\65\1\u01c2\22\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u01c4",
            "\1\u01c5",
            "\1\u01c6",
            "\1\u01c7",
            "\1\u01c8",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "",
            "\1\u01cb",
            "\1\u01cc",
            "\1\u01cd",
            "\1\u01ce",
            "\1\u01cf",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u01d1",
            "",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u01d3",
            "",
            "\1\u01d4",
            "\12\65\7\uffff\3\65\1\u01d5\26\65\4\uffff\1\65\1\uffff\32\65",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u01d7",
            "\1\u01d8",
            "",
            "\1\u01d9",
            "\1\u01da",
            "",
            "\1\u01db",
            "\1\u01dc",
<<<<<<< HEAD
            "",
            "",
            "\1\u01dd",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u01df",
            "",
            "\1\u01e0",
            "\1\u01e1",
            "\1\u01e2",
            "\1\u01e3",
            "\1\u01e4",
            "",
            "\1\u01e5",
            "\1\u01e6",
            "",
            "\1\u01e7",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
=======
            "\1\u01dd",
            "\1\u01de",
            "\1\u01df",
            "\1\u01e0",
            "",
            "",
            "\1\u01e1",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u01e3",
            "",
            "\1\u01e4",
            "\1\u01e5",
            "\1\u01e6",
            "\1\u01e7",
            "\1\u01e8",
            "",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u01e9",
            "\1\u01ea",
            "",
            "\1\u01eb",
<<<<<<< HEAD
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u01ee",
            "\1\u01ef",
            "",
            "\1\u01f0",
            "\1\u01f1",
=======
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u01ed",
            "\1\u01ee",
            "",
            "\1\u01ef",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u01f2",
            "\1\u01f3",
            "",
            "\1\u01f4",
            "\1\u01f5",
            "\1\u01f6",
<<<<<<< HEAD
            "",
            "\1\u01f7",
            "\1\u01f8",
            "\1\u01f9",
            "\1\u01fa",
            "\1\u01fb",
            "",
            "\1\u01fd\10\uffff\1\u01fc",
            "",
            "\1\u01fe",
            "\1\u01ff",
            "",
            "\1\u0200",
=======
            "\1\u01f7",
            "\1\u01f8",
            "",
            "",
            "\1\u01f9",
            "\1\u01fa",
            "\1\u01fb",
            "\1\u01fc",
            "\1\u01fd",
            "",
            "\1\u01ff\10\uffff\1\u01fe",
            "",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u0201",
            "\1\u0202",
            "",
            "\1\u0203",
            "\1\u0204",
            "\1\u0205",
            "\1\u0206",
            "\1\u0207",
            "\1\u0208",
            "\1\u0209",
            "\1\u020a",
            "",
            "\1\u020b",
            "\1\u020c",
            "\1\u020d",
<<<<<<< HEAD
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u020f",
            "\1\u0210",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u0213\10\uffff\1\u0212",
            "\12\66\7\uffff\17\66\1\u0214\12\66\4\uffff\1\66\1\uffff\32"+
            "\66",
            "",
            "\1\u0216",
            "\1\u0217",
            "\1\u0218",
            "",
=======
            "",
            "\1\u020e",
            "\1\u020f",
            "\1\u0210",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u0212",
            "\1\u0213",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u0216\10\uffff\1\u0215",
            "\12\65\7\uffff\17\65\1\u0217\12\65\4\uffff\1\65\1\uffff\32"+
            "\65",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "",
            "\1\u0219",
            "\1\u021a",
            "\1\u021b",
<<<<<<< HEAD
=======
            "",
            "",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u021c",
            "\1\u021d",
            "\1\u021e",
            "\1\u021f",
            "\1\u0220",
<<<<<<< HEAD
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u0223",
=======
            "\1\u0221",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u0224",
            "\1\u0225",
            "\1\u0226",
            "\1\u0227",
            "\1\u0228",
            "\1\u0229",
<<<<<<< HEAD
            "\1\u022a",
            "\1\u022b",
            "\1\u022c",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u022e",
            "\1\u022f",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u0232\5\uffff\1\u0233",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u0235",
=======
            "",
            "\1\u022a",
            "\1\u022b",
            "\1\u022c",
            "\1\u022d",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u022f",
            "\1\u0230",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u0233\5\uffff\1\u0234",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u0236",
            "\1\u0237",
            "\1\u0238",
            "\1\u0239",
            "",
            "\1\u023a",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "",
<<<<<<< HEAD
            "\1\u023c",
            "\1\u023d",
            "\1\u023e",
            "",
            "\1\u023f",
            "\1\u0240",
            "\1\u0241",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u0243",
=======
            "\1\u023b",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "",
            "\1\u023d",
            "\1\u023e",
            "\1\u023f",
            "",
            "\1\u0240",
            "\1\u0241",
            "\1\u0242",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u0244",
            "\1\u0245",
            "\1\u0246",
            "\1\u0247",
<<<<<<< HEAD
            "\1\u0248",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "",
            "",
            "\1\u024a",
            "\1\u024b",
            "\1\u024c",
            "\1\u024d",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u024f",
            "\1\u0250",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
=======
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "",
            "",
            "\1\u0249",
            "\1\u024a",
            "\1\u024b",
            "\1\u024c",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u024e",
            "\1\u024f",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u0251",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u0252",
            "",
            "\1\u0253",
<<<<<<< HEAD
            "",
            "\1\u0254",
            "\1\u0255\22\uffff\1\u0256",
            "",
            "",
=======
            "\1\u0254\22\uffff\1\u0255",
            "",
            "",
            "\1\u0256",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u0257",
            "",
            "\1\u0258",
            "",
            "\1\u0259",
            "\1\u025a",
            "\1\u025b",
            "\1\u025c",
            "\1\u025d",
<<<<<<< HEAD
            "\1\u025e",
            "",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u0260",
            "\1\u0261",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u0263",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u0266",
            "\1\u0267",
            "\1\u0268",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u026a",
            "",
            "\1\u026b\22\uffff\1\u026c",
            "\1\u026d",
=======
            "",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u025f",
            "\1\u0260",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u0262",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u0265",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u0267",
            "",
            "\1\u0268\22\uffff\1\u0269",
            "\1\u026a",
            "\1\u026b",
            "\1\u026c",
            "",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u026e",
            "",
            "\1\u026f",
<<<<<<< HEAD
            "",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u0271",
            "",
=======
            "\1\u0270",
            "\1\u0271",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u0272",
            "\1\u0273",
            "\1\u0274",
            "\1\u0275",
            "\1\u0276",
            "\1\u0277",
<<<<<<< HEAD
            "\1\u0278",
            "\1\u0279",
            "\1\u027a",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u027c",
            "\1\u027d",
            "\1\u027e",
            "",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u0280",
            "",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "",
            "",
            "\1\u0282",
            "\1\u0283",
            "\1\u0284",
            "",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
=======
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u0279",
            "\1\u027a",
            "\1\u027b",
            "",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u027d",
            "",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "",
            "",
            "\1\u027f",
            "",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u0281",
            "\1\u0282",
            "\1\u0283",
            "\1\u0284",
            "\1\u0285",
            "",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u0286",
            "\1\u0287",
            "\1\u0288",
            "\1\u0289",
            "\1\u028a",
            "",
            "\1\u028b",
            "\1\u028c",
<<<<<<< HEAD
            "\1\u028d",
            "\1\u028e",
            "\1\u028f",
            "\1\u0290",
            "\1\u0291",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u0293",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
=======
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u028e",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "",
            "\1\u0290",
            "\1\u0291",
            "\1\u0292",
            "",
            "\1\u0293",
            "",
            "\1\u0294",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "",
            "\1\u0295",
            "\1\u0296",
            "\1\u0297",
<<<<<<< HEAD
            "",
=======
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u0298",
            "",
            "\1\u0299",
            "\1\u029a",
            "\1\u029b",
<<<<<<< HEAD
            "",
=======
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u029c",
            "\1\u029d",
            "\1\u029e",
            "\1\u029f",
            "\1\u02a0",
            "",
            "\1\u02a1",
            "",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u02a3",
            "\1\u02a4",
            "\1\u02a5",
<<<<<<< HEAD
            "\1\u02a6",
            "\1\u02a7",
            "",
            "\1\u02a8",
            "",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u02aa",
            "\1\u02ab",
            "\1\u02ac",
            "\1\u02ad",
            "\12\66\7\uffff\3\66\1\u02ae\26\66\4\uffff\1\66\1\uffff\32\66",
=======
            "\12\65\7\uffff\3\65\1\u02a6\26\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u02a8",
            "\1\u02a9",
            "\1\u02aa",
            "\1\u02ab",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\12\65\7\uffff\3\65\1\u02ad\26\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u02af",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u02b0",
            "\1\u02b1",
            "\1\u02b2",
            "\1\u02b3",
            "\1\u02b4",
<<<<<<< HEAD
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\12\66\7\uffff\3\66\1\u02b6\26\66\4\uffff\1\66\1\uffff\32\66",
=======
            "\1\u02b5",
            "",
            "\1\u02b6",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u02b8",
            "\1\u02b9",
            "\1\u02ba",
            "\1\u02bb",
            "\1\u02bc",
            "\1\u02bd",
            "",
            "\1\u02be",
            "",
            "\1\u02bf",
<<<<<<< HEAD
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u02c1",
            "\1\u02c2",
=======
            "\1\u02c0",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u02c3",
            "",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u02c5",
            "\1\u02c6",
<<<<<<< HEAD
            "\1\u02c7",
            "\1\u02c8",
            "",
            "\1\u02c9",
            "",
=======
            "",
            "\1\u02c7",
            "\1\u02c8",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u02ca",
            "\1\u02cb",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u02ce",
            "\1\u02cf",
<<<<<<< HEAD
            "\1\u02d0",
            "\1\u02d1",
            "",
            "\1\u02d2",
=======
            "",
            "",
            "\1\u02d0",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u02d3",
            "\1\u02d4",
            "",
<<<<<<< HEAD
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
=======
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u02d6",
            "\1\u02d7",
            "\1\u02d8",
            "\1\u02d9",
            "\1\u02da",
            "\1\u02db",
<<<<<<< HEAD
            "",
            "",
            "\1\u02dc",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u02df",
            "\1\u02e0",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u02e2",
            "",
            "\1\u02e3",
=======
            "\1\u02dc",
            "",
            "",
            "\1\u02dd",
            "\1\u02de",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u02e0",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u02e2",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u02e4",
            "\1\u02e5",
            "\1\u02e6",
            "\1\u02e7",
            "\1\u02e8",
            "",
            "\1\u02e9",
            "",
<<<<<<< HEAD
            "",
=======
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "\1\u02ea",
            "",
            "\1\u02eb",
<<<<<<< HEAD
            "",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u02ed",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u02ef",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u02f1",
            "\1\u02f2",
            "\1\u02f3",
            "\1\u02f4",
            "\1\u02f5",
            "",
            "\1\u02f6",
            "",
            "\1\u02f7",
            "",
            "\1\u02f8",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u02fa",
            "\1\u02fb",
            "\1\u02fc",
            "\1\u02fd",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u02ff",
            "",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "\1\u0301",
            "\1\u0302",
            "\12\66\7\uffff\32\66\4\uffff\1\66\1\uffff\32\66",
            "",
            "\1\u0304",
=======
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u02ed",
            "\1\u02ee",
            "\1\u02ef",
            "\1\u02f0",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u02f2",
            "",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\1\u02f4",
            "\1\u02f5",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "",
            "\1\u02f7",
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
            "",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "",
            "\12\65\7\uffff\32\65\4\uffff\1\65\1\uffff\32\65",
            "",
            "",
            ""
    };

    static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
    static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
    static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
    static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
    static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
    static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
    static final short[][] DFA12_transition;

    static {
        int numStates = DFA12_transitionS.length;
        DFA12_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
        }
    }

    static class DFA12 extends DFA {

        public DFA12(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = DFA12_eot;
            this.eof = DFA12_eof;
            this.min = DFA12_min;
            this.max = DFA12_max;
            this.accept = DFA12_accept;
            this.special = DFA12_special;
            this.transition = DFA12_transition;
        }
        public String getDescription() {
<<<<<<< HEAD
            return "1:1: Tokens : ( T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | T__104 | T__105 | T__106 | T__107 | T__108 | T__109 | T__110 | T__111 | T__112 | T__113 | T__114 | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
=======
            return "1:1: Tokens : ( T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | T__96 | T__97 | T__98 | T__99 | T__100 | T__101 | T__102 | T__103 | T__104 | T__105 | T__106 | T__107 | T__108 | T__109 | T__110 | T__111 | T__112 | T__113 | T__114 | T__115 | T__116 | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
>>>>>>> fa4c414... Changed the KGridPlacementData of the KRendering metamodel
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA12_0 = input.LA(1);

                        s = -1;
                        if ( (LA12_0=='-') ) {s = 1;}

                        else if ( (LA12_0=='E') ) {s = 2;}

                        else if ( (LA12_0=='e') ) {s = 3;}

                        else if ( (LA12_0=='t') ) {s = 4;}

                        else if ( (LA12_0=='f') ) {s = 5;}

                        else if ( (LA12_0=='S') ) {s = 6;}

                        else if ( (LA12_0=='D') ) {s = 7;}

                        else if ( (LA12_0=='T') ) {s = 8;}

                        else if ( (LA12_0=='C') ) {s = 9;}

                        else if ( (LA12_0=='B') ) {s = 10;}

                        else if ( (LA12_0=='L') ) {s = 11;}

                        else if ( (LA12_0=='R') ) {s = 12;}

                        else if ( (LA12_0=='K') ) {s = 13;}

                        else if ( (LA12_0=='{') ) {s = 14;}

                        else if ( (LA12_0=='}') ) {s = 15;}

                        else if ( (LA12_0=='p') ) {s = 16;}

                        else if ( (LA12_0==',') ) {s = 17;}

                        else if ( (LA12_0=='d') ) {s = 18;}

                        else if ( (LA12_0==':') ) {s = 19;}

                        else if ( (LA12_0=='c') ) {s = 20;}

                        else if ( (LA12_0=='l') ) {s = 21;}

                        else if ( (LA12_0=='s') ) {s = 22;}

                        else if ( (LA12_0=='P') ) {s = 23;}

                        else if ( (LA12_0=='I') ) {s = 24;}

                        else if ( (LA12_0=='A') ) {s = 25;}

                        else if ( (LA12_0=='b') ) {s = 26;}

                        else if ( (LA12_0=='x') ) {s = 27;}

                        else if ( (LA12_0=='y') ) {s = 28;}

                        else if ( (LA12_0=='w') ) {s = 29;}

                        else if ( (LA12_0=='h') ) {s = 30;}

                        else if ( (LA12_0=='G') ) {s = 31;}

                        else if ( (LA12_0=='i') ) {s = 32;}

                        else if ( (LA12_0=='/') ) {s = 33;}

                        else if ( (LA12_0=='r') ) {s = 34;}

                        else if ( (LA12_0=='F') ) {s = 35;}

                        else if ( (LA12_0=='V') ) {s = 36;}

                        else if ( (LA12_0=='H') ) {s = 37;}

                        else if ( (LA12_0=='.') ) {s = 38;}

                        else if ( (LA12_0=='m') ) {s = 39;}

                        else if ( (LA12_0=='=') ) {s = 40;}

                        else if ( (LA12_0=='!') ) {s = 41;}

                        else if ( (LA12_0=='^') ) {s = 42;}

                        else if ( (LA12_0=='J'||(LA12_0>='M' && LA12_0<='O')||LA12_0=='Q'||LA12_0=='U'||(LA12_0>='W' && LA12_0<='Z')||LA12_0=='_'||LA12_0=='a'||LA12_0=='g'||(LA12_0>='j' && LA12_0<='k')||(LA12_0>='n' && LA12_0<='o')||LA12_0=='q'||(LA12_0>='u' && LA12_0<='v')||LA12_0=='z') ) {s = 43;}

                        else if ( ((LA12_0>='0' && LA12_0<='9')) ) {s = 44;}

                        else if ( (LA12_0=='\"') ) {s = 45;}

                        else if ( (LA12_0=='\'') ) {s = 46;}

                        else if ( ((LA12_0>='\t' && LA12_0<='\n')||LA12_0=='\r'||LA12_0==' ') ) {s = 47;}

                        else if ( ((LA12_0>='\u0000' && LA12_0<='\b')||(LA12_0>='\u000B' && LA12_0<='\f')||(LA12_0>='\u000E' && LA12_0<='\u001F')||(LA12_0>='#' && LA12_0<='&')||(LA12_0>='(' && LA12_0<='+')||(LA12_0>=';' && LA12_0<='<')||(LA12_0>='>' && LA12_0<='@')||(LA12_0>='[' && LA12_0<=']')||LA12_0=='`'||LA12_0=='|'||(LA12_0>='~' && LA12_0<='\uFFFF')) ) {s = 48;}

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA12_45 = input.LA(1);

                        s = -1;
                        if ( ((LA12_45>='\u0000' && LA12_45<='\uFFFF')) ) {s = 131;}

                        else s = 48;

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA12_46 = input.LA(1);

                        s = -1;
                        if ( ((LA12_46>='\u0000' && LA12_46<='\uFFFF')) ) {s = 131;}

                        else s = 48;

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA12_46 = input.LA(1);

                        s = -1;
                        if ( ((LA12_46>='\u0000' && LA12_46<='\uFFFF')) ) {s = 132;}

                        else s = 49;

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA12_47 = input.LA(1);

                        s = -1;
                        if ( ((LA12_47>='\u0000' && LA12_47<='\uFFFF')) ) {s = 132;}

                        else s = 49;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 12, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}