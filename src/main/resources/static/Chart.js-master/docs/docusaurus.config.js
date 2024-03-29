// VERSION replaced by deploy script
module.exports = {
  title: 'Chart.js',
  tagline: 'Open source HTML5 Charts for your website',
  url: 'https://chartjs.org',
  baseUrl: '/docs/VERSION/',
  favicon: 'img/favicon.ico',
  organizationName: 'chartjs', // Usually your GitHub org/user name.
  projectName: 'chartjs.github.io', // Usually your repo name.
  scripts: ['https://www.chartjs.org/dist/VERSION/chart.min.js'],
  themes: ['@docusaurus/theme-live-codeblock'],
  onBrokenLinks: 'warn',
  themeConfig: {
    image: 'img/logo.png',
    metadatas: [{name: 'twitter:card', content: 'summary'}],
    algolia: {
      apiKey: 'd7ee00a3cbaaf3c33e28ad1c274e7ba6',
      indexName: 'chartjs',
      algoliaOptions: {
        facetFilters: ['version:VERSION'],
      }
    },
    googleAnalytics: {
      trackingID: 'UA-28909194-3',
      // Optional fields.
      anonymizeIP: true, // Should IPs be anonymized?
    },
    colorMode: {
      disableSwitch: true, // Would need to implement for Charts embedded in docs
    },
    navbar: {
      title: 'Chart.js',
      logo: {
        alt: 'Chart.js Logo',
        src: 'img/logo.svg',
      },
    },
    footer: {
      style: 'dark',
      links: [
        {
          title: 'Other Docs',
          items: [
            {
              label: 'Samples',
              href: 'https://www.chartjs.org/samples/VERSION/',
            },
            {
              label: 'v2 Docs',
              href: 'https://www.chartjs.org/docs/2.9.4/',
            },
          ],
        },
        {
          title: 'Community',
          items: [
            {
              label: 'Slack',
              href: 'https://chartjs-slack.herokuapp.com/',
            },
            {
              label: 'Stack Overflow',
              href: 'https://stackoverflow.com/questions/tagged/chart.js',
            },
          ],
        },
        {
          title: 'Developers',
          items: [
            {
              label: 'GitHub',
              href: 'https://github.com/chartjs/Chart.js',
            },
            {
              label: 'Contributing',
              to: 'developers/contributing',
            },
          ],
        },
      ],
      copyright: `Copyright © ${new Date().getFullYear()} Chart.js contributors.`,
    },
  },
  presets: [
    [
      '@docusaurus/preset-classic',
      {
        docs: {
          sidebarPath: require.resolve('./sidebars.js'),
          routeBasePath: '/',
          editUrl: 'https://github.com/chartjs/Chart.js/edit/master/docs/',
        },
        theme: {
          customCss: require.resolve('./src/css/custom.css'),
        },
      },
    ],
  ],
};
