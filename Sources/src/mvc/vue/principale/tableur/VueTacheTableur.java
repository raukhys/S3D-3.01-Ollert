package mvc.vue.principale.tableur;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mvc.modele.ModeleOllert;
import mvc.modele.Sujet;
import mvc.vue.structure.VueListe;
import mvc.vue.structure.VueTache;
import ollert.donnee.tache.Tache;
import ollert.donnee.tache.attribut.Etiquette;
import ollert.donnee.tache.attribut.Utilisateur;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de la vue représentant une tâche sous forme de tableau
 * La vue est à la fois modèle (pour actualiser le contenu) et observateur (lors de la modification de son titre)
 */
public class VueTacheTableur extends VBox implements VueTache {

	/**
	 * Constructeur de la classe VueTacheTableau
	 */
	public VueTacheTableur() {
	}

	/**
	 * Actualise la vue courante
	 *
	 * @param sujet le modèle à partir duquel la vue est actualisée
	 */
	@Override
	public void actualiser(Sujet sujet) {
		ModeleOllert modele = (ModeleOllert) sujet;

		VueListe vl = (VueListe) this.getParent().getParent();
		VBox vbox = (VBox) vl.getChildren().get(1);
		int indice = vbox.getChildren().indexOf(this);


		VBox vp = (VBox) this.getParent().getParent().getParent();
		int indiceListe = vp.getChildren().indexOf(vl);

		Tache t = modele.getDonnee().getListes().get(indiceListe).getTaches().get(indice);

		HBox tache = (HBox) this.getChildren().get(0);

		HBox h1 = (HBox) tache.getChildren().get(0);
		Label l1 = (Label) h1.getChildren().get(0);
		l1.setText(t.getTitre());

		Label l2 = (Label) tache.getChildren().get(1);
		l2.setText("XX-XX-XXXX");
		if (t.getDateDebut() != null) {
			l2.setText(t.getDateDebut().toString());
		}

		Label l3 = (Label) tache.getChildren().get(2);
		l3.setText("XX-XX-XXXX");
		if (t.getDateFin() != null) {
			l3.setText(t.getDateFin().toString());
		}

		Label l4 = (Label) tache.getChildren().get(3);
		if (t.getMembres() != null) {
			String chaine = "";
			for (Object o : t.getMembres()) {
				Utilisateur u = (Utilisateur) o;
				chaine += "\"" + u.getPseudo() + "\" ";
			}
			l4.setText(chaine);
		}

		Label l5 = (Label) tache.getChildren().get(4);
		if (t.getTags() != null) {
			String chaine = "";
			for (Object o : t.getTags()) {
				Etiquette e = (Etiquette) o;
				chaine += "\"" + e.getValeur() + "\" ";
			}
			l5.setText(chaine);
		}

		Label l6 = (Label) tache.getChildren().get(5);
		l6.setText(t.getPriorite().toString());

	}

	@Override
	public List<Integer> getLocalisation() {
		ArrayList<Integer> loc = new ArrayList<>();
		loc.add(0, this.getParent().getChildrenUnmodifiable().indexOf(this));
		loc.addAll(0, this.getParentPrincipale().getLocalisation());
		return loc;
	}

	@Override
	public VueListeTableur getParentPrincipale() {
		return (VueListeTableur) this.getParent().getParent();
	}

	@Override
	public Node getChildrenPrincipale() {
		return null;
	}
}
