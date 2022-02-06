import Document, { Html, Head, Main, NextScript } from "next/document";

export default class MyDocument extends Document {
  render() {
    return (
      <Html>
        <Head>
          {/* Site Tag */}
          <script
            dangerouslySetInnerHTML={{
              __html: `
              !function(){var e=window.rudderanalytics=window.rudderanalytics||[];e.methods=["load","page","track","identify","alias","group","ready","reset","getAnonymousId","setAnonymousId"],e.factory=function(t){return function(){var r=Array.prototype.slice.call(arguments);return r.unshift(t),e.push(r),e}};for(var t=0;t<e.methods.length;t++){var r=e.methods[t];e[r]=e.factory(r)}e.loadJS=function(e,t){var r=document.createElement("script");r.type="text/javascript",r.async=!0,r.src="https://cdn.rudderlabs.com/v1/rudder-analytics.min.js";var a=document.getElementsByTagName("script")[0];a.parentNode.insertBefore(r,a)},e.loadJS(),
              e.load("1pBSDA8DDqVgLRzxN5JWyKJQoNN","https://hosted.rudderlabs.com"),
              e.ready(() => {
                  console.log("We are set!!!")
              })
          }();
          `
            }}
          />
        </Head>
        <body>
          <Main />
          <NextScript />
        </body>
      </Html>
    );
  }
}
