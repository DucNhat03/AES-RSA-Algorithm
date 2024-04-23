package RSA_Algorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;
import java.util.Random;

public class RSA extends JFrame {
	private static final long serialVersionUID = 4167680333269099030L;
	private JPanel encryptPanel;
	private JPanel decryptPanel;
	private JTextArea plainTextArea;
	private JTextArea encryptedTextArea;
	private JTextArea decryptedTextArea;
	private BigInteger N;
	private BigInteger e;
	private BigInteger d;
	private BigInteger phi;
	private JButton btnRandom;
	private JTextArea txtP, txtq, txtPhi, txtN, txtE, txtD;
	private BigInteger p;
	private BigInteger q;

	public RSA() {
		super("GROUP 1 - RSA Algorithm");

		initializeUI();

		btnRandom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				generateRSAKeys();
			}
		});
	}

	private void initializeUI() {
		Container container = getContentPane();
		getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(0, 0, 586, 563);
		container.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Khóa", null, panel, null);
		panel.setLayout(null);

		JLabel lblP = new JLabel("P:");
		lblP.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblP.setBounds(20, 36, 45, 13);
		panel.add(lblP);

		JScrollPane scrlp = new JScrollPane();
		scrlp.setBounds(86, 10, 437, 58);
		panel.add(scrlp);

		txtP = new JTextArea();
		txtP.setWrapStyleWord(true);
		txtP.setLineWrap(true);
		txtP.setColumns(40);
		txtP.setRows(10);
		scrlp.setViewportView(txtP);

		JLabel lblq = new JLabel("q:");
		lblq.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblq.setBounds(20, 100, 45, 30);
		panel.add(lblq);

		JScrollPane srlq = new JScrollPane();
		srlq.setBounds(86, 90, 437, 50);
		panel.add(srlq);

		txtq = new JTextArea();
		txtq.setLineWrap(true);
		txtq.setWrapStyleWord(true);
		txtq.setColumns(39);
		txtq.setRows(10);
		// srlq.setRowHeaderView(txtq);
		srlq.setViewportView(txtq);

		btnRandom = new JButton("Sinh Khóa");
		btnRandom.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRandom.setBounds(221, 483, 138, 43);
		panel.add(btnRandom);

		JLabel lblphi = new JLabel("Phi N:");
		lblphi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblphi.setBounds(16, 162, 60, 30);
		panel.add(lblphi);

		JScrollPane srlPhi = new JScrollPane();
		srlPhi.setBounds(86, 153, 437, 58);
		panel.add(srlPhi);

		txtPhi = new JTextArea();
		txtPhi.setRows(40);
		txtPhi.setWrapStyleWord(true);
		txtPhi.setLineWrap(true);
		srlPhi.setViewportView(txtPhi);

		JLabel lbltieuDe = new JLabel("Cập nhật khóa công khai");
		lbltieuDe.setForeground(new Color(255, 0, 0));
		lbltieuDe.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbltieuDe.setBounds(20, 231, 161, 21);
		panel.add(lbltieuDe);

		JLabel lblNewLabel = new JLabel("Số modul công khai N:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 262, 161, 36);
		panel.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(181, 262, 347, 43);
		panel.add(scrollPane);

		txtN = new JTextArea();
		txtN.setRows(40);
		txtN.setWrapStyleWord(true);
		txtN.setLineWrap(true);
		scrollPane.setViewportView(txtN);

		JLabel lblNewLabel_1 = new JLabel("Số mũ công khai e");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 340, 138, 30);
		panel.add(lblNewLabel_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(181, 327, 347, 43);
		panel.add(scrollPane_1);

		txtE = new JTextArea();
		txtE.setColumns(50);
		txtE.setWrapStyleWord(true);
		txtE.setLineWrap(true);
		txtE.setRows(40);
		scrollPane_1.setRowHeaderView(txtE);
		scrollPane_1.setViewportView(txtE);

		JLabel lblNewLabel_2 = new JLabel("Khóa bí mật:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setForeground(new Color(255, 0, 0));
		lblNewLabel_2.setBounds(16, 391, 138, 13);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("d: ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(20, 426, 45, 13);
		panel.add(lblNewLabel_3);

		JScrollPane txtd = new JScrollPane();
		txtd.setBounds(181, 411, 352, 43);
		panel.add(txtd);

		txtD = new JTextArea();
		txtD.setColumns(43);
		txtD.setRows(40);
		txtD.setWrapStyleWord(true);
		txtD.setLineWrap(true);
		txtd.setRowHeaderView(txtD);
		txtd.setViewportView(txtD);

		encryptPanel = new JPanel();
		tabbedPane.addTab("Encrypt", encryptPanel);
		JLabel plainLabel = new JLabel("Nhập nội dung:");
		plainLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		plainLabel.setBounds(0, 0, 290, 84);
		plainTextArea = new JTextArea();
		plainTextArea.setRows(10);
		plainTextArea.setLineWrap(true);
		plainTextArea.setWrapStyleWord(true);
		encryptPanel.add(plainLabel);

		JScrollPane plainScrollPane = new JScrollPane(plainTextArea);
		plainScrollPane.setBounds(151, 0, 429, 120);
		plainScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		plainScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		encryptPanel.add(plainScrollPane);

		JLabel encryptedLabel_1 = new JLabel("Chuỗi mã hóa:");
		encryptedLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		encryptedLabel_1.setBounds(10, 185, 158, 84);
		encryptedTextArea = new JTextArea();
		encryptedTextArea.setColumns(5);
		encryptedTextArea.setRows(10);
		encryptedTextArea.setLineWrap(true);
		encryptedTextArea.setWrapStyleWord(true);
		encryptedTextArea.setEditable(false);
		encryptPanel.add(encryptedLabel_1);

		JScrollPane encryptedScrollPane = new JScrollPane(encryptedTextArea);
		encryptedScrollPane.setBounds(151, 149, 429, 120);
		encryptedScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		encryptedScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		encryptPanel.add(encryptedScrollPane);

		JButton encryptButton = new JButton("Encrypt");
		encryptButton.setBackground(new Color(102, 205, 170));
		encryptButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		encryptButton.setBounds(10, 345, 550, 84);
		encryptButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtN.getText().isEmpty() || txtE.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng tạo khóa trước khi mã hóa", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else if (plainTextArea.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập nội dung cần mã hóa", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					encryptText();
				}

			}
		});

		encryptPanel.add(encryptButton);
		decryptPanel = new JPanel();
		tabbedPane.addTab("Decrypt", decryptPanel);

		JLabel encryptedLabel = new JLabel("Nhập chuỗi mã hóa:");
		encryptedLabel.setBounds(0, 0, 161, 134);
		encryptedLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		JTextArea encryptedTextAreaDecrypt = new JTextArea();
		encryptedTextAreaDecrypt.setRows(40);
		encryptedTextAreaDecrypt.setLineWrap(true);
		encryptedTextAreaDecrypt.setWrapStyleWord(true);
		decryptPanel.setLayout(null);
		decryptPanel.add(encryptedLabel);

		JScrollPane encryptedScrollPaneDecrypt = new JScrollPane(encryptedTextAreaDecrypt);
		encryptedScrollPaneDecrypt.setBounds(173, 0, 407, 134);
		decryptPanel.add(encryptedScrollPaneDecrypt);

		JLabel decryptedLabel = new JLabel("Chuỗi giải mã:");
		decryptedLabel.setBounds(10, 182, 171, 134);
		decryptedLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		decryptedTextArea = new JTextArea();
		decryptedTextArea.setRows(40);
		decryptedTextArea.setEditable(false);
		decryptedTextArea.setLineWrap(true);
		decryptedTextArea.setWrapStyleWord(true);
		decryptPanel.add(decryptedLabel);

		JScrollPane decryptedScrollPane = new JScrollPane(decryptedTextArea);
		decryptedScrollPane.setBounds(173, 182, 407, 134);
		decryptPanel.add(decryptedScrollPane);

		JButton decryptButton = new JButton("Decrypt");
		decryptButton.setBounds(21, 391, 550, 88);
		decryptButton.setBackground(new Color(255, 240, 245));
		decryptButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		decryptButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtN.getText().isEmpty() || txtD.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng tạo khóa trước khi giải mã", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else if (encryptedTextAreaDecrypt.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập chuỗi mã hóa cần giải mã", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					decryptText(encryptedTextAreaDecrypt.getText());
				}
			}
		});

		decryptPanel.add(decryptButton);

		createEncryptPanel();
		createDecryptPanel();

		setSize(600, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void createEncryptPanel() {
		encryptPanel.setLayout(null);
	}

	private void createDecryptPanel() {
	}

	private String bytesToString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(b).append(" ");
		}
		return sb.toString();
	}

	private void encryptText() {
		String plainText = plainTextArea.getText();
		byte[] encrypted = encryptRSA(plainText.getBytes());
		String encryptedText = bytesToString(encrypted);
		encryptedTextArea.setText(encryptedText);
	}

	private byte[] encryptRSA(byte[] data) {
		BigInteger message = new BigInteger(data);
		BigInteger encryptedMessage = message.modPow(e, N);
		return encryptedMessage.toByteArray();
	}

	private byte[] stringToBytes(String str) {
		String[] byteValues = str.trim().split(" ");
		byte[] bytes = new byte[byteValues.length];
		for (int i = 0; i < byteValues.length; i++) {
			bytes[i] = Byte.parseByte(byteValues[i]);
		}
		return bytes;
	}

	private byte[] decryptRSA(byte[] data) {
		BigInteger encryptedMessage = new BigInteger(data);
		BigInteger decryptedMessage = encryptedMessage.modPow(d, N);
		return decryptedMessage.toByteArray();
	}

	private void decryptText(String encryptedText) {
		byte[] encrypted = stringToBytes(encryptedText);
		byte[] decrypted = decryptRSA(encrypted);
		String decryptedText = new String(decrypted);
		decryptedTextArea.setText(decryptedText);
	}

	private void generateRSAKeys() {
		int bitlength = 256;
		Random r = new Random();
		p = BigInteger.probablePrime(bitlength, r);
		q = BigInteger.probablePrime(bitlength, r);
		N = p.multiply(q);
		phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		e = new BigInteger("65537");
		d = e.modInverse(phi);

		txtP.setText(p.toString());
		txtq.setText(q.toString());
		txtPhi.setText(phi.toString());
		txtN.setText(N.toString());
		txtE.setText(e.toString());
		txtD.setText(d.toString());
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new RSA().setVisible(true);
			}
		});
	}
}
