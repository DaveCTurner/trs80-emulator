package dct25.trs80.syntax;

import beaver.Symbol;
import beaver.Scanner;

import dct25.trs80.syntax.Terminals;

%%

%public
%class TRS80Scanner
%extends Scanner
%function nextToken
%type Symbol
%yylexthrow Scanner.Exception
%eofval{
	return newToken(Terminals.EOF, "end-of-file");
%eofval}
%unicode
%line
%column
%state QQSTRING
%{
	private Symbol newToken(short id)
	{
		return new Symbol(id, yyline + 1, yycolumn + 1, yylength());
	}

	private Symbol newToken(short id, Object value)
	{
		return new Symbol(id, yyline + 1, yycolumn + 1, yylength(), value);
	}
%}
LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]

IntegerLiteral = [:digit:] [:digit:]*
Identifier = [A-Z][A-Z]*

%%

{WhiteSpace}+   { /* ignore */ }

<YYINITIAL> {

	"CLS"       { return newToken(Terminals.CLS); }
	"GOTO"      { return newToken(Terminals.GOTO); }
	"END"		{ return newToken(Terminals.END); }
	"PRINT"		{ return newToken(Terminals.PRINT); }
	"LPRINT"	{ return newToken(Terminals.LPRINT); }
	"FOR"		{ return newToken(Terminals.FOR); }
	"TO"		{ return newToken(Terminals.TO); }
	"NEXT"		{ return newToken(Terminals.NEXT); }
	"INPUT"		{ return newToken(Terminals.INPUT); }
	"IF"		{ return newToken(Terminals.IF); }
	"THEN"		{ return newToken(Terminals.THEN); }
	"DIM"		{ return newToken(Terminals.DIM); }
	"RND"		{ return newToken(Terminals.RND); }

	"AND"		{ return newToken(Terminals.AND); }
	
	{IntegerLiteral}    { return newToken(Terminals.INTEGERLITERAL, yytext()); }
	\"			{ yybegin(QQSTRING); }
	{Identifier}		{ return newToken(Terminals.IDENTIFIER, yytext()); }

	"<>"		{ return newToken(Terminals.NOTEQUALS); }
	"("			{ return newToken(Terminals.OPENPARENTHESIS); }
	")"			{ return newToken(Terminals.CLOSEPARENTHESIS); }
	"="         { return newToken(Terminals.EQUALS); }
	";"         { return newToken(Terminals.SEMICOLON); }
	":"         { return newToken(Terminals.COLON); }
	","         { return newToken(Terminals.COMMA); }
	"@"         { return newToken(Terminals.AT); }
	"+"         { return newToken(Terminals.PLUS); }
	"-"         { return newToken(Terminals.MINUS); }
}

<QQSTRING> {
	[^\"]*		{ return newToken(Terminals.STRINGLITERAL, yytext()); }
	\"			{ yybegin(YYINITIAL); }
}

.|\n            { throw new Scanner.Exception("unexpected character '" + yytext() + "'"); }
