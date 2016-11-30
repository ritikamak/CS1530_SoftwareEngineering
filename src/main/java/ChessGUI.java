import javax.swing.JOptionPane;
/**
 * Chess Application GUI
 * @author Pete Stamos petestamos@gmail.com
 */
public class ChessGUI extends javax.swing.JFrame
{

    /* Constants */
	public static final boolean USER = true;
    public static final boolean COMP = true;
    public static final boolean BLACK = true;
    public static final boolean WHITE = false;

    /* Variables */
    //the gui needs a game object to update its display as game state changes
    private Game game;
	// this is a boolean flag true if the board is flipped
	private boolean flipped;
    //square buttons are stored in a 8x8 grid modeled after chessboard
    private javax.swing.JButton[][] guiBoard;

    //labels, game interface buttons
    private javax.swing.JLabel blackLabel;
    private javax.swing.JPanel durationPanel;
    private javax.swing.JLabel durationText;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label6;
    private javax.swing.JLabel label7;
    private javax.swing.JLabel label8;
    private javax.swing.JLabel labelA;
    private javax.swing.JLabel labelB;
    private javax.swing.JLabel labelC;
    private javax.swing.JLabel labelD;
    private javax.swing.JLabel labelE;
    private javax.swing.JLabel labelF;
    private javax.swing.JLabel labelG;
    private javax.swing.JLabel labelH;
    private javax.swing.JButton loadGameButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel menuBarPanel;
    private javax.swing.JButton newGameButton;
    private javax.swing.JPanel outOfPlayPanel;
    private javax.swing.JButton saveGameButton;
    private javax.swing.JLabel whiteLabel;
    private javax.swing.JPanel menuBarPanel1;
    private javax.swing.JComboBox<Piece.DisplayColor> colorSelector;
    private javax.swing.ButtonGroup colorSelectorButtonGroup;
    private javax.swing.JRadioButton computerColorButton;
    private javax.swing.JButton flipBoardButton;
    private javax.swing.JRadioButton playerColorButton;
    private javax.swing.JButton submitColorsButton;

	/* Constructors */
	public ChessGUI(Game g)
	{
		initGUI(g);
    }

	/* Public Methods */

	/* display() */
	//makes the GUI visible
    public void display(boolean show)
	{
		setVisible(show);
    }

	/* infoBox() */
    // method for GUI to communicate with user
	public void infoBox(String infoMessage, String titleBar)
    {
          JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

	/*refreshPieces()*/
	//tells GUI to reset the pieces
	public void refreshPieces()
	{
		setPieces();
	}

	/*refreshBoard()*/
	//tells GUI to reset the board and pieces
	public void refreshBoard()
	{
		setBoard();
		setPieces();
	}

	/* flipBoard() */
	//after flip board button is pressed, this function sets the GUI variable flip to its opposite, reapplies labels, resets board buttons and resets pieces accordingly
	public void flipBoard()
	{
        flipped = !flipped;
		applyLabels();
		setBoard();
		setPieces();
    }

	/* Private Methods */

	/* initGUI() */
	//initializes entire GUI
	private void initGUI(Game g)
	{
		game = g;
		flipped = false;
		createComponents();
		initMainPanel();
		initApplicationButtons();
		initBoard();
		initPanelLayouts();
		applyLabels();
		setBoard();
		setPieces();
    }


	/* flipInt() */
	//this function returns an int representing the "flipped" rank or file position of original int passed
	private int flipInt(int original)
	{
		int ret;

		ret = -1;
		switch(original){
			case 0:
				ret = 7;
				break;
			case 1:
				ret = 6;
				break;
			case 2:
				ret = 5;
				break;
			case 3:
				ret = 4;
				break;
			case 4:
				ret = 3;
				break;
			case 5:
				ret = 2;
				break;
			case 6:
				ret = 1;
				break;
			case 7:
				ret = 0;
				break;
		}
		return ret;
	}

	/* setPieceIcon() */
    // Determines if a piece is on the square(file and rank). If true, sets a relevant piece icon on gui
    private void setPieceIcon(javax.swing.JButton button)
    {
		Square s;
		Piece p;
		String resourceFile;

		s = (Square)button.getClientProperty( "square" );
		//initially clear any existing icons
		button.setIcon(null);
		//check if square is occupied
		if(s.isOccupied() == false){
			return; //if not, just return
		}
		//else, lets get the piece on that square and check its display color
		p = s.getPiece();
		resourceFile = "/images/";

		//we are then generating a resourceColor based on displayColor property
		switch(p.getDisplayColor()){
			case BLUE:
				resourceFile = resourceFile + "blue-";
				break;
			case GREEN:
				resourceFile = resourceFile + "green-";
				break;
			case ORANGE:
				resourceFile = resourceFile + "orange-";
				break;
			case RED:
				resourceFile = resourceFile + "red-";
				break;
			case YELLOW:
				resourceFile = resourceFile + "yellow-";
				break;
			default:
				if(p.getColor() == WHITE){
					resourceFile = resourceFile + "white-";
				}
				else if(p.getColor() == BLACK){
					resourceFile = resourceFile + "black-";
				}
		}

		resourceFile = resourceFile + p.getName().toLowerCase() + ".png";
		button.setIcon(new javax.swing.ImageIcon(getClass().getResource(resourceFile)));
    }

	/* setPieces()*/
	// Runs through each square on the board and sets the piece residing there
	private void setPieces()
	{
		int file;
		int rank;

		for(file = 0; file < 8; file++){
			for(rank = 0; rank < 8; rank++){
				setPieceIcon(guiBoard[file][rank]);
			}
		}
	}

	/* removeActionListeners() */
	//this function removes ALL action listers set to button parameter. This is largely used in resetting the board after a flip
	private void removeActionListeners(javax.swing.JButton button)
	{
		java.awt.event.ActionListener[] toRemove;
		int i;

		toRemove = button.getActionListeners();
		for(i = 0; i < toRemove.length; i++){
			button.removeActionListener(toRemove[i]);
		}
	}

	/* setBoard() */
	//this function links the GUI board squares to their backend counterpart, inverting the back end squares linked if the board is flipped
	private void setBoard()
	{
		int file;
		int rank;
		int f;
		int r;
		Square s;

		for(file = 0; file < 8; file++){
			for(rank = 0; rank < 8; rank++){
				if(flipped){
					f = flipInt(file);
					r = flipInt(rank);
				}
				else{
					f = file;
					r = rank;
				}
				s = game.board.getSquareAt(f, r);
				guiBoard[file][rank].putClientProperty("square", s);
				//check if we need to highlight square boarder
				if(s.isSelected() && s.isOccupied() && s.getPiece().isSelected()){
					guiBoard[file][rank].setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.GREEN, 2));
				}
				else if(s.isSelected()){
					guiBoard[file][rank].setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLUE, 2));
				}
				else{
					guiBoard[file][rank].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
				}
				//removes old action listener associated with GUI square (if any)
				removeActionListeners(guiBoard[file][rank]);
				//adds a listener to the square
				guiBoard[file][rank].addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e){
						//clicking on a square is GameInput
						GameInput gi = new GameInput(USER);//and all GUI button presses are from a human user (we may be in the midst of a robot uprising otherwise...)
						Square s = (Square)(((javax.swing.JButton)e.getSource()).getClientProperty( "square" ));
						//map dest square (the button that was pressed)
						gi.mapSquare("dest", s);
						InputHandler.handleGameInput(gi);
					}
				});
			}
		}
	}

	/* applyLabels() */
	//adds labels (inversed if gui is flipped)
	private void applyLabels()
	{
		//flipped labels
		if(flipped){
			label8.setText("1");
			label7.setText("2");
			label6.setText("3");
			label5.setText("4");
			label4.setText("5");
			label3.setText("6");
			label2.setText("7");
			label1.setText("8");
			labelA.setText("H");
			labelB.setText("G");
			labelC.setText("F");
			labelD.setText("E");
			labelE.setText("D");
			labelF.setText("C");
			labelG.setText("B");
			labelH.setText("A");
		}
		//normal labels
		else{
			label8.setText("8");
			label7.setText("7");
			label6.setText("6");
			label5.setText("5");
			label4.setText("4");
			label3.setText("3");
			label2.setText("2");
			label1.setText("1");
			labelA.setText("A");
			labelB.setText("B");
			labelC.setText("C");
			labelD.setText("D");
			labelE.setText("E");
			labelF.setText("F");
			labelG.setText("G");
			labelH.setText("H");
		}
        outOfPlayPanel.setBackground(new java.awt.Color(204, 204, 204));
        outOfPlayPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        blackLabel.setText("Computer");

        whiteLabel.setText("Player");
	}

	/* createCompenents() */
	//initializes most of the gui components
	private void createComponents()
	{
		colorSelectorButtonGroup = new javax.swing.ButtonGroup();
		mainPanel = new javax.swing.JPanel();
		label8 = new javax.swing.JLabel();
        label7 = new javax.swing.JLabel();
        label6 = new javax.swing.JLabel();
        label5 = new javax.swing.JLabel();
        label4 = new javax.swing.JLabel();
        label3 = new javax.swing.JLabel();
        label2 = new javax.swing.JLabel();
        label1 = new javax.swing.JLabel();
        labelA = new javax.swing.JLabel();
        labelB = new javax.swing.JLabel();
        labelC = new javax.swing.JLabel();
        labelD = new javax.swing.JLabel();
        labelE = new javax.swing.JLabel();
        labelF = new javax.swing.JLabel();
        labelG = new javax.swing.JLabel();
        labelH = new javax.swing.JLabel();
        outOfPlayPanel = new javax.swing.JPanel();
        blackLabel = new javax.swing.JLabel();
        whiteLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        durationPanel = new javax.swing.JPanel();
        durationText = new javax.swing.JLabel();
        menuBarPanel = new javax.swing.JPanel();
		newGameButton = new javax.swing.JButton();
		saveGameButton = new javax.swing.JButton();
		loadGameButton = new javax.swing.JButton();
		menuBarPanel1 = new javax.swing.JPanel();
		playerColorButton = new javax.swing.JRadioButton();
		computerColorButton = new javax.swing.JRadioButton();
		submitColorsButton = new javax.swing.JButton();
		colorSelector = new javax.swing.JComboBox<>();
		flipBoardButton = new javax.swing.JButton();
		guiBoard = new javax.swing.JButton[8][8];
	}

	/* initMainPanel() */
	//sets up our main panel
	private void initMainPanel()
	{
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LaboonChess");
        setMaximumSize(new java.awt.Dimension(725, 553));
        setMinimumSize(new java.awt.Dimension(725, 553));
        setResizable(false);

		mainPanel.setBackground(new java.awt.Color(255, 255, 255));
		mainPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
		mainPanel.setMaximumSize(new java.awt.Dimension(402, 402));
		mainPanel.setMinimumSize(new java.awt.Dimension(402, 402));
		mainPanel.setPreferredSize(new java.awt.Dimension(402, 402));
		mainPanel.setSize(new java.awt.Dimension(402, 402));

	}

	/* initBoard() */
	//sets up our board buttons
	private void initBoard()
	{
		int file;
		int rank;

		for(file = 0; file < 8; file++){
			for(rank = 0; rank < 8; rank++){
				guiBoard[file][rank] = new javax.swing.JButton();
        if((file % 2 == 0 && rank % 2 != 0) || (file % 2 != 0 && rank % 2 == 0)) {
          guiBoard[file][rank].setBackground(new java.awt.Color(255, 255, 255));
        }
        else {
          guiBoard[file][rank].setBackground(new java.awt.Color(153, 153, 153));
        }
				guiBoard[file][rank].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
				guiBoard[file][rank].setContentAreaFilled(false);
				guiBoard[file][rank].setMargin(new java.awt.Insets(0, 0, 0, 0));
				guiBoard[file][rank].setMaximumSize(new java.awt.Dimension(50, 50));
				guiBoard[file][rank].setMinimumSize(new java.awt.Dimension(50, 50));
				guiBoard[file][rank].setOpaque(true);
				guiBoard[file][rank].setPreferredSize(new java.awt.Dimension(50, 50));
				guiBoard[file][rank].setSize(new java.awt.Dimension(50, 50));
			}
		}
	}

	/* initApplicationButtons() */
	//sets up our application buttons (these are any buttons not directly related to the game of chess. )
	private void initApplicationButtons()
	{
		newGameButton.setText("New Game");
        newGameButton.setBorderPainted(true);
        newGameButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        newGameButton.setRequestFocusEnabled(false);
		newGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
				ApplicationInput ai = new ApplicationInput(ApplicationInput.AppOp.NEW_GAME);
                InputHandler.handleApplicationInput(ai);
            }
        });

        saveGameButton.setText("Save Game");
        saveGameButton.setBorderPainted(true);
        saveGameButton.setMargin(new java.awt.Insets(0, 0, 0, 0));


        loadGameButton.setText("Load Game");
        loadGameButton.setBorderPainted(true);
        loadGameButton.setMargin(new java.awt.Insets(0, 0, 0, 0));

		durationText.setText("Duration: 00:00");

		colorSelectorButtonGroup.add(playerColorButton);
        playerColorButton.setSelected(true);
        playerColorButton.setText("Player Color");
        //playerColorButton.setEnabled(false);

        colorSelectorButtonGroup.add(computerColorButton);
        computerColorButton.setText("Computer Color");
        //computerColorButton.setEnabled(false);

        colorSelector.setModel(new javax.swing.DefaultComboBoxModel<Piece.DisplayColor>(new Piece.DisplayColor[] { Piece.DisplayColor.STANDARD, Piece.DisplayColor.RED, Piece.DisplayColor.ORANGE, Piece.DisplayColor.YELLOW, Piece.DisplayColor.GREEN, Piece.DisplayColor.BLUE }));

        submitColorsButton.setText("Submit Colors");
        submitColorsButton.setBorderPainted(true);
        submitColorsButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        submitColorsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
			//create applicationinput object with change color command and the color selected
				ApplicationInput ai = new ApplicationInput(playerColorButton.isSelected(), ApplicationInput.AppOp.CHANGE_PIECE_DISPLAY_COLOR);
				ai.mapDisplayColor("displayColor", (Piece.DisplayColor)colorSelector.getSelectedItem());
				//let inputhandler do the rest of the work
				InputHandler.handleApplicationInput(ai);
			}
        });

        flipBoardButton.setText("Flip Board");
        flipBoardButton.setBorderPainted(true);
        flipBoardButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        flipBoardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
				ApplicationInput ai = new ApplicationInput(ApplicationInput.AppOp.FLIP_BOARD);
                InputHandler.handleApplicationInput(ai);
            }
        });
	}

	/* initPanelLayouts */
	//this function is pretty messy, expand at your own peril!
	private void initPanelLayouts()
	{
		javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
		//Square Buttons
		 mainPanel.setLayout(mainPanelLayout);
         mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(guiBoard[0][7], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(guiBoard[1][7], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(guiBoard[2][7], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(guiBoard[3][7], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(guiBoard[4][7], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(guiBoard[5][7], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(guiBoard[6][7], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(guiBoard[7][7], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(guiBoard[0][5], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(guiBoard[1][5], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(guiBoard[2][5], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(guiBoard[3][5], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(guiBoard[4][5], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(guiBoard[5][5], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(guiBoard[6][5], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(guiBoard[7][5], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                .addComponent(guiBoard[0][4], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(guiBoard[1][4], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(guiBoard[2][4], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(guiBoard[3][4], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(guiBoard[4][4], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(guiBoard[5][4], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(guiBoard[6][4], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(guiBoard[7][4], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(mainPanelLayout.createSequentialGroup()
                            .addComponent(guiBoard[0][6], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(guiBoard[1][6], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(guiBoard[2][6], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(guiBoard[3][6], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(guiBoard[4][6], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(guiBoard[5][6], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(guiBoard[6][6], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(guiBoard[7][6], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                            .addComponent(guiBoard[0][3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(guiBoard[1][3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(guiBoard[2][3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(guiBoard[3][3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(guiBoard[4][3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(guiBoard[5][3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(guiBoard[6][3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(guiBoard[7][3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(mainPanelLayout.createSequentialGroup()
                                    .addComponent(guiBoard[0][1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(guiBoard[1][1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(guiBoard[2][1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(guiBoard[3][1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(guiBoard[4][1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(guiBoard[5][1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(guiBoard[6][1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(guiBoard[7][1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                    .addComponent(guiBoard[0][0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(guiBoard[1][0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(guiBoard[2][0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(guiBoard[3][0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(guiBoard[4][0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(guiBoard[5][0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(guiBoard[6][0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(guiBoard[7][0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(guiBoard[0][2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(guiBoard[1][2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(guiBoard[2][2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(guiBoard[3][2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(guiBoard[4][2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(guiBoard[5][2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(guiBoard[6][2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(guiBoard[7][2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(guiBoard[0][7], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[1][7], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[3][7], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[2][7], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[4][7], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[5][7], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[6][7], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[7][7], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(guiBoard[0][6], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[1][6], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[3][6], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[2][6], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[4][6], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[5][6], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[6][6], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[7][6], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(guiBoard[0][5], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[1][5], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[3][5], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[2][5], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[4][5], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[5][5], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[6][5], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[7][5], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(guiBoard[0][4], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[1][4], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[3][4], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[2][4], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[4][4], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[5][4], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[6][4], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[7][4], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(guiBoard[0][3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[1][3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[3][3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[2][3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[4][3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[5][3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[6][3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[7][3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(guiBoard[0][2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[1][2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[3][2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[2][2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[4][2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[5][2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[6][2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[7][2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(guiBoard[0][1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[1][1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[3][1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[2][1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[4][1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[5][1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[6][1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[7][1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(guiBoard[0][0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[1][0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[3][0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[2][0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[4][0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[5][0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[6][0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiBoard[7][0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

		javax.swing.GroupLayout outOfPlayPanelLayout = new javax.swing.GroupLayout(outOfPlayPanel);
        outOfPlayPanel.setLayout(outOfPlayPanelLayout);
        outOfPlayPanelLayout.setHorizontalGroup(
            outOfPlayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addGroup(outOfPlayPanelLayout.createSequentialGroup()
                .addGroup(outOfPlayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(outOfPlayPanelLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(blackLabel))
                    .addGroup(outOfPlayPanelLayout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(whiteLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        outOfPlayPanelLayout.setVerticalGroup(
            outOfPlayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outOfPlayPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(blackLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 252, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(whiteLabel)
                .addGap(12, 12, 12))
        );

        durationPanel.setBackground(new java.awt.Color(204, 204, 204));
        durationPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));


        javax.swing.GroupLayout durationPanelLayout = new javax.swing.GroupLayout(durationPanel);
        durationPanel.setLayout(durationPanelLayout);
        durationPanelLayout.setHorizontalGroup(
            durationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(durationPanelLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(durationText)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        durationPanelLayout.setVerticalGroup(
            durationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(durationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(durationText)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

		menuBarPanel.setBackground(new java.awt.Color(204, 204, 204));
        menuBarPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		 javax.swing.GroupLayout menuBarPanel1Layout = new javax.swing.GroupLayout(menuBarPanel1);
        menuBarPanel1.setLayout(menuBarPanel1Layout);
        menuBarPanel1Layout.setHorizontalGroup(
            menuBarPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuBarPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(playerColorButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(computerColorButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(colorSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(submitColorsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(flipBoardButton)
                //.addGap(47, 47, 47))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
        );
        menuBarPanel1Layout.setVerticalGroup(
            menuBarPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuBarPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(menuBarPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitColorsButton)
                    .addComponent(flipBoardButton)
                    .addComponent(colorSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(playerColorButton)
                    .addComponent(computerColorButton)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(labelA)
                        .addGap(40, 40, 40)
                        .addComponent(labelB)
                        .addGap(42, 42, 42)
                        .addComponent(labelC)
                        .addGap(42, 42, 42)
                        .addComponent(labelD)
                        .addGap(42, 42, 42)
                        .addComponent(labelE)
                        .addGap(43, 43, 43)
                        .addComponent(labelF)
                        .addGap(43, 43, 43)
                        .addComponent(labelG)
                        .addGap(41, 41, 41)
                        .addComponent(labelH))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(label8)
                            .addComponent(label7)
                            .addComponent(label6)
                            .addComponent(label5)
                            .addComponent(label4)
                            .addComponent(label3)
                            .addComponent(label2)
                            .addComponent(label1))
                        .addGap(18, 18, 18)
                        .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(outOfPlayPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(durationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(46, Short.MAX_VALUE))
            .addComponent(menuBarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuBarPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuBarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(label8)
                        .addGap(35, 35, 35)
                        .addComponent(label7)
                        .addGap(33, 33, 33)
                        .addComponent(label6)
                        .addGap(33, 33, 33)
                        .addComponent(label5)
                        .addGap(35, 35, 35)
                        .addComponent(label4)
                        .addGap(33, 33, 33)
                        .addComponent(label3)
                        .addGap(35, 35, 35)
                        .addComponent(label2)
                        .addGap(34, 34, 34)
                        .addComponent(label1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(durationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(outOfPlayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelA)
                    .addComponent(labelB)
                    .addComponent(labelC)
                    .addComponent(labelD)
                    .addComponent(labelE)
                    .addComponent(labelF)
                    .addComponent(labelG)
                    .addComponent(labelH))
                .addGap(18, 18, 18)
                .addComponent(menuBarPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

		javax.swing.GroupLayout menuBarPanelLayout = new javax.swing.GroupLayout(menuBarPanel);
        menuBarPanel.setLayout(menuBarPanelLayout);
        menuBarPanelLayout.setHorizontalGroup(
            menuBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuBarPanelLayout.createSequentialGroup()
                .addComponent(newGameButton)
                .addGap(0, 0, 0)
                .addComponent(saveGameButton)
                .addGap(0, 0, 0)
                .addComponent(loadGameButton)
                .addGap(0, 0, 0))
        );
        menuBarPanelLayout.setVerticalGroup(
            menuBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuBarPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(menuBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newGameButton)
                    .addComponent(saveGameButton)
                    .addComponent(loadGameButton)))
        );

        menuBarPanel1.setBackground(new java.awt.Color(204, 204, 204));
        menuBarPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pack();
	}
}
