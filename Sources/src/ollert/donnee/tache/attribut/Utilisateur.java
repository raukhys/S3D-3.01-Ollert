package ollert.donnee.tache.attribut;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

// TODO réfléchir à la conception d'étiquette et d'utilisateur (même méthodes/attributs)

/**
 * Classe représentant un utilisateur présent dans une ou plusieurs tâches.
 */
public class Utilisateur implements Serializable{

	/**
	 * Map contenant la liste des utilisateurs présentent dans une page
	 *
	 * @key : String correspond au titre de la page qui est unique
	 * @value : ArrayList<Utilisateur>> correspond à la liste des utilisateurs présents dans une page
	 */
	private static Map<String, ArrayList<Utilisateur>> utilisateurs = new HashMap<String, ArrayList<Utilisateur>>();
	/**
	 * Représente le nombre d'utilisations de cet utilisateur
	 */
	private int nbUse;
	/**
	 * Représente le nom de cet utilisateur
	 */
	private String pseudo;

	/**
	 * Représente l'icône de cet utilisateur
	 */
	private byte[] icone;

	/**
	 * Création d'un Utilisateur
	 *
	 * @param nom pseudo de ce nouvel utilisateur
	 * @throws NullPointerException si le nom est nul
	 */
	private Utilisateur(String nom) {
		if (nom == null) throw new NullPointerException("Le pseudo de l'utilisateur ne peut pas être null");
		this.pseudo = nom;
		this.nbUse = 0;

        try {
            this.icone = genererIconeMembreParDefaut();
        } catch (IOException e) {
			System.err.println("Erreur lors de la génération de l'icône par défaut de l'utilisateur " + this.pseudo);
        }
    }



	/**
	 * @param o Objet de la comparaison
	 * @return True si les deux objets sont les mêmes, False sinon
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Utilisateur that = (Utilisateur) o;
		return Objects.equals(pseudo, that.pseudo);
	}

	/**
	 * Obtenir Utilisateur renvoie l'utilisateur correspondant à la page passée en paramètre et au nom de l'étiquette
	 * Une page est créée si la page passée en paramètre n'existe pas
	 * Un utilisateur est créé si l'utilisateur n'existe pas dans la page recherchée
	 *
	 * @param nomPage        correspond au nom de la page sur laquelle on cherche l'utilisateur
	 * @param nomUtilisateur valeur de l'utilisateur recherché
	 * @return l'utilisateur recherché
	 */
	public static Utilisateur obtenirUtilisateur(String nomPage, String nomUtilisateur) {
		ArrayList<Utilisateur> list = utilisateurs.get(nomPage);
		if (list == null) {
			list = new ArrayList<>();
			utilisateurs.put(nomPage, list);
		}
		if (nomUtilisateur == null) throw new NullPointerException("Le nom d'utilisateur ne peut pas être vide.");
		Utilisateur e = new Utilisateur(nomUtilisateur);
		if (list.contains(e)) {
			list.get(list.indexOf(e)).nbUse++;
			return list.get(list.indexOf(e));
		} else {
			e.nbUse++;
			list.add(e);
			return e;
		}
	}

	/**
	 * Supprimer Utilisateur supprime l'utilisateur correspondant à la page passée en paramètre et au nom de l'utilisateur
	 * Le nombre d'utilisations de l'utilisateur est décrémenté de 1
	 * L'utilisateur est supprimé de la liste si ce nombre atteint 0
	 * L'association clé-valeur associée à la page est supprimée si la liste d'utilisateurs de cette page est vide
	 *
	 * @param nomPage        correspond au nom de la page sur laquelle on cherche l'utilisateur
	 * @param nomUtilisateur valeur de l'utilisateur recherché
	 * @return l'utilisateur supprimé
	 */
	public static Utilisateur supprimerUtilisateur(String nomPage, String nomUtilisateur) {
		ArrayList<Utilisateur> list = utilisateurs.get(nomPage);
		if (list == null) throw new NullPointerException("Le nom de la page doit correspondre à une page existante.");
		if (nomUtilisateur == null) throw new NullPointerException("Le nom de l'utilisateur ne peut pas être vide.");
		Utilisateur u = new Utilisateur(nomUtilisateur);
		if (!list.contains(u)) throw new NullPointerException("L'utilisateur n'est pas présent dans la page.");
		u = list.get(list.indexOf(u));
		list.get(list.indexOf(u)).nbUse--;
		if (list.get(list.indexOf(u)).nbUse == 0) {
			list.remove(u);
			if (list.isEmpty()) {
				utilisateurs.remove(nomPage);
			}
		}
		return u;
	}

	/**
	 * @return le pseudo de l'utilisateur
	 */
	public String getPseudo() {
		return pseudo;
	}

	public byte[] getIcone() {
		return this.icone;
	}

	/**
	 * Génère une image de profil par défaut pour un utilisateur avec une lettre au milieu d'un cercle au fond de couleur aléatoire
	 * @return l'image de profil par défaut
	 */
	private byte[] genererIconeMembreParDefaut() throws IOException {
		Canvas canvas = new Canvas(200, 200);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		/* Dessine le cercle */
		// Définir une couleur de cercle aléatoire
		Random rand = new Random();
		// Générer des valeurs aléatoires pour rouge, vert et bleu
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		// Créer une couleur aléatoire avec ces valeurs
		Color randomColor = new Color(r, g, b, 1);
		gc.setFill(randomColor);
		gc.fillOval(0, 0, 200, 200);

		// Dessine la première lettre du pseudo sur le cercle
		gc.setFill(Color.WHITE);
		gc.setFont(new Font("Arial", 100));
		String premierLettrePseudo = this.pseudo.substring(0, 1).toUpperCase();
		gc.fillText(premierLettrePseudo, 60, 130);

		// Convertit le canvas en une image javafx
		// SnapshotParameters me permet de rendre le fond transparent
		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT);
		WritableImage writableImage = new WritableImage(200, 200);
		canvas.snapshot(params, writableImage);

		// Convertit l'image javafx en une image BufferedImage
		BufferedImage bufferedImage = SwingFXUtils.fromFXImage(writableImage, null);

		// Écrit l'image BufferedImage dans un ByteArrayOutputStream
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "png", baos);

		// Convertit le ByteArrayOutputStream en byte[]
		byte[] imageInByte = baos.toByteArray();

		return imageInByte;
	}

	/**
	 * Modifie le pseudo de l'utilisateur
	 * @param pseudo le nouveau pseudo de l'utilisateur
	 */
	public void setPseudo(String pseudo) {
		if(pseudo != null) this.pseudo = pseudo;
	}

	/**
	 * @return les informations de l'utilisateur
	 */
	@Override
	public String toString() {
		return "Utilisateur{" +
				"nbUse=" + nbUse +
				", pseudo='" + pseudo + '\'' +
				", icone=" + icone +
				'}';
	}
}
