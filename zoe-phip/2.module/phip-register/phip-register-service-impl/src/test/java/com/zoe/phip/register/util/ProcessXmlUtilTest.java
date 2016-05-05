package com.zoe.phip.register.util;

import com.zoe.phip.infrastructure.util.XmlBeanUtil;
import com.zoe.phip.infrastructure.util.XmlUtil;
import com.zoe.phip.register.model.EhrDataInfo;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by zengjiyang on 2016/4/13.
 */
public class ProcessXmlUtilTest {


    @Test
    public void testMixResponseXml() throws Exception {
        String xml = "<ProvideAndRegisterDocumentSetRequest xmlns=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "                                      xsi:schemaLocation=\"urn:hl7-org:v3 ../multicacheschemas/ProvideAndRegisterDocumentSetRequest.xsd\">\n" +
                "    <ID root=\"2.16.156.10011.0\" extension=\"C193FE3B-E71F-4D81-8B8B-9462F35E8D38\"/>\n" +
                "    <SourcePatientID>201507287435</SourcePatientID>\n" +
                "    <SourcePatientName>李云发</SourcePatientName>\n" +
                "    <HealthCardId>000000201507287435</HealthCardId>\n" +
                "    <IdentityId>350825201507287435</IdentityId>\n" +
                "    <Organization id=\"022103A005\">\n" +
                "        <Name>厦门大学附属第一医院</Name>\n" +
                "        <TelephoneNumber areaCode=\"022\" number=\"4448888\"/>\n" +
                "        <EmailAddress address=\"XXX@yy.com.cn\"/>\n" +
                "        <Address city=\"思明区\" country=\"中国\" postalCode=\"300002\" stateOrProvince=\"厦门市\"\n" +
                "                 street=\"云顶路\" streetNumber=\"1234\"/>\n" +
                "    </Organization>\n" +
                "    <RegistryPackage>\n" +
                "        <SubmissionSet targetObject=\"Document.1\" availabilityStatus=\"Submitted\">\n" +
                "            <SubmissionTime>2015-07-29T02:00:01Z</SubmissionTime>\n" +
                "            <UniqueId>1234567890</UniqueId>\n" +
                "            <SourceId>367098466511</SourceId>\n" +
                "            <Comments>脑缺血</Comments>\n" +
                "            <Title>门诊摘要</Title>\n" +
                "            <CreateTime>2015-07-29T03:27:29Z</CreateTime>\n" +
                "            <ServerOrganization>厦门大学附属第一医院</ServerOrganization>\n" +
                "            <EpisodeID>0201507287435</EpisodeID>\n" +
                "            <InTime>2015-07-28T10:43:15Z</InTime>\n" +
                "            <OutTime>2015-07-28T11:31:23Z</OutTime>\n" +
                "            <AdmissionDepart>内科</AdmissionDepart>\n" +
                "            <AdmissionDoctor>赵俊立1</AdmissionDoctor>\n" +
                "            <AdmissionType>门诊</AdmissionType>\n" +
                "            <DiagnosisResult>脑缺血</DiagnosisResult>\n" +
                "            <Author>\n" +
                "                <AuthorName>赵俊立</AuthorName>\n" +
                "                <AuthorInstitution>厦门大学附属第一医院</AuthorInstitution>\n" +
                "                <AuthorSpecialty>内科</AuthorSpecialty>\n" +
                "                <AuthorRole>三级专家</AuthorRole>\n" +
                "            </Author>\n" +
                "        </SubmissionSet>\n" +
                "    </RegistryPackage>\n" +
                "    <Document id=\"12345678\" mimeType=\"text/xml\" parentDocumentRelationship=\"APND\"\n" +
                "              parentDocumentId=\"AAC97D6B-75BC-4E6E-8174-EFC09C722A09\">\n" +
                "        <Content>\n" +
                "            PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iZ2IyMzEyIj8+PHJvb3QgdGltZT0iMjAxNS0wNy0yOSAwMDowMDowMCI+PG9yZz48aXRlbSBvcmdjb2RlPSIzNTAyMTFBMTAwMSIgb3JnbmFtZT0iz8PDxbTz0ae4vcr0tdrSu9K91LoiIC8+PC9vcmc+PGFjdGl2ZXBhdGllbnQ+PGl0ZW0gZGF0ZXRpbWU9IjIwMTUtMDctMjkgMTY6MzA6MzAiIG9yZ2NvZGU9IjM1MDIxMUExMDAxIiBvcmduYW1lPSLPw8PFtPPRp7i9yvS12tK70r3UuiIgY2FyZF9ubz0iMjAxNTA3Mjg3NDM1IiBuYW1lPSLA7tTGt6IiIHNleF9jb2RlPSIxIiBzZXhfbmFtZT0ixNDQ1CIgYmlydGhfZGF0ZT0iIiBsYXN0X3VwZGF0ZV9kdGltZT0iMjAxNS83LzI5IDI6MDA6MDEiIG9yZ19jb2RlPSIzNTAyMTFBMTAwMSIgcGF0aWVudF9pZD0iMjAxNTA3Mjg3NDM1IiBldmVudF90eXBlPSIxIiBldmVudF9ubz0iMjAxNTA3Mjg3NDM1IiByZXRyaWV2ZV9kYXRlPSIyMDE1LzcvMjkgMjowMDowMSIgc3RhcnRfZGF0ZT0iMjAxNS83LzI4IDEwOjQzOjE1IiBlbmRfZGF0ZT0iMjAxNS83LzI4IDExOjMxOjIzIiBmZWU9IjM4MC4wMCIgaWNkPSIgIiBkaWFnbm9zaXM9IsTUyLHRqiIgYWRkcmVzc19jb2RlPSIiIGFkZHJlc3M9IiIgdGVsX25vPSIiIG1hcnJpYWdlX2NvZGU9IiIgbWFycmlhZ2VfbmFtZT0iIiBvbnNldF9kdGltZT0iIiBkaWFnbm9zaXNfZGF0ZT0iMjAxNS83LzI4IDEwOjQzOjE1IiBjcmVhdGVfdGltZT0iMjAxNS83LzI5IDM6MDI6MDkiIC8+PC9hY3RpdmVwYXRpZW50PjxwYXRpZW50PjxpdGVtIGxhc3RfdXBkYXRlX2R0aW1lPSIyMDE1LzcvMjkgMjowMDowMSIgb3JnX2NvZGU9IjM1MDIxMUExMDAxIiBwYXRpZW50X2lkPSIyMDE1MDcyODc0MzUiIG91dHBhdF9mb3JtX25vPSIyMDE1MDcyODc0MzUiIGhlYWx0aF9yZWNvcmRfbm89IiIgbmFtZT0iwO7UxreiIiBzZXhfY29kZT0iMSIgc2V4X25hbWU9IsTQ0NQiIGJpcnRoX2RhdGU9IiIgbWFycmlhZ2VfY29kZT0iIiBtYXJyaWFnZV9uYW1lPSIiIGlkX3R5cGVfY29kZT0iOTkiIGlkX3R5cGVfbmFtZT0ivtPD8cntt93WpCIgaWRfbm89IjIwMTUwNzI4NzQzNSIgdmlzaXRfb3JnX25hbWU9Is/Dw8W089GnuL3K9LXa0rvSvdS6IiB2aXNpdF9vcmdfY29kZT0iNDAxMzU0MzEiIHZpc2l0X2RlcHRfbmFtZT0ixNq/xiIgdmlzaXRfZHRpbWU9IjIwMTUvNy8yOCAxMTozMToyMyIgcmVmZXJyYWxfbWFyaz0iIiByZWZlcnJhbF9tYXJrX25hbWU9IiIgY29uc3VsdF9xdWVzdGlvbj0iIiBoZWFsdGhfc2VydmljZV9kZW1hbmQ9IiIgaGVhbHRoX3Byb2JsZW1fZXZhbD0iIiB0cmVhdG1lbnRfcGxhbj0iIiBvdGhlcl9tZWRpY2FsX3RyZWF0bWVudD0iIiByZXNwX2RvY3Rvcl9uYW1lPSLV1L+hwaIiIHNicD0iIiBkYnA9IiIgY3JlYXRlX3RpbWU9IjIwMTUvNy8yOSAzOjI3OjI5IiByZWdfY29kZT0iMjAxNTA3Mjg3NDM1IiAvPjwvcGF0aWVudD48c3ltcHRvbSAvPjx0ZXN0IC8+PGRpYWdub3Npcz48aXRlbSBsYXN0X3VwZGF0ZV9kdGltZT0iMjAxNS83LzI5IDI6MDA6MDEiIG9yZ19jb2RlPSIzNTAyMTFBMTAwMSIgb3V0cGF0X2Zvcm1fbm89IjIwMTUwNzI4NzQzNSIgb3V0cGF0X2RpYWdfbmFtZT0ixNTIsdGqIiBvdXRwYXRfZGlhZ19jb2RlPSIgIiBkaWFnX2RhdGU9IjIwMTUvNy8yOCAxMTozMToyMyIgZGlhZ25vc2lzX2lkPSIxIiBjcmVhdGVfdGltZT0iMjAxNS83LzI5IDM6Mjc6NTkiIC8+PC9kaWFnbm9zaXM+PGRydWcgLz48ZmVlPjxpdGVtIGxhc3RfdXBkYXRlX2R0aW1lPSIyMDE1LzcvMjkgMjowMDowMSIgb3JnX2NvZGU9IjM1MDIxMUExMDAxIiBvdXRwYXRfZm9ybV9ubz0iMjAxNTA3Mjg3NDM1IiBvdXRwYXRfZmVlX3R5cGVfY29kZT0iMDQiIG91dHBhdF9mZWVfdHlwZV9uYW1lPSK87LLpt9EiIG91dHBhdF9mZWVfYW1vdW50PSIxMjAuMDAiIHBheV93YXlfY29kZT0iOTkiIHBheV93YXlfbmFtZT0ixuTL+yIgb3V0cGF0X3NldHRsZV93YXlfY29kZT0iMDEiIG91dHBhdF9zZXR0bGVfd2F5X25hbWU9Is/WvfAiIHByaWNlX2l0ZW1fbG9jYWxfbmFtZT0ivq3CrbbgxtXA1SIgcHJpY2VfaXRlbV9sb2NhbF9jb2RlPSIwMDgwMDAxMSIgcHJpY2VfaXRlbV9zdGRfbmFtZT0ivq3CrbbgxtXA1SIgcHJpY2VfaXRlbV9zdGRfY29kZT0iMDAyODc4IiBpZD0iY3oxMzA3NDA5OCIgY3JlYXRlX3RpbWU9IjIwMTUvNy8yOSAzOjMwOjQ2IiAvPjxpdGVtIGxhc3RfdXBkYXRlX2R0aW1lPSIyMDE1LzcvMjkgMjowMDowMSIgb3JnX2NvZGU9IjM1MDIxMUExMDAxIiBvdXRwYXRfZm9ybV9ubz0iMjAxNTA3Mjg3NDM1IiBvdXRwYXRfZmVlX3R5cGVfY29kZT0iMDQiIG91dHBhdF9mZWVfdHlwZV9uYW1lPSK87LLpt9EiIG91dHBhdF9mZWVfYW1vdW50PSIxMzAuMDAiIHBheV93YXlfY29kZT0iOTkiIHBheV93YXlfbmFtZT0ixuTL+yIgb3V0cGF0X3NldHRsZV93YXlfY29kZT0iMDEiIG91dHBhdF9zZXR0bGVfd2F5X25hbWU9Is/WvfAiIHByaWNlX2l0ZW1fbG9jYWxfbmFtZT0ivrGyv9GqudyyysmrtuDG1cDVs6zJ+cG9uPnRqrncIiBwcmljZV9pdGVtX2xvY2FsX2NvZGU9IjAwODAwMDQ3IiBwcmljZV9pdGVtX3N0ZF9uYW1lPSK+sbK/0aq53LLKyau24MbVwNazrMn5IiBwcmljZV9pdGVtX3N0ZF9jb2RlPSIyMDgxMDQiIGlkPSJjejEzMDc0MDk5IiBjcmVhdGVfdGltZT0iMjAxNS83LzI5IDM6MzA6NDYiIC8+PGl0ZW0gbGFzdF91cGRhdGVfZHRpbWU9IjIwMTUvNy8yOSAyOjAwOjAxIiBvcmdfY29kZT0iMzUwMjExQTEwMDEiIG91dHBhdF9mb3JtX25vPSIyMDE1MDcyODc0MzUiIG91dHBhdF9mZWVfdHlwZV9jb2RlPSIwNCIgb3V0cGF0X2ZlZV90eXBlX25hbWU9Irzssum30SIgb3V0cGF0X2ZlZV9hbW91bnQ9IjcwLjAwIiBwYXlfd2F5X2NvZGU9Ijk5IiBwYXlfd2F5X25hbWU9Isbky/siIG91dHBhdF9zZXR0bGVfd2F5X2NvZGU9IjAxIiBvdXRwYXRfc2V0dGxlX3dheV9uYW1lPSLP1r3wIiBwcmljZV9pdGVtX2xvY2FsX25hbWU9Ir6xsr/RqrncssrJq7bgxtXA1bOsyfnU9rzTwb24+SIgcHJpY2VfaXRlbV9sb2NhbF9jb2RlPSIwMDgwMDA0OCIgcHJpY2VfaXRlbV9zdGRfbmFtZT0ivrGyv9GqudyyysmrtuDG1cDWs6zJ+aOow7/U9rzTMrj5vNPK1aPAIiBwcmljZV9pdGVtX3N0ZF9jb2RlPSIyMDgxMDUiIGlkPSJjejEzMDc0MTAwIiBjcmVhdGVfdGltZT0iMjAxNS83LzI5IDM6MzA6NDYiIC8+PGl0ZW0gbGFzdF91cGRhdGVfZHRpbWU9IjIwMTUvNy8yOSAyOjAwOjAxIiBvcmdfY29kZT0iMzUwMjExQTEwMDEiIG91dHBhdF9mb3JtX25vPSIyMDE1MDcyODc0MzUiIG91dHBhdF9mZWVfdHlwZV9jb2RlPSIxNCIgb3V0cGF0X2ZlZV90eXBlX25hbWU9IrLEwc+30SIgb3V0cGF0X2ZlZV9hbW91bnQ9IjQwLjAwIiBwYXlfd2F5X2NvZGU9Ijk5IiBwYXlfd2F5X25hbWU9Isbky/siIG91dHBhdF9zZXR0bGVfd2F5X2NvZGU9IjAxIiBvdXRwYXRfc2V0dGxlX3dheV9uYW1lPSLP1r3wIiBwcmljZV9pdGVtX2xvY2FsX25hbWU9IrLKyavN0MCtxqwiIHByaWNlX2l0ZW1fbG9jYWxfY29kZT0iMDE4MDA0NDciIHByaWNlX2l0ZW1fc3RkX25hbWU9IrLKyavN0MCtxqwiIHByaWNlX2l0ZW1fc3RkX2NvZGU9IjIwNzU0OCIgaWQ9ImN6MTMwNzQxMDEiIGNyZWF0ZV90aW1lPSIyMDE1LzcvMjkgMzozMDo0NiIgLz48aXRlbSBsYXN0X3VwZGF0ZV9kdGltZT0iMjAxNS83LzI5IDI6MDA6MDEiIG9yZ19jb2RlPSIzNTAyMTFBMTAwMSIgb3V0cGF0X2Zvcm1fbm89IjIwMTUwNzI4NzQzNSIgb3V0cGF0X2ZlZV90eXBlX2NvZGU9IjE0IiBvdXRwYXRfZmVlX3R5cGVfbmFtZT0issTBz7fRIiBvdXRwYXRfZmVlX2Ftb3VudD0iMjAuMDAiIHBheV93YXlfY29kZT0iOTkiIHBheV93YXlfbmFtZT0ixuTL+yIgb3V0cGF0X3NldHRsZV93YXlfY29kZT0iMDEiIG91dHBhdF9zZXR0bGVfd2F5X25hbWU9Is/WvfAiIHByaWNlX2l0ZW1fbG9jYWxfbmFtZT0issq08ta9MSIgcHJpY2VfaXRlbV9sb2NhbF9jb2RlPSIwMDgwMDAyNSIgcHJpY2VfaXRlbV9zdGRfbmFtZT0iwNu8xtT2uLq1xNK908OyxMHPIiBwcmljZV9pdGVtX3N0ZF9jb2RlPSIwMDUzMzMiIGlkPSJjejEzMDc0MDk3IiBjcmVhdGVfdGltZT0iMjAxNS83LzI5IDM6MzA6NDUiIC8+PGl0ZW0gbGFzdF91cGRhdGVfZHRpbWU9IjIwMTUvNy8yOSAyOjAwOjAxIiBvcmdfY29kZT0iMzUwMjExQTEwMDEiIG91dHBhdF9mb3JtX25vPSIyMDE1MDcyODc0MzUiIG91dHBhdF9mZWVfdHlwZV9jb2RlPSIxNSIgb3V0cGF0X2ZlZV90eXBlX25hbWU9IrnSusW30SIgb3V0cGF0X2ZlZV9hbW91bnQ9IjMuMDAiIHBheV93YXlfY29kZT0iOTkiIHBheV93YXlfbmFtZT0ixuTL+yIgb3V0cGF0X3NldHRsZV93YXlfY29kZT0iMDEiIG91dHBhdF9zZXR0bGVfd2F5X25hbWU9Is/WvfAiIHByaWNlX2l0ZW1fbG9jYWxfbmFtZT0iudK6xbfRIiBwcmljZV9pdGVtX2xvY2FsX2NvZGU9Ijk5IiBwcmljZV9pdGVtX3N0ZF9uYW1lPSK50rrFt9EiIHByaWNlX2l0ZW1fc3RkX2NvZGU9Ijk5IiBpZD0iZ2gyMDE1MDcyODc0MzUgICAgOTkiIGNyZWF0ZV90aW1lPSIyMDE1LzcvMjkgMzozMTo0MiIgLz48L2ZlZT48b3BlcmF0aW9uIC8+PC9yb290Pg==\n" +
                "        </Content>\n" +
                "    </Document>\n" +
                "</ProvideAndRegisterDocumentSetRequest>";
        Document document = ProcessXmlUtil.load(xml);
        String errorMsg = "";
        String adapterPath = "/template/ehr/input/adapter/EhrRegisterAdapter.xml";
        EhrDataInfo baseInfo = null;

            SAXReader reader = new SAXReader();
            //XmanBaseInfo
            Document parserDoc = reader.read(this.getClass().getResourceAsStream(adapterPath));
            baseInfo = XmlBeanUtil.toBean(document, EhrDataInfo.class, parserDoc);



    }

    public void testXsdValidate() {
    }
}