/**
 * 
 */
/**
 * 
 */
module ByteBrew {
	  requires java.sql; // Permite usar Connection, DriverManager, etc.
	    
	    // Exporta os pacotes para que o Java possa usá-los
	    exports conexao;
	    exports br.edu.cafeteria.app;
	    exports br.edu.cafeteria.modelo.cliente;
}