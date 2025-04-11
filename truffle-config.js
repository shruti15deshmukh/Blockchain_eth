const path = require("path");

module.exports = {
  networks: {
    development: {
      host: "127.0.0.1", // Ganache
      port: 8550,
      network_id: "*",   // Match any network id
    },
  },

  // Set Solidity version
  compilers: {
    solc: {
      version: "0.8.20", // Match your contract pragma
    },
  },

  contracts_build_directory: path.join(__dirname, "build/contracts"),
};
