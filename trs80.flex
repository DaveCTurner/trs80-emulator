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
StringLiteral = \"[A-Za-z0-9,. ]*\"
Identifier = [A-Z][A-Z]*

%%

{WhiteSpace}+   { /* ignore */ }

<YYINITIAL> {

	"CLS"       { return newToken(Terminals.CLS); }
	"GOTO"      { return newToken(Terminals.GOTO); }
	"END"		{ return newToken(Terminals.END); }
	"PRINT"		{ return newToken(Terminals.PRINT); }
	"FOR"		{ return newToken(Terminals.FOR); }
	"TO"		{ return newToken(Terminals.TO); }
	"NEXT"		{ return newToken(Terminals.NEXT); }
	"INPUT"		{ return newToken(Terminals.INPUT); }
	"IF"		{ return newToken(Terminals.IF); }
	"THEN"		{ return newToken(Terminals.THEN); }
	"DIM"		{ return newToken(Terminals.DIM); }

	"AND"		{ return newToken(Terminals.AND); }
	
	"<>"		{ return newToken(Terminals.NOTEQUALS); }
	"("			{ return newToken(Terminals.OPENPARENTHESIS); }
	")"			{ return newToken(Terminals.CLOSEPARENTHESIS); }
	"="         { return newToken(Terminals.EQUALS); }
	";"         { return newToken(Terminals.SEMICOLON); }
	":"         { return newToken(Terminals.COLON); }
	","         { return newToken(Terminals.COMMA); }
	"@"         { return newToken(Terminals.AT); }
	{IntegerLiteral}    { return newToken(Terminals.INTEGERLITERAL, yytext()); }
	{StringLiteral}     { return newToken(Terminals.STRINGLITERAL, yytext()); }
	{Identifier}		{ return newToken(Terminals.IDENTIFIER, yytext()); }
}

.|\n            { throw new Scanner.Exception("unexpected character '" + yytext() + "'"); }
