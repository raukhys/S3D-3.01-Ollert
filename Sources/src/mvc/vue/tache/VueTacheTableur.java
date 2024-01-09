package mvc.vue.tache;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mvc.controleur.tache.ControlleurModification;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.liste.VueListe;
import mvc.vue.liste.VueListeTableur;
import mvc.vue.page.VuePageTableur;
import mvc.vue.tache.contenu.*;
import ollert.Page;
import ollert.tache.Tache;
import ollert.tache.TachePrincipale;
import ollert.tache.donneesTache.Etiquette;
import ollert.tache.donneesTache.Utilisateur;

import java.util.List;

/**
 * Classe de la vue représentant une tâche sous forme de tableau
 * La vue est à la fois modèle (pour actualiser le contenu) et observateur (lors de la modification de son titre)
 */
public class VueTacheTableur extends HBox implements VueTache {

	/**
	 * Constructeur de la classe VueTacheTableau
	 */
	public VueTacheTableur(ModeleOllert modeleControle) {

		this.setOnMouseClicked(new ControlleurModification(modeleControle));


		//this.setStyle("-fx-background-color: green;");

			HBox titre = new HBox();
				Label l1 = new Label("1");
			titre.getChildren().addAll(l1);
			titre.setMinWidth(280);
			titre.setMaxWidth(280);
			titre.setStyle("-fx-border-style: solid; -fx-border-color: black; -fx-border-width: 1 1 1 1; -fx-padding: 10;");


			Label l2 = new Label("2");
			l2.setMinWidth(200);
			l2.setMaxWidth(200);
			l2.setStyle("-fx-border-style: solid; -fx-border-color: black; -fx-border-width: 1 1 1 1; -fx-padding: 10;");

			Label l3 = new Label("3");
			l3.setMinWidth(200);
			l3.setMaxWidth(200);
			l3.setStyle("-fx-border-style: solid; -fx-border-color: black; -fx-border-width: 1 1 1 1; -fx-padding: 10;");

			Label l4 = new Label("4");
			l4.setMinWidth(200);
			l4.setMaxWidth(200);
			l4.setStyle("-fx-border-style: solid; -fx-border-color: black; -fx-border-width: 1 1 1 1; -fx-padding: 10;");

			Label l5 = new Label("5");
			l5.setMinWidth(200);
			l5.setMaxWidth(200);
			l5.setStyle("-fx-border-style: solid; -fx-border-color: black; -fx-border-width: 1 1 1 1; -fx-padding: 10;");

			Label l6 = new Label("6");
			l6.setMinWidth(200);
			l6.setMaxWidth(200);
			l6.setStyle("-fx-border-style: solid; -fx-border-color: black; -fx-border-width: 1 1 1 1; -fx-padding: 10;");

		this.getChildren().addAll(titre, l2, l3, l4, l5, l6);
	}

	/**
	 * Actualise la vue courante
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {
		ModeleOllert modele = (ModeleOllert) sujet;

		VueListe vl = (VueListe)this.getParent().getParent();
		VBox vbox = (VBox) vl.getChildren().get(1);
		int indice = vbox.getChildren().indexOf(this);


		VBox vp = (VBox)this.getParent().getParent().getParent();
		int indiceListe = vp.getChildren().indexOf(vl);

		Tache t = modele.getDonnee().getListes().get(indiceListe).getTaches().get(indice);

		HBox h1 = (HBox) this.getChildren().get(0);
		Label l1 = (Label) h1.getChildren().get(0);
		l1.setText(t.getTitre());

		Label l2 = (Label) this.getChildren().get(1);
		l2.setText("XX-XX-XXXX");
		if (t.getDateDebut() != null){
			l2.setText(t.getDateDebut().toString());
		}

		Label l3 = (Label) this.getChildren().get(2);
		l3.setText("XX-XX-XXXX");
		if (t.getDateFin() != null){
			l3.setText(t.getDateFin().toString());
		}

		Label l4 = (Label) this.getChildren().get(3);
		if (t.getMembres() != null){
			String chaine = "";
			for (Object o : t.getMembres()){
				Utilisateur u = (Utilisateur) o;
				chaine += "\""+u.getPseudo()+"\" ";
			}
			l4.setText(chaine);
		}

		Label l5 = (Label) this.getChildren().get(4);
		if (t.getTags() != null){
			String chaine = "";
			for (Object o : t.getTags()){
				Etiquette e = (Etiquette) o;
				chaine += "\""+e.getValeur()+"\" ";
			}
			l5.setText(chaine);
		}

		Label l6 = (Label) this.getChildren().get(5);
		l6.setText(t.getPriorite().toString());

	}

	/**
	 * @return tache réelle que représente la vue
	 */
	public TachePrincipale getTache() {
		return null;
	}

	@Override
	public List<Integer> getLocalisation() {
		return null;
	}

	public Node getParentPrincipale() {
		return null;
	}

	public Node getChildrenPrincipale() {
		return null;
	}
}
