/*
10 CLS : PRINT @ 412, "AMAZING"
20 PRINT @ 538, "COPYRIGHT BY"
30 PRINT @ 587, "CREATIVE COMPUTING  MORRISTOWN, NEW JERSEY"
40 FOR II = (1) TO (1500) : NEXT
50 CLS : INPUT "WHAT ARE YOUR WIDTH AND LENGTH"; H,V
60 IF (((H)<>(1)) AND ((V)<>(1))) THEN 90
70 PRINT "MEANINGLESS DIMENSIONS. TRY AGAIN"
80 FOR A = (1) TO (500) : NEXT : GOTO 50
90 PRINT @ 522, "PRINTOUT IS IN PROGRESS, PLEASE BE PATIENT"
100 FOR II = (1) TO (1500) : NEXT
110 DIM W((H),(V)), V((H),(V))
120 Q=(0) : Z=(0) : X=RND((H))
130 FOR I = (1) TO (H)
140 IF ((I)=(X)) THEN 160
150 PRINT ":--"; : GOTO 170
160 PRINT ":  ";
170 NEXT
180 PRINT ":"
190 C=(1) : W((X),(1))=(C) : C=((C)+(1))
200 R=(X) : S=(1) : GOTO 270
210 IF ((R)<>(H)) THEN 250
220 IF ((S)<>(V)) THEN 240
230 R=(1) : S=(1) : GOTO 260
240 R=(1) : S=((S)+(1)) : GOTO 260
250 R=((R)+(1))
260 IF (W((R),(S))=(0)) THEN 210
270 IF (((R)-(1))=(0)) THEN 600
280 IF (W(((R)-(1)),(S))<>(0)) THEN 600
290 IF (((S)-(1))=(0)) THEN 430
300 IF (W((R),((S)-(1)))<>(0)) THEN 430
310 IF ((R)=(H)) THEN 350
320 IF (W(((R)+(1)),(S))<>(0)) THEN 350
330 X=RND((3))
340 ON (X) GOTO 940, 980, 1020
350 IF ((S)<>(V)) THEN 380
360 IF ((Z)=(1)) THEN 410
370 Q=(1) : GOTO 390
380 IF (W((R),((S)+(1)))<>(0)) THEN 410
390 X=RND((3))
400 ON (X) GOTO 940, 980, 1090
410 X=RND((2))
420 ON (X) GOTO 940, 980
430 IF ((R)=(H)) THEN 530
440 IF (W(((R)+(1)),(S))<>(0)) THEN 530
450 IF ((S)<>(V)) THEN 480
460 IF ((Z)=(1)) THEN 510
470 Q=(1) : GOTO 490
480 IF (W((R),((S)+(1)))<>(0)) THEN 510
490 X=RND((3))
500 ON (X) GOTO 940, 1020, 1090
510 X=RND((2))
520 ON (X) GOTO 940, 1020
530 IF ((S)<>(V)) THEN 560
540 IF ((Z)=(1)) THEN 590
550 Q=(1) : GOTO 570
560 IF (W((R),((S)+(1)))<>(0)) THEN 590
570 X=RND((2))
580 ON (X) GOTO 940, 1090
590 GOTO 940
600 IF (((S)-(1))=(0)) THEN 790
610 IF (W((R),((S)-(1)))<>(0)) THEN 790
620 IF ((R)=(H)) THEN 720
630 IF (W(((R)+(1)),(S))<>(0)) THEN 720
640 IF ((S)<>(V)) THEN 670
650 IF ((Z)=(1)) THEN 700
660 Q=(1) : GOTO 680
670 IF (W((R),((S)+(1)))<>(0)) THEN 700
680 X=RND((3))
690 ON (X) GOTO 980, 1020, 1090
700 X=RND((2))
710 ON (X) GOTO 980, 1020
720 IF ((S)<>(V)) THEN 750
730 IF ((Z)=(1)) THEN 780
740 Q=(1) : GOTO 760
750 IF (W((R),((S)+(1)))<>(0)) THEN 780
760 X=RND((2))
770 ON (X) GOTO 980, 1090
780 GOTO 980
790 IF ((R)=(H)) THEN 880
800 IF (W(((R)+(1)),(S))<>(0)) THEN 880
810 IF ((S)<>(V)) THEN 840
820 IF ((Z)=(1)) THEN 870
830 Q=(1) : GOTO 990
840 IF (W((R),((S)+(1)))<>(0)) THEN 870
850 X=RND((2))
860 ON (X) GOTO 1020, 1090
870 GOTO 1020
880 IF ((S)<>(V)) THEN 910
890 IF ((Z)=(1)) THEN 930
900 Q=(1) : GOTO 920
910 IF (W((R),((S)+(1)))<>(0)) THEN 930
920 GOTO 1090
930 GOTO 1190
940 W(((R)-(1)),(S))=(C)
950 C=((C)+(1)) : V(((R)-(1)),(S))=(2) : R=((R)-(1))
960 IF ((C)=(((H)*(V))+(1))) THEN 1200
970 Q=(0) : GOTO 270
980 W((R),((S)-(1)))=(C)
990 C=((C)+(1))
1000 V((R),((S)-(1)))=(1) : S=((S)-(1)) : IF ((C)=(((H)*(V))+(1))) THEN 1200
1010 Q=(0) : GOTO 270
1020 W(((R)+(1)),(S))=(C)
1030 C=((C)+(1)) : IF (V((R),(S))=(0)) THEN 1050
1040 V((R),(S))=(3) : GOTO 1060
1050 V((R),(S))=(2)
1060 R=((R)+(1))
1070 IF ((C)=(((H)*(V))+(1))) THEN 1200
1080 GOTO 600
1090 IF ((Q)=(1)) THEN 1150
1100 W((R),((S)+(1)))=(C) : C=((C)+(1)) : IF (V((R),(S))=(0)) THEN 1120
1110 V((R),(S))=(3) : GOTO 1130
1120 V((R),(S))=(1)
1130 S=((S)+(1)) : IF ((C)=(((V)*(H))+(1))) THEN 1200
1140 GOTO 270
1150 Z=(1)
1160 IF (V((R),(S))=(0)) THEN 1180
1170 V((R),(S))=(3) : Q=(0) : GOTO 1190
1180 V((R),(S))=(1) : Q=(0) : R=(1) : S=(1) : GOTO 260
1190 GOTO 210
1200 FOR J = (1) TO (V)
1210 PRINT "I";
1220 FOR I = (1) TO (H)
1230 IF (V((I),(J))<(2)) THEN 1260
1240 PRINT "   ";
1250 GOTO 1270
1260 PRINT "  I";
1270 NEXT
1280 PRINT " "
1290 FOR I = (1) TO (H)
1300 IF (V((I),(J))=(0)) THEN 1340
1310 IF (V((I),(J))=(2)) THEN 1340
1320 PRINT ":  ";
1330 GOTO 1350
1340 PRINT ":--";
1350 NEXT
1360 PRINT ":"
1370 NEXT
1380 END
*/
package dct25.trs80.programs;
import dct25.trs80.emulator.Environment;
import dct25.trs80.emulator.Executable;
/** 
 * Translation of the following TRS-80 Program into Java:
---
10 CLS : PRINT @ 412, "AMAZING"
20 PRINT @ 538, "COPYRIGHT BY"
30 PRINT @ 587, "CREATIVE COMPUTING  MORRISTOWN, NEW JERSEY"
40 FOR II = (1) TO (1500) : NEXT
50 CLS : INPUT "WHAT ARE YOUR WIDTH AND LENGTH"; H,V
60 IF (((H)<>(1)) AND ((V)<>(1))) THEN 90
70 PRINT "MEANINGLESS DIMENSIONS. TRY AGAIN"
80 FOR A = (1) TO (500) : NEXT : GOTO 50
90 PRINT @ 522, "PRINTOUT IS IN PROGRESS, PLEASE BE PATIENT"
100 FOR II = (1) TO (1500) : NEXT
110 DIM W((H),(V)), V((H),(V))
120 Q=(0) : Z=(0) : X=RND((H))
130 FOR I = (1) TO (H)
140 IF ((I)=(X)) THEN 160
150 PRINT ":--"; : GOTO 170
160 PRINT ":  ";
170 NEXT
180 PRINT ":"
190 C=(1) : W((X),(1))=(C) : C=((C)+(1))
200 R=(X) : S=(1) : GOTO 270
210 IF ((R)<>(H)) THEN 250
220 IF ((S)<>(V)) THEN 240
230 R=(1) : S=(1) : GOTO 260
240 R=(1) : S=((S)+(1)) : GOTO 260
250 R=((R)+(1))
260 IF (W((R),(S))=(0)) THEN 210
270 IF (((R)-(1))=(0)) THEN 600
280 IF (W(((R)-(1)),(S))<>(0)) THEN 600
290 IF (((S)-(1))=(0)) THEN 430
300 IF (W((R),((S)-(1)))<>(0)) THEN 430
310 IF ((R)=(H)) THEN 350
320 IF (W(((R)+(1)),(S))<>(0)) THEN 350
330 X=RND((3))
340 ON (X) GOTO 940, 980, 1020
350 IF ((S)<>(V)) THEN 380
360 IF ((Z)=(1)) THEN 410
370 Q=(1) : GOTO 390
380 IF (W((R),((S)+(1)))<>(0)) THEN 410
390 X=RND((3))
400 ON (X) GOTO 940, 980, 1090
410 X=RND((2))
420 ON (X) GOTO 940, 980
430 IF ((R)=(H)) THEN 530
440 IF (W(((R)+(1)),(S))<>(0)) THEN 530
450 IF ((S)<>(V)) THEN 480
460 IF ((Z)=(1)) THEN 510
470 Q=(1) : GOTO 490
480 IF (W((R),((S)+(1)))<>(0)) THEN 510
490 X=RND((3))
500 ON (X) GOTO 940, 1020, 1090
510 X=RND((2))
520 ON (X) GOTO 940, 1020
530 IF ((S)<>(V)) THEN 560
540 IF ((Z)=(1)) THEN 590
550 Q=(1) : GOTO 570
560 IF (W((R),((S)+(1)))<>(0)) THEN 590
570 X=RND((2))
580 ON (X) GOTO 940, 1090
590 GOTO 940
600 IF (((S)-(1))=(0)) THEN 790
610 IF (W((R),((S)-(1)))<>(0)) THEN 790
620 IF ((R)=(H)) THEN 720
630 IF (W(((R)+(1)),(S))<>(0)) THEN 720
640 IF ((S)<>(V)) THEN 670
650 IF ((Z)=(1)) THEN 700
660 Q=(1) : GOTO 680
670 IF (W((R),((S)+(1)))<>(0)) THEN 700
680 X=RND((3))
690 ON (X) GOTO 980, 1020, 1090
700 X=RND((2))
710 ON (X) GOTO 980, 1020
720 IF ((S)<>(V)) THEN 750
730 IF ((Z)=(1)) THEN 780
740 Q=(1) : GOTO 760
750 IF (W((R),((S)+(1)))<>(0)) THEN 780
760 X=RND((2))
770 ON (X) GOTO 980, 1090
780 GOTO 980
790 IF ((R)=(H)) THEN 880
800 IF (W(((R)+(1)),(S))<>(0)) THEN 880
810 IF ((S)<>(V)) THEN 840
820 IF ((Z)=(1)) THEN 870
830 Q=(1) : GOTO 990
840 IF (W((R),((S)+(1)))<>(0)) THEN 870
850 X=RND((2))
860 ON (X) GOTO 1020, 1090
870 GOTO 1020
880 IF ((S)<>(V)) THEN 910
890 IF ((Z)=(1)) THEN 930
900 Q=(1) : GOTO 920
910 IF (W((R),((S)+(1)))<>(0)) THEN 930
920 GOTO 1090
930 GOTO 1190
940 W(((R)-(1)),(S))=(C)
950 C=((C)+(1)) : V(((R)-(1)),(S))=(2) : R=((R)-(1))
960 IF ((C)=(((H)*(V))+(1))) THEN 1200
970 Q=(0) : GOTO 270
980 W((R),((S)-(1)))=(C)
990 C=((C)+(1))
1000 V((R),((S)-(1)))=(1) : S=((S)-(1)) : IF ((C)=(((H)*(V))+(1))) THEN 1200
1010 Q=(0) : GOTO 270
1020 W(((R)+(1)),(S))=(C)
1030 C=((C)+(1)) : IF (V((R),(S))=(0)) THEN 1050
1040 V((R),(S))=(3) : GOTO 1060
1050 V((R),(S))=(2)
1060 R=((R)+(1))
1070 IF ((C)=(((H)*(V))+(1))) THEN 1200
1080 GOTO 600
1090 IF ((Q)=(1)) THEN 1150
1100 W((R),((S)+(1)))=(C) : C=((C)+(1)) : IF (V((R),(S))=(0)) THEN 1120
1110 V((R),(S))=(3) : GOTO 1130
1120 V((R),(S))=(1)
1130 S=((S)+(1)) : IF ((C)=(((V)*(H))+(1))) THEN 1200
1140 GOTO 270
1150 Z=(1)
1160 IF (V((R),(S))=(0)) THEN 1180
1170 V((R),(S))=(3) : Q=(0) : GOTO 1190
1180 V((R),(S))=(1) : Q=(0) : R=(1) : S=(1) : GOTO 260
1190 GOTO 210
1200 FOR J = (1) TO (V)
1210 PRINT "I";
1220 FOR I = (1) TO (H)
1230 IF (V((I),(J))<(2)) THEN 1260
1240 PRINT "   ";
1250 GOTO 1270
1260 PRINT "  I";
1270 NEXT
1280 PRINT " "
1290 FOR I = (1) TO (H)
1300 IF (V((I),(J))=(0)) THEN 1340
1310 IF (V((I),(J))=(2)) THEN 1340
1320 PRINT ":  ";
1330 GOTO 1350
1340 PRINT ":--";
1350 NEXT
1360 PRINT ":"
1370 NEXT
1380 END
---
 */
public class amazing_test_bas implements Executable {
  int II;
  int V;
  int Q;
  int A;
  int S;
  int R;
  int C;
  int H;
  int I;
  int X;
  int J;
  int Z;
  int[][] Ws;
  int[][] Vs;
  Environment _env;
  /** 
 * Execute the program (call line10statement0)
 * @param env The environment in which to execute the program.
 */
  public void execute(  Environment env){
    _env=env;
    line10statement0();
  }
  /** 
 *  ->  CLS ->  line10statement1
 */
  protected void line10statement0(){
    _env.clearScreen();
    line10statement1();
  }
  /** 
 *  line10statement0 ->  PRINT @ 412, "AMAZING" ->  line20statement0
 */
  protected void line10statement1(){
    _env.print("AMAZING",true);
    line20statement0();
  }
  /** 
 *  line10statement1 ->  PRINT @ 538, "COPYRIGHT BY" ->  line30statement0
 */
  protected void line20statement0(){
    _env.print("COPYRIGHT BY",true);
    line30statement0();
  }
  /** 
 *  line20statement0 ->  PRINT @ 587, "CREATIVE COMPUTING  MORRISTOWN, NEW JERSEY" ->  line40statement0
 */
  protected void line30statement0(){
    _env.print("CREATIVE COMPUTING  MORRISTOWN, NEW JERSEY",true);
    line40statement0();
  }
  /** 
 *  line30statement0 ->  FOR II = (1) TO (1500) ->  line40statement1
 */
  protected void line40statement0(){
    II=(1);
    line40statement1();
  }
  /** 
 *  line40statement0 line40statement1 ->  NEXT II ->  line50statement0 line40statement1
 */
  protected void line40statement1(){
    II+=1;
    if (II > (1500)) {
      line50statement0();
    }
 else {
      line40statement1();
    }
  }
  /** 
 *  line40statement1 line80statement2 ->  CLS ->  line50statement1
 */
  protected void line50statement0(){
    _env.clearScreen();
    line50statement1();
  }
  /** 
 *  line50statement0 ->  INPUT "WHAT ARE YOUR WIDTH AND LENGTH"; H,V ->  line60statement0
 */
  protected void line50statement1(){
    _env.print("WHAT ARE YOUR WIDTH AND LENGTH",true);
    H=_env.getInput();
    V=_env.getInput();
    line60statement0();
  }
  /** 
 *  line50statement1 ->  IF (((H)<>(1)) AND ((V)<>(1))) THEN 90 ->  line70statement0 line90statement0
 */
  protected void line60statement0(){
    if ((((H) != (1)) && ((V) != (1)))) {
      line90statement0();
    }
 else {
      line70statement0();
    }
  }
  /** 
 *  line60statement0 ->  PRINT "MEANINGLESS DIMENSIONS. TRY AGAIN" ->  line80statement0
 */
  protected void line70statement0(){
    _env.print("MEANINGLESS DIMENSIONS. TRY AGAIN",true);
    line80statement0();
  }
  /** 
 *  line70statement0 ->  FOR A = (1) TO (500) ->  line80statement1
 */
  protected void line80statement0(){
    A=(1);
    line80statement1();
  }
  /** 
 *  line80statement0 line80statement1 ->  NEXT A ->  line80statement2 line80statement1
 */
  protected void line80statement1(){
    A+=1;
    if (A > (500)) {
      line80statement2();
    }
 else {
      line80statement1();
    }
  }
  /** 
 *  line80statement1 ->  GOTO 50 ->  line50statement0
 */
  protected void line80statement2(){
    line50statement0();
  }
  /** 
 *  line60statement0 ->  PRINT @ 522, "PRINTOUT IS IN PROGRESS, PLEASE BE PATIENT" ->  line100statement0
 */
  protected void line90statement0(){
    _env.print("PRINTOUT IS IN PROGRESS, PLEASE BE PATIENT",true);
    line100statement0();
  }
  /** 
 *  line90statement0 ->  FOR II = (1) TO (1500) ->  line100statement1
 */
  protected void line100statement0(){
    II=(1);
    line100statement1();
  }
  /** 
 *  line100statement0 line100statement1 ->  NEXT II ->  line110statement0 line100statement1
 */
  protected void line100statement1(){
    II+=1;
    if (II > (1500)) {
      line110statement0();
    }
 else {
      line100statement1();
    }
  }
  /** 
 *  line100statement1 ->  DIM W((H),(V)), V((H),(V)) ->  line120statement0
 */
  protected void line110statement0(){
{
      Ws=new int[H][V];
    }
{
      Vs=new int[H][V];
    }
    line120statement0();
  }
  /** 
 *  line110statement0 ->  Q=(0) ->  line120statement1
 */
  protected void line120statement0(){
    Q=(0);
    line120statement1();
  }
  /** 
 *  line120statement0 ->  Z=(0) ->  line120statement2
 */
  protected void line120statement1(){
    Z=(0);
    line120statement2();
  }
  /** 
 *  line120statement1 ->  X=RND((H)) ->  line130statement0
 */
  protected void line120statement2(){
    X=(_env.getNextRandomNumber(H));
    line130statement0();
  }
  /** 
 *  line120statement2 ->  FOR I = (1) TO (H) ->  line140statement0
 */
  protected void line130statement0(){
    I=(1);
    line140statement0();
  }
  /** 
 *  line130statement0 line170statement0 ->  IF ((I)=(X)) THEN 160 ->  line150statement0 line160statement0
 */
  protected void line140statement0(){
    if (((I) == (X))) {
      line160statement0();
    }
 else {
      line150statement0();
    }
  }
  /** 
 *  line140statement0 ->  PRINT ":--"; ->  line150statement1
 */
  protected void line150statement0(){
    _env.print(":--",false);
    line150statement1();
  }
  /** 
 *  line150statement0 ->  GOTO 170 ->  line170statement0
 */
  protected void line150statement1(){
    line170statement0();
  }
  /** 
 *  line140statement0 ->  PRINT ":  "; ->  line170statement0
 */
  protected void line160statement0(){
    _env.print(":  ",false);
    line170statement0();
  }
  /** 
 *  line160statement0 line150statement1 ->  NEXT I ->  line180statement0 line140statement0
 */
  protected void line170statement0(){
    I+=1;
    if (I > (H)) {
      line180statement0();
    }
 else {
      line140statement0();
    }
  }
  /** 
 *  line170statement0 ->  PRINT ":" ->  line190statement0
 */
  protected void line180statement0(){
    _env.print(":",true);
    line190statement0();
  }
  /** 
 *  line180statement0 ->  C=(1) ->  line190statement1
 */
  protected void line190statement0(){
    C=(1);
    line190statement1();
  }
  /** 
 *  line190statement0 ->  W((X),(1))=(C) ->  line190statement2
 */
  protected void line190statement1(){
    Ws[((X) - 1)][((1) - 1)]=(C);
    line190statement2();
  }
  /** 
 *  line190statement1 ->  C=((C)+(1)) ->  line200statement0
 */
  protected void line190statement2(){
    C=((C) + (1));
    line200statement0();
  }
  /** 
 *  line190statement2 ->  R=(X) ->  line200statement1
 */
  protected void line200statement0(){
    R=(X);
    line200statement1();
  }
  /** 
 *  line200statement0 ->  S=(1) ->  line200statement2
 */
  protected void line200statement1(){
    S=(1);
    line200statement2();
  }
  /** 
 *  line200statement1 ->  GOTO 270 ->  line270statement0
 */
  protected void line200statement2(){
    line270statement0();
  }
  /** 
 *  line260statement0 line1190statement0 ->  IF ((R)<>(H)) THEN 250 ->  line220statement0 line250statement0
 */
  protected void line210statement0(){
    if (((R) != (H))) {
      line250statement0();
    }
 else {
      line220statement0();
    }
  }
  /** 
 *  line210statement0 ->  IF ((S)<>(V)) THEN 240 ->  line230statement0 line240statement0
 */
  protected void line220statement0(){
    if (((S) != (V))) {
      line240statement0();
    }
 else {
      line230statement0();
    }
  }
  /** 
 *  line220statement0 ->  R=(1) ->  line230statement1
 */
  protected void line230statement0(){
    R=(1);
    line230statement1();
  }
  /** 
 *  line230statement0 ->  S=(1) ->  line230statement2
 */
  protected void line230statement1(){
    S=(1);
    line230statement2();
  }
  /** 
 *  line230statement1 ->  GOTO 260 ->  line260statement0
 */
  protected void line230statement2(){
    line260statement0();
  }
  /** 
 *  line220statement0 ->  R=(1) ->  line240statement1
 */
  protected void line240statement0(){
    R=(1);
    line240statement1();
  }
  /** 
 *  line240statement0 ->  S=((S)+(1)) ->  line240statement2
 */
  protected void line240statement1(){
    S=((S) + (1));
    line240statement2();
  }
  /** 
 *  line240statement1 ->  GOTO 260 ->  line260statement0
 */
  protected void line240statement2(){
    line260statement0();
  }
  /** 
 *  line210statement0 ->  R=((R)+(1)) ->  line260statement0
 */
  protected void line250statement0(){
    R=((R) + (1));
    line260statement0();
  }
  /** 
 *  line250statement0 line230statement2 line240statement2 line1180statement4 ->  IF (W((R),(S))=(0)) THEN 210 ->  line270statement0 line210statement0
 */
  protected void line260statement0(){
    if (((Ws[((R) - 1)][((S) - 1)]) == (0))) {
      line210statement0();
    }
 else {
      line270statement0();
    }
  }
  /** 
 *  line260statement0 line200statement2 line970statement1 line1010statement1 line1140statement0 ->  IF (((R)-(1))=(0)) THEN 600 ->  line280statement0 line600statement0
 */
  protected void line270statement0(){
    if ((((R) - (1)) == (0))) {
      line600statement0();
    }
 else {
      line280statement0();
    }
  }
  /** 
 *  line270statement0 ->  IF (W(((R)-(1)),(S))<>(0)) THEN 600 ->  line290statement0 line600statement0
 */
  protected void line280statement0(){
    if (((Ws[(((R) - (1)) - 1)][((S) - 1)]) != (0))) {
      line600statement0();
    }
 else {
      line290statement0();
    }
  }
  /** 
 *  line280statement0 ->  IF (((S)-(1))=(0)) THEN 430 ->  line300statement0 line430statement0
 */
  protected void line290statement0(){
    if ((((S) - (1)) == (0))) {
      line430statement0();
    }
 else {
      line300statement0();
    }
  }
  /** 
 *  line290statement0 ->  IF (W((R),((S)-(1)))<>(0)) THEN 430 ->  line310statement0 line430statement0
 */
  protected void line300statement0(){
    if (((Ws[((R) - 1)][(((S) - (1)) - 1)]) != (0))) {
      line430statement0();
    }
 else {
      line310statement0();
    }
  }
  /** 
 *  line300statement0 ->  IF ((R)=(H)) THEN 350 ->  line320statement0 line350statement0
 */
  protected void line310statement0(){
    if (((R) == (H))) {
      line350statement0();
    }
 else {
      line320statement0();
    }
  }
  /** 
 *  line310statement0 ->  IF (W(((R)+(1)),(S))<>(0)) THEN 350 ->  line330statement0 line350statement0
 */
  protected void line320statement0(){
    if (((Ws[(((R) + (1)) - 1)][((S) - 1)]) != (0))) {
      line350statement0();
    }
 else {
      line330statement0();
    }
  }
  /** 
 *  line320statement0 ->  X=RND((3)) ->  line340statement0
 */
  protected void line330statement0(){
    X=(_env.getNextRandomNumber(3));
    line340statement0();
  }
  /** 
 *  line330statement0 ->  ON (X) GOTO 940, 980, 1020 ->  line940statement0 line980statement0 line1020statement0
 */
  protected void line340statement0(){
switch (X) {
case 1:
      line940statement0();
    break;
case 2:
  line980statement0();
break;
case 3:
line1020statement0();
break;
}
}
/** 
 *  line310statement0 line320statement0 ->  IF ((S)<>(V)) THEN 380 ->  line360statement0 line380statement0
 */
protected void line350statement0(){
if (((S) != (V))) {
line380statement0();
}
 else {
line360statement0();
}
}
/** 
 *  line350statement0 ->  IF ((Z)=(1)) THEN 410 ->  line370statement0 line410statement0
 */
protected void line360statement0(){
if (((Z) == (1))) {
line410statement0();
}
 else {
line370statement0();
}
}
/** 
 *  line360statement0 ->  Q=(1) ->  line370statement1
 */
protected void line370statement0(){
Q=(1);
line370statement1();
}
/** 
 *  line370statement0 ->  GOTO 390 ->  line390statement0
 */
protected void line370statement1(){
line390statement0();
}
/** 
 *  line350statement0 ->  IF (W((R),((S)+(1)))<>(0)) THEN 410 ->  line390statement0 line410statement0
 */
protected void line380statement0(){
if (((Ws[((R) - 1)][(((S) + (1)) - 1)]) != (0))) {
line410statement0();
}
 else {
line390statement0();
}
}
/** 
 *  line380statement0 line370statement1 ->  X=RND((3)) ->  line400statement0
 */
protected void line390statement0(){
X=(_env.getNextRandomNumber(3));
line400statement0();
}
/** 
 *  line390statement0 ->  ON (X) GOTO 940, 980, 1090 ->  line940statement0 line980statement0 line1090statement0
 */
protected void line400statement0(){
switch (X) {
case 1:
line940statement0();
break;
case 2:
line980statement0();
break;
case 3:
line1090statement0();
break;
}
}
/** 
 *  line360statement0 line380statement0 ->  X=RND((2)) ->  line420statement0
 */
protected void line410statement0(){
X=(_env.getNextRandomNumber(2));
line420statement0();
}
/** 
 *  line410statement0 ->  ON (X) GOTO 940, 980 ->  line940statement0 line980statement0
 */
protected void line420statement0(){
switch (X) {
case 1:
line940statement0();
break;
case 2:
line980statement0();
break;
}
}
/** 
 *  line290statement0 line300statement0 ->  IF ((R)=(H)) THEN 530 ->  line440statement0 line530statement0
 */
protected void line430statement0(){
if (((R) == (H))) {
line530statement0();
}
 else {
line440statement0();
}
}
/** 
 *  line430statement0 ->  IF (W(((R)+(1)),(S))<>(0)) THEN 530 ->  line450statement0 line530statement0
 */
protected void line440statement0(){
if (((Ws[(((R) + (1)) - 1)][((S) - 1)]) != (0))) {
line530statement0();
}
 else {
line450statement0();
}
}
/** 
 *  line440statement0 ->  IF ((S)<>(V)) THEN 480 ->  line460statement0 line480statement0
 */
protected void line450statement0(){
if (((S) != (V))) {
line480statement0();
}
 else {
line460statement0();
}
}
/** 
 *  line450statement0 ->  IF ((Z)=(1)) THEN 510 ->  line470statement0 line510statement0
 */
protected void line460statement0(){
if (((Z) == (1))) {
line510statement0();
}
 else {
line470statement0();
}
}
/** 
 *  line460statement0 ->  Q=(1) ->  line470statement1
 */
protected void line470statement0(){
Q=(1);
line470statement1();
}
/** 
 *  line470statement0 ->  GOTO 490 ->  line490statement0
 */
protected void line470statement1(){
line490statement0();
}
/** 
 *  line450statement0 ->  IF (W((R),((S)+(1)))<>(0)) THEN 510 ->  line490statement0 line510statement0
 */
protected void line480statement0(){
if (((Ws[((R) - 1)][(((S) + (1)) - 1)]) != (0))) {
line510statement0();
}
 else {
line490statement0();
}
}
/** 
 *  line480statement0 line470statement1 ->  X=RND((3)) ->  line500statement0
 */
protected void line490statement0(){
X=(_env.getNextRandomNumber(3));
line500statement0();
}
/** 
 *  line490statement0 ->  ON (X) GOTO 940, 1020, 1090 ->  line940statement0 line1020statement0 line1090statement0
 */
protected void line500statement0(){
switch (X) {
case 1:
line940statement0();
break;
case 2:
line1020statement0();
break;
case 3:
line1090statement0();
break;
}
}
/** 
 *  line460statement0 line480statement0 ->  X=RND((2)) ->  line520statement0
 */
protected void line510statement0(){
X=(_env.getNextRandomNumber(2));
line520statement0();
}
/** 
 *  line510statement0 ->  ON (X) GOTO 940, 1020 ->  line940statement0 line1020statement0
 */
protected void line520statement0(){
switch (X) {
case 1:
line940statement0();
break;
case 2:
line1020statement0();
break;
}
}
/** 
 *  line430statement0 line440statement0 ->  IF ((S)<>(V)) THEN 560 ->  line540statement0 line560statement0
 */
protected void line530statement0(){
if (((S) != (V))) {
line560statement0();
}
 else {
line540statement0();
}
}
/** 
 *  line530statement0 ->  IF ((Z)=(1)) THEN 590 ->  line550statement0 line590statement0
 */
protected void line540statement0(){
if (((Z) == (1))) {
line590statement0();
}
 else {
line550statement0();
}
}
/** 
 *  line540statement0 ->  Q=(1) ->  line550statement1
 */
protected void line550statement0(){
Q=(1);
line550statement1();
}
/** 
 *  line550statement0 ->  GOTO 570 ->  line570statement0
 */
protected void line550statement1(){
line570statement0();
}
/** 
 *  line530statement0 ->  IF (W((R),((S)+(1)))<>(0)) THEN 590 ->  line570statement0 line590statement0
 */
protected void line560statement0(){
if (((Ws[((R) - 1)][(((S) + (1)) - 1)]) != (0))) {
line590statement0();
}
 else {
line570statement0();
}
}
/** 
 *  line560statement0 line550statement1 ->  X=RND((2)) ->  line580statement0
 */
protected void line570statement0(){
X=(_env.getNextRandomNumber(2));
line580statement0();
}
/** 
 *  line570statement0 ->  ON (X) GOTO 940, 1090 ->  line940statement0 line1090statement0
 */
protected void line580statement0(){
switch (X) {
case 1:
line940statement0();
break;
case 2:
line1090statement0();
break;
}
}
/** 
 *  line540statement0 line560statement0 ->  GOTO 940 ->  line940statement0
 */
protected void line590statement0(){
line940statement0();
}
/** 
 *  line270statement0 line280statement0 line1080statement0 ->  IF (((S)-(1))=(0)) THEN 790 ->  line610statement0 line790statement0
 */
protected void line600statement0(){
if ((((S) - (1)) == (0))) {
line790statement0();
}
 else {
line610statement0();
}
}
/** 
 *  line600statement0 ->  IF (W((R),((S)-(1)))<>(0)) THEN 790 ->  line620statement0 line790statement0
 */
protected void line610statement0(){
if (((Ws[((R) - 1)][(((S) - (1)) - 1)]) != (0))) {
line790statement0();
}
 else {
line620statement0();
}
}
/** 
 *  line610statement0 ->  IF ((R)=(H)) THEN 720 ->  line630statement0 line720statement0
 */
protected void line620statement0(){
if (((R) == (H))) {
line720statement0();
}
 else {
line630statement0();
}
}
/** 
 *  line620statement0 ->  IF (W(((R)+(1)),(S))<>(0)) THEN 720 ->  line640statement0 line720statement0
 */
protected void line630statement0(){
if (((Ws[(((R) + (1)) - 1)][((S) - 1)]) != (0))) {
line720statement0();
}
 else {
line640statement0();
}
}
/** 
 *  line630statement0 ->  IF ((S)<>(V)) THEN 670 ->  line650statement0 line670statement0
 */
protected void line640statement0(){
if (((S) != (V))) {
line670statement0();
}
 else {
line650statement0();
}
}
/** 
 *  line640statement0 ->  IF ((Z)=(1)) THEN 700 ->  line660statement0 line700statement0
 */
protected void line650statement0(){
if (((Z) == (1))) {
line700statement0();
}
 else {
line660statement0();
}
}
/** 
 *  line650statement0 ->  Q=(1) ->  line660statement1
 */
protected void line660statement0(){
Q=(1);
line660statement1();
}
/** 
 *  line660statement0 ->  GOTO 680 ->  line680statement0
 */
protected void line660statement1(){
line680statement0();
}
/** 
 *  line640statement0 ->  IF (W((R),((S)+(1)))<>(0)) THEN 700 ->  line680statement0 line700statement0
 */
protected void line670statement0(){
if (((Ws[((R) - 1)][(((S) + (1)) - 1)]) != (0))) {
line700statement0();
}
 else {
line680statement0();
}
}
/** 
 *  line670statement0 line660statement1 ->  X=RND((3)) ->  line690statement0
 */
protected void line680statement0(){
X=(_env.getNextRandomNumber(3));
line690statement0();
}
/** 
 *  line680statement0 ->  ON (X) GOTO 980, 1020, 1090 ->  line980statement0 line1020statement0 line1090statement0
 */
protected void line690statement0(){
switch (X) {
case 1:
line980statement0();
break;
case 2:
line1020statement0();
break;
case 3:
line1090statement0();
break;
}
}
/** 
 *  line650statement0 line670statement0 ->  X=RND((2)) ->  line710statement0
 */
protected void line700statement0(){
X=(_env.getNextRandomNumber(2));
line710statement0();
}
/** 
 *  line700statement0 ->  ON (X) GOTO 980, 1020 ->  line980statement0 line1020statement0
 */
protected void line710statement0(){
switch (X) {
case 1:
line980statement0();
break;
case 2:
line1020statement0();
break;
}
}
/** 
 *  line620statement0 line630statement0 ->  IF ((S)<>(V)) THEN 750 ->  line730statement0 line750statement0
 */
protected void line720statement0(){
if (((S) != (V))) {
line750statement0();
}
 else {
line730statement0();
}
}
/** 
 *  line720statement0 ->  IF ((Z)=(1)) THEN 780 ->  line740statement0 line780statement0
 */
protected void line730statement0(){
if (((Z) == (1))) {
line780statement0();
}
 else {
line740statement0();
}
}
/** 
 *  line730statement0 ->  Q=(1) ->  line740statement1
 */
protected void line740statement0(){
Q=(1);
line740statement1();
}
/** 
 *  line740statement0 ->  GOTO 760 ->  line760statement0
 */
protected void line740statement1(){
line760statement0();
}
/** 
 *  line720statement0 ->  IF (W((R),((S)+(1)))<>(0)) THEN 780 ->  line760statement0 line780statement0
 */
protected void line750statement0(){
if (((Ws[((R) - 1)][(((S) + (1)) - 1)]) != (0))) {
line780statement0();
}
 else {
line760statement0();
}
}
/** 
 *  line750statement0 line740statement1 ->  X=RND((2)) ->  line770statement0
 */
protected void line760statement0(){
X=(_env.getNextRandomNumber(2));
line770statement0();
}
/** 
 *  line760statement0 ->  ON (X) GOTO 980, 1090 ->  line980statement0 line1090statement0
 */
protected void line770statement0(){
switch (X) {
case 1:
line980statement0();
break;
case 2:
line1090statement0();
break;
}
}
/** 
 *  line730statement0 line750statement0 ->  GOTO 980 ->  line980statement0
 */
protected void line780statement0(){
line980statement0();
}
/** 
 *  line600statement0 line610statement0 ->  IF ((R)=(H)) THEN 880 ->  line800statement0 line880statement0
 */
protected void line790statement0(){
if (((R) == (H))) {
line880statement0();
}
 else {
line800statement0();
}
}
/** 
 *  line790statement0 ->  IF (W(((R)+(1)),(S))<>(0)) THEN 880 ->  line810statement0 line880statement0
 */
protected void line800statement0(){
if (((Ws[(((R) + (1)) - 1)][((S) - 1)]) != (0))) {
line880statement0();
}
 else {
line810statement0();
}
}
/** 
 *  line800statement0 ->  IF ((S)<>(V)) THEN 840 ->  line820statement0 line840statement0
 */
protected void line810statement0(){
if (((S) != (V))) {
line840statement0();
}
 else {
line820statement0();
}
}
/** 
 *  line810statement0 ->  IF ((Z)=(1)) THEN 870 ->  line830statement0 line870statement0
 */
protected void line820statement0(){
if (((Z) == (1))) {
line870statement0();
}
 else {
line830statement0();
}
}
/** 
 *  line820statement0 ->  Q=(1) ->  line830statement1
 */
protected void line830statement0(){
Q=(1);
line830statement1();
}
/** 
 *  line830statement0 ->  GOTO 990 ->  line990statement0
 */
protected void line830statement1(){
line990statement0();
}
/** 
 *  line810statement0 ->  IF (W((R),((S)+(1)))<>(0)) THEN 870 ->  line850statement0 line870statement0
 */
protected void line840statement0(){
if (((Ws[((R) - 1)][(((S) + (1)) - 1)]) != (0))) {
line870statement0();
}
 else {
line850statement0();
}
}
/** 
 *  line840statement0 ->  X=RND((2)) ->  line860statement0
 */
protected void line850statement0(){
X=(_env.getNextRandomNumber(2));
line860statement0();
}
/** 
 *  line850statement0 ->  ON (X) GOTO 1020, 1090 ->  line1020statement0 line1090statement0
 */
protected void line860statement0(){
switch (X) {
case 1:
line1020statement0();
break;
case 2:
line1090statement0();
break;
}
}
/** 
 *  line820statement0 line840statement0 ->  GOTO 1020 ->  line1020statement0
 */
protected void line870statement0(){
line1020statement0();
}
/** 
 *  line790statement0 line800statement0 ->  IF ((S)<>(V)) THEN 910 ->  line890statement0 line910statement0
 */
protected void line880statement0(){
if (((S) != (V))) {
line910statement0();
}
 else {
line890statement0();
}
}
/** 
 *  line880statement0 ->  IF ((Z)=(1)) THEN 930 ->  line900statement0 line930statement0
 */
protected void line890statement0(){
if (((Z) == (1))) {
line930statement0();
}
 else {
line900statement0();
}
}
/** 
 *  line890statement0 ->  Q=(1) ->  line900statement1
 */
protected void line900statement0(){
Q=(1);
line900statement1();
}
/** 
 *  line900statement0 ->  GOTO 920 ->  line920statement0
 */
protected void line900statement1(){
line920statement0();
}
/** 
 *  line880statement0 ->  IF (W((R),((S)+(1)))<>(0)) THEN 930 ->  line920statement0 line930statement0
 */
protected void line910statement0(){
if (((Ws[((R) - 1)][(((S) + (1)) - 1)]) != (0))) {
line930statement0();
}
 else {
line920statement0();
}
}
/** 
 *  line910statement0 line900statement1 ->  GOTO 1090 ->  line1090statement0
 */
protected void line920statement0(){
line1090statement0();
}
/** 
 *  line890statement0 line910statement0 ->  GOTO 1190 ->  line1190statement0
 */
protected void line930statement0(){
line1190statement0();
}
/** 
 *  line340statement0 line400statement0 line420statement0 line500statement0 line520statement0 line580statement0 line590statement0 ->  W(((R)-(1)),(S))=(C) ->  line950statement0
 */
protected void line940statement0(){
Ws[(((R) - (1)) - 1)][((S) - 1)]=(C);
line950statement0();
}
/** 
 *  line940statement0 ->  C=((C)+(1)) ->  line950statement1
 */
protected void line950statement0(){
C=((C) + (1));
line950statement1();
}
/** 
 *  line950statement0 ->  V(((R)-(1)),(S))=(2) ->  line950statement2
 */
protected void line950statement1(){
Vs[(((R) - (1)) - 1)][((S) - 1)]=(2);
line950statement2();
}
/** 
 *  line950statement1 ->  R=((R)-(1)) ->  line960statement0
 */
protected void line950statement2(){
R=((R) - (1));
line960statement0();
}
/** 
 *  line950statement2 ->  IF ((C)=(((H)*(V))+(1))) THEN 1200 ->  line970statement0 line1200statement0
 */
protected void line960statement0(){
if (((C) == (((H) * (V)) + (1)))) {
line1200statement0();
}
 else {
line970statement0();
}
}
/** 
 *  line960statement0 ->  Q=(0) ->  line970statement1
 */
protected void line970statement0(){
Q=(0);
line970statement1();
}
/** 
 *  line970statement0 ->  GOTO 270 ->  line270statement0
 */
protected void line970statement1(){
line270statement0();
}
/** 
 *  line340statement0 line400statement0 line420statement0 line690statement0 line710statement0 line770statement0 line780statement0 ->  W((R),((S)-(1)))=(C) ->  line990statement0
 */
protected void line980statement0(){
Ws[((R) - 1)][(((S) - (1)) - 1)]=(C);
line990statement0();
}
/** 
 *  line980statement0 line830statement1 ->  C=((C)+(1)) ->  line1000statement0
 */
protected void line990statement0(){
C=((C) + (1));
line1000statement0();
}
/** 
 *  line990statement0 ->  V((R),((S)-(1)))=(1) ->  line1000statement1
 */
protected void line1000statement0(){
Vs[((R) - 1)][(((S) - (1)) - 1)]=(1);
line1000statement1();
}
/** 
 *  line1000statement0 ->  S=((S)-(1)) ->  line1000statement2
 */
protected void line1000statement1(){
S=((S) - (1));
line1000statement2();
}
/** 
 *  line1000statement1 ->  IF ((C)=(((H)*(V))+(1))) THEN 1200 ->  line1010statement0 line1200statement0
 */
protected void line1000statement2(){
if (((C) == (((H) * (V)) + (1)))) {
line1200statement0();
}
 else {
line1010statement0();
}
}
/** 
 *  line1000statement2 ->  Q=(0) ->  line1010statement1
 */
protected void line1010statement0(){
Q=(0);
line1010statement1();
}
/** 
 *  line1010statement0 ->  GOTO 270 ->  line270statement0
 */
protected void line1010statement1(){
line270statement0();
}
/** 
 *  line340statement0 line500statement0 line520statement0 line690statement0 line710statement0 line860statement0 line870statement0 ->  W(((R)+(1)),(S))=(C) ->  line1030statement0
 */
protected void line1020statement0(){
Ws[(((R) + (1)) - 1)][((S) - 1)]=(C);
line1030statement0();
}
/** 
 *  line1020statement0 ->  C=((C)+(1)) ->  line1030statement1
 */
protected void line1030statement0(){
C=((C) + (1));
line1030statement1();
}
/** 
 *  line1030statement0 ->  IF (V((R),(S))=(0)) THEN 1050 ->  line1040statement0 line1050statement0
 */
protected void line1030statement1(){
if (((Vs[((R) - 1)][((S) - 1)]) == (0))) {
line1050statement0();
}
 else {
line1040statement0();
}
}
/** 
 *  line1030statement1 ->  V((R),(S))=(3) ->  line1040statement1
 */
protected void line1040statement0(){
Vs[((R) - 1)][((S) - 1)]=(3);
line1040statement1();
}
/** 
 *  line1040statement0 ->  GOTO 1060 ->  line1060statement0
 */
protected void line1040statement1(){
line1060statement0();
}
/** 
 *  line1030statement1 ->  V((R),(S))=(2) ->  line1060statement0
 */
protected void line1050statement0(){
Vs[((R) - 1)][((S) - 1)]=(2);
line1060statement0();
}
/** 
 *  line1050statement0 line1040statement1 ->  R=((R)+(1)) ->  line1070statement0
 */
protected void line1060statement0(){
R=((R) + (1));
line1070statement0();
}
/** 
 *  line1060statement0 ->  IF ((C)=(((H)*(V))+(1))) THEN 1200 ->  line1080statement0 line1200statement0
 */
protected void line1070statement0(){
if (((C) == (((H) * (V)) + (1)))) {
line1200statement0();
}
 else {
line1080statement0();
}
}
/** 
 *  line1070statement0 ->  GOTO 600 ->  line600statement0
 */
protected void line1080statement0(){
line600statement0();
}
/** 
 *  line400statement0 line500statement0 line580statement0 line690statement0 line770statement0 line860statement0 line920statement0 ->  IF ((Q)=(1)) THEN 1150 ->  line1100statement0 line1150statement0
 */
protected void line1090statement0(){
if (((Q) == (1))) {
line1150statement0();
}
 else {
line1100statement0();
}
}
/** 
 *  line1090statement0 ->  W((R),((S)+(1)))=(C) ->  line1100statement1
 */
protected void line1100statement0(){
Ws[((R) - 1)][(((S) + (1)) - 1)]=(C);
line1100statement1();
}
/** 
 *  line1100statement0 ->  C=((C)+(1)) ->  line1100statement2
 */
protected void line1100statement1(){
C=((C) + (1));
line1100statement2();
}
/** 
 *  line1100statement1 ->  IF (V((R),(S))=(0)) THEN 1120 ->  line1110statement0 line1120statement0
 */
protected void line1100statement2(){
if (((Vs[((R) - 1)][((S) - 1)]) == (0))) {
line1120statement0();
}
 else {
line1110statement0();
}
}
/** 
 *  line1100statement2 ->  V((R),(S))=(3) ->  line1110statement1
 */
protected void line1110statement0(){
Vs[((R) - 1)][((S) - 1)]=(3);
line1110statement1();
}
/** 
 *  line1110statement0 ->  GOTO 1130 ->  line1130statement0
 */
protected void line1110statement1(){
line1130statement0();
}
/** 
 *  line1100statement2 ->  V((R),(S))=(1) ->  line1130statement0
 */
protected void line1120statement0(){
Vs[((R) - 1)][((S) - 1)]=(1);
line1130statement0();
}
/** 
 *  line1120statement0 line1110statement1 ->  S=((S)+(1)) ->  line1130statement1
 */
protected void line1130statement0(){
S=((S) + (1));
line1130statement1();
}
/** 
 *  line1130statement0 ->  IF ((C)=(((V)*(H))+(1))) THEN 1200 ->  line1140statement0 line1200statement0
 */
protected void line1130statement1(){
if (((C) == (((V) * (H)) + (1)))) {
line1200statement0();
}
 else {
line1140statement0();
}
}
/** 
 *  line1130statement1 ->  GOTO 270 ->  line270statement0
 */
protected void line1140statement0(){
line270statement0();
}
/** 
 *  line1090statement0 ->  Z=(1) ->  line1160statement0
 */
protected void line1150statement0(){
Z=(1);
line1160statement0();
}
/** 
 *  line1150statement0 ->  IF (V((R),(S))=(0)) THEN 1180 ->  line1170statement0 line1180statement0
 */
protected void line1160statement0(){
if (((Vs[((R) - 1)][((S) - 1)]) == (0))) {
line1180statement0();
}
 else {
line1170statement0();
}
}
/** 
 *  line1160statement0 ->  V((R),(S))=(3) ->  line1170statement1
 */
protected void line1170statement0(){
Vs[((R) - 1)][((S) - 1)]=(3);
line1170statement1();
}
/** 
 *  line1170statement0 ->  Q=(0) ->  line1170statement2
 */
protected void line1170statement1(){
Q=(0);
line1170statement2();
}
/** 
 *  line1170statement1 ->  GOTO 1190 ->  line1190statement0
 */
protected void line1170statement2(){
line1190statement0();
}
/** 
 *  line1160statement0 ->  V((R),(S))=(1) ->  line1180statement1
 */
protected void line1180statement0(){
Vs[((R) - 1)][((S) - 1)]=(1);
line1180statement1();
}
/** 
 *  line1180statement0 ->  Q=(0) ->  line1180statement2
 */
protected void line1180statement1(){
Q=(0);
line1180statement2();
}
/** 
 *  line1180statement1 ->  R=(1) ->  line1180statement3
 */
protected void line1180statement2(){
R=(1);
line1180statement3();
}
/** 
 *  line1180statement2 ->  S=(1) ->  line1180statement4
 */
protected void line1180statement3(){
S=(1);
line1180statement4();
}
/** 
 *  line1180statement3 ->  GOTO 260 ->  line260statement0
 */
protected void line1180statement4(){
line260statement0();
}
/** 
 *  line930statement0 line1170statement2 ->  GOTO 210 ->  line210statement0
 */
protected void line1190statement0(){
line210statement0();
}
/** 
 *  line960statement0 line1000statement2 line1070statement0 line1130statement1 ->  FOR J = (1) TO (V) ->  line1210statement0
 */
protected void line1200statement0(){
J=(1);
line1210statement0();
}
/** 
 *  line1200statement0 line1370statement0 ->  PRINT "I"; ->  line1220statement0
 */
protected void line1210statement0(){
_env.print("I",false);
line1220statement0();
}
/** 
 *  line1210statement0 ->  FOR I = (1) TO (H) ->  line1230statement0
 */
protected void line1220statement0(){
I=(1);
line1230statement0();
}
/** 
 *  line1220statement0 line1270statement0 ->  IF (V((I),(J))<(2)) THEN 1260 ->  line1240statement0 line1260statement0
 */
protected void line1230statement0(){
if (((Vs[((I) - 1)][((J) - 1)]) < (2))) {
line1260statement0();
}
 else {
line1240statement0();
}
}
/** 
 *  line1230statement0 ->  PRINT "   "; ->  line1250statement0
 */
protected void line1240statement0(){
_env.print("   ",false);
line1250statement0();
}
/** 
 *  line1240statement0 ->  GOTO 1270 ->  line1270statement0
 */
protected void line1250statement0(){
line1270statement0();
}
/** 
 *  line1230statement0 ->  PRINT "  I"; ->  line1270statement0
 */
protected void line1260statement0(){
_env.print("  I",false);
line1270statement0();
}
/** 
 *  line1260statement0 line1250statement0 ->  NEXT I ->  line1280statement0 line1230statement0
 */
protected void line1270statement0(){
I+=1;
if (I > (H)) {
line1280statement0();
}
 else {
line1230statement0();
}
}
/** 
 *  line1270statement0 ->  PRINT " " ->  line1290statement0
 */
protected void line1280statement0(){
_env.print(" ",true);
line1290statement0();
}
/** 
 *  line1280statement0 ->  FOR I = (1) TO (H) ->  line1300statement0
 */
protected void line1290statement0(){
I=(1);
line1300statement0();
}
/** 
 *  line1290statement0 line1350statement0 ->  IF (V((I),(J))=(0)) THEN 1340 ->  line1310statement0 line1340statement0
 */
protected void line1300statement0(){
if (((Vs[((I) - 1)][((J) - 1)]) == (0))) {
line1340statement0();
}
 else {
line1310statement0();
}
}
/** 
 *  line1300statement0 ->  IF (V((I),(J))=(2)) THEN 1340 ->  line1320statement0 line1340statement0
 */
protected void line1310statement0(){
if (((Vs[((I) - 1)][((J) - 1)]) == (2))) {
line1340statement0();
}
 else {
line1320statement0();
}
}
/** 
 *  line1310statement0 ->  PRINT ":  "; ->  line1330statement0
 */
protected void line1320statement0(){
_env.print(":  ",false);
line1330statement0();
}
/** 
 *  line1320statement0 ->  GOTO 1350 ->  line1350statement0
 */
protected void line1330statement0(){
line1350statement0();
}
/** 
 *  line1300statement0 line1310statement0 ->  PRINT ":--"; ->  line1350statement0
 */
protected void line1340statement0(){
_env.print(":--",false);
line1350statement0();
}
/** 
 *  line1340statement0 line1330statement0 ->  NEXT I ->  line1360statement0 line1300statement0
 */
protected void line1350statement0(){
I+=1;
if (I > (H)) {
line1360statement0();
}
 else {
line1300statement0();
}
}
/** 
 *  line1350statement0 ->  PRINT ":" ->  line1370statement0
 */
protected void line1360statement0(){
_env.print(":",true);
line1370statement0();
}
/** 
 *  line1360statement0 ->  NEXT J ->  line1380statement0 line1210statement0
 */
protected void line1370statement0(){
J+=1;
if (J > (V)) {
line1380statement0();
}
 else {
line1210statement0();
}
}
/** 
 *  line1370statement0 ->  END -> 
 */
protected void line1380statement0(){
}
}
/*
line10statement0[label="10.0"];
line10statement0 -> line10statement1;
line10statement1[label="10.1"];
line10statement1 -> line20statement0;
line20statement0[label="20.0"];
line20statement0 -> line30statement0;
line30statement0[label="30.0"];
line30statement0 -> line40statement0;
line40statement0[label="40.0"];
line40statement0 -> line40statement1;
line40statement1[label="40.1"];
line40statement1 -> line50statement0;
line40statement1 -> line40statement1;
line50statement0[label="50.0"];
line50statement0 -> line50statement1;
line50statement1[label="50.1"];
line50statement1 -> line60statement0;
line60statement0[label="60.0"];
line60statement0 -> line70statement0;
line60statement0 -> line90statement0;
line70statement0[label="70.0"];
line70statement0 -> line80statement0;
line80statement0[label="80.0"];
line80statement0 -> line80statement1;
line80statement1[label="80.1"];
line80statement1 -> line80statement2;
line80statement1 -> line80statement1;
line80statement2[label="80.2"];
line80statement2 -> line50statement0;
line90statement0[label="90.0"];
line90statement0 -> line100statement0;
line100statement0[label="100.0"];
line100statement0 -> line100statement1;
line100statement1[label="100.1"];
line100statement1 -> line110statement0;
line100statement1 -> line100statement1;
line110statement0[label="110.0"];
line110statement0 -> line120statement0;
line120statement0[label="120.0"];
line120statement0 -> line120statement1;
line120statement1[label="120.1"];
line120statement1 -> line120statement2;
line120statement2[label="120.2"];
line120statement2 -> line130statement0;
line130statement0[label="130.0"];
line130statement0 -> line140statement0;
line140statement0[label="140.0"];
line140statement0 -> line150statement0;
line140statement0 -> line160statement0;
line150statement0[label="150.0"];
line150statement0 -> line150statement1;
line150statement1[label="150.1"];
line150statement1 -> line170statement0;
line160statement0[label="160.0"];
line160statement0 -> line170statement0;
line170statement0[label="170.0"];
line170statement0 -> line180statement0;
line170statement0 -> line140statement0;
line180statement0[label="180.0"];
line180statement0 -> line190statement0;
line190statement0[label="190.0"];
line190statement0 -> line190statement1;
line190statement1[label="190.1"];
line190statement1 -> line190statement2;
line190statement2[label="190.2"];
line190statement2 -> line200statement0;
line200statement0[label="200.0"];
line200statement0 -> line200statement1;
line200statement1[label="200.1"];
line200statement1 -> line200statement2;
line200statement2[label="200.2"];
line200statement2 -> line270statement0;
line210statement0[label="210.0"];
line210statement0 -> line220statement0;
line210statement0 -> line250statement0;
line220statement0[label="220.0"];
line220statement0 -> line230statement0;
line220statement0 -> line240statement0;
line230statement0[label="230.0"];
line230statement0 -> line230statement1;
line230statement1[label="230.1"];
line230statement1 -> line230statement2;
line230statement2[label="230.2"];
line230statement2 -> line260statement0;
line240statement0[label="240.0"];
line240statement0 -> line240statement1;
line240statement1[label="240.1"];
line240statement1 -> line240statement2;
line240statement2[label="240.2"];
line240statement2 -> line260statement0;
line250statement0[label="250.0"];
line250statement0 -> line260statement0;
line260statement0[label="260.0"];
line260statement0 -> line270statement0;
line260statement0 -> line210statement0;
line270statement0[label="270.0"];
line270statement0 -> line280statement0;
line270statement0 -> line600statement0;
line280statement0[label="280.0"];
line280statement0 -> line290statement0;
line280statement0 -> line600statement0;
line290statement0[label="290.0"];
line290statement0 -> line300statement0;
line290statement0 -> line430statement0;
line300statement0[label="300.0"];
line300statement0 -> line310statement0;
line300statement0 -> line430statement0;
line310statement0[label="310.0"];
line310statement0 -> line320statement0;
line310statement0 -> line350statement0;
line320statement0[label="320.0"];
line320statement0 -> line330statement0;
line320statement0 -> line350statement0;
line330statement0[label="330.0"];
line330statement0 -> line340statement0;
line340statement0[label="340.0"];
line340statement0 -> line940statement0;
line340statement0 -> line980statement0;
line340statement0 -> line1020statement0;
line350statement0[label="350.0"];
line350statement0 -> line360statement0;
line350statement0 -> line380statement0;
line360statement0[label="360.0"];
line360statement0 -> line370statement0;
line360statement0 -> line410statement0;
line370statement0[label="370.0"];
line370statement0 -> line370statement1;
line370statement1[label="370.1"];
line370statement1 -> line390statement0;
line380statement0[label="380.0"];
line380statement0 -> line390statement0;
line380statement0 -> line410statement0;
line390statement0[label="390.0"];
line390statement0 -> line400statement0;
line400statement0[label="400.0"];
line400statement0 -> line940statement0;
line400statement0 -> line980statement0;
line400statement0 -> line1090statement0;
line410statement0[label="410.0"];
line410statement0 -> line420statement0;
line420statement0[label="420.0"];
line420statement0 -> line940statement0;
line420statement0 -> line980statement0;
line430statement0[label="430.0"];
line430statement0 -> line440statement0;
line430statement0 -> line530statement0;
line440statement0[label="440.0"];
line440statement0 -> line450statement0;
line440statement0 -> line530statement0;
line450statement0[label="450.0"];
line450statement0 -> line460statement0;
line450statement0 -> line480statement0;
line460statement0[label="460.0"];
line460statement0 -> line470statement0;
line460statement0 -> line510statement0;
line470statement0[label="470.0"];
line470statement0 -> line470statement1;
line470statement1[label="470.1"];
line470statement1 -> line490statement0;
line480statement0[label="480.0"];
line480statement0 -> line490statement0;
line480statement0 -> line510statement0;
line490statement0[label="490.0"];
line490statement0 -> line500statement0;
line500statement0[label="500.0"];
line500statement0 -> line940statement0;
line500statement0 -> line1020statement0;
line500statement0 -> line1090statement0;
line510statement0[label="510.0"];
line510statement0 -> line520statement0;
line520statement0[label="520.0"];
line520statement0 -> line940statement0;
line520statement0 -> line1020statement0;
line530statement0[label="530.0"];
line530statement0 -> line540statement0;
line530statement0 -> line560statement0;
line540statement0[label="540.0"];
line540statement0 -> line550statement0;
line540statement0 -> line590statement0;
line550statement0[label="550.0"];
line550statement0 -> line550statement1;
line550statement1[label="550.1"];
line550statement1 -> line570statement0;
line560statement0[label="560.0"];
line560statement0 -> line570statement0;
line560statement0 -> line590statement0;
line570statement0[label="570.0"];
line570statement0 -> line580statement0;
line580statement0[label="580.0"];
line580statement0 -> line940statement0;
line580statement0 -> line1090statement0;
line590statement0[label="590.0"];
line590statement0 -> line940statement0;
line600statement0[label="600.0"];
line600statement0 -> line610statement0;
line600statement0 -> line790statement0;
line610statement0[label="610.0"];
line610statement0 -> line620statement0;
line610statement0 -> line790statement0;
line620statement0[label="620.0"];
line620statement0 -> line630statement0;
line620statement0 -> line720statement0;
line630statement0[label="630.0"];
line630statement0 -> line640statement0;
line630statement0 -> line720statement0;
line640statement0[label="640.0"];
line640statement0 -> line650statement0;
line640statement0 -> line670statement0;
line650statement0[label="650.0"];
line650statement0 -> line660statement0;
line650statement0 -> line700statement0;
line660statement0[label="660.0"];
line660statement0 -> line660statement1;
line660statement1[label="660.1"];
line660statement1 -> line680statement0;
line670statement0[label="670.0"];
line670statement0 -> line680statement0;
line670statement0 -> line700statement0;
line680statement0[label="680.0"];
line680statement0 -> line690statement0;
line690statement0[label="690.0"];
line690statement0 -> line980statement0;
line690statement0 -> line1020statement0;
line690statement0 -> line1090statement0;
line700statement0[label="700.0"];
line700statement0 -> line710statement0;
line710statement0[label="710.0"];
line710statement0 -> line980statement0;
line710statement0 -> line1020statement0;
line720statement0[label="720.0"];
line720statement0 -> line730statement0;
line720statement0 -> line750statement0;
line730statement0[label="730.0"];
line730statement0 -> line740statement0;
line730statement0 -> line780statement0;
line740statement0[label="740.0"];
line740statement0 -> line740statement1;
line740statement1[label="740.1"];
line740statement1 -> line760statement0;
line750statement0[label="750.0"];
line750statement0 -> line760statement0;
line750statement0 -> line780statement0;
line760statement0[label="760.0"];
line760statement0 -> line770statement0;
line770statement0[label="770.0"];
line770statement0 -> line980statement0;
line770statement0 -> line1090statement0;
line780statement0[label="780.0"];
line780statement0 -> line980statement0;
line790statement0[label="790.0"];
line790statement0 -> line800statement0;
line790statement0 -> line880statement0;
line800statement0[label="800.0"];
line800statement0 -> line810statement0;
line800statement0 -> line880statement0;
line810statement0[label="810.0"];
line810statement0 -> line820statement0;
line810statement0 -> line840statement0;
line820statement0[label="820.0"];
line820statement0 -> line830statement0;
line820statement0 -> line870statement0;
line830statement0[label="830.0"];
line830statement0 -> line830statement1;
line830statement1[label="830.1"];
line830statement1 -> line990statement0;
line840statement0[label="840.0"];
line840statement0 -> line850statement0;
line840statement0 -> line870statement0;
line850statement0[label="850.0"];
line850statement0 -> line860statement0;
line860statement0[label="860.0"];
line860statement0 -> line1020statement0;
line860statement0 -> line1090statement0;
line870statement0[label="870.0"];
line870statement0 -> line1020statement0;
line880statement0[label="880.0"];
line880statement0 -> line890statement0;
line880statement0 -> line910statement0;
line890statement0[label="890.0"];
line890statement0 -> line900statement0;
line890statement0 -> line930statement0;
line900statement0[label="900.0"];
line900statement0 -> line900statement1;
line900statement1[label="900.1"];
line900statement1 -> line920statement0;
line910statement0[label="910.0"];
line910statement0 -> line920statement0;
line910statement0 -> line930statement0;
line920statement0[label="920.0"];
line920statement0 -> line1090statement0;
line930statement0[label="930.0"];
line930statement0 -> line1190statement0;
line940statement0[label="940.0"];
line940statement0 -> line950statement0;
line950statement0[label="950.0"];
line950statement0 -> line950statement1;
line950statement1[label="950.1"];
line950statement1 -> line950statement2;
line950statement2[label="950.2"];
line950statement2 -> line960statement0;
line960statement0[label="960.0"];
line960statement0 -> line970statement0;
line960statement0 -> line1200statement0;
line970statement0[label="970.0"];
line970statement0 -> line970statement1;
line970statement1[label="970.1"];
line970statement1 -> line270statement0;
line980statement0[label="980.0"];
line980statement0 -> line990statement0;
line990statement0[label="990.0"];
line990statement0 -> line1000statement0;
line1000statement0[label="1000.0"];
line1000statement0 -> line1000statement1;
line1000statement1[label="1000.1"];
line1000statement1 -> line1000statement2;
line1000statement2[label="1000.2"];
line1000statement2 -> line1010statement0;
line1000statement2 -> line1200statement0;
line1010statement0[label="1010.0"];
line1010statement0 -> line1010statement1;
line1010statement1[label="1010.1"];
line1010statement1 -> line270statement0;
line1020statement0[label="1020.0"];
line1020statement0 -> line1030statement0;
line1030statement0[label="1030.0"];
line1030statement0 -> line1030statement1;
line1030statement1[label="1030.1"];
line1030statement1 -> line1040statement0;
line1030statement1 -> line1050statement0;
line1040statement0[label="1040.0"];
line1040statement0 -> line1040statement1;
line1040statement1[label="1040.1"];
line1040statement1 -> line1060statement0;
line1050statement0[label="1050.0"];
line1050statement0 -> line1060statement0;
line1060statement0[label="1060.0"];
line1060statement0 -> line1070statement0;
line1070statement0[label="1070.0"];
line1070statement0 -> line1080statement0;
line1070statement0 -> line1200statement0;
line1080statement0[label="1080.0"];
line1080statement0 -> line600statement0;
line1090statement0[label="1090.0"];
line1090statement0 -> line1100statement0;
line1090statement0 -> line1150statement0;
line1100statement0[label="1100.0"];
line1100statement0 -> line1100statement1;
line1100statement1[label="1100.1"];
line1100statement1 -> line1100statement2;
line1100statement2[label="1100.2"];
line1100statement2 -> line1110statement0;
line1100statement2 -> line1120statement0;
line1110statement0[label="1110.0"];
line1110statement0 -> line1110statement1;
line1110statement1[label="1110.1"];
line1110statement1 -> line1130statement0;
line1120statement0[label="1120.0"];
line1120statement0 -> line1130statement0;
line1130statement0[label="1130.0"];
line1130statement0 -> line1130statement1;
line1130statement1[label="1130.1"];
line1130statement1 -> line1140statement0;
line1130statement1 -> line1200statement0;
line1140statement0[label="1140.0"];
line1140statement0 -> line270statement0;
line1150statement0[label="1150.0"];
line1150statement0 -> line1160statement0;
line1160statement0[label="1160.0"];
line1160statement0 -> line1170statement0;
line1160statement0 -> line1180statement0;
line1170statement0[label="1170.0"];
line1170statement0 -> line1170statement1;
line1170statement1[label="1170.1"];
line1170statement1 -> line1170statement2;
line1170statement2[label="1170.2"];
line1170statement2 -> line1190statement0;
line1180statement0[label="1180.0"];
line1180statement0 -> line1180statement1;
line1180statement1[label="1180.1"];
line1180statement1 -> line1180statement2;
line1180statement2[label="1180.2"];
line1180statement2 -> line1180statement3;
line1180statement3[label="1180.3"];
line1180statement3 -> line1180statement4;
line1180statement4[label="1180.4"];
line1180statement4 -> line260statement0;
line1190statement0[label="1190.0"];
line1190statement0 -> line210statement0;
line1200statement0[label="1200.0"];
line1200statement0 -> line1210statement0;
line1210statement0[label="1210.0"];
line1210statement0 -> line1220statement0;
line1220statement0[label="1220.0"];
line1220statement0 -> line1230statement0;
line1230statement0[label="1230.0"];
line1230statement0 -> line1240statement0;
line1230statement0 -> line1260statement0;
line1240statement0[label="1240.0"];
line1240statement0 -> line1250statement0;
line1250statement0[label="1250.0"];
line1250statement0 -> line1270statement0;
line1260statement0[label="1260.0"];
line1260statement0 -> line1270statement0;
line1270statement0[label="1270.0"];
line1270statement0 -> line1280statement0;
line1270statement0 -> line1230statement0;
line1280statement0[label="1280.0"];
line1280statement0 -> line1290statement0;
line1290statement0[label="1290.0"];
line1290statement0 -> line1300statement0;
line1300statement0[label="1300.0"];
line1300statement0 -> line1310statement0;
line1300statement0 -> line1340statement0;
line1310statement0[label="1310.0"];
line1310statement0 -> line1320statement0;
line1310statement0 -> line1340statement0;
line1320statement0[label="1320.0"];
line1320statement0 -> line1330statement0;
line1330statement0[label="1330.0"];
line1330statement0 -> line1350statement0;
line1340statement0[label="1340.0"];
line1340statement0 -> line1350statement0;
line1350statement0[label="1350.0"];
line1350statement0 -> line1360statement0;
line1350statement0 -> line1300statement0;
line1360statement0[label="1360.0"];
line1360statement0 -> line1370statement0;
line1370statement0[label="1370.0"];
line1370statement0 -> line1380statement0;
line1370statement0 -> line1210statement0;
line1380statement0[label="1380.0"];
*/
