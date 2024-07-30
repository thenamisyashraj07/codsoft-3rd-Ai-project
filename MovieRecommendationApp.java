import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Movie {
    private String title;
    private String genre;
    private ImageIcon imageIcon;

    public Movie(String title, String genre, ImageIcon imageIcon) {
        this.title = title;
        this.genre = genre;
        this.imageIcon = imageIcon;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    @Override
    public String toString() {
        return title; 
    }
}

class RecommendationSystem {
    private List<Movie> movies;

    public RecommendationSystem(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> recommendMovies(String genre) {
        List<Movie> recommendedMovies = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getGenre().equals(genre)) {
                recommendedMovies.add(movie);
            }
        }
        return recommendedMovies;
    }
}

public class MovieRecommendationApp extends JFrame {

    private JTable movieTable;
    private DefaultTableModel tableModel;
    private JComboBox<String> genreComboBox;
    private JButton recommendButton;

    private List<Movie> movies;
    private RecommendationSystem recommendationSystem;

    public MovieRecommendationApp() {
        setTitle("Movie Recommendation App");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        
        initializeMovies();

        
        recommendationSystem = new RecommendationSystem(movies);

        
        tableModel = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) return ImageIcon.class;
                return String.class;
            }
        };
        tableModel.setColumnIdentifiers(new Object[]{"Image", "Title", "Genre"});
        movieTable = new JTable(tableModel);
        movieTable.setRowHeight(300); 
        movieTable.getColumn("Image").setCellRenderer(new ImageIconTableCellRenderer());

        JScrollPane scrollPane = new JScrollPane(movieTable);
        add(scrollPane, BorderLayout.CENTER);

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel genreLabel = new JLabel("Preferred Genre:");
        inputPanel.add(genreLabel);

        String[] genres = {"Action", "Comedy", "Drama", "Fantasy", "Horror", "Sci-Fi", "Anime", "Indian"};
        genreComboBox = new JComboBox<>(genres);
        inputPanel.add(genreComboBox);

        recommendButton = new JButton("Recommend Movies");
        inputPanel.add(recommendButton);

        add(inputPanel, BorderLayout.SOUTH);

        // Event handling
        recommendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRecommendation();
            }
        });
    }

    private void initializeMovies() {
        movies = new ArrayList<>();
        
            
            movies.add(new Movie("Inception: A Dream Within a Dream", "Action", loadImage("D:\\download (34).jpeg")));
            movies.add(new Movie("The Matrix: Red Pill or Blue Pill?", "Action", loadImage("D:\\images (36).jpeg")));
            movies.add(new Movie("Mad Max: Fury Road - Beyond the Wasteland", "Action", loadImage("D:\\download (35).jpeg")));
            movies.add(new Movie("The Dark Knight: Rise of the Bat", "Action", loadImage("D:\\images (37).jpeg")));
            movies.add(new Movie("The Avengers: Earth's Mightiest Heroes", "Action", loadImage("D:\\download (36).jpeg")));
            movies.add(new Movie("The Hangover: What Happens in Vegas", "Comedy", loadImage("D:\\download (37).jpeg")));
            movies.add(new Movie("Superbad: A Night to Remember", "Comedy", loadImage("D:\\download (38).jpeg")));
            movies.add(new Movie("Step Brothers: Brothers From Another Mother", "Comedy", loadImage("D:\\download (39).jpeg")));
            movies.add(new Movie("Anchorman: The Legend of Ron Burgundy", "Comedy", loadImage("D:\\download (40).jpeg")));
            movies.add(new Movie("Mean Girls: The Queen Bee Drama", "Comedy", loadImage("D:\\download (41).jpeg")));
            movies.add(new Movie("The Shawshank Redemption: Hope is a Good Thing", "Drama", loadImage("D:\\download (42).jpeg")));
            movies.add(new Movie("Forrest Gump: Life is Like a Box of Chocolates", "Drama", loadImage("D:\\download (43).jpeg")));
            movies.add(new Movie("The Godfather: An Offer You Can't Refuse", "Drama", loadImage("D:\\download (44).jpeg")));
            movies.add(new Movie("Fight Club: The First Rule", "Drama", loadImage("D:\\download (45).jpeg")));
            movies.add(new Movie("The Social Network: The Rise of Facebook", "Drama", loadImage("D:\\download (46).jpeg")));
            movies.add(new Movie("Harry Potter and the Sorcerer's Stone: The Beginning of Magic", "Fantasy", loadImage("D:\\download (47).jpeg")));
            movies.add(new Movie("The Lord of the Rings: The Fellowship's Quest", "Fantasy", loadImage("D:\\download (48).jpeg")));
            movies.add(new Movie("Pan's Labyrinth: A Tale of Fantasy and Darkness", "Fantasy", loadImage("D:\\download (49).jpeg")));
            movies.add(new Movie("Alice in Wonderland: Down the Rabbit Hole", "Fantasy", loadImage("D:\\images (39).jpeg")));
            movies.add(new Movie("The Shining: A Haunting in the Overlook Hotel", "Horror", loadImage("D:\\images (40).jpeg")));
            movies.add(new Movie("Get Out: A Thrilling Escape", "Horror", loadImage("D:\\download (51).jpeg")));
            movies.add(new Movie("A Quiet Place: Survival in Silence", "Horror", loadImage("D:\\download (52).jpeg")));
            movies.add(new Movie("It: The Clown of Your Nightmares", "Horror", loadImage("D:\\download (53).jpeg")));
            movies.add(new Movie("The Exorcist: Demons of the Soul", "Horror", loadImage("D:\\images (41).jpeg")));
            movies.add(new Movie("Interstellar: A Journey Through Space and Time", "Sci-Fi", loadImage("D:\\download (54).jpeg")));
            movies.add(new Movie("Blade Runner 2049: A New Dawn of Humanity", "Sci-Fi", loadImage("D:\\download (55).jpeg")));
            movies.add(new Movie("Dune: The Sands of Arrakis", "Sci-Fi", loadImage("D:\\images (45).jpeg")));
            movies.add(new Movie("The Fifth Element: The Perfect Being", "Sci-Fi", loadImage("D:\\images (43).jpeg")));
            movies.add(new Movie("2001: A Space Odyssey: The Cosmic Journey", "Sci-Fi", loadImage("D:\\images (44).jpeg")));
        
            // Anime with images
            movies.add(new Movie("NARUTO: The Ninja's Journey", "Anime", loadImage("D:\\images (8).jpeg")));
            movies.add(new Movie("One Piece: The Grand Treasure Hunt", "Anime", loadImage("D:\\download (1).jpeg")));
            movies.add(new Movie("Attack on Titan: Humanity's Last Stand", "Anime", loadImage("D:\\download (2).jpeg")));
            movies.add(new Movie("My Hero Academia: Heroes Rising", "Anime", loadImage("D:\\download (3).jpeg")));
            movies.add(new Movie("Dragon Ball Z: The Ultimate Showdown", "Anime", loadImage("D:\\download (8).jpeg")));
            movies.add(new Movie("Death Note: The Book of Judgment", "Anime", loadImage("D:\\download (4).jpeg")));
            movies.add(new Movie("Fullmetal Alchemist: The Philosopher's Quest", "Anime", loadImage("D:\\download (5).jpeg")));
            movies.add(new Movie("Cowboy Bebop: Space Western Adventures", "Anime", loadImage("D:\\download (6).jpeg")));
            movies.add(new Movie("Spirited Away: A Spirited Journey", "Anime", loadImage("D:\\images (6).jpeg")));
            movies.add(new Movie("My Neighbor Totoro: The Forest Spirit", "Anime", loadImage("D:\\images (7).jpeg")));
        
        
            movies.add(new Movie("Bleach: The Soul Reaper Saga", "Anime", loadImage("D:\\download (24).jpeg")));
            movies.add(new Movie("Sword Art Online: The Virtual Reality Adventure", "Anime", loadImage("D:\\download (25).jpeg")));
            movies.add(new Movie("Tokyo Ghoul: The Dark Descent", "Anime", loadImage("D:\\download (27).jpeg")));
            movies.add(new Movie("Neon Genesis Evangelion: The Angel Wars", "Anime", loadImage("D:\\images (30).jpeg")));
            movies.add(new Movie("One Punch Man: The Hero's Quest", "Anime", loadImage("D:\\download (28).jpeg")));
            movies.add(new Movie("Hunter x Hunter: The Hunter's Path", "Anime", loadImage("D:\\download (29).jpeg")));
            movies.add(new Movie("Steins;Gate: The Time Travel Dilemma", "Anime", loadImage("D:\\download (30).jpeg")));
            movies.add(new Movie("Code Geass: The Rebellion of Lelouch", "Anime", loadImage("D:\\download (31).jpeg")));
            movies.add(new Movie("Gintama: The Alternate History", "Anime", loadImage("D:\\images (31).jpeg")));
            movies.add(new Movie("Re:Zero: The Rebirth of Subaru", "Anime", loadImage("D:\\images (32).jpeg")));
            movies.add(new Movie("Mob Psycho 100: The Psychic Adventures", "Anime", loadImage("D:\\download (32).jpeg")));
            movies.add(new Movie("Akira: The Post-Apocalyptic Epic", "Anime", loadImage("D:\\images (33).jpeg")));
            movies.add(new Movie("Samurai Champloo: The Samurai Journey", "Anime", loadImage("D:\\images (34).jpeg")));
            movies.add(new Movie("Made in Abyss: The Abyssal Adventure", "Anime", loadImage("D:\\download (33).jpeg")));
            movies.add(new Movie("Parasyte: The Alien Invasion", "Anime", loadImage("D:\\images (35).jpeg")));
        
            
            movies.add(new Movie("Sholay: The Epic Showdown", "Action", loadImage("D:\\mqdefault.jpg")));
            movies.add(new Movie("Don: The Crime Lord", "Action", loadImage("D:\\Don_(2006_Hindi_film)_poster.jpg")));
            movies.add(new Movie("Singham: The Fierce Protector", "Action", loadImage("D:\\images (46).jpeg")));
            movies.add(new Movie("Krrish: The Superhero's Legacy", "Action", loadImage("D:\\images (50).jpeg")));
            movies.add(new Movie("War: The Battle of Legends", "Action", loadImage("D:\\War-2021-322x455.jpeg")));
            movies.add(new Movie("Dhoom 2: The Ultimate Heist", "Action", loadImage("D:\\images (47).jpeg")));
            movies.add(new Movie("Baaghi: The Rebel's Fight", "Action", loadImage("D:\\images (48).jpeg")));
            movies.add(new Movie("Raees: The King of Crime", "Action", loadImage("D:\\Raees_film_poster.jpg")));
            movies.add(new Movie("Ek Tha Tiger: The Secret Agent", "Action", loadImage("D:\\images (49).jpeg")));
            movies.add(new Movie("Agneepath: The Path of Fire", "Action", loadImage("D:\\hqdefault.jpg")));
        
            
            movies.add(new Movie("3 Idiots: The College Chronicles", "Comedy", loadImage("D:\\images (51).jpeg")));
            movies.add(new Movie("Munna Bhai M.B.B.S.: The Doctor with a Heart", "Comedy", loadImage("D:\\Munna_Bhai_M.B.B.S._poster.jpg")));
            movies.add(new Movie("Andaz Apna Apna: The Hilarious Rivals", "Comedy", loadImage("D:\\images (52).jpeg ")));
            movies.add(new Movie("Chupke Chupke: The Comedy of Errors", "Comedy", loadImage("D:\\images (53).jpeg")));
            movies.add(new Movie("Hera Pheri: The Money Troubles", "Comedy", loadImage("D:\\images (59).jpeg")));
            movies.add(new Movie("Bheja Fry: The Hilarious Journey", "Comedy", loadImage("D:\\images (54).jpeg")));
            movies.add(new Movie("Gol Maal: The Laugh Riot", "Comedy", loadImage("D:\\images (55).jpeg")));
            movies.add(new Movie("Housefull: The Full House of Laughter", "Comedy", loadImage("D:\\images (56).jpeg")));
            movies.add(new Movie("Padosan: The Musical Comedy", "Comedy", loadImage("D:\\3fc51123b5d6874ade5d62a21f4f319f.jpg")));
            movies.add(new Movie("Welcome: The Comic Extravaganza", "Comedy", loadImage("D:\\images (58).jpeg")));
        
            
            movies.add(new Movie("Dangal: The Wrestling Drama", "Drama", loadImage("D:\\download (56).jpeg")));
            movies.add(new Movie("Lagaan: The Historic Cricket Match", "Drama", loadImage("D:\\Lagaan.jpg")));
            movies.add(new Movie("Dil Chahta Hai: The Friendship Journey", "Drama", loadImage("D:\\Dil_Chahta_Hai.jpg")));
            movies.add(new Movie("Zindagi Na Milegi Dobara: The Road Trip of a Lifetime", "Drama", loadImage("D:\\af136ea08e493ab09090e16de4050575485964dc.jpeg")));
            movies.add(new Movie("Kabhi Khushi Kabhie Gham: The Family Saga", "Drama", loadImage("D:\\63df81cab0768b0f33a71a24cc79fed0.jpg")));
            movies.add(new Movie("Piku: The Family Dynamics", "Drama", loadImage("D:\\71envC+VOnL._AC_UF350,350_QL50_.jpg")));
            movies.add(new Movie("Barfi!: The Tale of Love and Laughter", "Drama", loadImage("D:\\74637364.jpg")));
            movies.add(new Movie("Gully Boy: The Rise of a Rapper", "Drama", loadImage("D:\\c0832342dcfb792b3e2b686ce51820ea.jpg")));
            movies.add(new Movie("Taare Zameen Par: The Story of a Special Child", "Drama", loadImage("D:\\Taare_Zameen_Par_Like_Stars_on_Earth_poster.png")));
            movies.add(new Movie("Chakde! India: The Hockey Triumph", "Drama", loadImage("D:\\Chak_De!_India.jpg")));
        
            
            movies.add(new Movie("Baahubali: The Beginning: The Rise of a Kingdom", "Fantasy", loadImage("D:\\images (60).jpeg")));
            movies.add(new Movie("Baahubali 2: The Conclusion: The Epic Finale", "Fantasy", loadImage("D:\\b625602f3fa956e794777952f98eaa1a.jpg")));
            movies.add(new Movie("Koi... Mil Gaya: The Alien Encounter", "Fantasy", loadImage("D:\\7274ebff67a16e476effc727f708505c.jpg")));
            movies.add(new Movie("Ra.One: The Battle of the Virtual World", "Fantasy", loadImage("D:\\Ra.Oneposter.jpg")));
            movies.add(new Movie("Robot (Enthiran): The Rise of the Machines", "Fantasy", loadImage("D:\\desktop-wallpaper-enthiran-rajini-enthiran-thumbnail.jpg")));
            movies.add(new Movie("Makkhi: The Revenge of a Fly", "Fantasy", loadImage("D:\\74637408.jpg")));
            movies.add(new Movie("Jab Tak Hai Jaan: The Eternal Love Story", "Fantasy", loadImage("D:\\unnamed (1).jpg ")));
            movies.add(new Movie("Hawaizaada: The Historical Fantasy", "Fantasy", loadImage("D:\\unnamed (2).jpg")));
            movies.add(new Movie("Sardar Udham: The Revolutionary Tale", "Fantasy", loadImage("D:\\hqdefault (1).jpg")));
        
            
            movies.add(new Movie("Tumbbad: The Haunting Legend", "Horror", loadImage("D:\\Tumbbad-322x504.jpg")));
            movies.add(new Movie("Stree: The Ghost of Chanderi", "Horror", loadImage("D:\\MV5BMTgyYzkxMTQtZTcxMC00YTYyLThkMTYtOTJmY2M4MTUyMWEzXkEyXkFqcGdeQXVyODMyODMxNDY@._V1_.jpg")));
            movies.add(new Movie("Bhool Bhulaiyaa: The Haunted Mansion", "Horror", loadImage("D:\\sddefault (2).jpg")));
            movies.add(new Movie("Raaz: The Secret of the Haunted House", "Horror", loadImage("D:\\MV5BNWRkNWNmMjMtMzVmMy00OGNlLWJlZTEtYWViZmIwNWRmMmQzXkEyXkFqcGdeQXVyMjY1MjkzMjE@._V1_.jpg")));
            movies.add(new Movie("1920: The Year of Horror", "Horror", loadImage("D:\\sddefault (3).jpg")));
            movies.add(new Movie("Pari: The Witch's Curse", "Horror", loadImage("D:\\images (61).jpeg")));
            movies.add(new Movie("Bhoot: The Ghostly Encounter", "Horror", loadImage("D:\\Bhoot_Film_Poster.jpg")));
            movies.add(new Movie("Ghoul: The Terror Unleashed", "Horror", loadImage("D:\\unnamed (3).jpg")));
            movies.add(new Movie("Ragini MMS: The Haunting Secrets", "Horror", loadImage("D:\\ragini_mms_movie_posters_wallpapers.jpg")));
            movies.add(new Movie("Laxmii: The Possessed Spirit", "Horror", loadImage("D:\\Laxmii_poster.jpg")));
        
            
            movies.add(new Movie("Mission Mangal: The Space Odyssey", "Sci-Fi", loadImage("D:\\mission-mangal-1.jpg")));
            movies.add(new Movie("Love Story 2050: The Future Romance", "Sci-Fi", loadImage("D:\\Lovestory2050poster_Ver1.jpg")));
            movies.add(new Movie("kalki: The Future Birth Of Lord Sci-Fi", "Sci-Fi", loadImage("D:\\images (62).jpeg")));
        
           }

    private ImageIcon loadImage(String path) {
        
        return new ImageIcon(path);
    }

    private void handleRecommendation() {
        String selectedGenre = (String) genreComboBox.getSelectedItem();
        List<Movie> recommendedMovies = recommendationSystem.recommendMovies(selectedGenre);

        
        tableModel.setRowCount(0);

        
        for (Movie movie : recommendedMovies) {
            tableModel.addRow(new Object[]{movie.getImageIcon(), movie.getTitle(), movie.getGenre()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MovieRecommendationApp().setVisible(true));
    }
}

class TitleTableCellRenderer extends DefaultTableCellRenderer {
    @Override
    protected void setValue(Object value) {
        if (value instanceof String) {
            
            setFont(new Font("Comic Sans MS", Font.BOLD, 20)); 
            setForeground(Color.BLUE); 
            setText((String) value);
        } else {
            super.setValue(value);
        }
    }
}
