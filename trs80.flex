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

Number = [:digit:] [:digit:]*

%%

{WhiteSpace}+   { /* ignore */ }

<YYINITIAL> {
	{Number}    { return newToken(Terminals.NUMBER, new Integer(yytext())); }

	"CLS"       { return newToken(Terminals.CLS); }
	"GOTO"       { return newToken(Terminals.GOTO); }
	
	":"         { return newToken(Terminals.COLON); }
}

.|\n            { throw new Scanner.Exception("unexpected character '" + yytext() + "'"); }
