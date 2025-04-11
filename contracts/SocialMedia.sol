// contracts/SocialMedia.sol
// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract SocialMedia {
    struct Post {
        address author;
        string contentHash; // Encrypted content IPFS hash or encrypted directly
        uint timestamp;
    }

    Post[] public posts;

    event PostCreated(uint id, address author, string contentHash, uint timestamp);

    function createPost(string memory contentHash) public {
        posts.push(Post(msg.sender, contentHash, block.timestamp));
        emit PostCreated(posts.length - 1, msg.sender, contentHash, block.timestamp);
    }

    function getPost(uint index) public view returns (address, string memory, uint) {
        Post memory p = posts[index];
        return (p.author, p.contentHash, p.timestamp);
    }

    function getTotalPosts() public view returns (uint) {
        return posts.length;
    }
}
