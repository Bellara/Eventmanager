package whs.bocholt.Eventmanager.services;

/**
 * Created by Maren on 20.11.14.
 */
public class JSONTest {

    public static final StringBuilder GET_ALL_INVITATIONS = new StringBuilder(
            "{\n" +
            "    \"result\": {\n" +
            "        \"status\":\"success\",\n" +
            "        \"message\":\"\"\n" +
            "    },\n" +
            "\n" +
            "    \"data\": {\n" +
            "        \"invitations\": [\n" +
            "            {\n" +
            "                \"event\": {\n" +
            "                    \"eid\":\"123456\",\n" +
            "                    \"bezeichnung\":\"asdfasdf\",\n" +
            "                    \"ort\":\"sfasdf\",\n" +
            "                    \"zeit\":\"2014-15-11 20:00\",\n" +
            "                    \"admin\": {\n" +
            "                        \"id\":\"1234567\",\n" +
            "                        \"name\": \"Breuersbrock\",\n" +
            "                        \"vorname\": \"Henning\",\n" +
            "                        \"mail\": \"test@test.de\"\n" +
            "                    }\n" +
            "                },\n" +
            "            \"status\":\"1\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"event\": {\n" +
            "                    \"eid\":\"456789\",\n" +
            "                    \"bezeichnung\":\"asdfasdf\",\n" +
            "                    \"ort\":\"sfasdf\",\n" +
            "                    \"zeit\":\"2014-15-11 20:00\",\n" +
            "                    \"admin\": {\n" +
            "                        \"id\":\"1234567\",\n" +
            "                        \"name\": \"Breuersbrock\",\n" +
            "                        \"vorname\": \"Henning\",\n" +
            "                        \"mail\": \"test@test.de\"\n" +
            "                    }\n" +
            "                },\n" +
            "            \"status\": \"0\"\n" +
            "            }\n" +
            "\n" +
            "        ]\n" +
            "    }\n" +
            "}");
}
