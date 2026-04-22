<h2>Share a Study Resource</h2>
<form action="ResourceServlet" method="POST">
    <label>Select Subject:</label>
    <select name="subject">
        <option value="Java">Java</option>
        <option value="Operating Systems">Operating Systems</option>
        <option value="Software Engineering">Software Engineering</option>
    </select><br><br>

    <label>Short Description (e.g., Deadlock Notes):</label>
    <input type="text" name="description" required><br><br>

    <label>Resource URL (YouTube/Drive Link):</label>
    <input type="url" name="link_url" required><br><br>

    <input type="hidden" name="action" value="add">
    <button type="submit">Submit Resource</button>
</form>

<br>
<a href="ResourceServlet?action=view">View The Bulletin Board</a>