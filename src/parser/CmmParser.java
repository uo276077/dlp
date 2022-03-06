// Generated from C:/Users/uo276077/Desktop/DLP Lab/dlpLab3final/lab3project/src/parser\Cmm.g4 by ANTLR 4.9.2
package parser;

    import ast.*;
    import ast.expressions.*;
    import ast.expressions.literals.*;
    import ast.types.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CmmParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, ID=24, INT_CONSTANT=25, 
		REAL_CONSTANT=26, CHAR_CONSTANT=27, ONE_LINE_COMMENT=28, MULTIPLE_LINE_COMMENT=29, 
		WS=30;
	public static final int
		RULE_program = 0, RULE_main_function = 1, RULE_definition = 2, RULE_function_body = 3, 
		RULE_parameters = 4, RULE_variable_definition = 5, RULE_return_type = 6, 
		RULE_type = 7, RULE_built_in_type = 8, RULE_statement = 9, RULE_block = 10, 
		RULE_expression = 11, RULE_arguments = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "main_function", "definition", "function_body", "parameters", 
			"variable_definition", "return_type", "type", "built_in_type", "statement", 
			"block", "expression", "arguments"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'void'", "'main'", "'('", "')'", "'{'", "'}'", "','", "';'", "'['", 
			"']'", "'struct'", "'int'", "'double'", "'char'", "'='", "'write'", "'read'", 
			"'while'", "'if'", "'else'", "'return'", "'+'", "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"ID", "INT_CONSTANT", "REAL_CONSTANT", "CHAR_CONSTANT", "ONE_LINE_COMMENT", 
			"MULTIPLE_LINE_COMMENT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Cmm.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CmmParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public Main_functionContext main_function() {
			return getRuleContext(Main_functionContext.class,0);
		}
		public List<DefinitionContext> definition() {
			return getRuleContexts(DefinitionContext.class);
		}
		public DefinitionContext definition(int i) {
			return getRuleContext(DefinitionContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(26);
					definition();
					}
					} 
				}
				setState(31);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(32);
			main_function();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Main_functionContext extends ParserRuleContext {
		public Function_bodyContext function_body() {
			return getRuleContext(Function_bodyContext.class,0);
		}
		public Main_functionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_main_function; }
	}

	public final Main_functionContext main_function() throws RecognitionException {
		Main_functionContext _localctx = new Main_functionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_main_function);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(T__0);
			setState(35);
			match(T__1);
			setState(36);
			match(T__2);
			setState(37);
			match(T__3);
			setState(38);
			match(T__4);
			setState(39);
			function_body();
			setState(40);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefinitionContext extends ParserRuleContext {
		public Variable_definitionContext variable_definition() {
			return getRuleContext(Variable_definitionContext.class,0);
		}
		public Return_typeContext return_type() {
			return getRuleContext(Return_typeContext.class,0);
		}
		public TerminalNode ID() { return getToken(CmmParser.ID, 0); }
		public Function_bodyContext function_body() {
			return getRuleContext(Function_bodyContext.class,0);
		}
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public DefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definition; }
	}

	public final DefinitionContext definition() throws RecognitionException {
		DefinitionContext _localctx = new DefinitionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_definition);
		int _la;
		try {
			setState(54);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(42);
				variable_definition();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(43);
				return_type();
				setState(44);
				match(ID);
				setState(45);
				match(T__2);
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__11) | (1L << T__12) | (1L << T__13))) != 0)) {
					{
					setState(46);
					parameters();
					}
				}

				setState(49);
				match(T__3);
				setState(50);
				match(T__4);
				setState(51);
				function_body();
				setState(52);
				match(T__5);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_bodyContext extends ParserRuleContext {
		public List<Variable_definitionContext> variable_definition() {
			return getRuleContexts(Variable_definitionContext.class);
		}
		public Variable_definitionContext variable_definition(int i) {
			return getRuleContext(Variable_definitionContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public Function_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_body; }
	}

	public final Function_bodyContext function_body() throws RecognitionException {
		Function_bodyContext _localctx = new Function_bodyContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_function_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13))) != 0)) {
				{
				{
				setState(56);
				variable_definition();
				}
				}
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__20) | (1L << ID) | (1L << INT_CONSTANT) | (1L << REAL_CONSTANT) | (1L << CHAR_CONSTANT))) != 0)) {
				{
				{
				setState(62);
				statement();
				}
				}
				setState(67);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParametersContext extends ParserRuleContext {
		public List<Built_in_typeContext> built_in_type() {
			return getRuleContexts(Built_in_typeContext.class);
		}
		public Built_in_typeContext built_in_type(int i) {
			return getRuleContext(Built_in_typeContext.class,i);
		}
		public List<TerminalNode> ID() { return getTokens(CmmParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(CmmParser.ID, i);
		}
		public ParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameters; }
	}

	public final ParametersContext parameters() throws RecognitionException {
		ParametersContext _localctx = new ParametersContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			built_in_type();
			setState(69);
			match(ID);
			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6) {
				{
				{
				setState(70);
				match(T__6);
				setState(71);
				built_in_type();
				setState(72);
				match(ID);
				}
				}
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Variable_definitionContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(CmmParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(CmmParser.ID, i);
		}
		public Variable_definitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_definition; }
	}

	public final Variable_definitionContext variable_definition() throws RecognitionException {
		Variable_definitionContext _localctx = new Variable_definitionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_variable_definition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			type(0);
			setState(80);
			match(ID);
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6) {
				{
				{
				setState(81);
				match(T__6);
				setState(82);
				match(ID);
				}
				}
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(88);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Return_typeContext extends ParserRuleContext {
		public Built_in_typeContext built_in_type() {
			return getRuleContext(Built_in_typeContext.class,0);
		}
		public Return_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_type; }
	}

	public final Return_typeContext return_type() throws RecognitionException {
		Return_typeContext _localctx = new Return_typeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_return_type);
		try {
			setState(92);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__11:
			case T__12:
			case T__13:
				enterOuterAlt(_localctx, 1);
				{
				setState(90);
				built_in_type();
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(91);
				match(T__0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public Built_in_typeContext built_in_type() {
			return getRuleContext(Built_in_typeContext.class,0);
		}
		public List<Variable_definitionContext> variable_definition() {
			return getRuleContexts(Variable_definitionContext.class);
		}
		public Variable_definitionContext variable_definition(int i) {
			return getRuleContext(Variable_definitionContext.class,i);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode INT_CONSTANT() { return getToken(CmmParser.INT_CONSTANT, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		return type(0);
	}

	private TypeContext type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeContext _localctx = new TypeContext(_ctx, _parentState);
		TypeContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_type, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__11:
			case T__12:
			case T__13:
				{
				setState(95);
				built_in_type();
				}
				break;
			case T__10:
				{
				setState(96);
				match(T__10);
				setState(97);
				match(T__4);
				setState(101);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13))) != 0)) {
					{
					{
					setState(98);
					variable_definition();
					}
					}
					setState(103);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(104);
				match(T__5);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(113);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(107);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(108);
					match(T__8);
					setState(109);
					match(INT_CONSTANT);
					setState(110);
					match(T__9);
					}
					} 
				}
				setState(115);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Built_in_typeContext extends ParserRuleContext {
		public Type ast;
		public Token a;
		public Built_in_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_built_in_type; }
	}

	public final Built_in_typeContext built_in_type() throws RecognitionException {
		Built_in_typeContext _localctx = new Built_in_typeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_built_in_type);
		try {
			setState(122);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__11:
				enterOuterAlt(_localctx, 1);
				{
				setState(116);
				((Built_in_typeContext)_localctx).a = match(T__11);
				 ((Built_in_typeContext)_localctx).ast =  new IntType(((Built_in_typeContext)_localctx).a.getLine(), ((Built_in_typeContext)_localctx).a.getCharPositionInLine()+1); 
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 2);
				{
				setState(118);
				((Built_in_typeContext)_localctx).a = match(T__12);
				 ((Built_in_typeContext)_localctx).ast =  new DoubleType(((Built_in_typeContext)_localctx).a.getLine(), ((Built_in_typeContext)_localctx).a.getCharPositionInLine()+1); 
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 3);
				{
				setState(120);
				((Built_in_typeContext)_localctx).a = match(T__13);
				 ((Built_in_typeContext)_localctx).ast =  new CharType(((Built_in_typeContext)_localctx).a.getLine(), ((Built_in_typeContext)_localctx).a.getCharPositionInLine()+1); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode ID() { return getToken(CmmParser.ID, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_statement);
		int _la;
		try {
			setState(168);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(124);
				expression(0);
				setState(125);
				match(T__14);
				setState(126);
				expression(0);
				setState(127);
				match(T__7);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(129);
				match(T__15);
				setState(130);
				arguments();
				setState(131);
				match(T__7);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(133);
				match(T__16);
				setState(134);
				arguments();
				setState(135);
				match(T__7);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(137);
				match(T__17);
				setState(138);
				match(T__2);
				setState(139);
				expression(0);
				setState(140);
				match(T__3);
				setState(141);
				block();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(143);
				match(T__18);
				setState(144);
				match(T__2);
				setState(145);
				expression(0);
				setState(146);
				match(T__3);
				setState(147);
				block();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(149);
				match(T__18);
				setState(150);
				match(T__2);
				setState(151);
				expression(0);
				setState(152);
				match(T__3);
				setState(153);
				block();
				setState(154);
				match(T__19);
				setState(155);
				block();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(157);
				match(T__20);
				setState(158);
				expression(0);
				setState(159);
				match(T__7);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(161);
				match(ID);
				setState(162);
				match(T__2);
				setState(164);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ID) | (1L << INT_CONSTANT) | (1L << REAL_CONSTANT) | (1L << CHAR_CONSTANT))) != 0)) {
					{
					setState(163);
					arguments();
					}
				}

				setState(166);
				match(T__3);
				setState(167);
				match(T__7);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_block);
		int _la;
		try {
			setState(179);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__15:
			case T__16:
			case T__17:
			case T__18:
			case T__20:
			case ID:
			case INT_CONSTANT:
			case REAL_CONSTANT:
			case CHAR_CONSTANT:
				enterOuterAlt(_localctx, 1);
				{
				setState(170);
				statement();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(171);
				match(T__4);
				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__20) | (1L << ID) | (1L << INT_CONSTANT) | (1L << REAL_CONSTANT) | (1L << CHAR_CONSTANT))) != 0)) {
					{
					{
					setState(172);
					statement();
					}
					}
					setState(177);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(178);
				match(T__5);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public Expression ast;
		public ExpressionContext e1;
		public Token ID;
		public Token i1;
		public Token r1;
		public Token c1;
		public Token op;
		public ExpressionContext e2;
		public TerminalNode ID() { return getToken(CmmParser.ID, 0); }
		public TerminalNode INT_CONSTANT() { return getToken(CmmParser.INT_CONSTANT, 0); }
		public TerminalNode REAL_CONSTANT() { return getToken(CmmParser.REAL_CONSTANT, 0); }
		public TerminalNode CHAR_CONSTANT() { return getToken(CmmParser.CHAR_CONSTANT, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(182);
				((ExpressionContext)_localctx).ID = match(ID);
				 ((ExpressionContext)_localctx).ast =  new Variable(((ExpressionContext)_localctx).ID.getLine(), ((ExpressionContext)_localctx).ID.getCharPositionInLine()+1, (((ExpressionContext)_localctx).ID!=null?((ExpressionContext)_localctx).ID.getText():null)); 
				}
				break;
			case INT_CONSTANT:
				{
				setState(184);
				((ExpressionContext)_localctx).i1 = match(INT_CONSTANT);
				 ((ExpressionContext)_localctx).ast =  new IntLiteral( ((ExpressionContext)_localctx).i1.getLine(), ((ExpressionContext)_localctx).i1.getCharPositionInLine()+1,
				                                     LexerHelper.lexemeToInt((((ExpressionContext)_localctx).i1!=null?((ExpressionContext)_localctx).i1.getText():null)) ); 
				}
				break;
			case REAL_CONSTANT:
				{
				setState(186);
				((ExpressionContext)_localctx).r1 = match(REAL_CONSTANT);
				 ((ExpressionContext)_localctx).ast =  new DoubleLiteral(((ExpressionContext)_localctx).r1.getLine(), ((ExpressionContext)_localctx).r1.getCharPositionInLine()+1,
				                                       LexerHelper.lexemeToReal((((ExpressionContext)_localctx).r1!=null?((ExpressionContext)_localctx).r1.getText():null))); 
				}
				break;
			case CHAR_CONSTANT:
				{
				setState(188);
				((ExpressionContext)_localctx).c1 = match(CHAR_CONSTANT);
				 ((ExpressionContext)_localctx).ast =  new CharLiteral(((ExpressionContext)_localctx).c1.getLine(), ((ExpressionContext)_localctx).c1.getCharPositionInLine()+1,
				                                    LexerHelper.lexemeToChar((((ExpressionContext)_localctx).c1!=null?((ExpressionContext)_localctx).c1.getText():null))); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(199);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExpressionContext(_parentctx, _parentState);
					_localctx.e1 = _prevctx;
					_localctx.e1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_expression);
					setState(192);
					if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
					setState(193);
					((ExpressionContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==T__21 || _la==T__22) ) {
						((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(194);
					((ExpressionContext)_localctx).e2 = expression(6);
					 ((ExpressionContext)_localctx).ast =  new Arithmetic((((ExpressionContext)_localctx).e1!=null?(((ExpressionContext)_localctx).e1.start):null).getLine(), ((ExpressionContext)_localctx).e1.ast.getColumn(),
					                                              ((ExpressionContext)_localctx).e1.ast, (((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null), ((ExpressionContext)_localctx).e2.ast); 
					}
					} 
				}
				setState(201);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ArgumentsContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			expression(0);
			setState(207);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6) {
				{
				{
				setState(203);
				match(T__6);
				setState(204);
				expression(0);
				}
				}
				setState(209);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 7:
			return type_sempred((TypeContext)_localctx, predIndex);
		case 11:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3 \u00d5\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\7\2\36\n\2\f\2\16\2!\13\2\3\2\3\2\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\5\4\62\n\4\3\4\3\4"+
		"\3\4\3\4\3\4\5\49\n\4\3\5\7\5<\n\5\f\5\16\5?\13\5\3\5\7\5B\n\5\f\5\16"+
		"\5E\13\5\3\6\3\6\3\6\3\6\3\6\3\6\7\6M\n\6\f\6\16\6P\13\6\3\7\3\7\3\7\3"+
		"\7\7\7V\n\7\f\7\16\7Y\13\7\3\7\3\7\3\b\3\b\5\b_\n\b\3\t\3\t\3\t\3\t\3"+
		"\t\7\tf\n\t\f\t\16\ti\13\t\3\t\5\tl\n\t\3\t\3\t\3\t\3\t\7\tr\n\t\f\t\16"+
		"\tu\13\t\3\n\3\n\3\n\3\n\3\n\3\n\5\n}\n\n\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00a7\n\13\3\13\3\13\5\13\u00ab\n"+
		"\13\3\f\3\f\3\f\7\f\u00b0\n\f\f\f\16\f\u00b3\13\f\3\f\5\f\u00b6\n\f\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00c1\n\r\3\r\3\r\3\r\3\r\3\r\7"+
		"\r\u00c8\n\r\f\r\16\r\u00cb\13\r\3\16\3\16\3\16\7\16\u00d0\n\16\f\16\16"+
		"\16\u00d3\13\16\3\16\2\4\20\30\17\2\4\6\b\n\f\16\20\22\24\26\30\32\2\3"+
		"\3\2\30\31\2\u00e3\2\37\3\2\2\2\4$\3\2\2\2\68\3\2\2\2\b=\3\2\2\2\nF\3"+
		"\2\2\2\fQ\3\2\2\2\16^\3\2\2\2\20k\3\2\2\2\22|\3\2\2\2\24\u00aa\3\2\2\2"+
		"\26\u00b5\3\2\2\2\30\u00c0\3\2\2\2\32\u00cc\3\2\2\2\34\36\5\6\4\2\35\34"+
		"\3\2\2\2\36!\3\2\2\2\37\35\3\2\2\2\37 \3\2\2\2 \"\3\2\2\2!\37\3\2\2\2"+
		"\"#\5\4\3\2#\3\3\2\2\2$%\7\3\2\2%&\7\4\2\2&\'\7\5\2\2\'(\7\6\2\2()\7\7"+
		"\2\2)*\5\b\5\2*+\7\b\2\2+\5\3\2\2\2,9\5\f\7\2-.\5\16\b\2./\7\32\2\2/\61"+
		"\7\5\2\2\60\62\5\n\6\2\61\60\3\2\2\2\61\62\3\2\2\2\62\63\3\2\2\2\63\64"+
		"\7\6\2\2\64\65\7\7\2\2\65\66\5\b\5\2\66\67\7\b\2\2\679\3\2\2\28,\3\2\2"+
		"\28-\3\2\2\29\7\3\2\2\2:<\5\f\7\2;:\3\2\2\2<?\3\2\2\2=;\3\2\2\2=>\3\2"+
		"\2\2>C\3\2\2\2?=\3\2\2\2@B\5\24\13\2A@\3\2\2\2BE\3\2\2\2CA\3\2\2\2CD\3"+
		"\2\2\2D\t\3\2\2\2EC\3\2\2\2FG\5\22\n\2GN\7\32\2\2HI\7\t\2\2IJ\5\22\n\2"+
		"JK\7\32\2\2KM\3\2\2\2LH\3\2\2\2MP\3\2\2\2NL\3\2\2\2NO\3\2\2\2O\13\3\2"+
		"\2\2PN\3\2\2\2QR\5\20\t\2RW\7\32\2\2ST\7\t\2\2TV\7\32\2\2US\3\2\2\2VY"+
		"\3\2\2\2WU\3\2\2\2WX\3\2\2\2XZ\3\2\2\2YW\3\2\2\2Z[\7\n\2\2[\r\3\2\2\2"+
		"\\_\5\22\n\2]_\7\3\2\2^\\\3\2\2\2^]\3\2\2\2_\17\3\2\2\2`a\b\t\1\2al\5"+
		"\22\n\2bc\7\r\2\2cg\7\7\2\2df\5\f\7\2ed\3\2\2\2fi\3\2\2\2ge\3\2\2\2gh"+
		"\3\2\2\2hj\3\2\2\2ig\3\2\2\2jl\7\b\2\2k`\3\2\2\2kb\3\2\2\2ls\3\2\2\2m"+
		"n\f\4\2\2no\7\13\2\2op\7\33\2\2pr\7\f\2\2qm\3\2\2\2ru\3\2\2\2sq\3\2\2"+
		"\2st\3\2\2\2t\21\3\2\2\2us\3\2\2\2vw\7\16\2\2w}\b\n\1\2xy\7\17\2\2y}\b"+
		"\n\1\2z{\7\20\2\2{}\b\n\1\2|v\3\2\2\2|x\3\2\2\2|z\3\2\2\2}\23\3\2\2\2"+
		"~\177\5\30\r\2\177\u0080\7\21\2\2\u0080\u0081\5\30\r\2\u0081\u0082\7\n"+
		"\2\2\u0082\u00ab\3\2\2\2\u0083\u0084\7\22\2\2\u0084\u0085\5\32\16\2\u0085"+
		"\u0086\7\n\2\2\u0086\u00ab\3\2\2\2\u0087\u0088\7\23\2\2\u0088\u0089\5"+
		"\32\16\2\u0089\u008a\7\n\2\2\u008a\u00ab\3\2\2\2\u008b\u008c\7\24\2\2"+
		"\u008c\u008d\7\5\2\2\u008d\u008e\5\30\r\2\u008e\u008f\7\6\2\2\u008f\u0090"+
		"\5\26\f\2\u0090\u00ab\3\2\2\2\u0091\u0092\7\25\2\2\u0092\u0093\7\5\2\2"+
		"\u0093\u0094\5\30\r\2\u0094\u0095\7\6\2\2\u0095\u0096\5\26\f\2\u0096\u00ab"+
		"\3\2\2\2\u0097\u0098\7\25\2\2\u0098\u0099\7\5\2\2\u0099\u009a\5\30\r\2"+
		"\u009a\u009b\7\6\2\2\u009b\u009c\5\26\f\2\u009c\u009d\7\26\2\2\u009d\u009e"+
		"\5\26\f\2\u009e\u00ab\3\2\2\2\u009f\u00a0\7\27\2\2\u00a0\u00a1\5\30\r"+
		"\2\u00a1\u00a2\7\n\2\2\u00a2\u00ab\3\2\2\2\u00a3\u00a4\7\32\2\2\u00a4"+
		"\u00a6\7\5\2\2\u00a5\u00a7\5\32\16\2\u00a6\u00a5\3\2\2\2\u00a6\u00a7\3"+
		"\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\7\6\2\2\u00a9\u00ab\7\n\2\2\u00aa"+
		"~\3\2\2\2\u00aa\u0083\3\2\2\2\u00aa\u0087\3\2\2\2\u00aa\u008b\3\2\2\2"+
		"\u00aa\u0091\3\2\2\2\u00aa\u0097\3\2\2\2\u00aa\u009f\3\2\2\2\u00aa\u00a3"+
		"\3\2\2\2\u00ab\25\3\2\2\2\u00ac\u00b6\5\24\13\2\u00ad\u00b1\7\7\2\2\u00ae"+
		"\u00b0\5\24\13\2\u00af\u00ae\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1\u00af\3"+
		"\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b4\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4"+
		"\u00b6\7\b\2\2\u00b5\u00ac\3\2\2\2\u00b5\u00ad\3\2\2\2\u00b6\27\3\2\2"+
		"\2\u00b7\u00b8\b\r\1\2\u00b8\u00b9\7\32\2\2\u00b9\u00c1\b\r\1\2\u00ba"+
		"\u00bb\7\33\2\2\u00bb\u00c1\b\r\1\2\u00bc\u00bd\7\34\2\2\u00bd\u00c1\b"+
		"\r\1\2\u00be\u00bf\7\35\2\2\u00bf\u00c1\b\r\1\2\u00c0\u00b7\3\2\2\2\u00c0"+
		"\u00ba\3\2\2\2\u00c0\u00bc\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1\u00c9\3\2"+
		"\2\2\u00c2\u00c3\f\7\2\2\u00c3\u00c4\t\2\2\2\u00c4\u00c5\5\30\r\b\u00c5"+
		"\u00c6\b\r\1\2\u00c6\u00c8\3\2\2\2\u00c7\u00c2\3\2\2\2\u00c8\u00cb\3\2"+
		"\2\2\u00c9\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\31\3\2\2\2\u00cb\u00c9"+
		"\3\2\2\2\u00cc\u00d1\5\30\r\2\u00cd\u00ce\7\t\2\2\u00ce\u00d0\5\30\r\2"+
		"\u00cf\u00cd\3\2\2\2\u00d0\u00d3\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2"+
		"\3\2\2\2\u00d2\33\3\2\2\2\u00d3\u00d1\3\2\2\2\25\37\618=CNW^gks|\u00a6"+
		"\u00aa\u00b1\u00b5\u00c0\u00c9\u00d1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}