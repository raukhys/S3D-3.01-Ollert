package mvc.vue.liste;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.Observateur;
import mvc.vue.liste.contenu.VueTitreListe;
import mvc.vue.tache.VueTache;
import ollert.tache.ListeTaches;
import ollert.tache.Tache;
import ollert.tache.TachePrincipale;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de la vue représentant une liste de tâches sous forme de tableau
 */
public class VueListeTableau extends VBox implements VueListe {
	/**
	 * Liste des observateurs (les vues des tâches de la liste (VueTacheTableau))
	 */
	private List<Observateur> observateurs;
	/**
	 * Liste de tâches réelle que représente la vue
	 */
	private ListeTaches liste;

	/**
	 * Constructeur de la classe VueListeTableau
	 * @param liste Liste de tâches réelle que représente la vue
	 */
	public VueListeTableau(ListeTaches liste) {
		this.observateurs = new ArrayList<>();
		this.liste = liste;

		this.setStyle("-fx-background-color: blue;");

		// header listeTaches
		HBox header = new HBox();
		header.setStyle("-fx-background-color: red; -fx-padding: 10px;");
			VueTitreListe vtl = new VueTitreListe();
			this.observateurs.add(vtl);

			Button btn_modif = new Button("Modif");
			Button btn_deplacer = new Button("Deplacer");
			Button btn_ajouter = new Button("Ajouter");

			header.getChildren().addAll(vtl, btn_modif, btn_deplacer, btn_ajouter);
		this.getChildren().add(header);


		//footer listeTaches
		HBox footer = new HBox();
		footer.setStyle("-fx-background-color: green; -fx-padding: 10px;");


			footer.getChildren().addAll();
		this.getChildren().add(footer);
	}

	/**
	 * Ajoute un observateur à la liste des observateurs
	 * @param observateur L'observateur à ajouter
	 */
	@Override
	public void ajouterObservateur(Observateur observateur) {
		this.observateurs.add(observateur);
	}

	/**
	 * Supprime un observateur de la liste des observateurs
	 * @param observateur L'observateur à supprimer
	 */
	@Override
	public void supprimerObservateur(Observateur observateur) {
		this.observateurs.remove(observateur);
	}

	/**
	 * Notifie les observateurs de la liste des observateurs
	 */
	@Override
	public void notifierObservateurs() {
		for (Observateur observateur : this.observateurs)
			observateur.actualiser(this);
	}

	/**
	 * Actualise la vue courante
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {
		ModeleOllert modele = (ModeleOllert) sujet;

		HBox footer = (HBox) this.getChildren().get(this.getChildren().size()-1);
		this.getChildren().remove(footer);

		// LE PREMIER CHILDREN EST LE TITRE DE LA TACHE ET LE DERNIER EST LE BOUTON AJOUTER/SUPPRIMER
		for (int i=0; i<this.liste.sizeTaches(); i++) {
			TachePrincipale l = this.liste.getTache(i);
			// si tache existe deja
			if (this.getChildren().contains(l)) {
				int indice = this.getChildren().indexOf(l);
				VueTache vt = (VueTache) this.getChildren().get(indice);
				// si modification de la tache
				if (!vt.getTache().equals(l)) {
					VueTache vl_tmp = modele.getFabrique().creerVueTache(l);
					this.getChildren().add(i, (Node) vl_tmp);
				}
				vt.actualiser(modele);

			// nouvelle tache a afficher
			} else {
				VueTache vl_tmp = modele.getFabrique().creerVueTache(l);
				this.getChildren().add((Node) vl_tmp);
				vl_tmp.actualiser(modele);
			}
		}
		this.notifierObservateurs();

		this.getChildren().add(footer);
	}

	/**
	 * @return La liste de tâches réelle que représente la vue
	 */
	public ListeTaches getListe() {
		return this.liste;
	}
}
