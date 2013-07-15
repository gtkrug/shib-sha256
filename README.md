shib-sha256
===========

Simple crypto config for Shibboleth IDP that defaults SAML signing and digest algorithms to SHA-256.

You may want to instead use the official Shibboleth cyrpto extension module:

https://wiki.shibboleth.net/confluence/display/SHIB2/Changing+IdP+Signature+Method+Algorithm


Install
============

1)To add the extension code to the IdP:

    a) Add the extension project jar to the IdP source distribution's lib directory.

    b) Re-run the IdP install script to build and deploy the new WAR file.

2) To activate the extension code: In the IdP's conf/internal.xml, add the following new bean declaration:

    <bean id="OpensamlSha256Config" class="net.gfipm.cryptoconfig.OpensamlSha256ConfigBean" depends-on="shibboleth.OpensamlConfig" /> 


Build Instructions
============

If you want to build, the project is a standard maven project and should build with 'mvn clean install'.

