package br.com.mateus;

public interface LoopSteps {
	/*
	 * Ocorre apenas uma vez, antes do loop principal do jogo.
	 */
	void setup();
	/*
	 * Este método altera os estados de todos os objetos do jogo.
	 * O jogo será processado e será devolvido qual a próxima tela que deverá ser pintada.
	 */
	void processLogics();
	/*
	 * Faz a renderização da próxima tela no buffer, para a próxima utilização.
	 */
	void renderGraphics();
	/*
	 * Pinta o gráfico renderizado na tela.
	 */
	void paintScreen();
	/*
	 * É chamado quando o loop do jogo termina.
	 */
	void tearDown();
}
