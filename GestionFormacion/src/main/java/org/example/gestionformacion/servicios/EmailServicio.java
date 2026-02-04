package org.example.gestionformacion.servicios;

import org.example.gestionformacion.modelos.Alumno;
import org.example.gestionformacion.modelos.Empresa;
import org.example.gestionformacion.modelos.Practica;
import org.example.gestionformacion.repositorios.AlumnoRepositorio;
import org.example.gestionformacion.repositorios.EmpresaRepositorio;
import org.example.gestionformacion.repositorios.PracticaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServicio {


    private final AlumnoRepositorio alumnoRepositorio;

    private final EmpresaRepositorio empresaRepositorio;


    private final PracticaRepositorio practicaRepositorio;

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public EmailServicio(AlumnoRepositorio alumnoRepositorio, EmpresaRepositorio empresaRepositorio,
                         PracticaRepositorio practicaRepositorio, JavaMailSender mailSender) {
        this.alumnoRepositorio = alumnoRepositorio;
        this.empresaRepositorio = empresaRepositorio;
        this.practicaRepositorio = practicaRepositorio;
        this.mailSender = mailSender;
    }

    public void envioEmail(String to, Integer idAlumno, Integer idEmpresa, Integer idPractica) {

        Alumno alumno = alumnoRepositorio.findById(idAlumno)
                .orElseThrow(()-> new RuntimeException("No existe un alumno con este ID."));

        Empresa empresa = empresaRepositorio.findById(idEmpresa)
                .orElseThrow(()-> new RuntimeException("No existe una empresa con este ID."));

        Practica practica = practicaRepositorio.findById(idPractica)
                .orElseThrow(()-> new RuntimeException("No existe una practica con este ID."));

        String subject = "Práctica a la fase de formación en una empresa";
        String message = "Buenas " + alumno.getNombre() + ", has sido elegido para realizar la formación en la empresa " + empresa.getNombre() +
                "\n A continuación le pasamos toda la informacion mas detallada" +
                ".\nNombre del tutor laboral: " + empresa.getNombreTutor() + "\n" + "Correo del tutor laboral: " + empresa.getEmailTutor() +
                "\nDescripción de la empresa: " + empresa.getDescripcion() + "\nFecha de comienzo es: " + practica.getFechaFin() +
                "\nPor último la fecha de fin es: " + practica.getFechaFin();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        try {
            mailSender.send(mailMessage);
        } catch (Exception e) {
            System.err.println("Error sending verification email: " + e.getMessage());
        }
    }
}
