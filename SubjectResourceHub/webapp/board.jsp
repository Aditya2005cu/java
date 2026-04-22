<%@ page import="java.sql.ResultSet" %>
<h2>Subject Resource Hub</h2>

<table border="1">
    <tr>
        <th>Subject</th>
        <th>Description</th>
        <th>Link</th>
        <th>Upvotes</th>
        <th>Action</th>
    </tr>
    <% 
        // The Servlet passes the ResultSet or List here
        ResultSet rs = (ResultSet) request.getAttribute("resourcesData");
        while(rs != null && rs.next()) { 
    %>
    <tr>
        <td><%= rs.getString("subject") %></td>
        <td><%= rs.getString("description") %></td>
        <td><a href="<%= rs.getString("link_url") %>" target="_blank">Open Link</a></td>
        <td><%= rs.getInt("upvotes") %></td>
        <td>
            <form action="ResourceServlet" method="POST">
                <input type="hidden" name="action" value="upvote">
                <input type="hidden" name="resource_id" value="<%= rs.getInt("id") %>">
                <button type="submit">+1 Upvote</button>
            </form>
        </td>
    </tr>
    <% } %>
</table>