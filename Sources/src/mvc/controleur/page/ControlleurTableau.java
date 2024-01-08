package mvc.controleur.page;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import mvc.fabrique.FabriqueVueTableau;
import mvc.fabrique.FabriqueVueTableur;
import mvc.modele.ModeleOllert;
import mvc.vue.page.VuePage;
import mvc.vue.page.VuePageTableau;
import mvc.vue.page.VuePageTableur;

public class ControlleurTableau implements EventHandler<ActionEvent> {
	private ModeleOllert modele;

	public ControlleurTableau(ModeleOllert modele) {
		this.modele = modele;
	}

	@Override
	public void handle(ActionEvent event) {
		modele.setFabrique(new FabriqueVueTableau());
		Button src = (Button) event.getSource();
		GridPane racine = (GridPane) src.getParent().getParent().getParent();
		racine.getChildren().clear();
		VuePage vp = modele.getFabrique().creerVuePage(modele);
		racine.getChildren().add((Node) vp);
		modele.ajouterObservateur(vp);
		modele.notifierObservateurs();
	}
}
