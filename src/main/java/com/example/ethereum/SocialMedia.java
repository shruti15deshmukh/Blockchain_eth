package com.example.ethereum;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/hyperledger-web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.6.3.
 */
@SuppressWarnings("rawtypes")
public class SocialMedia extends Contract {
    public static final String BINARY = "608060405234801561000f575f80fd5b50610afe8061001d5f395ff3fe608060405234801561000f575f80fd5b506004361061004a575f3560e01c80630b1e7f831461004e57806340731c24146100805780635fe226b4146100b2578063c7303c61146100d0575b5f80fd5b6100686004803603810190610063919061045d565b6100ec565b60405161007793929190610560565b60405180910390f35b61009a6004803603810190610095919061045d565b6101c5565b6040516100a993929190610560565b60405180910390f35b6100ba610300565b6040516100c7919061059c565b60405180910390f35b6100ea60048036038101906100e591906106e1565b61030b565b005b5f81815481106100fa575f80fd5b905f5260205f2090600302015f91509050805f015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff169080600101805461013e90610755565b80601f016020809104026020016040519081016040528092919081815260200182805461016a90610755565b80156101b55780601f1061018c576101008083540402835291602001916101b5565b820191905f5260205f20905b81548152906001019060200180831161019857829003601f168201915b5050505050908060020154905083565b5f60605f805f85815481106101dd576101dc610785565b5b905f5260205f2090600302016040518060600160405290815f82015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200160018201805461025890610755565b80601f016020809104026020016040519081016040528092919081815260200182805461028490610755565b80156102cf5780601f106102a6576101008083540402835291602001916102cf565b820191905f5260205f20905b8154815290600101906020018083116102b257829003601f168201915b505050505081526020016002820154815250509050805f015181602001518260400151935093509350509193909250565b5f8080549050905090565b5f60405180606001604052803373ffffffffffffffffffffffffffffffffffffffff16815260200183815260200142815250908060018154018082558091505060019003905f5260205f2090600302015f909190919091505f820151815f015f6101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160010190816103bc919061094f565b506040820151816002015550507f285db98a4632176385795ddf0d2b67cef1a21f47eb2f5d06f786ab6b5147e20060015f805490506103fb9190610a4b565b33834260405161040e9493929190610a7e565b60405180910390a150565b5f604051905090565b5f80fd5b5f80fd5b5f819050919050565b61043c8161042a565b8114610446575f80fd5b50565b5f8135905061045781610433565b92915050565b5f6020828403121561047257610471610422565b5b5f61047f84828501610449565b91505092915050565b5f73ffffffffffffffffffffffffffffffffffffffff82169050919050565b5f6104b182610488565b9050919050565b6104c1816104a7565b82525050565b5f81519050919050565b5f82825260208201905092915050565b5f5b838110156104fe5780820151818401526020810190506104e3565b5f8484015250505050565b5f601f19601f8301169050919050565b5f610523826104c7565b61052d81856104d1565b935061053d8185602086016104e1565b61054681610509565b840191505092915050565b61055a8161042a565b82525050565b5f6060820190506105735f8301866104b8565b81810360208301526105858185610519565b90506105946040830184610551565b949350505050565b5f6020820190506105af5f830184610551565b92915050565b5f80fd5b5f80fd5b7f4e487b71000000000000000000000000000000000000000000000000000000005f52604160045260245ffd5b6105f382610509565b810181811067ffffffffffffffff82111715610612576106116105bd565b5b80604052505050565b5f610624610419565b905061063082826105ea565b919050565b5f67ffffffffffffffff82111561064f5761064e6105bd565b5b61065882610509565b9050602081019050919050565b828183375f83830152505050565b5f61068561068084610635565b61061b565b9050828152602081018484840111156106a1576106a06105b9565b5b6106ac848285610665565b509392505050565b5f82601f8301126106c8576106c76105b5565b5b81356106d8848260208601610673565b91505092915050565b5f602082840312156106f6576106f5610422565b5b5f82013567ffffffffffffffff81111561071357610712610426565b5b61071f848285016106b4565b91505092915050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52602260045260245ffd5b5f600282049050600182168061076c57607f821691505b60208210810361077f5761077e610728565b5b50919050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52603260045260245ffd5b5f819050815f5260205f209050919050565b5f6020601f8301049050919050565b5f82821b905092915050565b5f6008830261080e7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff826107d3565b61081886836107d3565b95508019841693508086168417925050509392505050565b5f819050919050565b5f61085361084e6108498461042a565b610830565b61042a565b9050919050565b5f819050919050565b61086c83610839565b6108806108788261085a565b8484546107df565b825550505050565b5f90565b610894610888565b61089f818484610863565b505050565b5b818110156108c2576108b75f8261088c565b6001810190506108a5565b5050565b601f821115610907576108d8816107b2565b6108e1846107c4565b810160208510156108f0578190505b6109046108fc856107c4565b8301826108a4565b50505b505050565b5f82821c905092915050565b5f6109275f198460080261090c565b1980831691505092915050565b5f61093f8383610918565b9150826002028217905092915050565b610958826104c7565b67ffffffffffffffff811115610971576109706105bd565b5b61097b8254610755565b6109868282856108c6565b5f60209050601f8311600181146109b7575f84156109a5578287015190505b6109af8582610934565b865550610a16565b601f1984166109c5866107b2565b5f5b828110156109ec578489015182556001820191506020850194506020810190506109c7565b86831015610a095784890151610a05601f891682610918565b8355505b6001600288020188555050505b505050505050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52601160045260245ffd5b5f610a558261042a565b9150610a608361042a565b9250828203905081811115610a7857610a77610a1e565b5b92915050565b5f608082019050610a915f830187610551565b610a9e60208301866104b8565b8181036040830152610ab08185610519565b9050610abf6060830184610551565b9594505050505056fea26469706673582212200aee9ea179932695548707b1af227fa9a67ce1f8c821d0ebc437842df21c8b6664736f6c63430008140033";

    private static String librariesLinkedBinary;

    public static final String FUNC_POSTS = "posts";

    public static final String FUNC_CREATEPOST = "createPost";

    public static final String FUNC_GETPOST = "getPost";

    public static final String FUNC_GETTOTALPOSTS = "getTotalPosts";

    public static final Event POSTCREATED_EVENT = new Event("PostCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected SocialMedia(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SocialMedia(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected SocialMedia(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected SocialMedia(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<PostCreatedEventResponse> getPostCreatedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(POSTCREATED_EVENT, transactionReceipt);
        ArrayList<PostCreatedEventResponse> responses = new ArrayList<PostCreatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PostCreatedEventResponse typedResponse = new PostCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.author = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.contentHash = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static PostCreatedEventResponse getPostCreatedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(POSTCREATED_EVENT, log);
        PostCreatedEventResponse typedResponse = new PostCreatedEventResponse();
        typedResponse.log = log;
        typedResponse.id = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.author = (String) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.contentHash = (String) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
        return typedResponse;
    }

    public Flowable<PostCreatedEventResponse> postCreatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getPostCreatedEventFromLog(log));
    }

    public Flowable<PostCreatedEventResponse> postCreatedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(POSTCREATED_EVENT));
        return postCreatedEventFlowable(filter);
    }

    public RemoteFunctionCall<Tuple3<String, String, BigInteger>> posts(BigInteger param0) {
        final Function function = new Function(FUNC_POSTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple3<String, String, BigInteger>>(function,
                new Callable<Tuple3<String, String, BigInteger>>() {
                    @Override
                    public Tuple3<String, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> createPost(String contentHash) {
        final Function function = new Function(
                FUNC_CREATEPOST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(contentHash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple3<String, String, BigInteger>> getPost(BigInteger index) {
        final Function function = new Function(FUNC_GETPOST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple3<String, String, BigInteger>>(function,
                new Callable<Tuple3<String, String, BigInteger>>() {
                    @Override
                    public Tuple3<String, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> getTotalPosts() {
        final Function function = new Function(FUNC_GETTOTALPOSTS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static SocialMedia load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new SocialMedia(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static SocialMedia load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SocialMedia(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static SocialMedia load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new SocialMedia(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static SocialMedia load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new SocialMedia(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<SocialMedia> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SocialMedia.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SocialMedia> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SocialMedia.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<SocialMedia> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(SocialMedia.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<SocialMedia> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SocialMedia.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }
    private static String getDevelopmentBinary(){
        return BINARY;
    }

//    public static void linkLibraries(List<Contract.LinkReference> references) {
//        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
//    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class PostCreatedEventResponse extends BaseEventResponse {
        public BigInteger id;

        public String author;

        public String contentHash;

        public BigInteger timestamp;
    }
}
