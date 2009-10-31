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
StringLiteral = \"[A-Za-z0-9, ]*\"

%%

{WhiteSpace}+   { /* ignore */ }

<YYINITIAL> {

	"CLS"       { return newToken(Terminals.CLS); }
	"GOTO"      { return newToken(Terminals.GOTO); }
	"END"		{ return newToken(Terminals.END); }
	"PRINT"		{ return newToken(Terminals.PRINT); }
	
	":"         { return newToken(Terminals.COLON); }
	","         { return newToken(Terminals.COMMA); }
	"@"         { return newToken(Terminals.AT); }
	{IntegerLiteral}    { return newToken(Terminals.INTEGERLITERAL, yytext()); }
	{StringLiteral}     { return newToken(Terminals.STRINGLITERAL, yytext()); }
}

.|\n            { throw new Scanner.Exception("unexpected character '" + yytext() + "'"); }
