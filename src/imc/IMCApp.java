package imc;

import javax.swing.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.net.URL;

public class IMCApp extends JFrame {

    private JTextField txtNombre, txtEdad, txtPeso, txtEstatura;
    private JTextArea txtResultado;
    private JLabel lblImagen;

    public IMCApp() {

        setTitle("Calculadora de IMC");
        setSize(420, 540); // ⬅️ MÁS ALTO PARA LA IMAGEN
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 20, 100, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 20, 200, 25);
        add(txtNombre);

        JLabel lblEdad = new JLabel("Edad:");
        lblEdad.setBounds(20, 60, 100, 25);
        add(lblEdad);

        txtEdad = new JTextField();
        txtEdad.setBounds(120, 60, 200, 25);
        add(txtEdad);

        JLabel lblPeso = new JLabel("Peso (kg):");
        lblPeso.setBounds(20, 100, 100, 25);
        add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(120, 100, 200, 25);
        add(txtPeso);

        JLabel lblEstatura = new JLabel("Estatura (m):");
        lblEstatura.setBounds(20, 140, 100, 25);
        add(lblEstatura);

        txtEstatura = new JTextField();
        txtEstatura.setBounds(120, 140, 200, 25);
        add(txtEstatura);

        JButton btnCalcular = new JButton("Calcular IMC");
        btnCalcular.setBounds(120, 180, 200, 30);
        add(btnCalcular);

        txtResultado = new JTextArea();
        txtResultado.setBounds(20, 220, 360, 80);
        txtResultado.setEditable(false);
        add(txtResultado);

        // 🖼️ Label para la imagen (tamaño correcto)
        lblImagen = new JLabel();
        lblImagen.setBounds(90, 320, 240, 180); // ⬅️ IDEAL PARA TUS IMÁGENES
        lblImagen.setHorizontalAlignment(JLabel.CENTER);
        add(lblImagen);

        btnCalcular.addActionListener((ActionEvent e) -> calcularIMC());
    }

    private void calcularIMC() {
        try {
            String nombre = txtNombre.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            double peso = Double.parseDouble(txtPeso.getText());
            double estatura = Double.parseDouble(txtEstatura.getText());

            Paciente paciente = new Paciente(nombre, edad, peso, estatura);

            String diagnostico = paciente.interpretarIMC();

            txtResultado.setText(
                "Paciente: " + paciente.getNombre() +
                "\nIMC: " + String.format("%.2f", paciente.calcularIMC()) +
                "\nDiagnóstico: " + diagnostico
            );

            mostrarImagen(diagnostico);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Datos inválidos");
        }
    }

    // 🔹 MÉTODO MEJORADO: escala la imagen automáticamente
    private void mostrarImagen(String diagnostico) {
        String ruta = null;

        switch (diagnostico) {
            case "Bajo peso":
                ruta = "/images/bajo_peso.png";
                break;
            case "Peso normal":
                ruta = "/images/normal.png";
                break;
            case "Sobrepeso":
                ruta = "/images/sobrepeso.png";
                break;
            case "Obesidad":
                ruta = "/images/obesidad.png";
                break;
        }

        URL imgURL = getClass().getResource(ruta);

        if (imgURL != null) {
            ImageIcon iconoOriginal = new ImageIcon(imgURL);

            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(
                    lblImagen.getWidth(),
                    lblImagen.getHeight(),
                    Image.SCALE_SMOOTH
            );

            lblImagen.setIcon(new ImageIcon(imagenEscalada));
        } else {
            JOptionPane.showMessageDialog(this,
                    "No se encontró la imagen:\n" + ruta,
                    "Error de imagen",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new IMCApp().setVisible(true);
    }
}
