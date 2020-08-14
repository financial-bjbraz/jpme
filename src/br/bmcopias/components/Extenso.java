package br.bmcopias.components;

/**
 * Title:        Swing Utilities
 * Description:  Tratamento de valores por extenso
 * Copyright:    Copyright (c) 2001
 * Company:      MSANTUNES Informática
 * @author       MSANTUNES Team
 * @version      1.0
 */

public class Extenso
{
  static int[] desc_num = 
		{  10,
		   20,
		   30,
		   40,
		   50,
		   60,
		   70,
		   80,
		   90,
		  100,
		  110,
		  120,
		  130,
		  140,
		  150,
		  160,
		  170,
		  180,
		  190,
		  200,
		  300,
		  400,
		  500,
		  600,
		  700,
		  800,
		  900,
		 1000,
		 1001,
		 2000,
		 3000,
		 4000,
		 5000,
		 6000,
		 7000,
		 8000,
		 9000,
		   -1	};

	static String[] desc_val =

		{
		   "UM",
		   "DOIS",
		   "TRES",
		   "QUATRO",
		   "CINCO",
		   "SEIS",
		   "SETE",
		   "OITO",
		   "NOVE",
		   "DEZ",
		   "ONZE",
		   "DOZE",
		   "TREZE",
		   "QUATORZE",
		   "QUINZE",
		   "DEZESEIS",
		   "DEZESSETE",
		   "DEZOITO",
		   "DEZENOVE",
		   "VINTE",
		   "TRINTA",
		   "QUARENTA",
		   "CINQUENTA",
		   "SESSENTA",
		   "SETENTA",
		   "OITENTA",
		   "NOVENTA",
		   "CEM",
		   "CENTO",
		   "DUZENTOS",
		   "TREZENTOS",
		   "QUATROCENTOS",
		   "QUINHENTOS",
		   "SEISCENTOS",
		   "SETECENTOS",
		   "OITOCENTOS",
		   "NOVECENTOS",
		   ""		};
	
	
	
	static String[] sing =
		{
		"MIL",
		"MILHAO",
		"BILHAO" };
	
	static String[] plur =
		{
		 "MIL",
		 "MILHOES",
		 "BILHOES" };
	
	
	
	static String[] moeda = {	"REAL",
				"REAIS"	};
	
	static String[] cents = {	"CENTAVO",
				"CENTAVOS"	};
	
	
	
	static	String apend = "E ";
	
	 static	String vbde = "DE ";
	
	 static	boolean	c_port = false;
	
	 static	char	uni_mil = 1;

	 static char[] vbarli = new char[500];
	 
	/**
	 * Converte o valor para extenso.
	 * @valor valor a ser convertido
	 * @largura largura da primeira linha, se diferente de zero será feita a divisão silábica
	 * @return retorna um string contendo o valor por extenso. Se largura diferente de zero,
	 * então será feita a divisão silábica na posição <b>largura</b>.
	 */
	static public String formatar(double valor, int largura)
	{
		return formatar(valor, largura, ' ');
	}
	
	static public String formatar(double valor, int largura, char fill)
	{
		double num = Math.round(valor * 100);
		StringBuffer auxb = new StringBuffer();
		StringBuffer buff = new StringBuffer();
		int auxc1 = 1;
		boolean auxc2;
		int auxi1;
		int auxi2;
		
		double auxd1;
		
		/* AUXC2 indica se deve ou nao escrever "DE" para valores como: um */
		/* milhao "DE" escudos                                             */
	
		auxc2 = (((num % 100000000) < 99.9) ? false : true);
	
		/* multiplica NUM por 10 para passar a casa dos centavos de centena */
		/* para milhar para a divisao do numero sair correta                */
	
		num *= 10;

		/* inicializa AUXD1 com 1 bilhao ;                                   */
		/* enquanto AUXD1 nao estiver na casa das unidades ;                 */
		/* num passa a ser o resto da divisao de num por auxd1 e divide auxd1*/
		/* por 1000 para passar para a unidade inferior                      */
		
		for (auxd1 = 1000000000; auxd1 > 1.1; num %= auxd1, auxd1 /= 1000)
		{
			/* se existe numero nesta unidade */
			
			if ((auxi2 = (int)(num / auxd1)) != 0)
			{
				auxb.setLength(0);
	
				/* se nao existe a centena ou nao existe a dezena e  */
				/* a unidade                                         */
	
				if ((auxi2 % 100) != 0 || (auxi2 / 100) != 0)
	
					/* se ja escreveu alguma coisa em BUFF e as  */
					/* demais unidades so possuem digitos zeros  */
	
					if (buff.length() > 0 && (num % auxd1) < 999.9)
	
						/* apenda ao buffer "E " para o caso:*/
						/* mil "E" cem escudos               */

	          auxb.append(apend);
	
	
				/* multiplica a centena por 10 para poder procurar   */
				/* na tabela NUM_DESC                                */
	
				auxi2 *= 10;
	
	
	
				/* escreve a centena */
	
				esc_cen(auxi2, auxb);
	
	
				/* coloca em AUXI1 o valor do indice para a tabela */
				/* TAB_UNI que contem a descricao desta unidade    */
													
				switch ((int)auxd1)
				{
					case 1000000000: auxi1 = 2;
							 break;
					case 1000000:	 auxi1 = 1;
							 break;
					default:         auxi1 = 0;
				}
					
				/* se a unidade eh maior ou igual a unidade dos mi- */
				/* lhares                                           */
	
				if ((--auxi1) >= 0)
				{
					/* se nao for para escrever "UM" na unidade */
					/* dos milhares e eh unidade dos milhares   */
					/* e eh UM e eh a primeira string a ser es- */
					/* crita no buffer de resposta              */
	
					if (uni_mil == 0 && auxi1 == 0 && auxi2 == 10 && buff.length() == 0)
	
						/* escreve apenas MIL e nao UM MIL  */
	
						auxb.append(sing[auxi1]);
					else
						/* se for maior que UM escreve o plu-*/
						/* ral senao escreve o sigular da    */
						/* unidade                           */

	          auxb.append((auxi2 == 10) ? sing[auxi1] : plur[auxi1]);
	
	
					/* coloca o branco para separar da proxima */
					/* srting a ser colocada em BUFF           */

	        auxb.append(" ");
				}
	
	
				/* se numero menor que 2 e nao foi escrito nada no */
				/* buffer                                          */
	
				if (num < 1999.9 && buff.length() == 0)
	
					/* indica que eh para escrever a moeda em  */
					/* singular                                */
	
					auxc1 = 0;
	
	
				/* concatena no buffer a centena escrita */
	
				buff.append(auxb.toString());
			}

		}
	
	
		/* se alguma coisa foi escrita em BUFF */
	
		if (buff.length() > 0)
		{
			/* se eh para colocar a palavra "DE " */
	
			if (!auxc2)
	
				/* concatena "DE " em BUFF */
	
				buff.append(vbde);
	
	
			/* concatena a moeda em BUFF */

	    buff.append(moeda[auxc1]);
		}
	
	
		/* se o numero possui centavos */
	
		if (num > 1.1)
		{
			/* se foi escrito alguma coisa em buff */
	
			if(buff.length() > 0)
			{
				/* concatena o separador */
	
				buff.append(" ");
	
	
				/* concatena a palavra "E " */
	
				buff.append(apend);
			}
	
	
			/* escreve os centavos */
	
			esc_cen((int)(num), buff);
	
	
			/* concatena em buff o nome dos centavos no singular ou plu- */
			/* ral                                                       */
	
			buff.append(cents[(num < 19.9) ? 0 : 1]);
		}

	  if (largura > 0)
		  sep_sil(buff, largura, fill);
	
		return buff.toString();
	}

	static private void esc_cen(int num, StringBuffer buff)
	{
	  int	auxc;			    /* indica se o numero a ser escrito	       */
											/* eh igual ao numero na tabela DES_NUM    */
	  int	auxi1,			  /* contador para pegar todos ous nu- 	     */
					            /* meros da centena                  	     */
				auxi2;			  /* contador na tabela DES_NUM       	     */
	
	  boolean first = true;
	
		/* precorre toda a centena comecando pela numero 100 */
	
		for(auxi1 = 1000 ; auxi1 > 1 ; num %= auxi1, auxi1 /= 10)
			{
			/* se numero eh maior que a unidade atual */
	
			if ((num / auxi1) != 0)
				{
				/* procura o numero na tabela DES_NUM */
	
				for(auxi2 = 0 ; (desc_num[auxi2] >= 0) && (num > desc_num[auxi2]) ; auxi2++)
					;
	
	
				/* se ja escreveu alguma coisa em BUFF */
	
				if(!first)
	
					/* concatena "E " */
	
					buff.append(apend);
	
	
				/* auxc indica se o numero eh diferente do achado */
				/* na tabela                                      */
	
				auxc = (num != desc_num[auxi2]) ? 1 : 0;
	
	
				/* concatena a descricao do numero em BUFF */
	
				buff.append(desc_val[auxi2 - auxc]);
	
	
				/* coloca o separador */
	
				buff.append(" ");
				
				/* indica que nao e a primeira vez*/
				
				first = false;
	
	
				/* se o numero eh igual ao numero da tabela */
	
				if(auxc == 0)
					return;
				}
			}
	 }

	static int sep_sil(StringBuffer buff, int tam, char fill)
	{
	  int auxp1,		/* aponta para o buffer inicial (sem separacao)      */
				auxp2,		/* aponta para o buffer que repersenta a linha a ser */
				          /* separada                  			     */
				auxp3;		/* aponta para o local onde deve ocorrer a separacao */
	
		int  auxi1,		/* auxiliar geral				     */
	       auxi2;		/* indica quantas linhas foram escritas		     */
	
	
		for (int i = 0; i < vbarli.length; ++i)
		  vbarli[i] = ' ';
	
		/* auxi contem o tamanho do buffer */
	
		auxi1 = buff.length();
	
	
		/* auxp1 aponta para o espaco livre */
	
		auxp1 = 0;
	
		/* verifica se tem caracter de preenchimento */
		if (fill != ' ')
		  vbarli[auxp1++] = fill;
	
		/* auxp2 aponta para a area posterior a area apontada por auxp1 */
	
		auxp2 = auxp1 + auxi1;
	
	
		/* move para auxp1 o buffer a ser dividido */

		buff.getChars(0, auxi1, vbarli, auxp1);
		buff.setLength(0);
	
		auxp1 = 0;
		
		/* faz a divisao do buffer em linhas */
	
		for(auxi2 = 0 ; auxp1 < auxp2 ; auxp1 += tam, auxi2++)
		{
			/* auxi1 contem o tamanho da linha a ser dividida */
			auxi1 = Math.min(tam, auxp2 - auxp1);

			/* copia a linha para auxp2 */
			System.arraycopy(vbarli, auxp1, vbarli, auxp2, auxi1);

			/* limpa o resto da linha */
			for (int i = auxi1; i < tam; ++i)
			  vbarli[auxp1 + auxi1 + i] = fill;
	
			/* se o proximo caractere eh branco */
			if(vbarli[auxp1 + auxi1] == ' ')
				/* incrementa o contador */
				auxp1++;
			else
			/* se o ultimo caractere nao eh branco e a linha eh  */
			/* menor que o tamanho que resta no buffer           */
			if((auxi1 < (auxp2 - auxp1)) && (vbarli[auxp1 + auxi1 - 1] != ' '))
			{
				/* auxp3 passa a apontar para o final da li- */
				/* nha                                       */

				auxp3 = auxp2 + auxi1 - 1;

				/* se eh para fazer a separacao silabica ou  */
				/* caracter for uma das letras abaixo e ca-  */
				/* ractere anterior nao for branco e nao     */
				/* chegou no final do buffer                 */

				while( ( c_port ||
					  vbarli[auxp3] == 'A' || vbarli[auxp3] == 'E' ||
						vbarli[auxp3] == 'H' || vbarli[auxp3] == 'I' ||
						vbarli[auxp3] == 'L' || vbarli[auxp3] == 'M' ||
						vbarli[auxp3] == 'N' || vbarli[auxp3] == 'O' ||
						vbarli[auxp3] == 'Q' || vbarli[auxp3] == 'R' ||
						vbarli[auxp3] == 'S' || vbarli[auxp3] == 'U' ) &&
					  vbarli[auxp3 - 1] != ' ' && auxp3 > auxp2)
						auxp3 --;


				/* se nao for o final do buffer */

				if(auxp3 > auxp2)
				{
					/* subtrai do tamanho da linha o nu- */
					/* mero de caracteres que ficarao na */
					/* proxima linha                     */

					auxi1 -= auxp3 - auxp2;

					/* limpa o final do buffer */

					for (int i = 0; i < auxi1; ++i)
					  vbarli[auxp3 + i] = ' ';


					/* se meio da palavra */

					if(vbarli[auxp3 - 1] != ' ')

						/* coloca o '-'	*/

						vbarli[auxp3] = '-';


					/* subtrai do apontador os caracteres*/
					/* que ficarao na proxima linha      */

					auxp1 -= auxi1;
				}
			}
	
			/* copia o buffer separado para BUFF */

			buff.append(vbarli, auxp2, tam);
		}
	
		/* retorna o numero de linhas utilizadas */
		return(auxi2);
	 }

	public static void main(String[] args)
	{
		System.out.println(Extenso.formatar(120.29, 100, '*'));
	}

}
