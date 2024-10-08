package mvc.controleur.dragdrop.liste;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import mvc.modele.ModeleOllert;

/**
 * Contrôleur pour le relâchement du drag d'une liste
 */
public class ControleurRelacherDragListe implements EventHandler<DragEvent> {
	/**
	 * Modele de l'application
	 */
	private final ModeleOllert modeleControle;

	/**
	 * Constructeur du contrôleur
	 *
	 * @param modeleControle Modele de l'application
	 */
	public ControleurRelacherDragListe(ModeleOllert modeleControle) {
		this.modeleControle = modeleControle;
	}

	/**
	 * Gère le relâchement du drag d'une liste
	 *
	 * @param dragEvent Événement de souris (déposer = relâchement du clic)
	 */
	@Override
	public void handle(DragEvent dragEvent) {
		modeleControle.setDraggedListe(null);
	}
}
