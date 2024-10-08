package mvc.controleur.tacheComplete;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import mvc.modele.ModeleOllert;

import static ollert.donnee.tache.attribut.Priorite.*;

/**
 * Classe ControleurArchiver qui permet d'archiver une tâche
 */
public class ControleurPriorite implements EventHandler<ActionEvent> {

	/**
	 * Constructeur de la classe ControleurModification
	 */
	private final ModeleOllert modele;

	/**
	 * Constructeur de la classe ControleurModification
	 *
	 * @param modele Modele de l'application
	 */
	public ControleurPriorite(ModeleOllert modele) {
		this.modele = modele;
	}

	/**
	 * Gère l'association d'une priorité à une tâche
	 *
	 * @param actionEvent action de l'utilisateur (clic sur le bouton)
	 */
	@Override
	public void handle(ActionEvent actionEvent) {
		switch (((Button) actionEvent.getSource()).getText()) {
			case "Faible":
				modele.getTacheComplete().setPriorite(FAIBLE);
				break;
			case "Moyenne":
				modele.getTacheComplete().setPriorite(MOYENNE);
				break;
			case "Elevée":
				modele.getTacheComplete().setPriorite(ELEVEE);
				break;
		}
		modele.notifierObservateurs();
	}
}
