package mp9.nf2.udp.unicast.joc;

public class SecretNum {
	/* Classe que genera números aleatoris per jugar a endivinar-los i els comprova
	 * amb el mètode comprova que retorna 0 si és encertat, 1 si és el número a buscar és més petit,
	 * i 2 si el número buscat és més gran.
	 */
	
	private int num;
	
	public SecretNum() {
		num = 0;
	}
	
	public SecretNum(int n) {
		pensa(n);
	}

	/**
	 * Genera un número aleatori de 0 fins a max
	 * @param max
	 */
	public void pensa(int max) {
		 setNum((int) ((Math.random()*max)+1));
		 //System.out.println("He pensat el " + getNum());
	}

	/**
	 *
	 * @param n número a comprovar
	 * @return 0 si és encertat, 1 si és el número a buscar és més petit,
	 * 	i 2 si el número buscat és més gran
	 */
	public int comprova(int n) {
		if(num == n) return 0;
		else if(num < n) return 1;
		else return 2;
	}


	public int getNum() {
		return num;
	}

	private void setNum(int num) {
		this.num = num;
	}
	

}
