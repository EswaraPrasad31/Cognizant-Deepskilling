const React = require('react');
global.React = React;

const { TextEncoder, TextDecoder } = require('util');
const { ReadableStream, WritableStream, TransformStream } = require('stream/web');
const { MessageChannel, MessagePort } = require('worker_threads');

global.TextEncoder = TextEncoder;
global.TextDecoder = TextDecoder;
if (typeof global.ReadableStream === 'undefined') {
  global.ReadableStream = ReadableStream;
}
if (typeof global.WritableStream === 'undefined') {
  global.WritableStream = WritableStream;
}
if (typeof global.TransformStream === 'undefined') {
  global.TransformStream = TransformStream;
}
if (typeof global.MessageChannel === 'undefined') {
  global.MessageChannel = MessageChannel;
}
if (typeof global.MessagePort === 'undefined') {
  global.MessagePort = MessagePort;
}

const Enzyme = require('enzyme');
const AdapterModule = require('@cfaester/enzyme-adapter-react-18');
const Adapter = AdapterModule.default || AdapterModule;

Enzyme.configure({ adapter: new Adapter() });
