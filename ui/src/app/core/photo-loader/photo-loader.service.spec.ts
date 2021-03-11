/*
 * Copyright (c) 2020 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { LoadingPhotoService } from './photo-loader.service';

describe('LoadingPhotoService', () => {
  let service: LoadingPhotoService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [LoadingPhotoService],
    });

    service = TestBed.inject(LoadingPhotoService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should be types check', () => {
    service.imageType.forEach((value: string) => {
      const file = new File([''], 'image', { type: value });

      expect(service.loader(file)).toBeTruthy();
    });
  });

  it('should be type check that is not included in list for example: image/x-jg', () => {
    const file = new File([''], 'image', { type: 'image/x-jg' });

    expect(service.loader(file)).toBeUndefined();
  });

  it('should be type check that is not included in list for example: text/html', () => {
    const file = new File([''], 'text', { type: 'text/html' });

    expect(service.loader(file)).toBeUndefined();
  });
});
