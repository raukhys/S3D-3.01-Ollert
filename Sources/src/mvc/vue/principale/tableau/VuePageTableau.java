package mvc.vue.principale.tableau;

import fabrique.FabriqueVueTableau;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.structure.VuePage;
import ollert.donnee.ListeTaches;
import ollert.donnee.Page;
import ollert.tool.Sauvegarde;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de la vue représentant une page sous forme de tableau
 */
public class VuePageTableau extends HBox implements VuePage {

	/**
	 * Constructeur de la classe VuePageTableau
	 */
	public VuePageTableau() {
		this.setStyle("-fx-background-color: #eee0cb; -fx-padding: 20px;");
	}

	/**
	 * Actualise la vue
	 *
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {
		ModeleOllert modele = (ModeleOllert) sujet;
		HBox centre = this.getChildrenPrincipale();

		// si une tache est en deplacement on actualise simplement le contenu
		if (modele.getDraggedTache() != null) {
			for (Node n : centre.getChildren()) {
				((VueListeTableau) n).actualiser(modele);
			}
			return;
		}

		// si on modifie les antécédant d'une tache on change le style
		if (modele.getListeAnt() != null) {
			centre.setStyle("-fx-padding: 10px;-fx-spacing: 20px; -fx-border-color: #d54461; -fx-border-width: 2px;");
		} else {
			centre.setStyle("-fx-padding: 10px;-fx-spacing: 20px;");

		}

		// on supprime et recreer les lites de taches
		centre.getChildren().clear();

		Page page = modele.getDonnee();
		List<ListeTaches> liste = page.getListes();

		for (ListeTaches l : liste) {
			VueListeTableau vl_tmp = new FabriqueVueTableau(modele).creerVueListe();
			centre.getChildren().add(vl_tmp);
			vl_tmp.actualiser(modele);
		}


		if (page.getTitre() != "defaut") {
			Sauvegarde.sauvegarderPage(page);
		}

	}

	@Override
	public List<Integer> getLocalisation() {
		return new ArrayList<>();
	}

	@Override
	public HBox getChildrenPrincipale() {
		return (HBox) ((ScrollPane) this.getChildren().get(0)).getContent();
	}

	@Override
	public Node getParentPrincipale() {
		return null;
	}
}
