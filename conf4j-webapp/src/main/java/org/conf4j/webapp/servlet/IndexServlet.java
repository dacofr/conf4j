package org.conf4j.webapp.servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.conf4j.sample.SampleWithConf;

@WebServlet(urlPatterns = "/index", name = "IndexServlet")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 8712805354125783121L;

    private final SampleWithConf scv = new SampleWithConf();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Writer out = resp.getWriter();
        resp.setContentType("text/html");
        out.append("<html>");
        out.append("<head>");
        out.append("<style type=\"text/css\">body { font-family: helvetica, sans-serif, Arial; font-size: 14px; }</style>");
        out.append("</head>");
        out.append("<body>");
        
        out.append("<a href=\"dump\">dump</a> | <a href=\"fulldump\">fulldump</a> | <a href=\"scope\">check scope</a> | <a href=\"unused\">check unused</a>");
        out.append("<hr>");

        out.append("<a href=\"");
        out.append(scv.getDevoxxHome());
        out.append("\">");
        out.append(scv.getDevoxxHome());
        out.append("</a><br>");

        out.append("<a href=\"");
        out.append(scv.getDevoxxUnitTest());
        out.append("\">");
        out.append(scv.getDevoxxUnitTest());
        out.append("</a><hr>");

        out.append("</body>");
        out.append("</html>");
        out.close();
    }

}
