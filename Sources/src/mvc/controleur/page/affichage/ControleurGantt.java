package mvc.controleur.page.affichage;

import fabrique.FabriqueVueGantt;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import mvc.modele.ModeleOllert;
import mvc.vue.structure.VuePage;

/**
 * Contrôleur pour l'affichage Gantt
 */
public class ControleurGantt implements EventHandler<ActionEvent> {
	/**
	 * Modele de l'application
	 */
	private final ModeleOllert modele;

	/**
	 * Constructeur du contrôleur
	 *
	 * @param modele Modele de l'application
	 */
	public ControleurGantt(ModeleOllert modele) {
		this.modele = modele;
	}

	/**
	 * Change l'affichage Gantt au click du bouton Gantt
	 *
	 * @param event l'événement utilisateur
	 */
	@Override
	public void handle(ActionEvent event) {
		modele.setTacheCible(null);
		modele.setListeAnt(null);

		modele.setFabrique(new FabriqueVueGantt(this.modele));
		Button src = (Button) event.getSource();
		BorderPane racine = (BorderPane) src.getParent().getParent();

		// TODO le controleur ne devrait pas avoir à faire ça ?
		// FIXME les pages sont ajoutées a l'infini sans jamais etre supprimée (temporairement corrigé dans setFabrique)
		VuePage vp = modele.getFabrique().creerVuePage();
		racine.setCenter((Node) vp);
		modele.ajouterObservateur(vp);
		modele.notifierObservateurs();
	}
}
