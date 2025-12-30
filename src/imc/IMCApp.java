package imc;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class IMCApp extends JFrame {

    private JTextField txtNombre, txtEdad, txtPeso, txtEstatura;
    private JTextArea txtResultado;

    public IMCApp() {
        setTitle("Calculadora de IMC");
        setSize(400, 400);
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
        txtResultado.setBounds(20, 230, 340, 100);
        txtResultado.setEditable(false);
        add(txtResultado);

        btnCalcular.addActionListener((ActionEvent e) -> calcularIMC());
    }

    private void calcularIMC() {
        try {
            String nombre = txtNombre.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            double peso = Double.parseDouble(txtPeso.getText());
            double estatura = Double.parseDouble(txtEstatura.getText());

            Paciente paciente = new Paciente(nombre, edad, peso, estatura);

            txtResultado.setText(
                "Paciente: " + paciente.getNombre() +
                "\nIMC: " + String.format("%.2f", paciente.calcularIMC()) +
                "\nDiagnóstico: " + paciente.interpretarIMC()
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Datos inválidos");
        }
    }

    public static void main(String[] args) {
        new IMCApp().setVisible(true);
    }
}
